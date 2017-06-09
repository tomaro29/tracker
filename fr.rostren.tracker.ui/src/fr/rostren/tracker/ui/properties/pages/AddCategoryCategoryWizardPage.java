/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddCategoryCategoryWizardPage extends AbstractCategoryWizardPage {
	private static final String PAGE_NAME="Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new Category to the selected category."; //$NON-NLS-1$

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param category the given category
	 */
	public AddCategoryCategoryWizardPage(String pageTitle, Category category) {
		super(MessageFormat.format(AddCategoryCategoryWizardPage.PAGE_NAME, pageTitle), AddCategoryCategoryWizardPage.PAGE_TITLE, AddCategoryCategoryWizardPage.WIZARD_DESCRIPTION,
				category);
	}
}