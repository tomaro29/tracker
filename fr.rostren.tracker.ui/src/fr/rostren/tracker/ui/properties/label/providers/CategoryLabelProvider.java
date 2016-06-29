package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Category;

public class CategoryLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_TITLE="UNDEFINED TITLE"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_DESCRIPTION="UNDEFINED DESCRIPTION"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Category) {
			return getCategoryTitle((Category)element) + AbstractLabelProvider.STRING_SEPARATOR + getCategoryDescription((Category)element);
		}
		return super.getText(element);
	}

	private String getCategoryTitle(Category category) {
		return StringUtils.isEmpty(category.getTitle()) ? CategoryLabelProvider.STRING_UNDEFINED_TITLE : category.getTitle();
	}

	private String getCategoryDescription(Category category) {
		return StringUtils.isEmpty(category.getDescription()) ? CategoryLabelProvider.STRING_UNDEFINED_DESCRIPTION : category.getDescription();
	}
}
