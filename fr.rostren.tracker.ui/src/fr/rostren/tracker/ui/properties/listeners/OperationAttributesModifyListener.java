package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.sections.operation.OperationAttributesPropertySection;

public class OperationAttributesModifyListener extends AbstractModifyListener {

	private final OperationAttributesPropertySection section;

	/**
	 * Constructor
	 * @param section the section
	 */
	public OperationAttributesModifyListener(OperationAttributesPropertySection section) {
		this.section=section;
	}

	@Override

	protected void executeModify(Widget widget) {
		EObject eObject=section.getCurrentEObject();
		CCombo titleCombo=section.getTitleCombo();
		CCombo originCombo=section.getOriginCombo();

		if (widget.equals(titleCombo)) {
			OperationTitle title=TrackerUtils.getOperationTitle((Operation)eObject, titleCombo.getText());
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.OPERATION__OPERATION_TITLE, title);
		}
		if (widget.equals(originCombo)) {
			Origin origin=TrackerUtils.getOperationOrigin((Operation)eObject, originCombo.getText());
			DomainUtils.executeSetCommand(eObject, TrackerPackage.Literals.OPERATION__ORIGIN, origin);
		}
	}
}
