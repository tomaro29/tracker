package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.ui.properties.content.comparators.OriginComparator;

public class OriginsRepositoryContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof OriginsRepository)
			return getChildren(element).length > 0;
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Origin> children = new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element : (List<?>) parentElement) {
				if (element instanceof Origin)
					children.add((Origin) element);
			}
		} else if (parentElement instanceof OriginsRepository) {
			children.addAll(((OriginsRepository) parentElement).getOrigins());
		}
		Collections.sort(children, new OriginComparator());
		return children.toArray();
	}
}
