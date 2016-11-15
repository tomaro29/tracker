package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Outgoing;

public class OutgoingTransferPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof Outgoing) {
			return true;
		}
		return false;
	}
}
