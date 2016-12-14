/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Origins Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.OriginsRepository#getOrigins <em>Origins</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOriginsRepository()
 * @model
 * @generated
 */
public interface OriginsRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Origins</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Origin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origins</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origins</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getOriginsRepository_Origins()
	 * @model containment="true"
	 * @generated
	 */
	EList<Origin> getOrigins();

} // OriginsRepository
