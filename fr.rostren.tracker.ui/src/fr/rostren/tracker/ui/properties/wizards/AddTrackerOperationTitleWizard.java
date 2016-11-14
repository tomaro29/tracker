package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOperationTitleWizardPage;

/**
 * Wizard to add an {@link OperationTitle} instance to an existing
 * {@link Tracker} instance.
 */
public class AddTrackerOperationTitleWizard extends Wizard {

	protected AddTrackerOperationTitleWizardPage page;

	public AddTrackerOperationTitleWizard(String pageTitle, Tracker tracker) {
		super();
		page = new AddTrackerOperationTitleWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Operation Title to tracker."; //$NON-NLS-1$
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	public String getOperationTitle() {
		return page.getOperationTitle();
	}
}
