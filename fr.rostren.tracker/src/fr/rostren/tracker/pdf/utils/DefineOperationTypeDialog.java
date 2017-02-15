package fr.rostren.tracker.pdf.utils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.pdf.utils.LineContent.OperationType;

public class DefineOperationTypeDialog extends Dialog {
	private OperationType type;

	/**
	 * Constructor
	 * @param shell the parent shell
	 */
	public DefineOperationTypeDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		return super.createDialogArea(parent);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		// TODO Auto-generated method stub
		super.cancelPressed();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	/**
	 * @return the operation type
	 */
	public OperationType getOperationType() {
		return type;
	}

	/**
	 * @param type
	 *            the operation type to set
	 */
	public void setOperationType(OperationType type) {
		this.type=type;
	}
}
