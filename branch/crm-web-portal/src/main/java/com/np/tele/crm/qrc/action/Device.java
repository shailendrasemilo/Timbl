package com.np.tele.crm.qrc.action;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
{ "device", "status", "status_raw", "uptimesince", "uptimesince_raw", "lastup", "lastup_raw", "downtimesince",
  "downtimesince_raw", "lastdown", "lastdown_raw", "host", "host_raw" })
public class Device
{
    @JsonProperty("device")
    private String              device;
    @JsonProperty("status")
    private String              status;
    @JsonProperty("status_raw")
    private Integer             statusRaw;
    @JsonProperty("uptimesince")
    private String              uptimesince;
    @JsonProperty("uptimesince_raw")
    private String              uptimesinceRaw;
    @JsonProperty("lastup")
    private String              lastup;
    @JsonProperty("lastup_raw")
    private String              lastupRaw;
    @JsonProperty("downtimesince")
    private String              downtimesince;
    @JsonProperty("downtimesince_raw")
    private String              downtimesinceRaw;
    @JsonProperty("lastdown")
    private String              lastdown;
    @JsonProperty("lastdown_raw")
    private String              lastdownRaw;
    @JsonProperty("host")
    private String              host;
    @JsonProperty("host_raw")
    private String              hostRaw;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("device")
    public String getDevice()
    {
        return device;
    }

    @JsonProperty("device")
    public void setDevice( String device )
    {
        this.device = device;
    }

    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    @JsonProperty("status")
    public void setStatus( String status )
    {
        this.status = status;
    }

    @JsonProperty("status_raw")
    public Integer getStatusRaw()
    {
        return statusRaw;
    }

    @JsonProperty("status_raw")
    public void setStatusRaw( Integer statusRaw )
    {
        this.statusRaw = statusRaw;
    }

    @JsonProperty("uptimesince")
    public String getUptimesince()
    {
        return uptimesince;
    }

    @JsonProperty("uptimesince")
    public void setUptimesince( String uptimesince )
    {
        this.uptimesince = uptimesince;
    }

    @JsonProperty("uptimesince_raw")
    public String getUptimesinceRaw()
    {
        return uptimesinceRaw;
    }

    @JsonProperty("uptimesince_raw")
    public void setUptimesinceRaw( String uptimesinceRaw )
    {
        this.uptimesinceRaw = uptimesinceRaw;
    }

    @JsonProperty("lastup")
    public String getLastup()
    {
        return lastup;
    }

    @JsonProperty("lastup")
    public void setLastup( String lastup )
    {
        this.lastup = lastup;
    }

    @JsonProperty("lastup_raw")
    public String getLastupRaw()
    {
        return lastupRaw;
    }

    @JsonProperty("lastup_raw")
    public void setLastupRaw( String lastupRaw )
    {
        this.lastupRaw = lastupRaw;
    }

    @JsonProperty("downtimesince")
    public String getDowntimesince()
    {
        return downtimesince;
    }

    @JsonProperty("downtimesince")
    public void setDowntimesince( String downtimesince )
    {
        this.downtimesince = downtimesince;
    }

    @JsonProperty("downtimesince_raw")
    public String getDowntimesinceRaw()
    {
        return downtimesinceRaw;
    }

    @JsonProperty("downtimesince_raw")
    public void setDowntimesinceRaw( String downtimesinceRaw )
    {
        this.downtimesinceRaw = downtimesinceRaw;
    }

    @JsonProperty("lastdown")
    public String getLastdown()
    {
        return lastdown;
    }

    @JsonProperty("lastdown")
    public void setLastdown( String lastdown )
    {
        this.lastdown = lastdown;
    }

    @JsonProperty("lastdown_raw")
    public String getLastdownRaw()
    {
        return lastdownRaw;
    }

    @JsonProperty("lastdown_raw")
    public void setLastdownRaw( String lastdownRaw )
    {
        this.lastdownRaw = lastdownRaw;
    }

    @JsonProperty("host")
    public String getHost()
    {
        return host;
    }

    @JsonProperty("host")
    public void setHost( String host )
    {
        this.host = host;
    }

    @JsonProperty("host_raw")
    public String getHostRaw()
    {
        return hostRaw;
    }

    @JsonProperty("host_raw")
    public void setHostRaw( String hostRaw )
    {
        this.hostRaw = hostRaw;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
    }

    @Override
    public String toString()
    {
        return "Device [device=" + device + ", status=" + status + ", statusRaw=" + statusRaw + ", uptimesince="
                + uptimesince + ", uptimesinceRaw=" + uptimesinceRaw + ", lastup=" + lastup + ", lastupRaw=" + lastupRaw
                + ", downtimesince=" + downtimesince + ", downtimesinceRaw=" + downtimesinceRaw + ", lastdown="
                + lastdown + ", lastdownRaw=" + lastdownRaw + ", host=" + host + ", hostRaw=" + hostRaw
                + ", additionalProperties=" + additionalProperties + "]";
    }
}
