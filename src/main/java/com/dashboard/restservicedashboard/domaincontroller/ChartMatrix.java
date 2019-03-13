package com.dashboard.restservicedashboard.domaincontroller;

import com.dashboard.commondashboard.Chart;
import com.dashboard.commondashboard.ColorMapper;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ChartMatrix {

	public final static String PIE_CHART_DATASET_NAME = "Status Piechart";

	private final LinkedHashMap<String, Integer> rowHeaders = new LinkedHashMap<>();
	private final LinkedHashMap<String, Integer> columnHeaders = new LinkedHashMap<>();

	private final int[][] chartValues;

	public ChartMatrix(List<String> rows, List<String> columns) {
		chartValues = new int[rows.size()][columns.size()];

		int rowIndex = 0;
		for (String row : rows) {
			rowHeaders.put(row, rowIndex++);
		}

		int columnIndex = 0;
		for (String column : columns) {
			columnHeaders.put(column, columnIndex++);
		}

	}

	public void setChartMatrixValue(String rowHeader, String columnHeader, Integer value) {
		chartValues[rowHeaders.get(rowHeader)][columnHeaders.get(columnHeader)] = value;
	}

	public int getChartMatrixValue(String rowHeader, String columnHeader) {
		return chartValues[rowHeaders.get(rowHeader)][columnHeaders.get(columnHeader)];
	}

	public List<Integer> getColumnValues(String columnHeader) {
		List<Integer> columnValues = new ArrayList<>();
		Integer columnIndex = columnHeaders.get(columnHeader);
		if (columnIndex != null) {
			for (int rowIndex = 0; rowIndex < rowHeaders.size(); rowIndex++) {
				columnValues.add(chartValues[rowIndex][columnIndex]);
			}
		}
		return columnValues;
	}

	public List<Integer> getRowValues(String rowHeader) {
		List<Integer> rowValues = new ArrayList<>();
		Integer rowIndex = rowHeaders.get(rowHeader);
		if (rowIndex != null) {
			for (int columnIndex = 0; columnIndex < columnHeaders.size(); columnIndex++) {
				rowValues.add(chartValues[rowIndex][columnIndex]);
			}
		}
		return rowValues;
	}

	public List<String> getRowHeaders() {
		return new ArrayList<String>(rowHeaders.keySet());
	}

	public List<String> getColumnHeaders() {
		return new ArrayList<String>(columnHeaders.keySet());
	}

	

	public ChartMatrix mergeTwoColumns(String firstColumnKey, String secondColumnKey, String newColumnKey) {

		List<Integer> firstColumnValues = this.getColumnValues(firstColumnKey);
		List<Integer> secondColumnValues = this.getColumnValues(secondColumnKey);
		
		if(firstColumnValues.isEmpty() || secondColumnValues.isEmpty()) {
			return this;
		}
		
		List<String> rowHeaders = this.getRowHeaders();
		List<String> newColumnHeaders = removeTwoColumnsAndAddOneColumn(firstColumnKey, secondColumnKey, newColumnKey);
		ChartMatrix newChartMatrix = new ChartMatrix(rowHeaders, newColumnHeaders);

		List<Integer> newColumnValues = sumTwoColumns(firstColumnValues, secondColumnValues);

		for (String columnHeader : newChartMatrix.getColumnHeaders()) {
			if (columnHeader.equals(newColumnKey)) {
				newChartMatrix.overwriteColumnValues(columnHeader, newColumnValues);
			} else {
				newChartMatrix.overwriteColumnValues(columnHeader, this.getColumnValues(columnHeader));
			}
		}
		return newChartMatrix;
	}

	private List<String> removeTwoColumnsAndAddOneColumn(String keyFirstColumn, String keySecondColumn,
			String newColumnHeader) {
		List<String> newColumnHeaders = this.getColumnHeaders();
		newColumnHeaders.remove(keyFirstColumn);
		newColumnHeaders.remove(keySecondColumn);
		newColumnHeaders.add(newColumnHeader);
		return newColumnHeaders;
	}

	private List<Integer> sumTwoColumns(List<Integer> firstColumnValues, List<Integer> secondColumnValues) {
		List<Integer> newColumnValues = new ArrayList<>();
		for (int i = 0; i < firstColumnValues.size(); i++) {
			newColumnValues.add(firstColumnValues.get(i) + secondColumnValues.get(i));
		}
		return newColumnValues;
	}
	
	private void overwriteColumnValues(String columnHeader, List<Integer> values) {
		if (values.size() != rowHeaders.size()) {
			throw new RuntimeException("Values more/less than rows");
		}
		Integer columnIndex = columnHeaders.get(columnHeader);
		for (int rowIndex = 0; rowIndex < rowHeaders.size(); rowIndex++) {
			chartValues[rowIndex][columnIndex] = values.get(rowIndex);
		}

	}

	public ChartData computePieChart(String date) {
		LinkedHashMap<String, List<Integer>> datasets = new LinkedHashMap<>();
		String chartName = PIE_CHART_DATASET_NAME;
		datasets.put(chartName, this.getRowValues(date));
		List<String> labels = this.getColumnHeaders();
		List<String> colors = ColorMapper.getColorsFromKey(labels);
		return new ChartData(labels, datasets, colors, Chart.ChartType.PIECHART);
	}

	public ChartData computeLineChart() {

		LinkedHashMap<String, List<Integer>> datasets = new LinkedHashMap<>();
		List<String> columnHeaders = this.getColumnHeaders();
		for (String columnHeader : columnHeaders) {
			datasets.put(columnHeader, this.getColumnValues(columnHeader));
		}
		List<String> labels = this.getRowHeaders();
		List<String> colors = ColorMapper.getColorsFromKey(columnHeaders);

		return new ChartData(labels, datasets, colors, Chart.ChartType.LINECHART);
	}

	@Override
	public String toString() {
		AsciiTable at = new AsciiTable();

		ArrayList<String> columnHeader = new ArrayList<>();
		columnHeader.add("");
		columnHeader.addAll(getColumnHeaders());

		at.addRule();
		at.addRow(columnHeader);
		at.addRule();
		for (int i = 0; i < getRowHeaders().size(); i++) {
			ArrayList<String> row = new ArrayList<>();
			row.add(getRowHeaders().get(i));
			for (int j = 0; j < getColumnHeaders().size(); j++) {
				row.add(Integer.valueOf(chartValues[i][j]).toString());
			}
			at.addRow(row);

		}
		at.addRule();
		return at.render();
	}

}
