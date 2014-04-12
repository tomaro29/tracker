package fr.rostren.tracker.ui.pdf.utils;

import java.math.BigDecimal;
import java.util.List;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class LineContent {
	public enum OperationType {
		CREDIT, DEBIT
	}

	private final String UNDEFINED_CATEGORY_TITLE = "UNDEFINED";
	private Operation operation;

	private String title;
	private Category linkedCategory;
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
	public LineContent(Date date, String title, BigDecimal amount,
			OperationType type, Origin origin) {
		this.title = formatTitle(title);
		if (OperationType.CREDIT.equals(type)) {
			operation = TrackerFactory.eINSTANCE.createCreditOperation();
			operation.setDate(date);
			operation.setTotalAmount(amount);
		} else if (OperationType.DEBIT.equals(type)) {
			operation = TrackerFactory.eINSTANCE.createDebitOperation();
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
		if (currentTitle.startsWith("ECH PRET")) {
			return "ECH PRET";
		} else if (currentTitle.startsWith("INTERETS CREDITEURS")) {
			return "INTERETS CREDITEURS";
		} else if (currentTitle.startsWith("CB LA POSTE")) {
			return "CB LA POSTE";
		} else if (currentTitle.startsWith("CHEQUE")) {
			return "CHEQUE";
		}
		return currentTitle;
	}

	/**
	 * Complete the operation
	 * 
	 * @param tracker
	 *            the tracker root
	 * @param operation
	 *            the operation to complete
	 * @param account
	 *            the account
	 */
	public void completeOperation(Tracker tracker, Operation operation,
			Account account) {

		if (linkedCategory == null) {
			linkedCategory = findCategory(title, tracker);
		}

		addTitle(operation, tracker);
		addTotalAmount(operation, tracker);
	}

	/**
	 * Adds a title to the operation
	 * 
	 * @param operation
	 *            the operation to edit
	 * @param tracker
	 *            the tracker model
	 */
	private void addTitle(Operation operation, Tracker tracker) {
		operation.setOperationTitle(linkedOperationTitle);
	}

	/**
	 * Adds the total amount as a subAmount to the operation
	 * 
	 * @param operation
	 *            the operation to edit
	 * @param tracker
	 *            the tracker model
	 */
	private void addTotalAmount(Operation operation, Tracker tracker) {
		Amount newAmountObject = createAmount(operation.getTotalAmount(),
				linkedCategory);
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
	private Amount createAmount(BigDecimal amount, Category linkedCategory) {
		Amount amountObject = TrackerFactory.eINSTANCE.createAmount();
		amountObject.setSubAmount(amount);
		amountObject.setCategory(linkedCategory);
		return amountObject;
	}

	/**
	 * Finds a category given an operation title in the categories repository of
	 * the tracker model
	 * 
	 * @param title
	 *            the title to find
	 * @param tracker
	 *            the tracker model
	 * @return the corresponding category
	 */
	private Category findCategory(String title, Tracker tracker) {
		List<Category> categories = tracker.getCategoriesRepository()
				.getCategories();
		List<OperationTitle> titles = tracker.getOperationsTitlesRepositories()
				.getOperationsTitles();
		Category undefinedCategory = null;

		for (Category category : categories) {
			if (UNDEFINED_CATEGORY_TITLE.equals(category.getTitle())) {
				undefinedCategory = category;
			}
			for (OperationTitle existingOperationTitle : category
					.getOperationTitles()) {
				if (existingOperationTitle.getTitle().equals(title)) {
					linkedOperationTitle = existingOperationTitle;
					return category;
				}
			}
		}
		if (undefinedCategory == null) {
			undefinedCategory = TrackerFactory.eINSTANCE.createCategory();
			undefinedCategory.setTitle(UNDEFINED_CATEGORY_TITLE);
			tracker.getCategoriesRepository().getCategories()
					.add(undefinedCategory);
		}
		linkedOperationTitle = TrackerFactory.eINSTANCE.createOperationTitle();
		linkedOperationTitle.setTitle(title);
		titles.add(linkedOperationTitle);
		undefinedCategory.getOperationTitles().add(linkedOperationTitle);
		return undefinedCategory;
	}

	/**
	 * Returns the operation
	 * 
	 * @return the operation
	 */
	public Operation getOperation() {
		return operation;
	}
}
