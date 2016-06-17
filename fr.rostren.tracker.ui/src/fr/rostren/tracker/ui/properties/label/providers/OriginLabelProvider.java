package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Origin;

public class OriginLabelProvider extends AbstractLabelProvider {
    @Override
    public String getText(Object element) {
	if (element instanceof Origin)
	    return ((Origin) element).getType() + STRING_SEPARATOR + ((Origin) element).getIdentifier();
	return super.getText(element);
    }
}
