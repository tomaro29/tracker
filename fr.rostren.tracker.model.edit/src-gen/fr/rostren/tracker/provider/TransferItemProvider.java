/**
 */
package fr.rostren.tracker.provider;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.Transfer} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class TransferItemProvider extends OperationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TransferItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIncomingAccountPropertyDescriptor(object);
			addOutgoingAccountPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Incoming Account feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIncomingAccountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Transfer_incomingAccount_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Transfer_incomingAccount_feature", "_UI_Transfer_type"),
				TrackerPackage.Literals.TRANSFER__INCOMING_ACCOUNT, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Outgoing Account feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOutgoingAccountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Transfer_outgoingAccount_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Transfer_outgoingAccount_feature", "_UI_Transfer_type"),
				TrackerPackage.Literals.TRANSFER__OUTGOING_ACCOUNT, true, false, true, null, null, null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		BigDecimal labelValue=((Transfer)object).getTotalAmount();
		String label=labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ? getString("_UI_Transfer_type") : getString("_UI_Transfer_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
