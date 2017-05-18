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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.provider.IncomingItemProvider;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.Incoming}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 */
public class IncomingItemProviderDev extends IncomingItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param adapterFactory the {@link AdapterFactory}
	 */
	public IncomingItemProviderDev(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, final EObject owner, EStructuralFeature feature, Object value, int index) {
		if (feature == null) {
			return super.createSetCommand(domain, owner, feature, value, index);
		}

		final int featureId=feature.getFeatureID();
		if (featureId != TrackerPackage.INCOMING__TOTAL_AMOUNT) {
			return super.createSetCommand(domain, owner, feature, value, index);
		}

		final Incoming incoming=(Incoming)owner;
		final Double newTotalAmount=(Double)value;

		if (incoming.getTotalAmount() == newTotalAmount) {
			return super.createSetCommand(domain, owner, feature, value, index);
		}

		// The total Amount has changed, we have to update existing value if
		// any
		final List<Command> commands=new ArrayList<>();

		List<Amount> subAmounts=incoming.getSubAmounts();
		if (subAmounts.size() == 1) {
			Amount subAmount=incoming.getSubAmounts().get(0);
			subAmount.setValue(newTotalAmount);
		}
		else {
			// TODO Afficher un message de warning pour mettre a jour les
			// subAmounts
		}

		commands.add(super.createSetCommand(domain, owner, feature, value, index));

		return new CompoundCommand(commands) {
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singleton(owner);
			}
		};
	}
}