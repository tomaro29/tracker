/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.tracker;

import java.util.Collections;
import java.util.List;

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

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOwnersContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OwnerLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOwnerWizard;

public class TrackerOwnersPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new TrackerOwnersContentProvider();
		labelProvider=new OwnerLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;

				AddTrackerOwnerWizard wizard=new AddTrackerOwnerWizard("Tracker", tracker); //$NON-NLS-1$
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Owner newOwner=TrackerFactory.eINSTANCE.createOwner();

					String ownerFirstName=wizard.getFirstName();
					if (ownerFirstName != null) {
						newOwner.setFirstName(ownerFirstName);
					}
					String ownerLastName=wizard.getLastName();
					if (ownerLastName != null) {
						newOwner.setLastName(ownerLastName);
					}

					DomainUtils.executeAddCommand(tracker, TrackerPackage.Literals.TRACKER__OWNERS, newOwner);
					refresh();
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(tracker, TrackerPackage.Literals.TRACKER__OWNERS, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the owners list
	 * @return the owners list
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<Owner> owners=((Tracker)currentEObject).getOwners();
		if (owners == null || owners.isEmpty()) {
			return Collections.emptyList();
		}
		return owners;
	}
}
