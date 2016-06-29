package fr.rostren.tracker.ui.properties.content.comparators;

import java.util.Comparator;

import fr.rostren.tracker.Category;

public class CategoryComparator implements Comparator<Category> {

    @Override
    public int compare(Category arg1, Category arg2) {
	if (arg1 == null || arg2 == null || arg1.getTitle() == null)
	    return -1;
	if (arg2.getTitle() == null)
	    return 1;
	return arg1.getTitle().compareTo(arg2.getTitle());
    }
}
