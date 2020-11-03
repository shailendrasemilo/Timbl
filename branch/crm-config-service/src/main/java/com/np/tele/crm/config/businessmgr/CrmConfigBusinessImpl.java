package com.np.tele.crm.config.businessmgr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.np.tele.crm.billing.dao.ICRMBillingTransactionDao;
import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.ext.pojos.AlertMasterPojo;
import com.np.tele.crm.ext.pojos.AtomPgwPojo;
import com.np.tele.crm.ext.pojos.EmailServerPojo;
import com.np.tele.crm.ext.pojos.HDFCPgwPojo;
import com.np.tele.crm.ext.pojos.PlanMigrationPolicyPojo;
import com.np.tele.crm.ext.pojos.SmsGatewayPojo;
import com.np.tele.crm.ext.pojos.TechProcessPgwPojo;
import com.np.tele.crm.ext.pojos.UserMasterPojo;
import com.np.tele.crm.ext.pojos.ValidationMasterPojo;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmPaymentCentresPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmConfigBusinessImpl
    implements ICrmConfigBusiness
{
    private static final Logger       LOGGER                = Logger.getLogger( CrmConfigBusinessImpl.class );
    private IConfigManagerDao         configManagerDao      = null;
    private ICRMBillingTransactionDao billingTransactionDao = null;
    private ICrmBillingManager        crmBillingManager;

    public ICRMBillingTransactionDao getBillingTransactionDao()
    {
        return billingTransactionDao;
    }

    public void setBillingTransactionDao( ICRMBillingTransactionDao inBillingTransactionDao )
    {
        billingTransactionDao = inBillingTransactionDao;
    }

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager inCrmBillingManager )
    {
        crmBillingManager = inCrmBillingManager;
    }

    @Override
    public ConfigDto configOperations( String inServiceParam, String inConfigParam, ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside CrmConfigBusinessImpl: configOperations" );
        ConfigDto configDto = new ConfigDto();
        try
        {
            if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( EmailServerPojo.class.getSimpleName(), inConfigParam ) )
                {
                    List<EmailServerPojo> emailServers = getEmailServers( getConfigManagerDao()
                            .getConfiguration( "EMAIL", null ) );
                    configDto.setEmailServers( emailServers );
                }
                else if ( StringUtils.equals( SmsGatewayPojo.class.getSimpleName(), inConfigParam ) )
                {
                    List<SmsGatewayPojo> smsGateways = getSmsGateways( getConfigManagerDao().getConfiguration( "SMS",
                                                                                                               null ) );
                    configDto.setSmsGateways( smsGateways );
                }
                else if ( StringUtils.equals( HDFCPgwPojo.class.getSimpleName(), inConfigParam ) )
                {
                    HDFCPgwPojo pgwPojo = new HDFCPgwPojo();
                    getMasterData( getConfigManagerDao().getConfiguration( "PGW", "HDFC" ), pgwPojo );
                    configDto.setHdfcPgwPojo( pgwPojo );
                }
                else if ( StringUtils.equals( AtomPgwPojo.class.getSimpleName(), inConfigParam ) )
                {
                    AtomPgwPojo pgwPojo = new AtomPgwPojo();
                    getMasterData( getConfigManagerDao().getConfiguration( "PGW", "ATOM" ), pgwPojo );
                    configDto.setAtomPgwPojo( pgwPojo );
                }
                else if ( StringUtils.equals( TechProcessPgwPojo.class.getSimpleName(), inConfigParam ) )
                {
                    TechProcessPgwPojo pgwPojo = new TechProcessPgwPojo();
                    getMasterData( getConfigManagerDao().getConfiguration( "PGW", "TP" ), pgwPojo );
                    configDto.setTechProcessPgwPojo( pgwPojo );
                }
                else if ( StringUtils.equals( PlanMigrationPolicyPojo.class.getSimpleName(), inConfigParam ) )
                {
                    PlanMigrationPolicyPojo planMigrationPolicyPojo = inConfigDto.getPlanMigrationPolicyPojo();
                    getMasterData( getConfigManagerDao().getConfiguration( planMigrationPolicyPojo.getCategory(),
                                                                           planMigrationPolicyPojo.getSubCategory() ),
                                   planMigrationPolicyPojo );
                    configDto.setPlanMigrationPolicyPojo( planMigrationPolicyPojo );
                }
                else if ( StringUtils.equals( CRMParameter.USER_ACCESS.getParameter(), inConfigParam ) )
                {
                    UserMasterPojo userMaster = getUserAccess( getConfigManagerDao()
                            .getConfiguration( CRMParameter.USER.getParameter(), CRMParameter.ACCESS.getParameter() ) );
                    LOGGER.info( "User Access Pojo:" + userMaster );
                    configDto.setUserMasterPojo( userMaster );
                }
                else if ( StringUtils.equals( CRMParameter.ALERTS_MASTER.getParameter(), inConfigParam ) )
                {
                    AlertMasterPojo alertMaster = getAlertMasters( getConfigManagerDao()
                            .getConfiguration( CRMParameter.ALERT.getParameter(), CRMParameter.MASTER.getParameter() ) );
                    LOGGER.info( "AlertMasterPojo :" + alertMaster );
                    configDto.setAlertMasterPojo( alertMaster );
                }
                else if ( StringUtils.equals( CRMParameter.VALIDATION_MASTER.getParameter(), inConfigParam ) )
                {
                    ValidationMasterPojo validationMaster = getValidationMasters( getConfigManagerDao()
                            .getConfiguration( CRMParameter.VALDATION.getParameter(),
                                               CRMParameter.MASTER.getParameter() ) );
                    LOGGER.info( "ValidationMasterPojo :" + validationMaster );
                    configDto.setValidationMaster( validationMaster );
                }
                else if ( StringUtils.equals( CRMParameter.LMS_REFERRAL_SOURCE.getParameter(), inConfigParam ) )
                {
                    List<CrmRcaReasonPojo> crmRcaReasonPojos = getConfigManagerDao()
                            .getRCAReasonList( CRMParameter.LMS.getParameter(), CRMParameter.REFERRAL.getParameter() );
                    LOGGER.info( "Size of CRM RCA Reason Pojo List :" + crmRcaReasonPojos.size() );
                    configDto.setCrmRcaReasons( crmRcaReasonPojos );
                }
                else if ( StringUtils.equals( CRMParameter.LMS_CLOSE_REASON.getParameter(), inConfigParam ) )
                {
                    List<CrmRcaReasonPojo> crmCloseReasonPojos = getConfigManagerDao()
                            .getRCAReasonList( CRMParameter.LMS.getParameter(), CRMParameter.REASON.getParameter() );
                    LOGGER.info( "Size of CRM close Reason Pojo List :" + crmCloseReasonPojos.size() );
                    configDto.setCrmCloseReasons( crmCloseReasonPojos );
                }
                else if ( StringUtils.equals( CRMParameter.INA_REFUSEL_REASON.getParameter(), inConfigParam ) )
                {
                    List<CrmRcaReasonPojo> crmCloseReasonPojos = getConfigManagerDao()
                            .getRCAReasonList( CRMParameter.INA.getParameter(), CRMParameter.REASON.getParameter() );
                    LOGGER.info( "Size of CRM Reason For Refusel List :" + crmCloseReasonPojos.size() );
                    configDto.setCrmCloseReasons( crmCloseReasonPojos );
                }
                else if ( StringUtils.equals( CrmCustomerDetailsPojo.class.getSimpleName(), inConfigParam ) )
                {
                    configDto = getConfigManagerDao().getCustomerDetails( inConfigDto );
                }
                else if ( StringUtils.equals( CRMParameter.EVENTS.getParameter(), inConfigParam ) )
                {
                    configDto = getConfigManagerDao().getEvents( inConfigDto );
                }
                else if ( StringUtils.equals( CRMParameter.DISTRIBUTOR_INVENTORY_LIST.getParameter(), inConfigParam ) )
                {
                    getCrmBillingManager().getDistributorInventoryList( inConfigDto );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inConfigDto.getStatusCode() )
                            && StringUtils.isValidObj( inConfigDto.getMacAddressList() ) )
                    {
                        if ( inConfigDto.getMacAddressList().size() < 3 )
                        {
                            Map<String, String> paramValues = new HashMap<String, String>();
                            paramValues.put( "LCONAME", inConfigDto.getServicePartner() );
                            paramValues.put( "INVENTORYSIZE", inConfigDto.getMacAddressList().size() + "" );
                            paramValues
                                    .put( CRMParameter.INVENTORYEMAIL.getParameter(), "shailendrasemilo@gmail.com" ); // eocnetwork@nextraworld.com
                            CRMServiceUtils.sendAlerts( CRMEvents.INVENTORY_SHORTAGE, CRMRequestType.OTHERS,
                                                        inConfigDto.getMacAddressList().size() + "", null, paramValues );
                        }
                    }
                    configDto = inConfigDto;
                }
                else if ( StringUtils.equals( CrmPaymentCentresPojo.class.getSimpleName(), inConfigParam ) )
                {
                    if ( !StringUtils.isValidObj( inConfigDto ) )
                    {
                        inConfigDto = configDto;
                    }
                    configDto = getConfigManagerDao().getPaymentCenters( inConfigDto );
                }
                else if ( StringUtils.equals( CrmTicketHistoryPojo.class.getSimpleName(), inConfigParam ) )
                {
                    if ( !StringUtils.isValidObj( inConfigDto ) )
                    {
                        inConfigDto = configDto;
                    }
                    configDto.setTicketHistoryList( CRMServiceUtils.getDBValueList( CrmTicketHistoryPojo.class,
                                                                                    "ticketId",
                                                                                    inConfigDto.getMappingId() ) );
                }
                else if ( StringUtils.equals( CRMParameter.POD_FILE_UPLOAD.getParameter(), inConfigParam ) )
                {
                    configDto = getConfigManagerDao().listUploadedFiles( inConfigDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.UPDATE.getParameter(), inServiceParam ) )
            {
                CRMServiceCode serviceCode = CRMServiceCode.CRM001;
                if ( StringUtils.equals( CRMParameter.INBOX.getParameter(), inConfigParam ) )
                {
                    serviceCode = updateInbox( inConfigDto );
                }
                configDto.setStatusCode( serviceCode.getStatusCode() );
                configDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        return configDto;
    }

    private <E> void getMasterData( List<CrmMasterPojo> inConfigList, E e )
    {
        if ( StringUtils.isValidObj( e ) && CommonValidator.isValidCollection( inConfigList ) )
        {
            for ( CrmMasterPojo masterPojo : inConfigList )
            {
                try
                {
                    Object catValue = masterPojo.getCategoryValue();
                    if ( StringUtils.equals( "B", masterPojo.getDataType() ) )
                    {
                        catValue = Boolean.parseBoolean( masterPojo.getCategoryValue() );
                    }
                    else if ( StringUtils.equals( "I", masterPojo.getDataType() )
                            && StringUtils.isNumeric( masterPojo.getCategoryValue() ) )
                    {
                        catValue = Integer.parseInt( masterPojo.getCategoryValue() );
                    }
                    org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, masterPojo.getSubSubCategory(),
                                                                                  catValue );
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "Exception:", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "Exception:", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    LOGGER.error( "Exception:", ex );
                }
            }
        }
    }

    private CRMServiceCode updateInbox( ConfigDto inConfigDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM001;
        if ( inConfigDto.getInboxId() > 0 )
        {
            CrmInboxPojo inbox = CRMServiceUtils.getDBValues( CrmInboxPojo.class, inConfigDto.getInboxId() );
            if ( StringUtils.isValidObj( inbox ) )
            {
                if ( StringUtils.equals( inConfigDto.getMappingId(), inbox.getMappingId() )
                        && StringUtils.equals( inConfigDto.getUserId(), inbox.getUserId() ) )
                {
                    inbox.setUnRead( false );
                    inbox = CRMServiceUtils.mergeDBValues( inbox );
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM203;
                }
            }
            else
            {
                serviceCode = CRMServiceCode.CRM203;
            }
        }
        return serviceCode;
    }

    private ValidationMasterPojo getValidationMasters( List<CrmMasterPojo> inConfiguration )
    {
        ValidationMasterPojo masterPojo = new ValidationMasterPojo();
        CRMServiceUtils.unmarshallMasterData( inConfiguration, masterPojo );
        return masterPojo;
    }

    private AlertMasterPojo getAlertMasters( List<CrmMasterPojo> inConfiguration )
    {
        AlertMasterPojo masterPojo = new AlertMasterPojo();
        CRMServiceUtils.unmarshallMasterData( inConfiguration, masterPojo );
        return masterPojo;
    }

    private UserMasterPojo getUserAccess( List<CrmMasterPojo> inConfiguration )
    {
        UserMasterPojo accessPojo = new UserMasterPojo();
        CRMServiceUtils.unmarshallMasterData( inConfiguration, accessPojo );
        return accessPojo;
    }

    private static List<SmsGatewayPojo> getSmsGateways( List<CrmMasterPojo> inConfigList )
    {
        List<SmsGatewayPojo> smsGateways = new ArrayList<SmsGatewayPojo>();
        SmsGatewayPojo gatewayPojo = null;
        for ( CrmMasterPojo masterPojo : inConfigList )
        {
            gatewayPojo = new SmsGatewayPojo();
            gatewayPojo.setAlias( masterPojo.getCategoryAlias() );
            int pojoIndex = smsGateways.indexOf( gatewayPojo );
            if ( pojoIndex < 0 )
            {
                gatewayPojo.setCategory( masterPojo.getCategory() );
                gatewayPojo.setSubCategory( masterPojo.getSubCategory() );
                smsGateways.add( gatewayPojo );
            }
            else
            {
                gatewayPojo = smsGateways.get( pojoIndex );
            }
            try
            {
                Object catValue = masterPojo.getCategoryValue();
                if ( StringUtils.equals( "B", masterPojo.getDataType() ) )
                {
                    catValue = Boolean.parseBoolean( masterPojo.getCategoryValue() );
                }
                else if ( StringUtils.equals( "I", masterPojo.getDataType() ) )
                {
                    if ( StringUtils.isNumeric( masterPojo.getCategoryValue() ) )
                    {
                        catValue = Integer.parseInt( masterPojo.getCategoryValue() );
                    }
                }
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( gatewayPojo,
                                                                              masterPojo.getSubSubCategory(), catValue );
            }
            catch ( IllegalAccessException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( InvocationTargetException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( NoSuchMethodException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
        }
        return smsGateways;
    }

    private static List<EmailServerPojo> getEmailServers( List<CrmMasterPojo> inConfigList )
    {
        List<EmailServerPojo> emailSevers = new ArrayList<EmailServerPojo>();
        EmailServerPojo emailServerPojo = null;
        for ( CrmMasterPojo masterPojo : inConfigList )
        {
            emailServerPojo = new EmailServerPojo();
            emailServerPojo.setAlias( masterPojo.getCategoryAlias() );
            int pojoIndex = emailSevers.indexOf( emailServerPojo );
            if ( pojoIndex < 0 )
            {
                emailServerPojo.setCategory( masterPojo.getCategory() );
                emailServerPojo.setSubCategory( masterPojo.getSubCategory() );
                emailSevers.add( emailServerPojo );
            }
            else
            {
                emailServerPojo = emailSevers.get( pojoIndex );
            }
            try
            {
                Object catValue = masterPojo.getCategoryValue();
                if ( StringUtils.equals( "B", masterPojo.getDataType() ) )
                {
                    catValue = Boolean.parseBoolean( masterPojo.getCategoryValue() );
                }
                else if ( StringUtils.equals( "I", masterPojo.getDataType() )
                        && StringUtils.isNumeric( masterPojo.getCategoryValue() ) )
                {
                    catValue = Integer.parseInt( masterPojo.getCategoryValue() );
                }
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( emailServerPojo,
                                                                              masterPojo.getSubSubCategory(), catValue );
            }
            catch ( IllegalAccessException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( InvocationTargetException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( NoSuchMethodException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
        }
        return emailSevers;
    }

    /*
     * public static void main( String[] args ) { ConfigManagerDaoImpl temp1 =
     * new ConfigManagerDaoImpl(); List<CrmMasterPojo> configList =
     * temp1.getAlertsConfiguration(); List<EmailServerPojo> emailServers =
     * getEmailServers( configList ); List<SmsGatewayPojo> smsGateways =
     * getSmsGateways( configList );
     * 
     * for(EmailServerPojo emailServerPojo:emailServers) {
     * System.out.println(emailServerPojo.toString()); }
     * 
     * for(SmsGatewayPojo smsGatewayPojo:smsGateways) {
     * System.out.println(smsGatewayPojo.toString()); } }
     */
    @Override
    public ConfigDto getInbox( ConfigDto inConfigDto )
    {
        LOGGER.info( "IN getInbox Method. Inbox Type Name ::" + inConfigDto.getInboxType() );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isValidObj( inConfigDto.getUserId() )
                    && StringUtils.isValidObj( inConfigDto.getInboxType() ) )
            {
                if ( inConfigDto.getInboxType().matches( CRMRequestType.LEAD.getRequestCode() + "|"
                                                                 + CRMRequestType.CAF.getRequestCode() + "|"
                                                                 + CRMRequestType.QRC.getRequestCode() + "|"
                                                                 + CRMRequestType.SHIFTING.getRequestCode() + "|"
                                                                 + CRMRequestType.WAIVER.getRequestCode() + "|"
                                                                 + CRMRequestType.FREEZE.getRequestCode() ) )
                {
                    return getConfigManagerDao().getInbox( inConfigDto );
                }
                else if ( inConfigDto.getInboxType().matches( CRMRequestType.ALL_INBOX.getRequestCode() ) )
                {
                    inConfigDto.setInboxCRFPojos( new ArrayList<CrmCustomerDetailsPojo>() );
                    inConfigDto.setInboxLeadPojos( new ArrayList<LMSPojo>() );
                    inConfigDto.setGroupInboxCRFPojos( new ArrayList<CrmCustomerDetailsPojo>() );
                    inConfigDto.setGroupInboxLeadPojos( new ArrayList<LMSPojo>() );
                    inConfigDto.setInboxSrPojosPersonal( new ArrayList<CrmSrTicketsPojo>() );
                    inConfigDto.setInboxSrPojosGroup( new ArrayList<CrmSrTicketsPojo>() );
                    inConfigDto.setInboxType( CRMRequestType.LEAD.getRequestCode() );
                    getConfigManagerDao().getInbox( inConfigDto );
                    inConfigDto.setInboxType( CRMRequestType.CAF.getRequestCode() );
                    getConfigManagerDao().getInbox( inConfigDto );
                    inConfigDto.setInboxType( CRMRequestType.QRC.getRequestCode() );
                    getConfigManagerDao().getInbox( inConfigDto );
                    return inConfigDto;
                }
                else
                {
                    LOGGER.info( "Inbox type is invalid" );
                    serviceCode = CRMServiceCode.CRM997;
                }
            }
            else if ( StringUtils.equals( inConfigDto.getInboxType(), CRMRequestType.CAF.getRequestCode() ) )
            {
                List<CrmInboxPojo> crmInboxPojos = getConfigManagerDao().getPartnerIdFromInbox( inConfigDto );
                if ( CommonValidator.isValidCollection( crmInboxPojos ) )
                {
                    inConfigDto.setPartnerId( StringUtils.numericValue( crmInboxPojos.get( 0 ).getPartnerId() + "" ) );
                }
            }
            else
            {
                LOGGER.info( "User Id or Inbox type invalid" );
                serviceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in getInbox Method ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        inConfigDto.setStatusCode( serviceCode.getStatusCode() );
        inConfigDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto getMappingIdtByAppointMents( ConfigDto inConfigDto )
    {
        LOGGER.info( "IN getMappingIdtByAppointMents Method. Mapping Id  ::" + inConfigDto.getMappingId() );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isNotBlank( inConfigDto.getMappingId() ) )
            {
                List<AppointmentPojo> appointmentPojos = getConfigManagerDao()
                        .getMappingIdtByAppointMents( inConfigDto.getMappingId() );
                if ( StringUtils.isValidObj( appointmentPojos ) )
                {
                    inConfigDto.setAppointmentPojos( appointmentPojos );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    LOGGER.info( "Find List Is Not Valid" );
                    serviceCode = CRMServiceCode.CRM999;
                }
            }
            else
            {
                LOGGER.info( "Mapping Id Not Valid" );
                serviceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in getMappingIdtByAppointMents Method ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        inConfigDto.setStatusCode( serviceCode.getStatusCode() );
        inConfigDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto geMappingIdByRemarks( ConfigDto inConfigDto )
    {
        LOGGER.info( "IN geMappingIdByRemarks Method. Mapping Id  ::" + inConfigDto.getMappingId() );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isNotBlank( inConfigDto.getMappingId() ) )
            {
                List<RemarksPojo> remarksPojos = getConfigManagerDao()
                        .geRemarksByMappingId( inConfigDto.getMappingId() );
                if ( StringUtils.isValidObj( remarksPojos ) )
                {
                    inConfigDto.setRemarksPojos( remarksPojos );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    LOGGER.info( "Find List Is Not Valid" );
                    serviceCode = CRMServiceCode.CRM999;
                }
            }
            else
            {
                LOGGER.info( "Mapping Id Not Valid" );
                serviceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in geMappingIdByRemarks Method ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        inConfigDto.setStatusCode( serviceCode.getStatusCode() );
        inConfigDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto changeInboxBin( ConfigDto inConfigDto )
    {
        LOGGER.info( "IN changeInboxBin Method. Mapping Id  ::" + inConfigDto.getMappingId() );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isNotBlank( inConfigDto.getMappingId() )
                    && StringUtils.isNotBlank( inConfigDto.getRequestType() )
                    && StringUtils.isNotBlank( inConfigDto.getFromStage() )
                    && StringUtils.isNotBlank( inConfigDto.getTostage() )
                    && StringUtils.isNotBlank( inConfigDto.getUserId() )
                    && ( StringUtils.isNotBlank( inConfigDto.getFromUserId() ) || StringUtils.isNotBlank( inConfigDto
                            .getToUserId() ) ) )
            {
                LOGGER.info( "Required detail mapping id " + inConfigDto.getMappingId() + "  request type::"
                        + inConfigDto.getRequestType() + " from stage:: " + inConfigDto.getFromStage()
                        + "  to stage :: " + inConfigDto.getTostage() + "  User Id ::" + inConfigDto.getUserId() );
                /*
                 * boolean result = getConfigManagerDao().changeInboxBin(
                 * inConfigDto.getMappingId(), inConfigDto.getRequestType(),
                 * inConfigDto.getFromUserId(), inConfigDto.getToUserId(),
                 * inConfigDto.getFromStage(), inConfigDto.getTostage(),
                 * inConfigDto.getUserId(), inConfigDto.getPartnerId() );
                 */
                boolean result = getConfigManagerDao().changeInboxBin( inConfigDto );
                if ( result )
                {
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    LOGGER.info( "Get Result from Dao " + result );
                    serviceCode = CRMServiceCode.CRM999;
                }
            }
            else
            {
                LOGGER.info( "Required Fields Are Not Present" );
                serviceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in changeInboxBin Method ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        inConfigDto.setStatusCode( serviceCode.getStatusCode() );
        inConfigDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto saveAppointment( ConfigDto inConfigDto )
    {
        return null;
    }

    @Override
    public ConfigDto getMappedUsersByUser( ConfigDto inConfigDto )
    {
        try
        {
            LOGGER.info( "Going to Get Users For User :: " + inConfigDto.getUserId() );
            inConfigDto = getConfigManagerDao().getMappingUsers( inConfigDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getMappedUsersByUser Method ", ex );
            inConfigDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inConfigDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto auditLogOperation( String inOperationParam, ConfigDto inConfigDto )
    {
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        LOGGER.info( "IN Audit Log Operation Method. Operation Name ::" + inOperationParam );
        boolean setStatusCode = true;
        try
        {
            if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inConfigDto.getAuditLogPojo() )
                        && StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getEvents() )
                        && StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getMappingId() )
                        && StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getModule() ) )
                {
                    inConfigDto = getConfigManagerDao().insertAuditLog( inConfigDto );
                    setStatusCode = false;
                }
                else
                {
                    LOGGER.info( "Manadatory Fields Not Available In Mehthod auditLogOperation For Operation ::"
                            + inOperationParam );
                    statuCode = CRMServiceCode.CRM997;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inOperationParam ) )
            {
                // user can search mapping and module wise \\and user wise
                if ( ( StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getMappingId() ) && StringUtils
                        .isNotBlank( inConfigDto.getAuditLogPojo().getModule() ) )
                        || StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getCreatedBy() ) )
                {
                    inConfigDto = getConfigManagerDao().searchAuditLog( inConfigDto );
                    setStatusCode = false;
                }
                else
                {
                    LOGGER.info( "Manadatory Fields Not Available In Mehthod auditLogOperation For Operation ::"
                            + inOperationParam );
                    statuCode = CRMServiceCode.CRM997;
                }
            }
            else
            {
                LOGGER.info( "Operation Name Is Not Correct. Operation Name ::" + inOperationParam );
                statuCode = CRMServiceCode.CRM007;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in lmsOperation Method ", ex );
            statuCode = CRMServiceCode.CRM997;
        }
        finally
        {
            if ( setStatusCode )
            {
                inConfigDto.setStatusCode( statuCode.getStatusCode() );
                inConfigDto.setStatusDesc( statuCode.getStatusDesc() );
            }
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto customerActivityOps( String inOperationParam, ConfigDto inConfigDto )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        LOGGER.info( "In customerActivityOps Method. Operation Name ::" + inOperationParam );
        boolean setStatusCode = true;
        try
        {
            if ( StringUtils.isValidObj( inConfigDto ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inOperationParam ) )
                {
                    inConfigDto = getConfigManagerDao().insertCustomerActivity( inConfigDto );
                    setStatusCode = false;
                }
                else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inOperationParam ) )
                {
                    getConfigManagerDao().searchCustomerActivity( inConfigDto );
                    setStatusCode = false;
                }
                else if ( StringUtils.equals( ServiceParameter.APICALL.getParameter(), inOperationParam ) )
                {
                    inConfigDto = getBillingTransactionDao().saveTransactionPOJO( inConfigDto );
                }
                else
                {
                    LOGGER.info( "Operation name is not correct. Operation Name ::" + inOperationParam );
                    statusCode = CRMServiceCode.CRM007;
                }
            }
            else
            {
                statusCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in customerActivityOps Method ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            if ( setStatusCode )
            {
                inConfigDto.setStatusCode( statusCode.getStatusCode() );
                inConfigDto.setStatusDesc( statusCode.getStatusDesc() );
            }
        }
        return inConfigDto;
    }
}
