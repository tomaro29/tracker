package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.BoockletAccount;

public class BoockletPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof BoockletAccount)
	    return true;
	return false;
    }
}
