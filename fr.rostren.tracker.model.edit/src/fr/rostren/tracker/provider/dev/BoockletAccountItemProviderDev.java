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

import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.provider.BoockletAccountItemProvider;

/**
 * This is the item provider adapter for a
 * {@link fr.rostren.tracker.BoockletAccount} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class BoockletAccountItemProviderDev extends BoockletAccountItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param adapterFactory the {@link AdapterFactory}
	 */
	public BoockletAccountItemProviderDev(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		Collection<?> children=super.getChildren(object);
		List<Transfer> transfers=children.stream().filter(child -> child instanceof Transfer).map(child -> (Transfer)child).collect(Collectors.toList());
		Collections.sort(transfers, (transfer1, transfer2) -> transfer1.getDate().compareTo(transfer2.getDate()));
		return transfers;
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, TrackerFactory.eINSTANCE.createIncoming((EObject)object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, TrackerFactory.eINSTANCE.createOutgoing((EObject)object)));
	}
}