package fr.rostren.tracker.presentation.dev;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.presentation.TrackerEditor;

public class TrackerEditorDev extends TrackerEditor {

	public Tracker getEditedTracker() {
		if (editingDomain != null) {
			ResourceSet resourceSet = editingDomain.getResourceSet();
			if (resourceSet == null)
				throw new IllegalArgumentException(new NullPointerException(
						"Invalid null ResourceSet"));

			for (Resource resource : resourceSet.getResources()) {
				if (resource == null)
					throw new IllegalArgumentException(
							new NullPointerException("Invalid null Resource"));

				if (resource.getContents().isEmpty())
					return null;

				final EObject root = resource.getContents().get(0);
				return (root instanceof Tracker) ? (Tracker) root : null;
			}
		}

		return null;
	}
}
