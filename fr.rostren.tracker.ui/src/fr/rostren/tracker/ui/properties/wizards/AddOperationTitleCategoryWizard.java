/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOperationTitleCategoryWizardPage;

/**
 * Wizard to add a {@link Category} instance to an existing
 * {@link OperationTitle} instance.
 */
public class AddOperationTitleCategoryWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddOperationTitleCategoryWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddOperationTitleCategoryWizardPage(pageTitle, tracker);
		title="Add Category to Operation Title."; //$NON-NLS-1$
	}

	/**
	 * Returns the category
	 * @return the category
	 */
	public Category getCategory() {
		return ((AddOperationTitleCategoryWizardPage)page).getCategory();
	}
}
