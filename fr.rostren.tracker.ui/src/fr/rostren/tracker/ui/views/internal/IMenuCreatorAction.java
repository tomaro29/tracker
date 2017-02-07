/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.views.internal;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Menu;

public interface IMenuCreatorAction extends IMenuCreator {
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
