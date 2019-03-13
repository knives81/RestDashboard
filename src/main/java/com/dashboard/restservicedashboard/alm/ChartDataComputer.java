package com.dashboard.restservicedashboard.alm;

import com.dashboard.commondashboard.DataRow;

import com.dashboard.restservicedashboard.domaincontroller.ChartMatrix;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ChartDataComputer {

	ChartMatrix transform(List<DataRow> dataRows) {

		List<String> recordDates = dataRows.stream().map(DataRow::getRecordDate).distinct().sorted().collect(Collectors.toList());
		List<String> statuses = dataRows.stream().map(DataRow::getStatus).distinct().collect(Collectors.toList());

		ChartMatrix chartMatrix = buildChartMatrix(dataRows, recordDates, statuses);
		return chartMatrix;

	}

	private ChartMatrix buildChartMatrix(List<DataRow> dataRows, List<String> recordDates, List<String> statuses) {
		ChartMatrix chartMatrix = new ChartMatrix(recordDates, statuses);

		for (String recordDate : recordDates) {
			for (String status : statuses) {
				Predicate<DataRow> predicate = d -> d.getRecordDate().equals(recordDate)
						&& d.getStatus().equals(status);
				int value = (int) dataRows.stream().filter(predicate).count();
				chartMatrix.setChartMatrixValue(recordDate, status, value);
			}
		}
		return chartMatrix;
	}

}
