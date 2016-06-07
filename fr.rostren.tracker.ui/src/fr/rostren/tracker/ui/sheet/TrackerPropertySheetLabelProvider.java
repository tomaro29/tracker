package fr.rostren.tracker.ui.sheet;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

public class TrackerPropertySheetLabelProvider extends DecoratingLabelProvider {

    public TrackerPropertySheetLabelProvider(ILabelProvider provider) {
	super(provider, null);
    }

}
