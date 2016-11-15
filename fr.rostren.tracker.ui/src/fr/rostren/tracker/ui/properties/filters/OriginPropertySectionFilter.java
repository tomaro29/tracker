package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Origin;

public class OriginPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Origin) {
			return true;
		}
		return false;
	}
}
