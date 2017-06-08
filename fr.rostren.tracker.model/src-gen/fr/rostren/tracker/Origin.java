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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Origin</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.Origin#getType <em>Type</em>}</li>
 *   <li>{@link fr.rostren.tracker.Origin#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link fr.rostren.tracker.Origin#getOperations <em>Operations</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOrigin()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='isTyped hasIdentifier isLinkedToOperations'"
 * @generated
 */
public interface Origin extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The literals
	 * are from the enumeration {@link fr.rostren.tracker.OriginType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see fr.rostren.tracker.OriginType
	 * @see #setType(OriginType)
	 * @see fr.rostren.tracker.TrackerPackage#getOrigin_Type()
	 * @model
	 * @generated
	 */
	OriginType getType();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Origin#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see fr.rostren.tracker.OriginType
	 * @see #getType()
	 * @generated
	 */
	void setType(OriginType value);

	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see fr.rostren.tracker.TrackerPackage#getOrigin_Identifier()
	 * @model
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.Origin#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' reference list. The
	 * list contents are of type {@link fr.rostren.tracker.Operation}. It is
	 * bidirectional and its opposite is '
	 * {@link fr.rostren.tracker.Operation#getOrigin <em>Origin</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Operations</em>' reference list.
	 * @see fr.rostren.tracker.TrackerPackage#getOrigin_Operations()
	 * @see fr.rostren.tracker.Operation#getOrigin
	 * @model opposite="origin"
	 * @generated
	 */
	EList<Operation> getOperations();

} // Origin
