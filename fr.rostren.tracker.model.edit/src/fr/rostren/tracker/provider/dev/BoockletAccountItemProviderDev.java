/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
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
	private static OperationTitle defaultOperationTitle;

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
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, createNewIncoming(object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, createNewOutgoing(object)));
	}

	/**
	 * Creates a new {@link Incoming} instance
	 * @param object the given account
	 * @return the created {@link Incoming} instance
	 */
	private Incoming createNewIncoming(Object object) {
		Incoming newIncoming=TrackerFactory.eINSTANCE.createIncoming();
		// Add a default sub amount
		List<Amount> amounts=newIncoming.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		// Add a default operation title
		addOperationTitle(object, newIncoming);
		return newIncoming;
	}

	/**
	 * Creates a new {@link Outgoing} instance
	 * @param object the given account
	 * @return the created {@link Outgoing} instance
	 */
	private Outgoing createNewOutgoing(Object object) {
		Outgoing newOutgoing=TrackerFactory.eINSTANCE.createOutgoing();
		// Add a default sub amount
		List<Amount> amounts=newOutgoing.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}
		// Add a default operation title
		addOperationTitle(object, newOutgoing);
		return newOutgoing;
	}

	/**
	 * Adds an {@link OperationTitle} instance to the given operation
	 * @param object the given account
	 * @param operation the given operation
	 */
	private void addOperationTitle(Object object, Operation operation) {
		if (!(object instanceof BoockletAccount)) {
			return;
		}
		EObject rootContainer=EcoreUtil.getRootContainer((BoockletAccount)object);
		if (!(rootContainer instanceof Tracker)) {
			return;
		}

		// Add a default operation title
		OperationsTitleRepository repository=((Tracker)rootContainer).getOperationsTitlesRepositories();
		if (repository == null) {
			return;
		}

		EList<OperationTitle> operationsTitles=repository.getOperationsTitles();
		if (!operationsTitles.isEmpty()) {
			for (OperationTitle operationTitle: operationsTitles) {
				if (operationTitle.getTitle() == null) {
					BoockletAccountItemProviderDev.defaultOperationTitle=operationTitle;
				}
			}
		}
		operation.setOperationTitle(BoockletAccountItemProviderDev.defaultOperationTitle);
	}
}