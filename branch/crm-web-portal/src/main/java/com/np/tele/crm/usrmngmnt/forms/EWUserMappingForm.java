package com.np.tele.crm.usrmngmnt.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmUserMappingPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.utils.StringUtils;

public class EWUserMappingForm
    extends ActionForm
{
    private String                   userId                  = null;
    private List<CrmUserPojo>        crmUserPojos            = null;
    private String                   mappingType             = null;
    private List<CrmUserMappingPojo> crmUserMappingPojosList = null;
    private String                   hiddenUserId            = null;
    private String                   functionalBin           = null;
    private List<ContentPojo>        leadStagesList          = null;
    //    private String                   empType                 = null;
    private String                   searchStatus            = null;
    private List<ContentPojo>        statusList              = null;
    private int                      userPojoSize            = 0;
    private String                   hiddenUserList          = null;
    private int                      rowCounter              = 0;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public List<CrmUserPojo> getCrmUserPojos()
    {
        return crmUserPojos;
    }

    public void setCrmUserPojos( List<CrmUserPojo> inCrmUserPojos )
    {
        crmUserPojos = inCrmUserPojos;
    }

    public String getMappingType()
    {
        return mappingType;
    }

    public void setMappingType( String inMappingType )
    {
        mappingType = inMappingType;
    }

    public List<CrmUserMappingPojo> getCrmUserMappingPojosList()
    {
        return crmUserMappingPojosList;
    }

    public void setCrmUserMappingPojosList( List<CrmUserMappingPojo> inCrmUserMappingPojosList )
    {
        crmUserMappingPojosList = inCrmUserMappingPojosList;
    }

    public String getHiddenUserId()
    {
        return hiddenUserId;
    }

    public void setHiddenUserId( String inHiddenUserId )
    {
        hiddenUserId = inHiddenUserId;
    }

    public String getFunctionalBin()
    {
        return functionalBin;
    }

    public void setFunctionalBin( String inFunctionalBin )
    {
        functionalBin = inFunctionalBin;
    }

    public List<ContentPojo> getLeadStagesList()
    {
        return leadStagesList;
    }

    public void setLeadStagesList( List<ContentPojo> inLeadStagesList )
    {
        leadStagesList = inLeadStagesList;
    }

    /*public String getEmpType()
    {
        return empType;
    }

    public void setEmpType( String inEmpType )
    {
        empType = inEmpType;
    }
    */
    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String inSearchStatus )
    {
        searchStatus = inSearchStatus;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> inStatusList )
    {
        statusList = inStatusList;
    }

    public int getUserPojoSize()
    {
        return userPojoSize;
    }

    public void setUserPojoSize( int inUserPojoSize )
    {
        userPojoSize = inUserPojoSize;
    }

    public String getHiddenUserList()
    {
        return hiddenUserList;
    }

    public void setHiddenUserList( String inHiddenUserList )
    {
        hiddenUserList = inHiddenUserList;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int inRowCounter )
    {
        rowCounter = inRowCounter;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( method, IAppConstants.METHOD_SELECT_USER_POP_UP )
                || StringUtils.equals( method, IAppConstants.METHOD_SELECT_USER_SEARCH )
                || StringUtils.equals( method, IAppConstants.METHOD_SELECT_USER_MAPPING )
                || StringUtils.equals( method, IAppConstants.METHOD_SEARCH_USER_MAPPING )
                || StringUtils.equals( method, IAppConstants.METHOD_SEARCH_USER_MAPPING_POP_UP ) )
        {
            this.setUserId( null );
            this.setUserPojoSize( 0 );
            this.setSearchStatus( null );
            this.setFunctionalBin( null );
            //            this.setEmpType( null );
            this.setCrmUserPojos( new ArrayList<CrmUserPojo>() );
            this.setStatusList( new ArrayList<ContentPojo>() );
            this.setLeadStagesList( new ArrayList<ContentPojo>() );
        }
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        return super.validate( inMapping, inRequest );
    }
}
