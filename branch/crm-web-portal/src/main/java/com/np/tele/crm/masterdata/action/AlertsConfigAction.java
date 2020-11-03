package com.np.tele.crm.masterdata.action;

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

import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.MasterForm;
import com.np.tele.crm.service.client.AlertMasterPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.StringUtils;

public class AlertsConfigAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( AlertsConfigAction.class );
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

    public ActionForward createAlertsConfigPage( ActionMapping inMapping,
                                                 ActionForm inForm,
                                                 HttpServletRequest inRequest,
                                                 HttpServletResponse inResponse )
    {
        MasterForm masterForm = (MasterForm) inForm;
        AlertMasterPojo alertMasterPojo = getCrmConfigManager().getAlertMasterPojo();
        if ( StringUtils.isValidObj( alertMasterPojo ) )
        {
            alertMasterPojo.setCategory( CRMParameter.ALERT.getParameter() );
            alertMasterPojo.setSubCategory( CRMParameter.MASTER.getParameter() );
        }
        masterForm.setAlertMasterPojo( alertMasterPojo );
        return inMapping.findForward( IActionForwardConstant.ALERTS_CONFIG );
    }

    public ActionForward createAlertsConfig( ActionMapping inMapping,
                                             ActionForm inForm,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        MasterForm masterForm = (MasterForm) inForm;
        MasterDataDto masterDataDto = new MasterDataDto();
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        HttpSession session = request.getSession( false );
        try
        {
            CrmuserDetailsDto crmuserDetailsDto = (CrmuserDetailsDto) session
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String userId = crmuserDetailsDto.getCrmUserDetailsPojo().getUserId();
            masterDataDto.setUserID( userId );
            if ( StringUtils.isValidObj( masterForm.getAlertMasterPojo() ) )
            {
                masterForm.getAlertMasterPojo().setCategory( CRMParameter.ALERT.getParameter() );
                masterForm.getAlertMasterPojo().setSubCategory( CRMParameter.MASTER.getParameter() );
                AlertMasterPojo alertMasterPojo = getCrmConfigManager().getAlertMasterPojo();
                if ( StringUtils.isValidObj( alertMasterPojo ) )
                {
                    PojoComparator<AlertMasterPojo> comparator = new PojoComparator<AlertMasterPojo>();
                    if ( comparator.compare( alertMasterPojo, masterForm.getAlertMasterPojo() ) == 0 )
                    {
                        masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    }
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.setAlertMasterPojo( masterForm.getAlertMasterPojo() );
                masterDataDto = getMasterBMngr().createMasterData( ServiceParameter.CREATE.getParameter(),
                                                                   CRMParameter.ALERTS_MASTER.getParameter(),
                                                                   masterDataDto );
            }
            if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured createAlertsConfig", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return inMapping.findForward( IActionForwardConstant.ALERTS_CONFIG );
    }
}
