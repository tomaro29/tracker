package fr.rostren.tracker.ui.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractTrackerPropertySection extends AbstractPropertySection {
	protected Composite body;
	protected EObject currentEObject;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		body=getWidgetFactory().createFlatFormComposite(parent);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input=((IStructuredSelection)selection).getFirstElement();
		Assert.isTrue(input instanceof EObject);
		currentEObject=(EObject)input;
	}

	/**
	 * Returns the shell
	 * @return the shell
	 */
	public Shell getShell() {
		Object currentShell=Display.getDefault().getActiveShell();
		if (!(currentShell instanceof Shell)) {
			return null;
		}
		return (Shell)currentShell;
	}

	/**
	 * Returns the current {@link EObject}
	 * @return the current {@link EObject}
	 */
	public EObject getCurrentEObject() {
		return currentEObject;
	}

	/**
	 * Returns the sorted list
	 * @param <T> any type
	 * @param set the set
	 * @param comparator the comparator
	 * @return the sorted list
	 */
	public <T> List<T> getSortedList(Set<T> set, Comparator<T> comparator) {
		List<T> list=new ArrayList<>(set);
		Collections.sort(list, comparator);
		list.removeAll(Collections.singleton(null));
		return list;
	}

	/**
	 * Adds listeners
	 */
	abstract protected void addListeners();

	/**
	 * Disposes listeners
	 */
	abstract protected void disposeListeners();
}
