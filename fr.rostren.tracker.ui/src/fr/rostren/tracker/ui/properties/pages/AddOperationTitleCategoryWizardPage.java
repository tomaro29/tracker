/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.AddIncomeCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddSpendingCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

/**
 * Page to add a {@link Category} instance to an existing {@link OperationTitle}
 * instance.
 */
public class AddOperationTitleCategoryWizardPage extends AbstractAddWizardPage {
	private static final String CATEGORIES_REPOSITORY="Categories Repository"; //$NON-NLS-1$
	private static final String PAGE_NAME="Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new category to the selected operation title."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected Category category;

	Tree categoriesTree;
	TreeViewer categoriesTreeViewer;

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
				AddIncomeCategoryWizard wizard=new AddIncomeCategoryWizard(AddOperationTitleCategoryWizardPage.CATEGORIES_REPOSITORY, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addIncomeCategory((IncomeCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			else if (category instanceof SpendingCategory) {
				AddSpendingCategoryWizard wizard=new AddSpendingCategoryWizard(AddOperationTitleCategoryWizardPage.CATEGORIES_REPOSITORY, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addSpendingCategory((SpendingCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			return null;
		}

		private Category addTrackerCategory(Tracker tracker) {
			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard(AddOperationTitleCategoryWizardPage.CATEGORIES_REPOSITORY, tracker);
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

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddOperationTitleCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOperationTitleCategoryWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddOperationTitleCategoryWizardPage.PAGE_TITLE);
		setDescription(AddOperationTitleCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		Set<Category> categories=new HashSet<>(TrackerUtils.getTrackerService(tracker).getCategories());
		categoriesTree=createTree(parent, "Category: ", addCategoryButtonlistener);
		categoriesTreeViewer=createTreeViewer(categoriesTree, listener);
		categoriesTreeViewer.setInput(new ArrayList<>(categories));
		categoriesTreeViewer.expandAll();
		if (!categories.isEmpty()) {
			category=categories.iterator().next();
		}
	}

	/**
	 * Returns the category
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
}