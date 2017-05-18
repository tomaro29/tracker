/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.account;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.ui.properties.listeners.AccountAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class AccountAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text nameText;
	protected Text amountText;
	protected Text identifierText;

	private final ModifyListener listener=new AccountAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		nameText=createLabeledText(body, null, "Name:"); //$NON-NLS-1$
		identifierText=createLabeledText(body, nameText, "Identifier:"); //$NON-NLS-1$
		amountText=createLabeledText(body, identifierText, "Amount:"); //$NON-NLS-1$

		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		nameText.setText(getAccountName());
		amountText.setText(getAccountAmount());
		identifierText.setText(getAccountIdentifier());
		addListeners();
	}

	/**
	 * Returns the account identifier
	 * @return the account identifier
	 */
	private String getAccountIdentifier() {
		Assert.isTrue(currentEObject instanceof Account);
		return String.valueOf(((Account)currentEObject).getIdentifier());
	}

	/**
	 * Returns the account amount
	 * @return the account amount
	 */
	private String getAccountAmount() {
		Assert.isTrue(currentEObject instanceof Account);
		return String.valueOf(((Account)currentEObject).getAmount());
	}

	/**
	 * Returns the account name
	 * @return the account name
	 */
	private String getAccountName() {
		Assert.isTrue(currentEObject instanceof Account);
		if (((Account)currentEObject).getName() == null) {
			return StringUtils.EMPTY;
		}
		return ((Account)currentEObject).getName();
	}

	@Override
	protected void addListeners() {
		nameText.addModifyListener(listener);
		amountText.addModifyListener(listener);
		identifierText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		nameText.removeModifyListener(listener);
		amountText.removeModifyListener(listener);
		identifierText.removeModifyListener(listener);
	}

	/**
	 * Returns the name text
	 * @return the name text
	 */
	public Text getNameText() {
		return nameText;
	}

	/**
	 * Returns the amount text
	 * @return the amount text
	 */
	public Text getAmountText() {
		return amountText;
	}

	/**
	 * Returns the identifier text
	 * @return the identifier text
	 */
	public Text getIdentifierText() {
		return identifierText;
	}
}
