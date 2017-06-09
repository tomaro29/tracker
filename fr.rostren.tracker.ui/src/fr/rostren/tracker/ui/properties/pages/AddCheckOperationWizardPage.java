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

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddCheckOperationWizardPage extends AbstractOperationWizardPage {
	protected static final String[] OPERATION_TYPES=new String[] {Credit.class.getSimpleName(), Debit.class.getSimpleName(), Incoming.class.getSimpleName(),
		Outgoing.class.getSimpleName()};

	private static final String PAGE_NAME="Add operation to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation to the selected checking account."; //$NON-NLS-1$

	private final ModifyListener modifyOperationTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			isCredit=AddCheckOperationWizardPage.OPERATION_TYPES[0].equals(text);
			isDebit=AddCheckOperationWizardPage.OPERATION_TYPES[1].equals(text);
			isIncoming=AddCheckOperationWizardPage.OPERATION_TYPES[2].equals(text);
			isOutgoing=AddCheckOperationWizardPage.OPERATION_TYPES[3].equals(text);
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddCheckOperationWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddCheckOperationWizardPage.PAGE_NAME, pageTitle), tracker, false, false, true, false);
		setTitle(AddCheckOperationWizardPage.PAGE_TITLE);
		setDescription(AddCheckOperationWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Operation Type: ", AddCheckOperationWizardPage.OPERATION_TYPES, modifyOperationTypeListener); //$NON-NLS-1$
		super.createContainer(parent);
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return title;
	}

	/**
	 * Returns the origin
	 * @return the origin
	 */
	public Origin getOperationOrigin() {
		return origin;
	}
}