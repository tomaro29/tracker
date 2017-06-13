/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.views.internal.menu.creators;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import fr.rostren.tracker.ui.views.TrackerHistogramView;
import fr.rostren.tracker.ui.views.internal.AbstractMenuCreatorAction;

public class FilterByMenuCreatorAction extends AbstractMenuCreatorAction {

	/**
	 * Constructor.
	 * @param view the histogram view in witch the filter will be applied
	 */
	public FilterByMenuCreatorAction(TrackerHistogramView view) {
		super(view);
		setText("Filter By"); //$NON-NLS-1$
		setImageDescriptor("icons/filter16X16.png"); //$NON-NLS-1$
	}

	@Override
	public Menu getMenu(Control parent) {
		if (menu != null && !menu.isDisposed()) {
			menu.dispose();
		}

		menu=new Menu(parent);
		//XXX add actions to this menu
		return menu;
	}
}
