package fr.rostren.tracker.ui.properties.label.providers;

import fr.rostren.tracker.Category;

public class CategoryLabelProvider extends AbstractLabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof Category)
	    return ((Category) element).getTitle();
	return super.getText(element);
    }
}
