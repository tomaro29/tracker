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
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;

/**
 * Page to add an {@link OperationTitle} instance to an existing
 * {@link Category} instance.
 */
public class AddCategoryOperationTitleWizardPage extends AbstractAddWizardPage {
	private static final String PAGE_NAME="Add operation title to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation title"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation title to the selected category."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected OperationTitle title;

	private final ISelectionChangedListener titleListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof OperationTitle) {
				title=(OperationTitle)firstElement;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddCategoryOperationTitleWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddCategoryOperationTitleWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddCategoryOperationTitleWizardPage.PAGE_TITLE);
		setDescription(AddCategoryOperationTitleWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		List<Object> operationsTitles=getOperationsTitles(tracker);
		createComboViewer(parent, "Operation Title: ", operationsTitles, //$NON-NLS-1$
				new OperationsTitlesRepositoryContentProvider(), new OperationTitleLabelProvider(), titleListener);
		if (!operationsTitles.isEmpty()) {
			title=(OperationTitle)operationsTitles.get(0);
		}
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return title;
	}
}