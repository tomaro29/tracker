/**
 */
package fr.rostren.tracker;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Transfer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.Transfer#getIncomingAccount <em>Incoming Account</em>}</li>
 *   <li>{@link fr.rostren.tracker.Transfer#getOutgoingAccount <em>Outgoing Account</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getTransfer()
 * @model abstract="true"
 * @generated
 */
public interface Transfer extends Operation {
	/**
	 * Returns the value of the '<em><b>Incoming Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Account</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Account</em>' reference.
	 * @see #setIncomingAccount(Account)
	 * @see fr.rostren.tracker.TrackerPackage#getTransfer_IncomingAccount()
	 * @model
	 * @generated
	 */
	Account getIncomingAccount();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Transfer#getIncomingAccount <em>Incoming Account</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Incoming Account</em>' reference.
	 * @see #getIncomingAccount()
	 * @generated
	 */
	void setIncomingAccount(Account value);

	/**
	 * Returns the value of the '<em><b>Outgoing Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Account</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Account</em>' reference.
	 * @see #setOutgoingAccount(Account)
	 * @see fr.rostren.tracker.TrackerPackage#getTransfer_OutgoingAccount()
	 * @model
	 * @generated
	 */
	Account getOutgoingAccount();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Transfer#getOutgoingAccount <em>Outgoing Account</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Outgoing Account</em>' reference.
	 * @see #getOutgoingAccount()
	 * @generated
	 */
	void setOutgoingAccount(Account value);

} // Transfer
