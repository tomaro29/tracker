package fr.rostren.tracker.ui.properties.sections.account;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.sections.wizards.AddCheckOperationWizard;

public class CheckingOperationsPropertySection extends AbstractTablePropertySection {
    protected Table operationsTable;

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof CheckingAccount);
	    CheckingAccount checking = (CheckingAccount) currentEObject;

	    AddCheckOperationWizard wizard = new AddCheckOperationWizard(checking);
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
		checking.getOperations().add(newOperation);
	    }
	}
    };

    private SelectionAdapter removeButtonListener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof BoockletAccount);
	    BoockletAccount boocklet = (BoockletAccount) currentEObject;

	    boocklet.getTransfers().remove(event.data);
	}
    };

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.operationsTable = createTable(body, null, addButtonlistener, removeButtonListener);
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
    }

    @Override
    public void refresh() {
	// TODO Auto-generated method stub
	super.refresh();
    }

    @Override
    protected void addListeners() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void disposeListeners() {
	// TODO Auto-generated method stub
	disposeButtonsListeners(addButtonlistener, removeButtonListener);
    }
}
