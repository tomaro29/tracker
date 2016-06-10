/**
 */
package fr.rostren.tracker.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Origins Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link fr.rostren.tracker.impl.OriginsRepositoryImpl#getOrigins
 * <em>Origins</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OriginsRepositoryImpl extends EObjectImpl implements OriginsRepository {
    /**
     * The cached value of the '{@link #getOrigins() <em>Origins</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOrigins()
     * @generated
     * @ordered
     */
    protected EList<Origin> origins;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected OriginsRepositoryImpl() {
	super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
	return TrackerPackage.Literals.ORIGINS_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Origin> getOrigins() {
	if (origins == null) {
	    origins = new EObjectContainmentEList<Origin>(Origin.class, this,
		    TrackerPackage.ORIGINS_REPOSITORY__ORIGINS);
	}
	return origins;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
	switch (featureID) {
	case TrackerPackage.ORIGINS_REPOSITORY__ORIGINS:
	    return ((InternalEList<?>) getOrigins()).basicRemove(otherEnd, msgs);
	}
	return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
	switch (featureID) {
	case TrackerPackage.ORIGINS_REPOSITORY__ORIGINS:
	    return getOrigins();
	}
	return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
	switch (featureID) {
	case TrackerPackage.ORIGINS_REPOSITORY__ORIGINS:
	    getOrigins().clear();
	    getOrigins().addAll((Collection<? extends Origin>) newValue);
	    return;
	}
	super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
	switch (featureID) {
	case TrackerPackage.ORIGINS_REPOSITORY__ORIGINS:
	    getOrigins().clear();
	    return;
	}
	super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
	switch (featureID) {
	case TrackerPackage.ORIGINS_REPOSITORY__ORIGINS:
	    return origins != null && !origins.isEmpty();
	}
	return super.eIsSet(featureID);
    }

} // OriginsRepositoryImpl
