package fr.rostren.tracker.ui.dialogs;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Operation;

public class CheckAndEditOperationWizardPage extends WizardPage {
	private Operation operation;
	private String operationTitle;
	private String currentOperationTitle;

	public CheckAndEditOperationWizardPage(Operation operation) {
		super("Edit " + operation.getOperationTitle().getTitle() + " Page"); //$NON-NLS-1$ //$NON-NLS-2$
		this.operation = operation;
		this.operationTitle = operation.getOperationTitle().getTitle();
		this.currentOperationTitle = operationTitle;
		setTitle("Validate '" + operationTitle + "' Operation"); //$NON-NLS-1$ //$NON-NLS-2$
		setDescription("Wizard to validate and edit operations."); //$NON-NLS-1$
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		Composite subContainer = new Composite(container, SWT.NONE);
		GridLayout subLayout = new GridLayout();
		subContainer.setLayout(subLayout);
		subLayout.numColumns = 2;

		// The operation type
		Label operationTypeLabel = new Label(subContainer, SWT.NONE);
		operationTypeLabel.setText("Operation Type :"); //$NON-NLS-1$
		Label valueLabel = new Label(subContainer, SWT.NONE);
		valueLabel.setText(operation.eClass().getName());

		// The operation date
		Label operationDateLabel = new Label(subContainer, SWT.NONE);
		operationDateLabel.setText("Operation Date :"); //$NON-NLS-1$
		Label dateLabel = new Label(subContainer, SWT.NONE);
		dateLabel.setText(operation.getDate().toString());

		// The operation total amount
		Label operationTotalAmountLabel = new Label(subContainer, SWT.NONE);
		operationTotalAmountLabel.setText("Operation Total Amount :"); //$NON-NLS-1$
		Label amountLabel = new Label(subContainer, SWT.NONE);
		amountLabel.setText(operation.getTotalAmount().toString());

		// The operation sub amounts
		Label operationSubAmountsLabel = new Label(container, SWT.NONE);
		operationSubAmountsLabel.setText("Operation Sub Amounts : "); //$NON-NLS-1$

		Table table = new Table(container, SWT.BORDER | SWT.CENTER | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setWidth(100);
		column1.setText("SubAmount"); //$NON-NLS-1$
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setWidth(200);
		column2.setText("Category"); //$NON-NLS-1$
		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setWidth(50);

		for (Amount amount : operation.getSubAmounts()) {
			TableItem ligne = new TableItem(table, SWT.NONE);
			ligne.setText(new String[] { amount.getSubAmount().toString(),
					amount.getCategory().getTitle() });
			Button button = new Button(table, SWT.PUSH);
			{
				FormData data = new FormData();
				data.right = new FormAttachment(100);
				button.setLayoutData(data);
			}
			button.setText("Edit"); //$NON-NLS-1$
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					super.widgetSelected(e);
				}
			});

			TableEditor editor = new TableEditor(table);
			editor.grabHorizontal = true;
			editor.minimumWidth = button.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(button, ligne, 2);
		}

		// required to avoid an error in the system
		setControl(container);
		setPageComplete(true);
	}
}
