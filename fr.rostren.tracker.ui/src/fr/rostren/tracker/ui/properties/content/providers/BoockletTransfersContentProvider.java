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

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.content.comparators.TransferComparator;

public class BoockletTransfersContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof BoockletAccount) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Transfer> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Transfer) {
					children.add((Transfer)element);
				}
			}
		}
		else if (parentElement instanceof BoockletAccount) {
			children.addAll(((BoockletAccount)parentElement).getTransfers());
		}
		Collections.sort(children, new TransferComparator());
		return children.toArray();
	}
}
