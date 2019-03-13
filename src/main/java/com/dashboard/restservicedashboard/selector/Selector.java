package com.dashboard.restservicedashboard.selector;

import com.dashboard.commondashboard.Chart;
import com.dashboard.commondashboard.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Selector {
	
	@Getter
    @Setter
    Entity.EntityType entityType;
	@Getter
    @Setter
    List<Option> items = new ArrayList<>();
	
	public Chart.ChartType identifyChartType() {
		for(Option item : items) {
			if(item.getTagName().equals(SelectorConstant.CHART_TYPE_LABEL)) {
				return Chart.ChartType.valueOf(item.getTagSelected());
			}			
		}
		throw new RuntimeException("No ChartJs Found");
	}
	
	

}
