/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTrackerPropertySection extends AbstractPropertySection {
	protected Composite body;
	protected EObject currentEObject;

	protected Button addButton;
	protected Button editButton;
	protected Button removeButton;

	protected SelectionAdapter addButtonListener;
	protected SelectionAdapter editButtonListener;
	protected SelectionAdapter removeButtonListener;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		body=getWidgetFactory().createFlatFormComposite(parent);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input=((IStructuredSelection)selection).getFirstElement();
		Assert.isTrue(input instanceof EObject);
		currentEObject=(EObject)input;
	}

	/**
	 * Returns the shell
	 * @return the shell
	 */
	public Shell getShell() {
		Object currentShell=Display.getDefault().getActiveShell();
		if (!(currentShell instanceof Shell)) {
			return null;
		}
		return (Shell)currentShell;
	}

	/**
	 * Returns the current {@link EObject}
	 * @return the current {@link EObject}
	 */
	public EObject getCurrentEObject() {
		return currentEObject;
	}

	/**
	 * Returns the sorted list
	 * @param <T> any type
	 * @param set the set
	 * @param comparator the comparator
	 * @return the sorted list
	 */
	public <T> List<T> getSortedList(Set<T> set, Comparator<T> comparator) {
		List<T> list=new ArrayList<>(set);
		Collections.sort(list, comparator);
		list.removeAll(Collections.singleton(null));
		return list;
	}

	/**
	 * Adds listeners
	 */
	abstract protected void addListeners();

	/**
	 * Disposes listeners
	 */
	abstract protected void disposeListeners();

	/**
	 * @param composite the composite parent
	 * @param widgetFactory the widget factory to create the button
	 * @param leftAttachment the leftAttachement composite
	 * @param topAttachment the topAttachment composite
	 * @param listener the button listener
	 */
	protected void createAddButton(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, Composite leftAttachment, Composite topAttachment,
			SelectionListener listener) {
		addButton=widgetFactory.createButton(composite, "Add", SWT.PUSH); //$NON-NLS-1$
		addButton.addSelectionListener(listener);
		addButton.setLayoutData(AddButtonFormData(leftAttachment, topAttachment));
	}

	/**
	 * @param composite the composite parent
	 * @param widgetFactory the widget factory to create the button
	 * @param leftAttachment the leftAttachement composite
	 * @param topAttachment the topAttachment button
	 * @param listener the button listener
	 */
	protected void createEditButton(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, Composite leftAttachment, Button topAttachment,
			SelectionListener listener) {
		editButton=widgetFactory.createButton(composite, "Edit", SWT.PUSH); //$NON-NLS-1$
		editButton.addSelectionListener(listener);
		editButton.setLayoutData(buttonFormData(leftAttachment, topAttachment));
	}

	/**
	 * @param composite the composite parent
	 * @param widgetFactory the widget factory to create the button
	 * @param leftAttachment the leftAttachement composite
	 * @param topAttachment the topAttachment button
	 * @param listener the button listener
	 */
	protected void createRemoveButton(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, Composite leftAttachment, Button topAttachment,
			SelectionListener listener) {
		removeButton=widgetFactory.createButton(composite, "Remove", SWT.PUSH); //$NON-NLS-1$
		removeButton.addSelectionListener(listener);
		removeButton.setLayoutData(buttonFormData(leftAttachment, topAttachment));
	}

	/**
	* Disposes buttons listeners
	* @param addButtonlistener the add button listener
	* @param editButtonButtonListener the edit button listener
	* @param removeButtonListener the remove button listener
	*/
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
	public void dispose() {
		disposeButtonsListeners(addButtonListener, editButtonListener, removeButtonListener);
	}

	@Override
	public void refresh() {
		disposeListeners();
		refreshViewer();
		addListeners();
	}

	/**
	 * Refreshes the viewer.
	 */
	protected void refreshViewer() {
		//Do Nothing
	}

	/**
	 * Formats the remove button layout
	 * @param leftAttachment the left composite attachment
	 * @param topAttachment the top button attachment
	 * @return the form data
	 */
	private FormData buttonFormData(Composite leftAttachment, Button topAttachment) {
		FormData data=new FormData();
		data.width=75;
		data.left=new FormAttachment(leftAttachment, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(topAttachment, ITabbedPropertyConstants.VSPACE);
		return data;
	}

	/**
	 * Formats the add button layout
	 * @param leftAttachment the left composite attachment
	 * @param topAttachment the top composite attachment
	 * @return the form data
	 */
	private FormData AddButtonFormData(Composite leftAttachment, Composite topAttachment) {
		FormData data=new FormData();
		data.width=75;
		data.left=new FormAttachment(leftAttachment, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(topAttachment, 0, SWT.TOP);
		return data;
	}
}
