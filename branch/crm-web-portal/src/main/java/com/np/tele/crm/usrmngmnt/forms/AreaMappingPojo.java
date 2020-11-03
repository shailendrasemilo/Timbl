package com.np.tele.crm.usrmngmnt.forms;

import java.util.Date;
import java.util.List;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.StatePojo;

public class AreaMappingPojo
{
    private long            mappingId;
    private long            stateId;
    private long            cityId;
    private long            areaId;
    private String          createdBy;
    private Date            createdTime;
    private String          modifiedBy;
    private Date            modifiedTime;
    private String          status;
    private boolean         editable;
    private String          userId;
    private List<StatePojo> stateDataList;
    private List<CityPojo>  cityList;
    private List<AreaPojo>  areaList;
    private String          areaName;
    private String          cityName;
    private String          stateName;
    private String          lastModifiedBy;
    private long            userRecordId;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public long getStateId()
    {
        return stateId;
    }

    public void setStateId( long stateId )
    {
        this.stateId = stateId;
    }

    public long getCityId()
    {
        return cityId;
    }

    public void setCityId( long cityId )
    {
        this.cityId = cityId;
    }

    public long getAreaId()
    {
        return areaId;
    }

    public void setAreaId( long areaId )
    {
        this.areaId = areaId;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getModifiedBy()
    {
        return modifiedBy;
    }

    public void setModifiedBy( String modifiedBy )
    {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime()
    {
        return modifiedTime;
    }

    public void setModifiedTime( Date modifiedTime )
    {
        this.modifiedTime = modifiedTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean editable )
    {
        this.editable = editable;
    }

    public long getMappingId()
    {
        return mappingId;
    }

    public void setMappingId( long mappingId )
    {
        this.mappingId = mappingId;
    }

    public List<StatePojo> getStateDataList()
    {
        return stateDataList;
    }

    public void setStateDataList( List<StatePojo> stateDataList )
    {
        this.stateDataList = stateDataList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> cityList )
    {
        this.cityList = cityList;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public void setAreaList( List<AreaPojo> areaList )
    {
        this.areaList = areaList;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = cityName;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = stateName;
    }

    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }
    
    public long getUserRecordId()
    {
        return userRecordId;
    }

    public void setUserRecordId( long userRecordId )
    {
        this.userRecordId = userRecordId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AreaMappingPojo [mappingId=" ).append( mappingId ).append( ", stateId=" ).append( stateId )
                .append( ", cityId=" ).append( cityId ).append( ", areaId=" ).append( areaId ).append( ", createdBy=" )
                .append( createdBy ).append( ", createdTime=" ).append( createdTime ).append( ", modifiedBy=" )
                .append( modifiedBy ).append( ", modifiedTime=" ).append( modifiedTime ).append( ", status=" )
                .append( status ).append( ", editable=" ).append( editable ).append( ", userId=" ).append( userId )
                .append( ", areaName=" ).append( areaName ).append( ", cityName=" ).append( cityName )
                .append( ", stateName=" ).append( stateName ).append( ", getUserId()=" ).append( getUserId() )
                .append( ", getStateId()=" ).append( getStateId() ).append( ", getCityId()=" ).append( getCityId() )
                .append( ", getAreaId()=" ).append( getAreaId() ).append( ", getCreatedBy()=" ).append( getCreatedBy() )
                .append( ", getCreatedTime()=" ).append( getCreatedTime() ).append( ", getModifiedBy()=" )
                .append( getModifiedBy() ).append( ", getModifiedTime()=" ).append( getModifiedTime() )
                .append( ", getStatus()=" ).append( getStatus() ).append( ", isEditable()=" ).append( isEditable() )
                .append( ", getMappingId()=" ).append( getMappingId() ).append( ", getAreaName()=" )
                .append( getAreaName() ).append( ", getCityName()=" ).append( getCityName() )
                .append( ", getStateName()=" ).append( getStateName() ).append( ", getClass()=" ).append( getClass() )
                .append( ", hashCode()=" ).append( hashCode() ).append( ", toString()=" ).append( super.toString() )
                .append( "]" );
        return builder.toString();
    }
}
