/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.impl;

import java.time.LocalDate;
import java.time.Month;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import fr.rostren.tracker.Account;
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
import fr.rostren.tracker.Title;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.util.TrackerValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class TrackerPackageImpl extends EPackageImpl implements TrackerPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ownerEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass accountEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkingAccountEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boockletAccountEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass creditEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass debitEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transferEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass incomingEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outgoingEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass titleEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoryEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationTitleEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass amountEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoryServiceEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass accountServiceEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationServiceEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoriesRepositoryEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass originEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass originsRepositoryEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackerEClass=null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackerServiceEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationsTitleRepositoryEClass=null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass incomeCategoryEClass=null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spendingCategoryEClass=null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum originTypeEEnum=null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dateEDataType=null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType monthEDataType=null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.rostren.tracker.TrackerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TrackerPackageImpl() {
		super(eNS_URI, TrackerFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited=false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 *
	 * <p>
	 * This method is used to initialize {@link TrackerPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TrackerPackage init() {
		if (isInited)
			return (TrackerPackage)EPackage.Registry.INSTANCE.getEPackage(TrackerPackage.eNS_URI);

		// Obtain or create and register package
		TrackerPackageImpl theTrackerPackage=(TrackerPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TrackerPackageImpl	? EPackage.Registry.INSTANCE.get(eNS_URI)
																																		: new TrackerPackageImpl());

		isInited=true;

		// Create package meta-data objects
		theTrackerPackage.createPackageContents();

		// Initialize created meta-data
		theTrackerPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theTrackerPackage, new EValidator.Descriptor() {
			@Override
			public EValidator getEValidator() {
				return TrackerValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theTrackerPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TrackerPackage.eNS_URI, theTrackerPackage);
		return theTrackerPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOwner() {
		return ownerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOwner_Accounts() {
		return (EReference)ownerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOwner_FirstName() {
		return (EAttribute)ownerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOwner_LastName() {
		return (EAttribute)ownerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAccount() {
		return accountEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAccount_Name() {
		return (EAttribute)accountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAccount_Amount() {
		return (EAttribute)accountEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAccount_Identifier() {
		return (EAttribute)accountEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCheckingAccount() {
		return checkingAccountEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCheckingAccount_Operations() {
		return (EReference)checkingAccountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBoockletAccount() {
		return boockletAccountEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBoockletAccount_Transfers() {
		return (EReference)boockletAccountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_Date() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_Origin() {
		return (EReference)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCredit() {
		return creditEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDebit() {
		return debitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_TotalAmount() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_OperationTitle() {
		return (EReference)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_SubAmounts() {
		return (EReference)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransfer() {
		return transferEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransfer_IncomingAccount() {
		return (EReference)transferEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransfer_OutgoingAccount() {
		return (EReference)transferEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIncoming() {
		return incomingEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOutgoing() {
		return outgoingEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTitle() {
		return titleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTitle_Title() {
		return (EAttribute)titleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCategory() {
		return categoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCategory_OperationTitles() {
		return (EReference)categoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCategory_Description() {
		return (EAttribute)categoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationTitle() {
		return operationTitleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationTitle_Categories() {
		return (EReference)operationTitleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAmount() {
		return amountEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAmount_Category() {
		return (EReference)amountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAmount_Value() {
		return (EAttribute)amountEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAmount_WishedDate() {
		return (EAttribute)amountEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCategoryService() {
		return categoryServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCategoryService_Category() {
		return (EReference)categoryServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAccountService() {
		return accountServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAccountService_Account() {
		return (EReference)accountServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationService() {
		return operationServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationService_Operation() {
		return (EReference)operationServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCategoriesRepository() {
		return categoriesRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCategoriesRepository_Income() {
		return (EReference)categoriesRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCategoriesRepository_Spending() {
		return (EReference)categoriesRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDate() {
		return dateEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrigin() {
		return originEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOrigin_Type() {
		return (EAttribute)originEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOrigin_Identifier() {
		return (EAttribute)originEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrigin_Operations() {
		return (EReference)originEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOriginsRepository() {
		return originsRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOriginsRepository_Origins() {
		return (EReference)originsRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTracker() {
		return trackerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTracker_Owners() {
		return (EReference)trackerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTracker_OriginsRepository() {
		return (EReference)trackerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTracker_CategoriesRepository() {
		return (EReference)trackerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTracker_OperationsTitlesRepositories() {
		return (EReference)trackerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTrackerService() {
		return trackerServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrackerService_Tracker() {
		return (EReference)trackerServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationsTitleRepository() {
		return operationsTitleRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationsTitleRepository_OperationsTitles() {
		return (EReference)operationsTitleRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIncomeCategory() {
		return incomeCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIncomeCategory_Incomes() {
		return (EReference)incomeCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpendingCategory() {
		return spendingCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpendingCategory_Spendings() {
		return (EReference)spendingCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getMonth() {
		return monthEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getOriginType() {
		return originTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TrackerFactory getTrackerFactory() {
		return (TrackerFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated=false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated=true;

		// Create classes and their features
		ownerEClass=createEClass(OWNER);
		createEReference(ownerEClass, OWNER__ACCOUNTS);
		createEAttribute(ownerEClass, OWNER__FIRST_NAME);
		createEAttribute(ownerEClass, OWNER__LAST_NAME);

		accountEClass=createEClass(ACCOUNT);
		createEAttribute(accountEClass, ACCOUNT__NAME);
		createEAttribute(accountEClass, ACCOUNT__AMOUNT);
		createEAttribute(accountEClass, ACCOUNT__IDENTIFIER);

		accountServiceEClass=createEClass(ACCOUNT_SERVICE);
		createEReference(accountServiceEClass, ACCOUNT_SERVICE__ACCOUNT);

		checkingAccountEClass=createEClass(CHECKING_ACCOUNT);
		createEReference(checkingAccountEClass, CHECKING_ACCOUNT__OPERATIONS);

		boockletAccountEClass=createEClass(BOOCKLET_ACCOUNT);
		createEReference(boockletAccountEClass, BOOCKLET_ACCOUNT__TRANSFERS);

		operationEClass=createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__TOTAL_AMOUNT);
		createEReference(operationEClass, OPERATION__OPERATION_TITLE);
		createEReference(operationEClass, OPERATION__SUB_AMOUNTS);
		createEReference(operationEClass, OPERATION__ORIGIN);
		createEAttribute(operationEClass, OPERATION__DATE);

		operationServiceEClass=createEClass(OPERATION_SERVICE);
		createEReference(operationServiceEClass, OPERATION_SERVICE__OPERATION);

		creditEClass=createEClass(CREDIT);

		debitEClass=createEClass(DEBIT);

		transferEClass=createEClass(TRANSFER);
		createEReference(transferEClass, TRANSFER__INCOMING_ACCOUNT);
		createEReference(transferEClass, TRANSFER__OUTGOING_ACCOUNT);

		incomingEClass=createEClass(INCOMING);

		outgoingEClass=createEClass(OUTGOING);

		titleEClass=createEClass(TITLE);
		createEAttribute(titleEClass, TITLE__TITLE);

		categoryEClass=createEClass(CATEGORY);
		createEReference(categoryEClass, CATEGORY__OPERATION_TITLES);
		createEAttribute(categoryEClass, CATEGORY__DESCRIPTION);

		categoryServiceEClass=createEClass(CATEGORY_SERVICE);
		createEReference(categoryServiceEClass, CATEGORY_SERVICE__CATEGORY);

		operationTitleEClass=createEClass(OPERATION_TITLE);
		createEReference(operationTitleEClass, OPERATION_TITLE__CATEGORIES);

		amountEClass=createEClass(AMOUNT);
		createEReference(amountEClass, AMOUNT__CATEGORY);
		createEAttribute(amountEClass, AMOUNT__VALUE);
		createEAttribute(amountEClass, AMOUNT__WISHED_DATE);

		categoriesRepositoryEClass=createEClass(CATEGORIES_REPOSITORY);
		createEReference(categoriesRepositoryEClass, CATEGORIES_REPOSITORY__INCOME);
		createEReference(categoriesRepositoryEClass, CATEGORIES_REPOSITORY__SPENDING);

		originEClass=createEClass(ORIGIN);
		createEAttribute(originEClass, ORIGIN__TYPE);
		createEAttribute(originEClass, ORIGIN__IDENTIFIER);
		createEReference(originEClass, ORIGIN__OPERATIONS);

		originsRepositoryEClass=createEClass(ORIGINS_REPOSITORY);
		createEReference(originsRepositoryEClass, ORIGINS_REPOSITORY__ORIGINS);

		trackerEClass=createEClass(TRACKER);
		createEReference(trackerEClass, TRACKER__OWNERS);
		createEReference(trackerEClass, TRACKER__ORIGINS_REPOSITORY);
		createEReference(trackerEClass, TRACKER__CATEGORIES_REPOSITORY);
		createEReference(trackerEClass, TRACKER__OPERATIONS_TITLES_REPOSITORIES);

		trackerServiceEClass=createEClass(TRACKER_SERVICE);
		createEReference(trackerServiceEClass, TRACKER_SERVICE__TRACKER);

		operationsTitleRepositoryEClass=createEClass(OPERATIONS_TITLE_REPOSITORY);
		createEReference(operationsTitleRepositoryEClass, OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES);

		incomeCategoryEClass=createEClass(INCOME_CATEGORY);
		createEReference(incomeCategoryEClass, INCOME_CATEGORY__INCOMES);

		spendingCategoryEClass=createEClass(SPENDING_CATEGORY);
		createEReference(spendingCategoryEClass, SPENDING_CATEGORY__SPENDINGS);

		// Create enums
		originTypeEEnum=createEEnum(ORIGIN_TYPE);

		// Create data types
		dateEDataType=createEDataType(DATE);
		monthEDataType=createEDataType(MONTH);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized=false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized=true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		checkingAccountEClass.getESuperTypes().add(this.getAccount());
		boockletAccountEClass.getESuperTypes().add(this.getAccount());
		creditEClass.getESuperTypes().add(this.getOperation());
		debitEClass.getESuperTypes().add(this.getOperation());
		transferEClass.getESuperTypes().add(this.getOperation());
		incomingEClass.getESuperTypes().add(this.getTransfer());
		outgoingEClass.getESuperTypes().add(this.getTransfer());
		categoryEClass.getESuperTypes().add(this.getTitle());
		operationTitleEClass.getESuperTypes().add(this.getTitle());
		incomeCategoryEClass.getESuperTypes().add(this.getCategory());
		spendingCategoryEClass.getESuperTypes().add(this.getCategory());

		// Initialize classes and features; add operations and parameters
		initEClass(ownerEClass, Owner.class, "Owner", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOwner_Accounts(), this.getAccount(), null, "accounts", null, 1, -1, Owner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOwner_FirstName(), ecorePackage.getEString(), "firstName", null, 0, 1, Owner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOwner_LastName(), ecorePackage.getEString(), "lastName", null, 0, 1, Owner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(accountEClass, Account.class, "Account", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAccount_Name(), ecorePackage.getEString(), "name", null, 0, 1, Account.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAccount_Amount(), ecorePackage.getEDouble(), "amount", null, 0, 1, Account.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAccount_Identifier(), ecorePackage.getEInt(), "identifier", null, 0, 1, Account.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(accountServiceEClass, AccountService.class, "AccountService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAccountService_Account(), this.getAccount(), null, "account", null, 1, 1, AccountService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "sumPerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getMonth(), "month", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "averagePerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getMonth(), "month", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "sumPerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "averagePerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "sumPerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDouble(), "averagePerCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDoubleObject(), "findIncomeCategoryAmounts", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "item", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "months", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, ecorePackage.getEDoubleObject(), "findSpendingCategoryAmounts", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "item", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "months", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(accountServiceEClass, this.getAmount(), "findCategoryAmounts", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getMonth(), "month", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "wishedDated", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(checkingAccountEClass, CheckingAccount.class, "CheckingAccount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCheckingAccount_Operations(), this.getOperation(), null, "operations", null, 0, -1, CheckingAccount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(boockletAccountEClass, BoockletAccount.class, "BoockletAccount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBoockletAccount_Transfers(), this.getTransfer(), null, "transfers", null, 0, -1, BoockletAccount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_TotalAmount(), ecorePackage.getEDouble(), "totalAmount", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_OperationTitle(), this.getOperationTitle(), null, "operationTitle", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_SubAmounts(), this.getAmount(), null, "subAmounts", null, 1, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_Origin(), this.getOrigin(), this.getOrigin_Operations(), "origin", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Date(), this.getDate(), "date", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(operationServiceEClass, OperationService.class, "OperationService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationService_Operation(), this.getOperation(), null, "operation", null, 1, 1, OperationService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op=addEOperation(operationServiceEClass, ecorePackage.getEDouble(), "sumAmounts", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOperation(), "operation", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(operationServiceEClass, null, "addSubAmount", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "amount", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(operationServiceEClass, null, "addSubAmount", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "amount", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(operationServiceEClass, null, "removeSubAmount", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAmount(), "amount", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(operationServiceEClass, ecorePackage.getEBoolean(), "validateAmounts", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(operationServiceEClass, ecorePackage.getEString(), "getOperationTitleAsString", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(operationServiceEClass, ecorePackage.getEString(), "getOperationTotalAmount", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(creditEClass, Credit.class, "Credit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(debitEClass, Debit.class, "Debit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transferEClass, Transfer.class, "Transfer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransfer_IncomingAccount(), this.getAccount(), null, "incomingAccount", null, 0, 1, Transfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransfer_OutgoingAccount(), this.getAccount(), null, "outgoingAccount", null, 0, 1, Transfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incomingEClass, Incoming.class, "Incoming", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outgoingEClass, Outgoing.class, "Outgoing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(titleEClass, Title.class, "Title", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTitle_Title(), ecorePackage.getEString(), "title", null, 0, 1, Title.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategory_OperationTitles(), this.getOperationTitle(), this.getOperationTitle_Categories(), "operationTitles", null, 0, -1, Category.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCategory_Description(), ecorePackage.getEString(), "description", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoryServiceEClass, CategoryService.class, "CategoryService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategoryService_Category(), this.getCategory(), null, "category", null, 1, 1, CategoryService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, null, "addDescription", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "description", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryServiceEClass, null, "removeDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, null, "addOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, null, "removeOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, null, "removeOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOperationTitle(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryServiceEClass, this.getCategory(), "getCategories", 0, -1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, this.getCategory(), "findCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(categoryServiceEClass, ecorePackage.getEBoolean(), "isTitleUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryServiceEClass, ecorePackage.getEBoolean(), "isUndefinedCategory", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(operationTitleEClass, OperationTitle.class, "OperationTitle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationTitle_Categories(), this.getCategory(), this.getCategory_OperationTitles(), "categories", null, 0, -1, OperationTitle.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(amountEClass, Amount.class, "Amount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAmount_Category(), this.getCategory(), null, "category", null, 1, 1, Amount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAmount_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, Amount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAmount_WishedDate(), this.getDate(), "wishedDate", null, 0, 1, Amount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoriesRepositoryEClass, CategoriesRepository.class, "CategoriesRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategoriesRepository_Income(), this.getIncomeCategory(), null, "income", null, 1, 1, CategoriesRepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategoriesRepository_Spending(), this.getSpendingCategory(), null, "spending", null, 1, 1, CategoriesRepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(originEClass, Origin.class, "Origin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrigin_Type(), this.getOriginType(), "type", null, 0, 1, Origin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getOrigin_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Origin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOrigin_Operations(), this.getOperation(), this.getOperation_Origin(), "operations", null, 0, -1, Origin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(originsRepositoryEClass, OriginsRepository.class, "OriginsRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOriginsRepository_Origins(), this.getOrigin(), null, "origins", null, 0, -1, OriginsRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackerEClass, Tracker.class, "Tracker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTracker_Owners(), this.getOwner(), null, "owners", null, 0, -1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTracker_OriginsRepository(), this.getOriginsRepository(), null, "originsRepository", null, 1, 1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTracker_CategoriesRepository(), this.getCategoriesRepository(), null, "categoriesRepository", null, 1, 1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTracker_OperationsTitlesRepositories(), this.getOperationsTitleRepository(), null, "operationsTitlesRepositories", null, 1, 1, Tracker.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackerServiceEClass, TrackerService.class, "TrackerService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrackerService_Tracker(), this.getTracker(), null, "tracker", null, 1, 1, TrackerService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "addIncomeCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "addSpendingCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "addOwner", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "firstName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "lastName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOwner", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOwner(), "owner", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOwner", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "firstName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "lastName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "addOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOrigin(), "origin", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "addOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOperationTitle(), "operationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, null, "deleteOperationTitle", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getCategoriesRepository(), "getCategoriesRepository", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getCategory(), "getAllCategories", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getAccount(), "getAccounts", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getCategory(), "getCategories", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, ecorePackage.getEInt(), "findYears", 0, -1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, ecorePackage.getEBoolean(), "isOperationTitleUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, ecorePackage.getEBoolean(), "isCategoryTitleUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "title", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, ecorePackage.getEBoolean(), "isOriginIdentifierUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, ecorePackage.getEBoolean(), "isOwnerIdentifierUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "firstName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "lastName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, ecorePackage.getEBoolean(), "isAccountIdentifierUnique", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		op=addEOperation(trackerServiceEClass, this.getAccount(), "findAccount", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getOperationTitle(), "getOperationsTitles", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(trackerServiceEClass, this.getOrigin(), "getOrigins", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(operationsTitleRepositoryEClass, OperationsTitleRepository.class, "OperationsTitleRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationsTitleRepository_OperationsTitles(), this.getOperationTitle(), null, "operationsTitles", null, 0, -1, OperationsTitleRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incomeCategoryEClass, IncomeCategory.class, "IncomeCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncomeCategory_Incomes(), this.getIncomeCategory(), null, "incomes", null, 0, -1, IncomeCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spendingCategoryEClass, SpendingCategory.class, "SpendingCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpendingCategory_Spendings(), this.getSpendingCategory(), null, "spendings", null, 0, -1, SpendingCategory.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(originTypeEEnum, OriginType.class, "OriginType");
		addEEnumLiteral(originTypeEEnum, OriginType.PDF_FILE);
		addEEnumLiteral(originTypeEEnum, OriginType.MANUAL);

		// Initialize data types
		initEDataType(dateEDataType, LocalDate.class, "Date", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(monthEDataType, Month.class, "Month", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// isUnique
		createIsUniqueAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source="http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation(ownerEClass, source, new String[] {"constraints", "hasAccount hasFirstName hasLastName"});
		addAnnotation(accountEClass, source, new String[] {"constraints", "hasUniqueName hasName hasAmount hasIdentifier"});
		addAnnotation(checkingAccountEClass, source, new String[] {"constraints", "isLinkedToOperations"});
		addAnnotation(boockletAccountEClass, source, new String[] {"constraints", "isLinkedToTansfers"});
		addAnnotation(operationEClass, source, new String[] {"constraints", "hasAmount hasTitle hasSubAmount hasOrigin hasDate hasValidAmount"});
		addAnnotation(transferEClass, source, new String[] {"constraints", "isLinkedToAccount"});
		addAnnotation(titleEClass, source, new String[] {"constraints", "isNotEmpty isNotBlank isUnique "});
		addAnnotation(categoryEClass, source, new String[] {"constraints", "isDescribed hasTitles"});
		addAnnotation(operationTitleEClass, source, new String[] {"constraints", "isLinkedToCategories"});
		addAnnotation(amountEClass, source, new String[] {"constraints", "hasDate hasCategory hasValue"});
		addAnnotation(categoriesRepositoryEClass, source, new String[] {"constraints", "hasCategories"});
		addAnnotation(originEClass, source, new String[] {"constraints", "isTyped hasIdentifier isLinkedToOperations"});
		addAnnotation(originsRepositoryEClass, source, new String[] {"constraints", "hasOrigins"});
		addAnnotation(trackerEClass, source, new String[] {"constraints", "hasOwners hasOrigins hasCategories hasTitles"});
		addAnnotation(operationsTitleRepositoryEClass, source, new String[] {"constraints", "hasTitles"});
		addAnnotation(incomeCategoryEClass, source, new String[] {"constraints", "hasCategories"});
		addAnnotation(spendingCategoryEClass, source, new String[] {"constraints", "hasCategories"});
	}

	/**
	 * Initializes the annotations for <b>isUnique</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createIsUniqueAnnotations() {
		String source="isUnique";
		addAnnotation(getAccount_Identifier(), source, new String[] {});
		addAnnotation(getOrigin_Identifier(), source, new String[] {});
	}

} // TrackerPackageImpl
