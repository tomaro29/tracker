/**
 */
package fr.rostren.tracker.impl;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.TrackerImpl#getOwners <em>Owners</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.TrackerImpl#getOriginsRepository <em>Origins Repository</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.TrackerImpl#getCategoriesRepository <em>Categories Repository</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.TrackerImpl#getOperationsTitlesRepositories <em>Operations Titles Repositories</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackerImpl extends EObjectImpl implements Tracker {
	/**
	 * The cached value of the '{@link #getOwners() <em>Owners</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwners()
	 * @generated
	 * @ordered
	 */
	protected EList<Owner> owners;

	/**
	 * The cached value of the '{@link #getOriginsRepository() <em>Origins Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginsRepository()
	 * @generated
	 * @ordered
	 */
	protected OriginsRepository originsRepository;

	/**
	 * The cached value of the '{@link #getCategoriesRepository() <em>Categories Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoriesRepository()
	 * @generated
	 * @ordered
	 */
	protected CategoriesRepository categoriesRepository;

	/**
	 * The cached value of the '{@link #getOperationsTitlesRepositories() <em>Operations Titles Repositories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationsTitlesRepositories()
	 * @generated
	 * @ordered
	 */
	protected OperationsTitleRepository operationsTitlesRepositories;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TRACKER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Owner> getOwners() {
		if (owners == null) {
			owners = new EObjectContainmentEList<Owner>(Owner.class, this, TrackerPackage.TRACKER__OWNERS);
		}
		return owners;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OriginsRepository getOriginsRepository() {
		return originsRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOriginsRepository(OriginsRepository newOriginsRepository, NotificationChain msgs) {
		OriginsRepository oldOriginsRepository = originsRepository;
		originsRepository = newOriginsRepository;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__ORIGINS_REPOSITORY, oldOriginsRepository, newOriginsRepository);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginsRepository(OriginsRepository newOriginsRepository) {
		if (newOriginsRepository != originsRepository) {
			NotificationChain msgs = null;
			if (originsRepository != null)
				msgs = ((InternalEObject)originsRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__ORIGINS_REPOSITORY, null, msgs);
			if (newOriginsRepository != null)
				msgs = ((InternalEObject)newOriginsRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__ORIGINS_REPOSITORY, null, msgs);
			msgs = basicSetOriginsRepository(newOriginsRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__ORIGINS_REPOSITORY, newOriginsRepository, newOriginsRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoriesRepository getCategoriesRepository() {
		return categoriesRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCategoriesRepository(CategoriesRepository newCategoriesRepository, NotificationChain msgs) {
		CategoriesRepository oldCategoriesRepository = categoriesRepository;
		categoriesRepository = newCategoriesRepository;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__CATEGORIES_REPOSITORY, oldCategoriesRepository, newCategoriesRepository);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategoriesRepository(CategoriesRepository newCategoriesRepository) {
		if (newCategoriesRepository != categoriesRepository) {
			NotificationChain msgs = null;
			if (categoriesRepository != null)
				msgs = ((InternalEObject)categoriesRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__CATEGORIES_REPOSITORY, null, msgs);
			if (newCategoriesRepository != null)
				msgs = ((InternalEObject)newCategoriesRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__CATEGORIES_REPOSITORY, null, msgs);
			msgs = basicSetCategoriesRepository(newCategoriesRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__CATEGORIES_REPOSITORY, newCategoriesRepository, newCategoriesRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationsTitleRepository getOperationsTitlesRepositories() {
		return operationsTitlesRepositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperationsTitlesRepositories(OperationsTitleRepository newOperationsTitlesRepositories, NotificationChain msgs) {
		OperationsTitleRepository oldOperationsTitlesRepositories = operationsTitlesRepositories;
		operationsTitlesRepositories = newOperationsTitlesRepositories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES, oldOperationsTitlesRepositories, newOperationsTitlesRepositories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationsTitlesRepositories(OperationsTitleRepository newOperationsTitlesRepositories) {
		if (newOperationsTitlesRepositories != operationsTitlesRepositories) {
			NotificationChain msgs = null;
			if (operationsTitlesRepositories != null)
				msgs = ((InternalEObject)operationsTitlesRepositories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES, null, msgs);
			if (newOperationsTitlesRepositories != null)
				msgs = ((InternalEObject)newOperationsTitlesRepositories).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES, null, msgs);
			msgs = basicSetOperationsTitlesRepositories(newOperationsTitlesRepositories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES, newOperationsTitlesRepositories, newOperationsTitlesRepositories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TrackerPackage.TRACKER__OWNERS:
				return ((InternalEList<?>)getOwners()).basicRemove(otherEnd, msgs);
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
				return basicSetOriginsRepository(null, msgs);
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
				return basicSetCategoriesRepository(null, msgs);
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				return basicSetOperationsTitlesRepositories(null, msgs);
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
			case TrackerPackage.TRACKER__OWNERS:
				return getOwners();
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
				return getOriginsRepository();
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
				return getCategoriesRepository();
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				return getOperationsTitlesRepositories();
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
			case TrackerPackage.TRACKER__OWNERS:
				getOwners().clear();
				getOwners().addAll((Collection<? extends Owner>)newValue);
				return;
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
				setOriginsRepository((OriginsRepository)newValue);
				return;
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
				setCategoriesRepository((CategoriesRepository)newValue);
				return;
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				setOperationsTitlesRepositories((OperationsTitleRepository)newValue);
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
			case TrackerPackage.TRACKER__OWNERS:
				getOwners().clear();
				return;
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
				setOriginsRepository((OriginsRepository)null);
				return;
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
				setCategoriesRepository((CategoriesRepository)null);
				return;
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				setOperationsTitlesRepositories((OperationsTitleRepository)null);
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
			case TrackerPackage.TRACKER__OWNERS:
				return owners != null && !owners.isEmpty();
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
				return originsRepository != null;
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
				return categoriesRepository != null;
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				return operationsTitlesRepositories != null;
		}
		return super.eIsSet(featureID);
	}

} //TrackerImpl
