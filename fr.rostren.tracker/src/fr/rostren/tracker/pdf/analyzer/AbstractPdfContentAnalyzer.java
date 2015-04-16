package fr.rostren.tracker.pdf.analyzer;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.LineContent.OperationType;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * An abstract Class to parse banks pdf format.
 * 
 * @author maro
 */
public abstract class AbstractPdfContentAnalyzer {
	/** The space string pattern. */
	static final Pattern SPACE_STRING_PATTREN = Pattern.compile("(\\s)+");
	/** The empty string pattern. */
	static final Pattern EMPTY_STRING_PATTREN = Pattern.compile("(\\s)*");
	/** The first part of a date pattern. */
	static final Pattern PART_1_DATE_PATTREN = Pattern
			.compile("(0[0-9]|1[0-9]|2[0-9]|3[0-1])");
	/** The second part of a date pattern. */
	static final Pattern PART_2_DATE_PATTREN = Pattern
			.compile("(0[1-9]|1[0-2])");
	/** The operation title pattern. */
	static final Pattern OPERATION_TITLE_PATTREN = Pattern.compile("(.)*");
	/** The amount number pattern. */
	static final Pattern AMOUNT_NUMBER_PATTREN = Pattern
			.compile("([0-9]((\\s)?[0-9]*)*,[0-9]{2})");
	/** The number pattern. */
	static final Pattern NUMBER_PATTREN = Pattern.compile("([0-9]*)");
	/** The fact string pattern. */
	static final Pattern FACT_PATTREN = Pattern.compile("FACT\\s+([0-9]*)");

	/** The thirteen number. */
	private static final int THIRTEEN = 13;
	/** The nine number. */
	private static final int NINE = 9;

	/** The pdf tokens. */
	protected enum PdfToken {
		/** The date token. */
		DATE,
		/** The 'operations depot' token. */
		OPERATIONS_DEPOT,
		/** The 'vir recu' token. */
		VIR_RECU,
		/** The 'paie cheque' token. */
		PAIE_CHEQUE,
		/** The 'frais bancaires' token. */
		FRAIS_BANCAIRES,
		/** The 'paiements cartes' token. */
		PAIEMENTS_CARTES,
		/** The 'retraits cartes' token. */
		RETRAITS_CARTES,
		/** The 'prelevements' token. */
		PRELEVEMENTS,
		/** The operations 'diverses' token. */
		OPERATIONS_DIVERSES
	}

	/** The current year as an integer. */
	private int currentYear;

	/** The complete current line content. */
	private String currentLine;
	/** The current split line. */
	private String[] currentSplitLine;
	/** The last parsed token. */
	private PdfToken lastToken;

	/** The last parsed potential date. */
	private Date lastPotentialDate;
	/** The last parsed potential operation title. */
	private String lastPotentialOperationTitle;
	/** The last parsed potential amount. */
	private BigDecimal lastPotentialAmount;

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
		LineContent currentLineContent = null;
		extractDataFromCurrentLine();
		if (isCompleted()) {
			if (lastToken != null && PdfToken.VIR_RECU.equals(lastToken)
					|| PdfToken.OPERATIONS_DEPOT.equals(lastToken)) {
				currentLineContent = new LineContent(lastPotentialDate,
						lastPotentialOperationTitle, lastPotentialAmount,
						OperationType.CREDIT, origin);
			} else if (lastToken != null
					&& PdfToken.PAIE_CHEQUE.equals(lastToken)
					|| PdfToken.PAIEMENTS_CARTES.equals(lastToken)
					|| PdfToken.PRELEVEMENTS.equals(lastToken)
					|| PdfToken.RETRAITS_CARTES.equals(lastToken)
					|| PdfToken.OPERATIONS_DIVERSES.equals(lastToken)) {
				currentLineContent = new LineContent(lastPotentialDate,
						lastPotentialOperationTitle, lastPotentialAmount,
						OperationType.DEBIT, origin);
			} else if (lastToken != null
					&& PdfToken.FRAIS_BANCAIRES.equals(lastToken)) {
				if (lastPotentialOperationTitle.contains("REMISE")
						|| lastPotentialOperationTitle.contains("INTERETS")) {
					// REMISE(C), INTERETS(C)
					currentLineContent = new LineContent(lastPotentialDate,
							lastPotentialOperationTitle, lastPotentialAmount,
							OperationType.CREDIT, origin);
				} else {
					// COTISATION(D), FRAIS(D)
					currentLineContent = new LineContent(lastPotentialDate,
							lastPotentialOperationTitle, lastPotentialAmount,
							OperationType.DEBIT, origin);
				}
			}
			reset();
		}
		return currentLineContent;
	}

	/**
	 * Tests if the operation characteristics are completely specified.
	 * 
	 * @return <code>true</code> if the date, the operationTitle and the amount
	 *         are set. <code>false</code> otherwise.
	 */
	protected final boolean isCompleted() {
		return lastPotentialDate != null && lastPotentialOperationTitle != null
				&& lastPotentialAmount != null;
	}

	/**
	 * Resets the date, the operationTitle and the amount.
	 */
	private void reset() {
		setLastPotentialDate(null);
		setLastPotentialOperationTitle(null);
		setLastPotentialAmount(null);
	}

	/**
	 * Tries to extract an operation characteristics from lines.
	 */
	protected abstract void extractDataFromCurrentLine();

	/**
	 * Specifies the date from the current line.
	 * 
	 * @return the Date from the current line
	 */
	protected abstract Date extractDateFromCurrentLine();

	/**
	 * Extracts the year from the current line.
	 * 
	 * @return the year parsed to integer value.
	 */
	protected final int extractYearFromCurrentLine() {
		String year = currentLine.subSequence(NINE, THIRTEEN).toString();
		return Integer.parseInt(year);
	}

	/**
	 * Sets the Month basing on the current content.
	 * 
	 * @param date
	 *            the date.
	 * @param content
	 *            the current content.
	 */
	protected void setMonthFromContent(Date date, String content) {
		switch (Integer.parseInt(content)) {
		case Month.JAN_VALUE:
			date.setMonth(Month.JAN);
			break;
		case Month.FEB_VALUE:
			date.setMonth(Month.FEB);
			break;
		case Month.MARS_VALUE:
			date.setMonth(Month.MARS);
			break;
		case Month.APR_VALUE:
			date.setMonth(Month.APR);
			break;
		case Month.MAY_VALUE:
			date.setMonth(Month.MAY);
			break;
		case Month.JUNE_VALUE:
			date.setMonth(Month.JUNE);
			break;
		case Month.JULY_VALUE:
			date.setMonth(Month.JULY);
			break;
		case Month.AUG_VALUE:
			date.setMonth(Month.AUG);
			break;
		case Month.SEPT_VALUE:
			date.setMonth(Month.SEPT);
			break;
		case Month.OCT_VALUE:
			date.setMonth(Month.OCT);
			break;
		case Month.NOV_VALUE:
			date.setMonth(Month.NOV);
			break;
		case Month.DEC_VALUE:
			date.setMonth(Month.DEC);
			break;
		default:
			break;
		}
	}

	/**
	 * Returns the current line.
	 * 
	 * @return the currentLine.
	 */
	public final String getCurrentLine() {
		return this.currentLine;
	}

	/**
	 * Sets the line.
	 * 
	 * @param line
	 *            the line to set.
	 */
	public final void setCurrentLine(final String line) {
		this.currentLine = line;
	}

	/**
	 * Sets the operation title.
	 * 
	 * @param operationTitle
	 *            the operation Title to set as a last potential title.
	 */
	public final void setLastPotentialOperationTitle(final String operationTitle) {
		this.lastPotentialOperationTitle = operationTitle;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount
	 *            the amount to set as a last parsed amount.
	 */
	public final void setLastPotentialAmount(final BigDecimal amount) {
		this.lastPotentialAmount = amount;
	}

	/**
	 * Returns the current year.
	 * 
	 * @return the current year.
	 */
	public final int getCurrentYear() {
		return this.currentYear;
	}

	/**
	 * Sets the current year.
	 * 
	 * @param year
	 *            the year to set.
	 */
	public final void setCurrentYear(final int year) {
		this.currentYear = year;
	}

	/**
	 * Sets the last potential date.
	 * 
	 * @param date
	 *            the date to set as a last potential date.
	 */
	public final void setLastPotentialDate(final Date date) {
		this.lastPotentialDate = date;
	}

	/**
	 * Returns the current split line.
	 * 
	 * @return the currentSplitLine
	 */
	public final String[] getCurrentSplitLine() {
		return this.currentSplitLine;
	}

	/**
	 * Sets the split line.
	 * 
	 * @param splitLine
	 *            the splitLine to set
	 */
	public final void setCurrentSplitLine(final String[] splitLine) {
		this.currentSplitLine = splitLine;
	}

	/**
	 * Returns the last token.
	 * 
	 * @return the lastToken
	 */
	public final PdfToken getLastToken() {
		return this.lastToken;
	}

	/**
	 * Sets the last parsed token.
	 * 
	 * @param token
	 *            the token to set as a last parsed token
	 */
	public final void setLastToken(final PdfToken token) {
		this.lastToken = token;
	}
}
