package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.date.DateAttributesPropertySection;

public class DateAttributesModifyListener extends AbstractModifyListener {

    private final DateAttributesPropertySection section;

    public DateAttributesModifyListener(DateAttributesPropertySection section) {
	this.section = section;
    }

    @Override
    protected void executeModifyText(Widget widget) {
	EObject eObject = section.getCurrentEObject();
	Text dayText = section.getDayText();
	Text monthText = section.getMonthText();
	Text yearText = section.getYearText();

	if (widget.equals(dayText))
	    executeSetCommand(eObject, TrackerPackage.Literals.DATE__DAY, Integer.parseInt(dayText.getText()));
	if (widget.equals(monthText))
	    executeSetCommand(eObject, TrackerPackage.Literals.DATE__MONTH, monthText.getText());
	if (widget.equals(yearText))
	    executeSetCommand(eObject, TrackerPackage.Literals.DATE__YEAR, Integer.parseInt(yearText.getText()));
    }
}
