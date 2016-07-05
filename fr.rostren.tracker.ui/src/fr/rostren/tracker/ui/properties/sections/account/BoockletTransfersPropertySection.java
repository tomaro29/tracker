package fr.rostren.tracker.ui.properties.sections.account;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
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

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.content.providers.BoockletTransfersContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.TransferLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.AccountTransfersModifyListener;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddBoockletTransferWizard;

public class BoockletTransfersPropertySection extends AbstractTablePropertySection {

	private final AccountTransfersModifyListener listener=new AccountTransfersModifyListener(this);

	private final ITreeContentProvider contentProvider=new BoockletTransfersContentProvider();
	private final ILabelProvider labelProvider=new TransferLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof BoockletAccount);
			BoockletAccount boocklet=(BoockletAccount)currentEObject;

			String pageTitle=boocklet.getName();
			Tracker tracker=(Tracker)boocklet.eContainer().eContainer();

			AddBoockletTransferWizard wizard=new AddBoockletTransferWizard(pageTitle, tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Transfer newTansfer=null;
				if (wizard.isIncoming()) {
					newTansfer=TrackerFactory.eINSTANCE.createIncoming();
				}
				else if (wizard.isOutgoing()) {
					newTansfer=TrackerFactory.eINSTANCE.createOutgoing();
				}

				if (newTansfer == null) {
					return;
				}

				OperationTitle transferTitle=wizard.getTransferTitle();
				if (transferTitle != null) {
					newTansfer.setOperationTitle(transferTitle);
				}

				Origin transferOrigin=wizard.getTransferOrigin();
				if (transferOrigin != null) {
					newTansfer.setOrigin(transferOrigin);
				}

				ListenersUtils.executeAddCommand(boocklet, TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, newTansfer);
				refresh();
			}
		}
	};
	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof BoockletAccount);
			BoockletAccount boocklet=(BoockletAccount)currentEObject;

			ISelection selection=tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove=((StructuredSelection)selection).getFirstElement();
			ListenersUtils.executeRemoveCommand(boocklet, TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, elementToRemove);
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
		tableViewer.setInput(getTransfers());
		addListeners();
	}

	private List<Transfer> getTransfers() {
		Assert.isTrue(currentEObject instanceof BoockletAccount);
		EList<Transfer> transfers=((BoockletAccount)currentEObject).getTransfers();
		if (transfers == null || transfers.isEmpty()) {
			return Collections.emptyList();
		}
		return transfers;
	}

	@Override
	protected void addListeners() {
		tableViewer.addSelectionChangedListener(listener);
	}

	@Override
	protected void disposeListeners() {
		tableViewer.removeSelectionChangedListener(listener);
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonlistener, removeButtonListener);
	}
}
