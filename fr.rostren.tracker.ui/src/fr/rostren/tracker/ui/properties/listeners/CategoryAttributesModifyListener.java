package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.category.CategoryAttributesPropertySection;

public class CategoryAttributesModifyListener extends AbstractModifyListener {

	private final CategoryAttributesPropertySection section;

	public CategoryAttributesModifyListener(CategoryAttributesPropertySection section) {
		this.section = section;
	}

	@Override
	protected void executeModify(Widget widget) {
		EObject eObject = section.getCurrentEObject();
		Text text = section.getDescriptionText();

		if (widget.equals(text))
			ListenersUtils.executeSetCommand(eObject, TrackerPackage.Literals.CATEGORY__DESCRIPTION, text.getText());
	}
}
