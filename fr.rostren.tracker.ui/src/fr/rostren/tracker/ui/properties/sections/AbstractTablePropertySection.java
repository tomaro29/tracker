/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTablePropertySection extends AbstractTrackerPropertySection {
	protected TableViewer tableViewer;
	protected Table table;

	protected ITreeContentProvider contentProvider;
	protected ILabelProvider labelProvider;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

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
		Table newTable=widgetFactory.createTable(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTableLayout(control, newTable, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTable, newTable, addButtonlistener);
		createRemoveButton(composite, widgetFactory, newTable, addButton, removeButtonListener);

		return newTable;
	}

	/**
	 * Creates a table
	 * @param composite the composite parent of the {@link Table} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param editButtonlistener the edit button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created table
	 */
	protected Table createTable(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter editButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Table newTable=widgetFactory.createTable(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTableLayout(control, newTable, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTable, newTable, addButtonlistener);
		createEditButton(composite, widgetFactory, newTable, addButton, editButtonlistener);
		createRemoveButton(composite, widgetFactory, newTable, editButton, removeButtonListener);

		return newTable;
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
	 * Disposes buttons listeners
	 * @param addButtonlistener the add button listener
	 * @param editButtonButtonListener the edit button listener
	 * @param removeButtonListener the remove button listener
	 */
	@Override
	protected void disposeButtonsListeners(SelectionAdapter addButtonlistener, SelectionAdapter editButtonButtonListener, SelectionAdapter removeButtonListener) {
		if (addButton != null && !addButton.isDisposed()) {
			addButton.removeSelectionListener(addButtonlistener);
		}
		if (editButton != null && !editButton.isDisposed()) {
			editButton.removeSelectionListener(editButtonButtonListener);
		}
		if (removeButton != null && !removeButton.isDisposed()) {
			removeButton.removeSelectionListener(removeButtonListener);
		}
	}

	@Override
	protected void refreshViewer() {
		tableViewer.setInput(getEObjects());

	}

	@Override
	protected void addListeners() {
		// Do Nothing
	}

	@Override
	protected void disposeListeners() {
		// Do Nothing
	}

	/**
	 * @return the list of objects
	 */
	abstract protected List<? extends EObject> getEObjects();
}
