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

import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Incoming;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Outgoing;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.content.providers.OriginsRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;

/**
 * Page to add a {@link Transfer} instance to an existing
 * {@link BoockletAccount} instance.
 */
public class AddBoockletTransferWizardPage extends AbstractAddWizardPage {
	protected static final String[] TRANSFER_TYPES=new String[] {Incoming.class.getSimpleName(), Outgoing.class.getSimpleName()};

	private static final String PAGE_NAME="Add tranfer to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add Transfer"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new transfer to the selected boocklet account."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected boolean isIncoming=true;
	protected boolean isOutgoing=false;
	protected OperationTitle title;
	protected Origin origin;

	private final ModifyListener modifyTransferTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			isIncoming=AddBoockletTransferWizardPage.TRANSFER_TYPES[0].equals(text);
			isOutgoing=AddBoockletTransferWizardPage.TRANSFER_TYPES[1].equals(text);
		}
	};

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

	private final ISelectionChangedListener originListener=new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {

			ISelection selection=event.getSelection();
			Assert.isTrue(selection instanceof StructuredSelection);
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement != null && firstElement instanceof Origin) {
				origin=(Origin)firstElement;
			}
		}
	};

	/**
	 * Constructor
	 * @param pageTitle the page title
	 * @param tracker the given tracker
	 */
	public AddBoockletTransferWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddBoockletTransferWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddBoockletTransferWizardPage.PAGE_TITLE);
		setDescription(AddBoockletTransferWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Transfer Type: ", AddBoockletTransferWizardPage.TRANSFER_TYPES, modifyTransferTypeListener); //$NON-NLS-1$

		List<Object> operationsTitles=getOperationsTitles(tracker);
		createComboViewer(parent, "Title: ", operationsTitles, new OperationsTitlesRepositoryContentProvider(), //$NON-NLS-1$
				new OperationTitleLabelProvider(), titleListener);
		if (!operationsTitles.isEmpty()) {
			title=(OperationTitle)operationsTitles.get(0);
		}

		List<Object> origins=getOrigins(tracker);
		createComboViewer(parent, "Origin: ", origins, new OriginsRepositoryContentProvider(), //$NON-NLS-1$
				new OriginLabelProvider(), originListener);
		if (!origins.isEmpty()) {
			origin=(Origin)origins.get(0);
		}
	}

	/**
	 * Returns <code>true</code> if is {@link Incoming}, <code>false</code> otherwise
	 * @return <code>true</code> if is {@link Incoming}, <code>false</code> otherwise
	 */
	public boolean isIncoming() {
		return isIncoming;
	}

	/**
	 * Returns <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise
	 * @return <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise
	 */
	public boolean isOutgoing() {
		return isOutgoing;
	}

	/**
	 * Returns the transfer title
	 * @return the transfer title
	 */
	public OperationTitle getTransferTitle() {
		return title;
	}

	/**
	 * Returns the transfer origin
	 * @return the transfer origin
	 */
	public Origin getTransferOrigin() {
		return origin;
	}
}