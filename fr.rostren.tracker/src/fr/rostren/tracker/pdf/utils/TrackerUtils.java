package fr.rostren.tracker.pdf.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
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
	Category category = amount.getCategory();
	return category == null ? StringUtils.EMPTY : category.getTitle();
    }

    public static String getSubAmount(Amount amount) {
	BigDecimal subAmount = amount.getSubAmount();
	return subAmount == null ? StringUtils.EMPTY : subAmount.toString();
    }

    public static String getOperationTotalAmount(Operation operation) {
	BigDecimal totalAmount = operation.getTotalAmount();
	return totalAmount == null ? StringUtils.EMPTY : totalAmount.toString();
    }

    public static String getOperationDate(Operation operation) {
	Date date = operation.getDate();
	return date == null ? StringUtils.EMPTY : date.toString();
    }

}
