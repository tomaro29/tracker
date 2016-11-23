package fr.rostren.tracker.pdf.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class TrackerUtils {

	public static final String UNDEFINED_TITLE="UNDEFINED"; //$NON-NLS-1$
	private static Map<String, OperationTitle> operationTitlesMap=new HashMap<>();

	/**
	 * Returns the category title
	 * @param amount the amount
	 * @return the category title
	 */
	public static String getCategoryTitle(Amount amount) {
		return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
	}

	/**
	 * Returns the amount value
	 * @param amount the amount
	 * @return the amount value
	 */
	public static String getAmountValue(Amount amount) {
		return amount.getValue() == null ? StringUtils.EMPTY : amount.getValue().toString();
	}

	/**
	 * Returns the operation title as a {@link String}
	 * @param operation the operation
	 * @return the operation title as a {@link String}
	 */
	public static String getOperationTitleAsString(Operation operation) {
		return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
	}

	/**
	 * Returns the operation total amount as a {@link String}
	 * @param operation the operation
	 * @return the operation total amount as a {@link String}
	 */
	public static String getOperationTotalAmount(Operation operation) {
		return operation.getTotalAmount() == null ? StringUtils.EMPTY : operation.getTotalAmount().toString();
	}

	/**
	 * Returns the operation date as a {@link String}
	 * @param operation the operation
	 * @return the operation date as a {@link String}
	 */
	public static String getOperationDate(Operation operation) {
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
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();
		if (repository == null) {
			repository=TrackerFactory.eINSTANCE.createOperationsTitleRepository();
			tracker.setOperationsTitlesRepositories(repository);
		}
		for (OperationTitle operationTitle: repository.getOperationsTitles()) {
			if (operationTitle.getTitle().equals(title)) {
				return operationTitle;
			}
		}
		return null;
	}

	/**
	 * Adds an operation title to the map
	 * @param title the title to add
	 */
	public static void addOperationTitleToMap(OperationTitle title) {
		TrackerUtils.operationTitlesMap.put(title.getTitle(), title);
	}

	/**
	 * Returns the operation origin
	 * @param operation the operation
	 * @param originId the origin Id
	 * @return the operation origin
	 */
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

	/**
	 * Returns the {@link OperationTitle} instance
	 * @param operation the operation
	 * @param operationTitleString the operation title as a {@link String}
	 * @return the {@link OperationTitle} instance
	 */
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

	/**
	 * Returns the amount category
	 * @param amount the amount
	 * @param categoryTitle the category title as a {@link String}
	 * @return the amount category
	 */
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

	/**
	 * <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 * @param category the category
	 * @return <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 */
	public static boolean isUndefinedCategory(Category category) {
		if (TrackerUtils.UNDEFINED_TITLE.equals(category.getTitle())) {
			return true;
		}
		return false;
	}
}
