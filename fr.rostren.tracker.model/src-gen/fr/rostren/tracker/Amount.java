/**
 */
package fr.rostren.tracker;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Amount</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.Amount#getCategory <em>Category</em>}</li>
 *   <li>{@link fr.rostren.tracker.Amount#getValue <em>Value</em>}</li>
 *   <li>{@link fr.rostren.tracker.Amount#getWishedDate <em>Wished Date</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getAmount()
 * @model
 * @generated
 */
public interface Amount extends EObject {
	/**
	 * Returns the value of the '<em><b>Category</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Category</em>' reference.
	 * @see #setCategory(Category)
	 * @see fr.rostren.tracker.TrackerPackage#getAmount_Category()
	 * @model required="true"
	 * @generated
	 */
	Category getCategory();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Amount#getCategory
	 * <em>Category</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Category</em>' reference.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(Category value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(BigDecimal)
	 * @see fr.rostren.tracker.TrackerPackage#getAmount_Value()
	 * @model
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Amount#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Returns the value of the '<em><b>Wished Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wished Date</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wished Date</em>' containment reference.
	 * @see #setWishedDate(Date)
	 * @see fr.rostren.tracker.TrackerPackage#getAmount_WishedDate()
	 * @model containment="true"
	 * @generated
	 */
	Date getWishedDate();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Amount#getWishedDate <em>Wished Date</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wished Date</em>' containment reference.
	 * @see #getWishedDate()
	 * @generated
	 */
	void setWishedDate(Date value);

} // Amount
