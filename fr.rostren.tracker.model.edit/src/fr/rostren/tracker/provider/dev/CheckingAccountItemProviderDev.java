/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.provider.CheckingAccountItemProvider;

/**
 * This is the item provider adapter for a
 * {@link fr.rostren.tracker.CheckingAccount} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public class CheckingAccountItemProviderDev extends CheckingAccountItemProvider {
	private static OperationTitle defaultOperationTitle;

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param adapterFactory the {@link AdapterFactory}
	 */
	public CheckingAccountItemProviderDev(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		Collection<?> children=super.getChildren(object);
		List<Operation> operations=children.stream().filter(child -> child instanceof Operation).map(child -> (Operation)child).collect(Collectors.toList());
		Collections.sort(operations, (op1, op2) -> op1.getDate().compareTo(op2.getDate()));
		return operations;
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, TrackerFactory.eINSTANCE.createCredit((EObject)object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, TrackerFactory.eINSTANCE.createDebit((EObject)object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, TrackerFactory.eINSTANCE.createIncoming((EObject)object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, TrackerFactory.eINSTANCE.createOutgoing((EObject)object)));
	}
}