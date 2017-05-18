/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.tabbed;

import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class TrackerTabbedPropertySheetPage extends TabbedPropertySheetPage {

	/**
	 * Constructor.
	 * @param contributor the contributor
	 */
	public TrackerTabbedPropertySheetPage(ITabbedPropertySheetPageContributor contributor) {
		super(contributor);
	}

	@Override
	public void resizeScrolledComposite() {
		// DO NOTHING
	}
}
