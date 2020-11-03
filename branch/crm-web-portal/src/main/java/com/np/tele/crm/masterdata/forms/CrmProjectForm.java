package com.np.tele.crm.masterdata.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.ProjectsPojo;

public class CrmProjectForm
    extends ActionForm
{
    /**
     * 
     */
    private static final long      serialVersionUID = 1L;
    private static final Logger    LOGGER           = Logger.getLogger( CrmProjectForm.class );
    private String                 extProjectName;
    private String                 status;
    private long                   projectId;
    private String                 projectDesc;
    private List<String>           parameterTypes;
    private List<ProjectsPojo>     projectList;
    private ProjectsPojo           projectPojo;
    private List<CrmParameterPojo> crmParameters;
    private int                    rowCounter;
    private boolean                regValue;
    private boolean                directSearch;
    List<ContentPojo>              statusList;
    private String                 searchStatus;

    public String getExtProjectName()
    {
        return extProjectName;
    }

    public void setExtProjectName( String inExtProjectName )
    {
        this.extProjectName = inExtProjectName;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }

    public void setProjectDesc( String inProjectDesc )
    {
        this.projectDesc = inProjectDesc;
    }

    public List<ProjectsPojo> getProjectList()
    {
        return projectList;
    }

    public void setProjectList( List<ProjectsPojo> inProjectList )
    {
        this.projectList = inProjectList;
    }

    public List<String> getParameterTypes()
    {
        return parameterTypes;
    }

    public void setParameterTypes( List<String> inParameterTypes )
    {
        parameterTypes = inParameterTypes;
    }

    public ProjectsPojo getProjectPojo()
    {
        return projectPojo;
    }

    public void setProjectPojo( ProjectsPojo inProjectPojo )
    {
        projectPojo = inProjectPojo;
    }

    public List<CrmParameterPojo> getCrmParameters()
    {
        return crmParameters;
    }

    public void setCrmParameters( List<CrmParameterPojo> inCrmParameters )
    {
        crmParameters = inCrmParameters;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId( long inProjectId )
    {
        projectId = inProjectId;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int inRowCounter )
    {
        rowCounter = inRowCounter;
    }

    public boolean isRegValue()
    {
        return regValue;
    }

    public void setRegValue( boolean inRegValue )
    {
        regValue = inRegValue;
    }

    public boolean isDirectSearch()
    {
        return directSearch;
    }

    public void setDirectSearch( boolean inDirectSearch )
    {
        directSearch = inDirectSearch;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> inStatusList )
    {
        statusList = inStatusList;
    }

    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String inSearchStatus )
    {
        searchStatus = inSearchStatus;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.resetCRMExtrProjectForm( methodName, this );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method );
        ActionErrors actionError = new ActionErrors();
        MasterFormHelper.validateExternalProject( actionError, method, this );
        return actionError;
    }
}
