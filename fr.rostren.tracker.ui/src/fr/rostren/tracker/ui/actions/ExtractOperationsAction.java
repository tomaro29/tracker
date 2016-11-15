package fr.rostren.tracker.ui.actions;

import java.io.IOException;
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
import fr.rostren.tracker.pdf.content.extractor.ExtractorException;
import fr.rostren.tracker.pdf.content.extractor.PDFContentExtractor;

public class ExtractOperationsAction extends Action {
	private final Shell shell;
	private final String pdfURIText;
	private final PDFContentExtractor extractor;

	private List<Operation> addedOperations=new ArrayList<>();
	private final Set<Origin> addedOrigins=new HashSet<>();
	private boolean done=false;

	/**
	 * Constructor
	 * @param shell the parent shell
	 * @param pdfURIText the pdf uri as a {@link String}
	 * @param account the checking account
	 */
	public ExtractOperationsAction(Shell shell, String pdfURIText, CheckingAccount account) {
		this.shell=shell;
		this.pdfURIText=pdfURIText;
		extractor=new PDFContentExtractor(pdfURIText, account);
	}

	@Override
	public void run() {
		extractOperations(extractor);
		if (done && addedOperations.isEmpty()) {
			MessageDialog.openError(shell, "Cannot Import PDF", //$NON-NLS-1$
					"The PDF is not valid, please make sure that the selection : '" //$NON-NLS-1$
																+ pdfURIText + "' has a correct format or contains at least one valid operation."); //$NON-NLS-1$
			return;
		}
	}

	/**
	 * Extracts the operations
	 * @param extractor the extractor
	 */
	private void extractOperations(PDFContentExtractor extractor) {
		try {
			addedOperations=extractor.extractOperations();
			for (Operation operation: addedOperations) {
				addedOrigins.add(operation.getOrigin());
			}
			done=true;
		}
		catch (ExtractorException e) {
			MessageDialog.openError(shell, "Problem while extracting operations", //$NON-NLS-1$
					e.getMessage());
		}
		catch (IOException e) {
			MessageDialog.openError(shell, "Problem while opening the PDF File", //$NON-NLS-1$
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
	public List<Operation> getAddedOperations() {
		return addedOperations;
	}

	/**
	 * Returns the added origins
	 * @return the added origins
	 */
	public Set<Origin> getAddedOrigins() {
		return addedOrigins;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}
}
