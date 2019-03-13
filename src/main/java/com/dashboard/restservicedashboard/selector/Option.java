package com.dashboard.restservicedashboard.selector;

import com.dashboard.commondashboard.Chart;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Builder
@NoArgsConstructor
public class Option {


	public Option(String tagName,List<String> tagValues,String tagSelected){
		this.tagName = tagName;
		this.tagValues = tagValues;
		this.tagSelected = tagSelected;
	}

	
	@Getter @Setter
	String tagName;
	@Getter @Setter
    List<String> tagValues;
	@Getter @Setter
    String tagSelected;
	
	public static Option buildOptionChartType() {
		List<String> chartTypes = Arrays.asList(Chart.ChartType.values())
		.stream()
		.map(Chart.ChartType::toString)
		.collect(Collectors.toList());
		return Option.builder().tagName(SelectorConstant.CHART_TYPE_LABEL).tagValues(chartTypes).tagSelected("").build();
	}
	public static Option buildOption(String tagName,List<String> tagValues) {
		List<String> listForTagValues = new ArrayList<>(tagValues);
		Collections.sort(listForTagValues);
		listForTagValues.add(SelectorConstant.ALL_LABEL);
		return Option.builder().tagName(tagName).tagValues(listForTagValues).tagSelected("").build();
	}
	
}