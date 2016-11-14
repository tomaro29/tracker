package fr.rostren.tracker.ui.properties.sections;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractAttributesPropertySection extends AbstractTrackerPropertySection {

	protected Text createLabeledText(Composite composite, Control control, String label) {
		TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
		Text attributeText = widgetFactory.createText(composite, StringUtils.EMPTY);

		FormData data = new FormData();
		data = new FormData();
		data.left = new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH);
		if (control == null) {
			data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		} else {
			data.top = new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right = new FormAttachment(100, 0);
		attributeText.setLayoutData(data);

		CLabel attributeLabel = widgetFactory.createCLabel(composite, label);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(attributeText, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(attributeText, 0, SWT.CENTER);
		attributeLabel.setLayoutData(data);
		return attributeText;
	}

	protected CCombo createLabeledCombo(Composite composite, Control control, String label) {
		TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
		CCombo refCombo = widgetFactory.createCCombo(composite);

		FormData data = new FormData();
		data = new FormData();
		data.left = new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH);
		if (control == null) {
			data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		} else {
			data.top = new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right = new FormAttachment(100, 0);
		refCombo.setLayoutData(data);

		CLabel comboLabel = widgetFactory.createCLabel(composite, label);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(refCombo, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(refCombo, 0, SWT.CENTER);
		comboLabel.setLayoutData(data);
		return refCombo;
	}
}
