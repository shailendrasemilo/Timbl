package com.np.tele.crm.qrc.action;

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

import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.qrc.bm.IBulkUpload;
import com.np.tele.crm.qrc.forms.BulkUploadForm;
import com.np.tele.crm.qrc.forms.BulkUploadFormHelper;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.StringUtils;

public class BulkUploadAction
    extends DispatchAction
{
    private static final Logger LOGGER       = Logger.getLogger( BulkUploadAction.class );
    private IBulkUpload         bulkUploadBm = null;

    public IBulkUpload getBulkUploadBm()
    {
        return bulkUploadBm;
    }

    public void setBulkUploadBm( IBulkUpload inBulkUploadBm )
    {
        bulkUploadBm = inBulkUploadBm;
    }

    public ActionForward bulkBoosterMountPage( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        BulkUploadForm bulkUploadForm = (BulkUploadForm) inForm;
        bulkUploadForm.setRemarksPojo( new RemarksPojo() );
        return inMapping.findForward( IActionForwardConstant.BULK_MOUNT_BOOSTER_PAGE );
    }

    public ActionForward uploadMountBooster( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        BulkUploadForm bulkUploadForm = (BulkUploadForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = getErrors( inRequest );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String filePath = getServlet().getServletContext().getRealPath( "/" ) + "MountBoosterExcelUpload";
            if ( BulkUploadFormHelper.bulkMountBoosterFormValidation( actionErrors, bulkUploadForm, filePath,
                                                                      userDto.getCrmUserDetailsPojo().getUserId() )
                    .isEmpty() )
            {
                List<String> statusList = getBulkUploadBm().processMountBoosterExcel( bulkUploadForm,
                                                                                      userDto.getCrmUserDetailsPojo()
                                                                                              .getUserId(), filePath );
                if ( statusList.size() > 1 )
                {
                    String status = statusList.get( 0 );
                    String errorFile = statusList.get( 1 );
                    int totalRecords = Integer.parseInt( statusList.get( 2 ) );
                    int validCount = Integer.parseInt( statusList.get( 3 ) );
                    LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords
                            + " Valid Records db status: " + status );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2,
                                                                     totalRecords ) );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3,
                                                                     validCount ) );
                    if ( ( validCount < totalRecords ) || StringUtils.equals( status, "failed" ) )
                    {
                        messages.add( "displayErrorFile", new ActionMessage( "" ) );
                        inRequest.setAttribute( "fileName", errorFile );
                    }
                    if ( validCount > 0 )
                    {
                        messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4,
                                                                         status ) );
                    }
                    bulkUploadForm.setRemarksPojo( new RemarksPojo() );
                }
                else
                {
                    actionErrors.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in upload mount booster file", ex );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.BULK_MOUNT_BOOSTER_PAGE );
    }

    public ActionForward bulkValidityExtensionPage( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        BulkUploadForm bulkUploadForm = (BulkUploadForm) inForm;
        List<CrmRcaReasonPojo> gracePeriodReasonList = new ArrayList<CrmRcaReasonPojo>();
        gracePeriodReasonList = QRCCacheManager.getCustomerGracePeriodReasons();
        bulkUploadForm.setCrmRcaReasonPojos( gracePeriodReasonList );
        bulkUploadForm.setRemarksPojo( new RemarksPojo() );
        return inMapping.findForward( IActionForwardConstant.BULK_VALIDITY_EXTENSION_PAGE );
    }

    public ActionForward uploadValidityExtension( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        BulkUploadForm bulkUploadForm = (BulkUploadForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = getErrors( inRequest );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String filePath = getServlet().getServletContext().getRealPath( "/" ) + "ValidityExtensionExcelUpload";
            if ( BulkUploadFormHelper.bulkValidityExtensionFormValidation( actionErrors, bulkUploadForm, filePath,
                                                                           userDto.getCrmUserDetailsPojo().getUserId() )
                    .isEmpty() )
            {
                List<String> statusList = getBulkUploadBm()
                        .processValidityExtensionExcel( bulkUploadForm, userDto.getCrmUserDetailsPojo().getUserId(),
                                                        filePath );
                if ( statusList.size() > 1 )
                {
                    String status = statusList.get( 0 );
                    String errorFile = statusList.get( 1 );
                    int totalRecords = Integer.parseInt( statusList.get( 2 ) );
                    int validCount = Integer.parseInt( statusList.get( 3 ) );
                    LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords
                            + " Valid Records db status: " + status );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2,
                                                                     totalRecords ) );
                    messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3,
                                                                     validCount ) );
                    if ( ( validCount < totalRecords ) || StringUtils.equals( status, "failed" ) )
                    {
                        messages.add( "displayErrorFile", new ActionMessage( "" ) );
                        inRequest.setAttribute( "fileName", errorFile );
                    }
                    if ( validCount > 0 )
                    {
                        messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4,
                                                                         status ) );
                    }
                    bulkUploadForm.setRemarksPojo( new RemarksPojo() );
                }
                else
                {
                    actionErrors.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in upload validity Extension file", ex );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.BULK_VALIDITY_EXTENSION_PAGE );
    }
}
