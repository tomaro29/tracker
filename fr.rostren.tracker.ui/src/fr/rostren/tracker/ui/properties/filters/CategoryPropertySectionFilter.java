package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Category;

public class CategoryPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof Category)
	    return true;
	return false;
    }
}
