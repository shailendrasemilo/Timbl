package com.np.tele.crm.masterdata.action;

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

import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.MasterForm;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.usrmngmnt.action.UserManagementAction;
import com.np.tele.crm.utils.BeanUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class EmailServerConfigAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( UserManagementAction.class );
    private ICrmConfigManager   crmConfigManager;
    private IMasterBMngr        masterBMngr;

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ActionForward createEmailServerPage( ActionMapping mapping,
                                                ActionForm inForm,
                                                HttpServletRequest request,
                                                HttpServletResponse response )
        throws Exception
    {
        MasterForm masterForm = (MasterForm) inForm;
        EmailServerPojo emailServerPojo = new EmailServerPojo();
        emailServerPojo.setCategory( "EMAIL" );
        emailServerPojo.setAuth( false );
        emailServerPojo.setEnable( false );
        masterForm.setEmailServerPojo( emailServerPojo );
        return mapping.findForward( IActionForwardConstant.CREATE_EMAIL_SERVER );
    }

    public ActionForward createEmailServer( ActionMapping mapping,
                                            ActionForm inForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
    {
        MasterForm masterForm = (MasterForm) inForm;
        MasterDataDto masterDataDto = new MasterDataDto();
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.CREATE_EMAIL_SERVER;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        try
        {
            masterDataDto.setUserID( userDto.getCrmUserDetailsPojo().getUserId() );
            if ( StringUtils.isValidObj( masterForm.getOldEmailServerPojo() ) )
            {
                PojoComparator<EmailServerPojo> comparator = new PojoComparator<EmailServerPojo>();
                if ( comparator.compare( masterForm.getOldEmailServerPojo(), masterForm.getEmailServerPojo() ) == 0 )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.setEmailServerPojo( masterForm.getEmailServerPojo() );
                masterDataDto = masterBMngr.createMasterData( ServiceParameter.CREATE.getParameter(),
                                                              CRMParameter.EMAIL.getParameter(), masterDataDto );
            }
            if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( IPropertiesConstant.SUCCESS_EMAIL_SERVER ) );
                forwardPage = IActionForwardConstant.SEARCH_EMAIL_ACTION;
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.info( "error occured while Email server configuration ::", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward searchEmailServerPage( ActionMapping mapping,
                                                ActionForm inForm,
                                                HttpServletRequest request,
                                                HttpServletResponse response )
    {
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        MasterForm masterForm = (MasterForm) inForm;
        List<EmailServerPojo> emailServerPojos = crmConfigManager.getEmailServers();
        LOGGER.info( "size of EMAIL SERVER SEARCH POJO:" + emailServerPojos.size() );
        if ( CommonValidator.isValidCollection( emailServerPojos ) )
        {
            masterForm.setSearchEmailServerList( emailServerPojos );
        }
        else
        {
            errors.add( IAppConstants.NO_RECORD_FOUND, new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
        }
        LOGGER.info( "size of EMAIL SERVER SEARCH POJO AFTER:" + masterForm.getSearchEmailServerList().size() );
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.SEARCH_EMAIL_SERVER );
    }

    public ActionForward editEmailServerPage( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
        throws Exception
    {
        MasterForm masterForm = (MasterForm) inForm;
        EmailServerPojo emailServerPojo = new EmailServerPojo();
        List<EmailServerPojo> emailServerPojos = masterForm.getSearchEmailServerList();
        if ( CommonValidator.isValidCollection( emailServerPojos ) )
        {
            emailServerPojo.setAlias( masterForm.getMasterAlias() );
            int index = emailServerPojos.indexOf( emailServerPojo );
            if ( index >= IAppConstants.ZERO )
            {
                LOGGER.info( "Index not zero" );
                emailServerPojo = emailServerPojos.get( index );
                masterForm.setEmailServerPojo( emailServerPojo );
                masterForm.setOldEmailServerPojo( BeanUtils.getTemporaryPojo( emailServerPojo ) );
            }
            LOGGER.info( "Pojo index ::" + index );
        }
        return inMapping.findForward( IActionForwardConstant.CREATE_EMAIL_SERVER );
    }
}