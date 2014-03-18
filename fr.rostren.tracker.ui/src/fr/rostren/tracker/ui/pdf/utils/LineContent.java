package fr.rostren.tracker.ui.pdf.utils;

import java.util.List;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
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

	public LineContent(Date date, String title, Float amount, OperationType type) {
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
	}

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

	public void completeOperation(Tracker tracker) {
		addTitle(tracker);
		addTotalAmount(tracker);
	}

	private void addTitle(Tracker tracker) {
		if (linkedCategory == null) {
			linkedCategory = findCategory(tracker);
		}
		operation.setOperationTitle(linkedOperationTitle);
	}

	private void addTotalAmount(Tracker tracker) {
		if (linkedCategory == null) {
			linkedCategory = findCategory(tracker);
		}
		Amount newAmountObject = createAmount(operation.getTotalAmount(),
				linkedCategory);
		operation.getSubAmounts().add(newAmountObject);
	}

	private Amount createAmount(Float amount, Category linkedCategory) {
		Amount amountObject = TrackerFactory.eINSTANCE.createAmount();
		amountObject.setSubAmount(amount);
		amountObject.setCategory(linkedCategory);
		return amountObject;
	}

	private Category findCategory(Tracker tracker) {
		List<Category> categories = tracker.getCategoriesRepository()
				.getCategories();
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
		undefinedCategory.getOperationTitles().add(linkedOperationTitle);
		return undefinedCategory;
	}

	public Operation getOperation() {
		return operation;
	}
}
