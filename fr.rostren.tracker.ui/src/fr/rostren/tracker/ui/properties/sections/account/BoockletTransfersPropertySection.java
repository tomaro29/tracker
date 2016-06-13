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
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.sections.wizards.AddBoockletTransferWizard;

public class BoockletTransfersPropertySection extends AbstractTablePropertySection {
    protected Table transfersTable;

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof BoockletAccount);
	    BoockletAccount boocklet = (BoockletAccount) currentEObject;

	    AddBoockletTransferWizard wizard = new AddBoockletTransferWizard(boocklet);
	    WizardDialog wizardDialog = new WizardDialog(getShell(), wizard);
	    if (Window.OK == wizardDialog.open()) {
		Transfer newTansfer = null;
		if (wizard.isIncoming())
		    newTansfer = TrackerFactory.eINSTANCE.createIncoming();
		else if (wizard.isOutgoing())
		    newTansfer = TrackerFactory.eINSTANCE.createOutgoing();

		if (newTansfer == null)
		    return;

		OperationTitle transferTitle = wizard.getTransferTitle();
		if (transferTitle != null)
		    newTansfer.setOperationTitle(transferTitle);

		Origin transferOrigin = wizard.getTransferOrigin();
		if (transferOrigin != null)
		    newTansfer.setOrigin(transferOrigin);
		boocklet.getTransfers().add(newTansfer);
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

	this.transfersTable = createTable(body, null, addButtonlistener, removeButtonListener);
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
