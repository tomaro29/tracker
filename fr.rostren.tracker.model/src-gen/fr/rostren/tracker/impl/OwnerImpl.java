/**
 */
package fr.rostren.tracker.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Owner</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.OwnerImpl#getAccounts <em>Accounts</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OwnerImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.OwnerImpl#getLastName <em>Last Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OwnerImpl extends EObjectImpl implements Owner {
	/**
         * The cached value of the '{@link #getAccounts() <em>Accounts</em>}' containment reference list.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getAccounts()
         * @generated
         * @ordered
         */
	protected EList<Account> accounts;

	/**
         * The default value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getFirstName()
         * @generated
         * @ordered
         */
	protected static final String FIRST_NAME_EDEFAULT = null;

	/**
         * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getFirstName()
         * @generated
         * @ordered
         */
	protected String firstName = FIRST_NAME_EDEFAULT;

	/**
         * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getLastName()
         * @generated
         * @ordered
         */
	protected static final String LAST_NAME_EDEFAULT = null;

	/**
         * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getLastName()
         * @generated
         * @ordered
         */
	protected String lastName = LAST_NAME_EDEFAULT;

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	protected OwnerImpl() {
                super();
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	protected EClass eStaticClass() {
                return TrackerPackage.Literals.OWNER;
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	public EList<Account> getAccounts() {
                if (accounts == null) {
                        accounts = new EObjectContainmentEList<Account>(Account.class, this, TrackerPackage.OWNER__ACCOUNTS);
                }
                return accounts;
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	public String getFirstName() {
                return firstName;
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	public void setFirstName(String newFirstName) {
                String oldFirstName = firstName;
                firstName = newFirstName;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OWNER__FIRST_NAME, oldFirstName, firstName));
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	public String getLastName() {
                return lastName;
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	public void setLastName(String newLastName) {
                String oldLastName = lastName;
                lastName = newLastName;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.OWNER__LAST_NAME, oldLastName, lastName));
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case TrackerPackage.OWNER__ACCOUNTS:
                                return ((InternalEList<?>)getAccounts()).basicRemove(otherEnd, msgs);
                }
                return super.eInverseRemove(otherEnd, featureID, msgs);
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case TrackerPackage.OWNER__ACCOUNTS:
                                return getAccounts();
                        case TrackerPackage.OWNER__FIRST_NAME:
                                return getFirstName();
                        case TrackerPackage.OWNER__LAST_NAME:
                                return getLastName();
                }
                return super.eGet(featureID, resolve, coreType);
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
                switch (featureID) {
                        case TrackerPackage.OWNER__ACCOUNTS:
                                getAccounts().clear();
                                getAccounts().addAll((Collection<? extends Account>)newValue);
                                return;
                        case TrackerPackage.OWNER__FIRST_NAME:
                                setFirstName((String)newValue);
                                return;
                        case TrackerPackage.OWNER__LAST_NAME:
                                setLastName((String)newValue);
                                return;
                }
                super.eSet(featureID, newValue);
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	public void eUnset(int featureID) {
                switch (featureID) {
                        case TrackerPackage.OWNER__ACCOUNTS:
                                getAccounts().clear();
                                return;
                        case TrackerPackage.OWNER__FIRST_NAME:
                                setFirstName(FIRST_NAME_EDEFAULT);
                                return;
                        case TrackerPackage.OWNER__LAST_NAME:
                                setLastName(LAST_NAME_EDEFAULT);
                                return;
                }
                super.eUnset(featureID);
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	public boolean eIsSet(int featureID) {
                switch (featureID) {
                        case TrackerPackage.OWNER__ACCOUNTS:
                                return accounts != null && !accounts.isEmpty();
                        case TrackerPackage.OWNER__FIRST_NAME:
                                return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
                        case TrackerPackage.OWNER__LAST_NAME:
                                return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
                }
                return super.eIsSet(featureID);
        }

	/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
	@Override
	public String toString() {
                if (eIsProxy()) return super.toString();

                StringBuffer result = new StringBuffer(super.toString());
                result.append(" (firstName: ");
                result.append(firstName);
                result.append(", lastName: ");
                result.append(lastName);
                result.append(')');
                return result.toString();
        }

} // OwnerImpl
