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
import fr.rostren.tracker.ui.properties.pages.AddTrackerCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerCategoryWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddTrackerCategoryWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddTrackerCategoryWizardPage(pageTitle, tracker);
		title="Add Category to tracker."; //$NON-NLS-1$
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return ((AddTrackerCategoryWizardPage)page).getCategoryTitle();
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return ((AddTrackerCategoryWizardPage)page).getCategoryDescription();
	}

	/**
	 * @return <code>true</code> is the category is an income one, <code>false</code> otherwise.
	 */
	public boolean isIncome() {
		return ((AddTrackerCategoryWizardPage)page).isIncome();
	}

	/**
	 * @return <code>true</code> is the category is an spending one, <code>false</code> otherwise.
	 */
	public boolean isSpending() {
		return ((AddTrackerCategoryWizardPage)page).isSpending();
	}
}
