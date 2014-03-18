/**
 */
package fr.rostren.tracker.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.presentation.dev.TrackerEditorDev;
import fr.rostren.tracker.ui.pdf.utils.ExtractPDFContent;

public class ImportPDFHandler extends AbstractHandler implements IHandler {
	private Shell shell;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		this.shell = HandlerUtil.getActiveShell(event);

		TrackerEditorDev editor = (TrackerEditorDev) HandlerUtil
				.getActiveEditor(event);

		// opens dialog to load a pdf
		LoadResourceDialog dialog = new LoadResourceDialog(shell);
		int result = dialog.open();
		if (result == Window.OK) {
			// read the pdf file and extract data
			String uriText = dialog.getURIText();

			final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
			if (!(editorPart instanceof TrackerEditorDev))
				return null;

			final Tracker tracker = ((TrackerEditorDev) editorPart)
					.getEditedTracker();

			ExtractPDFContent extractor = new ExtractPDFContent(uriText,
					tracker, shell, editor);
			extractor.execute();
			editor.doSave(new NullProgressMonitor());
		}

		return null;
	}
}
