/**
 */
package fr.rostren.tracker.ui.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.content.extractor.ExtractorException;
import fr.rostren.tracker.pdf.content.extractor.PDFContentExtractor;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;
import fr.rostren.tracker.ui.dialogs.CheckAndEditOperationWizard;

public class ImportPDFHandler extends AbstractHandler implements IHandler {
	private static final String PDF_URI_TEXT_FIELD_KEY = "PDF.URI.text.field.key.settings."; //$NON-NLS-1$
	private Shell shell;
	private String pdfURIText;
	private CheckingAccount account;
	List<Operation> addedOperations = new ArrayList<Operation>();
	Set<Origin> addedOrigins = new HashSet<Origin>();

	@Override
	public Object execute(ExecutionEvent event) {
		Object applicationContext = event.getApplicationContext();
		Object currentShell = HandlerUtil.getVariable(applicationContext, ISources.ACTIVE_SHELL_NAME);
		if (!(currentShell instanceof Shell)) {
			return null;
		}
		setShell((Shell) currentShell);
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		if (selection instanceof StructuredSelection) {
			IPreferenceStore preferenceStore = new PreferenceStore();
			for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
				Object selectedElement = AdapterFactoryEditingDomain.unwrap(objects.next());
				if (selectedElement instanceof CheckingAccount) {
					this.account = (CheckingAccount) selectedElement;
					// opens dialog to load a pdf
					// TODO implement the LoadResourceDialog to set the PDF URI
					// Text field content.
					LoadResourceDialog dialog = new LoadResourceDialog(getShell());
					int result = dialog.open();

					if (result == Window.OK) {
						this.pdfURIText = dialog.getURIText();
						if (pdfURIText.isEmpty()) {
							MessageDialog.openError(shell, "Empty PDF URI.", //$NON-NLS-1$
									"The pdf uri cannot be empty."); //$NON-NLS-1$

							return null;
						}
						importOperationsToModel();
					}
				}
			}
		}
		TrackerEditorDev editor = (TrackerEditorDev) HandlerUtil.getActiveEditor(event);
		editor.doSave(new NullProgressMonitor());
		resetHandler();
		return null;
	}

	private void resetHandler() {
		account = null;
		pdfURIText = null;
		addedOperations.clear();
		addedOrigins.clear();
	}

	private void importOperationsToModel() {
		// read the pdf file, extract data and edit operations
		// categories.
		PDFContentExtractor extractor = new PDFContentExtractor(pdfURIText, account);

		boolean extracted = extractOperations(extractor);
		if (addedOperations.isEmpty()) {
			MessageDialog.openError(shell, "Cannot Import PDF", //$NON-NLS-1$
					"The PDF is not valid, please make sure that the selection : '" //$NON-NLS-1$
							+ pdfURIText + "' has a correct format or contains at least one valid operation."); //$NON-NLS-1$
			return;
		}

		boolean editAborted = editOperations();
		if (editAborted) {
			TrackerUtils.getTracker(account).getOriginsRepository().getOrigins().removeAll(addedOrigins);
			account.getOperations().removeAll(addedOperations);
			MessageDialog.openInformation(shell, "Abort PDF Import Action", //$NON-NLS-1$
					"The Current PDF Import Action is aborted! All new origins and operations will be cleaned from the model."); //$NON-NLS-1$
			return;
		}

		if (extracted && !editAborted) {
			// display information if the pdf file is already
			// scanned
			Set<String> alreadyParsedFiles = extractor.getAlreadyParsedFiles();
			if (!alreadyParsedFiles.isEmpty()) {
				displayInformation(alreadyParsedFiles);
			}
		}
	}

	/**
	 * @return <code> true</code> if the extraction is done well,
	 *         <code>false</code> otherwise.
	 */
	private boolean extractOperations(PDFContentExtractor extractor) {
		boolean done = false;
		try {
			addedOperations = extractor.extractOperations();
			for (Operation operation : addedOperations) {
				addedOrigins.add(operation.getOrigin());
			}
			done = true;
		} catch (ExtractorException e) {
			MessageDialog.openError(getShell(), "Problem while extracting operations", //$NON-NLS-1$
					e.getMessage());
		} catch (IOException e) {
			MessageDialog.openError(getShell(), "Problem while opening the PDF File", //$NON-NLS-1$
					e.getMessage());
		}
		return done;
	}

	/**
	 * @return <code> true</code> if the edition is aborted, <code>false</code>
	 *         otherwise.
	 */
	private boolean editOperations() {
		CheckAndEditOperationWizard wizard = new CheckAndEditOperationWizard(addedOperations, account);
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		if (wizardDialog.open() == Window.CANCEL) {
			// Abort all changes
			return true;
		}
		return false;
	}

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

	public Shell getShell() {
		return this.shell;
	}

	private void setShell(Shell shell) {
		this.shell = shell;
	}
}
