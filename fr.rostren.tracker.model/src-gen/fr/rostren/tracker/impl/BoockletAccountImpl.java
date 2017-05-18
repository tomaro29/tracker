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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Boocklet Account</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.BoockletAccountImpl#getTransfers <em>Transfers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BoockletAccountImpl extends AccountImpl implements BoockletAccount {
	/**
	 * The cached value of the '{@link #getTransfers() <em>Transfers</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTransfers()
	 * @generated
	 * @ordered
	 */
	protected EList<Transfer> transfers;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected BoockletAccountImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.BOOCKLET_ACCOUNT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Transfer> getTransfers() {
		if (transfers == null) {
			transfers=new EObjectContainmentEList<Transfer>(Transfer.class, this, TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS);
		}
		return transfers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS:
				return ((InternalEList<?>)getTransfers()).basicRemove(otherEnd, msgs);
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
			case TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS:
				return getTransfers();
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
			case TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS:
				getTransfers().clear();
				getTransfers().addAll((Collection<? extends Transfer>)newValue);
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
			case TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS:
				getTransfers().clear();
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
			case TrackerPackage.BOOCKLET_ACCOUNT__TRANSFERS:
				return transfers != null && !transfers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // BoockletAccountImpl
