package fr.rostren.tracker.ui.views;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;

import fr.rostren.tracker.Account;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.histogram.Histogram;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.presentation.TrackerEditor;
import fr.rostren.tracker.ui.views.internal.FilterSelectionListener;
import fr.rostren.tracker.ui.views.internal.actions.RefreshHistogramAction;

public class TrackerHistogramView extends ViewPart {
	private static final String ALL_CATEGORIES_ITEM="All Categories"; //$NON-NLS-1$
	private static final String ALL_OPERATIONS_ITEM="All Operations"; //$NON-NLS-1$
	private static final String ACCOUNTS_COMBO_TITLE="Select Account:"; //$NON-NLS-1$
	private static final String YEARS_COMBO_TITLE="Select Year:"; //$NON-NLS-1$
	private static final String FILTER_BY_OPERATION_CHECK_BUTTON_TITLE="By Operation:"; //$NON-NLS-1$
	private static final String FILTER_BY_CATEGORY_CHECK_BUTTON_TITLE="By Category:"; //$NON-NLS-1$
	private static final String VIEW_TITLE="Histogram"; //$NON-NLS-1$
	private static final String FILTER_SECTION="Filter"; //$NON-NLS-1$
	private static final String HISTOGRAM_SECTION="Balance"; //$NON-NLS-1$

	/** The toolkit used to create our form. */
	FormToolkit toolkit;

	SashForm formBody;

	/** Form containing the Tracker Histogram View itself. */
	private Form histogramParentForm;
	private ScrolledForm filterForm;
	private ScrolledForm histogramForm;

	private SashForm filterColumn;
	/** Indicates whether the filters section is visible or not. */
	private final boolean filterVisible=true;

	private Histogram histogram;
	private Button operationCheckButton;
	private Button categoryCheckButton;
	private Combo categoriesCombo;
	private Combo operationsCombo;
	private Combo accountsCombo;
	private Combo yearsCombo;

	private Tracker tracker;
	private Section histogramSection;

	@Override
	public void createPartControl(Composite parent) {
		Composite root=new SashForm(parent, SWT.HORIZONTAL);
		GridLayout layout=new GridLayout();
		layout.marginHeight=0;
		layout.marginWidth=0;
		layout.verticalSpacing=0;
		layout.horizontalSpacing=0;
		root.setLayout(layout);
		GridData gridData=new GridData(GridData.FILL_BOTH);
		gridData.horizontalIndent=1;
		root.setLayoutData(gridData);

		toolkit=new FormToolkit(root.getDisplay());
		createHistogramViewForm(root);
	}

	/**
	 * Creates a histogram view form
	 * @param parent the composite parent of widgets
	 */
	private void createHistogramViewForm(Composite parent) {
		histogramParentForm=toolkit.createForm(parent);
		toolkit.decorateFormHeading(histogramParentForm);
		histogramParentForm.setText(TrackerHistogramView.VIEW_TITLE);

		// Add actions to tool bar
		IToolBarManager manager=histogramParentForm.getToolBarManager();
		addRefreshActionToToolBar(manager);
		manager.update(true);

		Composite mainBody=histogramParentForm.getBody();
		mainBody.setLayout(new GridLayout());
		formBody=new SashForm(mainBody, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(formBody);
		formBody.setLayoutData(new GridData(GridData.FILL_BOTH));

		tracker=getTracker();
		createFilterArea(formBody);
		createHistogramArea(formBody);

		formBody.setWeights(new int[] {1, 4});
		formBody.pack();
	}

	/**
	 * Adds the refresh action to the tool bar
	 * @param manager the tool bar manager
	 */
	private void addRefreshActionToToolBar(IToolBarManager manager) {
		manager.add(new RefreshHistogramAction(this));
	}

	/**
	 * Creates the filter section
	 * @param parent the composite parent
	 */
	private void createFilterArea(Composite parent) {
		filterColumn=new SashForm(parent, SWT.HORIZONTAL | SWT.SMOOTH);
		toolkit.adapt(filterColumn);

		Section filterSection=toolkit.createSection(filterColumn, ExpandableComposite.TITLE_BAR);//| ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
		filterSection.setText(TrackerHistogramView.FILTER_SECTION);

		filterForm=toolkit.createScrolledForm(filterSection);

		Composite filterSectionBody=filterForm.getBody();
		filterSectionBody.setLayout(new GridLayout(4, false));
		filterSectionBody.setLayoutData(new GridData(GridData.FILL_BOTH));

		createFilterContent(filterSectionBody);

		toolkit.paintBordersFor(filterForm);
		filterSection.setClient(filterForm);
		filterSection.setVisible(filterVisible);

		filterForm.layout();
		filterSection.layout();
	}

	/**
	 * Creates the histogram itself
	 * @param parent the composite parent
	 */
	private void createHistogramArea(Composite parent) {
		SashForm histogramColumn=new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(histogramColumn);

		histogramSection=toolkit.createSection(histogramColumn, ExpandableComposite.TITLE_BAR | SWT.MIN);
		histogramSection.setText(TrackerHistogramView.HISTOGRAM_SECTION);

		histogramForm=toolkit.createScrolledForm(histogramSection);

		Composite histogramSectionBody=histogramForm.getBody();
		histogramSectionBody.setLayout(new GridLayout());
		histogramSectionBody.setLayoutData(new GridData(GridData.FILL_BOTH));

		histogram=new Histogram(histogramColumn);

		toolkit.paintBordersFor(histogramForm);
		histogramSection.setClient(histogramForm);

		histogramForm.layout();
		histogramSection.layout();

		populateHistogram();
		histogramSection.update();
	}

	/**
	 * Populates the histogram according to the filter selection
	 */
	public void populateHistogram() {
		List<String> months=getHistogramMonths();
		List<Double> incomeValues=new ArrayList<>();
		List<Double> spendingValues=new ArrayList<>();

		Account account=TrackerUtils.findAccount(tracker, accountsCombo.getItem(accountsCombo.getSelectionIndex()));
		if (yearsCombo.getItems().length == 0) {
			//FIXME there is no item in the combo => warning message
			return;
		}
		int year=Integer.parseInt(yearsCombo.getText());
		if (categoryCheckButton.getSelection()) {
			String item=categoriesCombo.getText();
			if (item.equals(TrackerHistogramView.ALL_CATEGORIES_ITEM)) {
				incomeValues=TrackerUtils.findAllCategoriesAmount(account, months, year, true, IncomeCategory.class);
				spendingValues=TrackerUtils.findAllCategoriesAmount(account, months, year, true, SpendingCategory.class);
			}
			else {
				incomeValues=TrackerUtils.findIncomeCategoryAmounts(account, item, months, year, true);
				spendingValues=TrackerUtils.findSpendingCategoryAmounts(account, item, months, year, true);
			}
		}
		else if (operationCheckButton.getSelection()) {
			String item=operationsCombo.getText();
			if (item.equals(TrackerHistogramView.ALL_OPERATIONS_ITEM)) {
				incomeValues=TrackerUtils.findAllCategoriesAmount(account, months, year, true, IncomeCategory.class);
				spendingValues=TrackerUtils.findAllCategoriesAmount(account, months, year, true, SpendingCategory.class);
			}
			else {
				incomeValues=TrackerUtils.findOperationAmounts(tracker, item, months);
				spendingValues=TrackerUtils.findOperationAmounts(tracker, item, months);
			}
		}

		histogram.populateHistogram(months, incomeValues, spendingValues, false);
	}

	/**
	 * @return the list of dates
	 */
	private List<String> getHistogramMonths() {
		return Arrays.asList(Month.values()).stream()//
				.map(month -> month.toString())//
				.collect(Collectors.toList());
	}

	/**
	 * @param parent the composite parent of widgets
	 */
	private void createFilterContent(Composite parent) {
		Composite container=new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		//		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label accountsLabel=new Label(container, SWT.NONE);
		accountsLabel.setText(TrackerHistogramView.ACCOUNTS_COMBO_TITLE);
		accountsCombo=new Combo(container, SWT.NONE);

		container=new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		//		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		categoryCheckButton=createFilterButton(container, TrackerHistogramView.FILTER_BY_CATEGORY_CHECK_BUTTON_TITLE, true, true);
		categoriesCombo=new Combo(container, SWT.NONE);

		container=new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		//		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		operationCheckButton=createFilterButton(container, TrackerHistogramView.FILTER_BY_OPERATION_CHECK_BUTTON_TITLE, true, false);
		operationsCombo=new Combo(container, SWT.NONE);
		operationsCombo.setEnabled(false);

		categoryCheckButton.addSelectionListener(new FilterSelectionListener(operationCheckButton, categoriesCombo, operationsCombo, this));
		operationCheckButton.addSelectionListener(new FilterSelectionListener(categoryCheckButton, operationsCombo, categoriesCombo, this));

		container=new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		//		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		Label yearsLabel=new Label(container, SWT.NONE);
		yearsLabel.setText(TrackerHistogramView.YEARS_COMBO_TITLE);
		yearsCombo=new Combo(container, SWT.NONE);

		populateFilter();
	}

	/**
	 *Populates the Filter combos
	 */
	private void populateFilter() {
		populateCombo(accountsCombo, getAccountsItems(TrackerUtils.getAccounts(tracker)));
		populateCombo(categoriesCombo, getCategoriesItems(TrackerUtils.getCategories(tracker)));
		populateCombo(operationsCombo, getOperationsItems(TrackerUtils.getOperationsTitles(tracker)));
		populateCombo(yearsCombo, getYearsItems(TrackerUtils.findYears(tracker)));
	}

	/**
	 * Creates a filter button
	 * @param parent the composite parent
	 * @param title the button title
	 * @param isEnabled <code>true</code> if the button is enabled, <code>false</code> otherwise
	 * @param isSelected <code>true</code> if the button is selected, <code>false</code> otherwise
	 * @return the created button
	 */
	private Button createFilterButton(Composite parent, String title, boolean isEnabled, boolean isSelected) {
		Button button=new Button(parent, SWT.RADIO);
		button.setText(title);
		button.setEnabled(isEnabled);
		button.setSelection(isSelected);
		return button;
	}

	/**
	 * Populates the accounts combo
	 * @param combo the accounts combo
	 * @param items the table of new items
	 */
	private void populateCombo(Combo combo, String[] items) {
		String oldSelection=combo.getText();
		combo.setItems(items);
		List<String> asList=Arrays.asList(items);
		if (!StringUtils.isEmpty(oldSelection) && asList.contains(oldSelection)) {
			combo.select(asList.indexOf(oldSelection));
		}
		else {
			combo.select(0);
		}
	}

	/**
	 * Array of years
	 * @param years the set of years
	 * @return the array of items
	 */
	private String[] getYearsItems(Set<Integer> years) {
		return years.stream()//
				.map(year -> Integer.toString(year))//
				.collect(Collectors.toList()).stream()//
				.toArray(String[]::new);
	}

	/**
	 * Array of accounts titles
	 * @param accounts the set of accounts
	 * @return the array of items
	 */
	private String[] getAccountsItems(Set<Account> accounts) {
		return accounts.stream()//
				.map(account -> account.getName())//
				.collect(Collectors.toList()).stream()//
				.toArray(String[]::new);
	}

	/**
	 * Array of categories titles
	 * @param categories the set of categories
	 * @return the array of items
	 */
	private String[] getCategoriesItems(Set<Category> categories) {
		List<String> titles=new ArrayList<>();
		titles.addAll(categories.stream()//
				.filter(category -> !StringUtils.isEmpty(category.getTitle()))//
				.map(category -> category.getTitle())//
				.collect(Collectors.toList()));
		titles.add(0, TrackerHistogramView.ALL_CATEGORIES_ITEM);
		return titles.stream().toArray(String[]::new);
	}

	/**
	 * Array of operations titles
	 * @param operationsTitles the set of operations titles
	 * @return the array of items
	 */
	private String[] getOperationsItems(Set<OperationTitle> operationsTitles) {
		List<String> titles=new ArrayList<>();
		titles.addAll(operationsTitles.stream()//
				.map(operationTitle -> operationTitle.getTitle())//
				.collect(Collectors.toList()));
		titles.add(0, TrackerHistogramView.ALL_OPERATIONS_ITEM);
		return titles.stream().toArray(String[]::new);
	}

	@Override
	public void setFocus() {
		histogramParentForm.setFocus();
	}

	/**
	 * Returns the current active editor part
	 * @return the current active editor part
	 */
	private IEditorPart getEditorPart() {
		IWorkbenchWindow activeWorkbenchWindow=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage=activeWorkbenchWindow.getActivePage();

		if (activePage == null || activePage.getActiveEditor() == null) {
			return null;
		}
		return activePage.getActiveEditor();
	}

	/**
	 * Returns the current tracker object
	 * @return the current tracker object
	 */
	private Tracker getTracker() {
		IEditorPart editorPart=getEditorPart();
		if (!(editorPart instanceof TrackerEditor)) {
			return null;
		}
		TrackerEditor editor=(TrackerEditor)editorPart;
		ISelection selection=editor.getSelection();
		if (!(selection instanceof StructuredSelection)) {
			return null;
		}
		if (!selection.isEmpty()) {
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement instanceof XMIResourceImpl) {
				return (Tracker)((XMIResourceImpl)firstElement).getContents().get(0);
			}
		}
		URI uri=URI.createFileURI(((FileEditorInput)editorPart.getEditorInput()).getPath().toOSString());
		Resource resource=loadResource(uri);
		return (Tracker)resource.getContents().get(0);
	}

	/**
	 * Loads the resource
	 * @param uri the resource {@link URI} instance
	 * @return the loaded resource
	 */
	private Resource loadResource(URI uri) {
		ResourceSet resourceSet=new ResourceSetImpl();

		EPackage.Registry.INSTANCE.put(TrackerPackage.eNS_URI, TrackerPackage.eINSTANCE);

		Map<String, Object> options=resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		options.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.setPackageRegistry(TrackerPackage.Registry.INSTANCE);

		return resourceSet.getResource(uri, true);
	}

	/**
	 *Refreshes the Filter selection and the histogram.
	 */
	public void refresh() {
		populateFilter();
		populateHistogram();
		histogram.refresh();
	}
}
