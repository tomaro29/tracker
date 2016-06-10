package fr.rostren.tracker.ui.properties.sections.category;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.ui.properties.listeners.CategoryAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class CategoryAttributesPropertySection extends AbstractAttributesPropertySection {
    protected Text descriptionText;

    private ModifyListener listener = new CategoryAttributesModifyListener(this);

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.descriptionText = createAttribute(body, null, "Description:"); //$NON-NLS-1$
	addListeners();
    }

    @Override
    public void refresh() {
	disposeListeners();
	descriptionText.setText(getDescriptionValue());
	addListeners();
    }

    private String getDescriptionValue() {
	Assert.isTrue(currentEObject instanceof Category);
	String description = ((Category) currentEObject).getDescription();
	if (description == null)
	    return StringUtils.EMPTY;
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

    public Text getDescriptionText() {
	return descriptionText;
    }
}