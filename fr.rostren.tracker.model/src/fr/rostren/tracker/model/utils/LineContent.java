/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.model.utils;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;

public class LineContent {

	private static final String FACT=" FACT "; //$NON-NLS-1$

	private final OperationData operation;

	/** The title. */
	private final String title;
	/** The category. */
	private Category linkedCategory;
	/** The operation title. */
	private OperationTitle linkedOperationTitle;

	/**
	 * Constructor
	 * @param date the date of the operation
	 * @param title the title of the operation
	 * @param amount the amount of the operation
	 * @param type the operation type
	 * @param origin the operation origin
	 */
	public LineContent(LocalDate date, String title, double amount, OperationType type, Origin origin) {
		if (date == null) {
			throw new IllegalArgumentException("The operation date cannot be null."); //$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The operation title cannot be empty or null."); //$NON-NLS-1$
		}
		if (amount == 0.0) {
			throw new IllegalArgumentException("The operation amount cannot be zero."); //$NON-NLS-1$
		}
		if (type == null) {
			throw new IllegalArgumentException("The operation type cannot be null."); //$NON-NLS-1$
		}
		if (origin == null) {
			throw new IllegalArgumentException("The operation origin cannot be null."); //$NON-NLS-1$
		}

		this.title=formatTitle(title);
		operation=new OperationData(type, null, amount, date, origin, new ArrayList<>());
	}

	/**
	 * Formats the title
	 * @param currentTitle the current title
	 * @return the formatted title.
	 */
	private String formatTitle(String currentTitle) {
		if (currentTitle.startsWith("ECH PRET")) { //$NON-NLS-1$
			return "ECH PRET"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("INTERETS CREDITEURS")) { //$NON-NLS-1$
			return "INTERETS CREDITEURS"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("CB LA POSTE")) { //$NON-NLS-1$
			return "CB LA POSTE"; //$NON-NLS-1$
		}
		else if (currentTitle.startsWith("CHEQUE")) { //$NON-NLS-1$
			return "CHEQUE"; //$NON-NLS-1$
		}
		else if (currentTitle.contains(LineContent.FACT)) {
			return currentTitle.split(LineContent.FACT)[0];
		}
		return currentTitle;
	}

	/**
	 * Complete the operation
	 * @param tracker the tracker root
	 */
	public void completeOperation(Tracker tracker) {
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null."); //$NON-NLS-1$
		}
		linkedCategory=findCategoryInTrackerModel(title, tracker);
		operation.setOperationTitle(linkedOperationTitle);
		operation.getSubAmounts().add(TrackerFactory.eINSTANCE.createAmount(operation, operation.getTotalAmount(), linkedCategory));
	}

	/**
	 * Finds a category based on the given operation title.
	 * It creates a new category if the corresponding one
	 * does not exist already in the model.
	 * @param title the title of the category to find
	 * @param tracker the tracker model
	 * @return the corresponding category if it exists already, and a new
	 *         category otherwise.
	 */
	private Category findCategoryInTrackerModel(String title, Tracker tracker) {
		linkedOperationTitle=null;
		TrackerUtils.getTrackerService(tracker).findOperationTitle(title).ifPresent(operationTitle -> linkedOperationTitle=operationTitle);
		if (linkedOperationTitle != null && !linkedOperationTitle.getCategories().isEmpty()) {
			return linkedOperationTitle.getCategories().get(0);
		}
		OperationTitle newTitle=linkedOperationTitle == null ? TrackerFactory.eINSTANCE.createOperationTitle(tracker, title) : linkedOperationTitle;
		linkedOperationTitle=newTitle;
		return TrackerFactory.eINSTANCE.createCategory(tracker, newTitle, operation.getType());
	}

	/**
	 * @return the operation data
	 */
	public OperationData getOperation() {
		return operation;
	}

	/**
	 * @return the linkedCategory
	 */
	public Category getLinkedCategory() {
		return linkedCategory;
	}

	/**
	 * @return the linkedOperationTitle
	 */
	public OperationTitle getLinkedOperationTitle() {
		return linkedOperationTitle;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}
