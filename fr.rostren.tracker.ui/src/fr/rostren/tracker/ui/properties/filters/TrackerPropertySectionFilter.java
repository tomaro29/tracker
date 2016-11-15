package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Tracker;

public class TrackerPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Tracker) {
			return true;
		}
		return false;
	}
}
