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
import fr.rostren.tracker.ui.properties.pages.AddCategorySubCategoryWizardPage;

/**
 * Wizard to add an {@link Category} instance to an existing
 * {@link Category} instance.
 */
public class AddCategorySubCategoryWizard extends Wizard {

	protected AddCategorySubCategoryWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param category the category
	 */
	public AddCategorySubCategoryWizard(String pageTitle, Category category) {
		super();
		page=new AddCategorySubCategoryWizardPage(pageTitle, category);
	}

	@Override
	public String getWindowTitle() {
		return "Add Sub Category to Category."; //$NON-NLS-1$
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
	 * Returns the Category
	 * @return the Category
	 */
	public Category getSubCategory() {
		return page.getSubCategory();
	}
}
