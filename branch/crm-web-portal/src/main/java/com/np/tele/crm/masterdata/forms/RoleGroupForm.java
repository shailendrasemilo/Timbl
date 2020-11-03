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
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.GroupsPojo;

public class RoleGroupForm
    extends ActionForm
{
    /**
     * 
     */
    private static final long     serialVersionUID = 2178134156314472873L;
    private long                  roleGroupId;
    private String                roleGroupName;
    private String                roleGroupDescription;
    private String                roleName;
    private List<AccessGroupPojo> accessGroups;
    private List<CrmRolesPojo>    crmRoles;
    private List<GroupsPojo>      groupsPojos;
    private GroupsPojo            groupPojo;
    private AccessGroupPojo       accessGroup;
    private String                status;
    private String                searchStatus;
    private int                   rowCounter;
    private List<ContentPojo>     statusList;

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> statusList )
    {
        this.statusList = statusList;
    }

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

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public AccessGroupPojo getAccessGroup()
    {
        return accessGroup;
    }

    public void setAccessGroup( AccessGroupPojo accessGroup )
    {
        this.accessGroup = accessGroup;
    }

    public GroupsPojo getGroupPojo()
    {
        return groupPojo;
    }

    public void setGroupPojo( GroupsPojo groupPojo )
    {
        this.groupPojo = groupPojo;
    }
    private static final Logger LOGGER = Logger.getLogger( RoleGroupForm.class );

    public List<AccessGroupPojo> getAccessGroups()
    {
        return accessGroups;
    }

    public void setAccessGroups( List<AccessGroupPojo> accessGroups )
    {
        this.accessGroups = accessGroups;
    }

    public List<CrmRolesPojo> getCrmRoles()
    {
        return crmRoles;
    }

    public void setCrmRoles( List<CrmRolesPojo> crmRoles )
    {
        this.crmRoles = crmRoles;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName( String roleName )
    {
        this.roleName = roleName;
    }

    public long getRoleGroupId()
    {
        return roleGroupId;
    }

    public void setRoleGroupId( long roleGroupId )
    {
        this.roleGroupId = roleGroupId;
    }

    public String getRoleGroupName()
    {
        return roleGroupName;
    }

    public void setRoleGroupName( String roleGroupName )
    {
        this.roleGroupName = roleGroupName;
    }

    public String getRoleGroupDescription()
    {
        return roleGroupDescription;
    }

    public List<GroupsPojo> getGroupsPojos()
    {
        return groupsPojos;
    }

    public void setGroupsPojos( List<GroupsPojo> groupsPojos )
    {
        this.groupsPojos = groupsPojos;
    }

    public void setRoleGroupDescription( String roleGroupDescription )
    {
        this.roleGroupDescription = roleGroupDescription;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.resetAccessGroup( methodName, getAccessGroups() );
        MasterFormHelper.resetRoleGroup( methodName, this );
        LOGGER.info( "Master Data Reset End" );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Validation Start" );
        ActionErrors errors = new ActionErrors();
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.validateForm( errors, methodName, this );
        LOGGER.info( "Master Data Validation Start" );
        return errors;
    }
}
