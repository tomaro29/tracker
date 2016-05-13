/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
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
 * An implementation of the model object '<em><b>Categories Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.CategoriesRepositoryImpl#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoriesRepositoryImpl extends EObjectImpl implements CategoriesRepository {
	/**
         * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list.
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
	protected CategoriesRepositoryImpl() {
                super();
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	public EList<Category> getCategories() {
                if (categories == null) {
                        categories = new EObjectContainmentEList<Category>(Category.class, this, TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES);
                }
                return categories;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES:
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
                        case TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES:
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
                        case TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES:
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
                        case TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES:
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
                        case TrackerPackage.CATEGORIES_REPOSITORY__CATEGORIES:
                                return categories != null && !categories.isEmpty();
                }
                return super.eIsSet(featureID);
        }

} //CategoriesRepositoryImpl
