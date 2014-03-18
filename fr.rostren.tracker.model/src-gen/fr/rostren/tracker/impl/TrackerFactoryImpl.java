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

import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.CreditOperation;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.DebitOperation;
import fr.rostren.tracker.IncomingTransfer;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationTitleService;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.OutgoingTransfer;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TrackerFactoryImpl extends EFactoryImpl implements TrackerFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TrackerFactory init() {
		try {
			TrackerFactory theTrackerFactory = (TrackerFactory)EPackage.Registry.INSTANCE.getEFactory("http://fr.rostren.tracker/1.0"); 
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TrackerPackage.OWNER: return createOwner();
			case TrackerPackage.CHECKING_ACCOUNT: return createCheckingAccount();
			case TrackerPackage.BOOCKLET_ACCOUNT: return createBoockletAccount();
			case TrackerPackage.CREDIT_OPERATION: return createCreditOperation();
			case TrackerPackage.DEBIT_OPERATION: return createDebitOperation();
			case TrackerPackage.INCOMING_TRANSFER: return createIncomingTransfer();
			case TrackerPackage.OUTGOING_TRANSFER: return createOutgoingTransfer();
			case TrackerPackage.CATEGORY: return createCategory();
			case TrackerPackage.OPERATION_TITLE: return createOperationTitle();
			case TrackerPackage.AMOUNT: return createAmount();
			case TrackerPackage.OPERATION_TITLE_SERVICE: return createOperationTitleService();
			case TrackerPackage.CATEGORY_SERVICE: return createCategoryService();
			case TrackerPackage.ACCOUNT_SERVICE: return createAccountService();
			case TrackerPackage.OPERATION_SERVICE: return createOperationService();
			case TrackerPackage.CATEGORIES_REPOSITORY: return createCategoriesRepository();
			case TrackerPackage.DATE: return createDate();
			case TrackerPackage.ORIGIN: return createOrigin();
			case TrackerPackage.ORIGINS_REPOSITORY: return createOriginsRepository();
			case TrackerPackage.TRACKER: return createTracker();
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY: return createOperationsTitleRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Owner createOwner() {
		OwnerImpl owner = new OwnerImpl();
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckingAccount createCheckingAccount() {
		CheckingAccountImpl checkingAccount = new CheckingAccountImpl();
		return checkingAccount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoockletAccount createBoockletAccount() {
		BoockletAccountImpl boockletAccount = new BoockletAccountImpl();
		return boockletAccount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditOperation createCreditOperation() {
		CreditOperationImpl creditOperation = new CreditOperationImpl();
		return creditOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DebitOperation createDebitOperation() {
		DebitOperationImpl debitOperation = new DebitOperationImpl();
		return debitOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IncomingTransfer createIncomingTransfer() {
		IncomingTransferImpl incomingTransfer = new IncomingTransferImpl();
		return incomingTransfer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutgoingTransfer createOutgoingTransfer() {
		OutgoingTransferImpl outgoingTransfer = new OutgoingTransferImpl();
		return outgoingTransfer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category createCategory() {
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationTitle createOperationTitle() {
		OperationTitleImpl operationTitle = new OperationTitleImpl();
		return operationTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Amount createAmount() {
		AmountImpl amount = new AmountImpl();
		return amount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationTitleService createOperationTitleService() {
		OperationTitleServiceImpl operationTitleService = new OperationTitleServiceImpl();
		return operationTitleService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoryService createCategoryService() {
		CategoryServiceImpl categoryService = new CategoryServiceImpl();
		return categoryService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccountService createAccountService() {
		AccountServiceImpl accountService = new AccountServiceImpl();
		return accountService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationService createOperationService() {
		OperationServiceImpl operationService = new OperationServiceImpl();
		return operationService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoriesRepository createCategoriesRepository() {
		CategoriesRepositoryImpl categoriesRepository = new CategoriesRepositoryImpl();
		return categoriesRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date createDate() {
		DateImpl date = new DateImpl();
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Origin createOrigin() {
		OriginImpl origin = new OriginImpl();
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OriginsRepository createOriginsRepository() {
		OriginsRepositoryImpl originsRepository = new OriginsRepositoryImpl();
		return originsRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tracker createTracker() {
		TrackerImpl tracker = new TrackerImpl();
		return tracker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationsTitleRepository createOperationsTitleRepository() {
		OperationsTitleRepositoryImpl operationsTitleRepository = new OperationsTitleRepositoryImpl();
		return operationsTitleRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Month createMonthFromString(EDataType eDataType, String initialValue) {
		Month result = Month.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMonthToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OriginType createOriginTypeFromString(EDataType eDataType, String initialValue) {
		OriginType result = OriginType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOriginTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerPackage getTrackerPackage() {
		return (TrackerPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TrackerPackage getPackage() {
		return TrackerPackage.eINSTANCE;
	}

} //TrackerFactoryImpl
