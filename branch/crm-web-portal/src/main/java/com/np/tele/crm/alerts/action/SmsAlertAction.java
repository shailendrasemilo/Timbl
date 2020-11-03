package com.np.tele.crm.alerts.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.alerts.bm.IEventTemplateMapping;
import com.np.tele.crm.alerts.bm.ISmsAlert;
import com.np.tele.crm.alerts.forms.AlertFormHelper;
import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SmsGatewayPojo;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SmsAlertAction
    extends DispatchAction
{
    private static final Logger LOGGER               = Logger.getLogger( SmsAlertAction.class );
    private ISmsAlert           smsAlertImp          = null;
    private IMasterBMngr        masterDataBm         = null;
    private ICrmConfigManager   crmConfigManager     = null;
    IEventTemplateMapping       eventTemplateMapping = null;

    public ISmsAlert getSmsAlertImp()
    {
        return smsAlertImp;
    }

    public void setSmsAlertImp( ISmsAlert inSmsAlertImp )
    {
        smsAlertImp = inSmsAlertImp;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr inMasterDataBm )
    {
        masterDataBm = inMasterDataBm;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ActionForward newSMSTemplate( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        AlertsForm alertsFrom = (AlertsForm) form;
        List<SmsGatewayPojo> smsGatewayList = getCrmConfigManager().getSmsGateways();
        if ( CommonValidator.isValidCollection( smsGatewayList ) )
        {
            Iterator<SmsGatewayPojo> smsGatewayItr = smsGatewayList.iterator();
            while ( smsGatewayItr.hasNext() )
            {
                SmsGatewayPojo smsGatewayPojo = smsGatewayItr.next();
                if ( !smsGatewayPojo.isEnable() )
                {
                    smsGatewayItr.remove();
                }
            }
        }
        SortingComparator<SmsGatewayPojo> sortComparator = new SortingComparator<SmsGatewayPojo>( "subCategory" );
        Collections.sort( smsGatewayList, sortComparator );
        sortComparator = null;
        alertsFrom.setSmsGateways( smsGatewayList );
        return mapping.findForward( IActionForwardConstant.CREATE_SMS_TEMPLATE );
    }

    public ActionForward searchTemplate( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        AlertsForm alertsFrom = (AlertsForm) form;
        alertsFrom.setStatusFor( null );
        alertsFrom.setStatusList( CRMUtils.getAciveInactiveStatusList() );
        return mapping.findForward( IActionForwardConstant.SEARCH_TEMPLATE );
    }

    public ActionForward createSMSTemplate( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.CREATE_SMS_TEMPLATE;
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            alertsForm.setTemplateType( IAppConstants.SMS );
            alertDto = getSmsAlertImp().createSmsTemplate( alertsForm, userDto.getCrmUserDetailsPojo().getUserId() );
            LOGGER.info( "Status Code :" + alertDto.getStatusCode() );
            alertsForm.setStatusList( CRMUtils.getAciveInactiveStatusList() );
            alertsForm.setProjects( getMasterDataBm().getProjectList( alertsForm.getProjectType() ) );
            if ( alertDto.getStatusCode().equals( CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESSFULLY_TEMPLATE_CREATED, StringUtils
                                      .upperCase( IAppConstants.SMS ), alertDto.getSmsTemplatePojo()
                                      .getSmsTemplateName() ) );
                forwardPage = IActionForwardConstant.VIEW_SMS_TEMPLATE;
            }
            else
            {
                LOGGER.info( "somthing has wrong in create sms template.... " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( alertDto.getStatusCode() ) );
                forwardPage = IActionForwardConstant.CREATE_SMS_TEMPLATE;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while create SMS Template", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward editSMSTemplate( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response )
        throws Exception
    {
        AlertsForm alertForm = (AlertsForm) form;
        List<SmsGatewayPojo> smsGatewayList = getCrmConfigManager().getSmsGateways();
        if ( CommonValidator.isValidCollection( smsGatewayList ) )
        {
            Iterator<SmsGatewayPojo> smsGatewayItr = smsGatewayList.iterator();
            while ( smsGatewayItr.hasNext() )
            {
                SmsGatewayPojo smsGatewayPojo = smsGatewayItr.next();
                if ( !smsGatewayPojo.isEnable() )
                {
                    smsGatewayItr.remove();
                }
            }
        }
        alertForm.setSmsGateways( smsGatewayList );
        SmsTemplatePojo smsTemplatePojo = new SmsTemplatePojo();
        smsTemplatePojo.setSmsTemplateId( alertForm.getTemplateId() );
        int pojoIndex = alertForm.getSmsTemplateList().indexOf( smsTemplatePojo );
        if ( pojoIndex >= 0 )
        {
            SmsTemplatePojo smspojo = alertForm.getSmsTemplateList().get( pojoIndex );
            alertForm.setSmsType( smspojo.getSmsType() );
            alertForm.setTemplateName( smspojo.getSmsTemplateName() );
            alertForm.setProjectName( smspojo.getProjects().getProjectName() );
            alertForm.setProjectId( smspojo.getProjects().getProjectId() );
            alertForm.setSmsGateway( smspojo.getSmsGateway() );
            alertForm.setParameterList( getMasterDataBm().getParameters( smspojo.getProjects().getProjectId(),
                                                                         CRMParameter.ALERT, null ) );
            byte[] temp = smspojo.getSmsTemplate();
            String s = new String( temp );
            alertForm.setSmsTemplateBody( s );
            alertForm.setSmsTemplatePojo( smspojo );
        }
        LOGGER.info( "Size Of LIST:--------" + alertForm.getSmsTemplateList().size() );
        return mapping.findForward( IActionForwardConstant.MODIFY_SMS_TEMPLATE );
    }

    public ActionForward modifySMSTemplate( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        String forward = IActionForwardConstant.MODIFY_SMS_TEMPLATE;
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            alertDto = getSmsAlertImp().modifySmsTemplate( alertsForm, userDto.getCrmUserDetailsPojo().getUserId() );
            if ( alertDto.getStatusCode().equals( CRMServiceCode.CRM001.getStatusCode() ) )
            {
                alertsForm.setSmsTemplatePojo( null );
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESSFULLY_UPDATE_TEMPLATE, alertDto
                                      .getSmsTemplatePojo().getSmsTemplateName() ) );
                forward = IActionForwardConstant.VIEW_SMS_TEMPLATE;
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( alertDto.getStatusCode() ) );
                forward = IActionForwardConstant.MODIFY_SMS_TEMPLATE;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while modify SMS Template", ex );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( forward );
    }

    public ActionForward exportSMSTemplate( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        OutputStream out = null;
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            SmsTemplatePojo smsTemplatePojo = new SmsTemplatePojo();
            smsTemplatePojo.setSmsTemplateId( alertsForm.getTemplateId() );
            int pojoIndex = alertsForm.getSmsTemplateList().indexOf( smsTemplatePojo );
            if ( pojoIndex >= 0 )
            {
                SmsTemplatePojo smspojo = alertsForm.getSmsTemplateList().get( pojoIndex );
                if ( StringUtils.isValidObj( smspojo ) )
                {
                    response.setHeader( "Content-type", "application/download" );
                    response.setHeader( "Content-disposition", "inline; filename=" + smspojo.getSmsTemplateName()
                            + ".txt" );
                    byte[] body = smspojo.getSmsTemplate();
                    out = response.getOutputStream();
                    out.write( body );
                    out.close();
                    response.flushBuffer();
                }
            }
        }
        catch ( Exception ex )
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter( sw );
            ex.printStackTrace( pw );
            LOGGER.error( " Failed to generate sms Template Reason[ " + sw.toString() + " ] " );
        }
        return null;
    }

    public ActionForward viewSMSTemplate( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        List<SmsTemplatePojo> smsTemplateList = null;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        String target = IActionForwardConstant.SEARCH_TEMPLATE;
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            alertsForm.setProjects( getMasterDataBm().getProjectList( alertsForm.getProjectType() ) );
            alertDto = getSmsAlertImp().searchAndViewSMSTemplate( alertsForm );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), alertDto.getStatusCode() ) )
            {
                smsTemplateList = (List<SmsTemplatePojo>) alertDto.getSmsTemplatePojos();
                alertsForm.setSmsTemplateList( smsTemplateList );
                if ( smsTemplateList.isEmpty() )
                {
                    LOGGER.info( "Record not found..  " );
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while search and view sms template", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward activeAndDeactiveSMSTemplate( ActionMapping mapping,
                                                       ActionForm form,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        List<SmsTemplatePojo> smsTemplateList = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.SEARCH_TEMPLATE;
        try
        {
            HttpSession session = request.getSession( false );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT );
            AlertsForm alertsForm = (AlertsForm) form;
            SmsTemplatePojo smsTemplatePojo = new SmsTemplatePojo();
            smsTemplatePojo.setSmsTemplateId( alertsForm.getTemplateId() );
            int pojoIndex = alertsForm.getSmsTemplateList().indexOf( smsTemplatePojo );
            if ( pojoIndex >= 0 )
            {
                SmsTemplatePojo smspojo = alertsForm.getSmsTemplateList().get( pojoIndex );
                if ( StringUtils.isValidObj( smspojo ) )
                {
                    smspojo.setStatus( alertsForm.getStatus() );
                    smspojo.setLastModifiedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                    alertDto = getSmsAlertImp().activeAndDeactiveSMSTemplate( smspojo );
                }
            }
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), alertDto.getStatusCode() ) )
            {
                LOGGER.info( "sms Template modify status successfully..." );
                alertDto = getSmsAlertImp().searchAndViewSMSTemplate( alertsForm );
                smsTemplateList = (List<SmsTemplatePojo>) alertDto.getSmsTemplatePojos();
                LOGGER.info( "Size of smsTemplateList" + smsTemplateList.size() );
                if ( smsTemplateList.size() > 0 )
                {
                    alertsForm.setSmsTemplateList( smsTemplateList );
                }
                else
                {
                    LOGGER.info( "List is Empty" + smsTemplateList.size() );
                    alertsForm.setStatusCode( alertDto.getStatusCode() );
                }
            }
            else
            {
                LOGGER.info( "somthing has wrong in modify status of sms template ..." + alertDto.getStatusCode() );
                alertsForm.setStatusCode( alertDto.getStatusCode() );
                forward = IActionForwardConstant.SEARCH_TEMPLATE;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while active and deactive sms templete", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forward );
    }

    public ActionForward userAlertTemplatePage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        AlertsForm alertsForm = (AlertsForm) inForm;
        List<CrmNpMobilePojo> oldCrmNpMobilePojos = new ArrayList<CrmNpMobilePojo>();
        CrmNpMobilePojo oldCrmNpMobilePojo = null;
        List<CrmNpMobilePojo> crmNpMobilePojos = null;
        AlertsDto alertsDto = new AlertsDto();
        alertsDto = getEventTemplateMapping().eventTemplateMappingList( alertsDto );
        alertsForm.setEventList( alertsDto.getEventsPojoList() );
        SortingComparator<EventsPojo> sortComparator = new SortingComparator<EventsPojo>( "eventDescription" );
        Collections.sort( alertsForm.getEventList(), sortComparator );
        for ( EventsPojo object : alertsForm.getEventList() )
        {
            if ( object.getEventName().equalsIgnoreCase(IAppConstants.ADD_MASS_OUTAGE) )
            {
                alertsForm.setEventId( object.getEventId() );
                break;
            }
        }
        
        try
        {
            crmNpMobilePojos = getMasterDataBm().getCrmUserAlertMobileEmailList( alertsForm ).getCrmNpMobilePojos();
            if ( CommonValidator.isValidCollection( crmNpMobilePojos ) )
            {
                for ( CrmNpMobilePojo npMobilePojo : crmNpMobilePojos )
                {
                    oldCrmNpMobilePojo = new CrmNpMobilePojo();
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( oldCrmNpMobilePojo, npMobilePojo );
                    oldCrmNpMobilePojos.add( oldCrmNpMobilePojo );
                }
                alertsForm.setOldCrmNpMobileList( oldCrmNpMobilePojos );
            }
            alertsForm.setCrmNpMobileList( crmNpMobilePojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while addUserAlertTemplatePage", ex );
        }
        return inMapping.findForward( IActionForwardConstant.USER_ALERT_TEMPLATE );
    }

    public ActionForward deleteUserAlertMobileEmailRow( ActionMapping inMapping,
                                                        ActionForm inForm,
                                                        HttpServletRequest inRequest,
                                                        HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            AlertsForm alertsForm = (AlertsForm) inForm;
            LOGGER.info( "Row counter" + alertsForm.getRowCounter() );
            alertsForm.getCrmNpMobileList().remove( alertsForm.getRowCounter() );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.info( "Error in deleteUserAlertMobileEmailRow Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.USER_ALERT_TEMPLATE );
    }

    public ActionForward addUserAlertMobileEmailNewRow( final ActionMapping inMapping,
                                                        final ActionForm inForm,
                                                        final HttpServletRequest inRequest,
                                                        final HttpServletResponse inResponse )
        throws Exception
    {
        AlertsForm alertsForm = (AlertsForm) inForm;
        if ( !StringUtils.isValidObj( alertsForm.getCrmNpMobileList() ) )
        {
            alertsForm.setCrmNpMobileList( new ArrayList<CrmNpMobilePojo>() );
        }
        CrmNpMobilePojo pojo = new CrmNpMobilePojo();
        pojo.setEditable( true );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        alertsForm.getCrmNpMobileList().add( 0, pojo );
        return inMapping.findForward( IActionForwardConstant.USER_ALERT_TEMPLATE );
    }

    public ActionForward addUserAlertDetails( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        AlertsForm alertsForm = (AlertsForm) inForm;
        String forwardPage = IActionForwardConstant.ADD_USER_ALERT_REDIRECT;
        try
        {
            if ( !AlertFormHelper.validateUserAlertMobile( errors, alertsForm ).isEmpty() )
            {
                LOGGER.info( "Forward Search page" );
                forwardPage = IActionForwardConstant.USER_ALERT_TEMPLATE;
            }
            else if ( !AlertFormHelper.validateUserAlertEmail( errors, alertsForm ).isEmpty() )
            {
                LOGGER.info( "Forward Search page" );
                forwardPage = IActionForwardConstant.USER_ALERT_TEMPLATE;
            }
            else
            {
                if ( CommonValidator.isValidCollection( alertsForm.getCrmNpMobileList() ) )
                {
                    MasterDataDto masterDto = getMasterDataBm().addUserAlertDetails( alertsForm );
                    String statusCode = masterDto.getStatusCode();
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    }
                    else
                    {
                        LOGGER.info( "Response status  desc::" + masterDto.getStatusDesc()
                                + "Response status code  :: " + statusCode );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_REQUIRED ) );
                    forwardPage = IActionForwardConstant.USER_ALERT_TEMPLATE;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while saving User Alert", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public IEventTemplateMapping getEventTemplateMapping()
    {
        return eventTemplateMapping;
    }

    public void setEventTemplateMapping( IEventTemplateMapping inEventTemplateMapping )
    {
        eventTemplateMapping = inEventTemplateMapping;
    }
}
