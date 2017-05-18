/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.AddIncomeCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddSpendingCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class OperationSubAmountWizardPage extends AbstractAddWizardPage {

	private static final String ADD_PAGE_NAME="Add sub-amount to ''{0}'' Page"; //$NON-NLS-1$
	private static final String ADD_PAGE_TITLE="Add sub-amount"; //$NON-NLS-1$

	private static final String EDIT_PAGE_NAME="Edit sub-amount to ''{0}'' Page"; //$NON-NLS-1$
	private static final String EDIT_PAGE_TITLE="Edit sub-amount"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add or edit a new sub-amount to the selected operation."; //$NON-NLS-1$

	protected final Tracker tracker;
	protected final OperationData operation;
	protected final Amount amount;

	protected Category category;
	protected String value;
	protected LocalDate wishedDate;

	Tree categoriesTree;
	TreeViewer categoriesTreeViewer;

	private final ModifyListener modifyValueListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			value=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};
	private final SelectionAdapter addCategoryButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			TreeItem[] selection=categoriesTree.getSelection();
			Category newCategory=null;
			if (selection.length == 0) {
				newCategory=addTrackerCategory(tracker);
			}
			else {
				newCategory=addCategorySubCategory(tracker, (Category)selection[0].getData());
			}
			if (newCategory != null) {
				refreshTreeViewerContent(categoriesTreeViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getCategories()), newCategory);
			}
		}

		private Category addCategorySubCategory(Tracker tracker, Category category) {
			if (category instanceof IncomeCategory) {
				AddIncomeCategoryWizard wizard=new AddIncomeCategoryWizard("Categories Repository", //$NON-NLS-1$
						tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addIncomeCategory((IncomeCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			else if (category instanceof SpendingCategory) {
				AddSpendingCategoryWizard wizard=new AddSpendingCategoryWizard("Categories Repository", //$NON-NLS-1$
						tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addSpendingCategory((SpendingCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			return null;
		}

		private Category addTrackerCategory(Tracker tracker) {
			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard("Categories Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				if (wizard.isIncome()) {
					return addIncomeCategory(tracker.getCategoriesRepository().getIncome(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
				else if (wizard.isSpending()) {
					return addSpendingCategory(tracker.getCategoriesRepository().getSpending(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			return null;
		}

		private Category addSpendingCategory(SpendingCategory category, String title, String description) {
			Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();

			if (!StringUtils.isEmpty(title)) {
				newCategory.setTitle(title);
			}
			if (!StringUtils.isEmpty(description)) {
				newCategory.setDescription(description);
			}
			DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
			return newCategory;
		}

		private Category addIncomeCategory(IncomeCategory category, String title, String description) {
			Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();

			if (!StringUtils.isEmpty(title)) {
				newCategory.setTitle(title);
			}
			if (!StringUtils.isEmpty(description)) {
				newCategory.setDescription(description);
			}
			DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
			return newCategory;
		}
	};
	private final ISelectionChangedListener listener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Category) {
				category=(Category)firstElement;
			}
		}
	};
	private final ISelectionChangedListener categoryListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Category) {
				category=(Category)firstElement;
			}
		}
	};
	private final SelectionListener wishedDateKeyListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source=event.getSource();
			Assert.isTrue(source instanceof DateTime);
			DateTime date=(DateTime)source;
			wishedDate=LocalDate.of(date.getYear(), Month.of(date.getMonth()), date.getDay());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			// Do Nothing
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 * @param operation the operation
	 * @param amount the amount to edit if any, <code>null</code> otherwise.
	 * @param isAdd <code>true</code> if the action is a result of an Add action, <code>false</code> if is edit one
	 */
	public OperationSubAmountWizardPage(String pageTitle, Tracker tracker, OperationData operation, Amount amount, boolean isAdd) {
		super(MessageFormat.format(isAdd ? OperationSubAmountWizardPage.ADD_PAGE_NAME : OperationSubAmountWizardPage.EDIT_PAGE_NAME, pageTitle));
		this.tracker=tracker;
		this.operation=operation;
		this.amount=amount;
		setTitle(isAdd ? OperationSubAmountWizardPage.ADD_PAGE_TITLE : OperationSubAmountWizardPage.EDIT_PAGE_TITLE);
		setDescription(OperationSubAmountWizardPage.WIZARD_DESCRIPTION);
		if (amount == null) {
			return;
		}
		value=String.valueOf(amount.getValue());
		category=amount.getCategory();
		wishedDate=amount.getWishedDate();
	}

	@Override
	protected void createContainer(Composite parent) {
		Composite composite=new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		createText(composite, "Value: ", value, modifyValueListener); //$NON-NLS-1$

		Set<Category> categories=new HashSet<>(TrackerUtils.getTrackerService(tracker).getCategories()).stream()//
				.filter(category -> operation.getType() == OperationType.DEBIT && category instanceof SpendingCategory
									|| operation.getType() == OperationType.CREDIT && category instanceof IncomeCategory)//
				.collect(Collectors.toSet());
		categoriesTree=createTree(composite, "Category: ", addCategoryButtonlistener); //$NON-NLS-1$
		categoriesTreeViewer=createTreeViewer(categoriesTree, categoryListener);
		categoriesTreeViewer.setInput(new ArrayList<>(categories));
		categoriesTreeViewer.expandAll();
		if (category != null) {
			categoriesTreeViewer.setSelection(new StructuredSelection(category), true);
		}
		if (!categories.isEmpty()) {
			category=categories.iterator().next();
		}
		createDateTime(composite, "Wished Date", wishedDate, wishedDateKeyListener);//$NON-NLS-1$

		GridData data=new GridData();
		data.verticalAlignment=GridData.FILL;
		data.grabExcessVerticalSpace=true;
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=GridData.FILL;
		composite.setLayoutData(data);
	}

	/**
	 * Returns the amount category
	 * @return the amount category
	 */
	public Category getAmountCategory() {
		return category;
	}

	/**
	 * Returns the amount value
	 * @return the amount value
	 */
	public double getAmountValue() {
		return Double.parseDouble(value);
	}

	/**
	 * Returns the amount wished date
	 * @return the amount wished date
	 */
	public LocalDate getAmountWishedDate() {
		return wishedDate;
	}

	@Override
	public boolean isPageComplete() {
		if (!StringUtils.isEmpty(value) && !StringUtils.isBlank(value)) {
			try {
				Float.parseFloat(value);
			}
			catch (NumberFormatException e) {
				setErrorMessage("The operation amount must be a number !"); //$NON-NLS-1$
				return false;
			}
			if (getAmountValue() > operation.getTotalAmount()) {
				setErrorMessage("The operation sub amount value must be less than it's total amount !"); //$NON-NLS-1$
				return false;
			}
		}

		setErrorMessage(null);
		return true;
	}
}