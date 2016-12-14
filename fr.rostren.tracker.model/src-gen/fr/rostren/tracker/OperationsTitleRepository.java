/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operations Title Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.OperationsTitleRepository#getOperationsTitles <em>Operations Titles</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOperationsTitleRepository()
 * @model
 * @generated
 */
public interface OperationsTitleRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Operations Titles</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.OperationTitle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations Titles</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations Titles</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getOperationsTitleRepository_OperationsTitles()
	 * @model containment="true"
	 * @generated
	 */
	EList<OperationTitle> getOperationsTitles();

} // OperationsTitleRepository
