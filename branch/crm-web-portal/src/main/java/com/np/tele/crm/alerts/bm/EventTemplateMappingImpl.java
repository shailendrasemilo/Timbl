package com.np.tele.crm.alerts.bm;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.EventTemplatesPojo;

@AppMonitor
public class EventTemplateMappingImpl
    implements IEventTemplateMapping
{
    private static final Logger LOGGER          = Logger.getLogger( EventTemplateMappingImpl.class );
    private CRMAlertsService    crmAlertsClient = null;

    public CRMAlertsService getCrmAlertsClient()
    {
        return crmAlertsClient;
    }

    public void setCrmAlertsClient( CRMAlertsService crmAlertsClient )
    {
        this.crmAlertsClient = crmAlertsClient;
    }

    @Override
    public AlertsDto eventTemplateMappingList( AlertsDto inAlertsDto )
    {
        try
        {
            LOGGER.info( "Getting all event template list" );
            inAlertsDto = getCrmAlertsClient().eventTemplate( ServiceParameter.LIST.getParameter(), inAlertsDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in EventTemplateMappingImpl  eventTemplateMappingList method" + ex );
        }
        return inAlertsDto;
    }

    @Override
    public AlertsDto createEventTemplateMapping( AlertsForm inAlertsForm, String inUserId )
    {
        AlertsDto inAlertsDto = null;
        List<EventTemplatesPojo> evTempalteList = null;
        try
        {
            LOGGER.info( "EventTemplateMappingImpl : createEventTemplateMapping method" );
            inAlertsDto = new AlertsDto();
            evTempalteList = new ArrayList<EventTemplatesPojo>();
            for ( EventTemplatesPojo eventTemplatesPojo : inAlertsForm.getEvntTemplateList() )
            {
                if ( eventTemplatesPojo.isEditable() )
                {
                    if(eventTemplatesPojo.getEventTemplateId()>0){
                        eventTemplatesPojo.setLastModifiedBy( inUserId ) ;
                    }
                    eventTemplatesPojo.setCreatedBy( inUserId );
                    evTempalteList.add( eventTemplatesPojo );
                }
            }
            if ( !evTempalteList.isEmpty() )
            {
                inAlertsDto.getEvTempalteList().addAll( evTempalteList );
                LOGGER.info( "size of event template mapping List : " + inAlertsForm.getEvntTemplateList().size() );
                inAlertsDto = getCrmAlertsClient().eventTemplate( ServiceParameter.CREATE.getParameter(), inAlertsDto );
            }
            else
            {
                inAlertsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in EventTemplateMappingImpl  eventTemplateMappingList method" + ex );
        }
        return inAlertsDto;
    }

    @Override
    public AlertsDto templateMappedWithEvent( AlertsDto inAlertsDto )
    {
        try
        {
            LOGGER.info( "EventTemplateMappingImpl : templateMappedWithEvent method" );
            inAlertsDto = getCrmAlertsClient().eventTemplate( ServiceParameter.VIEW.getParameter(), inAlertsDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in EventTemplateMappingImpl  templateMappedWithEvent method" + ex );
        }
        return inAlertsDto;
    }
}
