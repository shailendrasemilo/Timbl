package com.np.tele.crm.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.service.client.ValidationMasterPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.GlobalUtils;
import com.np.tele.crm.utils.ICRMCacheManager;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public final class CRMCacheManager
    implements ICRMCacheManager
{
    private static final Logger                     LOGGER              = Logger.getLogger( CRMCacheManager.class );
    private static final String                     ALL_CRF_REASONS     = "AllCRFReasons";
    private static final String                     FUNCTIONAL_BINS     = "functionalBins";
    private static final String                     QRC_FUNCTIONAL_BINS = "qrcFunctionalBins";
    //    private static final String                     QRC_CATEGORIES          = "qrcCatgories";
    private static final Map<String, Collection<?>> contentMap          = new LinkedHashMap<String, Collection<?>>();
    private static final Map<String, Object>        dataMap             = new LinkedHashMap<String, Object>();
    private static final Map<String, Date>          timeMap             = new LinkedHashMap<String, Date>();
    private static final Map<String, String>        methodMap           = new LinkedHashMap<String, String>();
    //    private static final String                     CONNECTION_TYPE         = "connectionType";
    //    private static final String                     NATIONALITY_TYPE        = "nationalityType";
    private static final String                     ALL_PLAN_MASTER     = "allPlanMaster";

    public static IMasterBMngr getMasterDataBm()
    {
        return (IMasterBMngr) IAppConstants.flyWeightBeanMap.get( IAppConstants.MASTER_DATA );
    }

    public static IFinanceManager getFinanceManagerBm()
    {
        return (IFinanceManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.FINANCE_MANAGER );
    }

    public static ICrmConfigManager getConfigManagerImpl()
    {
        return (ICrmConfigManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.CRM_CONFIG_MANAGER );
    }

    public static IQrcManager getQrcManagerBm()
    {
        return (IQrcManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.QRC_MANAGER );
    }
    static
    {
        methodMap.put( PartnerPojo.class.getSimpleName(), "getActivePartnerList" );
        methodMap.put( CRMDisplayListConstants.ADDON_PLAN.getCode(), "getAddonPlans" );
        methodMap.put( CRMRCAReason.CRM_BANK.getStatusCode(), "getAllBankList" );
        methodMap.put( CRMCacheManager.ALL_CRF_REASONS, "getAllCRFReasons" );
        methodMap.put( CRMCacheManager.ALL_PLAN_MASTER, "getAllPlanMaster" );
        methodMap.put( CRMDisplayListConstants.ASSOCIATE_CONSULTANT.getCode(), "getAssociatePartner" );
        methodMap.put( CRMDisplayListConstants.BASE_PLAN.getCode(), "getBasePlans" );
        methodMap.put( CRMDisplayListConstants.BOOSTER_PLAN.getCode(), "getBoosterPlans" );
        methodMap.put( CRMDisplayListConstants.BUSINESS_PARTNER.getCode(), "getBussinessPartners" );
        methodMap.put( CrmRolesPojo.class.getSimpleName(), "getCrmRolePojos" );
        methodMap.put( CRMCacheManager.FUNCTIONAL_BINS, "getFunctionalBins" );
        methodMap.put( CRMDisplayListConstants.NETWORK_PARTNER.getCode(), "getNetworkPartners" );
        methodMap.put( CRMCacheManager.QRC_FUNCTIONAL_BINS, "getQRCFunctionalBins" );
        methodMap.put( CRMDisplayListConstants.SERVICE_PARTNER.getCode(), "getServicePartner" );
        methodMap.put( CRMDisplayListConstants.VAS_PLAN.getCode(), "getVasPlans" );
    }

    public static void toRefresh( String inKey )
    {
        synchronized ( timeMap )
        {
            timeMap.remove( inKey );
        }
    }

    public static void refreshMasterData()
    {
        synchronized ( timeMap )
        {
            timeMap.remove( ALL_CRF_REASONS );
            // timeMap.remove( CHEQUE_BOUNCING_REASONS );
            timeMap.remove( FUNCTIONAL_BINS );
            timeMap.remove( QRC_FUNCTIONAL_BINS );
            timeMap.remove( CRMRCAReason.CRM_BANK.getStatusCode() );
        }
    }

    //    private static void init()
    //    {
    //        Class<CRMCacheManager> c = CRMCacheManager.class;
    //        GlobalUtils.executeStaticNoArgsMethods( c );
    //    }
    private static void init( String inType )
    {
        if ( methodMap.containsKey( inType ) )
        {
            GlobalUtils.executeSpecificMethod( CRMCacheManager.class, methodMap.get( inType ) );
        }
        else
        {
            throw new IllegalArgumentException( inType );
        }
    }

    private static Collection<?> getCollection( String inKey )
    {
        if ( timeMap.containsKey( inKey ) && contentMap.containsKey( inKey ) )
        {
            if ( DateUtils.minuteDiff( timeMap.get( inKey ), Calendar.getInstance().getTime() ) >= 10 )
            {
                return null;
            }
            return contentMap.get( inKey );
        }
        return null;
    }

    private static void setCollection( String inKey, Collection<?> inList )
    {
        timeMap.put( inKey, Calendar.getInstance().getTime() );
        contentMap.put( inKey, inList );
    }

    private static Object getCachedData( String inKey )
    {
        if ( timeMap.containsKey( inKey ) && dataMap.containsKey( inKey ) )
        {
            if ( DateUtils.minuteDiff( timeMap.get( inKey ), Calendar.getInstance().getTime() ) >= 10 )
            {
                return null;
            }
            return dataMap.get( inKey );
        }
        return null;
    }

    private static void setCachedData( String inKey, Object inObject )
    {
        timeMap.put( inKey, Calendar.getInstance().getTime() );
        dataMap.put( inKey, inObject );
    }

    public static List<CrmRcaReasonPojo> getBankListByStatus( CRMStatusCode inStatus )
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMRCAReason.CRM_BANK
                .getStatusCode() + "-" + inStatus.getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            masterList = getAllBankList();
            List<CrmRcaReasonPojo> records = new ArrayList<CrmRcaReasonPojo>();
            for ( CrmRcaReasonPojo crmRcaReasonPojo : masterList )
            {
                if ( StringUtils.equals( inStatus.getStatusCode(), crmRcaReasonPojo.getStatus() ) )
                {
                    records.add( crmRcaReasonPojo );
                }
            }
            SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
            Collections.sort( records, sortComparator );
            sortComparator = null;
            setCollection( CRMRCAReason.CRM_BANK.getStatusCode() + "-" + inStatus.getStatusCode(), records );
            masterList = records;
        }
        return masterList;
    }

    public static List<CrmRcaReasonPojo> getAllBankList()
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMRCAReason.CRM_BANK
                .getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.CRM_BANK.getStatusCode() );
            masterList = getMasterData( crmRcaReasonPojo, null );
            setCollection( CRMRCAReason.CRM_BANK.getStatusCode(), masterList );
        }
        return masterList;
    }

    //get Functional Bins dynamically from DB
    public static List<CrmRcaReasonPojo> getFunctionalBins()
    {
        List<CrmRcaReasonPojo> functionalBins = (List<CrmRcaReasonPojo>) getCollection( CRMCacheManager.FUNCTIONAL_BINS );
        if ( !StringUtils.isValidObj( functionalBins ) || functionalBins.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
            functionalBins = getMasterData( crmRcaReasonPojo ); //service call
            SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
            Collections.sort( functionalBins, sortComparator );
            sortComparator = null;
            setCollection( CRMCacheManager.FUNCTIONAL_BINS, functionalBins );
        }
        return functionalBins;
    }

    /**
     *  Provide only those Functional Bins for which Modification is Allowed
     * @return
     */
    public static List<CrmRcaReasonPojo> getQRCFunctionalBins()
    {
        List<CrmRcaReasonPojo> functionalBins = (List<CrmRcaReasonPojo>) getCollection( CRMCacheManager.QRC_FUNCTIONAL_BINS );
        if ( !StringUtils.isValidObj( functionalBins ) || functionalBins.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
            crmRcaReasonPojo.setModificationAllowed( IAppConstants.Y );
            functionalBins = getMasterData( crmRcaReasonPojo ); //service call
            setCollection( CRMCacheManager.QRC_FUNCTIONAL_BINS, functionalBins );
        }
        SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
        Collections.sort( functionalBins, sortComparator );
        sortComparator = null;
        return functionalBins;
    }

    public static CrmRcaReasonPojo getFunctionalBinsByAlias( String inFunctionalAlias )
    {
        List<CrmRcaReasonPojo> functionalBins = getFunctionalBins();
        if ( CommonValidator.isValidCollection( functionalBins ) )
        {
            for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBins )
            {
                if ( StringUtils.equals( crmRcaReasonPojo.getValueAlias(), inFunctionalAlias ) )
                {
                    return crmRcaReasonPojo;
                }
            }
        }
        return null;
    }

    public static List<ContentPojo> getFunctionalBinsContent()
    {
        List<CrmRcaReasonPojo> functionalBins = getFunctionalBins();
        List<ContentPojo> functionalBinContentPojo = new ArrayList<ContentPojo>();
        ContentPojo contentPojo = null;
        for ( CrmRcaReasonPojo functionalBinPojo : functionalBins )
        {
            contentPojo = new ContentPojo( functionalBinPojo.getCategoryValue(), String.valueOf( functionalBinPojo
                    .getCategoryId() ) );
            functionalBinContentPojo.add( contentPojo );
        }
        return functionalBinContentPojo;
    }

    public static List<CrmQrcSubSubCategoriesPojo> getChequeBouncingReasons( QRCRolCategories inQRCRolCategories )
    {
        List<CrmQrcSubSubCategoriesPojo> categoriesPojos = (List<CrmQrcSubSubCategoriesPojo>) getCollection( inQRCRolCategories
                .getEvent() );
        if ( !StringUtils.isValidObj( categoriesPojos ) || categoriesPojos.isEmpty() )
        {
            categoriesPojos = QRCCacheManager.getActiveQrcSubSubCategories( inQRCRolCategories.getQrcType(),
                                                                            inQRCRolCategories.getCategory(),
                                                                            inQRCRolCategories.getSubCategory() );
            setCollection( inQRCRolCategories.getEvent(), categoriesPojos );
        }
        return categoriesPojos;
    }

    public static List<CrmRcaReasonPojo> getAllCRFReasons()
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMCacheManager.ALL_CRF_REASONS );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.INA_REASON.getStatusCode() );
            masterList = getMasterData( crmRcaReasonPojo );
            setCollection( CRMCacheManager.ALL_CRF_REASONS, masterList );
        }
        return masterList;
    }

    public static List<CrmRcaReasonPojo> getINAReasons( CRMRCAReason rcaReason )
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMRCAReason.INA.getStatusCode()
                + rcaReason.getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.INA_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( rcaReason.getStatusCode() );
            masterList = getMasterData( crmRcaReasonPojo );
            setCollection( CRMRCAReason.INA.getStatusCode() + rcaReason.getStatusCode(), masterList );
        }
        return masterList;
    }

    public static List<CrmRcaReasonPojo> getFinanceReasons( CRMRCAReason rcaReason )
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMRCAReason.FINANCE
                .getStatusCode() + rcaReason.getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.FINANCE_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( rcaReason.getStatusCode() );
            masterList = getMasterData( crmRcaReasonPojo );
            setCollection( CRMRCAReason.FINANCE.getStatusCode() + rcaReason.getStatusCode(), masterList );
        }
        return masterList;
    }

    //    public static List<EventsPojo> getEventList()
    //    {
    //        List<EventsPojo> masterList = (List<EventsPojo>) getCollection( CRMParameter.EVENTS.getParameter() );
    //        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
    //        {
    //            masterList = getConfigManagerImpl().getEvents();
    //            setCollection( CRMParameter.EVENTS.getParameter(), masterList );
    //        }
    //        return masterList;
    //    }
    public static List<PartnerPojo> getActivePartnerList()
    {
        List<PartnerPojo> masterList = (List<PartnerPojo>) getCollection( PartnerPojo.class.getSimpleName() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            masterList = getMasterDataBm().getPartnersByStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            LOGGER.info( "::::::::::::active partners" + masterList.size() );
            setCollection( PartnerPojo.class.getSimpleName(), masterList );
        }
        SortingComparator<PartnerPojo> sortComparator = new SortingComparator<PartnerPojo>( "partnerName" );
        Collections.sort( masterList, sortComparator );
        sortComparator = null;
        return masterList;
    }

    public static UserMasterPojo getUserMaster()
    {
        UserMasterPojo masterList = (UserMasterPojo) getCachedData( UserMasterPojo.class.getSimpleName() );
        if ( !StringUtils.isValidObj( masterList ) )
        {
            masterList = getConfigManagerImpl().getUserAccessPojo();
            setCachedData( UserMasterPojo.class.getSimpleName(), masterList );
        }
        return masterList;
    }

    public static ValidationMasterPojo getValidationMaster()
    {
        ValidationMasterPojo masterList = (ValidationMasterPojo) getCachedData( ValidationMasterPojo.class
                .getSimpleName() );
        if ( !StringUtils.isValidObj( masterList ) )
        {
            masterList = getConfigManagerImpl().getValidationMasterPojo();
            setCachedData( ValidationMasterPojo.class.getSimpleName(), masterList );
        }
        return masterList;
    }

    public static Map<String, Boolean> getCrmRoles()
    {
        Map<String, Boolean> rolePojos = (Map<String, Boolean>) getCachedData( CrmRolesPojo.class.getSimpleName() );
        if ( !StringUtils.isValidObj( rolePojos ) || rolePojos.isEmpty() )
        {
            rolePojos = new TreeMap<String, Boolean>();
            try
            {
                List<CrmRolesPojo> crmRoleList = getMasterDataBm().getRoles();
                if ( StringUtils.isValidObj( crmRoleList ) )
                {
                    for ( CrmRolesPojo crmRolesPojo : crmRoleList )
                    {
                        processCRMRoles( rolePojos, crmRolesPojo );
                    }
                }
                setCachedData( CrmRolesPojo.class.getSimpleName(), rolePojos );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Unable to retrieve User Roles Pojo:", ex );
            }
        }
        return rolePojos;
    }

    public static List<CrmRolesPojo> getCrmRolePojos()
    {
        List<CrmRolesPojo> masterList = (List<CrmRolesPojo>) getCollection( CrmRolesPojo.class.getSimpleName() );
        if ( ValidationUtil.isNotValidCollection( masterList ) )
        {
            try
            {
                masterList = getMasterDataBm().getRoles();
                setCollection( CrmRolesPojo.class.getSimpleName(), masterList );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Unable to retrieve User Roles Pojo:", ex );
            }
        }
        return masterList;
    }

    public static List<PartnerPojo> getPartnerByPartnerType( String partnerType )
    {
        List<PartnerPojo> masterList = (List<PartnerPojo>) getCollection( partnerType );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            masterList = new ArrayList<PartnerPojo>();
            for ( PartnerPojo partnerPojo : getActivePartnerList() )
            {
                for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partnerPojo.getCrmPartnerDetailses() )
                {
                    if ( StringUtils.equals( partnerType, crmPartnerDetailsPojo.getPartnerType() )
                            && StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                   crmPartnerDetailsPojo.getStatus() ) )
                    {
                        masterList.add( partnerPojo );
                        break;
                    }
                }
            }
            setCollection( partnerType, masterList );
        }
        return masterList;
    }

    public static List<PartnerPojo> getPartnerbyType( String inPartnerType, String inBusinessType, String inStatus )
    {
        List<PartnerPojo> partnerList = null;
        try
        {
            if ( StringUtils.isNotEmpty( inPartnerType ) && StringUtils.isNotEmpty( inBusinessType ) )
            {
                partnerList = new ArrayList<PartnerPojo>();
                List<PartnerPojo> partnerPojos = CRMCacheManager.getActivePartnerList();
                for ( PartnerPojo partnerPojo : partnerPojos )
                {
                    for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partnerPojo.getCrmPartnerDetailses() )
                    {
                        if ( StringUtils.isNotBlank( inStatus ) )
                        {
                            if ( StringUtils.equals( inPartnerType, crmPartnerDetailsPojo.getPartnerType() )
                                    && StringUtils.equals( inBusinessType, crmPartnerDetailsPojo.getBussinessType() )
                                    && StringUtils.equals( inStatus, crmPartnerDetailsPojo.getStatus() ) )
                            {
                                partnerList.add( partnerPojo );
                                break;
                            }
                        }
                        else
                        {
                            if ( StringUtils.equals( inPartnerType, crmPartnerDetailsPojo.getPartnerType() )
                                    && StringUtils.equals( inBusinessType, crmPartnerDetailsPojo.getBussinessType() ) )
                            {
                                partnerList.add( partnerPojo );
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getPartners mehtod:: " + ex );
        }
        return partnerList;
    }

    public static List<PartnerPojo> getNetworkPartners()
    {
        List<PartnerPojo> masterList = getPartnerByPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
        return masterList;
    }

    public static List<PartnerPojo> getBussinessPartners()
    {
        List<PartnerPojo> masterList = getPartnerByPartnerType( CRMDisplayListConstants.BUSINESS_PARTNER.getCode() );
        return masterList;
    }

    public static List<PartnerPojo> getServicePartner()
    {
        List<PartnerPojo> masterList = getPartnerByPartnerType( CRMDisplayListConstants.SERVICE_PARTNER.getCode() );
        return masterList;
    }

    public static List<PartnerPojo> getAssociatePartner()
    {
        List<PartnerPojo> masterList = getPartnerByPartnerType( CRMDisplayListConstants.ASSOCIATE_CONSULTANT.getCode() );
        return masterList;
    }

    public static List<CrmPartnerDetailsPojo> getPartnerDetails( String inPartnerId )
    {
        List<CrmPartnerDetailsPojo> masterList = new ArrayList<CrmPartnerDetailsPojo>();
        for ( PartnerPojo partnerPojo : getActivePartnerList() )
        {
            if ( StringUtils.equals( String.valueOf( partnerPojo.getPartnerId() ), inPartnerId ) )
            {
                masterList = partnerPojo.getCrmPartnerDetailses();
                break;
            }
        }
        return masterList;
    }

    public static List<CrmRcaReasonPojo> getMasterData( CrmRcaReasonPojo crmRcaReasonPojo )
    {
        return getMasterData( crmRcaReasonPojo, CRMStatusCode.ACTIVE.getStatusCode() );
    }

    public static List<CrmRcaReasonPojo> getMasterData( final CrmRcaReasonPojo crmRcaReasonPojo, final String inStatus )
    {
        List<CrmRcaReasonPojo> masterDataList = null;
        MasterDataDto masterDataDto = new MasterDataDto();
        if ( StringUtils.isNotBlank( inStatus ) )
        {
            crmRcaReasonPojo.setStatus( inStatus );
        }
        masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            masterDataList = new ArrayList<CrmRcaReasonPojo>();
            masterDataDto = getMasterDataBm().masterOperation( masterDataDto );
            if ( StringUtils.isValidObj( masterDataDto.getCrmRcaReasonsList() )
                    && !masterDataDto.getCrmRcaReasonsList().isEmpty() )
            {
                masterDataList = masterDataDto.getCrmRcaReasonsList();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return masterDataList;
    }

    public static List<CrmPlanMasterPojo> getAllPlanMaster()
    {
        List<CrmPlanMasterPojo> crMList = (List<CrmPlanMasterPojo>) getCollection( ALL_PLAN_MASTER );
        if ( !StringUtils.isValidObj( crMList ) || crMList.isEmpty() )
        {
            MasterDataDto masterDataDto = new MasterDataDto();
            masterDataDto = getMasterDataBm().getPlanMaster( masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
            {
                crMList = masterDataDto.getCrmPlanMasterPojos();
            }
            setCollection( ALL_PLAN_MASTER, crMList );
        }
        return crMList;
    }

    public static List<CrmPlanMasterPojo> getActivePlanDetailsByPlanCategory( String inPlanCategory )
    {
        List<CrmPlanMasterPojo> activePlanMasterList = (List<CrmPlanMasterPojo>) getCollection( inPlanCategory );
        if ( !CommonValidator.isValidCollection( activePlanMasterList ) )
        {
            activePlanMasterList = new ArrayList<CrmPlanMasterPojo>();
            List<CrmPlanMasterPojo> allPlanMaster = getAllPlanMaster();
            if ( CommonValidator.isValidCollection( allPlanMaster ) )
            {
                for ( CrmPlanMasterPojo planMasterPojo : allPlanMaster )
                {
                    if ( StringUtils.equals( planMasterPojo.getPlanCategory(), inPlanCategory )
                            && StringUtils.equals( planMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                    {
                        activePlanMasterList.add( planMasterPojo );
                    }
                }
                setCollection( inPlanCategory, activePlanMasterList );
            }
        }
        return activePlanMasterList;
    }

    public static List<CrmPlanMasterPojo> getBasePlans()
    {
        List<CrmPlanMasterPojo> basePlanMasterList = getActivePlanDetailsByPlanCategory( CRMDisplayListConstants.BASE_PLAN
                .getCode() );
        return basePlanMasterList;
    }

    public static List<CrmPlanMasterPojo> getAddonPlans()
    {
        List<CrmPlanMasterPojo> basePlanMasterList = getActivePlanDetailsByPlanCategory( CRMDisplayListConstants.ADDON_PLAN
                .getCode() );
        return basePlanMasterList;
    }

    public static List<CrmPlanMasterPojo> getBoosterPlans()
    {
        List<CrmPlanMasterPojo> basePlanMasterList = getActivePlanDetailsByPlanCategory( CRMDisplayListConstants.BOOSTER_PLAN
                .getCode() );
        return basePlanMasterList;
    }

    public static List<CrmPlanMasterPojo> getVasPlans()
    {
        List<CrmPlanMasterPojo> basePlanMasterList = getActivePlanDetailsByPlanCategory( CRMDisplayListConstants.VAS_PLAN
                .getCode() );
        return basePlanMasterList;
    }

    /*Get Base plan list dependend on like planService[EOCPO] */
    public static List<CrmPlanMasterPojo> getBasePlanDetails( String planService )
    {
        String basePlanKey = planService + CRMDisplayListConstants.BASE_PLAN.getCode();
        List<CrmPlanMasterPojo> basePlanList = (List<CrmPlanMasterPojo>) getCollection( basePlanKey );
        if ( !StringUtils.isValidObj( basePlanList ) || basePlanList.isEmpty() )
        {
            basePlanList = new ArrayList<CrmPlanMasterPojo>();
            for ( CrmPlanMasterPojo crmPlanMasterPojo : getBasePlans() )
            {
                if ( StringUtils.equals( crmPlanMasterPojo.getService(), planService )
                        && StringUtils.equals( crmPlanMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    basePlanList.add( crmPlanMasterPojo );
                }
            }
            setCollection( basePlanKey, basePlanList );
        }
        return basePlanList;
    }

    public static List<CrmPlanMasterPojo> getActivationPlans( String planService, String inPrepaid )
    {
        List<CrmPlanMasterPojo> basePlanList = getBasePlanDetails( planService );
        List<CrmPlanMasterPojo> planList = new ArrayList<CrmPlanMasterPojo>();
        if ( CommonValidator.isValidCollection( basePlanList ) )
        {
            for ( CrmPlanMasterPojo crmPlanMasterPojo : basePlanList )
            {
                if ( StringUtils.equals( crmPlanMasterPojo.getPrepaidYn(), inPrepaid )
                        && StringUtils.equals( crmPlanMasterPojo.getActvAllowedYn(), "Y" ) )
                {
                    planList.add( crmPlanMasterPojo );
                }
            }
        }
        return planList;
    }

    /*Get Addon plan list dependend on like planService[EOCPR] */
    public static List<CrmPlanMasterPojo> getAddonPlanDetails( String planService )
    {
        String addOnPlanKey = planService + CRMDisplayListConstants.ADDON_PLAN.getCode();
        List<CrmPlanMasterPojo> addonPlanList = (List<CrmPlanMasterPojo>) getCollection( addOnPlanKey );
        if ( !StringUtils.isValidObj( addonPlanList ) || addonPlanList.isEmpty() )
        {
            addonPlanList = new ArrayList<CrmPlanMasterPojo>();
            for ( CrmPlanMasterPojo crmPlanMasterPojo : getAddonPlans() )
            {
                if ( StringUtils.equals( crmPlanMasterPojo.getService(), planService )
                        && StringUtils.equals( crmPlanMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                        && StringUtils.equals( crmPlanMasterPojo.getActvAllowedYn(), "Y" ) )
                {
                    addonPlanList.add( crmPlanMasterPojo );
                }
            }
            setCollection( addOnPlanKey, addonPlanList );
        }
        return addonPlanList;
    }

    /*Get Speed boster plan list dependend on plan service*/
    public static List<CrmPlanMasterPojo> getSpeedBoosterPlanDetails( String planService, String inPlanType )
    {
        LOGGER.info( "Inside CRMCacheManager, getSpeedBoosterPlanDetails" );
        //String speedBoosterPlanKey = planService + CRMDisplayListConstants.SPEED_PLAN.getCode();
        List<CrmPlanMasterPojo> speedBoosterPlanList = new ArrayList<CrmPlanMasterPojo>();
        if ( !StringUtils.isValidObj( speedBoosterPlanList ) || speedBoosterPlanList.isEmpty() )
        {
            for ( CrmPlanMasterPojo crmPlanMasterPojo : getBoosterPlans() )
            {
                LOGGER.info( "Plan Name:: " + crmPlanMasterPojo.getPlanName() );
                LOGGER.info( "planService" + planService );
                if ( StringUtils.equals( crmPlanMasterPojo.getPlanType(), CRMDisplayListConstants.SPEED_PLAN.getCode() )
                        && StringUtils.equals( crmPlanMasterPojo.getService(), planService )
                        && StringUtils.equals( crmPlanMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    CrmPlanMasterPojo planMasterPojo = new CrmPlanMasterPojo();
                    try
                    {
                        PropertyUtils.copyProperties( planMasterPojo, crmPlanMasterPojo );
                    }
                    catch ( IllegalAccessException ex )
                    {
                        LOGGER.error( "Illegal Access Exception", ex );
                    }
                    catch ( InvocationTargetException ex )
                    {
                        LOGGER.error( "Invocation Target Exception", ex );
                    }
                    catch ( NoSuchMethodException ex )
                    {
                        LOGGER.error( "No Such Method Exception", ex );
                    }
                    planMasterPojo.setEditable( false );
                    LOGGER.info( "Primary Qouta : " + crmPlanMasterPojo.getPrimaryQuota() );
                    if ( StringUtils.isValidObj( planMasterPojo.getPrimaryQuota() ) )
                    {
                        planMasterPojo.setPrimaryQuota( crmPlanMasterPojo.getPrimaryQuota() / 1024 * 1024 * 1024 );
                        //.divide(  1024 * 1024 * 1024 , RoundingMode.CEILING ) );
                    }
                    LOGGER.info( "Primary Qouta in GB: " + planMasterPojo.getPrimaryQuota() );
                    if ( StringUtils.equals( inPlanType, "BOOSTER_PAID" ) && planMasterPojo.getRentInclTax() > 0 )
                    {
                        speedBoosterPlanList.add( planMasterPojo );
                    }
                    else if ( StringUtils.equals( inPlanType, "BOOSTER_FOC" ) && planMasterPojo.getRentInclTax() < 1 )
                    {
                        speedBoosterPlanList.add( planMasterPojo );
                    }
                }
            }
            LOGGER.info( "Spped booster list size: " + speedBoosterPlanList.size() );
        }
        return speedBoosterPlanList;
    }

    /*Get TopUp boster plan list dependend on plan service and plan type*/
    public static List<CrmPlanMasterPojo> getVasPlanDetails( String planService, String inPlanType )
    {
        LOGGER.info( "Inside CRMCacheManager, getVasPlanDetails" );
        List<CrmPlanMasterPojo> vasPlanList = new ArrayList<CrmPlanMasterPojo>();
        for ( CrmPlanMasterPojo crmPlanMasterPojo : getVasPlans() )
        {
            if ( StringUtils.equals( crmPlanMasterPojo.getPlanType(), CRMDisplayListConstants.VAS_PLAN.getCode() )
                    && StringUtils.equals( crmPlanMasterPojo.getService(), planService )
                    && StringUtils.equals( crmPlanMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                CrmPlanMasterPojo planMasterPojo = new CrmPlanMasterPojo();
                try
                {
                    PropertyUtils.copyProperties( planMasterPojo, crmPlanMasterPojo );
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "Illegal Access Exception", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "Invocation Target Exception", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    LOGGER.error( "No Such Method Exception", ex );
                }
                planMasterPojo.setEditable( false );
                LOGGER.info( "Primary Qouta : " + crmPlanMasterPojo.getPrimaryQuota() );
                if ( StringUtils.isValidObj( planMasterPojo.getPrimaryQuota() ) )
                {
                    planMasterPojo.setPrimaryQuota( crmPlanMasterPojo.getPrimaryQuota() / 1024 * 1024 * 1024 );
                    //.divide( BigDecimal.valueOf( 1024 * 1024 * 1024 ), RoundingMode.CEILING ) );
                }
                LOGGER.info( "Primary Qouta in GB: " + planMasterPojo.getPrimaryQuota() );
                if ( StringUtils.equals( inPlanType, "VAS_PAID" ) && planMasterPojo.getRentInclTax() > 0 )
                {
                    vasPlanList.add( planMasterPojo );
                }
                else if ( StringUtils.equals( inPlanType, "VAS_FOC" ) && planMasterPojo.getRentInclTax() < 1 )
                {
                    vasPlanList.add( planMasterPojo );
                }
            }
        }
        return vasPlanList;
    }

    public static List<CrmPlanMasterPojo> getTopUpBoosterPlanDetails( String planService, String inPlanType )
    {
        LOGGER.info( "Inside CRMCacheManager, getTopUpBoosterPlanDetails" );
        //String topUpBoosterPlanKey = planService + CRMDisplayListConstants.TOPUP_PLAN.getCode() + inPlanType;
        List<CrmPlanMasterPojo> topUpBoosterPlanList = new ArrayList<CrmPlanMasterPojo>();
        for ( CrmPlanMasterPojo crmPlanMasterPojo : getBoosterPlans() )
        {
            if ( StringUtils.equals( crmPlanMasterPojo.getPlanType(), CRMDisplayListConstants.TOPUP_PLAN.getCode() )
                    && StringUtils.equals( crmPlanMasterPojo.getService(), planService )
                    && StringUtils.equals( crmPlanMasterPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                CrmPlanMasterPojo planMasterPojo = new CrmPlanMasterPojo();
                try
                {
                    PropertyUtils.copyProperties( planMasterPojo, crmPlanMasterPojo );
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "Illegal Access Exception", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "Invocation Target Exception", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    LOGGER.error( "No Such Method Exception", ex );
                }
                planMasterPojo.setEditable( false );
                LOGGER.info( "Primary Qouta : " + crmPlanMasterPojo.getPrimaryQuota() );
                if ( StringUtils.isValidObj( planMasterPojo.getPrimaryQuota() ) )
                {
                    planMasterPojo.setPrimaryQuota( crmPlanMasterPojo.getPrimaryQuota() / 1024 * 1024 * 1024 );
                    //.divide( BigDecimal.valueOf( 1024 * 1024 * 1024 ), RoundingMode.CEILING ) );
                }
                LOGGER.info( "Primary Qouta in GB: " + planMasterPojo.getPrimaryQuota() );
                if ( StringUtils.equals( inPlanType, "BOOSTER_PAID" ) && planMasterPojo.getRentInclTax() > 0 )
                {
                    topUpBoosterPlanList.add( planMasterPojo );
                }
                else if ( StringUtils.equals( inPlanType, "BOOSTER_FOC" ) && planMasterPojo.getRentInclTax() < 1 )
                {
                    topUpBoosterPlanList.add( planMasterPojo );
                }
            }
        }
        return topUpBoosterPlanList;
    }

    @Override
    public String getDisplayValue( String inType, String inKey )
    {
        String displayValue = null;
        if ( !contentMap.containsKey( inType ) )
        {
            LOGGER.info( "Unable to Find data for key=   " + inType );
            init( inType );
        }
        if ( contentMap.containsKey( inType ) )
        {
            Collection<?> listPojos = contentMap.get( inType );
            if ( StringUtils.isValidObj( listPojos ) )
            {
                for ( Object object : listPojos )
                {
                    if ( object instanceof CrmRcaReasonPojo )
                    {
                        CrmRcaReasonPojo pojo = (CrmRcaReasonPojo) object;
                        if ( StringUtils.equals( pojo.getCategoryId() + "", inKey ) )
                        {
                            displayValue = pojo.getCategoryValue();
                            break;
                        }
                    }
                    else if ( object instanceof EventsPojo )
                    {
                        EventsPojo pojo = (EventsPojo) object;
                        if ( StringUtils.equals( pojo.getEventId() + "", inKey ) )
                        {
                            displayValue = pojo.getEventDescription();
                            break;
                        }
                    }
                    else if ( object instanceof PartnerPojo )
                    {
                        PartnerPojo pojo = (PartnerPojo) object;
                        if ( StringUtils.equals( pojo.getPartnerId() + "", inKey ) )
                        {
                            displayValue = pojo.getPartnerName();
                            break;
                        }
                    }
                    else if ( object instanceof CrmPlanMasterPojo )
                    {
                        CrmPlanMasterPojo pojo = (CrmPlanMasterPojo) object;
                        if ( StringUtils.equals( pojo.getPlanCode() + "", inKey ) )
                        {
                            displayValue = pojo.getPlanName();
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            LOGGER.info( "Unable to Find data for key after method execution=   " + inType );
        }
        return displayValue;
    }

    //    public static List<CrmQrcCategoriesPojo> getQrcCategories()
    //    {
    //        List<CrmQrcCategoriesPojo> categoriesPojos = (List<CrmQrcCategoriesPojo>) getCollection( CRMCacheManager.QRC_CATEGORIES );
    //        if ( !StringUtils.isValidObj( categoriesPojos ) || categoriesPojos.isEmpty() )
    //        {
    //            categoriesPojos = getQrcManagerBm().getQrcCategories( null );
    //            setCollection( CRMCacheManager.QRC_CATEGORIES, categoriesPojos );
    //        }
    //        return categoriesPojos;
    //    }
    //
    //    public static List<ContentPojo> getQueryCategories()
    //    {
    //        List<ContentPojo> masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.QUERY.getValue() );
    //        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
    //        {
    //            processQRCCategories();
    //            masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.QUERY.getValue() );
    //        }
    //        return masterList;
    //    }
    //
    //    public static List<ContentPojo> getRequestCategories()
    //    {
    //        List<ContentPojo> masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.REQUEST.getValue() );
    //        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
    //        {
    //            processQRCCategories();
    //            masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.REQUEST.getValue() );
    //        }
    //        return masterList;
    //    }
    //
    //    public static List<ContentPojo> getComplaintCategories()
    //    {
    //        List<ContentPojo> masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.COMPLAINTS.getValue() );
    //        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
    //        {
    //            processQRCCategories();
    //            masterList = (List<ContentPojo>) getCollection( CRMDisplayListConstants.COMPLAINTS.getValue() );
    //        }
    //        return masterList;
    //    }
    //get Connection Type dynamically from DB
    //    public static List<CrmRcaReasonPojo> getConnectionTypes()
    //    {
    //        List<CrmRcaReasonPojo> connectionList = (List<CrmRcaReasonPojo>) getCollection( CRMCacheManager.CONNECTION_TYPE );
    //        if ( !StringUtils.isValidObj( connectionList ) || connectionList.isEmpty() )
    //        {
    //            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
    //            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
    //            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
    //            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.CONNECTION_TYPE.getStatusCode() );
    //            connectionList = getMasterData( crmRcaReasonPojo ); //service call
    //            setCollection( CRMCacheManager.CONNECTION_TYPE, connectionList );
    //        }
    //        return connectionList;
    //    }
    //get Nationality Type dynamically from DB
    //    public static List<CrmRcaReasonPojo> getNationalityTypes()
    //    {
    //        List<CrmRcaReasonPojo> nationalityList = (List<CrmRcaReasonPojo>) getCollection( CRMCacheManager.NATIONALITY_TYPE );
    //        if ( !StringUtils.isValidObj( nationalityList ) || nationalityList.isEmpty() )
    //        {
    //            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
    //            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
    //            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
    //            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.NATIONALITY_TYPE.getStatusCode() );
    //            nationalityList = getMasterData( crmRcaReasonPojo ); //service call
    //            setCollection( CRMCacheManager.NATIONALITY_TYPE, nationalityList );
    //        }
    //        return nationalityList;
    //    }
    //    public static void processQRCCategories()
    //    {
    //        Set<ContentPojo> querySet = new HashSet<ContentPojo>();
    //        Set<ContentPojo> requestSet = new HashSet<ContentPojo>();
    //        Set<ContentPojo> complaintSet = new HashSet<ContentPojo>();
    //        ContentPojo contentPojo = null;
    //        List<CrmQrcCategoriesPojo> categoriesPojos = getQrcCategories();
    //        if ( StringUtils.isValidObj( categoriesPojos ) && !categoriesPojos.isEmpty() )
    //        {
    //            for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
    //            {
    //                if ( StringUtils.equals( crmQrcCategoriesPojo.getQrcType(), CRMDisplayListConstants.QUERY.getCode() ) )
    //                {
    //                    contentPojo = new ContentPojo();
    //                    contentPojo.setContentName( crmQrcCategoriesPojo.getCategory() );
    //                    contentPojo.setContentValue( crmQrcCategoriesPojo.getCategory() );
    //                    querySet.add( contentPojo );
    //                }
    //                else if ( StringUtils.equals( crmQrcCategoriesPojo.getQrcType(),
    //                                              CRMDisplayListConstants.REQUEST.getCode() ) )
    //                {
    //                    contentPojo = new ContentPojo();
    //                    contentPojo.setContentName( crmQrcCategoriesPojo.getCategory() );
    //                    contentPojo.setContentValue( crmQrcCategoriesPojo.getCategory() );
    //                    requestSet.add( contentPojo );
    //                }
    //                else if ( StringUtils.equals( crmQrcCategoriesPojo.getQrcType(),
    //                                              CRMDisplayListConstants.COMPLAINTS.getCode() ) )
    //                {
    //                    contentPojo = new ContentPojo();
    //                    contentPojo.setContentName( crmQrcCategoriesPojo.getCategory() );
    //                    contentPojo.setContentValue( crmQrcCategoriesPojo.getCategory() );
    //                    complaintSet.add( contentPojo );
    //                }
    //            }
    //            setCollection( CRMDisplayListConstants.QUERY.getValue(), new LinkedList<ContentPojo>( querySet ) );
    //            setCollection( CRMDisplayListConstants.REQUEST.getValue(), new LinkedList<ContentPojo>( requestSet ) );
    //            setCollection( CRMDisplayListConstants.COMPLAINTS.getValue(), new LinkedList<ContentPojo>( complaintSet ) );
    //        }
    //    }
    public static <E> void processCRMRoles( Map<String, Boolean> inRolePojos, E obj )
    {
        String roleName = null;
        boolean createAccess = false;
        boolean deleteAccess = false;
        boolean readAccess = false;
        boolean recoverAccess = false;
        boolean updateAccess = false;
        if ( obj instanceof UserRolesPojo )
        {
            UserRolesPojo userRolesPojo = (UserRolesPojo) obj;
            readAccess = userRolesPojo.isReadAccess();
            createAccess = userRolesPojo.isCreateAccess();
            updateAccess = userRolesPojo.isUpdateAccess();
            deleteAccess = userRolesPojo.isDeleteAccess();
            recoverAccess = userRolesPojo.isRecoverAccess();
            roleName = userRolesPojo.getCrmRoles().getRoleName();
        }
        else if ( obj instanceof AccessGroupPojo )
        {
            AccessGroupPojo accessGroupPojo = (AccessGroupPojo) obj;
            readAccess = accessGroupPojo.isReadAccess();
            createAccess = accessGroupPojo.isCreateAccess();
            updateAccess = accessGroupPojo.isUpdateAccess();
            deleteAccess = accessGroupPojo.isDeleteAccess();
            recoverAccess = accessGroupPojo.isRecoverAccess();
            roleName = accessGroupPojo.getCrmRoles().getRoleName();
        }
        else if ( obj instanceof CrmRolesPojo )
        {
            CrmRolesPojo crmRolesPojo = (CrmRolesPojo) obj;
            readAccess = true;
            createAccess = true;
            updateAccess = true;
            deleteAccess = true;
            recoverAccess = true;
            roleName = crmRolesPojo.getRoleName();
        }
        if ( readAccess )
        {
            inRolePojos.put( "view_" + roleName, readAccess );
        }
        if ( createAccess )
        {
            inRolePojos.put( "create_" + roleName, createAccess );
        }
        if ( updateAccess )
        {
            inRolePojos.put( "update_" + roleName, updateAccess );
        }
        if ( deleteAccess )
        {
            inRolePojos.put( "delete_" + roleName, deleteAccess );
        }
        if ( recoverAccess )
        {
            inRolePojos.put( "recover_" + roleName, recoverAccess );
        }
    }

    public static PartnerPojo getPartnerById( String inPartnerId )
    {
        LOGGER.info( "getPartnerById " + inPartnerId );
        if ( StringUtils.numericValue( inPartnerId ) > 0 )
        {
            List<PartnerPojo> data = getMasterDataBm().getPartnersById( inPartnerId );
            if ( CommonValidator.isValidCollection( data ) )
            {
                return data.get( 0 );
            }
        }
        return null;
    }

    public static boolean validInMaster( CrmRcaReasonPojo crmRcaReasonPojo )
    {
        MasterDataDto masterDto = new MasterDataDto();
        if ( CRMDisplayListConstants.RADIO_FREQUENCY.getCode().equals( crmRcaReasonPojo.getSubSubCategory() ) )
        {
            crmRcaReasonPojo.setSubSubCategory( CRMDisplayListConstants.BROADBAND.getCode() );
        }
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            masterDto = getMasterDataBm().masterOperation( masterDto );
            if ( CommonValidator.isValidCollection( masterDto.getCrmRcaReasonsList() ) )
            {
                return true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getPartnerDetailsId method", ex );
        }
        return false;
    }

    public static boolean validPaymentTransactionId( CrmPaymentDetailsPojo inPaymentDetailsPojo )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            crmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
            crmFinanceDto = getFinanceManagerBm().paymentListByTransId( crmFinanceDto );
            if ( CommonValidator.isValidCollection( crmFinanceDto.getPaymentDetailsPojos() ) )
            {
                LOGGER.info( "IN isValidCollection Duplicate" );
                return true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in validPaymentTransactionId  method", ex );
        }
        return false;
    }

    public static List<String> getReceiptNo( String inPartnerId )
    {
        List<String> receiptList = new ArrayList<String>();
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        try
        {
            LOGGER.info( "Partner ID::: " + inPartnerId );
            crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( "CashReceipt" );
            List<PartnerPojo> partnerList = getMasterDataBm().getPartnersById( inPartnerId );
            LOGGER.info( "partnerList Size::" + partnerList.size() );
            if ( !partnerList.isEmpty() && partnerList.size() > 0 )
            {
                LOGGER.info( "PartnerAlias" + partnerList.get( 0 ).getPartnerName() );
                crmRcaReasonPojo.setSubSubCategory( partnerList.get( 0 ).getPartnerName() );
            }
            crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
            masterDataDto = getMasterDataBm().masterOperation( masterDataDto );
            LOGGER.info( "receipt list size  " + masterDataDto.getCrmRcaReasonsList().size() );
            if ( !masterDataDto.getCrmRcaReasonsList().isEmpty() && masterDataDto.getCrmRcaReasonsList().size() > 0 )
            {
                LOGGER.info( "receipt list size:   " + masterDataDto.getCrmRcaReasonsList().size() );
                for ( CrmRcaReasonPojo rcaReasonPojo : masterDataDto.getCrmRcaReasonsList() )
                {
                    receiptList.add( rcaReasonPojo.getCategoryValue() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get recieptNo mehtod:: ", ex );
        }
        return receiptList;
    }

    public static List<CrmRcaReasonPojo> getCustInteractionSubCategories( String inParam )
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( inParam );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.QRC.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.QRC_INTERACTION.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( StringUtils.upperCase( inParam ) );
            masterList = getMasterData( crmRcaReasonPojo );
            setCollection( inParam, masterList );
        }
        return masterList;
    }

    //call from dwr
    public static List<CrmPlanMasterPojo> getActivationPlansList( String planService,
                                                                  String inPrepaid,
                                                                  String inPlanType )
    {
        List<CrmPlanMasterPojo> basePlanList = getBasePlanDetails( planService );
        List<CrmPlanMasterPojo> planList = new ArrayList<CrmPlanMasterPojo>();
        if ( CommonValidator.isValidCollection( basePlanList ) )
        {
            for ( CrmPlanMasterPojo crmPlanMasterPojo : basePlanList )
            {
                if ( StringUtils.equals( crmPlanMasterPojo.getPrepaidYn(), inPrepaid )
                        && StringUtils.equals( crmPlanMasterPojo.getActvAllowedYn(), "Y" )
                        && StringUtils.equals( crmPlanMasterPojo.getPlanType(), inPlanType ) )
                {
                    planList.add( crmPlanMasterPojo );
                }
            }
        }
        return planList;
    }

    public static List<ContentPojo> getProductByPartnerID( long inPartnerId, String inPartnerType )
    {
        return getMasterDataBm().getProductsByPartnerId( inPartnerId + "",
                                                         CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
    }

    public static List<String> getSecondaryMacAdd( long inPartnerId, String inCustomerId )
    {
        List<String> secMacList = (List<String>) getCollection( CRMRequestType.INA.getRequestCode() + "-"
                + inCustomerId );
        if ( ValidationUtil.isNotValidCollection( secMacList ) )
        {
            LOGGER.info( "Map Empty::" );
            secMacList = new ArrayList<String>();
            try
            {
                PartnerPojo partner = getPartnerById( inPartnerId + "" );
                if ( StringUtils.isValidObj( partner ) )
                {
                    ConfigDto configDto = getConfigManagerImpl()
                            .getMacAddressByNP( partner.getCrmPartnerCode(), inCustomerId,
                                                CRMRequestType.INA.getRequestCode() );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), configDto.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( configDto.getMacAddressList() ) )
                        {
                            secMacList.addAll( configDto.getMacAddressList() );
                            setCollection( CRMRequestType.INA.getRequestCode() + "-" + inCustomerId,
                                           configDto.getMacAddressList() );
                        }
                    }
                    else
                    {
                        secMacList = new ArrayList<String>();
                        secMacList.add( "Error" );
                        if ( StringUtils.isNotBlank( configDto.getBillingErrorCode() ) )
                        {
                            LOGGER.info( "Billing Error Code:" + configDto.getBillingErrorCode() );
                            secMacList.add( configDto.getBillingErrorCode() );
                        }
                        else
                        {
                            LOGGER.info( "Status Code:" + configDto.getStatusCode() );
                            secMacList.add( configDto.getStatusCode() + "" + configDto.getStatusDesc() );
                        }
                    }
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Unable to retrieve Secondary MAC Address:", ex );
            }
        }
        return secMacList;
    }
}
