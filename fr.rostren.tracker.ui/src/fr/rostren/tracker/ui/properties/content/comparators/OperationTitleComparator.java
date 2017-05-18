/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.OperationTitle;

public class OperationTitleComparator implements Comparator<OperationTitle> {

	@Override
	public int compare(OperationTitle arg1, OperationTitle arg2) {
		if (arg1 == null || arg2 == null || arg1.getTitle() == null) {
			return -1;
		}
		if (arg2.getTitle() == null) {
			return 1;
		}
		return arg1.getTitle().compareTo(arg2.getTitle());
	}
}
