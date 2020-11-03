package com.np.tele.crm.qrc.forms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.utils.StringUtils;

public class CustomerStatusHelper
{
    private static final Logger LOGGER = Logger.getLogger( CustomerStatusHelper.class );

    public static void validateStatus( QrcForm qrcForm, String method, ActionMessages inErrors )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_SAFE_CUSTODY ) )
        {
            if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                     CRMDisplayListConstants.POST_PAID.getCode() )
                    && ( StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) ) )
            {
                if ( qrcForm.getCustAdditionalDetails().getBalance() > 0 )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.INVALID_OUTSTANDING ) );
                }
            }
            else if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                          CRMDisplayListConstants.PRE_PAID.getCode() )
                    && StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
            {
                if ( qrcForm.getCustAdditionalDetails().getBalance() < 0 )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.INVALID_OUTSTANDING ) );
                }
            }
            else if ( !StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_SAFE_CUSTODY ) )
        {
            Map<String, Object[]> inResultMap = null;
            inResultMap = QrcFormHelper.validateCustomer( inResultMap, qrcForm );
            if ( qrcForm.getExceptionReason() <= 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_SAFE_CUSTODY_REASON ) );
            }
            if ( inResultMap == null )
            {
                inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
            }
            ValidationUtil.prepareErrorMessage( inErrors, inResultMap );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_CUSTOMERS_STATUS ) )
        {
            LOGGER.info( "File : " + qrcForm.getWhitelistExcelFile().getFileName() + " Size : "
                    + qrcForm.getWhitelistExcelFile().getFileSize() );
            Map<String, Object[]> inResultMap = null;
            if ( qrcForm.getExceptionReason() == 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_BLANK_REASON ) );
            }
            // validation before processing
            if ( StringUtils.isEmpty( qrcForm.getCustDetailsPojo().getStatus() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_WHITELIST_TYPE ) );
            }
            if ( qrcForm.getWhitelistExcelFile().getFileSize() == 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
            }
            else if ( qrcForm.getWhitelistExcelFile().getFileSize() > 20480 )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
            }
            inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
        }
    }

    public static void resetCustomerDetails( QrcForm qrcForm, String method )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_VIEW_SAFE_CUSTODY ) )
        {
            qrcForm.getRemarksPojo().setRemarks( "" );
            qrcForm.setExceptionReason( 0 );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_SAFE_CUSTODY ) )
        {
            qrcForm.getRemarksPojo().setRemarks( "" );
            qrcForm.setExceptionReason( 0 );
        }
    }
}
