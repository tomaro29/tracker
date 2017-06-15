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

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.content.providers.BoockletTransfersContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.TransferLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.AccountTransfersModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class BoockletTransfersPropertySection extends AbstractTablePropertySection {

	private final AccountTransfersModifyListener listener=new AccountTransfersModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new BoockletTransfersContentProvider();
		labelProvider=new TransferLabelProvider();
		addButtonListener=createAddButtonForAccountTransfers();
		removeButtonListener=createRemoveButtonForAccountTransfers();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the transfers
	 * @return the transfers
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof BoockletAccount);
		EList<Transfer> transfers=((BoockletAccount)currentEObject).getTransfers();
		if (transfers == null || transfers.isEmpty()) {
			return Collections.emptyList();
		}
		return transfers.stream().map(transfer -> (EObject)transfer).collect(Collectors.toList());
	}

	@Override
	protected void addListeners() {
		tableViewer.addSelectionChangedListener(listener);
	}

	@Override
	protected void disposeListeners() {
		tableViewer.removeSelectionChangedListener(listener);
	}
}
