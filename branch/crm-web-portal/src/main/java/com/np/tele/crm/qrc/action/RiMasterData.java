
package com.np.tele.crm.qrc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "treesize",
    "device"
})
public class RiMasterData {

    @JsonProperty("treesize")
    private Integer treesize;
    @JsonProperty("device")
    private List<Device> device = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("treesize")
    public Integer getTreesize() {
        return treesize;
    }

    @JsonProperty("treesize")
    public void setTreesize(Integer treesize) {
        this.treesize = treesize;
    }

    @JsonProperty("device")
    public List<Device> getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(List<Device> device) {
        this.device = device;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString()
    {
        return "RiMasterData [treesize=" + treesize + ", device=" + device + ", additionalProperties="
                + additionalProperties + "]";
    }
    
    

}
