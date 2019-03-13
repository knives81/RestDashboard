
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "backgroundColor",
    "borderColor",
    "pointBackgroundColor",
    "pointBorderColor",
    "pointHoverBackgroundColor",
    "pointHoverBorderColor"
})
public class Color {

    @JsonProperty("backgroundColor")
    private List<String> backgroundColor = null;
    @JsonProperty("borderColor")
    private List<String> borderColor = null;
    @JsonProperty("pointBackgroundColor")
    private List<String> pointBackgroundColor = null;
    @JsonProperty("pointBorderColor")
    private List<String> pointBorderColor = null;
    @JsonProperty("pointHoverBackgroundColor")
    private List<String> pointHoverBackgroundColor = null;
    @JsonProperty("pointHoverBorderColor")
    private List<String> pointHoverBorderColor = null;

    @JsonProperty("backgroundColor")
    public List<String> getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(List<String> backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color withBackgroundColor(List<String> backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @JsonProperty("borderColor")
    public List<String> getBorderColor() {
        return borderColor;
    }

    @JsonProperty("borderColor")
    public void setBorderColor(List<String> borderColor) {
        this.borderColor = borderColor;
    }

    public Color withBorderColor(List<String> borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    @JsonProperty("pointBackgroundColor")
    public List<String> getPointBackgroundColor() {
        return pointBackgroundColor;
    }

    @JsonProperty("pointBackgroundColor")
    public void setPointBackgroundColor(List<String> pointBackgroundColor) {
        this.pointBackgroundColor = pointBackgroundColor;
    }

    public Color withPointBackgroundColor(List<String> pointBackgroundColor) {
        this.pointBackgroundColor = pointBackgroundColor;
        return this;
    }

    @JsonProperty("pointBorderColor")
    public List<String> getPointBorderColor() {
        return pointBorderColor;
    }

    @JsonProperty("pointBorderColor")
    public void setPointBorderColor(List<String> pointBorderColor) {
        this.pointBorderColor = pointBorderColor;
    }

    public Color withPointBorderColor(List<String> pointBorderColor) {
        this.pointBorderColor = pointBorderColor;
        return this;
    }

    @JsonProperty("pointHoverBackgroundColor")
    public List<String> getPointHoverBackgroundColor() {
        return pointHoverBackgroundColor;
    }

    @JsonProperty("pointHoverBackgroundColor")
    public void setPointHoverBackgroundColor(List<String> pointHoverBackgroundColor) {
        this.pointHoverBackgroundColor = pointHoverBackgroundColor;
    }

    public Color withPointHoverBackgroundColor(List<String> pointHoverBackgroundColor) {
        this.pointHoverBackgroundColor = pointHoverBackgroundColor;
        return this;
    }

    @JsonProperty("pointHoverBorderColor")
    public List<String> getPointHoverBorderColor() {
        return pointHoverBorderColor;
    }

    @JsonProperty("pointHoverBorderColor")
    public void setPointHoverBorderColor(List<String> pointHoverBorderColor) {
        this.pointHoverBorderColor = pointHoverBorderColor;
    }

    public Color withPointHoverBorderColor(List<String> pointHoverBorderColor) {
        this.pointHoverBorderColor = pointHoverBorderColor;
        return this;
    }

}
