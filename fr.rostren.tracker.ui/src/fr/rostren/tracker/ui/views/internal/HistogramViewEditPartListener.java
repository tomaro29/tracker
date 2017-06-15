/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.views.internal;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import fr.rostren.tracker.presentation.TrackerEditor;
import fr.rostren.tracker.ui.views.TrackerHistogramView;

public class HistogramViewEditPartListener implements IPartListener2 {
	private final TrackerHistogramView view;

	/**
	 * Constructor
	 * @param view the histogram view
	 */
	public HistogramViewEditPartListener(TrackerHistogramView view) {
		this.view=view;
	}

	@Override
	public void partActivated(IWorkbenchPartReference partReference) {
		updateHistorgamView(partReference.getPage().getActivePart());
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference arg0) {
		//DO NOTHING
	}

	@Override
	public void partClosed(IWorkbenchPartReference partReference) {
		updateHistorgamView(partReference.getPage().getActivePart());
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference arg0) {
		//DO NOTHING
	}

	@Override
	public void partHidden(IWorkbenchPartReference arg0) {
		//DO NOTHING
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference arg0) {
		//DO NOTHING
	}

	@Override
	public void partOpened(IWorkbenchPartReference partReference) {
		if (partReference instanceof IEditorReference) {
			updateHistorgamView(partReference.getPage().getActivePart());
		}
	}

	@Override
	public void partVisible(IWorkbenchPartReference arg0) {
		//DO NOTHING
	}

	/**
	 * Updates the histogram view actions icons according to the current active part.
	 * @param part the current active part
	 */
	private void updateHistorgamView(IWorkbenchPart part) {
		if (part instanceof TrackerEditor) {
			view.refresh();
		}
	}
}
