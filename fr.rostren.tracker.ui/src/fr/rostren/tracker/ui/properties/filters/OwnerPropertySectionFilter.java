package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Owner;

public class OwnerPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Owner) {
			return true;
		}
		return false;
	}
}
