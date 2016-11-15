package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.ui.properties.content.comparators.OperationComparator;

public class OriginOperationsContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Origin) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Operation> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Operation) {
					children.add((Operation)element);
				}
			}
		}
		else if (parentElement instanceof Origin) {
			children.addAll(((Origin)parentElement).getOperations());
		}
		Collections.sort(children, new OperationComparator());
		return children.toArray();
	}
}
