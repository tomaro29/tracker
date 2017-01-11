/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2016, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.pdf.utils.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.utils.LineContent;
import fr.rostren.tracker.pdf.utils.LineContent.OperationType;
import fr.rostren.tracker.tests.TestUtils;

public class LineContentTest {

	private static final String TEST_MODEL_PATH="input/models/testModel.tracker"; //$NON-NLS-1$
	private static final String EMPTY_MODEL_PATH="input/models/emptyModel.tracker"; //$NON-NLS-1$
	private static final String INCOMPLETE_MODEL_PATH="input/models/incompleteModel.tracker"; //$NON-NLS-1$

	private final Date testDate=TrackerFactory.eINSTANCE.createDate();
	private final String testTitle="any title1"; //$NON-NLS-1$
	private final String testTitle2="any title2"; //$NON-NLS-1$
	private final String existingCreditTitle="VIR SEPA OBNA "; //$NON-NLS-1$
	private final String existingDebitTitle="VIR LIB "; //$NON-NLS-1$
	private final String existingCategory="UNDEFINED INCOME"; //$NON-NLS-1$
	private final String ECHPRETTitle="ECH PRET any other string"; //$NON-NLS-1$
	private final String INTERETSTitle="INTERETS CREDITEURS any other string"; //$NON-NLS-1$
	private final String POSTETitle="CB LA POSTE any other string"; //$NON-NLS-1$
	private final String CHEQUETitle="CHEQUE any other string"; //$NON-NLS-1$
	private final BigDecimal testAmount=new BigDecimal(20.0);
	private final BigDecimal zeroAmount=new BigDecimal(0.0);
	private final OperationType creditType=OperationType.CREDIT;
	private final OperationType debitType=OperationType.DEBIT;
	private final Origin testOrigin=TrackerFactory.eINSTANCE.createOrigin();
	private LineContent lineContent;

	/**
	 * Tests the constructor with null date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullDateTest() {
		new LineContent(null, testTitle, testAmount, creditType, testOrigin);
	}

	/**
	 * Tests the constructor with null title.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullTitleTest() {
		new LineContent(testDate, null, testAmount, creditType, testOrigin);
	}

	/**
	 * Tests the constructor with blank title.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_BlankTitleTest() {
		new LineContent(testDate, " ", testAmount, creditType, testOrigin); //$NON-NLS-1$
	}

	/**
	 * Tests the constructor with null amount.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullAmountTest() {
		new LineContent(testDate, testTitle, null, creditType, testOrigin);
	}

	/**
	 * Tests the constructor with zero amount.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_ZeroAmountTest() {
		new LineContent(testDate, testTitle, zeroAmount, creditType, testOrigin);
	}

	/**
	 * Tests the constructor with null type.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullTypeTest() {
		new LineContent(testDate, testTitle, testAmount, null, testOrigin);
	}

	/**
	 * Tests the constructor with null origin.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void constructor_NullOriginTest() {
		new LineContent(testDate, testTitle, testAmount, creditType, null);
	}

	/**
	 * Tests the credit and debit object creation.
	 * Tests the title formatting string.
	 */
	@Test
	public void lineContentTest() {
		lineContent=new LineContent(testDate, testTitle, testAmount, creditType, testOrigin);
		assertNotNull(lineContent);
		assertEquals(testDate, lineContent.getOperation().getDate());
		assertEquals(testTitle, lineContent.getTitle());
		assertEquals(testAmount, lineContent.getOperation().getTotalAmount());
		assertEquals(testOrigin, lineContent.getOperation().getOrigin());
		assertTrue(lineContent.getOperation() instanceof Credit);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent=new LineContent(testDate, testTitle, testAmount, debitType, testOrigin);
		assertNotNull(lineContent);
		assertEquals(testDate, lineContent.getOperation().getDate());
		assertEquals(testTitle, lineContent.getTitle());
		assertEquals(testAmount, lineContent.getOperation().getTotalAmount());
		assertEquals(testOrigin, lineContent.getOperation().getOrigin());
		assertTrue(lineContent.getOperation() instanceof Debit);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent=new LineContent(testDate, ECHPRETTitle, testAmount, creditType, testOrigin);
		assertEquals("ECH PRET", lineContent.getTitle()); //$NON-NLS-1$

		lineContent=new LineContent(testDate, INTERETSTitle, testAmount, creditType, testOrigin);
		assertEquals("INTERETS CREDITEURS", lineContent.getTitle()); //$NON-NLS-1$

		lineContent=new LineContent(testDate, POSTETitle, testAmount, creditType, testOrigin);
		assertEquals("CB LA POSTE", lineContent.getTitle()); //$NON-NLS-1$

		lineContent=new LineContent(testDate, CHEQUETitle, testAmount, creditType, testOrigin);
		assertEquals("CHEQUE", lineContent.getTitle()); //$NON-NLS-1$
	}

	/**
	 * Tests the complete operation method.
	 * @throws IOException an {@link IOException}
	 */
	@Test
	public void completeOperationTest() throws IOException {
		/*Test using an empty model*/
		EObject eObject=TestUtils.load(LineContentTest.EMPTY_MODEL_PATH);
		assertTrue(eObject instanceof Tracker);

		lineContent=new LineContent(testDate, testTitle, testAmount, creditType, testOrigin);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(testTitle, lineContent.getLinkedOperationTitle().getTitle());

		lineContent=new LineContent(testDate, existingCreditTitle, testAmount, creditType, testOrigin);

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertTrue(lineContent.getLinkedCategory() instanceof IncomeCategory);
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(existingCreditTitle, lineContent.getLinkedOperationTitle().getTitle());

		lineContent=new LineContent(testDate, existingDebitTitle, testAmount, debitType, testOrigin);

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertTrue(lineContent.getLinkedCategory() instanceof SpendingCategory);

		/*Test using an incomplete model*/
		eObject=TestUtils.load(LineContentTest.INCOMPLETE_MODEL_PATH);
		assertTrue(eObject instanceof Tracker);

		lineContent=new LineContent(testDate, testTitle, testAmount, creditType, testOrigin);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(testTitle, lineContent.getLinkedOperationTitle().getTitle());

		lineContent=new LineContent(testDate, existingCreditTitle, testAmount, creditType, testOrigin);

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertTrue(lineContent.getLinkedCategory() instanceof IncomeCategory);
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(existingCreditTitle, lineContent.getLinkedOperationTitle().getTitle());

		/*Test using a non empty model*/
		eObject=TestUtils.load(LineContentTest.TEST_MODEL_PATH);
		assertTrue(eObject instanceof Tracker);

		lineContent=new LineContent(testDate, testTitle, testAmount, creditType, testOrigin);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(testTitle, lineContent.getLinkedOperationTitle().getTitle());

		lineContent=new LineContent(testDate, existingCreditTitle, testAmount, creditType, testOrigin);

		lineContent.completeOperation((Tracker)eObject);
		assertNotNull(lineContent.getLinkedCategory());
		assertEquals(existingCategory, lineContent.getLinkedCategory().getTitle());
		assertNotNull(lineContent.getLinkedOperationTitle());
		assertEquals(existingCreditTitle, lineContent.getLinkedOperationTitle().getTitle());
	}

	/**
	 * Tests the complete operation method using a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void completeOperation_NullArgTest() {
		lineContent=new LineContent(testDate, testTitle, testAmount, creditType, testOrigin);
		assertNull(lineContent.getLinkedCategory());
		assertNull(lineContent.getLinkedOperationTitle());

		lineContent.completeOperation(null);
	}
}
