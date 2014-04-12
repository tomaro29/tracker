package fr.rostren.tracker.ui.pdf.utils;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.ui.pdf.analyzer.CaisseEpargnePdfContentAnalyzer;

/**
 * Extracts the content of a pdf file.
 * 
 * @author maro
 */
public class ExtractPDFContent {
	private String uriText;
	private Account account;
	private Shell shell;

	/**
	 * Constructor.
	 * 
	 * @param uriText
	 *            the selected PDF document uris to import.
	 * @param account
	 *            the account where we extract data
	 */
	public ExtractPDFContent(String uriText, Account account, Shell shell) {
		this.uriText = uriText;
		this.account = account;
		this.shell = shell;
	}

	/**
	 * Executes the extraction action
	 */
	public void execute() {
		if (account == null) {
			MessageDialog.openError(shell, "Cannot get selected account",
					"Cannot get selected account from the Traker editor!");
		}

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
	private void extractOperations(String src) throws IOException {
		PdfReader reader = new PdfReader(src);
		Tracker tracker = getTracker(account);
		for (int i = 0; i < reader.getNumberOfPages(); i++) {
			int index = src.lastIndexOf("/") + 1;
			String originId = src.substring(index, src.length()) + "_page_"
					+ (i + 1);
			if (!isAlreadyParsed(tracker, originId)) {
				Origin origin = createLinkedOrigin(tracker, originId);
				String page = PdfTextExtractor.getTextFromPage(reader, i + 1);

				String[] lines = page.split("\n");
				CaisseEpargnePdfContentAnalyzer analyzer = new CaisseEpargnePdfContentAnalyzer();

				for (int j = 0; j < lines.length; j++) {

					LineContent currentLineContent = analyzer.parseLine(
							lines[j], origin);
					if (currentLineContent != null && account != null) {
						if (account instanceof CheckingAccount) {
							CheckingAccount checking = (CheckingAccount) account;
							currentLineContent
									.completeOperation(tracker,
											currentLineContent.getOperation(),
											checking);
							checking.getOperations().add(
									currentLineContent.getOperation());
						}
					}
				}
			}
		}
		reader.close();
	}

	/**
	 * Create an origin for operations.
	 * 
	 * @param tracker
	 *            the current tracker model root
	 * @param src
	 *            the pdf source uri
	 * @return the created origin
	 */
	private Origin createLinkedOrigin(Tracker tracker, String originIdentifier) {
		Origin origin = TrackerFactory.eINSTANCE.createOrigin();
		origin.setIdentifier(originIdentifier);
		origin.setType(OriginType.PDF_FILE);
		tracker.getOriginsRepository().getOrigins().add(origin);
		return origin;
	}

	/**
	 * true if the current pdf is already parsed, false otherwise.
	 * 
	 * @param tracker
	 *            the current tracker root
	 * @param originId
	 *            the origin Identifier of the current page
	 * 
	 * @return true if the current pdf is already parsed, false otherwise.
	 */
	private boolean isAlreadyParsed(Tracker tracker, String originId) {
		List<Origin> existingOrigins = tracker.getOriginsRepository()
				.getOrigins();
		for (Origin existingOrigin : existingOrigins) {
			if (existingOrigin.getIdentifier().equals(originId)
					&& existingOrigin.getType().equals(OriginType.PDF_FILE)) {
				return true;
			}
		}
		return false;
	}

	private Tracker getTracker(Account account) {
		Owner owner = account.eContainer() instanceof Owner ? (Owner) account
				.eContainer() : null;
		return owner != null && owner.eContainer() instanceof Tracker ? (Tracker) owner
				.eContainer() : null;
	}
}
