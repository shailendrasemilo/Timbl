package com.np.tele.crm.inbox.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.inbox.bm.IInboxManager;
import com.np.tele.crm.inbox.form.InboxForm;
import com.np.tele.crm.pojos.AllInboxPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.service.client.CommonWorkflowPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class InboxAction
    extends DispatchAction
{
    private static final Logger LOGGER           = Logger.getLogger( InboxAction.class );
    private IInboxManager       inboxManagerImpl = null;
    private IQrcManager         qrcManagerBm     = null;

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    public IInboxManager getInboxManagerImpl()
    {
        return inboxManagerImpl;
    }

    public void setInboxManagerImpl( IInboxManager inInboxManagerImpl )
    {
        inboxManagerImpl = inInboxManagerImpl;
    }

    public ActionForward leadInbox( ActionMapping inMapping,
                                    ActionForm inForm,
                                    HttpServletRequest inRequest,
                                    HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = getMessages( inRequest );
        InboxForm inboxForm = (InboxForm) inForm;
        LOGGER.info( ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" );
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, null );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            inboxForm.setInboxSearchList( CRMUtils.getcustomerProfileSearchList( CRMRequestType.LEAD.getRequestCode(),
                                                                                 false ) );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            inboxForm.setInboxType( CRMRequestType.LEAD.getRequestCode() );
            getInboxData( configDto, inboxForm, inRequest );
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        inboxForm.setSearchMethod( "leadInbox" );
        saveMessages( inRequest, message );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    public ActionForward crfInbox( ActionMapping inMapping,
                                   ActionForm inForm,
                                   HttpServletRequest inRequest,
                                   HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = new ActionMessages();
        InboxForm inboxForm = (InboxForm) inForm;
        LOGGER.info( ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" );
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, null );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            inboxForm.setInboxSearchList( CRMUtils.getcustomerProfileSearchList( CRMRequestType.CAF.getRequestCode(),
                                                                                 false ) );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            inboxForm.setInboxType( CRMRequestType.CAF.getRequestCode() );
            getInboxData( configDto, inboxForm, inRequest );
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        HttpSession session = inRequest.getSession( false );
        String msg = (String) session.getAttribute( "SuccessMsg" );
        if ( StringUtils.isNotBlank( msg ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( CRMServiceCode.CRM001.getStatusCode() ) );
            session.removeAttribute( "SuccessMsg" );
        }
        inboxForm.setSearchMethod( "crfInbox" );
        saveMessages( inRequest, message );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    public ActionForward qrcInbox( ActionMapping inMapping,
                                   ActionForm inForm,
                                   HttpServletRequest inRequest,
                                   HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = getMessages( inRequest );
        InboxForm inboxForm = (InboxForm) inForm;
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, CRMRequestType.QRC.getRequestCode() );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            inboxForm.setInboxSearchList( CRMUtils.getcustomerProfileSearchList( CRMRequestType.QRC.getRequestCode(),
                                                                                 false ) );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            inboxForm.setInboxType( CRMRequestType.QRC.getRequestCode() );
            getInboxData( configDto, inboxForm, inRequest );
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        HttpSession session = inRequest.getSession( false );
        String msg = (String) session.getAttribute( "SuccessMsg" );
        if ( StringUtils.isNotBlank( msg ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( CRMServiceCode.CRM001.getStatusCode() ) );
            session.removeAttribute( "SuccessMsg" );
        }
        inboxForm.setSearchMethod( "qrcInbox" );
        saveMessages( inRequest, message );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    public ActionForward workflowInbox( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = getMessages( inRequest );
        InboxForm inboxForm = (InboxForm) inForm;
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, null );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            boolean toProcess = CommonUtils.isAllowed( inRequest.getSession( false ),
                                                       "view_qrc_adj,create_qrc_adj,update_qrc_adj,delete_qrc_adj" );
            if ( toProcess )
            {
                inboxForm.setInboxType( CRMRequestType.WAIVER.getRequestCode() );
                getInboxData( configDto, inboxForm, inRequest );
            }
            toProcess = CommonUtils
                    .isAllowed( inRequest.getSession( false ),
                                "view_qrc_shifting,create_qrc_shifting,update_qrc_shifting,delete_qrc_shifting" );
            if ( toProcess )
            {
                inboxForm.setInboxType( CRMRequestType.SHIFTING.getRequestCode() );
                getInboxData( configDto, inboxForm, inRequest );
            }
            inboxForm.setInboxSearchList( CRMUtils.getcustomerProfileSearchList( CRMParameter.WAIVER.getParameter(),
                                                                                 false ) );
            if ( !CommonValidator.isValidCollection( inboxForm.getWorkflowInboxList() ) )
            {
                inboxForm.setWorkflowInboxList( new ArrayList<CommonWorkflowPojo>() );
            }
            if ( !CommonValidator.isValidCollection( inboxForm.getWorkflowGroupInboxList() ) )
            {
                inboxForm.setWorkflowGroupInboxList( new ArrayList<CommonWorkflowPojo>() );
            }
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        HttpSession session = inRequest.getSession( false );
        String msg = (String) session.getAttribute( "SuccessMsg" );
        if ( StringUtils.isNotBlank( msg ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( CRMServiceCode.CRM001.getStatusCode() ) );
            session.removeAttribute( "SuccessMsg" );
        }
        saveMessages( inRequest, message );
        message = null;
        inboxForm.setSearchMethod( "workflowInbox" );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    public ActionForward freezeInbox( ActionMapping inMapping,
                                      ActionForm inForm,
                                      HttpServletRequest inRequest,
                                      HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = new ActionMessages();
        InboxForm inboxForm = (InboxForm) inForm;
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, null );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            inboxForm
                    .setInboxSearchList( CRMUtils.getcustomerProfileSearchList( CRMRequestType.FREEZE.getRequestCode(),
                                                                                false ) );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            inboxForm.setInboxType( CRMRequestType.FREEZE.getRequestCode() );
            getInboxData( configDto, inboxForm, inRequest );
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        HttpSession session = inRequest.getSession( false );
        String msg = (String) session.getAttribute( "SuccessMsg" );
        if ( StringUtils.isNotBlank( msg ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( CRMServiceCode.CRM001.getStatusCode() ) );
            session.removeAttribute( "SuccessMsg" );
        }
        inboxForm.setSearchMethod( "freezeInbox" );
        saveMessages( inRequest, message );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    public ActionForward allInbox( ActionMapping inMapping,
                                   ActionForm inForm,
                                   HttpServletRequest inRequest,
                                   HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages message = getMessages( inRequest );
        InboxForm inboxForm = (InboxForm) inForm;
        LOGGER.info( "all Inbox" );
        ConfigDto configDto = new ConfigDto();
        List<String> stages = retrieveUserBin( inRequest, configDto, CRMRequestType.QRC.getRequestCode() );
        if ( ValidationUtil.isValidCollection( stages ) )
        {
            configDto.getStages().addAll( stages );
            List<ContentPojo> pojos = new ArrayList<ContentPojo>();
            RolesPojo rolesPojo = (RolesPojo) inRequest.getSession().getAttribute( IAppConstants.CRM_ROLES );
            if ( StringUtils.equals( rolesPojo.getAvailable( "view_ina" ), "true" ) )
            {
                pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.CAF.getRequestCode(), false ) );
            }
            if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc" ), "true" )
                    || StringUtils.equals( rolesPojo.getAvailable( "view_qrc_tkt" ), "true" ) )
            {
                pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.QRC.getRequestCode(), false ) );
            }
            if ( StringUtils.equals( rolesPojo.getAvailable( "view_lead" ), "true" ) )
            {
                pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.LEAD.getRequestCode(), false ) );
            }
            if ( StringUtils.equals( rolesPojo.getAvailable( "view_freeze" ), "true" ) )
            {
                pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.FREEZE.getRequestCode(), false ) );
            }
            if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc_shifting" ), "true" )
                    || StringUtils.equals( rolesPojo.getAvailable( "view_qrc_adj" ), "true" ) )
            {
                pojos.addAll( CRMUtils.getcustomerProfileSearchList( "waiver", false ) );
            }
            Object[] newPojos = pojos.toArray();
            synchronized ( pojos )
            {
                for ( Object removeDuplicates : newPojos )
                {
                    if ( pojos.indexOf( removeDuplicates ) != pojos.lastIndexOf( removeDuplicates )
                            && pojos.indexOf( removeDuplicates ) >= 0 && pojos.lastIndexOf( removeDuplicates ) >= 0 )
                    {
                        pojos.remove( pojos.lastIndexOf( removeDuplicates ) );
                    }
                }
            }
            List<ContentPojo> allowedModeules = CRMUtils.getAllowedList( pojos );
            inboxForm.setInboxSearchList( allowedModeules );
            LOGGER.info( "Start capturing Inbox data for user-Id : " + configDto.getUserId() );
            inboxForm.setInboxType( CRMRequestType.ALL_INBOX.getRequestCode() );
            getInboxData( configDto, inboxForm, inRequest );
        }
        else
        {
            LOGGER.info( "No Functional Bin Found" );
        }
        HttpSession session = inRequest.getSession( false );
        String msg = (String) session.getAttribute( "SuccessMsg" );
        if ( StringUtils.isNotBlank( msg ) )
        {
            message.add( IAppConstants.APP_MESSAGE, new ActionMessage( CRMServiceCode.CRM001.getStatusCode() ) );
            session.removeAttribute( "SuccessMsg" );
        }
        saveMessages( inRequest, message );
        inboxForm.setSearchMethod( "allInbox" );
        return inMapping.findForward( IActionForwardConstant.INBOX );
    }

    private List<String> retrieveUserBin( HttpServletRequest inRequest, ConfigDto configDto, String inInboxType )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        configDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
        List<String> functionalBin = new ArrayList<String>();
        Set<String> stages = new HashSet<String>();
        if ( StringUtils.isNotEmpty( userDto.getCrmUserDetailsPojo().getFunctionalBin() ) )
        {
            LOGGER.info( "Functional Bin  ::::::::::" + userDto.getCrmUserDetailsPojo().getFunctionalBin() );
            functionalBin.addAll( Arrays.asList( StringUtils.split( userDto.getCrmUserDetailsPojo().getFunctionalBin(),
                                                                    "," ) ) );
            LOGGER.info( "Size of Functionl Bin :::: " + functionalBin.size() );
            List<CrmRcaReasonPojo> functionalBinCache = CRMCacheManager.getFunctionalBins();
            for ( String bin : functionalBin )
            {
                inner: for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBinCache )
                {
                    if ( StringUtils.equals( String.valueOf( crmRcaReasonPojo.getCategoryId() ), bin ) )
                    {
                        LOGGER.info( "Category ID:: " + crmRcaReasonPojo.getCategoryId() );
                        LOGGER.info( "User Bin ID:: " + bin );
                        CRMFunctionalBinStages functionBinStage = null;
                        if ( StringUtils.isNotBlank( crmRcaReasonPojo.getValueAlias() )
                                && !StringUtils.isNumeric( crmRcaReasonPojo.getValueAlias() ) )
                        {
                            functionBinStage = CRMFunctionalBinStages.getBinByStage( crmRcaReasonPojo.getValueAlias() );
                        }
                        if ( StringUtils.isValidObj( functionBinStage ) )
                        {
                            LOGGER.info( "Matched function bin stage with master stages"
                                    + functionBinStage.getSubStages() + " -- " + crmRcaReasonPojo.getValueAlias() );
                            stages.addAll( Arrays.asList( StringUtils.split( functionBinStage.getSubStages(), "," ) ) );
                        }
                        else
                        {
                            LOGGER.info( "Not Matched function bin stage:: " + crmRcaReasonPojo.getCategoryId() );
                            stages.add( String.valueOf( crmRcaReasonPojo.getCategoryId() ) );
                        }
                        break inner;
                    }
                }
            }
            if ( StringUtils.equals( CRMRequestType.QRC.getRequestCode(), inInboxType ) )
            {
                stages.addAll( functionalBin );
            }
            if ( CommonValidator.isValidCollection( userDto.getUserAssociatedServices() ) )
            {
                configDto.getUserAssociatedServices().addAll( userDto.getUserAssociatedServices() );
            }
            if ( CommonValidator.isValidCollection( userDto.getUserAssociatedPartners() ) )
            {
                configDto.getUserAssociatedPartners().addAll( userDto.getUserAssociatedPartners() );
            }
        }
        return new ArrayList<String>( stages );
    }

    //    public ActionForward changeInboxBin( ActionMapping inMapping,
    //                                         ActionForm inForm,
    //                                         HttpServletRequest inRequest,
    //                                         HttpServletResponse inResponse )
    //        throws Exception
    //    {
    //        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
    //                .getAttribute( IAppConstants.CRM_USER_OBJECT );
    //        String userId = userDto.getCrmUserDetailsPojo().getUserId();
    //        ConfigDto configDto = new ConfigDto();
    //        InboxForm inboxForm = (InboxForm) inForm;
    //        LOGGER.info( "Start changing lead owner on request user-Id : " + userId + " for lead : "
    //                + inboxForm.getLeadId() );
    //        configDto.setUserId( userId );
    //        if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.PERSONAL.getParameter() ) )
    //        {
    //            configDto.setFromUserId( userId );
    //            configDto.setToUserId( "" );
    //        }
    //        else if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.GROUP.getParameter() ) )
    //        {
    //            configDto.setFromUserId( "" );
    //            configDto.setToUserId( userId );
    //        }
    //        configDto.setMappingId( inboxForm.getLeadId() );
    //        configDto.setFromStage( inRequest.getParameter( "lmsStage" ) );
    //        configDto.setTostage( inRequest.getParameter( "lmsStage" ) );
    //        configDto = getInboxManagerImpl().changeInboxBin( configDto );
    //        configDto.getStages().add( inRequest.getParameter( "lmsStage" ) );
    //        getInboxData( configDto, inboxForm, inRequest );
    //        return inMapping.findForward( IActionForwardConstant.INBOX );
    //    }
    private void getInboxData( ConfigDto inConfigDto, InboxForm inInboxForm, HttpServletRequest inRequest )
    {
        boolean toSearch = false;
        if ( StringUtils.isNotBlank( inInboxForm.getSearchCriteria() )
                && StringUtils.isNotBlank( inInboxForm.getSearchCriteriaValue() ) )
        {
            LOGGER.info( "Criteria type::- " + inInboxForm.getSearchCriteria() + "Criteria Value::-"
                    + inInboxForm.getSearchCriteriaValue() );
            inConfigDto.setRequestType( inInboxForm.getSearchCriteria() );
            inConfigDto.setMappingId( inInboxForm.getSearchCriteriaValue() );
            toSearch = true;
        }
        LOGGER.info( "Going For Inbox Type :: " + inInboxForm.getInboxType() );
        inConfigDto.setInboxType( inInboxForm.getInboxType() );
        if ( StringUtils.equals( CRMRequestType.LEAD.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
            inInboxForm.setLmsInboxList( inConfigDto.getInboxLeadPojos() );
            inInboxForm.setLmsGroupInboxList( inConfigDto.getGroupInboxLeadPojos() );
            SortingComparator<LmsPojo> sorter = new SortingComparator<LmsPojo>( "createdTime" );
            Collections.sort( inInboxForm.getLmsInboxList(), sorter );
            Collections.sort( inInboxForm.getLmsGroupInboxList(), sorter );
            sorter = null;
        }
        else if ( StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
            inInboxForm.setCrfInboxList( inConfigDto.getInboxCRFPojos() );
            inInboxForm.setCrfGroupInboxList( inConfigDto.getGroupInboxCRFPojos() );
            SortingComparator<CrmCustomerDetailsPojo> sorter = new SortingComparator<CrmCustomerDetailsPojo>( "createdTime" );
            Collections.sort( inInboxForm.getCrfInboxList(), sorter );
            Collections.sort( inInboxForm.getCrfGroupInboxList(), sorter );
            sorter = null;
        }
        else if ( StringUtils.equals( CRMRequestType.FREEZE.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
            inInboxForm.setFreezeInboxList( inConfigDto.getInboxFreezePojos() );
            inInboxForm.setFreezeGroupInboxList( inConfigDto.getGroupInboxFreezePojos() );
            SortingComparator<CrmCustomerDetailsPojo> sorter = new SortingComparator<CrmCustomerDetailsPojo>( "createdTime" );
            Collections.sort( inInboxForm.getFreezeInboxList(), sorter );
            sorter = null;
        }
        else if ( StringUtils.equals( CRMRequestType.QRC.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
            getQrcManagerBm().setCategoriesNameById( inConfigDto.getInboxSrPojosPersonal() );
            getQrcManagerBm().setCategoriesNameById( inConfigDto.getInboxSrPojosGroup() );
            inInboxForm.setQrcInboxList( inConfigDto.getInboxSrPojosPersonal() );
            inInboxForm.setQrcGroupInboxList( inConfigDto.getInboxSrPojosGroup() );
            SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "lastModifiedTime" );
            Collections.sort( inInboxForm.getQrcInboxList(), Collections.reverseOrder( sorter ) );
            SortingComparator<CrmSrTicketsPojo> sorterGp = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
            Collections.sort( inInboxForm.getQrcGroupInboxList(), sorterGp );
            sorter = null;
        }
        else if ( StringUtils.equals( CRMRequestType.SHIFTING.getRequestCode(), inInboxForm.getInboxType() )
                || StringUtils.equals( CRMRequestType.WAIVER.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
            if ( StringUtils.equals( CRMRequestType.WAIVER.getRequestCode(), inInboxForm.getInboxType() ) )
            {
                if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowInboxList() ) )
                {
                    inInboxForm.getWorkflowInboxList().addAll( inConfigDto.getCommonWorkflowPojos() );
                }
                else
                    inInboxForm.setWorkflowInboxList( inConfigDto.getCommonWorkflowPojos() );
                if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowGroupInboxList() ) )
                {
                    inInboxForm.getWorkflowGroupInboxList().addAll( inConfigDto.getCommonWorkflowPojoGroup() );
                }
                else
                    inInboxForm.setWorkflowGroupInboxList( inConfigDto.getCommonWorkflowPojoGroup() );
            }
            if ( StringUtils.equals( CRMRequestType.SHIFTING.getRequestCode(), inInboxForm.getInboxType() ) )
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                String arr[] = userDto.getCrmUserDetailsPojo().getFunctionalBin().split( "," );
                List<String> bins = Arrays.asList( arr );
                if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowInboxList() ) )
                {
                    for ( CommonWorkflowPojo pojo : inConfigDto.getCommonWorkflowPojos() )
                    {
                        if ( bins.contains( pojo.getCurrentBin() ) )
                        {
                            inInboxForm.getWorkflowInboxList().add( pojo );
                        }
                    }
                }
                else
                {
                    inInboxForm.setWorkflowInboxList( new ArrayList<CommonWorkflowPojo>() );
                    for ( CommonWorkflowPojo pojo : inConfigDto.getCommonWorkflowPojos() )
                    {
                        if ( bins.contains( pojo.getCurrentBin() ) )
                        {
                            inInboxForm.getWorkflowInboxList().add( pojo );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowGroupInboxList() ) )
                {
                    for ( CommonWorkflowPojo pojo : inConfigDto.getCommonWorkflowPojoGroup() )
                    {
                        if ( bins.contains( pojo.getCurrentBin() ) )
                        {
                            inInboxForm.getWorkflowGroupInboxList().add( pojo );
                        }
                    }
                }
                else
                {
                    inInboxForm.setWorkflowGroupInboxList( new ArrayList<CommonWorkflowPojo>() );
                    for ( CommonWorkflowPojo pojo : inConfigDto.getCommonWorkflowPojoGroup() )
                    {
                        if ( bins.contains( pojo.getCurrentBin() ) )
                        {
                            inInboxForm.getWorkflowGroupInboxList().add( pojo );
                        }
                    }
                }
            }
            SortingComparator<CommonWorkflowPojo> sorter = new SortingComparator<CommonWorkflowPojo>( "createdTime" );
            if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowInboxList() ) )
            {
                Collections.sort( inInboxForm.getWorkflowInboxList(), sorter );
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowGroupInboxList() ) )
            {
                Collections.sort( inInboxForm.getWorkflowGroupInboxList(), sorter );
            }
            sorter = null;
        }
        else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.SERVICE_NAME.getCode() ) )
        {
            String serachValue = "";
            if ( StringUtils
                    .equals( inInboxForm.getSearchCriteriaValue(), CRMDisplayListConstants.BROADBAND.getValue() ) )
            {
                serachValue = CRMDisplayListConstants.BROADBAND.getCode();
            }
            else if ( StringUtils.equals( inInboxForm.getSearchCriteriaValue(),
                                          CRMDisplayListConstants.ETHERNET_ON_CABLE.getValue() ) )
            {
                serachValue = CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode();
            }
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllGroupInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllGroupInboxList() )
                {
                    if ( StringUtils.equals( allInboxPojo.getProduct(), serachValue ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllGroupInboxList( filterAllInboxList );
            }
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllInboxList() )
                {
                    if ( StringUtils.equals( allInboxPojo.getProduct(), serachValue ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllInboxList( filterAllInboxList );
            }
        }
        else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.NETWORK_PARTNER.getCode() ) )
        {
            List<String> searchValue = new ArrayList<String>();
            List<PartnerPojo> npPartnerList = CRMCacheManager
                    .getPartnerByPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
            String inputString = StringUtils.lowerCase( inInboxForm.getSearchCriteriaValue() );
            if ( CommonValidator.isValidCollection( npPartnerList ) )
            {
                for ( PartnerPojo p : npPartnerList )
                {
                    String partnerName = StringUtils.lowerCase( p.getPartnerName() );
                    if ( partnerName.contains( inputString ) )
                    {
                        searchValue.add( p.getPartnerId() + "" );
                    }
                }
            }
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllGroupInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllGroupInboxList() )
                {
                    if ( searchValue.contains( allInboxPojo.getNetworkPartner() ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllGroupInboxList( filterAllInboxList );
            }
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllInboxList() )
                {
                    if ( searchValue.contains( allInboxPojo.getNetworkPartner() ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllInboxList( filterAllInboxList );
            }
        }
        else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.SUB_SUB_CATEGORY.getCode() ) )
        {
            String serachValue = inInboxForm.getSearchCriteriaValue();
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllGroupInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllGroupInboxList() )
                {
                    if ( StringUtils.equals( allInboxPojo.getSubSubCategory(), serachValue ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllGroupInboxList( filterAllInboxList );
            }
            if ( ValidationUtil.isValidCollection( inInboxForm.getTempAllInboxList() ) )
            {
                List<AllInboxPojo> filterAllInboxList = new ArrayList<AllInboxPojo>();
                for ( AllInboxPojo allInboxPojo : inInboxForm.getTempAllInboxList() )
                {
                    if ( StringUtils.equals( allInboxPojo.getSubSubCategory(), serachValue ) )
                    {
                        filterAllInboxList.add( allInboxPojo );
                    }
                }
                inInboxForm.setAllInboxList( filterAllInboxList );
            }
        }
        else if ( StringUtils.equals( CRMRequestType.ALL_INBOX.getRequestCode(), inInboxForm.getInboxType() ) )
        {
            boolean processCrf = true;
            boolean processQrc = true;
            boolean processLead = true;
            if ( toSearch )
            {
                if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.CUSTOMER_ID.getCode() ) )
                {
                    processLead = false;
                }
                else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.CAF_ID.getCode() ) )
                {
                    processLead = false;
                }
                else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.LEAD_ID.getCode() ) )
                {
                    processCrf = false;
                    processQrc = false;
                }
                else if ( StringUtils.equals( inInboxForm.getSearchCriteria(), CustomerProfile.TICKET_ID.getCode() ) )
                {
                    processCrf = false;
                    processLead = false;
                }
            }
            boolean toProcess = CommonUtils.isAllowed( inRequest.getSession( false ),
                                                       "view_ina,create_ina,update_ina,delete_ina,view_freeze" );
            if ( toProcess && processCrf )
            {
                inConfigDto.setInboxType( CRMRequestType.CAF.getRequestCode() );
                inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
                inInboxForm.setCrfInboxList( inConfigDto.getInboxCRFPojos() );
                inInboxForm.setCrfGroupInboxList( inConfigDto.getGroupInboxCRFPojos() );
            }
            else
            {
                inInboxForm.setCrfInboxList( new ArrayList<CrmCustomerDetailsPojo>() );
                inInboxForm.setCrfGroupInboxList( new ArrayList<CrmCustomerDetailsPojo>() );
            }
            toProcess = CommonUtils.isAllowed( inRequest.getSession( false ),
                                               "view_lead,create_lead,update_lead,delete_lead" );
            if ( toProcess && processLead )
            {
                inConfigDto.setInboxType( CRMRequestType.LEAD.getRequestCode() );
                inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
                inInboxForm.setLmsInboxList( inConfigDto.getInboxLeadPojos() );
                inInboxForm.setLmsGroupInboxList( inConfigDto.getGroupInboxLeadPojos() );
            }
            else
            {
                inInboxForm.setLmsInboxList( new ArrayList<LmsPojo>() );
                inInboxForm.setLmsGroupInboxList( new ArrayList<LmsPojo>() );
            }
            toProcess = CommonUtils.isAllowed( inRequest.getSession( false ),
                                               "view_qrc_tkt,update_qrc_tkt,delete_qrc_tkt" );
            if ( toProcess && processQrc )
            {
                inConfigDto.setInboxType( CRMRequestType.QRC.getRequestCode() );
                inConfigDto = getInboxManagerImpl().getInboxData( inConfigDto );
                getQrcManagerBm().setCategoriesNameById( inConfigDto.getInboxSrPojosPersonal() );
                getQrcManagerBm().setCategoriesNameById( inConfigDto.getInboxSrPojosGroup() );
                inInboxForm.setQrcInboxList( inConfigDto.getInboxSrPojosPersonal() );
                inInboxForm.setQrcGroupInboxList( inConfigDto.getInboxSrPojosGroup() );
            }
            else
            {
                inInboxForm.setQrcInboxList( new ArrayList<CrmSrTicketsPojo>() );
                inInboxForm.setQrcGroupInboxList( new ArrayList<CrmSrTicketsPojo>() );
            }
            inInboxForm = getInboxManagerImpl().createAllInbox( inInboxForm );
            inInboxForm.setTempAllInboxList( inInboxForm.getAllInboxList() );
            inInboxForm.setTempAllGroupInboxList( inInboxForm.getAllGroupInboxList() );
            SortingComparator<AllInboxPojo> sorter = new SortingComparator<AllInboxPojo>( "lastModifiedTime" );
            Collections.sort( inInboxForm.getAllInboxList(), Collections.reverseOrder( sorter ) );
            sorter = null;
        }
        inInboxForm.setProducts( CRMUtils.getProducts() );
        //        inInboxForm.setLeadStages( CRMUtils.getAllStages() );
        inRequest.getSession( false ).setAttribute( IAppConstants.LMS_POLO_LIST, inConfigDto.getInboxLeadPojos() );
    }

    public ActionForward multipleChangeBinOwner( ActionMapping inMapping,
                                                 ActionForm inForm,
                                                 HttpServletRequest inRequest,
                                                 HttpServletResponse inResponse )
    {
        LOGGER.info( " in multiplechangeBinOwner" );
        //        ActionMessages message = getMessages( inRequest );
        //        ActionMessages errors = new ActionMessages();
        InboxForm inboxForm = (InboxForm) inForm;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String userId = userDto.getCrmUserDetailsPojo().getUserId();
            ConfigDto configDto = new ConfigDto();
            configDto.setUserId( userId );
            LOGGER.info( "Inbox Owner: " + inboxForm.getInboxOwner() );
            LOGGER.info( "Inbox Type: " + inboxForm.getInboxType() );
            if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.PERSONAL.getParameter() ) )
            {
                configDto.setFromUserId( userId );
                configDto.setToUserId( "" );
                LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
                if ( StringUtils.equals( inboxForm.getInboxType(), "ALL_INBOX" ) )
                {
                    assignSelfAllInbox( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.LEAD.getRequestCode() ) )
                {
                    assignSelfLead( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.CAF.getRequestCode() ) )
                {
                    assignSelfCAF( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.QRC.getRequestCode() ) )
                {
                    assignSelfQRC( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), "WORKFLOW" ) )
                {
                    assignSelfWorkFlow( inboxForm, configDto );
                }
            }
            else if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.GROUP.getParameter() ) )
            {
                configDto.setFromUserId( "" );
                configDto.setToUserId( userId );
                LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
                if ( StringUtils.equals( inboxForm.getInboxType(), "ALL_INBOX" ) )
                {
                    assignGroupAllInbox( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.LEAD.getRequestCode() ) )
                {
                    assignGroupLead( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.CAF.getRequestCode() ) )
                {
                    assignGroupCAF( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), CRMRequestType.QRC.getRequestCode() ) )
                {
                    assignGroupQRC( inboxForm, configDto );
                }
                else if ( StringUtils.equals( inboxForm.getInboxType(), "WORKFLOW" ) )
                {
                    assignGroupWorkFlow( inboxForm, configDto );
                }
            }
            LOGGER.info( "changeInboxBin result :::: " + configDto.getStatusCode() + " :: " + configDto.getStatusDesc() );
            /*if ( StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                message.add( IAppConstants.APP_MESSAGE,
                             new ActionMessage( "Your request has been processed successfully." ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( configDto.getStatusCode() ) );
            }*/
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in multipleChangeBinOwner", ex );
        }
        /* saveMessages( inRequest, message );
         saveErrors( inRequest, errors );*/
        inboxForm.setSearchMethod( "allInbox" );
        return inMapping.findForward( IActionForwardConstant.ALL_INBOX );
    }

    private void assignGroupAllInbox( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        AllInboxPojo allInboxPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getAllGroupInboxList() )
                && StringUtils.isValidObj( inInboxForm.getGroupIds() ) )
        {
            LOGGER.info( "groupIds Size::" + inInboxForm.getGroupIds().length );
            for ( int element : inInboxForm.getGroupIds() )
            {
                element = element - 1;
                LOGGER.info( "groupIds::>>>>>" + element );
                allInboxPojo = inInboxForm.getAllGroupInboxList().get( element );
                if ( StringUtils.isValidObj( allInboxPojo ) )
                {
                    LOGGER.info( "All GroupInbox Pojo::" + allInboxPojo );
                    inConfigDto.setFromStage( allInboxPojo.getStage() );
                    inConfigDto.setTostage( allInboxPojo.getStage() );
                    if ( StringUtils.equals( allInboxPojo.getType(), CRMRequestType.CAF.getRequestCode() ) )
                    {
                        LOGGER.info( "CRF RecordId::" + allInboxPojo.getLmsIdCrfRecordId() );
                        inConfigDto.setMappingId( String.valueOf( allInboxPojo.getLmsIdCrfRecordId() ) );
                    }
                    else if ( StringUtils.equals( allInboxPojo.getType(), CRMRequestType.LEAD.getRequestCode() )
                            || StringUtils.equals( allInboxPojo.getType(), CRMRequestType.QRC.getRequestCode() ) )
                    {
                        LOGGER.info( "Lead RecordId::" + allInboxPojo.getLeadIdCrfIdTicketId() );
                        inConfigDto.setMappingId( String.valueOf( allInboxPojo.getLeadIdCrfIdTicketId() ) );
                    }
                    inConfigDto.setRequestType( allInboxPojo.getType() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignSelfAllInbox( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        AllInboxPojo allInboxPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getAllInboxList() )
                && StringUtils.isValidObj( inInboxForm.getSelfIds() ) )
        {
            LOGGER.info( "SelfIds Size::" + inInboxForm.getSelfIds().length );
            for ( int element : inInboxForm.getSelfIds() )
            {
                element = element - 1;
                LOGGER.info( "SelfIds::>>>>>" + element );
                allInboxPojo = inInboxForm.getAllInboxList().get( element );
                if ( StringUtils.isValidObj( allInboxPojo ) )
                {
                    LOGGER.info( "All Inbox Pojo::" + allInboxPojo );
                    inConfigDto.setFromStage( allInboxPojo.getStage() );
                    inConfigDto.setTostage( allInboxPojo.getStage() );
                    if ( StringUtils.equals( allInboxPojo.getType(), CRMRequestType.CAF.getRequestCode() ) )
                    {
                        LOGGER.info( "CRF RecordId::" + allInboxPojo.getLmsIdCrfRecordId() );
                        inConfigDto.setMappingId( String.valueOf( allInboxPojo.getLmsIdCrfRecordId() ) );
                    }
                    else if ( StringUtils.equals( allInboxPojo.getType(), CRMRequestType.LEAD.getRequestCode() )
                            || StringUtils.equals( allInboxPojo.getType(), CRMRequestType.QRC.getRequestCode() ) )
                    {
                        LOGGER.info( "Lead RecordId::" + allInboxPojo.getLeadIdCrfIdTicketId() );
                        inConfigDto.setMappingId( String.valueOf( allInboxPojo.getLeadIdCrfIdTicketId() ) );
                    }
                    inConfigDto.setRequestType( allInboxPojo.getType() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignGroupWorkFlow( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CommonWorkflowPojo workflowPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowGroupInboxList() )
                && StringUtils.isValidObj( inInboxForm.getGroupIds() ) )
        {
            LOGGER.info( "GroupIds Size::" + inInboxForm.getGroupIds().length );
            for ( int element : inInboxForm.getGroupIds() )
            {
                element = element - 1;
                LOGGER.info( "GroupIds::>>>>>" + element );
                workflowPojo = inInboxForm.getWorkflowGroupInboxList().get( element );
                if ( StringUtils.isValidObj( workflowPojo ) )
                {
                    LOGGER.info( "workflowPojo::" + workflowPojo );
                    inConfigDto.setFromStage( String.valueOf( workflowPojo.getWorkflowStage() ) );
                    inConfigDto.setTostage( String.valueOf( workflowPojo.getWorkflowStage() ) );
                    inConfigDto.setMappingId( workflowPojo.getWorkflowId() );
                    inConfigDto.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignSelfWorkFlow( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CommonWorkflowPojo workflowPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getWorkflowInboxList() )
                && StringUtils.isValidObj( inInboxForm.getSelfIds() ) )
        {
            LOGGER.info( "SelfIds Size::" + inInboxForm.getSelfIds().length );
            for ( int element : inInboxForm.getSelfIds() )
            {
                element = element - 1;
                LOGGER.info( "SelfIds::>>>>>" + element );
                workflowPojo = inInboxForm.getWorkflowInboxList().get( element );
                if ( StringUtils.isValidObj( workflowPojo ) )
                {
                    LOGGER.info( "workflowPojo::" + workflowPojo );
                    inConfigDto.setFromStage( String.valueOf( workflowPojo.getWorkflowStage() ) );
                    inConfigDto.setTostage( String.valueOf( workflowPojo.getWorkflowStage() ) );
                    inConfigDto.setMappingId( workflowPojo.getWorkflowId() );
                    inConfigDto.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignGroupQRC( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CrmSrTicketsPojo srTicketsPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getQrcGroupInboxList() )
                && StringUtils.isValidObj( inInboxForm.getGroupIds() ) )
        {
            LOGGER.info( "GroupIds Size::" + inInboxForm.getGroupIds().length );
            for ( int element : inInboxForm.getGroupIds() )
            {
                element = element - 1;
                LOGGER.info( "GroupIds::>>>>>" + element );
                srTicketsPojo = inInboxForm.getQrcGroupInboxList().get( element );
                if ( StringUtils.isValidObj( srTicketsPojo ) )
                {
                    LOGGER.info( "QRC Pojo::" + srTicketsPojo );
                    LOGGER.info( "RecordId::" + srTicketsPojo.getSrId() );
                    inConfigDto.setFromStage( String.valueOf( srTicketsPojo.getFunctionalbinId() ) );
                    inConfigDto.setTostage( String.valueOf( srTicketsPojo.getFunctionalbinId() ) );
                    inConfigDto.setMappingId( srTicketsPojo.getSrId() );
                    inConfigDto.setRequestType( CRMRequestType.QRC.getRequestCode() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignSelfQRC( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CrmSrTicketsPojo srTicketsPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getQrcInboxList() )
                && StringUtils.isValidObj( inInboxForm.getSelfIds() ) )
        {
            LOGGER.info( "SelfIds Size::" + inInboxForm.getSelfIds().length );
            for ( int element : inInboxForm.getSelfIds() )
            {
                element = element - 1;
                LOGGER.info( "SelfIds::>>>>>" + element );
                srTicketsPojo = inInboxForm.getQrcInboxList().get( element );
                if ( StringUtils.isValidObj( srTicketsPojo ) )
                {
                    LOGGER.info( "QRC Pojo::" + srTicketsPojo );
                    LOGGER.info( "RecordId::" + srTicketsPojo.getSrId() );
                    inConfigDto.setFromStage( String.valueOf( srTicketsPojo.getFunctionalbinId() ) );
                    inConfigDto.setTostage( String.valueOf( srTicketsPojo.getFunctionalbinId() ) );
                    inConfigDto.setMappingId( srTicketsPojo.getSrId() );
                    inConfigDto.setRequestType( CRMRequestType.QRC.getRequestCode() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignGroupCAF( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CrmCustomerDetailsPojo pojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getCrfGroupInboxList() )
                && StringUtils.isValidObj( inInboxForm.getGroupIds() ) )
        {
            LOGGER.info( "groupIds Size::" + inInboxForm.getGroupIds().length );
            for ( int element : inInboxForm.getGroupIds() )
            {
                element = element - 1;
                LOGGER.info( "GroupIds::>>>>>" + element );
                pojo = inInboxForm.getCrfGroupInboxList().get( element );
                LOGGER.info( "GroupIds CAFPojo::" + pojo );
                LOGGER.info( "Record Id:: " + pojo.getRecordId() );
                inConfigDto.setFromStage( pojo.getCrfStage() );
                inConfigDto.setTostage( pojo.getCrfStage() );
                inConfigDto.setMappingId( String.valueOf( pojo.getRecordId() ) );
                inConfigDto.setRequestType( CRMRequestType.CAF.getRequestCode() );
                inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
            }
        }
    }

    private void assignSelfCAF( InboxForm inInboxForm, ConfigDto inConfigDto )
    {
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        if ( CommonValidator.isValidCollection( inInboxForm.getCrfInboxList() )
                && StringUtils.isValidObj( inInboxForm.getSelfIds() ) )
        {
            LOGGER.info( "SelfIds Size::" + inInboxForm.getSelfIds().length );
            for ( int element : inInboxForm.getSelfIds() )
            {
                element = element - 1;
                LOGGER.info( "SelfIds::>>>>>" + element );
                crmCustomerDetailsPojo = inInboxForm.getCrfInboxList().get( element );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    LOGGER.info( "RecordId::" + crmCustomerDetailsPojo.getRecordId() );
                    inConfigDto.setFromStage( crmCustomerDetailsPojo.getCrfStage() );
                    inConfigDto.setTostage( crmCustomerDetailsPojo.getCrfStage() );
                    inConfigDto.setMappingId( String.valueOf( crmCustomerDetailsPojo.getRecordId() ) );
                    inConfigDto.setRequestType( CRMRequestType.CAF.getRequestCode() );
                    inConfigDto = getInboxManagerImpl().changeInboxBin( inConfigDto );
                }
            }
        }
    }

    private void assignGroupLead( InboxForm inboxForm, ConfigDto configDto )
    {
        LmsPojo lmsPojo = null;
        if ( CommonValidator.isValidCollection( inboxForm.getLmsGroupInboxList() )
                && StringUtils.isValidObj( inboxForm.getGroupIds() ) )
        {
            LOGGER.info( "groupIds Size::" + inboxForm.getGroupIds().length );
            for ( int element : inboxForm.getGroupIds() )
            {
                element = element - 1;
                LOGGER.info( "GroupIds::>>>>>" + element );
                lmsPojo = inboxForm.getLmsGroupInboxList().get( element );
                if ( StringUtils.isValidObj( lmsPojo ) )
                {
                    LOGGER.info( "GroupIds LmsPojo::" + lmsPojo );
                    LOGGER.info( "LmsPojo lmsId:" + lmsPojo.getLmsId() );
                    configDto.setFromStage( lmsPojo.getLmsStage() );
                    configDto.setTostage( lmsPojo.getLmsStage() );
                    configDto.setMappingId( lmsPojo.getLeadId() );
                    configDto.setRequestType( CRMRequestType.LEAD.getRequestCode() );
                    configDto = getInboxManagerImpl().changeInboxBin( configDto );
                }
            }
        }
    }

    //    public ActionForward changeBinOwner( ActionMapping inMapping,
    //                                         ActionForm inForm,
    //                                         HttpServletRequest inRequest,
    //                                         HttpServletResponse inResponse )
    //        throws Exception
    //    {
    //        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
    //                .getAttribute( IAppConstants.CRM_USER_OBJECT );
    //        String userId = userDto.getCrmUserDetailsPojo().getUserId();
    //        ConfigDto configDto = new ConfigDto();
    //        InboxForm inboxForm = (InboxForm) inForm;
    //        LOGGER.info( "Row ID: " + inboxForm.getRowID() );
    //        LOGGER.info( "Element ID: " + inboxForm.getElementID() );
    //        LOGGER.info( "Inbox Owner: " + inboxForm.getInboxOwner() );
    //        LOGGER.info( "Current Stage: " + inboxForm.getCurrentStage() );
    //        LOGGER.info( "Bin Change inbox: " + inboxForm.getBinChangeInbox() );
    //        LOGGER.info( "Inbox Type: " + inboxForm.getInboxType() );
    //        if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.LEAD.getRequestCode() ) )
    //        {
    //            LOGGER.info( "Start changing lead owner on request user-Id : " + userId + " for lead : "
    //                    + inboxForm.getElementID() );
    //            configDto.setMappingId( inboxForm.getElementID() );
    //            configDto.setRequestType( CRMRequestType.LEAD.getRequestCode() );
    //        }
    //        else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.CRF.getRequestCode() ) )
    //        {
    //            LOGGER.info( "Start changing CRF owner on request user-Id : " + userId + " for CRF : "
    //                    + inboxForm.getElementID() );
    //            configDto.setMappingId( String.valueOf( inboxForm.getRowID() ) );
    //            configDto.setRequestType( CRMRequestType.CRF.getRequestCode() );
    //        }
    //        else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.QRC.getRequestCode() ) )
    //        {
    //            LOGGER.info( "Start changing SR owner on request user-Id : " + userId + " for SR : "
    //                    + inboxForm.getElementID() );
    //            configDto.setMappingId( String.valueOf( inboxForm.getElementID() ) );
    //            configDto.setRequestType( CRMRequestType.QRC.getRequestCode() );
    //        }
    //        else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), "WORKFLOW" ) )
    //        {
    //            LOGGER.info( "Start changing SR owner on request user-Id : " + userId + " for SR : "
    //                    + inboxForm.getElementID() );
    //            configDto.setMappingId( String.valueOf( inboxForm.getElementID() ) );
    //            configDto.setRequestType( inboxForm.getInboxType() );
    //        }
    //        configDto.setUserId( userId );
    //        if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.PERSONAL.getParameter() ) )
    //        {
    //            configDto.setFromUserId( userId );
    //            configDto.setToUserId( "" );
    //            LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
    //        }
    //        else if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.GROUP.getParameter() ) )
    //        {
    //            configDto.setFromUserId( "" );
    //            configDto.setToUserId( userId );
    //            LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
    //        }
    //        if ( StringUtils.isNumeric( inboxForm.getCurrentStage() ) )// functionBinID check for QRC cases
    //        {
    //            String stage = resolveStageByBinID( Long.parseLong( inboxForm.getCurrentStage() ) );
    //            if ( StringUtils.isNotBlank( stage ) )
    //            {
    //                inboxForm.setCurrentStage( stage );
    //            }
    //        }
    //        configDto.setFromStage( inboxForm.getCurrentStage() );
    //        configDto.setTostage( inboxForm.getCurrentStage() );
    //        configDto = getInboxManagerImpl().changeInboxBin( configDto );
    //        configDto.getStages().addAll( retrieveUserBin( inRequest, configDto ) );
    //        getInboxData( configDto, inboxForm, inRequest );
    //        return inMapping.findForward( IActionForwardConstant.INBOX );
    //    }
    //    private String resolveStageByBinID( Long inFunctionalbinId )
    //    {
    //        List<CrmRcaReasonPojo> functionalBinCache = CRMCacheManager.getFunctionalBins();
    //        for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBinCache )
    //        {
    //            if ( crmRcaReasonPojo.getCategoryId() == inFunctionalbinId )
    //            {
    //                if ( StringUtils.isNumeric( crmRcaReasonPojo.getValueAlias() )
    //                        || crmRcaReasonPojo.getValueAlias().equals( "-1" ) )
    //                {
    //                    return inFunctionalbinId.toString();
    //                }
    //                else
    //                    return crmRcaReasonPojo.getValueAlias();
    //            }
    //        }
    //        return null;
    //    }
    private void assignSelfLead( InboxForm inboxForm, ConfigDto configDto )
    {
        LmsPojo lmsPojo = null;
        if ( CommonValidator.isValidCollection( inboxForm.getLmsInboxList() )
                && StringUtils.isValidObj( inboxForm.getSelfIds() ) )
        {
            LOGGER.info( "SelfIds Size::" + inboxForm.getSelfIds().length );
            for ( int element : inboxForm.getSelfIds() )
            {
                element = element - 1;
                LOGGER.info( "SelfIds::>>>>>" + element );
                lmsPojo = inboxForm.getLmsInboxList().get( element );
                if ( StringUtils.isValidObj( lmsPojo ) )
                {
                    LOGGER.info( "LmsPojo" + lmsPojo );
                    LOGGER.info( "LmsPojo lmsId : " + lmsPojo.getLmsId() );
                    configDto.setFromStage( lmsPojo.getLmsStage() );
                    configDto.setTostage( lmsPojo.getLmsStage() );
                    configDto.setMappingId( lmsPojo.getLeadId() );
                    configDto.setRequestType( CRMRequestType.LEAD.getRequestCode() );
                    configDto = getInboxManagerImpl().changeInboxBin( configDto );
                }
            }
        }
    }
}
