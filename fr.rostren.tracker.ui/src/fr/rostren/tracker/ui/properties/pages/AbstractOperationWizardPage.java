/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.content.providers.OriginsRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;

public class AbstractOperationWizardPage extends AbstractAddWizardPage {

	protected boolean isIncoming;
	protected boolean isOutgoing;
	protected boolean isCredit;
	protected boolean isDebit;

	protected OperationTitle title;
	protected Origin origin;

	protected final Tracker tracker;

	protected ComboViewer titlesComboViewer;
	protected ComboViewer originsComboViewer;

	protected final SelectionAdapter addOperationTitleButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddTrackerOperationTitleWizard wizard=new AddTrackerOperationTitleWizard("Operations Titles Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				OperationTitle newOperationTitle=TrackerFactory.eINSTANCE.createOperationTitle();

				String newTitle=wizard.getOperationTitle();
				if (newTitle != null) {
					newOperationTitle.setTitle(newTitle);
				}

				DomainUtils.executeAddCommand(tracker.getOperationsTitlesRepositories(), TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, newOperationTitle);
				refreshComboViewerContent(titlesComboViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles()), newOperationTitle);
			}
		}
	};

	protected final SelectionAdapter addOriginButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddTrackerOriginWizard wizard=new AddTrackerOriginWizard("Origins Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Origin newOrigin=TrackerFactory.eINSTANCE.createOrigin();

				String identifier=wizard.getIdentifier();
				if (identifier != null) {
					newOrigin.setIdentifier(identifier);
				}

				DomainUtils.executeAddCommand(tracker.getOriginsRepository(), TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, newOrigin);
				refreshComboViewerContent(originsComboViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getOrigins()), newOrigin);
			}
		}
	};

	protected final ISelectionChangedListener titleListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof OperationTitle) {
				title=(OperationTitle)firstElement;
			}
		}
	};

	protected final ISelectionChangedListener originListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {

			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Origin) {
				origin=(Origin)firstElement;
			}
		}
	};

	/**
	 * @param pageName the page name
	 * @param tracker the tracker
	 * @param isIncoming <code>true</code> if is incoming
	 * @param isOutgoing <code>true</code> if outgoing
	 * @param isCredit <code>true</code> if credit
	 * @param isDebit <code>true</code> if debit
	 */
	protected AbstractOperationWizardPage(String pageName, Tracker tracker, boolean isIncoming, boolean isOutgoing, boolean isCredit, boolean isDebit) {
		super(pageName);
		this.tracker=tracker;
		this.isIncoming=isIncoming;
		this.isOutgoing=isOutgoing;
		this.isCredit=isCredit;
		this.isDebit=isDebit;
	}

	@Override
	protected void createContainer(Composite parent) {
		Set<OperationTitle> operationsTitles=new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles());
		titlesComboViewer=createComboViewer(parent, "Title: ", operationsTitles, new OperationsTitlesRepositoryContentProvider(), //$NON-NLS-1$
				new OperationTitleLabelProvider(), titleListener, addOperationTitleButtonlistener);
		if (!operationsTitles.isEmpty()) {
			title=operationsTitles.iterator().next();
		}

		Set<Origin> origins=new HashSet<>(TrackerUtils.getTrackerService(tracker).getOrigins());
		originsComboViewer=createComboViewer(parent, "Origin: ", origins, new OriginsRepositoryContentProvider(), //$NON-NLS-1$
				new OriginLabelProvider(), originListener, addOriginButtonlistener);
		if (!origins.isEmpty()) {
			origin=origins.iterator().next();
		}
	}

	/**
	 * Returns <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 */
	public boolean isIncoming() {
		return isIncoming;
	}

	/**
	 * Returns <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 */
	public boolean isOutgoing() {
		return isOutgoing;
	}

	/**
	 * Returns <code>true</code> if is {@link Credit}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Credit}, <code>false</code> otherwise.
	 */
	public boolean isCredit() {
		return isCredit;
	}

	/**
	 * Returns <code>true</code> if is {@link Debit}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Debit}, <code>false</code> otherwise.
	 */
	public boolean isDebit() {
		return isDebit;
	}
}
