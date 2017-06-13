/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Title;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.Transfer;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see fr.rostren.tracker.TrackerPackage
 * @generated
 */
public class TrackerSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static TrackerPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TrackerSwitch() {
		if (modelPackage == null) {
			modelPackage=TrackerPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param ePackage
	 *            the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TrackerPackage.OWNER: {
				Owner owner=(Owner)theEObject;
				T result=caseOwner(owner);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.ACCOUNT: {
				Account account=(Account)theEObject;
				T result=caseAccount(account);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.ACCOUNT_SERVICE: {
				AccountService accountService=(AccountService)theEObject;
				T result=caseAccountService(accountService);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.CHECKING_ACCOUNT: {
				CheckingAccount checkingAccount=(CheckingAccount)theEObject;
				T result=caseCheckingAccount(checkingAccount);
				if (result == null)
					result=caseAccount(checkingAccount);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.BOOCKLET_ACCOUNT: {
				BoockletAccount boockletAccount=(BoockletAccount)theEObject;
				T result=caseBoockletAccount(boockletAccount);
				if (result == null)
					result=caseAccount(boockletAccount);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.OPERATION: {
				Operation operation=(Operation)theEObject;
				T result=caseOperation(operation);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.OPERATION_SERVICE: {
				OperationService operationService=(OperationService)theEObject;
				T result=caseOperationService(operationService);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.CREDIT: {
				Credit credit=(Credit)theEObject;
				T result=caseCredit(credit);
				if (result == null)
					result=caseOperation(credit);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.DEBIT: {
				Debit debit=(Debit)theEObject;
				T result=caseDebit(debit);
				if (result == null)
					result=caseOperation(debit);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.TRANSFER: {
				Transfer transfer=(Transfer)theEObject;
				T result=caseTransfer(transfer);
				if (result == null)
					result=caseOperation(transfer);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.INCOMING: {
				Incoming incoming=(Incoming)theEObject;
				T result=caseIncoming(incoming);
				if (result == null)
					result=caseTransfer(incoming);
				if (result == null)
					result=caseOperation(incoming);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.OUTGOING: {
				Outgoing outgoing=(Outgoing)theEObject;
				T result=caseOutgoing(outgoing);
				if (result == null)
					result=caseTransfer(outgoing);
				if (result == null)
					result=caseOperation(outgoing);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.TITLE: {
				Title title=(Title)theEObject;
				T result=caseTitle(title);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.CATEGORY: {
				Category category=(Category)theEObject;
				T result=caseCategory(category);
				if (result == null)
					result=caseTitle(category);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.CATEGORY_SERVICE: {
				CategoryService categoryService=(CategoryService)theEObject;
				T result=caseCategoryService(categoryService);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.OPERATION_TITLE: {
				OperationTitle operationTitle=(OperationTitle)theEObject;
				T result=caseOperationTitle(operationTitle);
				if (result == null)
					result=caseTitle(operationTitle);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.AMOUNT: {
				Amount amount=(Amount)theEObject;
				T result=caseAmount(amount);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.CATEGORIES_REPOSITORY: {
				CategoriesRepository categoriesRepository=(CategoriesRepository)theEObject;
				T result=caseCategoriesRepository(categoriesRepository);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.ORIGIN: {
				Origin origin=(Origin)theEObject;
				T result=caseOrigin(origin);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.ORIGINS_REPOSITORY: {
				OriginsRepository originsRepository=(OriginsRepository)theEObject;
				T result=caseOriginsRepository(originsRepository);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.TRACKER: {
				Tracker tracker=(Tracker)theEObject;
				T result=caseTracker(tracker);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.TRACKER_SERVICE: {
				TrackerService trackerService=(TrackerService)theEObject;
				T result=caseTrackerService(trackerService);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY: {
				OperationsTitleRepository operationsTitleRepository=(OperationsTitleRepository)theEObject;
				T result=caseOperationsTitleRepository(operationsTitleRepository);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.INCOME_CATEGORY: {
				IncomeCategory incomeCategory=(IncomeCategory)theEObject;
				T result=caseIncomeCategory(incomeCategory);
				if (result == null)
					result=caseCategory(incomeCategory);
				if (result == null)
					result=caseTitle(incomeCategory);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			case TrackerPackage.SPENDING_CATEGORY: {
				SpendingCategory spendingCategory=(SpendingCategory)theEObject;
				T result=caseSpendingCategory(spendingCategory);
				if (result == null)
					result=caseCategory(spendingCategory);
				if (result == null)
					result=caseTitle(spendingCategory);
				if (result == null)
					result=defaultCase(theEObject);
				return result;
			}
			default:
				return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Owner</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOwner(Owner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Account</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Account</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccount(Account object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Checking Account</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Checking Account</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheckingAccount(CheckingAccount object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boocklet Account</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boocklet Account</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoockletAccount(BoockletAccount object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Credit</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Credit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCredit(Credit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Debit</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Debit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDebit(Debit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transfer</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transfer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransfer(Transfer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Incoming</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Incoming</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncoming(Incoming object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outgoing</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outgoing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutgoing(Outgoing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Title</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Title</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTitle(Title object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategory(Category object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Title</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Title</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationTitle(OperationTitle object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Amount</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Amount</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAmount(Amount object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category Service</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategoryService(CategoryService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Account Service</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Account Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccountService(AccountService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Service</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationService(OperationService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Categories Repository</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Categories Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategoriesRepository(CategoriesRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Origin</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Origin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrigin(Origin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Origins Repository</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Origins Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOriginsRepository(OriginsRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracker</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTracker(Tracker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrackerService(TrackerService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operations Title Repository</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operations Title Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationsTitleRepository(OperationsTitleRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Income Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Income Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncomeCategory(IncomeCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Spending Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Spending Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpendingCategory(SpendingCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // TrackerSwitch
