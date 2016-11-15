package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Owner} instance to an existing {@link Tracker} instance.
 */
public class AddTrackerOwnerWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add owner to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Owner"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new owner to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String firstName="first name"; //$NON-NLS-1$
	protected String lastName="last name"; //$NON-NLS-1$

	private final ModifyListener modifyFirstNameListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			firstName=((Text)event.widget).getText();
		}
	};

	private final ModifyListener modifyLastNameListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			lastName=((Text)event.widget).getText();
		}
	};

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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}