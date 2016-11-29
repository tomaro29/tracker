package fr.rostren.tracker.ui.properties.pages;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddOperationSubAmountWizardPage extends AbstractAddWizardPage {

	private static final String PAGE_NAME="Add sub-amount to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add sub-amount"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new sub-amount to the selected operation."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected Category category;
	protected String value;

	protected ComboViewer categoriesComboViewer;

	private final ModifyListener modifyValueListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			value=((Text)event.widget).getText();
			setPageComplete(isPageComplete());
		}
	};
	private final SelectionAdapter addCategoryButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard("Categories Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Category newCategory=TrackerFactory.eINSTANCE.createCategory();

				String title=wizard.getCategoryTitle();
				if (title != null) {
					newCategory.setTitle(title);
				}

				DomainUtils.executeAddCommand(tracker.getCategoriesRepository(), TrackerPackage.Literals.CATEGORIES_REPOSITORY__CATEGORIES, newCategory);
				refreshComboViewerContent(categoriesComboViewer, getCategories(tracker), newCategory);
			}
		}
	};
	private final ISelectionChangedListener categoryListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Category) {
				category=(Category)firstElement;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddOperationSubAmountWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOperationSubAmountWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddOperationSubAmountWizardPage.PAGE_TITLE);
		setDescription(AddOperationSubAmountWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Value: ", value, modifyValueListener); //$NON-NLS-1$

		Set<Object> categories=getCategories(tracker);
		categoriesComboViewer=createComboViewer(parent, "Category: ", categories, new CategoriesRepositoryContentProvider(), //$NON-NLS-1$
				new CategoryLabelProvider(), categoryListener, addCategoryButtonlistener);
		if (!categories.isEmpty()) {
			category=(Category)categories.iterator().next();
		}
	}

	/**
	 * Returns the amount category
	 * @return the amount category
	 */
	public Category getAmountCategory() {
		return category;
	}

	/**
	 * Returns the amount value
	 * @return the amount value
	 */
	public BigDecimal getAmountValue() {
		return new BigDecimal(value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		if (!StringUtils.isEmpty(value) && !StringUtils.isBlank(value)) {
			try {
				Float.parseFloat(value);
			}
			catch (NumberFormatException e) {
				setErrorMessage("The Account amount must be a number !"); //$NON-NLS-1$
				return false;
			}
		}
		setErrorMessage(null);
		return true;
	}
}