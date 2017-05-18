/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;

public class OperationSubAmountLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_AMOUNT_VALUE="UNDEFINED AMOUNT VALUE"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_AMOUNT_DATE="UNDEFINED AMOUNT WISHED DATE"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_AMOUNT_CATEGORY="UNDEFINED AMOUNT CATEGORY"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Amount) {
			return getAmountDate((Amount)element) + AbstractLabelProvider.DASH + getAmountCategory((Amount)element) + AbstractLabelProvider.DASH + getAmountValue((Amount)element);
		}
		return super.getText(element);
	}

	/**
	 * Returns amount value
	 * @param amount the given {@link Amount}
	 * @return the amount value
	 */
	private String getAmountValue(Amount amount) {
		if (amount == null || amount.getValue() == 0) {
			return null;
		}
		return StringUtils.isEmpty(String.valueOf(amount.getValue()))	? OperationSubAmountLabelProvider.STRING_UNDEFINED_AMOUNT_VALUE
																		: String.valueOf(amount.getValue()) + " euros"; //$NON-NLS-1$
	}

	/**
	 * Returns amount category
	 * @param amount the given {@link amount}
	 * @return the amount category
	 */
	private String getAmountCategory(Amount amount) {
		if (amount == null) {
			return null;
		}
		return amount.getCategory() == null ? OperationSubAmountLabelProvider.STRING_UNDEFINED_AMOUNT_CATEGORY : amount.getCategory().getTitle();
	}

	/**
	 * Returns amount date
	 * @param amount the given {@link Amount}
	 * @return the amount date
	 */
	private String getAmountDate(Amount amount) {
		return amount.getWishedDate() == null	? OperationSubAmountLabelProvider.STRING_UNDEFINED_AMOUNT_DATE
												: TrackerFactory.eINSTANCE.convertToString(TrackerPackage.Literals.AMOUNT__WISHED_DATE.getEAttributeType(), amount.getWishedDate());
	}
}
