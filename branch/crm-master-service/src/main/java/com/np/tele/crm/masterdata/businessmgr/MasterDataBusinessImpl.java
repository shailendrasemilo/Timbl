package com.np.tele.crm.masterdata.businessmgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.masterdata.dao.IMasterDataDao;
import com.np.tele.crm.pojos.AllInboxPojo;
import com.np.tele.crm.pojos.CrmHolidayDetails;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmRolesPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.GroupsPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.StringUtils;

public class MasterDataBusinessImpl
    implements IMasterDataBusiness
{
    private static final Logger       LOGGER        = Logger.getLogger( MasterDataBusinessImpl.class );
    private IMasterDataDao            masterDataDao = null;
    private static final List<String> masterTypes   = new ArrayList<String>();
    static
    {
        masterTypes.add( CRMParameter.EMAIL.getParameter() );
        masterTypes.add( CRMParameter.SMS.getParameter() );
        masterTypes.add( CRMParameter.ALERTS_MASTER.getParameter() );
    }

    public IMasterDataDao getMasterDataDao()
    {
        return masterDataDao;
    }

    public void setMasterDataDao( IMasterDataDao iMasterDataDao )
    {
        this.masterDataDao = iMasterDataDao;
    }

    @Override
    public MasterDataDto getMasterData( MasterDataDto inMasterDataDto, String inPojoName )
    {
        LOGGER.info( "IN MasterData Method" );
        MasterDataDto masterDataDto = new MasterDataDto();
        CRMServiceCode crmServiceCode = null;
        try
        {
            if ( CRMParameter.PARTNER_POJO.getParameter().equalsIgnoreCase( inPojoName ) )
            {
                List<PartnerPojo> partnerPojoList = getMasterDataDao().getPartnerPojoList( inMasterDataDto );
                if ( partnerPojoList.size() > 0 )
                {
                    LOGGER.info( "Size of Pojo" + partnerPojoList.size() );
                    masterDataDto.setPartnerPojoList( partnerPojoList );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM042;
                }
            }
            else if ( CRMParameter.CRM_ROLES.getParameter().equalsIgnoreCase( inPojoName ) )
            {
                List<CrmRolesPojo> crmRolesPojo = getMasterDataDao().getCRMRolesPojoList();
                if ( crmRolesPojo.size() > 0 )
                {
                    LOGGER.info( "Size of Pojo" + crmRolesPojo.size() );
                    masterDataDto.setCrmRolesPojoList( crmRolesPojo );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM042;
                }
            }
            else if ( CRMParameter.CRM_PARAMETER.getParameter().equalsIgnoreCase( inPojoName ) )
            {
                masterDataDto = getMasterDataDao().getCRMParameterPojoListForPrmtrGroup( inMasterDataDto );
                if ( !masterDataDto.getCrmParameterPojos().isEmpty() )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM042;
                }
            }
            else if ( CRMParameter.CRM_ACTIVE_PARAMETER_GROUP.getParameter().equalsIgnoreCase( inPojoName ) )
            {
                LOGGER.info( "Going for get active parameter group" );
                List<GroupsPojo> groupPojoList = getMasterDataDao().getActiveGroup( CRMParameter.PARAMETER_GROUP
                                                                                            .getParameter() );
                if ( groupPojoList.size() > 0 )
                {
                    LOGGER.info( "Size of Pojo" + groupPojoList.size() );
                    masterDataDto.setGroupsPojoList( groupPojoList );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM042;
                }
            }
            else if ( CRMParameter.CRM_ACTIVE_ROLE_GROUP.getParameter().equalsIgnoreCase( inPojoName ) )
            {
                LOGGER.info( "Going for get active roles group" );
                List<GroupsPojo> groupPojoList = getMasterDataDao().getActiveGroup( CRMParameter.ROLES_GROUP
                                                                                            .getParameter() );
                if ( groupPojoList.size() > 0 )
                {
                    LOGGER.info( "Size of Pojo" + groupPojoList.size() );
                    masterDataDto.setGroupsPojoList( groupPojoList );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM042;
                }
            }
            else
            {
                LOGGER.info( "Incorrect Pojo Name" );
                crmServiceCode = CRMServiceCode.CRM041;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Found Error in Get Master Data Method" );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        masterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        masterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return masterDataDto;
    }

    @Override
    public MasterDataDto rolesGroupOperations( MasterDataDto inMasterDataDto, String inOperation )
    {
        LOGGER.info( "In Roles Group Operations Method" );
        CRMServiceCode crmServiceCode = null;
        boolean process = false;
        try
        {
            if ( StringUtils.isValidObj( inMasterDataDto.getGroupsPojo() ) )
            {
                if ( ServiceParameter.CREATE.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Create Roles Group " );
                    inMasterDataDto = getMasterDataDao().createCRMRolesGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from createGroup method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( ServiceParameter.MODIFY.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Update Roles Group " );
                    inMasterDataDto = getMasterDataDao().updateCRMRolesGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from update Group method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( ServiceParameter.SEARCH.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Search Roles Group " );
                    inMasterDataDto = getMasterDataDao().searchGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from Search Group method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( CRMParameter.CHANGE_STATUS.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Change the Status Roles Group " );
                    inMasterDataDto = getMasterDataDao().changeStatusGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from Change the Status Group method" + inMasterDataDto.getStatusDesc() );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM007;
                    LOGGER.info( ":::::::::::::::::::::::::::Operation is not correct :::::::::::::::::::::" );
                    process = true;
                }
            }
            else
            {
                LOGGER.info( "Find Null in Group Pojo" );
                crmServiceCode = CRMServiceCode.CRM013;
                process = true;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Error in Roles Group Operation method method::", ex );
            process = true;
        }
        if ( process )
        {
            inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
            inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto parameterGroupOperations( MasterDataDto inMasterDataDto, String inOperation )
        throws SOAPException
    {
        LOGGER.info( "In Parameter Group Operations Method" );
        CRMServiceCode crmServiceCode = null;
        boolean process = false;
        try
        {
            if ( StringUtils.isValidObj( inMasterDataDto.getGroupsPojo() ) )
            {
                if ( ServiceParameter.CREATE.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Create Parameter Group " );
                    inMasterDataDto = getMasterDataDao().createCRMParameterGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from createParameterGroup method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( ServiceParameter.MODIFY.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Modify Parameter Group " );
                    inMasterDataDto = getMasterDataDao().updateCRMParameterGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from updateParameterGroup method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( ServiceParameter.SEARCH.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Search Parameter Group " );
                    inMasterDataDto = getMasterDataDao().searchGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from Search Group method" + inMasterDataDto.getStatusDesc() );
                }
                else if ( CRMParameter.CHANGE_STATUS.getParameter().equalsIgnoreCase( inOperation ) )
                {
                    LOGGER.info( "Going to Change the Status Parameter Group " );
                    inMasterDataDto = getMasterDataDao().changeStatusGroup( inMasterDataDto );
                    LOGGER.info( "Get Status from Change the Status Group method" + inMasterDataDto.getStatusDesc() );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM007;
                    LOGGER.info( "Provided Operation Is Not Correct" );
                    process = true;
                }
            }
            else
            {
                LOGGER.info( "Find Null in Group Pojo" );
                crmServiceCode = CRMServiceCode.CRM013;
                process = true;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Error in Parameter Group Operation method method::", ex );
            process = true;
        }
        if ( process )
        {
            inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
            inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto masterOperation( String inServiceParam, String inMasterType, MasterDataDto inMasterDataDto )
        throws SOAPException
    {
        LOGGER.info( "In Master Operation Method" );
        CRMServiceCode crmServiceCode = null;
        boolean process = false;
        try
        {
            if ( StringUtils.isNotBlank( inServiceParam ) && StringUtils.isValidObj( inMasterDataDto )
                    && StringUtils.isNotBlank( inMasterType ) )
            {
                if ( StringUtils.equals( inServiceParam, ServiceParameter.CREATE.getParameter() ) )
                {
                    if ( StringUtils.equals( CRMParameter.OPTION82.getParameter(), inMasterType ) )
                    {
                        LOGGER.info( "Going to Create option82 " );
                        inMasterDataDto = getMasterDataDao().createOption82( inMasterDataDto );
                    }
                    else if ( masterTypes.contains( inMasterType ) )
                    {
                        LOGGER.info( " Going to configure of master type " );
                        List<CrmMasterPojo> crmMasterPojo = null;
                        Object obj = null;
                        if ( StringUtils.isValidObj( inMasterDataDto.getEmailServerPojo() ) )
                        {
                            process = true;
                            obj = inMasterDataDto.getEmailServerPojo();
                            crmMasterPojo = CRMServiceUtils.getMasterData( inMasterDataDto.getEmailServerPojo() );
                        }
                        else if ( StringUtils.isValidObj( inMasterDataDto.getSmsGatewayPojo() ) )
                        {
                            process = true;
                            obj = inMasterDataDto.getSmsGatewayPojo();
                            crmMasterPojo = CRMServiceUtils.getMasterData( inMasterDataDto.getSmsGatewayPojo() );
                        }
                        else if ( StringUtils.isValidObj( inMasterDataDto.getAlertMasterPojo() ) )
                        {
                            process = true;
                            obj = inMasterDataDto.getAlertMasterPojo();
                            crmMasterPojo = CRMServiceUtils.getMasterData( inMasterDataDto.getAlertMasterPojo() );
                        }
                        if ( process && StringUtils.isValidObj( crmMasterPojo ) )
                        {
                            LOGGER.info( "CRM Master Pojos Size:" + crmMasterPojo.size() );
                            boolean toCreate = CRMServiceUtils.marshallMasterData( crmMasterPojo, obj,
                                                                                   inMasterDataDto.getUserID() );
                            LOGGER.info( "CRM Master Pojos To Create: " + toCreate );
                            inMasterDataDto = getMasterDataDao().updateMasterData( crmMasterPojo, toCreate );
                            process = true;
                        }
                        else
                        {
                            inMasterDataDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
                        }
                    }
                    else if ( StringUtils.equals( inMasterType, RemarksPojo.class.getSimpleName() ) )
                    {
                        crmServiceCode = CRMServiceCode.CRM999;
                        inMasterDataDto.getRemarks().setCreatedTime( Calendar.getInstance().getTime() );
                        long generatedId = CRMServiceUtils.saveDBValues( inMasterDataDto.getRemarks() );
                        if ( generatedId > 0 )
                        {
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                    else if ( StringUtils.equals( inMasterType, CRMParameter.CRM_RCA_REASON.getParameter() ) )
                    {
                        inMasterDataDto = getMasterDataDao().createAndUpdateCategoryValue( inMasterDataDto );
                    }
                    else if ( StringUtils.equals( CrmHolidayDetails.class.getSimpleName(), inMasterType ) )
                    {
                        inMasterDataDto = getMasterDataDao().postHolidayDetails( inMasterDataDto );
                    }
                }
                else if ( StringUtils.equals( inServiceParam, ServiceParameter.SEARCH.getParameter() ) )
                {
                    if ( StringUtils.equals( CRMParameter.OPTION82.getParameter(), inMasterType ) )
                    {
                        LOGGER.info( "Going to Search option82 " );
                        inMasterDataDto = getMasterDataDao().searchOption82( inMasterDataDto );
                    }
                }
                else if ( StringUtils.equals( inServiceParam, ServiceParameter.LIST.getParameter() ) )
                {
                    if ( StringUtils.equals( inMasterType, CRMParameter.CRM_RCA_REASON.getParameter() ) )
                    {
                        inMasterDataDto = getMasterDataDao().searchAllCategoryValue( inMasterDataDto );
                    }
                    else if ( StringUtils.equals( inMasterType, CRMParameter.PLAN_DETAILS_MASTER.getParameter() ) )
                    {
                        inMasterDataDto = getMasterDataDao().getAllPlanDetails( inMasterDataDto );
                    }
                    else if ( StringUtils.equals( inMasterType, CRMParameter.CRM_PARTNER_NETWORK_CONFIG.getParameter() ) )
                    {
                        inMasterDataDto = getMasterDataDao().getPartnerNetworkConfig( inMasterDataDto );
                    }
                    else if ( StringUtils.equals( CrmHolidayDetails.class.getSimpleName(), inMasterType ) )
                    {
                        inMasterDataDto = getMasterDataDao().getHoldayDetails( inMasterDataDto );
                    }
                    else if ( StringUtils.equals( CrmUserPojo.class.getSimpleName(), inMasterType ) )
                    {
                        inMasterDataDto = getMasterDataDao().getAssociatSRWithBP( inMasterDataDto );
                    }
                }
                else if ( StringUtils.equals( inServiceParam, ServiceParameter.VIEW.getParameter() ) )
                {
                    if ( StringUtils.equals( inMasterType, CrmRcaReasonPojo.class.getSimpleName() ) )
                    {
                        inMasterDataDto.setCrmRcaReason( CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class,
                                                                                      inMasterDataDto.getCrmRcaReason()
                                                                                              .getCategoryId() ) );
                    }
                }
                else if ( StringUtils.equals( inServiceParam, ServiceParameter.UPDATE.getParameter() ) )
                {
                    if ( StringUtils.equals( inMasterType, AllInboxPojo.class.getSimpleName() ) )
                    {
                        inMasterDataDto = getMasterDataDao().updateUnreadInbox( inMasterDataDto );
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM007;
                    LOGGER.info( "Provided Operation Is Not Correct" );
                    process = true;
                }
            }
            else
            {
                LOGGER.info( "master Dto is null" );
                crmServiceCode = CRMServiceCode.CRM013;
                process = true;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Error in master operations", ex );
            process = true;
        }
        finally
        {
            if ( StringUtils.isValidObj( crmServiceCode ) )
            {
                inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
                inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
        }
        return inMasterDataDto;
    }
}
