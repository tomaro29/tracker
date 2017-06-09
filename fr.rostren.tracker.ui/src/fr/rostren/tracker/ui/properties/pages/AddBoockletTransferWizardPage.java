/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.Transfer;

/**
 * Page to add a {@link Transfer} instance to an existing
 * {@link BoockletAccount} instance.
 */
public class AddBoockletTransferWizardPage extends AbstractOperationWizardPage {
	protected static final String[] TRANSFER_TYPES=new String[] {Incoming.class.getSimpleName(), Outgoing.class.getSimpleName()};

	private static final String PAGE_NAME="Add tranfer to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Transfer"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new transfer to the selected boocklet account."; //$NON-NLS-1$

	protected OperationTitle title;
	protected Origin origin;

	protected ComboViewer titlesComboViewer;
	protected ComboViewer originsComboViewer;

	private final ModifyListener modifyTransferTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			isIncoming=AddBoockletTransferWizardPage.TRANSFER_TYPES[0].equals(text);
			isOutgoing=AddBoockletTransferWizardPage.TRANSFER_TYPES[1].equals(text);
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddBoockletTransferWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddBoockletTransferWizardPage.PAGE_NAME, pageTitle), tracker, true, false, false, false);
		setTitle(AddBoockletTransferWizardPage.PAGE_TITLE);
		setDescription(AddBoockletTransferWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Transfer Type: ", AddBoockletTransferWizardPage.TRANSFER_TYPES, modifyTransferTypeListener); //$NON-NLS-1$
		super.createContainer(parent);
	}

	/**
	 * Returns the transfer title
	 * @return the transfer title
	 */
	public OperationTitle getTransferTitle() {
		return title;
	}

	/**
	 * Returns the transfer origin
	 * @return the transfer origin
	 */
	public Origin getTransferOrigin() {
		return origin;
	}
}