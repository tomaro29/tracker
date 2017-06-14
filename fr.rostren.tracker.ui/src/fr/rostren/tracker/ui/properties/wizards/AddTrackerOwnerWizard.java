/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOwnerWizardPage;

/**
 * Wizard to add an {@link Owner} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOwnerWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerOwnerWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerOwnerWizardPage(pageTitle, tracker);
		title="Add Owner to tracker."; //$NON-NLS-1$
	}

	/**
	 * Returns the first name
	 * @return the first name
	 */
	public String getFirstName() {
		return ((AddTrackerOwnerWizardPage)page).getFirstName();
	}

	/**
	 * Returns the last name
	 * @return the last name
	 */
	public String getLastName() {
		return ((AddTrackerOwnerWizardPage)page).getLastName();
	}
}
