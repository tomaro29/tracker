package fr.rostren.tracker.ui.properties.sections.owner;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.OwnerAccountsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.AccountLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OwnerAccountsPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider = new OwnerAccountsContentProvider();
	private final ILabelProvider labelProvider = new AccountLabelProvider();

	private final SelectionAdapter addButtonlistener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			// TODO
		}
	};

	private final SelectionAdapter removeButtonListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject = getCurrentEObject();
			Assert.isTrue(currentEObject instanceof Owner);
			Owner owner = (Owner) currentEObject;

			ISelection selection = tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove = ((StructuredSelection) selection).getFirstElement();
			ListenersUtils.executeRemoveCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, elementToRemove);
			refresh();
		}
	};

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		table = createTable(body, null, addButtonlistener, removeButtonListener);
		tableViewer = new TableViewer(table);
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

	private List<Account> getAccounts() {
		Assert.isTrue(currentEObject instanceof Owner);
		List<Account> accounts = ((Owner) currentEObject).getAccounts();
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
		disposeButtonsListeners(addButtonlistener, removeButtonListener);
	}
}
