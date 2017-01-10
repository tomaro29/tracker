/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.histogram;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesLabel;

public class Histogram {
	private static final String Y_AXIS_BUDGET="Budget"; //$NON-NLS-1$
	private static final String X_AXIS_DATE="Date"; //$NON-NLS-1$
	private static final String TITLE="Balance"; //$NON-NLS-1$
	private static final String INCOME="Income"; //$NON-NLS-1$
	private static final String SPENDING="Spending"; //$NON-NLS-1$

	private static final String LABEL_FORMAT="##.0"; //$NON-NLS-1$
	private static final Font LABEL_FONT=new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD); //$NON-NLS-1$

	private final IAxis xAxis;
	private final IAxis yAxis;
	private final Chart chart;

	/**
	 * Constructor
	 * @param parent the composite parent
	 */
	public Histogram(Composite parent) {
		chart=new Chart(parent, SWT.NONE);
		chart.setOrientation(SWT.HORIZONTAL);

		chart.getTitle().setText(Histogram.TITLE);
		chart.getLegend().setPosition(SWT.BOTTOM);

		IAxisSet axisSet=chart.getAxisSet();
		xAxis=axisSet.getXAxis(0);
		yAxis=axisSet.getYAxis(0);

		xAxis.getTitle().setText(Histogram.X_AXIS_DATE);
		yAxis.getTitle().setText(Histogram.Y_AXIS_BUDGET);
	}

	/**
	 * Populates the histogram
	 * @param titles the bars titles
	 * @param incomeSeriesValues the income series values
	 * @param spendingSeriesValues the spending series values
	 * @param enableStack <code>true</code> if the stack configuration is enabled, <code>false</code> otherwise.
	 */
	public void populateHistogram(String[] titles, double[] incomeSeriesValues, double[] spendingSeriesValues, boolean enableStack) {
		xAxis.setCategorySeries(titles);
		xAxis.enableCategory(true);

		//create income bar series
		IBarSeries incomeSeries=(IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, Histogram.INCOME);
		incomeSeries.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
		incomeSeries.setYSeries(incomeSeriesValues);

		//create spending bar series
		IBarSeries spendingSeries=(IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, Histogram.SPENDING);
		spendingSeries.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		spendingSeries.setYSeries(spendingSeriesValues);

		//Set Labels font, format and make visible
		ISeriesLabel incomeSeriesLabel=incomeSeries.getLabel();
		incomeSeriesLabel.setFormat(Histogram.LABEL_FORMAT);
		incomeSeriesLabel.setFont(Histogram.LABEL_FONT);
		incomeSeriesLabel.setVisible(true);

		ISeriesLabel spendingSeriesLabel=spendingSeries.getLabel();
		spendingSeriesLabel.setFormat(Histogram.LABEL_FORMAT);
		spendingSeriesLabel.setFont(Histogram.LABEL_FONT);
		spendingSeriesLabel.setVisible(true);

		// enable stack series
		incomeSeries.enableStack(enableStack);
		spendingSeries.enableStack(enableStack);

		// adjust the axis range
		chart.getAxisSet().adjustRange();
	}
}
