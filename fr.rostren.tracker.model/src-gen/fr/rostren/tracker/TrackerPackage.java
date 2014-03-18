/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.rostren.tracker.TrackerFactory
 * @model kind="package"
 * @generated
 */
public interface TrackerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tracker";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://fr.rostren.tracker/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracker";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TrackerPackage eINSTANCE = fr.rostren.tracker.impl.TrackerPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OwnerImpl <em>Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OwnerImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOwner()
	 * @generated
	 */
	int OWNER = 0;

	/**
	 * The feature id for the '<em><b>Accounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNER__ACCOUNTS = 0;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNER__FIRST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNER__LAST_NAME = 2;

	/**
	 * The number of structural features of the '<em>Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.AccountImpl <em>Account</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.AccountImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAccount()
	 * @generated
	 */
	int ACCOUNT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__AMOUNT = 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__IDENTIFIER = 2;

	/**
	 * The number of structural features of the '<em>Account</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.CheckingAccountImpl <em>Checking Account</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.CheckingAccountImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCheckingAccount()
	 * @generated
	 */
	int CHECKING_ACCOUNT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKING_ACCOUNT__NAME = ACCOUNT__NAME;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKING_ACCOUNT__AMOUNT = ACCOUNT__AMOUNT;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKING_ACCOUNT__IDENTIFIER = ACCOUNT__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKING_ACCOUNT__OPERATIONS = ACCOUNT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Checking Account</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKING_ACCOUNT_FEATURE_COUNT = ACCOUNT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.BoockletAccountImpl <em>Boocklet Account</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.BoockletAccountImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getBoockletAccount()
	 * @generated
	 */
	int BOOCKLET_ACCOUNT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOCKLET_ACCOUNT__NAME = ACCOUNT__NAME;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOCKLET_ACCOUNT__AMOUNT = ACCOUNT__AMOUNT;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOCKLET_ACCOUNT__IDENTIFIER = ACCOUNT__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Transfers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOCKLET_ACCOUNT__TRANSFERS = ACCOUNT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boocklet Account</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOCKLET_ACCOUNT_FEATURE_COUNT = ACCOUNT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.TitleImpl <em>Title</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.TitleImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTitle()
	 * @generated
	 */
	int TITLE = 10;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OperationImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TOTAL_AMOUNT = 0;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OPERATION_TITLE = 1;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__SUB_AMOUNTS = 2;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__DATE = 3;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ORIGIN = 4;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.CreditOperationImpl <em>Credit Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.CreditOperationImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCreditOperation()
	 * @generated
	 */
	int CREDIT_OPERATION = 5;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION__TOTAL_AMOUNT = OPERATION__TOTAL_AMOUNT;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION__OPERATION_TITLE = OPERATION__OPERATION_TITLE;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION__SUB_AMOUNTS = OPERATION__SUB_AMOUNTS;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION__DATE = OPERATION__DATE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION__ORIGIN = OPERATION__ORIGIN;

	/**
	 * The number of structural features of the '<em>Credit Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDIT_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.DebitOperationImpl <em>Debit Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.DebitOperationImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getDebitOperation()
	 * @generated
	 */
	int DEBIT_OPERATION = 6;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION__TOTAL_AMOUNT = OPERATION__TOTAL_AMOUNT;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION__OPERATION_TITLE = OPERATION__OPERATION_TITLE;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION__SUB_AMOUNTS = OPERATION__SUB_AMOUNTS;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION__DATE = OPERATION__DATE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION__ORIGIN = OPERATION__ORIGIN;

	/**
	 * The number of structural features of the '<em>Debit Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBIT_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.TransferImpl <em>Transfer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.TransferImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTransfer()
	 * @generated
	 */
	int TRANSFER = 7;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__TOTAL_AMOUNT = OPERATION__TOTAL_AMOUNT;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__OPERATION_TITLE = OPERATION__OPERATION_TITLE;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__SUB_AMOUNTS = OPERATION__SUB_AMOUNTS;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__DATE = OPERATION__DATE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__ORIGIN = OPERATION__ORIGIN;

	/**
	 * The feature id for the '<em><b>Incoming Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__INCOMING_ACCOUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__OUTGOING_ACCOUNT = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Transfer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.IncomingTransferImpl <em>Incoming Transfer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.IncomingTransferImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getIncomingTransfer()
	 * @generated
	 */
	int INCOMING_TRANSFER = 8;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__TOTAL_AMOUNT = TRANSFER__TOTAL_AMOUNT;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__OPERATION_TITLE = TRANSFER__OPERATION_TITLE;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__SUB_AMOUNTS = TRANSFER__SUB_AMOUNTS;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__DATE = TRANSFER__DATE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__ORIGIN = TRANSFER__ORIGIN;

	/**
	 * The feature id for the '<em><b>Incoming Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__INCOMING_ACCOUNT = TRANSFER__INCOMING_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Outgoing Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER__OUTGOING_ACCOUNT = TRANSFER__OUTGOING_ACCOUNT;

	/**
	 * The number of structural features of the '<em>Incoming Transfer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOMING_TRANSFER_FEATURE_COUNT = TRANSFER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OutgoingTransferImpl <em>Outgoing Transfer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OutgoingTransferImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOutgoingTransfer()
	 * @generated
	 */
	int OUTGOING_TRANSFER = 9;

	/**
	 * The feature id for the '<em><b>Total Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__TOTAL_AMOUNT = TRANSFER__TOTAL_AMOUNT;

	/**
	 * The feature id for the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__OPERATION_TITLE = TRANSFER__OPERATION_TITLE;

	/**
	 * The feature id for the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__SUB_AMOUNTS = TRANSFER__SUB_AMOUNTS;

	/**
	 * The feature id for the '<em><b>Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__DATE = TRANSFER__DATE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__ORIGIN = TRANSFER__ORIGIN;

	/**
	 * The feature id for the '<em><b>Incoming Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__INCOMING_ACCOUNT = TRANSFER__INCOMING_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Outgoing Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER__OUTGOING_ACCOUNT = TRANSFER__OUTGOING_ACCOUNT;

	/**
	 * The number of structural features of the '<em>Outgoing Transfer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTGOING_TRANSFER_FEATURE_COUNT = TRANSFER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLE__TITLE = 0;

	/**
	 * The number of structural features of the '<em>Title</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.CategoryImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 11;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TITLE = TITLE__TITLE;

	/**
	 * The feature id for the '<em><b>Operation Titles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__OPERATION_TITLES = TITLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DESCRIPTION = TITLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = TITLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OperationTitleImpl <em>Operation Title</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OperationTitleImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationTitle()
	 * @generated
	 */
	int OPERATION_TITLE = 12;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TITLE__TITLE = TITLE__TITLE;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TITLE__CATEGORIES = TITLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Title</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TITLE_FEATURE_COUNT = TITLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.AmountImpl <em>Amount</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.AmountImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAmount()
	 * @generated
	 */
	int AMOUNT = 13;

	/**
	 * The feature id for the '<em><b>Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMOUNT__CATEGORY = 0;

	/**
	 * The feature id for the '<em><b>Sub Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMOUNT__SUB_AMOUNT = 1;

	/**
	 * The number of structural features of the '<em>Amount</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMOUNT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OperationTitleServiceImpl <em>Operation Title Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OperationTitleServiceImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationTitleService()
	 * @generated
	 */
	int OPERATION_TITLE_SERVICE = 14;

	/**
	 * The number of structural features of the '<em>Operation Title Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TITLE_SERVICE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.CategoryServiceImpl <em>Category Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.CategoryServiceImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategoryService()
	 * @generated
	 */
	int CATEGORY_SERVICE = 15;

	/**
	 * The number of structural features of the '<em>Category Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SERVICE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.AccountServiceImpl <em>Account Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.AccountServiceImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAccountService()
	 * @generated
	 */
	int ACCOUNT_SERVICE = 16;

	/**
	 * The number of structural features of the '<em>Account Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT_SERVICE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OperationServiceImpl <em>Operation Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OperationServiceImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationService()
	 * @generated
	 */
	int OPERATION_SERVICE = 17;

	/**
	 * The number of structural features of the '<em>Operation Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_SERVICE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.CategoriesRepositoryImpl <em>Categories Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.CategoriesRepositoryImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategoriesRepository()
	 * @generated
	 */
	int CATEGORIES_REPOSITORY = 18;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORIES_REPOSITORY__CATEGORIES = 0;

	/**
	 * The number of structural features of the '<em>Categories Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORIES_REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.DateImpl <em>Date</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.DateImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 19;

	/**
	 * The feature id for the '<em><b>Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE__DAY = 0;

	/**
	 * The feature id for the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE__MONTH = 1;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE__YEAR = 2;

	/**
	 * The number of structural features of the '<em>Date</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OriginImpl <em>Origin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OriginImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOrigin()
	 * @generated
	 */
	int ORIGIN = 20;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGIN__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGIN__IDENTIFIER = 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGIN__OPERATIONS = 2;

	/**
	 * The number of structural features of the '<em>Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGIN_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OriginsRepositoryImpl <em>Origins Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OriginsRepositoryImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOriginsRepository()
	 * @generated
	 */
	int ORIGINS_REPOSITORY = 21;

	/**
	 * The feature id for the '<em><b>Origins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGINS_REPOSITORY__ORIGINS = 0;

	/**
	 * The number of structural features of the '<em>Origins Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORIGINS_REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.TrackerImpl <em>Tracker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.TrackerImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTracker()
	 * @generated
	 */
	int TRACKER = 22;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER__OWNERS = 0;

	/**
	 * The feature id for the '<em><b>Origins Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER__ORIGINS_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Categories Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER__CATEGORIES_REPOSITORY = 2;

	/**
	 * The feature id for the '<em><b>Operations Titles Repositories</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER__OPERATIONS_TITLES_REPOSITORIES = 3;

	/**
	 * The number of structural features of the '<em>Tracker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.impl.OperationsTitleRepositoryImpl <em>Operations Title Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.impl.OperationsTitleRepositoryImpl
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationsTitleRepository()
	 * @generated
	 */
	int OPERATIONS_TITLE_REPOSITORY = 23;

	/**
	 * The feature id for the '<em><b>Operations Titles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES = 0;

	/**
	 * The number of structural features of the '<em>Operations Title Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONS_TITLE_REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.rostren.tracker.Month <em>Month</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.Month
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getMonth()
	 * @generated
	 */
	int MONTH = 24;


	/**
	 * The meta object id for the '{@link fr.rostren.tracker.OriginType <em>Origin Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.rostren.tracker.OriginType
	 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOriginType()
	 * @generated
	 */
	int ORIGIN_TYPE = 25;


	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Owner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Owner</em>'.
	 * @see fr.rostren.tracker.Owner
	 * @generated
	 */
	EClass getOwner();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.Owner#getAccounts <em>Accounts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Accounts</em>'.
	 * @see fr.rostren.tracker.Owner#getAccounts()
	 * @see #getOwner()
	 * @generated
	 */
	EReference getOwner_Accounts();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Owner#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see fr.rostren.tracker.Owner#getFirstName()
	 * @see #getOwner()
	 * @generated
	 */
	EAttribute getOwner_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Owner#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see fr.rostren.tracker.Owner#getLastName()
	 * @see #getOwner()
	 * @generated
	 */
	EAttribute getOwner_LastName();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Account <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Account</em>'.
	 * @see fr.rostren.tracker.Account
	 * @generated
	 */
	EClass getAccount();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Account#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.rostren.tracker.Account#getName()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Name();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Account#getAmount <em>Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Amount</em>'.
	 * @see fr.rostren.tracker.Account#getAmount()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Amount();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Account#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see fr.rostren.tracker.Account#getIdentifier()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Identifier();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.CheckingAccount <em>Checking Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checking Account</em>'.
	 * @see fr.rostren.tracker.CheckingAccount
	 * @generated
	 */
	EClass getCheckingAccount();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.CheckingAccount#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see fr.rostren.tracker.CheckingAccount#getOperations()
	 * @see #getCheckingAccount()
	 * @generated
	 */
	EReference getCheckingAccount_Operations();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.BoockletAccount <em>Boocklet Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boocklet Account</em>'.
	 * @see fr.rostren.tracker.BoockletAccount
	 * @generated
	 */
	EClass getBoockletAccount();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.BoockletAccount#getTransfers <em>Transfers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transfers</em>'.
	 * @see fr.rostren.tracker.BoockletAccount#getTransfers()
	 * @see #getBoockletAccount()
	 * @generated
	 */
	EReference getBoockletAccount_Transfers();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see fr.rostren.tracker.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference '{@link fr.rostren.tracker.Operation#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Date</em>'.
	 * @see fr.rostren.tracker.Operation#getDate()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Date();

	/**
	 * Returns the meta object for the reference '{@link fr.rostren.tracker.Operation#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Origin</em>'.
	 * @see fr.rostren.tracker.Operation#getOrigin()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Origin();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Operation#getTotalAmount <em>Total Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Amount</em>'.
	 * @see fr.rostren.tracker.Operation#getTotalAmount()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_TotalAmount();

	/**
	 * Returns the meta object for the reference '{@link fr.rostren.tracker.Operation#getOperationTitle <em>Operation Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation Title</em>'.
	 * @see fr.rostren.tracker.Operation#getOperationTitle()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OperationTitle();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.Operation#getSubAmounts <em>Sub Amounts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Amounts</em>'.
	 * @see fr.rostren.tracker.Operation#getSubAmounts()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_SubAmounts();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.CreditOperation <em>Credit Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Credit Operation</em>'.
	 * @see fr.rostren.tracker.CreditOperation
	 * @generated
	 */
	EClass getCreditOperation();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.DebitOperation <em>Debit Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Debit Operation</em>'.
	 * @see fr.rostren.tracker.DebitOperation
	 * @generated
	 */
	EClass getDebitOperation();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Transfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transfer</em>'.
	 * @see fr.rostren.tracker.Transfer
	 * @generated
	 */
	EClass getTransfer();

	/**
	 * Returns the meta object for the reference '{@link fr.rostren.tracker.Transfer#getIncomingAccount <em>Incoming Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Incoming Account</em>'.
	 * @see fr.rostren.tracker.Transfer#getIncomingAccount()
	 * @see #getTransfer()
	 * @generated
	 */
	EReference getTransfer_IncomingAccount();

	/**
	 * Returns the meta object for the reference '{@link fr.rostren.tracker.Transfer#getOutgoingAccount <em>Outgoing Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Outgoing Account</em>'.
	 * @see fr.rostren.tracker.Transfer#getOutgoingAccount()
	 * @see #getTransfer()
	 * @generated
	 */
	EReference getTransfer_OutgoingAccount();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.IncomingTransfer <em>Incoming Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Incoming Transfer</em>'.
	 * @see fr.rostren.tracker.IncomingTransfer
	 * @generated
	 */
	EClass getIncomingTransfer();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OutgoingTransfer <em>Outgoing Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outgoing Transfer</em>'.
	 * @see fr.rostren.tracker.OutgoingTransfer
	 * @generated
	 */
	EClass getOutgoingTransfer();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Title <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Title</em>'.
	 * @see fr.rostren.tracker.Title
	 * @generated
	 */
	EClass getTitle();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Title#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see fr.rostren.tracker.Title#getTitle()
	 * @see #getTitle()
	 * @generated
	 */
	EAttribute getTitle_Title();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see fr.rostren.tracker.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the reference list '{@link fr.rostren.tracker.Category#getOperationTitles <em>Operation Titles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operation Titles</em>'.
	 * @see fr.rostren.tracker.Category#getOperationTitles()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_OperationTitles();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Category#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see fr.rostren.tracker.Category#getDescription()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_Description();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OperationTitle <em>Operation Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Title</em>'.
	 * @see fr.rostren.tracker.OperationTitle
	 * @generated
	 */
	EClass getOperationTitle();

	/**
	 * Returns the meta object for the reference list '{@link fr.rostren.tracker.OperationTitle#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Categories</em>'.
	 * @see fr.rostren.tracker.OperationTitle#getCategories()
	 * @see #getOperationTitle()
	 * @generated
	 */
	EReference getOperationTitle_Categories();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Amount <em>Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Amount</em>'.
	 * @see fr.rostren.tracker.Amount
	 * @generated
	 */
	EClass getAmount();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Amount#getSubAmount <em>Sub Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Amount</em>'.
	 * @see fr.rostren.tracker.Amount#getSubAmount()
	 * @see #getAmount()
	 * @generated
	 */
	EAttribute getAmount_SubAmount();

	/**
	 * Returns the meta object for the reference '{@link fr.rostren.tracker.Amount#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Category</em>'.
	 * @see fr.rostren.tracker.Amount#getCategory()
	 * @see #getAmount()
	 * @generated
	 */
	EReference getAmount_Category();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OperationTitleService <em>Operation Title Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Title Service</em>'.
	 * @see fr.rostren.tracker.OperationTitleService
	 * @generated
	 */
	EClass getOperationTitleService();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.CategoryService <em>Category Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category Service</em>'.
	 * @see fr.rostren.tracker.CategoryService
	 * @generated
	 */
	EClass getCategoryService();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.AccountService <em>Account Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Account Service</em>'.
	 * @see fr.rostren.tracker.AccountService
	 * @generated
	 */
	EClass getAccountService();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OperationService <em>Operation Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Service</em>'.
	 * @see fr.rostren.tracker.OperationService
	 * @generated
	 */
	EClass getOperationService();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.CategoriesRepository <em>Categories Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Categories Repository</em>'.
	 * @see fr.rostren.tracker.CategoriesRepository
	 * @generated
	 */
	EClass getCategoriesRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.CategoriesRepository#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see fr.rostren.tracker.CategoriesRepository#getCategories()
	 * @see #getCategoriesRepository()
	 * @generated
	 */
	EReference getCategoriesRepository_Categories();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date</em>'.
	 * @see fr.rostren.tracker.Date
	 * @generated
	 */
	EClass getDate();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Date#getDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day</em>'.
	 * @see fr.rostren.tracker.Date#getDay()
	 * @see #getDate()
	 * @generated
	 */
	EAttribute getDate_Day();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Date#getMonth <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Month</em>'.
	 * @see fr.rostren.tracker.Date#getMonth()
	 * @see #getDate()
	 * @generated
	 */
	EAttribute getDate_Month();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Date#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see fr.rostren.tracker.Date#getYear()
	 * @see #getDate()
	 * @generated
	 */
	EAttribute getDate_Year();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Origin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Origin</em>'.
	 * @see fr.rostren.tracker.Origin
	 * @generated
	 */
	EClass getOrigin();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Origin#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see fr.rostren.tracker.Origin#getType()
	 * @see #getOrigin()
	 * @generated
	 */
	EAttribute getOrigin_Type();

	/**
	 * Returns the meta object for the attribute '{@link fr.rostren.tracker.Origin#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see fr.rostren.tracker.Origin#getIdentifier()
	 * @see #getOrigin()
	 * @generated
	 */
	EAttribute getOrigin_Identifier();

	/**
	 * Returns the meta object for the reference list '{@link fr.rostren.tracker.Origin#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operations</em>'.
	 * @see fr.rostren.tracker.Origin#getOperations()
	 * @see #getOrigin()
	 * @generated
	 */
	EReference getOrigin_Operations();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OriginsRepository <em>Origins Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Origins Repository</em>'.
	 * @see fr.rostren.tracker.OriginsRepository
	 * @generated
	 */
	EClass getOriginsRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.OriginsRepository#getOrigins <em>Origins</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Origins</em>'.
	 * @see fr.rostren.tracker.OriginsRepository#getOrigins()
	 * @see #getOriginsRepository()
	 * @generated
	 */
	EReference getOriginsRepository_Origins();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.Tracker <em>Tracker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracker</em>'.
	 * @see fr.rostren.tracker.Tracker
	 * @generated
	 */
	EClass getTracker();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.Tracker#getOwners <em>Owners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owners</em>'.
	 * @see fr.rostren.tracker.Tracker#getOwners()
	 * @see #getTracker()
	 * @generated
	 */
	EReference getTracker_Owners();

	/**
	 * Returns the meta object for the containment reference '{@link fr.rostren.tracker.Tracker#getOriginsRepository <em>Origins Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Origins Repository</em>'.
	 * @see fr.rostren.tracker.Tracker#getOriginsRepository()
	 * @see #getTracker()
	 * @generated
	 */
	EReference getTracker_OriginsRepository();

	/**
	 * Returns the meta object for the containment reference '{@link fr.rostren.tracker.Tracker#getCategoriesRepository <em>Categories Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Categories Repository</em>'.
	 * @see fr.rostren.tracker.Tracker#getCategoriesRepository()
	 * @see #getTracker()
	 * @generated
	 */
	EReference getTracker_CategoriesRepository();

	/**
	 * Returns the meta object for the containment reference '{@link fr.rostren.tracker.Tracker#getOperationsTitlesRepositories <em>Operations Titles Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operations Titles Repositories</em>'.
	 * @see fr.rostren.tracker.Tracker#getOperationsTitlesRepositories()
	 * @see #getTracker()
	 * @generated
	 */
	EReference getTracker_OperationsTitlesRepositories();

	/**
	 * Returns the meta object for class '{@link fr.rostren.tracker.OperationsTitleRepository <em>Operations Title Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operations Title Repository</em>'.
	 * @see fr.rostren.tracker.OperationsTitleRepository
	 * @generated
	 */
	EClass getOperationsTitleRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.rostren.tracker.OperationsTitleRepository#getOperationsTitles <em>Operations Titles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations Titles</em>'.
	 * @see fr.rostren.tracker.OperationsTitleRepository#getOperationsTitles()
	 * @see #getOperationsTitleRepository()
	 * @generated
	 */
	EReference getOperationsTitleRepository_OperationsTitles();

	/**
	 * Returns the meta object for enum '{@link fr.rostren.tracker.Month <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Month</em>'.
	 * @see fr.rostren.tracker.Month
	 * @generated
	 */
	EEnum getMonth();

	/**
	 * Returns the meta object for enum '{@link fr.rostren.tracker.OriginType <em>Origin Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Origin Type</em>'.
	 * @see fr.rostren.tracker.OriginType
	 * @generated
	 */
	EEnum getOriginType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TrackerFactory getTrackerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OwnerImpl <em>Owner</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OwnerImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOwner()
		 * @generated
		 */
		EClass OWNER = eINSTANCE.getOwner();

		/**
		 * The meta object literal for the '<em><b>Accounts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OWNER__ACCOUNTS = eINSTANCE.getOwner_Accounts();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OWNER__FIRST_NAME = eINSTANCE.getOwner_FirstName();

		/**
		 * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OWNER__LAST_NAME = eINSTANCE.getOwner_LastName();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.AccountImpl <em>Account</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.AccountImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAccount()
		 * @generated
		 */
		EClass ACCOUNT = eINSTANCE.getAccount();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__NAME = eINSTANCE.getAccount_Name();

		/**
		 * The meta object literal for the '<em><b>Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__AMOUNT = eINSTANCE.getAccount_Amount();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__IDENTIFIER = eINSTANCE.getAccount_Identifier();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.CheckingAccountImpl <em>Checking Account</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.CheckingAccountImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCheckingAccount()
		 * @generated
		 */
		EClass CHECKING_ACCOUNT = eINSTANCE.getCheckingAccount();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECKING_ACCOUNT__OPERATIONS = eINSTANCE.getCheckingAccount_Operations();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.BoockletAccountImpl <em>Boocklet Account</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.BoockletAccountImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getBoockletAccount()
		 * @generated
		 */
		EClass BOOCKLET_ACCOUNT = eINSTANCE.getBoockletAccount();

		/**
		 * The meta object literal for the '<em><b>Transfers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOCKLET_ACCOUNT__TRANSFERS = eINSTANCE.getBoockletAccount_Transfers();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OperationImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__DATE = eINSTANCE.getOperation_Date();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__ORIGIN = eINSTANCE.getOperation_Origin();

		/**
		 * The meta object literal for the '<em><b>Total Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__TOTAL_AMOUNT = eINSTANCE.getOperation_TotalAmount();

		/**
		 * The meta object literal for the '<em><b>Operation Title</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OPERATION_TITLE = eINSTANCE.getOperation_OperationTitle();

		/**
		 * The meta object literal for the '<em><b>Sub Amounts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__SUB_AMOUNTS = eINSTANCE.getOperation_SubAmounts();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.CreditOperationImpl <em>Credit Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.CreditOperationImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCreditOperation()
		 * @generated
		 */
		EClass CREDIT_OPERATION = eINSTANCE.getCreditOperation();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.DebitOperationImpl <em>Debit Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.DebitOperationImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getDebitOperation()
		 * @generated
		 */
		EClass DEBIT_OPERATION = eINSTANCE.getDebitOperation();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.TransferImpl <em>Transfer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.TransferImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTransfer()
		 * @generated
		 */
		EClass TRANSFER = eINSTANCE.getTransfer();

		/**
		 * The meta object literal for the '<em><b>Incoming Account</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFER__INCOMING_ACCOUNT = eINSTANCE.getTransfer_IncomingAccount();

		/**
		 * The meta object literal for the '<em><b>Outgoing Account</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFER__OUTGOING_ACCOUNT = eINSTANCE.getTransfer_OutgoingAccount();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.IncomingTransferImpl <em>Incoming Transfer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.IncomingTransferImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getIncomingTransfer()
		 * @generated
		 */
		EClass INCOMING_TRANSFER = eINSTANCE.getIncomingTransfer();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OutgoingTransferImpl <em>Outgoing Transfer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OutgoingTransferImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOutgoingTransfer()
		 * @generated
		 */
		EClass OUTGOING_TRANSFER = eINSTANCE.getOutgoingTransfer();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.TitleImpl <em>Title</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.TitleImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTitle()
		 * @generated
		 */
		EClass TITLE = eINSTANCE.getTitle();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITLE__TITLE = eINSTANCE.getTitle_Title();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.CategoryImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Operation Titles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__OPERATION_TITLES = eINSTANCE.getCategory_OperationTitles();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OperationTitleImpl <em>Operation Title</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OperationTitleImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationTitle()
		 * @generated
		 */
		EClass OPERATION_TITLE = eINSTANCE.getOperationTitle();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_TITLE__CATEGORIES = eINSTANCE.getOperationTitle_Categories();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.AmountImpl <em>Amount</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.AmountImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAmount()
		 * @generated
		 */
		EClass AMOUNT = eINSTANCE.getAmount();

		/**
		 * The meta object literal for the '<em><b>Sub Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AMOUNT__SUB_AMOUNT = eINSTANCE.getAmount_SubAmount();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AMOUNT__CATEGORY = eINSTANCE.getAmount_Category();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OperationTitleServiceImpl <em>Operation Title Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OperationTitleServiceImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationTitleService()
		 * @generated
		 */
		EClass OPERATION_TITLE_SERVICE = eINSTANCE.getOperationTitleService();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.CategoryServiceImpl <em>Category Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.CategoryServiceImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategoryService()
		 * @generated
		 */
		EClass CATEGORY_SERVICE = eINSTANCE.getCategoryService();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.AccountServiceImpl <em>Account Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.AccountServiceImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getAccountService()
		 * @generated
		 */
		EClass ACCOUNT_SERVICE = eINSTANCE.getAccountService();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OperationServiceImpl <em>Operation Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OperationServiceImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationService()
		 * @generated
		 */
		EClass OPERATION_SERVICE = eINSTANCE.getOperationService();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.CategoriesRepositoryImpl <em>Categories Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.CategoriesRepositoryImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getCategoriesRepository()
		 * @generated
		 */
		EClass CATEGORIES_REPOSITORY = eINSTANCE.getCategoriesRepository();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORIES_REPOSITORY__CATEGORIES = eINSTANCE.getCategoriesRepository_Categories();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.DateImpl <em>Date</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.DateImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getDate()
		 * @generated
		 */
		EClass DATE = eINSTANCE.getDate();

		/**
		 * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE__DAY = eINSTANCE.getDate_Day();

		/**
		 * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE__MONTH = eINSTANCE.getDate_Month();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE__YEAR = eINSTANCE.getDate_Year();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OriginImpl <em>Origin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OriginImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOrigin()
		 * @generated
		 */
		EClass ORIGIN = eINSTANCE.getOrigin();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORIGIN__TYPE = eINSTANCE.getOrigin_Type();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORIGIN__IDENTIFIER = eINSTANCE.getOrigin_Identifier();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORIGIN__OPERATIONS = eINSTANCE.getOrigin_Operations();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OriginsRepositoryImpl <em>Origins Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OriginsRepositoryImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOriginsRepository()
		 * @generated
		 */
		EClass ORIGINS_REPOSITORY = eINSTANCE.getOriginsRepository();

		/**
		 * The meta object literal for the '<em><b>Origins</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORIGINS_REPOSITORY__ORIGINS = eINSTANCE.getOriginsRepository_Origins();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.TrackerImpl <em>Tracker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.TrackerImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getTracker()
		 * @generated
		 */
		EClass TRACKER = eINSTANCE.getTracker();

		/**
		 * The meta object literal for the '<em><b>Owners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKER__OWNERS = eINSTANCE.getTracker_Owners();

		/**
		 * The meta object literal for the '<em><b>Origins Repository</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKER__ORIGINS_REPOSITORY = eINSTANCE.getTracker_OriginsRepository();

		/**
		 * The meta object literal for the '<em><b>Categories Repository</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKER__CATEGORIES_REPOSITORY = eINSTANCE.getTracker_CategoriesRepository();

		/**
		 * The meta object literal for the '<em><b>Operations Titles Repositories</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKER__OPERATIONS_TITLES_REPOSITORIES = eINSTANCE.getTracker_OperationsTitlesRepositories();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.impl.OperationsTitleRepositoryImpl <em>Operations Title Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.impl.OperationsTitleRepositoryImpl
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOperationsTitleRepository()
		 * @generated
		 */
		EClass OPERATIONS_TITLE_REPOSITORY = eINSTANCE.getOperationsTitleRepository();

		/**
		 * The meta object literal for the '<em><b>Operations Titles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES = eINSTANCE.getOperationsTitleRepository_OperationsTitles();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.Month <em>Month</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.Month
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getMonth()
		 * @generated
		 */
		EEnum MONTH = eINSTANCE.getMonth();

		/**
		 * The meta object literal for the '{@link fr.rostren.tracker.OriginType <em>Origin Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.rostren.tracker.OriginType
		 * @see fr.rostren.tracker.impl.TrackerPackageImpl#getOriginType()
		 * @generated
		 */
		EEnum ORIGIN_TYPE = eINSTANCE.getOriginType();

	}

} //TrackerPackage
