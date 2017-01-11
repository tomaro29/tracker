/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spending Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.SpendingCategory#getSpendings <em>Spendings</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getSpendingCategory()
 * @model
 * @generated
 */
public interface SpendingCategory extends Category {
	/**
	 * Returns the value of the '<em><b>Spendings</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.SpendingCategory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spendings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spendings</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getSpendingCategory_Spendings()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpendingCategory> getSpendings();

} // SpendingCategory
