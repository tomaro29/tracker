package fr.rostren.tracker.ui.properties.sections.operation.title;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.ui.properties.listeners.OperationTitleAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OperationTitleAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text titleText;

	private ModifyListener listener = new OperationTitleAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		this.titleText = createAttribute(body, null, "Title:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void refresh() {
		disposeListeners();
		titleText.setText(getOperationTileValue());
		addListeners();
	}

	private String getOperationTileValue() {
		Assert.isTrue(currentEObject instanceof OperationTitle);
		String title = ((OperationTitle) currentEObject).getTitle();
		if (title == null)
			return StringUtils.EMPTY;
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

	public Text getTitleText() {
		return titleText;
	}
}
