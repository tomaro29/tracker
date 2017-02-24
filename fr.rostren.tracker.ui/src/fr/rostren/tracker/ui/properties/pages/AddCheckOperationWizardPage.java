package fr.rostren.tracker.ui.properties.pages;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

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
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OperationsTitlesRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.content.providers.OriginsRepositoryContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationTitleLabelProvider;
import fr.rostren.tracker.ui.properties.label.providers.OriginLabelProvider;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;

/**
 * Page to add an {@link Operation} instance to an existing
 * {@link CheckingAccount} instance.
 */
public class AddCheckOperationWizardPage extends AbstractAddWizardPage {
	protected static final String[] OPERATION_TYPES=new String[] {Credit.class.getSimpleName(), Debit.class.getSimpleName(), Incoming.class.getSimpleName(),
		Outgoing.class.getSimpleName()};

	private static final String PAGE_NAME="Add operation to ''{0}'' Page"; //$NON-NLS-1$
	private static final String PAGE_TITLE="Add operation"; //$NON-NLS-1$
	private static final String WIZARD_DESCRIPTION="Wizard to add a new operation to the selected checking account."; //$NON-NLS-1$

	protected final Tracker tracker;

	protected boolean isCredit=true;
	protected boolean isDebit=false;
	protected boolean isIncoming=false;
	protected boolean isOutgoing=false;
	protected OperationTitle title;
	protected Origin origin;

	protected ComboViewer titlesComboViewer;
	protected ComboViewer originsComboViewer;

	private final SelectionAdapter addOperationTitleButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddTrackerOperationTitleWizard wizard=new AddTrackerOperationTitleWizard("Operations Titles Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				OperationTitle newOperationTitle=TrackerFactory.eINSTANCE.createOperationTitle();

				String title=wizard.getOperationTitle();
				if (title != null) {
					newOperationTitle.setTitle(title);
				}

				DomainUtils.executeAddCommand(tracker.getOperationsTitlesRepositories(), TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, newOperationTitle);
				refreshComboViewerContent(titlesComboViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles()), newOperationTitle);
			}
		}
	};
	private final SelectionAdapter addOriginButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			AddTrackerOriginWizard wizard=new AddTrackerOriginWizard("Origins Repository", //$NON-NLS-1$
					tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Origin newOrigin=TrackerFactory.eINSTANCE.createOrigin();

				String identifier=wizard.getIdentifier();
				if (identifier != null) {
					newOrigin.setIdentifier(identifier);
				}

				DomainUtils.executeAddCommand(tracker.getOriginsRepository(), TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, newOrigin);
				refreshComboViewerContent(originsComboViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getOrigins()), newOrigin);
			}
		}
	};
	private final ModifyListener modifyOperationTypeListener=new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent event) {
			String text=((Combo)event.widget).getText();
			isCredit=AddCheckOperationWizardPage.OPERATION_TYPES[0].equals(text);
			isDebit=AddCheckOperationWizardPage.OPERATION_TYPES[1].equals(text);
			isIncoming=AddCheckOperationWizardPage.OPERATION_TYPES[2].equals(text);
			isOutgoing=AddCheckOperationWizardPage.OPERATION_TYPES[3].equals(text);
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
	public AddCheckOperationWizardPage(String pageTitle, Tracker tracker) {
		super(MessageFormat.format(AddCheckOperationWizardPage.PAGE_NAME, pageTitle));
		this.tracker=tracker;
		setTitle(AddCheckOperationWizardPage.PAGE_TITLE);
		setDescription(AddCheckOperationWizardPage.WIZARD_DESCRIPTION);
	}

	@Override
	protected void createContainer(Composite parent) {
		createCombo(parent, "Operation Type: ", AddCheckOperationWizardPage.OPERATION_TYPES, modifyOperationTypeListener); //$NON-NLS-1$

		Set<OperationTitle> operationsTitles=new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles());
		titlesComboViewer=createComboViewer(parent, "Title: ", operationsTitles, new OperationsTitlesRepositoryContentProvider(), //$NON-NLS-1$
				new OperationTitleLabelProvider(), titleListener, addOperationTitleButtonlistener);
		if (!operationsTitles.isEmpty()) {
			title=operationsTitles.iterator().next();
		}

		Set<Origin> origins=new HashSet<>(TrackerUtils.getTrackerService(tracker).getOrigins());
		originsComboViewer=createComboViewer(parent, "Origin: ", origins, new OriginsRepositoryContentProvider(), //$NON-NLS-1$
				new OriginLabelProvider(), originListener, addOriginButtonlistener);
		if (!origins.isEmpty()) {
			origin=origins.iterator().next();
		}
	}

	/**
	 * Returns <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Incoming}, <code>false</code> otherwise.
	 */
	public boolean isIncoming() {
		return isIncoming;
	}

	/**
	 * Returns <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Outgoing}, <code>false</code> otherwise.
	 */
	public boolean isOutgoing() {
		return isOutgoing;
	}

	/**
	 * Returns <code>true</code> if is {@link Credit}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Credit}, <code>false</code> otherwise.
	 */
	public boolean isCredit() {
		return isCredit;
	}

	/**
	 * Returns <code>true</code> if is {@link Debit}, <code>false</code> otherwise.
	 * @return <code>true</code> if is {@link Debit}, <code>false</code> otherwise.
	 */
	public boolean isDebit() {
		return isDebit;
	}

	/**
	 * Returns the operation title
	 * @return the operation title
	 */
	public OperationTitle getOperationTitle() {
		return title;
	}

	/**
	 * Returns the origin
	 * @return the origin
	 */
	public Origin getOperationOrigin() {
		return origin;
	}
}