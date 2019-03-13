package com.dashboard.restservicedashboard.chartitem;


import com.dashboard.commondashboard.ChartItem;
import com.dashboard.commondashboard.ChartItemWithGroup;
import com.dashboard.restservicedashboard.utils.Util;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChartItemController {

    @Autowired
    public ChartItemService chartItemService;

    private static final Logger log = LoggerFactory.getLogger(ChartItemController.class);

    @CrossOrigin
    @ApiOperation(value = "getChartItem", nickname = "getChartItem")
    @RequestMapping(method = RequestMethod.GET, path = "/chartitem", produces = "application/json")
    public List<ChartItem> getChartItem(@RequestParam(required = false, value = "filter") String tag) {
        String username = Util.getUserName();
        return chartItemService.getChartItems(username,tag);
    }

    @CrossOrigin
    @ApiOperation(value = "getChartItemGroup", nickname = "getChartItemGroup")
    @RequestMapping(method = RequestMethod.GET, path = "/chartitemgroup", produces = "application/json")
    public List<ChartItemWithGroup> getChartItemGroup(@RequestParam(required = false, value = "filter") String tag) {
        String username = Util.getUserName();
        List<ChartItem> chartItems = chartItemService.getChartItems(username,tag);

        List<ChartItemWithGroup> chartItemWithGroups = chartItemService.getChartItemWithGroups(chartItems);
        return chartItemWithGroups;
    }

    @Scheduled(fixedRateString = "60000")
    public void keepalive() {
        //chartItemService.keepalive();

    }


}
