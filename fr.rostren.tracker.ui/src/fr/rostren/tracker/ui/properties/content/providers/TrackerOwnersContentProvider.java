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

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.comparators.OwnerComparator;

public class TrackerOwnersContentProvider extends AbstractTrackerContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Owner> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Owner) {
					children.add((Owner)element);
				}
			}
		}
		else if (parentElement instanceof Tracker) {
			children.addAll(((Tracker)parentElement).getOwners());
		}
		Collections.sort(children, new OwnerComparator());
		return children.toArray();
	}
}
