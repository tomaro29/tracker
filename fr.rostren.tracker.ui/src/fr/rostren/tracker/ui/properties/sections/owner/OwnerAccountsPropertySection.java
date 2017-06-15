/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.owner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.ui.properties.content.providers.OwnerAccountsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.AccountLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OwnerAccountsPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OwnerAccountsContentProvider();
		labelProvider=new AccountLabelProvider();
		addButtonListener=createAddButtonForOwnerAccounts();
		removeButtonListener=createRemoveButtonForOwnerAccounts();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the accounts list
	 * @return the accounts list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Owner);
		List<Account> accounts=((Owner)currentEObject).getAccounts();
		if (accounts == null || accounts.isEmpty()) {
			return Collections.emptyList();
		}
		return accounts.stream().map(account -> (EObject)account).collect(Collectors.toList());
	}
}
