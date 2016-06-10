package fr.rostren.tracker.ui.properties.listeners;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.amount.AmountAttributesPropertySection;

public class AmountAttributesModifyListener extends AbstractModifyListener {

    private final AmountAttributesPropertySection section;

    public AmountAttributesModifyListener(AmountAttributesPropertySection section) {
	this.section = section;
    }

    @Override
    protected void executeModifyText(Widget widget) {
	EObject eObject = section.getCurrentEObject();
	Text text = section.getValueText();

	if (widget.equals(text))
	    executeSetCommand(eObject, TrackerPackage.Literals.AMOUNT__VALUE, new BigDecimal(text.getText()));
    }
}
