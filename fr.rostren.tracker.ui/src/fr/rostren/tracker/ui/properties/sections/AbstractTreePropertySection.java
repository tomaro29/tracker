/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryCategoryWizard;

public abstract class AbstractTreePropertySection extends AbstractTrackerPropertySection {
	protected TreeViewer treeViewer;
	protected Tree tree;

	protected ITreeContentProvider contentProvider;
	protected ILabelProvider labelProvider;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		if (editButtonListener == null) {
			tree=createTree(body, null, addButtonListener, removeButtonListener);
		}
		else {
			tree=createTree(body, null, addButtonListener, editButtonListener, removeButtonListener);
		}
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

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForCategorySubCategories() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				ISelection selection=treeViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				if (category instanceof IncomeCategory) {
					DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, elementToRemove);
				}
				else if (category instanceof SpendingCategory) {
					DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, elementToRemove);
				}
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an add button
	 * @return the add button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createAddButtonForCategorySubCategories() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				String pageTitle=category.getTitle();

				AddCategoryCategoryWizard wizard=new AddCategoryCategoryWizard(pageTitle, category);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					if (category instanceof IncomeCategory) {
						Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();
						setAttributes(wizard, newCategory);

						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
					}
					else if (category instanceof SpendingCategory) {
						Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();
						setAttributes(wizard, newCategory);

						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
					}

					refresh();
				}
			}
		};
	}

	/**
	 * Sets the given category attributes basing on the given wizard data
	 * @param wizard the wizard
	 * @param category the category
	 */
	protected void setAttributes(AddCategoryCategoryWizard wizard, Category category) {
		String title=wizard.getCategoryTitle();
		if (!StringUtils.isEmpty(title)) {
			category.setTitle(title);
		}
		String description=wizard.getCategoryDescription();
		if (!StringUtils.isEmpty(description)) {
			category.setDescription(description);
		}
	}

	/**
	 * Creates and returns an add button
	 * @return the add button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createAddButtonForCategoriesRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Tracker tracker;
				if (currentEObject instanceof Tracker) {
					tracker=(Tracker)currentEObject;
				}
				else {
					Assert.isTrue(currentEObject instanceof CategoriesRepository);
					CategoriesRepository repository=(CategoriesRepository)currentEObject;
					tracker=(Tracker)repository.eContainer();
				}

				TreeItem[] selection=tree.getSelection();
				if (selection.length == 0) {
					addTrackerCategory(tracker);
				}
				else {
					addCategorySubCategory(tracker, (Category)selection[0].getData());
				}
				refresh();
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForCategoriesRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				CategoriesRepository repository;
				if (currentEObject instanceof Tracker) {
					Tracker tracker=(Tracker)currentEObject;
					repository=tracker.getCategoriesRepository();
				}
				else {
					Assert.isTrue(currentEObject instanceof CategoriesRepository);
					repository=(CategoriesRepository)currentEObject;
				}

				ISelection selection=treeViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Category elementToRemove=(Category)((StructuredSelection)selection).getFirstElement();
				if (elementToRemove.eContainer() instanceof IncomeCategory) {
					DomainUtils.executeRemoveCommand(repository.getIncome(), TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, elementToRemove);
				}
				else if (elementToRemove.eContainer() instanceof SpendingCategory) {
					DomainUtils.executeRemoveCommand(repository.getSpending(), TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, elementToRemove);
				}
				refresh();
			}
		};
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
	abstract protected List<EObject> getEObjects();
}
