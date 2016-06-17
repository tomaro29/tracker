package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Operation;

public class OperationLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof Operation) {
	    StringBuilder sb = new StringBuilder();
	    if (((Operation) element).getOperationTitle() != null) {
		sb.append(((Operation) element).getOperationTitle().getTitle());
		sb.append(STRING_SEPARATOR);
	    }
	    if (((Operation) element).getDate() != null)
		sb.append(((Operation) element).getDate().toString());
	    return sb.toString();
	}
	return super.getText(element);
    }
}
