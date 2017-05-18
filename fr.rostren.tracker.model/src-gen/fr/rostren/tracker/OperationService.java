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

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.model.utils.OperationData;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.OperationService#getOperation <em>Operation</em>}</li>
 * </ul>
 *
 * @see fr.rostren.tracker.TrackerPackage#getOperationService()
 * @model
 * @generated
 */
public interface OperationService extends EObject {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see fr.rostren.tracker.TrackerPackage#getOperationService_Operation()
	 * @model required="true"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link fr.rostren.tracker.OperationService#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double sumAmounts(Operation operation);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void addSubAmount(double amount);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void addSubAmount(double amount, Category category);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void removeSubAmount(Amount amount);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validateAmounts();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getOperationTitleAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getOperationTotalAmount();

	/**
	 * Adapts an {@link OperationData} instance to an {@link Operation} instance
	 * @param operation the operation data to adapt
	 * @return the adapted {@link Operation} instance
	 */
	Operation adaptOperation(OperationData operationData);

	/**
	 * Adapts an {@link Operation} instance to an {@link OperationData} instance
	 * @param operation the operation to adapt
	 * @return the adapted {@link OperationData} instance
	 */
	OperationData adaptOperation();

	/**
	 * Returns the {@link OperationTitle} instance
	 * @param operation the operation
	 * @param title the operation title as a {@link String}
	 * @return the {@link OperationTitle} instance
	 */
	Optional<OperationTitle> findOperationTitle(String title);

	/**
	 * Returns the operation origin
	 * @param operation the operation
	 * @param originId the origin Id
	 * @return the operation origin
	 */
	Optional<Origin> findOperationOrigin(String originId);

} // OperationService
