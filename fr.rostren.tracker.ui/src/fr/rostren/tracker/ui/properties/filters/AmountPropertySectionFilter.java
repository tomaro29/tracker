package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Amount;

public class AmountPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object object) {
	if (object instanceof Amount)
	    return true;
	return false;
    }
}
