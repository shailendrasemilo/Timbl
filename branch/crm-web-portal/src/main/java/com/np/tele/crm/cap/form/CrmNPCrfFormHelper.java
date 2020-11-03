package com.np.tele.crm.cap.form;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class CrmNPCrfFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( CrmNPCrfFormHelper.class );

    public static ActionMessages validateNetConfDetail( ActionMessages inErrors, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "Inside network partner configuration validation method." );
        Map<String, Object[]> resultMap = null;
        //        boolean flag = false;
        //        DwrManagerImpl dwrManagerImpl = new DwrManagerImpl();
        //        List<CrmParameterPojo> crmParameterList = new ArrayList<CrmParameterPojo>();
        if ( !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                  CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            LOGGER.info( "validation of network configuration for EOC " );
            resultMap = ValidationPojoUtil
                    .validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                   ICRMValidationCriteriaUtil.FORM_CRF_OPTION_82_FOR_MASTER_NAME_VALUES, false );
        }
        //Commented for future in option82 parameters are need to save individually.
        //        {
        //            int option82emptyCount = 0;
        //            StringBuffer mandatoryOption82 = new StringBuffer();
        //            crmParameterList = dwrManagerImpl.getCrmParameterListOption82( String.valueOf( inCrmCapForm
        //                    .getCustomerDetailsPojo().getNpId() ), CRMDisplayListConstants.BROADBAND.getCode(), inCrmCapForm
        //                    .getOltType() );
        //            for ( CrmParameterPojo crmParameterPojo : crmParameterList )
        //            {
        //                mandatoryOption82.append( crmParameterPojo.getParameterName() );
        //                if ( StringUtils.equals( crmParameterPojo.getParameterName(), "OLTNODE ID" ) )
        //                {
        //                    if ( StringUtils.isEmpty( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId() )
        //                            || StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId(), "0" ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    else
        //                    {
        //                        if ( StringUtils.isNumeric( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId() ) )
        //                        {
        //                            inErrors.add( IAppConstants.APP_ERROR,
        //                                          new ActionMessage( IPropertiesConstant.ERROR_NUMERIC_OLT_NODE_ID ) );
        //                        }
        //                        flag = true;
        //                    }
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "OLT SLOT" ) )
        //                {
        //                    if ( StringUtils
        //                            .isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() ) )
        //                            || ( inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    else
        //                    {
        //                        if ( inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() < 1
        //                                || inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() > 24 )
        //                        {
        //                            inErrors.add( IAppConstants.APP_ERROR,
        //                                          new ActionMessage( IPropertiesConstant.ERROR_OLT_SLOT ) );
        //                            flag = true;
        //                        }
        //                    }
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "OLT PORT" ) )
        //                {
        //                    if ( StringUtils
        //                            .isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations().getOltPort() ) )
        //                            || ( inCrmCapForm.getCrmNetworkConfigurations().getOltPort() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    else
        //                    {
        //                        if ( inCrmCapForm.getCrmNetworkConfigurations().getOltPort() < 1
        //                                || inCrmCapForm.getCrmNetworkConfigurations().getOltPort() > 16 )
        //                        {
        //                            inErrors.add( IAppConstants.APP_ERROR,
        //                                          new ActionMessage( IPropertiesConstant.ERROR_OLT_PORT ) );
        //                        }
        //                        flag = true;
        //                    }
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "OLT SUB PORT" ) )
        //                {
        //                    if ( StringUtils.isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations()
        //                            .getOltSubPort() ) ) || ( inCrmCapForm.getCrmNetworkConfigurations().getOltSubPort() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    else
        //                    {
        //                        if ( inCrmCapForm.getCrmNetworkConfigurations().getOltSubPort() < 1
        //                                || inCrmCapForm.getCrmNetworkConfigurations().getOltSubPort() > 64 )
        //                        {
        //                            inErrors.add( IAppConstants.APP_ERROR,
        //                                          new ActionMessage( IPropertiesConstant.ERROR_OLT_SUB_PORT ) );
        //                        }
        //                        flag = true;
        //                    }
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "VLAN ID" ) )
        //                {
        //                    if ( StringUtils.isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() ) )
        //                            || ( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    else
        //                    {
        //                    }
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "SERVICE TYPE" ) )
        //                {
        //                    if ( StringUtils.isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() ) )
        //                            || ( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    /*else{
        //                        inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_VLAN_ID ) );
        //                        flag=true;
        //                    }*/
        //                }
        //                else if ( StringUtils.equals( crmParameterPojo.getParameterName(), "ONT/ONU PORT" ) )
        //                {
        //                    if ( StringUtils.isEmpty( String.valueOf( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() ) )
        //                            || ( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() == 0 ) )
        //                    {
        //                        option82emptyCount++;
        //                        continue;
        //                    }
        //                    /*else  if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getOntOnuPort(), "0" ) )
        //                     {
        //                     if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel(), "fttb" ) )
        //                     {
        //                         inErrors.add( IAppConstants.APP_ERROR,new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ONU_PORT ) );
        //                     }
        //                     else if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel(), "ftth" ) )
        //                     {
        //                         inErrors.add( IAppConstants.APP_ERROR,new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ONT_PORT ) );
        //                     }
        //                     flag=true;
        //                     }*/
        //                    if ( flag )
        //                    {
        //                        break;
        //                    }
        //                }
        //            }
        //            /* if ( ( !flag ) && option82emptyCount == crmParameterList.size() )
        //             {
        //                 inErrors.add( IAppConstants.APP_ERROR,
        //                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_OPTION82_PARAMETERS, mandatoryOption82 ) );
        //             }*/
        //            if ( StringUtils.isEmpty( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel() ) )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR,
        //                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_SERVICE_MODEL ) );
        //            }
        //            else if ( StringUtils.isNotEmpty( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel() ) )
        //            {
        //                if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getOntOnuPort(), "0" ) )
        //                {
        //                    if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel(), "fttb" ) )
        //                    {
        //                        inErrors.add( IAppConstants.APP_ERROR,
        //                                      new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ONU_PORT ) );
        //                    }
        //                    else if ( StringUtils.equals( inCrmCapForm.getCrmNetworkConfigurations().getServiceModel(), "ftth" ) )
        //                    {
        //                        inErrors.add( IAppConstants.APP_ERROR,
        //                                      new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ONT_PORT ) );
        //                    }
        //                }
        //            }
        //            /*if ( inCrmCapForm.getCrmNetworkConfigurations().getVlanId() == 0 )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_VLAN_ID ) );
        //            }
        //            if ( StringUtils.isEmpty( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId() ) )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_OLT_NODE_ID ) );
        //            }
        //            else if ( StringUtils.isNotEmpty( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId() )
        //                    && StringUtils.isNumeric( inCrmCapForm.getCrmNetworkConfigurations().getOltNoteId() ) )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR,
        //                              new ActionMessage( IPropertiesConstant.ERROR_NUMERIC_OLT_NODE_ID ) );
        //            }
        //             if ( inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() < 1
        //                    || inCrmCapForm.getCrmNetworkConfigurations().getOltSlot() > 24 )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_OLT_SLOT ) );
        //            }
        //            else if ( inCrmCapForm.getCrmNetworkConfigurations().getOltPort() < 1
        //                    || inCrmCapForm.getCrmNetworkConfigurations().getOltPort() > 16 )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_OLT_PORT ) );
        //            }
        //            else if ( inCrmCapForm.getCrmNetworkConfigurations().getOltSubPort() < 1
        //                    || inCrmCapForm.getCrmNetworkConfigurations().getOltSubPort() > 64 )
        //            {
        //                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_OLT_SUB_PORT ) );
        //            }
        //            */
        //        }
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        return inErrors;
    }

    public static ActionMessages validateNetworkDetails( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        Map<String, Object[]> resultMap = null;
        /* resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                      ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_ACTION, false );*/
        if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.REJECT_BY_NP ) )
        {
            /* if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
             {
                 resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                              ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE,
                                                              false );
             }*/
        }
        else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.SUBMIT_REFUSAL ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = rejectDetailsValidation( inActionError, inCrmCapForm );
            }
        }
        else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.CRF_CANCELLATION ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = rejectDetailsValidation( inActionError, inCrmCapForm );
            }
        }
        else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.APPROVE ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = aproveNetworkDetailsValidation( inActionError, inCrmCapForm );
            }
        }
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        return inActionError;
    }

    private static Map<String, Object[]> aproveNetworkDetailsValidation( ActionMessages inActionError,
                                                                         CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "aprove network details form" );
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                 CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                         ICRMValidationCriteriaUtil.FORM_CRF_FOR_NETWORK_CONFIGURATION,
                                                         false );
        }
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            /*resultMap = ValidationPojoUtil
                    .validateForm( inCrmCapForm.getRemarksPojo(),
                                   ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE, false );*/
        }
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateSingleFormProperty( inCrmCapForm,
                                                                       ICRMValidationCriteriaUtil.FORM_EMPTY_ASSIGN_TO,
                                                                       false );
        }
        return resultMap;
    }

    private static Map<String, Object[]> rejectDetailsValidation( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "reject network details form" );
        Map<String, Object[]> resultMap = null;
        /* resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                      ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE, false );*/
        return resultMap;
    }

    public static ActionMessages validateBindMapMacId( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "validate bind map mac id in SP stage" );
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                 CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                         ICRMValidationCriteriaUtil.FORM_CRF_ONT_RG_MDU_PORT, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_MAC_ADDRESS, false );
            }
        }
        else
        {
            resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                         ICRMValidationCriteriaUtil.FORM_CRF_ONT_RG_MDU_PORT, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_CPE_MAC_MAP_ID, false );
            }
            LOGGER.info( "Hexadecimal validation is: "
                    + !StringUtils.compareRegularExp( CRMRegex.PATTERN_VALIDATE_HEXADECIMAL.getPattern(), inCrmCapForm
                            .getCrmNetworkConfigurations().getCurrentCpeMacId() ) );
            /* if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
             {
                 resultMap = ValidationPojoUtil
                         .validateSingleFormProperty( inCrmCapForm,
                                                      ICRMValidationCriteriaUtil.FORM_CRF_DISPLAY_ISR_DATE, false );
             }*/
        }
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        return inActionError;
    }

    public static ActionMessages validateMapMacIdDetails( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "validate map mac id details in SP stage" );
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                     ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_ACTION, false );
        if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.REJECT_BY_SP ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                /*resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE,
                                                             false );*/
            }
        }
        else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.SUBMIT_REFUSAL_BY_SP ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = rejectDetailsValidation( inActionError, inCrmCapForm );
            }
        }
        else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.CRF_CANCELLATION ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = rejectDetailsValidation( inActionError, inCrmCapForm );
            }
        }
        else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE_BY_SP ) )
        {
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = aproveMapMacIdValidation( inActionError, inCrmCapForm );
            }
        }
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        return inActionError;
    }

    private static Map<String, Object[]> aproveMapMacIdValidation( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "aprove map mac id details form" );
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                 CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                         ICRMValidationCriteriaUtil.FORM_CRF_ONT_RG_MDU_PORT, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil
                        .validateForm( inCrmCapForm.getActivationDate(),
                                       ICRMValidationCriteriaUtil.FORM_CRF_ACTIVATION_DATE, false );
            }
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                /* resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                              ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE,
                                                              false );*/
            }
        }
        else
        {
            resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                         ICRMValidationCriteriaUtil.FORM_CRF_ONT_RG_MDU_PORT, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getCrmNetworkConfigurations(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_CPE_MAC_MAP_ID, false );
            }
            LOGGER.info( "Hexadecimal validation is: "
                    + !StringUtils.compareRegularExp( CRMRegex.PATTERN_VALIDATE_HEXADECIMAL.getPattern(), inCrmCapForm
                            .getCrmNetworkConfigurations().getCurrentCpeMacId() ) );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                /* resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                              ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE,
                                                              false );*/
            }
        }
        return resultMap;
    }

    public static ActionMessages validateCRFEntry( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "validate CRF Entry Form" );
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                     ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_ACTION, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil
                    .validateForm( inCrmCapForm.getRemarksPojo(),
                                   ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE, false );
        }
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE ) )
            {
                if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfStage(),
                                         CRMOperationStages.FULFILLMENT_TEAM.getCode() )
                        || StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfStage(),
                                               CRMOperationStages.NP_REJECT.getCode() ) )
                {
                    LOGGER.info( "Remarks Action APPROVE::" + inCrmCapForm.getRemarksPojo().getActions() );
                    resultMap = ValidationPojoUtil
                            .validateSingleFormProperty( inCrmCapForm.getCustomerDetailsPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_PARTNER_REQUIRED, false );
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.CHANGE_SELES_ADD ) )
            {
                LOGGER.info( "Sales Person Change Feasiblility Address ....");
            }
            else
            {
                LOGGER.info( "Remarks Action REJECT::" + inCrmCapForm.getRemarksPojo().getActions() );
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_REASON_FOR_REJECTION,
                                                             false );
            }
        }
        /* if ( StringUtils.isEmpty( inCrmCapForm.getRemarksPojo().getActions() ) )
         {
             LOGGER.info( "Empty remarks action:" );
             inActionError.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_EMPTY_REMARKS_ACTION ) );
         }
         else if ( StringUtils.isEmpty( inCrmCapForm.getRemarksPojo().getRemarks() ) )
         {
             inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_REMARK_REQ ) );
         }
         else if ( StringUtils.isNotEmpty( inCrmCapForm.getRemarksPojo().getRemarks() )
                 && ( inCrmCapForm.getRemarksPojo().getRemarks().length() > 128 ) )
         {
             inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_REMARKS_LENGTH ) );
         }
         else if ( StringUtils.isNotEmpty( inCrmCapForm.getRemarksPojo().getActions() )
                 && StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE ) )
         {
             if ( !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfStage(),
                                       CRMOperationStages.NP_REJECT.getCode() )
                     && !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfStage(),
                                             CRMOperationStages.SP_REJECT_BP.getCode() ) )
             {
                 if ( inCrmCapForm.getPartnerId() == 0 )
                 {
                     inActionError.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_PARTNER_REQUIRED ) );
                 }
             }
         }
         else if ( StringUtils.isNotEmpty( inCrmCapForm.getRemarksPojo().getActions() )
                 && StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.REJECT ) )
         {
             if ( inCrmCapForm.getRemarksPojo().getReasonId() == 0 )
             {
                 inActionError.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_LEAD_REASON_SELECT ) );
             }
         }*/
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        return inActionError;
    }

    public static boolean validCRFReference( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        LOGGER.info( "validate CRF Reference" );
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inCrmCustomerDetailsPojo, ICRMValidationCriteriaUtil.FORM_CRF_REFERENCE_NUMBER, false );
        return null == resultMap || resultMap.isEmpty();
    }

    public static boolean validISRReferenceNo( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        LOGGER.info( "validate ISR Reference No" );
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inCrmCustomerDetailsPojo, ICRMValidationCriteriaUtil.FORM_ISR_REFERENCE_NUMBER, false );
        return null == resultMap || resultMap.isEmpty();
    }

    public static ActionMessages validateISRDate( CrmCapForm inCrmCapForm, ActionMessages inErrors )
    {
        LOGGER.info( "inside validateISRDate  method." );
        if ( StringUtils.isValidObj( inCrmCapForm.getCustomerDetailsPojo().getActivationDate() )
                && StringUtils.isNotEmpty( inCrmCapForm.getDisplayISRDate() ) )
        {
            Date ISRDate = null;
            Date activationDate = null;
            try
            {
                ISRDate = IDateConstants.SDF_DD_MMM_YYYY.parse( inCrmCapForm.getDisplayISRDate() );
                String stringValue = DateUtils.convertXmlCalToString( inCrmCapForm.getCustomerDetailsPojo()
                        .getActivationDate() );
                activationDate = IDateConstants.SDF_DD_MMM_YYYY.parse( stringValue );
                long daydiff = DateUtils.daysDiff( activationDate, ISRDate );
                if ( daydiff < 0 )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_DISPLAY_ISR_VALIDATE_DATE ) );
                }
            }
            catch ( ParseException ex )
            {
                LOGGER.error( "Error occured while converting value", ex );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Error occured while converting value", ex );
            }
        }
        return inErrors;
    }

    public static ActionMessages validateCPEStatus( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "inside validateCPEStatus method." );
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getTelecommunicationPayment(),
                                                     ICRMValidationCriteriaUtil.FORM_INA_CPE_CRITERIA_COMMON, false );
        LOGGER.info( "Payment Mode........." + inCrmCapForm.getTelecommunicationPayment().getPaymentMode() );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                     CRMDisplayListConstants.CASH.getCode() ) )
            {
                /*  if ( !StringUtils.startsWith( inCrmCapForm.getTelecommunicationPayment().getReceiptNo(),
                                                    CRMDisplayListConstants.TELESOLUTIONS.getCode() ) )
                      {
                          inActionError.add( IAppConstants.APP_ERROR,
                                             new ActionMessage( CRMServiceCode.CRM703.getStatusCode(),
                                                                CRMDisplayListConstants.TELESOLUTIONS.getValue(),
                                                                CRMDisplayListConstants.TELESOLUTIONS.getCode() ) );
                      }
                      else
                      {*/
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getTelecommunicationPayment(),
                                                             ICRMValidationCriteriaUtil.FORM_CASH_PAYMENT_CRITERIA,
                                                             false );
                if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                {
                    CrmRcaReasonPojo master = new CrmRcaReasonPojo();
                    master.setCategory( CRMRCAReason.INA.getStatusCode() );
                    master.setSubCategory( "CashReceipt" );
                    master.setSubSubCategory( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
                    master.setCategoryValue( inCrmCapForm.getTelecommunicationPayment().getReceiptNo() );
                    master.setValueAlias( CRMStatusCode.UNUSED.getStatusCode() );
                    if ( !CRMCacheManager.validInMaster( master ) )
                    {
                        inActionError
                                .add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_INVALID_RECEIPT_NUMBER, inCrmCapForm
                                              .getTelecommunicationPayment().getReceiptNo() ) );
                    }
                }
                //                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getTelecommunicationPayment(),
                                                             ICRMValidationCriteriaUtil.FORM_ONLINE_PAYMENT_CRITERIA,
                                                             false );
            }
            else if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                          CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                if ( StringUtils.isNotBlank( inCrmCapForm.getTelecommunicationPayment().getDisplayChequeDate() ) )
                {
                    inCrmCapForm.getTelecommunicationPayment().setChequeDate( DateUtils.changeDateFormat( inCrmCapForm
                                                                                      .getTelecommunicationPayment()
                                                                                      .getDisplayChequeDate() ) );
                }
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getTelecommunicationPayment(),
                                                             ICRMValidationCriteriaUtil.FORM_CHEQUE_PAYMENT_CRITERIA,
                                                             false );
            }
            ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        }
        LOGGER.info( "Validation Result Map for validateCPEStatus method :" + resultMap );
        return inActionError;
    }
}
