package fr.rostren.tracker.histogram;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
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
	public void populateHistogram(List<String> titles, List<Double> incomeSeriesValues, List<Double> spendingSeriesValues, boolean enableStack) {
		xAxis.setCategorySeries(convertToStringArray(titles));
		xAxis.enableCategory(true);

		//create income bar series
		IBarSeries incomeSeries=(IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, Histogram.INCOME);
		incomeSeries.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
		double[] primitiveIncomeDoubleArray=convertToPrimitiveDoubleArray(incomeSeriesValues);
		if (primitiveIncomeDoubleArray != null) {
			incomeSeries.setYSeries(primitiveIncomeDoubleArray);
		}

		//create spending bar series
		IBarSeries spendingSeries=(IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, Histogram.SPENDING);
		spendingSeries.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		double[] primitiveSpendingDoubleArray=convertToPrimitiveDoubleArray(spendingSeriesValues);
		if (primitiveSpendingDoubleArray != null) {
			spendingSeries.setYSeries(primitiveSpendingDoubleArray);
		}

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

	/**
	 * Converts to primitive {@link Double} array
	 * @param list the given list
	 * @return the converted array
	 */
	private double[] convertToPrimitiveDoubleArray(List<Double> list) {
		return list == null ? null : ArrayUtils.toPrimitive(list.stream().toArray(Double[]::new));
	}

	/**
	 * Converts to {@link String} array
	 * @param list the given list
	 * @return the converted array
	 */
	private String[] convertToStringArray(List<String> list) {
		return list == null ? null : list.stream().toArray(String[]::new);
	}
}
