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
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.operation.OperationAttributesPropertySection;

public class OperationAttributesModifyListener extends AbstractModifyListener {

	private final OperationAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public OperationAttributesModifyListener(OperationAttributesPropertySection section) {
		this.section=section;
	}

	@Override

	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		CCombo titleCombo=section.getTitleCombo();
		CCombo originCombo=section.getOriginCombo();
		Text totalAmountText=section.getTotalAmountText();

		Operation operation=(Operation)eObject;
		OperationService service=TrackerUtils.getOperationService(operation);
		if (widget.equals(titleCombo)) {
			OperationTitle title=service.findOperationTitle(titleCombo.getText()).orElseThrow(IllegalArgumentException::new);
			DomainUtils.executeSetCommand(operation, TrackerPackage.Literals.OPERATION__OPERATION_TITLE, title);
		}
		if (widget.equals(originCombo)) {
			Origin origin=service.findOperationOrigin(originCombo.getText()).orElseThrow(IllegalArgumentException::new);
			DomainUtils.executeSetCommand(operation, TrackerPackage.Literals.OPERATION__ORIGIN, origin);
		}
		if (widget.equals(totalAmountText)) {
			double amount=Double.parseDouble(totalAmountText.getText());
			DomainUtils.executeSetCommand(operation, TrackerPackage.Literals.OPERATION__TOTAL_AMOUNT, amount);
			if (operation.getSubAmounts().isEmpty()) {
				Amount newAmount=TrackerFactory.eINSTANCE.createAmount(operation, amount, null);
				DomainUtils.executeAddCommand(operation, TrackerPackage.Literals.OPERATION__SUB_AMOUNTS, newAmount);
			}
			if (operation.getSubAmounts().size() == 1) {
				Amount subAmount=operation.getSubAmounts().get(0);
				DomainUtils.executeSetCommand(subAmount, TrackerPackage.Literals.AMOUNT__VALUE, amount);
			}
		}
	}
}
