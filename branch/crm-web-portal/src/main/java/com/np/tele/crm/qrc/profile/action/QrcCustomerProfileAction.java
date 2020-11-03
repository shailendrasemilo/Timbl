package com.np.tele.crm.qrc.profile.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jboss.logging.MDC;

import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.config.forms.CRMLogForm;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.forms.CustomerProfileHelper;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.qrc.forms.QrcFormHelper;
import com.np.tele.crm.qrc.profile.bm.ICustomerProfileMgr;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

/**
 * @author 
 *
 */
public class QrcCustomerProfileAction
    extends DispatchAction
{
    private static final Logger LOGGER             = Logger.getLogger( QrcCustomerProfileAction.class );
    private ICustomerProfileMgr customerProfileMgr = null;
    private IQrcManager         qrcManagerBm       = null;

    public ICustomerProfileMgr getCustomerProfileMgr()
    {
        return customerProfileMgr;
    }

    public void setCustomerProfileMgr( ICustomerProfileMgr customerProfileMgr )
    {
        this.customerProfileMgr = customerProfileMgr;
    }

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    public ActionForward viewCustomerCategory( ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            String method = inRequest.getParameter( "method" );
            CustomerProfileHelper.validate( qrcForm, method, errors );
            if ( errors.isEmpty() )
            {
                copyOldProperties( qrcForm );
                qrcForm.setConnectionTypes( CRMUtils.getConnectionTypes() );
                qrcForm.setVisaList( CRMUtils.getVisaTypes() );
                qrcForm.setRemarksPojo( new RemarksPojo() );
                qrcForm.setPresentStage( qrcForm.getCustDetailsPojo().getConnectionType() );
                qrcForm.setSrTicketPojo( new CrmSrTicketsPojo() );
                target = IActionForwardConstant.VIEW_CUSTOMER_CATEGORY;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward updateCustomerCategory( ActionMapping mapping,
                                                 ActionForm form,
                                                 HttpServletRequest inRequest,
                                                 HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.VIEW_CUSTOMER_CATEGORY;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            LOGGER.info( "updateCustomerCategory method called" );
            String method = inRequest.getParameter( "method" );
            CustomerProfileHelper.validate( qrcForm, method, errors );
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
            if ( errors.isEmpty() )
            {
                boolean isValueChange = validateValueChange( qrcForm );
                if ( !isValueChange )
                {
                    crmQrcDto.setActivityAction( CRMCustomerActivityActions.CUSTOMER_CATEGORY_MODIFY.getActionDesc() );
                    crmQrcDto = getCustomerProfileMgr().updateCustomerCategory( qrcForm, crmQrcDto );
                    LOGGER.info( "Status Code::" + crmQrcDto.getStatusCode() );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        target = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
                        if ( StringUtils.isEmpty( crmQrcDto.getCrmSrTicketsPojo().getSrId() ) )
                            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        else
                            message.add( IAppConstants.APP_MESSAGE,
                                         new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET, crmQrcDto
                                                 .getCrmSrTicketsPojo().getSrId() ) );
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
                else
                {
                    crmQrcDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward viewCustomerOwnerShip( ActionMapping mapping,
                                                ActionForm form,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            String method = inRequest.getParameter( "method" );
            CustomerProfileHelper.validate( qrcForm, method, errors );
            if ( errors.isEmpty() )
            {
                copyOldProperties( qrcForm );
                qrcForm.setVisaList( CRMUtils.getVisaTypes() );
                qrcForm.setRemarksPojo( new RemarksPojo() );
                qrcForm.setPresentName( qrcForm.getCustDetailsPojo().getCustFname() );
                qrcForm.setSrTicketPojo( new CrmSrTicketsPojo() );
                target = IActionForwardConstant.VIEW_CUSTOMER_CATEGORY;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward updateCustomerOwnerShip( ActionMapping mapping,
                                                  ActionForm form,
                                                  HttpServletRequest inRequest,
                                                  HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.VIEW_CUSTOMER_CATEGORY;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            LOGGER.info( "updateCustomerOwnership method called" );
            String method = inRequest.getParameter( "method" );
            CustomerProfileHelper.validate( qrcForm, method, errors );
            if ( errors.isEmpty() )
            {
                boolean isValueChange = validateValueChange( qrcForm );
                if ( !isValueChange )
                {
                    MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                    crmQrcDto.setActivityAction( CRMCustomerActivityActions.CUSTOMER_OWNERSHIP_MODIFY.getActionDesc() );
                    crmQrcDto = getCustomerProfileMgr().updateCustomerCategory( qrcForm, crmQrcDto );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.isEmpty( crmQrcDto.getCrmSrTicketsPojo().getSrId() ) )
                            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        else
                            message.add( IAppConstants.APP_MESSAGE,
                                         new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET, crmQrcDto
                                                 .getCrmSrTicketsPojo().getSrId() ) );
                        target = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
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
                else
                {
                    crmQrcDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward viewCustomerBillCycle( ActionMapping mapping,
                                                ActionForm form,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        LOGGER.info( "In QrcCustomerProfileAction :: viewCustomerBillCycle" );
        String target = IActionForwardConstant.VIEW_CUSTOMER_BILLCYCLE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        CrmSrTicketsPojo ticketsPojo = null;
        RemarksPojo remarksPojo = null;
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            ticketsPojo = new CrmSrTicketsPojo();
            remarksPojo = new RemarksPojo();
            String method = IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE;
            CustomerProfileHelper.validate( qrcForm, method, errors );
            if ( errors.isEmpty() )
            {
                qrcForm.setCustDetailsPojo( qrcForm.getCustDetailsPojo() );
            }
            else
            {
                target = IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE;
            }
            qrcForm.setSrTicketPojo( ticketsPojo );
            qrcForm.setRemarksPojo( remarksPojo );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured while ::", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward updateCustomerBillCycle( ActionMapping mapping,
                                                  ActionForm form,
                                                  HttpServletRequest inRequest,
                                                  HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.VIEW_CUSTOMER_BILLCYCLE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        try
        {
            String method = IAppConstants.METHOD_UPDATE_CUSTOMER_BILLCYCLE;
            CustomerProfileHelper.validate( qrcForm, method, errors );
            if ( errors.isEmpty() )
            {
                CrmQrcDto qrcDto = getCustomerProfileMgr().updateCustomerBillCycle( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isEmpty( qrcDto.getSrTicketNo() ) )
                        message.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                    else
                        message.add( IAppConstants.APP_MESSAGE,
                                     new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET, qrcDto
                                             .getSrTicketNo() ) );
                }
                else if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM434.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception Occured while updating the billing date :::", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward cancelBillCycle( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.CUSTOMER_BILL_CYCLE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        String customerId = inRequest.getParameter( "customerID" );
        String recordID = inRequest.getParameter( "recordId" );
        LOGGER.info( "customerId::" + customerId );
        LOGGER.info( "billCycleId::" + recordID );
        CrmQrcDto qrcDto = getCustomerProfileMgr()
                .cancelBillCycleRequest( customerId, recordID, qrcForm.getCrmUserId() );
        if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
        }
        else if ( StringUtils.isNotBlank( qrcDto.getBillingErrorCode() ) )
        {
            errors.add( IAppConstants.APP_ERROR,
                        new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
        }
        CRMLogForm logForm = new CRMLogForm();
        logForm.setCustomerId( customerId );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward viewGracePeriod( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.GRACE_PERIOD_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        qrcForm.setVisibileButton( true );
        List<CrmRcaReasonPojo> gracePeriodReasonList = new ArrayList<CrmRcaReasonPojo>();
        gracePeriodReasonList = QRCCacheManager.getCustomerGracePeriodReasons();
        qrcForm.setCrmRcaReasonPojos( gracePeriodReasonList );
        qrcForm.setRemarksPojo( new RemarksPojo() );
        qrcForm.setSrTicketPojo( new CrmSrTicketsPojo() );
        if ( !StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() )
                || !StringUtils.isValidObj( qrcForm.getCustAdditionalDetails().getExpiryDate() ) )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM528.getStatusCode() ) );
            qrcForm.setVisibileButton( false );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    public ActionForward saveGracePeriod( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.GRACE_PERIOD_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages message = new ActionMessages();
        QrcForm qrcForm = (QrcForm) form;
        QrcFormHelper.validateValidityExtension( errors, qrcForm );
        if ( errors.isEmpty() )
        {
            MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() + ", EXTDAYS:" + qrcForm.getDays() + ", REASON:"
                    + qrcForm.getGracePeriodReason() + ", TICKETID:" + qrcForm.getSrTicketNo() );
            CrmQrcDto crmQrcDto = getCustomerProfileMgr().saveGracePeriod( qrcForm );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
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
        List<CrmRcaReasonPojo> gracePeriodReasonList = new ArrayList<CrmRcaReasonPojo>();
        gracePeriodReasonList = QRCCacheManager.getCustomerGracePeriodReasons();
        qrcForm.setCrmRcaReasonPojos( gracePeriodReasonList );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, message );
        return mapping.findForward( target );
    }

    private void copyOldProperties( QrcForm qrcForm )
        throws IllegalAccessException, InvocationTargetException
    {
        CrmCustomerDetailsPojo oldCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        CrmNationalityDetailsPojo oldNationalityDetailsPojo = new CrmNationalityDetailsPojo();
        CrmDocumentDetailsPojo oldDocumentDetailsPojo = new CrmDocumentDetailsPojo();
        BeanUtils.copyProperties( oldCustomerDetailsPojo, qrcForm.getCustDetailsPojo() );
        BeanUtils.copyProperties( oldNationalityDetailsPojo, qrcForm.getNationalityDetailsPojo() );
        BeanUtils.copyProperties( oldDocumentDetailsPojo, qrcForm.getDocumentDetailsPojo() );
        LOGGER.info( "DOB :: " + oldCustomerDetailsPojo.getCustDob() );
        LOGGER.info( "DOB :: " + qrcForm.getCustDetailsPojo().getCustDob() );
        if ( StringUtils.isValidObj( oldCustomerDetailsPojo.getCustDob() ) )
        {
            qrcForm.getCustDetailsPojo().setDisplayCustDob( DateUtils.convertXmlCalToString( oldCustomerDetailsPojo
                                                                    .getCustDob() ) );
            oldCustomerDetailsPojo.setDisplayCustDob( DateUtils.convertXmlCalToString( oldCustomerDetailsPojo
                    .getCustDob() ) );
        }
        qrcForm.setTempCustomerDetailsPojo( oldCustomerDetailsPojo );
        qrcForm.setTempDocumentDetailsPojo( oldDocumentDetailsPojo );
        qrcForm.setTempNationalityDetailsPojo( oldNationalityDetailsPojo );
    }

    private boolean validateValueChange( QrcForm qrcForm )
    {
        boolean isValueChange = false;
        PojoComparator<CrmCustomerDetailsPojo> pojoComparator = new PojoComparator<CrmCustomerDetailsPojo>();
        PojoComparator<CrmNationalityDetailsPojo> nationaltyPojoComparator = new PojoComparator<CrmNationalityDetailsPojo>();
        PojoComparator<CrmDocumentDetailsPojo> documentDetailsPojoComparator = new PojoComparator<CrmDocumentDetailsPojo>();
        if ( pojoComparator.compare( qrcForm.getTempCustomerDetailsPojo(), qrcForm.getCustDetailsPojo() ) == 0 )
        {
            isValueChange = true;
            LOGGER.info( "CrmCustomerDetailsPojo will not be Save, because no change has been Made." );
        }
        if ( isValueChange && StringUtils.equals( qrcForm.getCustDetailsPojo().getNationality(), IAppConstants.OTHER ) )
        {
            if ( nationaltyPojoComparator.compare( qrcForm.getTempNationalityDetailsPojo(),
                                                   qrcForm.getNationalityDetailsPojo() ) == 0 )
            {
                isValueChange = true;
                LOGGER.info( "Nationality Details will not be Save, because no change has been Made." );
            }
            else
            {
                isValueChange = false;
            }
        }
        if ( isValueChange && StringUtils.isBlank( qrcForm.getCustDetailsPojo().getCustPanGirNo() ) )
        {
            if ( documentDetailsPojoComparator.compare( qrcForm.getTempDocumentDetailsPojo(),
                                                        qrcForm.getDocumentDetailsPojo() ) == 0 )
            {
                isValueChange = true;
                LOGGER.info( "DocumentDetailsPojo  will not be Save, because no change has been Made." );
            }
            else
            {
                isValueChange = false;
            }
        }
        LOGGER.info( "final Value::" + isValueChange );
        return isValueChange;
    }
}
