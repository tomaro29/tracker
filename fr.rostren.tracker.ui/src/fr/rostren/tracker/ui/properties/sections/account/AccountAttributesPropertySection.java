package fr.rostren.tracker.ui.properties.sections.account;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.ui.properties.listeners.AccountAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class AccountAttributesPropertySection extends AbstractAttributesPropertySection {
    protected Text nameText;
    protected Text amountText;
    protected Text identifierText;

    private ModifyListener listener = new AccountAttributesModifyListener(this);

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
	super.createControls(parent, aTabbedPropertySheetPage);

	this.nameText = createLabeledText(body, null, "Name:"); //$NON-NLS-1$
	this.identifierText = createLabeledText(body, nameText, "Identifier:"); //$NON-NLS-1$
	this.amountText = createLabeledText(body, identifierText, "Amount:"); //$NON-NLS-1$

	addListeners();
    }

    @Override
    public void refresh() {
	disposeListeners();

	nameText.setText(getAccountName());
	amountText.setText(getAccountAmount());
	identifierText.setText(getAccountIdentifier());

	addListeners();
    }

    private String getAccountIdentifier() {
	Assert.isTrue(currentEObject instanceof Account);
	return String.valueOf(((Account) currentEObject).getIdentifier());
    }

    private String getAccountAmount() {
	Assert.isTrue(currentEObject instanceof Account);
	return String.valueOf(((Account) currentEObject).getAmount());
    }

    private String getAccountName() {
	Assert.isTrue(currentEObject instanceof Account);
	if (((Account) currentEObject).getName() == null)
	    return StringUtils.EMPTY;
	return ((Account) currentEObject).getName();
    }

    @Override
    protected void addListeners() {
	nameText.addModifyListener(listener);
	amountText.addModifyListener(listener);
	identifierText.addModifyListener(listener);
    }

    @Override
    protected void disposeListeners() {
	nameText.removeModifyListener(listener);
	amountText.removeModifyListener(listener);
	identifierText.removeModifyListener(listener);
    }

    public Text getNameText() {
	return nameText;
    }

    public Text getAmountText() {
	return amountText;
    }

    public Text getIdentifierText() {
	return identifierText;
    }
}
