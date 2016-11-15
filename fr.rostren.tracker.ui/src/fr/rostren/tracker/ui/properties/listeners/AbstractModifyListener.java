package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Widget;

public abstract class AbstractModifyListener implements ModifyListener {

	@Override
	public void modifyText(ModifyEvent event) {
		Widget widget=event.widget;
		if (widget == null || widget.isDisposed()) {
			return;
		}

		executeModify(widget);
	}

	/**
	 * Executes the modify action
	 * @param widget the widget
	 */
	abstract protected void executeModify(Widget widget);
}
