package fr.rostren.tracker.pdf.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.Transfer;

/**
 * A util Class.
 *
 * @author maro
 *
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
		List<Category> categories=new ArrayList<>();
		if (repository.getIncome() != null) {
			categories.add(repository.getIncome());
			for (Category category: repository.getIncome().getIncomes()) {
				categories.addAll(getCategories(category));
			}
		}
		if (repository.getSpending() != null) {
			categories.add(repository.getSpending());
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
		List<Category> categories=new ArrayList<>();
		if (repository.getIncome() != null) {
			categories.add(repository.getIncome());
		}
		if (repository.getSpending() != null) {
			categories.add(repository.getSpending());
		}
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
		Set<Object> accounts=new HashSet<>();
		for (Owner owner: tracker.getOwners()) {
			accounts.addAll(owner.getAccounts());
		}
		return accounts;
	}

	/**
	 * Returns the categories
	 * @param tracker the {@link Tracker} instance
	 * @return the categories
	 */
	public static Set<Object> getCategories(Tracker tracker) {
		Set<Object> categories=new HashSet<>();
		categories.addAll(getAllCategories(tracker.getCategoriesRepository()));
		return categories;
	}

	/**
	 * Returns the available years
	 * @param tracker the {@link Tracker} instance
	 * @return the years
	 */
	public static Set<Integer> getYears(Tracker tracker) {
		Set<Integer> years=new HashSet<>();
		for (Object account: getAccounts(tracker)) {
			if (account instanceof CheckingAccount) {
				for (Operation operation: ((CheckingAccount)account).getOperations()) {
					years.add(operation.getDate().getYear());
				}
			}
			else if (account instanceof BoockletAccount) {
				for (Transfer transfer: ((BoockletAccount)account).getTransfers()) {
					years.add(transfer.getDate().getYear());
				}
			}
		}
		return years;
	}

	/**
	 * Returns the category title
	 * @param amount the amount
	 * @return the category title
	 */
	public static String getCategoryTitle(Amount amount) {
		if (amount == null) {
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	/**
	 * Returns the amount value
	 * @param amount the amount
	 * @return the amount value
	 */
	public static String getAmountValue(Amount amount) {
		if (amount == null) {
			throw new IllegalArgumentException("The amount cannot be null.");//$NON-NLS-1$
		}
		return amount.getValue() == null ? StringUtils.EMPTY : amount.getValue().toString();
	}

	/**
	 * Returns the operation title as a {@link String}
	 * @param operation the operation
	 * @return the operation title as a {@link String}
	 */
	public static String getOperationTitleAsString(Operation operation) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
	}

	/**
	 * Returns the operation total amount as a {@link String}
	 * @param operation the operation
	 * @return the operation total amount as a {@link String}
	 */
	public static String getOperationTotalAmount(Operation operation) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
		return operation.getTotalAmount() == null ? StringUtils.EMPTY : operation.getTotalAmount().toString();
	}

	/**
	 * Returns the operation date as a {@link String}
	 * @param operation the operation
	 * @return the operation date as a {@link String}
	 */
	public static String getOperationDate(Operation operation) {
		if (operation == null) {
			throw new IllegalArgumentException("The operation cannot be null.");//$NON-NLS-1$
		}
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
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The title to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		for (Category category: getAllCategories(tracker.getCategoriesRepository())) {
			isCategoryTitleUnique(category, title);
		}
		return true;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param category the category
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	private static boolean isCategoryTitleUnique(Category category, String title) {
		if (StringUtils.deleteWhitespace(category.getTitle()).equals(StringUtils.deleteWhitespace(title))) {
			return false;
		}

		if (category instanceof IncomeCategory) {
			for (IncomeCategory subCategory: ((IncomeCategory)category).getIncomes()) {
				return isCategoryTitleUnique(subCategory, title);
			}
		}
		if (category instanceof SpendingCategory) {
			for (SpendingCategory subCategory: ((SpendingCategory)category).getSpendings()) {
				return isCategoryTitleUnique(subCategory, title);
			}
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
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		for (Origin origin: tracker.getOriginsRepository().getOrigins()) {
			if (StringUtils.deleteWhitespace(origin.getIdentifier()).equals(StringUtils.deleteWhitespace(identifier))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param firstName the firstName
	 * @param lastName the lastName
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isOwnerIdentifierUnique(Tracker tracker, String firstName, String lastName) {
		if (StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)) {
			throw new IllegalArgumentException("The first name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)) {
			throw new IllegalArgumentException("The last name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		for (Owner owner: tracker.getOwners()) {
			if (StringUtils.deleteWhitespace(firstName).equals(StringUtils.deleteWhitespace(owner.getFirstName()))
				&& StringUtils.deleteWhitespace(lastName).equals(StringUtils.deleteWhitespace(owner.getLastName()))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param identifier the identifier
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 */
	public static boolean isAccountIdentifierUnique(Tracker tracker, String identifier) {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		for (Owner owner: tracker.getOwners()) {
			for (Account account: owner.getAccounts()) {
				if (account.getIdentifier() == Integer.parseInt(identifier)) {
					return false;
				}
			}
		}
		return true;
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
	 * Returns the sum of sub amounts values
	 * @param subAmounts the sub amounts
	 * @return the sum of sub amounts values
	 */
	private static BigDecimal getSum(EList<Amount> subAmounts) {
		BigDecimal sum=new BigDecimal(0);
		for (Amount amount: subAmounts) {
			sum=sum.add(amount.getValue());
		}
		return sum;
	}

	/**
	 * @param account the concerned account
	 * @param dates the dates to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the income category amount of the given item and its children
	 */
	public static List<Double> getAllIncomeCategoryAmount(Account account, List<String> dates, int year, boolean wishedEnabled) {
		List<Double> incomes=new ArrayList<>();
		for (String date: dates) {
			//List<Amount> amounts=getAllIncomeAmounts(account, Month.get(date), year, wishedEnabled);
			List<Amount> amounts=getAllAmounts(account, Month.get(date), year, wishedEnabled, IncomeCategory.class);
			incomes.add(getTotalAmount(amounts));
		}
		return incomes;
	}

	private static boolean isDateValid(Amount amount, Operation operation, int year, Month month, boolean wishedEnabled) {
		Date comparisonDate=wishedEnabled && amount.getWishedDate() != null ? amount.getWishedDate() : operation.getDate();
		return month.equals(comparisonDate.getMonth()) && year == comparisonDate.getYear();
	}

	/**
	 * @param account the concerned account
	 * @param dates the dates to witch we need to extract income category amount
	 * @param year the year for witch we need to extract data
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the income category amount of the given item and its children
	 */
	public static List<Double> getAllSpendingCategoryAmount(Account account, List<String> dates, int year, boolean wishedEnabled) {
		List<Double> spendings=new ArrayList<>();
		//FIXME Java 8
		for (String date: dates) {
			List<Amount> amounts=getAllAmounts(account, Month.get(date), year, wishedEnabled, SpendingCategory.class);
			spendings.add(getTotalAmount(amounts));
		}
		return spendings;
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
	 * @param amounts the list of amounts to addition
	 * @return the total amount value
	 */
	private static Double getTotalAmount(List<Amount> amounts) {
		BigDecimal totalAmount=new BigDecimal(0);
		for (Amount amount: amounts) {
			totalAmount=totalAmount.add(amount.getValue());
		}
		return Double.parseDouble(totalAmount.toString());
	}

	private static List<Amount> getAllAmounts(Account account, Month month, int year, boolean wishedEnabled, Class<?> clazz) {
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			return checking.getOperations()//
					.stream()//
					.flatMap(op -> op.getSubAmounts()//
							.stream()//
							.filter(amount -> clazz.isInstance(amount.getCategory()))//
							.filter(amount -> isDateValid(amount, op, year, month, wishedEnabled)))//
					.collect(Collectors.toList());
		}
		return Collections.emptyList();

	}

	/**
	 * @param account the concerned account
	 * @param category the category
	 * @param month the month
	 * @param year the year
	 * @param wishedEnabled <code>true</code> if the wished date is enabled, <code>false</code> otherwise.
	 * @return the list of amounts related to the given category
	 */
	private static List<Amount> getCategoryAmounts(Account account, Category category, Month month, int year, boolean wishedEnabled) {
		List<Amount> amounts=new ArrayList<>();
		if (account instanceof CheckingAccount) {
			CheckingAccount checking=(CheckingAccount)account;
			for (Operation operation: checking.getOperations()) {
				for (Amount amount: operation.getSubAmounts()) {
					if (category.equals(amount.getCategory())) {
						Date comparisonDate=wishedEnabled ? amount.getWishedDate() : operation.getDate();
						if (month.equals(comparisonDate.getMonth()) && year == comparisonDate.getYear()) {
							amounts.add(amount);
						}
					}
				}
			}
		}
		return amounts;
	}

	/**
	 * @param spendingCategory the spending category, root of all spendings
	 * @param title the category title
	 * @return the spending category
	 */
	private static SpendingCategory getSpendingCategory(SpendingCategory spendingCategory, String title) {
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
		for (Object account: getAccounts(tracker)) {
			if (accountName.equals(((Account)account).getName())) {
				return (Account)account;
			}
		}
		return null;
	}
}
