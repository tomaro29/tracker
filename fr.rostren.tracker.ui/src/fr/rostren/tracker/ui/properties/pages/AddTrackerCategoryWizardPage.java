package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * Page to add a {@link Category} instance to an existing {@link Tracker}
 * instance.
 */
public class AddTrackerCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new Category to the selected tracker."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected String title;
	protected String description;

	protected Button incomeCheckButton;
	protected Button spendingCheckButton;

	private final ModifyListener modifyTitleListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			title=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};

	private final ModifyListener modifyDescriptionListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			description=((Text)event.widget).getText();
		}
	};

	private final SelectionListener selectionIncomeListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Button button=(Button)event.getSource();
			spendingCheckButton.setSelection(!button.getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			// Do Nothing
		}
	};
	private final SelectionListener selectionSpendingListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Button button=(Button)event.getSource();
			incomeCheckButton.setSelection(!button.getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			// Do Nothing
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddTrackerCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddTrackerCategoryWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddTrackerCategoryWizardPage.PAGE_TITLE);
		setDescription(AddTrackerCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Title: ", title, modifyTitleListener); //$NON-NLS-1$
		createText(parent, "Description: ", description, modifyDescriptionListener); //$NON-NLS-1$
		incomeCheckButton=createCheckButton(parent, "Is Income", selectionIncomeListener); //$NON-NLS-1$
		spendingCheckButton=createCheckButton(parent, "Is Spending", selectionSpendingListener); //$NON-NLS-1$
	}

	/**
	 * Returns the category title
	 * @return the category title
	 */
	public String getCategoryTitle() {
		return title;
	}

	/**
	 * Returns the category description
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return description;
	}

	/**
	 * @return <code>true</code> if the category is an income one, <code>false</code> otherwise.
	 */
	public boolean isIncome() {
		return incomeCheckButton.getSelection();
	}

	/**
	 * @return <code>true</code> if the category is an spending one, <code>false</code> otherwise.
	 */
	public boolean isSpending() {
		return spendingCheckButton.getSelection();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			setErrorMessage("The Category title cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.isCategoryTitleUnique(tracker, title)) {
			setErrorMessage("The Category title must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}