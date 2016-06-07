/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Category Service</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see fr.rostren.tracker.TrackerPackage#getCategoryService()
 * @model
 * @generated
 */
public interface CategoryService extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void addCategory(String title);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void deleteCategory(Category category);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void addCategoryOperation(String operationTitle);

} // CategoryService
