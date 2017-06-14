/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.tracker;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOperationsTitlesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;

public class TrackerOperationsTitlesPropertySection extends AbstractTablePropertySection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new TrackerOperationsTitlesContentProvider();
		labelProvider=new OperationTitleLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;
				OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();

				AddTrackerOperationTitleWizard wizard=new AddTrackerOperationTitleWizard("Tracker", tracker); //$NON-NLS-1$
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					OperationTitle newOperationTitle=TrackerFactory.eINSTANCE.createOperationTitle();

					String operationTitle=wizard.getOperationTitle();
					if (operationTitle != null) {
						newOperationTitle.setTitle(operationTitle);
					}

					DomainUtils.executeAddCommand(repository, TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, newOperationTitle);
					refresh();
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;
				OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(repository, TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Returns the operations titles list
	 * @return the operations titles list
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Tracker);
		List<OperationTitle> titles=((Tracker)currentEObject).getOperationsTitlesRepositories().getOperationsTitles();
		if (titles == null || titles.isEmpty()) {
			return Collections.emptyList();
		}
		return titles;
	}
}