package fr.rostren.tracker.pdf.content.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

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
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.analyzer.AbstractPdfContentAnalyzer;
import fr.rostren.tracker.pdf.analyzer.AnonymousPdfContentAnalyzer;
import fr.rostren.tracker.pdf.analyzer.CEPdfContentAnalyzer;
import fr.rostren.tracker.pdf.analyzer.CICPdfContentAnalyzer;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.OperationData;
import fr.rostren.tracker.pdf.utils.TrackerPdfReader;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

/**
 * Extracts the content of a pdf file.
 */
public class PDFContentExtractor {
	private static final String CIC_PDF_REGEX=".*CIC.*"; //$NON-NLS-1$
	private static final String CE_PDF_REGEX=".*CE.*"; //$NON-NLS-1$

	private Set<String> alreadyParsedFiles=new HashSet<>();

	private final String uriText;
	private final Account account;

	/**
	 * Constructor.
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
	public List<OperationData> extractOperations(IProgressMonitor monitor) throws ExtractorException {
		if (monitor == null) {
			throw new IllegalArgumentException("The monitor cannot be null."); //$NON-NLS-1$
		}
		List<URI> uris=getURIsFromText().stream()//
				.filter(uri -> !StringUtils.isEmpty(uri))//
				.map(uri -> URI.createURI(uri.endsWith(".pdf") ? uri : uri.concat(".pdf")))// //$NON-NLS-1$ //$NON-NLS-2$
				.collect(Collectors.toList());

		List<OperationData> operations=new ArrayList<>();
		for (URI selectedFileURI: uris) {
			if (selectedFileURI.isPlatform()) {
				IPath resourcePath=new Path(selectedFileURI.toPlatformString(true));
				IFile iFile=ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);
				String uri=iFile.getLocationURI().getPath();
				String fileName=iFile.getProjectRelativePath().toOSString();
				operations.addAll(extractOperations(uri, fileName, monitor));
			}
			else {
				String uri=selectedFileURI.toFileString();
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
		if (uriText.contains(".pdf ")) { //$NON-NLS-1$
			return Arrays.asList(uriText.split(".pdf ")); //$NON-NLS-1$
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
	private List<OperationData> extractOperations(String src, String fileName, IProgressMonitor monitor) throws ExtractorException {
		AbstractPdfContentAnalyzer analyzer=adaptAnalyzer(fileName);

		List<OperationData> operations=new ArrayList<>();
		try (TrackerPdfReader reader=new TrackerPdfReader(src)) {
			Tracker tracker=TrackerUtils.getTracker(account);
			int numberOfPages=reader.getNumberOfPages();
			for (int i=0; i < numberOfPages; i++) {
				String originId=fileName + "_page_" + (i + 1);//$NON-NLS-1$
				if (!isAlreadyParsed(tracker, originId)) {
					Origin origin=createLinkedOrigin(originId);
					String page=PdfTextExtractor.getTextFromPage(reader, i + 1);

					String[] lines=page.split("\n"); //$NON-NLS-1$
					monitor.beginTask(src + " page " + i, lines.length); //$NON-NLS-1$
					Arrays.asList(lines).stream()//
							.forEach(line -> {
								monitor.subTask(" parsing line: " + line); //$NON-NLS-1$
								LineContent currentLineContent=analyzer.parseLine(line, origin);
								if (currentLineContent != null && account instanceof CheckingAccount) {
									currentLineContent.completeOperation(tracker);
									operations.add(currentLineContent.getOperation());
								}
								monitor.worked(1);
							});
				}
			}
			alreadyParsedFiles.add(fileName);
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
	 * Adapts the pdf analyzer basing on the pdf file name.
	 * @param fileName the file name
	 * @return the adapted analyzer
	 */
	private AbstractPdfContentAnalyzer adaptAnalyzer(String fileName) {
		if (fileName.matches(PDFContentExtractor.CE_PDF_REGEX)) {
			return new CEPdfContentAnalyzer();
		}
		if (fileName.matches(PDFContentExtractor.CIC_PDF_REGEX)) {
			return new CICPdfContentAnalyzer();
		}
		return new AnonymousPdfContentAnalyzer();
	}

	/**
	 * Create an origin for operations.
	 *
	 * @param originIdentifier
	 *            the pdf origin identifier
	 * @return the created origin
	 */
	private Origin createLinkedOrigin(String originIdentifier) {
		Origin origin=TrackerFactory.eINSTANCE.createOrigin();
		origin.setIdentifier(originIdentifier);
		origin.setType(OriginType.PDF_FILE);
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
		return tracker.getOriginsRepository().getOrigins().stream()//
				.filter(existingOrigin -> existingOrigin != null && originId.equals(existingOrigin.getIdentifier()) && OriginType.PDF_FILE.equals(existingOrigin.getType()))
				.findAny().isPresent();
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
