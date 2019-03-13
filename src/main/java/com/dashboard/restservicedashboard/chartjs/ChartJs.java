
package com.dashboard.restservicedashboard.chartjs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "data",
    "colors",
    "options"
})
public class ChartJs {

    @JsonProperty("type")
    private String type;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("colors")
    private List<Color> colors = null;
    @JsonProperty("options")
    private Options options;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ChartJs withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public ChartJs withData(Data data) {
        this.data = data;
        return this;
    }

    @JsonProperty("colors")
    public List<Color> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public ChartJs withColors(List<Color> colors) {
        this.colors = colors;
        return this;
    }

    @JsonProperty("options")
    public Options getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(Options options) {
        this.options = options;
    }

    public ChartJs withOptions(Options options) {
        this.options = options;
        return this;
    }

}
