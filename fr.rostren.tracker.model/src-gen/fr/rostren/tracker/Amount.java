/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Amount</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.rostren.tracker.Amount#getCategory <em>Category</em>}</li>
 *   <li>{@link fr.rostren.tracker.Amount#getSubAmount <em>Sub Amount</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.rostren.tracker.TrackerPackage#getAmount()
 * @model
 * @generated
 */
public interface Amount extends EObject {
	/**
	 * Returns the value of the '<em><b>Sub Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Amount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Amount</em>' attribute.
	 * @see #setSubAmount(float)
	 * @see fr.rostren.tracker.TrackerPackage#getAmount_SubAmount()
	 * @model
	 * @generated
	 */
	float getSubAmount();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Amount#getSubAmount <em>Sub Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Amount</em>' attribute.
	 * @see #getSubAmount()
	 * @generated
	 */
	void setSubAmount(float value);

	/**
	 * Returns the value of the '<em><b>Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' reference.
	 * @see #setCategory(Category)
	 * @see fr.rostren.tracker.TrackerPackage#getAmount_Category()
	 * @model required="true"
	 * @generated
	 */
	Category getCategory();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Amount#getCategory <em>Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' reference.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(Category value);

} // Amount
