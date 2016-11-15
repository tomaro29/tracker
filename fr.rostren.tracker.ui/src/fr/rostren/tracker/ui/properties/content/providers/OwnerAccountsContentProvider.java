package fr.rostren.tracker.ui.properties.content.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.ui.properties.content.comparators.AccountComparator;

public class OwnerAccountsContentProvider extends AbstractContentProvider {

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Owner) {
			return getChildren(element).length > 0;
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Account> children=new ArrayList<>();
		if (parentElement instanceof List<?>) {
			for (Object element: (List<?>)parentElement) {
				if (element instanceof Account) {
					children.add((Account)element);
				}
			}
		}
		else if (parentElement instanceof Owner) {
			children.addAll(((Owner)parentElement).getAccounts());
		}
		Collections.sort(children, new AccountComparator());
		return children.toArray();
	}
}
