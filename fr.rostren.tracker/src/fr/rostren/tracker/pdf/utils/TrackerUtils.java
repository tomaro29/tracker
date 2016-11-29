package fr.rostren.tracker.pdf.utils;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class TrackerUtils {

	public static final String UNDEFINED_TITLE="UNDEFINED"; //$NON-NLS-1$

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
		EList<Category> categories=tracker.getCategoriesRepository().getCategories();
		for (Category category: categories) {
			if (title.equals(category.getTitle())) {
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
		if (category == null) {
			throw new IllegalArgumentException("The category cannot be null.");//$NON-NLS-1$
		}
		if (TrackerUtils.UNDEFINED_TITLE.equals(category.getTitle())) {
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
		for (Category category: tracker.getCategoriesRepository().getCategories()) {
			if (StringUtils.deleteWhitespace(category.getTitle()).equals(StringUtils.deleteWhitespace(title))) {
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
}
