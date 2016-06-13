package fr.rostren.tracker.ui.properties.sections.pages;

import java.text.MessageFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;

public class AddBoockletTransferWizardPage extends AbstractAddWizardPage {
    protected static final String[] TRANSFER_TYPES = new String[] { Incoming.class.getSimpleName(),
	    Outgoing.class.getSimpleName() };

    private static final String PAGE_NAME = "Add tranfer to ''{0}'' Page"; //$NON-NLS-1$
    private static final String PAGE_TITLE = "Add Transfer"; //$NON-NLS-1$
    private static final String WIZARD_DESCRIPTION = "Wizard to add a new transfer to the selected boocklet account."; //$NON-NLS-1$

    protected final Tracker tracker;

    protected boolean isIncoming = true;
    protected boolean isOutgoing = false;
    protected OperationTitle title;
    protected Origin origin;

    private ModifyListener modifyTransferTypeListener = new ModifyListener() {
	@Override
	public void modifyText(ModifyEvent event) {
	    String text = ((Combo) event.widget).getText();
	    isIncoming = TRANSFER_TYPES[0].equals(text);
	    isOutgoing = TRANSFER_TYPES[1].equals(text);
	}
    };

    private ModifyListener modifyTitleListener = new ModifyListener() {
	@Override
	public void modifyText(ModifyEvent event) {
	    String text = ((Combo) event.widget).getText();
	    title = getOperationTitle(tracker, text);
	}
    };

    private ModifyListener modifyOriginListener = new ModifyListener() {
	@Override
	public void modifyText(ModifyEvent event) {
	    String text = ((Combo) event.widget).getText();
	    origin = getOrigin(tracker, text);
	}
    };

    public AddBoockletTransferWizardPage(BoockletAccount account) {
	super(MessageFormat.format(PAGE_NAME, account.getName()));
	this.tracker = (Tracker) account.eContainer().eContainer();
	setTitle(PAGE_TITLE);
	setDescription(WIZARD_DESCRIPTION);
    }

    @Override
    protected void createContainer(Composite parent) {
	Composite composite = new Composite(parent, SWT.NONE);
	GridLayout layout = new GridLayout();
	composite.setLayout(layout);
	layout.numColumns = 2;

	createCombo(composite, "Transfer Type: ", TRANSFER_TYPES, modifyTransferTypeListener); //$NON-NLS-1$

	String[] operationsTitles = getOperationsTitles(tracker);
	createCombo(composite, "Title: ", operationsTitles, modifyTitleListener); //$NON-NLS-1$
	if (operationsTitles.length > 0)
	    title = getOperationTitle(tracker, operationsTitles[0]);

	String[] origins = getOrigins(tracker);
	createCombo(composite, "Origin: ", origins, modifyOriginListener); //$NON-NLS-1$
	if (origins.length > 0)
	    origin = getOrigin(tracker, origins[0]);
    }

    public boolean isIncoming() {
	return isIncoming;
    }

    public boolean isOutgoing() {
	return isOutgoing;
    }

    public OperationTitle getTransferTitle() {
	return title;
    }

    public Origin getTransferOrigin() {
	return origin;
    }
}