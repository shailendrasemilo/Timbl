package com.np.tele.crm.alerts.action;

import java.util.ArrayList;
import java.util.Collections;
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

import com.np.tele.crm.alerts.bm.IEventTemplateMapping;
import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.service.client.EventTemplatesPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;

public class TemplateMappingAction
    extends DispatchAction
{
    private static final Logger LOGGER               = Logger.getLogger( TemplateMappingAction.class );
    IEventTemplateMapping       eventTemplateMapping = null;

    public IEventTemplateMapping getEventTemplateMapping()
    {
        return eventTemplateMapping;
    }

    public void setEventTemplateMapping( IEventTemplateMapping inEventTemplateMapping )
    {
        eventTemplateMapping = inEventTemplateMapping;
    }

    public ActionForward templateMappingPg( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "Entering TemplateMappingAction : templateMappingPg" );
        AlertsForm alertsForm = (AlertsForm) inForm;
        AlertsDto alertsDto = new AlertsDto();
        try
        {
            // For Mapping Lists
            alertsDto = getEventTemplateMapping().templateMappedWithEvent( alertsDto );
            alertsForm.setEvntTemplateList( alertsDto.getEvTempalteList() );
            LOGGER.info( "size of event template list : " + alertsDto.getEvTempalteList().size() );
            // For All type of Lists
            alertsDto = getEventTemplateMapping().eventTemplateMappingList( alertsDto );
            alertsForm.setEventList( alertsDto.getEventsPojoList() );
            SortingComparator<EventsPojo> sortComparator = new SortingComparator<EventsPojo>( "eventDescription" );
            Collections.sort( alertsForm.getEventList(), sortComparator );
            sortComparator = null;
            SortingComparator<SmsTemplatePojo> sortComparator1 = new SortingComparator<SmsTemplatePojo>( "smsTemplateName" );
            Collections.sort( alertsDto.getSmsTemplatePojos(), sortComparator1 );
            sortComparator1 = null;
            alertsForm.setSmsTemplateList( alertsDto.getSmsTemplatePojos() );
            SortingComparator<EmailTemplatePojo> sortComparator2 = new SortingComparator<EmailTemplatePojo>( "emailTemplateName" );
            Collections.sort( alertsDto.getEmailTemplatePojos(), sortComparator2 );
            sortComparator2 = null;
            alertsForm.setEmailTemplateList( alertsDto.getEmailTemplatePojos() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured while getting record" + ex );
        }
        return inMapping.findForward( IActionForwardConstant.EVENT_TEMPLATE_MAPPING_PAGE );
    }

    public ActionForward addTemplateMappingRow( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
        throws Exception
    {
        EventTemplatesPojo eTemplatesPojo = null;
        List<EventTemplatesPojo> evntTemplatePojos = null;
        ActionMessages errors = new ActionMessages();
        try
        {
            AlertsForm alertsForm = (AlertsForm) inForm;
            if ( StringUtils.isValidObj( alertsForm.getEventList() ) && !alertsForm.getEventList().isEmpty() )
            {
                if ( !StringUtils.isValidObj( alertsForm.getEvntTemplateList() ) )
                {
                    alertsForm.setEvntTemplateList( new ArrayList<EventTemplatesPojo>() );
                }
                evntTemplatePojos = alertsForm.getEvntTemplateList();
                for ( EventTemplatesPojo pojo : evntTemplatePojos )
                {
                    if ( alertsForm.getEventList().contains( pojo.getEvents() ) && pojo.getEventTemplateId() > 0 )
                    {
                        alertsForm.getEventList().remove( pojo.getEvents() );
                        LOGGER.info( "Size OF Event LIst" + alertsForm.getEventList().size() );
                    }
                }
                if ( alertsForm.getEventList().size() > 0 )
                {
                    eTemplatesPojo = new EventTemplatesPojo();
                    eTemplatesPojo.setEditable( true );
                    eTemplatesPojo.setEvents( new EventsPojo() );
                    eTemplatesPojo.setSmsTemplate( new SmsTemplatePojo() );
                    eTemplatesPojo.setEmailTemplate( new EmailTemplatePojo() );
                    evntTemplatePojos.add( 0, eTemplatesPojo );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.NO_EVENT_FOUND ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured while add mapping" + ex.getMessage() );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.EVENT_TEMPLATE_MAPPING_PAGE );
    }

    public ActionForward deleteTemplateMappingRow( ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
    {
        AlertsForm alertsForm = (AlertsForm) inForm;
        LOGGER.info( "Index ::" + alertsForm.getRowCounter() );
        LOGGER.info( "size before remove :: " + alertsForm.getEvntTemplateList().size() );
        alertsForm.getEvntTemplateList().remove( alertsForm.getRowCounter() );
        LOGGER.info( "size after remove :: " + alertsForm.getEvntTemplateList().size() );
        return inMapping.findForward( IActionForwardConstant.EVENT_TEMPLATE_MAPPING_PAGE );
    }

    public ActionForward createEventTemplateMapping( ActionMapping inMapping,
                                                     ActionForm inForm,
                                                     HttpServletRequest inRequest,
                                                     HttpServletResponse inResponse )
        throws Exception
    {
        AlertsDto alertsDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            AlertsForm alertsForm = (AlertsForm) inForm;
            LOGGER.info( "User Id :" + userDto.getCrmUserDetailsPojo().getUserId() );
            alertsDto = getEventTemplateMapping().createEventTemplateMapping( alertsForm,
                                                                              userDto.getCrmUserDetailsPojo()
                                                                                      .getUserId() );
            String statusCode = alertsDto.getStatusCode();
            LOGGER.info( "Status Code :" + statusCode );
            if ( null != statusCode )
            {
                forwardPage = IActionForwardConstant.EVENT_TEMPLATE_MAPPING_PAGE;
                alertsDto = getEventTemplateMapping().templateMappedWithEvent( alertsDto );
                alertsForm.setEvntTemplateList( alertsDto.getEvTempalteList() );
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    alertsDto = getEventTemplateMapping().templateMappedWithEvent( alertsDto );
                    alertsForm.setEvntTemplateList( alertsDto.getEvTempalteList() );
                    LOGGER.info( "template has been successfully map with event" );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    LOGGER.info( "Somthing has wrong in create event template mapping.... " );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                forwardPage = IActionForwardConstant.EVENT_TEMPLATE_MAPPING_PAGE;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while template mapping with event", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            forwardPage = IActionForwardConstant.ERROR;
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }
}
