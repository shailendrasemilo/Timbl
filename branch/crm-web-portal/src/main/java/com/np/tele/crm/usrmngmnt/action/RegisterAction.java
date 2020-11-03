package com.np.tele.crm.usrmngmnt.action;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.CrmUserAssociationPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class RegisterAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( RegisterAction.class );
    private IUserBMngr          usermngmntbm;
    private IMasterBMngr        masterDataBm;

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr usermngmntbm )
    {
        this.usermngmntbm = usermngmntbm;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public ActionForward newUserForward( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
    {
        LoginForm loginForm = (LoginForm) form;
        loginForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        loginForm.setProductList( CRMUtils.getProducts() );
        loginForm.setPartnerList( CRMCacheManager.getActivePartnerList() );
        return mapping.findForward( IActionForwardConstant.CREATE_USER );
    }

    public ActionForward registerNewUser( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.CREATE_USER;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        List<String> serviceNames = null;
        if ( StringUtils.isValidObj( loginForm.getServiceNameArray() ) && loginForm.getServiceNameArray().length > 0 )
        {
            serviceNames = Arrays.asList( loginForm.getServiceNameArray() );
        }
        List<String> partners = null;
        if ( StringUtils.isValidObj( loginForm.getPartnerNameArray() ) && loginForm.getPartnerNameArray().length > 0 )
        {
            partners = Arrays.asList( loginForm.getPartnerNameArray() );
        }
        List<CrmUserAssociationPojo> associationPojos = new ArrayList<CrmUserAssociationPojo>();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( userDto ) )
            {
                if ( CommonValidator.isValidCollection( serviceNames ) )
                {
                    for ( String service : serviceNames )
                    {
                        CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                        crmUserAssociationPojo.setAssociatedValue( service );
                        crmUserAssociationPojo.setAssociationType( "SN" );
                        crmUserAssociationPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                        crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        associationPojos.add( crmUserAssociationPojo );
                    }
                }
                if ( CommonValidator.isValidCollection( partners ) )
                {
                    for ( String partner : partners )
                    {
                        CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                        crmUserAssociationPojo.setAssociatedValue( partner );
                        crmUserAssociationPojo.setAssociationType( "PT" );
                        crmUserAssociationPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                        crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        associationPojos.add( crmUserAssociationPojo );
                    }
                }
                loginForm.setAssociationPojos( associationPojos );
                CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().userOperation( loginForm,
                                                                                       ServiceParameter.CREATE
                                                                                               .getParameter(),
                                                                                       userDto.getCrmUserDetailsPojo()
                                                                                               .getUserId() );
                if ( StringUtils.isValidObj( crmUserDetailsDto ) )
                {
                    String statusCode = crmUserDetailsDto.getStatusCode();
                    if ( !StringUtils.isEmpty( statusCode ) )
                    {
                        if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                        {
                            String userId = crmUserDetailsDto.getCrmUserDetailsPojo().getUserId();
                            LOGGER.info( "UserId........" + userId );
                            loginForm.setUserId( userId );
                            HttpSession session = request.getSession( false );
                            session.setAttribute( "USERID", loginForm.getUserId() );
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.REGISTER_SUCCESS, userId ) );
                            forwardPage = IActionForwardConstant.SEARCH_USER_BY_ID_PAGE;
                        }
                        else
                        {
                            LOGGER.info( "Status code in else" + statusCode );
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status code null found" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                    }
                }
                else
                {
                    LOGGER.info( "Get Value NULL for crmuserdetailsdto from business manager" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                }
            }
            else
            {
                LOGGER.info( "In userDto session value null else block" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in register action", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }
}
