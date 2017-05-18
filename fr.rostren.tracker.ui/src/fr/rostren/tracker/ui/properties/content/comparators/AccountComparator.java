/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Account;

public class AccountComparator implements Comparator<Account> {

	@Override
	public int compare(Account arg1, Account arg2) {
		if (arg1 == null || arg2 == null || arg1.getName() == null) {
			return -1;
		}
		if (arg1.getName() == null) {
			return 1;
		}
		return arg1.getName().compareTo(arg2.getName());
	}
}
