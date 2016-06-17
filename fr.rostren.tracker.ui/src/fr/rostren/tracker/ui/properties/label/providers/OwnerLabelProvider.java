package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Owner;

public class OwnerLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof Owner)
	    return ((Owner) element).getFirstName() + STRING_SEPARATOR + ((Owner) element).getLastName();
	return super.getText(element);
    }
}
