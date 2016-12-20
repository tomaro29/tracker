/**
 */
package fr.rostren.tracker.provider.dev;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
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

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, createNewCredit(object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, createNewDebit(object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, createNewIncoming(object)));
		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, createNewOutgoing(object)));
	}

	/**
	 * Creates a new {@link Credit} instance
	 * @param object the given account
	 * @return the created {@link Credit} instance
	 */
	private Credit createNewCredit(Object object) {
		Credit operation=TrackerFactory.eINSTANCE.createCredit();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * Adds an {@link OperationTitle} instance to the given operation
	 * @param object the given account
	 * @param operation the given operation
	 */
	private void addOperationTitle(Object object, Operation operation) {
		if (!(object instanceof CheckingAccount)) {
			return;
		}
		EObject rootContainer=EcoreUtil.getRootContainer((CheckingAccount)object);
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
					CheckingAccountItemProviderDev.defaultOperationTitle=operationTitle;
				}
			}
		}
		operation.setOperationTitle(CheckingAccountItemProviderDev.defaultOperationTitle);
	}

	/**
	 * Creates a new {@link Debit} instance
	 * @param object the given account
	 * @return the created {@link Debit} instance
	 */
	private Debit createNewDebit(Object object) {
		Debit operation=TrackerFactory.eINSTANCE.createDebit();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * Creates a new {@link Incoming} instance
	 * @param object the given account
	 * @return the created {@link Incoming} instance
	 */
	private Incoming createNewIncoming(Object object) {
		Incoming operation=TrackerFactory.eINSTANCE.createIncoming();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}

	/**
	 * Creates a new {@link Outgoing} instance
	 * @param object the given account
	 * @return the created {@link Outgoing} instance
	 */
	private Outgoing createNewOutgoing(Object object) {
		Outgoing operation=TrackerFactory.eINSTANCE.createOutgoing();
		// Add a default sub amount
		List<Amount> amounts=operation.getSubAmounts();

		if (amounts.isEmpty()) {
			Amount amount=TrackerFactory.eINSTANCE.createAmount();
			amounts.add(amount);
		}

		addOperationTitle(object, operation);
		return operation;
	}
}