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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Categories Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.CategoriesRepositoryImpl#getIncome <em>Income</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.CategoriesRepositoryImpl#getSpending <em>Spending</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoriesRepositoryImpl extends EObjectImpl implements CategoriesRepository {
	/**
	 * The cached value of the '{@link #getIncome() <em>Income</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncome()
	 * @generated
	 * @ordered
	 */
	protected IncomeCategory income;
	/**
	 * The cached value of the '{@link #getSpending() <em>Spending</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpending()
	 * @generated
	 * @ordered
	 */
	protected SpendingCategory spending;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoriesRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.CATEGORIES_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncomeCategory getIncome() {
		return income;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncome(IncomeCategory newIncome, NotificationChain msgs) {
		IncomeCategory oldIncome=income;
		income=newIncome;
		if (eNotificationRequired()) {
			ENotificationImpl notification=new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORIES_REPOSITORY__INCOME, oldIncome, newIncome);
			if (msgs == null)
				msgs=notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIncome(IncomeCategory newIncome) {
		if (newIncome != income) {
			NotificationChain msgs=null;
			if (income != null)
				msgs=((InternalEObject)income).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.CATEGORIES_REPOSITORY__INCOME, null, msgs);
			if (newIncome != null)
				msgs=((InternalEObject)newIncome).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.CATEGORIES_REPOSITORY__INCOME, null, msgs);
			msgs=basicSetIncome(newIncome, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORIES_REPOSITORY__INCOME, newIncome, newIncome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpendingCategory getSpending() {
		return spending;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpending(SpendingCategory newSpending, NotificationChain msgs) {
		SpendingCategory oldSpending=spending;
		spending=newSpending;
		if (eNotificationRequired()) {
			ENotificationImpl notification=new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORIES_REPOSITORY__SPENDING, oldSpending, newSpending);
			if (msgs == null)
				msgs=notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpending(SpendingCategory newSpending) {
		if (newSpending != spending) {
			NotificationChain msgs=null;
			if (spending != null)
				msgs=((InternalEObject)spending).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.CATEGORIES_REPOSITORY__SPENDING, null, msgs);
			if (newSpending != null)
				msgs=((InternalEObject)newSpending).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.CATEGORIES_REPOSITORY__SPENDING, null, msgs);
			msgs=basicSetSpending(newSpending, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORIES_REPOSITORY__SPENDING, newSpending, newSpending));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.CATEGORIES_REPOSITORY__INCOME:
				return basicSetIncome(null, msgs);
			case TrackerPackage.CATEGORIES_REPOSITORY__SPENDING:
				return basicSetSpending(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.CATEGORIES_REPOSITORY__INCOME:
				return getIncome();
			case TrackerPackage.CATEGORIES_REPOSITORY__SPENDING:
				return getSpending();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.CATEGORIES_REPOSITORY__INCOME:
				setIncome((IncomeCategory)newValue);
				return;
			case TrackerPackage.CATEGORIES_REPOSITORY__SPENDING:
				setSpending((SpendingCategory)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TrackerPackage.CATEGORIES_REPOSITORY__INCOME:
				setIncome((IncomeCategory)null);
				return;
			case TrackerPackage.CATEGORIES_REPOSITORY__SPENDING:
				setSpending((SpendingCategory)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TrackerPackage.CATEGORIES_REPOSITORY__INCOME:
				return income != null;
			case TrackerPackage.CATEGORIES_REPOSITORY__SPENDING:
				return spending != null;
		}
		return super.eIsSet(featureID);
	}

} // CategoriesRepositoryImpl
