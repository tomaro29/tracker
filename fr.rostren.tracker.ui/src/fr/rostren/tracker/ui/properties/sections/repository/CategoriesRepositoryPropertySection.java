/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTreePropertySection;

public class CategoriesRepositoryPropertySection extends AbstractTreePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CategoriesRepositoryContentProvider();
		labelProvider=new CategoryLabelProvider();

		addButtonListener=createAddButtonForCategoriesRepository();
		removeButtonListener=createRemoveButtonForCategoriesRepository();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof CategoriesRepository);
		List<Category> categories=TrackerUtils.getTrackerService(currentEObject).getCategories();
		if (categories == null || categories.isEmpty()) {
			return Collections.emptyList();
		}
		return categories.stream().map(category -> (EObject)category).collect(Collectors.toList());
	}
}
