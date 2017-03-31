package fr.rostren.tracker.ui.properties.sections;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTreePropertySection extends AbstractTrackerPropertySection {
	public TreeViewer treeViewer;
	public Tree tree;
	protected Button addButton;
	protected Button editButton;
	protected Button removeButton;

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
		Tree tree=widgetFactory.createTree(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTreeLayout(control, tree, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		addButton=widgetFactory.createButton(composite, "Add", SWT.PUSH); //$NON-NLS-1$
		addButton.addSelectionListener(addButtonlistener);
		formatAddButton(tree, tree);

		removeButton=widgetFactory.createButton(composite, "Remove", SWT.PUSH); //$NON-NLS-1$
		removeButton.addSelectionListener(removeButtonListener);
		formatRemoveButton(tree, addButton);

		return tree;
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
		Tree tree=widgetFactory.createTree(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTreeLayout(control, tree, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		addButton=widgetFactory.createButton(composite, "Add", SWT.PUSH); //$NON-NLS-1$
		addButton.addSelectionListener(addButtonlistener);
		formatAddButton(tree, tree);

		editButton=widgetFactory.createButton(composite, "Edit", SWT.PUSH); //$NON-NLS-1$
		editButton.addSelectionListener(editButtonlistener);
		formatEditButton(tree, addButton);

		removeButton=widgetFactory.createButton(composite, "Remove", SWT.PUSH); //$NON-NLS-1$
		removeButton.addSelectionListener(removeButtonListener);
		formatRemoveButton(tree, editButton);

		return tree;
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
	 * Formats the edit button layout
	 * @param leftAttachment the left composite attachment
	 * @param topAttachment the top button attachment
	 */
	private void formatEditButton(Composite leftAttachment, Button topAttachment) {
		FormData data=new FormData();
		data.width=75;
		data.left=new FormAttachment(leftAttachment, ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(topAttachment, ITabbedPropertyConstants.VSPACE);
		editButton.setLayoutData(data);
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
}
