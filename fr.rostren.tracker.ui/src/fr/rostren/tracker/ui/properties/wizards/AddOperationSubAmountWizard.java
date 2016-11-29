package fr.rostren.tracker.ui.properties.wizards;

import java.math.BigDecimal;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOperationSubAmountWizardPage;

/**
 * Wizard to add an {@link Amount} instance to an existing
 * {@link Operation} instance.
 */
public class AddOperationSubAmountWizard extends Wizard {

	protected AddOperationSubAmountWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddOperationSubAmountWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddOperationSubAmountWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Operation Amount."; //$NON-NLS-1$
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
	 * Returns the amount category
	 * @return the amount category
	 */
	public Category getAmountCategory() {
		return page.getAmountCategory();
	}

	/**
	 * Returns the amount value
	 * @return the amount value
	 */
	public BigDecimal getAmountValue() {
		return page.getAmountValue();
	}
}
