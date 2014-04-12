/**
 */
package fr.rostren.tracker.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;
import fr.rostren.tracker.ui.pdf.utils.ExtractPDFContent;

public class ImportPDFHandler extends AbstractHandler implements IHandler {
	private Shell shell;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Object applicationContext = event.getApplicationContext();
		Object currentShell = HandlerUtil.getVariable(applicationContext,
				ISources.ACTIVE_SHELL_NAME);
		if (!(currentShell instanceof Shell)) {
			return null;
		} else {
			this.shell = (Shell) currentShell;
		}

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);

		TrackerEditorDev editor = (TrackerEditorDev) HandlerUtil
				.getActiveEditor(event);
		if (selection instanceof StructuredSelection) {
			NullProgressMonitor monitor = new NullProgressMonitor();
			for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
				Object selectedElement = AdapterFactoryEditingDomain
						.unwrap(objects.next());
				if (selectedElement instanceof Account) {

					// opens dialog to load a pdf
					LoadResourceDialog dialog = new LoadResourceDialog(shell);
					int result = dialog.open();
					if (result == Window.OK) {
						// read the pdf file and extract data
						String uriText = dialog.getURIText();

						ExtractPDFContent extractor = new ExtractPDFContent(
								uriText, (Account) selectedElement, shell);
						extractor.execute();
					}
				}
			}
			editor.doSave(monitor);
		}

		return null;
	}
}
