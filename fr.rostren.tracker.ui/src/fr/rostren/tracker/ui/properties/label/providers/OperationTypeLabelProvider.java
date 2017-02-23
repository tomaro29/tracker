package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.model.utils.OperationType;

public class OperationTypeLabelProvider extends AbstractLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof OperationType) {
			return ((OperationType)element).toString();
		}
		return super.getText(element);
	}
}
