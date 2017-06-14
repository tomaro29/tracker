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
import fr.rostren.tracker.ui.properties.pages.AddCategoryCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddCategoryCategoryWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param category the category
	 */
	public AddCategoryCategoryWizard(String pageTitle, Category category) {
		super();
		page=new AddCategoryCategoryWizardPage(pageTitle, category);
		title="Add Category to category."; //$NON-NLS-1$
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return ((AddCategoryCategoryWizardPage)page).getCategoryTitle();
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return ((AddCategoryCategoryWizardPage)page).getCategoryDescription();
	}
}
