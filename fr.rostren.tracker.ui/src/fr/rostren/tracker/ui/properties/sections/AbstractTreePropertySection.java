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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTreePropertySection extends AbstractTrackerPropertySection {
	protected TreeViewer treeViewer;
	protected Tree tree;

	protected ITreeContentProvider contentProvider;
	protected ILabelProvider labelProvider;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		treeViewer=new TreeViewer(tree);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	/**
	 * Creates a tree
	 * @param composite the composite parent of the {@link Tree} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created tree
	 */
	protected Tree createTree(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Tree newTree=widgetFactory.createTree(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTreeLayout(control, newTree, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTree, newTree, addButtonlistener);
		createRemoveButton(composite, widgetFactory, newTree, addButton, removeButtonListener);

		return newTree;
	}

	/**
	 * Creates a tree
	 * @param composite the composite parent of the {@link Tree} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param editButtonlistener the edit button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created tree
	 */
	protected Tree createTree(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter editButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Tree newTree=widgetFactory.createTree(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTreeLayout(control, newTree, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTree, newTree, addButtonlistener);
		createEditButton(composite, widgetFactory, newTree, addButton, editButtonlistener);
		createRemoveButton(composite, widgetFactory, newTree, editButton, removeButtonListener);

		return newTree;
	}

	/**
	 * Formats the tree layout
	 * @param control the text control, as a top attachment
	 * @param tree the tree to format
	 * @param font the table content font
	 */
	private void formatTreeLayout(Text control, Tree tree, Font font) {
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
		tree.setLayoutData(data);
		tree.setFont(font);
	}

	@Override
	protected void refreshViewer() {
		treeViewer.setInput(getEObjects());
		treeViewer.expandAll();
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
