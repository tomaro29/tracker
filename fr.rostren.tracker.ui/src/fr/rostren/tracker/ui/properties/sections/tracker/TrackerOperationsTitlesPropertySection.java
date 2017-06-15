/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.tracker;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOperationsTitlesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class TrackerOperationsTitlesPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new TrackerOperationsTitlesContentProvider();
		labelProvider=new OperationTitleLabelProvider();
		addButtonListener=createAddButtonForOperationsTitleRepository();
		removeButtonListener=createRemoveButtonForOperationsTitleRepository();

		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations titles list
	 * @return the operations titles list
	 */
	@Override
	protected List<EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<OperationTitle> operationsTitles=((Tracker)currentEObject).getOperationsTitlesRepositories().getOperationsTitles();
		if (operationsTitles == null || operationsTitles.isEmpty()) {
			return Collections.emptyList();
		}
		return operationsTitles.stream().map(operationTitle -> (EObject)operationTitle).collect(Collectors.toList());
	}
}