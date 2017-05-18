/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import java.util.Optional;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOriginOperationWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing {@link Origin}
 * instance.
 */
public class AddOriginOperationWizard extends Wizard {

	protected AddOriginOperationWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddOriginOperationWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddOriginOperationWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Operation to origin."; //$NON-NLS-1$
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
	 * Returns the operation
	 * @return the operation
	 */
	public Optional<Operation> getOperation() {
		return page.getOperation();
	}
}
