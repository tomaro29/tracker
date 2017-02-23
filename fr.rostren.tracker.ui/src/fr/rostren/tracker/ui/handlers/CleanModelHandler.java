package fr.rostren.tracker.ui.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;

/**
 * The Clean Model action handler.
 */
public class CleanModelHandler extends AbstractHandler implements IHandler {
	/** The parent shell. */
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
		TrackerEditorDev editor=(TrackerEditorDev)HandlerUtil.getActiveEditor(event);

		boolean clean=MessageDialog.openQuestion(getShell(), "Clean Model's Content", //$NON-NLS-1$
				"This action will delete all operations and origins in the current tracker model.\n" //$NON-NLS-1$
																						+ "Are you sure you want to delete all ?"); //$NON-NLS-1$
		if (!clean) {
			return null;
		}

		if (selection instanceof StructuredSelection) {
			@SuppressWarnings("unchecked")
			List<Object> list=new ArrayList<>(selection.toList());
			list.stream()//
					.map(selElement -> AdapterFactoryEditingDomain.unwrap(selElement)).filter(CheckingAccount.class::isInstance)//
					.forEach(selectedElement -> {
						// delete all the account content and the pdf origins
						CheckingAccount account=(CheckingAccount)selectedElement;
						Tracker tracker=TrackerUtils.getTracker(account);
						tracker.getOriginsRepository().getOrigins().clear();
						account.getOperations().clear();
					});

			// for (Iterator<?> objects = selection.iterator();
			// objects.hasNext();) {
			// Object selectedElement =
			// AdapterFactoryEditingDomain.unwrap(objects.next());
			// if (selectedElement instanceof CheckingAccount) {
			//
			// // delete all the account content and the pdf origins
			// CheckingAccount account = (CheckingAccount) selectedElement;
			// Tracker tracker = TrackerUtils.getTracker(account);
			//
			// tracker.getOriginsRepository().getOrigins().clear();
			// account.getOperations().clear();
			// }
			// }
			editor.doSave(new NullProgressMonitor());
		}
		return null;
	}

	/**
	 * Returns the shell
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}

	/**
	 * Sets the shell
	 * @param shell the shell
	 */
	private void setShell(Shell shell) {
		this.shell=shell;
	}
}
