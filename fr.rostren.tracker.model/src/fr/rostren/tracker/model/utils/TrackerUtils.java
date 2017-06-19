/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.model.utils;

import java.text.MessageFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.AccountService;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerService;

/**
 * An utility Class.
 */
public final class TrackerUtils {
	public static final String UNDEFINED_INCOME_TITLE="UNDEFINED INCOME"; //$NON-NLS-1$
	public static final String UNDEFINED_SPENDING_TITLE="UNDEFINED SPENDING"; //$NON-NLS-1$

	private static final String OBJECT_CANNOT_BE_NULL="The ''{0}'' cannot be null."; //$NON-NLS-1$
	private static Map<EObject, EObject> servicesMap=new HashMap<>();

	/**
	 * A private Constructor to prevent this class non static calls
	 */
	private TrackerUtils() {
		// Do Nothing
	}

	/**
	 * Returns the tracker
	 * @param eObject the {@link EObject} instance
	 * @return the tracker
	 */
	public static Tracker getTracker(EObject eObject) {
		EObject parent=eObject;
		while (parent != null && !(parent instanceof Tracker)) {
			parent=parent.eContainer();
		}
		return parent == null ? null : (Tracker)parent;
	}

	/**
	 * Returns the tracker service
	 * @param eObject the {@link EObject} instance
	 * @return the tracker service
	 */
	public static TrackerService getTrackerService(EObject eObject) {
		Tracker tracker=TrackerUtils.getTracker(eObject);
		if (tracker == null) {
			throw new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Tracker.class.getName().toLowerCase()));
		}
		if (TrackerUtils.servicesMap.containsKey(tracker)) {
			return (TrackerService)TrackerUtils.servicesMap.get(tracker);
		}
		TrackerService service=TrackerFactory.eINSTANCE.createTrackerService();
		service.setTracker(tracker);
		TrackerUtils.servicesMap.put(tracker, service);
		return service;
	}

	/**
	 * Returns the account service
	 * @param account the {@link Account} instance
	 * @return the account service
	 */
	public static AccountService getAccountService(Account account) {
		if (account == null) {
			throw new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Account.class.getName().toLowerCase()));
		}
		if (TrackerUtils.servicesMap.containsKey(account)) {
			return (AccountService)TrackerUtils.servicesMap.get(account);
		}
		AccountService service=TrackerFactory.eINSTANCE.createAccountService();
		service.setAccount(account);
		TrackerUtils.servicesMap.put(account, service);
		return service;
	}

	/**
	 * Returns the category service
	 * @param category the {@link Category} instance
	 * @return the category service
	 */
	public static CategoryService getCategoryService(Category category) {
		if (category == null) {
			throw new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Category.class.getName().toLowerCase()));
		}
		if (TrackerUtils.servicesMap.containsKey(category)) {
			return (CategoryService)TrackerUtils.servicesMap.get(category);
		}
		CategoryService service=TrackerFactory.eINSTANCE.createCategoryService();
		service.setCategory(category);
		TrackerUtils.servicesMap.put(category, service);
		return service;
	}

	/**
	 * Returns the operation service
	 * @param operation the {@link Operation} instance
	 * @return the operation service
	 */
	public static OperationService getOperationService(Operation operation) {
		if (operation == null) {
			throw new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Operation.class.getName().toLowerCase()));
		}
		if (TrackerUtils.servicesMap.containsKey(operation)) {
			return (OperationService)TrackerUtils.servicesMap.get(operation);
		}
		OperationService service=TrackerFactory.eINSTANCE.createOperationService();
		service.setOperation(operation);
		TrackerUtils.servicesMap.put(operation, service);
		return service;
	}

	/**
	 * Returns the categories
	 * @param income the {@link IncomeCategory} instance
	 * @return the categories
	 */
	public static List<Category> getAllIncomeCategories(IncomeCategory income) {
		List<Category> categories=income.getIncomes()//
				.stream()//
				.flatMap(category -> getCategoryService(category).getCategories().stream())//
				.collect(Collectors.toList());
		categories.add(0, income);
		return categories;
	}

	/**
	 * Returns the categories
	 * @param spending the {@link SpendingCategory} instance
	 * @return the categories
	 */
	public static List<Category> getAllSpendingCategories(SpendingCategory spending) {
		List<Category> categories=spending.getSpendings()//
				.stream()//
				.flatMap(category -> getCategoryService(category).getCategories().stream())//
				.collect(Collectors.toList());
		categories.add(0, spending);
		return categories;
	}

	/**
	 * Returns the category title
	 * @param amountOpt the amount
	 * @return the category title
	 */
	public static String getCategoryTitle(Optional<Amount> amountOpt) {
		Amount amount=amountOpt.orElseThrow(() -> new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Amount.class.getName().toLowerCase())));
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	/**
	 * Returns the amount value
	 * @param amountOpt the amount
	 * @return the amount value
	 */
	public static String getAmountValue(Optional<Amount> amountOpt) {
		Amount amount=amountOpt.orElseThrow(() -> new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Amount.class.getName().toLowerCase())));
		return amount.getValue() == 0 ? StringUtils.EMPTY : String.valueOf(amount.getValue());
	}

	/**
	 * Returns the amount potential category in the containing tracker
	 * @param amount the amount
	 * @param title the category title as a {@link String}
	 * @return the amount potential category in the containing tracker
	 */
	public static Optional<Category> findAmountCategory(Amount amount, String title) {
		if (amount == null) {
			throw new IllegalArgumentException(MessageFormat.format(TrackerUtils.OBJECT_CANNOT_BE_NULL, Amount.class.getName().toLowerCase()));
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return Optional.empty();
		}
		TrackerService service=TrackerUtils.getTrackerService(amount);
		return service.getAllCategories().stream()//
				.map(category -> TrackerUtils.getCategoryService(category).findCategory(title))//
				.filter(category -> category != null).findFirst();
	}

	/**
	 * @param tracker the opened tracker root
	 * @param item the operation title
	 * @param months the months to witch we need to extract the amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @param type the operation type
	 * @return the operation amounts
	 */
	public static List<Double> findOperationAmounts(Tracker tracker, String item, List<String> months, int year, boolean wishedEnabled, OperationType type) {
		List<Double> amounts=new ArrayList<>();
		List<Account> accounts=tracker.getOwners().stream()//
				.flatMap(owner -> owner.getAccounts().stream())//
				.collect(Collectors.toList());
		for (String month: months) {
			amounts.addAll(accounts.stream()//
					.flatMap(account -> getAccountService(account).findOperationAmounts(item, Month.valueOf(month), year, wishedEnabled, type).stream())//
					.map(amount -> amount.getValue()).collect(Collectors.toList()));
		}
		return amounts;
	}
}
