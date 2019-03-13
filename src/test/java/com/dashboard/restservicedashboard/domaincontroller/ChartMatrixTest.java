package com.dashboard.restservicedashboard.domaincontroller;

import com.dashboard.commondashboard.AlmTags;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChartMatrixTest {
	
	
	
	private static ChartMatrix chartMatrix;
	private static final String chartMatrixOutput =
			"┌───────────────┬───────────────┬───────────────┬───────────────┬──────────────┐\r\n" +
			"│               │Passed         │Passed     With│Blocked        │Failed        │\r\n" +
			"│               │               │Defect         │               │              │\r\n" +
			"├───────────────┼───────────────┼───────────────┼───────────────┼──────────────┤\r\n" +
			"│2018-01-01     │0              │1              │2              │3             │\r\n" +
			"│2018-01-02     │4              │5              │6              │7             │\r\n" +
			"│2018-01-03     │8              │9              │10             │11            │\r\n" +
			"└───────────────┴───────────────┴───────────────┴───────────────┴──────────────┘";

	private static final String chartMatrixMergedOutput =
			"┌───────────────────┬───────────────────┬───────────────────┬──────────────────┐\r\n" +
			"│                   │Blocked            │Failed             │PASSED2           │\r\n" +
			"├───────────────────┼───────────────────┼───────────────────┼──────────────────┤\r\n" +
			"│2018-01-01         │2                  │3                  │1                 │\r\n" +
			"│2018-01-02         │6                  │7                  │9                 │\r\n" +
			"│2018-01-03         │10                 │11                 │17                │\r\n" +
			"└───────────────────┴───────────────────┴───────────────────┴──────────────────┘";


	@BeforeClass
	public static void build() {
		chartMatrix = buildChartMatrix();
	}


	@Test
	public void columnShouldHaveCorrectValues() {
		List<Integer> columnValues = Arrays.asList(1,5,9);
		assertEquals(columnValues, chartMatrix.getColumnValues(AlmTags.PASSED_WITH_DEFECT));
	}

	@Test
	public void rowShouldHaveCorrectValues() {
		List<Integer> rowValues = Arrays.asList(4,5,6,7);
		assertEquals(rowValues, chartMatrix.getRowValues("2018-01-02"));
	}

	@Test
	public void columnHeaderShouldBeCorrect() {
		List<String> columnHeaders = Arrays.asList(AlmTags.PASSED,AlmTags.PASSED_WITH_DEFECT,AlmTags.BLOCKED,AlmTags.FAILED);
		assertEquals(columnHeaders, chartMatrix.getColumnHeaders());
	}

	@Test
	public void rowHeaderShouldBeCorrect() {
		List<String> rowHeaders = Arrays.asList("2018-01-01","2018-01-02","2018-01-03");
		assertEquals(rowHeaders, chartMatrix.getRowHeaders());
	}

	@Test
	public void chartMatrixShouldHaveColumnMerged() {
		String newColumnHeader = "PASSED2";
		ChartMatrix chartMatrixWithColumnMerged = chartMatrix.mergeTwoColumns(AlmTags.PASSED,AlmTags.PASSED_WITH_DEFECT, newColumnHeader);

		assertEquals(Arrays.asList(1,9,17),chartMatrixWithColumnMerged.getColumnValues(newColumnHeader));

	}
	@Test
	public void pieChartDataShouldBeBuilt() {
		List<String> labels = Arrays.asList(AlmTags.PASSED,AlmTags.PASSED_WITH_DEFECT,AlmTags.BLOCKED,AlmTags.FAILED);
		List<String> colors = Arrays.asList("#00B050", "#92D050", "#FC041C", "#C00000");
		List<Integer> values = Arrays.asList(4,5,6,7);

		ChartData chartData = chartMatrix.computePieChart("2018-01-02");

		//assertEquals(values, chartData.getDatasets().get(ChartJs.PIE_CHART_DATASET_NAME));
		assertEquals(labels, chartData.getLabels());
		assertEquals(colors, chartData.getColors());
	}

	@Test
	public void lineChartDataShouldBeBuilt() {
		List<String> labels = Arrays.asList("2018-01-01","2018-01-02","2018-01-03");
		List<String> colors = Arrays.asList("#00B050", "#92D050", "#FC041C", "#C00000");
		List<Integer> passedValues = Arrays.asList(0,4,8);
		List<Integer> passedWithDefectValues = Arrays.asList(1,5,9);
		List<Integer> blockedValues = Arrays.asList(2,6,10);

		ChartData chartData = chartMatrix.computeLineChart();

		assertEquals(labels, chartData.getLabels());
		assertEquals(colors, chartData.getColors());
		//assertEquals(passedValues, chartData.getDatasets().get(AlmTags.PASSED));
		//assertEquals(passedWithDefectValues, chartData.getDatasets().get(AlmTags.PASSED_WITH_DEFECT));
		//assertEquals(blockedValues, chartData.getDatasets().get(AlmTags.BLOCKED));

	}



	private static ChartMatrix buildChartMatrix() {
		List<String> rows = new ArrayList<>();
		rows.add("2018-01-01");
		rows.add("2018-01-02");
		rows.add("2018-01-03");

		List<String> columns = new ArrayList<>();
		columns.add(AlmTags.PASSED);
		columns.add(AlmTags.PASSED_WITH_DEFECT);
		columns.add(AlmTags.BLOCKED);
		columns.add(AlmTags.FAILED);

		ChartMatrix chartMatrix = new ChartMatrix(rows, columns);

		int value=0;
		for(String rowHeader : chartMatrix.getRowHeaders()) {
			for(String columnHeader : chartMatrix.getColumnHeaders()) {
				chartMatrix.setChartMatrixValue(rowHeader, columnHeader, value++);
			}
		}
		return chartMatrix;

	}

}
