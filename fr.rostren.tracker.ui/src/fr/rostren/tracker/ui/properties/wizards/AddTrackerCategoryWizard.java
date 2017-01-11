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

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerCategoryWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerCategoryWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Category to tracker."; //$NON-NLS-1$
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return page.getCategoryTitle();
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return page.getCategoryDescription();
	}

	/**
	 * @return <code>true</code> is the category is an income one, <code>false</code> otherwise.
	 */
	public boolean isIncome() {
		return page.isIncome();
	}

	/**
	 * @return <code>true</code> is the category is an spending one, <code>false</code> otherwise.
	 */
	public boolean isSpending() {
		return page.isSpending();
	}
}
