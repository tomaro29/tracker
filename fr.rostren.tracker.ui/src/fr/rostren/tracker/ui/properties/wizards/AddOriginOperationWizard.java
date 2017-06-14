/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import java.util.Optional;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOriginOperationWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing {@link Origin}
 * instance.
 */
public class AddOriginOperationWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddOriginOperationWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddOriginOperationWizardPage(pageTitle, tracker);
		title="Add Operation to origin."; //$NON-NLS-1$
	}

	/**
	 * Returns the operation
	 * @return the operation
	 */
	public Optional<Operation> getOperation() {
		return ((AddOriginOperationWizardPage)page).getOperation();
	}
}
