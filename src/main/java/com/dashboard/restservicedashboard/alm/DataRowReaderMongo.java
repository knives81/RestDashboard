package com.dashboard.restservicedashboard.alm;

import com.dashboard.commondashboard.Chart;
import com.dashboard.commondashboard.DataRow;
import com.dashboard.commondashboard.DataRowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataRowReaderMongo implements DataRowReader {
	
	@Autowired
	DataRowRepository dataRowRepository;
	
	private static final Logger log = LoggerFactory.getLogger(DataRowReaderMongo.class);

	@Override
	public List<DataRow> read(String className, List<Integer> ids, Integer days) {
		
		String _class = className;
		String recordDate = subtractDaysFromToday(days);
		List<DataRow> dataRowsFromDb = dataRowRepository.findEntitiesWihtConfigurationIndex(_class, ids, recordDate);
		return dataRowsFromDb;
		
	}
	
	private String subtractDaysFromToday(int day) {
		SimpleDateFormat sdf = new SimpleDateFormat(Chart.DATE_FORMAT);
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, day * -1);
		return sdf.format(c.getTime());
	}

}
