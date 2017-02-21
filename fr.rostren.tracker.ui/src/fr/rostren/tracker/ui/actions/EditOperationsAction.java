package fr.rostren.tracker.ui.actions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.OperationAdapter;
import fr.rostren.tracker.pdf.utils.OperationData;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.AbortEditActionException;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.CheckAndEditOperationWizard;

public class EditOperationsAction extends Action {
	private static final String ACTION_ABORTED_MESSAGE="The Current PDF Import Action is aborted! No new origins or operations are added to the model!"; //$NON-NLS-1$
	private final Shell shell;
	private final CheckingAccount account;
	private final List<OperationData> addedOperations;

	/**
	 * Constructor
	 * @param shell the parent shell
	 * @param account the checking account
	 * @param addedOperations the added operations
	 */
	public EditOperationsAction(Shell shell, CheckingAccount account, List<OperationData> addedOperations) {
		this.shell=shell;
		this.account=account;
		this.addedOperations=addedOperations;
	}

	@Override
	public void run() {
		try {
			editOperations();
			addOperationsToAccount();
		}
		catch (AbortEditActionException e) {
			MessageDialog.openInformation(shell, "Abort PDF Import Action", e.getMessage());//$NON-NLS-1$
		}
	}

	/**
	 * Adds operations to the checking account.
	 */
	private void addOperationsToAccount() {
		Collections.sort(addedOperations, (op1, op2) -> op1.getDate().compareTo(op2.getDate()));
		addedOperations.stream().forEach(
				operationData -> DomainUtils.executeAddCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, OperationAdapter.adaptOperation(operationData)));
		addedOperations.stream().map(operation -> operation.getOrigin())//
				.collect(Collectors.toSet()).stream()//
				.forEach(origin -> DomainUtils.executeAddCommand(TrackerUtils.getTracker(account).getOriginsRepository(), TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS,
						origin));
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
