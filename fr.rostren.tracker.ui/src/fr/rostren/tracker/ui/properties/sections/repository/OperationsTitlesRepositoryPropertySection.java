package fr.rostren.tracker.ui.properties.sections.repository;

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

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.listeners.ListenersUtils;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;

public class OperationsTitlesRepositoryPropertySection extends AbstractTablePropertySection {

    private ITreeContentProvider contentProvider = new OperationsTitlesRepositoryContentProvider();
    private ILabelProvider labelProvider = new OperationTitleLabelProvider();

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
	    OperationsTitleRepository repository = (OperationsTitleRepository) currentEObject;
	    Tracker tracker = (Tracker) repository.eContainer();

	    AddTrackerOperationTitleWizard wizard = new AddTrackerOperationTitleWizard("Operations Titles Repository", //$NON-NLS-1$
		    tracker);
	    WizardDialog wizardDialog = new WizardDialog(getShell(), wizard);
	    if (Window.OK == wizardDialog.open()) {
		OperationTitle newOperationTitle = TrackerFactory.eINSTANCE.createOperationTitle();

		String title = wizard.getOperationTitle();
		if (title != null)
		    newOperationTitle.setTitle(title);

		ListenersUtils.executeAddCommand(repository,
			TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, newOperationTitle);
		refresh();
	    }
	}
    };

    private SelectionAdapter removeButtonListener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    EObject currentEObject = getCurrentEObject();
	    Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
	    OperationsTitleRepository repository = (OperationsTitleRepository) currentEObject;

	    ISelection selection = viewer.getSelection();
	    Assert.isTrue(selection instanceof StructuredSelection);
	    Object elementToRemove = ((StructuredSelection) selection).getFirstElement();
	    ListenersUtils.executeRemoveCommand(repository,
		    TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, elementToRemove);
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
	viewer.setInput(getOperationsTitles());
	addListeners();
    }

    private List<OperationTitle> getOperationsTitles() {
	Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
	List<OperationTitle> operationsTitles = ((OperationsTitleRepository) currentEObject).getOperationsTitles();
	if (operationsTitles == null || operationsTitles.isEmpty())
	    return Collections.emptyList();
	return operationsTitles;
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
