package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOriginOperationWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing {@link Origin}
 * instance.
 */
public class AddOriginOperationWizard extends Wizard {

    protected AddOriginOperationWizardPage page;

    public AddOriginOperationWizard(String pageTitle, Tracker tracker) {
	super();
	this.page = new AddOriginOperationWizardPage(pageTitle, tracker);
    }

    @Override
    public String getWindowTitle() {
	return "Add Operation to origin."; //$NON-NLS-1$
    }

    @Override
    public boolean performFinish() {
	return true;
    }

    @Override
    public void addPages() {
	addPage(page);
    }

    public Operation getOperation() {
	return page.getOperation();
    }
}
