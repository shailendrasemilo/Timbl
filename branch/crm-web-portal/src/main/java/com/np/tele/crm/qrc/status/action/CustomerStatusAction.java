/**
 * 
 */
package com.np.tele.crm.qrc.status.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jboss.logging.MDC;

import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.forms.CustomerStatusHelper;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.qrc.status.bm.ICustomerStatusBusinessMgr;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class CustomerStatusAction
    extends DispatchAction
{
    private static final Logger        LOGGER            = Logger.getLogger( CustomerStatusAction.class );
    private ICustomerStatusBusinessMgr customersStatusBm = null;

    public ICustomerStatusBusinessMgr getCustomersStatusBm()
    {
        return customersStatusBm;
    }

    public void setCustomersStatusBm( ICustomerStatusBusinessMgr customersStatusBm )
    {
        this.customersStatusBm = customersStatusBm;
    }

    public ActionForward viewSafeCustody( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inRespons )
    {
        String target = IActionForwardConstant.SAFE_CUSTODY_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        boolean buttonStatus = true;
        try
        {
            QrcForm inQrcForm = (QrcForm) inForm;
            String method = inRequest.getParameter( "method" );
            CustomerStatusHelper.validateStatus( inQrcForm, method, errors );
            if ( !errors.isEmpty() )
            {
                buttonStatus = false;
            }
            List<CrmRcaReasonPojo> whitlistReasonList = new ArrayList<CrmRcaReasonPojo>();
            whitlistReasonList = QRCCacheManager.getCustomerSafeCustodyReasons();
            inQrcForm.setCrmRcaReasonPojos( whitlistReasonList );
            inQrcForm.setRemarksPojo( new RemarksPojo() );
            inQrcForm.setExceptionReason( 0 );
            inQrcForm.setVisibileButton( buttonStatus );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return inMapping.findForward( target );
    }

    public ActionForward updateSafeCustody( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inRespons )
    {
        LOGGER.info( "updateSafeCustody Method called::" );
        String target = IActionForwardConstant.SAFE_CUSTODY_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        try
        {
            QrcForm qrcForm = (QrcForm) inForm;
            String method = inRequest.getParameter( "method" );
            CustomerStatusHelper.validateStatus( qrcForm, method, errors );
            String oldStatus = qrcForm.getCustDetailsPojo().getStatus();
            if ( errors.isEmpty() )
            {
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                CrmQrcDto crmQrcDto = getCustomersStatusBm().updateSafeCustody( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setSearchString( qrcForm.getCustomerId() );
                    target = IActionForwardConstant.QRC_CUSTOMER_PAGE;
                    message.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                else if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                {
                    qrcForm.getCustDetailsPojo().setStatus( oldStatus );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
                else
                {
                    qrcForm.getCustDetailsPojo().setStatus( oldStatus );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::", e );
        }
        saveMessages( inRequest, message );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    public ActionForward viewCustomersStatus( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inRespons )
    {
        String target = IActionForwardConstant.CUSTOMER_STATUS_PAGE;
        try
        {
            QrcForm qrcForm = (QrcForm) inForm;
            List<ContentPojo> list = CRMUtils.getManageCustomerStatus();
            qrcForm.setCustomerStatusList( list );
            qrcForm.getCustDetailsPojo().setStatus( CRMStatusCode.BARRED.getStatusCode() );
            qrcForm.setCrmRcaReasonPojos( QRCCacheManager.getCustomerBarringReasons() );
            qrcForm.setRemarksPojo( new RemarksPojo() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception Occured::", ex );
        }
        return inMapping.findForward( target );
    }

    public ActionForward updateCustomersStatus( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inRespons )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String method = inRequest.getParameter( "method" );
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        try
        {
            CustomerStatusHelper.validateStatus( qrcForm, method, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                String filePath = getServlet().getServletContext().getRealPath( "/" ) + "CustomerStatusUpload";
                List<String> statusList = getCustomersStatusBm().updateCustomersStatus( qrcForm, user, filePath );
                if ( statusList.size() > 1 )
                {
                    String errorFile = statusList.get( 1 );
                    String fileStatus = statusList.get( 0 );
                    String status = fileStatus.substring( 0, fileStatus.indexOf( "_" ) );
                    int validCount = Integer.parseInt( fileStatus.substring( fileStatus.indexOf( "_" ) + 1,
                                                                             fileStatus.lastIndexOf( "_" ) ) );
                    int totalRecords = Integer.parseInt( fileStatus.substring( fileStatus.lastIndexOf( "_" ) + 1 ) );
                    LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords
                            + " Valid Records db status: " + status );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2,
                                                                     totalRecords ) );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3,
                                                                     validCount ) );
                    if ( validCount < totalRecords )
                    {
                        messages.add( "displayErrorFile", new ActionMessage( "" ) );
                        inRequest.setAttribute( "fileName", errorFile );
                    }
                    if ( validCount > 0 )
                    {
                        messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4,
                                                                         status ) );
                    }
                    qrcForm.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                }
                else
                {
                    actionErrors.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.CUSTOMER_STATUS_PAGE );
    }
}
