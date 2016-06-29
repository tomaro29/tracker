package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.ui.properties.content.comparators.OperationTitleComparator;

public class CategoryOperationsTitlesContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Category)
			return getChildren(element).length > 0;
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<OperationTitle> children = new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element : (List<?>) parentElement) {
				if (element instanceof OperationTitle)
					children.add((OperationTitle) element);
			}
		} else if (parentElement instanceof Category) {
			children.addAll(((Category) parentElement).getOperationTitles());
		}
		Collections.sort(children, new OperationTitleComparator());
		return children.toArray();
	}
}
