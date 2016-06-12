package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.origin.OriginAttributesPropertySection;

public class OriginAttributesModifyListener extends AbstractModifyListener {

	private final OriginAttributesPropertySection section;

	public OriginAttributesModifyListener(OriginAttributesPropertySection section) {
		this.section = section;
	}

	@Override
	protected void executeModifyText(Widget widget) {
		EObject eObject = section.getCurrentEObject();
		Text id = section.getIdText();
		Text type = section.getTypeText();

		if (widget.equals(id))
			executeSetCommand(eObject, TrackerPackage.Literals.ORIGIN__IDENTIFIER, id.getText());
		else if (widget.equals(type))
			executeSetCommand(eObject, TrackerPackage.Literals.ORIGIN__TYPE, OriginType.valueOf(type.getText()));
	}
}
