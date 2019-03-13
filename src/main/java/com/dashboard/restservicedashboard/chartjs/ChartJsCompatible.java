package com.dashboard.restservicedashboard.chartjs;

import com.dashboard.commondashboard.Chart;
import com.dashboard.restservicedashboard.chartcustominfo.CustomInfo;

import java.util.List;

public interface ChartJsCompatible {
	
	String getTitle();
	List<String> getLabels();
	List<Dataset> getDatasets();
	List<String> getColors();
	String getDataSetName();
	Chart.ChartType getChartType();
	CustomInfo getCustomInfo();

}
