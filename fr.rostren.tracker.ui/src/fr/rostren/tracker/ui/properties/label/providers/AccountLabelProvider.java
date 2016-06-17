package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Account;

public class AccountLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof Account)
	    return ((Account) element).getName() + STRING_SEPARATOR + ((Account) element).getIdentifier();
	return super.getText(element);
    }
}
