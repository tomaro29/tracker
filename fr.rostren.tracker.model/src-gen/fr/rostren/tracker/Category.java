/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Category</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link fr.rostren.tracker.Category#getOperationTitles
 * <em>Operation Titles</em>}</li>
 * <li>{@link fr.rostren.tracker.Category#getDescription <em>Description</em>}
 * </li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends Title {
    /**
     * Returns the value of the '<em><b>Operation Titles</b></em>' reference
     * list. The list contents are of type
     * {@link fr.rostren.tracker.OperationTitle}. It is bidirectional and its
     * opposite is '{@link fr.rostren.tracker.OperationTitle#getCategories
     * <em>Categories</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Titles</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation Titles</em>' reference list.
     * @see fr.rostren.tracker.TrackerPackage#getCategory_OperationTitles()
     * @see fr.rostren.tracker.OperationTitle#getCategories
     * @model opposite="categories"
     * @generated
     */
    EList<OperationTitle> getOperationTitles();

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see fr.rostren.tracker.TrackerPackage#getCategory_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link fr.rostren.tracker.Category#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

} // Category
