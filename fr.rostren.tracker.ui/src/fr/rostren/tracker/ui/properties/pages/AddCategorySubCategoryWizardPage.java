/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;

/**
 * Page to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategorySubCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new category to the selected category."; //$NON-NLS-1$

	protected Category subCategory;

	private final ISelectionChangedListener categoryListener=event -> {
		ISelection selection=event.getSelection();
		Assert.isTrue(selection instanceof StructuredSelection);
		StructuredSelection ss=(StructuredSelection)selection;
		Object firstElement=ss.getFirstElement();
		if (firstElement != null && firstElement instanceof Category) {
			subCategory=(Category)firstElement;
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param category the given category
	 */
	public AddCategorySubCategoryWizardPage(String pageTitle, Category category) {
		super(MessageFormat.format(AddCategorySubCategoryWizardPage.PAGE_NAME, pageTitle), category);
		setTitle(AddCategorySubCategoryWizardPage.PAGE_TITLE);
		setDescription(AddCategorySubCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		List<Category> categories=new ArrayList<>();
		if (object instanceof IncomeCategory) {
			categories.addAll(((IncomeCategory)object).getIncomes());
		}
		else {
			categories.addAll(((SpendingCategory)object).getSpendings());
		}
		categoriesComboViewer=createComboViewer(parent, "Category: ", new HashSet<>(categories), //$NON-NLS-1$
				new CategoriesRepositoryContentProvider(), new CategoryLabelProvider(), categoryListener, addSubCategoryButtonlistener);
		if (!categories.isEmpty()) {
			subCategory=categories.get(0);
		}
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public Category getSubCategory() {
		return subCategory;
	}
}