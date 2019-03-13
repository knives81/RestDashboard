package com.dashboard.restservicedashboard.domaincontroller;

import com.dashboard.commondashboard.Chart;
import com.dashboard.restservicedashboard.chartjs.ChartJsCompatible;
import com.dashboard.restservicedashboard.chartjs.Dataset;
import com.dashboard.restservicedashboard.chartcustominfo.CustomInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

@RequiredArgsConstructor
public class ChartData implements ChartJsCompatible {
	
	@Getter @Setter String title;
	@Getter @Setter	CustomInfo customInfo;
	@Getter
    private final List<String> labels;
	@Getter
    private final LinkedHashMap<String, List<Integer>> data;
	@Getter
    private final List<String> colors;
	@Getter
    private final Chart.ChartType chartType;


	
	@Override
	public String toString() {
		return  "Title:"+title+"\r\n"+
				"Labels:"+labels+"\r\n"+
				"Data:"+data+"\r\n"+
				"Colors:"+colors;
	}

	@JsonIgnore
	@Override
	public List<Dataset> getDatasets() {
		List<Dataset> datasets = new ArrayList<>();
		for (Entry<String, List<Integer>> entry : data.entrySet()) {
			Dataset dataset = new Dataset();
			dataset.setFill(false);
			dataset.setLabel(entry.getKey());
			dataset.setData(entry.getValue());
			datasets.add(dataset);	
		}		
		return datasets;
	}

	@JsonIgnore
	@Override
	public String getDataSetName() {
		for (Entry<String, List<Integer>> entry : data.entrySet()) {		    
		    return entry.getKey();	    
		}
		throw new RuntimeException("Dataset empty");
		
	}

}
