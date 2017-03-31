package fr.rostren.tracker.ui.properties.sections.repository;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPart;
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

	private final ITreeContentProvider contentProvider=new CategoriesRepositoryContentProvider();
	private final ILabelProvider labelProvider=new CategoryLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
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
				AddIncomeCategoryWizard wizard=new AddIncomeCategoryWizard("Categories Repository", //$NON-NLS-1$
						tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					addIncomeCategory((IncomeCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			else if (category instanceof SpendingCategory) {
				AddSpendingCategoryWizard wizard=new AddSpendingCategoryWizard("Categories Repository", //$NON-NLS-1$
						tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					addSpendingCategory((SpendingCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
		}

		private void addTrackerCategory(Tracker tracker) {
			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard("Categories Repository", //$NON-NLS-1$
					tracker);
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

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
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

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		tree=createTree(body, null, addButtonlistener, removeButtonListener);
		treeViewer=new TreeViewer(tree);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	@Override
	public void refresh() {
		disposeListeners();
		treeViewer.setInput(getCategories());
		treeViewer.expandAll();
		addListeners();
	}

	/**
	 * Returns the categories list
	 * @return the categories list
	 */
	private List<Category> getCategories() {
		Assert.isTrue(currentEObject instanceof CategoriesRepository);
		List<Category> categories=TrackerUtils.getTrackerService(currentEObject).getCategories();
		if (categories == null || categories.isEmpty()) {
			return Collections.emptyList();
		}
		return categories;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void disposeListeners() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonlistener, null, removeButtonListener);
	}
}
