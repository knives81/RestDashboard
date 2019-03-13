package com.dashboard.restservicedashboard.info;

import com.dashboard.commondashboard.SchedulerInfo;
import com.dashboard.commondashboard.SchedulerInfoRepository;

import com.dashboard.restservicedashboard.configuration.AppProperties;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoController {
	
	@Autowired
	private AppProperties appProp;
	
	@Autowired
	private Infos infos;

	@Autowired
	private SchedulerInfoRepository schedulerInfoRepository;
	
	private static final Logger log = LoggerFactory.getLogger(InfoController.class);
	
    @CrossOrigin
   	@ApiOperation(value = "info", nickname = "info")
       @RequestMapping(method = RequestMethod.GET, path="/infoapp", produces = "application/json")
   	public List<Info> infoApp() {

		SchedulerInfo schedulerInfo =  schedulerInfoRepository.findAll().get(0);
		infos.putSchedulerVersion(schedulerInfo.getSchedulerVersion());
    	infos.putBackendVersion(appProp.getRestserviceDashboardVersion());
    	infos.putUpdateDate(schedulerInfo.getWhenUpdated());
    	return infos.getMessage();
   	}
    
    @CrossOrigin
	@ApiOperation(value = "chekcConf", nickname = "checkConf")
    @RequestMapping(method = RequestMethod.GET, path="/checkconf", produces = "application/json")
	public ResponseEntity chekcConf() {
    	return new ResponseEntity(HttpStatus.OK);

	}
    
	
	
	
}
