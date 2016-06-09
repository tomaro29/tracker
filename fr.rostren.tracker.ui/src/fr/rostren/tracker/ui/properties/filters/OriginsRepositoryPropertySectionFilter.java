package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.OriginsRepository;

public class OriginsRepositoryPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof OriginsRepository)
	    return true;
	return false;
    }
}
