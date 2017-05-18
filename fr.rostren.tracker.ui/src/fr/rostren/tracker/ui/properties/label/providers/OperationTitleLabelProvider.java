/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.provider.OperationTitleItemProvider;

public class OperationTitleLabelProvider extends AbstractLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof OperationTitle) {
			return StringUtils.isEmpty(((OperationTitle)element).getTitle()) ? OperationTitleItemProvider.STRING_UNDEFINED_OPERATION_TITLE : ((OperationTitle)element).getTitle();
		}
		return super.getText(element);
	}
}
