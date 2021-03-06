/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.pdf.analyzer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.model.utils.LineContent;
import fr.rostren.tracker.model.utils.OperationType;

/**
 * An abstract Class to parse pdf format.
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
	protected final Pattern NUMBER_PATTREN=Pattern.compile("([1-9][0-9]*)"); //$NON-NLS-1$
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
	 * This parses a single line in the pdf.
	 *
	 * @param line
	 *            the current line.
	 * @param origin
	 *            the operation origin
	 * @return {@link LineContent}
	 */
	public LineContent parseLine(String line, Origin origin) {
		if (StringUtils.isEmpty(line) || StringUtils.isBlank(line)) {
			return null;
		}
		if (origin == null) {
			throw new IllegalArgumentException("The origin of the line to parse cannot be null"); //$NON-NLS-1$
		}
		setCurrentLine(line);
		setCurrentSplitLine(getCurrentLine().split(SPACE_STRING_PATTREN.pattern()));
		Matcher numbersMatcher=ONLY_NUMBERS.matcher(line);
		if (numbersMatcher.matches()) {
			return null;
		}

		return parseValidLine(line, origin);
	}

	/**
	 * Parses a valid line
	 * @param line line is not empty, not blank and not a number
	 * @param origin not null
	 * @return the {@link LineContent} instance
	 */
	abstract LineContent parseValidLine(String line, Origin origin);

	/**
	 * Extracts operations if exists from the current line.
	 *
	 * @param origin
	 *            the operation origin
	 * @param stringSeparator the string separator to use
	 * @return {@link LineContent}
	 */
	protected LineContent extractOperation(Origin origin, String stringSeparator) {
		extractDataFromCurrentLine(stringSeparator);
		if (!isCompleted()) {
			return null;
		}
		return getLineContent(origin);
	}

	/**
	 * REturns the {@link LineContent} instance
	 * @param origin the line origin
	 * @return the {@link LineContent} instance
	 */
	private LineContent getLineContent(Origin origin) {
		LineContent lineContent=null;
		if (lastToken != null && PdfToken.VIR_RECU.equals(lastToken) || PdfToken.OPERATIONS_DEPOT.equals(lastToken)) {
			lineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
		}
		else if (lastToken != null && PdfToken.PAIE_CHEQUE.equals(lastToken)	|| PdfToken.PAIEMENTS_CARTES.equals(lastToken) || PdfToken.PRELEVEMENTS.equals(lastToken)
					|| PdfToken.RETRAITS_CARTES.equals(lastToken) || PdfToken.OPERATIONS_DIVERSES.equals(lastToken)) {
			lineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
		}
		else if (lastToken != null && PdfToken.FRAIS_BANCAIRES.equals(lastToken)) {
			if (lastPotentialOperationTitle.contains("REMISE") //$NON-NLS-1$
				|| lastPotentialOperationTitle.contains("INTERETS")) { //$NON-NLS-1$
				// REMISE(C), INTERETS(C)
				lineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.CREDIT, origin);
			}
			else {
				// COTISATION(D), FRAIS(D)
				lineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.DEBIT, origin);
			}
		}
		else if (lastToken != null && PdfToken.DATE.equals(lastToken)) {
			//in this case the operation type is not defined
			lineContent=new LineContent(lastPotentialDate, lastPotentialOperationTitle, lastPotentialAmount, OperationType.UNDEFINED, origin);
		}
		reset();
		return lineContent;
	}

	/**
	 * Tests if the operation characteristics are completely specified.
	 *
	 * @return "true" if the date, the operationTitle and the amount are set.
	 *         "false" otherwise.
	 */
	protected boolean isCompleted() {
		return lastPotentialDate != null && lastPotentialOperationTitle != null && BigDecimal.ZERO.doubleValue() != lastPotentialAmount;
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
	 * @param stringSeparator the string separator to use
	 */
	protected void extractDataFromCurrentLine(String stringSeparator) {
		if (getCurrentLine().matches(getCompleteLinePattern().pattern())) {
			extractCompleteLine(stringSeparator);
		}
		else if (getCurrentLine().matches(getPartialLinePattern().pattern())) {
			extractPartialLine();
		}
		else if (getCurrentLine().matches(AMOUNT_NUMBER_PATTREN.pattern())) {
			setLastPotentialAmount(getAmountAsDouble(getCurrentLine().toString(), stringSeparator));
		}
	}

	/**
	 * Returns the complete line pattern
	 * @return the complete line pattern
	 */
	protected abstract Pattern getCompleteLinePattern();

	/**
	 * Returns the complete line pattern
	 * @return the complete line pattern
	 */
	protected abstract Pattern getPartialLinePattern();

	/**
	 * Extracts the complete line
	 * @param stringSeparator the string separator to use
	 */
	protected void extractCompleteLine(String stringSeparator) {
		setLastPotentialDate(extractDateFromCurrentLine());
		String[] splitLine=getCurrentSplitLine();
		int length=splitLine.length;
		String lastPart=splitLine[length - 1];
		String beforeLastPart=splitLine[length - 2];

		StringBuilder titleBuilder=new StringBuilder();
		if (!beforeLastPart.matches(FACT_PATTREN.pattern()) && beforeLastPart.length() <= 3 && beforeLastPart.matches(NUMBER_PATTREN.pattern())) {
			for (int index=1; index < length - 2; index++) {
				titleBuilder.append(splitLine[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDouble(beforeLastPart + lastPart, stringSeparator));
		}
		else {
			for (int index=1; index < splitLine.length - 1; index++) {
				titleBuilder.append(splitLine[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDouble(lastPart.toString(), stringSeparator));
		}

		String potentialTitle=titleBuilder.toString();
		if (potentialTitle.matches(getPartialLinePattern().pattern())) {
			String[] split=potentialTitle.split(STRING_SEPARATOR);
			titleBuilder=new StringBuilder();
			for (int index=1; index < split.length - 1; index++) {
				titleBuilder.append(split[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}
			titleBuilder.append(split[split.length - 1]);
			setLastPotentialOperationTitle(titleBuilder.toString());
		}
		else {
			setLastPotentialOperationTitle(titleBuilder.toString());
		}
	}

	/**
	 * Extracts the partial line
	 */
	protected void extractPartialLine() {
		setLastPotentialDate(extractDateFromCurrentLine());
		StringBuilder titleBuilder=new StringBuilder();
		for (int index=1; index < getCurrentSplitLine().length; index++) {
			titleBuilder.append(getCurrentSplitLine()[index]);
			titleBuilder.append(STRING_SEPARATOR);
		}
		setLastPotentialOperationTitle(titleBuilder.toString());
	}

	/**
	 * Returns the amount as {@link Double}
	 * @param amount the amount as a string
	 * @param stringSeparator the string separator to use
	 * @return the {@link Double} amount
	 */
	private Double getAmountAsDouble(String amount, String stringSeparator) {
		return new Double(amount.replaceAll(stringSeparator, StringUtils.EMPTY).replaceAll(",", ".")); //$NON-NLS-1$ //$NON-NLS-2$
	}

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
	protected abstract int extractYearFromCurrentLine();

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
