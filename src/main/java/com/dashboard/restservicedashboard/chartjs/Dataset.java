
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fill",
    "data",
    "label",
    "backgroundColor",
    "borderColor",
    "borderDash",
    "pointRadius",
    "pointHoverRadius"
})
public class Dataset {

    @JsonProperty("fill")
    private Boolean fill;
    @JsonProperty("data")
    private List<Integer> data = null;
    @JsonProperty("label")
    private String label;
    @JsonProperty("backgroundColor")
    private String backgroundColor;
    @JsonProperty("borderColor")
    private String borderColor;
    @JsonProperty("borderDash")
    private List<Integer> borderDash = null;
    @JsonProperty("pointRadius")
    private Integer pointRadius;
    @JsonProperty("pointHoverRadius")
    private Integer pointHoverRadius;

    @JsonProperty("fill")
    public Boolean getFill() {
        return fill;
    }

    @JsonProperty("fill")
    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    public Dataset withFill(Boolean fill) {
        this.fill = fill;
        return this;
    }

    @JsonProperty("data")
    public List<Integer> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Integer> data) {
        this.data = data;
    }

    public Dataset withData(List<Integer> data) {
        this.data = data;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public Dataset withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("backgroundColor")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Dataset withBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @JsonProperty("borderColor")
    public String getBorderColor() {
        return borderColor;
    }

    @JsonProperty("borderColor")
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Dataset withBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    @JsonProperty("borderDash")
    public List<Integer> getBorderDash() {
        return borderDash;
    }

    @JsonProperty("borderDash")
    public void setBorderDash(List<Integer> borderDash) {
        this.borderDash = borderDash;
    }

    public Dataset withBorderDash(List<Integer> borderDash) {
        this.borderDash = borderDash;
        return this;
    }

    @JsonProperty("pointRadius")
    public Integer getPointRadius() {
        return pointRadius;
    }

    @JsonProperty("pointRadius")
    public void setPointRadius(Integer pointRadius) {
        this.pointRadius = pointRadius;
    }

    public Dataset withPointRadius(Integer pointRadius) {
        this.pointRadius = pointRadius;
        return this;
    }

    @JsonProperty("pointHoverRadius")
    public Integer getPointHoverRadius() {
        return pointHoverRadius;
    }

    @JsonProperty("pointHoverRadius")
    public void setPointHoverRadius(Integer pointHoverRadius) {
        this.pointHoverRadius = pointHoverRadius;
    }

    public Dataset withPointHoverRadius(Integer pointHoverRadius) {
        this.pointHoverRadius = pointHoverRadius;
        return this;
    }

}
