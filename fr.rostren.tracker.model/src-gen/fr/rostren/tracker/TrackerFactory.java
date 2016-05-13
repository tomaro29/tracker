/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.rostren.tracker.TrackerPackage
 * @generated
 */
public interface TrackerFactory extends EFactory {
	/**
         * The singleton instance of the factory.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	TrackerFactory eINSTANCE = fr.rostren.tracker.impl.TrackerFactoryImpl.init();

	/**
         * Returns a new object of class '<em>Owner</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Owner</em>'.
         * @generated
         */
	Owner createOwner();

	/**
         * Returns a new object of class '<em>Checking Account</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Checking Account</em>'.
         * @generated
         */
	CheckingAccount createCheckingAccount();

	/**
         * Returns a new object of class '<em>Boocklet Account</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Boocklet Account</em>'.
         * @generated
         */
	BoockletAccount createBoockletAccount();

	/**
         * Returns a new object of class '<em>Credit Operation</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Credit Operation</em>'.
         * @generated
         */
	CreditOperation createCreditOperation();

	/**
         * Returns a new object of class '<em>Debit Operation</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Debit Operation</em>'.
         * @generated
         */
	DebitOperation createDebitOperation();

	/**
         * Returns a new object of class '<em>Incoming Transfer</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Incoming Transfer</em>'.
         * @generated
         */
	IncomingTransfer createIncomingTransfer();

	/**
         * Returns a new object of class '<em>Outgoing Transfer</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Outgoing Transfer</em>'.
         * @generated
         */
	OutgoingTransfer createOutgoingTransfer();

	/**
         * Returns a new object of class '<em>Category</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Category</em>'.
         * @generated
         */
	Category createCategory();

	/**
         * Returns a new object of class '<em>Operation Title</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Operation Title</em>'.
         * @generated
         */
	OperationTitle createOperationTitle();

	/**
         * Returns a new object of class '<em>Amount</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Amount</em>'.
         * @generated
         */
	Amount createAmount();

	/**
         * Returns a new object of class '<em>Operation Title Service</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Operation Title Service</em>'.
         * @generated
         */
	OperationTitleService createOperationTitleService();

	/**
         * Returns a new object of class '<em>Category Service</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Category Service</em>'.
         * @generated
         */
	CategoryService createCategoryService();

	/**
         * Returns a new object of class '<em>Account Service</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Account Service</em>'.
         * @generated
         */
	AccountService createAccountService();

	/**
         * Returns a new object of class '<em>Operation Service</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Operation Service</em>'.
         * @generated
         */
	OperationService createOperationService();

	/**
         * Returns a new object of class '<em>Categories Repository</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Categories Repository</em>'.
         * @generated
         */
	CategoriesRepository createCategoriesRepository();

	/**
         * Returns a new object of class '<em>Date</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Date</em>'.
         * @generated
         */
	Date createDate();

	/**
         * Returns a new object of class '<em>Origin</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Origin</em>'.
         * @generated
         */
	Origin createOrigin();

	/**
         * Returns a new object of class '<em>Origins Repository</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Origins Repository</em>'.
         * @generated
         */
	OriginsRepository createOriginsRepository();

	/**
         * Returns a new object of class '<em>Tracker</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Tracker</em>'.
         * @generated
         */
	Tracker createTracker();

	/**
         * Returns a new object of class '<em>Operations Title Repository</em>'.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return a new object of class '<em>Operations Title Repository</em>'.
         * @generated
         */
	OperationsTitleRepository createOperationsTitleRepository();

	/**
         * Returns the package supported by this factory.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @return the package supported by this factory.
         * @generated
         */
	TrackerPackage getTrackerPackage();

} //TrackerFactory
