
package com.dashboard.restservicedashboard.chartjs;

import com.dashboard.restservicedashboard.chartcustominfo.CustomInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "legend",
    "tooltips",
    "showLabel",
    "responsive",
    "animation",
    "maintainAspectRatio",
    "title"
})
public class Options {

    @JsonProperty("legend")
    private Legend legend;
    @JsonProperty("tooltips")
    private Tooltips tooltips;
    @JsonProperty("showLabel")
    private Boolean showLabel;
    @JsonProperty("responsive")
    private Boolean responsive;
    @JsonProperty("animation")
    private Animation animation;
    @JsonProperty("maintainAspectRatio")
    private Boolean maintainAspectRatio;
    @JsonProperty("title")
    private Title title;

    @Getter @Setter CustomInfo customInfo;


    @JsonProperty("legend")
    public Legend getLegend() {
        return legend;
    }

    @JsonProperty("legend")
    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Options withLegend(Legend legend) {
        this.legend = legend;
        return this;
    }

    @JsonProperty("tooltips")
    public Tooltips getTooltips() {
        return tooltips;
    }

    @JsonProperty("tooltips")
    public void setTooltips(Tooltips tooltips) {
        this.tooltips = tooltips;
    }

    public Options withTooltips(Tooltips tooltips) {
        this.tooltips = tooltips;
        return this;
    }

    @JsonProperty("showLabel")
    public Boolean getShowLabel() {
        return showLabel;
    }

    @JsonProperty("showLabel")
    public void setShowLabel(Boolean showLabel) {
        this.showLabel = showLabel;
    }

    public Options withShowLabel(Boolean showLabel) {
        this.showLabel = showLabel;
        return this;
    }

    @JsonProperty("responsive")
    public Boolean getResponsive() {
        return responsive;
    }

    @JsonProperty("responsive")
    public void setResponsive(Boolean responsive) {
        this.responsive = responsive;
    }

    public Options withResponsive(Boolean responsive) {
        this.responsive = responsive;
        return this;
    }

    @JsonProperty("animation")
    public Animation getAnimation() {
        return animation;
    }

    @JsonProperty("animation")
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Options withAnimation(Animation animation) {
        this.animation = animation;
        return this;
    }

    @JsonProperty("maintainAspectRatio")
    public Boolean getMaintainAspectRatio() {
        return maintainAspectRatio;
    }

    @JsonProperty("maintainAspectRatio")
    public void setMaintainAspectRatio(Boolean maintainAspectRatio) {
        this.maintainAspectRatio = maintainAspectRatio;
    }

    public Options withMaintainAspectRatio(Boolean maintainAspectRatio) {
        this.maintainAspectRatio = maintainAspectRatio;
        return this;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    public Options withTitle(Title title) {
        this.title = title;
        return this;
    }

}
