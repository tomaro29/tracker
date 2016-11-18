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
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.dialogs.CheckAndEditOperationWizard;

public class EditOperationsAction extends Action {
	private final Shell shell;
	private final CheckingAccount account;
	private final List<Operation> addedOperations;
	private final Set<Origin> addedOrigins;
	private boolean aborted=false;

	/**
	 * Constructor
	 * @param shell the parent shell
	 * @param account the checking account
	 * @param addedOperations the added operations
	 * @param addedOrigins the added origins
	 */
	public EditOperationsAction(Shell shell, CheckingAccount account, List<Operation> addedOperations, Set<Origin> addedOrigins) {
		this.shell=shell;
		this.account=account;
		this.addedOperations=addedOperations;
		this.addedOrigins=addedOrigins;
	}

	@Override
	public void run() {
		aborted=editOperations();
		if (aborted) {
			removeAll();
			displayInformationMessage();
			return;
		}

		addOperationsToAccount();
	}

	/**
	 * Adds operations to the checking account.
	 */
	private void addOperationsToAccount() {
		for (Operation operation: addedOperations) {
			DomainUtils.executeAddCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, operation);
		}
	}

	/**
	 * Displays an information message to inform that the action was aborted.
	 */
	private void displayInformationMessage() {
		MessageDialog.openInformation(shell, "Abort PDF Import Action", //$NON-NLS-1$
				"The Current PDF Import Action is aborted! All new origins and operations will be cleaned from the model."); //$NON-NLS-1$
	}

	/**
	 * Removes all extracted operations. Used when the action is aborted.
	 */
	private void removeAll() {
		TrackerUtils.getTracker(account).getOriginsRepository().getOrigins().removeAll(addedOrigins);
		account.getOperations().removeAll(addedOperations);
	}

	/**
	 * Edits operations.
	 * @return whether the edit action is aborted or not.
	 */
	private boolean editOperations() {
		CheckAndEditOperationWizard wizard=new CheckAndEditOperationWizard(addedOperations);
		WizardDialog wizardDialog=new WizardDialog(shell, wizard);
		return wizardDialog.open() == Window.CANCEL;
	}

	/**
	 * @return <code>true</code> if aborted, <code>false</code> otherwise.
	 */
	public boolean isAborted() {
		return aborted;
	}
}
