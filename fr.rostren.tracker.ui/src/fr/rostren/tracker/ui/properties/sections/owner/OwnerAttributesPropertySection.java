package fr.rostren.tracker.ui.properties.sections.owner;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.ui.properties.listeners.OwnerAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OwnerAttributesPropertySection extends AbstractAttributesPropertySection {
    protected Text firstNameText;
    protected Text lastNameText;

    private ModifyListener listener = new OwnerAttributesModifyListener(this);

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.firstNameText = createLabeledText(body, null, "First Name:"); //$NON-NLS-1$
	this.lastNameText = createLabeledText(body, firstNameText, "Last Name:"); //$NON-NLS-1$
	addListeners();
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
	super.setInput(part, selection);

	disposeListeners();
	firstNameText.setText(getOwnerFirstName());
	lastNameText.setText(getOwnerLastName());
	addListeners();
    }

    private String getOwnerFirstName() {
	Assert.isTrue(currentEObject instanceof Owner);
	String firstName = ((Owner) currentEObject).getFirstName();
	if (firstName == null)
	    return StringUtils.EMPTY;
	return firstName;
    }

    private String getOwnerLastName() {
	Assert.isTrue(currentEObject instanceof Owner);
	String lastName = ((Owner) currentEObject).getLastName();
	if (lastName == null)
	    return StringUtils.EMPTY;
	return lastName;
    }

    @Override
    protected void addListeners() {
	firstNameText.addModifyListener(listener);
	lastNameText.addModifyListener(listener);
    }

    @Override
    protected void disposeListeners() {
	firstNameText.removeModifyListener(listener);
	lastNameText.removeModifyListener(listener);
    }

    public Text getFirstNameText() {
	return firstNameText;
    }

    public Text getLastNameText() {
	return lastNameText;
    }
}
