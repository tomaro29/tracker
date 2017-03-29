package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Account;

public class AccountLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_IDENTIFIER="UNDEFINED Identifier"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Account) {
			return getAccountName((Account)element) + AbstractLabelProvider.DASH + getAccountIdentifier((Account)element);
		}
		return super.getText(element);
	}

	/**
	 * Returns the {@link Account} type
	 * @param account the given account
	 * @return the {@link Account} type
	 */
	private String getAccountType(Account account) {
		return account.getClass().getInterfaces()[0].getSimpleName();
	}

	/**
	 * Returns the {@link Account} name
	 * @param account the given account
	 * @return the {@link Account} name
	 */
	private String getAccountName(Account account) {
		return StringUtils.isEmpty(account.getName()) ? getAccountType(account) : account.getName();
	}

	/**
	 * Returns the account identifier
	 * @param account the given account
	 * @return the account identifier
	 */
	private String getAccountIdentifier(Account account) {
		return account.getIdentifier() == 0 ? AccountLabelProvider.STRING_UNDEFINED_IDENTIFIER : String.valueOf(account.getIdentifier());
	}
}
