package fr.rostren.tracker.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.pdf.content.extractor.ExtractorException;
import fr.rostren.tracker.pdf.content.extractor.PDFContentExtractor;
import fr.rostren.tracker.pdf.utils.OperationData;

public class ExtractOperationsAction implements IRunnableWithProgress {
	private final String pdfURIText;
	private final PDFContentExtractor extractor;

	private List<OperationData> addedOperations=new ArrayList<>();

	/**
	 * Constructor
	 * @param pdfURIText the pdf uri as a {@link String}
	 * @param account the checking account
	 */
	public ExtractOperationsAction(String pdfURIText, CheckingAccount account) {
		this.pdfURIText=pdfURIText;
		extractor=new PDFContentExtractor(pdfURIText, account);
	}

	@Override
	public void run(IProgressMonitor monitor) throws InterruptedException {
		boolean done=false;
		monitor.beginTask("Extract Operations", 1); //$NON-NLS-1$
		if (!monitor.isCanceled()) {
			done=extractOperations(extractor, monitor);
		}
		if (!monitor.isCanceled() && done && addedOperations.isEmpty()) {
			if (!extractor.getAlreadyParsedFiles().isEmpty()) {
				throw new InterruptedException("The PDF file : '" //$NON-NLS-1$
												+ pdfURIText + "' is already parsed."); //$NON-NLS-1$
			}
			throw new InterruptedException("The PDF is not valid, please make sure that the selection : '" //$NON-NLS-1$
											+ pdfURIText + "' has a correct format or contains at least one valid operation."); //$NON-NLS-1$
		}
		monitor.done();
	}

	/**
	 * Extracts the operations
	 * @param extractor the extractor
	 * @param monitor the progress monitor
	 * @return <code>true</code> if it is done, <code>false</code> otherwise.
	 * @throws InterruptedException if extraction action is interrupted for a known reason
	 */
	private boolean extractOperations(PDFContentExtractor extractor, IProgressMonitor monitor) throws InterruptedException {
		try {
			addedOperations=extractor.extractOperations(monitor);
			return true;
		}
		catch (ExtractorException e) {
			throw new InterruptedException("Problem while extracting operations: "	+ //$NON-NLS-1$
											e.getMessage());
		}
	}

	/**
	 * @return the extractor
	 */
	public PDFContentExtractor getExtractor() {
		return extractor;
	}

	/**
	 * Returns the added operations
	 * @return the added operations
	 */
	public List<OperationData> getAddedOperations() {
		return addedOperations;
	}
}
