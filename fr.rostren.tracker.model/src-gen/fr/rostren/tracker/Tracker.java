/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tracker</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link fr.rostren.tracker.Tracker#getOwners <em>Owners</em>}</li>
 * <li>{@link fr.rostren.tracker.Tracker#getOriginsRepository
 * <em>Origins Repository</em>}</li>
 * <li>{@link fr.rostren.tracker.Tracker#getCategoriesRepository
 * <em>Categories Repository</em>}</li>
 * <li>{@link fr.rostren.tracker.Tracker#getOperationsTitlesRepositories
 * <em>Operations Titles Repositories</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getTracker()
 * @model
 * @generated
 */
public interface Tracker extends EObject {
    /**
     * Returns the value of the '<em><b>Owners</b></em>' containment reference
     * list. The list contents are of type {@link fr.rostren.tracker.Owner}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owners</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Owners</em>' containment reference list.
     * @see fr.rostren.tracker.TrackerPackage#getTracker_Owners()
     * @model containment="true"
     * @generated
     */
    EList<Owner> getOwners();

    /**
     * Returns the value of the '<em><b>Origins Repository</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origins Repository</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Origins Repository</em>' containment
     *         reference.
     * @see #setOriginsRepository(OriginsRepository)
     * @see fr.rostren.tracker.TrackerPackage#getTracker_OriginsRepository()
     * @model containment="true" required="true"
     * @generated
     */
    OriginsRepository getOriginsRepository();

    /**
     * Sets the value of the '
     * {@link fr.rostren.tracker.Tracker#getOriginsRepository
     * <em>Origins Repository</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Origins Repository</em>' containment
     *            reference.
     * @see #getOriginsRepository()
     * @generated
     */
    void setOriginsRepository(OriginsRepository value);

    /**
     * Returns the value of the '<em><b>Categories Repository</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Categories Repository</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Categories Repository</em>' containment
     *         reference.
     * @see #setCategoriesRepository(CategoriesRepository)
     * @see fr.rostren.tracker.TrackerPackage#getTracker_CategoriesRepository()
     * @model containment="true" required="true"
     * @generated
     */
    CategoriesRepository getCategoriesRepository();

    /**
     * Sets the value of the '
     * {@link fr.rostren.tracker.Tracker#getCategoriesRepository
     * <em>Categories Repository</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Categories Repository</em>'
     *            containment reference.
     * @see #getCategoriesRepository()
     * @generated
     */
    void setCategoriesRepository(CategoriesRepository value);

    /**
     * Returns the value of the '<em><b>Operations Titles Repositories</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operations Titles Repositories</em>' reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operations Titles Repositories</em>'
     *         containment reference.
     * @see #setOperationsTitlesRepositories(OperationsTitleRepository)
     * @see fr.rostren.tracker.TrackerPackage#getTracker_OperationsTitlesRepositories()
     * @model containment="true" required="true"
     * @generated
     */
    OperationsTitleRepository getOperationsTitlesRepositories();

    /**
     * Sets the value of the '
     * {@link fr.rostren.tracker.Tracker#getOperationsTitlesRepositories
     * <em>Operations Titles Repositories</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operations Titles Repositories</em>'
     *            containment reference.
     * @see #getOperationsTitlesRepositories()
     * @generated
     */
    void setOperationsTitlesRepositories(OperationsTitleRepository value);

} // Tracker
