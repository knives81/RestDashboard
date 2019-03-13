package com.dashboard.restservicedashboard.alm;

import com.dashboard.commondashboard.ChartItem;
import com.dashboard.restservicedashboard.chartitem.ChartItemFilter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChartItemFilterTest {


    @Test
    public void getOnlyVisibleChartItems() {

        ChartItemFilter aut = new ChartItemFilter(getChartItems(),"mpinzi", "");
        List<ChartItem> chartItems = aut.filter();
        assertEquals(3, chartItems.size());

    }


    @Test
    public void getChartItemsBasedOnUsername() {

        ChartItemFilter aut = new ChartItemFilter(getChartItems(),"pino", "");
        List<ChartItem> chartItems = aut.filter();
        assertEquals(2, chartItems.size());

    }

    @Test
    public void getChartItemsBasedOnTags() {
        ChartItemFilter aut = new ChartItemFilter(getChartItems(),"mpinzi", "BB");
        List<ChartItem> chartItems = aut.filter();

        assertEquals(2, chartItems.size());

    }

    @Test
    public void getChartItemsBasedOnLowerCaseTags() {
        ChartItemFilter aut = new ChartItemFilter(getChartItems(),"mpinzi", "aa");
        List<ChartItem> chartItems = aut.filter();

        assertEquals(1, chartItems.size());

    }

    private List<ChartItem> getChartItems() {


        ChartItem item1 = new ChartItem();
        item1.setChartType("PIECHART");
        item1.setDesc("Desc 1");
        item1.setEntityType("DEFECT");
        item1.setIds(Arrays.asList(1,2,3));
        item1.setConfId(0);
        item1.setIsVisible(true);
        item1.setTags(Arrays.asList("AAA"));
        item1.setUsernames(Arrays.asList("pino","paperino"));

        ChartItem item2 = new ChartItem();
        item2.setChartType("PIECHART");
        item2.setDesc("Desc 1");
        item2.setEntityType("DEFECT");
        item2.setIds(Arrays.asList(1,2,3));
        item2.setConfId(0);
        item2.setIsVisible(true);
        item2.setTags(Arrays.asList("BBB"));
        item2.setUsernames(Arrays.asList("pino","paperino"));

        ChartItem item3 = new ChartItem();
        item3.setChartType("PIECHART");
        item3.setDesc("Desc 1");
        item3.setEntityType("DEFECT");
        item3.setIds(Arrays.asList(1,2,3));
        item3.setConfId(0);
        item3.setIsVisible(true);
        item3.setTags(Arrays.asList("BBB","CCC"));
        item3.setUsernames(Arrays.asList("paperino"));

        ChartItem item4 = new ChartItem();
        item4.setChartType("PIECHART");
        item4.setDesc("Desc 1");
        item4.setEntityType("DEFECT");
        item4.setIds(Arrays.asList(1,2,3));
        item4.setConfId(0);
        item4.setIsVisible(false);
        item4.setTags(Arrays.asList("tag1"));
        item4.setUsernames(Arrays.asList("pino","paperino"));

        List<ChartItem> chartItems = Arrays.asList(item1,item2,item3,item4);

        return chartItems;

    }
}
