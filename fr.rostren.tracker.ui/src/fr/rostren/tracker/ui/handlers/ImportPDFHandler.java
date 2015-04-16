/**
 */
package fr.rostren.tracker.ui.handlers;

//CHECKSTYLE:OFF
import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.pdf.content.extractor.ExtractorException;
import fr.rostren.tracker.pdf.content.extractor.PDFContentExtractor;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

//CHECKSTYLE:ON

/**
 * The import pdf action handler.
 * 
 * @author maro
 *
 */
public class ImportPDFHandler extends AbstractHandler implements IHandler {
	/** The parent shell. */
	private Shell shell;

	@Override
	public Object execute(ExecutionEvent event) {
		Object applicationContext = event.getApplicationContext();
		Object currentShell = HandlerUtil.getVariable(applicationContext,
				ISources.ACTIVE_SHELL_NAME);
		if (!(currentShell instanceof Shell)) {
			return null;
		}
		shell = (Shell) currentShell;
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		TrackerEditorDev editor = (TrackerEditorDev) HandlerUtil
				.getActiveEditor(event);
		if (selection instanceof StructuredSelection) {
			for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
				Object selectedElement = AdapterFactoryEditingDomain
						.unwrap(objects.next());
				if (selectedElement instanceof Account) {

					// opens dialog to load a pdf
					LoadResourceDialog dialog = new LoadResourceDialog(
							getShell());
					int result = dialog.open();
					if (result == Window.OK) {
						readAndExtractData(dialog.getURIText(),
								(Account) selectedElement);
					}
				}
			}
			editor.doSave(new NullProgressMonitor());
		}

		return null;
	}

	/**
	 * Reads and extract data from the given pdf file.
	 * 
	 * @param uri
	 *            the pdf file uri as string.
	 * @param account
	 *            the selected account.
	 */
	private void readAndExtractData(String uri, Account account) {
		// read the pdf file and extract data
		PDFContentExtractor extractor = new PDFContentExtractor(uri, account);
		try {
			List<Operation> operations = extractor.extractOperations();
			// TODO open a dialog to confirm
			// informations and add to the model

			if (account instanceof CheckingAccount) {
				CheckingAccount checkingAccount = (CheckingAccount) account;
				for (Operation operation : operations) {
					checkingAccount.getOperations().add(operation);
				}
			}
			Set<String> alreadyParsedFiles = extractor.getAlreadyParsedFiles();
			if (!alreadyParsedFiles.isEmpty()) {
				displayInformation(alreadyParsedFiles);
			}
		} catch (ExtractorException e) {
			MessageDialog.openError(getShell(),
					"Problem while extracting operations", //$NON-NLS-1$
					e.getMessage());
		} catch (IOException e) {
			MessageDialog.openError(getShell(),
					"Problem while opening the PDF File", //$NON-NLS-1$
					e.getMessage());
		}
	}

	/**
	 * Displays information when the parsing is finish.
	 * 
	 * @param alreadyParsedFiles
	 *            the already parsed files.
	 */
	private void displayInformation(Set<String> alreadyParsedFiles) {
		StringBuilder sb = new StringBuilder();
		for (String parsedOrigin : alreadyParsedFiles) {
			if (!sb.toString().isEmpty()) {
				sb.append("',\n'"); //$NON-NLS-1$
			}
			sb.append(parsedOrigin);
		}
		MessageDialog.openInformation(shell, "Unable to Import Files", //$NON-NLS-1$
				"The files:\n'" //$NON-NLS-1$
						+ sb.toString() + "'\n" //$NON-NLS-1$
						+ "was already imported before!"); //$NON-NLS-1$

	}

	/**
	 * Returns the shell.
	 * 
	 * @return the shell.
	 */
	public Shell getShell() {
		return this.shell;
	}
}
