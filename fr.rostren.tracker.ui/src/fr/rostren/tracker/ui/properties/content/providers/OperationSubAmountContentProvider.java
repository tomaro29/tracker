/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.ui.properties.content.comparators.AmountComparator;

public class OperationSubAmountContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Operation) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Amount> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Amount) {
					children.add((Amount)element);
				}
			}
		}
		else if (parentElement instanceof Operation) {
			children.addAll(((Operation)parentElement).getSubAmounts());
		}
		Collections.sort(children, new AmountComparator());
		return children.toArray();
	}
}
