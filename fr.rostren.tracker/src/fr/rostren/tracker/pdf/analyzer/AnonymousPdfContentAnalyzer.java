package fr.rostren.tracker.pdf.analyzer;

import java.time.LocalDate;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.LineContent;

/**
 * This class parses an anonymous Pdf format.
 * any date - operation title - amount => anonymous operation
 * The user is invited to define the operations types
 * FIXME to implement
 */
public class AnonymousPdfContentAnalyzer extends AbstractPdfContentAnalyzer {

	@Override
	public LineContent parseLine(String line, Origin origin) {
		//XXX to implement
		return null;
	}

	@Override
	protected void extractDataFromCurrentLine() {
		//XXX to implement
	}

	@Override
	protected LocalDate extractDateFromCurrentLine() {
		//XXX to implement
		return null;
	}

	@Override
	protected int extractYearFromCurrentLine() {
		//XXX to implement
		return 0;
	}
}
