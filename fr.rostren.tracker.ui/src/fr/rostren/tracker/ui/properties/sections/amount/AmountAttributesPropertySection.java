package fr.rostren.tracker.ui.properties.sections.amount;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.ui.properties.listeners.AmountAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class AmountAttributesPropertySection extends AbstractAttributesPropertySection {
    protected Text valueText;

    private ModifyListener listener = new AmountAttributesModifyListener(this);

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.valueText = createLabeledText(body, null, "Value:"); //$NON-NLS-1$
	addListeners();
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);

	disposeListeners();
	valueText.setText(getAmountvalue());
	addListeners();
    }

    @Override
    public void refresh() {
	disposeListeners();
	valueText.setText(getAmountvalue());
	super.refresh();
	addListeners();
    }

    private String getAmountvalue() {
	Assert.isTrue(currentEObject instanceof Amount);
	BigDecimal value = ((Amount) currentEObject).getValue();
	if (value == null)
	    return StringUtils.EMPTY;
	return String.valueOf(value);
    }

    @Override
    protected void addListeners() {
	valueText.addModifyListener(listener);
    }

    @Override
    protected void disposeListeners() {
	valueText.removeModifyListener(listener);
    }

    public Text getValueText() {
	return valueText;
    }
}
