package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;

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
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryCategoryWizard;

/**
 * Page to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategorySubCategoryWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add category to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add category"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new category to the selected category."; //$NON-NLS-1$

	protected final Category category;

	protected Category subCategory;

	protected ComboViewer categoriesComboViewer;

	private final SelectionAdapter addSubCategoryButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddCategoryCategoryWizard wizard=new AddCategoryCategoryWizard("Sub Categories", //$NON-NLS-1$
					category);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Category newCategory=TrackerFactory.eINSTANCE.createCategory();

				String title=wizard.getCategoryTitle();
				if (!StringUtils.isEmpty(title)) {
					newCategory.setTitle(title);
				}
				String description=wizard.getCategoryDescription();
				if (!StringUtils.isEmpty(description)) {
					newCategory.setDescription(description);
				}

				DomainUtils.executeAddCommand(category, TrackerPackage.Literals.CATEGORY__SUB_CATEGORIES, newCategory);
				refreshComboViewerContent(categoriesComboViewer, new HashSet<>(category.getSubCategories()), newCategory);
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
				subCategory=(Category)firstElement;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param category the given category
	 */
	public AddCategorySubCategoryWizardPage(String pageTitle, Category category) {
		super(MessageFormat.format(AddCategorySubCategoryWizardPage.PAGE_NAME, pageTitle));
		this.category=category;
		setTitle(AddCategorySubCategoryWizardPage.PAGE_TITLE);
		setDescription(AddCategorySubCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		List<Category> categories=category.getSubCategories();
		categoriesComboViewer=createComboViewer(parent, "Category: ", new HashSet<>(categories), //$NON-NLS-1$
				new CategoriesRepositoryContentProvider(), new CategoryLabelProvider(), categoryListener, addSubCategoryButtonlistener);
		if (!categories.isEmpty()) {
			subCategory=categories.get(0);
		}
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public Category getSubCategory() {
		return subCategory;
	}
}