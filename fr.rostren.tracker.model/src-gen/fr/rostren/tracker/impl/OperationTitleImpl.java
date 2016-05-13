/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.Category;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.TrackerPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Title</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.OperationTitleImpl#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationTitleImpl extends TitleImpl implements OperationTitle {
	/**
         * The cached value of the '{@link #getCategories() <em>Categories</em>}' reference list.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getCategories()
         * @generated
         * @ordered
         */
	protected EList<Category> categories;

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	protected OperationTitleImpl() {
                super();
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	protected EClass eStaticClass() {
                return TrackerPackage.Literals.OPERATION_TITLE;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public EList<Category> getCategories() {
                if (categories == null) {
                        categories = new EObjectWithInverseResolvingEList.ManyInverse<Category>(Category.class, this, TrackerPackage.OPERATION_TITLE__CATEGORIES, TrackerPackage.CATEGORY__OPERATION_TITLES);
                }
                return categories;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCategories()).basicAdd(otherEnd, msgs);
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
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
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
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                return getCategories();
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
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                getCategories().clear();
                                getCategories().addAll((Collection<? extends Category>)newValue);
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
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                getCategories().clear();
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
                        case TrackerPackage.OPERATION_TITLE__CATEGORIES:
                                return categories != null && !categories.isEmpty();
                }
                return super.eIsSet(featureID);
        }

} //OperationTitleImpl
