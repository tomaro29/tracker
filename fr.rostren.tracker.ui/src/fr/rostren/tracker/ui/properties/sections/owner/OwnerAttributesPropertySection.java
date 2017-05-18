/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.owner;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.ui.properties.listeners.OwnerAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OwnerAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text firstNameText;
	protected Text lastNameText;

	private final ModifyListener listener=new OwnerAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		firstNameText=createLabeledText(body, null, "First Name:"); //$NON-NLS-1$
		lastNameText=createLabeledText(body, firstNameText, "Last Name:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		String firstName=getOwnerFirstName();
		if (!StringUtils.isEmpty(firstName)) {
			firstNameText.setText(firstName);
		}
		String lastName=getOwnerLastName();
		if (!StringUtils.isEmpty(lastName)) {
			lastNameText.setText(lastName);
		}
		addListeners();
	}

	/**
	 * Returns the owner first name
	 * @return the owner first name
	 */
	private String getOwnerFirstName() {
		Assert.isTrue(currentEObject instanceof Owner);
		return ((Owner)currentEObject).getFirstName();
	}

	/**
	 * Returns the owner last name
	 * @return the owner last name
	 */
	private String getOwnerLastName() {
		Assert.isTrue(currentEObject instanceof Owner);
		return ((Owner)currentEObject).getLastName();
	}

	@Override
	protected void addListeners() {
		firstNameText.addModifyListener(listener);
		lastNameText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		firstNameText.removeModifyListener(listener);
		lastNameText.removeModifyListener(listener);
	}

	/**
	 * Returns the first name {@link Text}
	 * @return the first name {@link Text}
	 */
	public Text getFirstNameText() {
		return firstNameText;
	}

	/**
	 * Returns the last name {@link Text}
	 * @return the last name {@link Text}
	 */
	public Text getLastNameText() {
		return lastNameText;
	}
}
