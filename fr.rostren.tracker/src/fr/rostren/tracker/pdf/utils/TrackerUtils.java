package fr.rostren.tracker.pdf.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

/**
 * A util Class.
 */
public class TrackerUtils {
	public static final String UNDEFINED_INCOME_TITLE="UNDEFINED INCOME"; //$NON-NLS-1$
	public static final String UNDEFINED_SPENDING_TITLE="UNDEFINED SPENDING"; //$NON-NLS-1$

	/**
	 * Returns the operations titles
	 * @param tracker the {@link Tracker} instance
	 * @return the operations titles list
	 */
	public static Set<Object> getOperationsTitles(Tracker tracker) {
		return new HashSet<>(tracker.getOperationsTitlesRepositories().getOperationsTitles());
	}

	/**
	 * Returns the origins
	 * @param tracker the {@link Tracker} instance
	 * @return the origins
	 */
	public static Set<Object> getOrigins(Tracker tracker) {
		return new HashSet<>(tracker.getOriginsRepository().getOrigins());
	}

	/**
	 * Returns the categories
	 * @param repository the {@link CategoriesRepository} instance
	 * @return the categories
	 */
	public static List<Category> getAllCategories(CategoriesRepository repository) {
		//FIXME validate Java 8 code migration
		Optional<IncomeCategory> income=Optional.of(repository.getIncome());
		Optional<SpendingCategory> spending=Optional.of(repository.getSpending());

		List<Category> categories=new ArrayList<>();
		if (income.isPresent()) {
			categories.add(income.get());
			//			income.get().getIncomes()//
			//					.stream()//
			//					.map(category -> getCategories(category))//
			//					.collect(Collectors.toList());
			for (Category category: repository.getIncome().getIncomes()) {
				categories.addAll(getCategories(category));
			}
		}
		if (spending.isPresent()) {
			categories.add(spending.get());
			for (Category category: repository.getSpending().getSpendings()) {
				categories.addAll(getCategories(category));
			}
		}
		return categories;
	}

	/**
	 * Returns the categories
	 * @param repository the {@link CategoriesRepository} instance
	 * @return the categories
	 */
	public static List<Category> getCategories(CategoriesRepository repository) {
		//FIXME validate Java 8 code migration
		Optional<IncomeCategory> incomeOpt=Optional.of(repository.getIncome());
		Optional<SpendingCategory> spendingOpt=Optional.of(repository.getSpending());

		List<Category> categories=new ArrayList<>();
		incomeOpt.ifPresent(income -> categories.add(income));
		spendingOpt.ifPresent(spending -> categories.add(spending));
		return categories;
	}

	/**
	 * Returns the categories
	 * @param category the {@link Category} instance
	 * @return the categories
	 */
	public static List<Category> getCategories(Category category) {
		List<Category> categories=new ArrayList<>();
		categories.add(category);
		if (category instanceof IncomeCategory) {
			for (Category subCategory: ((IncomeCategory)category).getIncomes()) {
				categories.addAll(getCategories(subCategory));
			}
		}
		if (category instanceof SpendingCategory) {
			for (Category subCategory: ((SpendingCategory)category).getSpendings()) {
				categories.addAll(getCategories(subCategory));
			}
		}
		return categories;
	}

	/**
	 * Returns the accounts
	 * @param tracker the {@link Tracker} instance
	 * @return the accounts
	 */
	public static Set<Object> getAccounts(Tracker tracker) {
		return tracker.getOwners()//
				.stream()//
				.flatMap(owner -> owner.getAccounts().stream())//
				.collect(Collectors.toSet());
	}

	/**
	 * Returns the set of all categories
	 * @param tracker the {@link Tracker} instance
	 * @return the set of all categories
	 */
	public static Set<Object> getCategories(Tracker tracker) {
		return getAllCategories(tracker.getCategoriesRepository()).stream()//
				.collect(Collectors.toSet());
	}

	/**
	 * Returns the available years
	 * @param tracker the {@link Tracker} instance
	 * @return the years
	 */
	public static Set<Integer> getYears(Tracker tracker) {
		//FIXME validate Java 8 code migration
		Set<Integer> years=new HashSet<>();
		getAccounts(tracker).stream()//
				.filter(account -> account instanceof CheckingAccount)//
				.forEach(account -> years.addAll(((CheckingAccount)account).getOperations().stream()//
						.mapToInt(operation -> operation.getDate().getYear())//
						.boxed()//
						.collect(Collectors.toSet())//
		));
		getAccounts(tracker).stream()//
				.filter(account -> account instanceof BoockletAccount)//
				.forEach(account -> years.addAll(((BoockletAccount)account).getTransfers().stream()//
						.mapToInt(transfer -> transfer.getDate().getYear())//
						.boxed()//
						.collect(Collectors.toSet())//
		));
		return years;
	}

	/**
	 * Returns the category title
	 * @param amountOpt the amount
	 * @return the category title
	 */
	public static String getCategoryTitle(Optional<Amount> amountOpt) {
		//FIXME validate Java 8 code migration
		if (!amountOpt.isPresent()) {
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		Amount amount=amountOpt.orElse(null);
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	/**
	 * Returns the amount value
	 * @param amountOpt the amount
	 * @return the amount value
	 */
	public static String getAmountValue(Optional<Amount> amountOpt) {
		//FIXME validate Java 8 code migration
		if (!amountOpt.isPresent()) {
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		Amount amount=amountOpt.orElse(null);
		return amount.getValue() == null ? StringUtils.EMPTY : amount.getValue().toString();
	}

	/**
	 * Returns the operation title as a {@link String}
	 * @param operationOpt the operation
	 * @return the operation title as a {@link String}
	 */
	public static String getOperationTitleAsString(Optional<Operation> operationOpt) {
		//FIXME validate Java 8 code migration
		if (!operationOpt.isPresent()) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		Operation operation=operationOpt.orElse(null);
		return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
	}

	/**
	 * Returns the operation total amount as a {@link String}
	 * @param operationOpt the operation
	 * @return the operation total amount as a {@link String}
	 */
	public static String getOperationTotalAmount(Optional<Operation> operationOpt) {
		//FIXME validate Java 8 code migration
		if (!operationOpt.isPresent()) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		Operation operation=operationOpt.orElse(null);
		return operation.getTotalAmount() == null ? StringUtils.EMPTY : operation.getTotalAmount().toString();
	}

	/**
	 * Returns the operation date as a {@link String}
	 * @param operationOpt the operation
	 * @return the operation date as a {@link String}
	 */
	public static String getOperationDate(Optional<Operation> operationOpt) {
		//FIXME validate Java 8 code migration
		if (!operationOpt.isPresent()) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		Operation operation=operationOpt.orElse(null);
		return operation.getDate() == null ? StringUtils.EMPTY : operation.getDate().toString();
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
	 * Returns the operation title
	 * @param tracker the tracker
	 * @param title the title
	 * @return the operation title
	 */
	public static OperationTitle getOperationTitle(Tracker tracker, String title) {
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();
		if (repository == null) {
			repository=TrackerFactory.eINSTANCE.createOperationsTitleRepository();
			tracker.setOperationsTitlesRepositories(repository);
		}
		for (OperationTitle operationTitle: repository.getOperationsTitles()) {
			if (title.equals(operationTitle.getTitle())) {
				return operationTitle;
			}
		}
		return null;
	}

	/**
	 * Returns the operation origin
	 * @param operation the operation
	 * @param originId the origin Id
	 * @return the operation origin
	 */
	public static Origin getOperationOrigin(Operation operation, String originId) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(originId) || StringUtils.isBlank(originId)) {
			return null;
		}
		Tracker tracker=TrackerUtils.getTracker(operation);
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		EList<Origin> origins=tracker.getOriginsRepository().getOrigins();
		for (Origin origin: origins) {
			if (originId.equals(origin.getIdentifier())) {
				return origin;
			}
		}
		return null;
	}

	/**
	 * Returns the {@link OperationTitle} instance
	 * @param operation the operation
	 * @param title the operation title as a {@link String}
	 * @return the {@link OperationTitle} instance
	 */
	public static OperationTitle getOperationTitle(Operation operation, String title) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		Tracker tracker=TrackerUtils.getTracker(operation);
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		EList<OperationTitle> operationTitles=tracker.getOperationsTitlesRepositories().getOperationsTitles();
		for (OperationTitle opTitle: operationTitles) {
			if (title.equals(opTitle.getTitle())) {
				return opTitle;
			}
		}
		return null;
	}

	/**
	 * Returns the amount category
	 * @param amount the amount
	 * @param title the category title as a {@link String}
	 * @return the amount category
	 */
	public static Category getAmountCategory(Amount amount, String title) {
		if (amount == null) {
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		Tracker tracker=TrackerUtils.getTracker(amount);
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		for (Category category: getAllCategories(tracker.getCategoriesRepository())) {
			return getCategory(category, title);
		}
		return null;
	}

	/**
	 * Returns the concerned category
	 * @param category the category to test
	 * @param title the category title as a {@link String}
	 * @return the category
	 */
	private static Category getCategory(Category category, String title) {
		if (title.equals(category.getTitle())) {
			return category;
		}
		if (category instanceof IncomeCategory) {
			for (IncomeCategory subCategory: ((IncomeCategory)category).getIncomes()) {
				return getCategory(subCategory, title);
			}
		}
		if (category instanceof SpendingCategory) {
			for (SpendingCategory subCategory: ((SpendingCategory)category).getSpendings()) {
				return getCategory(subCategory, title);
			}
		}
		return null;
	}

	/**
	 * <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 * @param category the category
	 * @return <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 */
	public static boolean isUndefinedCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("The category cannot be null.");//$NON-NLS-1$
		}
		if (TrackerUtils.UNDEFINED_INCOME_TITLE.equals(category.getTitle()) || TrackerUtils.UNDEFINED_SPENDING_TITLE.equals(category.getTitle())) {
			return true;
		}
		return false;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isOperationTitleUnique(Tracker tracker, String title) {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The title to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		for (OperationTitle opTitle: tracker.getOperationsTitlesRepositories().getOperationsTitles()) {
			String opTitleTitle=opTitle.getTitle();
			if (!StringUtils.isEmpty(opTitleTitle) && StringUtils.deleteWhitespace(opTitleTitle).equals(StringUtils.deleteWhitespace(title))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isCategoryTitleUnique(Tracker tracker, String title) {
		//FIXME validate the next java8 code
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The title to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		return getAllCategories(tracker.getCategoriesRepository()).stream()//
				.allMatch(category -> isCategoryTitleUnique(category, title));
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param category the category
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	private static boolean isCategoryTitleUnique(Category category, String title) {
		//FIXME validate the next java8 code
		if (StringUtils.deleteWhitespace(category.getTitle()).equals(StringUtils.deleteWhitespace(title))) {
			return false;
		}

		if (category instanceof IncomeCategory) {
			return ((IncomeCategory)category).getIncomes().stream()//
					.allMatch(subCategory -> isCategoryTitleUnique(subCategory, title));
		}
		if (category instanceof SpendingCategory) {
			return ((SpendingCategory)category).getSpendings().stream()//
					.allMatch(subCategory -> isCategoryTitleUnique(subCategory, title));
		}
		return true;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param identifier the identifier
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isOriginIdentifierUnique(Tracker tracker, String identifier) {
		//FIXME validate the next java8 code
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}

		return tracker.getOriginsRepository().getOrigins().stream()//
				.noneMatch(origin -> StringUtils.deleteWhitespace(origin.getIdentifier()).equals(StringUtils.deleteWhitespace(identifier)));
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param firstName the firstName
	 * @param lastName the lastName
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isOwnerIdentifierUnique(Tracker tracker, String firstName, String lastName) {
		//FIXME validate the next java8 code
		if (StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)) {
			throw new IllegalArgumentException("The first name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)) {
			throw new IllegalArgumentException("The last name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}

		return tracker.getOwners().stream()//
				.noneMatch(owner -> StringUtils.deleteWhitespace(firstName).equals(StringUtils.deleteWhitespace(owner.getFirstName()))
									&& StringUtils.deleteWhitespace(lastName).equals(StringUtils.deleteWhitespace(owner.getLastName())));
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param identifier the identifier
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isAccountIdentifierUnique(Tracker tracker, String identifier) {
		//FIXME validate the next java8 code
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		return tracker.getOwners().stream()//
				.noneMatch(owner -> owner.getAccounts().stream()//
						.noneMatch(account -> account.getIdentifier() == Integer.parseInt(identifier)));
	}

	/**
	 * <code>true</code> if the sum of all sub amounts is equal to the total amount. <code>false</code> otherwise.
	 * @param operation the operation
	 * @return <code>true</code> if the sum of all sub amounts is equal to the total amount. <code>false</code> otherwise.
	 */
	public static boolean isValidOperationAmounts(Operation operation) {
		return operation.getTotalAmount().equals(getSum(operation.getSubAmounts()));
	}

	/**
	 * Returns the sum of sub amounts values as {@link BigDecimal} instance
	 * @param subAmounts the sub amounts to addition
	 * @return the sum of sub amounts values as {@link BigDecimal} instance
	 */
	private static BigDecimal getSum(EList<Amount> subAmounts) {
		return new BigDecimal(getTotalAmount(subAmounts));
	}

	/**
	 * REturns the total amount of all given amounts as {@link Double} instance.
	 * @param amounts the list of amounts to addition
	 * @return the total amount value, sum of all given amounts as {@link Double} instance.
	 */
	private static Double getTotalAmount(List<Amount> amounts) {
		//FIXME validate the next java8 code
		return amounts.stream()//
				.map(amount -> Double.parseDouble(amount.getValue().toString()))//
				.mapToDouble((d) -> d)//
				.sum();
	}

	/**
	 * <code>true</code> if the amount has a valid date; <code>false</code> otherwise.
	 * @param amount the amount to check
	 * @param operation the operation container of the given amount
	 * @param year the selected year
	 * @param month the selected month
	 * @param wishedEnabled <code>true</code> if the comparison is based on the wished date, <code>false</code> otherwise
	 * @return <code>true</code> if the amount has a valid date; <code>false</code> otherwise.
	 */
	private static boolean isDateValid(Optional<Amount> amount, Optional<Operation> operation, int year, Month month, boolean wishedEnabled) {
		//FIXME validate the next java8 code
		Optional<Date> wishedDate=amount.isPresent() ? Optional.of(amount.get().getWishedDate()) : Optional.empty();
		Optional<Date> operationDate=operation.isPresent() ? Optional.of(operation.get().getDate()) : Optional.empty();

		Date comparisonDate=wishedEnabled && wishedDate.isPresent() ? wishedDate.get() : operationDate.get();
		return month.equals(comparisonDate.getMonth()) && year == comparisonDate.getYear();
	}

	/**
	 * Returns the total amount
	 * @param account the concerned account
	 * @param dates the dates to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @param clazz the class type of the amount {@link Category}.
	 * @return the total amount of all typed categories
	 */
	public static List<Double> getAllCategoriesAmount(Optional<Account> account, List<String> dates, int year, boolean wishedEnabled, Class<?> clazz) {
		//FIXME validate the next java8 code
		if (account.isPresent()) {
			return dates//
					.stream()//
					.map(date -> getTotalAmount(getAllAmounts(account.get(), Month.get(date), year, wishedEnabled, clazz)))//
					.collect(Collectors.toList());
		}
		return Collections.emptyList();

		//		List<Double> spendings=new ArrayList<>();
		//		//FIXME Java 8
		//		for (String date: dates) {
		//			List<Amount> amounts=getAllAmounts(account, Month.get(date), year, wishedEnabled, SpendingCategory.class);
		//			spendings.add(getTotalAmount(amounts));
		//		}
		//		return spendings;
	}

	/**
	 * @param account the concerned account
	 * @param item the category item
	 * @param dates the dates to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the income category amount of the given item and its children
	 */
	public static List<Double> getIncomeCategoryAmount(Account account, String item, List<String> dates, int year, boolean wishedEnabled) {
		List<Double> incomes=new ArrayList<>();
		Category incomeCategory=getIncomeCategory(TrackerUtils.getTracker(account).getCategoriesRepository().getIncome(), item);
		for (String date: dates) {
			List<Amount> amounts=getCategoryAmounts(account, incomeCategory, Month.get(date), year, wishedEnabled);
			incomes.add(getTotalAmount(amounts));
		}
		return incomes;
	}

	/**
	 * @param account the concerned account
	 * @param item the category item
	 * @param dates the dates to witch we need to extract spending category amount
	 * @param year the year
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the spending category amount of the given item and its children
	 */
	public static List<Double> getSpendingCategoryAmount(Account account, String item, List<String> dates, int year, boolean wishedEnabled) {
		List<Double> spendings=new ArrayList<>();
		Category spendingCategory=getSpendingCategory(TrackerUtils.getTracker(account).getCategoriesRepository().getSpending(), item);
		for (String date: dates) {
			List<Amount> amounts=getCategoryAmounts(account, spendingCategory, Month.get(date), year, wishedEnabled);
			spendings.add(getTotalAmount(amounts));
		}
		return spendings;
	}

	/**
	 * Returns all valid amounts vs comparison year and month.
	 * @param account the account.
	 * @param month the selected month.
	 * @param year the selected year.
	 * @param wishedEnabled <code>true</code> is the wished date is enabled, <code>false</code> otherwise.
	 * @param clazz the class type of the amount {@link Category}.
	 * @return all valid amounts vs comparison year and month.
	 */
	private static List<Amount> getAllAmounts(Account account, Month month, int year, boolean wishedEnabled, Class<?> clazz) {
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			return checking.getOperations()//
					.stream()//
					.flatMap(operation -> operation.getSubAmounts()//
							.stream()//
							.filter(amount -> clazz.isInstance(amount.getCategory()))//
							.filter(amount -> isDateValid(Optional.of(amount), Optional.of(operation), year, month, wishedEnabled)))//
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * @param account the concerned account.
	 * @param category the category.
	 * @param month the month.
	 * @param year the year.
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the list of amounts related to the given category.
	 */
	private static List<Amount> getCategoryAmounts(Account account, Category category, Month month, int year, boolean wishedEnabled) {
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			return checking.getOperations()//
					.stream()//
					.flatMap(operation -> operation.getSubAmounts()//
							.stream()//
							.filter(amount -> category.equals(amount.getCategory()))//
							.filter(amount -> isDateValid(Optional.of(amount), Optional.of(operation), year, month, wishedEnabled)))//
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * @param spendingCategory the spending category, root of all spendings
	 * @param title the category title
	 * @return the spending category
	 */
	private static SpendingCategory getSpendingCategory(SpendingCategory spendingCategory, String title) {
		// FIXME to validate java 8 code migration
		//		return spendingCategory.getSpendings().stream().filter(spending -> spending.getTitle().equals(title));

		for (SpendingCategory spending: spendingCategory.getSpendings()) {
			if (spending.getTitle().equals(title)) {
				return spending;
			}
			return getSpendingCategory(spending, title);
		}
		return null;
	}

	/**
	 * @param incomeCategory the income category, root of all incomes
	 * @param title the category title
	 * @return the income category
	 */
	private static IncomeCategory getIncomeCategory(IncomeCategory incomeCategory, String title) {
		for (IncomeCategory income: incomeCategory.getIncomes()) {
			if (income.getTitle().equals(title)) {
				return income;
			}
			return getIncomeCategory(income, title);
		}
		return null;
	}

	/**
	 * @param tracker the opened tracker root
	 * @param item the opertaion item
	 * @param dates the dates to witch we need to extract the amount
	 * @return the operation amounts
	 */
	public static List<Double> getOperationAmount(Tracker tracker, String item, List<String> dates) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param tracker the opened tracker root
	 * @param accountName the account name
	 * @return the account
	 */
	public static Account getAccount(Tracker tracker, String accountName) {
		//FIXME to validate Java 8 code migration
		return getAccounts(tracker)//
				.stream()//
				.filter(account -> accountName.equals(((Account)account).getName()))//
				.map(Account.class::cast)//
				.findFirst().orElse(null);
	}
}
