package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

public class TrackerUtils {

	public static Tracker getTracker(Account account) {
		Owner owner = account.eContainer() instanceof Owner ? (Owner) account
				.eContainer() : null;
		return owner != null && owner.eContainer() instanceof Tracker ? (Tracker) owner
				.eContainer() : null;
	}

}
