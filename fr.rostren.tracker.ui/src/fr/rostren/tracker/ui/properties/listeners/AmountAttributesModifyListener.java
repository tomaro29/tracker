/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.amount.AmountAttributesPropertySection;

public class AmountAttributesModifyListener extends AbstractModifyListener {

	private final AmountAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public AmountAttributesModifyListener(AmountAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text text=section.getValueText();
		CCombo combo=section.getCategoryCombo();

		if (widget.equals(text)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.AMOUNT__VALUE, Double.valueOf(text.getText()));
		}
		if (widget.equals(combo)) {
			Category category=TrackerUtils.findAmountCategory((Amount)eObject, combo.getText()).orElseThrow(IllegalArgumentException::new);
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.AMOUNT__CATEGORY, category);
		}
	}
}
