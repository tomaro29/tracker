/**
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 */
package fr.rostren.tracker.util;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Title;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.Transfer;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see fr.rostren.tracker.TrackerPackage
 * @generated
 */
public class TrackerValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TrackerValidator INSTANCE=new TrackerValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE="fr.rostren.tracker"; //$NON-NLS-1$

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT=0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT=TrackerValidator.GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * @generated NOT
	 */
	private static final Map<String, List<String>> VALIDATION_HAS_NOT_UNIQUE_VALUE=new HashMap<>();
	/**
	 * @generated NOT
	 */
	private static final Map<String, List<String>> VALIDATION_HAS_NOT_VALUE=new HashMap<>();
	/**
	 * @generated NOT
	 */
	private static final Map<String, List<String>> VALIDATION_HAS_NOT_VALID_VALUE=new HashMap<>();
	/**
	 * @generated NOT
	 */
	private static final Map<String, List<String>> VALIDATION_IS_EMPTY=new HashMap<>();
	/**
	 * @generated NOT
	 */
	private static final Map<String, List<String>> VALIDATION_IS_BLANK=new HashMap<>();
	/**
	 * @generated NOT
	 */
	private static final String VALIDATION_HAS_NOT_UNIQUE_VALUE_MESSAGE="The feature ''{0}'' value of one or more ''{1}'' is not unique"; //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	private static final String VALIDATION_HAS_NOT_VALUE_MESSAGE="The feature ''{0}'' of one or more ''{1}'' is not defined or blank"; //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	private static final String VALIDATION_HAS_NOT_VALID_VALUE_MESSAGE="The feature ''{0}'' of one or more ''{1}'' has not valid value comparing to the total sum"; //$NON-NLS-1$
	/**
		 * @generated NOT
		 */
	private static final String VALIDATION_IS_EMPTY_VALUE_MESSAGE="The feature ''{0}'' of one or more ''{1}'' is empty"; //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	private static final String VALIDATION_IS_BLANK_VALUE_MESSAGE="The feature ''{0}'' of one or more ''{1}'' is blank"; //$NON-NLS-1$

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return TrackerPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case TrackerPackage.OWNER:
				return validateOwner((Owner)value, diagnostics, context);
			case TrackerPackage.ACCOUNT:
				return validateAccount((Account)value, diagnostics, context);
			case TrackerPackage.ACCOUNT_SERVICE:
				return validateAccountService((AccountService)value, diagnostics, context);
			case TrackerPackage.CHECKING_ACCOUNT:
				return validateCheckingAccount((CheckingAccount)value, diagnostics, context);
			case TrackerPackage.BOOCKLET_ACCOUNT:
				return validateBoockletAccount((BoockletAccount)value, diagnostics, context);
			case TrackerPackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case TrackerPackage.OPERATION_SERVICE:
				return validateOperationService((OperationService)value, diagnostics, context);
			case TrackerPackage.CREDIT:
				return validateCredit((Credit)value, diagnostics, context);
			case TrackerPackage.DEBIT:
				return validateDebit((Debit)value, diagnostics, context);
			case TrackerPackage.TRANSFER:
				return validateTransfer((Transfer)value, diagnostics, context);
			case TrackerPackage.INCOMING:
				return validateIncoming((Incoming)value, diagnostics, context);
			case TrackerPackage.OUTGOING:
				return validateOutgoing((Outgoing)value, diagnostics, context);
			case TrackerPackage.TITLE:
				return validateTitle((Title)value, diagnostics, context);
			case TrackerPackage.CATEGORY:
				return validateCategory((Category)value, diagnostics, context);
			case TrackerPackage.CATEGORY_SERVICE:
				return validateCategoryService((CategoryService)value, diagnostics, context);
			case TrackerPackage.OPERATION_TITLE:
				return validateOperationTitle((OperationTitle)value, diagnostics, context);
			case TrackerPackage.AMOUNT:
				return validateAmount((Amount)value, diagnostics, context);
			case TrackerPackage.CATEGORIES_REPOSITORY:
				return validateCategoriesRepository((CategoriesRepository)value, diagnostics, context);
			case TrackerPackage.ORIGIN:
				return validateOrigin((Origin)value, diagnostics, context);
			case TrackerPackage.ORIGINS_REPOSITORY:
				return validateOriginsRepository((OriginsRepository)value, diagnostics, context);
			case TrackerPackage.TRACKER:
				return validateTracker((Tracker)value, diagnostics, context);
			case TrackerPackage.TRACKER_SERVICE:
				return validateTrackerService((TrackerService)value, diagnostics, context);
			case TrackerPackage.OPERATIONS_TITLE_REPOSITORY:
				return validateOperationsTitleRepository((OperationsTitleRepository)value, diagnostics, context);
			case TrackerPackage.INCOME_CATEGORY:
				return validateIncomeCategory((IncomeCategory)value, diagnostics, context);
			case TrackerPackage.SPENDING_CATEGORY:
				return validateSpendingCategory((SpendingCategory)value, diagnostics, context);
			case TrackerPackage.ORIGIN_TYPE:
				return validateOriginType((OriginType)value, diagnostics, context);
			case TrackerPackage.DATE:
				return validateDate((LocalDate)value, diagnostics, context);
			case TrackerPackage.MONTH:
				return validateMonth((Month)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOwner(Owner owner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(owner, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(owner, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOwner_hasAccount(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOwner_hasFirstName(owner, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOwner_hasLastName(owner, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasAccount constraint of '<em>Owner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOwner_hasAccount(Owner owner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, TrackerValidator.DIAGNOSTIC_SOURCE, 0, "_UI_GenericConstraint_diagnostic",
						new Object[] {"hasAccount", getObjectLabel(owner, context)}, new Object[] {owner}, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasFirstName constraint of '<em>Owner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOwner_hasFirstName(Owner owner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, TrackerValidator.DIAGNOSTIC_SOURCE, 0, "_UI_GenericConstraint_diagnostic",
						new Object[] {"hasFirstName", getObjectLabel(owner, context)}, new Object[] {owner}, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasLastName constraint of '<em>Owner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOwner_hasLastName(Owner owner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, TrackerValidator.DIAGNOSTIC_SOURCE, 0, "_UI_GenericConstraint_diagnostic",
						new Object[] {"hasLastName", getObjectLabel(owner, context)}, new Object[] {owner}, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAccount(Account account, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(account, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(account, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasUniqueName(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasName(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasAmount(account, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasIdentifier(account, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasUniqueName constraint of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAccount_hasUniqueName(Account account, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validateAccount_hasName(account, diagnostics, context)) {
			return true;
		}
		Owner owner=(Owner)account.eContainer();
		for (Account sibling: owner.getAccounts()) {
			if (!sibling.equals(account) && sibling.getName().equals(account.getName())) {
				if (diagnostics == null) {
					return false;
				}

				String featureName=TrackerPackage.eINSTANCE.getAccount_Name().getName();
				String objectName=TrackerPackage.eINSTANCE.getAccount().getName();
				logValidationProblem(Diagnostic.ERROR, account, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_UNIQUE_VALUE_MESSAGE,
						TrackerValidator.VALIDATION_HAS_NOT_UNIQUE_VALUE);
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates the hasName constraint of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAccount_hasName(Account account, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isEmpty(account.getName()) || StringUtils.isBlank(account.getName())) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAccount_Name().getName();
			String objectName=TrackerPackage.eINSTANCE.getAccount().getName();
			logValidationProblem(Diagnostic.ERROR, account, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasAmount constraint of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAccount_hasAmount(Account account, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (account.getAmount() == 0.0) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAccount_Name().getName();
			String objectName=TrackerPackage.eINSTANCE.getAccount().getName();
			logValidationProblem(Diagnostic.ERROR, account, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasIdentifier constraint of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAccount_hasIdentifier(Account account, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (account.getIdentifier() == 0) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAccount_Name().getName();
			String objectName=TrackerPackage.eINSTANCE.getAccount().getName();
			logValidationProblem(Diagnostic.ERROR, account, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAccountService(AccountService accountService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(accountService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCheckingAccount(CheckingAccount checkingAccount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(checkingAccount, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(checkingAccount, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasUniqueName(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasName(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasAmount(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasIdentifier(checkingAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCheckingAccount_isLinkedToOperations(checkingAccount, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isLinkedToOperations constraint of '<em>Checking Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCheckingAccount_isLinkedToOperations(CheckingAccount checkingAccount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (checkingAccount.getOperations() == null || checkingAccount.getOperations().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getCheckingAccount_Operations().getName();
			String objectName=TrackerPackage.eINSTANCE.getCheckingAccount().getName();
			logValidationProblem(Diagnostic.ERROR, checkingAccount, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBoockletAccount(BoockletAccount boockletAccount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(boockletAccount, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(boockletAccount, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasUniqueName(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasName(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasAmount(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAccount_hasIdentifier(boockletAccount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateBoockletAccount_isLinkedToTansfers(boockletAccount, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isLinkedToTansfers constraint of '<em>Boocklet Account</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBoockletAccount_isLinkedToTansfers(BoockletAccount boockletAccount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (boockletAccount.getTransfers() == null || boockletAccount.getTransfers().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getBoockletAccount_Transfers().getName();
			String objectName=TrackerPackage.eINSTANCE.getBoockletAccount().getName();
			logValidationProblem(Diagnostic.ERROR, boockletAccount, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(operation, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(operation, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(operation, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(operation, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasAmount constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasAmount(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operation.getTotalAmount() == 0) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_TotalAmount().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasTitle constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasTitle(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operation.getOperationTitle() == null || !validateOperationTitle(operation.getOperationTitle(), diagnostics, context)) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_OperationTitle().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasSubAmount constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasSubAmount(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operation.getSubAmounts() == null || operation.getSubAmounts().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_SubAmounts().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasOrigin constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasOrigin(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operation.getOrigin() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_Origin().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasDate constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasDate(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operation.getDate() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_Date().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasValidAmount constraint of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperation_hasValidAmount(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		OperationService service=TrackerFactory.eINSTANCE.createOperationService();
		if (service.sumAmounts(operation) != operation.getTotalAmount()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperation_TotalAmount().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperation().getName();
			logValidationProblem(Diagnostic.ERROR, operation, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALID_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALID_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationService(OperationService operationService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operationService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCredit(Credit credit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(credit, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(credit, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(credit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(credit, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDebit(Debit debit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(debit, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(debit, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(debit, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(debit, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransfer(Transfer transfer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(transfer, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(transfer, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(transfer, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTransfer_isLinkedToAccount(transfer, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isLinkedToAccount constraint of '<em>Transfer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTransfer_isLinkedToAccount(Transfer transfer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (transfer.getIncomingAccount() == null && transfer.getOutgoingAccount() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getTransfer_IncomingAccount().getName() + File.separator + TrackerPackage.eINSTANCE.getTransfer_OutgoingAccount().getName();
			String objectName=TrackerPackage.eINSTANCE.getTransfer().getName();
			logValidationProblem(Diagnostic.ERROR, transfer, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIncoming(Incoming incoming, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(incoming, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(incoming, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(incoming, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTransfer_isLinkedToAccount(incoming, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutgoing(Outgoing outgoing, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(outgoing, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(outgoing, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasAmount(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasTitle(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasSubAmount(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasOrigin(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasDate(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperation_hasValidAmount(outgoing, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTransfer_isLinkedToAccount(outgoing, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTitle(Title title, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(title, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(title, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotEmpty(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotBlank(title, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isUnique(title, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isNotEmpty constraint of '<em>Title</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTitle_isNotEmpty(Title title, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isEmpty(title.getTitle())) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getTitle_Title().getName();
			String objectName=TrackerPackage.eINSTANCE.getTitle().getName();
			logValidationProblem(Diagnostic.ERROR, title, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_IS_EMPTY_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_IS_EMPTY);
			return false;
		}
		return true;
	}

	/**
	 * Validates the isNotBlank constraint of '<em>Title</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTitle_isNotBlank(Title title, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isBlank(title.getTitle())) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getTitle_Title().getName();
			String objectName=TrackerPackage.eINSTANCE.getTitle().getName();
			logValidationProblem(Diagnostic.ERROR, title, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_IS_BLANK_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_IS_BLANK);
			return false;
		}
		return true;
	}

	/**
	 * Validates the isUnique constraint of '<em>Title</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTitle_isUnique(Title title, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validateTitle_isNotEmpty(title, diagnostics, context) && !validateTitle_isNotBlank(title, diagnostics, context)) {
			return false;
		}
		boolean isNotUnique=false;
		if (title instanceof Category) {
			CategoriesRepository repository=getRepository(title);
			for (IncomeCategory sibling: repository.getIncome().getIncomes()) {
				if (!sibling.equals(title) && sibling.getTitle().equals(title.getTitle())) {
					isNotUnique=true;
					break;
				}
			}
			for (IncomeCategory sibling: repository.getIncome().getIncomes()) {
				if (!sibling.equals(title) && sibling.getTitle().equals(title.getTitle())) {
					isNotUnique=true;
					break;
				}
			}
		}
		else if (title instanceof OperationTitle) {
			OperationsTitleRepository repository=(OperationsTitleRepository)title.eContainer();
			for (OperationTitle sibling: repository.getOperationsTitles()) {
				if (!sibling.equals(title) && sibling.getTitle().equals(title.getTitle())) {
					isNotUnique=true;
					break;
				}
			}
		}

		if (isNotUnique) {
			if (diagnostics == null) {
				return false;
			}

			String featureName=TrackerPackage.eINSTANCE.getTitle_Title().getName();
			String objectName=TrackerPackage.eINSTANCE.getTitle().getName();
			logValidationProblem(Diagnostic.ERROR, title, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_UNIQUE_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_UNIQUE_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * @param title
	 * @return
	 * @generated NOT
	 */
	private CategoriesRepository getRepository(Title title) {
		EObject parent=title.eContainer();
		while (!(parent instanceof CategoriesRepository)) {
			parent=parent.eContainer();
		}
		return (CategoriesRepository)parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategory(Category category, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(category, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(category, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotEmpty(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotBlank(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isUnique(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_isDescribed(category, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_hasTitles(category, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isDescribed constraint of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCategory_isDescribed(Category category, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isEmpty(category.getDescription()) || StringUtils.isBlank(category.getDescription())) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getCategory_Description().getName();
			String objectName=TrackerPackage.eINSTANCE.getCategory().getName();
			logValidationProblem(Diagnostic.ERROR, category, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasTitles constraint of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCategory_hasTitles(Category category, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (category.getOperationTitles() == null || category.getOperationTitles().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getCategory_OperationTitles().getName();
			String objectName=TrackerPackage.eINSTANCE.getCategory().getName();
			logValidationProblem(Diagnostic.ERROR, category, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategoryService(CategoryService categoryService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(categoryService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationTitle(OperationTitle operationTitle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(operationTitle, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(operationTitle, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotEmpty(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotBlank(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isUnique(operationTitle, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperationTitle_isLinkedToCategories(operationTitle, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isLinkedToCategories constraint of '<em>Operation Title</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperationTitle_isLinkedToCategories(OperationTitle operationTitle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operationTitle.getCategories() == null && operationTitle.getCategories().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperationTitle_Categories().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperationTitle().getName();
			logValidationProblem(Diagnostic.ERROR, operationTitle, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAmount(Amount amount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(amount, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(amount, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAmount_hasDate(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAmount_hasCategory(amount, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateAmount_hasValue(amount, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasDate constraint of '<em>Amount</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAmount_hasDate(Amount amount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (amount.getWishedDate() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAmount_WishedDate().getName();
			String objectName=TrackerPackage.eINSTANCE.getAmount().getName();
			logValidationProblem(Diagnostic.ERROR, amount, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasCategory constraint of '<em>Amount</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAmount_hasCategory(Amount amount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (amount.getCategory() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAmount_Category().getName();
			String objectName=TrackerPackage.eINSTANCE.getAmount().getName();
			logValidationProblem(Diagnostic.ERROR, amount, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasValue constraint of '<em>Amount</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateAmount_hasValue(Amount amount, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (amount.getValue() == 0) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getAmount_Value().getName();
			String objectName=TrackerPackage.eINSTANCE.getAmount().getName();
			logValidationProblem(Diagnostic.ERROR, amount, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategoriesRepository(CategoriesRepository categoriesRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(categoriesRepository, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(categoriesRepository, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(categoriesRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategoriesRepository_hasCategories(categoriesRepository, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasCategories constraint of '<em>Categories Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCategoriesRepository_hasCategories(CategoriesRepository categoriesRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if ((categoriesRepository.getSpending() == null || !validateSpendingCategory(categoriesRepository.getSpending(), diagnostics, context))
			&& (categoriesRepository.getIncome() == null || !validateIncomeCategory(categoriesRepository.getIncome(), diagnostics, context))) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getCategoriesRepository_Income().getName()	+ File.separator
								+ TrackerPackage.eINSTANCE.getCategoriesRepository_Spending().getName();
			String objectName=TrackerPackage.eINSTANCE.getCategoriesRepository().getName();
			logValidationProblem(Diagnostic.ERROR, categoriesRepository, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrigin(Origin origin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(origin, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(origin, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOrigin_isTyped(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOrigin_hasIdentifier(origin, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOrigin_isLinkedToOperations(origin, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the isTyped constraint of '<em>Origin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOrigin_isTyped(Origin origin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (origin.getType() == null) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOrigin_Type().getName();
			String objectName=TrackerPackage.eINSTANCE.getOrigin().getName();
			logValidationProblem(Diagnostic.ERROR, origin, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasIdentifier constraint of '<em>Origin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOrigin_hasIdentifier(Origin origin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isEmpty(origin.getIdentifier()) || StringUtils.isBlank(origin.getIdentifier())) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOrigin_Identifier().getName();
			String objectName=TrackerPackage.eINSTANCE.getOrigin().getName();
			logValidationProblem(Diagnostic.ERROR, origin, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the isLinkedToOperations constraint of '<em>Origin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOrigin_isLinkedToOperations(Origin origin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (origin.getOperations() == null && origin.getOperations().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOrigin_Operations().getName();
			String objectName=TrackerPackage.eINSTANCE.getOrigin().getName();
			logValidationProblem(Diagnostic.ERROR, origin, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOriginsRepository(OriginsRepository originsRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(originsRepository, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(originsRepository, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(originsRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOriginsRepository_hasOrigins(originsRepository, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasOrigins constraint of '<em>Origins Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOriginsRepository_hasOrigins(OriginsRepository originsRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (originsRepository.getOrigins() == null || originsRepository.getOrigins().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOriginsRepository_Origins().getName();
			String objectName=TrackerPackage.eINSTANCE.getOriginsRepository().getName();
			logValidationProblem(Diagnostic.ERROR, originsRepository, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTracker(Tracker tracker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tracker, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(tracker, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTracker_hasOwners(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTracker_hasOrigins(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTracker_hasCategories(tracker, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTracker_hasTitles(tracker, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasOwners constraint of '<em>Tracker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTracker_hasOwners(Tracker tracker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (tracker.getOwners() == null || tracker.getOwners().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getTracker_Owners().getName();
			String objectName=TrackerPackage.eINSTANCE.getTracker().getName();
			logValidationProblem(Diagnostic.ERROR, tracker, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasOrigins constraint of '<em>Tracker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTracker_hasOrigins(Tracker tracker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validateOriginsRepository(tracker.getOriginsRepository(), diagnostics, context)) {
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasCategories constraint of '<em>Tracker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTracker_hasCategories(Tracker tracker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validateCategoriesRepository(tracker.getCategoriesRepository(), diagnostics, context)) {
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasTitles constraint of '<em>Tracker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTracker_hasTitles(Tracker tracker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validateOperationsTitleRepository(tracker.getOperationsTitlesRepositories(), diagnostics, context)) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrackerService(TrackerService trackerService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(trackerService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationsTitleRepository(OperationsTitleRepository operationsTitleRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(operationsTitleRepository, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(operationsTitleRepository, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(operationsTitleRepository, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateOperationsTitleRepository_hasTitles(operationsTitleRepository, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasTitles constraint of '<em>Operations Title Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOperationsTitleRepository_hasTitles(OperationsTitleRepository operationsTitleRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (operationsTitleRepository.getOperationsTitles() == null || operationsTitleRepository.getOperationsTitles().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getOperationsTitleRepository_OperationsTitles().getName();
			String objectName=TrackerPackage.eINSTANCE.getOperationsTitleRepository().getName();
			logValidationProblem(Diagnostic.ERROR, operationsTitleRepository, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIncomeCategory(IncomeCategory incomeCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(incomeCategory, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(incomeCategory, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotEmpty(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotBlank(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isUnique(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_isDescribed(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_hasTitles(incomeCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateIncomeCategory_hasCategories(incomeCategory, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasCategories constraint of '<em>Income Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateIncomeCategory_hasCategories(IncomeCategory incomeCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (incomeCategory.getIncomes() == null || incomeCategory.getIncomes().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getIncomeCategory_Incomes().getName();
			String objectName=TrackerPackage.eINSTANCE.getIncomeCategory().getName();
			logValidationProblem(Diagnostic.ERROR, incomeCategory, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSpendingCategory(SpendingCategory spendingCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(spendingCategory, diagnostics, context)) {
			return false;
		}
		boolean result=validate_EveryMultiplicityConforms(spendingCategory, diagnostics, context);
		if (result || diagnostics != null) {
			result&=validate_EveryDataValueConforms(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryReferenceIsContained(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryBidirectionalReferenceIsPaired(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryProxyResolves(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_UniqueID(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryKeyUnique(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validate_EveryMapEntryUnique(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotEmpty(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isNotBlank(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateTitle_isUnique(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_isDescribed(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateCategory_hasTitles(spendingCategory, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result&=validateSpendingCategory_hasCategories(spendingCategory, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the hasCategories constraint of '<em>Spending Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSpendingCategory_hasCategories(SpendingCategory spendingCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (spendingCategory.getSpendings() == null || spendingCategory.getSpendings().isEmpty()) {
			if (diagnostics == null) {
				return false;
			}
			String featureName=TrackerPackage.eINSTANCE.getSpendingCategory_Spendings().getName();
			String objectName=TrackerPackage.eINSTANCE.getSpendingCategory().getName();
			logValidationProblem(Diagnostic.ERROR, spendingCategory, featureName, objectName, diagnostics, TrackerValidator.VALIDATION_HAS_NOT_VALUE_MESSAGE,
					TrackerValidator.VALIDATION_HAS_NOT_VALUE);
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOriginType(OriginType originType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDate(LocalDate date, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMonth(Month month, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

	/**
	 * @param object
	 * @param featureName
	 * @param objectName
	 * @param diagnostics
	 * @param message
	 * @param map
	 * @generated NOT
	 */
	private void logValidationProblem(int severity, EObject object, String featureName, String objectName, DiagnosticChain diagnostics, String message,
			Map<String, List<String>> map) {
		List<String> list=map.get(objectName);
		if (list != null && !list.isEmpty() && list.contains(featureName)) {
			return;
		}
		diagnostics.add(new BasicDiagnostic(severity, TrackerValidator.DIAGNOSTIC_SOURCE, 0, MessageFormat.format(message, featureName, objectName), new Object[] {object}));
		if (list == null) {
			list=new ArrayList<>();
		}
		list.add(featureName);

		if (!map.containsKey(objectName)) {
			map.put(objectName, list);
		}
	}

} //TrackerValidator
