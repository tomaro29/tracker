package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Date;

public class DatePropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Date) {
			return true;
		}
		return false;
	}
}
