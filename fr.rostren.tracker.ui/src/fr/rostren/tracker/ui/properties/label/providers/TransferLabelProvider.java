package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;

public class TransferLabelProvider extends AbstractLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Transfer) {
			return getTransferTitle((Transfer)element) + AbstractLabelProvider.STRING_SEPARATOR + getTransferDate((Transfer)element);
		}
		return super.getText(element);
	}

	/**
	 * Returns the transfer title
	 * @param transfer the {@link Transfer} instance
	 * @return the transfer title
	 */
	private String getTransferTitle(Transfer transfer) {
		return transfer.getOperationTitle() == null || StringUtils.isEmpty(transfer.getOperationTitle().getTitle())	? OperationLabelProvider.STRING_UNDEFINED_TITLE
																													: transfer.getOperationTitle().getTitle();
	}

	/**
	 * Returns the transfer date
	 * @param operation the {@link Transfer} instance
	 * @return the transfer date
	 */
	private String getTransferDate(Transfer operation) {
		return operation.getDate() == null	? OperationLabelProvider.STRING_UNDEFINED_DATE
											: TrackerFactory.eINSTANCE.convertToString(TrackerPackage.Literals.OPERATION__DATE.getEAttributeType(), operation.getDate());
	}
}
