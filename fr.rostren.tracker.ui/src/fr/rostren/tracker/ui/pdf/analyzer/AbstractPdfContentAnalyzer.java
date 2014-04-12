package fr.rostren.tracker.ui.pdf.analyzer;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.ui.pdf.utils.LineContent;
import fr.rostren.tracker.ui.pdf.utils.LineContent.OperationType;

/**
 * An abstract Class to parse banks pdf format.
 * 
 * @author maro
 */
public abstract class AbstractPdfContentAnalyzer {

	protected enum PdfToken {
		DATE, VIR_RECU, PAIE_CHEQUE, FRAIS_BANCAIRES, PAIEMENTS_CARTES, RETRAITS_CARTES, PRELEVEMENTS, OPERATIONS_DIVERSES
	}

	protected final Pattern SPACE_STRING_PATTREN = Pattern.compile("(\\s)+");
	protected final Pattern EMPTY_STRING_PATTREN = Pattern.compile("(\\s)*");
	protected final Pattern PART_1_DATE_PATTREN = Pattern
			.compile("(0[0-9]|1[0-9]|2[0-9]|3[0-1])");
	protected final Pattern PART_2_DATE_PATTREN = Pattern
			.compile("(0[1-9]|1[0-2])");
	protected final Pattern OPERATION_TITLE_PATTREN = Pattern.compile("(.)*");
	protected final Pattern AMOUNT_NUMBER_PATTREN = Pattern
			.compile("([0-9]((\\s)?[0-9]*)*,[0-9]{2})");
	protected final Pattern NUMBER_PATTREN = Pattern.compile("([0-9]*)");
	protected final Pattern FACT_PATTREN = Pattern.compile("FACT\\s+([0-9]*)");

	protected int currentYear;
	protected String currentLine;
	protected String[] currentSplitLine;
	protected PdfToken lastToken;

	protected Date lastPotentialDate = null;
	protected String lastPotentialOperationTitle = null;
	protected BigDecimal lastPotentialAmount = null;

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
			if (lastToken != null && PdfToken.VIR_RECU.equals(lastToken)) {
				currentLineContent = new LineContent(lastPotentialDate,
						lastPotentialOperationTitle, lastPotentialAmount,
						OperationType.CREDIT, origin);
			} else if (lastToken != null
					&& PdfToken.PAIE_CHEQUE.equals(lastToken)
					|| PdfToken.PAIEMENTS_CARTES.equals(lastToken)
					|| PdfToken.PRELEVEMENTS.equals(lastToken)
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
	 * @return "true" if the date, the operationTitle and the amount are set.
	 *         "false" otherwise.
	 */
	protected boolean isCompleted() {
		return (lastPotentialDate != null
				&& lastPotentialOperationTitle != null && lastPotentialAmount != null);
	}

	/**
	 * Resets the date, the operationTitle and the amount.
	 */
	protected void reset() {
		lastPotentialDate = null;
		lastPotentialOperationTitle = null;
		lastPotentialAmount = null;
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

	protected int extractYearFromCurrentLine() {
		String year = currentLine.subSequence(9, 13).toString();
		return Integer.parseInt(year);
	}

	/**
	 * Sets the Month basing on the current content
	 * 
	 * @param date
	 * @param content
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
			break;
		}
	}
}
