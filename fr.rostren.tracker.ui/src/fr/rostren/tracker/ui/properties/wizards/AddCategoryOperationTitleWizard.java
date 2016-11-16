package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddCategoryOperationTitleWizardPage;

/**
 * Wizard to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategoryOperationTitleWizard extends Wizard {

	protected AddCategoryOperationTitleWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddCategoryOperationTitleWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddCategoryOperationTitleWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Operation Title to Category."; //$NON-NLS-1$
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
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return page.getOperationTitle();
	}
}
