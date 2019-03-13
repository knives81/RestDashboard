package com.dashboard.restservicedashboard.selector;

import com.dashboard.commondashboard.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigurationService {
	
	@Autowired
	private DefectConfRepository defectConfRepository;
	
	@Autowired
	private TestsetConfRepository testsetConfRepository;
	
	
	private TestsetConf getTestsetConf() {
		List<TestsetConf> testsetConfs = testsetConfRepository.findAll();
		if(testsetConfs.isEmpty()) {
			return null;
		} else {
			return testsetConfs.get(0);
		}
	}
	private DefectConf getDefectConf() {
		List<DefectConf> defectConfs = defectConfRepository.findAll();
		if(defectConfs.isEmpty()) {
			return null;
		} else {
			return defectConfs.get(0);
		}
	}

	
	public EntityConfAbstract getEntityConf(Entity.EntityType entityType) {
		if(entityType.equals(Entity.EntityType.DEFECT)) {
			return getDefectConf();
		} else if(entityType.equals(Entity.EntityType.TESTSET)) {
			return getTestsetConf();
		}
		throw new RuntimeException("No Entity Found");
	}

}
