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

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOriginsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class TrackerOriginsPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new TrackerOriginsContentProvider();
		labelProvider=new OriginLabelProvider();
		addButtonListener=createAddButtonForOriginsRepository();
		removeButtonListener=createRemoveButtonForOriginsRepository();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the origins list
	 * @return the origins list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<Origin> origins=((Tracker)currentEObject).getOriginsRepository().getOrigins();
		if (origins == null || origins.isEmpty()) {
			return Collections.emptyList();
		}
		return origins.stream().map(origin -> (EObject)origin).collect(Collectors.toList());
	}
}
