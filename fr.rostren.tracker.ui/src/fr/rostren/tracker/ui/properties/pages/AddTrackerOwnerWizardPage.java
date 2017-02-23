package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * Page to add a {@link Owner} instance to an existing {@link Tracker} instance.
 */
public class AddTrackerOwnerWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add owner to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Owner"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new owner to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String firstName;
	protected String lastName;

	private final ModifyListener modifyFirstNameListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			firstName=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	private final ModifyListener modifyLastNameListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			lastName=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerOwnerWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerOwnerWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddTrackerOwnerWizardPage.PAGE_TITLE);
		setDescription(AddTrackerOwnerWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "First Name: ", firstName, modifyFirstNameListener); //$NON-NLS-1$
		createText(parent, "Last Name: ", lastName, modifyLastNameListener); //$NON-NLS-1$
	}

	/**
	 * Returns the first name
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the last name
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		if (StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)) {
			setErrorMessage("The Owner First Name cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)) {
			setErrorMessage("The Owner Last Name cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.isOwnerIdentifierUnique(tracker, firstName, lastName)) {
			setErrorMessage("The owner must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}