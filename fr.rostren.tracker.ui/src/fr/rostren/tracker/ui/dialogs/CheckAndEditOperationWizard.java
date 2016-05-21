package fr.rostren.tracker.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;

public class CheckAndEditOperationWizard extends Wizard {
	private List<Operation> operations = new ArrayList<Operation>();

	protected BiMap<Operation, CheckAndEditOperationWizardPage> pages;
	private boolean canFinish = false;

	public CheckAndEditOperationWizard(List<Operation> operations, CheckingAccount account) {
		super();
		setNeedsProgressMonitor(true);
		this.operations = operations;

		account.getOperations().addAll(operations);
		this.pages = HashBiMap.create();
		for (Operation operation : operations) {
			CheckAndEditOperationWizardPage page = new CheckAndEditOperationWizardPage(operation);
			pages.put(operation, page);
		}
	}

	@Override
	public String getWindowTitle() {
		return "Import Operations From PDF File(s)."; //$NON-NLS-1$
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean canFinish() {
		return canFinish;
	}

	@Override
	public void addPages() {
		for (Operation operation : operations) {
			addPage(pages.get(operation));
		}
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		Operation operation = pages.inverse().get(page);
		int previousIndex = operations.indexOf(operation) - 1;
		if (isFirstPage(previousIndex) && isLastPage(previousIndex)) {
			canFinish = true;
		} else {
			canFinish = false;
		}

		if (previousIndex > 0 && operations.size() > previousIndex) {
			return pages.get(operations.get(previousIndex));
		}
		return super.getPreviousPage(page);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		Operation operation = pages.inverse().get(page);
		int nextIndex = operations.indexOf(operation) + 1;
		if (isLastPage(nextIndex)) {
			canFinish = true;
		} else {
			canFinish = false;
		}

		if (operations.size() > nextIndex) {
			return pages.get(operations.get(nextIndex));
		}
		return super.getNextPage(page);
	}

	private boolean isLastPage(int index) {
		if (operations.size() > 0 && operations.size() - 1 == index) {
			return true;
		}
		return false;
	}

	private boolean isFirstPage(int index) {
		if (operations.size() > 0 && index == 0) {
			return true;
		}
		return false;
	}
}
