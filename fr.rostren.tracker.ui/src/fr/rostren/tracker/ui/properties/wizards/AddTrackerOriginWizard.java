/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddTrackerOriginWizardPage;

/**
 * Wizard to add an {@link Origin} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOriginWizard extends Wizard {

	protected AddTrackerOriginWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerOriginWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerOriginWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Origin to tracker."; //$NON-NLS-1$
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
	 * Returns the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return page.getIdentifier();
	}

	/**
	 * Returns the type
	 * @return the type
	 */
	public OriginType getType() {
		return page.getType();
	}
}
