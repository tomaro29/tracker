/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.ui.properties.content.providers.CategoryOperationsTitlesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class CategoryOperationsTitlesPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CategoryOperationsTitlesContentProvider();
		labelProvider=new OperationTitleLabelProvider();
		addButtonListener=createAddButtonForCategoryOperationsTitles();
		removeButtonListener=createRemoveButtonForCategoryOperationsTitles();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Retursn the list of operations titles
	 * @return the list of operations titles
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Category);
		List<OperationTitle> operationsTitles=((Category)currentEObject).getOperationTitles();
		if (operationsTitles == null || operationsTitles.isEmpty()) {
			return Collections.emptyList();
		}
		return operationsTitles.stream().map(operationTitle -> (EObject)operationTitle).collect(Collectors.toList());
	}
}
