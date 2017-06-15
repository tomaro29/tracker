/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OperationsTitlesRepositoryPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new OperationsTitlesRepositoryContentProvider();
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
		Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
		List<OperationTitle> operationsTitles=((OperationsTitleRepository)currentEObject).getOperationsTitles();
		if (operationsTitles == null || operationsTitles.isEmpty()) {
			return Collections.emptyList();
		}
		return operationsTitles.stream().map(operationTitle -> (EObject)operationTitle).collect(Collectors.toList());
	}
}
