/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.operation.title.OperationTitleAttributesPropertySection;

public class OperationTitleAttributesModifyListener extends AbstractModifyListener {

	private final OperationTitleAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public OperationTitleAttributesModifyListener(OperationTitleAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text text=section.getTitleText();

		if (widget.equals(text)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.TITLE__TITLE, text.getText());
		}
	}
}
