/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.pdf.analyzer;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.model.utils.LineContent;

/**
 * This class parses CIC Pdf format.
 */
public class CICPdfContentAnalyzer extends AbstractPdfContentAnalyzer {
	private final Pattern CIC_DATE_LINE_PATTREN=Pattern.compile("SOLDE CREDITEUR AU " //$NON-NLS-1$
			.concat(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern()).concat(DATE_SEPARATOR_PATTREN.pattern())
					.concat(PART_3_DATE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()).concat(AMOUNT_NUMBER_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern())));
	private final Pattern COMPLETE_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()).concat(AMOUNT_NUMBER_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()));
	private final Pattern PARTIAL_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_3_DATE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()));

	@Override
	LineContent parseValidLine(String line, Origin origin) {
		Matcher dateMatcher=CIC_DATE_LINE_PATTREN.matcher(line);
		if (getCurrentYear() == 0 && dateMatcher.matches()) {
			setCurrentYear(extractYearFromCurrentLine());
			setLastToken(PdfToken.DATE);
			return null;
		}
		if (getLastToken() != null && PdfToken.DATE.equals(getLastToken())) {
			return extractOperation(origin, "[\\. ]"); //$NON-NLS-1$
		}
		return null;
	}

	@Override
	protected LocalDate extractDateFromCurrentLine() {
		if (getCurrentLine().matches(COMPLETE_LINE_PATTERN.pattern()) || getCurrentLine().matches(PARTIAL_LINE_PATTERN.pattern())) {
			String potentialDate=getCurrentSplitLine()[0];
			String[] split=potentialDate.split(DATE_SEPARATOR_PATTREN.pattern());

			return LocalDate.of(Integer.parseInt(split[2]), Month.of(Integer.parseInt(split[1])), Integer.parseInt(split[0]));
		}
		return null;
	}

	@Override
	protected Pattern getCompleteLinePattern() {
		return COMPLETE_LINE_PATTERN;
	}

	@Override
	protected Pattern getPartialLinePattern() {
		return PARTIAL_LINE_PATTERN;
	}

	@Override
	protected int extractYearFromCurrentLine() {
		String year=getCurrentLine().subSequence(25, 29).toString();
		return Integer.parseInt(year);
	}
}
