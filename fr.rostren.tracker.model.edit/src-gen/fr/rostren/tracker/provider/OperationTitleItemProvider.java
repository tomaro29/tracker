/**
 */
package fr.rostren.tracker.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.TrackerPackage;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.OperationTitle} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationTitleItemProvider extends TitleItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OperationTitleItemProvider(AdapterFactory adapterFactory) {
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

                        addCategoriesPropertyDescriptor(object);
                }
                return itemPropertyDescriptors;
        }

	/**
         * This adds a property descriptor for the Categories feature.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	protected void addCategoriesPropertyDescriptor(Object object) {
                itemPropertyDescriptors.add
                        (createItemPropertyDescriptor
                                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                                 getResourceLocator(),
                                 getString("_UI_OperationTitle_categories_feature"),
                                 getString("_UI_PropertyDescriptor_description", "_UI_OperationTitle_categories_feature", "_UI_OperationTitle_type"),
                                 TrackerPackage.Literals.OPERATION_TITLE__CATEGORIES,
                                 true,
                                 false,
                                 true,
                                 null,
                                 null,
                                 null));
        }

	/**
         * This returns OperationTitle.gif.
         * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
         * @generated
         */
	@Override
	public Object getImage(Object object) {
                return overlayImage(object, getResourceLocator().getImage("full/obj16/OperationTitle"));
        }

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String label = ((OperationTitle)object).getTitle();
		return label == null || label.length() == 0 ?
				"***Undefined Operation Title***" : label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(OperationTitle.class)) {
		case TrackerPackage.OPERATION_TITLE__TITLE:
			Object notifier = notification.getNotifier();
			if (notifier instanceof OperationTitle) {
				OperationTitle operationTitle = (OperationTitle) notifier;
				operationTitle.eContainer();
				fireNotifyChanged(new ViewerNotification(notification,
						operationTitle.eContainer(), true, true));

				// FIXME Add the title of the current operation to the title
				// List of the category;
			}
			return;
		}
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
                super.collectNewChildDescriptors(newChildDescriptors, object);
        }

}
