/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
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

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;

/**
 * Page to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategoryOperationTitleWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add operation title to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation title"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation title to the selected category."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected OperationTitle title;

	protected ComboViewer titlesComboViewer;

	private final SelectionAdapter addOperationTitleButtonlistener=new SelectionAdapter() {
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
	private final ISelectionChangedListener titleListener=new ISelectionChangedListener() {

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

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddCategoryOperationTitleWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddCategoryOperationTitleWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddCategoryOperationTitleWizardPage.PAGE_TITLE);
		setDescription(AddCategoryOperationTitleWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		Set<OperationTitle> operationsTitles=new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles());
		titlesComboViewer=createComboViewer(parent, "Operation Title: ", operationsTitles, //$NON-NLS-1$
				new OperationsTitlesRepositoryContentProvider(), new OperationTitleLabelProvider(), titleListener, addOperationTitleButtonlistener);
		if (!operationsTitles.isEmpty()) {
			title=operationsTitles.iterator().next();
		}
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return title;
	}
}