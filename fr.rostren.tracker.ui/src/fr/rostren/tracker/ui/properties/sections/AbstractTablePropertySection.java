package fr.rostren.tracker.ui.properties.sections;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTablePropertySection extends AbstractTrackerPropertySection {
	public TableViewer tableViewer;
	protected Button addButton;
	protected Button removeButton;
	protected Table table;

	/**
	 * Creates a table
	 * @param composite the composite parent of the {@link Table} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created table
	 */
	protected Table createTable(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Table table=widgetFactory.createTable(composite, SWT.V_SCROLL | SWT.MULTI);

		table.addListener(SWT.MouseWheel, new Listener() {
			@Override
			public void handleEvent(Event event) {
				table.getVerticalBar().setIncrement(event.count * 3);
			}
		});

		addButton=widgetFactory.createButton(composite, "Add", SWT.PUSH); //$NON-NLS-1$
		addButton.addSelectionListener(addButtonlistener);

		removeButton=widgetFactory.createButton(composite, "Remove", SWT.PUSH); //$NON-NLS-1$
		removeButton.addSelectionListener(removeButtonListener);

		FormData data=new FormData();
		data=new FormData();
		data.left=new FormAttachment(0, 5);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, -85);
		table.setLayoutData(data);

		data=new FormData();
		data.width=75;
		data.left=new FormAttachment(table, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(table, 0, SWT.TOP);
		addButton.setLayoutData(data);

		data=new FormData();
		data.width=75;
		data.left=new FormAttachment(table, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(addButton, ITabbedPropertyConstants.VSPACE);
		removeButton.setLayoutData(data);

		return table;
	}

	/**
	 * Disposes buttons listeners
	 * @param addButtonlistener the add listener
	 * @param removeButtonListener the remove listener
	 */
	protected void disposeButtonsListeners(SelectionAdapter addButtonlistener, SelectionAdapter removeButtonListener) {
		if (addButton != null && !addButton.isDisposed()) {
			addButton.removeSelectionListener(addButtonlistener);
		}
		if (removeButton != null && !removeButton.isDisposed()) {
			removeButton.removeSelectionListener(removeButtonListener);
		}
	}
}
