package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.CategoriesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.CategoryLabelProvider;

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

	public AddOperationTitleCategoryWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOperationTitleCategoryWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddOperationTitleCategoryWizardPage.PAGE_TITLE);
		setDescription(AddOperationTitleCategoryWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		List<Object> categories=getCategories(tracker);
		createComboViewer(parent, "Category: ", categories, new CategoriesRepositoryContentProvider(), //$NON-NLS-1$
				new CategoryLabelProvider(), listener);
		if (!categories.isEmpty()) {
			category=(Category)categories.get(0);
		}
	}

	public Category getCategory() {
		return category;
	}
}