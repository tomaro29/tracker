package fr.rostren.tracker.ui.properties.sections.tracker;

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

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.TrackerCategoriesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

public class TrackerCategoriesPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new TrackerCategoriesContentProvider();
	private final ILabelProvider labelProvider=new CategoryLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Tracker);
			Tracker tracker=(Tracker)currentEObject;
			CategoriesRepository repository=tracker.getCategoriesRepository();

			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard("Tracker", tracker); //$NON-NLS-1$
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Category newCategory=TrackerFactory.eINSTANCE.createCategory();

				String categTitle=wizard.getCategoryTitle();
				if (categTitle != null) {
					newCategory.setTitle(categTitle);
				}

				String categDesc=wizard.getCategoryDescription();
				if (categDesc != null) {
					newCategory.setTitle(categDesc);
				}

				ListenersUtils.executeAddCommand(repository, TrackerPackage.Literals.CATEGORIES_REPOSITORY__CATEGORIES, newCategory);
				refresh();
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Tracker);
			Tracker tracker=(Tracker)currentEObject;
			CategoriesRepository repository=tracker.getCategoriesRepository();

			ISelection selection=tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove=((StructuredSelection)selection).getFirstElement();
			ListenersUtils.executeRemoveCommand(repository, TrackerPackage.Literals.CATEGORIES_REPOSITORY__CATEGORIES, elementToRemove);
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
		tableViewer.setInput(getCategories());
		addListeners();
	}

	/**
	 * Returns the categories list
	 * @return the categories list
	 */
	private List<Category> getCategories() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<Category> categories=((Tracker)currentEObject).getCategoriesRepository().getCategories();
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
		disposeButtonsListeners(addButtonlistener, removeButtonListener);
	}
}
