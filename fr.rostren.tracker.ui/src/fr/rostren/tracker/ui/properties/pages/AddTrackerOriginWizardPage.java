/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Origin} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOriginWizardPage extends AbstractAddWizardPage {
	protected static final String[] ORIGIN_TYPES=new String[] {OriginType.MANUAL.getLiteral(), OriginType.PDF_FILE.getLiteral()};

	private static final String PAGE_NAME="Add origin to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Origin"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new Origin to the selected tracker."; //$NON-NLS-1$

	protected String identifier;
	protected OriginType type=OriginType.MANUAL;

	private final ModifyListener modifyIdentifierListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			identifier=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	private final ModifyListener modifyOriginTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			if (AddTrackerOriginWizardPage.ORIGIN_TYPES[0].equals(text)) {
				type=OriginType.MANUAL;
			}
			if (AddTrackerOriginWizardPage.ORIGIN_TYPES[1].equals(text)) {
				type=OriginType.PDF_FILE;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerOriginWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerOriginWizardPage.PAGE_NAME, pageTitle), tracker);
		setTitle(AddTrackerOriginWizardPage.PAGE_TITLE);
		setDescription(AddTrackerOriginWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Identifier: ", identifier, modifyIdentifierListener); //$NON-NLS-1$
		createCombo(parent, "Type: ", AddTrackerOriginWizardPage.ORIGIN_TYPES, modifyOriginTypeListener); //$NON-NLS-1$
	}

	/**
	 * Returns the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Returns the type
	 * @return the type
	 */
	public OriginType getType() {
		return type;
	}

	@Override
	public boolean isPageComplete() {
		return isOriginPageComplete(identifier);
	}
}