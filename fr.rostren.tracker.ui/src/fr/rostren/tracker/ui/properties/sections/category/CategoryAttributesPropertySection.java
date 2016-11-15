package fr.rostren.tracker.ui.properties.sections.category;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.ui.properties.listeners.CategoryAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class CategoryAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text descriptionText;

	private final ModifyListener listener=new CategoryAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		descriptionText=createLabeledText(body, null, "Description:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		descriptionText.setText(getDescriptionValue());
		addListeners();
	}

	/**
	 * Returns the description value
	 * @return the description value
	 */
	private String getDescriptionValue() {
		Assert.isTrue(currentEObject instanceof Category);
		String description=((Category)currentEObject).getDescription();
		if (description == null) {
			return StringUtils.EMPTY;
		}
		return description;
	}

	@Override
	protected void addListeners() {
		descriptionText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		descriptionText.removeModifyListener(listener);
	}

	/**
	 * Returns the description {@link Text}
	 * @return the description {@link Text}
	 */
	public Text getDescriptionText() {
		return descriptionText;
	}
}
