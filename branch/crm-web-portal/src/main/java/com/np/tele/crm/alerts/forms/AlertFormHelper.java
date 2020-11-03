package com.np.tele.crm.alerts.forms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.service.client.EventTemplatesPojo;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public final class AlertFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( AlertFormHelper.class );

    public static void resetSmsList( String inMethodName, List<SmsTemplatePojo> inSmsTemplates )
    {
        if ( StringUtils.equals( "newSMSTemplate", inMethodName )
                || StringUtils.equals( "newEmailTemplate", inMethodName )
                || StringUtils.equals( "viewSMSTemplate", inMethodName )
                || StringUtils.equals( "searchAndViewEmailTemplate", inMethodName )
                || StringUtils.equals( "searchTemplate", inMethodName ) )
        {
            if ( StringUtils.isValidObj( inSmsTemplates ) && !inSmsTemplates.isEmpty() )
            {
                inSmsTemplates.clear();
            }
        }
    }

    public static void resetEmailList( String inMethodName, List<EmailTemplatePojo> inEmailTemplates )
    {
        if ( StringUtils.equals( "newSMSTemplate", inMethodName )
                || StringUtils.equals( "newEmailTemplate", inMethodName )
                || StringUtils.equals( "viewSMSTemplate", inMethodName )
                || StringUtils.equals( "searchAndViewEmailTemplate", inMethodName )
                || StringUtils.equals( "searchTemplate", inMethodName ) )
        {
            if ( StringUtils.isValidObj( inEmailTemplates ) && !inEmailTemplates.isEmpty() )
            {
                inEmailTemplates.clear();
            }
        }
    }

    public static void resetTemplates( String inMethodName, AlertsForm inAlertsForm )
    {
        if ( StringUtils.equals( "templateMappingPg", inMethodName )
                || StringUtils.equals( "addTemplateMappingRow", inMethodName )
                || StringUtils.equals( "createEventTemplateMapping", inMethodName ) )
        {
            if ( StringUtils.isValidObj( inAlertsForm.getEvntTemplateList() ) )
            {
                for ( EventTemplatesPojo pojo : inAlertsForm.getEvntTemplateList() )
                {
                    pojo.setEditable( false );
                    pojo.setSmsEnabled( false );
                    pojo.setEmailEnabled( false );
                }
            }
        }
        if ( StringUtils.equals( "newSMSTemplate", inMethodName )
                || StringUtils.equals( "newEmailTemplate", inMethodName )
                || StringUtils.equals( "searchTemplate", inMethodName ) )
        {
            inAlertsForm.setTemplateName( null );
            inAlertsForm.setEmailSubject( null );
            inAlertsForm.setEmailCc( null );
            inAlertsForm.setEmailBcc( null );
            inAlertsForm.setEmailFrom( null );
            inAlertsForm.setSmsTemplateBody( null );
            inAlertsForm.setEmailTemplateBody( null );
            inAlertsForm.setEmailServer( null );
            inAlertsForm.setProjectType( null );
            inAlertsForm.setProjectId( 0 );
            inAlertsForm.setProjectName( null );
            inAlertsForm.setSmsType( null );
            inAlertsForm.setSmsGateway( null );
            inAlertsForm.setTemplateType( null );
            inAlertsForm.setParameterList( null );
            inAlertsForm.setStatus( null );
            inAlertsForm.setProjects( null );
            inAlertsForm.setSmsTemplatePojo( null );
            inAlertsForm.setEmailTemplatePojo( null );
        }
        if ( StringUtils.equals( "createEmailTemplate", inMethodName )
                || StringUtils.equals( "createSMSTemplate", inMethodName ) )
        {
            inAlertsForm.setProjectType( "" );
            inAlertsForm.setProjectId( 0 );
            inAlertsForm.setProjectName( null );
        }
    }

    public static void validateEventTemplates( String inMethod, ActionErrors inActionError, AlertsForm inAlertsForm )
    {
        LOGGER.info( "validateEventTemplates" );
        if ( StringUtils.equals( "createEventTemplateMapping", inMethod ) )
        {
            if ( StringUtils.isValidObj( inAlertsForm.getEvntTemplateList() ) )
            {
                boolean toUpdate = false;
                for ( EventTemplatesPojo eventTemplatesPojo : inAlertsForm.getEvntTemplateList() )
                {
                    LOGGER.info( "Editable :" + eventTemplatesPojo.isEditable() );
                    if ( eventTemplatesPojo.isEditable() )
                    {
                        toUpdate = true;
                        break;
                    }
                }
                if ( !toUpdate )
                {
                    inActionError.add( "eventTemplateEditable",
                                       new ActionMessage( IPropertiesConstant.ERROR_EVENT_TEMPLATE_EDITABLE ) );
                }
                if ( toUpdate )
                {
                    Set<EventTemplatesPojo> eventTemplateSet = new HashSet<EventTemplatesPojo>();
                    eventTemplateSet.addAll( inAlertsForm.getEvntTemplateList() );
                    if ( eventTemplateSet.size() < inAlertsForm.getEvntTemplateList().size() )
                    {
                        inActionError.add( "eventTemplateEditable",
                                           new ActionMessage( IPropertiesConstant.ERROR_EVENT_TEMPLATE_DUPLICATE ) );
                    }
                }
            }
        }
    }

    public static ActionMessages validateUserAlertMobile( ActionMessages inErrors, AlertsForm inAlertsForm )
    {
        LOGGER.info( "METHOD_ADD_USER_ALERT_DETAILS" );
        Set<Long> crmNpMobilePojos = new HashSet<Long>();
        if ( CommonValidator.isValidCollection( inAlertsForm.getCrmNpMobileList() ) )
        {
            for ( CrmNpMobilePojo pojo : inAlertsForm.getCrmNpMobileList() )
            {
                LOGGER.info( "METHOD_ADD_USER_ALERT_DETAILS::" + pojo.getMobileNo() );
                if ( pojo.getMobileNo() > 0 )
                {
                    String mobileNum = String.valueOf( pojo.getMobileNo() );
                    String startNos = ValidationUtil.validateInputMobile( mobileNum );
                    if ( mobileNum.length() != FileHeaderConstants.LMS2.getSize() )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_MOBILE_LENGTH ) );
                        break;
                    }
                    else if ( !StringUtils.isEmpty( startNos ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
                        break;
                    }
                    else if ( !crmNpMobilePojos.add( pojo.getMobileNo() ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                         pojo.getMobileNo(),
                                                         "Mass Outage Event" ) );
                        break;
                    }
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_REQUIRED ) );
                    break;
                }
            }
        }
        return inErrors;
    }

    public static ActionMessages validateUserAlertEmail( ActionMessages inErrors, AlertsForm inAlertsForm )
    {
        LOGGER.info( "METHOD_ADD_USER_ALERT_DETAILS" );
        Set<String> crmNpMobilePojos = new HashSet<String>();
        if ( CommonValidator.isValidCollection( inAlertsForm.getCrmNpMobileList() ) )
        {
            for ( CrmNpMobilePojo pojo : inAlertsForm.getCrmNpMobileList() )
            {
                LOGGER.info( "METHOD_ADD_USER_ALERT_DETAILS::" + pojo.getEmailId() );
                if ( StringUtils.isEmpty( pojo.getEmailId() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_EMAIL_ID_REQUIRED ) );
                    break;
                }
                else if ( !StringUtils.isValidEmailID( pojo.getEmailId() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_EMAIL_ID_PATTERN ) );
                    break;
                }
                else if ( !crmNpMobilePojos.add( pojo.getEmailId() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                                              pojo.getEmailId(),
                                                                              "Mass Outage Event" ) );
                    break;
                }
            }
        }
        return inErrors;
    }
}
