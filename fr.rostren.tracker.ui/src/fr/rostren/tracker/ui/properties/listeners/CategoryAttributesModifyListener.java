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
import fr.rostren.tracker.ui.properties.sections.category.CategoryAttributesPropertySection;

public class CategoryAttributesModifyListener extends AbstractModifyListener {

	private final CategoryAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public CategoryAttributesModifyListener(CategoryAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text title=section.getTitleText();
		Text description=section.getDescriptionText();

		if (widget.equals(title)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.TITLE__TITLE, title.getText());
		}
		if (widget.equals(description)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.CATEGORY__DESCRIPTION, description.getText());
		}
	}
}
