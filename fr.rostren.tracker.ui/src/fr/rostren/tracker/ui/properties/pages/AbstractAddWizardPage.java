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

	/**
	 * Constructor
	 * @param pageName the page name
	 */
	protected AbstractAddWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout();
		layout.numColumns=2;
		layout.makeColumnsEqualWidth=false;
		layout.marginWidth=0;
		layout.marginHeight=0;
		container.setLayout(layout);

		createContainer(container);

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Creates a container
	 * @param parent the composite parent of the container to create
	 */
	abstract protected void createContainer(Composite parent);

	/**
	 * Returns the list of operations related to all checking accounts
	 * @param tracker the given tracker object
	 * @return the list of operations in checking accounts
	 */
	protected List<Object> getOperations(Tracker tracker) {
		List<Object> operations=new ArrayList<>();
		for (Owner owner: tracker.getOwners()) {
			for (Account account: owner.getAccounts()) {
				if (account instanceof CheckingAccount) {
					operations.addAll(((CheckingAccount)account).getOperations());
				}
			}
		}
		return operations;
	}

	/**
	 * Returns the operations titles
	 * @param tracker the {@link Tracker} instance
	 * @return the operations titles list
	 */
	protected List<Object> getOperationsTitles(Tracker tracker) {
		return new ArrayList<>(tracker.getOperationsTitlesRepositories().getOperationsTitles());
	}

	/**
	 * Returns the origins
	 * @param tracker the {@link Tracker} instance
	 * @return the origins
	 */
	protected List<Object> getOrigins(Tracker tracker) {
		return new ArrayList<>(tracker.getOriginsRepository().getOrigins());
	}

	/**
	 * Returns the categories
	 * @param tracker the {@link Tracker} instance
	 * @return the categories
	 */
	protected List<Object> getCategories(Tracker tracker) {
		return new ArrayList<>(tracker.getCategoriesRepository().getCategories());
	}

	/**
	 * Creates text field
	 * @param composite the composite parent of the text to create
	 * @param label the text label
	 * @param content the content
	 * @param modifyListener the linked listener
	 * @return the created text field
	 */
	protected Text createText(Composite composite, String label, String content, ModifyListener modifyListener) {
		createLabel(composite, label);
		Text text=new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		text.setText(content);

		text.addModifyListener(modifyListener);
		return text;
	}

	/**
	 * Creates combo
	 * @param composite the composite parent of the combo to create
	 * @param label the combo label
	 * @param items the combo content
	 * @param modifyListener the linked listener
	 * @return the created combo field
	 */
	protected Combo createCombo(Composite composite, String label, String[] items, ModifyListener modifyListener) {
		createLabel(composite, label);
		Combo combo=new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		combo.setItems(items);
		combo.select(0);

		combo.addModifyListener(modifyListener);
		return combo;
	}

	/**
	 * Creates combo viewer
	 * @param composite the composite parent of the combo to create
	 * @param label the combo label
	 * @param input the combo input
	 * @param contentProvider the content provider
	 * @param labelProvider the label Provider
	 * @param listener the linked listener
	 * @return the created combo viewer
	 */
	protected ComboViewer createComboViewer(Composite composite, String label, List<Object> input, IContentProvider contentProvider, ILabelProvider labelProvider,
			ISelectionChangedListener listener) {
		createLabel(composite, label);
		ComboViewer viewer=new ComboViewer(composite, SWT.READ_ONLY);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(input);
		viewer.getCombo().select(0);

		viewer.addSelectionChangedListener(listener);
		return viewer;
	}

	/**
	 * Creates a label
	 * @param composite the composite parent of the label to create
	 * @param label the label
	 */
	private void createLabel(Composite composite, String label) {
		if (label == null) {
			return;
		}
		Label textLabel=new Label(composite, SWT.NONE);
		textLabel.setText(label);
	}
}
