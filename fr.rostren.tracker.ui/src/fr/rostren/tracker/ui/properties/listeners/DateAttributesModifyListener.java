package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.date.DateAttributesPropertySection;

public class DateAttributesModifyListener extends AbstractModifyListener {

	private final DateAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public DateAttributesModifyListener(DateAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text dayText=section.getDayText();
		Text monthText=section.getMonthText();
		Text yearText=section.getYearText();

		if (widget.equals(dayText)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.DATE__DAY, Integer.parseInt(dayText.getText()));
		}
		if (widget.equals(monthText)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.DATE__MONTH, monthText.getText());
		}
		if (widget.equals(yearText)) {
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.DATE__YEAR, Integer.parseInt(yearText.getText()));
		}
	}
}
