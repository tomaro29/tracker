package fr.rostren.tracker.ui.properties.label.providers;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class AbstractLabelProvider implements ILabelProvider {
    protected static final String STRING_SEPARATOR = " "; //$NON-NLS-1$
    protected static final String UNDEFINED_LABEL = "UNDEFINED"; //$NON-NLS-1$

    @Override
    public void removeListener(ILabelProviderListener listener) {
	// DO NOTHING
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
	return false;
    }

    @Override
    public void dispose() {
	// DO NOTHING
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
	// DO NOTHING
    }

    @Override
    public String getText(Object element) {
	if (element instanceof String[])
	    return ((String[]) element)[0];
	if (element instanceof String)
	    return (String) element;
	return null;
    }

    @Override
    public Image getImage(Object element) {
	return null;
    }
}
