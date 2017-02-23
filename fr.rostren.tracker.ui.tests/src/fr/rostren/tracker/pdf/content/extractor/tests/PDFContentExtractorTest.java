package fr.rostren.tracker.pdf.content.extractor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.pdf.content.extractor.ExtractorException;
import fr.rostren.tracker.pdf.content.extractor.PDFContentExtractor;
import fr.rostren.tracker.tests.TestUtils;

public class PDFContentExtractorTest {
	private static final String TEST_MODEL_PATH="input/models/testModel.tracker"; //$NON-NLS-1$

	private final CheckingAccount account=TrackerFactory.eINSTANCE.createCheckingAccount();
	private final Owner owner=TrackerFactory.eINSTANCE.createOwner();
	private final Tracker tracker=TrackerFactory.eINSTANCE.createTracker();

	private final String emptyURI=""; //$NON-NLS-1$
	private final String blankURI=" "; //$NON-NLS-1$
	private final String invalidURI="uri"; //$NON-NLS-1$
	private final String validURI="input/CE - TEST.pdf"; //$NON-NLS-1$
	private final String notPDFURI="input/EMPTY_FILE.txt"; //$NON-NLS-1$

	private final Set<String> alreadyParsedFiles=new HashSet<>();

	/**
	 * Tests Constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullURITest() {
		new PDFContentExtractor(null, account);
	}

	/**
	 * Tests Constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_EmptyURITest() {
		new PDFContentExtractor(emptyURI, account);
	}

	/**
	 * Tests Constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_BlankURITest() {
		new PDFContentExtractor(blankURI, account);
	}

	/**
	 * Tests Constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullAccountTest() {
		new PDFContentExtractor(invalidURI, null);
	}

	/**
	 * Tests Constructor
	 */
	@Test
	public void extractorTest() {
		PDFContentExtractor extractor=new PDFContentExtractor(invalidURI, account);
		assertNotNull(extractor);
		assertNotNull(extractor.getAlreadyParsedFiles());
		assertTrue(extractor.getAlreadyParsedFiles().isEmpty());

		alreadyParsedFiles.add("one"); //$NON-NLS-1$
		extractor.setAlreadyParsedFiles(alreadyParsedFiles);
		assertFalse(extractor.getAlreadyParsedFiles().isEmpty());
		assertEquals(1, extractor.getAlreadyParsedFiles().size());
	}

	/**
	 * Tests Constructor
	 * @throws IOException IOException
	 * @throws ExtractorException ExtractorException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void extractOperations_NullMonitorTest() throws ExtractorException, IOException {
		PDFContentExtractor extractor=new PDFContentExtractor(invalidURI, account);
		extractor.extractOperations(null);
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * @throws ExtractorException ExtractorException
	 */
	@Test(expected=ExtractorException.class)
	public void extractOperations_InvalidURITest() throws ExtractorException {
		PDFContentExtractor extractor=new PDFContentExtractor(invalidURI, account);
		extractor.extractOperations(new NullProgressMonitor());
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * @throws IOException IOException
	 * @throws ExtractorException ExtractorException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void extractOperations_NoTrackerContainerTest() throws ExtractorException, IOException {
		PDFContentExtractor extractor=new PDFContentExtractor(validURI, account);
		extractor.extractOperations(new NullProgressMonitor());
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * @throws ExtractorException ExtractorException
	 */
	@Test
	public void extractOperationsTest() throws ExtractorException {
		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);

		PDFContentExtractor extractor=new PDFContentExtractor(validURI, account);
		List<OperationData> operations=extractor.extractOperations(new NullProgressMonitor());
		assertNotNull(operations);
		assertFalse(operations.isEmpty());
		assertEquals(44, operations.size());
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * using a model in which the pdf is already parsed.
	 * @throws IOException IOException
	 * @throws ExtractorException ExtractorException
	 */
	@Test
	public void extractOperations_AlreadyExtractedTest() throws ExtractorException, IOException {
		Tracker existingTracker=(Tracker)TestUtils.load(PDFContentExtractorTest.TEST_MODEL_PATH);
		PDFContentExtractor extractor=new PDFContentExtractor(validURI, existingTracker.getOwners().get(0).getAccounts().get(0));

		List<OperationData> operations=extractor.extractOperations(new NullProgressMonitor());
		assertNotNull(operations);
		assertTrue(operations.isEmpty());

		Set<String> alreadyParsed=extractor.getAlreadyParsedFiles();
		assertNotNull(alreadyParsed);
		assertFalse(alreadyParsed.isEmpty());
		assertEquals(1, alreadyParsed.size());
		assertEquals("input\\CE - TEST.pdf", alreadyParsed.iterator().next().toString()); //$NON-NLS-1$
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * non pdf file as an input.
	 * @throws IOException IOException
	 * @throws ExtractorException ExtractorException
	 */
	@Test(expected=ExtractorException.class)
	public void extractOperations_NotPDFTest() throws ExtractorException, IOException {
		Tracker existingTracker=(Tracker)TestUtils.load(PDFContentExtractorTest.TEST_MODEL_PATH);
		PDFContentExtractor extractor=new PDFContentExtractor(notPDFURI, existingTracker.getOwners().get(0).getAccounts().get(0));

		extractor.extractOperations(new NullProgressMonitor());
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * non pdf file as an input.
	 * @throws IOException IOException
	 * @throws ExtractorException ExtractorException
	 */
	@Test(expected=ExtractorException.class)
	public void extractOperations_NotPDFMultipleURIsTest() throws ExtractorException, IOException {
		Tracker existingTracker=(Tracker)TestUtils.load(PDFContentExtractorTest.TEST_MODEL_PATH);
		PDFContentExtractor extractor=new PDFContentExtractor(notPDFURI + " " + validURI, existingTracker.getOwners().get(0).getAccounts().get(0)); //$NON-NLS-1$

		extractor.extractOperations(new NullProgressMonitor());
	}

	/**
	 * Tests {@link PDFContentExtractor#extractOperations(org.eclipse.core.runtime.IProgressMonitor)}
	 * non pdf file as an input.
	 * @throws IOException IOException
	 */
	@Test
	public void extractOperations_MultipleURIsTest() throws IOException {
		Tracker existingTracker=(Tracker)TestUtils.load(PDFContentExtractorTest.TEST_MODEL_PATH);
		PDFContentExtractor extractor=new PDFContentExtractor(notPDFURI + " " + validURI, existingTracker.getOwners().get(0).getAccounts().get(0)); //$NON-NLS-1$
		try {
			extractor.extractOperations(new NullProgressMonitor());
		}
		catch (ExtractorException e) {
			// expected
		}

		assertTrue(extractor.getAlreadyParsedFiles().isEmpty());

		extractor=new PDFContentExtractor(validURI + " " + notPDFURI, existingTracker.getOwners().get(0).getAccounts().get(0)); //$NON-NLS-1$
		try {
			extractor.extractOperations(new NullProgressMonitor());
		}
		catch (ExtractorException e) {
			// expected
		}

		assertTrue(extractor.getAlreadyParsedFiles().isEmpty());

		extractor=new PDFContentExtractor(" " + validURI + " " + " " + notPDFURI, existingTracker.getOwners().get(0).getAccounts().get(0)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		try {
			extractor.extractOperations(new NullProgressMonitor());
		}
		catch (ExtractorException e) {
			// expected
		}

		assertTrue(extractor.getAlreadyParsedFiles().isEmpty());
	}
}
