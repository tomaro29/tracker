/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Outgoing;
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
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {

	// create newCredit
	Credit newCredit = createNewCredit();

	newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newCredit));

	// create newDebit
	Debit newDebit = createNewDebit();

	newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newDebit));

	// create newIncoming
	Incoming newIncoming = createNewIncoming();

	newChildDescriptors
		.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newIncoming));

	// create newOutgoing
	Outgoing newOutgoing = createNewOutgoing();

	newChildDescriptors
		.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newOutgoing));
    }

    private Credit createNewCredit() {
	Credit newCredit = TrackerFactory.eINSTANCE.createCredit();
	// Add a default sub amount
	List<Amount> amounts = newCredit.getSubAmounts();

	if (amounts.isEmpty()) {
	    Amount amount = TrackerFactory.eINSTANCE.createAmount();
	    amounts.add(amount);
	}

	// Add a default operation title
	OperationTitle operationTitle = TrackerFactory.eINSTANCE.createOperationTitle();
	newCredit.setOperationTitle(operationTitle);
	return newCredit;
    }

    private Debit createNewDebit() {
	Debit newDebit = TrackerFactory.eINSTANCE.createDebit();
	// Add a default sub amount
	List<Amount> amounts = newDebit.getSubAmounts();

	if (amounts.isEmpty()) {
	    Amount amount = TrackerFactory.eINSTANCE.createAmount();
	    amounts.add(amount);
	}

	// Add a default operation title
	OperationTitle operationTitle = TrackerFactory.eINSTANCE.createOperationTitle();
	newDebit.setOperationTitle(operationTitle);
	return newDebit;
    }

    private Incoming createNewIncoming() {
	Incoming newIncoming = TrackerFactory.eINSTANCE.createIncoming();
	// Add a default sub amount
	List<Amount> amounts = newIncoming.getSubAmounts();

	if (amounts.isEmpty()) {
	    Amount amount = TrackerFactory.eINSTANCE.createAmount();
	    amounts.add(amount);
	}

	// Add a default operation title
	OperationTitle operationTitle = TrackerFactory.eINSTANCE.createOperationTitle();
	newIncoming.setOperationTitle(operationTitle);
	return newIncoming;
    }

    private Outgoing createNewOutgoing() {
	Outgoing newOutgoing = TrackerFactory.eINSTANCE.createOutgoing();
	// Add a default sub amount
	List<Amount> amounts = newOutgoing.getSubAmounts();

	if (amounts.isEmpty()) {
	    Amount amount = TrackerFactory.eINSTANCE.createAmount();
	    amounts.add(amount);
	}

	// Add a default operation title
	OperationTitle operationTitle = TrackerFactory.eINSTANCE.createOperationTitle();
	newOutgoing.setOperationTitle(operationTitle);
	return newOutgoing;
    }

}
