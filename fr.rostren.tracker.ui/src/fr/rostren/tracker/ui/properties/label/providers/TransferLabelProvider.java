package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Transfer;

public class TransferLabelProvider extends AbstractLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Transfer) {
			return getTransferTitle((Transfer) element) + AbstractLabelProvider.STRING_SEPARATOR
					+ getTransferDate((Transfer) element);
		}
		return super.getText(element);
	}

	private String getTransferTitle(Transfer operation) {
		return operation.getOperationTitle() == null || StringUtils.isEmpty(operation.getOperationTitle().getTitle())
				? OperationLabelProvider.STRING_UNDEFINED_TITLE : operation.getOperationTitle().getTitle();
	}

	private String getTransferDate(Transfer operation) {
		return operation.getDate() == null ? OperationLabelProvider.STRING_UNDEFINED_DATE
				: operation.getDate().toString();
	}
}
