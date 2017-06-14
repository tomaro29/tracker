/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.repository;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTreePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddIncomeCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddSpendingCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

public class CategoriesRepositoryPropertySection extends AbstractTreePropertySection {

	private static final String CATEGORIES_REPOSITORY="Categories Repository"; //$NON-NLS-1$

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CategoriesRepositoryContentProvider();
		labelProvider=new CategoryLabelProvider();

		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CategoriesRepository);
				CategoriesRepository repository=(CategoriesRepository)currentEObject;
				Tracker tracker=(Tracker)repository.eContainer();

				TreeItem[] selection=tree.getSelection();
				if (selection.length == 0) {
					addTrackerCategory(tracker);
				}
				else {
					addCategorySubCategory(tracker, (Category)selection[0].getData());
				}

				refresh();
			}

			private void addCategorySubCategory(Tracker tracker, Category category) {
				if (category instanceof IncomeCategory) {
					AddIncomeCategoryWizard wizard=new AddIncomeCategoryWizard(CategoriesRepositoryPropertySection.CATEGORIES_REPOSITORY, tracker);
					WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
					if (Window.OK == wizardDialog.open()) {
						addIncomeCategory((IncomeCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
					}
				}
				else if (category instanceof SpendingCategory) {
					AddSpendingCategoryWizard wizard=new AddSpendingCategoryWizard(CategoriesRepositoryPropertySection.CATEGORIES_REPOSITORY, tracker);
					WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
					if (Window.OK == wizardDialog.open()) {
						addSpendingCategory((SpendingCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
					}
				}
			}

			private void addTrackerCategory(Tracker tracker) {
				AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard(CategoriesRepositoryPropertySection.CATEGORIES_REPOSITORY, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					if (wizard.isIncome()) {
						addIncomeCategory(tracker.getCategoriesRepository().getIncome(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
					}
					else if (wizard.isSpending()) {
						addSpendingCategory(tracker.getCategoriesRepository().getSpending(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
					}
				}
			}

			private void addSpendingCategory(SpendingCategory category, String title, String description) {
				Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();

				if (!StringUtils.isEmpty(title)) {
					newCategory.setTitle(title);
				}
				if (!StringUtils.isEmpty(description)) {
					newCategory.setDescription(description);
				}
				DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
			}

			private void addIncomeCategory(IncomeCategory category, String title, String description) {
				Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();

				if (!StringUtils.isEmpty(title)) {
					newCategory.setTitle(title);
				}
				if (!StringUtils.isEmpty(description)) {
					newCategory.setDescription(description);
				}
				DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
			}
		};

		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CategoriesRepository);
				CategoriesRepository repository=(CategoriesRepository)currentEObject;

				ISelection selection=treeViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Category elementToRemove=(Category)((StructuredSelection)selection).getFirstElement();
				if (elementToRemove.eContainer() instanceof IncomeCategory) {
					DomainUtils.executeRemoveCommand(repository.getIncome(), TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, elementToRemove);
				}
				else if (elementToRemove.eContainer() instanceof SpendingCategory) {
					DomainUtils.executeRemoveCommand(repository.getSpending(), TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, elementToRemove);
				}
				refresh();
			}
		};

		tree=createTree(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof CategoriesRepository);
		List<Category> categories=TrackerUtils.getTrackerService(currentEObject).getCategories();
		if (categories == null || categories.isEmpty()) {
			return Collections.emptyList();
		}
		return categories;
	}
}
