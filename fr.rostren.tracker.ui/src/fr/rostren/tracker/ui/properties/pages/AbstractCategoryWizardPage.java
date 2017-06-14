/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class AbstractCategoryWizardPage extends AbstractAddWizardPage {

	protected String title;
	protected String description;
	protected final EObject object;

	private final ModifyListener modifyTitleListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			title=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	private final ModifyListener modifyDescriptionListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			description=((Text)event.widget).getText();
		}
	};

	/**
	 * @param pageName the page name
	 * @param title the page title
	 * @param description the page description
	 * @param object the object
	 */
	protected AbstractCategoryWizardPage(String pageName, String title, String description, EObject object) {
		super(pageName, object);
		setTitle(title);
		setDescription(description);
		this.object=object;
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Title: ", title, modifyTitleListener); //$NON-NLS-1$
		createText(parent, "Description: ", description, modifyDescriptionListener); //$NON-NLS-1$
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return title;
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return description;
	}

	@Override
	public boolean isPageComplete() {
		return isCategoryPageComplete(title);
	}
}
