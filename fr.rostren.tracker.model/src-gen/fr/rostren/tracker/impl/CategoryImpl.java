/**
 */
package fr.rostren.tracker.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link fr.rostren.tracker.impl.CategoryImpl#getOperationTitles
 * <em>Operation Titles</em>}</li>
 * <li>{@link fr.rostren.tracker.impl.CategoryImpl#getDescription
 * <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryImpl extends TitleImpl implements Category {
    /**
     * The cached value of the '{@link #getOperationTitles()
     * <em>Operation Titles</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getOperationTitles()
     * @generated
     * @ordered
     */
    protected EList<OperationTitle> operationTitles;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CategoryImpl() {
	super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
	return TrackerPackage.Literals.CATEGORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<OperationTitle> getOperationTitles() {
	if (operationTitles == null) {
	    operationTitles = new EObjectWithInverseResolvingEList.ManyInverse<OperationTitle>(OperationTitle.class,
		    this, TrackerPackage.CATEGORY__OPERATION_TITLES, TrackerPackage.OPERATION_TITLE__CATEGORIES);
	}
	return operationTitles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDescription() {
	return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDescription(String newDescription) {
	String oldDescription = description;
	description = newDescription;
	if (eNotificationRequired())
	    eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORY__DESCRIPTION, oldDescription,
		    description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
	switch (featureID) {
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    return ((InternalEList<InternalEObject>) (InternalEList<?>) getOperationTitles()).basicAdd(otherEnd, msgs);
	}
	return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
	switch (featureID) {
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    return ((InternalEList<?>) getOperationTitles()).basicRemove(otherEnd, msgs);
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
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    return getOperationTitles();
	case TrackerPackage.CATEGORY__DESCRIPTION:
	    return getDescription();
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
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    getOperationTitles().clear();
	    getOperationTitles().addAll((Collection<? extends OperationTitle>) newValue);
	    return;
	case TrackerPackage.CATEGORY__DESCRIPTION:
	    setDescription((String) newValue);
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
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    getOperationTitles().clear();
	    return;
	case TrackerPackage.CATEGORY__DESCRIPTION:
	    setDescription(DESCRIPTION_EDEFAULT);
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
	case TrackerPackage.CATEGORY__OPERATION_TITLES:
	    return operationTitles != null && !operationTitles.isEmpty();
	case TrackerPackage.CATEGORY__DESCRIPTION:
	    return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
	}
	return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
	if (eIsProxy())
	    return super.toString();

	StringBuffer result = new StringBuffer(super.toString());
	result.append(" (description: ");
	result.append(description);
	result.append(')');
	return result.toString();
    }

} // CategoryImpl
