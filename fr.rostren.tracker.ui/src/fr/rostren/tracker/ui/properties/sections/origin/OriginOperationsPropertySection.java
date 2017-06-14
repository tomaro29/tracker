/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.origin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OriginOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddOriginOperationWizard;

public class OriginOperationsPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OriginOperationsContentProvider();
		labelProvider=new OperationLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Origin);
				Origin origin=(Origin)currentEObject;

				String pageTitle=origin.getIdentifier();
				Tracker tracker=(Tracker)origin.eContainer().eContainer();

				AddOriginOperationWizard wizard=new AddOriginOperationWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Optional<Operation> operation=wizard.getOperation();
					DomainUtils.executeAddCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, operation);
					refresh();
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Origin);
				Origin origin=(Origin)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations list
	 * @return the operations list
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Origin);
		List<Operation> operations=((Origin)currentEObject).getOperations();
		if (operations == null || operations.isEmpty()) {
			return Collections.emptyList();
		}
		return operations;
	}
}
