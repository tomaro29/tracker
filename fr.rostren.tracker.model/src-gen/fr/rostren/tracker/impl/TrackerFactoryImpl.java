/**
 */
package fr.rostren.tracker.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class TrackerFactoryImpl extends EFactoryImpl implements TrackerFactory {
	private static OperationTitle defaultOperationTitle;

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static TrackerFactory init() {
		try {
			TrackerFactory theTrackerFactory=(TrackerFactory)EPackage.Registry.INSTANCE.getEFactory(TrackerPackage.eNS_URI);
			if (theTrackerFactory != null) {
				return theTrackerFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TrackerFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TrackerFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TrackerPackage.OWNER:
				return createOwner();
			case TrackerPackage.CHECKING_ACCOUNT:
				return createCheckingAccount();
			case TrackerPackage.BOOCKLET_ACCOUNT:
				return createBoockletAccount();
			case TrackerPackage.CREDIT:
				return createCredit();
			case TrackerPackage.DEBIT:
				return createDebit();
			case TrackerPackage.INCOMING:
				return createIncoming();
			case TrackerPackage.OUTGOING:
				return createOutgoing();
			case TrackerPackage.OPERATION_TITLE:
				return createOperationTitle();
			case TrackerPackage.AMOUNT:
				return createAmount();
			case TrackerPackage.CATEGORY_SERVICE:
				return createCategoryService();
			case TrackerPackage.ACCOUNT_SERVICE:
				return createAccountService();
			case TrackerPackage.OPERATION_SERVICE:
				return createOperationService();
			case TrackerPackage.CATEGORIES_REPOSITORY:
				return createCategoriesRepository();
			case TrackerPackage.ORIGIN:
				return createOrigin();
			case TrackerPackage.ORIGINS_REPOSITORY:
				return createOriginsRepository();
			case TrackerPackage.TRACKER:
				return createTracker();
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY:
				return createOperationsTitleRepository();
			case TrackerPackage.INCOME_CATEGORY:
				return createIncomeCategory();
			case TrackerPackage.SPENDING_CATEGORY:
				return createSpendingCategory();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TrackerPackage.ORIGIN_TYPE:
				return createOriginTypeFromString(eDataType, initialValue);
			case TrackerPackage.DATE:
				return createDateFromString(eDataType, initialValue);
			case TrackerPackage.MONTH:
				return createMonthFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TrackerPackage.ORIGIN_TYPE:
				return convertOriginTypeToString(eDataType, instanceValue);
			case TrackerPackage.DATE:
				return convertDateToString(eDataType, instanceValue);
			case TrackerPackage.MONTH:
				return convertMonthToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Owner createOwner() {
		OwnerImpl owner=new OwnerImpl();
		return owner;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CheckingAccount createCheckingAccount() {
		CheckingAccountImpl checkingAccount=new CheckingAccountImpl();
		return checkingAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoockletAccount createBoockletAccount() {
		BoockletAccountImpl boockletAccount=new BoockletAccountImpl();
		return boockletAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Credit createCredit() {
		CreditImpl credit=new CreditImpl();
		return credit;
	}

	@Override
	public Credit createCredit(EObject object) {
		Credit operation=TrackerFactory.eINSTANCE.createCredit();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Debit createDebit() {
		DebitImpl debit=new DebitImpl();
		return debit;
	}

	@Override
	public Debit createDebit(EObject object) {
		Debit operation=TrackerFactory.eINSTANCE.createDebit();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Incoming createIncoming() {
		IncomingImpl incoming=new IncomingImpl();
		return incoming;
	}

	@Override
	public Incoming createIncoming(EObject object) {
		Incoming operation=TrackerFactory.eINSTANCE.createIncoming();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Outgoing createOutgoing() {
		OutgoingImpl outgoing=new OutgoingImpl();
		return outgoing;
	}

	@Override
	public Outgoing createOutgoing(EObject object) {
		Outgoing operation=TrackerFactory.eINSTANCE.createOutgoing();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationTitle createOperationTitle() {
		OperationTitleImpl operationTitle=new OperationTitleImpl();
		return operationTitle;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Amount createAmount() {
		AmountImpl amount=new AmountImpl();
		return amount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CategoryService createCategoryService() {
		CategoryServiceImpl categoryService=new CategoryServiceImpl();
		return categoryService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AccountService createAccountService() {
		AccountServiceImpl accountService=new AccountServiceImpl();
		return accountService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationService createOperationService() {
		OperationServiceImpl operationService=new OperationServiceImpl();
		return operationService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CategoriesRepository createCategoriesRepository() {
		CategoriesRepositoryImpl categoriesRepository=new CategoriesRepositoryImpl();
		return categoriesRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Origin createOrigin() {
		OriginImpl origin=new OriginImpl();
		return origin;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OriginsRepository createOriginsRepository() {
		OriginsRepositoryImpl originsRepository=new OriginsRepositoryImpl();
		return originsRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tracker createTracker() {
		TrackerImpl tracker=new TrackerImpl();
		return tracker;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationsTitleRepository createOperationsTitleRepository() {
		OperationsTitleRepositoryImpl operationsTitleRepository=new OperationsTitleRepositoryImpl();
		return operationsTitleRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncomeCategory createIncomeCategory() {
		IncomeCategoryImpl incomeCategory=new IncomeCategoryImpl();
		return incomeCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpendingCategory createSpendingCategory() {
		SpendingCategoryImpl spendingCategory=new SpendingCategoryImpl();
		return spendingCategory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Month createMonthFromString(EDataType eDataType, String initialValue) {
		return (Month)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMonthToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OriginType createOriginTypeFromString(EDataType eDataType, String initialValue) {
		OriginType result=OriginType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOriginTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LocalDate createDateFromString(EDataType eDataType, String initialValue) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d/MM/yyyy");
		return initialValue == null ? LocalDate.now() : LocalDate.parse(initialValue, formatter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertDateToString(EDataType eDataType, Object instanceValue) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d/MM/yyyy");
		return ((LocalDate)instanceValue).format(formatter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TrackerPackage getTrackerPackage() {
		return (TrackerPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TrackerPackage getPackage() {
		return TrackerPackage.eINSTANCE;
	}

	@Override
	public Amount createAmount(Operation operation, double amount, Category category) {
		Amount amountObject=createAmount();
		amountObject.setValue(amount);
		amountObject.setCategory(category);
		amountObject.setWishedDate(LocalDate.of(operation.getDate().getYear(), operation.getDate().getMonth(), operation.getDate().getDayOfMonth()));
		return amountObject;
	}

	@Override
	public Amount createAmount(OperationData operation, double amount, Category category) {
		Amount amountObject=createAmount();
		amountObject.setValue(amount);
		amountObject.setCategory(category);
		amountObject.setWishedDate(LocalDate.of(operation.getDate().getYear(), operation.getDate().getMonth(), operation.getDate().getDayOfMonth()));
		return amountObject;
	}

	@Override
	public Origin createOrigin(String identifier, OriginType type) {
		Origin origin=createOrigin();
		origin.setIdentifier(identifier);
		origin.setType(type);
		return origin;
	}

	@Override
	public OperationTitle createOperationTitle(Tracker tracker, String title) {
		OperationTitle newTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		newTitle.setTitle(title);
		tracker.getOperationsTitlesRepositories().getOperationsTitles().add(newTitle);
		return newTitle;
	}

	@Override
	public Category createCategory(Tracker tracker, OperationTitle title, OperationType type) {
		CategoriesRepository categoriesRepository=TrackerUtils.getTrackerService(tracker).getCategoriesRepository();
		if (OperationType.CREDIT.equals(type)) {
			Category undefined=getUndefinedIncomeCategory(categoriesRepository);
			List<IncomeCategory> incomes=categoriesRepository.getIncome().getIncomes();
			if (!incomes.contains(undefined)) {
				incomes.add((IncomeCategory)undefined);
			}
			undefined.getOperationTitles().add(title);
			return undefined;
		}
		else if (OperationType.DEBIT.equals(type)) {
			Category undefined=getUndefinedSpendingCategory(categoriesRepository);
			List<SpendingCategory> spendings=categoriesRepository.getSpending().getSpendings();
			if (!spendings.contains(undefined)) {
				spendings.add((SpendingCategory)undefined);
			}
			undefined.getOperationTitles().add(title);
			return undefined;
		}
		return null;
	}

	@Override
	public IncomeCategory createCategory(IncomeCategory income) {
		IncomeCategory category=TrackerFactory.eINSTANCE.createIncomeCategory();
		category.setTitle(TrackerUtils.UNDEFINED_INCOME_TITLE);
		income.getIncomes().add(category);
		return category;
	}

	@Override
	public SpendingCategory createCategory(SpendingCategory spending) {
		SpendingCategory category=TrackerFactory.eINSTANCE.createSpendingCategory();
		category.setTitle(TrackerUtils.UNDEFINED_SPENDING_TITLE);
		spending.getSpendings().add(category);
		return category;
	}

	/**
	 * Returns the undefined income category
	 * @param repository the repository
	 * @return the undefined income category
	 */
	private Category getUndefinedIncomeCategory(CategoriesRepository repository) {
		IncomeCategory income=repository.getIncome();
		if (income == null) {
			income=TrackerFactory.eINSTANCE.createIncomeCategory();
			repository.setIncome(income);
		}

		Optional<IncomeCategory> findAny=income.getIncomes().stream()//
				.filter(category -> TrackerUtils.getCategoryService(category).isUndefinedCategory())//
				.findAny();
		return findAny.orElse(createCategory(income));
	}

	/**
	 * Returns the undefined spending category
	 * @param repository the repository
	 * @return the undefined spending category
	 */
	private Category getUndefinedSpendingCategory(CategoriesRepository repository) {
		SpendingCategory spending=repository.getSpending();
		if (spending == null) {
			spending=TrackerFactory.eINSTANCE.createSpendingCategory();
			repository.setSpending(spending);
		}
		return spending.getSpendings().stream()//
				.filter(category -> TrackerUtils.getCategoryService(category).isUndefinedCategory())//
				.findAny().orElse(createCategory(spending));
	}

	/**
	 * Adds an {@link OperationTitle} instance to the given operation
	 * @param object the given account
	 * @param operation the given operation
	 */
	private void addOperationTitle(EObject object, Operation operation) {
		EObject rootContainer=EcoreUtil.getRootContainer(object);
		if (!(rootContainer instanceof Tracker)) {
			return;
		}

		// Add a default operation title
		OperationsTitleRepository repository=((Tracker)rootContainer).getOperationsTitlesRepositories();
		if (repository == null) {
			return;
		}

		EList<OperationTitle> operationsTitles=repository.getOperationsTitles();
		if (!operationsTitles.isEmpty()) {
			for (OperationTitle operationTitle: operationsTitles) {
				if (operationTitle.getTitle() == null) {
					TrackerFactoryImpl.defaultOperationTitle=operationTitle;
				}
			}
		}
		operation.setOperationTitle(TrackerFactoryImpl.defaultOperationTitle);
	}

	@Override
	public TrackerService createTrackerService() {
		TrackerServiceImpl trackerService=new TrackerServiceImpl();
		return trackerService;
	}

} // TrackerFactoryImpl
