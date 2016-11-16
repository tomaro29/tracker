package fr.rostren.tracker.pdf.content.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.analyzer.CaisseEpargnePdfContentAnalyzer;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

/**
 * Extracts the content of a pdf file.
 *
 * @author maro
 */
public class PDFContentExtractor {
	private Set<String> alreadyParsedFiles=new HashSet<>();

	private final String uriText;
	private final Account account;

	/**
	 * Constructor.
	 *
	 * @param uriText
	 *            the selected PDF document uris to import.
	 * @param account
	 *            the account where we extract data
	 */
	public PDFContentExtractor(String uriText, Account account) {
		this.uriText=uriText;
		this.account=account;
	}

	/**
	 * Extracts all operations from a pdf file
	 *
	 * @return the list of all operations extracted from the pdf file.
	 * @throws ExtractorException if an {@link ExtractorException} is thrown
	 * @throws IOException if an {@link IOException} is thrown
	 */
	public List<Operation> extractOperations() throws ExtractorException, IOException {
		if (account == null) {
			throw new ExtractorException("Cannot get the selected account from the Traker editor!"); //$NON-NLS-1$
		}

		List<Operation> operations=new ArrayList<>();
		for (String uri: getURISFromText()) {
			if (StringUtils.isEmpty(uri)) {
				continue;
			}

			URI selectedFileURI=URI.createURI(uri);
			if (selectedFileURI.isPlatform()) {
				IPath resourcePath=new Path(selectedFileURI.toPlatformString(true));
				IFile iFile=ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);
				uri=iFile.getLocationURI().getPath();
			}
			else {
				uri=selectedFileURI.toFileString();
			}

			operations.addAll(extractOperations(uri));
		}
		return operations;
	}

	/**
	 * Returns Uris
	 * @return uris as a table
	 */
	private String[] getURISFromText() {
		if (uriText != null && uriText.contains(" ")) { //$NON-NLS-1$
			return uriText.split(" "); //$NON-NLS-1$
		}
		return new String[] {uriText,};
	}

	/**
	 * Extracts text from a PDF document.
	 *
	 * @param src
	 *            the original PDF document path
	 * @return the list of all the extracted operation
	 * @throws ExtractorException if an {@link ExtractorException} is thrown
	 * @throws IOException if an {@link IOException} is thrown
	 */
	private List<Operation> extractOperations(String src) throws ExtractorException, IOException {
		List<Operation> operations=new ArrayList<>();

		PdfReader reader=new PdfReader(src);
		try {
			Tracker tracker=TrackerUtils.getTracker(account);
			CaisseEpargnePdfContentAnalyzer analyzer=new CaisseEpargnePdfContentAnalyzer();
			for (int i=0; i < reader.getNumberOfPages(); i++) {
				int index=src.lastIndexOf("/") + 1; //$NON-NLS-1$
				String fileName=src.substring(index, src.length());
				String originId=fileName	+ "_page_" //$NON-NLS-1$
								+ (i + 1);
				if (!isAlreadyParsed(tracker, originId)) {
					Origin origin=createLinkedOrigin(tracker, originId);
					String page=PdfTextExtractor.getTextFromPage(reader, i + 1);

					String[] lines=page.split("\n"); //$NON-NLS-1$
					for (int j=0; j < lines.length; j++) {
						String line=lines[j];
						LineContent currentLineContent=analyzer.parseLine(line, origin);
						if (currentLineContent != null && account != null && account instanceof CheckingAccount) {
							currentLineContent.completeOperation(tracker, currentLineContent.getOperation());
							operations.add(currentLineContent.getOperation());
						}
					}
				}
				else {
					alreadyParsedFiles.add(fileName);
				}
			}
		}
		catch (IOException exception) {
			throw new ExtractorException(Level.SEVERE, "Problem while extracting the pdf datas: Cannot get datas from the imported pdf!", //$NON-NLS-1$
					exception);
		}
		finally {
			reader.close();
		}
		return operations;
	}

	/**
	 * Create an origin for operations.
	 *
	 * @param tracker
	 *            the current tracker model root
	 * @param originIdentifier
	 *            the pdf origin identifier
	 * @return the created origin
	 */
	private Origin createLinkedOrigin(Tracker tracker, String originIdentifier) {
		Origin origin=TrackerFactory.eINSTANCE.createOrigin();
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
		List<Origin> existingOrigins=tracker.getOriginsRepository().getOrigins();
		for (Origin existingOrigin: existingOrigins) {
			if (existingOrigin != null	&& existingOrigin.getIdentifier() != null && existingOrigin.getIdentifier().equals(originId)
				&& existingOrigin.getType().equals(OriginType.PDF_FILE)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the alreadyParsedOrigins
	 */
	public Set<String> getAlreadyParsedFiles() {
		return alreadyParsedFiles;
	}

	/**
	 * @param alreadyParsedFiles
	 *            the alreadyParsedOrigins to set
	 */
	public void setAlreadyParsedFiles(Set<String> alreadyParsedFiles) {
		this.alreadyParsedFiles=alreadyParsedFiles;
	}
}
