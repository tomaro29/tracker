package fr.rostren.tracker.ui.views.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import fr.rostren.tracker.ui.views.TrackerHistogramView;

public class FilterSelectionListener implements SelectionListener {

	private final Button otherButton;
	private final Combo attachedCombo;
	private final Combo otherCombo;
	private final TrackerHistogramView view;

	/**
	 * Constructor
	 * @param otherButton the other linked filter button
	 * @param attachedCombo the linked filter combo
	 * @param otherCombo the other linked filter combo
	 * @param view the related histogram view
	 */
	public FilterSelectionListener(Button otherButton, Combo attachedCombo, Combo otherCombo, TrackerHistogramView view) {
		this.otherButton=otherButton;
		this.attachedCombo=attachedCombo;
		this.otherCombo=otherCombo;
		this.view=view;
	}

	/**
	 * Constructor.
	 * @param view the related histogram view
	 */
	public FilterSelectionListener(TrackerHistogramView view) {
		this.view=view;
		otherButton=null;
		attachedCombo=null;
		otherCombo=null;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		//Do Nothing
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (otherButton != null && attachedCombo != null) {
			Object source=event.getSource();
			Assert.isTrue(source instanceof Button);

			Button button=(Button)source;
			otherButton.setSelection(!button.getSelection());
			otherCombo.setEnabled(!button.getSelection());
			attachedCombo.setEnabled(button.getSelection());
		}

		//refresh the histogram population according to selection
		view.refresh();
	}
}
