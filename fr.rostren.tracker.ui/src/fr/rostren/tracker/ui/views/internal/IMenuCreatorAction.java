/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.views.internal;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Menu;

public interface IMenuCreatorAction extends IMenuCreator {
	/**
	 * Adds an action the to toolbar action menu
	 * @param parent the menu
	 * @param action the action to add
	 */
	default void addActionToMenu(Menu parent, Action action) {
		StringBuffer label=new StringBuffer();
		//add the numerical accelerator
		label.append('&');
		label.append(' ');
		label.append(action.getText());
		action.setText(label.toString());

		ActionContributionItem item=new ActionContributionItem(action);
		item.fill(parent, -1);
	}
}
