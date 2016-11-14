package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;

/**
 * Page to add a {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME = "Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE = "Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION = "Wizard to add a new Category to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String title = "identifier"; //$NON-NLS-1$
	protected String description = "description"; //$NON-NLS-1$

	private ModifyListener modifyTitleListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			title = ((Text) event.widget).getText();
		}
	};

	private ModifyListener modifyDescriptionListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			description = ((Text) event.widget).getText();
		}
	};

	public AddTrackerCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(PAGE_NAME, pageTitle));
		this.tracker = tracker;
		setTitle(PAGE_TITLE);
		setDescription(WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Title: ", title, modifyTitleListener); //$NON-NLS-1$
		createText(parent, "Description: ", description, modifyDescriptionListener); //$NON-NLS-1$
	}

	public String getCategoryTitle() {
		return title;
	}

	public String getCategoryDescription() {
		return description;
	}
}