package fr.rostren.tracker.ui.properties.sections.origin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.OriginOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddOriginOperationWizard;

public class OriginOperationsPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new OriginOperationsContentProvider();
	private final ILabelProvider labelProvider=new OperationLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Origin);
			Origin origin=(Origin)currentEObject;

			String pageTitle=origin.getIdentifier();
			Tracker tracker=(Tracker)origin.eContainer().eContainer();

			AddOriginOperationWizard wizard=new AddOriginOperationWizard(pageTitle, tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Optional<Operation> operation=wizard.getOperation();
				ListenersUtils.executeAddCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, operation);
				refresh();
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Origin);
			Origin origin=(Origin)currentEObject;

			ISelection selection=tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove=((StructuredSelection)selection).getFirstElement();
			ListenersUtils.executeRemoveCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, elementToRemove);
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
		tableViewer.setInput(getOperations());
		addListeners();
	}

	/**
	 * Returns the operations list
	 * @return the operations list
	 */
	private List<Operation> getOperations() {
		Assert.isTrue(currentEObject instanceof Origin);
		List<Operation> operations=((Origin)currentEObject).getOperations();
		if (operations == null || operations.isEmpty()) {
			return Collections.emptyList();
		}
		return operations;
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
