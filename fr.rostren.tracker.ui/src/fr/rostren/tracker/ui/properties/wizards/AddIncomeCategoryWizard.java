/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddIncomeCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddIncomeCategoryWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddIncomeCategoryWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddIncomeCategoryWizardPage(pageTitle, tracker);
		title="Add Category to tracker."; //$NON-NLS-1$
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return ((AddIncomeCategoryWizardPage)page).getCategoryTitle();
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return ((AddIncomeCategoryWizardPage)page).getCategoryDescription();
	}
}
