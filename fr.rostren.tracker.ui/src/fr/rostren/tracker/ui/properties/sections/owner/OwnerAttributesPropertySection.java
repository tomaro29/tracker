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

	private final ModifyListener listener = new OwnerAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		firstNameText = createLabeledText(body, null, "First Name:"); //$NON-NLS-1$
		lastNameText = createLabeledText(body, firstNameText, "Last Name:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		String firstName = getOwnerFirstName();
		if (!StringUtils.isEmpty(firstName)) {
			firstNameText.setText(firstName);
		}
		String lastName = getOwnerLastName();
		if (!StringUtils.isEmpty(lastName)) {
			lastNameText.setText(lastName);
		}
		addListeners();
	}

	private String getOwnerFirstName() {
		Assert.isTrue(currentEObject instanceof Owner);
		return ((Owner) currentEObject).getFirstName();
	}

	private String getOwnerLastName() {
		Assert.isTrue(currentEObject instanceof Owner);
		return ((Owner) currentEObject).getLastName();
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
