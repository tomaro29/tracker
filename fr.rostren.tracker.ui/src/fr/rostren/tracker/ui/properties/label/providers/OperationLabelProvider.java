/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;

public class OperationLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_TITLE="UNDEFINED Title"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_DATE="UNDEFINED Date"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Operation) {
			return getOperationDate((Operation)element)	+ AbstractLabelProvider.DASH + getOperationType((Operation)element) + AbstractLabelProvider.DASH
					+ getOperationTitle((Operation)element);
		}
		return super.getText(element);
	}

	/**
	 * Returns the operation class name
	 * @param operation the operation
	 * @return the operation type
	 */
	private String getOperationType(Operation operation) {
		return operation.eClass().getName();
	}

	/**
	 * Returns operation title
	 * @param operation the given {@link Operation}
	 * @return the operation title
	 */
	private String getOperationTitle(Operation operation) {
		return operation.getOperationTitle() == null || StringUtils.isEmpty(operation.getOperationTitle().getTitle())	? OperationLabelProvider.STRING_UNDEFINED_TITLE
																														: operation.getOperationTitle().getTitle();
	}

	/**
	 * Returns operation date
	 * @param operation the given {@link Operation}
	 * @return the operation date
	 */
	private String getOperationDate(Operation operation) {
		return operation.getDate() == null	? OperationLabelProvider.STRING_UNDEFINED_DATE
											: TrackerFactory.eINSTANCE.convertToString(TrackerPackage.Literals.OPERATION__DATE.getEAttributeType(), operation.getDate());
	}
}
