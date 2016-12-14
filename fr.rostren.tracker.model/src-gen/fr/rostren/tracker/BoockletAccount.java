/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Boocklet Account</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.BoockletAccount#getTransfers <em>Transfers</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getBoockletAccount()
 * @model
 * @generated
 */
public interface BoockletAccount extends Account {
	/**
	 * Returns the value of the '<em><b>Transfers</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Transfer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfers</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfers</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getBoockletAccount_Transfers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transfer> getTransfers();

} // BoockletAccount
