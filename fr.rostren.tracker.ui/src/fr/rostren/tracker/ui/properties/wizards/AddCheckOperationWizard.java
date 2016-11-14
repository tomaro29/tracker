package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddCheckOperationWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddCheckOperationWizard extends Wizard {

	protected AddCheckOperationWizardPage page;

	public AddCheckOperationWizard(String pageTitle, Tracker tracker) {
		super();
		page = new AddCheckOperationWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Checking Account Operation."; //$NON-NLS-1$
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

	public boolean isCredit() {
		return page.isCredit();
	}

	public boolean isDebit() {
		return page.isDebit();
	}

	public OperationTitle getOperationTitle() {
		return page.getOperationTitle();
	}

	public Origin getOperationOrigin() {
		return page.getOperationOrigin();
	}
}
