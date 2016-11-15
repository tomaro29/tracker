package fr.rostren.tracker.ui.actions;

import java.text.MessageFormat;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.CheckingAccount;

public class ImportOperationsAction extends Action {
	private static final String FILES_ALREADY_IMPORTED_BEFORE="The files:\n" //$NON-NLS-1$
																	+ "''{0}''\n" //$NON-NLS-1$
																+ "are already imported before!"; //$NON-NLS-1$

	private final Shell shell;
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
		// Reads the pdf file, Extracts data
		ExtractOperationsAction extractAction=new ExtractOperationsAction(shell, pdfURIText, account);
		extractAction.run();
		if (!extractAction.isDone()) {
			return;
		}
		// Edits operations SubAmounts and categories
		EditOperationsAction editAction=new EditOperationsAction(shell, account, extractAction.getAddedOperations(), extractAction.getAddedOrigins());
		editAction.run();
		if (!editAction.isAborted()) {
			return;
		}
		// display information if the pdf file is already scanned
		Set<String> alreadyParsedFiles=extractAction.getExtractor().getAlreadyParsedFiles();
		if (!alreadyParsedFiles.isEmpty()) {
			displayInformation(alreadyParsedFiles);
		}
	}

	/**
	 * Displays information
	 * @param alreadyParsedFiles the set of already parsed files uris
	 */
	private void displayInformation(Set<String> alreadyParsedFiles) {
		StringBuilder sb=new StringBuilder();
		for (String parsedOrigin: alreadyParsedFiles) {
			if (!sb.toString().isEmpty()) {
				sb.append("',\n'"); //$NON-NLS-1$
			}
			sb.append(parsedOrigin);
		}
		MessageDialog.openInformation(shell, "Unable to Import Files", //$NON-NLS-1$
				MessageFormat.format(ImportOperationsAction.FILES_ALREADY_IMPORTED_BEFORE, sb.toString()));
	}
}
