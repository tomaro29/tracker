/**
 */
package fr.rostren.tracker.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import fr.rostren.tracker.Debit;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.Debit} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class DebitItemProvider extends OperationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DebitItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns Debit.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Debit"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String operationTitle=TrackerUtils.getOperationTitleAsString((Debit)object);
		String operationAmount=TrackerUtils.getOperationTotalAmount((Debit)object);

		if (operationTitle == null) {
			return "New " + getString("_UI_DebitOperation_type");
		}
		if (operationTitle.length() == 0) {
			return getString("_UI_DebitOperation_type") + " --> Undefined Operation Title = " + operationAmount + " euros";
		}
		return getString("_UI_DebitOperation_type") + " --> " + operationTitle + " = " + operationAmount + " euros";
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

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature=feature;
		Object childObject=child;

		boolean qualify=childFeature == TrackerPackage.Literals.OPERATION__DATE || childFeature == TrackerPackage.Literals.OPERATION__WISHED_DATE;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] {getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner)});
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
