package fr.rostren.tracker.ui.properties.sections.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.ui.properties.sections.pages.AddBoockletTransferWizardPage;

public class AddBoockletTransferWizard extends Wizard {

    protected AddBoockletTransferWizardPage page;

    public AddBoockletTransferWizard(BoockletAccount account) {
	super();
	this.page = new AddBoockletTransferWizardPage(account);
    }

    @Override
    public String getWindowTitle() {
	return "Add Boocklet Account Transfer."; //$NON-NLS-1$
    }

    @Override
    public boolean performFinish() {
	return true;
    }

    @Override
    public void addPages() {
	addPage(page);
    }

    public boolean isIncoming() {
	return page.isIncoming();
    }

    public boolean isOutgoing() {
	return page.isOutgoing();
    }

    public OperationTitle getTransferTitle() {
	return page.getTransferTitle();
    }

    public Origin getTransferOrigin() {
	return page.getTransferOrigin();
    }
}
