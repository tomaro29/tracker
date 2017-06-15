/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.operation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.ui.properties.content.providers.OperationSubAmountContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationSubAmountLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OperationSubAmountsPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OperationSubAmountContentProvider();
		labelProvider=new OperationSubAmountLabelProvider();
		addButtonListener=createAddButtonForOperationSubAmounts();
		editButtonListener=createEditButtonForOperationSubAmounts();
		removeButtonListener=createRemoveButtonForOperationsSubAmounts();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the sub amounts
	 * @return the sub amounts
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Operation);
		EList<Amount> subAmounts=((Operation)currentEObject).getSubAmounts();
		if (subAmounts == null || subAmounts.isEmpty()) {
			return Collections.emptyList();
		}
		return subAmounts.stream().map(subAmount -> (EObject)subAmount).collect(Collectors.toList());
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonListener, editButtonListener, removeButtonListener);
	}
}
