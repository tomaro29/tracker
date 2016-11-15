package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Owner;

public class OwnerLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_FIRST_NAME="UNDEFINED First Name"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_LAST_NAME="UNDEFINED Last Name"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Owner) {
			return getOwnerFirstName((Owner)element) + AbstractLabelProvider.STRING_SEPARATOR + getOwnerLastName((Owner)element);
		}
		return super.getText(element);
	}

	private String getOwnerFirstName(Owner owner) {
		return StringUtils.isEmpty(owner.getFirstName()) ? OwnerLabelProvider.STRING_UNDEFINED_FIRST_NAME : owner.getFirstName();
	}

	private String getOwnerLastName(Owner owner) {
		return StringUtils.isEmpty(owner.getLastName()) ? OwnerLabelProvider.STRING_UNDEFINED_LAST_NAME : owner.getLastName();
	}
}
