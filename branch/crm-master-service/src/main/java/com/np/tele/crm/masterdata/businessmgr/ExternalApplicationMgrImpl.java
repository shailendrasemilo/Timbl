package com.np.tele.crm.masterdata.businessmgr;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.masterdata.dao.IExternalApplicationDao;
import com.np.tele.crm.pojos.CrmParameterPojo;
import com.np.tele.crm.pojos.ProjectsPojo;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class ExternalApplicationMgrImpl
    implements IExternalApplicationMgr
{
    private static final Logger     LOGGER                 = Logger.getLogger( ExternalApplicationMgrImpl.class );
    private IExternalApplicationDao externalApplicationDao = null;

    public IExternalApplicationDao getExternalApplicationDao()
    {
        return externalApplicationDao;
    }

    public void setExternalApplicationDao( IExternalApplicationDao inExternalApplicationDao )
    {
        externalApplicationDao = inExternalApplicationDao;
    }

    @Override
    public MasterDataDto externalApplication( String inServiceParam, MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside externalApplication Service" );
        if ( StringUtils.isValidObj( inServiceParam ) & StringUtils.isValidObj( inMasterDataDto ) )
        {
            if ( inServiceParam.equals( ServiceParameter.CREATE.getParameter() ) )
            {
                return registerExternalApp( inMasterDataDto, inServiceParam );
            }
            else if ( inServiceParam.equals( ServiceParameter.LIST.getParameter() ) )
            {
                return listExternalApp( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.MODIFY_STATUS.getParameter() ) )
            {
                return modifyExternalAppStatus( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.UPDATE.getParameter() ) )
            {
                return registerExternalApp( inMasterDataDto, inServiceParam );
            }
            else if ( inServiceParam.equals( ServiceParameter.VIEW.getParameter() ) )
            {
                return viewExternalApp( inMasterDataDto );
            }
        }
        else
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        LOGGER.info( "Exit externalApplication Service" );
        return inMasterDataDto;
    }

    private MasterDataDto viewExternalApp( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside listExternalApp Service" );
        ProjectsPojo pojo = inMasterDataDto.getProjectsPojo();
        if ( StringUtils.isValidObj( pojo ) && pojo.getProjectId() > 0 )
        {
            ProjectsPojo projectsPojo = getExternalApplicationDao().getProject( pojo );
            if ( StringUtils.isValidObj( projectsPojo ) && !projectsPojo.getCrmParameterPojosSet().isEmpty() )
            {
                inMasterDataDto.setProjectsPojo( projectsPojo );
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM057.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM057.getStatusDesc() );
            }
        }
        else
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inMasterDataDto;
    }

    private MasterDataDto modifyExternalAppStatus( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside modifyExternalAppStatus Service" );
        ProjectsPojo pojo = inMasterDataDto.getProjectsPojo();
        if ( StringUtils.isValidObj( pojo ) )
        {
            boolean isUpdated = getExternalApplicationDao().modifyExternalAppStatus( pojo );
            if ( isUpdated )
            {
                inMasterDataDto.setProjectsPojo( pojo );
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            }
        }
        return inMasterDataDto;
    }

    private MasterDataDto listExternalApp( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside listExternalApp Service" );
        ProjectsPojo pojo = inMasterDataDto.getProjectsPojo();
        if ( StringUtils.isEmpty( pojo.getProjectType() ) )
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM046.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM046.getStatusDesc() );
        }
        else
        {
            List<ProjectsPojo> pojosList = getExternalApplicationDao().listRegisteredExternalApp( pojo );
            if ( StringUtils.isValidObj( pojosList ) )
            {
                inMasterDataDto.setProjectsPojos( pojosList );
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
        }
        return inMasterDataDto;
    }

    private MasterDataDto registerExternalApp( MasterDataDto inMasterDataDto, String inServiceParam )
    {
        LOGGER.info( "Entering registerExternalApp Service" );
        if ( StringUtils.isEmpty( inMasterDataDto.getProjectsPojo().getProjectName() ) )
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM044.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM044.getStatusDesc() );
        }
        else
        {
            if ( StringUtils.isValidObj( inMasterDataDto.getProjectsPojo().getCrmParameterPojosSet() )
                    && !inMasterDataDto.getProjectsPojo().getCrmParameterPojosSet().isEmpty() )
            {
                for ( CrmParameterPojo crmParameterPojo : inMasterDataDto.getProjectsPojo().getCrmParameterPojosSet() )
                {
                    crmParameterPojo.setProjectsPojo( ( inMasterDataDto.getProjectsPojo() ) );
                }
            }
            if ( inServiceParam.equalsIgnoreCase( ServiceParameter.CREATE.getParameter() ) )
            {
                inMasterDataDto = getExternalApplicationDao().registerExternalApp( inMasterDataDto );
            }
            else
            {
                LOGGER.info( "Update Parameters :" );
                inMasterDataDto = getExternalApplicationDao().updateExternalProjectParam( inMasterDataDto );
            }
        }
        return inMasterDataDto;
    }
}
