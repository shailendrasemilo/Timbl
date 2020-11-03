package com.np.tele.crm.qrc.forms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmShiftingWorkflowPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class QrcWorkFlowHelper
{
    private static final Logger LOGGER = Logger.getLogger( CustomerStatusHelper.class );

    public static void validate( QrcForm inQrcForm, String method, ActionMessages inErrors )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_SHIFTING_INITIATION ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_IFR_STAGE ) )
        {
            Map<String, Object[]> resultMap = null;
            resultMap = ValidationPojoUtil
                    .validateSingleFormProperty( inQrcForm.getShiftingWorkflowPojo(),
                                                 ICRMValidationCriteriaUtil.FORM_PARTNER_REQUIRED, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_CSD_LEVEL2 ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                if ( StringUtils.isEmpty( inQrcForm.getCustomerResponse() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_RESPONSE ) );
                }
                else
                {
                    if ( StringUtils.equals( inQrcForm.getCustomerResponse(), IAppConstants.YES_CHAR ) )
                    {
                        if ( StringUtils.isEmpty( inQrcForm.getPlanTypeShifting() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_SHIFTING_PLAN_TYPE ) );
                        }
                        else
                        {
                            if ( StringUtils.isEmpty( inQrcForm.getPlanDetailsPojo().getBasePlanCode() ) )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_BASE_PLAN_CODE ) );
                            }
                        }
                    }
                    if ( StringUtils.equals( inQrcForm.getCustomerResponse(), IAppConstants.NO_CHAR ) )
                    {
                        if ( StringUtils.isEmpty( inQrcForm.getShiftingWorkflowPojo().getCloserReason() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_CLOSER_REASON ) );
                        }
                    }
                }
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_CSD_LEVEL3 ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                if ( StringUtils.equals( IAppConstants.NO_CHAR, inQrcForm.getCustomerResponse() ) )
                {
                    if ( StringUtils.isEmpty( inQrcForm.getShiftingWorkflowPojo().getCloserReason() ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_CLOSER_REASON ) );
                    }
                }
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_WAIVER_APPROVED_REJECT ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil.validateForm( inQrcForm.getCustWaiverPojo()
                    .getCustomerId(), ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                if ( !StringUtils.equals( inQrcForm.getCustWaiverPojo().getStatus(),
                                          CRMStatusCode.REJECTED.getStatusCode() )
                        && !StringUtils.equals( inQrcForm.getCustWaiverPojo().getStatus(),
                                                CRMStatusCode.WAIVER_APPROVE.getStatusCode() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_WAIVER_ACTION ) );
                }
                else
                {
                    if ( StringUtils.equals( inQrcForm.getCustWaiverPojo().getStatus(),
                                             CRMStatusCode.REJECTED.getStatusCode() )
                            && StringUtils.isEmpty( inQrcForm.getCustWaiverPojo().getRejectionReason() ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_WAIVER_REJECTED_REASON ) );
                    }
                }
            }
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_SAVE_SP_DETAILS ) )
        {
            Map<String, Object[]> resultMap = null;
            resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getOrderDetailsPojo(),
                                   ICRMValidationCriteriaUtil.FORM_QRC_SHIFTING_CPE_CHANGE, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_EOC_LEVEL_ONE ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            /*if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                if ( StringUtils.equals( IAppConstants.YES_CHAR, inQrcForm.getShiftingWorkflowPojo()
                        .getPhysicalInstallation() )
                        && StringUtils.equals( IAppConstants.NO_CHAR, inQrcForm.getShiftingWorkflowPojo()
                                .getCustomerRefusal() )
                        && StringUtils.equals( IAppConstants.YES_CHAR, inQrcForm.getShiftingWorkflowPojo()
                                .getCpeAvailable() ) )
                {
                    if ( StringUtils.equals( inQrcForm.getShiftingWorkflowPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() ) )
                    {
                        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                        {
                            resultMap = ValidationPojoUtil
                                    .validateForm( inQrcForm.getShiftingWorkflowPojo(),
                                                   ICRMValidationCriteriaUtil.FORM_CRF_FOR_NETWORK_CONFIGURATION, false );
                        }
                        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                        {
                            resultMap = ValidationPojoUtil
                                    .validateForm( inQrcForm.getShiftingWorkflowPojo(),
                                                   ICRMValidationCriteriaUtil.FORM_CRF_CPE_MAC_MAP_ID, false );
                        }
                    }
                    else
                    {
                        if ( StringUtils.isEmpty( inQrcForm.getNassPortId() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_OPTION_82_NASSPORT_VALUES_REQUIRED ) );
                        }
                        resultMap = ValidationPojoUtil.validateForm( inQrcForm.getShiftingWorkflowPojo(),
                                                                     ICRMValidationCriteriaUtil.FORM_CRF_MAC_ADDRESS,
                                                                     false );
                    }
                }
            }*/
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_NOC_LEVEL1 ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_IFR_EOC_LEVEL2 ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }

    public static void reset( QrcForm qrcForm, String method )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_SHIFTING_INITIATION ) )
        {
            qrcForm.setRemarksPojo( new RemarksPojo() );
            qrcForm.getShiftingWorkflowPojo().setShiftingType( null );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_WAIVER_PAGE ) )
        {
            if ( StringUtils.isValidObj( qrcForm.getCustWaiverPojo() ) )
                qrcForm.getCustWaiverPojo().setRejectionReason( "" );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_IFR_STAGE ) )
        {
            qrcForm.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_EDIT_SP ) )
        {
            qrcForm.setNewPrimaryMacId( null );
            qrcForm.setNewSecondaryMacId( null );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_EDIT_NP ) )
        {
            if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                qrcForm.setNewOption82( null );
            }
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_GET_SHIFTING_HISTORY ) )
        {
            qrcForm.setCrmShiftingWorkflowPojos( null );
        }
    }

    public static void validateChangeInstAddr( ActionMessages errors, QrcForm qrcForm )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( qrcForm.getNewInstallationAddress(),
                               ICRMValidationCriteriaUtil.FORM_QRC_INSTALLATION_ADDRESS_CHANGE, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateForm( qrcForm.getRemarksPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        }
        ValidationUtil.prepareErrorMessage( errors, resultMap );
    }
}
