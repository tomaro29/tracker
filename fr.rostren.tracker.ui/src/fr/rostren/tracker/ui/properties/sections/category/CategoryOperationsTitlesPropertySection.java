/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.category;

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

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoryOperationsTitlesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryOperationTitleWizard;

public class CategoryOperationsTitlesPropertySection extends AbstractTablePropertySection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		contentProvider=new CategoryOperationsTitlesContentProvider();
		labelProvider=new OperationTitleLabelProvider();
		addButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				String pageTitle=category.getTitle();
				Tracker tracker=TrackerUtils.getTracker(category);

				AddCategoryOperationTitleWizard wizard=new AddCategoryOperationTitleWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					OperationTitle title=wizard.getOperationTitle();
					if (title != null) {
						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, title);
						refresh();
					}
				}
			}
		};
		removeButtonListener=new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, elementToRemove);
				refresh();
			}
		};

		table=createTable(body, null, addButtonListener, removeButtonListener);
		super.createControls(parent, aTabbedPropertySheetPage);
	}

	/**
	 * Retursn the list of operations titles
	 * @return the list of operations titles
	 */
	@Override
	protected List<? extends EObject> getEObjects() {
		Assert.isTrue(currentEObject instanceof Category);
		List<OperationTitle> operationsTitles=((Category)currentEObject).getOperationTitles();
		if (operationsTitles == null || operationsTitles.isEmpty()) {
			return Collections.emptyList();
		}
		return operationsTitles;
	}
}
