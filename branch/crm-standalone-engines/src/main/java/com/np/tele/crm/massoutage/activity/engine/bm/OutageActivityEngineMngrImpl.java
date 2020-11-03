package com.np.tele.crm.massoutage.activity.engine.bm;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CrmConfigClient;
import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmMassOutageMastersPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmMassOutageSocietyPojo;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class OutageActivityEngineMngrImpl
    implements IMassOutageActivityEngineManager
{
    private static final Logger LOGGER = Logger.getLogger( OutageActivityEngineMngrImpl.class );

    @Override
    public void getListOfMassOutage()
    {
        LOGGER.info( "Inside getListOfMassOutage" );
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        try
        {
            qrcService = new CrmQrcClient();
            massOutageDto = new CrmMassOutageDto();
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.MAPPING_LIST.getParameter(),
                                                             massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( massOutageDto.getMassOutagePojos() ) )
            {
                LOGGER.info( "MassOutagePojos size ::" + massOutageDto.getMassOutagePojos().size() );
                for ( CrmMassOutagePojo outagePojo : massOutageDto.getMassOutagePojos() )
                {
                    if ( StringUtils.equals( outagePojo.getServiceName(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                    {
                        if ( StringUtils.equals( outagePojo.getOutageActivity(), IAppConstants.SINGLE_PARAM ) )
                        {
                            if ( StringUtils.equals( outagePojo.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                            {
                                //Resolved 
                                if ( getOutageMasterName( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    CRMStatusCode.RESOLVED.getStatusCode() );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                            else
                            {
                                LOGGER.info( "Outage is still in progress: " + outagePojo.getOutageId() );
                            }
                        }
                        else if ( StringUtils.equals( outagePojo.getOutageActivity(), IAppConstants.Y ) )
                        {
                            if ( StringUtils.equals( outagePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                //ADD 
                                if ( getOutageMasterName( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    IAppConstants.SINGLE_PARAM );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                            else if ( StringUtils.equals( outagePojo.getStatus(),
                                                          CRMStatusCode.RESOLVED.getStatusCode() ) )
                            {
                                if ( getOutageMasterName( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    IAppConstants.SINGLE_PARAM );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                                if ( getOutageMasterName( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    CRMStatusCode.RESOLVED.getStatusCode() );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                        }
                    }
                    else
                    {
                        if ( StringUtils.equals( outagePojo.getOutageActivity(), IAppConstants.SINGLE_PARAM ) )
                        {
                            if ( StringUtils.equals( outagePojo.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                            {
                                //Resolved
                                if ( getOutageSocietyList( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    CRMStatusCode.RESOLVED.getStatusCode() );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                            else
                            {
                                LOGGER.info( "Outage is still in progress: " + outagePojo.getOutageId() );
                            }
                        }
                        else if ( StringUtils.equals( outagePojo.getOutageActivity(), IAppConstants.Y ) )
                        {
                            if ( StringUtils.equals( outagePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                //ADD
                                if ( getOutageSocietyList( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    IAppConstants.SINGLE_PARAM );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                            else if ( StringUtils.equals( outagePojo.getStatus(),
                                                          CRMStatusCode.RESOLVED.getStatusCode() ) )
                            {
                                if ( getOutageSocietyList( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    IAppConstants.SINGLE_PARAM );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                                if ( getOutageMasterName( outagePojo ) )
                                {
                                    updateMassOutageActivityStatus( outagePojo.getOutageId(),
                                                                    CRMStatusCode.RESOLVED.getStatusCode() );
                                }
                                else
                                {
                                    LOGGER.info( "MassOutage Pojo is not Update with outage ID: "
                                            + outagePojo.getOutageId() );
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                LOGGER.info( "Outage Master not found for current Date: " + Calendar.getInstance().getTime() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception while getListOfMassOutage from Massoutage", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getListOfMassOutage from Massoutage. ", ex );
        }
    }

    private boolean getOutageSocietyList( CrmMassOutagePojo inOutagePojo )
    {
        LOGGER.info( "Inside getOutageSocietyList" );
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        CrmMassOutageSocietyPojo massOutageSocietyPojo = null;
        Set<Long> resolvedSocietyList = new HashSet<Long>();
        Set<Long> activeSocietyList = new HashSet<Long>();
        boolean toProcess = false;
        try
        {
            qrcService = new CrmQrcClient();
            massOutageDto = new CrmMassOutageDto();
            massOutageSocietyPojo = new CrmMassOutageSocietyPojo();
            massOutageSocietyPojo.setOutageId( inOutagePojo.getOutageId() );
            massOutageDto.setCrmMassOutageSocietyPojo( massOutageSocietyPojo );
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.SEARCH_OUTAGE_SOCIETY.getParameter(),
                                                             massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( massOutageDto.getCrmMassOutageSocietyPojos() ) )
            {
                LOGGER.info( "Size of OutageSocietyPojos::" + massOutageDto.getCrmMassOutageSocietyPojos().size() );
                for ( CrmMassOutageSocietyPojo outageSocietyPojo : massOutageDto.getCrmMassOutageSocietyPojos() )
                {
                    if ( StringUtils.isValidObj( outageSocietyPojo.getResolvedOn() )
                            && StringUtils.equals( outageSocietyPojo.getOutageActivity(), IAppConstants.SINGLE_PARAM ) )
                    {
                        resolvedSocietyList.add( outageSocietyPojo.getSocietyId() );
                    }
                    else if ( StringUtils.equals( outageSocietyPojo.getOutageActivity(), IAppConstants.Y ) )
                    {
                        activeSocietyList.add( outageSocietyPojo.getSocietyId() );
                        if ( StringUtils.isValidObj( outageSocietyPojo.getResolvedOn() ) )
                        {
                            resolvedSocietyList.add( outageSocietyPojo.getSocietyId() );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( resolvedSocietyList ) )
                {
                    massOutageDto.getSocietyList().addAll( resolvedSocietyList );
                    massOutageDto = qrcService.massOutageOperations( ServiceParameter.CUSTOMER_LIST.getParameter(),
                                                                     massOutageDto );
                    if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && CommonValidator.isValidCollection( massOutageDto.getCustomerList() ) )
                    {
                        LOGGER.info( "Size of Resolve outage Customer List ::" + massOutageDto.getCustomerList().size() );
                        toProcess = saveResolveOutageActivity( massOutageDto.getCustomerList(), inOutagePojo );
                        if ( toProcess )
                        {
                            toProcess = updateMassOutageSocietyStatus( resolvedSocietyList,
                                                                       CRMStatusCode.RESOLVED.getStatusCode() );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( activeSocietyList ) )
                {
                    massOutageDto.getSocietyList().clear();
                    massOutageDto.getSocietyList().addAll( activeSocietyList );
                    massOutageDto = qrcService.massOutageOperations( ServiceParameter.CUSTOMER_LIST.getParameter(),
                                                                     massOutageDto );
                    if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && CommonValidator.isValidCollection( massOutageDto.getCustomerList() ) )
                    {
                        LOGGER.info( "Size of Active outage Customer List ::" + massOutageDto.getCustomerList().size() );
                        toProcess = saveActiveMassOutageActivity( massOutageDto.getCustomerList(), inOutagePojo );
                        if ( toProcess )
                        {
                            toProcess = updateMassOutageSocietyStatus( activeSocietyList, IAppConstants.SINGLE_PARAM );
                        }
                    }
                }
                if ( StringUtils.equals( inOutagePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                        && CommonValidator.isValidCollection( resolvedSocietyList ) )
                {
                    toProcess = false;
                }
            }
            else
            {
                LOGGER.info( "NO Found : Outage Society" + inOutagePojo.getOutageId() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getOutageSocietyList from Massoutage. ", ex );
        }
        return toProcess;
    }

    private boolean updateMassOutageSocietyStatus( Set<Long> resolvedSocietyList, String outageActivity )
    {
        LOGGER.info( "Update MassOutage society after activity complete : updateMassOutageSocietyStatus" );
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        CrmMassOutageSocietyPojo massOutageSocietyPojo = null;
        boolean furtherProcess = true;
        try
        {
            massOutageDto = new CrmMassOutageDto();
            massOutageSocietyPojo = new CrmMassOutageSocietyPojo();
            massOutageSocietyPojo.setOutageActivity( outageActivity );
            qrcService = new CrmQrcClient();
            massOutageDto.setCrmMassOutageSocietyPojo( massOutageSocietyPojo );
            massOutageDto.getSocietyList().addAll( resolvedSocietyList );
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.UPDATE_STATUS.getParameter(),
                                                             massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Update MassOutage with Activity status " + outageActivity );
            }
            else
            {
                furtherProcess = false;
                LOGGER.info( "MassOutage is not update" );
            }
        }
        catch ( Exception ex )
        {
            furtherProcess = false;
            LOGGER.error( "Exception while updateMassOutageSocietyStatus. ", ex );
        }
        return furtherProcess;
    }

    private boolean getOutageMasterName( CrmMassOutagePojo inOutagePojo )
    {
        LOGGER.info( "Inside getOutageMasterName" );
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        CrmMassOutageMastersPojo outageMastersPojo = null;
        Set<String> resolvedMastersList = new HashSet<String>();
        Set<String> activeMastersList = new HashSet<String>();
        boolean furtherProcess = true;
        try
        {
            qrcService = new CrmQrcClient();
            massOutageDto = new CrmMassOutageDto();
            outageMastersPojo = new CrmMassOutageMastersPojo();
            outageMastersPojo.setOutageId( inOutagePojo.getOutageId() );
            massOutageDto.setCrmMassOutageMastersPojo( outageMastersPojo );
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.SEARCH.getParameter(), massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( massOutageDto.getOutageMastersPojos() ) )
            {
                LOGGER.info( "Size of OutageMastersPojos::" + massOutageDto.getOutageMastersPojos().size() );
                for ( CrmMassOutageMastersPojo massOutageMastersPojo : massOutageDto.getOutageMastersPojos() )
                {
                    if ( StringUtils.equals( massOutageMastersPojo.getOutageActivity(), IAppConstants.SINGLE_PARAM ) )
                    {
                        if ( StringUtils.isValidObj( massOutageMastersPojo.getResolvedOn() ) )
                        {
                            resolvedMastersList.add( massOutageMastersPojo.getMasterName() );
                        }
                    }
                    else if ( StringUtils.equals( massOutageMastersPojo.getOutageActivity(), IAppConstants.Y ) )
                    {
                        activeMastersList.add( massOutageMastersPojo.getMasterName() );
                        if ( StringUtils.isValidObj( massOutageMastersPojo.getResolvedOn() ) )
                        {
                            resolvedMastersList.add( massOutageMastersPojo.getMasterName() );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( activeMastersList ) )
                {
                    massOutageDto.getMasterList().addAll( activeMastersList );
                    massOutageDto = qrcService.massOutageOperations( ServiceParameter.CUSTOMER_LIST.getParameter(),
                                                                     massOutageDto );
                    if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && CommonValidator.isValidCollection( massOutageDto.getCustomerList() ) )
                    {
                        LOGGER.info( "Size of Active outage Customer List ::" + massOutageDto.getCustomerList().size() );
                        furtherProcess = saveActiveMassOutageActivity( massOutageDto.getCustomerList(), inOutagePojo );
                        if ( furtherProcess )
                        {
                            furtherProcess = updateMassOutageMasterStatus( activeMastersList,
                                                                           IAppConstants.SINGLE_PARAM,
                                                                           inOutagePojo.getOutageId() );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( resolvedMastersList ) )
                {
                    massOutageDto.getMasterList().clear();
                    massOutageDto.getMasterList().addAll( resolvedMastersList );
                    massOutageDto = qrcService.massOutageOperations( ServiceParameter.CUSTOMER_LIST.getParameter(),
                                                                     massOutageDto );
                    if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && CommonValidator.isValidCollection( massOutageDto.getCustomerList() ) )
                    {
                        LOGGER.info( "Size of Resolve outage Customer List ::" + massOutageDto.getCustomerList().size() );
                        furtherProcess = saveResolveOutageActivity( massOutageDto.getCustomerList(), inOutagePojo );
                        if ( furtherProcess )
                        {
                            furtherProcess = updateMassOutageMasterStatus( resolvedMastersList,
                                                                           CRMStatusCode.RESOLVED.getStatusCode(),
                                                                           inOutagePojo.getOutageId() );
                        }
                    }
                }
                if ( StringUtils.equals( inOutagePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                        && CommonValidator.isValidCollection( resolvedMastersList ) )
                {
                    furtherProcess = false;
                }
            }
            else
            {
                LOGGER.info( "NO Found : Outage Master" + inOutagePojo.getOutageId() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getMasterNames from Massoutage. ", ex );
            furtherProcess = false;
        }
        return furtherProcess;
    }

    private boolean updateMassOutageMasterStatus( Set<String> resolvedMastersList,
                                                  String outageActivity,
                                                  long inOutageId )
    {
        LOGGER.info( "Update MassOutage master after activity complete : updateMassOutageMasterStatus" );
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        CrmMassOutageMastersPojo massOutageMastersPojo = null;
        boolean toProcess = true;
        try
        {
            massOutageDto = new CrmMassOutageDto();
            massOutageMastersPojo = new CrmMassOutageMastersPojo();
            massOutageMastersPojo.setOutageActivity( outageActivity );
            massOutageMastersPojo.setOutageId( inOutageId );
            qrcService = new CrmQrcClient();
            massOutageDto.setCrmMassOutageMastersPojo( massOutageMastersPojo );
            massOutageDto.getMasterList().addAll( resolvedMastersList );
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.UPDATE_STATUS.getParameter(),
                                                             massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Update MassOutage with Activity status " + outageActivity );
            }
            else
            {
                toProcess = false;
                LOGGER.info( "MassOutage is not update" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updateMassOutageActivityStatus. ", ex );
            toProcess = false;
        }
        return toProcess;
    }

    private boolean saveActiveMassOutageActivity( List<String> customerList, CrmMassOutagePojo inOutagePojo )
    {
        LOGGER.info( "In saveActiveMassOutageActivity" );
        CrmCustomerActivityPojo activityLog = null;
        ConfigDto configDto = null;
        CRMConfigService configService = null;
        boolean furtherProcess = true;
        try
        {
            activityLog = new CrmCustomerActivityPojo();
            configDto = new ConfigDto();
            configDto.setUserType( CRMCustomerActivityActions.ADD_MASSOUTAGE.getActionCode() );
            configService = new CrmConfigClient();
            configDto.getUserIds().addAll( customerList );
            activityLog.setAction( CRMCustomerActivityActions.ADD_MASSOUTAGE.getActionCode() );
            activityLog.setCreatedBy( inOutagePojo.getCreatedBy() );
            activityLog.setNewValue( "Outage Start Time : "
                    + DateUtils.convertXmlCalToString( inOutagePojo.getOutageStartTime(),
                                                       IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
            activityLog.setReason( inOutagePojo.getReason() );
            activityLog.setClientIp( CRMUtils.getServerIp() );
            activityLog.setServerIp( CRMUtils.getServerIp() );
            configDto.setCustomerActivityPojo( activityLog );
            LOGGER.info( "SMS Enable::::::: " + inOutagePojo.isSendSms() + "Outage ID: " + inOutagePojo.getOutageId() );
            configDto.setUnRead( inOutagePojo.isSendSms() );
            configDto.setMappingId( inOutagePojo.getOutageId() + "" );
            configDto = configService.customerActivityOps( ServiceParameter.CREATE.getParameter(), configDto );
            if ( StringUtils.isValidObj( configDto )
                    && StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully save add activity For Outage ID: " + inOutagePojo.getOutageId()
                        + ", and MassOutage Reason: " + inOutagePojo.getReason() );
            }
            else
            {
                furtherProcess = false;
                LOGGER.info( "Failed : Customer Activity Opration!! " );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting error in insert Active outage Customer Activity " + ex );
            furtherProcess = false;
        }
        return furtherProcess;
    }

    private boolean saveResolveOutageActivity( List<String> customerList, CrmMassOutagePojo inOutagePojo )
    {
        LOGGER.info( "In saveResolveOutageActivity" );
        CrmCustomerActivityPojo activityLog = null;
        ConfigDto configDto = null;
        CRMConfigService configService = null;
        boolean furtherProcess = true;
        try
        {
            activityLog = new CrmCustomerActivityPojo();
            configDto = new ConfigDto();
            configService = new CrmConfigClient();
            configDto.getUserIds().addAll( customerList );
            activityLog.setAction( CRMCustomerActivityActions.RESOLVED_MASSOUTAGE.getActionCode() );
            activityLog.setCreatedBy( inOutagePojo.getLastModifiedBy() );
            activityLog.setNewValue( "Outage Resolved Time : "
                    + DateUtils.convertXmlCalToString( inOutagePojo.getResolutionTime(),
                                                       IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
            activityLog.setReason( inOutagePojo.getReason() );
            activityLog.setClientIp( CRMUtils.getServerIp() );
            activityLog.setServerIp( CRMUtils.getServerIp() );
            configDto.setCustomerActivityPojo( activityLog );
            LOGGER.info( "SMS Enable::::::: " + inOutagePojo.isSendSms() + "Outage ID: " + inOutagePojo.getOutageId() );
            configDto.setUnRead( inOutagePojo.isSendSms() );
            configDto.setMappingId( inOutagePojo.getOutageId() + "" );
            configDto = configService.customerActivityOps( ServiceParameter.CREATE.getParameter(), configDto );
            if ( StringUtils.isValidObj( configDto )
                    && StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully save resolve activity For Outage ID: " + inOutagePojo.getOutageId()
                        + ", and MassOutage Reason: " + inOutagePojo.getReason() );
            }
            else
            {
                furtherProcess = false;
                LOGGER.info( "Failed : Customer Activity Opration!! " );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting error in insert Resolve Customer Activity " + ex );
            furtherProcess = false;
        }
        return furtherProcess;
    }

    private void updateMassOutageActivityStatus( long inOutageId, String inOutageActivity )
    {
        LOGGER.info( "Update MassOutage after activity complete" );
        CrmMassOutagePojo massOutagePojo = null;
        CrmMassOutageDto massOutageDto = null;
        CrmQrcService qrcService = null;
        try
        {
            massOutagePojo = new CrmMassOutagePojo();
            massOutageDto = new CrmMassOutageDto();
            qrcService = new CrmQrcClient();
            massOutagePojo.setOutageId( inOutageId );
            massOutagePojo.setOutageActivity( inOutageActivity );
            massOutageDto.setCrmMassOutagePojo( massOutagePojo );
            massOutageDto = qrcService.massOutageOperations( ServiceParameter.MODIFY_STATUS.getParameter(),
                                                             massOutageDto );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Update MassOutage with Activity status N" );
            }
            else
            {
                LOGGER.info( "MassOutage is not update" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updateMassOutageActivityStatus. ", ex );
        }
    }
}
