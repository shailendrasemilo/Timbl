package com.np.tele.crm.lms.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.inbox.form.InboxForm;
import com.np.tele.crm.lms.bm.ILmsManager;
import com.np.tele.crm.lms.form.LmsForm;
import com.np.tele.crm.lms.form.LmsFormHelper;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.usrmngmnt.bm.ICustomerProfileMgr;
import com.np.tele.crm.usrmngmnt.forms.CustomerProfileForm;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

@AppMonitor
public class LmsAction
    extends DispatchAction
{
    private static final Logger LOGGER             = Logger.getLogger( LmsAction.class );
    private ILmsManager         lmsManagerImpl     = null;
    private IMasterBMngr        masterBMngr        = null;
    private ICrmConfigManager   crmConfigManager   = null;
    private ICustomerProfileMgr custProfileMgrImpl = null;

    public ILmsManager getLmsManagerImpl()
    {
        return lmsManagerImpl;
    }

    public void setLmsManagerImpl( ILmsManager inLmsManagerImpl )
    {
        lmsManagerImpl = inLmsManagerImpl;
    }

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr masterBMngr )
    {
        this.masterBMngr = masterBMngr;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ICustomerProfileMgr getCustProfileMgrImpl()
    {
        return custProfileMgrImpl;
    }

    public void setCustProfileMgrImpl( ICustomerProfileMgr inCustProfileMgrImpl )
    {
        custProfileMgrImpl = inCustProfileMgrImpl;
    }

    public ActionForward leadGenereationPage( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        LmsForm lmsForm = (LmsForm) inForm;
        lmsForm.setAppointmentPojo( new AppointmentPojo() );
        lmsForm.setLmsPojo( new LmsPojo() );
        lmsForm.setRemarksPojo( new RemarksPojo() );
        lmsForm.setShowAppointmentDiv( true );
        lmsForm.setNonEditableAtSC( false );
        //----- Temporory work-------//
        getRequiredLists( lmsForm );
        return inMapping.findForward( IActionForwardConstant.LEAD_GENERATION );
    }

    public ActionForward leadGenereation( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        LOGGER.info( "Lead Genration method Executed:" );
        ActionMessages message = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LmsForm lmsForm = (LmsForm) inForm;
        String forwardPage = IActionForwardConstant.LEAD_GENERATION;
        LmsDto lmsDto = null;
        LmsPojo lmsPojo = null;
        try
        {
            if ( !LmsFormHelper.validateLmsForm( IAppConstants.METHOD_LEAD_GENEREATION, errors, lmsForm ).isEmpty() )
            {
                LOGGER.info( "Error in LMS  page ..." );
                forwardPage = IActionForwardConstant.LEAD_GENERATION;
            }
            else
            {
                if ( StringUtils.isEmpty( lmsForm.getRemarksPojo().getActions() ) )
                {
                    lmsForm.getRemarksPojo().setActions( CRMActionConstants.CREATE_LEAD.getStoringValue() );
                }
                LOGGER.info( "Mobile No:" + lmsForm.getLmsPojo().getContactNumber() );
                LOGGER.info( "User ID:" + lmsForm.getLmsPojo().getCreatedBy() );
                if ( GISUtils.isSocietyFeasible( lmsForm.getLmsPojo() ) )
                {
                    lmsForm.getLmsPojo().setFeasibility( CRMParameter.YES.getParameter() );
                }
                else if ( GISUtils.isAreaFeasible( lmsForm.getLmsPojo() ) )
                {
                    lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                }
                else
                {
                    if ( StringUtils.isValidObj( lmsForm.getLmsPojo() ) && lmsForm.getLmsPojo().getPincode() > 0 )
                    {
                        LmsPojo leadPojo = getLmsManagerImpl().getSocietyByPinCode( lmsForm.getLmsPojo().getPincode() );
                        if ( StringUtils.isValidObj( leadPojo ) && StringUtils.isNotEmpty( leadPojo.getState() ) )
                        {
                            lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                        }
                        else
                        {
                            lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                        }
                    }
                    else
                    {
                        lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                    }
                }
                lmsDto = getLmsManagerImpl().leadOperations( lmsForm, ServiceParameter.CREATE.getParameter() );
                if ( StringUtils.isValidObj( lmsDto ) && StringUtils.isNotEmpty( lmsDto.getStatusCode() ) )
                {
                    LOGGER.info( "Status Code of Lead:" + lmsDto.getStatusCode() );
                    LOGGER.info( "Size of success insert pojo is: " + lmsDto.getSuccessInsertPojos().size() );
                    if ( CommonValidator.isValidCollection( lmsDto.getSuccessInsertPojos() )
                            && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), lmsDto.getStatusCode() ) )
                    {
                        lmsPojo = lmsDto.getSuccessInsertPojos().get( 0 );
                        LOGGER.info( "Generated Id ::" + lmsPojo.getLmsId() );
                        lmsPojo.setLeadId( "L" + lmsPojo.getLmsId() );
                        lmsForm.setLmsPojo( lmsPojo );
                        forwardPage = IActionForwardConstant.VIEW_LEAD;
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while Genrated New Lead", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                getRequiredLists( lmsForm );
            }
            saveMessages( inRequest, message );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forwardPage );
    }

    public ActionForward lmsFileUploadPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LmsForm lmsForm = (LmsForm) inForm;
        lmsForm.setRemarksPojo( new RemarksPojo() );
        lmsForm.setLmsPojo( new LmsPojo() );
        return inMapping.findForward( IActionForwardConstant.LMS_FILE_UPLOAD );
    }

    public ActionForward lmsFileUpload( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        ActionMessages messages = new ActionMessages();
        LmsForm lmsForm = (LmsForm) inForm;
        String fileName = lmsForm.getFormFile().getFileName();
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        LOGGER.info( "File : " + fileName + " Size : " + lmsForm.getFormFile().getFileSize() );
        String filePath = getServlet().getServletContext().getRealPath( "/" ) + "LmsExcelUpload";
        List<String> statusList = getLmsManagerImpl().processLMSExcel( lmsForm, user, filePath );
        if ( statusList.size() > 1 )
        {
            String status = statusList.get( 0 );
            int totalRecords = Integer.parseInt( statusList.get( 1 ) );
            int validCount = Integer.parseInt( statusList.get( 2 ) );
            String errorFile = statusList.get( 3 );
            LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords
                    + " Valid Records db status: " + status );
            messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2, totalRecords ) );
            messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3, validCount ) );
            if ( validCount < totalRecords )
            {
                messages.add( "displayErrorFile", new ActionMessage( "" ) );
                inRequest.setAttribute( "fileName", errorFile );
            }
            if ( validCount > 0 )
            {
                messages.add( "uploadStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4, status ) );
            }
        }
        else
        {
            messages.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
        }
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.LMS_FILE_UPLOAD );
    }

    @SuppressWarnings("unchecked")
    public ActionForward modifyLeadPage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        ActionMessages message = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            LmsForm lmsForm = (LmsForm) inForm;
            lmsForm.setInaModule( PropertyUtils.getModuleDetails( IPropertiesConstant.INA_MODULE ) );
            LmsPojo lmsPojo = new LmsPojo();
            HttpSession httpSession = inRequest.getSession( false );
            String inboxId = inRequest.getParameter( "InboxId" );
            lmsPojo.setLmsId( StringUtils.numericValue( inRequest.getParameter( "lmsId" ) ) );
            lmsForm.setLmsPojoList( (List<LmsPojo>) httpSession.getAttribute( IAppConstants.LMS_POLO_LIST ) );
            LOGGER.info( "LMS Pojo List Size: " + lmsForm.getLmsPojoList().size() );
            int pojoIndex = lmsForm.getLmsPojoList().indexOf( lmsPojo );
            if ( pojoIndex >= 0 )
            {
                lmsPojo = lmsForm.getLmsPojoList().get( pojoIndex );
            }
            LOGGER.info( "LmsPojo" + lmsPojo );
            LOGGER.info( "LmsPojo lmsId : " + lmsPojo.getLmsId() );
            if ( StringUtils.numericValue( inboxId ) > 0 && StringUtils.isValidObj( lmsPojo ) )
            {
                ConfigDto configDto = new ConfigDto();
                configDto.setMappingId( lmsPojo.getLeadId() );
                configDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                configDto.setInboxId( StringUtils.numericValue( inboxId ) );
                configDto = getCrmConfigManager().updateInboxStatus( configDto );
                String statusCode = configDto.getStatusCode();
                if ( !StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    LOGGER.info( "Somthing has wrong in Edit Lead Details" );
                }
            }
            ConfigDto configDto = new ConfigDto();
            configDto.setMappingId( lmsPojo.getLeadId() );
            lmsForm.setLmsPojo( lmsPojo );
            /*configDto = getLmsManagerImpl().getAppointments( configDto );
            lmsForm.setAppointmentPojoList( configDto.getAppointmentPojos() );
            int appointmentsCounts = lmsForm.getAppointmentPojoList().size();
            //int appointmentsCounts = getLmsManagerImpl().getAppointmentsCount( lmsForm );
            if ( ( appointmentsCounts >= 5 && ( StringUtils.equals( lmsPojo.getLmsStage(),
                                                                    CRMOperationStages.AREA_MANAGER.getCode() ) || StringUtils
                    .equals( lmsPojo.getLmsStage(), CRMOperationStages.SALES.getCode() ) ) )
                    || StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.FULFILLMENT_TEAM.getCode() )
                    || ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.CLUSTER_HEAD.getCode() ) && appointmentsCounts == 1 )
                    || ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.SALES_COORDINATOR.getCode() ) && lmsForm
                            .isNonEditableAtSC() ) )
            {
                lmsForm.setShowAppointmentDiv( false );
            }
            else
            {
                lmsForm.setShowAppointmentDiv( true );
            }*/
            List<PartnerPojo> partnerPojos = CRMCacheManager.getPartnerbyType( CRMDisplayListConstants.BUSINESS_PARTNER
                    .getCode(), lmsPojo.getProduct(), CRMStatusCode.ACTIVE.getStatusCode() );
            if ( CommonValidator.isValidCollection( partnerPojos ) )
            {
                lmsForm.setPartnerList( partnerPojos );
            }
            else
            {
                partnerPojos = new ArrayList<PartnerPojo>();
                lmsForm.setPartnerList( partnerPojos );
            }
            /* else if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.FULFILLMENT_TEAM.getCode() ) )
             {
                 lmsForm.setShowAppointmentDiv( false );
             }
             else if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.CLUSTER_HEAD.getCode() )
                     && appointmentsCounts == 1 )
             {
                 lmsForm.setShowAppointmentDiv( false );
             }*/
            configDto = getLmsManagerImpl().getRemarks( configDto );
            lmsForm.setRemarksPojoList( configDto.getRemarksPojos() );
            lmsForm.setNonEditableAtSC( getLmsManagerImpl().getBackRouteSCValidation( lmsForm.getRemarksPojoList() ) );
            /* if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.SALES_COORDINATOR.getCode() )
                     && lmsForm.isNonEditableAtSC() )
             {
                 lmsForm.setShowAppointmentDiv( false );
              }*/
            LOGGER.info( "Size of Remarks Pojo In ::::::::::::::::::::" + configDto.getRemarksPojos().size() );
            if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.AREA_MANAGER.getCode() ) )
            {
                configDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                configDto.setUserType( CRMOperationStages.SALES.getCode() );
                configDto = getLmsManagerImpl().getBPs( configDto );
                LOGGER.info( "Mapped user-ids list size : " + configDto.getUserIds().size() );
                if ( !configDto.getUserIds().isEmpty() )
                {
                    lmsForm.setMappedUsers( configDto.getUserIds() );
                }
                else
                {
                    lmsForm.setMappedUsers( new ArrayList<String>() );
                }
            }
            else
                lmsForm.setMappedUsers( new ArrayList<String>() );
            if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.SALES_COORDINATOR.getCode() )
                    && !configDto.getAppointmentPojos().isEmpty() && lmsForm.isShowAppointmentDiv() )
            {
                lmsForm.setAppointmentPojo( configDto.getAppointmentPojos().get( 0 ) );
            }
            else
            {
                lmsForm.setAppointmentPojo( new AppointmentPojo() );
            }
            lmsForm.setRemarksPojo( new RemarksPojo() );
            configDto.setAuditLogPojo( new CrmAuditLogPojo() );
            configDto.getAuditLogPojo().setMappingId( String.valueOf( lmsForm.getLmsPojo().getLmsId() ) );
            configDto.getAuditLogPojo().setModule( CRMParameter.LMS.getParameter() );
            configDto = getLmsManagerImpl().getActivityLogs( configDto );
            httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
            if ( configDto.getAuditLogPojos().size() > 0 )
            {
                httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
            }
            getRequiredLists( lmsForm );
            LOGGER.info( "Size of Remarks Pojo In Form " + lmsForm.getRemarksPojoList().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while perparing data for modify lead page : ", ex );
        }
        saveMessages( inRequest, message );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.LEAD_GENERATION );
    }

    private void getRequiredLists( LmsForm inLmsForm )
    {
        ConfigDto configDto = null;
        try
        {
            inLmsForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            inLmsForm.setProducts( CRMUtils.getProducts() );
            configDto = getLmsManagerImpl().getLeadCloseReason();
            inLmsForm.setCloseReasons( configDto.getCrmCloseReasons() );
            inLmsForm.setAppointmentModes( CRMUtils.getAppointmentModes() );
            inLmsForm.setAppointmentTimings( CRMUtils.getAppointmentTimings() );
            inLmsForm.setStageIndex( CRMUtils.getStageIndex( inLmsForm.getLmsPojo().getLmsStage() ) );
            inLmsForm.setPerformingActions( getLmsManagerImpl().getActionsList( inLmsForm ) );
            inLmsForm.setPerformingActionsAll( CRMUtils.getLMSActions() );
            configDto = getLmsManagerImpl().getReferralSources();
            inLmsForm.setCrmReferralSources( configDto.getCrmRcaReasons() );
            if ( CommonValidator.isValidCollection( inLmsForm.getCrmReferralSources() ) )
            {
                Collections.swap( inLmsForm.getCrmReferralSources(), 2, 3 );
            }
            LmsPojo lmsPojo = inLmsForm.getLmsPojo();
            if ( StringUtils.isValidObj( lmsPojo ) )
            {
                if ( StringUtils.isNotEmpty( lmsPojo.getState() ) )
                {
                    inLmsForm.setCityPojoList( GISUtils.getCities( inLmsForm.getStatePojoList(), lmsPojo.getState() ) );
                    LOGGER.info( "City Pojo List Size : " + inLmsForm.getCityPojoList().size() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while setting required lists to display : ", ex );
        }
    }

    public ActionForward modifyLeadDetails( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "Lead Saving method Executed:" );
        ActionMessages message = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.LEAD_GENERATION;
        LmsForm lmsForm = (LmsForm) inForm;
        LmsDto lmsDto = null;
        try
        {
            if ( !LmsFormHelper.validateLmsForm( IAppConstants.METHOD_MODIFY_DETAILS, errors, lmsForm ).isEmpty() )
            {
                LOGGER.info( "Error in LMS  page ..." );
                forward = IActionForwardConstant.LEAD_GENERATION;
            }
            else
            {
                if ( StringUtils.isEmpty( lmsForm.getLmsPojo().getState() ) )
                {
                    lmsForm.getLmsPojo().setCity( "" );
                }
                if ( StringUtils.isEmpty( lmsForm.getRemarksPojo().getActions() ) )
                {
                    lmsForm.getRemarksPojo().setActions( CRMActionConstants.SAVE_LEAD.getStoringValue() );
                }
                if ( !StringUtils.equals( lmsForm.getRemarksPojo().getActions(),
                                          CRMActionConstants.SAVE_LEAD.getStoringValue() )
                        && StringUtils.equals( lmsForm.getLmsPojo().getLmsStage(),
                                               CRMActionConstants.BACKWARD_SC.getStoringValue() ) )
                {
                    if ( GISUtils.isSocietyFeasible( lmsForm.getLmsPojo() ) )
                    {
                        lmsForm.getLmsPojo().setFeasibility( CRMParameter.YES.getParameter() );
                    }
                    else if ( GISUtils.isAreaFeasible( lmsForm.getLmsPojo() ) )
                    {
                        lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                    }
                    else
                    {
                        if ( StringUtils.isValidObj( lmsForm.getLmsPojo() ) && lmsForm.getLmsPojo().getPincode() > 0 )
                        {
                            LmsPojo leadPojo = getLmsManagerImpl().getSocietyByPinCode( lmsForm.getLmsPojo()
                                                                                                .getPincode() );
                            if ( StringUtils.isValidObj( leadPojo )
                                    && StringUtils.equals( lmsForm.getLmsPojo().getArea(), leadPojo.getArea() ) )
                            {
                                lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                            }
                            else
                            {
                                lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                            }
                        }
                        else
                        {
                            lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                        }
                    }
                }
                lmsDto = getLmsManagerImpl().leadOperations( lmsForm, ServiceParameter.SAVE.getParameter() );
                if ( StringUtils.isValidObj( lmsDto ) && StringUtils.isNotBlank( lmsDto.getStatusCode() ) )
                {
                    LOGGER.info( "Status Code of Lead:" + lmsDto.getStatusCode() );
                    if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        message.add( IAppConstants.APP_MESSAGE, new ActionMessage( lmsDto.getStatusCode() ) );
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Your request has been processed successfully." );
                        forward = IActionForwardConstant.INBOX;
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while Saving Lead", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                getRequiredLists( lmsForm );
            }
            saveMessages( inRequest, message );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward performAction( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "Lead Perform Action method Executed:" );
        ActionMessages message = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.LEAD_GENERATION;
        LmsForm lmsForm = (LmsForm) inForm;
        LmsDto lmsDto = new LmsDto();
        try
        {
            if ( !LmsFormHelper.validateLmsForm( IAppConstants.METHOD_PERFORM_ACTION, errors, lmsForm ).isEmpty() )
            {
                LOGGER.info( "Error in LMS  page ..." );
                forward = IActionForwardConstant.LEAD_GENERATION;
            }
            else
            {
                if ( StringUtils.equals( lmsForm.getRemarksPojo().getActions(),
                                         CRMActionConstants.CLOSE_LEAD.getStoringValue() ) )
                {
                    lmsDto = getLmsManagerImpl().leadOperations( lmsForm, ServiceParameter.CLOSE.getParameter() );
                }
                else if ( StringUtils.equals( lmsForm.getRemarksPojo().getActions(),
                                              CRMActionConstants.MATURE_LEAD.getStoringValue() ) )
                {
                    if ( StringUtils.equals( lmsForm.getInaModule(), IAppConstants.Y ) )
                    {
                        String crfIds = inRequest.getParameter( "crfIds" );
                        lmsForm.setCrfIds( crfIds );
                        LOGGER.info( "Retrieved List : " + lmsForm.getCrfIds() );
                    }
                    lmsDto = getLmsManagerImpl().leadOperations( lmsForm, ServiceParameter.MATURE.getParameter() );
                }
                else
                {
                    if ( StringUtils.equals( lmsForm.getLmsPojo().getLmsStage(),
                                             CRMActionConstants.BACKWARD_SC.getStoringValue() ) )
                    {
                        if ( GISUtils.isSocietyFeasible( lmsForm.getLmsPojo() ) )
                        {
                            lmsForm.getLmsPojo().setFeasibility( CRMParameter.YES.getParameter() );
                        }
                        else if ( GISUtils.isAreaFeasible( lmsForm.getLmsPojo() ) )
                        {
                            lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                        }
                        else
                        {
                            if ( StringUtils.isValidObj( lmsForm.getLmsPojo() )
                                    && lmsForm.getLmsPojo().getPincode() > 0 )
                            {
                                LmsPojo leadPojo = getLmsManagerImpl().getSocietyByPinCode( lmsForm.getLmsPojo()
                                                                                                    .getPincode() );
                                if ( StringUtils.isValidObj( leadPojo )
                                        && StringUtils.equals( lmsForm.getLmsPojo().getArea(), leadPojo.getArea() ) )
                                {
                                    lmsForm.getLmsPojo().setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                                }
                                else
                                {
                                    lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                                }
                            }
                            else
                            {
                                lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                            }
                        }
                        /*if ( GISUtils.isSocietyFeasible( lmsForm.getLmsPojo() ) )
                        {
                            lmsForm.getLmsPojo().setFeasibility( CRMParameter.YES.getParameter() );
                        }
                        else
                        {
                            lmsForm.getLmsPojo().setFeasibility( CRMParameter.NO.getParameter() );
                        }*/
                    }
                    lmsDto = getLmsManagerImpl().leadOperations( lmsForm, ServiceParameter.FORWARD.getParameter() );
                }
                if ( StringUtils.isNotEmpty( lmsDto.getStatusCode() ) )
                {
                    LOGGER.info( "Status Code of Lead:" + lmsDto.getStatusCode() );
                    if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        message.add( IAppConstants.APP_MESSAGE, new ActionMessage( lmsDto.getStatusCode() ) );
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Your request has been processed successfully." );
                        forward = IActionForwardConstant.INBOX;
                    }
                    else if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM201.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode(), lmsForm
                                .getRemarksPojo().getActions() ) );
                    }
                    else if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM307.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode(), lmsDto
                                .getLmsCrfMappingPojo().getCrfValue() ) );
                        lmsForm.getRemarksPojo().setActions( CRMActionConstants.MATURE_LEAD.getStoringValue() );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while Performing Action on Specified Lead", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                getRequiredLists( lmsForm );
            }
            saveMessages( inRequest, message );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward viewLeadDetails( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.VIEW_LEAD_DETAILS;
        HttpSession httpSession = inRequest.getSession( false );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) httpSession.getAttribute( IAppConstants.CRM_USER_OBJECT );
        String userId = userDto.getCrmUserDetailsPojo().getUserId();
        InboxForm inboxForm = null;
        CustomerProfileForm customerProfileForm = null;
        RemarksPojo remarksPojo = new RemarksPojo();
        String type = inRequest.getParameter( "inType" );
        String inboxId = inRequest.getParameter( "InboxId" );
        String lmsId = inRequest.getParameter( "lmsId" );
        LOGGER.info( "Incoming Type " + type );
        LmsPojo lmsPojo = new LmsPojo();
        lmsPojo.setLmsId( StringUtils.numericValue( lmsId ) );
        int pojoIndex = 0;
        if ( StringUtils.equals( type, "inbox" ) )
        {
            inboxForm = (InboxForm) httpSession.getAttribute( "inboxForm" );
            pojoIndex = inboxForm.getLmsInboxList().indexOf( lmsPojo );
            if ( pojoIndex >= 0 )
            {
                lmsPojo = inboxForm.getLmsInboxList().get( pojoIndex );
            }
            else
            {
                pojoIndex = inboxForm.getLmsGroupInboxList().indexOf( lmsPojo );
                if ( pojoIndex >= 0 )
                {
                    lmsPojo = inboxForm.getLmsGroupInboxList().get( pojoIndex );
                }
            }
        }
        else
        {
            customerProfileForm = (CustomerProfileForm) httpSession.getAttribute( "customerProfileForm" );
            pojoIndex = customerProfileForm.getLmsPojoList().indexOf( lmsPojo );
            if ( pojoIndex >= 0 )
            {
                lmsPojo = customerProfileForm.getLmsPojoList().get( pojoIndex );
            }
        }
        if ( StringUtils.equals( type, "inbox" ) )
        {
            if ( StringUtils.numericValue( inboxId ) > 0 && StringUtils.isValidObj( lmsPojo ) )
            {
                ConfigDto configDto = new ConfigDto();
                configDto.setMappingId( lmsPojo.getLeadId() );
                configDto.setUserId( userId );
                configDto.setInboxId( StringUtils.numericValue( inboxId ) );
                configDto = getCrmConfigManager().updateInboxStatus( configDto );
                String statusCode = configDto.getStatusCode();
                if ( !StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    LOGGER.info( "Somthing has wrong in view Lead Details" );
                }
            }
        }
        ConfigDto configDto = new ConfigDto();
        LmsForm lmsForm = (LmsForm) inForm;
        lmsForm.setSrTicketPojo( new CrmSrTicketsPojo() );
        lmsForm.setTicketHistory( new CrmTicketHistoryPojo() );
        lmsForm.setTicketActions( new ArrayList<ContentPojo>() );
        lmsForm.setLmsPojo( lmsPojo );
        lmsForm.setRemarksPojo( remarksPojo );
        configDto.setMappingId( lmsPojo.getLeadId() );
        /*configDto = getLmsManagerImpl().getAppointments( configDto );
        lmsForm.setAppointmentPojoList( configDto.getAppointmentPojos() );*/
        configDto = getLmsManagerImpl().getRemarks( configDto );
        lmsForm.setRemarksPojoList( configDto.getRemarksPojos() );
        lmsForm.setProducts( CRMUtils.getProducts() );
        lmsForm.setAppointmentModes( CRMUtils.getAppointmentModes() );
        lmsForm.setAppointmentTimings( CRMUtils.getAppointmentTimings() );
        lmsForm.setPerformingActionsAll( CRMUtils.getLMSActions() );
        configDto = getLmsManagerImpl().getLeadCloseReason();
        lmsForm.setCloseReasons( configDto.getCrmCloseReasons() );
        configDto.setAuditLogPojo( new CrmAuditLogPojo() );
        configDto.getAuditLogPojo().setMappingId( String.valueOf( lmsForm.getLmsPojo().getLmsId() ) );
        configDto.getAuditLogPojo().setModule( CRMParameter.LMS.getParameter() );
        configDto = getLmsManagerImpl().getActivityLogs( configDto );
        httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
        lmsForm.setParameter( type );
        if ( configDto.getAuditLogPojos().size() > 0 )
        {
            httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
        }
        LOGGER.info( "Viewing lead on request user-Id : " + userId + " for lead : " + lmsPojo.getLeadId() );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward saveRemarks( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.VIEW_LEAD_DETAILS;
        LmsForm lmsForm = (LmsForm) inForm;
        RemarksPojo remarksPojo = lmsForm.getRemarksPojo();
        MasterDataDto masterDto = new MasterDataDto();
        LmsDto lmsDto = null;
        try
        {
            LOGGER.info( "Remarks Pojo is::::::" + remarksPojo );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( remarksPojo, ICRMValidationCriteriaUtil.FORM_REMARKS_CRITERIA_COMMON, false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                remarksPojo.setActions( CrmActionEnum.SAVED_REMARKS.getActionCode() );
                remarksPojo.setMappingType( CRMRequestType.LMS.getRequestCode() );
                masterDto = getMasterBMngr().saveRemarks( remarksPojo );
                String statusCode = masterDto.getStatusCode();
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        ConfigDto configDto = new ConfigDto();
                        configDto.setMappingId( remarksPojo.getMappingId() );
                        configDto = getLmsManagerImpl().getRemarks( configDto );
                        lmsForm.setRemarksPojoList( configDto.getRemarksPojos() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
            lmsDto = getCustProfileMgrImpl().leadCustomerProfileSearch( CustomerProfile.LEAD_ID.getCode(),
                                                                        remarksPojo.getMappingId() );
            if ( StringUtils.isValidObj( lmsDto ) && CommonValidator.isValidCollection( lmsDto.getLeadPojos() ) )
            {
                lmsForm.setLmsPojo( lmsDto.getLeadPojos().get( 0 ) );
            }
            else
            {
                lmsForm.setLmsPojo( new LmsPojo() );
            }
            lmsForm.setTicketActions( new ArrayList<ContentPojo>() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in saving remarks", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward getAppointment( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        ConfigDto configDto = null;
        String mappingId = inRequest.getParameter( "mappingId" );
        LmsForm lmsForm = (LmsForm) inForm;
        lmsForm.setAppointmentPojo( null );
        if ( StringUtils.isValidObj( mappingId ) )
        {
            configDto = new ConfigDto();
            configDto.setMappingId( mappingId );
            configDto = getLmsManagerImpl().getAppointments( configDto );
            if ( CommonValidator.isValidCollection( configDto.getAppointmentPojos() ) )
            {
                lmsForm.setAppointmentPojoList( configDto.getAppointmentPojos() );
                SortingComparator<AppointmentPojo> sorter = new SortingComparator<AppointmentPojo>( "createdTime" );
                Collections.sort( lmsForm.getAppointmentPojoList(), Collections.reverseOrder( sorter ) );
                sorter = null;
            }
        }
        else
        {
            List<AppointmentPojo> pojos = new ArrayList<AppointmentPojo>();
            lmsForm.setAppointmentPojoList( pojos );
        }
        return inMapping.findForward( IActionForwardConstant.LMS_CUSTOMER_APPOINTMENT );
    }

    public ActionForward logLMSTicket( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside LMSAction : Ticket creation for Lms" );
        String target = IActionForwardConstant.VIEW_LEAD_DETAILS;
        CrmQrcDto crmQrcDto = null;
        LmsForm lmsForm = (LmsForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        HttpSession inSession = inRequest.getSession( false );
        LmsFormHelper.validateSRTktPojo( errors, lmsForm );
        if ( errors.isEmpty() )
        {
            try
            {
                crmQrcDto = new CrmQrcDto();
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inSession.getAttribute( IAppConstants.CRM_USER_OBJECT );
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                lmsForm.getSrTicketPojo().setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                lmsForm.getSrTicketPojo().setModuleType( CRMRequestType.LMS.getRequestCode() );
                crmQrcDto.setCrmSrTicketsPojo( lmsForm.getSrTicketPojo() );
                crmQrcDto.setTicketHistory( lmsForm.getTicketHistory() );
                if ( lmsForm.getSrTicketPojo().getResolutionType() == 1 )
                {
                    crmQrcDto.setFutureStage( lmsForm.getSrTicketPojo().getFunctionalbinId() + "" );
                }
                LOGGER.info( "Ticket Pojo : " + lmsForm.getSrTicketPojo() );
                crmQrcDto = getLmsManagerImpl().logLMSTicket( crmQrcDto );
                LOGGER.info( "Status Code : " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_LOGGED, crmQrcDto
                                          .getCrmSrTicketsPojo().getSrId() ) );
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_REOPENED, crmQrcDto
                                          .getCrmSrTicketsPojo().getSrId() ) );
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM404.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_EXIST_FORWARD, crmQrcDto
                                          .getCrmSrTicketsPojo().getSrId() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() )
                        || StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), crmQrcDto.getStatusCode() )
                        || StringUtils.equals( CRMServiceCode.CRM404.getStatusCode(), crmQrcDto.getStatusCode() )
                        || !errors.isEmpty() )
                {
                    LOGGER.info( "Lead Id ::" + lmsForm.getSrTicketPojo().getMappingId() );
                    LmsDto lmsDto = getCustProfileMgrImpl()
                            .leadCustomerProfileSearch( CustomerProfile.LEAD_ID.getCode(),
                                                        lmsForm.getSrTicketPojo().getMappingId() );
                    if ( StringUtils.isValidObj( lmsDto ) && CommonValidator.isValidCollection( lmsDto.getLeadPojos() ) )
                    {
                        lmsForm.setLmsPojo( lmsDto.getLeadPojos().get( 0 ) );
                    }
                    else
                    {
                        lmsForm.setLmsPojo( new LmsPojo() );
                    }
                    long categoryID = lmsForm.getSrTicketPojo().getQrcCategoryId();
                    long subCategoryID = lmsForm.getSrTicketPojo().getQrcSubCategoryId();
                    long subSubCategoryID = lmsForm.getSrTicketPojo().getQrcSubSubCategoryId();
                    String qrcType = lmsForm.getSrTicketPojo().getQrcType();
                    LOGGER.info( "categoryID ::" + categoryID + ",SubCategoryID ::" + subCategoryID
                            + ",SubSubCategoryID ::" + subSubCategoryID );
                    if ( StringUtils.isNotBlank( qrcType ) && categoryID > 0 && subCategoryID > 0
                            && subSubCategoryID > 0 )
                    {
                        lmsForm.setTicketActions( QRCCacheManager.getQrcActionsList( categoryID, subCategoryID,
                                                                                     subSubCategoryID ) );
                    }
                }
                LOGGER.info( "Ticket Pojo : " + lmsForm.getSrTicketPojo() );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while logging ticket for Lead ID : " + lmsForm.getLmsPojo().getLeadId(), ex );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    public ActionForward getLMSTktInfo( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CrmSrTicketsPojo ticketsPojo = null;
        CrmQrcDto crmQrcDto = null;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        String forwardPage = IActionForwardConstant.LEAD_TICKET_PAGE_DETAILS;
        LmsForm lmsForm = (LmsForm) inForm;
        try
        {
            String mappingId = inRequest.getParameter( "mappingId" );
            crmQrcDto = new CrmQrcDto();
            ticketsPojo = new CrmSrTicketsPojo();
            if ( StringUtils.isValidObj( mappingId ) )
            {
                ticketsPojo.setMappingId( mappingId );
                crmQrcDto.setCrmSrTicketsPojo( ticketsPojo );
                crmQrcDto = getLmsManagerImpl().getLMSTickets( crmQrcDto );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                    {
                        getLmsManagerImpl().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                        lmsForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                        SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
                        Collections.sort( lmsForm.getSrTicketsPojos(), Collections.reverseOrder( sorter ) );
                        sorter = null;
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while searching LMS tickets", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }
}
