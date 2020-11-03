package com.np.tele.crm.sla.engine.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMReportsClient;
import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.client.UserManagementClient;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmHolidayDetails;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SLACommonUtils
{
    private static final Logger                     LOGGER                   = Logger.getLogger( SLACommonUtils.class );
    private static List<String>                     holidayList              = new ArrayList<String>();
    private static List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
    private static Calendar                         pastCacheTime            = null;

    public static List<String> getHolidayList()
    {
        LOGGER.info( "Inside SLACommonUtils, getHolidayList" );
        if ( !CommonValidator.isValidCollection( holidayList )
                || pastCacheTime.get( Calendar.DATE ) != Calendar.getInstance().get( Calendar.DATE ) )
        {
            CrmHolidayDetails crmHolidayDetails = null;
            MasterDataClient masterDataClient = null;
            MasterDataDto masterDataDto = null;
            try
            {
                crmHolidayDetails = new CrmHolidayDetails();
                crmHolidayDetails.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                masterDataDto = new MasterDataDto();
                masterDataDto.setCrmHolidayPojo( crmHolidayDetails );
                masterDataClient = new MasterDataClient();
                masterDataDto = masterDataClient.masterOperations( ServiceParameter.LIST.getParameter(),
                                                                   CrmHolidayDetails.class.getSimpleName(),
                                                                   masterDataDto );
                if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && CommonValidator.isValidCollection( masterDataDto.getCrmHolidayPojos() ) )
                {
                    List<CrmHolidayDetails> crmHolidayDetailPojos = masterDataDto.getCrmHolidayPojos();
                    LOGGER.info( "Holiday List Size:" + crmHolidayDetailPojos.size() );
                    for ( CrmHolidayDetails holidayDetails : crmHolidayDetailPojos )
                    {
                        String convertedDate = DateUtils.convertXmlCalToString( holidayDetails.getHolidayDate(),
                                                                                IDateConstants.SDF_DD_MMM_YYYY );
                        holidayList.add( convertedDate );
                    }
                    pastCacheTime = Calendar.getInstance();
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while fetching Holiday list", ex );
            }
        }
        return holidayList;
    }

    public static List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoriesPojos()
    {
        LOGGER.info( "Inside SLACommonUtils, getQrcSubSubCategoriesPojos" );
        if ( !CommonValidator.isValidCollection( qrcSubSubCategoriesPojos )
                || ( StringUtils.isValidObj( pastCacheTime ) && pastCacheTime.get( Calendar.DATE ) != Calendar
                        .getInstance().get( Calendar.DATE ) ) )
        {
            CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = null;
            CRMReportService crmReportService = null;
            ReportDto reportDto = null;
            try
            {
                subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
                subSubCategoriesPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                reportDto = new ReportDto();
                reportDto.setSubSubCategoriesPojo( subSubCategoriesPojo );
                crmReportService = new CRMReportsClient();
                reportDto = crmReportService
                        .slaOperations( ServiceParameter.SLA_QRC.getParameter(),
                                        CRMParameter.QRC_SUB_SUB_CATEGORY.getParameter(), reportDto );
                if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && CommonValidator.isValidCollection( reportDto.getQrcSubSubCategoriesPojos() ) )
                {
                    qrcSubSubCategoriesPojos = reportDto.getQrcSubSubCategoriesPojos();
                    pastCacheTime = Calendar.getInstance();
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while fetching QRC Sub-Sub-Categories", ex );
            }
        }
        LOGGER.info( "QrcSubSubCategoriesPojos Size : " + qrcSubSubCategoriesPojos.size() );
        return qrcSubSubCategoriesPojos;
    }

    public static String getUsersByParameter( String inParam, String inValue, String inFunctionalBin )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        crmuserDetailsDto.setCrmUserDetailsPojo( new CrmUserPojo() );
        crmuserDetailsDto.setUserRolesPojo( new UserRolesPojo() );
        crmuserDetailsDto.getUserRolesPojo().setCrmParameter( new CrmParameterPojo() );
        StringBuilder userList = new StringBuilder();
        CRMUserManagement crmUserClient = null;
        try
        {
            crmuserDetailsDto.getCrmUserDetailsPojo().setFunctionalBin( inFunctionalBin );
            crmuserDetailsDto.getUserRolesPojo().setParameterValue( inValue );
            crmuserDetailsDto.getUserRolesPojo().getCrmParameter().setParameterName( inParam );
            crmUserClient = new UserManagementClient();
            crmuserDetailsDto = crmUserClient.getUsersByParameter( crmuserDetailsDto );
            if ( StringUtils.isValidObj( crmuserDetailsDto )
                    && CommonValidator.isValidCollection( crmuserDetailsDto.getUserRolesPojos() ) )
            {
                for ( UserRolesPojo rolesPojo : crmuserDetailsDto.getUserRolesPojos() )
                {
                    if ( StringUtils.isNotBlank( userList ) )
                    {
                        userList.append( IAppConstants.COMMA );
                    }
                    userList.append( rolesPojo.getUserId() );
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception while getting CRM User Client.", ex );
        }
        return userList.toString();
    }

    public static String getUsersByBinID( String inFunctionalBin )
    {
        CrmRcaReasonPojo crmRcaReasonPojo = null;
        StringBuilder userList = new StringBuilder();
        if ( StringUtils.isNotBlank( inFunctionalBin ) )
        {
            crmRcaReasonPojo = getFunctionBinPojo( inFunctionalBin );
        }
        if ( StringUtils.isValidObj( crmRcaReasonPojo ) )
        {
            CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
            crmuserDetailsDto.setCrmUserDetailsPojo( new CrmUserPojo() );
            CRMUserManagement crmUserClient = null;
            try
            {
                crmuserDetailsDto.getCrmUserDetailsPojo().setFunctionalBin( crmRcaReasonPojo.getCategoryId() + "" );
                crmuserDetailsDto.getCrmUserDetailsPojo().setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmUserClient = new UserManagementClient();
                crmuserDetailsDto = crmUserClient.searchUser( crmuserDetailsDto );
                if ( StringUtils.isValidObj( crmuserDetailsDto )
                        && CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserDetailsPojoList() ) )
                {
                    for ( CrmUserPojo userPojo : crmuserDetailsDto.getCrmUserDetailsPojoList() )
                    {
                        if ( StringUtils.isNotBlank( userList ) )
                        {
                            userList.append( IAppConstants.COMMA );
                        }
                        userList.append( userPojo.getUserId() );
                    }
                }
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "SOAP Exception while getting CRM User Client.", ex );
            }
        }
        return userList.toString();
    }

    public static CrmRcaReasonPojo getFunctionBinPojo( String inFunctionalBin )
    {
        CrmRcaReasonPojo crmRcaReasonPojo = null;
        try
        {
            if ( StringUtils.isNumeric( inFunctionalBin ) )
            {
                crmRcaReasonPojo = new CrmRcaReasonPojo();
                crmRcaReasonPojo.setCategoryId( Long.valueOf( inFunctionalBin ) );
                MasterDataDto masterDataDto = new MasterDataDto();
                masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
                masterDataDto = new MasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                         CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                         masterDataDto );;
                if ( StringUtils.isValidObj( masterDataDto.getCrmRcaReasonsList() )
                        && !masterDataDto.getCrmRcaReasonsList().isEmpty() )
                {
                    crmRcaReasonPojo = masterDataDto.getCrmRcaReasonsList().get( 0 );
                }
            }
            else if ( StringUtils.isNotBlank( inFunctionalBin ) )
            {
                crmRcaReasonPojo = new CrmRcaReasonPojo();
                crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
                crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
                crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
                crmRcaReasonPojo.setModificationAllowed( IAppConstants.Y );
                crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmRcaReasonPojo.setValueAlias( inFunctionalBin );
                MasterDataDto masterDataDto = new MasterDataDto();
                masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
                masterDataDto = new MasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                         CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                         masterDataDto );;
                if ( StringUtils.isValidObj( masterDataDto.getCrmRcaReasonsList() )
                        && !masterDataDto.getCrmRcaReasonsList().isEmpty() )
                {
                    crmRcaReasonPojo = masterDataDto.getCrmRcaReasonsList().get( 0 );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmRcaReasonPojo;
    }

    private static CrmAuditLogPojo getLatestAuditLog( List<CrmAuditLogPojo> inAuditLogPojos )
    {
        SortingComparator<CrmAuditLogPojo> sortComparator = new SortingComparator<CrmAuditLogPojo>( "auditLogId" );
        Collections.sort( inAuditLogPojos, Collections.reverseOrder( sortComparator ) );
        return inAuditLogPojos.get( 0 );
    }

    public static RemarksPojo getLatestRemarks( String inMappingID, XMLGregorianCalendar inStartTime, String inOwner )
    {
        ReportDto reportDto = null;
        RemarksPojo remarksPojo = null;
        CRMReportService crmReportService = null;
        try
        {
            if ( StringUtils.isNotBlank( inMappingID ) )
            {
                remarksPojo = new RemarksPojo();
                reportDto = new ReportDto();
                remarksPojo.setMappingId( inMappingID );
                remarksPojo.setCreatedBy( inOwner );
                remarksPojo.setCreatedTime( inStartTime );
                reportDto.setRemarksPojo( remarksPojo );
                crmReportService = new CRMReportsClient();
                reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                            CRMParameter.REMARKS.getParameter(), reportDto );
                if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && CommonValidator.isValidCollection( reportDto.getRemarksPojos() ) )
                {
                    SortingComparator<RemarksPojo> sortComparator = new SortingComparator<RemarksPojo>( "remarkId" );
                    Collections.sort( reportDto.getRemarksPojos(), Collections.reverseOrder( sortComparator ) );
                    remarksPojo = reportDto.getRemarksPojos().get( 0 );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception while fetching remarks for Mapping ID : " + inMappingID + " : ", ex );
        }
        return remarksPojo;
    }

    public static ReportDto.ParamMap convertToClientMap( Map<String, String> inMap )
    {
        ReportDto.ParamMap reporParamMap = null;
        ReportDto.ParamMap.Entry reporParammapEntry = null;
        try
        {
            reporParamMap = new ReportDto.ParamMap();
            for ( Map.Entry<String, String> entry : inMap.entrySet() )
            {
                reporParammapEntry = new ReportDto.ParamMap.Entry();
                reporParammapEntry.setKey( entry.getKey() );
                reporParammapEntry.setValue( entry.getValue() );
                reporParamMap.getEntry().add( reporParammapEntry );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while converting to Cliet Map", ex );
        }
        return reporParamMap;
    }

    public static Map<String, String> prepareParameterMap( final String inCurrentBin,
                                                           final String inCurrentOwner,
                                                           final String inAreaName,
                                                           final String inServiceName,
                                                           final String inModule,
                                                           final String inMappingID,
                                                           final XMLGregorianCalendar inBinAssignTime,
                                                           final String inRemarks )
    {
        Map<String, String> paramValues = new HashMap<String, String>();
        paramValues.put( CRMParameter.CURRENT_BIN.getParameter(),
                         StringUtils.isNotBlank( inCurrentBin ) ? inCurrentBin : IAppConstants.NOTAPPLICABLE );
        paramValues.put( CRMParameter.CURRENT_OWNER.getParameter(), StringUtils.isNotBlank( inCurrentOwner )
                && !StringUtils.equals( inCurrentOwner, IAppConstants.DASH ) ? inCurrentOwner
                                                                            : IAppConstants.NOTAPPLICABLE );
        paramValues.put( CRMParameter.AREA_NAME.getParameter(),
                         StringUtils.isNotBlank( inAreaName ) ? inAreaName : IAppConstants.NOTAPPLICABLE );
        paramValues.put( CRMParameter.SERVICE_NAME.getParameter(),
                         StringUtils.isNotBlank( inServiceName ) ? CRMDisplayListConstants
                                 .getValueByCode( "Product", inServiceName ) : IAppConstants.NOTAPPLICABLE );
        if ( StringUtils.equals( inModule, CRMRequestType.LMS.getRequestCode() ) )
        {
            paramValues.put( CRMParameter.LEAD_ID.getParameter(), inMappingID );
        }
        else if ( StringUtils.equals( inModule, CRMRequestType.INA.getRequestCode() ) )
        {
            paramValues.put( CRMParameter.CRFID.getParameter(), inMappingID );
        }
        paramValues.put( CRMParameter.BIN_ASSIGN_TIME.getParameter(),
                         DateUtils.convertXmlCalToString( inBinAssignTime, IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
        paramValues.put( CRMParameter.REMARKS.getParameter(),
                         StringUtils.isNotBlank( inRemarks ) ? inRemarks : IAppConstants.NOTAPPLICABLE );
        return paramValues;
    }

    //    public static List<CrmHolidayDetails> getHolidayList()
    //    {
    //        LOGGER.info( "Inside SLACommonUtils, getHolidayList" );
    //        if ( !CommonValidator.isValidCollection( crmHolidayDetailPojos )
    //                || pastCacheTime.get( Calendar.DATE ) != Calendar.getInstance().get( Calendar.DATE ) )
    //        {
    //            CrmHolidayDetails crmHolidayDetails = null;
    //            MasterDataClient masterDataClient = null;
    //            MasterDataDto masterDataDto = null;
    //            try
    //            {
    //                crmHolidayDetails = new CrmHolidayDetails();
    //                crmHolidayDetails.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
    //                masterDataDto = new MasterDataDto();
    //                masterDataDto.setCrmHolidayPojo( crmHolidayDetails );
    //                masterDataClient = new MasterDataClient();
    //                masterDataDto = masterDataClient.masterOperations( ServiceParameter.LIST.getParameter(),
    //                                                                   CrmHolidayDetails.class.getSimpleName(),
    //                                                                   masterDataDto );
    //                if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
    //                        && CommonValidator.isValidCollection( masterDataDto.getCrmHolidayPojos() ) )
    //                {
    //                    crmHolidayDetailPojos = masterDataDto.getCrmHolidayPojos();
    //                    LOGGER.info( "Holiday List Size:" + crmHolidayDetailPojos.size() );
    //                    pastCacheTime = Calendar.getInstance();
    //                }
    //            }
    //            catch ( Exception ex )
    //            {
    //                LOGGER.error( "Exception while fetching Holiday list", ex );
    //            }
    //        }
    //        return crmHolidayDetailPojos;
    //    }
    public static CrmAuditLogPojo getLatestAuditLog( String inParameter1, String inParameter2, ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLACommonUtils, getLatestAuditLog" );
        CRMReportService crmReportService = null;
        CrmAuditLogPojo auditLogPojo = null;
        try
        {
            crmReportService = new CRMReportsClient();
            inReportDto = crmReportService.slaOperations( inParameter1, inParameter2, inReportDto );
            if ( StringUtils.equals( inReportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( inReportDto.getAuditLogPojos() ) )
            {
                auditLogPojo = getLatestAuditLog( inReportDto.getAuditLogPojos() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while fetching audit log history", ex );
        }
        return auditLogPojo;
    }

    public static String getQrcSubCategory( long inQrcSubCategoryId )
    {
        LOGGER.info( "Inside SLACommonUtils, getQrcSubCategory" );
        String qrcSubCategory = null;
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = null;
        CRMReportService crmReportService = null;
        ReportDto reportDto = null;
        try
        {
            subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
            subSubCategoriesPojo.setQrcSubCategoryId( inQrcSubCategoryId );
            reportDto = new ReportDto();
            reportDto.setSubSubCategoriesPojo( subSubCategoriesPojo );
            crmReportService = new CRMReportsClient();
            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_QRC.getParameter(),
                                                        CRMParameter.QRC_SUB_CATEGORY.getParameter(), reportDto );
            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                subSubCategoriesPojo = reportDto.getSubSubCategoriesPojo();
                qrcSubCategory = subSubCategoriesPojo.getQrcSubSubCategory();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching QRC Sub-Sub-Categories", ex );
        }
        return qrcSubCategory;
    }
}
