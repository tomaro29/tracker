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
import java.util.List;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;

public class OperationData {
	private LocalDate date;
	private Origin origin;
	private OperationType type;
	private double totalAmount;
	private OperationTitle title;
	private List<Amount> subAmounts=new ArrayList<>();

	/**
	 * Constructor
	 */
	public OperationData() {
		// Do Nothing
	}

	/**
	 *Constructor
	 * @param type the operation type
	 * @param title the operation title
	 * @param totalAmount the operation total amount
	 * @param date the operation date
	 * @param origin the operation origin
	 * @param subAmounts the operation sub amounts list
	 */
	public OperationData(OperationType type, OperationTitle title, double totalAmount, LocalDate date, Origin origin, List<Amount> subAmounts) {
		this.type=type;
		this.title=title;
		this.totalAmount=totalAmount;
		this.date=date;
		this.origin=origin;
		this.subAmounts=subAmounts;
	}

	/**
	 * Returns the operation type
	 * @return the type
	 */
	public OperationType getType() {
		return type;
	}

	/**
	 * Sets the Operation type
	 * @param type the operation type
	 */
	public void setType(OperationType type) {
		this.type=type;
	}

	/**
	 * Returns the operation date
	 * @return the operation date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the operation date
	 * @param value the operation date
	 */
	public void setDate(LocalDate value) {
		date=value;
	}

	/**
	 * Returns the operation origin
	 * @return the operation origin
	 */
	public Origin getOrigin() {
		return origin;
	}

	/**
	 * Sets the operation origin
	 * @param value the operation origin
	 */
	public void setOrigin(Origin value) {
		origin=value;
	}

	/**
	 * Returns the total amount
	 * @return the total amount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets the total amount
	 * @param value the total amount
	 */
	public void setTotalAmount(double value) {
		totalAmount=value;
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return title;
	}

	/**
	 * Sets the operation title
	 * @param value the operation title
	 */
	public void setOperationTitle(OperationTitle value) {
		title=value;
	}

	/**
	 * Returns the operation subAmounts
	 * @return the operation subAmounts
	 */
	public List<Amount> getSubAmounts() {
		return subAmounts;
	}
}
