/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.origin;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.ui.properties.content.providers.OriginOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OriginOperationsPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OriginOperationsContentProvider();
		labelProvider=new OperationLabelProvider();
		addButtonListener=createAddButtonForOriginOperations();
		removeButtonListener=createRemoveButtonForOriginOperations();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations list
	 * @return the operations list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Origin);
		List<Operation> operations=((Origin)currentEObject).getOperations();
		if (operations == null || operations.isEmpty()) {
			return Collections.emptyList();
		}
		return operations.stream().map(operation -> (EObject)operation).collect(Collectors.toList());
	}
}
