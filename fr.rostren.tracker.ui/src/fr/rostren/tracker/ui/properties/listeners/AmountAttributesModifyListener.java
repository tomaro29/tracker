package fr.rostren.tracker.ui.properties.listeners;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.sections.amount.AmountAttributesPropertySection;

public class AmountAttributesModifyListener extends AbstractModifyListener {

	private final AmountAttributesPropertySection section;

	public AmountAttributesModifyListener(AmountAttributesPropertySection section) {
		this.section=section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		Text text=section.getValueText();
		CCombo combo=section.getCategoryCombo();

		if (widget.equals(text)) {
			ListenersUtils.executeSetCommand(eObject, TrackerPackage.Literals.AMOUNT__VALUE, new BigDecimal(text.getText()));
		}
		if (widget.equals(combo)) {
			Category category=TrackerUtils.getAmountCategory((Amount)eObject, combo.getText());
			ListenersUtils.executeSetCommand(eObject, TrackerPackage.Literals.AMOUNT__CATEGORY, category);
		}
	}
}
