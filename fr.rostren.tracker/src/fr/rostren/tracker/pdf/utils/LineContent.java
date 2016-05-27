package fr.rostren.tracker.pdf.utils;

import java.math.BigDecimal;
import java.util.List;

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
    public LineContent(Date date, String title, BigDecimal amount, OperationType type, Origin origin) {
	this.setTitle(formatTitle(title));
	if (OperationType.CREDIT.equals(type)) {
	    this.operation = TrackerFactory.eINSTANCE.createCredit();
	    this.operation.setDate(date);
	    this.operation.setTotalAmount(amount);
	} else if (OperationType.DEBIT.equals(type)) {
	    this.operation = TrackerFactory.eINSTANCE.createDebit();
	    this.operation.setDate(date);
	    this.operation.setTotalAmount(amount);
	}
	this.operation.setOrigin(origin);
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
	} else if (currentTitle.startsWith("INTERETS CREDITEURS")) { //$NON-NLS-1$
	    return "INTERETS CREDITEURS"; //$NON-NLS-1$
	} else if (currentTitle.startsWith("CB LA POSTE")) { //$NON-NLS-1$
	    return "CB LA POSTE"; //$NON-NLS-1$
	} else if (currentTitle.startsWith("CHEQUE")) { //$NON-NLS-1$
	    return "CHEQUE"; //$NON-NLS-1$
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
     */
    public void completeOperation(Tracker tracker, Operation operation) {
	if (getLinkedCategory() == null) {
	    setLinkedCategory(findCategoryInTrackerModel(getTitle(), tracker));
	}

	// Adds a title to the operation
	operation.setOperationTitle(getLinkedOperationTitle());

	// Adds the total amount as a subAmount to the operation
	Amount newAmountObject = createCategoryAmount(operation.getTotalAmount(), getLinkedCategory());
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
    public Amount createCategoryAmount(BigDecimal amount, Category linkedCategory) {
	Amount amountObject = TrackerFactory.eINSTANCE.createAmount();
	amountObject.setValue(amount);
	amountObject.setCategory(linkedCategory);
	return amountObject;
    }

    /**
     * Finds a category given an operation title in the categories repository of
     * the tracker model. It creates a new category if the corresponding one
     * does not exist already in the model.
     * 
     * @param title
     *            the title of the category to find
     * @param tracker
     *            the tracker model
     * @return the corresponding category if it exists already, and a new
     *         category otherwise.
     */
    public Category findCategoryInTrackerModel(String title, Tracker tracker) {
	List<Category> categories = tracker.getCategoriesRepository().getCategories();
	List<OperationTitle> titles = tracker.getOperationsTitlesRepositories().getOperationsTitles();
	Category undefinedCategory = null;

	for (Category category : categories) {
	    if (TrackerUtils.UNDEFINED_TITLE.equals(category.getTitle())) {
		undefinedCategory = category;
	    }
	    for (OperationTitle existingOperationTitle : category.getOperationTitles()) {
		if (existingOperationTitle.getTitle().equals(title)) {
		    setLinkedOperationTitle(existingOperationTitle);
		    return category;
		}
	    }
	}
	if (undefinedCategory == null) {
	    undefinedCategory = TrackerFactory.eINSTANCE.createCategory();
	    undefinedCategory.setTitle(TrackerUtils.UNDEFINED_TITLE);
	    tracker.getCategoriesRepository().getCategories().add(undefinedCategory);
	}
	setLinkedOperationTitle(TrackerFactory.eINSTANCE.createOperationTitle());
	getLinkedOperationTitle().setTitle(title);
	titles.add(getLinkedOperationTitle());
	undefinedCategory.getOperationTitles().add(getLinkedOperationTitle());
	return undefinedCategory;
    }

    /**
     * Returns the operation
     * 
     * @return the operation
     */
    public Operation getOperation() {
	return this.operation;
    }

    /**
     * @param operation
     *            the operation to set
     */
    public void setOperation(Operation operation) {
	this.operation = operation;
    }

    /**
     * @return the linkedCategory
     */
    public Category getLinkedCategory() {
	return this.linkedCategory;
    }

    /**
     * @param linkedCategory
     *            the linkedCategory to set
     */
    public void setLinkedCategory(Category linkedCategory) {
	this.linkedCategory = linkedCategory;
    }

    /**
     * @return the linkedOperationTitle
     */
    public OperationTitle getLinkedOperationTitle() {
	return this.linkedOperationTitle;
    }

    /**
     * @param linkedOperationTitle
     *            the linkedOperationTitle to set
     */
    public void setLinkedOperationTitle(OperationTitle linkedOperationTitle) {
	this.linkedOperationTitle = linkedOperationTitle;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
}
