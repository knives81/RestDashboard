package com.dashboard.restservicedashboard.chartcustominfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CustomInfo {

    CustomInfo(){
        this.text = "";
        this.color = "#000";
    }

    @Getter String text;
    @Getter String color;
}
