package fr.rostren.tracker.ui.properties.sections.amount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Date;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.comparators.CategoryComparator;
import fr.rostren.tracker.ui.properties.listeners.AmountAttributesModifyListener;
import fr.rostren.tracker.ui.properties.listeners.DateSelectionListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class AmountAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text valueText;
	protected DateTime dateTime;
	protected CCombo categoryCombo;

	private final ModifyListener listener=new AmountAttributesModifyListener(this);
	private final SelectionListener datelistener=new DateSelectionListener(this, dateTime);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		valueText=createLabeledText(body, null, "Value:"); //$NON-NLS-1$
		dateTime=createDateTime(body, valueText, "Date:"); //$NON-NLS-1$
		categoryCombo=createLabeledCombo(body, dateTime, "Category:"); //$NON-NLS-1$

		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		valueText.setText(getAmountvalue());
		dateTime.setDate(getYear(), getMonth(), getDay());
		String[] items=getItems();
		categoryCombo.setItems(items);
		setComboSelection(items);
		addListeners();
	}

	/**
	 * Sets the combo selection
	 * @param items the selection items
	 */
	private void setComboSelection(String[] items) {
		Category category=getAmountCategoryItem();
		if (category != null) {
			String title=category.getTitle();
			categoryCombo.select(Arrays.asList(items).indexOf(title));
		}
	}

	/**
	 * Returns the amount value as a {@link String}
	 * @return the amount value as a {@link String}
	 */
	private String getAmountvalue() {
		Assert.isTrue(currentEObject instanceof Amount);
		double value=((Amount)currentEObject).getValue();
		if (value == 0) {
			return StringUtils.EMPTY;
		}
		return String.valueOf(value);
	}

	/**
	 * Returns the amount wished date year
	 * @return the amount wished date year
	 */
	private int getYear() {
		Assert.isTrue(currentEObject instanceof Amount);
		Date wishedDate=((Amount)currentEObject).getWishedDate();
		return wishedDate == null ? 0 : wishedDate.getYear();
	}

	/**
	 * Returns the amount wished date month
	 * @return the amount wished date month
	 */
	private int getMonth() {
		Assert.isTrue(currentEObject instanceof Amount);
		Date wishedDate=((Amount)currentEObject).getWishedDate();
		return wishedDate == null ? 0 : wishedDate.getMonth().getValue();
	}

	/**
	 * Returns the amount wished date day
	 * @return the amount wished date day
	 */
	private int getDay() {
		Assert.isTrue(currentEObject instanceof Amount);
		Date wishedDate=((Amount)currentEObject).getWishedDate();
		return wishedDate == null ? 0 : wishedDate.getDay();
	}

	@Override
	protected void addListeners() {
		valueText.addModifyListener(listener);
		dateTime.addSelectionListener(datelistener);
	}

	@Override
	protected void disposeListeners() {
		valueText.removeModifyListener(listener);
		dateTime.removeSelectionListener(datelistener);
	}

	/**
	 * Returns the value {@link Text}
	 * @return the value {@link Text}
	 */
	public Text getValueText() {
		return valueText;
	}

	/**
	 * Returns the category {@link CCombo}
	 * @return the category {@link CCombo}
	 */
	public CCombo getCategoryCombo() {
		return categoryCombo;
	}

	/**
	 * Returns the amount category
	 * @return the amount category
	 */
	private Category getAmountCategoryItem() {
		Assert.isTrue(currentEObject instanceof Amount);
		return ((Amount)currentEObject).getCategory();
	}

	/**
	 * Returns the category item index
	 * @param category the given category
	 * @return the category item index
	 */
	private int getAmountCategoryItemIndex(Category category) {
		List<Category> sortedCategories=getSortedCategories();
		return sortedCategories.indexOf(category);
	}

	/**
	 * Returns the sorted categories
	 * @return the sorted categories
	 */
	private List<Category> getSortedCategories() {
		Assert.isTrue(currentEObject instanceof Amount);
		Tracker tracker=TrackerUtils.getTracker(currentEObject);
		Set<Category> categories=new HashSet(TrackerUtils.getCategories(tracker));
		return getSortedList(categories, new CategoryComparator());
	}

	/**
	 * Returns items
	 * @return items
	 */
	private String[] getItems() {
		List<Category> categories=getSortedCategories();
		List<String> titles=new ArrayList<>();
		for (Category category: categories) {
			titles.add(category.getTitle());
		}
		titles.removeAll(Collections.singleton(null));
		return titles.stream().toArray(String[]::new);
	}
}
