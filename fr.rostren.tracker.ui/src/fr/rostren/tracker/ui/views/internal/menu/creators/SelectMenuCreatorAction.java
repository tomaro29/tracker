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

public class SelectMenuCreatorAction extends AbstractMenuCreatorAction {

	/**
	 * Constructor.
	 * @param view the histogram view in witch the selection will be applied
	 */
	public SelectMenuCreatorAction(TrackerHistogramView view) {
		super(view);
		setText("Select"); //$NON-NLS-1$
	}

	@Override
	public Menu getMenu(Control parent) {
		if (menu != null && !menu.isDisposed()) {
			menu.dispose();
		}

		menu=new Menu(parent);
		addActionToMenu(menu, new SelectAccountMenuCreatorAction(view));
		addActionToMenu(menu, new SelectYearMenuCreatorAction(view));

		return menu;
	}
}
