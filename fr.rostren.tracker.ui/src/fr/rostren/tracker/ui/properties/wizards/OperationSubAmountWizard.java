/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import java.time.LocalDate;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.ui.properties.pages.OperationSubAmountWizardPage;

/**
 * Wizard to add an {@link Amount} instance to an existing
 * {@link Operation} instance.
 */
public class OperationSubAmountWizard extends AbstractAddWizard {

	/**
	 * Constructor.
	 * @param tracker the tracker
	 * @param operation the operation
	 * @param operationType the operation type as selected in the combo
	 * @param amount the amount to edit if any, <code>null</code> otherwise
	 * @param isAdd <code>true</code> if the action is a result of an Add action, <code>false</code> if is edit one
	 */
	public OperationSubAmountWizard(Tracker tracker, OperationData operation, OperationType operationType, Amount amount, boolean isAdd) {
		super();
		page=new OperationSubAmountWizardPage(operation.getOperationTitle().getTitle(), tracker, operation, operationType, amount, isAdd);
		title=isAdd ? "Add Operation Amount." : "Edit Operation Amount."; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Returns the amount category
	 * @return the amount category
	 */
	public Category getAmountCategory() {
		return ((OperationSubAmountWizardPage)page).getAmountCategory();
	}

	/**
	 * Returns the amount value
	 * @return the amount value
	 */
	public double getAmountValue() {
		return ((OperationSubAmountWizardPage)page).getAmountValue();
	}

	/**
	 * Returns the amount wished date
	 * @return the amount wished date
	 */
	public LocalDate getAmountWishedDate() {
		return ((OperationSubAmountWizardPage)page).getAmountWishedDate();
	}
}
