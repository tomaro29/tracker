/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Checking Account</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.CheckingAccount#getOperations <em>Operations</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getCheckingAccount()
 * @model
 * @generated
 */
public interface CheckingAccount extends Account {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getCheckingAccount_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

} // CheckingAccount
