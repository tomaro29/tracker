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

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OwnerAccountsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.AccountLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddAccountWizard;

public class OwnerAccountsPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OwnerAccountsContentProvider();
		labelProvider=new AccountLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Owner);
				Owner owner=(Owner)currentEObject;

				String pageTitle=owner.getFirstName() + " " + owner.getLastName(); //$NON-NLS-1$
				Tracker tracker=(Tracker)owner.eContainer();

				AddAccountWizard wizard=new AddAccountWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Account newAccount=null;
					if (wizard.isCheckingAccount()) {
						newAccount=TrackerFactory.eINSTANCE.createCheckingAccount();
					}
					else if (wizard.isBoockletAccount()) {
						newAccount=TrackerFactory.eINSTANCE.createBoockletAccount();
					}

					if (newAccount == null) {
						return;
					}

					String accountName=wizard.getAccountName();
					if (!StringUtils.isEmpty(accountName) && !StringUtils.isBlank(accountName)) {
						newAccount.setName(accountName);
					}

					int accountIdentifier=wizard.getAccountIdentifier();
					if (accountIdentifier != 0) {
						newAccount.setIdentifier(accountIdentifier);
					}

					double accountAmount=wizard.getAccountAmount();
					if (Double.isFinite(accountAmount)) {
						newAccount.setAmount(accountAmount);
					}

					DomainUtils.executeAddCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, newAccount);
					refresh();
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Owner);
				Owner owner=(Owner)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the accounts list
	 * @return the accounts list
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Owner);
		List<Account> accounts=((Owner)currentEObject).getAccounts();
		if (accounts == null || accounts.isEmpty()) {
			return Collections.emptyList();
		}
		return accounts;
	}
}
