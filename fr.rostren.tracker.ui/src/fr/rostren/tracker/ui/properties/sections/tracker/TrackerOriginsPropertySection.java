package fr.rostren.tracker.ui.properties.sections.tracker;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.TrackerOriginsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;

public class TrackerOriginsPropertySection extends AbstractTablePropertySection {

    private ITreeContentProvider contentProvider = new TrackerOriginsContentProvider();
    private ILabelProvider labelProvider = new OriginLabelProvider();

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof Tracker);
	    Tracker tracker = (Tracker) currentEObject;
	    OriginsRepository repository = tracker.getOriginsRepository();

	    AddTrackerOriginWizard wizard = new AddTrackerOriginWizard("Origins Repository", tracker); //$NON-NLS-1$
	    WizardDialog wizardDialog = new WizardDialog(getShell(), wizard);
	    if (Window.OK == wizardDialog.open()) {
		Origin newOrigin = TrackerFactory.eINSTANCE.createOrigin();

		String identifier = wizard.getIdentifier();
		if (identifier != null)
		    newOrigin.setIdentifier(identifier);
		OriginType type = wizard.getType();
		if (type != null)
		    newOrigin.setType(type);

		ListenersUtils.executeAddCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS,
			newOrigin);
		refresh();
	    }
	}
    };

    private SelectionAdapter removeButtonListener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof Tracker);
	    Tracker tracker = (Tracker) currentEObject;
	    OriginsRepository repository = tracker.getOriginsRepository();

	    ISelection selection = viewer.getSelection();
	    Assert.isTrue(selection instanceof StructuredSelection);
	    Object elementToRemove = ((StructuredSelection) selection).getFirstElement();
	    ListenersUtils.executeRemoveCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS,
		    elementToRemove);
	    refresh();
	}
    };

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.table = createTable(body, null, addButtonlistener, removeButtonListener);
	this.viewer = new TableViewer(table);
	viewer.setContentProvider(contentProvider);
	viewer.setLabelProvider(labelProvider);
	addListeners();
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
    }

    @Override
    public void refresh() {
	disposeListeners();
	viewer.setInput(getOrigins());
	addListeners();
    }

    private List<Origin> getOrigins() {
	Assert.isTrue(currentEObject instanceof Tracker);
	List<Origin> origins = ((Tracker) currentEObject).getOriginsRepository().getOrigins();
	if (origins == null || origins.isEmpty())
	    return Collections.emptyList();
	return origins;
    }

    @Override
    protected void addListeners() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void disposeListeners() {
	// TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
	disposeButtonsListeners(addButtonlistener, removeButtonListener);
    }
}
