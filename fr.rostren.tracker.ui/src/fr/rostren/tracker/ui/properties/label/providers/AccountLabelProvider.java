package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Account;

public class AccountLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_NAME = "UNDEFINED Name"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_IDENTIFIER = "UNDEFINED Identifier"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Account) {
			return getAccountName((Account) element) + AbstractLabelProvider.STRING_SEPARATOR
					+ getAccountIdentifier((Account) element);
		}
		return super.getText(element);
	}

	private String getAccountName(Account account) {
		return StringUtils.isEmpty(account.getName()) ? AccountLabelProvider.STRING_UNDEFINED_NAME : account.getName();
	}

	private String getAccountIdentifier(Account account) {
		return account.getIdentifier() == 0 ? AccountLabelProvider.STRING_UNDEFINED_IDENTIFIER
				: String.valueOf(account.getIdentifier());
	}
}
