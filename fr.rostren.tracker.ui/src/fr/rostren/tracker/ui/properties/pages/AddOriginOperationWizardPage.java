package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.Optional;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Credit;
import fr.rostren.tracker.Debit;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.ui.properties.content.providers.OriginOperationsContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationLabelProvider;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddOriginOperationWizardPage extends AbstractAddWizardPage {
	protected static final String[] OPERATION_TYPES=new String[] {Credit.class.getSimpleName(), Debit.class.getSimpleName(), Incoming.class.getSimpleName(),
		Outgoing.class.getSimpleName()};

	private static final String PAGE_NAME="Add operation to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation to the selected origin."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected Optional<Operation> operation;

	private final ISelectionChangedListener listener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement instanceof Operation) {
				operation=Optional.of((Operation)firstElement);
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddOriginOperationWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddOriginOperationWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddOriginOperationWizardPage.PAGE_TITLE);
		setDescription(AddOriginOperationWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createComboViewer(parent, "Operations: ", getOperations(tracker), new OriginOperationsContentProvider(), //$NON-NLS-1$
				new OperationLabelProvider(), listener, null);
	}

	/**
	 * Returns the operation
	 * @return the operation
	 */
	public Optional<Operation> getOperation() {
		return operation;
	}
}