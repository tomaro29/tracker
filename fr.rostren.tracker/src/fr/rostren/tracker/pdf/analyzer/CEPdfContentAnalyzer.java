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
 * This class parses CE Pdf format.
 */
public class CEPdfContentAnalyzer extends AbstractPdfContentAnalyzer {

	private final Pattern CE_DATE_LINE_PATTREN=Pattern.compile(
			"au (0[1-9]|1[0-9]|2[0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}[\\s]*-[\\s]*N\u00B0[\\s]*([1-9]|0[1-9]|[1-9]{2})[\\s]*Page[\\s]*[1-9][\\s]*/[\\s]*[1-9][\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_VIREMENTS_RECUS_LINE_PATTREN=Pattern.compile("Virements re\u00E7us[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_PAIEMENT_CHEQUE_LINE_PATTREN=Pattern.compile("Paiements ch\u00E8ques[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_FRAIS_BANCAIRES_LINE_PATTREN=Pattern.compile("Frais bancaires et cotisations : [+|-][0-9]+,[0-9]{2}\\s\u20AC[\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN=Pattern.compile("Paiements carte bancaire N\u00B0\\s[0-9]+[A-Z\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_RETRAITS_CARTES_BANCAIRES_LINE_PATTREN=Pattern.compile("Retraits carte bancaire N\u00B0\\s[0-9]+[A-Z\\s\\n\\r]*"); //$NON-NLS-1$
	private final Pattern CE_PRELEVEMENTS_LINE_PATTREN=Pattern.compile("Pr\u00E9l\u00E8vements"); //$NON-NLS-1$
	private final Pattern CE_OPERATIONS_DIVERSES_LINE_PATTREN=Pattern.compile("Op\u00E9rations diverses"); //$NON-NLS-1$
	private final Pattern CE_OPERATIONS_DEPOT_LINE_PATTREN=Pattern.compile("Op\u00E9rations de d\u00E9p\u00F4t"); //$NON-NLS-1$

	private final Pattern CE_COMPLETE_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()).concat(AMOUNT_NUMBER_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()));
	private final Pattern CE_PARTIAL_LINE_PATTERN=Pattern.compile(PART_1_DATE_PATTREN.pattern().concat(DATE_SEPARATOR_PATTREN.pattern()).concat(PART_2_DATE_PATTREN.pattern())
			.concat(EMPTY_STRING_PATTREN.pattern()).concat(OPERATION_TITLE_PATTREN.pattern()).concat(EMPTY_STRING_PATTREN.pattern()));

	@Override
	LineContent parseValidLine(String line, Origin origin) {
		Matcher dateMatcher=CE_DATE_LINE_PATTREN.matcher(line);
		Matcher operationsDepotMatcher=CE_OPERATIONS_DEPOT_LINE_PATTREN.matcher(line);
		Matcher virRecuMatcher=CE_VIREMENTS_RECUS_LINE_PATTREN.matcher(line);
		Matcher paiementChequeMatcher=CE_PAIEMENT_CHEQUE_LINE_PATTREN.matcher(line);
		Matcher fraisBancairesMatcher=CE_FRAIS_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher paiementsCartesMatcher=CE_PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher retraitsMatcher=CE_RETRAITS_CARTES_BANCAIRES_LINE_PATTREN.matcher(line);
		Matcher prelevementsMatcher=CE_PRELEVEMENTS_LINE_PATTREN.matcher(line);
		Matcher operationsDiversesMatcher=CE_OPERATIONS_DIVERSES_LINE_PATTREN.matcher(line);

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
			return extractOperation(origin, STRING_SEPARATOR);
		}
		return null;
	}

	@Override
	protected Pattern getCompleteLinePattern() {
		return CE_COMPLETE_LINE_PATTERN;
	}

	@Override
	protected Pattern getPartialLinePattern() {
		return CE_PARTIAL_LINE_PATTERN;
	}

	@Override
	protected LocalDate extractDateFromCurrentLine() {
		if (getCurrentLine().matches(CE_COMPLETE_LINE_PATTERN.pattern()) || getCurrentLine().matches(CE_PARTIAL_LINE_PATTERN.pattern())) {
			String potentialDate=getCurrentSplitLine()[0];
			String[] split=potentialDate.split(DATE_SEPARATOR_PATTREN.pattern());

			return LocalDate.of(getCurrentYear(), Month.of(Integer.parseInt(split[1])), Integer.parseInt(split[0]));
		}
		return null;
	}

	@Override
	protected int extractYearFromCurrentLine() {
		String year=getCurrentLine().subSequence(9, 13).toString();
		return Integer.parseInt(year);
	}
}
