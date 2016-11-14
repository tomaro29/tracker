package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Operation;

public class OperationPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Operation)
			return true;
		return false;
	}
}
