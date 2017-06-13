/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.properties.sections;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractAttributesPropertySection extends AbstractTrackerPropertySection {

	/**
	 * Creates a label text
	 * @param composite the composite parent of the label to create
	 * @param control the control
	 * @param label the label
	 * @return the created {@link Text}
	 */
	protected Text createLabeledText(Composite composite, Control control, String label) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		Text attributeText=widgetFactory.createText(composite, StringUtils.EMPTY);

		FormData data=new FormData();
		data.left=new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH + 15);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, 0);
		attributeText.setLayoutData(data);

		CLabel attributeLabel=widgetFactory.createCLabel(composite, label);
		data=new FormData();
		data.left=new FormAttachment(0, 0);
		data.right=new FormAttachment(attributeText, -ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(attributeText, 0, SWT.CENTER);
		attributeLabel.setLayoutData(data);
		return attributeText;
	}

	/**
	 * Creates a date time zone
	 * @param composite the composite parent of the label to create
	 * @param control the control
	 * @param label the label
	 * @return the created {@link Text}
	 */
	protected DateTime createDateTime(Composite composite, Control control, String label) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		DateTime dateTime=new DateTime(composite, SWT.DATE | SWT.MEDIUM | SWT.DROP_DOWN);

		TCHAR lpszFormat=new TCHAR(0, "dd/MM/yyyy", true);
		OS.SendMessage(dateTime.handle, OS.DTM_SETFORMAT, 0, lpszFormat);

		FormData data=new FormData();
		data.left=new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH + 15);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, 0);
		dateTime.setLayoutData(data);

		CLabel dateTimeLabel=widgetFactory.createCLabel(composite, label);
		data=new FormData();
		data.left=new FormAttachment(0, 0);
		data.right=new FormAttachment(dateTime, -ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(dateTime, 0, SWT.CENTER);
		dateTimeLabel.setLayoutData(data);
		return dateTime;
	}

	/**
	 * Creates a labeled combo
	 * @param composite the composite parent of the combo to create
	 * @param control the control
	 * @param label the label
	 * @return the created {@link CCombo}
	 */
	protected CCombo createLabeledCombo(Composite composite, Control control, String label) {
		TabbedPropertySheetWidgetFactory widgetFactory=getWidgetFactory();
		CCombo refCombo=widgetFactory.createCCombo(composite);

		FormData data=new FormData();
		data.left=new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH + 15);
		if (control == null) {
			data.top=new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		}
		else {
			data.top=new FormAttachment(control, ITabbedPropertyConstants.VSPACE);
		}
		data.right=new FormAttachment(100, 0);
		refCombo.setLayoutData(data);

		CLabel comboLabel=widgetFactory.createCLabel(composite, label);
		data=new FormData();
		data.left=new FormAttachment(0, 0);
		data.right=new FormAttachment(refCombo, -ITabbedPropertyConstants.HSPACE);
		data.top=new FormAttachment(refCombo, 0, SWT.CENTER);
		comboLabel.setLayoutData(data);
		return refCombo;
	}

	/**
	 * Returns the Operation wished date year
	 * @param date the operation date
	 * @return the Operation wished date year
	 */
	protected int getYear(LocalDate date) {
		return date == null ? 0 : date.getYear();
	}

	/**
	 * Returns the Operation wished date month
	 * @param date the operation date
	 * @return the Operation wished date month
	 */
	protected int getMonth(LocalDate date) {
		return date == null ? 0 : date.getMonth().getValue() - 1;
	}

	/**
	 * Returns the Operation wished date day
	 * @param date the operation date
	 * @return the Operation wished date day
	 */
	protected int getDay(LocalDate date) {
		return date == null ? 0 : date.getDayOfMonth();
	}
}
