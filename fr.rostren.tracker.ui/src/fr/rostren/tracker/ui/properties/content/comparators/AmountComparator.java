package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Amount;

public class AmountComparator implements Comparator<Amount> {

	@Override
	public int compare(Amount arg1, Amount arg2) {
		if (arg1 == null || arg2 == null) {
			return -1;
		}
		return Double.compare(arg1.getValue(), arg2.getValue());
	}
}
