package fr.rostren.tracker.ui.properties.sections.category;

import java.util.Collections;
import java.util.List;

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
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategorySubCategoriesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCategorySubCategoryWizard;

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

			AddCategorySubCategoryWizard wizard=new AddCategorySubCategoryWizard(pageTitle, category);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Category subCategory=wizard.getSubCategory();
				if (subCategory != null) {
					DomainUtils.executeAddCommand(category, TrackerPackage.Literals.CATEGORY__SUB_CATEGORIES, subCategory);
					refresh();
				}
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
			DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.CATEGORY__SUB_CATEGORIES, elementToRemove);
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
		List<Category> subCategories=((Category)currentEObject).getSubCategories();
		if (subCategories == null || subCategories.isEmpty()) {
			return Collections.emptyList();
		}
		return subCategories;
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
