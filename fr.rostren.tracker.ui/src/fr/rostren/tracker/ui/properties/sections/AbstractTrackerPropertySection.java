package fr.rostren.tracker.ui.properties.sections;

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
	this.body = getWidgetFactory().createFlatFormComposite(parent);
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
	Assert.isTrue(selection instanceof IStructuredSelection);
	Object input = ((IStructuredSelection) selection).getFirstElement();
	Assert.isTrue(input instanceof EObject);
	this.currentEObject = (EObject) input;
    }

    public Shell getShell() {
	Object currentShell = Display.getDefault().getActiveShell();
	if (!(currentShell instanceof Shell))
	    return null;
	return (Shell) currentShell;
    }

    public EObject getCurrentEObject() {
	return currentEObject;
    }

    abstract protected void addListeners();

    abstract protected void disposeListeners();
}
