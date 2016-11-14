package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.owner.OwnerAttributesPropertySection;

public class OwnerAttributesModifyListener extends AbstractModifyListener {

	private final OwnerAttributesPropertySection section;

	public OwnerAttributesModifyListener(OwnerAttributesPropertySection section) {
		this.section = section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject = section.getCurrentEObject();
		Text firstName = section.getFirstNameText();
		Text lastName = section.getLastNameText();

		if (widget.equals(firstName)) {
			ListenersUtils.executeSetCommand(eObject, TrackerPackage.Literals.OWNER__FIRST_NAME, firstName.getText());
		} else if (widget.equals(lastName)) {
			ListenersUtils.executeSetCommand(eObject, TrackerPackage.Literals.OWNER__LAST_NAME, lastName.getText());
		}
	}
}
