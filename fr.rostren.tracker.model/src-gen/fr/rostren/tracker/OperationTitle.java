/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Title</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.OperationTitle#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOperationTitle()
 * @model
 * @generated
 */
public interface OperationTitle extends Title {

	/**
	 * Returns the value of the '<em><b>Categories</b></em>' reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Category}.
	 * It is bidirectional and its opposite is '{@link fr.rostren.tracker.Category#getOperationTitles <em>Operation Titles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getOperationTitle_Categories()
	 * @see fr.rostren.tracker.Category#getOperationTitles
	 * @model opposite="operationTitles"
	 * @generated
	 */
	EList<Category> getCategories();
} // OperationTitle
