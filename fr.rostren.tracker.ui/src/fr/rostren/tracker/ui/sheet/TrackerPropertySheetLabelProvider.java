/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.sheet;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

public class TrackerPropertySheetLabelProvider extends DecoratingLabelProvider {

	/**
	 * Constructor
	 * @param provider the label provider
	 */
	public TrackerPropertySheetLabelProvider(ILabelProvider provider) {
		super(provider, null);
	}
}
