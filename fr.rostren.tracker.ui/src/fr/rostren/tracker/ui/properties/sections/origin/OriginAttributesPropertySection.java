package fr.rostren.tracker.ui.properties.sections.origin;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.ui.properties.listeners.OriginAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OriginAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text idText;
	protected Text typeText;

	private final ModifyListener listener=new OriginAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		idText=createLabeledText(body, null, "Identifier:"); //$NON-NLS-1$
		typeText=createLabeledText(body, idText, "Type:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		idText.setText(getOriginIdentifier());
		typeText.setText(getOriginType());
		addListeners();
	}

	/**
	 * Returns the origins identifier
	 * @return the origins identifier
	 */
	private String getOriginIdentifier() {
		Assert.isTrue(currentEObject instanceof Origin);
		String id=((Origin)currentEObject).getIdentifier();
		if (id == null) {
			return StringUtils.EMPTY;
		}
		return id;
	}

	/**
	 * Returns the origin type
	 * @return the origin type
	 */
	private String getOriginType() {
		Assert.isTrue(currentEObject instanceof Origin);
		OriginType type=((Origin)currentEObject).getType();
		if (type == null) {
			return StringUtils.EMPTY;
		}
		return type.getLiteral();
	}

	@Override
	protected void addListeners() {
		idText.addModifyListener(listener);
		typeText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		idText.removeModifyListener(listener);
		typeText.removeModifyListener(listener);
	}

	/**
	 * Returns the id {@link Text}
	 * @return the id {@link Text}
	 */
	public Text getIdText() {
		return idText;
	}

	/**
	 * Returns the type {@link Text}
	 * @return the type {@link Text}
	 */
	public Text getTypeText() {
		return typeText;
	}
}
