package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Operation;

public class OperationComparator implements Comparator<Operation> {

	@Override
	public int compare(Operation arg1, Operation arg2) {
		if (arg1 == null || arg2 == null) {
			return -1;
		}
		OperationTitleComparator comparator=new OperationTitleComparator();
		return comparator.compare(arg1.getOperationTitle(), arg2.getOperationTitle());
	}
}
