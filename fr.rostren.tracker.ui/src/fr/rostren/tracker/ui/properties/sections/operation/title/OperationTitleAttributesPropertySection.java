package fr.rostren.tracker.ui.properties.sections.operation.title;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.ui.properties.listeners.OperationTitleAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OperationTitleAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text titleText;

	private final ModifyListener listener=new OperationTitleAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		titleText=createLabeledText(body, null, "Title:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		titleText.setText(getOperationTileValue());
		addListeners();
	}

	/**
	 * Returns the operation title value
	 * @return the operation title value
	 */
	private String getOperationTileValue() {
		Assert.isTrue(currentEObject instanceof OperationTitle);
		String title=((OperationTitle)currentEObject).getTitle();
		if (title == null) {
			return StringUtils.EMPTY;
		}
		return title;
	}

	@Override
	protected void addListeners() {
		titleText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		titleText.removeModifyListener(listener);
	}

	/**
	 * Returns the title {@link Text}
	 * @return the title {@link Text}
	 */
	public Text getTitleText() {
		return titleText;
	}
}
