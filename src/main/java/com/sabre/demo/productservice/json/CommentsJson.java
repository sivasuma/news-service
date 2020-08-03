package com.sabre.demo.productservice.json;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "by",
        "id",
        "kids",
        "parent",
        "text",
        "time",
        "type"
})
public class CommentsJson {

    @JsonProperty("by")
    private String by;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kids")
    private List<Integer> kids = null;
    @JsonProperty("parent")
    private Integer parent;
    @JsonProperty("text")
    private String text;
    @JsonProperty("time")
    private long time;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("by")
    public String getBy() {
        return by;
    }

    @JsonProperty("by")
    public void setBy(String by) {
        this.by = by;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("kids")
    public List<Integer> getKids() {
        return kids;
    }

    @JsonProperty("kids")
    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    @JsonProperty("parent")
    public Integer getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("time")
    public Long getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Long time) {
        this.time = time;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}