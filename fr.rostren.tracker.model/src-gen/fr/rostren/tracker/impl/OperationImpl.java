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

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerPackage;

import java.time.LocalDate;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.OperationImpl#getTotalAmount <em>Total Amount</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OperationImpl#getOperationTitle <em>Operation Title</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OperationImpl#getSubAmounts <em>Sub Amounts</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OperationImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OperationImpl#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class OperationImpl extends EObjectImpl implements Operation {
	/**
	 * The default value of the '{@link #getTotalAmount() <em>Total Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalAmount()
	 * @generated
	 * @ordered
	 */
	protected static final double TOTAL_AMOUNT_EDEFAULT=0.0;

	/**
	 * The cached value of the '{@link #getTotalAmount() <em>Total Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalAmount()
	 * @generated
	 * @ordered
	 */
	protected double totalAmount=TOTAL_AMOUNT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOperationTitle() <em>Operation Title</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationTitle()
	 * @generated
	 * @ordered
	 */
	protected OperationTitle operationTitle;

	/**
	 * The cached value of the '{@link #getSubAmounts() <em>Sub Amounts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubAmounts()
	 * @generated
	 * @ordered
	 */
	protected EList<Amount> subAmounts;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected Origin origin;

	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final LocalDate DATE_EDEFAULT=null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected LocalDate date=DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTotalAmount(double newTotalAmount) {
		double oldTotalAmount=totalAmount;
		totalAmount=newTotalAmount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION__TOTAL_AMOUNT, oldTotalAmount, totalAmount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationTitle getOperationTitle() {
		if (operationTitle != null && operationTitle.eIsProxy()) {
			InternalEObject oldOperationTitle=(InternalEObject)operationTitle;
			operationTitle=(OperationTitle)eResolveProxy(oldOperationTitle);
			if (operationTitle != oldOperationTitle) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.OPERATION__OPERATION_TITLE, oldOperationTitle, operationTitle));
			}
		}
		return operationTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationTitle basicGetOperationTitle() {
		return operationTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperationTitle(OperationTitle newOperationTitle) {
		OperationTitle oldOperationTitle=operationTitle;
		operationTitle=newOperationTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION__OPERATION_TITLE, oldOperationTitle, operationTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Amount> getSubAmounts() {
		if (subAmounts == null) {
			subAmounts=new EObjectContainmentEList<Amount>(Amount.class, this, TrackerPackage.OPERATION__SUB_AMOUNTS);
		}
		return subAmounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Origin getOrigin() {
		if (origin != null && origin.eIsProxy()) {
			InternalEObject oldOrigin=(InternalEObject)origin;
			origin=(Origin)eResolveProxy(oldOrigin);
			if (origin != oldOrigin) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.OPERATION__ORIGIN, oldOrigin, origin));
			}
		}
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Origin basicGetOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrigin(Origin newOrigin, NotificationChain msgs) {
		Origin oldOrigin=origin;
		origin=newOrigin;
		if (eNotificationRequired()) {
			ENotificationImpl notification=new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION__ORIGIN, oldOrigin, newOrigin);
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
	public void setOrigin(Origin newOrigin) {
		if (newOrigin != origin) {
			NotificationChain msgs=null;
			if (origin != null)
				msgs=((InternalEObject)origin).eInverseRemove(this, TrackerPackage.ORIGIN__OPERATIONS, Origin.class, msgs);
			if (newOrigin != null)
				msgs=((InternalEObject)newOrigin).eInverseAdd(this, TrackerPackage.ORIGIN__OPERATIONS, Origin.class, msgs);
			msgs=basicSetOrigin(newOrigin, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION__ORIGIN, newOrigin, newOrigin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalDate getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(LocalDate newDate) {
		LocalDate oldDate=date;
		date=newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.OPERATION__ORIGIN:
				if (origin != null)
					msgs=((InternalEObject)origin).eInverseRemove(this, TrackerPackage.ORIGIN__OPERATIONS, Origin.class, msgs);
				return basicSetOrigin((Origin)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.OPERATION__SUB_AMOUNTS:
				return ((InternalEList<?>)getSubAmounts()).basicRemove(otherEnd, msgs);
			case TrackerPackage.OPERATION__ORIGIN:
				return basicSetOrigin(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.OPERATION__TOTAL_AMOUNT:
				return getTotalAmount();
			case TrackerPackage.OPERATION__OPERATION_TITLE:
				if (resolve)
					return getOperationTitle();
				return basicGetOperationTitle();
			case TrackerPackage.OPERATION__SUB_AMOUNTS:
				return getSubAmounts();
			case TrackerPackage.OPERATION__ORIGIN:
				if (resolve)
					return getOrigin();
				return basicGetOrigin();
			case TrackerPackage.OPERATION__DATE:
				return getDate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.OPERATION__TOTAL_AMOUNT:
				setTotalAmount((Double)newValue);
				return;
			case TrackerPackage.OPERATION__OPERATION_TITLE:
				setOperationTitle((OperationTitle)newValue);
				return;
			case TrackerPackage.OPERATION__SUB_AMOUNTS:
				getSubAmounts().clear();
				getSubAmounts().addAll((Collection<? extends Amount>)newValue);
				return;
			case TrackerPackage.OPERATION__ORIGIN:
				setOrigin((Origin)newValue);
				return;
			case TrackerPackage.OPERATION__DATE:
				setDate((LocalDate)newValue);
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
			case TrackerPackage.OPERATION__TOTAL_AMOUNT:
				setTotalAmount(TOTAL_AMOUNT_EDEFAULT);
				return;
			case TrackerPackage.OPERATION__OPERATION_TITLE:
				setOperationTitle((OperationTitle)null);
				return;
			case TrackerPackage.OPERATION__SUB_AMOUNTS:
				getSubAmounts().clear();
				return;
			case TrackerPackage.OPERATION__ORIGIN:
				setOrigin((Origin)null);
				return;
			case TrackerPackage.OPERATION__DATE:
				setDate(DATE_EDEFAULT);
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
			case TrackerPackage.OPERATION__TOTAL_AMOUNT:
				return totalAmount != TOTAL_AMOUNT_EDEFAULT;
			case TrackerPackage.OPERATION__OPERATION_TITLE:
				return operationTitle != null;
			case TrackerPackage.OPERATION__SUB_AMOUNTS:
				return subAmounts != null && !subAmounts.isEmpty();
			case TrackerPackage.OPERATION__ORIGIN:
				return origin != null;
			case TrackerPackage.OPERATION__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
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
		result.append(" (totalAmount: ");
		result.append(totalAmount);
		result.append(", date: ");
		result.append(date);
		result.append(')');
		return result.toString();
	}

} //OperationImpl
