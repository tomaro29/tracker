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

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link OperationTitle} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOperationTitleWizardPage extends AbstractAddWizardPage {
	protected static final String[] ORIGIN_TYPES=new String[] {OriginType.MANUAL.getLiteral(), OriginType.PDF_FILE.getLiteral()};

	private static final String PAGE_NAME="Add operation title to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation title"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation title to the selected tracker."; //$NON-NLS-1$

	protected String title;

	private final ModifyListener modifyTitleListener=event -> {
		title=((Text)event.widget).getText();
		setPageComplete(isPageComplete());
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerOperationTitleWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerOperationTitleWizardPage.PAGE_NAME, pageTitle), tracker);
		setTitle(AddTrackerOperationTitleWizardPage.PAGE_TITLE);
		setDescription(AddTrackerOperationTitleWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Title: ", title, modifyTitleListener); //$NON-NLS-1$
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public String getOperationTitle() {
		return title;
	}

	@Override
	public boolean isPageComplete() {
		return isOperationTitlePageComplete(title);
	}
}