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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CheckingOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCheckOperationWizard;

public class CheckingOperationsPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CheckingOperationsContentProvider();
		labelProvider=new OperationLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CheckingAccount);
				CheckingAccount checking=(CheckingAccount)currentEObject;

				String pageTitle=checking.getName();
				Tracker tracker=TrackerUtils.getTracker(checking);

				AddCheckOperationWizard wizard=new AddCheckOperationWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Operation newOperation=null;
					if (wizard.isCredit()) {
						newOperation=TrackerFactory.eINSTANCE.createCredit();
					}
					else if (wizard.isDebit()) {
						newOperation=TrackerFactory.eINSTANCE.createDebit();
					}
					else if (wizard.isIncoming()) {
						newOperation=TrackerFactory.eINSTANCE.createIncoming();
					}
					else if (wizard.isOutgoing()) {
						newOperation=TrackerFactory.eINSTANCE.createOutgoing();
					}

					if (newOperation == null) {
						return;
					}

					OperationTitle operationTitle=wizard.getOperationTitle();
					if (operationTitle != null) {
						newOperation.setOperationTitle(operationTitle);
					}

					Origin operationOrigin=wizard.getOperationOrigin();
					if (operationOrigin != null) {
						newOperation.setOrigin(operationOrigin);
					}

					DomainUtils.executeAddCommand(checking, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newOperation);
					refresh();
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CheckingAccount);
				CheckingAccount account=(CheckingAccount)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations
	 * @return the operations
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof CheckingAccount);
		EList<Operation> operations=((CheckingAccount)currentEObject).getOperations();
		if (operations == null || operations.isEmpty()) {
			return Collections.emptyList();
		}
		return operations;
	}
}
