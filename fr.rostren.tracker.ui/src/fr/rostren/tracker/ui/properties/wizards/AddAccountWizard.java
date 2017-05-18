/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.pages.AddAccountWizardPage;

/**
 * Wizard to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddAccountWizard extends Wizard {

	protected AddAccountWizardPage page;

	/**
	 * Constructor.
	 * @param pageTitle the page title
	 * @param tracker the tracker
	 */
	public AddAccountWizard(String pageTitle, Tracker tracker) {
		super();
		page=new AddAccountWizardPage(pageTitle, tracker);
	}

	@Override
	public String getWindowTitle() {
		return "Add Checking Account Operation."; //$NON-NLS-1$
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	/**
	 * Returns <code>true</code> if is an {@link CheckingAccount} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link CheckingAccount} <code>false</code> otherwise.
	 */
	public boolean isCheckingAccount() {
		return page.isCheckingAccount();
	}

	/**
	 * Returns <code>true</code> if is an {@link BoockletAccount} <code>false</code> otherwise.
	 * @return <code>true</code> if is an {@link BoockletAccount} <code>false</code> otherwise.
	 */
	public boolean isBoockletAccount() {
		return page.isBoockletAccount();
	}

	/**
	 * Returns the account name
	 * @return the account name
	 */
	public String getAccountName() {
		return page.getAccountName();
	}

	/**
	 * Returns the operation origin
	 * @return the operation origin
	 */
	public int getAccountIdentifier() {
		return page.getAccountIdentifier();
	}

	/**
	 * Returns the account amount
	 * @return the account amount
	 */
	public float getAccountAmount() {
		return page.getAccountAmount();
	}
}
