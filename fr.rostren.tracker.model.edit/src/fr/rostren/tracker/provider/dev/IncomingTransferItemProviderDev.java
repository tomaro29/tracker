/**
 */
package fr.rostren.tracker.provider.dev;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.IncomingTransfer;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.provider.IncomingTransferItemProvider;

import java.math.BigDecimal;
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

/**
 * This is the item provider adapter for a
 * {@link fr.rostren.tracker.IncomingTransfer} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public class IncomingTransferItemProviderDev extends
		IncomingTransferItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param adapterFactory
	 *            the factory
	 */
	public IncomingTransferItemProviderDev(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain,
			final EObject owner, EStructuralFeature feature, Object value,
			int index) {
		Command cmd = null;
		if (feature == null) {
			cmd = super.createSetCommand(domain, owner, feature, value, index);
		}

		final int featureId = feature.getFeatureID();
		if (featureId != TrackerPackage.INCOMING_TRANSFER__TOTAL_AMOUNT) {
			cmd = super.createSetCommand(domain, owner, feature, value, index);
		}

		final IncomingTransfer incomingTransfer = (IncomingTransfer) owner;
		final BigDecimal newTotalAmount = (BigDecimal) value;

		if (incomingTransfer.getTotalAmount() == newTotalAmount) {
			cmd = super.createSetCommand(domain, owner, feature, value, index);
		}
		if (cmd != null) {
			return cmd;
		}

		// The total Amount has changed, we have to update existing value if
		// any
		final List<Command> commands = new ArrayList<Command>();

		List<Amount> subAmounts = incomingTransfer.getSubAmounts();
		if (subAmounts.size() == 1) {
			Amount subAmount = incomingTransfer.getSubAmounts().get(0);
			subAmount.setSubAmount(newTotalAmount);
		} else {
			// TODO Afficher un message de warning pour mettre à jour les
			// subAmounts
		}

		commands.add(super.createSetCommand(domain, owner, feature, value,
				index));

		return new CompoundCommand(commands) {
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singleton(owner);
			}
		};
	}
}
