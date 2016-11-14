package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.CategoriesRepository;

public class CategoriesRepositoryPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof CategoriesRepository)
			return true;
		return false;
	}
}
