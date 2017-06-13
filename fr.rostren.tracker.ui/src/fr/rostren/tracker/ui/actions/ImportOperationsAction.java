/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.model.utils.TrackerUtils;

public class ImportOperationsAction extends Action {

	private static final String REPOSITORY_DOES_NOT_EXIST="The ''{0}'' repository does not exist, can we create it automatically ?"; //$NON-NLS-1$
	private static final String REPOSITORY_MISSING="Some repositories are missing. User is invited to create all repositories in the tracker manually before asking for importing any file!"; //$NON-NLS-1$

	protected final Shell shell;
	private final String pdfURIText;
	private final CheckingAccount account;

	/**
	 * Constructor
	 * @param shell the parent shell
	 * @param pdfURIText the pdf uri as a {@link String}
	 * @param account the checking account
	 */
	public ImportOperationsAction(Shell shell, String pdfURIText, CheckingAccount account) {
		this.shell=shell;
		this.pdfURIText=pdfURIText;
		this.account=account;
	}

	@Override
	public void run() {
		validate();

		// Reads the pdf file and extracts data using a progress monitor
		ExtractOperationsAction extractAction=new ExtractOperationsAction(pdfURIText, account);
		runJobInDialog(extractAction);
		if (!extractAction.getAddedOperations().isEmpty()) {
			// Edits operations SubAmounts and categories
			EditOperationsAction editAction=new EditOperationsAction(shell, account, extractAction.getAddedOperations());
			editAction.run();
		}
	}

	/**
	 * Runs the given job in the progress monitor view
	 * @param job the job to run
	 */
	public void runJobInDialog(final IRunnableWithProgress job) {
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				IWorkbench workench=PlatformUI.getWorkbench();
				IWorkbenchWindow window=workench.getActiveWorkbenchWindow();
				Shell dialogShell=window != null ? window.getShell() : null;
				try {
					ProgressMonitorDialog dialog=new ProgressMonitorDialog(dialogShell);
					dialog.run(true, true, job);
				}
				catch (InterruptedException e) {
					MessageDialog.openInformation(dialogShell, "Unable to Import Files", e.getMessage());//$NON-NLS-1$
					Thread.currentThread().interrupt();
				}
				catch (InvocationTargetException e) {
					MessageDialog.openWarning(dialogShell, "Extraction interruption", "The extraction action has been interrupted for a technical reason."); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		};

		Display.getDefault().syncExec(runnable);
	}

	/**
	 * This validates that the account repositories are not null. Otherwise, we ask the user if automatical creation is authorized.
	 */
	private void validate() {
		Tracker tracker=TrackerUtils.getTracker(account);
		boolean missing=false;
		if (tracker.getCategoriesRepository() == null) {
			missing=true;
			if (askForAuthorization("categories")) { //$NON-NLS-1$
				CategoriesRepository repository=TrackerFactory.eINSTANCE.createCategoriesRepository();
				tracker.setCategoriesRepository(repository);
				missing=false;
			}
		}
		if (tracker.getOperationsTitlesRepositories() == null) {
			missing=true;
			if (askForAuthorization("operation titles")) { //$NON-NLS-1$
				OperationsTitleRepository repository=TrackerFactory.eINSTANCE.createOperationsTitleRepository();
				tracker.setOperationsTitlesRepositories(repository);
				missing=false;
			}
		}
		if (tracker.getOriginsRepository() == null) {
			missing=true;
			if (askForAuthorization("origins")) { //$NON-NLS-1$
				OriginsRepository repository=TrackerFactory.eINSTANCE.createOriginsRepository();
				tracker.setOriginsRepository(repository);
				missing=false;
			}
		}

		if (missing) {
			displayWarning();
		}
	}

	/**
	 * <code>true</code> if the user allows to create automatically, <code>false</code> otherwise.
	 * @param missingRepository the missing repository identifier
	 * @return <code>true</code> if the user allows to create automatically, <code>false</code> otherwise.
	 */
	private boolean askForAuthorization(String missingRepository) {
		return MessageDialog.openQuestion(shell, "Authorization", MessageFormat.format(ImportOperationsAction.REPOSITORY_DOES_NOT_EXIST, missingRepository)); //$NON-NLS-1$
	}

	/**
	 * Displays warning
	 */
	private void displayWarning() {
		MessageDialog.openWarning(shell, "Missing Repository", //$NON-NLS-1$
				ImportOperationsAction.REPOSITORY_MISSING);
	}
}
