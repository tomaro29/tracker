package fr.rostren.tracker.ui.views.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import fr.rostren.tracker.ui.Activator;
import fr.rostren.tracker.ui.views.TrackerHistogramView;

public abstract class AbstractMenuCreatorAction extends Action implements IMenuCreatorAction {
	protected final TrackerHistogramView view;
	protected Menu menu;

	/**
	 * Constructor
	 * @param view the histogram view
	 */
	public AbstractMenuCreatorAction(TrackerHistogramView view) {
		this.view=view;
		setMenuCreator(this);
	}

	@Override
	public void run() {
		//Do Nothing
	}

	@Override
	public void dispose() {
		//Do Nothing
	}

	@Override
	public void setChecked(boolean checked) {
		super.setChecked(false);
	}

	@Override
	public Menu getMenu(Control parent) {
		return null;
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}

	/**
	 * Sets the image descriptor
	 * @param imageName the image name
	 */
	protected void setImageDescriptor(String imageName) {
		try {
			URL url=new URL(Activator.getDefault().getBundle().getEntry("/"), imageName);
			ImageDescriptor descriptor=ImageDescriptor.createFromURL(url);
			setImageDescriptor(descriptor);
		}
		catch (MalformedURLException e) {
			throw new IllegalArgumentException("The image does not exist.");
		}
	}
}
