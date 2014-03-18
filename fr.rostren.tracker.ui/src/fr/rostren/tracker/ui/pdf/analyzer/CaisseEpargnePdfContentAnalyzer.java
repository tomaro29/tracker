package fr.rostren.tracker.ui.pdf.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.ui.pdf.utils.LineContent;

/**
 * This class parses all caisse epargne Pdf format.
 * 
 * @author mrostren
 */
public class CaisseEpargnePdfContentAnalyzer extends AbstractPdfContentAnalyzer {

	private final Pattern DATE_LINE_PATTREN = Pattern
			.compile("au (0[0-9]|1[0-9]|2[0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4} - N° [1-9] Page [1-9] / [1-9][\\s\\n\\r]*");
	private final Pattern VIREMENTS_RECUS_LINE_PATTREN = Pattern
			.compile("Virements reçus[\\s\\n\\r]*");
	private final Pattern PAIEMENT_CHEQUE_LINE_PATTREN = Pattern
			.compile("Paiements chèques[\\s\\n\\r]*");
	private final Pattern FRAIS_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Frais bancaires et cotisations : [+|-][0-9]+,[0-9]{2}\\s€[\\s\\n\\r]*");
	private final Pattern PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Paiements carte bancaire N°\\s[0-9]+[A-Z\\s\\n\\r]*");
	private final Pattern RETRAITS_CARTES_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Retraits carte bancaire N°\\s[0-9]+[A-Z\\s\\n\\r]*");
	private final Pattern PRELEVEMENTS_LINE_PATTREN = Pattern
			.compile("Prélèvements");
	private final Pattern OPERATIONS_DIVERSES_LINE_PATTREN = Pattern
			.compile("Opérations diverses");

	private final Pattern DATE_SEPARATOR_PATTREN = Pattern.compile("/");
	private final Pattern COMPLETE_LINE_PATTERN = Pattern
			.compile(PART_1_DATE_PATTREN.pattern()
					.concat(DATE_SEPARATOR_PATTREN.pattern())
					.concat(PART_2_DATE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(OPERATION_TITLE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(AMOUNT_NUMBER_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern()));
	private final Pattern PARTIAL_LINE_PATTERN = Pattern
			.compile(PART_1_DATE_PATTREN.pattern()
					.concat(DATE_SEPARATOR_PATTREN.pattern())
					.concat(PART_2_DATE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(OPERATION_TITLE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern()));

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.rostren.tracker.ui.pdf.analyzer.AbstractPdfContentAnalyzer#parseLine
	 * (java.lang.String)
	 */
	public LineContent parseLine(String line) {
		currentLine = line;
		currentSplitLine = currentLine.split(SPACE_STRING_PATTREN.pattern());
		Matcher dateMatcher = DATE_LINE_PATTREN.matcher(currentLine);
		Matcher virRecuMatcher = VIREMENTS_RECUS_LINE_PATTREN
				.matcher(currentLine);
		Matcher paiementChequeMatcher = PAIEMENT_CHEQUE_LINE_PATTREN
				.matcher(currentLine);
		Matcher fraisBancairesMatcher = FRAIS_BANCAIRES_LINE_PATTREN
				.matcher(currentLine);
		Matcher paiementsCartesMatcher = PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN
				.matcher(currentLine);
		Matcher retraitsMatcher = RETRAITS_CARTES_BANCAIRES_LINE_PATTREN
				.matcher(currentLine);
		Matcher prelevementsMatcher = PRELEVEMENTS_LINE_PATTREN
				.matcher(currentLine);
		Matcher operationsDiversesMatcher = OPERATIONS_DIVERSES_LINE_PATTREN
				.matcher(currentLine);

		if (dateMatcher.matches()) {
			currentYear = extractYearFromCurrentLine();
			lastToken = PdfToken.DATE;
		} else if (virRecuMatcher.matches()) {
			lastToken = PdfToken.VIR_RECU;
		} else if (paiementChequeMatcher.matches()) {
			lastToken = PdfToken.PAIE_CHEQUE;
		} else if (fraisBancairesMatcher.matches()) {
			lastToken = PdfToken.FRAIS_BANCAIRES;
		} else if (paiementsCartesMatcher.matches()) {
			lastToken = PdfToken.PAIEMENTS_CARTES;
		} else if (retraitsMatcher.matches()) {
			lastToken = PdfToken.RETRAITS_CARTES;
		} else if (prelevementsMatcher.matches()) {
			lastToken = PdfToken.PRELEVEMENTS;
		} else if (operationsDiversesMatcher.matches()) {
			lastToken = PdfToken.OPERATIONS_DIVERSES;
		} else if (lastToken != null && PdfToken.DATE.equals(lastToken)) {
			// DONOTHING
		} else if (lastToken != null
				&& (PdfToken.VIR_RECU.equals(lastToken)
						|| PdfToken.PAIE_CHEQUE.equals(lastToken)
						|| PdfToken.FRAIS_BANCAIRES.equals(lastToken)
						|| PdfToken.PAIEMENTS_CARTES.equals(lastToken)
						|| PdfToken.RETRAITS_CARTES.equals(lastToken)
						|| PdfToken.PRELEVEMENTS.equals(lastToken) || PdfToken.OPERATIONS_DIVERSES
							.equals(lastToken))) {
			return extractOperation();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.rostren.tracker.ui.pdf.analyzer.AbstractPdfContentAnalyzer#
	 * extractDataFromCurrentLine()
	 */
	protected void extractDataFromCurrentLine() {
		if (currentLine.matches(COMPLETE_LINE_PATTERN.pattern())) {
			lastPotentialDate = extractDateFromCurrentLine();
			StringBuilder titleBuilder = new StringBuilder();
			StringBuilder amountBuilder = new StringBuilder();
			int length = currentSplitLine.length;
			String lastPart = currentSplitLine[length - 1];
			String beforeLastPart = currentSplitLine[length - 2];
			if (beforeLastPart.matches(NUMBER_PATTREN.pattern())) {
				for (int index = 1; index < length - 2; index++) {
					titleBuilder.append(currentSplitLine[index]);
					titleBuilder.append(" ");
				}
				amountBuilder.append(beforeLastPart);
				amountBuilder.append(lastPart);

				lastPotentialAmount = Float.parseFloat(amountBuilder.toString()
						.replaceAll(" ", "").replaceAll(",", "."));
			} else {
				for (int index = 1; index < currentSplitLine.length - 1; index++) {
					titleBuilder.append(currentSplitLine[index]);
					titleBuilder.append(" ");
				}

				lastPotentialAmount = Float.parseFloat(lastPart.toString()
						.replaceAll(" ", "").replaceAll(",", "."));
			}
			lastPotentialOperationTitle = titleBuilder.toString();
		} else if (currentLine.matches(PARTIAL_LINE_PATTERN.pattern())) {
			lastPotentialDate = extractDateFromCurrentLine();
			StringBuilder titleBuilder = new StringBuilder();
			for (int index = 1; index < currentSplitLine.length - 1; index++) {
				titleBuilder.append(currentSplitLine[index]);
				titleBuilder.append(" ");
			}
			lastPotentialOperationTitle = titleBuilder.toString();
		} else if (currentLine.matches(AMOUNT_NUMBER_PATTREN.pattern())) {
			lastPotentialAmount = Float.parseFloat(currentLine.replaceAll(" ",
					"").replaceAll(",", "."));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.rostren.tracker.ui.pdf.analyzer.AbstractPdfContentAnalyzer#
	 * extractDateFromCurrentLine()
	 */
	protected Date extractDateFromCurrentLine() {
		Date date = null;
		if (currentLine.matches(COMPLETE_LINE_PATTERN.pattern())
				|| currentLine.matches(PARTIAL_LINE_PATTERN.pattern())) {
			date = TrackerFactory.eINSTANCE.createDate();
			String potentialDate = currentSplitLine[0];
			String[] split = potentialDate.split(DATE_SEPARATOR_PATTREN
					.pattern());

			date.setDay(Integer.parseInt(split[0]));

			String month = split[1].substring(0, 2);
			setMonthFromContent(date, month);
			date.setYear(currentYear);
		}
		return date;
	}
}
