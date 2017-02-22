package fr.rostren.tracker.pdf.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class LineContent {

	private static final String FACT=" FACT "; //$NON-NLS-1$

	private final OperationData operation;

	/** The title. */
	private String title;
	/** The category. */
	private Category linkedCategory;
	/** The operation title. */
	private OperationTitle linkedOperationTitle;

	/**
	 * Constructor
	 *
	 * @param date
	 *            the date of the operation
	 * @param title
	 *            the title of the operation
	 * @param amount
	 *            the amount of the operation
	 * @param type
	 *            the operation type
	 * @param origin
	 *            the operation origin
	 */
	public LineContent(LocalDate date, String title, double amount, OperationType type, Origin origin) {
		if (date == null) {
			throw new IllegalArgumentException("The operation date cannot be null."); //$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The operation title cannot be empty or null."); //$NON-NLS-1$
		}
		if (amount == 0) {
			throw new IllegalArgumentException("The operation amount cannot be zero."); //$NON-NLS-1$
		}
		if (type == null) {
			throw new IllegalArgumentException("The operation type cannot be null."); //$NON-NLS-1$
		}
		if (origin == null) {
			throw new IllegalArgumentException("The operation origin cannot be null."); //$NON-NLS-1$
		}

		this.setTitle(formatTitle(title));
		operation=new OperationData();
		operation.setType(type);
		operation.setDate(date);
		operation.setTotalAmount(amount);
		operation.setOrigin(origin);
	}

	/**
	 * Formats the title
	 *
	 * @param currentTitle
	 *            the current title
	 * @return the formatted title.
	 */
	private String formatTitle(String currentTitle) {
		if (currentTitle.startsWith("ECH PRET")) { //$NON-NLS-1$
			return "ECH PRET"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("INTERETS CREDITEURS")) { //$NON-NLS-1$
			return "INTERETS CREDITEURS"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("CB LA POSTE")) { //$NON-NLS-1$
			return "CB LA POSTE"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("CHEQUE")) { //$NON-NLS-1$
			return "CHEQUE"; //$NON-NLS-1$
		}
		else if (currentTitle.contains(LineContent.FACT)) {
			return currentTitle.split(LineContent.FACT)[0];
		}
		return currentTitle;
	}

	/**
	 * Complete the operation
	 *
	 * @param tracker
	 *            the tracker root
	 */
	public void completeOperation(Tracker tracker) {
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null."); //$NON-NLS-1$
		}

		setLinkedCategory(findCategoryInTrackerModel(getTitle(), tracker));

		// Adds a title to the operation
		operation.setOperationTitle(getLinkedOperationTitle());

		// Adds the total amount as a subAmount to the operation
		Amount newAmountObject=TrackerUtils.createAmount(operation, operation.getTotalAmount(), getLinkedCategory());
		operation.getSubAmounts().add(newAmountObject);
	}

	/**
	 * Finds a category based on the given operation title.
	 * It creates a new category if the corresponding one
	 * does not exist already in the model.
	 *
	 * @param title
	 *            the title of the category to find
	 * @param tracker
	 *            the tracker model
	 * @return the corresponding category if it exists already, and a new
	 *         category otherwise.
	 */
	private Category findCategoryInTrackerModel(String title, Tracker tracker) {
		linkedOperationTitle=null;
		TrackerUtils.findOperationTitle(tracker, title).ifPresent(operationTitle -> setLinkedOperationTitle(operationTitle));
		if (linkedOperationTitle != null && !linkedOperationTitle.getCategories().isEmpty()) {
			return linkedOperationTitle.getCategories().get(0);
		}
		OperationTitle newTitle=linkedOperationTitle == null ? createNewTitle(tracker) : linkedOperationTitle;
		return createNewCategory(newTitle, tracker);
	}

	/**
	 * Creates a new category
	 * @param newTitle the operation Title
	 * @param tracker
	 *            the tracker model
	 * @return the newly created category
	 */
	private Category createNewCategory(OperationTitle newTitle, Tracker tracker) {
		CategoriesRepository categoriesRepository=getCategoriesRepository(tracker);
		if (OperationType.CREDIT.equals(operation.getType())) {
			Category undefined=getUndefinedIncomeCategory(categoriesRepository);
			List<IncomeCategory> incomes=categoriesRepository.getIncome().getIncomes();
			if (!incomes.contains(undefined)) {
				incomes.add((IncomeCategory)undefined);
			}
			undefined.getOperationTitles().add(newTitle);
			return undefined;
		}
		else if (OperationType.DEBIT.equals(operation.getType())) {
			Category undefined=getUndefinedSpendingCategory(categoriesRepository);
			List<SpendingCategory> spendings=categoriesRepository.getSpending().getSpendings();
			if (!spendings.contains(undefined)) {
				spendings.add((SpendingCategory)undefined);
			}
			undefined.getOperationTitles().add(newTitle);
			return undefined;
		}
		return null;
	}

	/**
	 * Creates a new {@link OperationTitle} instance
	 * @param tracker the tracker
	 * @return the new title instance
	 */
	private OperationTitle createNewTitle(Tracker tracker) {
		OperationTitle newTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		setLinkedOperationTitle(newTitle);
		newTitle.setTitle(title);
		tracker.getOperationsTitlesRepositories().getOperationsTitles().add(newTitle);
		return newTitle;
	}

	/**
	 * @param tracker the tracker
	 * @return the categories repository if any, a new one otherwise.
	 */
	private CategoriesRepository getCategoriesRepository(Tracker tracker) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		if (repository == null) {
			repository=TrackerFactory.eINSTANCE.createCategoriesRepository();
			tracker.setCategoriesRepository(repository);
		}
		return repository;
	}

	/**
	 * Returns the undefined income category
	 * @param repository the repository
	 * @return the undefined income category
	 */
	private Category getUndefinedIncomeCategory(CategoriesRepository repository) {
		IncomeCategory income=repository.getIncome();
		if (income == null) {
			income=TrackerFactory.eINSTANCE.createIncomeCategory();
			repository.setIncome(income);
		}

		Optional<IncomeCategory> findAny=income.getIncomes().stream()//
				.filter(category -> TrackerUtils.isUndefinedCategory(category))//
				.findAny();
		return findAny.orElse(getCategoryInIncome(income));
	}

	/**
	 * Returns the undefined spending category
	 * @param repository the repository
	 * @return the undefined spending category
	 */
	private Category getUndefinedSpendingCategory(CategoriesRepository repository) {
		SpendingCategory spending=repository.getSpending();
		if (spending == null) {
			spending=TrackerFactory.eINSTANCE.createSpendingCategory();
			repository.setSpending(spending);
		}
		return spending.getSpendings().stream()//
				.filter(category -> TrackerUtils.isUndefinedCategory(category))//
				.findAny().orElse(getCategoryInSpending(spending));
	}

	/**
	 * returns the undefined category in a new undefined group
	 * @param income the income category
	 * @return the undefined category in a new undefined group
	 */
	private IncomeCategory getCategoryInIncome(IncomeCategory income) {
		IncomeCategory category=TrackerFactory.eINSTANCE.createIncomeCategory();
		category.setTitle(TrackerUtils.UNDEFINED_INCOME_TITLE);
		return category;
	}

	/**
	 * returns the undefined category in a new undefined group
	 * @param spending the spending category
	 * @return the undefined category in a new undefined group
	 */
	private SpendingCategory getCategoryInSpending(SpendingCategory spending) {
		SpendingCategory category=TrackerFactory.eINSTANCE.createSpendingCategory();
		category.setTitle(TrackerUtils.UNDEFINED_SPENDING_TITLE);
		return category;
	}

	/**
	 * Returns the operation data
	 *
	 * @return the operation data
	 */
	public OperationData getOperation() {
		return operation;
	}

	/**
	 * @return the linkedCategory
	 */
	public Category getLinkedCategory() {
		return linkedCategory;
	}

	/**
	 * @param linkedCategory
	 *            the linkedCategory to set
	 */
	private void setLinkedCategory(Category linkedCategory) {
		this.linkedCategory=linkedCategory;
	}

	/**
	 * @return the linkedOperationTitle
	 */
	public OperationTitle getLinkedOperationTitle() {
		return linkedOperationTitle;
	}

	/**
	 * @param linkedOperationTitle
	 *            the linkedOperationTitle to set
	 */
	private void setLinkedOperationTitle(OperationTitle linkedOperationTitle) {
		this.linkedOperationTitle=linkedOperationTitle;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title=title;
	}
}
