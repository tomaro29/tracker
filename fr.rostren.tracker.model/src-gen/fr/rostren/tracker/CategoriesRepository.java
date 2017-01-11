/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Categories Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.CategoriesRepository#getIncome <em>Income</em>}</li>
 *   <li>{@link fr.rostren.tracker.CategoriesRepository#getSpending <em>Spending</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getCategoriesRepository()
 * @model
 * @generated
 */
public interface CategoriesRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Income</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Income</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Income</em>' containment reference.
	 * @see #setIncome(IncomeCategory)
	 * @see fr.rostren.tracker.TrackerPackage#getCategoriesRepository_Income()
	 * @model containment="true" required="true"
	 * @generated
	 */
	IncomeCategory getIncome();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.CategoriesRepository#getIncome <em>Income</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Income</em>' containment reference.
	 * @see #getIncome()
	 * @generated
	 */
	void setIncome(IncomeCategory value);

	/**
	 * Returns the value of the '<em><b>Spending</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spending</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spending</em>' containment reference.
	 * @see #setSpending(SpendingCategory)
	 * @see fr.rostren.tracker.TrackerPackage#getCategoriesRepository_Spending()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SpendingCategory getSpending();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.CategoriesRepository#getSpending <em>Spending</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spending</em>' containment reference.
	 * @see #getSpending()
	 * @generated
	 */
	void setSpending(SpendingCategory value);

} // CategoriesRepository
