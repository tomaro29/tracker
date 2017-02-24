package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * Page to add a {@link Origin} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerOriginWizardPage extends AbstractAddWizardPage {
	protected static final String[] ORIGIN_TYPES=new String[] {OriginType.MANUAL.getLiteral(), OriginType.PDF_FILE.getLiteral()};

	private static final String PAGE_NAME="Add origin to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Origin"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new Origin to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String identifier;
	protected OriginType type=OriginType.MANUAL;

	private final ModifyListener modifyIdentifierListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			identifier=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	private final ModifyListener modifyOriginTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			if (AddTrackerOriginWizardPage.ORIGIN_TYPES[0].equals(text)) {
				type=OriginType.MANUAL;
			}
			if (AddTrackerOriginWizardPage.ORIGIN_TYPES[1].equals(text)) {
				type=OriginType.PDF_FILE;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerOriginWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerOriginWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddTrackerOriginWizardPage.PAGE_TITLE);
		setDescription(AddTrackerOriginWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Identifier: ", identifier, modifyIdentifierListener); //$NON-NLS-1$
		createCombo(parent, "Type: ", AddTrackerOriginWizardPage.ORIGIN_TYPES, modifyOriginTypeListener); //$NON-NLS-1$
	}

	/**
	 * Returns the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Returns the type
	 * @return the type
	 */
	public OriginType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			setErrorMessage("The origin identifier cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.getTrackerService(tracker).isOriginIdentifierUnique(identifier)) {
			setErrorMessage("The origin identifier must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}