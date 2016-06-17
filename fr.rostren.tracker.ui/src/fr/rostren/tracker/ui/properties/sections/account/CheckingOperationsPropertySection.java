package fr.rostren.tracker.ui.properties.sections.account;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
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

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.CheckingOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCheckOperationWizard;

public class CheckingOperationsPropertySection extends AbstractTablePropertySection {

    private ITreeContentProvider contentProvider = new CheckingOperationsContentProvider();
    private ILabelProvider labelProvider = new OperationLabelProvider();

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof CheckingAccount);
	    CheckingAccount checking = (CheckingAccount) currentEObject;

	    String pageTitle = checking.getName();
	    Tracker tracker = (Tracker) checking.eContainer().eContainer();

	    AddCheckOperationWizard wizard = new AddCheckOperationWizard(pageTitle, tracker);
	    WizardDialog wizardDialog = new WizardDialog(getShell(), wizard);
	    if (Window.OK == wizardDialog.open()) {
		Operation newOperation = null;
		if (wizard.isCredit())
		    newOperation = TrackerFactory.eINSTANCE.createCredit();
		else if (wizard.isDebit())
		    newOperation = TrackerFactory.eINSTANCE.createDebit();
		else if (wizard.isIncoming())
		    newOperation = TrackerFactory.eINSTANCE.createIncoming();
		else if (wizard.isOutgoing())
		    newOperation = TrackerFactory.eINSTANCE.createOutgoing();

		if (newOperation == null)
		    return;

		OperationTitle operationTitle = wizard.getOperationTitle();
		if (operationTitle != null)
		    newOperation.setOperationTitle(operationTitle);

		Origin operationOrigin = wizard.getOperationOrigin();
		if (operationOrigin != null)
		    newOperation.setOrigin(operationOrigin);

		ListenersUtils.executeAddCommand(checking, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
			newOperation);
		refresh();
	    }
	}
    };

    private SelectionAdapter removeButtonListener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof CheckingAccount);
	    CheckingAccount account = (CheckingAccount) currentEObject;

	    ISelection selection = viewer.getSelection();
	    Assert.isTrue(selection instanceof StructuredSelection);
	    Object elementToRemove = ((StructuredSelection) selection).getFirstElement();
	    ListenersUtils.executeRemoveCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
		    elementToRemove);
	    refresh();
	}
    };

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.table = createTable(body, null, addButtonlistener, removeButtonListener);
	this.viewer = new TableViewer(table);
	viewer.setContentProvider(contentProvider);
	viewer.setLabelProvider(labelProvider);
	addListeners();
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
    }

    @Override
    public void refresh() {
	disposeListeners();
	viewer.setInput(getOperations());
	addListeners();
    }

    private List<Operation> getOperations() {
	Assert.isTrue(currentEObject instanceof CheckingAccount);
	EList<Operation> operations = ((CheckingAccount) currentEObject).getOperations();
	if (operations == null || operations.isEmpty())
	    return Collections.emptyList();
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
