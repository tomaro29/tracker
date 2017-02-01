package fr.rostren.tracker.ui.views.internal.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import fr.rostren.tracker.ui.Activator;
import fr.rostren.tracker.ui.views.TrackerHistogramView;

public class RefreshHistogramAction extends Action {
	private final TrackerHistogramView view;

	/**
	 * Constructor.
	 * @param view the histogram view to refresh
	 */
	public RefreshHistogramAction(TrackerHistogramView view) {
		this.view=view;
		setToolTipText("Refresh Histogram");//$NON-NLS-1$
		setImageDescriptor("icons/refresh16X16.gif"); //$NON-NLS-1$
	}

	@Override
	public void setChecked(boolean checked) {
		super.setChecked(false);
	}

	@Override
	public void run() {
		view.refresh();
	}

	/**
	 * Sets the image descriptor
	 * @param imageName the image name
	 */
	private void setImageDescriptor(String imageName) {
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
