/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOperationTitleWizardPage;

/**
 * Wizard to add an {@link OperationTitle} instance to an existing
 * {@link Tracker} instance.
 */
public class AddTrackerOperationTitleWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerOperationTitleWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerOperationTitleWizardPage(pageTitle, tracker);
		title="Add Operation Title to tracker."; //$NON-NLS-1$
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public String getOperationTitle() {
		return ((AddTrackerOperationTitleWizardPage)page).getOperationTitle();
	}
}
