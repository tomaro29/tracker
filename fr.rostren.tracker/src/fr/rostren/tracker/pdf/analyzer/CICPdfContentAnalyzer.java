package fr.rostren.tracker.pdf.analyzer;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;

/**
 * This class parses CIC Pdf format.
 * FIXME to implement
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

		Matcher dateMatcher=CIC_DATE_LINE_PATTREN.matcher(line);
		if (getCurrentYear() == 0 && dateMatcher.matches()) {
			setCurrentYear(extractYearFromCurrentLine());
			setLastToken(PdfToken.DATE);
			return null;
		}
		if (getLastToken() != null && PdfToken.DATE.equals(getLastToken())) {
			return extractOperation(origin);
		}
		return null;
	}

	@Override
	protected void extractDataFromCurrentLine() {
		if (getCurrentLine().matches(COMPLETE_LINE_PATTERN.pattern())) {
			extractCompleteLine();
		}
		else if (getCurrentLine().matches(PARTIAL_LINE_PATTERN.pattern())) {
			extractPartialLine();
		}
		else if (getCurrentLine().matches(AMOUNT_NUMBER_PATTREN.pattern())) {
			setLastPotentialAmount(getAmountAsDouble(getCurrentLine().toString()));
		}
	}

	/**
	 * Extracts the partial line
	 */
	private void extractPartialLine() {
		setLastPotentialDate(extractDateFromCurrentLine());
		StringBuilder titleBuilder=new StringBuilder();
		for (int index=1; index < getCurrentSplitLine().length; index++) {
			titleBuilder.append(getCurrentSplitLine()[index]);
			titleBuilder.append(STRING_SEPARATOR);
		}
		setLastPotentialOperationTitle(titleBuilder.toString());
	}

	/**
	 * Extracts the complete line
	 */
	private void extractCompleteLine() {
		setLastPotentialDate(extractDateFromCurrentLine());
		String[] currentSplitLine=getCurrentSplitLine();
		int length=currentSplitLine.length;
		String lastPart=currentSplitLine[length - 1];
		String beforeLastPart=currentSplitLine[length - 2];

		StringBuilder titleBuilder=new StringBuilder();
		if (!beforeLastPart.matches(FACT_PATTREN.pattern()) && beforeLastPart.length() <= 3 && beforeLastPart.matches(NUMBER_PATTREN.pattern())) {
			for (int index=1; index < length - 2; index++) {
				titleBuilder.append(currentSplitLine[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDouble(beforeLastPart + lastPart));
		}
		else {
			for (int index=1; index < currentSplitLine.length - 1; index++) {
				titleBuilder.append(currentSplitLine[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDouble(lastPart.toString()));
		}

		String potentialTitle=titleBuilder.toString();
		if (potentialTitle.matches(PARTIAL_LINE_PATTERN.pattern())) {
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
	 * Returns the amount as {@link Double}
	 * @param amount the amount as a string
	 * @return the {@link Double} amount
	 */
	private Double getAmountAsDouble(String amount) {
		return new Double(amount.replaceAll("[\\. ]", StringUtils.EMPTY).replaceAll(",", ".")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	protected int extractYearFromCurrentLine() {
		String year=getCurrentLine().subSequence(25, 29).toString();
		return Integer.parseInt(year);
	}
}
