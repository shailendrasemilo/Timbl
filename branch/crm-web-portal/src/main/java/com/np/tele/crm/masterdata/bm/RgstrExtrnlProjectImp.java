package com.np.tele.crm.masterdata.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.forms.CrmProjectForm;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.comparators.ProjectPojoComparator;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class RgstrExtrnlProjectImp
    implements IRgstrExtrnlProject
{
    private static final Logger LOGGER     = Logger.getLogger( RgstrExtrnlProjectImp.class );
    private MasterData          masterData = null;

    public MasterData getMasterData()
    {
        return masterData;
    }

    public void setMasterData( MasterData inMasterData )
    {
        masterData = inMasterData;
    }

    @Override
    public MasterDataDto rgstrExtrnlProject( CrmProjectForm inForm, String inUserId )
    {
        ProjectsPojo projectsPojo = null;
        MasterDataDto masterDataDto = null;
        try
        {
            projectsPojo = new ProjectsPojo();
            masterDataDto = new MasterDataDto();
            projectsPojo.setProjectName( inForm.getExtProjectName() );
            projectsPojo.setProjectDescription( inForm.getProjectDesc() );
            projectsPojo.setProjectType( CRMParameter.EXTERNAL.getParameter() );
            projectsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            projectsPojo.setCreatedBy( inUserId );
            projectsPojo.getCrmParameterPojosSet().addAll( inForm.getCrmParameters() );
            masterDataDto.setProjectsPojo( projectsPojo );
            LOGGER.info( "Going to Create external project " );
            masterDataDto = getMasterData().externalApplication( ServiceParameter.CREATE.getParameter(), masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for register external project", ex );
        }
        return masterDataDto;
    }

    public MasterDataDto searchRgstrExtrnlProject( CrmProjectForm inProjectForm )
    {
        ProjectsPojo projectsPojo = null;
        MasterDataDto masterDataDto = null;
        try
        {
            projectsPojo = new ProjectsPojo();
            masterDataDto = new MasterDataDto();
            projectsPojo.setProjectName( StringUtils.trimToEmpty( inProjectForm.getExtProjectName() ) );
            projectsPojo.setStatus( inProjectForm.getSearchStatus() );
            LOGGER.info( "project Name :" + inProjectForm.getExtProjectName() + ": and Project Status :"
                    + inProjectForm.getSearchStatus() );
            projectsPojo.setProjectType( CRMParameter.EXTERNAL.getParameter() );
            masterDataDto.setProjectsPojo( projectsPojo );
            masterDataDto = getMasterData().externalApplication( ServiceParameter.LIST.getParameter(), masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Exception while searching external projects:", ex );
        }
        return masterDataDto;
    }

    @Override
    public List<String> getParameterType()
    {
        List<String> parameters = new ArrayList<String>();
        parameters.add( "Text" );
        parameters.add( "Number" );
        parameters.add( "Date" );
        return parameters;
    }

    @Override
    public MasterDataDto modifyExtrProject( CrmProjectForm inProjectForm, String inUserId )
    {
        ProjectsPojo projectsPojo = null;
        MasterDataDto masterDataDto = null;
        try
        {
            projectsPojo = new ProjectsPojo();
            masterDataDto = new MasterDataDto();
            projectsPojo.getCrmParameterPojosSet().clear();
            projectsPojo.setProjectName( inProjectForm.getExtProjectName().trim() );
            projectsPojo.getCrmParameterPojosSet().addAll( inProjectForm.getCrmParameters() );
            projectsPojo.setLastModifiedBy( inUserId );
            projectsPojo.setProjectDescription( inProjectForm.getProjectDesc().trim() );
            projectsPojo.setProjectType( CRMParameter.EXTERNAL.getParameter() );
            if ( StringUtils.isValidObj( inProjectForm.getProjectPojo() ) )
            {
                projectsPojo.setProjectId( inProjectForm.getProjectId() );
                ProjectPojoComparator comparator = new ProjectPojoComparator();
                if ( comparator.compare( inProjectForm.getProjectPojo(), projectsPojo ) == 0 )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.setProjectsPojo( projectsPojo );
                masterDataDto = getMasterData().externalApplication( ServiceParameter.UPDATE.getParameter(),
                                                                     masterDataDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for modify external project", ex );
        }
        return masterDataDto;
    }

    public MasterDataDto changeExtrnalProjectStatus( CrmProjectForm inProjectForm, String inUserId )
    {
        MasterDataDto masterDataDto = null;
        ProjectsPojo projectPojo = null;
        try
        {
            masterDataDto = new MasterDataDto();
            projectPojo = new ProjectsPojo();
            projectPojo.setStatus( inProjectForm.getStatus() );
            projectPojo.setProjectId( inProjectForm.getProjectId() );
            projectPojo.setLastModifiedBy( inUserId );
            masterDataDto.setProjectsPojo( projectPojo );
            masterDataDto = getMasterData().externalApplication( ServiceParameter.MODIFY_STATUS.getParameter(),
                                                                 masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for modify status", ex );
        }
        return masterDataDto;
    }
}
