package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerCategoryWizard extends Wizard {

    protected AddTrackerCategoryWizardPage page;

    public AddTrackerCategoryWizard(String pageTitle, Tracker tracker) {
	super();
	this.page = new AddTrackerCategoryWizardPage(pageTitle, tracker);
    }

    @Override
    public String getWindowTitle() {
	return "Add Origin to tracker."; //$NON-NLS-1$
    }

    @Override
    public boolean performFinish() {
	return true;
    }

    @Override
    public void addPages() {
	addPage(page);
    }

    public String getCategoryTitle() {
	return page.getCategoryTitle();
    }

    public String getCategoryDescription() {
	return page.getCategoryDescription();
    }
}
