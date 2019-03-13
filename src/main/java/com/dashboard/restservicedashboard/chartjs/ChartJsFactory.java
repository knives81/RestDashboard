package com.dashboard.restservicedashboard.chartjs;

import com.dashboard.commondashboard.Chart;
import com.dashboard.restservicedashboard.chartcustominfo.CustomInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartJsFactory {
	
	private static final String PIE = "pie";
	private static final String BAR = "bar";
	private static final String LINE = "line";
	private static final String DOUGHNUT = "doughnut";
	
	private static final String LEGEND_POSITION = "bottom";
	private static final String CHART_DATASET = "Dataset1";


public static ChartJs getChart(ChartJsCompatible chartable) {
	Chart.ChartType chartType =  chartable.getChartType();
	if (chartType.equals(Chart.ChartType.PIECHART)) {
		return ChartJsFactory.getPieChart(chartable);
	} else if (chartType.equals(Chart.ChartType.LINECHART)) {
		return ChartJsFactory.getLineChart(chartable);
	} else {
		throw new RuntimeException("Type of chart not supported");
	}
}
	
private static ChartJs getLineChart(ChartJsCompatible chartable) {
		
		Data data = new Data();
		data.setLabels(chartable.getLabels());
		
		List<Dataset> datasets = chartable.getDatasets();
		for(Dataset dataset : datasets) {
			dataset.setPointRadius(8);
		}
		data.setDatasets(datasets);
		
		ChartJs chartJs = new ChartJs();
		chartJs.setType(LINE);
		chartJs.setColors(getListOfBackgroundColors2(chartable.getColors()));
		chartJs.setData(data);

	    Options options = buildOption(chartable.getTitle(),new CustomInfo("",""));
		chartJs.setOptions(options);
		
		return chartJs;
		
		
		
	}
	
	private static ChartJs getPieChart(ChartJsCompatible chartable) {
		
		ChartJs chartJs = new ChartJs();
		chartJs.setType(DOUGHNUT);
		Data data = new Data();
		
				
		chartJs.setColors(getListOfBackgroundColors(chartable.getColors()));
		data.setLabels(chartable.getLabels());		
		data.setDatasets(chartable.getDatasets());		
		chartJs.setData(data);

		Options options = buildOption(chartable.getTitle(),chartable.getCustomInfo());
		chartJs.setOptions(options);
		
		return chartJs;
		
	}

	private static Options buildOption(String textTitle, CustomInfo customInfo) {
		Options options = new Options();
		
		Legend legend = new Legend();
		legend.setPosition(LEGEND_POSITION);
		legend.setDisplay(true);
		options.setLegend(legend);
		
		Tooltips tooltips = new Tooltips();
		tooltips.setEnabled(true);
		options.setTooltips(tooltips);
		
		options.setShowLabel(true);
		
		options.setResponsive(true);
		
		Animation animation = new Animation();
		animation.setAnimateRotate(true);
		animation.setAnimateScale(true);
		options.setAnimation(animation);
		
		options.setMaintainAspectRatio(false);
		
		
		Title title = new Title();
		title.setDisplay(true);
		title.setText(textTitle);
		options.setTitle(title);
		options.setCustomInfo(customInfo);
		
		return options;
	}
	private static List<Color> getListOfBackgroundColors(List<String> colors) {		
		Color newColor = new Color().withBackgroundColor(colors).withBorderColor(colors);		
		return Collections.singletonList(newColor);
	}
	private static List<Color> getListOfBackgroundColors2(List<String> colors) {	
		List<Color> borderColors = new ArrayList<>();
		for(String color: colors) {
			borderColors.add(new Color().withBorderColor(Collections.singletonList(color)).withBackgroundColor(Collections.singletonList(color)));
		}
				
		return borderColors;
	}

}
