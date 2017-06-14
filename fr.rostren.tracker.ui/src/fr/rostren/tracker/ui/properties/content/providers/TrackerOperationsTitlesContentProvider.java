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

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.comparators.OperationTitleComparator;

public class TrackerOperationsTitlesContentProvider extends AbstractTrackerContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		List<OperationTitle> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof OperationTitle) {
					children.add((OperationTitle)element);
				}
			}
		}
		else if (parentElement instanceof Tracker) {
			children.addAll(((Category)parentElement).getOperationTitles());
		}
		Collections.sort(children, new OperationTitleComparator());
		return children.toArray();
	}
}
