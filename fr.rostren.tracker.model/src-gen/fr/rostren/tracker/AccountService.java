/**
 */
package fr.rostren.tracker;

import java.time.Month;
import java.util.List;

import org.eclipse.emf.common.util.EList;
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
	double sumPerCategory(Category category, Month month, int year, boolean wishedDated);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	double averagePerCategory(Category category, Month month, int year, boolean wishedDated);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double sumPerCategory(Category category, int year, boolean wishedDated);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double averagePerCategory(Category category, int year, boolean wishedDated);

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

	/**
	 * Returns the total amount
	 * @param months the months to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @param clazz the class type of the amount {@link Category}.
	 * @return the total amount of all typed categories
	 */
	List<Double> findAllCategoriesAmount(List<String> months, int year, boolean wishedEnabled, Class<?> clazz);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthsMany="true"
	 * @generated
	 */
	EList<Double> findIncomeCategoryAmounts(String item, EList<String> months, int year, boolean wishedDated);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthsMany="true"
	 * @generated
	 */
	EList<Double> findSpendingCategoryAmounts(String item, EList<String> months, int year, boolean wishedDated);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	EList<Amount> findCategoryAmounts(Category category, Month month, int year, boolean wishedDated);

} // AccountService
