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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;

/**
 * Page to add a {@link Category} instance to an existing {@link OperationTitle}
 * instance.
 */
public class AddOperationTitleCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add Category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new category to the selected operation title."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected Category category;

	protected ComboViewer categoriesComboViewer;

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
	private final ISelectionChangedListener listener=new ISelectionChangedListener() {

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
	public AddOperationTitleCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOperationTitleCategoryWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddOperationTitleCategoryWizardPage.PAGE_TITLE);
		setDescription(AddOperationTitleCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		Set<Category> categories=TrackerUtils.getCategories(tracker);
		categoriesComboViewer=createComboViewer(parent, "Category: ", categories, new CategoriesRepositoryContentProvider(), //$NON-NLS-1$
				new CategoryLabelProvider(), listener, addCategoryButtonlistener);
		if (!categories.isEmpty()) {
			category=categories.iterator().next();
		}
	}

	/**
	 * Returns the category
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
}