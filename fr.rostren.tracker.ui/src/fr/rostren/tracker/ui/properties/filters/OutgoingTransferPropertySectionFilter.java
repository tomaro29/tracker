/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.filters;

import org.eclipse.jface.viewers.IFilter;

import fr.rostren.tracker.Outgoing;

public class OutgoingTransferPropertySectionFilter implements IFilter {

	@Override
	public boolean select(Object object) {
		return object instanceof Outgoing;
	}
}
