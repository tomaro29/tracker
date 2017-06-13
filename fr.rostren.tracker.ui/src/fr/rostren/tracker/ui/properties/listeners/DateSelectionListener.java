/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.listeners;

import java.time.LocalDate;
import java.time.Month;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DateTime;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class DateSelectionListener implements SelectionListener {

	private final AbstractAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public DateSelectionListener(AbstractAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		// Do Nothing
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		EObject currentEObject=section.getCurrentEObject();
		if (currentEObject instanceof Operation) {
			updateOperationDate(event, (Operation)currentEObject);
		}
		else if (currentEObject instanceof Amount) {
			updateAmountDate(event, (Amount)currentEObject);
		}
	}

	/**
	 * Updates the date according to the selected date
	 * @param event the selection event
	 * @param operation the operation to update
	 */
	private void updateOperationDate(SelectionEvent event, Operation operation) {
		Object source=event.getSource();
		Assert.isTrue(source instanceof DateTime);
		DateTime newDate=(DateTime)source;

		DomainUtils.executeSetCommand(operation, TrackerPackage.Literals.OPERATION__DATE, LocalDate.of(newDate.getYear(), Month.of(newDate.getMonth()), newDate.getDay()));
	}

	/**
	 * Updates the date according to the selected date
	 * @param event the selection event
	 * @param amount the amount to update
	 */
	private void updateAmountDate(SelectionEvent event, Amount amount) {
		Object source=event.getSource();
		Assert.isTrue(source instanceof DateTime);
		DateTime newDate=(DateTime)source;

		DomainUtils.executeSetCommand(amount, TrackerPackage.Literals.AMOUNT__WISHED_DATE, LocalDate.of(newDate.getYear(), Month.of(newDate.getMonth()), newDate.getDay()));
	}
}
