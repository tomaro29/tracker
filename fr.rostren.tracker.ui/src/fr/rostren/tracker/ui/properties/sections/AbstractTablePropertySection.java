package fr.rostren.tracker.ui.properties.sections;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
		formatTableLayout(control, table, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		addButton=widgetFactory.createButton(composite, "Add", SWT.PUSH); //$NON-NLS-1$
		addButton.addSelectionListener(addButtonlistener);
		formatAddButton(table, table);

		removeButton=widgetFactory.createButton(composite, "Remove", SWT.PUSH); //$NON-NLS-1$
		removeButton.addSelectionListener(removeButtonListener);
		formatRemoveButton(table, addButton);

		return table;
	}

	/**
	 * Formats the table layout
	 * @param control the text control, as a top attachment
	 * @param table the table to format
	 * @param font the table content font
	 */
	private void formatTableLayout(Text control, Table table, Font font) {
		FormData data=new FormData();
		data.left=new FormAttachment(0, 5);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, -85);
		data.height=275;
		table.setLayoutData(data);
		table.setFont(font);
	}

	/**
	 * Formats the remove button layout
	 * @param leftAttachment the left composite attachment
	 * @param topAttachment the top button attachment
	 */
	private void formatRemoveButton(Composite leftAttachment, Button topAttachment) {
		FormData data=new FormData();
		data.width=75;
		data.left=new FormAttachment(leftAttachment, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(topAttachment, ITabbedPropertyConstants.VSPACE);
		removeButton.setLayoutData(data);
	}

	/**
	 * Formats the add button layout
	 * @param leftAttachment the left composite attachment
	 * @param topAttachment the top composite attachment
	 */
	private void formatAddButton(Composite leftAttachment, Composite topAttachment) {
		FormData data=new FormData();
		data.width=75;
		data.left=new FormAttachment(leftAttachment, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(topAttachment, 0, SWT.TOP);
		addButton.setLayoutData(data);
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
