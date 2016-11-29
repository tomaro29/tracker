/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2016, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.pdf.utils.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

public class TrackerUtilsTest {

	private final OperationsTitleRepository operationTitlesRepository=TrackerFactory.eINSTANCE.createOperationsTitleRepository();
	private final OriginsRepository originsRepository=TrackerFactory.eINSTANCE.createOriginsRepository();
	private final CategoriesRepository categoriesRepository=TrackerFactory.eINSTANCE.createCategoriesRepository();
	private final CheckingAccount account=TrackerFactory.eINSTANCE.createCheckingAccount();
	private final Owner owner=TrackerFactory.eINSTANCE.createOwner();
	private final Tracker tracker=TrackerFactory.eINSTANCE.createTracker();
	private final Amount amount=TrackerFactory.eINSTANCE.createAmount();
	private final Category category=TrackerFactory.eINSTANCE.createCategory();
	private final Operation credit=TrackerFactory.eINSTANCE.createCredit();
	private final OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle();
	private final Origin origin=TrackerFactory.eINSTANCE.createOrigin();
	private final Date operationDate=TrackerFactory.eINSTANCE.createDate();
	private final String title="title"; //$NON-NLS-1$
	private final String originId="originId"; //$NON-NLS-1$

	/**
	 *Tests the {@link TrackerUtils#getCategoryTitle(fr.rostren.tracker.Amount)} method with a valid and invalid arguments.
	 */
	@Test
	public void getCategoryTitleTest() {
		/*Test with an amount with no category.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getCategoryTitle(amount));

		/*Test with an amount with no category title.*/
		amount.setCategory(category);
		assertNull(TrackerUtils.getCategoryTitle(amount));

		/*Test with an amount with category title.*/
		category.setTitle(title);
		amount.setCategory(category);
		String categoryTitle=TrackerUtils.getCategoryTitle(amount);
		assertNotNull(categoryTitle);
		assertEquals(title, categoryTitle);
	}

	/**
	 *Tests the {@link TrackerUtils#getCategoryTitle(fr.rostren.tracker.Amount)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCategoryTitle_NullAmountTest() {
		TrackerUtils.getCategoryTitle(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountValue(fr.rostren.tracker.Amount)} method with a valid and invalid arguments.
	 */
	@Test
	public void getAmountValueTest() {
		/*Test with an amount with no value.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getAmountValue(amount));

		/*Test with an amount with value.*/
		BigDecimal amountValue=new BigDecimal(20.0);
		amount.setValue(amountValue);
		String value=TrackerUtils.getAmountValue(amount);
		assertNotNull(value);
		assertEquals(amountValue.toString(), value);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountValue(fr.rostren.tracker.Amount)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountValue_NullAmountTest() {
		TrackerUtils.getAmountValue(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitleAsString(Operation)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleAsStringTest() {
		/*Test with an operation with no title.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTitleAsString(credit));

		/*Test with an operation with operation title without title.*/
		credit.setOperationTitle(operationTitle);
		assertNull(TrackerUtils.getOperationTitleAsString(credit));

		/*Test with an operation with operation title with title.*/
		operationTitle.setTitle(title);
		credit.setOperationTitle(operationTitle);
		String operationTitleAsString=TrackerUtils.getOperationTitleAsString(credit);
		assertNotNull(operationTitleAsString);
		assertEquals(title, operationTitleAsString);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitleAsString(Operation)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitleAsString_NullOperationTest() {
		TrackerUtils.getOperationTitleAsString(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTotalAmount(Operation)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTotalAmountTest() {
		/*Test with an operation with no total amount.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTotalAmount(credit));

		/*Test with an operation with no total amount value.*/
		credit.setTotalAmount(amount.getValue());
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTotalAmount(credit));

		/*Test with an operation with total amount value.*/
		BigDecimal amountValue=new BigDecimal(20.0);
		credit.setTotalAmount(amountValue);
		String operationTotalAmount=TrackerUtils.getOperationTotalAmount(credit);
		assertNotNull(operationTotalAmount);
		assertEquals(amountValue.toString(), operationTotalAmount);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTotalAmount(Operation)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTotalAmount_NullOperationTest() {
		TrackerUtils.getOperationTotalAmount(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationDate(Operation)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationDateTest() {
		/*Test with an operation with no date.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationDate(credit));

		/*Test with an operation with date without value.*/
		credit.setDate(operationDate);
		assertEquals("0/Jan/0", TrackerUtils.getOperationDate(credit)); //$NON-NLS-1$

		/*Test with an operation with date value.*/
		credit.setDate(operationDate);
		operationDate.setDay(1);
		operationDate.setMonth(Month.APR);
		operationDate.setYear(2001);
		String date=TrackerUtils.getOperationDate(credit);
		assertNotNull(date);
		assertEquals("1/Apr/2001", date); //$NON-NLS-1$
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationDate(Operation)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationDate_NullOperationTest() {
		TrackerUtils.getOperationDate(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getTracker(EObject)} method with a valid and invalid arguments.
	 */
	@Test
	public void getTrackerTest() {
		assertEquals(null, TrackerUtils.getTracker(credit));
		assertEquals(null, TrackerUtils.getTracker(operationTitle));
		assertEquals(null, TrackerUtils.getTracker(operationDate));
		assertEquals(null, TrackerUtils.getTracker(amount));
		assertEquals(null, TrackerUtils.getTracker(category));

		assertEquals(tracker, TrackerUtils.getTracker(tracker));

		tracker.setOperationsTitlesRepositories(operationTitlesRepository);
		operationTitlesRepository.getOperationsTitles().add(operationTitle);
		assertEquals(tracker, TrackerUtils.getTracker(operationTitle));
	}

	/**
	 *Tests the {@link TrackerUtils#getTracker(EObject)} method with a null argument.
	 */
	@Test
	public void getTracker_NullEObjectTest() {
		assertNull(TrackerUtils.getTracker(null));
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitle(Tracker, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleUsingTrackerTest() {
		assertEquals(null, TrackerUtils.getOperationTitle(tracker, null));
		assertEquals(null, TrackerUtils.getOperationTitle(tracker, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.getOperationTitle(tracker, " ")); //$NON-NLS-1$
		assertEquals(null, TrackerUtils.getOperationTitle(tracker, title));

		tracker.setOperationsTitlesRepositories(operationTitlesRepository);
		operationTitlesRepository.getOperationsTitles().add(operationTitle);
		assertEquals(null, TrackerUtils.getOperationTitle(tracker, title));

		operationTitle.setTitle(title);
		assertEquals(operationTitle, TrackerUtils.getOperationTitle(tracker, title));

		operationTitle.setTitle(title);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitle(Tracker, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitleUsingTracker_NullTrackerTest() {
		Tracker nullTracker=null;
		TrackerUtils.getOperationTitle(nullTracker, title);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitle(Operation, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleTest() {
		assertEquals(null, TrackerUtils.getOperationTitle(credit, null));
		assertEquals(null, TrackerUtils.getOperationTitle(credit, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.getOperationTitle(credit, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		tracker.setOperationsTitlesRepositories(operationTitlesRepository);
		operationTitlesRepository.getOperationsTitles().add(operationTitle);
		assertEquals(null, TrackerUtils.getOperationTitle(credit, title));

		operationTitle.setTitle(title);
		assertEquals(operationTitle, TrackerUtils.getOperationTitle(credit, title));
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitle(Operation, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitle_NullOperationTest() {
		Operation nullOperation=null;
		TrackerUtils.getOperationTitle(nullOperation, title);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitle(Operation, String)} method with a null tracker container of the operation.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitle_NullTrackerTest() {
		TrackerUtils.getOperationTitle(credit, title);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationOrigin(Operation, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationOriginTest() {
		assertEquals(null, TrackerUtils.getOperationOrigin(credit, null));
		assertEquals(null, TrackerUtils.getOperationOrigin(credit, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.getOperationOrigin(credit, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		tracker.setOriginsRepository(originsRepository);
		originsRepository.getOrigins().add(origin);
		assertEquals(null, TrackerUtils.getOperationOrigin(credit, originId));

		origin.setIdentifier(originId);
		assertEquals(origin, TrackerUtils.getOperationOrigin(credit, originId));
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationOrigin(Operation, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationOrigin_NullOperationTest() {
		Operation nullOperation=null;
		TrackerUtils.getOperationOrigin(nullOperation, originId);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationOrigin(Operation, String)} method with a null tracker container of the operation.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationOrigin_NullTrackerTest() {
		TrackerUtils.getOperationOrigin(credit, originId);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountCategory(Amount, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getAmountCategoryTest() {
		assertEquals(null, TrackerUtils.getAmountCategory(amount, null));
		assertEquals(null, TrackerUtils.getAmountCategory(amount, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.getAmountCategory(amount, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		credit.getSubAmounts().add(amount);
		tracker.setCategoriesRepository(categoriesRepository);
		categoriesRepository.getCategories().add(category);
		assertEquals(null, TrackerUtils.getAmountCategory(amount, title));

		category.setTitle(title);
		assertEquals(category, TrackerUtils.getAmountCategory(amount, title));
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountCategory(Amount, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountCategory_NullAmountTest() {
		Amount nullAmount=null;
		TrackerUtils.getAmountCategory(nullAmount, title);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountCategory(Amount, String)} method with a null tracker container of the amount.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountCategory_NullTrackerTest() {
		TrackerUtils.getAmountCategory(amount, title);
	}

	/**
	 *Tests the {@link TrackerUtils#isUndefinedCategory(Category)} method with a valid and invalid arguments.
	 */
	@Test
	public void isUndefinedCategoryTest() {
		assertFalse(TrackerUtils.isUndefinedCategory(category));

		/*Test with an amount with category title.*/
		category.setTitle(title);
		assertFalse(TrackerUtils.isUndefinedCategory(category));

		/*Test with an amount with UNDEFINED category title.*/
		category.setTitle(TrackerUtils.UNDEFINED_TITLE);
		assertTrue(TrackerUtils.isUndefinedCategory(category));
	}

	/**
	 *Tests the {@link TrackerUtils#isUndefinedCategory(Category)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isUndefinedCategory_NullCategoryTest() {
		TrackerUtils.isUndefinedCategory(null);
	}
}
