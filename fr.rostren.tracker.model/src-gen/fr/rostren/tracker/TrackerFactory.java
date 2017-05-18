/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see fr.rostren.tracker.TrackerPackage
 * @generated
 */
public interface TrackerFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	TrackerFactory eINSTANCE=fr.rostren.tracker.impl.TrackerFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Owner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Owner</em>'.
	 * @generated
	 */
	Owner createOwner();

	/**
	 * Returns a new object of class '<em>Checking Account</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Checking Account</em>'.
	 * @generated
	 */
	CheckingAccount createCheckingAccount();

	/**
	 * Returns a new object of class '<em>Boocklet Account</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Boocklet Account</em>'.
	 * @generated
	 */
	BoockletAccount createBoockletAccount();

	/**
	 * Returns a new object of class '<em>Credit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credit</em>'.
	 * @generated
	 */
	Credit createCredit();

	/**
	 * Returns a new object of class '<em>Credit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credit</em>'.
	 */
	Credit createCredit(EObject object);

	/**
	 * Returns a new object of class '<em>Debit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Debit</em>'.
	 * @generated
	 */
	Debit createDebit();

	/**
	 * Returns a new object of class '<em>Debit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Debit</em>'.
	 */
	Debit createDebit(EObject object);

	/**
	 * Returns a new object of class '<em>Incoming</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Incoming</em>'.
	 * @generated
	 */
	Incoming createIncoming();

	/**
	 * Returns a new object of class '<em>Incoming</em>'.
	 * @param object the given account
	 * @return a new object of class '<em>Incoming</em>'.
	 */
	Incoming createIncoming(EObject object);

	/**
	 * Returns a new object of class '<em>Outgoing</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Outgoing</em>'.
	 * @generated
	 */
	Outgoing createOutgoing();

	/**
	 * Returns a new object of class '<em>Outgoing</em>'.
	 * @param object the given account
	 * @return a new object of class '<em>Outgoing</em>'.
	 */
	Outgoing createOutgoing(EObject object);

	/**
	 * Returns a new object of class '<em>Operation Title</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Operation Title</em>'.
	 * @generated
	 */
	OperationTitle createOperationTitle();

	/**
	 * Returns a new object of class '<em>Operation Title</em>'.
	 * @param tracker the tracker
	 * @param title the title
	 * @return a new object of class '<em>Operation Title</em>'.
	 */
	public OperationTitle createOperationTitle(Tracker tracker, String title);

	/**
	 * Returns a new object of class '<em>Amount</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Amount</em>'.
	 * @generated
	 */
	Amount createAmount();

	/**
	 * Returns a new object of class '<em>Amount</em>'.
	 * @param operation the operation
	 * @param amount amount
	 * @param category the linked category
	 * @return a new object of class '<em>Amount</em>'.
	 */
	Amount createAmount(Operation operation, double amount, Category category);

	/**
	 * Returns a new object of class '<em>Amount</em>'.
	 * @param operation the operation
	 * @param amount amount
	 * @param category the linked category
	 * @return a new object of class '<em>Amount</em>'.
	 */
	Amount createAmount(OperationData operation, double amount, Category linkedCategory);

	/**
	 * Returns a new object of class '<em>Category Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Category Service</em>'.
	 * @generated
	 */
	CategoryService createCategoryService();

	/**
	 * Returns a new object of class '<em>Account Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Account Service</em>'.
	 * @generated
	 */
	AccountService createAccountService();

	/**
	 * Returns a new object of class '<em>Operation Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Operation Service</em>'.
	 * @generated
	 */
	OperationService createOperationService();

	/**
	 * Returns a new object of class '<em>Categories Repository</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Categories Repository</em>'.
	 * @generated
	 */
	CategoriesRepository createCategoriesRepository();

	/**
	 * Returns a new object of class '<em>Origin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Origin</em>'.
	 * @generated
	 */
	Origin createOrigin();

	/**
	 * Returns a new object of class '<em>Origin</em>'.
	 * @param identifier the identifier
	 * @param type the type
	 * @return a new object of class '<em>Origin</em>'.
	 */
	Origin createOrigin(String identifier, OriginType type);

	/**
	 * Returns a new object of class '<em>Origins Repository</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
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
	 * Returns a new object of class '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service</em>'.
	 * @generated
	 */
	TrackerService createTrackerService();

	/**
	 * Returns a new object of class '<em>Operations Title Repository</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Operations Title Repository</em>'.
	 * @generated
	 */
	OperationsTitleRepository createOperationsTitleRepository();

	/**
	 * Returns a new object of class '<em>Income Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Income Category</em>'.
	 * @generated
	 */
	IncomeCategory createIncomeCategory();

	/**
	 * Returns a new object of class '<em>Spending Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Spending Category</em>'.
	 * @generated
	 */
	SpendingCategory createSpendingCategory();

	/**
	 * Returns a new object of class '<em>Spending Category</em>'.
	 * @param tracker  the tracker model
	 * @param title the operation Title
	 * @param type the operation type
	 * @return a new object of class '<em>Spending Category</em>'.
	 */
	Category createCategory(Tracker tracker, OperationTitle title, OperationType type);

	/**
	 * Returns a new object of class '<em>Income Category</em>'.
	 * @param income the income category
	 * @return a new object of class '<em>Income Category</em>'.
	 */
	IncomeCategory createCategory(IncomeCategory income);

	/**
	 * Returns a new object of class '<em>Spending Category</em>'.
	 * @param spending the spending category
	 * @return a new object of class '<em>Spending Category</em>'.
	 */
	SpendingCategory createCategory(SpendingCategory spending);

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TrackerPackage getTrackerPackage();
} // TrackerFactory
