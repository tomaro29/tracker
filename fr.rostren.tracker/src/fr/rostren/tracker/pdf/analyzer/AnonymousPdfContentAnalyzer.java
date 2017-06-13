/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.pdf.analyzer;

import java.time.LocalDate;
import java.util.regex.Pattern;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.model.utils.LineContent;

/**
 * This class parses an anonymous Pdf format.
 * any date - operation title - amount => anonymous operation
 * The user is invited to define the operations types
 */
public class AnonymousPdfContentAnalyzer extends AbstractPdfContentAnalyzer {

	@Override
	public LineContent parseLine(String line, Origin origin) {
		//XXX to implement
		return null;
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

	@Override
	LineContent parseValidLine(String line, Origin origin) {
		//XXX to implement
		return null;
	}

	@Override
	protected Pattern getCompleteLinePattern() {
		//XXX to implement
		return null;
	}

	@Override
	protected Pattern getPartialLinePattern() {
		//XXX to implement
		return null;
	}
}
