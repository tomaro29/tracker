package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddCheckOperationWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddCheckOperationWizard extends Wizard {

	protected AddCheckOperationWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddCheckOperationWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddCheckOperationWizardPage(pageTitle, tracker);
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

	/**
	 * Returns <code>true</code> if is an {@link Incoming} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link Incoming} <code>false</code> otherwise.
	 */
	public boolean isIncoming() {
		return page.isIncoming();
	}

	/**
	 * Returns <code>true</code> if is an {@link Outgoing} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link Outgoing} <code>false</code> otherwise.
	 */
	public boolean isOutgoing() {
		return page.isOutgoing();
	}

	/**
	 * Returns <code>true</code> if is an {@link Credit} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link Credit} <code>false</code> otherwise.
	 */
	public boolean isCredit() {
		return page.isCredit();
	}

	/**
	 * Returns <code>true</code> if is an {@link Debit} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link Debit} <code>false</code> otherwise.
	 */
	public boolean isDebit() {
		return page.isDebit();
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return page.getOperationTitle();
	}

	/**
	 * Returns the operation origin
	 * @return the operation origin
	 */
	public Origin getOperationOrigin() {
		return page.getOperationOrigin();
	}
}
