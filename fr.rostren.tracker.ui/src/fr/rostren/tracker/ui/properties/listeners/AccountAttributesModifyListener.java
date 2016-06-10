package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.properties.sections.account.AccountAttributesPropertySection;

public class AccountAttributesModifyListener extends AbstractModifyListener {

    private final AccountAttributesPropertySection section;

    public AccountAttributesModifyListener(AccountAttributesPropertySection section) {
	this.section = section;
    }

    @Override
    protected void executeModifyText(Widget widget) {
	EObject eObject = section.getCurrentEObject();
	Text nameText = section.getNameText();
	Text amountText = section.getAmountText();
	Text identifierText = section.getIdentifierText();

	if (widget.equals(nameText))
	    executeSetCommand(eObject, TrackerPackage.Literals.ACCOUNT__NAME, nameText.getText());
	else if (widget.equals(amountText))
	    executeSetCommand(eObject, TrackerPackage.Literals.ACCOUNT__AMOUNT,
		    Float.parseFloat(amountText.getText()));
	else if (widget.equals(identifierText))
	    executeSetCommand(eObject, TrackerPackage.Literals.ACCOUNT__IDENTIFIER,
		    Integer.parseInt(identifierText.getText()));
    }
}
