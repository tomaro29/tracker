package fr.rostren.tracker.ui.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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

import fr.rostren.tracker.Category;
import fr.rostren.tracker.Month;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.histogram.Histogram;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.presentation.TrackerEditor;
import fr.rostren.tracker.ui.views.internal.FilterSelectionListener;

public class TrackerHistogramView extends ViewPart {
	private static final String ALL_CATEGORIES_ITEM="All Categories"; //$NON-NLS-1$
	private static final String ALL_OPERATIONS_ITEM="All Operations"; //$NON-NLS-1$
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
	private boolean filterVisible;

	/**The histogram object*/
	private Histogram histogram;
	private Button operationCheckButton;
	private Button categoryCheckButton;
	private Combo categoriesCombo;
	private Combo operationsCombo;

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

		Composite mainBody=histogramParentForm.getBody();
		mainBody.setLayout(new GridLayout());
		formBody=new SashForm(mainBody, SWT.HORIZONTAL | SWT.SMOOTH);
		toolkit.adapt(formBody);
		formBody.setLayoutData(new GridData(GridData.FILL_BOTH));

		createFilterColumn();
		createHistogramColumn();

		formBody.setWeights(new int[] {1, 3});
		formBody.pack();
	}

	/**
	 * Creates the filter section
	 */
	private void createFilterColumn() {
		filterColumn=new SashForm(formBody, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(filterColumn);

		Section filterSection=toolkit.createSection(filterColumn, ExpandableComposite.TITLE_BAR);
		filterSection.setText(TrackerHistogramView.FILTER_SECTION);

		filterForm=toolkit.createScrolledForm(filterSection);

		Composite filterSectionBody=filterForm.getBody();
		filterSectionBody.setLayout(new GridLayout(2, false));
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
	 */
	private void createHistogramColumn() {
		SashForm histogramColumn=new SashForm(formBody, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(histogramColumn);

		Section histogramSection=toolkit.createSection(histogramColumn, ExpandableComposite.TITLE_BAR | SWT.MIN);
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
	}

	/**
	 * Populates the histogram according to the filter selection
	 */
	public void populateHistogram() {
		List<String> dates=getHistogramDates();
		List<Double> incomeValues=new ArrayList<>();
		List<Double> spendingValues=new ArrayList<>();

		if (categoryCheckButton.getSelection()) {
			String item=categoriesCombo.getItem(categoriesCombo.getSelectionIndex());
			if (item.equals(TrackerHistogramView.ALL_CATEGORIES_ITEM)) {
				incomeValues=TrackerUtils.getAllIncomeCategoryAmount(dates);
				spendingValues=TrackerUtils.getAllSpendingCategoryAmount(dates);
			}
			else {
				incomeValues=TrackerUtils.getIncomeCategoryAmount(item, dates);
				spendingValues=TrackerUtils.getSpendingCategoryAmount(item, dates);
			}
		}
		else if (operationCheckButton.getSelection()) {
			String item=operationsCombo.getItem(operationsCombo.getSelectionIndex());
			if (item.equals(TrackerHistogramView.ALL_OPERATIONS_ITEM)) {
				incomeValues=TrackerUtils.getAllIncomeCategoryAmount(dates);
				spendingValues=TrackerUtils.getAllSpendingCategoryAmount(dates);
			}
			else {
				incomeValues=TrackerUtils.getOperationAmount(item, dates);
				spendingValues=TrackerUtils.getOperationAmount(item, dates);
			}
		}

		histogram.populateHistogram(dates, incomeValues, spendingValues, false);
	}

	/**
	 * @return the list of dates
	 */
	private List<String> getHistogramDates() {
		List<String> dates=new ArrayList<>();
		for (Month month: Month.VALUES) {
			dates.add(month.getLiteral());
		}
		return dates;
	}

	/**
	 * @param parent the composite parent of widgets
	 */
	private void createFilterContent(Composite parent) {
		categoryCheckButton=createFilterButton(parent, TrackerHistogramView.FILTER_BY_CATEGORY_CHECK_BUTTON_TITLE, true, true);
		categoriesCombo=new Combo(parent, SWT.NONE);
		populateCategoriesCombo(categoriesCombo);

		operationCheckButton=createFilterButton(parent, TrackerHistogramView.FILTER_BY_OPERATION_CHECK_BUTTON_TITLE, true, false);
		operationsCombo=new Combo(parent, SWT.NONE);
		operationsCombo.setEnabled(false);
		populateOperationsCombo(operationsCombo);

		categoryCheckButton.addSelectionListener(new FilterSelectionListener(operationCheckButton, categoriesCombo, operationsCombo, this));
		operationCheckButton.addSelectionListener(new FilterSelectionListener(categoryCheckButton, operationsCombo, categoriesCombo, this));
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
		Button button=new Button(parent, SWT.CHECK);
		button.setText(title);
		button.setEnabled(isEnabled);
		button.setSelection(isSelected);
		return button;
	}

	/**
	 * Populates the categories combo
	 * @param combo the categories combo
	 */
	private void populateCategoriesCombo(Combo combo) {
		Tracker tracker=getTracker();
		String[] items=getCategoriesItems(TrackerUtils.getCategories(tracker));
		combo.setItems(items);
		combo.select(0);
	}

	/**
	 * Populates the operations combo
	 * @param combo the operations combo
	 */
	private void populateOperationsCombo(Combo combo) {
		Tracker tracker=getTracker();
		String[] items=getOperationsItems(TrackerUtils.getOperationsTitles(tracker));
		combo.setItems(items);
		combo.select(0);
	}

	/**
	 * Array of categories titles
	 * @param categories the set of categories
	 * @return the array of items
	 */
	private String[] getCategoriesItems(Set<Object> categories) {
		String[] items=new String[categories.size() + 1];
		items[0]=TrackerHistogramView.ALL_CATEGORIES_ITEM;
		int i=1;
		Iterator<Object> iterator=categories.iterator();
		while (iterator.hasNext()) {
			Category category=(Category)iterator.next();
			items[i]=category.getTitle();
			i++;
		}
		return items;
	}

	/**
	 * Array of operations titles
	 * @param Operations the set of operations
	 * @return the array of items
	 */
	private String[] getOperationsItems(Set<Object> Operations) {
		String[] items=new String[Operations.size() + 1];
		items[0]=TrackerHistogramView.ALL_OPERATIONS_ITEM;
		int i=1;
		Iterator<Object> iterator=Operations.iterator();
		while (iterator.hasNext()) {
			OperationTitle operation=(OperationTitle)iterator.next();
			items[i]=operation.getTitle();
			i++;
		}
		return items;
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
			return (Tracker)firstElement;
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

}