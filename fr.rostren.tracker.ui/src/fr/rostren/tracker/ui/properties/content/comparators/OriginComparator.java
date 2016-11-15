package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Origin;

public class OriginComparator implements Comparator<Origin> {

	@Override
	public int compare(Origin arg1, Origin arg2) {
		if (arg1 == null || arg2 == null || arg1.getIdentifier() == null) {
			return -1;
		}
		if (arg1.getIdentifier() == null) {
			return 1;
		}
		return arg1.getIdentifier().compareTo(arg2.getIdentifier());
	}
}
