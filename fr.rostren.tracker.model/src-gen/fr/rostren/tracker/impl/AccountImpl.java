/**
 */
package fr.rostren.tracker.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.TrackerPackage;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Account</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.AccountImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.AccountImpl#getAmount <em>Amount</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.AccountImpl#getIdentifier <em>Identifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AccountImpl extends EObjectImpl implements Account {
	/**
         * The default value of the '{@link #getName() <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getName()
         * @generated
         * @ordered
         */
	protected static final String NAME_EDEFAULT = null;

	/**
         * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getName()
         * @generated
         * @ordered
         */
	protected String name = NAME_EDEFAULT;

	/**
         * The default value of the '{@link #getAmount() <em>Amount</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getAmount()
         * @generated
         * @ordered
         */
	protected static final float AMOUNT_EDEFAULT = 0.0F;

	/**
         * The cached value of the '{@link #getAmount() <em>Amount</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getAmount()
         * @generated
         * @ordered
         */
	protected float amount = AMOUNT_EDEFAULT;

	/**
         * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getIdentifier()
         * @generated
         * @ordered
         */
	protected static final int IDENTIFIER_EDEFAULT = 0;

	/**
         * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @see #getIdentifier()
         * @generated
         * @ordered
         */
	protected int identifier = IDENTIFIER_EDEFAULT;

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	protected AccountImpl() {
                super();
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	protected EClass eStaticClass() {
                return TrackerPackage.Literals.ACCOUNT;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public String getName() {
                return name;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public void setName(String newName) {
                String oldName = name;
                name = newName;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.ACCOUNT__NAME, oldName, name));
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public float getAmount() {
                return amount;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public void setAmount(float newAmount) {
                float oldAmount = amount;
                amount = newAmount;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.ACCOUNT__AMOUNT, oldAmount, amount));
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public int getIdentifier() {
                return identifier;
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	public void setIdentifier(int newIdentifier) {
                int oldIdentifier = identifier;
                identifier = newIdentifier;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.ACCOUNT__IDENTIFIER, oldIdentifier, identifier));
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case TrackerPackage.ACCOUNT__NAME:
                                return getName();
                        case TrackerPackage.ACCOUNT__AMOUNT:
                                return getAmount();
                        case TrackerPackage.ACCOUNT__IDENTIFIER:
                                return getIdentifier();
                }
                return super.eGet(featureID, resolve, coreType);
        }

	/**
         * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
         * @generated
         */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
                switch (featureID) {
                        case TrackerPackage.ACCOUNT__NAME:
                                setName((String)newValue);
                                return;
                        case TrackerPackage.ACCOUNT__AMOUNT:
                                setAmount((Float)newValue);
                                return;
                        case TrackerPackage.ACCOUNT__IDENTIFIER:
                                setIdentifier((Integer)newValue);
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
                        case TrackerPackage.ACCOUNT__NAME:
                                setName(NAME_EDEFAULT);
                                return;
                        case TrackerPackage.ACCOUNT__AMOUNT:
                                setAmount(AMOUNT_EDEFAULT);
                                return;
                        case TrackerPackage.ACCOUNT__IDENTIFIER:
                                setIdentifier(IDENTIFIER_EDEFAULT);
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
                        case TrackerPackage.ACCOUNT__NAME:
                                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
                        case TrackerPackage.ACCOUNT__AMOUNT:
                                return amount != AMOUNT_EDEFAULT;
                        case TrackerPackage.ACCOUNT__IDENTIFIER:
                                return identifier != IDENTIFIER_EDEFAULT;
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
                result.append(" (name: ");
                result.append(name);
                result.append(", amount: ");
                result.append(amount);
                result.append(", identifier: ");
                result.append(identifier);
                result.append(')');
                return result.toString();
        }

} //AccountImpl
