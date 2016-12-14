package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddCategoryCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddCategoryCategoryWizard extends Wizard {

	protected AddCategoryCategoryWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param category the category
	 */
	public AddCategoryCategoryWizard(String pageTitle, Category category) {
		super();
		page=new AddCategoryCategoryWizardPage(pageTitle, category);
	}

	@Override
	public String getWindowTitle() {
		return "Add Category to category."; //$NON-NLS-1$
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
}
