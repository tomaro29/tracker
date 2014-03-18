/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CreditOperation;
import fr.rostren.tracker.DebitOperation;
import fr.rostren.tracker.IncomingTransfer;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OutgoingTransfer;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.provider.CheckingAccountItemProvider;

/**
 * This is the item provider adapter for a
 * {@link fr.rostren.tracker.CheckingAccount} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public class CheckingAccountItemProviderDev extends CheckingAccountItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	public CheckingAccountItemProviderDev(AdapterFactory adapterFactory) {
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

		// create newCreditOperation
		CreditOperation newCreditOperation = createNewCreditOperation();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
				newCreditOperation));

		// create newDebitOperation
		DebitOperation newDebitOperation = createNewDebitOperation();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
				newDebitOperation));

		// create newIncomingTransfer
		IncomingTransfer newIncomingTransfer = createNewIncomingTransfer();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
				newIncomingTransfer));

		// create newOutgoingTransfer
		OutgoingTransfer newOutgoingTransfer = createNewOutgoingTransfer();

		newChildDescriptors.add(createChildParameter(
				TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS,
				newOutgoingTransfer));
	}

	private CreditOperation createNewCreditOperation() {
		CreditOperation newCreditOperation = TrackerFactory.eINSTANCE
				.createCreditOperation();
		// Add a default sub amount
		List<Amount> amounts = newCreditOperation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount = TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle = TrackerFactory.eINSTANCE
				.createOperationTitle();
		newCreditOperation.setOperationTitle(operationTitle);
		return newCreditOperation;
	}

	private DebitOperation createNewDebitOperation() {
		DebitOperation newDebitOperation = TrackerFactory.eINSTANCE
				.createDebitOperation();
		// Add a default sub amount
		List<Amount> amounts = newDebitOperation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount = TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		OperationTitle operationTitle = TrackerFactory.eINSTANCE
				.createOperationTitle();
		newDebitOperation.setOperationTitle(operationTitle);
		return newDebitOperation;
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
