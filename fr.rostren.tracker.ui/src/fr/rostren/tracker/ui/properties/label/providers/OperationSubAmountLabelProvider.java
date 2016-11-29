package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Amount;

public class OperationSubAmountLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_AMOUNT_VALUE="UNDEFINED AMOUNT VALUE"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_AMOUNT_CATEGORY="UNDEFINED AMOUNT CATEGORY"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Amount) {
			return getAmountCategory((Amount)element) + AbstractLabelProvider.DASH + getAmountValue((Amount)element);
		}
		return super.getText(element);
	}

	/**
	 * Returns amount value
	 * @param amount the given {@link Amount}
	 * @return the amount value
	 */
	private String getAmountValue(Amount amount) {
		return StringUtils.isEmpty(amount.getValue().toString()) ? OperationSubAmountLabelProvider.STRING_UNDEFINED_AMOUNT_VALUE : amount.getValue().toString() + " euros"; //$NON-NLS-1$
	}

	/**
	 * Returns amount category
	 * @param amount the given {@link amount}
	 * @return the amount category
	 */
	private String getAmountCategory(Amount amount) {
		return amount.getCategory() == null ? OperationSubAmountLabelProvider.STRING_UNDEFINED_AMOUNT_CATEGORY : amount.getCategory().getTitle();
	}
}
