package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddBoockletTransferWizardPage;

/**
 * Wizard to add a {@link Transfer} instance to an existing
 * {@link BoockletAccount} instance.
 */
/**
 *
 */
public class AddBoockletTransferWizard extends Wizard {

	protected AddBoockletTransferWizardPage page;

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddBoockletTransferWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddBoockletTransferWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Boocklet Account Transfer."; //$NON-NLS-1$
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
	 * Returns <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 */
	public boolean isIncoming() {
		return page.isIncoming();
	}

	/**
	 * Returns <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 */
	public boolean isOutgoing() {
		return page.isOutgoing();
	}

	/**
	 * Returns the transfer title
	 * @return the transfer title
	 */
	public OperationTitle getTransferTitle() {
		return page.getTransferTitle();
	}

	/**
	 * Returns the transfer origin
	 * @return the transfer origin
	 */
	public Origin getTransferOrigin() {
		return page.getTransferOrigin();
	}
}
