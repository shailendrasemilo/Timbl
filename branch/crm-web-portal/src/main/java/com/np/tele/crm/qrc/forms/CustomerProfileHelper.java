package com.np.tele.crm.qrc.forms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class CustomerProfileHelper
{
    private static final Logger LOGGER = Logger.getLogger( CustomerProfileHelper.class );

    public static void validate( QrcForm inQrcForm, String method, ActionMessages inErrors )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_CUSTOMER_BILLCYCLE ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm.getCustDetailsPojo(),
                                   ICRMValidationCriteriaUtil.FORM_QRC_BILL_CYCLE_CHANGE, false );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_CUSTOMER_CATEGORY ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_CUSTOMER_OWNERSHIP ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQrcForm, ICRMValidationCriteriaUtil.FORM_QRC_CUSTOMER_ID, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_CUSTOMER_OWNERSHIP )
                || StringUtils.equals( method, IAppConstants.METHOD_UPDATE_CUSTOMER_CATEGORY ) )
        {
            Map<String, Object[]> resultMap = null;
            if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getConnectionType(), "0" ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CONNECTION_TYPE ) );
            }
            else
            {
                if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getConnectionType(),
                                         CRMDisplayListConstants.INDIVIDUAL.getCode() )
                        || StringUtils.equals( inQrcForm.getCustDetailsPojo().getConnectionType(),
                                               CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) )
                {
                    
                    inQrcForm.getCustDetailsPojo().setCustDob( DateUtils.changeDateFormat( inQrcForm
                                                                       .getCustDetailsPojo().getDisplayCustDob() ) );
                    resultMap = ValidationPojoUtil
                            .validateForm( inQrcForm.getCustDetailsPojo(),
                                           ICRMValidationCriteriaUtil.FORM_QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL,
                                           false );
                    if ( ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                            && StringUtils
                                    .equals( inQrcForm.getCustDetailsPojo().getNationality(), IAppConstants.OTHER ) )
                    {
                        inQrcForm.getNationalityDetailsPojo()
                                .setPassportValidity( DateUtils.changeDateFormat( inQrcForm.getNationalityDetailsPojo()
                                                              .getDisplayPassportValidity() ) );
                        inQrcForm.getNationalityDetailsPojo().setVisaValidity( DateUtils.changeDateFormat( inQrcForm
                                                                                       .getNationalityDetailsPojo()
                                                                                       .getDisplayVisaValidity() ) );
                        resultMap = ValidationPojoUtil
                                .validateForm( inQrcForm.getNationalityDetailsPojo(),
                                               ICRMValidationCriteriaUtil.FORM_QRC_PASSPORT_DETAILS, false );
                    }
                }
                else
                {
                    resultMap = ValidationPojoUtil
                            .validateForm( inQrcForm.getCustDetailsPojo(),
                                           ICRMValidationCriteriaUtil.FORM_QRC_CHANGE_CATEGORY_CRITERIA_LTD, false );
                }
                if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                {
                    resultMap = ValidationPojoUtil.validateForm( inQrcForm.getRemarksPojo(),
                                                                 ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK,
                                                                 false );
                }
                //                if ( ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                //                        && StringUtils.isEmpty( inQrcForm.getCustDetailsPojo().getCustPanGirNo() ) )
                //                {
                //                    resultMap = ValidationPojoUtil
                //                            .validateForm( inQrcForm.getDocumentDetailsPojo(),
                //                                           ICRMValidationCriteriaUtil.FORM_QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL_FORM60,
                //                                           false );
                //                }
            }
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }

    public static void reset( QrcForm qrcForm, String method )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE ) )
        {
            qrcForm.setRemarksPojo( new RemarksPojo() );
            qrcForm.setSrTicketNo( null );
        }
    }
}
