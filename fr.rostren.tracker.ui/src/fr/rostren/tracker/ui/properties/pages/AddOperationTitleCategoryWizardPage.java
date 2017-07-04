/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * Page to add a {@link Category} instance to an existing {@link OperationTitle}
 * instance.
 */
public class AddOperationTitleCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new category to the selected operation title."; //$NON-NLS-1$

	protected Category category;

	private final ISelectionChangedListener listener=event -> {
		ISelection selection=event.getSelection();
		Assert.isTrue(selection instanceof StructuredSelection);
		StructuredSelection ss=(StructuredSelection)selection;
		Object firstElement=ss.getFirstElement();
		if (firstElement != null && firstElement instanceof Category) {
			category=(Category)firstElement;
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddOperationTitleCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOperationTitleCategoryWizardPage.PAGE_NAME, pageTitle), tracker);
		setTitle(AddOperationTitleCategoryWizardPage.PAGE_TITLE);
		setDescription(AddOperationTitleCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		Set<Category> categories=new HashSet<>(TrackerUtils.getTrackerService(object).getCategories());
		categoriesTree=createTree(parent, "Category: ", addCategoryButtonlistener != null); //$NON-NLS-1$
		categoriesTreeViewer=createTreeViewer(categoriesTree, listener);
		categoriesTreeViewer.setInput(new ArrayList<>(categories));
		categoriesTreeViewer.expandAll();
		if (addCategoryButtonlistener != null) {
			getAddButton().addSelectionListener(addCategoryButtonlistener);
		}

		if (!categories.isEmpty()) {
			category=categories.iterator().next();
		}
	}

	/**
	 * Returns the category
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
}