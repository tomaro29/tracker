package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.ui.properties.content.comparators.CategoryComparator;

public class CategorySubCategoriesContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Category) {
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
		else if (parentElement instanceof IncomeCategory) {
			children.addAll(((IncomeCategory)parentElement).getIncomes());
		}
		else if (parentElement instanceof SpendingCategory) {
			children.addAll(((SpendingCategory)parentElement).getSpendings());
		}
		Collections.sort(children, new CategoryComparator());
		return children.toArray();
	}
}
