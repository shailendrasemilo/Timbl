package com.np.tele.crm.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMCacheManager;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public final class QRCCacheManager
    implements ICRMCacheManager
{
    private static final Logger                     LOGGER                          = Logger.getLogger( QRCCacheManager.class );
    private static final Map<String, Collection<?>> contentMap                      = new LinkedHashMap<String, Collection<?>>();
    private static final Map<String, Date>          timeMap                         = new LinkedHashMap<String, Date>();
    private static final String                     ACTIVE_QRC_CATEGORIES           = "qrcCatgories";
    private static final String                     ALL_QRC_CATEGORIES              = "allQrcCatgories";
    private static final String                     TEMPORARY_DISCONNECTION_REASONS = "temporaryDisconnectionReason";
    private static final String                     PERMANENT_DISCONNECTION_REASONS = "permanentDisconnectionReason";
    private static final String                     ACTIVE_QRC_SUB_SUB_CATEGORIES   = "qrcSubSubCatgories";

    public static IQrcManager getQrcManagerBm()
    {
        return (IQrcManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.QRC_MANAGER );
    }

    public static void toRefresh( String inKey )
    {
        synchronized ( timeMap )
        {
            timeMap.remove( inKey );
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

    public static List<CrmQrcCategoriesPojo> getAllQrcCategories()
    {
        List<CrmQrcCategoriesPojo> categoriesPojos = (List<CrmQrcCategoriesPojo>) getCollection( QRCCacheManager.ALL_QRC_CATEGORIES );
        if ( !StringUtils.isValidObj( categoriesPojos ) || categoriesPojos.isEmpty() )
        {
            categoriesPojos = getQrcManagerBm().getQrcCategories( null );
            setCollection( QRCCacheManager.ALL_QRC_CATEGORIES, categoriesPojos );
        }
        return categoriesPojos;
    }

    public static List<CrmQrcCategoriesPojo> getActiveQrcCategories()
    {
        List<CrmQrcCategoriesPojo> categoriesPojos = (List<CrmQrcCategoriesPojo>) getCollection( QRCCacheManager.ACTIVE_QRC_CATEGORIES );
        if ( !StringUtils.isValidObj( categoriesPojos ) || categoriesPojos.isEmpty() )
        {
            categoriesPojos = getQrcManagerBm().getQrcCategories( CRMStatusCode.ACTIVE.getStatusCode() );
            setCollection( QRCCacheManager.ACTIVE_QRC_CATEGORIES, categoriesPojos );
        }
        SortingComparator<CrmQrcCategoriesPojo> sortComparator = new SortingComparator<CrmQrcCategoriesPojo>( "qrcCategory" );
        Collections.sort( categoriesPojos, sortComparator );
        sortComparator = null;
        return categoriesPojos;
    }

    public static List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategories()
    {
        List<CrmQrcSubSubCategoriesPojo> categoriesSubSubPojos = (List<CrmQrcSubSubCategoriesPojo>) getCollection( QRCCacheManager.ACTIVE_QRC_SUB_SUB_CATEGORIES );
        if ( !StringUtils.isValidObj( categoriesSubSubPojos ) || categoriesSubSubPojos.isEmpty() )
        {
            categoriesSubSubPojos = getQrcManagerBm().getQrcSubSubCategories( CRMStatusCode.ACTIVE.getStatusCode() );
            setCollection( QRCCacheManager.ACTIVE_QRC_SUB_SUB_CATEGORIES, categoriesSubSubPojos );
        }
        SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
        Collections.sort( categoriesSubSubPojos, sortComparator );
        sortComparator = null;
        return categoriesSubSubPojos;
    }

    public static <E> List<CrmQrcSubCategoriesPojo> getActiveQrcSubCategories( E category )
    {
        if ( StringUtils.isValidObj( category ) )
        {
            List<CrmQrcCategoriesPojo> categoriesPojos = getActiveQrcCategories();
            if ( category instanceof Long || StringUtils.isNumeric( category.toString() ) )
            {
                CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
                qrcCategoriesPojo.setQrcCategoryId( StringUtils.numericValue( category.toString() ) );
                int pojoIndex = categoriesPojos.indexOf( qrcCategoriesPojo );
                if ( pojoIndex >= 0 )
                {
                    qrcCategoriesPojo = categoriesPojos.get( pojoIndex );
                    SortingComparator<CrmQrcSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubCategoriesPojo>( "qrcSubCategory" );
                    Collections.sort( qrcCategoriesPojo.getCrmQrcSubCategorieses(), sortComparator );
                    sortComparator = null;
                    return qrcCategoriesPojo.getCrmQrcSubCategorieses();
                }
            }
            else if ( category instanceof String )
            {
                for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
                {
                    if ( StringUtils.equals( category.toString(), crmQrcCategoriesPojo.getQrcCategory() ) )
                    {
                        SortingComparator<CrmQrcSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubCategoriesPojo>( "qrcSubCategory" );
                        Collections.sort( crmQrcCategoriesPojo.getCrmQrcSubCategorieses(), sortComparator );
                        sortComparator = null;
                        return crmQrcCategoriesPojo.getCrmQrcSubCategorieses();
                    }
                }
            }
        }
        return null;
    }

    public static <E> List<CrmQrcSubCategoriesPojo> getActiveSubCategories( E category, String inModuleType )
    {
        List<CrmQrcSubCategoriesPojo> crmQrcSubCategoriesPojos = null;
        if ( StringUtils.isEmpty( inModuleType ) )
        {
            inModuleType = null;
        }
        if ( StringUtils.isValidObj( category ) )
        {
            crmQrcSubCategoriesPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
            List<CrmQrcCategoriesPojo> categoriesPojos = getActiveQrcCategories();
            if ( category instanceof Long || StringUtils.isNumeric( category.toString() ) )
            {
                CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
                qrcCategoriesPojo.setQrcCategoryId( StringUtils.numericValue( category.toString() ) );
                int pojoIndex = categoriesPojos.indexOf( qrcCategoriesPojo );
                if ( pojoIndex >= 0 )
                {
                    qrcCategoriesPojo = categoriesPojos.get( pojoIndex );
                    for ( CrmQrcSubCategoriesPojo qrcSubCategoriesPojo : qrcCategoriesPojo.getCrmQrcSubCategorieses() )
                    {
                        if ( StringUtils.equals( qrcSubCategoriesPojo.getModuleType(), inModuleType )
                                && StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                        {
                            crmQrcSubCategoriesPojos.add( qrcSubCategoriesPojo );
                        }
                        else if ( !StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                        {
                            crmQrcSubCategoriesPojos.add( qrcSubCategoriesPojo );
                        }
                    }
                    return crmQrcSubCategoriesPojos;
                }
            }
        }
        return crmQrcSubCategoriesPojos;
    }

    public static <E> List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategories( String qrcType,
                                                                                     E category,
                                                                                     E subCategory )
    {
        CrmQrcSubCategoriesPojo qrcSubCategoriesPojo = null;
        if ( StringUtils.isValidObj( category ) && StringUtils.isValidObj( subCategory ) )
        {
            List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojoList = getActiveQrcSubCategories( category );
            if ( CommonValidator.isValidCollection( qrcSubCategoriesPojoList ) )
            {
                if ( subCategory instanceof Long || StringUtils.isNumeric( subCategory.toString() ) )
                {
                    qrcSubCategoriesPojo = new CrmQrcSubCategoriesPojo();
                    qrcSubCategoriesPojo.setQrcSubCategoryId( StringUtils.numericValue( subCategory.toString() ) );
                    int pojoIndex = qrcSubCategoriesPojoList.indexOf( qrcSubCategoriesPojo );
                    if ( pojoIndex >= 0 )
                    {
                        qrcSubCategoriesPojo = qrcSubCategoriesPojoList.get( pojoIndex );
                    }
                    else
                    {
                        qrcSubCategoriesPojo = null;
                    }
                }
                else if ( subCategory instanceof String )
                {
                    for ( CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo : qrcSubCategoriesPojoList )
                    {
                        if ( StringUtils.equals( subCategory.toString(), crmQrcSubCategoriesPojo.getQrcSubCategory() ) )
                        {
                            qrcSubCategoriesPojo = crmQrcSubCategoriesPojo;
                        }
                    }
                }
            }
            if ( StringUtils.isValidObj( qrcSubCategoriesPojo )
                    && CommonValidator.isValidCollection( qrcSubCategoriesPojo.getCrmQrcSubSubCategorieses() ) )
            {
                if ( StringUtils.isNotBlank( qrcType ) )
                {
                    List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojoList = new ArrayList<CrmQrcSubSubCategoriesPojo>();
                    for ( CrmQrcSubSubCategoriesPojo crSubSubCategoriesPojo : qrcSubCategoriesPojo
                            .getCrmQrcSubSubCategorieses() )
                    {
                        if ( StringUtils.equals( crSubSubCategoriesPojo.getQrcType(), qrcType ) )
                        {
                            qrcSubSubCategoriesPojoList.add( crSubSubCategoriesPojo );
                        }
                    }
                    SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
                    Collections.sort( qrcSubSubCategoriesPojoList, sortComparator );
                    sortComparator = null;
                    return qrcSubSubCategoriesPojoList;
                }
                else
                {
                    SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
                    Collections.sort( qrcSubCategoriesPojo.getCrmQrcSubSubCategorieses(), sortComparator );
                    sortComparator = null;
                    return qrcSubCategoriesPojo.getCrmQrcSubSubCategorieses();
                }
            }
        }
        return null;
    }

    public static String getQrcCategory( long categoryId )
    {
        CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
        qrcCategoriesPojo.setQrcCategoryId( categoryId );
        List<CrmQrcCategoriesPojo> categoriesPojos = getActiveQrcCategories();
        if ( CommonValidator.isValidCollection( categoriesPojos ) )
        {
            int pojoIndex = categoriesPojos.indexOf( qrcCategoriesPojo );
            if ( pojoIndex >= 0 )
            {
                qrcCategoriesPojo = categoriesPojos.get( pojoIndex );
                return qrcCategoriesPojo.getQrcCategory();
            }
        }
        return null;
    }

    public static String getQrcSubCategory( long categoryId, long subCategoryId )
    {
        List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojoList = getActiveQrcSubCategories( categoryId );
        CrmQrcSubCategoriesPojo qrcSubCategoriesPojo = new CrmQrcSubCategoriesPojo();
        qrcSubCategoriesPojo.setQrcSubCategoryId( subCategoryId );
        if ( CommonValidator.isValidCollection( qrcSubCategoriesPojoList ) )
        {
            int pojoIndex = qrcSubCategoriesPojoList.indexOf( qrcSubCategoriesPojo );
            if ( pojoIndex >= 0 )
            {
                qrcSubCategoriesPojo = qrcSubCategoriesPojoList.get( pojoIndex );
                return qrcSubCategoriesPojo.getQrcSubCategory();
            }
        }
        return null;
    }

    public static CrmQrcSubCategoriesPojo getQrcSubCategory( long subCategoryId,
                                                             List<CrmQrcSubCategoriesPojo> inQrcSubCategories )
    {
        CrmQrcSubCategoriesPojo qrcSubCategoriesPojo = new CrmQrcSubCategoriesPojo();
        qrcSubCategoriesPojo.setQrcSubCategoryId( subCategoryId );
        if ( CommonValidator.isValidCollection( inQrcSubCategories ) )
        {
            int pojoIndex = inQrcSubCategories.indexOf( qrcSubCategoriesPojo );
            if ( pojoIndex >= 0 )
            {
                return inQrcSubCategories.get( pojoIndex );
            }
        }
        return null;
    }

    public static String getQrcSubSubCategory( String qrcType,
                                               long categoryId,
                                               long subCategoryId,
                                               long subSubCategoryId )
    {
        List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojoList = getActiveQrcSubSubCategories( qrcType,
                                                                                                     categoryId,
                                                                                                     subCategoryId );
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
        subSubCategoriesPojo.setQrcSubSubCategoryId( subSubCategoryId );
        if ( CommonValidator.isValidCollection( qrcSubSubCategoriesPojoList ) )
        {
            int pojoIndex = qrcSubSubCategoriesPojoList.indexOf( subSubCategoriesPojo );
            if ( pojoIndex >= 0 )
            {
                subSubCategoriesPojo = qrcSubSubCategoriesPojoList.get( pojoIndex );
                return subSubCategoriesPojo.getQrcSubSubCategory();
            }
        }
        return null;
    }

    public static List<CrmRcaReasonPojo> getWhitelistBarringReasons()
    {
        return getQRCReasons( CRMRCAReason.QRC_WHITELIST_BARRED );
    }

    public static List<CrmRcaReasonPojo> getWhitelistUnBarringReasons()
    {
        return getQRCReasons( CRMRCAReason.QRC_WHITELIST_UNBARRED );
    }

    public static List<CrmRcaReasonPojo> getCustomerBarringReasons()
    {
        return getQRCReasons( CRMRCAReason.QRC_BARRED );
    }

    public static List<CrmRcaReasonPojo> getCustomerUnBarringReasons()
    {
        return getQRCReasons( CRMRCAReason.QRC_UNBARRED );
    }

    public static List<CrmRcaReasonPojo> getCustomerSafeCustodyReasons()
    {
        return getQRCReasons( CRMRCAReason.QRC_SAFECUSTODY );
    }

    public static List<CrmRcaReasonPojo> getCustomerGracePeriodReasons()
    {
        return getQRCReasons( CRMRCAReason.VALIDITY_EXTENSION_REASON );
    }

    public static List<CrmRcaReasonPojo> getQRCReasons( CRMRCAReason rcaReason )
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( rcaReason.getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.QRC.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.QRC_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( rcaReason.getStatusCode() );
            masterList = CRMCacheManager.getMasterData( crmRcaReasonPojo );
            setCollection( rcaReason.getStatusCode(), masterList );
        }
        SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
        Collections.sort( masterList, sortComparator );
        sortComparator = null;
        return masterList;
    }

    @Override
    public String getDisplayValue( String inType, String inKey )
    {
        String displayValue = null;
        return displayValue;
    }

    public static List<CrmRcaReasonPojo> getTemporaryDisconnectionReasons()
    {
        List<CrmRcaReasonPojo> tdReasonMasterList = (List<CrmRcaReasonPojo>) getCollection( QRCCacheManager.TEMPORARY_DISCONNECTION_REASONS );
        if ( !StringUtils.isValidObj( tdReasonMasterList ) || tdReasonMasterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.QRC.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.QRC_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.QRC_TEMPORARY_DISCONNECTION.getStatusCode() );
            tdReasonMasterList = CRMCacheManager.getMasterData( crmRcaReasonPojo );
            setCollection( QRCCacheManager.TEMPORARY_DISCONNECTION_REASONS, tdReasonMasterList );
        }
        SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
        Collections.sort( tdReasonMasterList, sortComparator );
        sortComparator = null;
        return tdReasonMasterList;
    }

    public static List<CrmRcaReasonPojo> getPermanentDisconnectionReasons()
    {
        List<CrmRcaReasonPojo> pdReasonMasterList = (List<CrmRcaReasonPojo>) getCollection( QRCCacheManager.PERMANENT_DISCONNECTION_REASONS );
        if ( !StringUtils.isValidObj( pdReasonMasterList ) || pdReasonMasterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.QRC.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.QRC_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.QRC_PERMANENT_DISCONNECTION.getStatusCode() );
            pdReasonMasterList = CRMCacheManager.getMasterData( crmRcaReasonPojo );
            setCollection( QRCCacheManager.PERMANENT_DISCONNECTION_REASONS, pdReasonMasterList );
        }
        SortingComparator<CrmRcaReasonPojo> sortComparator = new SortingComparator<CrmRcaReasonPojo>( "categoryValue" );
        Collections.sort( pdReasonMasterList, sortComparator );
        sortComparator = null;
        return pdReasonMasterList;
    }

    public static List<ContentPojo> getQrcActionsList( long inCategoryId, long inSubCategoryId, long inSubSubCategoryId )
    {
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( null, inCategoryId, inSubCategoryId );
        List<ContentPojo> contentPojos = null;
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
        subSubCategoriesPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
        int pojoIndex = subSubCategories.indexOf( subSubCategoriesPojo );
        if ( pojoIndex > -1 )
        {
            subSubCategoriesPojo = subSubCategories.get( pojoIndex );
            byte resolutionType = subSubCategoriesPojo.getResolutionType();
            contentPojos = new ArrayList<ContentPojo>();
            switch ( resolutionType )
            {
                case 0:
                    contentPojos.add( new ContentPojo( CrmTicketActions.getTicketActions( (byte) 0 ).getDesc(), String
                            .valueOf( 0 ) ) );
                    //                    conte ntPojos.add( new ContentPojo( "Rol", "0" ) );
                    break;
                case 1:
                    contentPojos.add( new ContentPojo( CrmTicketActions.getTicketActions( (byte) 1 ).getDesc(), String
                            .valueOf( 1 ) ) );
                    //                    contentPojos.add( new ContentPojo( "Forward", "1" ) );
                    break;
                case 2:
                    contentPojos.add( new ContentPojo( CrmTicketActions.getTicketActions( (byte) 0 ).getDesc(), String
                            .valueOf( 0 ) ) );
                    contentPojos.add( new ContentPojo( CrmTicketActions.getTicketActions( (byte) 1 ).getDesc(), String
                            .valueOf( 1 ) ) );
                    //                    contentPojos.add( new ContentPojo( "Rol", "0" ) );
                    //                    contentPojos.add( new ContentPojo( "Forward", "1" ) );
                    break;
            }
        }
        return contentPojos;
    }

    public static List<CrmRcaReasonPojo> getWaiverRejectionReasons()
    {
        List<CrmRcaReasonPojo> masterList = (List<CrmRcaReasonPojo>) getCollection( CRMRCAReason.WAIVER_REASON_REJECT
                .getStatusCode() );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.WAIVER.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.WAIVER_REASON.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.WAIVER_REASON_REJECT.getStatusCode() );
            masterList = CRMCacheManager.getMasterData( crmRcaReasonPojo );
            setCollection( CRMRCAReason.WAIVER_REASON_REJECT.getStatusCode(), masterList );
        }
        return masterList;
    }

    public static List<CrmQrcCategoriesPojo> getQrcCategories( String inStatus, String inFunctionalBin )
    {
        List<CrmQrcCategoriesPojo> categoriesPojos = (List<CrmQrcCategoriesPojo>) getCollection( CrmQrcCategoriesPojo.class
                .getSimpleName() + IAppConstants.UNDERSCORE + inStatus + IAppConstants.UNDERSCORE + inFunctionalBin );
        if ( !CommonValidator.isValidCollection( categoriesPojos ) )
        {
            List<CrmQrcCategoriesPojo> qrcCategoriesPojos = new ArrayList<CrmQrcCategoriesPojo>();
            categoriesPojos = getQrcManagerBm().getQrcCategories( inStatus );
            if ( CommonValidator.isValidCollection( categoriesPojos ) )
            {
                if ( StringUtils.isNotBlank( inFunctionalBin ) )
                {
                    for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
                    {
                        if ( StringUtils.contains( crmQrcCategoriesPojo.getFunctionalBin(), inFunctionalBin ) ) //CRMOperationStages.CUSTOMER_CARE.getCode() 
                        {
                            qrcCategoriesPojos.add( crmQrcCategoriesPojo );
                        }
                    }
                }
                else
                {
                    qrcCategoriesPojos.addAll( categoriesPojos );
                }
                if ( CommonValidator.isValidCollection( qrcCategoriesPojos ) )
                {
                    setCollection( CrmQrcCategoriesPojo.class.getSimpleName() + IAppConstants.UNDERSCORE + inStatus
                            + IAppConstants.UNDERSCORE + inFunctionalBin, qrcCategoriesPojos );
                    SortingComparator<CrmQrcCategoriesPojo> sortComparator = new SortingComparator<CrmQrcCategoriesPojo>( "qrcCategory" );
                    Collections.sort( qrcCategoriesPojos, sortComparator );
                    sortComparator = null;
                }
            }
            return qrcCategoriesPojos;
        }
        else
        {
            return categoriesPojos;
        }
    }

    public static List<CrmQrcSubCategoriesPojo> getQrcSubCategory( String inStatus, String inFunctionalBin )
    {
        List<CrmQrcSubCategoriesPojo> subCategoriesPojos = (List<CrmQrcSubCategoriesPojo>) getCollection( CrmQrcSubCategoriesPojo.class
                .getSimpleName() + IAppConstants.UNDERSCORE + inStatus + IAppConstants.UNDERSCORE + inFunctionalBin );
        if ( !CommonValidator.isValidCollection( subCategoriesPojos ) )
        {
            List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojos = null;
            synchronized ( inStatus )
            {
                if ( !CommonValidator.isValidCollection( subCategoriesPojos ) )
                {
                    qrcSubCategoriesPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
                    List<CrmQrcCategoriesPojo> categoriesPojos = getQrcCategories( inStatus, inFunctionalBin );
                    for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
                    {
                        qrcSubCategoriesPojos.addAll( crmQrcCategoriesPojo.getCrmQrcSubCategorieses() );
                    }
                    if ( CommonValidator.isValidCollection( qrcSubCategoriesPojos ) )
                    {
                        setCollection( CrmQrcSubCategoriesPojo.class.getSimpleName() + IAppConstants.UNDERSCORE
                                + inStatus + IAppConstants.UNDERSCORE + inFunctionalBin, qrcSubCategoriesPojos );
                        SortingComparator<CrmQrcSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubCategoriesPojo>( "qrcSubCategory" );
                        Collections.sort( qrcSubCategoriesPojos, sortComparator );
                        sortComparator = null;
                    }
                    subCategoriesPojos = qrcSubCategoriesPojos;
                }
                else
                {
                    qrcSubCategoriesPojos = (List<CrmQrcSubCategoriesPojo>) getCollection( CrmQrcSubCategoriesPojo.class
                            .getSimpleName()
                            + IAppConstants.UNDERSCORE
                            + inStatus
                            + IAppConstants.UNDERSCORE
                            + inFunctionalBin );
                }
            }
            return qrcSubCategoriesPojos;
        }
        else
        {
            return subCategoriesPojos;
        }
    }

    public static List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategory( String inStatus,
                                                                         String inFunctionalBin,
                                                                         long inSubCategory )
    {
        @SuppressWarnings("unchecked")
        List<CrmQrcSubSubCategoriesPojo> records = (List<CrmQrcSubSubCategoriesPojo>) getCollection( CrmQrcSubSubCategoriesPojo.class
                .getSimpleName()
                + IAppConstants.UNDERSCORE
                + inStatus
                + IAppConstants.UNDERSCORE
                + inFunctionalBin
                + IAppConstants.UNDERSCORE + inSubCategory );
        if ( !CommonValidator.isValidCollection( records ) )
        {
            List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
            List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojos = getQrcSubCategory( inStatus, inFunctionalBin );
            if ( CommonValidator.isValidCollection( qrcSubCategoriesPojos ) )
            {
                CrmQrcSubCategoriesPojo subcat = getQrcSubCategory( inSubCategory, qrcSubCategoriesPojos );
                if ( StringUtils.isValidObj( subcat ) )
                {
                    qrcSubSubCategoriesPojos.addAll( subcat.getCrmQrcSubSubCategorieses() );
                    if ( CommonValidator.isValidCollection( qrcSubSubCategoriesPojos ) )
                    {
                        SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
                        Collections.sort( qrcSubSubCategoriesPojos, sortComparator );
                        sortComparator = null;
                        setCollection( CrmQrcSubSubCategoriesPojo.class.getSimpleName() + IAppConstants.UNDERSCORE
                                + inStatus + IAppConstants.UNDERSCORE + inFunctionalBin + IAppConstants.UNDERSCORE
                                + inSubCategory, qrcSubSubCategoriesPojos );
                    }
                }
            }
            return qrcSubSubCategoriesPojos;
        }
        else
        {
            return records;
        }
    }

    public static List<CrmQrcCategoriesPojo> getActiveQrcCategoriesByModuleType( String inModuleType )
    {
        List<CrmQrcCategoriesPojo> moduleTypeQrcCatPojos = null;
        List<CrmQrcCategoriesPojo> categoriesPojos = getQrcManagerBm().getQrcCategories( CRMStatusCode.ACTIVE
                                                                                                 .getStatusCode() );
        if ( CommonValidator.isValidCollection( categoriesPojos ) && !StringUtils.equals( "0", inModuleType ) )
        {
            moduleTypeQrcCatPojos = new ArrayList<CrmQrcCategoriesPojo>();
            for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
            {
                if ( StringUtils.equals( inModuleType, crmQrcCategoriesPojo.getModuleType() )
                        && StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                {
                    moduleTypeQrcCatPojos.add( crmQrcCategoriesPojo );
                }
                else if ( !StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                {
                    moduleTypeQrcCatPojos.add( crmQrcCategoriesPojo );
                }
            }
        }
        return moduleTypeQrcCatPojos;
    }
}
