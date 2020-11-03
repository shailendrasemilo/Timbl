package com.np.tele.crm.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.validator.util.CommonValidator;

public class CRMUtils
{
    private static final String                   ALL_STATUS             = "AllStatus";
    private static final String                   USER_STATUS            = "UserStatus";
    private static final String                   PLEASE_SELECT          = "Please Select";
    private static final String                   ALL                    = "All";
    private static final String                   PARTIAL_STATUS         = "PartialStatus";
    private static final String                   LEAD_STATUS            = "LeadStatus";
    private static final String                   ACIVE_INACTIVE_STATUS  = "AciveInactiveStatus";
    private static final String                   CMS_FILE_STATUS        = "CMSFileStatus";
    private static final String                   CMS_RECORD_STATUS      = "CMSRecordStatus";
    private static final String                   CMS_LMS_ACTIONS        = "LMSActions";
    //    private static final String                   CUSTOMER_SERVICE_TYPE_LIST = "customerServiceTypeList";
    private static final String                   CUSTOMER_STATUS        = "CustomerStatus";
    private static final String                   MANAGE_CUSTOMER_STATUS = "ManageStatus";
    private static final String                   QRC_TICKET_STATUS_LIST = "qrcTicketStatus";
    private static final String                   QRC_TICKET_TYPE        = "qrcType";
    private static final String                   CRM_TICKET_ACTIONS     = "ticketAction";
    private static final String                   CRM_WAIVER_STATUS      = "WaiverStatus";
    private static final String                   CRM_WORKFLOW_STATUS    = "WorkflowStatus";
    private static final Logger                   LOGGER                 = Logger.getLogger( CRMUtils.class );
    private static Map<String, List<ContentPojo>> contentMap             = new LinkedHashMap<String, List<ContentPojo>>();

    private static List<ContentPojo> getList( String inKey )
    {
        return contentMap.get( inKey );
    }

    private static void setList( String inKey, List<ContentPojo> inList )
    {
        contentMap.put( inKey, inList );
    }
    static
    {
        init();
    }

    private static void init()
    {
        Class<CRMUtils> c = CRMUtils.class;
        GlobalUtils.executeStaticNoArgsMethods( c );
    }

    public static String generatePassword( String inFirstName, String inLastName, String inMobileNo )
    {
        String generatedPassword = null;
        try
        {
            if ( inFirstName.length() == 1 )
            {
                generatedPassword = inFirstName.substring( 0, 1 ) + inFirstName.substring( 0, 1 )
                        + inFirstName.substring( 0, 1 ) + inLastName.substring( 0, 3 ) + "@"
                        + inMobileNo.substring( 0, 3 );
            }
            else if ( inFirstName.length() == 2 )
            {
                generatedPassword = inFirstName.substring( 0, 2 ) + inFirstName.substring( 0, 1 )
                        + inLastName.substring( 0, 3 ) + "@" + inMobileNo.substring( 0, 3 );
            }
            else
            {
                generatedPassword = inFirstName.substring( 0, 3 ) + inLastName.substring( 0, 3 ) + "@"
                        + inMobileNo.substring( 0, 3 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Generate Password Method" + ex );
        }
        return generatedPassword;
    }

    public static List<ContentPojo> getAllStatusList()
    {
        List<ContentPojo> statusList = getList( CRMUtils.ALL_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                statusList.add( contentPojo );
            }
            setList( CRMUtils.ALL_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getAciveInactiveStatusList()
    {
        List<ContentPojo> statusList = getList( CRMUtils.ACIVE_INACTIVE_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo( CRMUtils.ALL, "" );
            statusList.add( contentPojo );
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.ACIVE_INACTIVE_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    statusList.add( contentPojo );
                }
            }
            setList( CRMUtils.ACIVE_INACTIVE_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getPartialStatusList()
    {
        List<ContentPojo> partialStatusList = getList( PARTIAL_STATUS );
        if ( !StringUtils.isValidObj( partialStatusList ) || partialStatusList.isEmpty() )
        {
            partialStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo( CRMUtils.ALL, "" );
            partialStatusList.add( contentPojo );
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.PARTIAL_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    partialStatusList.add( contentPojo );
                }
            }
            setList( PARTIAL_STATUS, partialStatusList );
        }
        return partialStatusList;
    }

    public static List<ContentPojo> getLeadStatusList()
    {
        List<ContentPojo> statusList = getList( LEAD_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo( CRMUtils.ALL, "" );
            statusList.add( contentPojo );
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.LEAD_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    statusList.add( contentPojo );
                }
            }
            setList( LEAD_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getFullUserStatusList()
    {
        List<ContentPojo> fullStatusList = getList( CRMUtils.USER_STATUS );
        if ( !StringUtils.isValidObj( fullStatusList ) || fullStatusList.isEmpty() )
        {
            fullStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo( CRMUtils.ALL, "" );
            fullStatusList.add( contentPojo );
            for ( CRMStatusCode crmStatusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( crmStatusCode.getModule(), CRMUtils.USER_STATUS ) )
                {
                    contentPojo = new ContentPojo( crmStatusCode.getStatusDesc(), crmStatusCode.getStatusCode() );
                    fullStatusList.add( contentPojo );
                }
            }
            setList( CRMUtils.USER_STATUS, fullStatusList );
        }
        return fullStatusList;
    }

    public static List<ContentPojo> getFieldTypes()
    {
        List<ContentPojo> fieldTypeList = getList( CRMParameter.FIELDTYPE.getParameter() );
        if ( !StringUtils.isValidObj( fieldTypeList ) || fieldTypeList.isEmpty() )
        {
            fieldTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.FIELDTYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    fieldTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.FIELDTYPE.getParameter(), fieldTypeList );
        }
        return fieldTypeList;
    }

    public static List<ContentPojo> getPartnerTypes()
    {
        List<ContentPojo> allPartnerTypeList = getList( CRMParameter.PARTNER_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( allPartnerTypeList ) || allPartnerTypeList.isEmpty() )
        {
            allPartnerTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PARTNER_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allPartnerTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.PARTNER_TYPE.getParameter(), allPartnerTypeList );
        }
        return allPartnerTypeList;
    }

    public static List<ContentPojo> getProducts()
    {
        List<ContentPojo> allProductsList = getList( CRMParameter.PRODUCT.getParameter() );
        if ( !StringUtils.isValidObj( allProductsList ) || allProductsList.isEmpty() )
        {
            allProductsList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PRODUCT.getParameter() )
                        && !StringUtils.equals( crmDisplayConst.getCode(),
                                                CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allProductsList.add( contentPojo );
                }
            }
            setList( CRMParameter.PRODUCT.getParameter(), allProductsList );
        }
        return allProductsList;
    }

    public static List<ContentPojo> getOltType()
    {
        List<ContentPojo> oltTypeList = getList( CRMParameter.OLTTYPE.getParameter() );
        if ( !StringUtils.isValidObj( oltTypeList ) || oltTypeList.isEmpty() )
        {
            oltTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.OLTTYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    oltTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.OLTTYPE.getParameter(), oltTypeList );
        }
        return oltTypeList;
    }

    public static List<ContentPojo> getAllStages()
    {
        List<ContentPojo> leadStages = getList( CRMOperationStages.class.getSimpleName() );
        if ( !StringUtils.isValidObj( leadStages ) || leadStages.isEmpty() )
        {
            leadStages = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMOperationStages crmLeadStages : CRMOperationStages.values() )
            {
                contentPojo = new ContentPojo( crmLeadStages.getDesc(), crmLeadStages.getCode() );
                leadStages.add( contentPojo );
            }
            setList( CRMOperationStages.class.getSimpleName(), leadStages );
        }
        return leadStages;
    }

    public static List<ContentPojo> getFunctionalBins()
    {
        List<ContentPojo> functionalBins = getList( CRMFunctionalBinStages.class.getSimpleName() );
        if ( !StringUtils.isValidObj( functionalBins ) || functionalBins.isEmpty() )
        {
            functionalBins = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMFunctionalBinStages functionalBin : CRMFunctionalBinStages.values() )
            {
                contentPojo = new ContentPojo( functionalBin.getFunctionalBin(), functionalBin.getStage() );
                functionalBins.add( contentPojo );
            }
            setList( CRMFunctionalBinStages.class.getSimpleName(), functionalBins );
        }
        return functionalBins;
    }

    public static List<ContentPojo> getFunctionalBinsByStages()
    {
        List<ContentPojo> functionalBins = getList( CRMFunctionalBinStages.class.getSimpleName()
                + CRMOperationStages.class.getSimpleName() );
        if ( !StringUtils.isValidObj( functionalBins ) || functionalBins.isEmpty() )
        {
            functionalBins = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMFunctionalBinStages functionalBin : CRMFunctionalBinStages.values() )
            {
                String[] substages = StringUtils.split( functionalBin.getSubStages(), IAppConstants.COMMA );
                if ( StringUtils.isValidObj( substages ) )
                {
                    for ( String subStage : substages )
                    {
                        if ( StringUtils.isNotBlank( subStage ) )
                        {
                            contentPojo = new ContentPojo( functionalBin.getFunctionalBin(), subStage );
                            functionalBins.add( contentPojo );
                        }
                    }
                }
            }
            setList( CRMFunctionalBinStages.class.getSimpleName() + CRMOperationStages.class.getSimpleName(),
                     functionalBins );
            LOGGER.info( CRMFunctionalBinStages.class.getSimpleName() + CRMOperationStages.class.getSimpleName() + ":"
                    + functionalBins );
        }
        return functionalBins;
    }

    public static List<ContentPojo> getAppointmentModes()
    {
        List<ContentPojo> appointmentModes = getList( CRMParameter.MODE_OF_APPOINTMENT.getParameter() );
        if ( !StringUtils.isValidObj( appointmentModes ) || appointmentModes.isEmpty() )
        {
            appointmentModes = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils
                        .equals( crmDisplayConst.getListType(), CRMParameter.MODE_OF_APPOINTMENT.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    appointmentModes.add( contentPojo );
                }
            }
            setList( CRMParameter.MODE_OF_APPOINTMENT.getParameter(), appointmentModes );
        }
        return appointmentModes;
    }

    public static List<ContentPojo> getAppointmentTimings()
    {
        List<ContentPojo> appointmentTimings = getList( CRMParameter.APPOINTMENT_TIME.getParameter() );
        if ( !StringUtils.isValidObj( appointmentTimings ) || appointmentTimings.isEmpty() )
        {
            appointmentTimings = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.APPOINTMENT_TIME.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    appointmentTimings.add( contentPojo );
                }
            }
            setList( CRMParameter.APPOINTMENT_TIME.getParameter(), appointmentTimings );
        }
        return appointmentTimings;
    }

    public static List<ContentPojo> getCategoryList()
    {
        List<ContentPojo> categoryList = getList( CRMParameter.CATEGORY.getParameter() );
        if ( !StringUtils.isValidObj( categoryList ) || categoryList.isEmpty() )
        {
            categoryList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMRCAReason crmrcaReason : CRMRCAReason.values() )
            {
                if ( StringUtils.equals( crmrcaReason.getId(), CRMParameter.CATEGORY.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmrcaReason.getStatusDesc(),
                                                   crmrcaReason.getStatusCode(),
                                                   crmrcaReason.getModuleName() );
                    categoryList.add( contentPojo );
                }
            }
            setList( CRMParameter.CATEGORY.getParameter(), categoryList );
        }
        return categoryList;
    }

    public static List<ContentPojo> getConnectionTypes()
    {
        List<ContentPojo> dataList = getList( CRMParameter.CONNECTION_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( dataList ) || dataList.isEmpty() )
        {
            dataList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.CONNECTION_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    dataList.add( contentPojo );
                }
            }
            setList( CRMParameter.CONNECTION_TYPE.getParameter(), dataList );
        }
        return dataList;
    }

    public static List<ContentPojo> getVisaTypes()
    {
        List<ContentPojo> dataList = getList( CRMParameter.VISA_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( dataList ) || dataList.isEmpty() )
        {
            dataList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.VISA_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    dataList.add( contentPojo );
                }
            }
            setList( CRMParameter.VISA_TYPE.getParameter(), dataList );
        }
        return dataList;
    }

    public static List<ContentPojo> getDayOfInstallations()
    {
        List<ContentPojo> allDayOfInstallationList = getList( CRMParameter.DAY_OF_INSTALLATION.getParameter() );
        if ( !StringUtils.isValidObj( allDayOfInstallationList ) || allDayOfInstallationList.isEmpty() )
        {
            allDayOfInstallationList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils
                        .equals( crmDisplayConst.getListType(), CRMParameter.DAY_OF_INSTALLATION.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allDayOfInstallationList.add( contentPojo );
                }
            }
            setList( CRMParameter.DAY_OF_INSTALLATION.getParameter(), allDayOfInstallationList );
        }
        return allDayOfInstallationList;
    }

    public static List<ContentPojo> getServiceTypesList()
    {
        List<ContentPojo> customerServiceTypes = getList( CRMParameter.SERVICE_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( customerServiceTypes ) || customerServiceTypes.isEmpty() )
        {
            customerServiceTypes = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayListConstants : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayListConstants.getListType(),
                                         CRMParameter.SERVICE_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayListConstants.getValue(),
                                                   crmDisplayListConstants.getCode() );
                    customerServiceTypes.add( contentPojo );
                }
            }
            setList( CRMParameter.SERVICE_TYPE.getParameter(), customerServiceTypes );
        }
        return customerServiceTypes;
    }

    public static List<ContentPojo> getCPEStatusList()
    {
        List<ContentPojo> allCPEStatusList = getList( CRMParameter.CPE_STATUS.getParameter() );
        if ( !StringUtils.isValidObj( allCPEStatusList ) || allCPEStatusList.isEmpty() )
        {
            allCPEStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.CPE_STATUS.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allCPEStatusList.add( contentPojo );
                }
            }
            setList( CRMParameter.CPE_STATUS.getParameter(), allCPEStatusList );
        }
        return allCPEStatusList;
    }

    public static List<ContentPojo> getEOCCPEStatusList()
    {
        List<ContentPojo> allCPEStatusList = new ArrayList<ContentPojo>();
        ContentPojo contentPojo = new ContentPojo( CRMDisplayListConstants.NEXTRA_RECOMMENDED.getValue(),
                                                   CRMDisplayListConstants.NEXTRA_RECOMMENDED.getCode() );
        allCPEStatusList.add( contentPojo );
        return allCPEStatusList;
    }

    public static List<ContentPojo> getBillingPreferences()
    {
        List<ContentPojo> allBillingPreferenceList = getList( CRMParameter.BILLING_PREFERENCES.getParameter() );
        if ( !StringUtils.isValidObj( allBillingPreferenceList ) || allBillingPreferenceList.isEmpty() )
        {
            allBillingPreferenceList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils
                        .equals( crmDisplayConst.getListType(), CRMParameter.BILLING_PREFERENCES.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allBillingPreferenceList.add( contentPojo );
                }
            }
            SortingComparator<ContentPojo> sortComparator = new SortingComparator<ContentPojo>( "contentValue" );
            Collections.sort( allBillingPreferenceList, sortComparator );
            setList( CRMParameter.BILLING_PREFERENCES.getParameter(), allBillingPreferenceList );
        }
        return allBillingPreferenceList;
    }

    public static List<ContentPojo> getPaymentMode()
    {
        List<ContentPojo> allPaymentModeList = getList( CRMParameter.PAYMENT_MODE.getParameter() );
        if ( !StringUtils.isValidObj( allPaymentModeList ) || allPaymentModeList.isEmpty() )
        {
            allPaymentModeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_MODE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allPaymentModeList.add( contentPojo );
                }
            }
            setList( CRMParameter.PAYMENT_MODE.getParameter(), allPaymentModeList );
        }
        return allPaymentModeList;
    }

    /**
     * Module Wise Payent Modes
     * @param inModule : I&A, Refund etc.
     * @return
     */
    public static List<ContentPojo> getPaymentMode( String inModule )
    {
        List<ContentPojo> allPaymentModeList = getList( CRMParameter.PAYMENT_MODE.getParameter()
                + IAppConstants.UNDERSCORE + inModule );
        if ( !StringUtils.isValidObj( allPaymentModeList ) || allPaymentModeList.isEmpty() )
        {
            allPaymentModeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_MODE.getParameter() )
                        && StringUtils.contains( crmDisplayConst.getFilter1(), inModule ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allPaymentModeList.add( contentPojo );
                }
            }
            setList( CRMParameter.PAYMENT_MODE.getParameter() + IAppConstants.UNDERSCORE + inModule, allPaymentModeList );
        }
        return allPaymentModeList;
    }

    public static List<ContentPojo> getDocuments()
    {
        List<ContentPojo> allDocumentList = getList( CRMParameter.DOCUMENTS.getParameter() );
        if ( !StringUtils.isValidObj( allDocumentList ) || allDocumentList.isEmpty() )
        {
            allDocumentList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.DOCUMENTS.getParameter() )
                        || StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.COMMON_PROOF.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allDocumentList.add( contentPojo );
                }
            }
            setList( CRMParameter.DOCUMENTS.getParameter(), allDocumentList );
        }
        List<ContentPojo> idProofs = new ArrayList<ContentPojo>();
        if ( CommonValidator.isValidCollection( allDocumentList ) )
        {
            ContentPojo cPojo = null;
            for ( ContentPojo contentPojo : allDocumentList )
            {
                cPojo = new ContentPojo();
                CRMUtils.copyAllProperties( cPojo, contentPojo );
                idProofs.add( cPojo );
            }
        }
        return idProofs;
    }

    public static List<ContentPojo> getAddressProofList()
    {
        List<ContentPojo> allAddressProofList = getList( CRMParameter.ADDRESS_PROOF.getParameter() );
        if ( !StringUtils.isValidObj( allAddressProofList ) || allAddressProofList.isEmpty() )
        {
            allAddressProofList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.ADDRESS_PROOF.getParameter() )
                        || StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.COMMON_PROOF.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    allAddressProofList.add( contentPojo );
                }
            }
            setList( CRMParameter.ADDRESS_PROOF.getParameter(), allAddressProofList );
        }
        List<ContentPojo> addressProofList = new ArrayList<ContentPojo>();
        if ( CommonValidator.isValidCollection( allAddressProofList ) )
        {
            ContentPojo cPojo = null;
            for ( ContentPojo contentPojo : allAddressProofList )
            {
                cPojo = new ContentPojo();
                CRMUtils.copyAllProperties( cPojo, contentPojo );
                addressProofList.add( cPojo );
            }
        }
        return addressProofList;
    }

    public static List<ContentPojo> getNetworkConnectivityInfo()
    {
        List<ContentPojo> networkConnectivityInfoList = getList( CRMParameter.NETWORK_CONNECTIVITY_INFO.getParameter() );
        if ( !StringUtils.isValidObj( networkConnectivityInfoList ) || networkConnectivityInfoList.isEmpty() )
        {
            networkConnectivityInfoList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(),
                                         CRMParameter.NETWORK_CONNECTIVITY_INFO.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    networkConnectivityInfoList.add( contentPojo );
                }
            }
            setList( CRMParameter.NETWORK_CONNECTIVITY_INFO.getParameter(), networkConnectivityInfoList );
        }
        return networkConnectivityInfoList;
    }

    public static List<ContentPojo> getcustomerProfileSearchList( String inParam, boolean flag )
    {
        List<ContentPojo> customerProfileSearchList = getList( CustomerProfile.class.getSimpleName() + inParam + flag );
        if ( !StringUtils.isValidObj( customerProfileSearchList ) || customerProfileSearchList.isEmpty() )
        {
            customerProfileSearchList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo();
            if ( flag )
            {
                for ( CustomerProfile customerProfile : CustomerProfile.values() )
                {
                    if ( customerProfile.getShowValue() == 0
                            && StringUtils.contains( customerProfile.getModule(), inParam ) )
                    {
                        contentPojo = new ContentPojo( customerProfile.getValue(),
                                                       customerProfile.getCode(),
                                                       customerProfile.getModule() );
                        customerProfileSearchList.add( contentPojo );
                    }
                    if ( customerProfile.getShowValue() == 0 && StringUtils.isBlank( inParam ) )
                    {
                        contentPojo = new ContentPojo( customerProfile.getValue(),
                                                       customerProfile.getCode(),
                                                       customerProfile.getModule() );
                        customerProfileSearchList.add( contentPojo );
                    }
                }
            }
            else
            {
                for ( CustomerProfile customerProfile : CustomerProfile.values() )
                {
                    if ( StringUtils.contains( customerProfile.getMethod(), inParam ) )
                    {
                        contentPojo = new ContentPojo( customerProfile.getValue(),
                                                       customerProfile.getCode(),
                                                       customerProfile.getModule() );
                        customerProfileSearchList.add( contentPojo );
                    }
                }
            }
            setList( CustomerProfile.class.getSimpleName() + inParam + flag, customerProfileSearchList );
        }
        return customerProfileSearchList;
    }

    public static List<ContentPojo> getChequeRealizationStatusList()
    {
        List<ContentPojo> chequeRealizationStatusList = getList( CRMParameter.REALIZATION_STATUS_LIST.getParameter() );
        if ( !StringUtils.isValidObj( chequeRealizationStatusList ) || chequeRealizationStatusList.isEmpty() )
        {
            chequeRealizationStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(),
                                         CRMParameter.REALIZATION_STATUS_LIST.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    chequeRealizationStatusList.add( contentPojo );
                }
            }
            setList( CRMParameter.REALIZATION_STATUS_LIST.getParameter(), chequeRealizationStatusList );
        }
        return chequeRealizationStatusList;
    }

    public static List<ContentPojo> getPaymentRecievedStatus()
    {
        List<ContentPojo> paymentRecievedStatusList = getList( CRMParameter.PAYMENT_RECIEVED_STATUS_LIST.getParameter() );
        if ( !StringUtils.isValidObj( paymentRecievedStatusList ) || paymentRecievedStatusList.isEmpty() )
        {
            paymentRecievedStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(),
                                         CRMParameter.PAYMENT_RECIEVED_STATUS_LIST.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    paymentRecievedStatusList.add( contentPojo );
                }
            }
            setList( CRMParameter.PAYMENT_RECIEVED_STATUS_LIST.getParameter(), paymentRecievedStatusList );
        }
        return paymentRecievedStatusList;
    }

    public static List<ContentPojo> getPaymentCaseStatusList()
    {
        List<ContentPojo> paymentCaseStatusList = getList( CRMParameter.PAYMENT_CASE_STATUS_LIST.getParameter() );
        if ( !StringUtils.isValidObj( paymentCaseStatusList ) || paymentCaseStatusList.isEmpty() )
        {
            paymentCaseStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(),
                                         CRMParameter.PAYMENT_CASE_STATUS_LIST.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    paymentCaseStatusList.add( contentPojo );
                }
            }
            setList( CRMParameter.PAYMENT_CASE_STATUS_LIST.getParameter(), paymentCaseStatusList );
        }
        return paymentCaseStatusList;
    }

    public static List<ContentPojo> getInstallationStatusList()
    {
        List<ContentPojo> installationStatusList = getList( CRMParameter.INSTALLATION_STATUS_LIST.getParameter() );
        if ( !StringUtils.isValidObj( installationStatusList ) || installationStatusList.isEmpty() )
        {
            installationStatusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(),
                                         CRMParameter.INSTALLATION_STATUS_LIST.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    installationStatusList.add( contentPojo );
                }
            }
            setList( CRMParameter.INSTALLATION_STATUS_LIST.getParameter(), installationStatusList );
        }
        return installationStatusList;
    }

    public static List<ContentPojo> getEntityTypeList()
    {
        List<ContentPojo> entityTypeList = getList( CRMParameter.ENTITY_TYPE_LIST.getParameter() );
        if ( !StringUtils.isValidObj( entityTypeList ) || entityTypeList.isEmpty() )
        {
            entityTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.ENTITY_TYPE_LIST.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    entityTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.ENTITY_TYPE_LIST.getParameter(), entityTypeList );
        }
        return entityTypeList;
    }

    public static List<ContentPojo> getPaymentChannelList()
    {
        List<ContentPojo> paymentChannelList = getList( CRMParameter.PAYMENT_CHANNEL.getParameter() );
        if ( !StringUtils.isValidObj( paymentChannelList ) || paymentChannelList.isEmpty() )
        {
            paymentChannelList = new ArrayList<ContentPojo>();
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_CHANNEL.getParameter() ) )
                {
                    ContentPojo contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    paymentChannelList.add( contentPojo );
                }
            }
            setList( CRMParameter.PAYMENT_CHANNEL.getParameter(), paymentChannelList );
        }
        return paymentChannelList;
    }

    public static List<ContentPojo> getCMSFileStatusList()
    {
        List<ContentPojo> statusList = getList( CMS_FILE_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.CMS_FILE_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    statusList.add( contentPojo );
                }
            }
            setList( CMS_FILE_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getCMSRecordStatusList()
    {
        List<ContentPojo> statusList = getList( CMS_RECORD_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.CMS_RECORD_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    statusList.add( contentPojo );
                }
            }
            setList( CMS_RECORD_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getLMSActions()
    {
        List<ContentPojo> masterList = getList( CMS_LMS_ACTIONS );
        if ( !StringUtils.isValidObj( masterList ) || masterList.isEmpty() )
        {
            masterList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMActionConstants crmActions : CRMActionConstants.values() )
            {
                if ( StringUtils.equals( crmActions.getModule(), CRMParameter.LMS.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmActions.getDisplayValue(), crmActions.getStoringValue() );
                    masterList.add( contentPojo );
                }
            }
            setList( CMS_LMS_ACTIONS, masterList );
        }
        return masterList;
    }

    public static String getEnumValue( String inType, String inKey )
    {
        if ( contentMap.containsKey( inType ) )
        {
            List<ContentPojo> listPojos = contentMap.get( inType );
            ContentPojo cpojo = new ContentPojo();
            cpojo.setContentValue( StringUtils.trim( inKey ) );
            if ( listPojos.contains( cpojo ) )
            {
                int index = listPojos.indexOf( cpojo );
                if ( index >= 0 )
                {
                    cpojo = listPojos.get( index );
                    inKey = cpojo.getContentName();
                    return inKey;
                }
                else
                {
                    LOGGER.info( "Pojo not found for " + cpojo );
                    return inKey;
                }
            }
            else
            {
                LOGGER.info( "Pojo not found for " + cpojo );
                return inKey;
            }
        }
        else if ( StringUtils.equals( CRMCustomerActivityActions.class.getSimpleName(), inType ) )
        {
            if ( StringUtils.isNotBlank( CRMCustomerActivityActions.getActionByCode( inKey ) ) )
            {
                return CRMCustomerActivityActions.getActionByCode( inKey );
            }
            else if ( StringUtils.isNotBlank( inKey ) )
            {
                return inKey;
            }
            else
            {
                return IAppConstants.DASH;
            }
        }
        return null;
    }

    public static List<ContentPojo> getPerformingActions( int inParameter )
    {
        List<ContentPojo> actionsToPerform = new ArrayList<ContentPojo>();
        try
        {
            ContentPojo contentPojo = null;
            for ( CRMActionConstants crmActions : CRMActionConstants.values() )
            {
                if ( inParameter == -1 )
                {
                    contentPojo = new ContentPojo( crmActions.getDisplayValue(), crmActions.getStoringValue() );
                    actionsToPerform.add( contentPojo );
                }
                else
                {
                    if ( StringUtils.contains( crmActions.getForStage(), String.valueOf( inParameter ) ) )
                    {
                        contentPojo = new ContentPojo( crmActions.getDisplayValue(), crmActions.getStoringValue() );
                        actionsToPerform.add( contentPojo );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while calling getPerformingActions:: " + ex );
        }
        return actionsToPerform;
    }

    public static String getValue( String inStr )
    {
        String codeValue = "";
        for ( CRMActionConstants crmActions : CRMActionConstants.values() )
        {
            if ( StringUtils.equals( crmActions.getStoringValue(), inStr ) )
            {
                codeValue = crmActions.getDisplayValue();
                break;
            }
        }
        return codeValue;
    }

    public static int getStageIndex( String inStr )
    {
        int index = 0;
        for ( CRMOperationStages crmStage : CRMOperationStages.values() )
        {
            if ( StringUtils.equals( crmStage.getCode(), inStr ) )
            {
                index = crmStage.getIndex();
                break;
            }
        }
        return index;
    }

    public static <E> void setSimpleProperty( E obj, String inProperty, Object inValue )
    {
        if ( StringUtils.isValidObj( obj ) )
        {
            try
            {
                if ( org.apache.commons.beanutils.PropertyUtils.isReadable( obj, inProperty ) )
                {
                    if ( !StringUtils.isValidObj( org.apache.commons.beanutils.PropertyUtils
                            .getSimpleProperty( obj, inProperty ) ) )
                    {
                        org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( obj, inProperty, inValue );
                    }
                }
                LOGGER.info( "Class Name:" + obj.getClass() );
                LOGGER.info( inProperty + " Value: "
                        + org.apache.commons.beanutils.PropertyUtils.getProperty( obj, inProperty ) );
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

    public static <E> String getSimpleProperty( E obj, String inProperty )
    {
        Object value = null;
        if ( StringUtils.isValidObj( obj ) )
        {
            try
            {
                if ( org.apache.commons.beanutils.PropertyUtils.isReadable( obj, inProperty ) )
                {
                    value = org.apache.commons.beanutils.PropertyUtils.getSimpleProperty( obj, inProperty );
                }
                LOGGER.info( "Class Name:" + obj.getClass() );
                LOGGER.info( inProperty + " Value: "
                        + org.apache.commons.beanutils.PropertyUtils.getProperty( obj, inProperty ) );
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
        if ( StringUtils.isValidObj( value ) )
        {
            return String.valueOf( value );
        }
        return IAppConstants.EMPTY_STRING;
    }

    public static <E> void copyAllProperties( E toObj, E fromObj )
    {
        if ( StringUtils.isValidObj( fromObj ) && StringUtils.isValidObj( toObj ) )
        {
            try
            {
                org.apache.commons.beanutils.PropertyUtils.copyProperties( toObj, fromObj );
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

    public static String getServerIp()
    {
        InetAddress inetAddress = null;
        String ip = null;
        try
        {
            inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        }
        catch ( UnknownHostException ex )
        {
            LOGGER.error( "Unable to get Server IP Address" + ex );
        }
        return ip;
    }

    public static List<ContentPojo> getCustomerServiceTypeList()
    {
        LOGGER.info( ".........................In CRMUtils class and method is getCustomerServiceTypeList:.............................." );
        List<ContentPojo> customerServiceTypeList = getList( CRMParameter.SERVICE_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( customerServiceTypeList ) || customerServiceTypeList.isEmpty() )
        {
            customerServiceTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.SERVICE_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    customerServiceTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.SERVICE_TYPE.getParameter(), customerServiceTypeList );
            LOGGER.info( "Customer Service type list is : " + customerServiceTypeList );
        }
        return customerServiceTypeList;
    }

    public static List<ContentPojo> getCategories( String category, String subCategory )
    {
        return getCategories( category, subCategory, false );
    }

    /**
     * 
     * @param category Category code for fetching underlying <code>subCategories</code>
     * @param subCategory <code>null</code> to fetch all <code>subCategories</code>
     * @param description <code>false</code> for using <code>statusDesc</code>. 
     * <code>true</code> for using <code>description</code> in <code>subSubCategories</code>
     * @return
     */
    public static List<ContentPojo> getCategories( String category, String subCategory, boolean description )
    {
        List<ContentPojo> categoryList = new ArrayList<ContentPojo>();
        ContentPojo contentPojo = null;
        if ( StringUtils.isEmpty( subCategory ) )
        {
            for ( CRMRCAReason crmrcaReason : CRMRCAReason.values() )
            {
                if ( StringUtils.equals( crmrcaReason.getId(), category ) )
                {
                    contentPojo = new ContentPojo( crmrcaReason.getStatusCode(), crmrcaReason.getStatusDesc() );
                    categoryList.add( contentPojo );
                }
            }
        }
        else
        {
            outer: for ( CRMRCAReason crmrcaReason : CRMRCAReason.values() )
            {
                if ( StringUtils.equals( crmrcaReason.getId(), category ) )
                {
                    for ( CRMRCAReason crmrcaReasons : CRMRCAReason.values() )
                    {
                        if ( StringUtils.equals( crmrcaReasons.getId(), category + IAppConstants.UNDERSCORE
                                + subCategory ) )
                        {
                            contentPojo = new ContentPojo( description ? crmrcaReasons.getStatusDesc()
                                                                      : crmrcaReasons.getStatusCode(),
                                                           description ? crmrcaReasons.getDescription() : crmrcaReasons
                                                                   .getStatusDesc() );
                            categoryList.add( contentPojo );
                        }
                    }
                    break outer;
                }
            }
        }
        return categoryList;
    }

    public static List<ContentPojo> getCustomerStatus()
    {
        List<ContentPojo> customerStatus = getList( CUSTOMER_STATUS );
        if ( !CommonValidator.isValidCollection( customerStatus ) )
        {
            customerStatus = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CUSTOMER_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    customerStatus.add( contentPojo );
                }
            }
            setList( CUSTOMER_STATUS, customerStatus );
        }
        return customerStatus;
    }

    public static List<ContentPojo> getManageCustomerStatus()
    {
        List<ContentPojo> customerStatus = getList( MANAGE_CUSTOMER_STATUS );
        if ( !CommonValidator.isValidCollection( customerStatus ) )
        {
            customerStatus = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), MANAGE_CUSTOMER_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    customerStatus.add( contentPojo );
                }
            }
            setList( MANAGE_CUSTOMER_STATUS, customerStatus );
        }
        return customerStatus;
    }

    public static List<ContentPojo> getQRCTicketStatus()
    {
        List<ContentPojo> ticketstatusPOJOs = getList( QRC_TICKET_STATUS_LIST );
        if ( !StringUtils.isValidObj( ticketstatusPOJOs ) || ticketstatusPOJOs.isEmpty() )
        {
            ticketstatusPOJOs = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = new ContentPojo( CRMUtils.ALL, "" );
            ticketstatusPOJOs.add( contentPojo );
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.QRC_TICKET_STATUS_LIST ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    ticketstatusPOJOs.add( contentPojo );
                }
            }
            setList( QRC_TICKET_STATUS_LIST, ticketstatusPOJOs );
        }
        return ticketstatusPOJOs;
    }

    public static List<ContentPojo> getQRCType()
    {
        List<ContentPojo> ticketTypePOJOs = getList( CRMUtils.QRC_TICKET_TYPE );
        ContentPojo contentPojo = null;
        if ( !StringUtils.isValidObj( ticketTypePOJOs ) || ticketTypePOJOs.isEmpty() )
        {
            ticketTypePOJOs = new ArrayList<ContentPojo>();
            for ( CRMDisplayListConstants listConstant : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( listConstant.getListType(), CRMParameter.QRC.getParameter() ) )
                {
                    contentPojo = new ContentPojo( listConstant.getValue(), listConstant.getCode() );
                    ticketTypePOJOs.add( contentPojo );
                }
            }
            setList( CRMUtils.QRC_TICKET_TYPE, ticketTypePOJOs );
        }
        return ticketTypePOJOs;
    }

    public static List<ContentPojo> getTicketActions()
    {
        List<ContentPojo> crmTicketActions = getList( CRMUtils.CRM_TICKET_ACTIONS );
        ContentPojo contentPojo = null;
        if ( !StringUtils.isValidObj( crmTicketActions ) || crmTicketActions.isEmpty() )
        {
            crmTicketActions = new ArrayList<ContentPojo>();
            for ( CrmTicketActions ticketActions : CrmTicketActions.values() )
            {
                contentPojo = new ContentPojo( ticketActions.getDesc(), String.valueOf( ticketActions.getCode() ) );
                crmTicketActions.add( contentPojo );
            }
            setList( CRMUtils.CRM_TICKET_ACTIONS, crmTicketActions );
        }
        return crmTicketActions;
    }

    public static List<ContentPojo> getWaiverStatus()
    {
        List<ContentPojo> waiverStatus = getList( CRM_WAIVER_STATUS );
        if ( !CommonValidator.isValidCollection( waiverStatus ) )
        {
            waiverStatus = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRM_WAIVER_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    waiverStatus.add( contentPojo );
                }
            }
            setList( CRM_WAIVER_STATUS, waiverStatus );
        }
        return waiverStatus;
    }

    public static void main( String[] args )
    {
        List<ContentPojo> list = getcustomerProfileSearchList( CRMRequestType.QRC.getRequestCode(), false );
        for ( ContentPojo contentPojo : list )
        {
            System.out.println( "ContentValue::" + contentPojo.getContentValue() );
            System.out.println( "getCode::" + contentPojo.getContentName() );
            System.out.println( "ModuleName::" + contentPojo.getModuleName() );
        }
    }

    public static <E> void copyPropertyValue( E pojo1, E pojo2, E pojo3 )
    {
        if ( StringUtils.isValidObj( pojo1 ) && StringUtils.isValidObj( pojo2 ) )
        {
            StringBuilder oldValue = new StringBuilder();
            StringBuilder newValue = new StringBuilder();
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( pojo1 );
            for ( PropertyDescriptor descriptor : prDesc )
            {
                String propertyName = descriptor.getName();
                try
                {
                    Object value1 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo1, propertyName );
                    Object value2 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo2, propertyName );
                    if ( StringUtils.isValidObj( value2 )
                            && ! ( value1 instanceof Set || ( StringUtils.isValidObj( value2 ) ? StringUtils
                                    .contains( value2.getClass().getName(), "com.np.tele.crm.pojos.CrmUserPojo" )
                                                                                              : false ) ) )
                    {
                        if ( !value2.equals( value1 ) )
                        {
                            if ( ! ( value2 instanceof Long && ( (Long) value2 ).longValue() == 0 ) )
                            {
                                PropertyUtils.setProperty( pojo1, propertyName, value2 );
                                if ( StringUtils.isValidObj( pojo3 ) )
                                {
                                    if ( StringUtils.isValidObj( value1 ) )
                                        oldValue.append( value1 ).append( IAppConstants.WHITE_SPACE );
                                    newValue.append( value2 ).append( IAppConstants.WHITE_SPACE );
                                }
                            }
                        }
                    }
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "IllegalAccessException:", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "InvocationTargetException:", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    LOGGER.error( "NoSuchMethodException:", ex );
                }
            }
            if ( StringUtils.isValidObj( pojo3 ) )
            {
                try
                {
                    LOGGER.info( "Old Value::" + oldValue );
                    LOGGER.info( "New Value::" + newValue );
                    PropertyUtils.setProperty( pojo3, "oldValue", oldValue.toString().trim() );
                    PropertyUtils.setProperty( pojo3, "newValue", newValue.toString().trim() );
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "IllegalAccessException:", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "InvocationTargetException:", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    LOGGER.error( "NoSuchMethodException:", ex );
                }
            }
        }
    }

    public static CRMServiceCode getServiceByErrorKey( String errorKey )
    {
        for ( CRMServiceCode crmServiceCode : CRMServiceCode.values() )
        {
            if ( StringUtils.equals( crmServiceCode.getStatusCode(), errorKey ) )
            {
                return crmServiceCode;
            }
        }
        return CRMServiceCode.CRM997;
    }

    public static List<ContentPojo> getWorkflowStatus()
    {
        List<ContentPojo> statusList = getList( CRM_WORKFLOW_STATUS );
        if ( !StringUtils.isValidObj( statusList ) || statusList.isEmpty() )
        {
            statusList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMStatusCode statusCode : CRMStatusCode.values() )
            {
                if ( StringUtils.contains( statusCode.getModule(), CRMUtils.CRM_WORKFLOW_STATUS ) )
                {
                    contentPojo = new ContentPojo( statusCode.getStatusDesc(), statusCode.getStatusCode() );
                    statusList.add( contentPojo );
                }
            }
            setList( CRM_WORKFLOW_STATUS, statusList );
        }
        return statusList;
    }

    public static List<ContentPojo> getShiftingType()
    {
        List<ContentPojo> shiftingTypeList = getList( CRMParameter.SHIFTING_TYPE.getParameter() );
        if ( !StringUtils.isValidObj( shiftingTypeList ) || shiftingTypeList.isEmpty() )
        {
            shiftingTypeList = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.SHIFTING_TYPE.getParameter() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    shiftingTypeList.add( contentPojo );
                }
            }
            setList( CRMParameter.SHIFTING_TYPE.getParameter(), shiftingTypeList );
        }
        return shiftingTypeList;
    }

    public static List<ContentPojo> getPaymentChannelsForPaymentMode( String inPaymentMode )
    {
        List<ContentPojo> paymentChannelForPaymentMode = new ArrayList<ContentPojo>();
        for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
        {
            if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_CHANNEL.getParameter() ) )
            {
                if ( StringUtils.contains( crmDisplayConst.getFilter1(), inPaymentMode ) )
                {
                    ContentPojo contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    paymentChannelForPaymentMode.add( contentPojo );
                }
            }
        }
        LOGGER.info( "List Size::" + paymentChannelForPaymentMode.size() );
        return paymentChannelForPaymentMode;
    }

    /*    public static List<ContentPojo> getCaseStatusByPaymentMode( String inPaymentMode )
        {
            List<ContentPojo> caseSatusForPaymentMode = new ArrayList<ContentPojo>();
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_CASE_STATUS_LIST.getParameter() ) )
                {
                    if ( StringUtils.contains( crmDisplayConst.getFilter1(), inPaymentMode ) )
                    {
                        ContentPojo contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                        caseSatusForPaymentMode.add( contentPojo );
                    }
                }
            }
            LOGGER.info( "List Size::" + caseSatusForPaymentMode.size() );
            return caseSatusForPaymentMode;
        }*/
    public static List<ContentPojo> getAllActions()
    {
        List<ContentPojo> records = getList( CrmActionEnum.class.getSimpleName() );
        if ( !StringUtils.isValidObj( records ) || records.isEmpty() )
        {
            records = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CrmActionEnum record : CrmActionEnum.values() )
            {
                contentPojo = new ContentPojo( record.getActionName(), record.getActionCode() );
                records.add( contentPojo );
            }
            setList( CrmActionEnum.class.getSimpleName(), records );
        }
        return records;
    }

    public static List<ContentPojo> getQrcTicketActions()
    {
        List<ContentPojo> records = getList( CRMActionConstants.class.getSimpleName()
                + CRMRequestType.QRC.getRequestCode() );
        if ( !StringUtils.isValidObj( records ) || records.isEmpty() )
        {
            records = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMActionConstants record : CRMActionConstants.values() )
            {
                if ( StringUtils.contains( record.getModule(), CRMRequestType.QRC.getRequestCode() ) )
                {
                    contentPojo = new ContentPojo( record.getDisplayValue(), record.getStoringValue() );
                    records.add( contentPojo );
                }
            }
            setList( CRMActionConstants.class.getSimpleName() + CRMRequestType.QRC.getRequestCode(), records );
        }
        return records;
    }

    public static String prepareCustomerFullName( String inFName,
                                                  String inLName,
                                                  String inConnectionType,
                                                  String inGender )
    {
        String fullName = null;
        if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(), inConnectionType )
                || StringUtils.equals( CRMDisplayListConstants.PROPRIETORSHIP.getCode(), inConnectionType ) )
        {
            fullName = inFName
                    + ( StringUtils.isNotBlank( inLName ) ? ( IAppConstants.SPACE + inLName ) : IAppConstants.SPACE );
        }
        else
        {
            fullName = inFName;
        }
        return fullName;
    }

    public static List<ContentPojo> getAllowedList( List<ContentPojo> pojos )
    {
        ContentPojo contentPojo = null;
        List<ContentPojo> allowedList = new ArrayList<ContentPojo>();
        List<String> allowedModles = new ArrayList<String>();
        if ( StringUtils
                .equals( com.np.tele.crm.utils.PropertyUtils.getModuleDetails( IPropertiesConstant.LMS_MODULE ),
                         IAppConstants.Y ) )
        {
            allowedModles.add( CustomerProfile.LEAD_ID.getModule() );
        }
        if ( StringUtils
                .equals( com.np.tele.crm.utils.PropertyUtils.getModuleDetails( IPropertiesConstant.INA_MODULE ),
                         IAppConstants.Y ) )
        {
            allowedModles.add( CustomerProfile.CAF_ID.getModule() );
        }
        if ( StringUtils
                .equals( com.np.tele.crm.utils.PropertyUtils.getModuleDetails( IPropertiesConstant.QRC_MODULE ),
                         IAppConstants.Y ) )
        {
            allowedModles.add( CustomerProfile.TICKET_ID.getModule() );
        }
        if ( StringUtils
                .equals( com.np.tele.crm.utils.PropertyUtils.getModuleDetails( IPropertiesConstant.INA_MODULE ),
                         IAppConstants.Y )
                || StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                        .getModuleDetails( IPropertiesConstant.QRC_MODULE ), IAppConstants.Y ) )
        {
            allowedModles.add( CustomerProfile.NETWORK_PARTNER.getModule() );
        }
        if ( StringUtils
                .equals( com.np.tele.crm.utils.PropertyUtils.getModuleDetails( IPropertiesConstant.INA_MODULE ),
                         IAppConstants.Y )
                || StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                        .getModuleDetails( IPropertiesConstant.LMS_MODULE ), IAppConstants.Y )
                || StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                        .getModuleDetails( IPropertiesConstant.QRC_MODULE ), IAppConstants.Y ) )
        {
            allowedModles.add( CustomerProfile.SERVICE_NAME.getModule() );
        }
        if ( StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                .getModuleDetails( IPropertiesConstant.ADMIN_MODULE ), IAppConstants.Y ) )
        {
            allowedModles.add( "ADMIN" );
        }
        if ( StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                .getModuleDetails( IPropertiesConstant.REPORT_MODULE ), IAppConstants.Y ) )
        {
            allowedModles.add( "REPORT" );
        }
        if ( StringUtils.equals( com.np.tele.crm.utils.PropertyUtils
                .getModuleDetails( IPropertiesConstant.FINANCE_MODULE ), IAppConstants.Y ) )
        {
            allowedModles.add( "FINANCE" );
        }
        for ( ContentPojo pojo : pojos )
        {
            if ( allowedModles.contains( pojo.getModuleName() ) )
            {
                contentPojo = new ContentPojo( pojo.getContentName(), pojo.getContentValue() );
                allowedList.add( contentPojo );
            }
        }
        return allowedList;
    }
}
