package com.np.lms.rest.dto;

import java.io.Serializable;
import java.util.List;

public class MasterRequestDto
    implements Serializable
{
    private String       operation;
    private String       serviceParam;
    private String       statusCode;
    private String       statusDesc;
    private short        stateId;
    private short        areaId;
    private short        localityId;
    private short        cityId;
    private String       stateName;
    private String       cityName;
    private String       areaName;
    private String       localityName;
    private List<String> functionalBin;
    private String       userId;
    private String       requestType;
    private String       leadId;
    private String       currentStage;
    private String       mappingId;
    private String       clientIp;
    private String       serverIp;

    public String getOperation()
    {
        return operation;
    }

    public void setOperation( String inOperation )
    {
        operation = inOperation;
    }

    public String getServerIp()
    {
        return serverIp;
    }

    public void setServerIp( String inServerIp )
    {
        serverIp = inServerIp;
    }

    public String getServiceParam()
    {
        return serviceParam;
    }

    public void setServiceParam( String inServiceParam )
    {
        serviceParam = inServiceParam;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public short getStateId()
    {
        return stateId;
    }

    public void setStateId( short inStateId )
    {
        stateId = inStateId;
    }

    public short getAreaId()
    {
        return areaId;
    }

    public void setAreaId( short inAreaId )
    {
        areaId = inAreaId;
    }

    public short getLocalityId()
    {
        return localityId;
    }

    public void setLocalityId( short inLocalityId )
    {
        localityId = inLocalityId;
    }

    public short getCityId()
    {
        return cityId;
    }

    public void setCityId( short inCityId )
    {
        cityId = inCityId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String inRequestType )
    {
        requestType = inRequestType;
    }

    public List<String> getFunctionalBin()
    {
        return functionalBin;
    }

    public void setFunctionalBin( List<String> inFunctionalBin )
    {
        functionalBin = inFunctionalBin;
    }

    public String getLeadId()
    {
        return leadId;
    }

    public void setLeadId( String inLeadId )
    {
        leadId = inLeadId;
    }

    public String getCurrentStage()
    {
        return currentStage;
    }

    public void setCurrentStage( String inCurrentStage )
    {
        currentStage = inCurrentStage;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String inStateName )
    {
        stateName = inStateName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String inCityName )
    {
        cityName = inCityName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String inAreaName )
    {
        areaName = inAreaName;
    }

    public String getLocalityName()
    {
        return localityName;
    }

    public void setLocalityName( String inLocalityName )
    {
        localityName = inLocalityName;
    }

    public String getMappingId()
    {
        return mappingId;
    }

    public void setMappingId( String inMappingId )
    {
        mappingId = inMappingId;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp( String inClientIp )
    {
        clientIp = inClientIp;
    }
}
