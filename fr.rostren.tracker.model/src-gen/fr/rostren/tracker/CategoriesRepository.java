/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Categories Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.CategoriesRepository#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getCategoriesRepository()
 * @model
 * @generated
 */
public interface CategoriesRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Category}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getCategoriesRepository_Categories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Category> getCategories();

} // CategoriesRepository
