package fr.rostren.tracker.pdf.content.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.analyzer.CEPdfContentAnalyzer;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.TrackerPdfReader;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

/**
 * Extracts the content of a pdf file.
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
		if (StringUtils.isEmpty(uriText) || StringUtils.isBlank(uriText)) {
			throw new IllegalArgumentException("The uri cannot be null or empty or blank."); //$NON-NLS-1$
		}
		if (account == null) {
			throw new IllegalArgumentException("The account cannot be null."); //$NON-NLS-1$
		}
		this.uriText=uriText;
		this.account=account;
	}

	/**
	 * Extracts all operations from a pdf file
	 * @param monitor the progress monitor
	 *
	 * @return the list of all operations extracted from the pdf file.
	 * @throws ExtractorException if an {@link ExtractorException} is thrown
	 */
	public List<Operation> extractOperations(IProgressMonitor monitor) throws ExtractorException {
		if (monitor == null) {
			throw new IllegalArgumentException("The monitor cannot be null."); //$NON-NLS-1$
		}
		List<Operation> operations=new ArrayList<>();
		for (String uri: getURIsFromText()) {
			monitor.subTask(uri);
			if (StringUtils.isEmpty(uri)) {
				continue;
			}

			URI selectedFileURI=URI.createURI(uri);
			if (selectedFileURI.isPlatform()) {
				IPath resourcePath=new Path(selectedFileURI.toPlatformString(true));
				IFile iFile=ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);
				uri=iFile.getLocationURI().getPath();
				String fileName=iFile.getProjectRelativePath().toOSString();
				operations.addAll(extractOperations(uri, fileName, monitor));
			}
			else {
				uri=selectedFileURI.toFileString();
				operations.addAll(extractOperations(uri, uri, monitor));
			}
		}
		return operations;
	}

	/**
	 * Returns the list of Uris
	 * @return uris as a table
	 */
	private List<String> getURIsFromText() {
		if (uriText.contains(" ")) { //$NON-NLS-1$
			return Arrays.asList(uriText.split(" ")); //$NON-NLS-1$
		}
		return Arrays.asList(uriText);
	}

	/**
	 * Extracts text from a PDF document.
	 *
	 * @param src
	 *            the original PDF document path
	 * @param fileName
	 * 			  the file name
	 * @param monitor the progress monitor
	 * @return the list of all the extracted operation
	 * @throws ExtractorException if an {@link ExtractorException} is thrown
	 */
	private List<Operation> extractOperations(String src, String fileName, IProgressMonitor monitor) throws ExtractorException {
		List<Operation> operations=new ArrayList<>();

		try (TrackerPdfReader reader=new TrackerPdfReader(src)) {
			Tracker tracker=TrackerUtils.getTracker(account);
			CEPdfContentAnalyzer analyzer=new CEPdfContentAnalyzer();
			for (int i=0; i < reader.getNumberOfPages(); i++) {
				String originId=fileName + "_page_" + (i + 1);//$NON-NLS-1$
				if (!isAlreadyParsed(tracker, originId)) {
					Origin origin=createLinkedOrigin(tracker, originId);
					String page=PdfTextExtractor.getTextFromPage(reader, i + 1);

					String[] lines=page.split("\n"); //$NON-NLS-1$
					monitor.beginTask(src + " page " + i, lines.length); //$NON-NLS-1$
					for (int j=0; j < lines.length; j++) {
						String line=lines[j];
						monitor.subTask(" parsing line: " + line); //$NON-NLS-1$
						LineContent currentLineContent=analyzer.parseLine(line, origin);
						if (currentLineContent != null && account != null && account instanceof CheckingAccount) {
							currentLineContent.completeOperation(tracker);
							operations.add(currentLineContent.getOperation());
						}
						monitor.worked(1);
					}
				}
				else {
					alreadyParsedFiles.add(fileName);
				}
			}
			monitor.done();
		}
		catch (IOException exception) {
			alreadyParsedFiles.clear();
			throw new ExtractorException(Level.SEVERE, "Problem while extracting the pdf datas: Cannot get data from the imported pdf!", //$NON-NLS-1$
					exception);
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
	 *            the origin Identifier of the current page, this is never null
	 *
	 * @return true if the current pdf is already parsed, false otherwise.
	 */
	private boolean isAlreadyParsed(Tracker tracker, String originId) {
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null."); //$NON-NLS-1$
		}
		if (tracker.getOriginsRepository() == null) {
			tracker.setOriginsRepository(TrackerFactory.eINSTANCE.createOriginsRepository());
		}
		List<Origin> existingOrigins=tracker.getOriginsRepository().getOrigins();
		for (Origin existingOrigin: existingOrigins) {
			if (existingOrigin != null && originId.equals(existingOrigin.getIdentifier()) && OriginType.PDF_FILE.equals(existingOrigin.getType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return all parsed files.
	 *
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
