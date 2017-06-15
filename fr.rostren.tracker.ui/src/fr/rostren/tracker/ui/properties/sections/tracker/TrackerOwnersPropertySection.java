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
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOwnersContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OwnerLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class TrackerOwnersPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new TrackerOwnersContentProvider();
		labelProvider=new OwnerLabelProvider();
		addButtonListener=createAddButtonForTrackerOwners();
		removeButtonListener=createRemoveButtonForTrackerOwners();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the owners list
	 * @return the owners list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<Owner> owners=((Tracker)currentEObject).getOwners();
		if (owners == null || owners.isEmpty()) {
			return Collections.emptyList();
		}
		return owners.stream().map(owner -> (EObject)owner).collect(Collectors.toList());
	}
}
