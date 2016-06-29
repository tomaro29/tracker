package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.ui.properties.content.comparators.OperationComparator;

public class CheckingOperationsContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof CheckingAccount)
			return getChildren(element).length > 0;
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Operation> children = new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element : (List<?>) parentElement) {
				if (element instanceof Operation)
					children.add((Operation) element);
			}
		} else if (parentElement instanceof CheckingAccount) {
			children.addAll(((CheckingAccount) parentElement).getOperations());
		}
		Collections.sort(children, new OperationComparator());
		return children.toArray();
	}
}
