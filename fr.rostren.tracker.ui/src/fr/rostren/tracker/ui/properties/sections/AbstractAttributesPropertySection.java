package fr.rostren.tracker.ui.properties.sections;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractAttributesPropertySection extends AbstractPropertySection {
    protected Composite body;
    protected EObject currentEObject;

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);
	this.body = getWidgetFactory().createFlatFormComposite(parent);
    }

    protected Text createAttribute(Composite composite, Text control, String label) {
	Text attributeText = getWidgetFactory().createText(composite, StringUtils.EMPTY);

	FormData data = new FormData();
	data = new FormData();
	data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
	if (control == null) {
	    data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
	} else {
	    data.top = new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
	}
	data.right = new FormAttachment(100, 0);
	attributeText.setLayoutData(data);

	CLabel attributeLabel = getWidgetFactory().createCLabel(composite, label);
	data = new FormData();
	data.left = new FormAttachment(0, 0);
	data.right = new FormAttachment(attributeText, -ITabbedPropertyConstants.HSPACE);
	data.top = new FormAttachment(attributeText, 0, SWT.CENTER);
	attributeLabel.setLayoutData(data);
	return attributeText;
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);
	Assert.isTrue(selection instanceof IStructuredSelection);
	Object input = ((IStructuredSelection) selection).getFirstElement();
	Assert.isTrue(input instanceof EObject);
	this.currentEObject = (EObject) input;
    }

    public EObject getCurrentEObject() {
	return currentEObject;
    }

    abstract protected void addListeners();

    abstract protected void disposeListeners();
}
