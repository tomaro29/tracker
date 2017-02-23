/**
 */
package fr.rostren.tracker;

import java.time.Month;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Account Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.AccountService#getAccount <em>Account</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getAccountService()
 * @model
 * @generated
 */
public interface AccountService extends EObject {
	/**
	 * Returns the value of the '<em><b>Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Account</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Account</em>' reference.
	 * @see #setAccount(Account)
	 * @see fr.rostren.tracker.TrackerPackage#getAccountService_Account()
	 * @model required="true"
	 * @generated
	 */
	Account getAccount();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.AccountService#getAccount <em>Account</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Account</em>' reference.
	 * @see #getAccount()
	 * @generated
	 */
	void setAccount(Account value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	double sumPerCategory(Category category, Month month, int year);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	double averagePerCategory(Category category, Month month, int year);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double sumPerCategory(Category category, int year);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double averagePerCategory(Category category, int year);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double sumPerCategory(Category category);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double averagePerCategory(Category category);

} // AccountService
