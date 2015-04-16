package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Line Content Class.
 * 
 * @author maro
 *
 */
public class LineContent {
	/**
	 * The Operation Type Class.
	 * 
	 * @author maro
	 *
	 */
	public enum OperationType {
		/** Credit operation. */
		CREDIT,
		/** Debit operation. */
		DEBIT
	}

	/** The default title. */
	private static final String UNDEFINED_CATEGORY_TITLE = "UNDEFINED";
	/** The operation. */
	private Operation operation;

	/** The title. */
	private String title;
	/** The category. */
	private Category linkedCategory;
	/** The operation title. */
	private OperationTitle linkedOperationTitle;

	/**
	 * Constructor.
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
		formatTitle(title);
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
	 * Formats the title.
	 * 
	 * @param string
	 *            the current title
	 */
	private void formatTitle(String string) {
		if (string.startsWith("ECH PRET")) {
			this.title = "ECH PRET";
		} else if (string.startsWith("INTERETS CREDITEURS")) {
			this.title = "INTERETS CREDITEURS";
		} else if (string.startsWith("CB LA POSTE")) {
			this.title = "CB LA POSTE";
		} else if (string.startsWith("CHEQUE")) {
			this.title = "CHEQUE";
		}
		this.title = string;
	}

	/**
	 * Complete the operation.
	 * 
	 * @param tracker
	 *            the tracker root
	 * @param op
	 *            the operation to complete
	 */
	public void completeOperation(Tracker tracker, Operation op) {
		if (linkedCategory == null) {
			linkedCategory = findCategoryInTrackerModel(title, tracker);
		}

		// Adds a title to the operation
		op.setOperationTitle(linkedOperationTitle);

		// Adds the total amount as a subAmount to the operation
		Amount newAmountObject = createCategoryAmount(op.getTotalAmount(),
				linkedCategory);
		op.getSubAmounts().add(newAmountObject);
	}

	/**
	 * Creates an amount.
	 * 
	 * @param amount
	 *            amount to create
	 * @param category
	 *            the linked category
	 * @return the created amount
	 */
	public Amount createCategoryAmount(BigDecimal amount, Category category) {
		Amount amountObject = TrackerFactory.eINSTANCE.createAmount();
		amountObject.setSubAmount(amount);
		amountObject.setCategory(category);
		return amountObject;
	}

	/**
	 * Finds a category given an operation title in the categories repository of
	 * the tracker model. It creates a new category if the corresponding one
	 * does not exist already in the model.
	 * 
	 * @param string
	 *            the title of the category to find
	 * @param tracker
	 *            the tracker model
	 * @return the corresponding category if it exists already, and a new
	 *         category otherwise.
	 */
	public Category findCategoryInTrackerModel(String string, Tracker tracker) {
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
				if (existingOperationTitle.getTitle().equals(string)) {
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
		linkedCategory = (Category) TrackerFactory.eINSTANCE
				.createOperationTitle();
		linkedOperationTitle.setTitle(string);
		titles.add(linkedOperationTitle);
		undefinedCategory.getOperationTitles().add(linkedOperationTitle);
		return undefinedCategory;
	}

	/**
	 * Returns the operation.
	 * 
	 * @return the operation.
	 */
	public Operation getOperation() {
		return operation;
	}
}
