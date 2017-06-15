/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.account;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.ui.properties.content.providers.CheckingOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class CheckingOperationsPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CheckingOperationsContentProvider();
		labelProvider=new OperationLabelProvider();
		addButtonListener=createAddButtonForCheckingAccountOperations();
		removeButtonListener=createRemoveButtonForCheckingAccountOperations();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations
	 * @return the operations
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof CheckingAccount);
		EList<Operation> operations=((CheckingAccount)currentEObject).getOperations();
		if (operations == null || operations.isEmpty()) {
			return Collections.emptyList();
		}
		return operations.stream().map(operation -> (EObject)operation).collect(Collectors.toList());
	}
}
