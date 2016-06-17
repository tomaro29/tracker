package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOwnerWizardPage;

/**
 * Wizard to add an {@link Owner} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOwnerWizard extends Wizard {

    protected AddTrackerOwnerWizardPage page;

    public AddTrackerOwnerWizard(String pageTitle, Tracker tracker) {
	super();
	this.page = new AddTrackerOwnerWizardPage(pageTitle, tracker);
    }

    @Override
    public String getWindowTitle() {
	return "Add Owner to tracker."; //$NON-NLS-1$
    }

    @Override
    public boolean performFinish() {
	return true;
    }

    @Override
    public void addPages() {
	addPage(page);
    }

    public String getFirstName() {
	return page.getFirstName();
    }

    public String getLastName() {
	return page.getLastName();
    }
}
