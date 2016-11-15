package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.ui.properties.content.comparators.OperationTitleComparator;

public class OperationsTitlesRepositoryContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof OperationsTitleRepository) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<OperationTitle> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof OperationTitle) {
					children.add((OperationTitle)element);
				}
			}
		}
		else if (parentElement instanceof OperationsTitleRepository) {
			children.addAll(((OperationsTitleRepository)parentElement).getOperationsTitles());
		}
		Collections.sort(children, new OperationTitleComparator());
		return children.toArray();
	}
}
