package fr.rostren.tracker.ui.dialogs;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.pdf.utils.TrackerUtils;

public class CheckAndEditOperationWizardPage extends WizardPage {
	private static final String PAGE_NAME="Edit ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Validate ''{0}'' Operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to validate and edit operations."; //$NON-NLS-1$

	private static final String ADD_BUTTON_LABEL="Add"; //$NON-NLS-1$
	private static final String EDIT_BUTTON_LABEL="Edit"; //$NON-NLS-1$
	private static final String REMOVE_BUTTON_LABEL="Remove"; //$NON-NLS-1$
	private static final String CATEGORY_COLUMN_TITLE="Category"; //$NON-NLS-1$
	private static final String SUB_AMOUNT_COLUMN_TITLE="Value"; //$NON-NLS-1$
	private static final String OPERATION_TOTAL_AMOUNT_LABEL="Operation Total Amount :"; //$NON-NLS-1$
	private static final String OPERATION_DATE_LABEL="Operation Date :"; //$NON-NLS-1$
	private static final String OPERATION_TYPE_LABEL="Operation Type :"; //$NON-NLS-1$

	/** The refinement table group title. */
	private static final String REFINEMENT_GROUP_TITLE="Operation Sub Amounts"; //$NON-NLS-1$

	protected Amount lastSelection;

	private final Operation operation;
	private final String operationTitle;

	// TableEditor editor;
	private Table table;

	/**
	 * Constructor
	 * @param operation the operation
	 */
	public CheckAndEditOperationWizardPage(Operation operation) {
		super(MessageFormat.format(CheckAndEditOperationWizardPage.PAGE_NAME, operation.getOperationTitle().getTitle()));

		this.operation=operation;
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
		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				// the selected row
				final TableItem item=(TableItem)event.item;
				if (item == null) {
					return;
				}
				lastSelection=(Amount)item.getData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do Nothing
			}
		});

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

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setVisible(true);

		table.setFont(new Font(table.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		TableColumn subAmountColumn=new TableColumn(table, SWT.NONE);
		subAmountColumn.setWidth(100);
		subAmountColumn.setText(CheckAndEditOperationWizardPage.SUB_AMOUNT_COLUMN_TITLE);

		TableColumn categoryColumn=new TableColumn(table, SWT.NONE);
		categoryColumn.setWidth(200);
		categoryColumn.setText(CheckAndEditOperationWizardPage.CATEGORY_COLUMN_TITLE);

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
		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String label=button.getText();
				if (SWT.Selection == event.type) {
					if (CheckAndEditOperationWizardPage.ADD_BUTTON_LABEL.equals(label)) {
						performAdd();
					}
					else if (CheckAndEditOperationWizardPage.EDIT_BUTTON_LABEL.equals(label)) {
						performEdit(lastSelection);
					}
					else if (CheckAndEditOperationWizardPage.REMOVE_BUTTON_LABEL.equals(label)) {
						performRemove(lastSelection);
					}
				}
			}

			private void performAdd() {
				// disposeTextEditor();

				// TODO open a wizard to fill the subAmount informations
				// TODO set the table input with the updated list of subAmounts
				table.redraw();

				Amount newSubAmount=TrackerFactory.eINSTANCE.createAmount();
				operation.getSubAmounts().add(newSubAmount);
			}

			private void performEdit(Amount amount) {
				// FIXME implement this
			}

			private void performRemove(Amount amount) {
				// FIXME implement this
			}
		});

		return button;
	}

	/**
	 * Adds buttons to the given container
	 * @param composite the given container
	 */
	private void addButtons(Composite composite) {
		// Add button
		createButton(composite, CheckAndEditOperationWizardPage.ADD_BUTTON_LABEL);
		// Edit button
		createButton(composite, CheckAndEditOperationWizardPage.EDIT_BUTTON_LABEL);
		// Remove button
		createButton(composite, CheckAndEditOperationWizardPage.REMOVE_BUTTON_LABEL);
		composite.pack();
	}

	/**
	 * Creates operation labels
	 * @param subContainer the sub container
	 * @param operation the operation
	 */
	private void createOperationLabels(Composite subContainer, Operation operation) {
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_TYPE_LABEL, operation.eClass().getName());
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_DATE_LABEL, TrackerUtils.getOperationDate(operation));
		createLabel(subContainer, CheckAndEditOperationWizardPage.OPERATION_TOTAL_AMOUNT_LABEL, TrackerUtils.getOperationTotalAmount(operation));
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
	 * Populates the table with all operation sub amounts.
	 */
	private void populateTable() {
		for (Amount amount: operation.getSubAmounts()) {
			TableItem item=new TableItem(table, SWT.NONE);
			Font font=new Font(table.getDisplay(), "Arial", 9, SWT.CENTER); //$NON-NLS-1$
			item.setFont(font);
			item.setText(new String[] {amount.getValue().toString(), amount.getCategory().getTitle()});
		}
	}
}
