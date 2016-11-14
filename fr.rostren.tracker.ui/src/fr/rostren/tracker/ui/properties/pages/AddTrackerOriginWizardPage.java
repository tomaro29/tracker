package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Origin} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOriginWizardPage extends AbstractAddWizardPage {
	protected static final String[] ORIGIN_TYPES = new String[] { OriginType.MANUAL.getLiteral(),
			OriginType.PDF_FILE.getLiteral() };

	private static final String PAGE_NAME = "Add origin to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE = "Add Origin"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION = "Wizard to add a new Origin to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String identifier = "identifier"; //$NON-NLS-1$
	protected OriginType type = OriginType.MANUAL;

	private ModifyListener modifyIdentifierListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			identifier = ((Text) event.widget).getText();
		}
	};

	private ModifyListener modifyOriginTypeListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text = ((Combo) event.widget).getText();
			if (ORIGIN_TYPES[0].equals(text))
				type = OriginType.MANUAL;
			if (ORIGIN_TYPES[1].equals(text))
				type = OriginType.PDF_FILE;
		}
	};

	public AddTrackerOriginWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(PAGE_NAME, pageTitle));
		this.tracker = tracker;
		setTitle(PAGE_TITLE);
		setDescription(WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Identifier: ", identifier, modifyIdentifierListener); //$NON-NLS-1$
		createCombo(parent, "Type: ", ORIGIN_TYPES, modifyOriginTypeListener); //$NON-NLS-1$
	}

	public String getIdentifier() {
		return identifier;
	}

	public OriginType getType() {
		return type;
	}
}