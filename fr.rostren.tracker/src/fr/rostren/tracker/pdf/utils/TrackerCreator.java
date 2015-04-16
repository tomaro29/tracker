package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CreditOperation;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.DebitOperation;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * The tracker model elements creator.
 * 
 * @author maro
 *
 */
public class TrackerCreator {

	/**
	 * Creates an {@link OriginsRepository}.
	 * 
	 * @return the repository.
	 */
	public OriginsRepository originsRepository() {
		return (OriginsRepository) EcoreUtil
				.create(TrackerPackage.Literals.ORIGINS_REPOSITORY);
	}

	/**
	 * Creates an {@link Origin}.
	 * 
	 * @param originIdentifier
	 *            the origin identifier.
	 * @return the created origin.
	 */
	public Origin origin(String originIdentifier) {
		Origin origin = (Origin) EcoreUtil
				.create(TrackerPackage.Literals.ORIGIN);
		origin.setIdentifier(originIdentifier);
		origin.setType(OriginType.PDF_FILE);
		return origin;
	}

	/**
	 * Creates an {@link Amount}.
	 * 
	 * @param operation
	 *            the operation.
	 * @param amount
	 *            the amount
	 * @param category
	 *            the category.
	 * @return the created amount.
	 */
	public Amount amount(Operation operation, BigDecimal amount,
			Category category) {
		Amount amountObject = (Amount) EcoreUtil
				.create(TrackerPackage.Literals.AMOUNT);
		amountObject.setSubAmount(amount);
		amountObject.setCategory(category);
		operation.getSubAmounts().add(amountObject);
		return amountObject;
	}

	/**
	 * Creates a {@link Category}.
	 * 
	 * @param tracker
	 *            the tracker.
	 * 
	 * @param title
	 *            the title.
	 * @return the created category.
	 */
	public Category category(Tracker tracker, String title) {
		Category category = (Category) EcoreUtil
				.create(TrackerPackage.Literals.CATEGORY);
		category.setTitle(title);
		tracker.getCategoriesRepository().getCategories().add(category);
		return category;
	}

	/**
	 * Creates a new {@link OperationTitle}.
	 * 
	 * @param category
	 *            the category
	 * @param titles
	 *            the list of titles
	 * @param title
	 *            the title
	 * @return the created object.
	 */
	public OperationTitle operationTitle(Category category,
			java.util.List<OperationTitle> titles, String title) {
		OperationTitle operationTitle = (OperationTitle) EcoreUtil
				.create(TrackerPackage.Literals.OPERATION_TITLE);
		operationTitle.setTitle(title);
		titles.add(operationTitle);
		category.getOperationTitles().add(operationTitle);
		return operationTitle;
	}

	/**
	 * 
	 * Creates the {@link CreditOperation}.
	 * 
	 * @param date
	 *            the date
	 * @param amount
	 *            the amount
	 * @param origin
	 *            the origin
	 * @return the created object.
	 */
	public Operation creditOperation(Date date, BigDecimal amount, Origin origin) {
		Operation operation = (CreditOperation) EcoreUtil
				.create(TrackerPackage.Literals.CREDIT_OPERATION);
		operation.setDate(date);
		operation.setTotalAmount(amount);
		operation.setOrigin(origin);
		return operation;
	}

	/**
	 * Creates the {@link DebitOperation}.
	 * 
	 * @param date
	 *            the date
	 * @param amount
	 *            the amount
	 * @param origin
	 *            the origin
	 * @return the created object.
	 */
	public Operation debitOperation(Date date, BigDecimal amount, Origin origin) {
		Operation operation = (DebitOperation) EcoreUtil
				.create(TrackerPackage.Literals.DEBIT_OPERATION);
		operation.setDate(date);
		operation.setTotalAmount(amount);
		operation.setOrigin(origin);
		return operation;
	}

	/**
	 * Creates a {@link Date}.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * 
	 * @return the created date.
	 */
	public Date date(int day, String month, int year) {
		Date date = (Date) EcoreUtil.create(TrackerPackage.Literals.DATE);
		date.setDay(day);
		date.setMonth(getMonthFromContent(month));
		date.setYear(year);
		return date;
	}

	/**
	 * Sets the Month basing on the current content.
	 * 
	 * @param content
	 *            the current content.
	 */
	private Month getMonthFromContent(String content) {
		Month month = null;
		switch (Integer.parseInt(content)) {
		case Month.JAN_VALUE:
			month = Month.JAN;
			break;
		case Month.FEB_VALUE:
			month = Month.FEB;
			break;
		case Month.MARS_VALUE:
			month = Month.MARS;
			break;
		case Month.APR_VALUE:
			month = Month.APR;
			break;
		case Month.MAY_VALUE:
			month = Month.MAY;
			break;
		case Month.JUNE_VALUE:
			month = Month.JUNE;
			break;
		case Month.JULY_VALUE:
			month = Month.JULY;
			break;
		case Month.AUG_VALUE:
			month = Month.AUG;
			break;
		case Month.SEPT_VALUE:
			month = Month.SEPT;
			break;
		case Month.OCT_VALUE:
			month = Month.OCT;
			break;
		case Month.NOV_VALUE:
			month = Month.NOV;
			break;
		case Month.DEC_VALUE:
			month = Month.DEC;
			break;
		default:
			break;
		}
		return month;
	}

}
