/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.TrackerPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Spending Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.SpendingCategoryImpl#getSpendings <em>Spendings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpendingCategoryImpl extends CategoryImpl implements SpendingCategory {
	/**
	 * The cached value of the '{@link #getSpendings() <em>Spendings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpendings()
	 * @generated
	 * @ordered
	 */
	protected EList<SpendingCategory> spendings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpendingCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.SPENDING_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SpendingCategory> getSpendings() {
		if (spendings == null) {
			spendings=new EObjectContainmentEList<SpendingCategory>(SpendingCategory.class, this, TrackerPackage.SPENDING_CATEGORY__SPENDINGS);
		}
		return spendings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.SPENDING_CATEGORY__SPENDINGS:
				return ((InternalEList<?>)getSpendings()).basicRemove(otherEnd, msgs);
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
			case TrackerPackage.SPENDING_CATEGORY__SPENDINGS:
				return getSpendings();
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
			case TrackerPackage.SPENDING_CATEGORY__SPENDINGS:
				getSpendings().clear();
				getSpendings().addAll((Collection<? extends SpendingCategory>)newValue);
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
			case TrackerPackage.SPENDING_CATEGORY__SPENDINGS:
				getSpendings().clear();
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
			case TrackerPackage.SPENDING_CATEGORY__SPENDINGS:
				return spendings != null && !spendings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SpendingCategoryImpl
