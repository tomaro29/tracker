/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.ui.actions.ImportOperationsAction;

public class ImportPDFHandler extends AbstractHandler {
	private Shell shell;

	@Override
	public Object execute(ExecutionEvent event) {
		Object applicationContext=event.getApplicationContext();
		Object currentShell=HandlerUtil.getVariable(applicationContext, ISources.ACTIVE_SHELL_NAME);
		if (!(currentShell instanceof Shell)) {
			return null;
		}
		shell=(Shell)currentShell;

		IStructuredSelection selection=(IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		if (!(selection instanceof StructuredSelection)) {
			return null;
		}

		String pdfURIText;
		CheckingAccount account;
		for (Iterator<?> objects=selection.iterator(); objects.hasNext();) {
			Object selectedElement=AdapterFactoryEditingDomain.unwrap(objects.next());
			if (!(selectedElement instanceof CheckingAccount)) {
				continue;
			}

			account=(CheckingAccount)selectedElement;
			// opens dialog to load a pdf
			LoadResourceDialog dialog=new LoadResourceDialog(shell);
			if (dialog.open() != Window.OK) {
				continue;
			}

			pdfURIText=dialog.getURIText();
			if (pdfURIText.isEmpty()) {
				MessageDialog.openError(shell, "Empty PDF URI.", //$NON-NLS-1$
						"The pdf uri cannot be empty."); //$NON-NLS-1$
				return null;
			}
			runImportOperationsAction(pdfURIText, account);
		}
		return null;

	}

	/**
	 * Runs the import operations action
	 * @param pdfURIText the pdf file uri
	 * @param account the {@link CheckingAccount} instance
	 */
	private void runImportOperationsAction(String pdfURIText, CheckingAccount account) {
		ImportOperationsAction action=new ImportOperationsAction(shell, pdfURIText, account);
		action.run();
	}
}