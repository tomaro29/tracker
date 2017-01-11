package fr.rostren.tracker.ui.properties.sections.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategorySubCategoriesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryCategoryWizard;

public class CategorySubCategoriesPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new CategorySubCategoriesContentProvider();
	private final ILabelProvider labelProvider=new CategoryLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Category);
			Category category=(Category)currentEObject;

			String pageTitle=category.getTitle();

			AddCategoryCategoryWizard wizard=new AddCategoryCategoryWizard(pageTitle, category);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				if (category instanceof IncomeCategory) {
					Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();

					String title=wizard.getCategoryTitle();
					if (!StringUtils.isEmpty(title)) {
						newCategory.setTitle(title);
					}
					String description=wizard.getCategoryDescription();
					if (!StringUtils.isEmpty(description)) {
						newCategory.setDescription(description);
					}

					DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
				}
				else if (category instanceof SpendingCategory) {
					Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();

					String title=wizard.getCategoryTitle();
					if (!StringUtils.isEmpty(title)) {
						newCategory.setTitle(title);
					}
					String description=wizard.getCategoryDescription();
					if (!StringUtils.isEmpty(description)) {
						newCategory.setDescription(description);
					}

					DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
				}

				refresh();
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Category);
			Category category=(Category)currentEObject;

			ISelection selection=tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove=((StructuredSelection)selection).getFirstElement();
			if (category instanceof IncomeCategory) {
				DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, elementToRemove);
			}
			else if (category instanceof SpendingCategory) {
				DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, elementToRemove);
			}
			refresh();
		}
	};

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		table=createTable(body, null, addButtonlistener, removeButtonListener);
		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	@Override
	public void refresh() {
		disposeListeners();
		tableViewer.setInput(getSubCategories());
		addListeners();
	}

	/**
	 * Retursn the list of sub categories
	 * @return the list of sub categories
	 */
	private List<Category> getSubCategories() {
		Assert.isTrue(currentEObject instanceof Category);
		List<Category> subCategories=new ArrayList<>();
		if (currentEObject instanceof IncomeCategory) {
			subCategories.addAll(((IncomeCategory)currentEObject).getIncomes());
		}
		if (currentEObject instanceof SpendingCategory) {
			subCategories.addAll(((SpendingCategory)currentEObject).getSpendings());
		}

		return subCategories.isEmpty() ? Collections.emptyList() : subCategories;
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
		disposeButtonsListeners(addButtonlistener, removeButtonListener);
	}
}
