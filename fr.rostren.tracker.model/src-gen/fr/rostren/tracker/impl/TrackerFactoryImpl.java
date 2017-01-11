/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class TrackerFactoryImpl extends EFactoryImpl implements TrackerFactory {
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
			case TrackerPackage.OPERATION_TITLE_SERVICE:
				return createOperationTitleService();
			case TrackerPackage.CATEGORY_SERVICE:
				return createCategoryService();
			case TrackerPackage.ACCOUNT_SERVICE:
				return createAccountService();
			case TrackerPackage.OPERATION_SERVICE:
				return createOperationService();
			case TrackerPackage.CATEGORIES_REPOSITORY:
				return createCategoriesRepository();
			case TrackerPackage.DATE:
				return createDate();
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
			case TrackerPackage.MONTH:
				return createMonthFromString(eDataType, initialValue);
			case TrackerPackage.ORIGIN_TYPE:
				return createOriginTypeFromString(eDataType, initialValue);
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
			case TrackerPackage.MONTH:
				return convertMonthToString(eDataType, instanceValue);
			case TrackerPackage.ORIGIN_TYPE:
				return convertOriginTypeToString(eDataType, instanceValue);
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Debit createDebit() {
		DebitImpl debit=new DebitImpl();
		return debit;
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Outgoing createOutgoing() {
		OutgoingImpl outgoing=new OutgoingImpl();
		return outgoing;
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
	public OperationTitleService createOperationTitleService() {
		OperationTitleServiceImpl operationTitleService=new OperationTitleServiceImpl();
		return operationTitleService;
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
	public Date createDate() {
		DateImpl date=new DateImpl();
		return date;
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
		Month result=Month.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMonthToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OriginType createOriginTypeFromString(EDataType eDataType, String initialValue) {
		OriginType result=OriginType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
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

} // TrackerFactoryImpl
