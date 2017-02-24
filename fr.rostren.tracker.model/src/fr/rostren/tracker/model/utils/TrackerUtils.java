package fr.rostren.tracker.model.utils;

import java.util.Collections;
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
 * A util Class.
 */
public class TrackerUtils {
	public static final String UNDEFINED_INCOME_TITLE="UNDEFINED INCOME"; //$NON-NLS-1$
	public static final String UNDEFINED_SPENDING_TITLE="UNDEFINED SPENDING"; //$NON-NLS-1$
	private static Map<EObject, EObject> servicesMap=new HashMap<>();

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
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
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
			throw new IllegalArgumentException("The account cannot be null."); //$NON-NLS-1$
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
			throw new IllegalArgumentException("The category cannot be null."); //$NON-NLS-1$
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
			throw new IllegalArgumentException("The operation cannot be null."); //$NON-NLS-1$
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
	 * @param incomeOpt the {@link IncomeCategory} {@link Optional} instance
	 * @return the categories
	 */
	public static List<Category> getAllIncomeCategories(Optional<IncomeCategory> incomeOpt) {
		return incomeOpt.map(income -> {
			List<Category> categories=income.getIncomes()//
					.stream()//
					.flatMap(category -> getCategoryService(category).getCategories().stream())//
					.collect(Collectors.toList());
			categories.add(0, income);
			return categories;
		}).orElse(Collections.emptyList());
	}

	/**
	 * Returns the categories
	 * @param spendingOpt the {@link SpendingCategory} {@link Optional} instance
	 * @return the categories
	 */
	public static List<Category> getAllSpendingCategories(Optional<SpendingCategory> spendingOpt) {
		return spendingOpt.map(spending -> {
			List<Category> categories=spending.getSpendings()//
					.stream()//
					.flatMap(category -> getCategoryService(category).getCategories().stream())//
					.collect(Collectors.toList());
			categories.add(0, spending);
			return categories;
		}).orElse(Collections.emptyList());
	}

	/**
	 * Returns the category title
	 * @param amountOpt the amount
	 * @return the category title
	 */
	public static String getCategoryTitle(Optional<Amount> amountOpt) {
		//FIXME validate Java 8 code migration
		Amount amount=amountOpt.orElseThrow(() -> new IllegalArgumentException("The amount cannot be null.")); //$NON-NLS-1$
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	/**
	 * Returns the amount value
	 * @param amountOpt the amount
	 * @return the amount value
	 */
	public static String getAmountValue(Optional<Amount> amountOpt) {
		//FIXME validate Java 8 code migration
		Amount amount=amountOpt.orElseThrow(() -> new IllegalArgumentException("The amount cannot be null.")); //$NON-NLS-1$
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
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		TrackerService service=TrackerUtils.getTrackerService(amount);
		return service.getAllCategories().stream()//
				.map(category -> TrackerUtils.getCategoryService(category).findCategory(title))//
				.filter(category -> category != null).findFirst();
	}

	/**
	 * @param tracker the opened tracker root
	 * @param item the operation item
	 * @param dates the dates to witch we need to extract the amount
	 * @return the operation amounts
	 */
	public static List<Double> findOperationAmounts(Tracker tracker, String item, List<String> dates) {
		// TODO Auto-generated method stub
		return null;
	}
}