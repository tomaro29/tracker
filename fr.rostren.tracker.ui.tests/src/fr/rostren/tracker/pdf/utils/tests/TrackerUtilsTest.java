package fr.rostren.tracker.pdf.utils.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
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
	private final IncomeCategory incomeCategory=TrackerFactory.eINSTANCE.createIncomeCategory();
	private final SpendingCategory spendingCategory=TrackerFactory.eINSTANCE.createSpendingCategory();
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
		assertEquals(StringUtils.EMPTY, TrackerUtils.getCategoryTitle(Optional.of(amount)));

		/*Test with an amount with no category title.*/
		amount.setCategory(incomeCategory);
		assertNull(TrackerUtils.getCategoryTitle(Optional.of(amount)));

		/*Test with an amount with category title.*/
		incomeCategory.setTitle(title);
		amount.setCategory(incomeCategory);
		String categoryTitle=TrackerUtils.getCategoryTitle(Optional.of(amount));
		assertNotNull(categoryTitle);
		assertEquals(title, categoryTitle);
	}

	/**
	 *Tests the {@link TrackerUtils#getCategoryTitle(fr.rostren.tracker.Amount)} method with a null argument.
	 */
	@Test(expected=NullPointerException.class)
	public void getCategoryTitle_NullAmountOptTest() {
		TrackerUtils.getCategoryTitle(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getCategoryTitle(fr.rostren.tracker.Amount)} method with a not existing argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCategoryTitle_NotExistingAmountOptTest() {
		TrackerUtils.getCategoryTitle(Optional.empty());
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountValue(fr.rostren.tracker.Amount)} method with a valid and invalid arguments.
	 */
	@Test
	public void getAmountValueTest() {
		/*Test with an amount with no value.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getAmountValue(Optional.of(amount)));

		/*Test with an amount with value.*/
		double amountValue=20.0;
		amount.setValue(amountValue);
		String value=TrackerUtils.getAmountValue(Optional.of(amount));
		assertNotNull(value);
		assertEquals(String.valueOf(amountValue), value);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountValue(fr.rostren.tracker.Amount)} method with a null argument.
	 */
	@Test(expected=NullPointerException.class)
	public void getAmountValue_NullAmountOptTest() {
		TrackerUtils.getAmountValue(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getAmountValue(fr.rostren.tracker.Amount)} method with a not existing argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountValue_NotExitingAmountOptTest() {
		TrackerUtils.getAmountValue(Optional.empty());
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitleAsString(Operation)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleAsStringTest() {
		/*Test with an operation with no title.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTitleAsString(Optional.of(credit)));

		/*Test with an operation with operation title without title.*/
		credit.setOperationTitle(operationTitle);
		assertNull(TrackerUtils.getOperationTitleAsString(Optional.of(credit)));

		/*Test with an operation with operation title with title.*/
		operationTitle.setTitle(title);
		credit.setOperationTitle(operationTitle);
		String operationTitleAsString=TrackerUtils.getOperationTitleAsString(Optional.of(credit));
		assertNotNull(operationTitleAsString);
		assertEquals(title, operationTitleAsString);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitleAsString(Operation)} method with a null argument.
	 */
	@Test(expected=NullPointerException.class)
	public void getOperationTitleAsString_NullOperationOptTest() {
		TrackerUtils.getOperationTitleAsString(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTitleAsString(Operation)} method with a not existing argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitleAsString_NotExistingOperationOptTest() {
		TrackerUtils.getOperationTitleAsString(Optional.empty());
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTotalAmount(Operation)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTotalAmountTest() {
		/*Test with an operation with no total amount.*/
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTotalAmount(Optional.of(credit)));

		/*Test with an operation with no total amount value.*/
		credit.setTotalAmount(amount.getValue());
		assertEquals(StringUtils.EMPTY, TrackerUtils.getOperationTotalAmount(Optional.of(credit)));

		/*Test with an operation with total amount value.*/
		double amountValue=20.0;
		credit.setTotalAmount(amountValue);
		String operationTotalAmount=TrackerUtils.getOperationTotalAmount(Optional.of(credit));
		assertNotNull(operationTotalAmount);
		assertEquals(String.valueOf(amountValue), operationTotalAmount);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTotalAmount(Operation)} method with a null argument.
	 */
	@Test(expected=NullPointerException.class)
	public void getOperationTotalAmount_NullOperationOptTest() {
		TrackerUtils.getOperationTotalAmount(null);
	}

	/**
	 *Tests the {@link TrackerUtils#getOperationTotalAmount(Operation)} method with a not existing argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTotalAmount_NotExistingOperationOptTest() {
		TrackerUtils.getOperationTotalAmount(Optional.empty());
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
		assertEquals(null, TrackerUtils.getTracker(incomeCategory));
		assertEquals(null, TrackerUtils.getTracker(spendingCategory));

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
	 *Tests the {@link TrackerUtils#findOperationTitle(Tracker, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleUsingTrackerTest() {
		assertEquals(null, TrackerUtils.findOperationTitle(tracker, null));
		assertEquals(null, TrackerUtils.findOperationTitle(tracker, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.findOperationTitle(tracker, " ")); //$NON-NLS-1$
		assertEquals(Optional.empty(), TrackerUtils.findOperationTitle(tracker, title));

		tracker.setOperationsTitlesRepositories(operationTitlesRepository);
		operationTitlesRepository.getOperationsTitles().add(operationTitle);
		assertEquals(Optional.empty(), TrackerUtils.findOperationTitle(tracker, title));

		operationTitle.setTitle(title);
		assertEquals(operationTitle, TrackerUtils.findOperationTitle(tracker, title).orElseThrow(IllegalArgumentException::new));

		operationTitle.setTitle(title);
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationTitle(Tracker, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitleUsingTracker_NullTrackerTest() {
		Tracker nullTracker=null;
		TrackerUtils.findOperationTitle(nullTracker, title);
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationTitle(Operation, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationTitleTest() {
		assertEquals(null, TrackerUtils.findOperationTitle(credit, null));
		assertEquals(null, TrackerUtils.findOperationTitle(credit, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.findOperationTitle(credit, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		tracker.setOperationsTitlesRepositories(operationTitlesRepository);
		operationTitlesRepository.getOperationsTitles().add(operationTitle);
		assertEquals(Optional.empty(), TrackerUtils.findOperationTitle(credit, title));

		operationTitle.setTitle(title);
		assertEquals(operationTitle, TrackerUtils.findOperationTitle(credit, title).orElseThrow(IllegalArgumentException::new));
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationTitle(Operation, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitle_NullOperationTest() {
		Operation nullOperation=null;
		TrackerUtils.findOperationTitle(nullOperation, title);
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationTitle(Operation, String)} method with a null tracker container of the operation.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationTitle_NullTrackerTest() {
		TrackerUtils.findOperationTitle(credit, title);
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationOrigin(Operation, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getOperationOriginTest() {
		assertEquals(null, TrackerUtils.findOperationOrigin(credit, null));
		assertEquals(null, TrackerUtils.findOperationOrigin(credit, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.findOperationOrigin(credit, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		tracker.setOriginsRepository(originsRepository);
		originsRepository.getOrigins().add(origin);
		assertEquals(Optional.empty(), TrackerUtils.findOperationOrigin(credit, originId));

		origin.setIdentifier(originId);
		assertEquals(origin, TrackerUtils.findOperationOrigin(credit, originId).orElseThrow(IllegalArgumentException::new));
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationOrigin(Operation, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationOrigin_NullOperationTest() {
		Operation nullOperation=null;
		TrackerUtils.findOperationOrigin(nullOperation, originId);
	}

	/**
	 *Tests the {@link TrackerUtils#findOperationOrigin(Operation, String)} method with a null tracker container of the operation.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getOperationOrigin_NullTrackerTest() {
		TrackerUtils.findOperationOrigin(credit, originId);
	}

	/**
	 *Tests the {@link TrackerUtils#findAmountCategory(Amount, String)} method with a valid and invalid arguments.
	 */
	@Test
	public void getAmountCategoryTest() {
		assertEquals(null, TrackerUtils.findAmountCategory(amount, null));
		assertEquals(null, TrackerUtils.findAmountCategory(amount, StringUtils.EMPTY));
		assertEquals(null, TrackerUtils.findAmountCategory(amount, " ")); //$NON-NLS-1$

		tracker.getOwners().add(owner);
		owner.getAccounts().add(account);
		account.getOperations().add(credit);
		credit.getSubAmounts().add(amount);
		tracker.setCategoriesRepository(categoriesRepository);
		IncomeCategory income=TrackerFactory.eINSTANCE.createIncomeCategory();
		categoriesRepository.setIncome(income);
		categoriesRepository.getIncome().getIncomes().add(incomeCategory);
		assertEquals(Optional.empty(), TrackerUtils.findAmountCategory(amount, title));

		incomeCategory.setTitle(title);
		assertEquals(incomeCategory, TrackerUtils.findAmountCategory(amount, title).orElseThrow(IllegalArgumentException::new));
	}

	/**
	 *Tests the {@link TrackerUtils#findAmountCategory(Amount, String)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountCategory_NullAmountTest() {
		Amount nullAmount=null;
		TrackerUtils.findAmountCategory(nullAmount, title);
	}

	/**
	 *Tests the {@link TrackerUtils#findAmountCategory(Amount, String)} method with a null tracker container of the amount.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getAmountCategory_NullTrackerTest() {
		TrackerUtils.findAmountCategory(amount, title);
	}

	/**
	 *Tests the {@link TrackerUtils#isUndefinedCategory(Category)} method with a valid and invalid arguments.
	 */
	@Test
	public void isUndefinedCategoryTest() {
		assertFalse(TrackerUtils.isUndefinedCategory(incomeCategory));

		/*Test with an amount with category title.*/
		incomeCategory.setTitle(title);
		assertFalse(TrackerUtils.isUndefinedCategory(incomeCategory));

		/*Test with an amount with UNDEFINED category title.*/
		incomeCategory.setTitle(TrackerUtils.UNDEFINED_INCOME_TITLE);
		assertTrue(TrackerUtils.isUndefinedCategory(incomeCategory));

		assertFalse(TrackerUtils.isUndefinedCategory(spendingCategory));

		/*Test with an amount with category title.*/
		spendingCategory.setTitle(title);
		assertFalse(TrackerUtils.isUndefinedCategory(spendingCategory));

		/*Test with an amount with UNDEFINED category title.*/
		spendingCategory.setTitle(TrackerUtils.UNDEFINED_SPENDING_TITLE);
		assertTrue(TrackerUtils.isUndefinedCategory(spendingCategory));
	}

	/**
	 *Tests the {@link TrackerUtils#isUndefinedCategory(Category)} method with a null argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isUndefinedCategory_NullCategoryTest() {
		TrackerUtils.isUndefinedCategory(null);
	}
}
