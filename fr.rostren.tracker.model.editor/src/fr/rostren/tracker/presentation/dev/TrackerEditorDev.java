package fr.rostren.tracker.presentation.dev;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.presentation.TrackerEditor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A custom tracker editor.
 * 
 * @author maro
 *
 */
public class TrackerEditorDev extends TrackerEditor {

	/**
	 * Returns the edited tracker.
	 * 
	 * @return the tracker
	 */
	public Tracker getEditedTracker() {
		Tracker tracker = null;
		if (editingDomain != null) {
			ResourceSet resourceSet = editingDomain.getResourceSet();
			if (resourceSet == null) {
				throw new IllegalArgumentException(new NullPointerException(
						"Invalid null ResourceSet"));
			}

			for (Resource resource : resourceSet.getResources()) {
				if (resource == null) {
					throw new IllegalArgumentException(
							new NullPointerException("Invalid null Resource"));
				}

				if (resource.getContents().isEmpty()) {
					return null;
				}

				final EObject root = resource.getContents().get(0);
				tracker = (root instanceof Tracker) ? (Tracker) root : null;
			}
		}

		return tracker;
	}
}
