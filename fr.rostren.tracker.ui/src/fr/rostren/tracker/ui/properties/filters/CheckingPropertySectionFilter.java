package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.CheckingAccount;

public class CheckingPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof CheckingAccount) {
			return true;
		}
		return false;
	}
}
