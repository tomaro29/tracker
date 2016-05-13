/**
 */
package fr.rostren.tracker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.TrackerPackage;
import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Amount</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.AmountImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.AmountImpl#getSubAmount <em>Sub Amount</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AmountImpl extends EObjectImpl implements Amount {
	/**
         * The cached value of the '{@link #getCategory() <em>Category</em>}' reference.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getCategory()
         * @generated
         * @ordered
         */
	protected Category category;

	/**
         * The default value of the '{@link #getSubAmount() <em>Sub Amount</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getSubAmount()
         * @generated
         * @ordered
         */
	protected static final BigDecimal SUB_AMOUNT_EDEFAULT = null;

	/**
         * The cached value of the '{@link #getSubAmount() <em>Sub Amount</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getSubAmount()
         * @generated
         * @ordered
         */
	protected BigDecimal subAmount = SUB_AMOUNT_EDEFAULT;

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	protected AmountImpl() {
                super();
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	protected EClass eStaticClass() {
                return TrackerPackage.Literals.AMOUNT;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public BigDecimal getSubAmount() {
                return subAmount;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public void setSubAmount(BigDecimal newSubAmount) {
                BigDecimal oldSubAmount = subAmount;
                subAmount = newSubAmount;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.AMOUNT__SUB_AMOUNT, oldSubAmount, subAmount));
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public Category getCategory() {
                if (category != null && category.eIsProxy()) {
                        InternalEObject oldCategory = (InternalEObject)category;
                        category = (Category)eResolveProxy(oldCategory);
                        if (category != oldCategory) {
                                if (eNotificationRequired())
                                        eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.AMOUNT__CATEGORY, oldCategory, category));
                        }
                }
                return category;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public Category basicGetCategory() {
                return category;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public void setCategory(Category newCategory) {
                Category oldCategory = category;
                category = newCategory;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.AMOUNT__CATEGORY, oldCategory, category));
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case TrackerPackage.AMOUNT__CATEGORY:
                                if (resolve) return getCategory();
                                return basicGetCategory();
                        case TrackerPackage.AMOUNT__SUB_AMOUNT:
                                return getSubAmount();
                }
                return super.eGet(featureID, resolve, coreType);
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public void eSet(int featureID, Object newValue) {
                switch (featureID) {
                        case TrackerPackage.AMOUNT__CATEGORY:
                                setCategory((Category)newValue);
                                return;
                        case TrackerPackage.AMOUNT__SUB_AMOUNT:
                                setSubAmount((BigDecimal)newValue);
                                return;
                }
                super.eSet(featureID, newValue);
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public void eUnset(int featureID) {
                switch (featureID) {
                        case TrackerPackage.AMOUNT__CATEGORY:
                                setCategory((Category)null);
                                return;
                        case TrackerPackage.AMOUNT__SUB_AMOUNT:
                                setSubAmount(SUB_AMOUNT_EDEFAULT);
                                return;
                }
                super.eUnset(featureID);
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public boolean eIsSet(int featureID) {
                switch (featureID) {
                        case TrackerPackage.AMOUNT__CATEGORY:
                                return category != null;
                        case TrackerPackage.AMOUNT__SUB_AMOUNT:
                                return SUB_AMOUNT_EDEFAULT == null ? subAmount != null : !SUB_AMOUNT_EDEFAULT.equals(subAmount);
                }
                return super.eIsSet(featureID);
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public String toString() {
                if (eIsProxy()) return super.toString();

                StringBuffer result = new StringBuffer(super.toString());
                result.append(" (subAmount: ");
                result.append(subAmount);
                result.append(')');
                return result.toString();
        }

} //AmountImpl
