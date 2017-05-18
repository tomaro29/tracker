/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.filters;

import java.time.LocalDate;

import org.eclipse.jface.viewers.IFilter;

public class DatePropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		if (object instanceof LocalDate) {
			return true;
		}
		return false;
	}
}
