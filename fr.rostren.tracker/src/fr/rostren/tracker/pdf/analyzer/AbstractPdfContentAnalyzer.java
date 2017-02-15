package fr.rostren.tracker.pdf.analyzer;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.LineContent.OperationType;

/**
 * An abstract Class to parse banks pdf format.
 */
public abstract class AbstractPdfContentAnalyzer {

	public enum PdfToken {
		DATE,
		OPERATIONS_DEPOT,
		VIR_RECU,
		PAIE_CHEQUE,
		FRAIS_BANCAIRES,
		PAIEMENTS_CARTES,
		RETRAITS_CARTES,
		PRELEVEMENTS,
		OPERATIONS_DIVERSES
	}

	protected final Shell shell;
	protected final Pattern ONLY_NUMBERS=Pattern.compile("[0-9]*"); //$NON-NLS-1$
	protected final String STRING_SEPARATOR=" "; //$NON-NLS-1$
	protected final Pattern SPACE_STRING_PATTREN=Pattern.compile("(\\s)+"); //$NON-NLS-1$
	protected final Pattern EMPTY_STRING_PATTREN=Pattern.compile("(\\s)*"); //$NON-NLS-1$
	protected final Pattern DATE_SEPARATOR_PATTREN=Pattern.compile("/"); //$NON-NLS-1$
	protected final Pattern PART_1_DATE_PATTREN=Pattern.compile("(0[0-9]|1[0-9]|2[0-9]|3[0-1])"); //$NON-NLS-1$
	protected final Pattern PART_2_DATE_PATTREN=Pattern.compile("(0[1-9]|1[0-2]|[1-9])"); //$NON-NLS-1$
	protected final Pattern PART_3_DATE_PATTREN=Pattern.compile("([0-9]{4})"); //$NON-NLS-1$
	protected final Pattern OPERATION_TITLE_PATTREN=Pattern.compile("(.)*"); //$NON-NLS-1$
	protected final Pattern AMOUNT_NUMBER_PATTREN=Pattern.compile("([0-9](([\\s.])?[0-9]*)*,[0-9]{2})"); //$NON-NLS-1$
	protected final Pattern NUMBER_PATTREN=Pattern.compile("([0-9]*)"); //$NON-NLS-1$
	protected final Pattern FACT_PATTREN=Pattern.compile("FACT\\s+([0-9]*)"); //$NON-NLS-1$

	boolean isCredit;
	LocalDate lastPotentialDate=null;
	String lastPotentialOperationTitle=null;
	double lastPotentialAmount=0;

	private int currentYear=0;
	private String currentLine;
	/** The current split line. */
	private String[] currentSplitLine;
	/** The last parsed token. */
	private PdfToken lastToken;

	/**
	 *Constructor.
	 *@param shell the shell.
	 */
	public AbstractPdfContentAnalyzer(Shell shell) {
		this.shell=shell;
	}

	/**
	 * This parses a single line in the pdf.
	 *
	 * @param line
	 *            the current line.
	 * @param origin
	 *            the operation origin
	 * @return {@link LineContent}
	 */
	public abstract LineContent parseLine(String line, Origin origin);

	/**
	 * Extracts operations if exists from the current line.
	 *
	 * @param origin
	 *            the operation origin
	 * @return {@link LineContent}
	 */
	protected LineContent extractOperation(Origin origin) {
		extractDataFromCurrentLine();
		if (!isCompleted()) {
			return null;
		}
		LineContent currentLineContent=getLineContent(origin);
		reset();
		return currentLineContent;
	}

	/**
	 * REturns the {@link LineContent} instance
	 * @param origin the line origin
	 * @return the {@link LineContent} instance
	 */
	private LineContent getLineContent(Origin origin) {
		if (lastToken != null && PdfToken.VIR_RECU.equals(lastToken) || PdfToken.OPERATIONS_DEPOT.equals(lastToken)) {
			return new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
		}
		else if (lastToken != null && PdfToken.PAIE_CHEQUE.equals(lastToken)	|| PdfToken.PAIEMENTS_CARTES.equals(lastToken) || PdfToken.PRELEVEMENTS.equals(lastToken)
					|| PdfToken.RETRAITS_CARTES.equals(lastToken) || PdfToken.OPERATIONS_DIVERSES.equals(lastToken)) {
			return new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
		}
		else if (lastToken != null && PdfToken.FRAIS_BANCAIRES.equals(lastToken)) {
			if (lastPotentialOperationTitle.contains("REMISE") //$NON-NLS-1$
				|| lastPotentialOperationTitle.contains("INTERETS")) { //$NON-NLS-1$
				// REMISE(C), INTERETS(C)
				return new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
			}
			// COTISATION(D), FRAIS(D)
			return new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
		}
		else if (lastToken != null && PdfToken.DATE.equals(lastToken)) {//the operation type is not defined
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					isCredit=MessageDialog.openQuestion(shell, "Operation Type Definition", //$NON-NLS-1$
							"Is the next operation a Credit one ?\n" //$NON-NLS-1$
																								+ lastPotentialDate + STRING_SEPARATOR.toString() + lastPotentialOperationTitle
																							+ STRING_SEPARATOR.toString() + lastPotentialAmount);
				}
			});

			return new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, isCredit ? OperationType.CREDIT : OperationType.DEBIT, origin);
		}
		return null;
	}

	/**
	 * Tests if the operation characteristics are completely specified.
	 *
	 * @return "true" if the date, the operationTitle and the amount are set.
	 *         "false" otherwise.
	 */
	protected boolean isCompleted() {
		return lastPotentialDate != null && lastPotentialOperationTitle != null && lastPotentialAmount != 0;
	}

	/**
	 * Resets the date, the operationTitle and the amount.
	 */
	private void reset() {
		setLastPotentialDate(null);
		setLastPotentialOperationTitle(null);
		setLastPotentialAmount(0);
	}

	/**
	 * Tries to extract an operation characteristics from lines.
	 */
	protected abstract void extractDataFromCurrentLine();

	/**
	 * Specify the date from the current line.
	 *
	 * @return the Date from the current line
	 */
	protected abstract LocalDate extractDateFromCurrentLine();

	/**
	 * Extracts the year
	 * @return the extracted year
	 */
	protected int extractYearFromCurrentLine() {
		String year=currentLine.subSequence(25, 29).toString();
		return Integer.parseInt(year);
	}

	/**
	 * Returns the current line.
	 *
	 * @return the currentLine.
	 */

	public String getCurrentLine() {
		return currentLine;
	}

	/**
	 * Sets the line.
	 *
	 * @param line
	 *            the line to set.
	 */
	public void setCurrentLine(String line) {
		currentLine=line;
	}

	/**
	 * Sets the operation title.
	 *
	 * @param operationTitle
	 *            the operation Title to set as a last potential title.
	 */
	public void setLastPotentialOperationTitle(String operationTitle) {
		lastPotentialOperationTitle=operationTitle;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount to set as a last parsed amount.
	 */
	public void setLastPotentialAmount(double amount) {
		lastPotentialAmount=amount;
	}

	/**
	 * Returns the current year.
	 *
	 * @return the current year.
	 */
	public int getCurrentYear() {
		return currentYear;
	}

	/**
	 * Sets the current year.
	 *
	 * @param year
	 *            the year to set.
	 */
	public void setCurrentYear(int year) {
		currentYear=year;
	}

	/**
	 * Sets the last potential date.
	 *
	 * @param date
	 *            the date to set as a last potential date.
	 */
	public void setLastPotentialDate(LocalDate date) {
		lastPotentialDate=date;
	}

	/**
	 * Returns the current split line.
	 *
	 * @return the currentSplitLine
	 */
	public String[] getCurrentSplitLine() {
		return currentSplitLine;
	}

	/**
	 * Sets the split line.
	 *
	 * @param splitLine
	 *            the splitLine to set
	 */
	public void setCurrentSplitLine(String[] splitLine) {
		currentSplitLine=splitLine;
	}

	/**
	 * Returns the last token.
	 *
	 * @return the lastToken
	 */
	public PdfToken getLastToken() {
		return lastToken;
	}

	/**
	 * Sets the last parsed token.
	 *
	 * @param token
	 *            the token to set as a last parsed token
	 */
	public void setLastToken(PdfToken token) {
		lastToken=token;
	}
}
