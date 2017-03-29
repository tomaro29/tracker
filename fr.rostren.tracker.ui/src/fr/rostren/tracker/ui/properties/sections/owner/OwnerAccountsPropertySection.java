package fr.rostren.tracker.ui.properties.sections.owner;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
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

	private final ITreeContentProvider contentProvider=new OwnerAccountsContentProvider();
	private final ILabelProvider labelProvider=new AccountLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
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

				float accountAmount=wizard.getAccountAmount();
				if (accountAmount != 0.0) {
					newAccount.setAmount(accountAmount);
				}

				DomainUtils.executeAddCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, newAccount);
				refresh();
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
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

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		table=createTable(body, null, addButtonlistener, removeButtonListener);
		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	@Override
	public void refresh() {
		disposeListeners();
		tableViewer.setInput(getAccounts());
		addListeners();
	}

	/**
	 * Returns the accounts list
	 * @return the accounts list
	 */
	private List<Account> getAccounts() {
		Assert.isTrue(currentEObject instanceof Owner);
		List<Account> accounts=((Owner)currentEObject).getAccounts();
		if (accounts == null || accounts.isEmpty()) {
			return Collections.emptyList();
		}
		return accounts;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void disposeListeners() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonlistener, null, removeButtonListener);
	}
}
