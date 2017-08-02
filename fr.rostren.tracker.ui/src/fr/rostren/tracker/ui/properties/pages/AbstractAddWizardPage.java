/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.pages;

import java.math.BigDecimal;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.OperationData;
import fr.rostren.tracker.model.utils.TrackerUtils;
import fr.rostren.tracker.ui.DomainUtils;
import fr.rostren.tracker.ui.properties.wizards.AddCategoryCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddIncomeCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddSpendingCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerCategoryWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOperationTitleWizard;
import fr.rostren.tracker.ui.properties.wizards.AddTrackerOriginWizard;

public abstract class AbstractAddWizardPage extends AbstractWizardPage {
	private static final String CATEGORIES_REPOSITORY="Categories Repository"; //$NON-NLS-1$
	protected final EObject object;

	protected ComboViewer titlesComboViewer;
	protected ComboViewer originsComboViewer;
	protected ComboViewer categoriesComboViewer;
	protected Tree categoriesTree;
	protected TreeViewer categoriesTreeViewer;

	protected final SelectionAdapter addOperationTitleButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			if (object instanceof Tracker) {
				Tracker tracker=(Tracker)object;
				AddTrackerOperationTitleWizard wizard=new AddTrackerOperationTitleWizard("Operations Titles Repository", //$NON-NLS-1$
						tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					OperationTitle newOperationTitle=TrackerFactory.eINSTANCE.createOperationTitle();

					String newTitle=wizard.getOperationTitle();
					if (newTitle != null) {
						newOperationTitle.setTitle(newTitle);
					}

					DomainUtils.executeAddCommand(tracker.getOperationsTitlesRepositories(), TrackerPackage.Literals.OPERATIONS_TITLE_REPOSITORY__OPERATIONS_TITLES,
							newOperationTitle);
					refreshComboViewerContent(titlesComboViewer, new HashSet<>(TrackerUtils.getTrackerService(tracker).getOperationsTitles()), newOperationTitle);
				}
			}
		}
	};
	protected final SelectionAdapter addOriginButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			if (object instanceof Tracker) {
				Tracker tracker=(Tracker)object;
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
		}
	};
	protected final SelectionAdapter addSubCategoryButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			if (object instanceof Category) {
				Category category=(Category)object;
				AddCategoryCategoryWizard wizard=new AddCategoryCategoryWizard("Sub Categories", //$NON-NLS-1$
						category);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					if (category instanceof IncomeCategory) {
						IncomeCategory newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();
						String title=wizard.getCategoryTitle();
						if (!StringUtils.isEmpty(title)) {
							newCategory.setTitle(title);
						}
						String description=wizard.getCategoryDescription();
						if (!StringUtils.isEmpty(description)) {
							newCategory.setDescription(description);
						}

						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
						refreshComboViewerContent(categoriesComboViewer, new HashSet<>(((IncomeCategory)category).getIncomes()), newCategory);
					}
					else if (category instanceof SpendingCategory) {
						SpendingCategory newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();
						String title=wizard.getCategoryTitle();
						if (!StringUtils.isEmpty(title)) {
							newCategory.setTitle(title);
						}
						String description=wizard.getCategoryDescription();
						if (!StringUtils.isEmpty(description)) {
							newCategory.setDescription(description);
						}

						DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
						refreshComboViewerContent(categoriesComboViewer, new HashSet<>(((SpendingCategory)category).getSpendings()), newCategory);
					}
				}
			}
		}
	};
	protected final SelectionAdapter addCategoryButtonlistener=new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			Category newCategory=null;
			if (categoriesTree == null || categoriesTree.getSelection().length == 0) {
				newCategory=addTrackerCategory((Tracker)object);
			}
			else {
				TreeItem[] selection=categoriesTree.getSelection();
				newCategory=addCategorySubCategory((Tracker)object, (Category)selection[0].getData());
			}
			if (newCategory != null && categoriesTreeViewer != null) {
				refreshTreeViewerContent(categoriesTreeViewer, TrackerUtils.getTrackerService(object).getCategories(), newCategory);
			}
		}

		private Category addCategorySubCategory(Tracker tracker, Category category) {
			if (category instanceof IncomeCategory) {
				AddIncomeCategoryWizard wizard=new AddIncomeCategoryWizard(AbstractAddWizardPage.CATEGORIES_REPOSITORY, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addIncomeCategory((IncomeCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			else if (category instanceof SpendingCategory) {
				AddSpendingCategoryWizard wizard=new AddSpendingCategoryWizard(AbstractAddWizardPage.CATEGORIES_REPOSITORY, tracker);
				WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
				if (Window.OK == wizardDialog.open()) {
					return addSpendingCategory((SpendingCategory)category, wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			return null;
		}

		private Category addTrackerCategory(Tracker tracker) {
			AddTrackerCategoryWizard wizard=new AddTrackerCategoryWizard(AbstractAddWizardPage.CATEGORIES_REPOSITORY, tracker);
			WizardDialog wizardDialog=new WizardDialog(getShell(), wizard);
			if (Window.OK == wizardDialog.open()) {
				if (wizard.isIncome()) {
					return addIncomeCategory(tracker.getCategoriesRepository().getIncome(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
				else if (wizard.isSpending()) {
					return addSpendingCategory(tracker.getCategoriesRepository().getSpending(), wizard.getCategoryTitle(), wizard.getCategoryDescription());
				}
			}
			return null;
		}

		private Category addSpendingCategory(SpendingCategory category, String title, String description) {
			Category newCategory=TrackerFactory.eINSTANCE.createSpendingCategory();

			if (!StringUtils.isEmpty(title)) {
				newCategory.setTitle(title);
			}
			if (!StringUtils.isEmpty(description)) {
				newCategory.setDescription(description);
			}
			DomainUtils.executeAddCommand(category, TrackerPackage.Literals.SPENDING_CATEGORY__SPENDINGS, newCategory);
			return newCategory;
		}

		private Category addIncomeCategory(IncomeCategory category, String title, String description) {
			Category newCategory=TrackerFactory.eINSTANCE.createIncomeCategory();

			if (!StringUtils.isEmpty(title)) {
				newCategory.setTitle(title);
			}
			if (!StringUtils.isEmpty(description)) {
				newCategory.setDescription(description);
			}
			DomainUtils.executeAddCommand(category, TrackerPackage.Literals.INCOME_CATEGORY__INCOMES, newCategory);
			return newCategory;
		}
	};

	/**
	 * Constructor
	 * @param pageName the page name
	 * @param object the object
	 */
	protected AbstractAddWizardPage(String pageName, EObject object) {
		super(pageName);
		this.object=object;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout();
		layout.numColumns=2;
		layout.makeColumnsEqualWidth=false;
		layout.marginWidth=0;
		layout.marginHeight=0;
		container.setLayout(layout);

		createContainer(container);

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Creates a container
	 * @param parent the composite parent of the container to create
	 */
	abstract protected void createContainer(Composite parent);

	/**
	 * @param title the category title
	 * @return <code>true</code> if the category title is valid, <code>false</code> otherwise.
	 */
	protected boolean isCategoryPageComplete(String title) {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			setErrorMessage("The Category title cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.getTrackerService(object).isCategoryTitleUnique(title)) {
			setErrorMessage("The Category title must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}

	/**
	 * @param value the value
	 * @param amountValue the amountValue
	 * @param operation the operation
	 * @return <code>true</code> if the sub amount is valid, <code>false</code> otherwise.
	 */
	protected boolean isOperationSubAmountPageComplete(String value, Double amountValue, OperationData operation) {
		if (!StringUtils.isEmpty(value) && !StringUtils.isBlank(value)) {
			try {
				double parsed=Double.parseDouble(value);
				if (Double.isNaN(parsed) || Double.isInfinite(parsed) || BigDecimal.ZERO.doubleValue() == parsed) {
					setErrorMessage("The amount value must be finite"); //$NON-NLS-1$
					return false;
				}
			}
			catch (NumberFormatException e) {
				setErrorMessage("The operation amount must be a number !"); //$NON-NLS-1$
				return false;
			}
			if (amountValue > operation.getTotalAmount()) {
				setErrorMessage("The operation sub amount value must be less than it's total amount !"); //$NON-NLS-1$
				return false;
			}
		}

		setErrorMessage(null);
		return true;
	}

	/**
	 * @param tracker the tracker
	 * @param identifier the account identifier
	 * @param amount the account amount
	 *  @return <code>true</code> if the account attributes are valid, <code>false</code> otherwise.
	 */
	protected boolean isAccountPageComplete(Tracker tracker, String identifier, String amount) {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			setErrorMessage("The Account identifier cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		try {
			int parseInt=Integer.parseInt(identifier);
			if (parseInt == 0) {
				setErrorMessage("The Account identifier cannot be '0' !"); //$NON-NLS-1$
				return false;
			}
		}
		catch (NumberFormatException e) {
			setErrorMessage("The Account identifier must be a number !"); //$NON-NLS-1$
			return false;
		}
		if (!StringUtils.isEmpty(amount) && !StringUtils.isBlank(amount)) {
			try {
				float parseFloat=Float.parseFloat(amount);
				if (Float.isInfinite(parseFloat) || Float.isNaN(parseFloat)) {
					setErrorMessage("The Account amount cannot be '0' !"); //$NON-NLS-1$
					return false;
				}
			}
			catch (NumberFormatException e) {
				setErrorMessage("The Account amount must be a number !"); //$NON-NLS-1$
				return false;
			}
		}
		if (!TrackerUtils.getTrackerService(tracker).isAccountIdentifierUnique(identifier)) {
			setErrorMessage("The Account identifier must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}

	/**
	 * @param title the operation title
	 * @return <code>true</code> if the operationTitle attributes are valid, <code>false</code> otherwise.
	 */
	protected boolean isOperationTitlePageComplete(String title) {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			setErrorMessage("The Operation title cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.getTrackerService(object).isOperationTitleUnique(title)) {
			setErrorMessage("The Operation title must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;

	}

	/**
	 * @param identifier the origin identifier
	 * @return <code>true</code> if the origin attributes are valid, <code>false</code> otherwise.
	 */
	protected boolean isOriginPageComplete(String identifier) {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			setErrorMessage("The origin identifier cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.getTrackerService(object).isOriginIdentifierUnique(identifier)) {
			setErrorMessage("The origin identifier must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}

	/**
	 * @param firstName the owner firstName
	 * @param lastName the owner lastName
	 * @return <code>true</code> if the owner attributes are valid, <code>false</code> otherwise.
	 */
	protected boolean isOwnerPageComplete(String firstName, String lastName) {
		if (StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)) {
			setErrorMessage("The Owner First Name cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)) {
			setErrorMessage("The Owner Last Name cannot be empty or blank !"); //$NON-NLS-1$
			return false;
		}
		if (!TrackerUtils.getTrackerService(object).isOwnerIdentifierUnique(firstName, lastName)) {
			setErrorMessage("The owner must be unique !"); //$NON-NLS-1$
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}
