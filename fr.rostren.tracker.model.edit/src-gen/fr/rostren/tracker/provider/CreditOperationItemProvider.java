/**
 */
package fr.rostren.tracker.provider;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import fr.rostren.tracker.CreditOperation;

/**
 * This is the item provider adapter for a {@link fr.rostren.tracker.CreditOperation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CreditOperationItemProvider extends OperationItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CreditOperationItemProvider(AdapterFactory adapterFactory) {
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
         * This returns CreditOperation.gif.
         * <!-- begin-user-doc --> <!--
     * end-user-doc -->
         * @generated
         */
    @Override
    public Object getImage(Object object) {
                return overlayImage(object, getResourceLocator().getImage("full/obj16/CreditOperation"));
        }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
	CreditOperation creditOperation = (CreditOperation) object;
	String operationTitle = creditOperation.getOperationTitle().getTitle();
	BigDecimal totalAmount = creditOperation.getTotalAmount();
	String operationAmount = totalAmount == null ? StringUtils.EMPTY : totalAmount.toString();

	if (operationTitle == null) {
	    return "New " + getString("_UI_CreditOperation_type");
	} else if (operationTitle.length() == 0) {
	    return getString("_UI_CreditOperation_type") + " --> Undefined Operation Title = " + operationAmount
		    + " euros";
	} else {
	    return getString("_UI_CreditOperation_type") + " --> " + operationTitle + " = " + operationAmount
		    + " euros";
	}
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
