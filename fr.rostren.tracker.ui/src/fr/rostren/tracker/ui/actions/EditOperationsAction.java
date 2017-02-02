package fr.rostren.tracker.ui.actions;

import java.util.Collections;
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
import fr.rostren.tracker.ui.AbortEditActionException;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.dialogs.CheckAndEditOperationWizard;

public class EditOperationsAction extends Action {
	private static final String ACTION_ABORTED_MESSAGE="The Current PDF Import Action is aborted! All new origins and operations will be cleaned from the model."; //$NON-NLS-1$
	private final Shell shell;
	private final CheckingAccount account;
	private final List<Operation> addedOperations;
	private final Set<Origin> addedOrigins;

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
		try {
			editOperations();
			addOperationsToAccount();
		}
		catch (AbortEditActionException e) {
			removeAll();
			MessageDialog.openInformation(shell, "Abort PDF Import Action", e.getMessage());//$NON-NLS-1$
		}
	}

	/**
	 * Adds operations to the checking account.
	 */
	private void addOperationsToAccount() {
		Collections.sort(addedOperations, (op1, op2) -> op1.getDate().compareTo(op2.getDate()));
		addedOperations.stream().forEach(operation -> DomainUtils.executeAddCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, operation));
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
	 * @throws AbortEditActionException if the action is aborted
	 */
	private void editOperations() throws AbortEditActionException {
		CheckAndEditOperationWizard wizard=new CheckAndEditOperationWizard(addedOperations, account);
		WizardDialog wizardDialog=new WizardDialog(shell, wizard);
		if (wizardDialog.open() == Window.CANCEL) {
			throw new AbortEditActionException(EditOperationsAction.ACTION_ABORTED_MESSAGE);
		}
	}
}
