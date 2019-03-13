package com.dashboard.restservicedashboard.alm;

import com.dashboard.commondashboard.DataRow;

import java.util.List;

public interface DataRowReader {

	List<DataRow> read(String className, List<Integer> ids, Integer days);
	

}
