package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Owner;

public class OwnerComparator implements Comparator<Owner> {

	@Override
	public int compare(Owner arg1, Owner arg2) {
		if (arg1 == null || arg2 == null || arg1.getFirstName() == null || arg1.getLastName() == null) {
			return -1;
		}
		if (arg2.getFirstName() == null || arg2.getLastName() == null) {
			return 1;
		}
		return arg1.getFirstName().compareTo(arg2.getFirstName()) & arg1.getLastName().compareTo(arg2.getLastName());
	}
}
