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

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.ui.dialogs.CheckAndEditOperationWizard;

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

			List<Operation> operations=new ArrayList<>();
			operations.add(selectedOperation);
			CheckAndEditOperationWizard wizard=new CheckAndEditOperationWizard(operations, (CheckingAccount)selectedOperation.eContainer());
			WizardDialog wizardDialog=new WizardDialog(shell, wizard);
			if (wizardDialog.open() == Window.OK) {
				// FIXME apply changes in the model use a command
			}
		}
		return null;
	}

	private void setShell(Shell shell) {
		this.shell=shell;
	}
}
