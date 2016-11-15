package fr.rostren.tracker.pdf.utils;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;

public class TrackerUtils {

	public static final String UNDEFINED_TITLE="UNDEFINED"; //$NON-NLS-1$

	public static String getCategoryTitle(Amount amount) {
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	public static String getAmountValue(Amount amount) {
		return amount.getValue() == null ? StringUtils.EMPTY : amount.getValue().toString();
	}

	public static String getOperationTitleAsString(Operation operation) {
		return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
	}

	public static String getOperationTotalAmount(Operation operation) {
		return operation.getTotalAmount() == null ? StringUtils.EMPTY : operation.getTotalAmount().toString();
	}

	public static String getOperationDate(Operation operation) {
		return operation.getDate() == null ? StringUtils.EMPTY : operation.getDate().toString();
	}

	public static Tracker getTracker(EObject eObject) {
		EObject parent=eObject;
		while (parent != null && !(parent instanceof Tracker)) {
			parent=parent.eContainer();
		}
		return parent == null ? null : (Tracker)parent;
	}

	public static Origin getOperationOrigin(Operation operation, String originId) {
		Tracker tracker=TrackerUtils.getTracker(operation);
		EList<Origin> origins=tracker.getOriginsRepository().getOrigins();
		for (Origin origin: origins) {
			if (origin.getIdentifier() != null && origin.getIdentifier().equals(originId)) {
				return origin;
			}
		}
		return null;
	}

	public static OperationTitle getOperationTitle(Operation operation, String operationTitleString) {
		Tracker tracker=TrackerUtils.getTracker(operation);
		EList<OperationTitle> operationTitles=tracker.getOperationsTitlesRepositories().getOperationsTitles();
		for (OperationTitle opTitle: operationTitles) {
			if (opTitle.getTitle() != null && opTitle.getTitle().equals(operationTitleString)) {
				return opTitle;
			}
		}
		return null;
	}

	public static Category getAmountCategory(Amount amount, String categoryTitle) {
		Tracker tracker=TrackerUtils.getTracker(amount);
		EList<Category> categories=tracker.getCategoriesRepository().getCategories();
		for (Category category: categories) {
			if (category.getTitle() != null && category.getTitle().equals(categoryTitle)) {
				return category;
			}
		}
		return null;
	}
}
