package com.dashboard.restservicedashboard.runcommand;


import com.dashboard.restservicedashboard.configuration.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ImportSchedulerDb {

    @Autowired
    private AppProperties appProp;

    @Autowired
    private ImportService importService;

    @Scheduled(fixedRateString = "${app.scheduled-rate-in-ms}")
    public void importDataInDb() {
        if(appProp.getIsSyncFolderActivated()) {
            importService.runImport();
        }


    }

}
