
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "display",
    "position"
})
public class Legend {

    @JsonProperty("display")
    private Boolean display;
    @JsonProperty("position")
    private String position;

    @JsonProperty("display")
    public Boolean getDisplay() {
        return display;
    }

    @JsonProperty("display")
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Legend withDisplay(Boolean display) {
        this.display = display;
        return this;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    public Legend withPosition(String position) {
        this.position = position;
        return this;
    }

}
