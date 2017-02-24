package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.comparators.CategoryComparator;

public class CategoriesRepositoryContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof CategoriesRepository) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Category> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Category) {
					children.add((Category)element);
				}
			}
		}
		else if (parentElement instanceof CategoriesRepository) {
			for (Category category: TrackerUtils.getTrackerService((EObject)parentElement).getAllCategories()) {
				children.addAll(TrackerUtils.getCategoryService(category).getCategories());
			}
		}
		Collections.sort(children, new CategoryComparator());
		return children.toArray();
	}
}
