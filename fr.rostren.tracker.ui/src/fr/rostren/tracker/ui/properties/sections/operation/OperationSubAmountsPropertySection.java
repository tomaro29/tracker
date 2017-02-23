package fr.rostren.tracker.ui.properties.sections.operation;

import java.util.Collections;
import java.util.List;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationService;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.content.providers.OperationSubAmountContentProvider;
import fr.rostren.tracker.ui.properties.label.providers.OperationSubAmountLabelProvider;
import fr.rostren.tracker.ui.properties.sections.AbstractTablePropertySection;
import fr.rostren.tracker.ui.properties.wizards.OperationSubAmountWizard;

public class OperationSubAmountsPropertySection extends AbstractTablePropertySection {

	private final ITreeContentProvider contentProvider=new OperationSubAmountContentProvider();
	private final ILabelProvider labelProvider=new OperationSubAmountLabelProvider();

	private final SelectionAdapter addButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			Operation operation=getOperation();
			Tracker tracker=TrackerUtils.getTracker(operation);

			OperationService operationService=TrackerFactory.eINSTANCE.createOperationService();
			operationService.setOperation(operation);
			OperationSubAmountWizard wizard=new OperationSubAmountWizard(tracker, operationService.adaptOperation(), null, true);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				Amount newAmount=TrackerFactory.eINSTANCE.createAmount();

				Category category=wizard.getAmountCategory();
				if (category != null) {
					newAmount.setCategory(category);
				}

				double value=wizard.getAmountValue();
				if (value != 0) {
					newAmount.setValue(value);
				}

				EList<Category> categories=operation.getOperationTitle().getCategories();
				if (!categories.contains(newAmount.getCategory())) {
					categories.add(newAmount.getCategory());
				}

				DomainUtils.executeAddCommand(operation, TrackerPackage.Literals.OPERATION__SUB_AMOUNTS, newAmount);
				refresh();
			}
		}
	};

	private final SelectionAdapter removeButtonListener=new SelectionAdapter() {
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

	/**
	 * Returns the current operation object
	 * @return the current operation object
	 */
	protected Operation getOperation() {
		EObject currentEObject=getCurrentEObject();
		Assert.isTrue(currentEObject instanceof Operation);
		return (Operation)currentEObject;
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		table=createTable(body, null, addButtonlistener, removeButtonListener);
		tableViewer=new TableViewer(table);
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(labelProvider);
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	@Override
	public void refresh() {
		disposeListeners();
		tableViewer.setInput(getSubAmounts());
		addListeners();
	}

	/**
	 * Returns the sub amounts
	 * @return the sub amounts
	 */
	private List<Amount> getSubAmounts() {
		Assert.isTrue(currentEObject instanceof Operation);
		EList<Amount> subAmounts=((Operation)currentEObject).getSubAmounts();
		if (subAmounts == null || subAmounts.isEmpty()) {
			return Collections.emptyList();
		}
		return subAmounts;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void disposeListeners() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		disposeButtonsListeners(addButtonlistener, removeButtonListener);
	}
}
