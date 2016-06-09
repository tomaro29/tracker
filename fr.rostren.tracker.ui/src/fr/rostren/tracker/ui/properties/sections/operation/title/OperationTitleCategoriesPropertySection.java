package fr.rostren.tracker.ui.properties.sections.operation.title;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class OperationTitleCategoriesPropertySection extends AbstractPropertySection {
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	// TODO Auto-generated method stub
	// FIXME
	// https://eclipse.org/articles/Article-Tabbed-Properties/tabbed_properties_view.html
	super.createControls(parent, aTabbedPropertySheetPage);
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	// TODO Auto-generated method stub
	super.setInput(part, selection);
    }

    @Override
    public void refresh() {
	// TODO Auto-generated method stub
	super.refresh();
    }
}
