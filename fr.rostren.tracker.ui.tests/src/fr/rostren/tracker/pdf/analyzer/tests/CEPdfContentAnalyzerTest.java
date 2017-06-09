/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.pdf.analyzer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.model.utils.LineContent;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.pdf.analyzer.AbstractPdfContentAnalyzer;
import fr.rostren.tracker.pdf.analyzer.AbstractPdfContentAnalyzer.PdfToken;
import fr.rostren.tracker.pdf.analyzer.CEPdfContentAnalyzer;

public class CEPdfContentAnalyzerTest {

	private final static String currentLine="currentLine"; //$NON-NLS-1$
	private final static String[] currentSplitLine= {"currentSplitLine"}; //$NON-NLS-1$
	private final static int currentYear=2012;
	private final static PdfToken pdfToken=PdfToken.DATE;

	private final static String emptyLine=""; //$NON-NLS-1$
	private final static String blankLine=" "; //$NON-NLS-1$
	private final static String invalidLine="invalid line"; //$NON-NLS-1$
	private final static String validLineWithDate="au 20/12/2014 - N\u00B01 Page 1/2"; //$NON-NLS-1$
	private final static String janValidLine="12/1 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String febValidLine="12/2 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String marValidLine="12/3 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String aprValidLine="12/4 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String mayValidLine="12/5 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String junValidLine="12/6 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String julValidLine="12/7 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String augValidLine="12/8 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String septValidLine="12/9 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String octValidLine="12/10 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String novValidLine="12/11 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String decValidLine="12/12 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String inValidMonthLine="12/13 OperationTitle 20,00"; //$NON-NLS-1$
	private final static String validLineWithBigAmount="12/12 OperationTitle 2 120,00"; //$NON-NLS-1$
	private final static double expectedBigAmount=2120.00;
	private final static String partialValidLine="12/12 OperationTitle"; //$NON-NLS-1$
	private final static String amountValidLine="20,00"; //$NON-NLS-1$
	private final static String bigAmountValidLine="2120.00"; //$NON-NLS-1$
	private final static double expectedAmount=20.00;
	private final static String numbersInvalidLine="15787455025875689"; //$NON-NLS-1$

	/**Tokens*/
	private final static String virementsRecusLine="Virements re\u00E7us"; //$NON-NLS-1$
	private final static String paiementChequeLine="Paiements ch\u00E8ques"; //$NON-NLS-1$
	private final static String fraisBancairesLine="Frais bancaires et cotisations : +9,01 \u20AC"; //$NON-NLS-1$
	private final static String paiementsCartesBancairesLine="Paiements carte bancaire N\u00B0 2"; //$NON-NLS-1$
	private final static String retraitsCartesBancairesLine="Retraits carte bancaire N\u00B0 3"; //$NON-NLS-1$
	private final static String prelevementsLine="Pr\u00E9l\u00E8vements"; //$NON-NLS-1$
	private final static String operationsDiversesLine="Op\u00E9rations diverses"; //$NON-NLS-1$
	private final static String operationsDepotLine="Op\u00E9rations de d\u00E9p\u00F4t"; //$NON-NLS-1$

	private final CEPdfContentAnalyzer analyzer=new CEPdfContentAnalyzer();

	private final Origin testOrigin=TrackerFactory.eINSTANCE.createOrigin();

	/**
	 * Tests the constructor.
	 */
	@Test
	public void constructorTest() {
		assertNotNull(analyzer);

		assertNull(analyzer.getCurrentLine());
		analyzer.setCurrentLine(CEPdfContentAnalyzerTest.currentLine);
		assertEquals(CEPdfContentAnalyzerTest.currentLine, analyzer.getCurrentLine());

		assertNull(analyzer.getCurrentSplitLine());
		analyzer.setCurrentSplitLine(CEPdfContentAnalyzerTest.currentSplitLine);
		assertEquals(1, analyzer.getCurrentSplitLine().length);
		assertEquals(CEPdfContentAnalyzerTest.currentSplitLine[0], analyzer.getCurrentSplitLine()[0]);

		assertEquals(0, analyzer.getCurrentYear());
		analyzer.setCurrentYear(CEPdfContentAnalyzerTest.currentYear);
		assertEquals(CEPdfContentAnalyzerTest.currentYear, analyzer.getCurrentYear());

		assertNull(analyzer.getLastToken());
		analyzer.setLastToken(CEPdfContentAnalyzerTest.pdfToken);
		assertEquals(CEPdfContentAnalyzerTest.pdfToken, analyzer.getLastToken());
	}

	/**
	 * Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	 */
	@Test
	public void parseLine_nullLineTest() {
		assertNull(analyzer.parseLine(null, testOrigin));
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void parseLine_emptyLineTest() {
		assertNull(analyzer.parseLine(CEPdfContentAnalyzerTest.emptyLine, testOrigin));
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void parseLine_blankLineTest() {
		assertNull(analyzer.parseLine(CEPdfContentAnalyzerTest.blankLine, testOrigin));
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test(expected=IllegalArgumentException.class)
	public void parseLine_nullOriginTest() {
		analyzer.parseLine(CEPdfContentAnalyzerTest.decValidLine, null);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void validDateParsingTest() {
		//valid complete parsing
		analyzer.setCurrentYear(2014);
		analyzer.setLastToken(PdfToken.VIR_RECU);
		LineContent content=analyzer.parseLine(CEPdfContentAnalyzerTest.janValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.JANUARY);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.febValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.FEBRUARY);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.marValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.MARCH);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.aprValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.APRIL);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.mayValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.MAY);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.junValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.JUNE);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.julValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.JULY);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.augValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.AUGUST);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.septValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.SEPTEMBER);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.octValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.OCTOBER);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.novValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.NOVEMBER);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.decValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.DECEMBER);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test(expected=DateTimeException.class)
	public void invalidDateParsingTest() {
		//valid complete parsing
		analyzer.setCurrentYear(2014);
		analyzer.setLastToken(PdfToken.VIR_RECU);
		analyzer.parseLine(CEPdfContentAnalyzerTest.inValidMonthLine, testOrigin);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void parseLineTest() {
		/*Tests without setting last token and the year*/
		LineContent content=analyzer.parseLine(CEPdfContentAnalyzerTest.decValidLine, testOrigin);
		assertNull(content);

		//line with date
		assertNull(analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithDate, testOrigin));
		assertEquals(PdfToken.DATE, analyzer.getLastToken());

		/*Tests after setting last token and the year*/
		//invalid lines
		assertNull(analyzer.parseLine(CEPdfContentAnalyzerTest.invalidLine, testOrigin));
		assertNull(analyzer.parseLine(CEPdfContentAnalyzerTest.numbersInvalidLine, testOrigin));

		//valid partial parsing (Date is the last token)
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.partialValidLine, testOrigin);
		assertNull(content);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.amountValidLine, testOrigin);
		assertNull(content);

		analyzer.setLastToken(PdfToken.VIR_RECU);
		assertEquals(PdfToken.VIR_RECU, analyzer.getLastToken());
		//valid partial parsing (VIR_RECU is the last token)
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.partialValidLine, testOrigin);
		assertNull(content);
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.amountValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.DECEMBER);

		//valid complete parsing
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.decValidLine, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		//parse tokens
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.virementsRecusLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.VIR_RECU, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.paiementChequeLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PAIE_CHEQUE, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.fraisBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.FRAIS_BANCAIRES, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.paiementsCartesBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PAIEMENTS_CARTES, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.retraitsCartesBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.RETRAITS_CARTES, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.prelevementsLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PRELEVEMENTS, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.operationsDiversesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.OPERATIONS_DIVERSES, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);

		content=analyzer.parseLine(CEPdfContentAnalyzerTest.operationsDepotLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.OPERATIONS_DEPOT, analyzer.getLastToken());
		content=analyzer.parseLine(CEPdfContentAnalyzerTest.validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, CEPdfContentAnalyzerTest.expectedBigAmount, Month.DECEMBER);
	}

	/**
	 * Tests the valid line credit parse result
	 * @param content the parsed line content
	 * @param expectedAmount the expected amount to check
	 * @param expectedMonth the expected month to check
	 */
	private void testCreditParsingResult(LineContent content, double expectedAmount, Month expectedMonth) {
		assertNotNull(content);
		assertEquals("OperationTitle ", content.getTitle()); //$NON-NLS-1$
		OperationData operation=content.getOperation();
		assertEquals(OperationType.CREDIT, operation.getType());
		assertNull(operation.getOperationTitle());
		assertEquals(String.valueOf(expectedAmount), String.valueOf(operation.getTotalAmount()));
		LocalDate date=operation.getDate();
		assertNotNull(date);
		assertEquals(12, date.getDayOfMonth());
		assertEquals(expectedMonth, date.getMonth());
		assertEquals(2014, date.getYear());
		assertNull(content.getLinkedOperationTitle());
		assertNull(content.getLinkedCategory());
	}

	/**
	 * Tests the valid line Debit parse result
	 * @param content the parsed line content
	 * @param expectedAmount the expected amount to check
	 * @param expectedMonth the expected month to check
	 */
	private void testDebitParsingResult(LineContent content, double expectedAmount, Month expectedMonth) {
		assertNotNull(content);
		assertEquals("OperationTitle ", content.getTitle()); //$NON-NLS-1$
		OperationData operation=content.getOperation();
		assertEquals(OperationType.DEBIT, operation.getType());
		assertNull(operation.getOperationTitle());
		assertEquals(String.valueOf(expectedAmount), String.valueOf(operation.getTotalAmount()));
		LocalDate date=operation.getDate();
		assertNotNull(date);
		assertEquals(12, date.getDayOfMonth());
		assertEquals(expectedMonth, date.getMonth());
		assertEquals(2014, date.getYear());
		assertNull(content.getLinkedOperationTitle());
		assertNull(content.getLinkedCategory());
	}
}
