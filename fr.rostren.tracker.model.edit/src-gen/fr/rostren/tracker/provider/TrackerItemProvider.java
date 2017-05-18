/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.Tracker} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class TrackerItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TrackerItemProvider(AdapterFactory adapterFactory) {
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

			addOwnersPropertyDescriptor(object);
			addOriginsRepositoryPropertyDescriptor(object);
			addCategoriesRepositoryPropertyDescriptor(object);
			addOperationsTitlesRepositoriesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Owners feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOwnersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Tracker_owners_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Tracker_owners_feature", "_UI_Tracker_type"),
				TrackerPackage.Literals.TRACKER__OWNERS, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Origins Repository feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOriginsRepositoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Tracker_originsRepository_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Tracker_originsRepository_feature", "_UI_Tracker_type"),
				TrackerPackage.Literals.TRACKER__ORIGINS_REPOSITORY, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Categories Repository feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCategoriesRepositoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Tracker_categoriesRepository_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Tracker_categoriesRepository_feature", "_UI_Tracker_type"),
				TrackerPackage.Literals.TRACKER__CATEGORIES_REPOSITORY, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Operations Titles Repositories feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOperationsTitlesRepositoriesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Tracker_operationsTitlesRepositories_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Tracker_operationsTitlesRepositories_feature", "_UI_Tracker_type"),
				TrackerPackage.Literals.TRACKER__OPERATIONS_TITLES_REPOSITORIES, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(TrackerPackage.Literals.TRACKER__OWNERS);
			childrenFeatures.add(TrackerPackage.Literals.TRACKER__ORIGINS_REPOSITORY);
			childrenFeatures.add(TrackerPackage.Literals.TRACKER__CATEGORIES_REPOSITORY);
			childrenFeatures.add(TrackerPackage.Literals.TRACKER__OPERATIONS_TITLES_REPOSITORIES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Tracker.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Tracker"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_Tracker_type");
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

		switch (notification.getFeatureID(Tracker.class)) {
			case TrackerPackage.TRACKER__OWNERS:
			case TrackerPackage.TRACKER__ORIGINS_REPOSITORY:
			case TrackerPackage.TRACKER__CATEGORIES_REPOSITORY:
			case TrackerPackage.TRACKER__OPERATIONS_TITLES_REPOSITORIES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.TRACKER__OWNERS, TrackerFactory.eINSTANCE.createOwner()));

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.TRACKER__ORIGINS_REPOSITORY, TrackerFactory.eINSTANCE.createOriginsRepository()));

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.TRACKER__CATEGORIES_REPOSITORY, TrackerFactory.eINSTANCE.createCategoriesRepository()));

		newChildDescriptors.add(createChildParameter(TrackerPackage.Literals.TRACKER__OPERATIONS_TITLES_REPOSITORIES, TrackerFactory.eINSTANCE.createOperationsTitleRepository()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TrackerEditPlugin.INSTANCE;
	}

}
