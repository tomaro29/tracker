/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Amount;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginType;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.Transfer;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.AddAccountWizard;
import fr.rostren.tracker.ui.properties.wizards.AddBoockletTransferWizard;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryOperationTitleWizard;
import fr.rostren.tracker.ui.properties.wizards.AddCheckOperationWizard;
import fr.rostren.tracker.ui.properties.wizards.AddOperationTitleCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddOriginOperationWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOwnerWizard;
import fr.rostren.tracker.ui.properties.wizards.OperationSubAmountWizard;

public abstract class AbstractTablePropertySection extends AbstractTrackerPropertySection {
	private static final String OPERATIONS_TITLES_REPOSITORY_STRING="Operations Titles Repository"; //$NON-NLS-1$
	private static final String ORIGINS_REPOSITORY_STRING="Origins Repository"; //$NON-NLS-1$
	private static final String TRACKER_STRING="Tracker"; //$NON-NLS-1$

	protected TableViewer tableViewer;
	protected Table table;

	protected ITreeContentProvider contentProvider;
	protected ILabelProvider labelProvider;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		if (editButtonListener == null) {
			table=createTable(body, null, addButtonListener, removeButtonListener);
		}
		else {
			table=createTable(body, null, addButtonListener, editButtonListener, removeButtonListener);
		}
		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	/**
	 * Creates a table
	 * @param composite the composite parent of the {@link Table} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created table
	 */
	protected Table createTable(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Table newTable=widgetFactory.createTable(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTableLayout(control, newTable, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTable, newTable, addButtonlistener);
		createRemoveButton(composite, widgetFactory, newTable, addButton, removeButtonListener);

		return newTable;
	}

	/**
	 * Creates a table
	 * @param composite the composite parent of the {@link Table} to create
	 * @param control the control
	 * @param addButtonlistener the add button listener
	 * @param editButtonlistener the edit button listener
	 * @param removeButtonListener the remove button listener
	 * @return the created table
	 */
	protected Table createTable(Composite composite, Text control, SelectionAdapter addButtonlistener, SelectionAdapter editButtonlistener, SelectionAdapter removeButtonListener) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Table newTable=widgetFactory.createTable(composite, SWT.V_SCROLL | SWT.MULTI);
		formatTableLayout(control, newTable, new Font(composite.getDisplay(), "Arial", 10, SWT.BOLD)); //$NON-NLS-1$

		createAddButton(composite, widgetFactory, newTable, newTable, addButtonlistener);
		createEditButton(composite, widgetFactory, newTable, addButton, editButtonlistener);
		createRemoveButton(composite, widgetFactory, newTable, editButton, removeButtonListener);

		return newTable;
	}

	/**
	 * Formats the table layout
	 * @param control the text control, as a top attachment
	 * @param table the table to format
	 * @param font the table content font
	 */
	private void formatTableLayout(Text control, Table table, Font font) {
		FormData data=new FormData();
		data.left=new FormAttachment(0, 5);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, -85);
		data.height=275;
		table.setLayoutData(data);
		table.setFont(font);
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForAccountTransfers() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof BoockletAccount);
				BoockletAccount boocklet=(BoockletAccount)currentEObject;

				String pageTitle=boocklet.getName();
				Tracker tracker=(Tracker)boocklet.eContainer().eContainer();

				AddBoockletTransferWizard wizard=new AddBoockletTransferWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Transfer newTansfer=getTransfer(wizard);

					if (newTansfer == null) {
						return;
					}

					setAttributes(wizard, newTansfer);

					DomainUtils.executeAddCommand(boocklet, TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, newTansfer);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForAccountTransfers() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof BoockletAccount);
				BoockletAccount boocklet=(BoockletAccount)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(boocklet, TrackerPackage.Literals.BOOCKLET_ACCOUNT__TRANSFERS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForCategoryOperationsTitles() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				String pageTitle=category.getTitle();
				Tracker tracker=TrackerUtils.getTracker(category);

				AddCategoryOperationTitleWizard wizard=new AddCategoryOperationTitleWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					OperationTitle title=wizard.getOperationTitle();
					if (title != null) {
						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, title);
						refresh();
					}
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForCategoryOperationsTitles() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Category);
				Category category=(Category)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(category, TrackerPackage.Literals.CATEGORY__OPERATION_TITLES, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForOperationsSubAmounts() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Operation operation=getOperation();

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(operation, TrackerPackage.Literals.OPERATION__SUB_AMOUNTS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForCheckingAccountOperations() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CheckingAccount);
				CheckingAccount checking=(CheckingAccount)currentEObject;

				String pageTitle=checking.getName();
				Tracker tracker=TrackerUtils.getTracker(checking);

				AddCheckOperationWizard wizard=new AddCheckOperationWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Operation newOperation=getOperation(wizard);
					if (newOperation == null) {
						return;
					}

					setAttributes(wizard, newOperation);
					DomainUtils.executeAddCommand(checking, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, newOperation);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForCheckingAccountOperations() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof CheckingAccount);
				CheckingAccount account=(CheckingAccount)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(account, TrackerPackage.Literals.CHECKING_ACCOUNT__OPERATIONS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOperationSubAmounts() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Operation operation=getOperation();
				Tracker tracker=TrackerUtils.getTracker(operation);

				OperationService operationService=TrackerFactory.eINSTANCE.createOperationService();
				operationService.setOperation(operation);
				OperationData operationData=operationService.adaptOperation();
				OperationSubAmountWizard wizard=new OperationSubAmountWizard(tracker, operationData, operationData.getType(), null, true);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Amount newAmount=TrackerFactory.eINSTANCE.createAmount();
					setAttributes(wizard, newAmount);

					EList<Category> categories=operation.getOperationTitle().getCategories();
					if (!categories.contains(newAmount.getCategory())) {
						categories.add(newAmount.getCategory());
					}

					DomainUtils.executeAddCommand(operation, TrackerPackage.Literals.OPERATION__SUB_AMOUNTS, newAmount);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns an Edit button
	 * @return the edit button
	 */
	public SelectionAdapter createEditButtonForOperationSubAmounts() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Operation operation=getOperation();

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToEdit=((StructuredSelection)selection).getFirstElement();
				if (elementToEdit == null || !(elementToEdit instanceof Amount)) {
					return;
				}
				Amount amount=(Amount)elementToEdit;
				Tracker tracker=TrackerUtils.getTracker(operation);

				OperationService operationService=TrackerFactory.eINSTANCE.createOperationService();
				operationService.setOperation(operation);
				OperationData operationData=operationService.adaptOperation();
				OperationSubAmountWizard wizard=new OperationSubAmountWizard(tracker, operationData, operationData.getType(), amount, false);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					setAttributes(wizard, amount);

					EList<Category> categories=operation.getOperationTitle().getCategories();
					if (!categories.contains(amount.getCategory())) {
						categories.add(amount.getCategory());
					}
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForOperationTitleCategories() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof OperationTitle);
				OperationTitle operationTitle=(OperationTitle)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(operationTitle, TrackerPackage.Literals.OPERATION_TITLE__CATEGORIES, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOperationTitleCategories() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof OperationTitle);
				OperationTitle operationTitle=(OperationTitle)currentEObject;

				String pageTitle=operationTitle.getTitle();
				Tracker tracker=(Tracker)operationTitle.eContainer().eContainer();

				AddOperationTitleCategoryWizard wizard=new AddOperationTitleCategoryWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Category category=wizard.getCategory();
					if (category != null) {
						DomainUtils.executeAddCommand(operationTitle, TrackerPackage.Literals.OPERATION_TITLE__CATEGORIES, category);
						refresh();
					}
				}
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOriginOperations() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Origin);
				Origin origin=(Origin)currentEObject;

				String pageTitle=origin.getIdentifier();
				Tracker tracker=(Tracker)origin.eContainer().eContainer();

				AddOriginOperationWizard wizard=new AddOriginOperationWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Optional<Operation> operation=wizard.getOperation();
					DomainUtils.executeAddCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, operation);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForOriginOperations() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Origin);
				Origin origin=(Origin)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(origin, TrackerPackage.Literals.ORIGIN__OPERATIONS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOwnerAccounts() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Owner);
				Owner owner=(Owner)currentEObject;

				String pageTitle=owner.getFirstName() + " " + owner.getLastName(); //$NON-NLS-1$
				Tracker tracker=(Tracker)owner.eContainer();

				AddAccountWizard wizard=new AddAccountWizard(pageTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Account newAccount=getAccount(wizard);
					if (newAccount == null) {
						return;
					}
					setAttributes(wizard, newAccount);

					DomainUtils.executeAddCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, newAccount);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button {@link SelectionAdapter} instance
	 */
	public SelectionAdapter createRemoveButtonForOwnerAccounts() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Owner);
				Owner owner=(Owner)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(owner, TrackerPackage.Literals.OWNER__ACCOUNTS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForTrackerOwners() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;

				AddTrackerOwnerWizard wizard=new AddTrackerOwnerWizard(AbstractTablePropertySection.TRACKER_STRING, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Owner newOwner=TrackerFactory.eINSTANCE.createOwner();

					String ownerFirstName=wizard.getFirstName();
					if (ownerFirstName != null) {
						newOwner.setFirstName(ownerFirstName);
					}
					String ownerLastName=wizard.getLastName();
					if (ownerLastName != null) {
						newOwner.setLastName(ownerLastName);
					}

					DomainUtils.executeAddCommand(tracker, TrackerPackage.Literals.TRACKER__OWNERS, newOwner);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button
	 */
	public SelectionAdapter createRemoveButtonForTrackerOwners() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Assert.isTrue(currentEObject instanceof Tracker);
				Tracker tracker=(Tracker)currentEObject;

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(tracker, TrackerPackage.Literals.TRACKER__OWNERS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOriginsRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Tracker tracker;
				OriginsRepository repository;
				String wizardTitle;
				if (currentEObject instanceof Tracker) {
					tracker=(Tracker)currentEObject;
					repository=tracker.getOriginsRepository();
					wizardTitle=AbstractTablePropertySection.TRACKER_STRING;
				}
				else {
					Assert.isTrue(currentEObject instanceof OriginsRepository);
					repository=(OriginsRepository)currentEObject;
					tracker=(Tracker)repository.eContainer();
					wizardTitle=AbstractTablePropertySection.ORIGINS_REPOSITORY_STRING;
				}

				AddTrackerOriginWizard wizard=new AddTrackerOriginWizard(wizardTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					Origin newOrigin=TrackerFactory.eINSTANCE.createOrigin();

					String identifier=wizard.getIdentifier();
					if (identifier != null) {
						newOrigin.setIdentifier(identifier);
					}
					OriginType type=wizard.getType();
					if (type != null) {
						newOrigin.setType(type);
					}

					DomainUtils.executeAddCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, newOrigin);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button
	 */
	public SelectionAdapter createRemoveButtonForOriginsRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				OriginsRepository repository;
				if (currentEObject instanceof Tracker) {
					Tracker tracker=(Tracker)currentEObject;
					repository=tracker.getOriginsRepository();
				}
				else {
					Assert.isTrue(currentEObject instanceof OriginsRepository);
					repository=(OriginsRepository)currentEObject;
				}

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(repository, TrackerPackage.Literals.ORIGINS_REPOSITORY__ORIGINS, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns an Add button
	 * @return the add button
	 */
	public SelectionAdapter createAddButtonForOperationsTitleRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				Tracker tracker;
				OperationsTitleRepository repository;
				String wizardTitle;
				if (currentEObject instanceof Tracker) {
					tracker=(Tracker)currentEObject;
					repository=tracker.getOperationsTitlesRepositories();
					wizardTitle=AbstractTablePropertySection.TRACKER_STRING;
				}
				else {
					Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
					repository=(OperationsTitleRepository)currentEObject;
					tracker=(Tracker)repository.eContainer();
					wizardTitle=AbstractTablePropertySection.OPERATIONS_TITLES_REPOSITORY_STRING;
				}

				AddTrackerOperationTitleWizard wizard=new AddTrackerOperationTitleWizard(wizardTitle, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					OperationTitle newOperationTitle=TrackerFactory.eINSTANCE.createOperationTitle();

					String operationTitle=wizard.getOperationTitle();
					if (operationTitle != null) {
						newOperationTitle.setTitle(operationTitle);
					}

					DomainUtils.executeAddCommand(repository, TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, newOperationTitle);
					refresh();
				}
			}
		};
	}

	/**
	 * Creates and returns a remove button
	 * @return the remove button
	 */
	public SelectionAdapter createRemoveButtonForOperationsTitleRepository() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				EObject currentEObject=getCurrentEObject();
				OperationsTitleRepository repository;
				if (currentEObject instanceof Tracker) {
					Tracker tracker=(Tracker)currentEObject;
					repository=tracker.getOperationsTitlesRepositories();
				}
				else {
					Assert.isTrue(currentEObject instanceof OperationsTitleRepository);
					repository=(OperationsTitleRepository)currentEObject;
				}

				ISelection selection=tableViewer.getSelection();
				Assert.isTrue(selection instanceof StructuredSelection);
				Object elementToRemove=((StructuredSelection)selection).getFirstElement();
				DomainUtils.executeRemoveCommand(repository, TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES, elementToRemove);
				refresh();
			}
		};
	}

	/**
	 * Creates and returns the transfer
	 * @param wizard the wizard
	 * @return the created transfer
	 */
	Transfer getTransfer(AddBoockletTransferWizard wizard) {
		if (wizard.isIncoming()) {
			return TrackerFactory.eINSTANCE.createIncoming();
		}
		else if (wizard.isOutgoing()) {
			return TrackerFactory.eINSTANCE.createOutgoing();
		}
		return null;
	}

	/**
	 * Returns the current operation object
	 * @return the current operation object
	 */
	Operation getOperation() {
		EObject currentEObject=getCurrentEObject();
		Assert.isTrue(currentEObject instanceof Operation);
		return (Operation)currentEObject;
	}

	/**
	 * Returns the new operation
	 * @param wizard the wizard
	 * @return the new operation
	 */
	Operation getOperation(AddCheckOperationWizard wizard) {
		Operation newOperation=null;
		if (wizard.isCredit()) {
			newOperation=TrackerFactory.eINSTANCE.createCredit();
		}
		else if (wizard.isDebit()) {
			newOperation=TrackerFactory.eINSTANCE.createDebit();
		}
		else if (wizard.isIncoming()) {
			newOperation=TrackerFactory.eINSTANCE.createIncoming();
		}
		else if (wizard.isOutgoing()) {
			newOperation=TrackerFactory.eINSTANCE.createOutgoing();
		}
		return newOperation;
	}

	/**
	 * Returns the new account
	 * @param wizard the wizard
	 * @return the new account
	 */
	Account getAccount(AddAccountWizard wizard) {
		Account newAccount=null;
		if (wizard.isCheckingAccount()) {
			newAccount=TrackerFactory.eINSTANCE.createCheckingAccount();
		}
		else if (wizard.isBoockletAccount()) {
			newAccount=TrackerFactory.eINSTANCE.createBoockletAccount();
		}
		return newAccount;
	}

	/**
	 * Sets the given transfer attributes basing on the given wizard data
	 * @param wizard the wizard
	 * @param transfer the transfer
	 */
	void setAttributes(AddBoockletTransferWizard wizard, Transfer transfer) {
		OperationTitle transferTitle=wizard.getTransferTitle();
		if (transferTitle != null) {
			transfer.setOperationTitle(transferTitle);
		}

		Origin transferOrigin=wizard.getTransferOrigin();
		if (transferOrigin != null) {
			transfer.setOrigin(transferOrigin);
		}
	}

	/**
	 * Sets the attributes of the given amount basing on the given wizard data
	 * @param wizard the wizard
	 * @param amount the amount
	 */
	void setAttributes(OperationSubAmountWizard wizard, Amount amount) {
		Category category=wizard.getAmountCategory();
		if (category != null) {
			amount.setCategory(category);
		}

		double value=wizard.getAmountValue();
		if (Double.isFinite(value) && BigDecimal.ZERO.doubleValue() != value) {
			amount.setValue(value);
		}
	}

	/**
	 * Sets the given operation attributes basing on the given wizard data
	 * @param wizard the wizard
	 * @param operation the operation
	 */
	void setAttributes(AddCheckOperationWizard wizard, Operation operation) {
		OperationTitle operationTitle=wizard.getOperationTitle();
		if (operationTitle != null) {
			operation.setOperationTitle(operationTitle);
		}

		Origin operationOrigin=wizard.getOperationOrigin();
		if (operationOrigin != null) {
			operation.setOrigin(operationOrigin);
		}
	}

	/**
	 * Sets the given account attributes basing on the given wizard data
	 * @param wizard the wizard
	 * @param account the account
	 */
	void setAttributes(AddAccountWizard wizard, Account account) {
		String accountName=wizard.getAccountName();
		if (!StringUtils.isEmpty(accountName) && !StringUtils.isBlank(accountName)) {
			account.setName(accountName);
		}

		int accountIdentifier=wizard.getAccountIdentifier();
		if (accountIdentifier != 0) {
			account.setIdentifier(accountIdentifier);
		}

		double accountAmount=wizard.getAccountAmount();
		if (Double.isFinite(accountAmount) && BigDecimal.ZERO.doubleValue() != accountAmount) {
			account.setAmount(accountAmount);
		}
	}

	/**
	 * Disposes buttons listeners
	 * @param addButtonlistener the add button listener
	 * @param editButtonButtonListener the edit button listener
	 * @param removeButtonListener the remove button listener
	 */
	@Override
	protected void disposeButtonsListeners(SelectionAdapter addButtonlistener, SelectionAdapter editButtonButtonListener, SelectionAdapter removeButtonListener) {
		if (addButton != null && !addButton.isDisposed()) {
			addButton.removeSelectionListener(addButtonlistener);
		}
		if (editButton != null && !editButton.isDisposed()) {
			editButton.removeSelectionListener(editButtonButtonListener);
		}
		if (removeButton != null && !removeButton.isDisposed()) {
			removeButton.removeSelectionListener(removeButtonListener);
		}
	}

	@Override
	protected void refreshViewer() {
		tableViewer.setInput(getEObjects());

	}

	@Override
	protected void addListeners() {
		// Do Nothing
	}

	@Override
	protected void disposeListeners() {
		// Do Nothing
	}

	/**
	 * @return the list of objects
	 */
	abstract protected List<EObject> getEObjects();
}
