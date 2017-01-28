package fr.rostren.tracker.ui.properties.pages;

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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class OperationSubAmountWizardPage extends AbstractWizardPage {

	private static final String PAGE_NAME="Add sub-amount to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add sub-amount"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new sub-amount to the selected operation."; //$NON-NLS-1$

	protected final Tracker tracker;
	protected final Operation operation;
	protected final Amount amount;

	protected Category category;
	protected String value;
	protected Date wishedDate;

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
				if (wizard.isIncome()) {
					Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();

					String title=wizard.getCategoryTitle();
					if (!StringUtils.isEmpty(title)) {
						newCategory.setTitle(title);
					}
					String description=wizard.getCategoryDescription();
					if (!StringUtils.isEmpty(description)) {
						newCategory.setDescription(description);
					}
					DomainUtils.executeAddCommand(tracker.getCategoriesRepository().getIncome(), TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
					refreshComboViewerContent(categoriesComboViewer, TrackerUtils.getCategories(tracker), newCategory);
				}
				else if (wizard.isSpending()) {
					Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();

					String title=wizard.getCategoryTitle();
					if (!StringUtils.isEmpty(title)) {
						newCategory.setTitle(title);
					}
					String description=wizard.getCategoryDescription();
					if (!StringUtils.isEmpty(description)) {
						newCategory.setDescription(description);
					}
					DomainUtils.executeAddCommand(tracker.getCategoriesRepository().getSpending(), TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
					refreshComboViewerContent(categoriesComboViewer, TrackerUtils.getCategories(tracker), newCategory);
				}
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
	private final SelectionListener wishedDateKeyListener=new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source=event.getSource();
			Assert.isTrue(source instanceof DateTime);
			DateTime date=(DateTime)source;
			wishedDate.setDay(date.getDay());
			wishedDate.setMonth(Month.get(date.getMonth()));
			wishedDate.setYear(date.getYear());
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
	 * @param operation the operation
	 * @param amount the amount to edit if any, <code>null</code> otherwise.
	 */
	public OperationSubAmountWizardPage(String pageTitle, Tracker tracker, Operation operation, Amount amount) {
		super(MessageFormat.format(OperationSubAmountWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		this.operation=operation;
		this.amount=amount;
		setTitle(OperationSubAmountWizardPage.PAGE_TITLE);
		setDescription(OperationSubAmountWizardPage.WIZARD_DESCRIPTION);
		if (amount != null) {
			value=String.valueOf(amount.getValue());
			category=amount.getCategory();
			wishedDate=amount.getWishedDate();
		}
	}

	@Override
	protected void createContainer(Composite parent) {
		createText(parent, "Value: ", value, modifyValueListener); //$NON-NLS-1$

		Set<Category> categories=TrackerUtils.getCategories(tracker);
		categoriesComboViewer=createComboViewer(parent, "Category: ", categories, new CategoriesRepositoryContentProvider(), //$NON-NLS-1$
				new CategoryLabelProvider(), categoryListener, addCategoryButtonlistener);
		if (!categories.isEmpty()) {
			category=categories.iterator().next();
		}
		createDateTime(parent, "Wished Date", wishedDate, wishedDateKeyListener);//$NON-NLS-1$
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
	public double getAmountValue() {
		return Double.parseDouble(value);
	}

	/**
	 * Returns the amount wished date
	 * @return the amount wished date
	 */
	public Date getAmountWishedDate() {
		return wishedDate;
	}

	@Override
	public boolean isPageComplete() {
		if (!StringUtils.isEmpty(value) && !StringUtils.isBlank(value)) {
			try {
				Float.parseFloat(value);
			}
			catch (NumberFormatException e) {
				setErrorMessage("The operation amount must be a number !"); //$NON-NLS-1$
				return false;
			}
			if (getAmountValue() > operation.getTotalAmount()) {
				setErrorMessage("The operation sub amount value must be less than it's total amount !"); //$NON-NLS-1$
				return false;
			}
		}

		setErrorMessage(null);
		return true;
	}
}