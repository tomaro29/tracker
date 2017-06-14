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

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.model.utils.TrackerUtils;

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

	protected final OperationData operation;
	protected final Amount amount;

	protected Category category;
	protected String value;
	protected LocalDate wishedDate;

	Tree categoriesTree;
	TreeViewer categoriesTreeViewer;
	private final OperationType operationType;

	private final ModifyListener modifyValueListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			value=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
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
	 * @param operationType the operation type as selected in the combo
	 * @param amount the amount to edit if any, <code>null</code> otherwise.
	 * @param isAdd <code>true</code> if the action is a result of an Add action, <code>false</code> if is edit one
	 */
	public OperationSubAmountWizardPage(String pageTitle, Tracker tracker, OperationData operation, OperationType operationType, Amount amount, boolean isAdd) {
		super(MessageFormat.format(isAdd ? OperationSubAmountWizardPage.ADD_PAGE_NAME : OperationSubAmountWizardPage.EDIT_PAGE_NAME, pageTitle), tracker);
		this.operation=operation;
		this.operationType=operationType;
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

		Set<Category> categories=new HashSet<>(TrackerUtils.getTrackerService(object).getCategories()).stream()//
				.filter(categ -> operationType == OperationType.DEBIT && categ instanceof SpendingCategory
									|| operationType == OperationType.CREDIT && categ instanceof IncomeCategory)//
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
		return isOperationSubAmountPageComplete(value, getAmountValue(), operation);
	}
}