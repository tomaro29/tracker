/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.histogram;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;

public class Histogram {

	/**
	 * Constructor
	 * @param parent the composite parent
	 * @param title the histogram title
	 * @param xAxisTitle the x axis title
	 * @param yAxisTitle the y axis title
	 */
	public Histogram(Composite parent, String title, String xAxisTitle, String yAxisTitle) {
		Chart chart=new Chart(parent, SWT.NONE);
		chart.setOrientation(SWT.HORIZONTAL);

		chart.getTitle().setText(title);

		IAxisSet axisSet=chart.getAxisSet();
		IAxis xAxis=axisSet.getXAxis(0);
		IAxis yAxis=axisSet.getYAxis(0);

		xAxis.getTitle().setText(xAxisTitle);
		yAxis.getTitle().setText(yAxisTitle);

		xAxis.setCategorySeries(new String[] {"Linux", "Windows"});
		xAxis.enableCategory(true);

		IBarSeries series=(IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, "line series");
		series.setBarColor(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
		double[] values= {0.7, 0.2};
		series.setYSeries(values);
	}
}
