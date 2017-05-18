/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.presentation.dev;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.presentation.TrackerEditor;
import fr.rostren.tracker.ui.properties.tabbed.TrackerTabbedPropertySheetPage;

public class TrackerEditorDev extends TrackerEditor implements ITabbedPropertySheetPageContributor {
	@Override
	public String getContributorId() {
		return "fr.rostren.tracker.ui.common"; //$NON-NLS-1$
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter == IPropertySheetPage.class) {
			return new TrackerTabbedPropertySheetPage(this);
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Returns the edited {@link Tracker} instance
	 * @return the edited {@link Tracker} instance
	 */
	public Tracker getEditedTracker() {
		if (editingDomain != null) {
			ResourceSet resourceSet=editingDomain.getResourceSet();
			if (resourceSet == null) {
				throw new IllegalArgumentException(new NullPointerException("Invalid null ResourceSet")); //$NON-NLS-1$
			}

			for (Resource resource: resourceSet.getResources()) {
				if (resource == null) {
					throw new IllegalArgumentException(new NullPointerException("Invalid null Resource")); //$NON-NLS-1$
				}

				if (resource.getContents().isEmpty()) {
					return null;
				}

				final EObject root=resource.getContents().get(0);
				return root instanceof Tracker ? (Tracker)root : null;
			}
		}

		return null;
	}
}