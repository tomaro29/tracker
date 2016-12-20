/**
 */
package fr.rostren.tracker.provider.dev;

import org.eclipse.emf.common.notify.Adapter;

import fr.rostren.tracker.provider.TrackerItemProviderAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support
 * Viewers. The adapters generated by this factory convert EMF adapter
 * notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The
 * adapters also support Eclipse property sheets. Note that most of the adapters
 * are shared among multiple instances. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 */
public class TrackerItemProviderAdapterFactoryDev extends TrackerItemProviderAdapterFactory {

	/**
	 * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 */
	public TrackerItemProviderAdapterFactoryDev() {
		super();
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.BoockletAccount}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createBoockletAccountAdapter() {
		if (boockletAccountItemProvider == null) {
			boockletAccountItemProvider=new BoockletAccountItemProviderDev(this);
		}

		return boockletAccountItemProvider;
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.CheckingAccount}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createCheckingAccountAdapter() {
		if (checkingAccountItemProvider == null) {
			checkingAccountItemProvider=new CheckingAccountItemProviderDev(this);
		}

		return checkingAccountItemProvider;
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Credit}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createCreditAdapter() {
		if (creditItemProvider == null) {
			creditItemProvider=new CreditItemProviderDev(this);
		}

		return creditItemProvider;
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Debit}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createDebitAdapter() {
		if (debitItemProvider == null) {
			debitItemProvider=new DebitItemProviderDev(this);
		}

		return debitItemProvider;
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Incoming} . <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createIncomingAdapter() {
		if (incomingItemProvider == null) {
			incomingItemProvider=new IncomingItemProviderDev(this);
		}

		return incomingItemProvider;
	}

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Outgoing} . <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public Adapter createOutgoingAdapter() {
		if (outgoingItemProvider == null) {
			outgoingItemProvider=new OutgoingItemProviderDev(this);
		}

		return outgoingItemProvider;
	}
}