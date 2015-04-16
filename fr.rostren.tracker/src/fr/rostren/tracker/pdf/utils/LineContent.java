package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Line Content Class.
 * 
 * @author maro
 *
 */
public class LineContent {
	/** The default title. */
	private static final String UNDEFINED_CATEGORY_TITLE = "UNDEFINED";
	/** The tracker elements creator. */
	private final TrackerCreator creator = new TrackerCreator();

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
			creator.creditOperation(date, amount, origin);
		} else if (OperationType.DEBIT.equals(type)) {
			creator.debitOperation(date, amount, origin);
		}
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
		creator.amount(op, op.getTotalAmount(), linkedCategory);
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
		Category theCategory = null;

		for (Category category : categories) {
			if (UNDEFINED_CATEGORY_TITLE.equals(category.getTitle())) {
				theCategory = category;
			}
			for (OperationTitle existingOperationTitle : category
					.getOperationTitles()) {
				if (existingOperationTitle.getTitle().equals(string)) {
					linkedOperationTitle = existingOperationTitle;
					return category;
				}
			}
		}
		if (theCategory == null) {
			theCategory = creator.category(tracker, UNDEFINED_CATEGORY_TITLE);
		}
		creator.operationTitle(theCategory, titles, string);
		return theCategory;
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
