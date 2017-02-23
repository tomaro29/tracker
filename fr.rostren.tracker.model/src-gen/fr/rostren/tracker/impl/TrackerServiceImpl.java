/**
 */
package fr.rostren.tracker.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.TrackerServiceImpl#getTracker <em>Tracker</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TrackerServiceImpl extends EObjectImpl implements TrackerService {
	/**
	 * The cached value of the '{@link #getTracker() <em>Tracker</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracker()
	 * @generated
	 * @ordered
	 */
	protected Tracker tracker;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TRACKER_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tracker getTracker() {
		if (tracker != null && tracker.eIsProxy()) {
			InternalEObject oldTracker=(InternalEObject)tracker;
			tracker=(Tracker)eResolveProxy(oldTracker);
			if (tracker != oldTracker) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.TRACKER_SERVICE__TRACKER, oldTracker, tracker));
				}
			}
		}
		return tracker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tracker basicGetTracker() {
		return tracker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTracker(Tracker newTracker) {
		Tracker oldTracker=tracker;
		tracker=newTracker;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER_SERVICE__TRACKER, oldTracker, tracker));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Adds an income category instance to the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addIncomeCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		Optional<IncomeCategory> any=repository.getIncome().getIncomes().stream().filter(category -> category.getTitle().equals(title)).findAny();
		if (!any.isPresent()) {
			IncomeCategory incomeCategory=TrackerFactory.eINSTANCE.createIncomeCategory();
			incomeCategory.setTitle(title);
			repository.getIncome().getIncomes().add(incomeCategory);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Adds a spending category instance to the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addSpendingCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		Optional<SpendingCategory> any=repository.getSpending().getSpendings().stream().filter(category -> category.getTitle().equals(title)).findAny();
		if (!any.isPresent()) {
			SpendingCategory spendingCategory=TrackerFactory.eINSTANCE.createSpendingCategory();
			spendingCategory.setTitle(title);
			repository.getSpending().getSpendings().add(spendingCategory);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Removes the given category from the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteCategory(Category category) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		if (category instanceof IncomeCategory && repository.getIncome().getIncomes().contains(category)) {
			repository.getIncome().getIncomes().remove(category);
		}
		if (category instanceof SpendingCategory && repository.getSpending().getSpendings().contains(category)) {
			repository.getSpending().getSpendings().remove(category);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Removes all categories titled by the given title
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();

		List<IncomeCategory> incomes=repository.getIncome().getIncomes();
		List<Category> matches=incomes.stream().filter(category -> category.getTitle().equals(title)).collect(Collectors.toList());
		incomes.removeAll(matches);

		List<SpendingCategory> spendings=repository.getSpending().getSpendings();
		matches=spendings.stream().filter(category -> category.getTitle().equals(title)).collect(Collectors.toList());
		spendings.removeAll(matches);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addOwner(String firstName, String lastName) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOwner(Owner owner) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOwner(String firstName, String lastName) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addOrigin(String identifier) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOrigin(Origin origin) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOrigin(String identifier) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addOperationTitle(String title) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOperationTitle(OperationTitle operationTitle) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void deleteOperationTitle(String title) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				if (resolve) {
					return getTracker();
				}
				return basicGetTracker();
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				setTracker((Tracker)newValue);
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				setTracker((Tracker)null);
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				return tracker != null;
		}
		return super.eIsSet(featureID);
	}

} //TrackerServiceImpl
