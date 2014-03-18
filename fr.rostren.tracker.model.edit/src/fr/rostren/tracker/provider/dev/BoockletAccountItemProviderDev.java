/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.IncomingTransfer;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OutgoingTransfer;
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {

		// create newIncomingTransfer
		IncomingTransfer newIncomingTransfer = createNewIncomingTransfer();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS,
				newIncomingTransfer));

		// create newOutgoingTransfer
		OutgoingTransfer newOutgoingTransfer = createNewOutgoingTransfer();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS,
				newOutgoingTransfer));
	}

	private IncomingTransfer createNewIncomingTransfer() {
		IncomingTransfer newIncomingTransfer = TrackerFactory.eINSTANCE
				.createIncomingTransfer();
		// Add a default sub amount
		List<Amount> amounts = newIncomingTransfer.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount = TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle = TrackerFactory.eINSTANCE
				.createOperationTitle();
		newIncomingTransfer.setOperationTitle(operationTitle);
		return newIncomingTransfer;
	}

	private OutgoingTransfer createNewOutgoingTransfer() {
		OutgoingTransfer newOutgoingTransfer = TrackerFactory.eINSTANCE
				.createOutgoingTransfer();
		// Add a default sub amount
		List<Amount> amounts = newOutgoingTransfer.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount = TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle = TrackerFactory.eINSTANCE
				.createOperationTitle();
		newOutgoingTransfer.setOperationTitle(operationTitle);
		return newOutgoingTransfer;
	}

}
