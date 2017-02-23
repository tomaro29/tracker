/**
 */
package fr.rostren.tracker.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Category Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.CategoryServiceImpl#getCategory <em>Category</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryServiceImpl extends EObjectImpl implements CategoryService {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.CATEGORY_SERVICE;
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
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.CATEGORY_SERVICE__CATEGORY, oldCategory, category));
				}
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORY_SERVICE__CATEGORY, oldCategory, category));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addDescription(String description) {
		category.setDescription(description);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeDescription() {
		category.setDescription(null);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addOperationTitle(String title) {
		Tracker tracker=TrackerUtils.getTracker(category);
		List<OperationTitle> titles=TrackerUtils.getOperationsTitles(tracker).stream()//
				.filter(operationTitle -> operationTitle.getTitle() != null && operationTitle.getTitle().equals(title))//
				.collect(Collectors.toList());
		if (titles.isEmpty()) {
			OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle(tracker, title);
			category.getOperationTitles().add(operationTitle);
			return;
		}
		if (titles.size() > 1) {
			throw new IllegalArgumentException("The Tracker contains several Operation Titles for the same string !"); //$NON-NLS-1$
		}
		OperationTitle any=titles.get(0);
		if (!category.getOperationTitles().contains(any)) {
			category.getOperationTitles().add(any);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeOperationTitle(String title) {
		List<OperationTitle> titles=category.getOperationTitles().stream()//
				.filter(operationTitle -> operationTitle.getTitle() == null || title.equals(operationTitle.getTitle()))//
				.collect(Collectors.toList());
		category.getOperationTitles().removeAll(titles);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeOperationTitle(OperationTitle title) {
		if (category.getOperationTitles().contains(title)) {
			category.getOperationTitles().remove(title);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				if (resolve) {
					return getCategory();
				}
				return basicGetCategory();
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
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				setCategory((Category)newValue);
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
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				setCategory((Category)null);
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
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				return category != null;
		}
		return super.eIsSet(featureID);
	}

} // CategoryServiceImpl
