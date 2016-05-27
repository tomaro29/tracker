package fr.rostren.tracker.pdf.utils;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

public class TrackerUtils {

    public static final String UNDEFINED_TITLE = "UNDEFINED"; //$NON-NLS-1$

    public static Tracker getTracker(Account account) {
	Owner owner = account.eContainer() instanceof Owner ? (Owner) account.eContainer() : null;
	return owner != null && owner.eContainer() instanceof Tracker ? (Tracker) owner.eContainer() : null;
    }

    public static String getCategoryTitle(Amount amount) {
	return amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle();
    }

    public static String getAmountValue(Amount amount) {
	return amount.getValue() == null ? StringUtils.EMPTY : amount.getValue().toString();
    }

    public static String getOperationTitle(Operation operation) {
	return operation.getOperationTitle() == null ? StringUtils.EMPTY : operation.getOperationTitle().getTitle();
    }

    public static String getOperationTotalAmount(Operation operation) {
	return operation.getTotalAmount() == null ? StringUtils.EMPTY : operation.getTotalAmount().toString();
    }

    public static String getOperationDate(Operation operation) {
	return operation.getDate() == null ? StringUtils.EMPTY : operation.getDate().toString();
    }

}
