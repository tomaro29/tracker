package fr.rostren.tracker.pdf.analyzer;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.utils.LineContent;

/**
 * This class parses all caisse epargne Pdf format.
 *
 * @author maro
 */
public class CEPdfContentAnalyzer extends AbstractPdfContentAnalyzer {

	private final Pattern ONLY_NUMBERS=Pattern.compile("[0-9]*"); //$NON-NLS-1$
	private final Pattern DATE_LINE_PATTREN=Pattern
			.compile("au (0[1-9]|1[0-9]|2[0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}[\\s]*-[\\s]*N\u00B0[\\s]*[1-9]{2}[\\s]*Page[\\s]*[1-9][\\s]*/[\\s]*[1-9][\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern VIREMENTS_RECUS_LINE_PATTREN=Pattern.compile("Virements re\u00E7us[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern PAIEMENT_CHEQUE_LINE_PATTREN=Pattern.compile("Paiements ch\u00E8ques[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern FRAIS_BANCAIRES_LINE_PATTREN=Pattern.compile("Frais bancaires et cotisations : [+|-][0-9]+,[0-9]{2}\\s\u20AC[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN=Pattern.compile("Paiements carte bancaire N\u00B0\\s[0-9]+[A-Z\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern RETRAITS_CARTES_BANCAIRES_LINE_PATTREN=Pattern.compile("Retraits carte bancaire N\u00B0\\s[0-9]+[A-Z\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern PRELEVEMENTS_LINE_PATTREN=Pattern.compile("Pr\u00E9l\u00E8vements"); //$NON-NLS-1$
	private final Pattern OPERATIONS_DIVERSES_LINE_PATTREN=Pattern.compile("Op\u00E9rations diverses"); //$NON-NLS-1$
	private final Pattern OPERATIONS_DEPOT_LINE_PATTREN=Pattern.compile("Op\u00E9rations de d\u00E9p\u00F4t"); //$NON-NLS-1$

	private final Pattern DATE_SEPARATOR_PATTREN=Pattern.compile("/"); //$NON-NLS-1$
	private final Pattern COMPLETE_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()).concat(AMOUNT_NUMBER_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()));
	private final Pattern PARTIAL_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()));

	private final String STRING_SEPARATOR=" "; //$NON-NLS-1$

	@Override
	public LineContent parseLine(String line, Origin origin) {
		setCurrentLine(line);
		setCurrentSplitLine(getCurrentLine().split(SPACE_STRING_PATTREN.pattern()));
		Matcher numbersMatcher=ONLY_NUMBERS.matcher(line);
		if (numbersMatcher.matches()) {
			return null;
		}

		Matcher dateMatcher=DATE_LINE_PATTREN.matcher(line);
		Matcher operationsDepotMatcher=OPERATIONS_DEPOT_LINE_PATTREN.matcher(line);
		Matcher virRecuMatcher=VIREMENTS_RECUS_LINE_PATTREN.matcher(line);
		Matcher paiementChequeMatcher=PAIEMENT_CHEQUE_LINE_PATTREN.matcher(line);
		Matcher fraisBancairesMatcher=FRAIS_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher paiementsCartesMatcher=PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher retraitsMatcher=RETRAITS_CARTES_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher prelevementsMatcher=PRELEVEMENTS_LINE_PATTREN.matcher(line);
		Matcher operationsDiversesMatcher=OPERATIONS_DIVERSES_LINE_PATTREN.matcher(line);

		if (getCurrentYear() == 0 && dateMatcher.matches()) {
			setCurrentYear(extractYearFromCurrentLine());
			setLastToken(PdfToken.DATE);
		}
		else if (operationsDepotMatcher.matches()) {
			setLastToken(PdfToken.OPERATIONS_DEPOT);
		}
		else if (virRecuMatcher.matches()) {
			setLastToken(PdfToken.VIR_RECU);
		}
		else if (paiementChequeMatcher.matches()) {
			setLastToken(PdfToken.PAIE_CHEQUE);
		}
		else if (fraisBancairesMatcher.matches()) {
			setLastToken(PdfToken.FRAIS_BANCAIRES);
		}
		else if (paiementsCartesMatcher.matches()) {
			setLastToken(PdfToken.PAIEMENTS_CARTES);
		}
		else if (retraitsMatcher.matches()) {
			setLastToken(PdfToken.RETRAITS_CARTES);
		}
		else if (prelevementsMatcher.matches()) {
			setLastToken(PdfToken.PRELEVEMENTS);
		}
		else if (operationsDiversesMatcher.matches()) {
			setLastToken(PdfToken.OPERATIONS_DIVERSES);
		}
		else if (getLastToken() != null && PdfToken.DATE.equals(getLastToken())) {
			// DONOTHING
		}
		else if (getLastToken() != null
					&& (PdfToken.OPERATIONS_DEPOT.equals(getLastToken())	|| PdfToken.VIR_RECU.equals(getLastToken()) || PdfToken.PAIE_CHEQUE.equals(getLastToken())
						|| PdfToken.FRAIS_BANCAIRES.equals(getLastToken()) || PdfToken.PAIEMENTS_CARTES.equals(getLastToken()) || PdfToken.RETRAITS_CARTES.equals(getLastToken())
						|| PdfToken.PRELEVEMENTS.equals(getLastToken()) || PdfToken.OPERATIONS_DIVERSES.equals(getLastToken()))) {
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
			setLastPotentialAmount(getAmountAsDecimal(getCurrentLine().toString()));
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
		int length=getCurrentSplitLine().length;
		String lastPart=getCurrentSplitLine()[length - 1];
		String beforeLastPart=getCurrentSplitLine()[length - 2];

		StringBuilder titleBuilder=new StringBuilder();
		if (!beforeLastPart.matches(FACT_PATTREN.pattern()) && beforeLastPart.length() <= 3 && beforeLastPart.matches(NUMBER_PATTREN.pattern())) {
			for (int index=1; index < length - 2; index++) {
				titleBuilder.append(getCurrentSplitLine()[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDecimal(beforeLastPart + lastPart));
		}
		else {
			for (int index=1; index < getCurrentSplitLine().length - 1; index++) {
				titleBuilder.append(getCurrentSplitLine()[index]);
				titleBuilder.append(STRING_SEPARATOR);
			}

			setLastPotentialAmount(getAmountAsDecimal(lastPart.toString()));
		}
		setLastPotentialOperationTitle(titleBuilder.toString());
	}

	/**
	 * Returns the amount as {@link BigDecimal}
	 * @param amount the amount as a string
	 * @return the {@link BigDecimal}amount
	 */
	private BigDecimal getAmountAsDecimal(String amount) {
		return new BigDecimal(amount.replaceAll(STRING_SEPARATOR, StringUtils.EMPTY).replaceAll(",", ".")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected Date extractDateFromCurrentLine() {
		Date date=null;
		if (getCurrentLine().matches(COMPLETE_LINE_PATTERN.pattern()) || getCurrentLine().matches(PARTIAL_LINE_PATTERN.pattern())) {
			date=TrackerFactory.eINSTANCE.createDate();
			String potentialDate=getCurrentSplitLine()[0];
			String[] split=potentialDate.split(DATE_SEPARATOR_PATTREN.pattern());

			date.setDay(Integer.parseInt(split[0]));

			String month=split[1].substring(0, 2);
			setMonthFromContent(date, month);
			date.setYear(getCurrentYear());
		}
		return date;
	}
}
