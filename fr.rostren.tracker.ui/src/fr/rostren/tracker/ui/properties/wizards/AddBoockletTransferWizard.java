package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.pages.AddBoockletTransferWizardPage;

/**
 * Wizard to add a {@link Transfer} instance to an existing
 * {@link BoockletAccount} instance.
 */
public class AddBoockletTransferWizard extends Wizard {

    protected AddBoockletTransferWizardPage page;

    public AddBoockletTransferWizard(String pageTitle, Tracker tracker) {
	super();
	this.page = new AddBoockletTransferWizardPage(pageTitle, tracker);
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
