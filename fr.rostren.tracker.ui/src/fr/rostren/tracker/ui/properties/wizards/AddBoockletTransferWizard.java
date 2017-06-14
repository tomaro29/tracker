/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

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
public class AddBoockletTransferWizard extends AbstractAddWizard {

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddBoockletTransferWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddBoockletTransferWizardPage(pageTitle, tracker);
		title="Add Boocklet Account Transfer."; //$NON-NLS-1$
	}

	/**
	 * Returns <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 */
	public boolean isIncoming() {
		return ((AddBoockletTransferWizardPage)page).isIncoming();
	}

	/**
	 * Returns <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 */
	public boolean isOutgoing() {
		return ((AddBoockletTransferWizardPage)page).isOutgoing();
	}

	/**
	 * Returns the transfer title
	 * @return the transfer title
	 */
	public OperationTitle getTransferTitle() {
		return ((AddBoockletTransferWizardPage)page).getTransferTitle();
	}

	/**
	 * Returns the transfer origin
	 * @return the transfer origin
	 */
	public Origin getTransferOrigin() {
		return ((AddBoockletTransferWizardPage)page).getTransferOrigin();
	}
}
