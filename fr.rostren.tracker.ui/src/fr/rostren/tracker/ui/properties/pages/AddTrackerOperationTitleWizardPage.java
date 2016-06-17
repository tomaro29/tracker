package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link OperationTitle} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOperationTitleWizardPage extends AbstractAddWizardPage {
    protected static final String[] ORIGIN_TYPES = new String[] { OriginType.MANUAL.getLiteral(),
	    OriginType.PDF_FILE.getLiteral() };

    private static final String PAGE_NAME = "Add operation title to ''{0}'' Page"; //$NON-NLS-1$
    private static final String PAGE_TITLE = "Add operation title"; //$NON-NLS-1$
    private static final String WIZARD_DESCRIPTION = "Wizard to add a new operation title to the selected tracker."; //$NON-NLS-1$

    protected final Tracker tracker;

    protected String title = "title"; //$NON-NLS-1$

    private ModifyListener modifyTitleListener = new ModifyListener() {
	@Override
	public void modifyText(ModifyEvent event) {
	    title = ((Text) event.widget).getText();
	}
    };

    public AddTrackerOperationTitleWizardPage(String pageTitle, Tracker tracker) {
	super(MessageFormat.format(PAGE_NAME, pageTitle));
	this.tracker = tracker;
	setTitle(PAGE_TITLE);
	setDescription(WIZARD_DESCRIPTION);
    }

    @Override
    protected void createContainer(Composite parent) {
	createText(parent, "Title: ", title, modifyTitleListener); //$NON-NLS-1$
    }

    public String getOperationTitle() {
	return title;
    }

}