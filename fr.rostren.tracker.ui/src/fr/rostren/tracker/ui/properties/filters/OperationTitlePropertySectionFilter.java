package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.OperationTitle;

public class OperationTitlePropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof OperationTitle)
	    return true;
	return false;
    }
}