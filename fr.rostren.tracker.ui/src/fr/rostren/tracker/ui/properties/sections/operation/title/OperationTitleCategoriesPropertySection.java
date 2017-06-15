/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.operation.title;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.ui.properties.content.providers.OperationTitleCategoriesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OperationTitleCategoriesPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OperationTitleCategoriesContentProvider();
		labelProvider=new CategoryLabelProvider();
		addButtonListener=createAddButtonForOperationTitleCategories();
		removeButtonListener=createRemoveButtonForOperationTitleCategories();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the categories list
	 * @return the categories list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof OperationTitle);
		List<Category> categories=((OperationTitle)currentEObject).getCategories();
		if (categories == null || categories.isEmpty()) {
			return Collections.emptyList();
		}
		return categories.stream().map(category -> (EObject)category).collect(Collectors.toList());
	}
}
