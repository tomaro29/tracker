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
public class AddSpendingCategoryWizardPage extends AbstractCategoryWizardPage {
	private static final String PAGE_NAME="Add Spending Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Spending Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new Spending Category to the selected tracker."; //$NON-NLS-1$

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddSpendingCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddSpendingCategoryWizardPage.PAGE_NAME, pageTitle), AddSpendingCategoryWizardPage.PAGE_TITLE, AddSpendingCategoryWizardPage.WIZARD_DESCRIPTION,
				tracker);
	}
}