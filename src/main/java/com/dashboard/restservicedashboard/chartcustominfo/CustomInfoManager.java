package com.dashboard.restservicedashboard.chartcustominfo;

import com.dashboard.commondashboard.*;
import com.dashboard.restservicedashboard.alm.DataRowReaderMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CustomInfoManager {

    @Autowired
    PianificationRepository pianificationRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomInfoManager.class);

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public CustomInfo computeCustomInfo(List<Integer> ids, List<DataRow> dataRows, Entity.EntityType entityType) {

        if(entityType.equals(Entity.EntityType.DEFECT)) {
            return new CustomInfo();
        }
        List<Pianification> pianifications = pianificationRepository.findByTestSetConfIdIn(ids);
        if(pianifications.size()!=ids.size() || pianifications.isEmpty()) {
            return new CustomInfo();
        }

        String today = SDF.format(new Date());
        List<Pianification.TestPerDay> pianificationTestPerDays = new ArrayList<>();
        for(Pianification pianification : pianifications) {
            pianificationTestPerDays.addAll(pianification.getTestPerDays());
        }

        Predicate<Pianification.TestPerDay> predicate = d -> d.getDay().equals(today);
        int numOfTestInPianificationToday = pianificationTestPerDays.stream()
                .filter(predicate)
                .mapToInt(Pianification.TestPerDay::getNumOfTest)
                .sum();


        Predicate<DataRow> predicateDataRow = d ->
                d.getRecordDate().equals(today)
                && (d.getStatus().equals(AlmTags.PASSED) ||
                   d.getStatus().equals(AlmTags.PASSED_WITH_DEFECT) ||
                   d.getStatus().equals(AlmTags.NOT_AVAILABLE));

        int numOfCompletedTest = (int) dataRows.stream().filter(predicateDataRow).count();

        Integer delta = numOfCompletedTest - numOfTestInPianificationToday;
        log.info("numOfCompletedTest:"+numOfCompletedTest+" numOfTestInPianificationToday:"+numOfTestInPianificationToday);

        if(delta==0) {
            return new CustomInfo(delta.toString(),ColorMapper.GREY);
        } else if(delta<0) {
            return new CustomInfo(delta.toString(),ColorMapper.RED);
        } else{
            return new CustomInfo("+"+delta.toString(),ColorMapper.GREEN);
        }
    }

}
