/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import java.time.LocalDate;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.ui.properties.pages.OperationSubAmountWizardPage;

/**
 * Wizard to add an {@link Amount} instance to an existing
 * {@link Operation} instance.
 */
public class OperationSubAmountWizard extends Wizard {

	protected OperationSubAmountWizardPage page;
	private final boolean isAdd;

	/**
	 * Constructor.
	 * @param tracker the tracker
	 * @param operation the operation
	 * @param amount the amount to edit if any, <code>null</code> otherwise
	 * @param isAdd <code>true</code> if the action is a result of an Add action, <code>false</code> if is edit one
	 */
	public OperationSubAmountWizard(Tracker tracker, OperationData operation, Amount amount, boolean isAdd) {
		super();
		this.isAdd=isAdd;
		page=new OperationSubAmountWizardPage(operation.getOperationTitle().getTitle(), tracker, operation, amount, isAdd);
	}

	@Override
	public String getWindowTitle() {
		return isAdd ? "Add Operation Amount." : "Edit Operation Amount."; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	/**
	 * Returns the amount category
	 * @return the amount category
	 */
	public Category getAmountCategory() {
		return page.getAmountCategory();
	}

	/**
	 * Returns the amount value
	 * @return the amount value
	 */
	public double getAmountValue() {
		return page.getAmountValue();
	}

	/**
	 * Returns the amount wished date
	 * @return the amount wished date
	 */
	public LocalDate getAmountWishedDate() {
		return page.getAmountWishedDate();
	}
}
