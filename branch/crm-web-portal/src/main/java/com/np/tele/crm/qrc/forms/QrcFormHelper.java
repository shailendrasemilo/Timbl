package com.np.tele.crm.qrc.forms;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.action.Device;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustInteractionsPojo;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmOrderDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmShiftingWorkflowPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( QrcFormHelper.class );

    public static void resetQRCForm( QrcForm inForm, String inMethod )
    {
        if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_SEARCH_CUSTOMER_PAGE ) )
        {
            LOGGER.info( "in reset: " + inMethod );
            inForm.setSearchString( "" );
            inForm.setCustomerId( "" );
            inForm.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            inForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
            inForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
            inForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inForm.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
            inForm.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            inForm.setNationalityDetailsPojo( new CrmNationalityDetailsPojo() );
            inForm.setCrmCustomerActivityList( null );
            inForm.setSrTicketsPojos( null );
            inForm.setEndDate( null );
            inForm.setCrmQrcWhitelistPojo( new CrmQrcWhitelistPojo() );
            inForm.setInvoicePojo( new CrmInvoiceDetailsPojo() );
            inForm.setInvoiceList( new ArrayList<CrmInvoiceDetailsPojo>() );
            inForm.setCrmAddonPlanMasterPojo( null );
            inForm.setCrmPlanMasterPojo( null );
            inForm.setCrmCustAssetDetailsPojo( null );
            inForm.setCrmPaymentDetailsPojo( new CrmPaymentDetailsPojo() );
            inForm.setDevice1Object( null );
            inForm.setPartnerNetworkConfigPojo( new CrmPartnerNetworkConfigPojo() );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_SEARCH_CUSTOMER ) )
        {
            LOGGER.info( "in reset: " + inMethod );
            inForm.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            inForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
            inForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
            inForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inForm.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
            inForm.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            inForm.setNationalityDetailsPojo( new CrmNationalityDetailsPojo() );
            inForm.setCrmCustomerActivityList( null );
            inForm.setSrTicketsPojos( null );
            inForm.setEndDate( null );
            inForm.setCrmQrcWhitelistPojo( new CrmQrcWhitelistPojo() );
            inForm.setInvoicePojo( new CrmInvoiceDetailsPojo() );
            inForm.setInvoiceList( new ArrayList<CrmInvoiceDetailsPojo>() );
            inForm.setCrmAddonPlanMasterPojo( null );
            inForm.setCrmPlanMasterPojo( null );
            inForm.setCrmCustAssetDetailsPojo( null );
            inForm.setCrmPaymentDetailsPojo( new CrmPaymentDetailsPojo() );
            inForm.setDevice1Object( null );
            inForm.setPartnerNetworkConfigPojo( new CrmPartnerNetworkConfigPojo() );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_ADD_SINGLE_WHITELIST ) )
        {
            LOGGER.info( "in reset: " + inMethod );
            inForm.setEndDate( null );
            inForm.setCrmRcaReasonPojos( null );
            if ( StringUtils.isValidObj( inForm.getCrmQrcWhitelistPojo() ) )
            {
                inForm.getCrmQrcWhitelistPojo().setReason( 0 );
            }
            inForm.setRemoveOption( "" );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_BARRING_UNBARRING_PAGE ) )
        {
            LOGGER.info( "in reset: " + inMethod );
            inForm.setSrTicketNo( null );
            inForm.setExceptionReason( 0 );
            inForm.setCrmRcaReasonPojos( null );
            inForm.setRemarksPojo( new RemarksPojo() );
            inForm.setInExceptionList( false );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_DISCONNECTION_PAGE ) )
        {
            inForm.setRemarksPojo( new RemarksPojo() );
            inForm.setSrTicketNo( null );
            inForm.setChurnType( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_ADDRESS_CHANGE_PAGE ) )
        {
            inForm.setNewInstallationAddress( null );
            inForm.setInstAddrArea( null );
            inForm.setInstAddrLocality( null );
            inForm.setInstAddrSociety( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_WAIVER_PAGE ) )
        {
            LOGGER.info( "in reset:::::::::::::::::::::::::: " + inMethod );
            inForm.setCustWaiverPojo( new CrmCustWaiverPojo() );
            inForm.setRemarksPojo( new RemarksPojo() );
            inForm.setSrTicketNo( null );
            inForm.setCustWaiverPojos( new ArrayList<CrmCustWaiverPojo>() );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_MAC )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_OPTION82 )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_CPE )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_DEVICE_CHANGE_PAGE ) )
        {
            LOGGER.info( "in reset: " + inMethod );
            inForm.setNewOption82( "" );
            inForm.setNewPrimaryMacId( "" );
            inForm.setNewSecondaryMacId( "" );
            inForm.setShowDivValue( "" );
            inForm.setSrTicketNo( "" );
        }
        /*else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_ADD_ACCESSORIES_PAGE ) )
        {
            inForm.setShowDivValue( null );
            inForm.setRemarksPojo( null );
            inForm.setSrTicketNo( null );
            inForm.setCrmCustAssetDetailsPojo( null );
            inForm.setCrmCustAssetDetailsPojos( new ArrayList<CrmCustAssetDetailsPojo>() );
        }*/
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_CUSTOMER_ACTIVATION_PLAN ) )
        {
            LOGGER.info( "in reset:::::::::::::::::::::::::: " + inMethod );
            inForm.setPlanType( null );
            inForm.setAddonAction( null );
            // inForm.setPlanMasterList( null );
            //inForm.setAddonPlanMasterList( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_TARIFF_MIGRATION_PAGE ) )
        {
            LOGGER.info( "in reset:::::::::::::::::::::::::: " + inMethod );
            inForm.setPlanType( null );
            inForm.setPlanUsageType( null );
            inForm.setPlanMasterList( null );
            inForm.setAddonPlanMasterList( null );
            inForm.setSrTicketsPojos( null );
            inForm.setBasePlanQuota( 0l );
            inForm.setAddonPlanQuota( 0l );
            inForm.setTotalQuota( 0l );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_SAVE_CUSTOMER_PLAN ) )
        {
            LOGGER.info( "in reset:::::::::::::::::::::::::: " + inMethod );
            inForm.setPlanType( null );
            inForm.setPlanUsageType( null );
            if ( CommonValidator.isValidCollection( inForm.getPlanMasterList() ) )
            {
                for ( CrmPlanMasterPojo planMasterPojo : inForm.getPlanMasterList() )
                {
                    planMasterPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_VIEW_TICKET_PAGE ) )
        {
            if ( StringUtils.isValidObj( inForm.getRemarksPojo() ) )
            {
                inForm.getRemarksPojo().setRemarks( "" );
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_TICKET_PAGE ) )
        {
            if ( StringUtils.isValidObj( inForm.getRemarksPojo() ) )
            {
                inForm.getRemarksPojo().setRemarks( "" );
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_SEARCH_CUSTOMER_INTERACTION ) )
        {
            LOGGER.info( "in reset:::::::::::::::::::::::::: " + inMethod );
            if ( StringUtils.isValidObj( inForm.getCustomerInteractionCategories() ) )
            {
                LOGGER.info( "Inside Valid Categories" );
                inForm.setCustomerInteractionCategories( new ArrayList<ContentPojo>() );
            }
            if ( StringUtils.isValidObj( inForm.getInteractionSubCategories() ) )
            {
                LOGGER.info( "Inside Valid Sub Categories" );
                inForm.setInteractionSubCategories( new ArrayList<CrmRcaReasonPojo>() );
            }
            if ( StringUtils.isValidObj( inForm.getRemarksPojo() )
                    && StringUtils.isValidObj( inForm.getRemarksPojo().getRemarks() ) )
            {
                LOGGER.info( "Inside Valid Remark" );
                inForm.getRemarksPojo().setRemarks( "" );
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_VIEW_VALIDITY_EXTENSION_PAGE ) )
        {
            inForm.setDays( "0" );
            inForm.setGracePeriodReason( "0" );
            inForm.setSrTicketNo( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_GET_ACCESSORIES_ADDACC )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_VIEW_CUSTOMER_CATEGORY )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_VIEW_CUSTOMER_OWNERSHIP ) )
        {
            inForm.setSrTicketNo( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_SHIFTING_INITIATION_PAGE ) )
        {
            inForm.setRemarksPojo( new RemarksPojo() );
            inForm.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            inForm.setOldshiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            inForm.setNonEditableAtSC( false );
            inForm.setCityList( new ArrayList<CityPojo>() );
        }
    }

    public static void checkValidDetails( ActionMessages errors, QrcForm qrcForm )
    {
        if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) && StringUtils
                .equals( qrcForm.getCustDetailsPojo().getServiceType(), CRMDisplayListConstants.PRE_PAID.getCode() ) )
        {
            errors.add( IAppConstants.APP_ERROR,
                        new ActionMessage( IPropertiesConstant.ERROR_INVALID_WHITELIST_SERVICE_TYPE,
                                           CRMDisplayListConstants.PRE_PAID.getValue() ) );
        }
        else if ( StringUtils.isEmpty( qrcForm.getRemarksPojo().getRemarks() ) )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BARRING_REMARKS ) );
        }
        else if ( qrcForm.getCrmQrcWhitelistPojo().getReason() <= 0 )
        {
            errors.add( IAppConstants.APP_ERROR,
                        new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_REASON ) );
        }
        else if ( StringUtils.isValidObj( qrcForm.getEndDate() ) )
        {
            try
            {
                Date date1 = IDateConstants.SDF_DD_MMM_YYYY.parse( DateUtils.getCurrentDateStr() );
                Date date2 = IDateConstants.SDF_DD_MMM_YYYY.parse( qrcForm.getEndDate() );
                if ( date2.compareTo( date1 ) < 1 )
                {
                    LOGGER.info( date2.compareTo( date1 ) );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_INVALID_BARRING_END_DATE ) );
                }
            }
            catch ( ParseException ex )
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_INVALID_BARRING_END_DATE ) );
                LOGGER.error( "getting error  in checkValidDetails ", ex );
            }
        }
    }

    public static void checkValidBUBFields( ActionMessages errors, QrcForm qrcForm )
    {
        LOGGER.info( "validating barring/unbarring fields" );
        Map<String, Object[]> inResultMap = null;
        if ( qrcForm.getExceptionReason() == 0 )
        {
            errors.add( IAppConstants.APP_ERROR,
                        new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_REASON ) );
        }
        inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
        if ( ( StringUtils.isValidObj( inResultMap ) ) && ( !inResultMap.isEmpty() ) )
        {
            ValidationUtil.prepareErrorMessage( errors, inResultMap );
        }
    }

    public static void bulkWhitelistFormValidation( ActionMessages actionErrors, QrcForm qrcForm, String fileName )
    {
        boolean valid = true;
        LOGGER.info( "File : " + fileName + " Size : " + qrcForm.getWhitelistExcelFile().getFileSize() );
        // validation before processing
        if ( StringUtils.isEmpty( qrcForm.getCrmQrcWhitelistPojo().getWhitelistType() ) )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_TYPE ) );
            //valid = false;
        }
        if ( qrcForm.getWhitelistExcelFile().getFileSize() == 0 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
            //  valid = false;
        }
        else if ( qrcForm.getWhitelistExcelFile().getFileSize() > 20480 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
            // valid = false;
        }
        if ( qrcForm.getCrmQrcWhitelistPojo().getReason() == 0 )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_REASON ) );
            //valid = false;
        }
        try
        {
            Date date1 = IDateConstants.SDF_DD_MMM_YYYY.parse( DateUtils.getCurrentDateStr() );
            Date date2 = IDateConstants.SDF_DD_MMM_YYYY.parse( qrcForm.getEndDate() );
            if ( date2.compareTo( date1 ) < 1 )
            {
                LOGGER.info( date2.compareTo( date1 ) );
                actionErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_INVALID_BARRING_END_DATE ) );
                //  valid = false;
            }
        }
        catch ( ParseException e )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_INVALID_BARRING_END_DATE ) );
            //valid = false;
            LOGGER.error( "getting error  in bulkWhitelistFormValidation " + e );
        }
        Map<String, Object[]> inResultMap = null;
        inResultMap = validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
        /* if ( StringUtils.isEmpty( qrcForm.getRemarksPojo().getRemarks() ) )
         {
             actionErrors.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_REASON ) );
             valid = false;
         }*/
        //return valid;
    }

    public static void validateDisconnection( ActionMessages inErrors, QrcForm inQrcForm )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_DISCONNECTION, false );
        //  resultMap=validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }

    public static void validateWaiver( ActionMessages inErrors, QrcForm inQrcForm )
    {
        LOGGER.info( "waiverHead::" + inQrcForm.getCustWaiverPojo().getWaiverHead() );
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inQrcForm.getCustWaiverPojo(), ICRMValidationCriteriaUtil.FORM_QRC_WAIVER, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
        }
        /* resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                      ICRMValidationCriteriaUtil.FORM_QRC_REMARKS, false );*/
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }

    public static ActionMessages saveTicketActionValidation( ActionMessages inActionErrors,
                                                             QrcForm inQrcForm,
                                                             String inAction )
    {
        LOGGER.info( "In saveTicketActionValidation ::: QrcFormHelper" );
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.equals( inAction, "forward" ) )
        {
            LOGGER.info( "In saveTicketActionValidation ::: QrcFormHelper:::::forward" );
            String futureStage = inQrcForm.getFutureStage();
            LOGGER.info( "In saveTicketActionValidation ::: QrcFormHelper:::::forward and futureStage is ::: "
                    + futureStage );
            resultMap = ValidationPojoUtil
                    .validateSingleFormProperty( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_FUTURESTAGE, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
        }
        if ( StringUtils.equals( inAction, "resolve" ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inQrcForm.getSrTicketPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_QRC_RESOLVE_ACTION, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
        }
        ValidationUtil.prepareErrorMessage( inActionErrors, resultMap );
        return inActionErrors;
    }

    public static ActionMessages remarksValidation( ActionMessages inActionErrors, QrcForm inQrcForm )
    {
        Map<String, Object[]> resultMap = null;
        ValidationUtil.prepareErrorMessage( inActionErrors, resultMap );
        resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                     ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        ValidationUtil.prepareErrorMessage( inActionErrors, resultMap );
        return inActionErrors;
    }

    public static void resetWaiverFields( QrcForm inQrcForm )
    {
        inQrcForm.setCustWaiverPojo( new CrmCustWaiverPojo() );
        inQrcForm.setRemarksPojo( new RemarksPojo() );
        inQrcForm.setSrTicketNo( null );
    }

    public static void validateCreateCustInteraction( ActionMessages inErrors,
                                                      CrmCustInteractionsPojo inCustInteractionsPojo )
    {
        // boolean isValid = true;
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil.validateForm( inCustInteractionsPojo,
                                                     ICRMValidationCriteriaUtil.FORM_QRC_CUST_INTERACTION_CREATE_CHECK,
                                                     false );
        if ( ( null != resultMap ) && ( !resultMap.isEmpty() ) )
        {
            //    isValid = false;
        }
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        // return isValid;
    }

    /*public static ActionMessages validateQRCForm( String inMethod, ActionErrors actionErrors, QrcForm inQrcForm )
    {
        if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_MAC ) )
        {
            if(StringUtils.equals( inQrcForm.getCustDetailsPojo().getProduct(),CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )){
    
            }else{
    
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_OPTION82 ) )
        {
    
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_QRC_EDIT_CPE ) )
        {
    
        }
        return null;
    }*/
    public static void validateMac( ActionMessages inErrors, QrcForm inQrcForm )
    {
        LOGGER.info( "validateMac method called" );
        Map<String, Object[]> inResultMap = null;
        inResultMap = validateCustomer( inResultMap, inQrcForm );
        inResultMap = validateRemarks( inResultMap, inQrcForm.getRemarksPojo() );
        if ( ( StringUtils.isValidObj( inResultMap ) ) && ( !inResultMap.isEmpty() ) )
        {
            LOGGER.info( "validation errors map size  :::: " + inResultMap.size() );
            ValidationUtil.prepareErrorMessage( inErrors, inResultMap );
            LOGGER.info( inErrors.size() );
            //  return false;
        }
        else if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getProduct(),
                                      CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
        {
            if ( StringUtils.isBlank( inQrcForm.getNewPrimaryMacId() )
                    && StringUtils.isBlank( inQrcForm.getNewSecondaryMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_ATLEAST_ONE_PRI_SEC ) );
            }
            else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_VALIDATE_HEXADECIMAL_FOR_QRC.getPattern(),
                                                      inQrcForm.getNewPrimaryMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_PATTERN_HAXADECIMAL_QRC,
                                                 IAppConstants.QRC_PRIMARY_MAC ) );
            }
            else if ( StringUtils.equals( inQrcForm.getNewPrimaryMacId(),
                                          inQrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_DEVICE_OLD_NEW_MAC,
                                                                          IAppConstants.QRC_PRIMARY_MAC ) );
            }
            else if ( !StringUtils.isBlank( inQrcForm.getNewSecondaryMacId() ) )
            {
                if ( StringUtils.equals( inQrcForm.getNewSecondaryMacId(),
                                         inQrcForm.getNetworkConfigurationsPojo().getCurrentSlaveMacId() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_DEVICE_OLD_NEW_MAC,
                                                     IAppConstants.QRC_SECONDARY_MAC ) );
                }
            }
        }
        else if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getProduct(),
                                      CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            if ( StringUtils.isBlank( inQrcForm.getNewPrimaryMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_MAC_EMPTY ) );
            }
            else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_VALIDATE_HEXADECIMAL_FOR_QRC.getPattern(),
                                                      inQrcForm.getNewPrimaryMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_PATTERN_HAXADECIMAL ) );
            }
            else if ( StringUtils.equals( inQrcForm.getNewPrimaryMacId(),
                                          inQrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_OLD_NEW_MAC, "" ) );
            }
        }
    }

    public static void validateOption82( ActionMessages inErrors, QrcForm inQrcForm )
    {
        Map<String, Object[]> inResultMap = null;
        inResultMap = validateCustomer( inResultMap, inQrcForm );
        inResultMap = validateRemarks( inResultMap, inQrcForm.getRemarksPojo() );
        if ( ( StringUtils.isValidObj( inResultMap ) ) && ( !inResultMap.isEmpty() ) )
        {
            ValidationUtil.prepareErrorMessage( inErrors, inResultMap );
            //  return false;
        }
        /*if ( StringUtils.isBlank( inQrcForm.getRemarksPojo().getRemarks() ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_REMARKS_REQUIRED ) );
            return false;
        }*/
        if ( StringUtils.isBlank( inQrcForm.getNewOption82() ) )
        {
            if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_EMPTY_OPTION82NASPORT,
                                                 IAppConstants.OPTION82 ) );
            }
            else
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_EMPTY_OPTION82NASPORT,
                                                 IAppConstants.NASPORTID ) );
            }
            //return false;
        }
        else if ( StringUtils.equals( inQrcForm.getNewOption82(),
                                      inQrcForm.getNetworkConfigurationsPojo().getOption82() ) )
        {
            if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_OLD_NEW_OPTION82,
                                                 IAppConstants.OPTION82 ) );
            }
            else
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_DEVICE_OLD_NEW_OPTION82,
                                                 IAppConstants.NASPORTID ) );
            }
            // return false;
        }
        // return true;
    }

    public static boolean validateCPE( ActionMessages inErrors, QrcForm inQrcForm )
    {
        if ( StringUtils.isBlank( inQrcForm.getRemarksPojo().getRemarks() ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_REMARKS_REQUIRED ) );
            return false;
        }
        return true;
    }

    public static void checkValidStaticIPWiring( ActionMessages errors, QrcForm inQrcForm, String type )
    {
        if ( StringUtils.equals( type, CRMParameter.STATIC_IP.getParameter() ) )
        {
            Map<String, Object[]> resultMap = null;
            if ( CommonValidator.isValidCollection( inQrcForm.getCrmCustAssetDetailsPojos() ) )
            {
                for ( CrmCustAssetDetailsPojo assetDetailsPojo : inQrcForm.getCrmCustAssetDetailsPojos() )
                {
                    LOGGER.info( "validating static ip" );
                    resultMap = ValidationPojoUtil.validateForm( assetDetailsPojo,
                                                                 ICRMValidationCriteriaUtil.FORM_QRC_ADD_STATICIP_POJO,
                                                                 false );
                    if ( StringUtils.isValidObj( resultMap ) )
                    {
                        LOGGER.info( assetDetailsPojo );
                        //ValidationUtil.prepareErrorMessage( errors, resultMap );
                        break;
                    }
                }
            }
            resultMap = validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
        }
        else
        {
            LOGGER.info( "validating Static ip/Wiring charges" );
            Map<String, Object[]> resultMap = ValidationPojoUtil.validateForm( inQrcForm.getCrmCustAssetDetailsPojo(),
                                                                               ICRMValidationCriteriaUtil.FORM_QRC_ADD_CHARGE,
                                                                               false );
            resultMap = validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
        }
    }

    public static boolean validateEditConditions( ActionMessages inErrors, QrcForm inQrcForm )
    {
        boolean isValid = true;
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.isNotEmpty( inQrcForm.getQrcActions() ) )
        {
            CRMActionConstants actionConstants = CRMActionConstants.getCRMActionConstants( inQrcForm.getQrcActions() );
            resultMap = ValidationPojoUtil.validateForm( inQrcForm.getTicketHistory(),
                                                         ICRMValidationCriteriaUtil.FORM_TICKET_HISTORY_CRITERIA_COMMON,
                                                         false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.size() == 0 )
            {
                switch ( actionConstants )
                {
                    case FOLLOW_UP:
                        inQrcForm.getSrTicketPojo().setFollowupOn( DateUtils
                                .changeDateFormat( inQrcForm.getSrTicketPojo().getDisplayFollowupOn() ) );
                        resultMap = ValidationPojoUtil.validateForm( inQrcForm.getSrTicketPojo(),
                                                                     ICRMValidationCriteriaUtil.FORM_QRC_TICKET_FOLLOWON_CHECKS,
                                                                     false );
                        if ( !validFollowUpOn( inQrcForm ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_INVALID_FOLLOWUPON ) );
                            isValid = false;
                        }
                        break;
                    case FORWARD:
                        resultMap = ValidationPojoUtil
                                .validateForm( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_FUTURESTAGE, false );
                        break;
                    case RESOLVE:
                        resultMap = ValidationPojoUtil.validateForm( inQrcForm.getSrTicketPojo(),
                                                                     ICRMValidationCriteriaUtil.FORM_QRC_RESOLVE_ACTION,
                                                                     false );
                        break;
                    case SAVE_REMARKS:
                        break;
                    case SAVE_FLAG:
                        break;
                    default:
                        inErrors.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.ERROR_EDIT_TICKET_SELECT_ACTION ) );
                        break;
                }
            }
        }
        if ( StringUtils.isValidObj( resultMap ) )
        {
            isValid = false;
            LOGGER.info( resultMap.size() );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        return isValid;
    }

    private static boolean validFollowUpOn( QrcForm inQrcForm )
    {
        try
        {
            Date start = Calendar.getInstance().getTime();
            Date end = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( inQrcForm.getFollowupOn() );
            return end.compareTo( start ) == 1;
        }
        catch ( ParseException ex )
        {
            LOGGER.info( "Unable to validate FollowUpOn time", ex );
        }
        return false;
    }

    public static Map<String, Object[]> validateRemarks( Map<String, Object[]> inResultMap, RemarksPojo inRemarksPojo )
    {
        if ( StringUtils.isValidObj( inResultMap ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inRemarksPojo, ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            if ( StringUtils.isValidObj( resultMap ) )
            {
                inResultMap.putAll( resultMap );
            }
        }
        else
        {
            inResultMap = ValidationPojoUtil.validateForm( inRemarksPojo,
                                                           ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        }
        return inResultMap;
    }

    public static boolean planBoosterValidations( ActionMessages inErrors,
                                                  QrcForm inQrcForm,
                                                  String inUserID,
                                                  XMLGregorianCalendar inExpiryDate )
    {
        boolean isValid = false;
        Map<String, Object[]> resultMap = null;
        List<CrmCustAssetDetailsPojo> crmCustAssetDetailsPojos = null;
        CrmCustAssetDetailsPojo custAssetDetailsPojo = null;
        double boosterCharges = 0L;
        if ( StringUtils.equals( inQrcForm.getPlanCategory(),
                                 CRMCustomerActivityActions.BOOSTER_PLAN.getActionDesc() ) )
        {
            if ( StringUtils.equals( inQrcForm.getActivationTime(), "selected" ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_INVALID_ACTIVATION_TIME ) );
            }
            else
            {
                if ( StringUtils.equals( inQrcForm.getPlanUsageType(), "BOOSTER_USAGE" ) )
                {
                    if ( CommonValidator.isValidCollection( inQrcForm.getPlanMasterList() ) )
                    {
                        crmCustAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
                        for ( CrmPlanMasterPojo planMasterPojo : inQrcForm.getPlanMasterList() )
                        {
                            if ( planMasterPojo.isEditable() && planMasterPojo.getQuantity() > 0 )
                            {
                                custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                                custAssetDetailsPojo
                                        .setCustomerRecordId( inQrcForm.getCustDetailsPojo().getRecordId() );
                                custAssetDetailsPojo.setCategoryValue( planMasterPojo.getPlanCode() );
                                custAssetDetailsPojo.setCategoryCount( planMasterPojo.getQuantity() );
                                custAssetDetailsPojo.setCategoryAmount( planMasterPojo
                                        .getRentInclTax() == null ? 0.0 : planMasterPojo.getRentInclTax() );
                                if ( StringUtils.equals( inQrcForm.getPlanType(), "BOOSTER_PAID" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.BOOSTER_USAGE_PAID.getParameter() );
                                }
                                else if ( StringUtils.equals( inQrcForm.getPlanType(), "BOOSTER_FOC" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.BOOSTER_USAGE_FOC.getParameter() );
                                }
                                custAssetDetailsPojo.setStatus( planMasterPojo.getStatus() );
                                custAssetDetailsPojo.setCreatedBy( inUserID );
                                custAssetDetailsPojo.setLastModifiedTime( inExpiryDate );
                                boosterCharges += custAssetDetailsPojo.getCategoryCount()
                                        * custAssetDetailsPojo.getCategoryAmount().doubleValue();
                                crmCustAssetDetailsPojos.add( custAssetDetailsPojo );
                            }
                        }
                    }
                }
                else if ( StringUtils.equals( inQrcForm.getPlanUsageType(), "BOOSTER_SPEED" ) )
                {
                    if ( CommonValidator.isValidCollection( inQrcForm.getPlanMasterList() ) )
                    {
                        crmCustAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
                        if ( StringUtils.isValidObj( inQrcForm.getCrmPlanMasterPojo() )
                                && StringUtils.isNotBlank( inQrcForm.getPlanCode() ) )
                        {
                            custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                            custAssetDetailsPojo.setCustomerRecordId( Long
                                    .valueOf( inQrcForm.getCustDetailsPojo().getRecordId() ) );
                            custAssetDetailsPojo.setCategoryValue( inQrcForm.getPlanCode() );
                            custAssetDetailsPojo.setCategoryCount( (byte) 1 );
                            if ( StringUtils.equals( inQrcForm.getPlanType(), "BOOSTER_PAID" ) )
                            {
                                custAssetDetailsPojo.setCategoryName( CRMParameter.BOOSTER_SPEED_PAID.getParameter() );
                            }
                            else if ( StringUtils.equals( inQrcForm.getPlanType(), "BOOSTER_FOC" ) )
                            {
                                custAssetDetailsPojo.setCategoryName( CRMParameter.BOOSTER_SPEED_FOC.getParameter() );
                            }
                            for ( CrmPlanMasterPojo planMasterPojo : inQrcForm.getPlanMasterList() )
                            {
                                if ( StringUtils.equals( planMasterPojo.getPlanCode(), inQrcForm.getPlanCode() ) )
                                {
                                    custAssetDetailsPojo.setCategoryAmount( planMasterPojo
                                            .getRentInclTax() == null ? 0 : planMasterPojo.getRentInclTax() );
                                    break;
                                }
                            }
                            custAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            custAssetDetailsPojo.setCreatedBy( inUserID );
                            custAssetDetailsPojo.setLastModifiedTime( inExpiryDate );
                            boosterCharges += custAssetDetailsPojo.getCategoryCount()
                                    * custAssetDetailsPojo.getCategoryAmount().doubleValue();
                            crmCustAssetDetailsPojos.add( custAssetDetailsPojo );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( crmCustAssetDetailsPojos ) )
                {
                    inQrcForm.setCrmCustAssetDetailsPojos( crmCustAssetDetailsPojos );
                    isValid = true;
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_SELECTING_BOOSTER_PLAN ) );
                }
                if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                        && StringUtils.equals( inQrcForm.getCustDetailsPojo().getServiceType(),
                                               CRMDisplayListConstants.PRE_PAID.getCode() )
                        && inQrcForm.getCustAdditionalDetails().getBalance() < ( boosterCharges - 1 ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM427.getStatusCode() ) );
                }
            }
        }
        validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        return isValid;
    }

    public static void validateServiceRequestPOJO( ActionMessages inErrors, QrcForm inQrcForm )
    {
        CrmSrTicketsPojo ticketsPojo = inQrcForm.getSrTicketPojo();
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( ticketsPojo, ICRMValidationCriteriaUtil.FORM_QRCTICKET_POST_CRITERIA_SR, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateForm( inQrcForm.getTicketHistory(),
                                                         ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        }
        if ( ( StringUtils.isValidObj( resultMap ) ) && ( !resultMap.isEmpty() ) )
        {
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }

    public static void validateCustomerID( ActionMessages inErrors, QrcForm inQrcForm )
    {
        LOGGER.info( "validateCustomerID" );
        Map<String, Object[]> inResultMap = null;
        inResultMap = QrcFormHelper.validateCustomer( inResultMap, inQrcForm );
        ValidationUtil.prepareErrorMessage( inErrors, inResultMap );
    }

    public static Map<String, Object[]> validateCustomer( Map<String, Object[]> inResultMap, QrcForm inQRCForm )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inQRCForm, ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
        if ( StringUtils.isValidObj( inResultMap ) )
        {
            inResultMap.putAll( resultMap );
        }
        else
        {
            inResultMap = resultMap;
        }
        return inResultMap;
    }

    public static boolean customerVASValidations( ActionMessages inErrors, QrcForm inQrcForm, String inUserID )
    {
        boolean isValid = false;
        Map<String, Object[]> resultMap = null;
        List<CrmCustAssetDetailsPojo> crmCustAssetDetailsPojos = null;
        CrmCustAssetDetailsPojo custAssetDetailsPojo = null;
        double vasCharges = 0L;
        if ( StringUtils.equals( inQrcForm.getPlanCategory(), "VAS_MANAGEMENT" ) )
        {
            if ( StringUtils.equals( inQrcForm.getActivationTime(), "selected" ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_INVALID_ACTIVATION_TIME ) );
            }
            else
            {
                if ( StringUtils.equals( inQrcForm.getPlanUsageType(),
                                         CRMCustomerActivityActions.SMART_BROADBAND_ACTIVATION.getActionDesc() ) )
                {
                    if ( CommonValidator.isValidCollection( inQrcForm.getVasToActivatePojos() ) )
                    {
                        crmCustAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
                        for ( CrmPlanMasterPojo planMasterPojo : inQrcForm.getVasToActivatePojos() )
                        {
                            if ( planMasterPojo.isEditable() )
                            {
                                custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                                custAssetDetailsPojo
                                        .setCustomerRecordId( inQrcForm.getCustDetailsPojo().getRecordId() );
                                custAssetDetailsPojo.setCategoryValue( planMasterPojo.getPlanCode() );
                                custAssetDetailsPojo.setCategoryCount( (byte) 1 );
                                custAssetDetailsPojo.setCategoryAmount( planMasterPojo.getRentInclTax() );
                                if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_PAID" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_ACTIVATION_PAID.getParameter() );
                                }
                                else if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_FOC" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_ACTIVATION_FOC.getParameter() );
                                }
                                custAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                custAssetDetailsPojo.setCreatedBy( inUserID );
                                vasCharges += custAssetDetailsPojo.getCategoryCount()
                                        * custAssetDetailsPojo.getCategoryAmount().doubleValue();
                                crmCustAssetDetailsPojos.add( custAssetDetailsPojo );
                            }
                        }
                    }
                }
                else if ( StringUtils
                        .equals( inQrcForm.getPlanUsageType(),
                                 CRMCustomerActivityActions.SMART_BROADBAND_DEACTIVATION.getActionDesc() ) )
                {
                    if ( CommonValidator.isValidCollection( inQrcForm.getVasToDeactivatePojos() ) )
                    {
                        crmCustAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
                        for ( CrmPlanMasterPojo planMasterPojo : inQrcForm.getVasToDeactivatePojos() )
                        {
                            if ( planMasterPojo.isEditable() )
                            {
                                custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                                custAssetDetailsPojo
                                        .setCustomerRecordId( inQrcForm.getCustDetailsPojo().getRecordId() );
                                custAssetDetailsPojo.setCategoryValue( planMasterPojo.getPlanCode() );
                                custAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_PAID" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_ACTIVATION_PAID.getParameter() );
                                }
                                else if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_FOC" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_ACTIVATION_FOC.getParameter() );
                                }
                                if ( CommonValidator.isValidCollection( inQrcForm.getCrmCustAssetDetailsPojos() ) )
                                {
                                    int pojoIndex = -1;
                                    pojoIndex = inQrcForm.getCrmCustAssetDetailsPojos().indexOf( custAssetDetailsPojo );
                                    if ( pojoIndex > -1 )
                                    {
                                        custAssetDetailsPojo = inQrcForm.getCrmCustAssetDetailsPojos().get( pojoIndex );
                                    }
                                }
                                custAssetDetailsPojo.setCategoryCount( (byte) 1 );
                                if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_PAID" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_DEACTIVATION_PAID.getParameter() );
                                }
                                else if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_FOC" ) )
                                {
                                    custAssetDetailsPojo
                                            .setCategoryName( CRMParameter.VAS_DEACTIVATION_FOC.getParameter() );
                                }
                                custAssetDetailsPojo.setLastModifiedBy( inUserID );
                                custAssetDetailsPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                                crmCustAssetDetailsPojos.add( custAssetDetailsPojo );
                            }
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( crmCustAssetDetailsPojos ) )
                {
                    inQrcForm.setCrmCustAssetDetailsPojos( crmCustAssetDetailsPojos );
                    isValid = true;
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_SELECTING_VAS_PLAN ) );
                }
                if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                        && StringUtils.equals( inQrcForm.getCustDetailsPojo().getServiceType(),
                                               CRMDisplayListConstants.PRE_PAID.getCode() )
                        && inQrcForm.getCustAdditionalDetails().getBalance() < ( vasCharges - 1 ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM427.getStatusCode() ) );
                }
            }
        }
        validateRemarks( resultMap, inQrcForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        return isValid;
    }

    public static void validateUsagePeriod( QrcForm inQrcForm, ActionMessages inErrors )
    {
        LOGGER.info( "Inside QrcFormHelper, validateUsagePeriod" );
        Date fromDate = null;
        Date toDate = null;
        Date currDate = null;
        if ( StringUtils.isEmpty( inQrcForm.getUsageFormDate() ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.EMPTY_FROM_USAGE_DATE ) );
        }
        else if ( StringUtils.isEmpty( inQrcForm.getUsageToDate() ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.EMPTY_TO_USAGE_DATE ) );
        }
        else
        {
            fromDate = DateUtils.getDate( inQrcForm.getUsageFormDate(), "dd-MMM-yyyy" ).getTime();
            toDate = DateUtils.getDate( inQrcForm.getUsageToDate(), "dd-MMM-yyyy" ).getTime();
            currDate = DateUtils.getCurrentDate();
            if ( DateUtils.daysDiff( fromDate, currDate ) < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.FROM_DATE_AFTER_CURRENT_DATE ) );
            }
            else if ( DateUtils.daysDiff( toDate, currDate ) < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.TO_DATE_AFTER_CURRENT_DATE ) );
            }
            else if ( DateUtils.daysDiff( fromDate, toDate ) < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.FROM_DATE_AFTER_TO_DATE ) );
            }
            else if ( DateUtils.daysDiff( fromDate, toDate ) > 30 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.SELECTED_PERIOD_MORE_THAN_THIRTY_DAYS ) );
            }
        }
    }

    public static boolean planMigrationValidation( ActionMessages actionErrors, QrcForm qrcForm )
    {
        Map<String, Object[]> resultMap = null;
        boolean isValid = true;
        if ( StringUtils.isEmpty( qrcForm.getSelectedPlanCode() ) )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_SELECTING_BASE_PLAN ) );
            isValid = false;
        }
        else if ( StringUtils.equals( qrcForm.getSelectedPlanCode(), qrcForm.getPlanDetailsPojo().getBasePlanCode() )
                && StringUtils.equals( qrcForm.getAddonPlanCode(), qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM429.getStatusCode() ) );
            isValid = false;
        }
        /*else if ( StringUtils.equals( qrcForm.getSelectedPlanCode(), qrcForm.getPlanDetailsPojo().getBasePlanCode() )
                && StringUtils.isEmpty( qrcForm.getAddonPlanCode() )
                && StringUtils.isEmpty( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_SELECTING_ADDON_PLAN ) );
            isValid = false;
        }*/
        validateRemarks( resultMap, qrcForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( actionErrors, resultMap );
        return isValid;
    }

    public static boolean planReactivationOrRenewValidation( ActionMessages actionErrors, QrcForm qrcForm )
    {
        Map<String, Object[]> resultMap = null;
        boolean isValid = true;
        if ( StringUtils.isEmpty( qrcForm.getSelectedPlanCode() ) )
        {
            actionErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_SELECTING_BASE_PLAN ) );
            isValid = false;
        }
        validateRemarks( resultMap, qrcForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( actionErrors, resultMap );
        return isValid;
    }

    public static void resetFormProperty( QrcForm qrcForm )
    {
        qrcForm.setPlanUsageType( null );
        qrcForm.setPlanType( null );
        qrcForm.setPlanCategory( null );
        qrcForm.setActivationTime( null );
        qrcForm.setSrTicketsPojos( null );
        qrcForm.setPlanMasterList( null );
        qrcForm.setRemarksPojo( new RemarksPojo() );
    }

    public static void validateValidityExtension( ActionMessages actionErrors, QrcForm qrcForm )
    {
        Map<String, Object[]> inResultMap = null;
        inResultMap = validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
        if ( inResultMap == null )
        {
            inResultMap = ValidationPojoUtil
                    .validateForm( qrcForm, ICRMValidationCriteriaUtil.FORM_QRC_VALIDITY_EXTENSION, false );
        }
        ValidationUtil.prepareErrorMessage( actionErrors, inResultMap );
    }
}
