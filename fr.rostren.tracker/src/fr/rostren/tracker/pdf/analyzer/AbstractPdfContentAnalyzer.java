package fr.rostren.tracker.pdf.analyzer;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.LineContent.OperationType;

/**
 * An abstract Class to parse banks pdf format.
 *
 * @author maro
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

	protected final Pattern SPACE_STRING_PATTREN=Pattern.compile("(\\s)+"); //$NON-NLS-1$
	protected final Pattern EMPTY_STRING_PATTREN=Pattern.compile("(\\s)*"); //$NON-NLS-1$
	protected final Pattern PART_1_DATE_PATTREN=Pattern.compile("(0[0-9]|1[0-9]|2[0-9]|3[0-1])"); //$NON-NLS-1$
	protected final Pattern PART_2_DATE_PATTREN=Pattern.compile("([1-9]|0[1-9]|1[0-2])"); //$NON-NLS-1$
	protected final Pattern OPERATION_TITLE_PATTREN=Pattern.compile("(.)*"); //$NON-NLS-1$
	protected final Pattern AMOUNT_NUMBER_PATTREN=Pattern.compile("([0-9]((\\s)?[0-9]*)*,[0-9]{2})"); //$NON-NLS-1$
	protected final Pattern NUMBER_PATTREN=Pattern.compile("([0-9]*)"); //$NON-NLS-1$
	protected final Pattern FACT_PATTREN=Pattern.compile("FACT\\s+([0-9]*)"); //$NON-NLS-1$

	private int currentYear=0;
	private String currentLine;
	private String[] currentSplitLine;
	private PdfToken lastToken;

	private Date lastPotentialDate=null;
	private String lastPotentialOperationTitle=null;
	private BigDecimal lastPotentialAmount=null;

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
		LineContent currentLineContent=null;
		extractDataFromCurrentLine();
		if (isCompleted()) {
			if (lastToken != null && PdfToken.VIR_RECU.equals(lastToken) || PdfToken.OPERATIONS_DEPOT.equals(lastToken)) {
				currentLineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
			}
			else if (lastToken != null && PdfToken.PAIE_CHEQUE.equals(lastToken)	|| PdfToken.PAIEMENTS_CARTES.equals(lastToken) || PdfToken.PRELEVEMENTS.equals(lastToken)
						|| PdfToken.RETRAITS_CARTES.equals(lastToken) || PdfToken.OPERATIONS_DIVERSES.equals(lastToken)) {
				currentLineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
			}
			else if (lastToken != null && PdfToken.FRAIS_BANCAIRES.equals(lastToken)) {
				if (lastPotentialOperationTitle.contains("REMISE") //$NON-NLS-1$
					|| lastPotentialOperationTitle.contains("INTERETS")) { //$NON-NLS-1$
					// REMISE(C), INTERETS(C)
					currentLineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
				}
				else {
					// COTISATION(D), FRAIS(D)
					currentLineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
				}
			}
			reset();
		}
		return currentLineContent;
	}

	/**
	 * Tests if the operation characteristics are completely specified.
	 *
	 * @return "true" if the date, the operationTitle and the amount are set.
	 *         "false" otherwise.
	 */
	protected boolean isCompleted() {
		return lastPotentialDate != null && lastPotentialOperationTitle != null && lastPotentialAmount != null;
	}

	/**
	 * Resets the date, the operationTitle and the amount.
	 */
	protected void reset() {
		setLastPotentialDate(null);
		setLastPotentialOperationTitle(null);
		setLastPotentialAmount(null);
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
	protected abstract Date extractDateFromCurrentLine();

	/**
	 * Extracts the year
	 * @return the extracted year
	 */
	protected int extractYearFromCurrentLine() {
		String year=currentLine.subSequence(9, 13).toString();
		return Integer.parseInt(year);
	}

	/**
	 * Sets the Month basing on the current content
	 *
	 * @param date the date
	 * @param content the content
	 */
	protected void setMonthFromContent(Date date, String content) {
		switch (Integer.parseInt(content)) {
			case 1:
				date.setMonth(Month.JAN);
				break;
			case 2:
				date.setMonth(Month.FEB);
				break;
			case 3:
				date.setMonth(Month.MARS);
				break;
			case 4:
				date.setMonth(Month.APR);
				break;
			case 5:
				date.setMonth(Month.MAY);
				break;
			case 6:
				date.setMonth(Month.JUNE);
				break;
			case 7:
				date.setMonth(Month.JULY);
				break;
			case 8:
				date.setMonth(Month.AUG);
				break;
			case 9:
				date.setMonth(Month.SEPT);
				break;
			case 10:
				date.setMonth(Month.OCT);
				break;
			case 11:
				date.setMonth(Month.NOV);
				break;
			case 12:
				date.setMonth(Month.DEC);
				break;
			default:
				throw new IllegalArgumentException("Invalid date, the month must be [1-12]"); //$NON-NLS-1$
		}
	}

	/**
	 * @return the currentLine
	 */
	public String getCurrentLine() {
		return currentLine;
	}

	/**
	 * @param currentLine
	 *            the currentLine to set
	 */
	public void setCurrentLine(String currentLine) {
		this.currentLine=currentLine;
	}

	/**
	 * @param lastPotentialOperationTitle
	 *            the lastPotentialOperationTitle to set
	 */
	public void setLastPotentialOperationTitle(String lastPotentialOperationTitle) {
		this.lastPotentialOperationTitle=lastPotentialOperationTitle;
	}

	/**
	 * @param lastPotentialAmount
	 *            the lastPotentialAmount to set
	 */
	public void setLastPotentialAmount(BigDecimal lastPotentialAmount) {
		this.lastPotentialAmount=lastPotentialAmount;
	}

	/**
	 * @return the currentYear
	 */
	public int getCurrentYear() {
		return currentYear;
	}

	/**
	 * @param currentYear
	 *            the currentYear to set
	 */
	public void setCurrentYear(int currentYear) {
		this.currentYear=currentYear;
	}

	/**
	 * @param lastPotentialDate
	 *            the lastPotentialDate to set
	 */
	public void setLastPotentialDate(Date lastPotentialDate) {
		this.lastPotentialDate=lastPotentialDate;
	}

	/**
	 * @return the currentSplitLine
	 */
	public String[] getCurrentSplitLine() {
		return currentSplitLine;
	}

	/**
	 * @param currentSplitLine
	 *            the currentSplitLine to set
	 */
	public void setCurrentSplitLine(String[] currentSplitLine) {
		this.currentSplitLine=currentSplitLine;
	}

	/**
	 * @return the lastToken
	 */
	public PdfToken getLastToken() {
		return lastToken;
	}

	/**
	 * @param lastToken
	 *            the lastToken to set
	 */
	public void setLastToken(PdfToken lastToken) {
		this.lastToken=lastToken;
	}
}
