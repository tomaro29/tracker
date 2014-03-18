/**
 */
package fr.rostren.tracker.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import fr.rostren.tracker.util.TrackerAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TrackerItemProviderAdapterFactory extends TrackerAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Owner} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OwnerItemProvider ownerItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Owner}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOwnerAdapter() {
		if (ownerItemProvider == null) {
			ownerItemProvider = new OwnerItemProvider(this);
		}

		return ownerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.CheckingAccount} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckingAccountItemProvider checkingAccountItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.CheckingAccount}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCheckingAccountAdapter() {
		if (checkingAccountItemProvider == null) {
			checkingAccountItemProvider = new CheckingAccountItemProvider(this);
		}

		return checkingAccountItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.BoockletAccount} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BoockletAccountItemProvider boockletAccountItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.BoockletAccount}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBoockletAccountAdapter() {
		if (boockletAccountItemProvider == null) {
			boockletAccountItemProvider = new BoockletAccountItemProvider(this);
		}

		return boockletAccountItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.CreditOperation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CreditOperationItemProvider creditOperationItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.CreditOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCreditOperationAdapter() {
		if (creditOperationItemProvider == null) {
			creditOperationItemProvider = new CreditOperationItemProvider(this);
		}

		return creditOperationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.DebitOperation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DebitOperationItemProvider debitOperationItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.DebitOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDebitOperationAdapter() {
		if (debitOperationItemProvider == null) {
			debitOperationItemProvider = new DebitOperationItemProvider(this);
		}

		return debitOperationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.IncomingTransfer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IncomingTransferItemProvider incomingTransferItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.IncomingTransfer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIncomingTransferAdapter() {
		if (incomingTransferItemProvider == null) {
			incomingTransferItemProvider = new IncomingTransferItemProvider(this);
		}

		return incomingTransferItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OutgoingTransfer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutgoingTransferItemProvider outgoingTransferItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OutgoingTransfer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOutgoingTransferAdapter() {
		if (outgoingTransferItemProvider == null) {
			outgoingTransferItemProvider = new OutgoingTransferItemProvider(this);
		}

		return outgoingTransferItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Category} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryItemProvider categoryItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Category}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCategoryAdapter() {
		if (categoryItemProvider == null) {
			categoryItemProvider = new CategoryItemProvider(this);
		}

		return categoryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OperationTitle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationTitleItemProvider operationTitleItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OperationTitle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationTitleAdapter() {
		if (operationTitleItemProvider == null) {
			operationTitleItemProvider = new OperationTitleItemProvider(this);
		}

		return operationTitleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Amount} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AmountItemProvider amountItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Amount}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAmountAdapter() {
		if (amountItemProvider == null) {
			amountItemProvider = new AmountItemProvider(this);
		}

		return amountItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OperationTitleService} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationTitleServiceItemProvider operationTitleServiceItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OperationTitleService}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationTitleServiceAdapter() {
		if (operationTitleServiceItemProvider == null) {
			operationTitleServiceItemProvider = new OperationTitleServiceItemProvider(this);
		}

		return operationTitleServiceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.CategoryService} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryServiceItemProvider categoryServiceItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.CategoryService}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCategoryServiceAdapter() {
		if (categoryServiceItemProvider == null) {
			categoryServiceItemProvider = new CategoryServiceItemProvider(this);
		}

		return categoryServiceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.AccountService} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AccountServiceItemProvider accountServiceItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.AccountService}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAccountServiceAdapter() {
		if (accountServiceItemProvider == null) {
			accountServiceItemProvider = new AccountServiceItemProvider(this);
		}

		return accountServiceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OperationService} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationServiceItemProvider operationServiceItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OperationService}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationServiceAdapter() {
		if (operationServiceItemProvider == null) {
			operationServiceItemProvider = new OperationServiceItemProvider(this);
		}

		return operationServiceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.CategoriesRepository} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoriesRepositoryItemProvider categoriesRepositoryItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.CategoriesRepository}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCategoriesRepositoryAdapter() {
		if (categoriesRepositoryItemProvider == null) {
			categoriesRepositoryItemProvider = new CategoriesRepositoryItemProvider(this);
		}

		return categoriesRepositoryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Date} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateItemProvider dateItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Date}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDateAdapter() {
		if (dateItemProvider == null) {
			dateItemProvider = new DateItemProvider(this);
		}

		return dateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Origin} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OriginItemProvider originItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Origin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOriginAdapter() {
		if (originItemProvider == null) {
			originItemProvider = new OriginItemProvider(this);
		}

		return originItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OriginsRepository} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OriginsRepositoryItemProvider originsRepositoryItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OriginsRepository}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOriginsRepositoryAdapter() {
		if (originsRepositoryItemProvider == null) {
			originsRepositoryItemProvider = new OriginsRepositoryItemProvider(this);
		}

		return originsRepositoryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.Tracker} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerItemProvider trackerItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.Tracker}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTrackerAdapter() {
		if (trackerItemProvider == null) {
			trackerItemProvider = new TrackerItemProvider(this);
		}

		return trackerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link fr.rostren.tracker.OperationsTitleRepository} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationsTitleRepositoryItemProvider operationsTitleRepositoryItemProvider;

	/**
	 * This creates an adapter for a {@link fr.rostren.tracker.OperationsTitleRepository}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationsTitleRepositoryAdapter() {
		if (operationsTitleRepositoryItemProvider == null) {
			operationsTitleRepositoryItemProvider = new OperationsTitleRepositoryItemProvider(this);
		}

		return operationsTitleRepositoryItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (ownerItemProvider != null) ownerItemProvider.dispose();
		if (checkingAccountItemProvider != null) checkingAccountItemProvider.dispose();
		if (boockletAccountItemProvider != null) boockletAccountItemProvider.dispose();
		if (creditOperationItemProvider != null) creditOperationItemProvider.dispose();
		if (debitOperationItemProvider != null) debitOperationItemProvider.dispose();
		if (incomingTransferItemProvider != null) incomingTransferItemProvider.dispose();
		if (outgoingTransferItemProvider != null) outgoingTransferItemProvider.dispose();
		if (categoryItemProvider != null) categoryItemProvider.dispose();
		if (operationTitleItemProvider != null) operationTitleItemProvider.dispose();
		if (amountItemProvider != null) amountItemProvider.dispose();
		if (operationTitleServiceItemProvider != null) operationTitleServiceItemProvider.dispose();
		if (categoryServiceItemProvider != null) categoryServiceItemProvider.dispose();
		if (accountServiceItemProvider != null) accountServiceItemProvider.dispose();
		if (operationServiceItemProvider != null) operationServiceItemProvider.dispose();
		if (categoriesRepositoryItemProvider != null) categoriesRepositoryItemProvider.dispose();
		if (dateItemProvider != null) dateItemProvider.dispose();
		if (originItemProvider != null) originItemProvider.dispose();
		if (originsRepositoryItemProvider != null) originsRepositoryItemProvider.dispose();
		if (trackerItemProvider != null) trackerItemProvider.dispose();
		if (operationsTitleRepositoryItemProvider != null) operationsTitleRepositoryItemProvider.dispose();
	}

}
