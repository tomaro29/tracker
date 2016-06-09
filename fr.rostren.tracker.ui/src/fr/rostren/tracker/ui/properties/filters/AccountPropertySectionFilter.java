package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Account;

public class AccountPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof Account)
	    return true;
	return false;
    }
}
