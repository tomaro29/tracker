package fr.rostren.tracker.pdf.analyzer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.analyzer.AbstractPdfContentAnalyzer;
import fr.rostren.tracker.pdf.analyzer.AbstractPdfContentAnalyzer.PdfToken;
import fr.rostren.tracker.pdf.analyzer.CEPdfContentAnalyzer;
import fr.rostren.tracker.pdf.utils.LineContent;

public class CEPdfContentAnalyzerTest {

	private final String currentLine="currentLine"; //$NON-NLS-1$
	private final String[] currentSplitLine= {"currentSplitLine"}; //$NON-NLS-1$
	private final int currentYear=2012;
	private final PdfToken pdfToken=PdfToken.DATE;

	private final CEPdfContentAnalyzer analyzer=new CEPdfContentAnalyzer();

	private final Origin testOrigin=TrackerFactory.eINSTANCE.createOrigin();

	private final String emptyLine=""; //$NON-NLS-1$
	private final String blankLine=" "; //$NON-NLS-1$
	private final String invalidLine="invalid line"; //$NON-NLS-1$
	private final String validLineWithDate="au 20/12/2014 - N\u00B01 Page 1/2"; //$NON-NLS-1$
	private final String janValidLine="12/1 OperationTitle 20,00"; //$NON-NLS-1$
	private final String febValidLine="12/2 OperationTitle 20,00"; //$NON-NLS-1$
	private final String marValidLine="12/3 OperationTitle 20,00"; //$NON-NLS-1$
	private final String aprValidLine="12/4 OperationTitle 20,00"; //$NON-NLS-1$
	private final String mayValidLine="12/5 OperationTitle 20,00"; //$NON-NLS-1$
	private final String junValidLine="12/6 OperationTitle 20,00"; //$NON-NLS-1$
	private final String julValidLine="12/7 OperationTitle 20,00"; //$NON-NLS-1$
	private final String augValidLine="12/8 OperationTitle 20,00"; //$NON-NLS-1$
	private final String septValidLine="12/9 OperationTitle 20,00"; //$NON-NLS-1$
	private final String octValidLine="12/10 OperationTitle 20,00"; //$NON-NLS-1$
	private final String novValidLine="12/11 OperationTitle 20,00"; //$NON-NLS-1$
	private final String decValidLine="12/12 OperationTitle 20,00"; //$NON-NLS-1$
	private final String inValidMonthLine="12/13 OperationTitle 20,00"; //$NON-NLS-1$
	private final String validLineWithBigAmount="12/12 OperationTitle 2 120,00"; //$NON-NLS-1$
	private final double expectedBigAmount=2120.00;
	private final String partialValidLine="12/12 OperationTitle"; //$NON-NLS-1$
	private final String amountValidLine="20,00"; //$NON-NLS-1$
	private final String bigAmountValidLine="2120.00"; //$NON-NLS-1$
	private final double expectedAmount=20.00;
	private final String numbersInvalidLine="15787455025875689"; //$NON-NLS-1$

	/**Tokens*/
	private final String virementsRecusLine="Virements re\u00E7us"; //$NON-NLS-1$
	private final String paiementChequeLine="Paiements ch\u00E8ques"; //$NON-NLS-1$
	private final String fraisBancairesLine="Frais bancaires et cotisations : +9,01 \u20AC"; //$NON-NLS-1$
	private final String paiementsCartesBancairesLine="Paiements carte bancaire N\u00B0 2"; //$NON-NLS-1$
	private final String retraitsCartesBancairesLine="Retraits carte bancaire N\u00B0 3"; //$NON-NLS-1$
	private final String prelevementsLine="Pr\u00E9l\u00E8vements"; //$NON-NLS-1$
	private final String operationsDiversesLine="Op\u00E9rations diverses"; //$NON-NLS-1$
	private final String operationsDepotLine="Op\u00E9rations de d\u00E9p\u00F4t"; //$NON-NLS-1$

	/**
	 * Tests the constructor.
	 */
	@Test
	public void constructorTest() {
		assertNotNull(analyzer);

		assertNull(analyzer.getCurrentLine());
		analyzer.setCurrentLine(currentLine);
		assertEquals(currentLine, analyzer.getCurrentLine());

		assertNull(analyzer.getCurrentSplitLine());
		analyzer.setCurrentSplitLine(currentSplitLine);
		assertEquals(1, analyzer.getCurrentSplitLine().length);
		assertEquals(currentSplitLine[0], analyzer.getCurrentSplitLine()[0]);

		assertEquals(0, analyzer.getCurrentYear());
		analyzer.setCurrentYear(currentYear);
		assertEquals(currentYear, analyzer.getCurrentYear());

		assertNull(analyzer.getLastToken());
		analyzer.setLastToken(pdfToken);
		assertEquals(pdfToken, analyzer.getLastToken());
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
		assertNull(analyzer.parseLine(emptyLine, testOrigin));
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void parseLine_blankLineTest() {
		assertNull(analyzer.parseLine(blankLine, testOrigin));
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test(expected=IllegalArgumentException.class)
	public void parseLine_nullOriginTest() {
		analyzer.parseLine(decValidLine, null);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void validDateParsingTest() {
		//valid complete parsing
		analyzer.setCurrentYear(2014);
		analyzer.setLastToken(PdfToken.VIR_RECU);
		LineContent content=analyzer.parseLine(janValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.JAN);
		content=analyzer.parseLine(febValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.FEB);
		content=analyzer.parseLine(marValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.MARS);
		content=analyzer.parseLine(aprValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.APR);
		content=analyzer.parseLine(mayValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.MAY);
		content=analyzer.parseLine(junValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.JUNE);
		content=analyzer.parseLine(julValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.JULY);
		content=analyzer.parseLine(augValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.AUG);
		content=analyzer.parseLine(septValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.SEPT);
		content=analyzer.parseLine(octValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.OCT);
		content=analyzer.parseLine(novValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.NOV);
		content=analyzer.parseLine(decValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.DEC);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test(expected=IllegalArgumentException.class)
	public void invalidDateParsingTest() {
		//valid complete parsing
		analyzer.setCurrentYear(2014);
		analyzer.setLastToken(PdfToken.VIR_RECU);
		analyzer.parseLine(inValidMonthLine, testOrigin);
	}

	/**
	* Tests the {@link AbstractPdfContentAnalyzer#parseLine(String, Origin)} method
	*/
	@Test
	public void parseLineTest() {
		/*Tests without setting last token and the year*/
		LineContent content=analyzer.parseLine(decValidLine, testOrigin);
		assertNull(content);

		//line with date
		assertNull(analyzer.parseLine(validLineWithDate, testOrigin));
		assertEquals(PdfToken.DATE, analyzer.getLastToken());

		/*Tests after setting last token and the year*/
		//invalid lines
		assertNull(analyzer.parseLine(invalidLine, testOrigin));
		assertNull(analyzer.parseLine(numbersInvalidLine, testOrigin));

		//valid partial parsing (Date is the last token)
		content=analyzer.parseLine(partialValidLine, testOrigin);
		assertNull(content);
		content=analyzer.parseLine(amountValidLine, testOrigin);
		assertNull(content);

		analyzer.setLastToken(PdfToken.VIR_RECU);
		assertEquals(PdfToken.VIR_RECU, analyzer.getLastToken());
		//valid partial parsing (VIR_RECU is the last token)
		content=analyzer.parseLine(partialValidLine, testOrigin);
		assertNull(content);
		content=analyzer.parseLine(amountValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.DEC);

		//valid complete parsing
		content=analyzer.parseLine(decValidLine, testOrigin);
		testCreditParsingResult(content, expectedAmount, Month.DEC);

		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, expectedBigAmount, Month.DEC);

		//parse tokens
		content=analyzer.parseLine(virementsRecusLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.VIR_RECU, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(paiementChequeLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PAIE_CHEQUE, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(fraisBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.FRAIS_BANCAIRES, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(paiementsCartesBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PAIEMENTS_CARTES, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(retraitsCartesBancairesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.RETRAITS_CARTES, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(prelevementsLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.PRELEVEMENTS, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(operationsDiversesLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.OPERATIONS_DIVERSES, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testDebitParsingResult(content, expectedBigAmount, Month.DEC);

		content=analyzer.parseLine(operationsDepotLine, testOrigin);
		assertNull(content);
		assertEquals(PdfToken.OPERATIONS_DEPOT, analyzer.getLastToken());
		content=analyzer.parseLine(validLineWithBigAmount, testOrigin);
		testCreditParsingResult(content, expectedBigAmount, Month.DEC);
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
		Operation operation=content.getOperation();
		assertTrue(operation instanceof Credit);
		assertNull(operation.getOperationTitle());
		assertEquals(String.valueOf(expectedAmount), String.valueOf(operation.getTotalAmount()));
		Date date=operation.getDate();
		assertNotNull(date);
		assertEquals(12, date.getDay());
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
		Operation operation=content.getOperation();
		assertTrue(operation instanceof Debit);
		assertNull(operation.getOperationTitle());
		assertEquals(String.valueOf(expectedAmount), String.valueOf(operation.getTotalAmount()));
		Date date=operation.getDate();
		assertNotNull(date);
		assertEquals(12, date.getDay());
		assertEquals(expectedMonth, date.getMonth());
		assertEquals(2014, date.getYear());
		assertNull(content.getLinkedOperationTitle());
		assertNull(content.getLinkedCategory());
	}
}
