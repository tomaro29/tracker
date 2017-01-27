package fr.rostren.tracker.ui.dialogs;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.wizards.OperationSubAmountWizard;

public class CheckAndEditOperationWizardPage extends WizardPage {
	private static final String AMOUNTS_VALUES_ERROR_MESSAGE="The total amount is:{0} euros. Please edit sub amounts accordingly."; //$NON-NLS-1$
	private static final String PAGE_NAME="Edit ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Validate ''{0}'' Operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to validate and edit operations."; //$NON-NLS-1$

	private static final String ADD_BUTTON_LABEL="Add"; //$NON-NLS-1$
	private static final String EDIT_BUTTON_LABEL="Edit"; //$NON-NLS-1$
	private static final String REMOVE_BUTTON_LABEL="Remove"; //$NON-NLS-1$
	private static final String CATEGORY_COLUMN_TITLE="Category"; //$NON-NLS-1$
	private static final String WISHED_DATE_COLUMN_TITLE="Wished Date"; //$NON-NLS-1$
	private static final String SUB_AMOUNT_COLUMN_TITLE="Value"; //$NON-NLS-1$
	private static final String OPERATION_TOTAL_AMOUNT_LABEL="Operation Total Amount :"; //$NON-NLS-1$
	private static final String OPERATION_DATE_LABEL="Operation Date :"; //$NON-NLS-1$
	private static final String OPERATION_TYPE_LABEL="Operation Type :"; //$NON-NLS-1$

	/** The refinement table group title. */
	private static final String REFINEMENT_GROUP_TITLE="Operation Sub Amounts"; //$NON-NLS-1$

	protected Amount lastSelection;
	protected Operation operation;
	protected Account account;
	protected Table table;
	protected Button addButton;
	protected Button editButton;
	protected Button removeButton;

	protected Date date;

	private final int TEXT_MARGIN=3;
	private final int FONT_WIDTH=10;

	private final String operationTitle;

	private final Listener modifyDateListener=new Listener() {

		@Override
		public void handleEvent(Event event) {
			Object source=event.widget;
			Assert.isTrue(source instanceof DateTime);
			DateTime dateTime=(DateTime)source;
			//set operation date to the new selected date
			date.setDay(dateTime.getDay());
			date.setMonth(Month.get(dateTime.getMonth()));
			date.setYear(dateTime.getYear());

			//set all subAmounts wished dates to the new selected date
			operation.getSubAmounts().stream()//
					.forEach(subAmount -> {
						Date wishedDate=subAmount.getWishedDate();
						wishedDate.setDay(dateTime.getDay());
						wishedDate.setMonth(Month.get(dateTime.getMonth()));
						wishedDate.setYear(dateTime.getYear());
					});
			populateTable();
		}
	};

	private final SelectionListener dateKeyListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source=event.getSource();
			Assert.isTrue(source instanceof DateTime);
			DateTime dateTime=(DateTime)source;
			//set operation date to the new selected date
			date.setDay(dateTime.getDay());
			date.setMonth(Month.get(dateTime.getMonth()));
			date.setYear(dateTime.getYear());

			//set all subAmounts wished dates to the new selected date
			operation.getSubAmounts().stream()//
					.forEach(subAmount -> {
						Date wishedDate=subAmount.getWishedDate();
						wishedDate.setDay(dateTime.getDay());
						wishedDate.setMonth(Month.get(dateTime.getMonth()));
						wishedDate.setYear(dateTime.getYear());
					});
			populateTable();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			// Do Nothing
		}
	};

	/**
	 * Constructor
	 * @param operation the operation
	 * @param account the {@link Account} instance to use
	 */
	public CheckAndEditOperationWizardPage(Operation operation, Account account) {
		super(MessageFormat.format(CheckAndEditOperationWizardPage.PAGE_NAME, operation.getOperationTitle().getTitle()));

		this.operation=operation;
		date=operation.getDate();
		this.account=account;
		operationTitle=operation.getOperationTitle().getTitle();

		setTitle(MessageFormat.format(CheckAndEditOperationWizardPage.PAGE_TITLE, operationTitle));
		setDescription(CheckAndEditOperationWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout();
		container.setLayout(layout);

		// Create operation information area
		createOperationInfContainer(container);

		// The operation sub amounts and category
		createSubAmountsTable(container);
		populateTable();

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Creates the container
	 * @param parent the composite parent of the container to create
	 */
	private void createOperationInfContainer(Composite parent) {
		Composite composite=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout();
		composite.setLayout(layout);
		layout.numColumns=2;

		createOperationLabels(composite, operation);
	}

	/**
	 * Creates the sub-amounts table
	 * @param parent the composite parent of the table to create
	 * @return the sub-amounts table
	 */
	private Table createSubAmountsTable(Composite parent) {
		final Composite subAmoutsGroup=createGroup(parent, CheckAndEditOperationWizardPage.REFINEMENT_GROUP_TITLE);
		final Composite container=createContainer(subAmoutsGroup, 2, 1, 0);
		final Composite group=createGroup(container, StringUtils.EMPTY);

		table=createTable(group);
		table.addSelectionListener(new AmountsTableSelectionListener());

		final Composite buttonsContainer=createContainer(container, 1, 1, 45);

		// Width restriction for buttons container
		parent.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent event) {
				Rectangle bounds=container.getBounds();

				GridData gridData=(GridData)group.getLayoutData();
				gridData.widthHint=bounds.width - 90;

				gridData=(GridData)buttonsContainer.getLayoutData();
				gridData.widthHint=90;
			}
		});
		addButtons(buttonsContainer);
		container.pack();
		return table;
	}

	/**
	 * Creates the table
	 * @param parent the composite parent of the table to create
	 * @return the created table
	 */
	private Table createTable(final Composite parent) {
		Table table=new Table(parent, SWT.FILL | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setVisible(true);

		table.setFont(new Font(table.getDisplay(), "Arial", FONT_WIDTH, SWT.BOLD)); //$NON-NLS-1$

		TableColumn subAmountColumn=new TableColumn(table, SWT.NONE);
		subAmountColumn.setText(CheckAndEditOperationWizardPage.SUB_AMOUNT_COLUMN_TITLE);
		subAmountColumn.setWidth(100);

		TableColumn categoryColumn=new TableColumn(table, SWT.NONE);
		categoryColumn.setText(CheckAndEditOperationWizardPage.CATEGORY_COLUMN_TITLE);
		categoryColumn.setWidth(100);

		TableColumn wishedDateColumn=new TableColumn(table, SWT.NONE);
		wishedDateColumn.setText(CheckAndEditOperationWizardPage.WISHED_DATE_COLUMN_TITLE);
		wishedDateColumn.setWidth(100);

		return table;
	}

	/**
	 * Creates a container
	 * @param parent the composite parent of the container to create
	 * @param columnsNumber the columns number
	 * @param horizontalSpan the horizontal span
	 * @param marginHeight the margin left
	 * @return the created container
	 */
	private Composite createContainer(Composite parent, int columnsNumber, int horizontalSpan, int marginHeight) {
		final Composite container=new Composite(parent, SWT.NONE | SWT.NULL);
		GridLayout gLayout=new GridLayout();
		gLayout.numColumns=3;
		gLayout.makeColumnsEqualWidth=false;
		gLayout.marginWidth=0;
		gLayout.marginHeight=0;
		container.setLayout(gLayout);

		GridData gridData=new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.grabExcessHorizontalSpace=true;
		gridData.horizontalSpan=horizontalSpan;
		container.setLayoutData(gridData);

		GridLayout layout=new GridLayout();
		layout.numColumns=columnsNumber;
		layout.marginWidth=0;
		layout.marginHeight=marginHeight;
		layout.horizontalSpacing=8;
		container.setLayout(layout);

		return container;
	}

	/**
	 * Created a group
	 * @param parent the composite parent of the group to create
	 * @param label the label
	 * @return the group
	 */
	private Composite createGroup(Composite parent, String label) {
		Group group=new Group(parent, SWT.NONE);
		group.setText(label);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		return group;
	}

	/**
	 * Creates a button
	 * @param parent the composite parent of the button to create
	 * @param label the label
	 * @return the created button
	 */
	private Button createButton(Composite parent, String label) {
		final Button button=new Button(parent, SWT.PUSH | SWT.CENTER);
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button.setText(label);

		// keep the same buttons width after resizing
		button.addListener(SWT.Resize, event -> button.setSize(90, button.getSize().y));
		button.addListener(SWT.Selection, new TableButtonListener(button));
		return button;
	}

	/**
	 * Adds buttons to the given container
	 * @param composite the given container
	 */
	private void addButtons(Composite composite) {
		// Add button
		addButton=createButton(composite, CheckAndEditOperationWizardPage.ADD_BUTTON_LABEL);

		// Edit button
		editButton=createButton(composite, CheckAndEditOperationWizardPage.EDIT_BUTTON_LABEL);
		editButton.setEnabled(false);

		// Remove button
		removeButton=createButton(composite, CheckAndEditOperationWizardPage.REMOVE_BUTTON_LABEL);
		removeButton.setEnabled(false);

		composite.pack();
	}

	/**
	 * Creates operation labels
	 * @param subContainer the sub container
	 * @param operation the operation
	 */
	private void createOperationLabels(Composite subContainer, Operation operation) {
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_TYPE_LABEL, operation.eClass().getName());
		createDateTime(subContainer, CheckAndEditOperationWizardPage.OPERATION_DATE_LABEL, date, dateKeyListener, modifyDateListener);
		//		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_DATE_LABEL, TrackerUtils.getOperationDate(Optional.of(operation)));
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_TOTAL_AMOUNT_LABEL, TrackerUtils.getOperationTotalAmount(Optional.of(operation)));
	}

	/**
	 * Creates label
	 * @param subContainer the sub container
	 * @param labelContent the label content
	 * @param textLabelContent the label text
	 */
	private void createLabel(Composite subContainer, String labelContent, String textLabelContent) {
		Label label=new Label(subContainer, SWT.NONE);
		label.setText(labelContent);

		Label textLabel=new Label(subContainer, SWT.NONE);
		textLabel.setText(textLabelContent);
	}

	/**
	 * Creates a date time zone
	 * @param composite the composite parent of the label to create
	 * @param label the label
	 * @param content the content
	 * @param selectionListener the selection listener
	 * @param modifyListener the modify listener
	 * @return the created {@link Text}
	 */
	private DateTime createDateTime(Composite composite, String label, Date content, SelectionListener selectionListener, Listener modifyListener) {
		createLabel(composite, label);
		DateTime dateTime=new DateTime(composite, SWT.DATE | SWT.MEDIUM | SWT.DROP_DOWN);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (content != null) {
			dateTime.setDate(content.getYear(), content.getMonth().getValue(), content.getDay());
		}
		dateTime.addSelectionListener(selectionListener);
		dateTime.addListener(SWT.Modify, modifyListener);
		return dateTime;
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

	/**
	 * Populates the table with all operation sub amounts.
	 */
	protected void populateTable() {
		table.removeAll();
		for (Amount amount: operation.getSubAmounts()) {
			TableItem item=new TableItem(table, SWT.NONE);
			Font font=new Font(table.getDisplay(), "Arial", 9, SWT.CENTER); //$NON-NLS-1$
			item.setFont(font);
			item.setData(amount);
			item.setText(new String[] {amount.getValue().toString(), amount.getCategory().getTitle(), amount.getWishedDate().toString()});
		}
	}

	@Override
	public boolean isPageComplete() {
		return TrackerUtils.isValidOperationAmounts(operation);
	}

	private class TableButtonListener implements Listener {
		private final Button button;

		/**
		 * Constructor.
		 * @param button the pushed button
		 */
		public TableButtonListener(Button button) {
			this.button=button;
		}

		@Override
		public void handleEvent(Event event) {
			if (SWT.Selection == event.type) {
				if (button.equals(addButton)) {
					performAdd();
				}
				else if (button.equals(editButton)) {
					performEdit(lastSelection);
				}
				else if (button.equals(removeButton)) {
					performRemove(lastSelection);
				}
			}
		}

		/**
		 * Performs the add action.
		 */
		private void performAdd() {
			String pageTitle=operation.getOperationTitle().getTitle();
			Tracker tracker=TrackerUtils.getTracker(account);
			OperationSubAmountWizard wizard=new OperationSubAmountWizard(pageTitle, tracker, operation, null);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Amount newAmount=TrackerFactory.eINSTANCE.createAmount();

				Category category=wizard.getAmountCategory();
				if (category != null) {
					newAmount.setCategory(category);
				}

				BigDecimal value=wizard.getAmountValue();
				if (value != null) {
					newAmount.setValue(value);
				}
				adaptAndValidateValues(newAmount);
				populateTable();
			}
		}

		/**
		 * Performs the edit action.
		 * @param amount the amount to edit
		 */
		private void performEdit(Amount amount) {
			String pageTitle=operation.getOperationTitle().getTitle();
			Tracker tracker=TrackerUtils.getTracker(account);
			OperationSubAmountWizard wizard=new OperationSubAmountWizard(pageTitle, tracker, operation, amount);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				amount.setValue(wizard.getAmountValue());
				amount.setCategory(wizard.getAmountCategory());
				setPageComplete(isPageComplete());
				if (!isPageComplete()) {
					setErrorMessage(MessageFormat.format(CheckAndEditOperationWizardPage.AMOUNTS_VALUES_ERROR_MESSAGE, operation.getTotalAmount()));
				}
				else {
					setErrorMessage(null);
				}
				populateTable();
			}
		}

		/**
		 * Performs the remove action.
		 * @param amount the amount to remove
		 */
		private void performRemove(Amount amount) {
			if (operation.getSubAmounts().size() > 2) {
				operation.getSubAmounts().remove(amount);
				setErrorMessage(MessageFormat.format(CheckAndEditOperationWizardPage.AMOUNTS_VALUES_ERROR_MESSAGE, operation.getTotalAmount()));
				populateTable();
			}
			else if (operation.getSubAmounts().size() == 2) {
				operation.getSubAmounts().remove(amount);
				Amount totalAmount=operation.getSubAmounts().get(0);
				totalAmount.setValue(operation.getTotalAmount());
				populateTable();
			}
			else {
				setMessage("The operation must have at least one sub amount illustrating it's total amount :" + operation.getTotalAmount() + " euros."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		/**
		 * Adapts the existing amount value.
		 * @param newAmount the new added amount
		 */
		private void adaptAndValidateValues(Amount newAmount) {
			if (operation.getSubAmounts().size() == 1) {
				Amount amount=operation.getSubAmounts().get(0);
				BigDecimal lastValue=amount.getValue();
				BigDecimal newValue=lastValue.subtract(newAmount.getValue());
				amount.setValue(newValue);
			}

			operation.getSubAmounts().add(newAmount);
			EList<Category> categories=operation.getOperationTitle().getCategories();
			if (!categories.contains(newAmount.getCategory())) {
				categories.add(newAmount.getCategory());
			}
			setPageComplete(isPageComplete());
			if (!isPageComplete()) {
				setErrorMessage(MessageFormat.format(CheckAndEditOperationWizardPage.AMOUNTS_VALUES_ERROR_MESSAGE, operation.getTotalAmount()));
			}
		}
	}

	private class AmountsTableSelectionListener implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent event) {
			// the selected row
			final TableItem item=(TableItem)event.item;
			if (item == null) {
				return;
			}
			lastSelection=(Amount)item.getData();
			if (lastSelection == null) {
				editButton.setEnabled(false);
				removeButton.setEnabled(false);
				return;
			}
			editButton.setEnabled(true);
			if (operation.getSubAmounts().size() > 1) {
				removeButton.setEnabled(true);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// Do Nothing
		}
	}
}
