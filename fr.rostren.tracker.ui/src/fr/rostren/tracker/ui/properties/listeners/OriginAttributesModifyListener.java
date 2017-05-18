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

import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.origin.OriginAttributesPropertySection;

public class OriginAttributesModifyListener extends AbstractModifyListener {

	private final OriginAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public OriginAttributesModifyListener(OriginAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text id=section.getIdText();
		Text type=section.getTypeText();

		if (widget.equals(id)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.ORIGIN__IDENTIFIER, id.getText());
		}
		else if (widget.equals(type)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.ORIGIN__TYPE, OriginType.valueOf(type.getText()));
		}
	}
}
