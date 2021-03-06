/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
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
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.OperationType;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTypesContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTypeLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.OperationSubAmountWizard;

public class CheckAndEditOperationWizardPage extends AbstractWizardPage {
	private static final String AMOUNTS_VALUES_ERROR_MESSAGE="The total amount is:{0} euros. Please edit sub amounts accordingly."; //$NON-NLS-1$
	private static final String PAGE_NAME="Edit ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="''{0}'' Operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to edit operations."; //$NON-NLS-1$

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

	private static final int FONT_WIDTH=10;

	protected Amount lastSelection;
	protected OperationData operation;
	protected Account account;
	protected Table table;
	protected Button addButton;
	protected Button editButton;
	protected Button removeButton;
	protected OperationType operationType;

	private final OperationService operationService;

	private final String operationTitle;

	private final ISelectionChangedListener typeListener=event -> {
		ISelection selection=event.getSelection();
		Assert.isTrue(selection instanceof StructuredSelection);
		StructuredSelection ss=(StructuredSelection)selection;
		Object firstElement=ss.getFirstElement();
		if (firstElement != null && firstElement instanceof OperationType) {
			operationType=(OperationType)firstElement;
		}
	};

	private final Listener modifyDateListener=event -> {
		Object source=event.widget;
		Assert.isTrue(source instanceof DateTime);
		DateTime dateTime=(DateTime)source;
		LocalDate date=LocalDate.of(dateTime.getYear(), Month.of(dateTime.getMonth() + 1), dateTime.getDay());
		//set operation date to the new selected date
		operation.setDate(date);
		//set all subAmounts wished dates to the new selected date
		operation.getSubAmounts().stream().forEach(subAmount -> subAmount.setWishedDate(date));
		populateTable();
	};

	private final SelectionListener dateKeyListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source=event.getSource();
			Assert.isTrue(source instanceof DateTime);
			DateTime dateTime=(DateTime)source;
			LocalDate date=LocalDate.of(dateTime.getYear(), Month.of(dateTime.getMonth() + 1), dateTime.getDay());
			//set operation date to the new selected date
			operation.setDate(date);
			//set all subAmounts wished dates to the new selected date
			operation.getSubAmounts().stream().forEach(subAmount -> subAmount.setWishedDate(date));
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
	public CheckAndEditOperationWizardPage(OperationData operation, Account account) {
		super(MessageFormat.format(CheckAndEditOperationWizardPage.PAGE_NAME, operation.getOperationTitle().getTitle()));

		this.operation=operation;
		operationService=TrackerFactory.eINSTANCE.createOperationService();
		operationService.setOperation(operationService.adaptOperation(operation));
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
		Table newTable=new Table(parent, SWT.FILL | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		newTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		newTable.setHeaderVisible(true);
		newTable.setLinesVisible(true);
		newTable.setVisible(true);

		newTable.setFont(new Font(newTable.getDisplay(), "Arial", CheckAndEditOperationWizardPage.FONT_WIDTH, SWT.BOLD)); //$NON-NLS-1$

		TableColumn subAmountColumn=new TableColumn(newTable, SWT.NONE);
		subAmountColumn.setText(CheckAndEditOperationWizardPage.SUB_AMOUNT_COLUMN_TITLE);
		subAmountColumn.setWidth(100);

		TableColumn categoryColumn=new TableColumn(newTable, SWT.NONE);
		categoryColumn.setText(CheckAndEditOperationWizardPage.CATEGORY_COLUMN_TITLE);
		categoryColumn.setWidth(100);

		TableColumn wishedDateColumn=new TableColumn(newTable, SWT.NONE);
		wishedDateColumn.setText(CheckAndEditOperationWizardPage.WISHED_DATE_COLUMN_TITLE);
		wishedDateColumn.setWidth(100);

		return newTable;
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
	private void createOperationLabels(Composite subContainer, OperationData operation) {
		ComboViewer typeCombo=createComboViewer(subContainer, CheckAndEditOperationWizardPage.OPERATION_TYPE_LABEL, new HashSet<>(Arrays.asList(OperationType.values())),
				new OperationsTypesContentProvider(), new OperationTypeLabelProvider(), typeListener, null);
		typeCombo.getCombo().select(Arrays.asList(typeCombo.getCombo().getItems()).indexOf(operation.getType().toString()));
		operationType=operation.getType();
		createDateTime(subContainer, CheckAndEditOperationWizardPage.OPERATION_DATE_LABEL, operation.getDate(), dateKeyListener, modifyDateListener);
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_TOTAL_AMOUNT_LABEL,
				operation.getTotalAmount() == 0 ? StringUtils.EMPTY : String.valueOf(operation.getTotalAmount()));
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
	private DateTime createDateTime(Composite composite, String label, LocalDate content, SelectionListener selectionListener, Listener modifyListener) {
		createLabel(composite, label);
		DateTime dateTime=new DateTime(composite, SWT.DATE | SWT.MEDIUM | SWT.DROP_DOWN);

		TCHAR lpszFormat=new TCHAR(0, "dd/MM/yyyy", true);
		OS.SendMessage(dateTime.handle, OS.DTM_SETFORMAT, 0, lpszFormat);

		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (content != null) {
			dateTime.setDate(content.getYear(), content.getMonthValue() - 1, content.getDayOfMonth());
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
		if (label != null) {
			Label textLabel=new Label(composite, SWT.NONE);
			textLabel.setText(label);
		}
	}

	/**
	 * Populates the table with all operation sub amounts.
	 */
	protected void populateTable() {
		table.removeAll();
		operation.getSubAmounts().stream()//
				.forEach(amount -> {
					TableItem item=new TableItem(table, SWT.NONE);
					Font font=new Font(table.getDisplay(), "Arial", 9, SWT.CENTER); //$NON-NLS-1$
					item.setFont(font);
					item.setData(amount);
					item.setText(new String[] {String.valueOf(amount.getValue()), amount.getCategory() == null ? StringUtils.EMPTY : amount.getCategory().getTitle(),
						TrackerFactory.eINSTANCE.convertToString(TrackerPackage.Literals.AMOUNT__WISHED_DATE.getEAttributeType(), amount.getWishedDate())});
				});
	}

	@Override
	public boolean isPageComplete() {
		return operationService.validateAmounts();
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
			Tracker tracker=TrackerUtils.getTracker(account);
			OperationSubAmountWizard wizard=new OperationSubAmountWizard(tracker, operation, operationType, null, true);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Amount newAmount=TrackerFactory.eINSTANCE.createAmount();

				Category category=wizard.getAmountCategory();
				if (category != null) {
					newAmount.setCategory(category);
				}

				double value=wizard.getAmountValue();
				if (Double.isFinite(value) && BigDecimal.ZERO.doubleValue() != value) {
					newAmount.setValue(value);
				}
				LocalDate date=wizard.getAmountWishedDate();
				if (date != null) {
					newAmount.setWishedDate(date);
				}
				else {
					newAmount.setWishedDate(operation.getDate());
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
			Tracker tracker=TrackerUtils.getTracker(account);
			OperationSubAmountWizard wizard=new OperationSubAmountWizard(tracker, operation, operationType, amount, false);
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
				double lastValue=amount.getValue();
				double newValue=lastValue - newAmount.getValue();
				amount.setValue(newValue);
			}

			operation.getSubAmounts().add(newAmount);
			EList<Category> categories=operation.getOperationTitle().getCategories();
			if (!categories.contains(newAmount.getCategory())) {
				categories.add(newAmount.getCategory());
			}
			boolean isComplete=isPageComplete();
			setPageComplete(isComplete);
			if (!isComplete) {
				setErrorMessage(MessageFormat.format(CheckAndEditOperationWizardPage.AMOUNTS_VALUES_ERROR_MESSAGE, operation.getTotalAmount()));
			}
		}
	}

	private class AmountsTableSelectionListener implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent event) {
			// the selected row
			Optional<TableItem> itemOpt=Optional.of((TableItem)event.item);
			itemOpt.ifPresent(item -> {
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
			});
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			// Do Nothing
		}
	}
}
