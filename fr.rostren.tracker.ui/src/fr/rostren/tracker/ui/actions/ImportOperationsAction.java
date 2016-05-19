/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2016, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.actions;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;

public class ImportOperationsAction extends Action {
    private static final String FILES_ALREADY_IMPORTED_BEFORE = "The files:\n''{0}''\nare already imported before!"; //$NON-NLS-1$

    private final Shell shell;
    private final String pdfURIText;
    private final CheckingAccount account;

    private List<Operation> addedOperations = new ArrayList<Operation>();
    private Set<Origin> addedOrigins = new HashSet<Origin>();

    /**
     * @param shell
     * @param pdfURIText
     * @param account
     */
    public ImportOperationsAction(Shell shell, String pdfURIText, CheckingAccount account) {
	this.shell = shell;
	this.pdfURIText = pdfURIText;
	this.account = account;
    }

    @Override
    public void run() {
	// Reads the pdf file, Extracts data and Edits operations categories.
	ExtractOperationsAction extractAction = new ExtractOperationsAction(shell, pdfURIText, account);
	extractAction.run();

	EditOperationsAction editAction = new EditOperationsAction(shell, account, addedOperations, addedOrigins);
	editAction.run();

	if (extractAction.isDone() && editAction.isAborted()) {
	    // display information if the pdf file is already scanned
	    Set<String> alreadyParsedFiles = extractAction.getExtractor().getAlreadyParsedFiles();
	    if (!alreadyParsedFiles.isEmpty())
		displayInformation(alreadyParsedFiles);
	}
    }

    /**
     * @return the addedOperations
     */
    public List<Operation> getAddedOperations() {
	return addedOperations;
    }

    /**
     * @return the addedOrigins
     */
    public Set<Origin> getAddedOrigins() {
	return addedOrigins;
    }

    private void displayInformation(Set<String> alreadyParsedFiles) {
	StringBuilder sb = new StringBuilder();
	for (String parsedOrigin : alreadyParsedFiles) {
	    if (!sb.toString().isEmpty())
		sb.append("',\n'"); //$NON-NLS-1$
	    sb.append(parsedOrigin);
	}
	MessageDialog.openInformation(shell, "Unable to Import Files", //$NON-NLS-1$
		MessageFormat.format(FILES_ALREADY_IMPORTED_BEFORE, sb.toString()));
    }
}
