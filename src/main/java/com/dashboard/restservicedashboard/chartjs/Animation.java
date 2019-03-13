
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "animateScale",
    "animateRotate"
})
public class Animation {

    @JsonProperty("animateScale")
    private Boolean animateScale;
    @JsonProperty("animateRotate")
    private Boolean animateRotate;

    @JsonProperty("animateScale")
    public Boolean getAnimateScale() {
        return animateScale;
    }

    @JsonProperty("animateScale")
    public void setAnimateScale(Boolean animateScale) {
        this.animateScale = animateScale;
    }

    public Animation withAnimateScale(Boolean animateScale) {
        this.animateScale = animateScale;
        return this;
    }

    @JsonProperty("animateRotate")
    public Boolean getAnimateRotate() {
        return animateRotate;
    }

    @JsonProperty("animateRotate")
    public void setAnimateRotate(Boolean animateRotate) {
        this.animateRotate = animateRotate;
    }

    public Animation withAnimateRotate(Boolean animateRotate) {
        this.animateRotate = animateRotate;
        return this;
    }

}
