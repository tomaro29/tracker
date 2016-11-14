package fr.rostren.tracker.ui.properties.sections.date;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.ui.properties.listeners.DateAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class DateAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text dayText;
	protected Text monthText;
	protected Text yearText;

	private final ModifyListener listener = new DateAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		dayText = createLabeledText(body, null, "Day:"); //$NON-NLS-1$
		monthText = createLabeledText(body, dayText, "Month:"); //$NON-NLS-1$
		yearText = createLabeledText(body, monthText, "Year:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		dayText.setText(getDateDay());
		monthText.setText(getDateMonth());
		yearText.setText(getDateYear());
		addListeners();
	}

	private String getDateYear() {
		Assert.isTrue(currentEObject instanceof Date);
		return String.valueOf(((Date) currentEObject).getYear());
	}

	private String getDateMonth() {
		Assert.isTrue(currentEObject instanceof Date);
		return ((Date) currentEObject).getMonth().toString();
	}

	private String getDateDay() {
		Assert.isTrue(currentEObject instanceof Date);
		return String.valueOf(((Date) currentEObject).getDay());
	}

	@Override
	protected void addListeners() {
		dayText.addModifyListener(listener);
		monthText.addModifyListener(listener);
		yearText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		dayText.removeModifyListener(listener);
		monthText.removeModifyListener(listener);
		yearText.removeModifyListener(listener);
	}

	public Text getDayText() {
		return dayText;
	}

	public Text getMonthText() {
		return monthText;
	}

	public Text getYearText() {
		return yearText;
	}
}
