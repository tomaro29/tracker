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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Income Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.IncomeCategory#getIncomes <em>Incomes</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getIncomeCategory()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='hasCategories'"
 * @generated
 */
public interface IncomeCategory extends Category {
	/**
	 * Returns the value of the '<em><b>Incomes</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.IncomeCategory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incomes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incomes</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getIncomeCategory_Incomes()
	 * @model containment="true"
	 * @generated
	 */
	EList<IncomeCategory> getIncomes();

} // IncomeCategory
