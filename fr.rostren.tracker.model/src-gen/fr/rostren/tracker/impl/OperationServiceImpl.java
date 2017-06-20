/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operation Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.OperationServiceImpl#getOperation <em>Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationServiceImpl extends EObjectImpl implements OperationService {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	private static final BiMap<OperationData, Operation> operationsBiMap=HashBiMap.create();

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.OPERATION_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getOperation() {
		if (operation != null && operation.eIsProxy()) {
			InternalEObject oldOperation=(InternalEObject)operation;
			operation=(Operation)eResolveProxy(oldOperation);
			if (operation != oldOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.OPERATION_SERVICE__OPERATION, oldOperation, operation));
			}
		}
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperation(Operation newOperation) {
		Operation oldOperation=operation;
		operation=newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OPERATION_SERVICE__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double sumAmounts(Operation operation) {
		return operation.getSubAmounts().stream().mapToDouble(subAmount -> subAmount.getValue()).sum();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addSubAmount(double amount) {
		operation.getSubAmounts().add(TrackerFactory.eINSTANCE.createAmount(operation, amount, null));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addSubAmount(double amount, Category category) {
		operation.getSubAmounts().add(TrackerFactory.eINSTANCE.createAmount(operation, amount, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeSubAmount(Amount amount) {
		operation.getSubAmounts().add(amount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean validateAmounts() {
		return operation.getTotalAmount() == sumAmounts(operation);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public OperationData adaptOperation() {
		if (OperationServiceImpl.operationsBiMap.containsValue(operation)) {
			return OperationServiceImpl.operationsBiMap.inverse().get(operation);
		}
		OperationData operationData=new OperationData(OperationType.valueOf(operation.eClass().getName().toUpperCase()), operation.getOperationTitle(), operation.getTotalAmount(),
				operation.getDate(), operation.getOrigin(), operation.getSubAmounts());
		OperationServiceImpl.operationsBiMap.put(operationData, operation);
		return operationData;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public Operation adaptOperation(OperationData operatioData) {
		if (OperationServiceImpl.operationsBiMap.containsKey(operatioData)) {
			return OperationServiceImpl.operationsBiMap.get(operatioData);
		}
		if ((operation == null || operation instanceof Debit) && OperationType.CREDIT.equals(operatioData.getType())) {
			operation=TrackerFactory.eINSTANCE.createCredit();
		}
		else if ((operation == null || operation instanceof Credit) && OperationType.DEBIT.equals(operatioData.getType())) {
			operation=TrackerFactory.eINSTANCE.createDebit();
		}

		operation.setDate(operatioData.getDate());
		operation.setOperationTitle(operatioData.getOperationTitle());
		operation.setOrigin(operatioData.getOrigin());
		operation.setTotalAmount(operatioData.getTotalAmount());
		operation.getSubAmounts().addAll(operatioData.getSubAmounts());
		OperationServiceImpl.operationsBiMap.put(operatioData, operation);
		return operation;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public Optional<OperationTitle> findOperationTitle(String title) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}

		TrackerService service=TrackerUtils.getTrackerService(operation);
		return service.findOperationTitle(title);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public Optional<Origin> findOperationOrigin(String originId) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(originId) || StringUtils.isBlank(originId)) {
			return null;
		}
		Tracker tracker=TrackerUtils.getTracker(operation);
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		return tracker.getOriginsRepository().getOrigins().stream()//
				.filter(origin -> originId.equals(origin.getIdentifier()))//
				.findFirst();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the operation title as a {@link String}
	 * @param operationOpt the operation {@link Optional} instance
	 * @return the operation title as a {@link String}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getOperationTitleAsString() {
		return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the operation total amount as a {@link String}
	 * @param operationOpt the operation {@link Optional} instance
	 * @return the operation total amount as a {@link String}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getOperationTotalAmount() {
		return operation.getTotalAmount() == 0 ? StringUtils.EMPTY : String.valueOf(operation.getTotalAmount());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.OPERATION_SERVICE__OPERATION:
				if (resolve)
					return getOperation();
				return basicGetOperation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.OPERATION_SERVICE__OPERATION:
				setOperation((Operation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TrackerPackage.OPERATION_SERVICE__OPERATION:
				setOperation((Operation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TrackerPackage.OPERATION_SERVICE__OPERATION:
				return operation != null;
		}
		return super.eIsSet(featureID);
	}

} // OperationServiceImpl
