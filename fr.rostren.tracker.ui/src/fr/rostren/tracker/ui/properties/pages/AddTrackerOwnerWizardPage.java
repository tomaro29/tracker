/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Owner} instance to an existing {@link Tracker} instance.
 */
public class AddTrackerOwnerWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add owner to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Owner"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new owner to the selected tracker."; //$NON-NLS-1$

	protected String firstName;
	protected String lastName;

	private final ModifyListener modifyFirstNameListener=event -> {
		firstName=((Text)event.widget).getText();
		setPageComplete(isPageComplete());
	};

	private final ModifyListener modifyLastNameListener=event -> {
		lastName=((Text)event.widget).getText();
		setPageComplete(isPageComplete());
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerOwnerWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerOwnerWizardPage.PAGE_NAME, pageTitle), tracker);
		setTitle(AddTrackerOwnerWizardPage.PAGE_TITLE);
		setDescription(AddTrackerOwnerWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "First Name: ", firstName, modifyFirstNameListener); //$NON-NLS-1$
		createText(parent, "Last Name: ", lastName, modifyLastNameListener); //$NON-NLS-1$
	}

	/**
	 * Returns the first name
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the last name
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isPageComplete() {
		return isOwnerPageComplete(firstName, lastName);
	}
}