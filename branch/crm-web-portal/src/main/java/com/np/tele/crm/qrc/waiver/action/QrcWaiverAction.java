/**
 * 
 */
package com.np.tele.crm.qrc.waiver.action;

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
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.qrc.forms.QrcWorkFlowHelper;
import com.np.tele.crm.qrc.workflow.bm.IWorkFlowManager;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

/**
 * @author 
 *
 */
public class QrcWaiverAction
    extends DispatchAction
{
    private static final Logger LOGGER          = Logger.getLogger( QrcWaiverAction.class );
    private IWorkFlowManager    workFlowManager = null;
    private IQrcManager         qrcManagerBm    = null;
    private IMasterBMngr        masterBMngr     = null;

    public IWorkFlowManager getWorkFlowManager()
    {
        return workFlowManager;
    }

    public void setWorkFlowManager( IWorkFlowManager workFlowManager )
    {
        this.workFlowManager = workFlowManager;
    }

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public ActionForward viewWaiver( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
    {
        String target = IActionForwardConstant.QRC_VIEW_WAIVER_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            qrcForm.setCustWaiverPojo( new CrmCustWaiverPojo() );
            if ( StringUtils.isNotEmpty( request.getParameter( "workflowId" ) )
                    && StringUtils.equals( request.getParameter( "inRequestType" ),
                                           CRMRequestType.WAIVER.getRequestCode() ) )
            {
                qrcForm.getCustWaiverPojo().setWorkflowId( request.getParameter( "workflowId" ) );
                getWaiverPojo( request, qrcForm );
                String inboxId = request.getParameter( "inInboxId" );
                MasterDataDto dataDto = getMasterBMngr().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Marked inbox entry to unbold" );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::", e );
        }
        saveErrors( request, errors );
        saveMessages( request, message );
        return mapping.findForward( target );
    }

    public ActionForward modifiyWaiverPage( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
    {
        String target = IActionForwardConstant.QRC_UPDATE_WAIVER_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        String inboxId = request.getParameter( "inboxId" );
        try
        {
            qrcForm.setCustWaiverPojo( new CrmCustWaiverPojo() );
            if ( StringUtils.isNotEmpty( request.getParameter( "workflowId" ) )
                    && StringUtils.equals( request.getParameter( "inRequestType" ),
                                           CRMRequestType.WAIVER.getRequestCode() ) )
            {
                qrcForm.getCustWaiverPojo().setWorkflowId( request.getParameter( "workflowId" ) );
                getWaiverPojo( request, qrcForm );
                qrcForm.setRemarksPojo( new RemarksPojo() );
                qrcForm.setRejectionReasons( QRCCacheManager.getWaiverRejectionReasons() );
                MasterDataDto dataDto = getMasterBMngr().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Marked inbox entry to unbold" );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::", e );
        }
        saveErrors( request, errors );
        saveMessages( request, message );
        return mapping.findForward( target );
    }

    public ActionForward approveRejectWaiver( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
    {
        String target = IActionForwardConstant.QRC_UPDATE_WAIVER_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        String method = request.getParameter( "method" );
        try
        {
            if ( StringUtils.isValidObj( qrcForm.getCustWaiverPojo() ) )
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                QrcWorkFlowHelper.validate( qrcForm, method, errors );
                if ( errors.isEmpty() )
                {
                    CrmQrcDto crmQrcDto = getWorkFlowManager().approveRejectWaiver( qrcForm );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Before::" + crmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                        qrcForm.getCustWaiverPojo().setWorkflowId( crmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                        LOGGER.info( "After::" + qrcForm.getCustWaiverPojo().getWorkflowId() );
                        getWaiverPojo( request, qrcForm );
                        target = IActionForwardConstant.WORKFLOW_INBOX;
                        message.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    }
                    else if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    }
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::", e );
        }
        saveErrors( request, errors );
        saveMessages( request.getSession(), message );
        message = null;
        return mapping.findForward( target );
    }

    private void getWaiverPojo( HttpServletRequest request, QrcForm qrcForm )
    {
        CrmQrcDto qrcDto = getQrcManagerBm().getCustWaiverPojos( qrcForm );
        LOGGER.info( "getWaiverPojo ::qrcDto status :::::" + qrcDto.getStatusCode() );
        if ( CommonValidator.isValidCollection( qrcDto.getCrmCustWaiverPojosList() ) )
        {
            qrcForm.setCustWaiverPojo( qrcDto.getCrmCustWaiverPojosList().get( 0 ) );
            qrcForm.setPresentStage( qrcForm.getCustWaiverPojo().getStatus() );
        }
    }
}
