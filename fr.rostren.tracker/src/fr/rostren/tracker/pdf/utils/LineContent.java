package fr.rostren.tracker.pdf.utils;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class LineContent {
	private static final String FACT=" FACT "; //$NON-NLS-1$

	public enum OperationType {
		CREDIT,
		DEBIT
	}

	private Operation operation;

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
	public LineContent(Date date, String title, BigDecimal amount, OperationType type, Origin origin) {
		if (date == null) {
			throw new IllegalArgumentException("The operation date cannot be null."); //$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The operation title cannot be empty or null."); //$NON-NLS-1$
		}
		if (amount == null || amount.equals(new BigDecimal(0.0))) {
			throw new IllegalArgumentException("The operation amount cannot be zero or null."); //$NON-NLS-1$
		}
		if (type == null) {
			throw new IllegalArgumentException("The operation type cannot be null."); //$NON-NLS-1$
		}
		if (origin == null) {
			throw new IllegalArgumentException("The operation origin cannot be null."); //$NON-NLS-1$
		}

		this.setTitle(formatTitle(title));
		if (OperationType.CREDIT.equals(type)) {
			operation=TrackerFactory.eINSTANCE.createCredit();
			operation.setDate(date);
			operation.setTotalAmount(amount);
		}
		else if (OperationType.DEBIT.equals(type)) {
			operation=TrackerFactory.eINSTANCE.createDebit();
			operation.setDate(date);
			operation.setTotalAmount(amount);
		}
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
		Amount newAmountObject=createCategoryAmount(operation.getTotalAmount(), getLinkedCategory());
		operation.getSubAmounts().add(newAmountObject);
	}

	/**
	 * Creates an amount
	 *
	 * @param amount
	 *            amount to create
	 * @param linkedCategory
	 *            the linked category
	 * @return the created amount
	 */
	private Amount createCategoryAmount(BigDecimal amount, Category linkedCategory) {
		Amount amountObject=TrackerFactory.eINSTANCE.createAmount();
		amountObject.setValue(amount);
		amountObject.setCategory(linkedCategory);
		Date wishedDate=TrackerFactory.eINSTANCE.createDate();
		wishedDate.setYear(operation.getDate().getYear());
		wishedDate.setMonth(operation.getDate().getMonth());
		wishedDate.setDay(operation.getDate().getDay());
		amountObject.setWishedDate(wishedDate);
		return amountObject;
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
		return getExistingTitle(title, tracker).filter(existingTitle -> !existingTitle.getCategories().isEmpty())//
				.map(existingTitle -> existingTitle.getCategories().get(0))//
				.orElse(createNewCategory(tracker).orElseThrow(IllegalArgumentException::new));
	}

	/**
	 * Creates a new category
	 * @param tracker
	 *            the tracker model
	 * @return the newly created category
	 */
	private Optional<Category> createNewCategory(Tracker tracker) {
		OperationTitle newTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		setLinkedOperationTitle(newTitle);
		newTitle.setTitle(title);
		tracker.getOperationsTitlesRepositories().getOperationsTitles().add(newTitle);

		Category undefined=null;
		if (operation instanceof Credit) {
			undefined=getUndefinedIncomeCategory(getCategoriesRepository(tracker));
			undefined.getOperationTitles().add(newTitle);
		}
		if (operation instanceof Debit) {
			undefined=getUndefinedSpendingCategory(getCategoriesRepository(tracker));
			undefined.getOperationTitles().add(newTitle);
		}

		return Optional.of(undefined);
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
		for (Category category: income.getIncomes()) {
			if (TrackerUtils.isUndefinedCategory(category)) {
				return category;
			}
		}

		// return the undefined category in a new undefined group
		IncomeCategory category=TrackerFactory.eINSTANCE.createIncomeCategory();
		category.setTitle(TrackerUtils.UNDEFINED_INCOME_TITLE);
		income.getIncomes().add(category);
		return category;
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
		for (Category category: spending.getSpendings()) {
			if (TrackerUtils.isUndefinedCategory(category)) {
				return category;
			}
		}

		// return the undefined category in a new undefined group
		SpendingCategory category=TrackerFactory.eINSTANCE.createSpendingCategory();
		category.setTitle(TrackerUtils.UNDEFINED_SPENDING_TITLE);
		spending.getSpendings().add(category);
		return category;
	}

	/**
	 * Returns the existing title
	 * @param title the title to seek
	 * @param tracker the tracker
	 * @return the existing title
	 */
	private Optional<OperationTitle> getExistingTitle(String title, Tracker tracker) {
		Optional<OperationTitle> operationTitleOpt=TrackerUtils.getOperationTitle(tracker, title);
		operationTitleOpt.ifPresent(operationTitle -> setLinkedOperationTitle(operationTitle));
		return operationTitleOpt;
	}

	/**
	 * Returns the operation
	 *
	 * @return the operation
	 */
	public Operation getOperation() {
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
