package fr.rostren.tracker.ui.properties.sections.repository;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;

public class OriginsRepositoryPropertySection extends AbstractTablePropertySection {
    protected Table originsTable;

    private SelectionAdapter addButtonlistener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    // TODO
	}
    };

    private SelectionAdapter removeButtonListener = new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent event) {
	    // TODO
	}
    };

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.originsTable = createTable(body, null, addButtonlistener, removeButtonListener);
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
    }

    @Override
    public void refresh() {
	// TODO Auto-generated method stub
	super.refresh();
    }

    @Override
    protected void addListeners() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void disposeListeners() {
	// TODO Auto-generated method stub
	disposeButtonsListeners(addButtonlistener, removeButtonListener);
    }
}
