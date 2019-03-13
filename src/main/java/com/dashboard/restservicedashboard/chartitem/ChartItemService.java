package com.dashboard.restservicedashboard.chartitem;

import com.dashboard.commondashboard.ChartItem;
import com.dashboard.commondashboard.ChartItemRepository;
import com.dashboard.commondashboard.ChartItemWithGroup;
import com.dashboard.restservicedashboard.alm.AlmDataService;
import com.dashboard.restservicedashboard.notification.NotificationManager;
import com.dashboard.restservicedashboard.usagelog.UsageLog;
import com.dashboard.restservicedashboard.usagelog.UsageLogRepository;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChartItemService {

    private static final Logger log = LoggerFactory.getLogger(ChartItemService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    @Setter
    private UsageLogRepository usageLogRepository;

    @Autowired
    @Setter
    private ChartItemRepository chartItemRepository;

    public List<ChartItem> getChartItems(String username, String userTag) {

        UsageLog usageLog = new UsageLog(username, new Date(), "ChartItemList");
        usageLogRepository.save(usageLog);

        ChartItemFilter chartItemFilter = new ChartItemFilter(chartItemRepository.findAll(),username,userTag);

        return chartItemFilter.filter();
    }

    public List<ChartItemWithGroup> getChartItemWithGroups(List<ChartItem> chartItems) {
        chartItems.sort(Comparator.comparing(ChartItem::getConfId));

        List<ChartItemWithGroup> chartItemWithGroups = new ArrayList<>();

        LinkedHashMap<String,List<ChartItem>> groupToChartItems = new LinkedHashMap<>();
        for(ChartItem chartItem : chartItems){
            String group = chartItem.getTags().get(0);
            if(groupToChartItems.get(group)==null) {
                List<ChartItem> chartItemsForGroup = new ArrayList<>();
                chartItemsForGroup.add(chartItem);
                groupToChartItems.put(group,chartItemsForGroup);
            } else {
                groupToChartItems.get(group).add(chartItem);
            }
        }

        groupToChartItems.forEach((k, v) -> chartItemWithGroups.add(new ChartItemWithGroup(k,v)));
        return chartItemWithGroups;
    }

    public void keepalive() {
        ChartItemFilter chartItemFilter = new ChartItemFilter(chartItemRepository.findAll(),"moinzi","");
        log.info("Keepalive {} {}", dateFormat.format(new Date()),chartItemFilter.filter().size());
    }
}
