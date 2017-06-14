/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.content.providers;

import fr.rostren.tracker.Tracker;

public class AbstractTrackerContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Tracker) {
			return getChildren(element).length > 0;
		}
		return false;
	}
}
