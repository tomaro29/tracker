/**
 */
package fr.rostren.tracker.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
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
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;
import fr.rostren.tracker.ui.actions.ImportOperationsAction;

public class ImportPDFHandler extends AbstractHandler {
    private Shell shell;
    private String pdfURIText;
    private CheckingAccount account;

    @Override
    public Object execute(ExecutionEvent event) {
	Object applicationContext = event.getApplicationContext();
	Object currentShell = HandlerUtil.getVariable(applicationContext, ISources.ACTIVE_SHELL_NAME);
	if (!(currentShell instanceof Shell))
	    return null;
	this.shell = (Shell) currentShell;

	IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
	if (!(selection instanceof StructuredSelection))
	    return null;

	for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
	    Object selectedElement = AdapterFactoryEditingDomain.unwrap(objects.next());
	    if (!(selectedElement instanceof CheckingAccount))
		continue;

	    this.account = (CheckingAccount) selectedElement;
	    // opens dialog to load a pdf
	    // TODO implement the LoadResourceDialog to set the PDF URI
	    // Text field content.
	    LoadResourceDialog dialog = new LoadResourceDialog(shell);
	    if (dialog.open() != Window.OK)
		continue;

	    this.pdfURIText = dialog.getURIText();
	    if (pdfURIText.isEmpty()) {
		MessageDialog.openError(shell, "Empty PDF URI.", //$NON-NLS-1$
			"The pdf uri cannot be empty."); //$NON-NLS-1$
		return null;
	    }
	    runImportOperationsToModel();
	}
	saveAndReset(event);
	return null;
    }

    private void saveAndReset(ExecutionEvent event) {
	// FIXME use EMF Commands instead of saving directly the model!
	TrackerEditorDev editor = (TrackerEditorDev) HandlerUtil.getActiveEditor(event);
	editor.doSave(new NullProgressMonitor());
	account = null;
	pdfURIText = null;
    }

    private void runImportOperationsToModel() {
	ImportOperationsAction action = new ImportOperationsAction(shell, pdfURIText, account);
	action.run();
    }
}
