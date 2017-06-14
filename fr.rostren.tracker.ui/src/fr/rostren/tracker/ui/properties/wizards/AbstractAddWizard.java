/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.wizards;

import org.eclipse.jface.wizard.Wizard;

import fr.rostren.tracker.ui.properties.pages.AbstractAddWizardPage;

public class AbstractAddWizard extends Wizard {

	protected AbstractAddWizardPage page;
	protected String title;

	@Override
	public String getWindowTitle() {
		return title;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(page);
	}

}
