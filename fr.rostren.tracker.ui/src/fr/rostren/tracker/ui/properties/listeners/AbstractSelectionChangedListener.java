package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public abstract class AbstractSelectionChangedListener implements ISelectionChangedListener {

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection=event.getSelection();
		if (selection == null || selection.isEmpty()) {
			return;
		}

		executeSelectionChanged(selection);
	}

	protected abstract void executeSelectionChanged(ISelection selection);
}
