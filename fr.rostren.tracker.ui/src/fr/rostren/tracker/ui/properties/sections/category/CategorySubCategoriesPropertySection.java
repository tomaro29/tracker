/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.ui.properties.content.providers.CategorySubCategoriesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTreePropertySection;

public class CategorySubCategoriesPropertySection extends AbstractTreePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CategorySubCategoriesContentProvider();
		labelProvider=new CategoryLabelProvider();
		addButtonListener=createAddButtonForCategorySubCategories();
		removeButtonListener=createRemoveButtonForCategorySubCategories();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Retursn the list of sub categories
	 * @return the list of sub categories
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Category);
		List<Category> subCategories=new ArrayList<>();
		if (currentEObject instanceof IncomeCategory) {
			subCategories.addAll(((IncomeCategory)currentEObject).getIncomes());
		}
		if (currentEObject instanceof SpendingCategory) {
			subCategories.addAll(((SpendingCategory)currentEObject).getSpendings());
		}

		return subCategories.isEmpty() ? Collections.emptyList() : subCategories.stream().map(subCategory -> (EObject)subCategory).collect(Collectors.toList());
	}
}
