package fr.rostren.tracker.ui.properties.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

public abstract class AbstractAddWizardPage extends WizardPage {

    protected AbstractAddWizardPage(String pageName) {
	super(pageName);
    }

    @Override
    public void createControl(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	GridLayout layout = new GridLayout();
	layout.numColumns = 2;
	layout.makeColumnsEqualWidth = false;
	layout.marginWidth = 0;
	layout.marginHeight = 0;
	container.setLayout(layout);

	createContainer(container);

	setControl(container);
	setPageComplete(true);
    }

    abstract protected void createContainer(Composite parent);

    protected List<Object> getOperations(Tracker tracker) {
	List<Object> operations = new ArrayList<>();
	for (Owner owner : tracker.getOwners()) {
	    for (Account account : owner.getAccounts()) {
		if (account instanceof CheckingAccount)
		    operations.addAll(((CheckingAccount) account).getOperations());
	    }
	}
	return operations;
    }

    protected List<Object> getOperationsTitles(Tracker tracker) {
	return new ArrayList<>(tracker.getOperationsTitlesRepositories().getOperationsTitles());
    }

    protected List<Object> getOrigins(Tracker tracker) {
	return new ArrayList<>(tracker.getOriginsRepository().getOrigins());
    }

    protected List<Object> getCategories(Tracker tracker) {
	return new ArrayList<>(tracker.getCategoriesRepository().getCategories());
    }

    protected Text createText(Composite composite, String label, String content, ModifyListener modifyListener) {
	createLabel(composite, label);
	Text text = new Text(composite, SWT.BORDER);
	text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
	text.setText(content);

	text.addModifyListener(modifyListener);
	return text;
    }

    protected Combo createCombo(Composite composite, String label, String[] items, ModifyListener modifyListener) {
	createLabel(composite, label);
	Combo combo = new Combo(composite, SWT.READ_ONLY);
	combo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
	combo.setItems(items);
	combo.select(0);

	combo.addModifyListener(modifyListener);
	return combo;
    }

    protected ComboViewer createComboViewer(Composite composite, String label, List<Object> input,
	    IContentProvider contentProvider, ILabelProvider labelProvider, ISelectionChangedListener listener) {
	createLabel(composite, label);
	ComboViewer viewer = new ComboViewer(composite, SWT.READ_ONLY);
	viewer.setContentProvider(contentProvider);
	viewer.setLabelProvider(labelProvider);
	viewer.setInput(input);
	viewer.getCombo().select(0);

	viewer.addSelectionChangedListener(listener);
	return viewer;
    }

    private void createLabel(Composite composite, String label) {
	if (label == null)
	    return;
	Label textLabel = new Label(composite, SWT.NONE);
	textLabel.setText(label);
    }
}
