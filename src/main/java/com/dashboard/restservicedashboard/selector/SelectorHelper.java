package com.dashboard.restservicedashboard.selector;

import com.dashboard.commondashboard.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class SelectorHelper {

    @Setter @Getter Entity.EntityType entityType;
    @Setter @Getter Integer confPositionIndex;
    @Setter @Getter Integer orderTagName;
    @Setter @Getter String entityTagName = "";
    @Setter @Getter String entityTagValue = "";

}
