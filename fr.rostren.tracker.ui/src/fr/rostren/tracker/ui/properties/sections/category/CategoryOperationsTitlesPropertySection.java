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
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.CategoryOperationsTitlesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryOperationTitleWizard;

public class CategoryOperationsTitlesPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new CategoryOperationsTitlesContentProvider();
	private final ILabelProvider labelProvider=new OperationTitleLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Category);
			Category category=(Category)currentEObject;

			String pageTitle=category.getTitle();
			Tracker tracker=(Tracker)category.eContainer().eContainer();

			AddCategoryOperationTitleWizard wizard=new AddCategoryOperationTitleWizard(pageTitle, tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				OperationTitle title=wizard.getOperationTitle();
				if (title != null) {
					ListenersUtils.executeAddCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, title);
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
			ListenersUtils.executeRemoveCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, elementToRemove);
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
		tableViewer.setInput(getOperationsTitles());
		addListeners();
	}

	private List<OperationTitle> getOperationsTitles() {
		Assert.isTrue(currentEObject instanceof Category);
		List<OperationTitle> operationsTitles=((Category)currentEObject).getOperationTitles();
		if (operationsTitles == null || operationsTitles.isEmpty()) {
			return Collections.emptyList();
		}
		return operationsTitles;
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
