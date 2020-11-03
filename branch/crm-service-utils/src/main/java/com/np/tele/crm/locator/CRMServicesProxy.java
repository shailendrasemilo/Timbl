package com.np.tele.crm.locator;

import org.apache.log4j.Logger;

import com.np.tele.crm.alerts.service.IAlertsService;
import com.np.tele.crm.cap.service.ICrmCapService;
import com.np.tele.crm.config.service.ICrmConfigService;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.faultRepair.IQRCFaultRepairService;
import com.np.tele.crm.finance.service.ICrmFinanceService;
import com.np.tele.crm.gis.service.IGISService;
import com.np.tele.crm.lms.service.ILMSService;
import com.np.tele.crm.masterdata.service.IMasterDataService;
import com.np.tele.crm.qrc.service.ICrmQrcService;
import com.np.tele.crm.usrmngmnt.service.IUserManagementService;

public class CRMServicesProxy
{
    private static Logger           LOGGER           = Logger.getLogger( CRMServicesProxy.class );
    //    private Properties              crmServicesProxyProps = new Properties();
    private static CRMServicesProxy crmServicesProxy = null;

    public static CRMServicesProxy getInstance()
    {
        if ( crmServicesProxy == null )
        {
            synchronized ( CRMServicesProxy.class )
            {
                if ( crmServicesProxy == null )
                {
                    crmServicesProxy = new CRMServicesProxy();
                }
            }
        }
        return crmServicesProxy;
    }

    private CRMServicesProxy()
    {
        //        init();
    }

    //    private void init()
    //    {
    //        LOGGER.info( "Initializing CRMServicesProxy" );
    //        InputStream inputStream = null;
    //        try
    //        {
    //            inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_SERVICES_PROXY_CONFIG );
    //            crmServicesProxyProps.load( inputStream );
    //            String lookupModeUsrMngmntServiceEJB = crmServicesProxyProps
    //                    .getProperty( IGlobalConstants.MODULE_NAME_USER_MANAGEMENT_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeUsrMngmntServiceEJB )
    //                    && lookupModeUsrMngmntServiceEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + IGlobalConstants.MODULE_NAME_USER_MANAGEMENT_EJB );
    //                CRM_ALERTS_SERVICE_EJB_LOOKUP_NAME = IGlobalConstants.APP_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME;
    //            }
    //            LOGGER.info( "JNDI Name to be used for " + IGlobalConstants.MODULE_NAME_USER_MANAGEMENT_EJB + " : "
    //                    + CRM_ALERTS_SERVICE_EJB_LOOKUP_NAME );
    //            String lookupModeVtaReplenishEJB = crmServicesProxyProps.getProperty( GlobalConstants.VTA_REPLENISH_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeVtaReplenishEJB )
    //                    && lookupModeVtaReplenishEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + GlobalConstants.VTA_REPLENISH_EJB );
    //                VTA_REPLENISH_EJB_LOOKUP_NAME = GlobalConstants.GLOBAL_REMOTE_REPLENISH_EJB_BINDING_NAME;
    //            }
    //            LOGGER.info( "JNDI Name to be used for " + GlobalConstants.VTA_REPLENISH_EJB + " : "
    //                    + VTA_REPLENISH_EJB_LOOKUP_NAME );
    //
    //
    //            String lookupModeVtaIP2UserEJB = crmServicesProxyProps.getProperty( GlobalConstants.VTA_IP2USER_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeVtaIP2UserEJB )
    //                    && lookupModeVtaIP2UserEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + GlobalConstants.VTA_IP2USER_EJB );
    //                VTA_IP2USER_EJB_LOOKUP_NAME = GlobalConstants.GLOBAL_REMOTE_IP2USER_EJB_BINDING_NAME;
    //            }
    //            LOGGER.info( "JNDI Name to be used for " + GlobalConstants.VTA_IP2USER_EJB + " : "
    //                    + VTA_IP2USER_EJB_LOOKUP_NAME );
    //
    //
    //            String lookupModeVtaConfigMgrEJB = crmServicesProxyProps
    //                    .getProperty( GlobalConstants.VTA_CONFIG_MANAGER_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeVtaConfigMgrEJB )
    //                    && lookupModeVtaConfigMgrEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + GlobalConstants.VTA_CONFIG_MANAGER_EJB );
    //                VTA_CONFIGMGR_EJB_LOOKUP_NAME = GlobalConstants.GLOBAL_REMOTE_CONFIG_MGR_EJB_BINDING_NAME ;
    //            }
    //            LOGGER.info( "JNDI Name to be used for " + GlobalConstants.VTA_CONFIG_MANAGER_EJB + " : "
    //                    + VTA_CONFIGMGR_EJB_LOOKUP_NAME );
    //
    //            String lookupModeVtaBalanceQuotaEJB = crmServicesProxyProps
    //                    .getProperty( GlobalConstants.VTA_BALANCE_QUOTA_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeVtaBalanceQuotaEJB )
    //                    && lookupModeVtaBalanceQuotaEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + GlobalConstants.VTA_BALANCE_QUOTA_EJB );
    //                VTA_BALANCE_QUOTA_EJB_LOOKUP_NAME = GlobalConstants.GLOBAL_REMOTE_BALANCE_QUOTA_EJB_BINDING_NAME;
    //            }
    //
    //            LOGGER.info( "JNDI Name to be used for " + GlobalConstants.VTA_BALANCE_QUOTA_EJB + " : "
    //                    + VTA_BALANCE_QUOTA_EJB_LOOKUP_NAME );
    //
    //            String lookupModeVtaSysLocatorEJB = crmServicesProxyProps
    //                    .getProperty( GlobalConstants.VTA_SYS_LOCATOR_EJB );
    //            if ( StringUtils.isNotEmpty( lookupModeVtaSysLocatorEJB )
    //                    && lookupModeVtaSysLocatorEJB.equalsIgnoreCase( "remote" ) )
    //            {
    //                LOGGER.info( "Using Remote EJB Interface for " + GlobalConstants.VTA_SYS_LOCATOR_EJB );
    //                VTA_SYS_LOCATOR_EJB_LOOKUP_NAME = GlobalConstants.GLOBAL_REMOTE_VTA_SYS_LOCATOR_EJB_BINDING_NAME ;
    //            }
    //
    //            LOGGER.info( "JNDI Name to be used for " + GlobalConstants.VTA_SYS_LOCATOR_EJB + " : "
    //                    + VTA_SYS_LOCATOR_EJB_LOOKUP_NAME );
    //        }
    //        catch ( IOException ex )
    //        {
    //            LOGGER.fatal( "IOException initializing common ConnectionUtil", ex );
    //            throw new RuntimeException( ex );
    //        }
    //        finally
    //        {
    //            IOUtils.closeQuietly( inputStream );
    //        }
    //    }
    public IUserManagementService getUserManagementService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "User Managemnet EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( IUserManagementService.class, ejbLookupName );
    }

    public IAlertsService getAlertsService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Alerts Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( IAlertsService.class, ejbLookupName );
    }

    public ICrmConfigService getCRMConfigService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Config Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ICrmConfigService.class, ejbLookupName );
    }

    public IMasterDataService getCRMMasterDataService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Master Data Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( IMasterDataService.class, ejbLookupName );
    }

    public IGISService getGISService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_GIS_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_GIS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_GIS_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_GIS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_GIS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_GIS_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "GIS Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( IGISService.class, ejbLookupName );
    }

    public ICrmCapService getCRMCapService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Crm Cap Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ICrmCapService.class, ejbLookupName );
    }

    public ICrmFinanceService getCRMFinanceService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Crm Finance Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ICrmFinanceService.class, ejbLookupName );
    }

    public ICrmQrcService getCrmQrcService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Crm QRC Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ICrmQrcService.class, ejbLookupName );
    }

    public ICrmQrcService getCrmReportService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "Crm Report Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ICrmQrcService.class, ejbLookupName );
    }

    public ILMSService getLmsService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_LMS_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_LMS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_LMS_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_LMS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_LMS_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_LMS_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "lms Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( ILMSService.class, ejbLookupName );
    }

    public IQRCFaultRepairService getRIRestService( final String inLookUpType, final String inScope )
    {
        String ejbLookupName = IGlobalConstants.APP_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
        if ( IGlobalConstants.LOCAL.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
            }
        }
        if ( IGlobalConstants.REMOTE.equals( inLookUpType ) )
        {
            if ( IGlobalConstants.APP.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.APP_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.MODULE.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.MODULE_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
            }
            else if ( IGlobalConstants.GLOBAL.equals( inScope ) )
            {
                ejbLookupName = IGlobalConstants.GLOBAL_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME;
            }
        }
        LOGGER.info( "RI Rest Service EJB Lookup Name:" + ejbLookupName );
        return ServiceLocator.lookup( IQRCFaultRepairService.class, ejbLookupName );
    }
    //    @SuppressWarnings(
    //    { "rawtypes", "unchecked" })
    //    public Hashtable getInitialContextForRemoteServer( VTAInstances vtaInstance )
    //    {
    //        Hashtable ht = new Hashtable();
    //        ht.put( "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", crmServicesProxyProps
    //                .getProperty( "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED" ) );
    //        ht.put( "remote.connections", crmServicesProxyProps.getProperty( "remote.connections" ) );
    //        ht.put( "remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",
    //                crmServicesProxyProps
    //                        .getProperty( "remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS" ) );
    //        ht.put( "remote.connection.default.host", vtaInstance.getServerIP() );
    //        ht.put( "remote.connection.default.port", vtaInstance.getPort() );
    //        return ht;
    //    }
    //
    //    @SuppressWarnings(
    //    { "rawtypes", "unchecked" })
    //    public Hashtable getInitialContextForRemoteServer( String serverIP, String serverPort )
    //    {
    //        Hashtable ht = new Hashtable();
    //        ht.put( "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", crmServicesProxyProps
    //                .getProperty( "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED" ) );
    //        ht.put( "remote.connections", crmServicesProxyProps.getProperty( "remote.connections" ) );
    //        ht.put( "remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",
    //                crmServicesProxyProps
    //                        .getProperty( "remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS" ) );
    //        ht.put( "remote.connection.default.host", serverIP );
    //        ht.put( "remote.connection.default.port", serverPort );
    //        LOGGER.info( "Doing initial context properties for " + serverIP + ":" + serverPort );
    //        return ht;
    //    }
}
