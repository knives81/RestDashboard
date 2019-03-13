package com.dashboard.restservicedashboard.usagelog;

import com.dashboard.commondashboard.Chart;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class UsageLog {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(Chart.DATE_FORMAT);
	
	private final String username;
	private final String date;
	private final String serviceAsked;
	
	public UsageLog(String username, Date date, String serviceAsked) {	
		this(username,sdf.format(date),serviceAsked);
	}

}
