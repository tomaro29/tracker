package fr.rostren.tracker.ui.actions;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.dialogs.CheckAndEditOperationWizard;

public class EditOperationsAction extends Action {
	private final Shell shell;
	private final CheckingAccount account;
	private List<Operation> addedOperations;
	private Set<Origin> addedOrigins;
	private boolean aborted = false;

	public EditOperationsAction(Shell shell, CheckingAccount account, List<Operation> addedOperations,
			Set<Origin> addedOrigins) {
		this.shell = shell;
		this.account = account;
		this.addedOperations = addedOperations;
		this.addedOrigins = addedOrigins;
	}

	@Override
	public void run() {
		editOperations();
		if (aborted) {
			TrackerUtils.getTracker(account).getOriginsRepository().getOrigins().removeAll(addedOrigins);
			account.getOperations().removeAll(addedOperations);
			MessageDialog.openInformation(shell, "Abort PDF Import Action", //$NON-NLS-1$
					"The Current PDF Import Action is aborted! All new origins and operations will be cleaned from the model."); //$NON-NLS-1$
			return;
		}
	}

	private void editOperations() {
		CheckAndEditOperationWizard wizard = new CheckAndEditOperationWizard(addedOperations, account);
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		this.aborted = wizardDialog.open() == Window.CANCEL;
	}

	/**
	 * @return the aborted
	 */
	public boolean isAborted() {
		return aborted;
	}
}
