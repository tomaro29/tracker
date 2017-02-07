package fr.rostren.tracker.ui.views.internal.menu.creators;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import fr.rostren.tracker.ui.views.TrackerHistogramView;
import fr.rostren.tracker.ui.views.internal.AbstractMenuCreatorAction;

public class FilterMenuCreatorAction extends AbstractMenuCreatorAction {

	/**
	 * Constructor.
	 * @param view the histogram view in witch the filter will be applied
	 */
	public FilterMenuCreatorAction(TrackerHistogramView view) {
		super(view);
		setToolTipText("Filter Histogram");//$NON-NLS-1$
		setImageDescriptor("icons/filter16X16.png"); //$NON-NLS-1$
	}

	@Override
	public Menu getMenu(Control parent) {
		if (menu != null && !menu.isDisposed()) {
			menu.dispose();
		}

		menu=new Menu(parent);
		addActionToMenu(menu, new ShowMenuCreatorAction(view));
		addActionToMenu(menu, new SelectMenuCreatorAction(view));
		addActionToMenu(menu, new FilterByMenuCreatorAction(view));
		addActionToMenu(menu, new CompareMenuCreatorAction(view));
		return menu;
	}
}
