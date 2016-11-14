package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.content.providers.OriginsRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddCheckOperationWizardPage extends AbstractAddWizardPage {
	protected static final String[] OPERATION_TYPES = new String[] { Credit.class.getSimpleName(),
			Debit.class.getSimpleName(), Incoming.class.getSimpleName(), Outgoing.class.getSimpleName() };

	private static final String PAGE_NAME = "Add operation to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE = "Add operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION = "Wizard to add a new operation to the selected checking account."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected boolean isCredit = true;
	protected boolean isDebit = false;
	protected boolean isIncoming = false;
	protected boolean isOutgoing = false;
	protected OperationTitle title;
	protected Origin origin;

	private ModifyListener modifyOperationTypeListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text = ((Combo) event.widget).getText();
			isCredit = OPERATION_TYPES[0].equals(text);
			isDebit = OPERATION_TYPES[1].equals(text);
			isIncoming = OPERATION_TYPES[2].equals(text);
			isOutgoing = OPERATION_TYPES[3].equals(text);
		}
	};

	private ISelectionChangedListener titleListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection = event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss = (StructuredSelection) selection;
			Object firstElement = ss.getFirstElement();
			if (firstElement != null && firstElement instanceof OperationTitle)
				title = (OperationTitle) firstElement;
		}
	};

	private ISelectionChangedListener originListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {

			ISelection selection = event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss = (StructuredSelection) selection;
			Object firstElement = ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Origin)
				origin = (Origin) firstElement;
		}
	};

	public AddCheckOperationWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(PAGE_NAME, pageTitle));
		this.tracker = tracker;
		setTitle(PAGE_TITLE);
		setDescription(WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Operation Type: ", OPERATION_TYPES, modifyOperationTypeListener); //$NON-NLS-1$

		List<Object> operationsTitles = getOperationsTitles(tracker);
		createComboViewer(parent, "Title: ", operationsTitles, new OperationsTitlesRepositoryContentProvider(), //$NON-NLS-1$
				new OperationTitleLabelProvider(), titleListener);
		if (!operationsTitles.isEmpty())
			title = (OperationTitle) operationsTitles.get(0);

		List<Object> origins = getOrigins(tracker);
		createComboViewer(parent, "Origin: ", origins, new OriginsRepositoryContentProvider(), //$NON-NLS-1$
				new OriginLabelProvider(), originListener);
		if (!origins.isEmpty())
			origin = (Origin) origins.get(0);
	}

	public boolean isIncoming() {
		return isIncoming;
	}

	public boolean isOutgoing() {
		return isOutgoing;
	}

	public boolean isCredit() {
		return isCredit;
	}

	public boolean isDebit() {
		return isDebit;
	}

	public OperationTitle getOperationTitle() {
		return title;
	}

	public Origin getOperationOrigin() {
		return origin;
	}
}