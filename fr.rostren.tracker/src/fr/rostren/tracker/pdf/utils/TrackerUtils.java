package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

/**
 * A util Class.
 * 
 * @author maro
 *
 */
public class TrackerUtils {
	/**
	 * Returns the tracker root model element basing on the given account.
	 * 
	 * @param account
	 *            the account
	 * @return the tracker root model element basing on the given account.
	 */
	public Tracker getTracker(Account account) {
		Owner owner = account.eContainer() instanceof Owner ? (Owner) account
				.eContainer() : null;
		return owner != null && owner.eContainer() instanceof Tracker ? (Tracker) owner
				.eContainer() : null;
	}
}
