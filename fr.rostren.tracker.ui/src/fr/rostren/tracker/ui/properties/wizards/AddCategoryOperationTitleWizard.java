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
import fr.rostren.tracker.ui.properties.pages.AddCategoryOperationTitleWizardPage;

/**
 * Wizard to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategoryOperationTitleWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddCategoryOperationTitleWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddCategoryOperationTitleWizardPage(pageTitle, tracker);
		title="Add Operation Title to Category."; //$NON-NLS-1$
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return ((AddCategoryOperationTitleWizardPage)page).getOperationTitle();
	}
}
