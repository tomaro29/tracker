package fr.rostren.tracker.ui.properties.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.pdf.utils.OperationData;
import fr.rostren.tracker.ui.properties.pages.CheckAndEditOperationWizardPage;

public class CheckAndEditOperationWizard extends Wizard {
	protected BiMap<OperationData, CheckAndEditOperationWizardPage> pages;

	private List<OperationData> operations=new ArrayList<>();
	private boolean canFinish=false;

	/**
	 * Constructor
	 * @param operations the operations
	 * @param account the checking account
	 */
	public CheckAndEditOperationWizard(List<OperationData> operations, Account account) {
		super();
		setNeedsProgressMonitor(true);
		this.operations=operations;

		pages=HashBiMap.create();
		for (OperationData operation: operations) {
			CheckAndEditOperationWizardPage page=new CheckAndEditOperationWizardPage(operation, account);
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
		for (OperationData operation: operations) {
			addPage(pages.get(operation));
		}
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		OperationData operation=pages.inverse().get(page);
		int previousIndex=operations.indexOf(operation) - 1;
		canFinish=isFirstPage(previousIndex) && isLastPage(previousIndex);

		if (previousIndex > 0 && operations.size() > previousIndex) {
			return pages.get(operations.get(previousIndex));
		}
		return super.getPreviousPage(page);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		OperationData operation=pages.inverse().get(page);
		int nextIndex=operations.indexOf(operation) + 1;
		canFinish=isLastPage(nextIndex);

		if (operations.size() > nextIndex) {
			return pages.get(operations.get(nextIndex));
		}
		return super.getNextPage(page);
	}

	/**
	 * Returns <code>true</code> if it is the last page, <code>false</code> otherwise
	 * @param index the page index
	 * @return <code>true</code> if it is the last page, <code>false</code> otherwise
	 */
	private boolean isLastPage(int index) {
		return operations.size() > 0 && operations.size() - 1 == index;
	}

	/**
	 * Returns <code>true</code> if it is the first page, <code>false</code> otherwise
	 * @param index the page index
	 * @return <code>true</code> if it is the first page, <code>false</code> otherwise
	 */
	private boolean isFirstPage(int index) {
		return operations.size() > 0 && index == 0;
	}
}
