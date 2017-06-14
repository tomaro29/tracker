/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOriginWizardPage;

/**
 * Wizard to add an {@link Origin} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOriginWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerOriginWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerOriginWizardPage(pageTitle, tracker);
		title="Add Origin to tracker."; //$NON-NLS-1$
	}

	/**
	 * Returns the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return ((AddTrackerOriginWizardPage)page).getIdentifier();
	}

	/**
	 * Returns the type
	 * @return the type
	 */
	public OriginType getType() {
		return ((AddTrackerOriginWizardPage)page).getType();
	}
}
