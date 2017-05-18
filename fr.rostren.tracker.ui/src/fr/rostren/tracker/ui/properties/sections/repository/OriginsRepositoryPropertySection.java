/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections.repository;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OriginsRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;

public class OriginsRepositoryPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new OriginsRepositoryContentProvider();
	private final ILabelProvider labelProvider=new OriginLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof OriginsRepository);
			OriginsRepository repository=(OriginsRepository)currentEObject;
			Tracker tracker=(Tracker)repository.eContainer();

			AddTrackerOriginWizard wizard=new AddTrackerOriginWizard("Origins Repository", tracker); //$NON-NLS-1$
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Origin newOrigin=TrackerFactory.eINSTANCE.createOrigin();

				String identifier=wizard.getIdentifier();
				if (identifier != null) {
					newOrigin.setIdentifier(identifier);
				}
				OriginType type=wizard.getType();
				if (type != null) {
					newOrigin.setType(type);
					DomainUtils.executeAddCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, newOrigin);
					refresh();
				}
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			EObject currentEObject=getCurrentEObject();
			Assert.isTrue(currentEObject instanceof OriginsRepository);
			OriginsRepository repository=(OriginsRepository)currentEObject;

			ISelection selection=tableViewer.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			Object elementToRemove=((StructuredSelection)selection).getFirstElement();
			DomainUtils.executeRemoveCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, elementToRemove);
			refresh();
		}
	};

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		table=createTable(body, null, addButtonlistener, removeButtonListener);
		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	@Override
	public void refresh() {
		disposeListeners();
		tableViewer.setInput(getOrigins());
		addListeners();
	}

	/**
	 * Returns the origins list
	 * @return the origins list
	 */
	private List<Origin> getOrigins() {
		Assert.isTrue(currentEObject instanceof OriginsRepository);
		List<Origin> origins=((OriginsRepository)currentEObject).getOrigins();
		if (origins == null || origins.isEmpty()) {
			return Collections.emptyList();
		}
		return origins;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void disposeListeners() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonlistener, null, removeButtonListener);
	}
}
