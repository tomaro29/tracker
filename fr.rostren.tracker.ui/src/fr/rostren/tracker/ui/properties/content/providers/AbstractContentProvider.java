package fr.rostren.tracker.ui.properties.content.providers;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class AbstractContentProvider implements ITreeContentProvider {

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	// DO NOTHING
    }

    @Override
    public void dispose() {
	// DO NOTHING
    }

    @Override
    public Object getParent(Object element) {
	return null;
    }

    @Override
    public Object[] getElements(Object inputElement) {
	return getChildren(inputElement);
    }

    @Override
    public boolean hasChildren(Object element) {
	// Implemented in subClasses !
	return false;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	// Implemented in subClasses !
	return null;
    }
}
