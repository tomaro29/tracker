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

import java.time.LocalDate;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.Operation#getTotalAmount <em>Total Amount</em>}</li>
 *   <li>{@link fr.rostren.tracker.Operation#getOperationTitle <em>Operation Title</em>}</li>
 *   <li>{@link fr.rostren.tracker.Operation#getSubAmounts <em>Sub Amounts</em>}</li>
 *   <li>{@link fr.rostren.tracker.Operation#getOrigin <em>Origin</em>}</li>
 *   <li>{@link fr.rostren.tracker.Operation#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOperation()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='hasAmount hasTitle hasSubAmount hasOrigin hasDate hasValidAmount'"
 * @generated
 */
public interface Operation extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(LocalDate)
	 * @see fr.rostren.tracker.TrackerPackage#getOperation_Date()
	 * @model dataType="fr.rostren.tracker.Date"
	 * @generated
	 */
	LocalDate getDate();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Operation#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(LocalDate value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.rostren.tracker.Origin#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' reference.
	 * @see #setOrigin(Origin)
	 * @see fr.rostren.tracker.TrackerPackage#getOperation_Origin()
	 * @see fr.rostren.tracker.Origin#getOperations
	 * @model opposite="operations" required="true"
	 * @generated
	 */
	Origin getOrigin();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Operation#getOrigin
	 * <em>Origin</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @param value
	 *            the new value of the '<em>Origin</em>' reference.
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(Origin value);

	/**
	 * Returns the value of the '<em><b>Total Amount</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Amount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Total Amount</em>' attribute.
	 * @see #setTotalAmount(double)
	 * @see fr.rostren.tracker.TrackerPackage#getOperation_TotalAmount()
	 * @model
	 * @generated
	 */
	double getTotalAmount();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Operation#getTotalAmount <em>Total Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Amount</em>' attribute.
	 * @see #getTotalAmount()
	 * @generated
	 */
	void setTotalAmount(double value);

	/**
	 * Returns the value of the '<em><b>Operation Title</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Title</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Title</em>' reference.
	 * @see #setOperationTitle(OperationTitle)
	 * @see fr.rostren.tracker.TrackerPackage#getOperation_OperationTitle()
	 * @model required="true"
	 * @generated
	 */
	OperationTitle getOperationTitle();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Operation#getOperationTitle <em>Operation Title</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Operation Title</em>' reference.
	 * @see #getOperationTitle()
	 * @generated
	 */
	void setOperationTitle(OperationTitle value);

	/**
	 * Returns the value of the '<em><b>Sub Amounts</b></em>' containment reference list.
	 * The list contents are of type {@link fr.rostren.tracker.Amount}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Amounts</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Amounts</em>' containment reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getOperation_SubAmounts()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Amount> getSubAmounts();

} // Operation
