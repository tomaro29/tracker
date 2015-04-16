package fr.rostren.tracker.pdf.analyzer;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.TrackerCreator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses all CE Pdf format.
 * 
 * @author maro
 */
public class CaisseEpargnePdfContentAnalyzer extends AbstractPdfContentAnalyzer {
	/** The empty string. */
	private static final String EMPTY_STR = " ";
	/** The date line pattern. */
	private static final Pattern DATE_LINE_PATTREN = Pattern
			.compile("au (0[0-9]|1[0-9]|2[0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4} - N° [1-9] Page [1-9] / [1-9][\\s\\n\\r]*");
	/** The virements recu line pattern. */
	private static final Pattern VIREMENTS_RECUS_LINE_PATTREN = Pattern
			.compile("Virements reçus[\\s\\n\\r]*");
	/** The paiement cheque line pattern. */
	private static final Pattern PAIEMENT_CHEQUE_LINE_PATTREN = Pattern
			.compile("Paiements chèques[\\s\\n\\r]*");
	/** The frais bancaires line pattern. */
	private static final Pattern FRAIS_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Frais bancaires et cotisations : [+|-][0-9]+,[0-9]{2}\\s€[\\s\\n\\r]*");
	/** The paiements cartes bancaires line pattern. */
	private static final Pattern PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Paiements carte bancaire N°\\s[0-9]+[A-Z\\s\\n\\r]*");
	/** The retraits cartes bancaires line pattern. */
	private static final Pattern RETRAITS_CARTES_BANCAIRES_LINE_PATTREN = Pattern
			.compile("Retraits carte bancaire N°\\s[0-9]+[A-Z\\s\\n\\r]*");
	/** The prelevements line pattern. */
	private static final Pattern PRELEVEMENTS_LINE_PATTREN = Pattern
			.compile("Prélèvements");
	/** The operations diverses line pattern. */
	private static final Pattern OPERATIONS_DIVERSES_LINE_PATTREN = Pattern
			.compile("Opérations diverses");
	/** The operations depot line pattern. */
	private static final Pattern OPERATIONS_DEPOT_LINE_PATTREN = Pattern
			.compile("Opérations de dépôt");
	/** The date separator pattern. */
	private static final Pattern DATE_SEPARATOR_PATTREN = Pattern.compile("/");
	/** The complete line pattern. */
	private static final Pattern COMPLETE_LINE_PATTERN = Pattern
			.compile(PART_1_DATE_PATTREN.pattern()
					.concat(DATE_SEPARATOR_PATTREN.pattern())
					.concat(PART_2_DATE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(OPERATION_TITLE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(AMOUNT_NUMBER_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern()));
	/** The partial line pattern. */
	private static final Pattern PARTIAL_LINE_PATTERN = Pattern
			.compile(PART_1_DATE_PATTREN.pattern()
					.concat(DATE_SEPARATOR_PATTREN.pattern())
					.concat(PART_2_DATE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern())
					.concat(OPERATION_TITLE_PATTREN.pattern())
					.concat(EMPTY_STRING_PATTREN.pattern()));
	/** The tracker elements creator. */
	private final TrackerCreator creator = new TrackerCreator();

	@Override
	public LineContent parseLine(String line, Origin origin) {
		setCurrentLine(line);
		setCurrentSplitLine(getCurrentLine().split(
				SPACE_STRING_PATTREN.pattern()));
		Matcher dateMatcher = DATE_LINE_PATTREN.matcher(getCurrentLine());
		Matcher operationsDepotMatcher = OPERATIONS_DEPOT_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher virRecuMatcher = VIREMENTS_RECUS_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher paiementChequeMatcher = PAIEMENT_CHEQUE_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher fraisBancairesMatcher = FRAIS_BANCAIRES_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher paiementsCartesMatcher = PAIEMENTS_CARTES_BANCAIRES_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher retraitsMatcher = RETRAITS_CARTES_BANCAIRES_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher prelevementsMatcher = PRELEVEMENTS_LINE_PATTREN
				.matcher(getCurrentLine());
		Matcher operationsDiversesMatcher = OPERATIONS_DIVERSES_LINE_PATTREN
				.matcher(getCurrentLine());

		if (dateMatcher.matches()) {
			setCurrentYear(extractYearFromCurrentLine());
			setLastToken(PdfToken.DATE);
		} else if (operationsDepotMatcher.matches()) {
			setLastToken(PdfToken.OPERATIONS_DEPOT);
		} else if (virRecuMatcher.matches()) {
			setLastToken(PdfToken.VIR_RECU);
		} else if (paiementChequeMatcher.matches()) {
			setLastToken(PdfToken.PAIE_CHEQUE);
		} else if (fraisBancairesMatcher.matches()) {
			setLastToken(PdfToken.FRAIS_BANCAIRES);
		} else if (paiementsCartesMatcher.matches()) {
			setLastToken(PdfToken.PAIEMENTS_CARTES);
		} else if (retraitsMatcher.matches()) {
			setLastToken(PdfToken.RETRAITS_CARTES);
		} else if (prelevementsMatcher.matches()) {
			setLastToken(PdfToken.PRELEVEMENTS);
		} else if (operationsDiversesMatcher.matches()) {
			setLastToken(PdfToken.OPERATIONS_DIVERSES);
		} else if (getLastToken() != null
				&& PdfToken.DATE.equals(getLastToken())) {
			// DONOTHING
		} else if (getLastToken() != null) {
			switch (getLastToken()) {
			case OPERATIONS_DEPOT:
			case VIR_RECU:
			case PAIE_CHEQUE:
			case FRAIS_BANCAIRES:
			case PAIEMENTS_CARTES:
			case RETRAITS_CARTES:
			case PRELEVEMENTS:
			case OPERATIONS_DIVERSES:
				return extractOperation(origin);
			default:
				break;
			}
		}
		return null;
	}

	@Override
	protected void extractDataFromCurrentLine() {
		if (getCurrentLine().matches(COMPLETE_LINE_PATTERN.pattern())) {
			setLastPotentialDate(extractDateFromCurrentLine());
			StringBuilder titleBuilder = new StringBuilder();
			StringBuilder amountBuilder = new StringBuilder();
			int length = getCurrentSplitLine().length;
			String lastPart = getCurrentSplitLine()[length - 1];
			String beforeLastPart = getCurrentSplitLine()[length - 2];

			if (!beforeLastPart.matches(FACT_PATTREN.pattern())
					&& beforeLastPart.length() <= 3
					&& beforeLastPart.matches(NUMBER_PATTREN.pattern())) {
				for (int index = 1; index < length - 2; index++) {
					titleBuilder.append(getCurrentSplitLine()[index]);
					titleBuilder.append(EMPTY_STR);
				}
				amountBuilder.append(beforeLastPart);
				amountBuilder.append(lastPart);

				setLastPotentialAmount(getAmountAsDecimal(amountBuilder
						.toString()));
			} else {
				for (int index = 1; index < getCurrentSplitLine().length - 1; index++) {
					titleBuilder.append(getCurrentSplitLine()[index]);
					titleBuilder.append(EMPTY_STR);
				}

				setLastPotentialAmount(getAmountAsDecimal(lastPart.toString()));
			}
			setLastPotentialOperationTitle(titleBuilder.toString());
		} else if (getCurrentLine().matches(PARTIAL_LINE_PATTERN.pattern())) {
			setLastPotentialDate(extractDateFromCurrentLine());
			StringBuilder titleBuilder = new StringBuilder();
			for (int index = 1; index < getCurrentSplitLine().length; index++) {
				titleBuilder.append(getCurrentSplitLine()[index]);
				titleBuilder.append(EMPTY_STR);
			}
			setLastPotentialOperationTitle(titleBuilder.toString());
		} else if (getCurrentLine().matches(AMOUNT_NUMBER_PATTREN.pattern())) {
			setLastPotentialAmount(getAmountAsDecimal(getCurrentLine()
					.toString()));
		}
	}

	/**
	 * Converts an amount as a string to an amount as a decimal.
	 * 
	 * @param amount
	 *            the amount as a string.
	 * @return the amount as a decimal kind.
	 */
	private BigDecimal getAmountAsDecimal(String amount) {
		return new BigDecimal(amount.replaceAll(EMPTY_STR, "").replaceAll(",",
				"."));
	}

	@Override
	protected Date extractDateFromCurrentLine() {
		Date date = null;
		if (getCurrentLine().matches(COMPLETE_LINE_PATTERN.pattern())
				|| getCurrentLine().matches(PARTIAL_LINE_PATTERN.pattern())) {

			String potentialDate = getCurrentSplitLine()[0];
			String[] split = potentialDate.split(DATE_SEPARATOR_PATTREN
					.pattern());

			date = creator.date(Integer.parseInt(split[0]),
					split[1].substring(0, 2), getCurrentYear());
		}
		return date;
	}
}
