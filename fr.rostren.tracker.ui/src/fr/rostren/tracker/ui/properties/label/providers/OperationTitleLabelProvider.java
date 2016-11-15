package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.OperationTitle;

public class OperationTitleLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_OPERATION_TITLE="UNDEFINED OPERATION TITLE"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof OperationTitle) {
			return StringUtils.isEmpty(((OperationTitle)element).getTitle()) ? OperationTitleLabelProvider.STRING_UNDEFINED_OPERATION_TITLE : ((OperationTitle)element).getTitle();
		}
		return super.getText(element);
	}
}
