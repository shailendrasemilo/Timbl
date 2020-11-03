package com.np.tele.crm.masterdata.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.GroupsPojo;

public class ParameterGroupForm
    extends ActionForm
{
    private static final Logger    LOGGER           = Logger.getLogger( ParameterGroupForm.class );
    private static final long      serialVersionUID = -7985733299366927995L;
    private long                   parameterGroupId;
    private String                 parameterGroupName;
    private String                 parameterGroupDescription;
    private List<AccessGroupPojo>  accessGroups;
    private List<CrmParameterPojo> parameterPojos;
    private List<GroupsPojo>       groupsPojos;
    private GroupsPojo             groupPojo;
    private AccessGroupPojo        accessGroup;
    private String                 status;
    private String                 searchStatus;
    private int                    rowCounter;

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> statusList )
    {
        this.statusList = statusList;
    }
    List<ContentPojo> statusList;

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int rowCounter )
    {
        this.rowCounter = rowCounter;
    }

    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String searchStatus )
    {
        this.searchStatus = searchStatus;
    }

    public List<GroupsPojo> getGroupsPojos()
    {
        return groupsPojos;
    }

    public void setGroupsPojos( List<GroupsPojo> groupsPojos )
    {
        this.groupsPojos = groupsPojos;
    }

    public GroupsPojo getGroupPojo()
    {
        return groupPojo;
    }

    public void setGroupPojo( GroupsPojo groupPojo )
    {
        this.groupPojo = groupPojo;
    }

    public AccessGroupPojo getAccessGroup()
    {
        return accessGroup;
    }

    public void setAccessGroup( AccessGroupPojo accessGroup )
    {
        this.accessGroup = accessGroup;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public List<AccessGroupPojo> getAccessGroups()
    {
        return accessGroups;
    }

    public void setAccessGroups( List<AccessGroupPojo> accessGroups )
    {
        this.accessGroups = accessGroups;
    }

    public List<CrmParameterPojo> getParameterPojos()
    {
        return parameterPojos;
    }

    public void setParameterPojos( List<CrmParameterPojo> parameterPojos )
    {
        this.parameterPojos = parameterPojos;
    }

    public long getParameterGroupId()
    {
        return parameterGroupId;
    }

    public void setParameterGroupId( long parameterGroupId )
    {
        this.parameterGroupId = parameterGroupId;
    }

    public String getParameterGroupName()
    {
        return parameterGroupName;
    }

    public void setParameterGroupName( String parameterGroupName )
    {
        this.parameterGroupName = parameterGroupName;
    }

    public String getParameterGroupDescription()
    {
        return parameterGroupDescription;
    }

    public void setParameterGroupDescription( String parameterGroupDescription )
    {
        this.parameterGroupDescription = parameterGroupDescription;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "ParameterGroupForm Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.resetParameterGroup( methodName, this );
        LOGGER.info( "ParameterGroupForm Data Reset End" );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "ParameterGroupForm Data Validation Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        ActionErrors actionError = new ActionErrors();
        actionError = MasterFormHelper.validateParameterGroup( methodName, actionError, this );
        LOGGER.info( "ParameterGroupForm Data Validation End" );
        return actionError;
    }
}
