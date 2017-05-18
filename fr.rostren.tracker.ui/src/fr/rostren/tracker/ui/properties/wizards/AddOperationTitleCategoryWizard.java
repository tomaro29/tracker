/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddOperationTitleCategoryWizardPage;

/**
 * Wizard to add a {@link Category} instance to an existing
 * {@link OperationTitle} instance.
 */
public class AddOperationTitleCategoryWizard extends Wizard {

	protected AddOperationTitleCategoryWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddOperationTitleCategoryWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddOperationTitleCategoryWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Category to Operation Title."; //$NON-NLS-1$
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
	 * Returns the category
	 * @return the category
	 */
	public Category getCategory() {
		return page.getCategory();
	}
}
