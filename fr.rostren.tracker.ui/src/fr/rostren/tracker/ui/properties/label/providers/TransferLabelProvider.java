package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Transfer;

public class TransferLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {

	if (element instanceof Transfer) {
	    StringBuilder sb = new StringBuilder();
	    if (((Transfer) element).getOperationTitle() != null) {
		sb.append(((Transfer) element).getOperationTitle().getTitle());
		sb.append(STRING_SEPARATOR);
	    }
	    if (((Transfer) element).getDate() != null)
		sb.append(((Transfer) element).getDate().toString());
	    return sb.toString();
	}
	return super.getText(element);
    }
}
