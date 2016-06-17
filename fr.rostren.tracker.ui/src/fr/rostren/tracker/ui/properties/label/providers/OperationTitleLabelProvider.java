package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.OperationTitle;

public class OperationTitleLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof OperationTitle)
	    return ((OperationTitle) element).getTitle();
	return super.getText(element);
    }
}
