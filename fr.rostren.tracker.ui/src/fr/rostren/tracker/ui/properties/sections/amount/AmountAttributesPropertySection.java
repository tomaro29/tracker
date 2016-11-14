package fr.rostren.tracker.ui.properties.sections.amount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Sets;

import fr.rostren.tracker.Amount;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.comparators.CategoryComparator;
import fr.rostren.tracker.ui.properties.listeners.AmountAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class AmountAttributesPropertySection extends AbstractAttributesPropertySection {
	protected Text valueText;
	protected CCombo categoryCombo;

	private final ModifyListener listener = new AmountAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		valueText = createLabeledText(body, null, "Value:"); //$NON-NLS-1$
		categoryCombo = createLabeledCombo(body, valueText, "Category:"); //$NON-NLS-1$

		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		valueText.setText(getAmountvalue());
		String[] items = getItems();
		categoryCombo.setItems(items);
		setComboSelection(items);
		addListeners();
	}

	private void setComboSelection(String[] items) {
		Category category = getAmountCategoryItem();
		if (category != null) {
			String title = category.getTitle();
			categoryCombo.setItem(Arrays.asList(items).indexOf(title), title);
		}
	}

	private String getAmountvalue() {
		Assert.isTrue(currentEObject instanceof Amount);
		BigDecimal value = ((Amount) currentEObject).getValue();
		if (value == null) {
			return StringUtils.EMPTY;
		}
		return String.valueOf(value);
	}

	@Override
	protected void addListeners() {
		valueText.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		valueText.removeModifyListener(listener);
	}

	public Text getValueText() {
		return valueText;
	}

	public CCombo getCategoryCombo() {
		return categoryCombo;
	}

	private Category getAmountCategoryItem() {
		Assert.isTrue(currentEObject instanceof Amount);
		return ((Amount) currentEObject).getCategory();
	}

	private int getAmountCategoryItemIndex(Category category) {
		List<Category> sortedCategories = getSortedCategories();
		return sortedCategories.indexOf(category);
	}

	private List<Category> getSortedCategories() {
		Assert.isTrue(currentEObject instanceof Amount);
		Tracker tracker = TrackerUtils.getTracker(currentEObject);
		Set<Category> categories = Sets.newHashSet(tracker.getCategoriesRepository().getCategories());
		return getSortedList(categories, new CategoryComparator());
	}

	private String[] getItems() {
		List<Category> categories = getSortedCategories();
		List<String> titles = new ArrayList<>();
		for (Category category : categories) {
			titles.add(category.getTitle());
		}
		titles.removeAll(Collections.singleton(null));
		return titles.toArray(new String[0]);
	}
}
