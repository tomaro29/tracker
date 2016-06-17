package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Transfer;

public class TransferComparator implements Comparator<Transfer> {

    @Override
    public int compare(Transfer arg1, Transfer arg2) {
	if (arg1 == null || arg2 == null)
	    return -1;
	OperationTitleComparator comparator = new OperationTitleComparator();
	return comparator.compare(arg1.getOperationTitle(), arg2.getOperationTitle());
    }
}
