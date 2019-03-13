package com.dashboard.restservicedashboard.configuration;



import com.dashboard.commondashboard.DataRowDefect;
import com.dashboard.commondashboard.DataRowTestconfig;
import com.dashboard.commondashboard.Entity;

import java.util.HashMap;
import java.util.Map;

public class Entity2DataRowMapper {


    private static final Map<Entity.EntityType, String> mapping;
    static
    {
        mapping = new HashMap<Entity.EntityType, String>();
        mapping.put(Entity.EntityType.DEFECT, DataRowDefect.class.getName());
        mapping.put(Entity.EntityType.TESTSET, DataRowTestconfig.class.getName());
    }

    public static String getDataRowType(Entity.EntityType entityType) {
        return mapping.get(entityType);
    }
}
