package fr.rostren.tracker.ui.pdf.utils;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;
import fr.rostren.tracker.ui.pdf.analyzer.CaisseEpargnePdfContentAnalyzer;

/**
 * @author mrostren
 */
public class ExtractPDFContent {
	private String uriText;
	private Tracker tracker;
	private Shell shell;
	private TrackerEditorDev editor;

	private CheckingAccount checkingAccount = null;

	public ExtractPDFContent(String uriText, Tracker tracker, Shell shell,
			TrackerEditorDev editor) {
		this.uriText = uriText;
		this.tracker = tracker;
		this.shell = shell;
		this.editor = editor;
	}

	public void execute() {
		if (tracker == null) {
			MessageDialog.openError(shell, "Cannot get current Model",
					"Cannot get current Model from the Traker editor!");
		}

		this.checkingAccount = initializeCheckingAccount(tracker);

		String[] uris = new String[] {};
		if (uriText.contains(" ")) {
			uris = uriText.split(" ");
		} else {
			uris = new String[] { uriText, };
		}

		for (String uri : uris) {
			if (!"".equals(uri)) {
				URI selectedFileURI = URI.createURI(uri);
				if (selectedFileURI.isPlatform()) {
					IPath resourcePath = new Path(
							selectedFileURI.toPlatformString(true));
					IFile iFile = ResourcesPlugin.getWorkspace().getRoot()
							.getFile(resourcePath);
					uri = iFile.getLocationURI().getPath();
				} else {
					uri = selectedFileURI.toFileString();
				}

				try {
					extractOperations(uri);
				} catch (IOException e) {
					MessageDialog.openError(shell,
							"Problem while extracting the pdf datas",
							"Cannot get datas from the imported pdf!");
				}
			}
		}
	}

	/**
	 * Extracts text from a PDF document.
	 * 
	 * @param src
	 *            the original PDF document
	 * @throws IOException
	 */
	public void extractOperations(String src) throws IOException {
		PdfReader reader = new PdfReader(src);
		for (int i = 0; i < reader.getNumberOfPages(); i++) {
			String page = PdfTextExtractor.getTextFromPage(reader, i + 1);

			String[] lines = page.split("\n");
			CaisseEpargnePdfContentAnalyzer analyzer = new CaisseEpargnePdfContentAnalyzer();

			for (int j = 0; j < lines.length; j++) {
				LineContent currentLineContent = analyzer.parseLine(lines[j]);
				if (currentLineContent != null && checkingAccount != null) {
					currentLineContent.completeOperation(tracker);
					checkingAccount.getOperations().add(
							currentLineContent.getOperation());
					editor.doSave(new NullProgressMonitor());
				}
			}
		}
		reader.close();
	}

	private CheckingAccount initializeCheckingAccount(Tracker tracker) {
		CheckingAccount checkingAccount = null;
		// TODO specify the concerned owner
		for (Account account : tracker.getOwners().get(0).getAccounts()) {
			if (account instanceof CheckingAccount) {
				checkingAccount = (CheckingAccount) account;
				break;
			}
		}
		return checkingAccount;
	}
}
