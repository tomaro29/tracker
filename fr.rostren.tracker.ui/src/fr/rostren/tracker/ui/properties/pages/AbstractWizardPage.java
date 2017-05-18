/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;

public abstract class AbstractWizardPage extends WizardPage {

	/**
	 * Constructor
	 * @param pageName the page name
	 */
	protected AbstractWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
	}

	/**
	 * Returns the list of operations related to all checking accounts
	 * @param tracker the given tracker object
	 * @return the list of operations in checking accounts
	 */
	protected Set<Object> getOperations(Tracker tracker) {
		Set<Object> operations=new HashSet<>();
		for (Owner owner: tracker.getOwners()) {
			for (Account account: owner.getAccounts()) {
				if (account instanceof CheckingAccount) {
					operations.addAll(((CheckingAccount)account).getOperations());
				}
			}
		}
		return operations;
	}

	/**
	 * Creates text field
	 * @param composite the composite parent of the text to create
	 * @param label the text label
	 * @param content the content
	 * @param modifyListener the linked listener
	 * @return the created text field
	 */
	protected Text createText(Composite composite, String label, String content, ModifyListener modifyListener) {
		createLabel(composite, label);
		Text text=new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (!StringUtils.isEmpty(content) && !StringUtils.isBlank(content)) {
			text.setText(content);
		}

		text.addModifyListener(modifyListener);
		return text;
	}

	/**
	 * Creates check button
	 * @param composite the composite parent of the button to create
	 * @param label the text label
	 * @param selectionListener the linked selection listener
	 * @return the created check button
	 */
	protected Button createCheckButton(Composite composite, String label, SelectionListener selectionListener) {
		Button button=new Button(composite, SWT.RADIO);
		button.setText(label);
		button.addSelectionListener(selectionListener);
		return button;
	}

	/**
	 * Creates a date time zone
	 * @param composite the composite parent of the label to create
	 * @param label the label
	 * @param content the content
	 * @param selectionListener the selection listener
	 * @return the created {@link Text}
	 */
	protected DateTime createDateTime(Composite composite, String label, LocalDate content, SelectionListener selectionListener) {
		createLabel(composite, label);
		DateTime dateTime=new DateTime(composite, SWT.DATE | SWT.MEDIUM | SWT.DROP_DOWN);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (content != null) {
			dateTime.setDay(content.getDayOfMonth());
			dateTime.setMonth(content.getMonth().getValue() - 1);
			dateTime.setYear(content.getYear());
		}
		dateTime.addSelectionListener(selectionListener);
		return dateTime;
	}

	/**
	 * Creates combo
	 * @param composite the composite parent of the combo to create
	 * @param label the combo label
	 * @param items the combo content
	 * @param modifyListener the linked listener
	 * @return the created combo field
	 */
	protected Combo createCombo(Composite composite, String label, String[] items, ModifyListener modifyListener) {
		createLabel(composite, label);
		Combo combo=new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		combo.setItems(items);
		combo.select(0);

		combo.addModifyListener(modifyListener);
		return combo;
	}

	/**
	 * Creates combo viewer
	 * @param composite the composite parent of the combo to create
	 * @param label the combo label
	 * @param input the combo input
	 * @param contentProvider the content provider
	 * @param labelProvider the label Provider
	 * @param listener the linked listener
	 * @param addButtonlistener the "Add" button listener
	 * @return the created combo viewer
	 */
	protected ComboViewer createComboViewer(Composite composite, String label, Set<? extends Object> input, IContentProvider contentProvider, ILabelProvider labelProvider,
			ISelectionChangedListener listener, SelectionAdapter addButtonlistener) {
		createLabel(composite, label);

		Composite parent=new Composite(composite, SWT.NONE);
		parent.setLayout(new GridLayout(3, false));

		ComboViewer viewer=new ComboViewer(parent, SWT.READ_ONLY);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(new ArrayList<>(input));
		viewer.getCombo().select(0);

		viewer.addSelectionChangedListener(listener);

		if (addButtonlistener != null) {
			Button addButton=new Button(parent, SWT.PUSH);
			addButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 2, 0));
			addButton.setText("Add"); //$NON-NLS-1$
			addButton.addSelectionListener(addButtonlistener);
		}
		return viewer;
	}

	/**
	 * Creates tree
	 * @param composite the composite parent of the combo to create
	 * @param label the combo label
	 * @param addButtonlistener the "Add" button listener
	 * @return the created tree
	 */
	protected Tree createTree(Composite composite, String label, SelectionAdapter addButtonlistener) {
		createLabel(composite, label);
		Composite parent=new Composite(composite, SWT.NONE);
		parent.setLayout(new GridLayout(3, false));

		Tree tree=new Tree(parent, SWT.V_SCROLL | SWT.BORDER);
		tree.setFont(new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$
		if (addButtonlistener != null) {
			Button addButton=new Button(parent, SWT.PUSH);
			addButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 0));
			addButton.setText("Add"); //$NON-NLS-1$
			addButton.addSelectionListener(addButtonlistener);
		}

		GridData data=new GridData();
		data.verticalAlignment=GridData.FILL;
		data.grabExcessVerticalSpace=true;
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=GridData.FILL;
		parent.setLayoutData(data);
		tree.setLayoutData(data);
		return tree;
	}

	/**
	 * Creates tree viewer
	 * @param tree the tree parent of the treeviewer to create
	 * @param listener the linked listener
	 * @return the created tree viewer
	 */
	protected TreeViewer createTreeViewer(Tree tree, ISelectionChangedListener listener) {
		TreeViewer treeViewer=new TreeViewer(tree);
		treeViewer.addSelectionChangedListener(listener);
		treeViewer.setContentProvider(new CategoriesRepositoryContentProvider());
		treeViewer.setLabelProvider(new CategoryLabelProvider());
		return treeViewer;
	}

	/**
	 * Refreshes the Combo viewer content by setting the input, and selects the last element in the combo.
	 * @param comboViewer the combo to refresh
	 * @param set the input to set
	 * @param selection the new selection
	 */
	protected void refreshComboViewerContent(ComboViewer comboViewer, Set<? extends Object> set, Object selection) {
		comboViewer.setInput(new ArrayList<>(set));
		String[] items=comboViewer.getCombo().getItems();
		for (int i=0; i < items.length; i++) {
			if (items[i].equals(selection)) {
				comboViewer.getCombo().select(i);
				return;
			}
		}
	}

	/**
	 * Refreshes the tree viewer content by setting the input, and selects the last element in the tree.
	 * @param treeViewer the tree viewer to refresh
	 * @param set the input to set
	 * @param selection the new selection
	 */
	protected void refreshTreeViewerContent(TreeViewer treeViewer, Set<? extends Object> set, Object selection) {
		treeViewer.setInput(new ArrayList<>(set));
		treeViewer.refresh();
		treeViewer.expandAll();
		treeViewer.setSelection(new StructuredSelection(selection));
	}

	/**
	 * Creates a label
	 * @param composite the composite parent of the label to create
	 * @param label the label
	 */
	private void createLabel(Composite composite, String label) {
		if (label == null) {
			return;
		}
		Label textLabel=new Label(composite, SWT.NONE);
		textLabel.setText(label);
	}
}
