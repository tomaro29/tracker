/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.account.BoockletTransfersPropertySection;

public class AccountTransfersModifyListener extends AbstractSelectionChangedListener {

	private final BoockletTransfersPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public AccountTransfersModifyListener(BoockletTransfersPropertySection section) {
		this.section=section;
	}

	@Override
	public void executeSelectionChanged(ISelection selection) {
		Assert.isTrue(selection instanceof Transfer);
		Transfer transfer=(Transfer)selection;

		EObject currentEObject=section.getCurrentEObject();
		Assert.isTrue(currentEObject instanceof BoockletAccount);
		BoockletAccount bookclet=(BoockletAccount)currentEObject;

		DomainUtils.executeAddCommand(bookclet, TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, transfer);
	}
}
