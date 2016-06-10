/**
 */
package fr.rostren.tracker.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.TrackerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Account Service</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class AccountServiceImpl extends EObjectImpl implements AccountService {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AccountServiceImpl() {
	super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
	return TrackerPackage.Literals.ACCOUNT_SERVICE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public float sumPerCategory(Account account, Category category, Month month, int year) {
	// TODO: implement this method
	// Ensure that you remove @generated or mark it @generated NOT
	throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public float averagePerCategory(Account account, Category category, Month month, int year) {
	// TODO: implement this method
	// Ensure that you remove @generated or mark it @generated NOT
	throw new UnsupportedOperationException();
    }

} // AccountServiceImpl
