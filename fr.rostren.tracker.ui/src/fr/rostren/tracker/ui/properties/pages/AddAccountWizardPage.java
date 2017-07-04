/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddAccountWizardPage extends AbstractAddWizardPage {
	protected static final String[] ACCOUNTS_TYPES=new String[] {CheckingAccount.class.getSimpleName(), BoockletAccount.class.getSimpleName()};

	private static final String PAGE_NAME="Add account to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add account"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new account to the selected owner."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected boolean isChecking=true;
	protected boolean isBoocklet=false;
	protected String name;
	protected String identifier;
	protected String amount;

	private final ModifyListener modifyAccountTypeListener=event -> {
		String text=((Combo)event.widget).getText();
		isChecking=AddAccountWizardPage.ACCOUNTS_TYPES[0].equals(text);
		isBoocklet=AddAccountWizardPage.ACCOUNTS_TYPES[1].equals(text);
	};

	private final ModifyListener modifyNameListener=event -> name=((Text)event.widget).getText();

	private final ModifyListener modifyIdentifierListener=event -> {
		identifier=((Text)event.widget).getText();
		setPageComplete(isPageComplete());
	};

	private final ModifyListener modifyAmountListener=event -> {
		amount=((Text)event.widget).getText();
		setPageComplete(isPageComplete());
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddAccountWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddAccountWizardPage.PAGE_NAME, pageTitle), tracker);
		this.tracker=tracker;
		setTitle(AddAccountWizardPage.PAGE_TITLE);
		setDescription(AddAccountWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Account Type: ", AddAccountWizardPage.ACCOUNTS_TYPES, modifyAccountTypeListener); //$NON-NLS-1$

		createText(parent, "Name: ", name, modifyNameListener); //$NON-NLS-1$
		createText(parent, "Identifier: ", identifier, modifyIdentifierListener); //$NON-NLS-1$
		createText(parent, "Amount: ", amount, modifyAmountListener); //$NON-NLS-1$
	}

	/**
	 * Returns <code>true</code> if is {@link CheckingAccount}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link CheckingAccount}, <code>false</code> otherwise.
	 */
	public boolean isCheckingAccount() {
		return isChecking;
	}

	/**
	 * Returns <code>true</code> if is {@link BoockletAccount}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link BoockletAccount}, <code>false</code> otherwise.
	 */
	public boolean isBoockletAccount() {
		return isBoocklet;
	}

	/**
	 * Returns the account name
	 * @return the account name
	 */
	public String getAccountName() {
		return name;
	}

	/**
	 * Returns the account identifier
	 * @return the account identifier
	 */
	public int getAccountIdentifier() {
		return Integer.parseInt(identifier);
	}

	/**
	 * Returns the account amount
	 * @return the account amount
	 */
	public double getAccountAmount() {
		return Double.parseDouble(amount);
	}

	@Override
	public boolean isPageComplete() {
		return isAccountPageComplete(tracker, identifier, amount);
	}
}