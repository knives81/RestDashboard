package com.dashboard.restservicedashboard.alm;

import com.dashboard.commondashboard.ChartItem;
import com.dashboard.commondashboard.ChartItemWithGroup;
import com.dashboard.restservicedashboard.chartitem.ChartItemService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ChartItemServiceTest {

    @Test
    public void getChartItemWithGroup() {
        ChartItemService aut = new ChartItemService();

        ChartItem chartItem1 = new ChartItem();
        chartItem1.setConfId(0);
        chartItem1.setTags(Arrays.asList("Tag1", "Tag2"));

        ChartItem chartItem2 = new ChartItem();
        chartItem2.setConfId(1);
        chartItem2.setTags(Arrays.asList("Tag1", "Tag3"));

        ChartItem chartItem3 = new ChartItem();
        chartItem3.setConfId(2);
        chartItem3.setTags(Arrays.asList("Tag2", "Tag3"));

        List<ChartItem> chartItemList = Arrays.asList(chartItem1,chartItem2,chartItem3);

        List<ChartItemWithGroup> chartItemWithGroups = aut.getChartItemWithGroups(chartItemList);

        Assert.assertEquals("Tag1",chartItemWithGroups.get(0).getGroup());
        Assert.assertEquals(Integer.valueOf(0),chartItemWithGroups.get(0).getChartItems().get(0).getConfId());
        Assert.assertEquals(Integer.valueOf(1),chartItemWithGroups.get(0).getChartItems().get(1).getConfId());
        Assert.assertEquals("Tag2",chartItemWithGroups.get(1).getGroup());
        Assert.assertEquals(Integer.valueOf(2),chartItemWithGroups.get(1).getChartItems().get(0).getConfId());






    }
}
