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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Amount</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.AmountImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.AmountImpl#getValue <em>Value</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.AmountImpl#getWishedDate <em>Wished Date</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AmountImpl extends EObjectImpl implements Amount {
	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected Category category;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT=0.0;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected double value=VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWishedDate() <em>Wished Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWishedDate()
	 * @generated
	 * @ordered
	 */
	protected static final LocalDate WISHED_DATE_EDEFAULT=null;

	/**
	 * The cached value of the '{@link #getWishedDate() <em>Wished Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWishedDate()
	 * @generated
	 * @ordered
	 */
	protected LocalDate wishedDate=WISHED_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AmountImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.AMOUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Category getCategory() {
		if (category != null && category.eIsProxy()) {
			InternalEObject oldCategory=(InternalEObject)category;
			category=(Category)eResolveProxy(oldCategory);
			if (category != oldCategory) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.AMOUNT__CATEGORY, oldCategory, category));
			}
		}
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category basicGetCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCategory(Category newCategory) {
		Category oldCategory=category;
		category=newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.AMOUNT__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(double newValue) {
		double oldValue=value;
		value=newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.AMOUNT__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalDate getWishedDate() {
		return wishedDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWishedDate(LocalDate newWishedDate) {
		LocalDate oldWishedDate=wishedDate;
		wishedDate=newWishedDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.AMOUNT__WISHED_DATE, oldWishedDate, wishedDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.AMOUNT__CATEGORY:
				if (resolve)
					return getCategory();
				return basicGetCategory();
			case TrackerPackage.AMOUNT__VALUE:
				return getValue();
			case TrackerPackage.AMOUNT__WISHED_DATE:
				return getWishedDate();
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
			case TrackerPackage.AMOUNT__CATEGORY:
				setCategory((Category)newValue);
				return;
			case TrackerPackage.AMOUNT__VALUE:
				setValue((Double)newValue);
				return;
			case TrackerPackage.AMOUNT__WISHED_DATE:
				setWishedDate((LocalDate)newValue);
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
			case TrackerPackage.AMOUNT__CATEGORY:
				setCategory((Category)null);
				return;
			case TrackerPackage.AMOUNT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case TrackerPackage.AMOUNT__WISHED_DATE:
				setWishedDate(WISHED_DATE_EDEFAULT);
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
			case TrackerPackage.AMOUNT__CATEGORY:
				return category != null;
			case TrackerPackage.AMOUNT__VALUE:
				return value != VALUE_EDEFAULT;
			case TrackerPackage.AMOUNT__WISHED_DATE:
				return WISHED_DATE_EDEFAULT == null ? wishedDate != null : !WISHED_DATE_EDEFAULT.equals(wishedDate);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result=new StringBuffer(super.toString());
		result.append(" (value: ");
		result.append(value);
		result.append(", wishedDate: ");
		result.append(wishedDate);
		result.append(')');
		return result.toString();
	}

} //AmountImpl
