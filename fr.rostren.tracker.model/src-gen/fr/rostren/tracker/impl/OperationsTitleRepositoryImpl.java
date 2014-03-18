/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.TrackerPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operations Title Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.OperationsTitleRepositoryImpl#getOperationsTitles <em>Operations Titles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationsTitleRepositoryImpl extends EObjectImpl implements OperationsTitleRepository {
	/**
	 * The cached value of the '{@link #getOperationsTitles() <em>Operations Titles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationsTitles()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationTitle> operationsTitles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationsTitleRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationTitle> getOperationsTitles() {
		if (operationsTitles == null) {
			operationsTitles = new EObjectContainmentEList<OperationTitle>(OperationTitle.class, this, TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES);
		}
		return operationsTitles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES:
				return ((InternalEList<?>)getOperationsTitles()).basicRemove(otherEnd, msgs);
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
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES:
				return getOperationsTitles();
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
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES:
				getOperationsTitles().clear();
				getOperationsTitles().addAll((Collection<? extends OperationTitle>)newValue);
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
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES:
				getOperationsTitles().clear();
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
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES:
				return operationsTitles != null && !operationsTitles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationsTitleRepositoryImpl
