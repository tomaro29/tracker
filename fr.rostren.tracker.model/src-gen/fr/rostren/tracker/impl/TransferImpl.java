/**
 */
package fr.rostren.tracker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Transfer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.TransferImpl#getIncomingAccount <em>Incoming Account</em>}</li>
 *   <li>{@link fr.rostren.tracker.impl.TransferImpl#getOutgoingAccount <em>Outgoing Account</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TransferImpl extends OperationImpl implements Transfer {
	/**
	 * The cached value of the '{@link #getIncomingAccount() <em>Incoming Account</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getIncomingAccount()
	 * @generated
	 * @ordered
	 */
	protected Account incomingAccount;

	/**
	 * The cached value of the '{@link #getOutgoingAccount() <em>Outgoing Account</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getOutgoingAccount()
	 * @generated
	 * @ordered
	 */
	protected Account outgoingAccount;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TransferImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TRANSFER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Account getIncomingAccount() {
		if (incomingAccount != null && incomingAccount.eIsProxy()) {
			InternalEObject oldIncomingAccount=(InternalEObject)incomingAccount;
			incomingAccount=(Account)eResolveProxy(oldIncomingAccount);
			if (incomingAccount != oldIncomingAccount) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.TRANSFER__INCOMING_ACCOUNT, oldIncomingAccount, incomingAccount));
			}
		}
		return incomingAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Account basicGetIncomingAccount() {
		return incomingAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIncomingAccount(Account newIncomingAccount) {
		Account oldIncomingAccount=incomingAccount;
		incomingAccount=newIncomingAccount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRANSFER__INCOMING_ACCOUNT, oldIncomingAccount, incomingAccount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Account getOutgoingAccount() {
		if (outgoingAccount != null && outgoingAccount.eIsProxy()) {
			InternalEObject oldOutgoingAccount=(InternalEObject)outgoingAccount;
			outgoingAccount=(Account)eResolveProxy(oldOutgoingAccount);
			if (outgoingAccount != oldOutgoingAccount) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.TRANSFER__OUTGOING_ACCOUNT, oldOutgoingAccount, outgoingAccount));
			}
		}
		return outgoingAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Account basicGetOutgoingAccount() {
		return outgoingAccount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutgoingAccount(Account newOutgoingAccount) {
		Account oldOutgoingAccount=outgoingAccount;
		outgoingAccount=newOutgoingAccount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRANSFER__OUTGOING_ACCOUNT, oldOutgoingAccount, outgoingAccount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.TRANSFER__INCOMING_ACCOUNT:
				if (resolve)
					return getIncomingAccount();
				return basicGetIncomingAccount();
			case TrackerPackage.TRANSFER__OUTGOING_ACCOUNT:
				if (resolve)
					return getOutgoingAccount();
				return basicGetOutgoingAccount();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.TRANSFER__INCOMING_ACCOUNT:
				setIncomingAccount((Account)newValue);
				return;
			case TrackerPackage.TRANSFER__OUTGOING_ACCOUNT:
				setOutgoingAccount((Account)newValue);
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
			case TrackerPackage.TRANSFER__INCOMING_ACCOUNT:
				setIncomingAccount((Account)null);
				return;
			case TrackerPackage.TRANSFER__OUTGOING_ACCOUNT:
				setOutgoingAccount((Account)null);
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
			case TrackerPackage.TRANSFER__INCOMING_ACCOUNT:
				return incomingAccount != null;
			case TrackerPackage.TRANSFER__OUTGOING_ACCOUNT:
				return outgoingAccount != null;
		}
		return super.eIsSet(featureID);
	}

} // TransferImpl
