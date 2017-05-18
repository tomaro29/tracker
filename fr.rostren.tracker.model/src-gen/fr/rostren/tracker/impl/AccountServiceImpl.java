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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Account Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.AccountServiceImpl#getAccount <em>Account</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AccountServiceImpl extends EObjectImpl implements AccountService {
	/**
	 * The cached value of the '{@link #getAccount() <em>Account</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccount()
	 * @generated
	 * @ordered
	 */
	protected Account account;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AccountServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.ACCOUNT_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Account getAccount() {
		if (account != null && account.eIsProxy()) {
			InternalEObject oldAccount=(InternalEObject)account;
			account=(Account)eResolveProxy(oldAccount);
			if (account != oldAccount) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.ACCOUNT_SERVICE__ACCOUNT, oldAccount, account));
				}
			}
		}
		return account;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account basicGetAccount() {
		return account;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAccount(Account newAccount) {
		Account oldAccount=account;
		account=newAccount;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.ACCOUNT_SERVICE__ACCOUNT, oldAccount, account));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double sumPerCategory(Category category, Month month, int year, boolean wishedDated) {
		return getAmountsStream(category, month, year, wishedDated).sum();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double averagePerCategory(Category category, Month month, int year, boolean wishedDated) {
		return getAmountsStream(category, month, year, wishedDated).average().orElseThrow(() -> new IllegalArgumentException());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double sumPerCategory(Category category, int year, boolean wishedDated) {
		return getAmountsStream(category, year, wishedDated).sum();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double averagePerCategory(Category category, int year, boolean wishedDated) {
		return getAmountsStream(category, year, wishedDated).average().orElseThrow(() -> new IllegalArgumentException());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double sumPerCategory(Category category) {
		return getAmountsStream(category).sum();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double averagePerCategory(Category category) {
		return getAmountsStream(category).average().orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public List<Double> findAllCategoriesAmount(List<String> months, int year, boolean wishedEnabled, Class<?> clazz) {
		return months.stream()//
				.mapToDouble(month -> getTotalAmount(findAllAmounts(Month.valueOf(month), year, wishedEnabled, clazz)))//
				.boxed()//
				.collect(Collectors.toList());
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param item the category item
	 * @param months the months to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the income category amount of the given item and its children
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Double> findIncomeCategoryAmounts(String item, EList<String> months, int year, boolean wishedEnabled) {
		Category category=TrackerUtils.getCategoryService(TrackerUtils.getTracker(account).getCategoriesRepository().getIncome()).findCategory(item);
		if (category != null) {
			return new BasicEList<>(months.stream()//
					.mapToDouble(month -> getTotalAmount(findCategoryAmounts(category, Month.valueOf(month), year, wishedEnabled)))//
					.boxed()//
					.collect(Collectors.toList()));
		}
		return new BasicEList<>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param item the category item
	 * @param months the months to witch we need to extract spending category amount
	 * @param year the year
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the spending category amount of the given item and its children
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Double> findSpendingCategoryAmounts(String item, EList<String> months, int year, boolean wishedEnabled) {
		Category category=TrackerUtils.getCategoryService(TrackerUtils.getTracker(account).getCategoriesRepository().getSpending()).findCategory(item);
		if (category != null) {
			return new BasicEList<>(months.stream()//
					.mapToDouble(month -> getTotalAmount(findCategoryAmounts(category, Month.valueOf(month), year, wishedEnabled)))//
					.boxed()//
					.collect(Collectors.toList()));
		}
		return new BasicEList<>();
	}

	/**
	 * Returns all valid amounts vs comparison year and month.
	 * @param month the selected month.
	 * @param year the selected year.
	 * @param wishedEnabled <code>true</code> is the wished date is enabled, <code>false</code> otherwise.
	 * @param clazz the class type of the amount {@link Category}.
	 * @return all valid amounts vs comparison year and month.
	 */
	private List<Amount> findAllAmounts(Month month, int year, boolean wishedEnabled, Class<?> clazz) {
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			return checking.getOperations()//
					.stream()//
					.flatMap(operation -> operation.getSubAmounts()//
							.stream()//
							.filter(amount -> clazz.isInstance(amount.getCategory()))//
							.filter(amount -> isDateValid(amount, operation, year, month, wishedEnabled)))//
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the list of all amounts of operations related to the given category or one of its sub categories
	 * @param category the category.
	 * @param month the month.
	 * @param year the year.
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the list of amounts related to the given category.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Amount> findCategoryAmounts(Category category, Month month, int year, boolean wishedEnabled) {
		List<Category> linkedCategories=TrackerUtils.getCategoryService(category).getCategories();
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			return new BasicEList<>(checking.getOperations()//
					.stream()//
					.flatMap(operation -> operation.getSubAmounts()//
							.stream()//
							.filter(amount -> linkedCategories.contains(amount.getCategory()))//
							.filter(amount -> isDateValid(amount, operation, year, month, wishedEnabled)))//
					.collect(Collectors.toList()));
		}
		return new BasicEList<>();
	}

	/**
	 * REturns the total amount of all given amounts as double.
	 * @param amounts the list of amounts to addition
	 * @return the total amount value, sum of all given amounts as double.
	 */
	private static double getTotalAmount(List<Amount> amounts) {
		return amounts.stream()//
				.mapToDouble(amount -> amount.getValue())//
				.sum();
	}

	/**
	 * <code>true</code> if the amount has a valid date; <code>false</code> otherwise.
	 * @param amount the amount to check
	 * @param operation the operation container of the given amount
	 * @param year the selected year
	 * @param month the selected month
	 * @param wishedEnabled <code>true</code> if the comparison is based on the wished date, <code>false</code> otherwise
	 * @return <code>true</code> if the amount has a valid date; <code>false</code> otherwise.
	 */
	private static boolean isDateValid(Amount amount, Operation operation, int year, Month month, boolean wishedEnabled) {
		Optional<LocalDate> wishedDate=amount.getWishedDate() != null ? Optional.of(amount.getWishedDate()) : Optional.empty();
		Optional<LocalDate> operationDate=operation.getDate() != null ? Optional.of(operation.getDate()) : Optional.empty();

		LocalDate comparisonDate=wishedEnabled	? wishedDate.orElse(operationDate.orElseThrow(IllegalArgumentException::new))
												: operationDate.orElseThrow(IllegalArgumentException::new);
		return month.equals(comparisonDate.getMonth()) && year == comparisonDate.getYear();
	}

	/**
	 * @return the {@link DoubleStream} representing all concerned amounts
	 */
	private DoubleStream getAmountsStream(Category category, Month month, int year, boolean wishedDated) {
		if (account instanceof CheckingAccount) {
			return wishedDated	? ((CheckingAccount)account).getOperations().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category, month, year))//
					.mapToDouble(amount -> amount.getValue())
								: ((CheckingAccount)account).getOperations().stream()//
										.filter(operation -> filterOperation(operation, month, year))//
										.flatMap(operation -> operation.getSubAmounts().stream())//
										.filter(amount -> filterAmount(amount, category))//
										.mapToDouble(amount -> amount.getValue());
		}
		if (account instanceof BoockletAccount) {
			return wishedDated	? ((BoockletAccount)account).getTransfers().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category, month, year))//
					.mapToDouble(amount -> amount.getValue())
								: ((BoockletAccount)account).getTransfers().stream()//
										.filter(operation -> filterOperation(operation, month, year))//
										.flatMap(operation -> operation.getSubAmounts().stream())//
										.filter(amount -> filterAmount(amount, category))//
										.mapToDouble(amount -> amount.getValue());
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the {@link DoubleStream} representing all concerned amounts
	 */
	private DoubleStream getAmountsStream(Category category, int year, boolean wishedDated) {
		if (account instanceof CheckingAccount) {
			return wishedDated	? ((CheckingAccount)account).getOperations().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category, year))//
					.mapToDouble(amount -> amount.getValue())
								: ((CheckingAccount)account).getOperations().stream()//
										.filter(operation -> filterOperation(operation, year))//
										.flatMap(operation -> operation.getSubAmounts().stream())//
										.filter(amount -> filterAmount(amount, category))//
										.mapToDouble(amount -> amount.getValue());
		}
		if (account instanceof BoockletAccount) {
			return wishedDated	? ((BoockletAccount)account).getTransfers().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category, year))//
					.mapToDouble(amount -> amount.getValue())
								: ((BoockletAccount)account).getTransfers().stream()//
										.filter(operation -> filterOperation(operation, year))//
										.flatMap(operation -> operation.getSubAmounts().stream())//
										.filter(amount -> filterAmount(amount, category))//
										.mapToDouble(amount -> amount.getValue());
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the {@link DoubleStream} representing all concerned amounts
	 */
	private DoubleStream getAmountsStream(Category category) {
		if (account instanceof CheckingAccount) {
			return ((CheckingAccount)account).getOperations().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category))//
					.mapToDouble(amount -> amount.getValue());
		}
		if (account instanceof BoockletAccount) {
			return ((BoockletAccount)account).getTransfers().stream()//
					.flatMap(operation -> operation.getSubAmounts().stream())//
					.filter(amount -> filterAmount(amount, category))//
					.mapToDouble(amount -> amount.getValue());
		}
		throw new UnsupportedOperationException();
	}

	private boolean filterOperation(Operation operation, Month month, int year) {
		return operation.getDate().getMonth().equals(month) && filterOperation(operation, year);
	}

	private boolean filterOperation(Operation operation, int year) {
		return operation.getDate().getYear() == year;
	}

	private boolean filterAmount(Amount amount, Category category, Month month, int year) {
		return amount.getWishedDate().getMonth().equals(month) && filterAmount(amount, category, year);
	}

	private boolean filterAmount(Amount amount, Category category, int year) {
		return amount.getWishedDate().getYear() == year && filterAmount(amount, category);
	}

	private boolean filterAmount(Amount amount, Category category) {
		return amount.getCategory().equals(category);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.ACCOUNT_SERVICE__ACCOUNT:
				if (resolve) {
					return getAccount();
				}
				return basicGetAccount();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.ACCOUNT_SERVICE__ACCOUNT:
				setAccount((Account)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TrackerPackage.ACCOUNT_SERVICE__ACCOUNT:
				setAccount((Account)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TrackerPackage.ACCOUNT_SERVICE__ACCOUNT:
				return account != null;
		}
		return super.eIsSet(featureID);
	}

} // AccountServiceImpl
