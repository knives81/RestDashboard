package com.dashboard.restservicedashboard.info;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class Infos {
	private Map<String,String> messages = new HashMap<>();
	
	private static final String BACKEND_VERSION = "BACKEND_VERSION";
	private static final String SCHEDULER_VERSION = "SCHEDULER_VERSION";
	private static final String UPDATE_DATE = "UPDATE_DATE";
	private static final String MESSAGE = "MESSAGE";
	
	private static final String DATE_FORMAT_WITH_TIME = "dd-MM-yy HH.mm.ss";	

	public void putBackendVersion(String backedVersion) {
		messages.put(BACKEND_VERSION, "Backend version: "+backedVersion);
	}
	public void putSchedulerVersion(String schedulerVersion) {
		messages.put(SCHEDULER_VERSION, "Scheduler version: "+schedulerVersion);
	}

	public void putUpdateDate(String updateDate) {
		messages.put(UPDATE_DATE, "Update date: "+updateDate);
	}

	public void putMessage(String message) {
		messages.put(MESSAGE, message);
	}
	public List<Info> getMessage() {
		List<Info> infos = new ArrayList<>();
		
		Info info1 = new Info(messages.get(BACKEND_VERSION));
		infos.add(info1);

		Info info3 = new Info(messages.get(SCHEDULER_VERSION));
		infos.add(info3);

		Info info2 = new Info(messages.get(UPDATE_DATE));
		infos.add(info2);
		
		if(StringUtils.isNotBlank(messages.get(MESSAGE))) {
			Info info = new Info(messages.get(MESSAGE));
			infos.add(info);
		}		
		
		return infos;
	}
}
