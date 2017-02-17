package fr.rostren.tracker.ui.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.pdf.utils.OperationAdapter;
import fr.rostren.tracker.pdf.utils.OperationData;
import fr.rostren.tracker.ui.properties.wizards.CheckAndEditOperationWizard;

public class EditOperationHandler extends AbstractHandler {
	private Shell shell;

	@Override
	public Object execute(ExecutionEvent event) {
		Object applicationContext=event.getApplicationContext();
		Object currentShell=HandlerUtil.getVariable(applicationContext, ISources.ACTIVE_SHELL_NAME);
		if (!(currentShell instanceof Shell)) {
			return null;
		}

		setShell((Shell)currentShell);
		IStructuredSelection selection=(IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		if (selection instanceof StructuredSelection) {
			Operation selectedOperation=(Operation)selection.getFirstElement();

			List<OperationData> operations=new ArrayList<>();
			operations.add(OperationAdapter.adaptOperation(selectedOperation));
			CheckAndEditOperationWizard wizard=new CheckAndEditOperationWizard(operations, (Account)selectedOperation.eContainer());
			WizardDialog wizardDialog=new WizardDialog(shell, wizard);
			if (wizardDialog.open() == Window.OK) {
				// FIXME apply changes in the model use a command
			}
		}
		return null;
	}

	/**
	 * Sets the shell
	 * @param shell the shell
	 */
	private void setShell(Shell shell) {
		this.shell=shell;
	}
}
