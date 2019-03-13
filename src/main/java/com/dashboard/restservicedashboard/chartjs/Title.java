
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "display",
    "text"
})
public class Title {

    @JsonProperty("display")
    private Boolean display;
    @JsonProperty("text")
    private String text;

    @JsonProperty("display")
    public Boolean getDisplay() {
        return display;
    }

    @JsonProperty("display")
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Title withDisplay(Boolean display) {
        this.display = display;
        return this;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    public Title withText(String text) {
        this.text = text;
        return this;
    }

}
