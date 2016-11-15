/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
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

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		// create newIncoming
		Incoming newIncoming=createNewIncoming();

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, newIncoming));

		// create newOutgoing
		Outgoing newOutgoing=createNewOutgoing();

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, newOutgoing));
	}

	/**
	 * Creates a new {@link Incoming} instance
	 * @return the created {@link Incoming} instance
	 */
	private Incoming createNewIncoming() {
		Incoming newIncoming=TrackerFactory.eINSTANCE.createIncoming();
		// Add a default sub amount
		List<Amount> amounts=newIncoming.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		newIncoming.setOperationTitle(operationTitle);
		return newIncoming;
	}

	/**
	 * Creates a new {@link Outgoing} instance
	 * @return the created {@link Outgoing} instance
	 */
	private Outgoing createNewOutgoing() {
		Outgoing newOutgoing=TrackerFactory.eINSTANCE.createOutgoing();
		// Add a default sub amount
		List<Amount> amounts=newOutgoing.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		newOutgoing.setOperationTitle(operationTitle);
		return newOutgoing;
	}
}
