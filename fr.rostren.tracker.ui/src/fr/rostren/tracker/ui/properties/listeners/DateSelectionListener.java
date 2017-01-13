package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DateTime;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class DateSelectionListener implements SelectionListener {

	private final DateTime dateTime;
	private final AbstractAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 * @param dateTime the date time widget
	 */
	public DateSelectionListener(AbstractAttributesPropertySection section, DateTime dateTime) {
		this.dateTime=dateTime;
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
			Operation operation=(Operation)currentEObject;
			Date dateToUpdate=operation.getDate();
			updateDate(event, dateToUpdate);
		}
		else if (currentEObject instanceof Amount) {
			Amount amount=(Amount)currentEObject;
			Date dateToUpdate=amount.getWishedDate();
			updateDate(event, dateToUpdate);
		}
	}

	/**
	 * Updates the date according to the selected date
	 * @param event the selection event
	 * @param dateToUpdate the date to update
	 */
	private void updateDate(SelectionEvent event, Date dateToUpdate) {
		Object source=event.getSource();
		Assert.isTrue(source instanceof DateTime);
		DateTime newDate=(DateTime)source;

		DomainUtils.executeSetCommand(dateToUpdate, TrackerPackage.Literals.DATE__YEAR, newDate.getYear());
		DomainUtils.executeSetCommand(dateToUpdate, TrackerPackage.Literals.DATE__MONTH, Month.get(newDate.getMonth()));
		DomainUtils.executeSetCommand(dateToUpdate, TrackerPackage.Literals.DATE__DAY, newDate.getDay());
	}
}
