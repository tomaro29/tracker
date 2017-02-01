/**
 */
package fr.rostren.tracker;

import java.time.Month;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Account Service</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see fr.rostren.tracker.TrackerPackage#getAccountService()
 * @model
 * @generated
 */
public interface AccountService extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	double sumPerCategory(Account account, Category category, Month month, int year);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model monthDataType="fr.rostren.tracker.Month"
	 * @generated
	 */
	double averagePerCategory(Account account, Category category, Month month, int year);

} // AccountService
