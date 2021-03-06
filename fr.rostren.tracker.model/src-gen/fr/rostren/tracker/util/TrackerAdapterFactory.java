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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see fr.rostren.tracker.TrackerPackage
 * @generated
 */
public class TrackerAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static TrackerPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TrackerAdapterFactory() {
		if (modelPackage == null) {
			modelPackage=TrackerPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected TrackerSwitch<Adapter> modelSwitch=new TrackerSwitch<Adapter>() {
		@Override
		public Adapter caseOwner(Owner object) {
			return createOwnerAdapter();
		}

		@Override
		public Adapter caseAccount(Account object) {
			return createAccountAdapter();
		}

		@Override
		public Adapter caseAccountService(AccountService object) {
			return createAccountServiceAdapter();
		}

		@Override
		public Adapter caseCheckingAccount(CheckingAccount object) {
			return createCheckingAccountAdapter();
		}

		@Override
		public Adapter caseBoockletAccount(BoockletAccount object) {
			return createBoockletAccountAdapter();
		}

		@Override
		public Adapter caseOperation(Operation object) {
			return createOperationAdapter();
		}

		@Override
		public Adapter caseOperationService(OperationService object) {
			return createOperationServiceAdapter();
		}

		@Override
		public Adapter caseCredit(Credit object) {
			return createCreditAdapter();
		}

		@Override
		public Adapter caseDebit(Debit object) {
			return createDebitAdapter();
		}

		@Override
		public Adapter caseTransfer(Transfer object) {
			return createTransferAdapter();
		}

		@Override
		public Adapter caseIncoming(Incoming object) {
			return createIncomingAdapter();
		}

		@Override
		public Adapter caseOutgoing(Outgoing object) {
			return createOutgoingAdapter();
		}

		@Override
		public Adapter caseTitle(Title object) {
			return createTitleAdapter();
		}

		@Override
		public Adapter caseCategory(Category object) {
			return createCategoryAdapter();
		}

		@Override
		public Adapter caseCategoryService(CategoryService object) {
			return createCategoryServiceAdapter();
		}

		@Override
		public Adapter caseOperationTitle(OperationTitle object) {
			return createOperationTitleAdapter();
		}

		@Override
		public Adapter caseAmount(Amount object) {
			return createAmountAdapter();
		}

		@Override
		public Adapter caseCategoriesRepository(CategoriesRepository object) {
			return createCategoriesRepositoryAdapter();
		}

		@Override
		public Adapter caseOrigin(Origin object) {
			return createOriginAdapter();
		}

		@Override
		public Adapter caseOriginsRepository(OriginsRepository object) {
			return createOriginsRepositoryAdapter();
		}

		@Override
		public Adapter caseTracker(Tracker object) {
			return createTrackerAdapter();
		}

		@Override
		public Adapter caseTrackerService(TrackerService object) {
			return createTrackerServiceAdapter();
		}

		@Override
		public Adapter caseOperationsTitleRepository(OperationsTitleRepository object) {
			return createOperationsTitleRepositoryAdapter();
		}

		@Override
		public Adapter caseIncomeCategory(IncomeCategory object) {
			return createIncomeCategoryAdapter();
		}

		@Override
		public Adapter caseSpendingCategory(SpendingCategory object) {
			return createSpendingCategoryAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Owner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Owner
	 * @generated
	 */
	public Adapter createOwnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Account <em>Account</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Account
	 * @generated
	 */
	public Adapter createAccountAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.CheckingAccount <em>Checking Account</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.CheckingAccount
	 * @generated
	 */
	public Adapter createCheckingAccountAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.BoockletAccount <em>Boocklet Account</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.BoockletAccount
	 * @generated
	 */
	public Adapter createBoockletAccountAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.Operation <em>Operation</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Credit <em>Credit</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Credit
	 * @generated
	 */
	public Adapter createCreditAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Debit <em>Debit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Debit
	 * @generated
	 */
	public Adapter createDebitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.Transfer <em>Transfer</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Transfer
	 * @generated
	 */
	public Adapter createTransferAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.Incoming <em>Incoming</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Incoming
	 * @generated
	 */
	public Adapter createIncomingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.Outgoing <em>Outgoing</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Outgoing
	 * @generated
	 */
	public Adapter createOutgoingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Title <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Title
	 * @generated
	 */
	public Adapter createTitleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.Category <em>Category</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Category
	 * @generated
	 */
	public Adapter createCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.OperationTitle <em>Operation Title</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.OperationTitle
	 * @generated
	 */
	public Adapter createOperationTitleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Amount <em>Amount</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Amount
	 * @generated
	 */
	public Adapter createAmountAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.CategoryService <em>Category Service</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.CategoryService
	 * @generated
	 */
	public Adapter createCategoryServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link fr.rostren.tracker.AccountService <em>Account Service</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see fr.rostren.tracker.AccountService
	 * @generated
	 */
	public Adapter createAccountServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.OperationService <em>Operation Service</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.OperationService
	 * @generated
	 */
	public Adapter createOperationServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.CategoriesRepository <em>Categories Repository</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.CategoriesRepository
	 * @generated
	 */
	public Adapter createCategoriesRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Origin <em>Origin</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Origin
	 * @generated
	 */
	public Adapter createOriginAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.OriginsRepository <em>Origins Repository</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.OriginsRepository
	 * @generated
	 */
	public Adapter createOriginsRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.Tracker <em>Tracker</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.Tracker
	 * @generated
	 */
	public Adapter createTrackerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.TrackerService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.TrackerService
	 * @generated
	 */
	public Adapter createTrackerServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.OperationsTitleRepository <em>Operations Title Repository</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.OperationsTitleRepository
	 * @generated
	 */
	public Adapter createOperationsTitleRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.IncomeCategory <em>Income Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.IncomeCategory
	 * @generated
	 */
	public Adapter createIncomeCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.rostren.tracker.SpendingCategory <em>Spending Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.rostren.tracker.SpendingCategory
	 * @generated
	 */
	public Adapter createSpendingCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // TrackerAdapterFactory
