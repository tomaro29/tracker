package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Operation;

public class OperationLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_TITLE = "UNDEFINED Title"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_DATE = "UNDEFINED Date"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Operation) {
			return getOperationTitle((Operation) element) + AbstractLabelProvider.STRING_SEPARATOR
					+ getOperationDate((Operation) element);
		}
		return super.getText(element);
	}

	private String getOperationTitle(Operation operation) {
		return operation.getOperationTitle() == null || StringUtils.isEmpty(operation.getOperationTitle().getTitle())
				? OperationLabelProvider.STRING_UNDEFINED_TITLE : operation.getOperationTitle().getTitle();
	}

	private String getOperationDate(Operation operation) {
		return operation.getDate() == null ? OperationLabelProvider.STRING_UNDEFINED_DATE
				: operation.getDate().toString();
	}
}
