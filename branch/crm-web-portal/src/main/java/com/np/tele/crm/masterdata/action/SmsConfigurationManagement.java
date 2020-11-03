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
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SmsGatewayPojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.BeanUtils;
import com.np.tele.crm.utils.StringUtils;

public class SmsConfigurationManagement
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( SmsConfigurationManagement.class );
    private IMasterBMngr        masterDataBm;
    private ICrmConfigManager   crmConfigManager;

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager crmConfigManager )
    {
        this.crmConfigManager = crmConfigManager;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public ActionForward smsConfigurationPage( ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response )
    {
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        try
        {
            MasterForm masterForm = (MasterForm) form;
            SmsGatewayPojo smsGatewayPojo = new SmsGatewayPojo();
            smsGatewayPojo.setCategory( CRMParameter.SMS.getParameter() );
            smsGatewayPojo.setEnable( false );
            masterForm.setSmsGatewayPojo( smsGatewayPojo );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( IActionForwardConstant.SMS_COFIGURATION_PAGE );
    }

    public ActionForward searchSMSGateway( ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response )
    {
        MasterForm masterForm = (MasterForm) form;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        try
        {
            List<SmsGatewayPojo> smsGateWayList = getCrmConfigManager().getSmsGateways();
            LOGGER.info( "smsGateWayList" + smsGateWayList.size() );
            if ( !smsGateWayList.isEmpty() )
            {
                LOGGER.info( "Searh searchSMSGateway" + smsGateWayList.size() );
                masterForm.setSmsGateWayList( smsGateWayList );
                LOGGER.info( "Searh searchSMSGateway" + masterForm.getSmsGateWayList().size() );
            }
            else
            {
                errors.add( IAppConstants.NO_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( IActionForwardConstant.SEARCH_SMS_CONFIGURATION_PAGE );
    }

    public ActionForward smsConfigurationOperation( ActionMapping mapping,
                                                    ActionForm form,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response )
    {
        MasterForm masterForm = (MasterForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        MasterDataDto masterDataDto = new MasterDataDto();
        String forwardPage = IActionForwardConstant.SMS_COFIGURATION_PAGE;
        try
        {
            LOGGER.info( "smsConfigurationOperation called......." );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( masterForm.getOldSmsGatewayPojo() ) )
            {
                PojoComparator<SmsGatewayPojo> comparator = new PojoComparator<SmsGatewayPojo>();
                if ( comparator.compare( masterForm.getOldSmsGatewayPojo(), masterForm.getSmsGatewayPojo() ) == 0 )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto = getMasterDataBm().smsConfigurationOperation( masterForm,
                                                                             userDto.getCrmUserDetailsPojo()
                                                                                     .getUserId(),
                                                                             ServiceParameter.CREATE.getParameter() );
            }
            if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( masterDataDto.getStatusCode() ) );
                forwardPage = IActionForwardConstant.SEARCH_SMS_CONFIG;
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.info( "error occured while sms configuration ::", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward updateSMSGatewayPage( ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response )
    {
        MasterForm masterForm = (MasterForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            List<SmsGatewayPojo> smsGatewayList = masterForm.getSmsGateWayList();
            SmsGatewayPojo smsGatewayPojo = new SmsGatewayPojo();
            smsGatewayPojo.setAlias( masterForm.getSmsAlias() );
            LOGGER.info( "Master Form alias value........" + masterForm.getSmsAlias() );
            int pojoIndex = smsGatewayList.indexOf( smsGatewayPojo );
            if ( pojoIndex >= 0 )
            {
                smsGatewayPojo = smsGatewayList.get( pojoIndex );
                masterForm.setSmsGatewayPojo( smsGatewayPojo );
                masterForm.setOldSmsGatewayPojo( BeanUtils.getTemporaryPojo( smsGatewayPojo ) );
            }
            else
            {
                masterForm.setSmsGatewayPojo( smsGatewayPojo );
            }
            LOGGER.info( "Pojo index" + pojoIndex );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.info( "Error Occured", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.SMS_COFIGURATION_PAGE );
    }
}
