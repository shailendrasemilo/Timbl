package com.np.tele.crm.finance.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.finance.forms.FinanceFormHelper;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class UpFrontPaymentRecoveryAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( UpFrontPaymentRecoveryAction.class );
    private IFinanceManager     financeManagerBm;
    private IMasterBMngr        masterBMngr;

    public IFinanceManager getFinanceManagerBm()
    {
        return financeManagerBm;
    }

    public void setFinanceManagerBm( IFinanceManager financeManagerBm )
    {
        this.financeManagerBm = financeManagerBm;
    }

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr masterBMngr )
    {
        this.masterBMngr = masterBMngr;
    }

    public ActionForward fileUploadUFPaymentPage( ActionMapping inMapping,
                                                  ActionForm inForm,
                                                  HttpServletRequest inRequest,
                                                  HttpServletResponse inResponse )
        throws Exception
    {
        FinanceForm financeForm = (FinanceForm) inForm;
        List<PartnerPojo> partnerPojos = CRMCacheManager.getBussinessPartners();
        financeForm.setPartnerList( partnerPojos );
        RemarksPojo remarksPojo = new RemarksPojo();
        financeForm.setRemarksPojo( remarksPojo );
        return inMapping.findForward( IActionForwardConstant.FILE_UPLOAD_UFPAYMENT_PAGE );
    }

    public ActionForward fileUploadUFPayment( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
        throws Exception
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        FinanceForm financeForm = (FinanceForm) inForm;
        LOGGER.info( "Remarks:" + financeForm.getRemarksPojo() );
        if ( StringUtils.isValidObj( financeForm.getFormFile() ) )
        {
            String fileName = financeForm.getFormFile().getFileName();
            String user = userDto.getCrmUserDetailsPojo().getUserId();
            LOGGER.info( "File : " + fileName + " Size : " + financeForm.getFormFile().getFileSize() );
            String filePath = getServlet().getServletContext().getRealPath( "/" ) + "UpFrontExcelUpload";
            List<String> statusList = getFinanceManagerBm().processUpFrontExcel( financeForm, user, filePath );
            if ( CommonValidator.isValidCollection( statusList ) )
            {
                String errorFile = statusList.get( 3 );
                String status = statusList.get( 0 );
                int validCount = Integer.parseInt( statusList.get( 2 ) );
                int totalRecords = Integer.parseInt( statusList.get( 1 ) );
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
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.FILE_INVALID_UPLOAD ) );
        }
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.FILE_UPLOAD_UFPAYMENT_PAGE );
    }

    public ActionForward manualyUpfrontPaymentPage( ActionMapping inMapping,
                                                    ActionForm inForm,
                                                    HttpServletRequest inRequest,
                                                    HttpServletResponse inResponse )
        throws Exception
    {
        CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = new CrmUpfrontPaymentPojo();
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        crmUpfrontPaymentPojo.setDisplayChequeDate( sdf.format( new Date() ) );
        crmUpfrontPaymentPojo.setAmount( 0.0 );
        FinanceForm financeForm = (FinanceForm) inForm;
        financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        List<PartnerPojo> partnerPojos = CRMCacheManager.getBussinessPartners();
        financeForm.setPartnerList( partnerPojos );
        financeForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
        financeForm.setCrmUpfrontPaymentPojo( crmUpfrontPaymentPojo );
        return inMapping.findForward( IActionForwardConstant.MANUALY_UP_FRONT_PAYMENT_PAGE );
    }

    public ActionForward submitUpfrontPaymentManualy( ActionMapping inMapping,
                                                      ActionForm inForm,
                                                      HttpServletRequest inRequest,
                                                      HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "In side submit payment manualy..." );
        CrmFinanceDto financeDto = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        String target = IActionForwardConstant.MANUALY_UP_FRONT_PAYMENT_PAGE;;
        String[] crfIdsArray = StringUtils.split( inRequest.getParameter( "CrfIds" ), "," );
        LOGGER.info( "The obtained crf id are : " + financeForm.getCrfId() );
        Set<CrmUpCrfMappingPojo> crfMappingPojos = null;
        if ( crfIdsArray.length > 0 )
        {
            if ( checkDuplicateCrfId( crfIdsArray ) )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_CRF_ID ) );
            }
            else
            {
                crfMappingPojos = crfIdValidation( errors, financeForm, inRequest, crfIdsArray );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CRF_NUMBER ) );
        }
        if ( errors.isEmpty() )
        {
            try
            {
                financeDto = getFinanceManagerBm().manualPayment( financeForm, crfMappingPojos );
                LOGGER.info( "In side submit payment manualy... and finance dto code  is : "
                        + financeDto.getStatusCode() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), financeDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( financeDto.getStatusCode() ) );
                    List<PartnerPojo> partnerPojos = CRMCacheManager.getBussinessPartners();
                    financeForm.setPartnerList( partnerPojos );
                    CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = new CrmUpfrontPaymentPojo();
                    financeForm.setCrmUpfrontPaymentPojo( crmUpfrontPaymentPojo );
                    financeForm.setToDate( new SimpleDateFormat( "dd-MMM-yyyy" ).format( new Date() ).toString() );
                    financeForm.setFromDate( new SimpleDateFormat( "dd-MMM-yyyy" ).format( new Date() ).toString() );
                    target = IActionForwardConstant.SEARCH_UP_FRONT_PAYMENT_ACTION;
                }
                else
                {
                    LOGGER.info( " Unable to Process Request Status Code " + financeDto.getStatusCode()
                            + " & Status Desc:" + financeDto.getStatusDesc() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
                }
            }
            catch ( Exception ex )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                LOGGER.error( "error occured while searching payment details", ex );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    public ActionForward searchUpfrontPaymentPage( ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
        throws Exception
    {
        FinanceForm financeForm = (FinanceForm) inForm;
        List<PartnerPojo> partnerPojos = CRMCacheManager.getBussinessPartners();
        financeForm.setPartnerList( partnerPojos );
        CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = new CrmUpfrontPaymentPojo();
        financeForm.setCrmUpfrontPaymentPojo( crmUpfrontPaymentPojo );
        return inMapping.findForward( IActionForwardConstant.SEARCH_UP_FRONT_PAYMENT_PAGE );
    }

    public ActionForward searchUpfrontPayment( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "inside search upfront payment page" );
        CrmFinanceDto financeDto = null;
        HttpSession httpSession = inRequest.getSession( false );
        List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojoList = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        String target = IActionForwardConstant.SEARCH_UP_FRONT_PAYMENT_PAGE;
        try
        {
            FinanceForm financeForm = (FinanceForm) inForm;
            LOGGER.info( "From Date ::::::: " + financeForm.getFromDate() );
            if ( !FinanceFormHelper.validateUpfrontDetails( errors, financeForm ).isEmpty() )
            {
                LOGGER.info( "Search Upfront Payment Details" );
                target = IActionForwardConstant.SEARCH_UP_FRONT_PAYMENT_PAGE;
            }
            else
            {
                if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_UPFRONT_PAYMENT_LIST ) ) )
                {
                    httpSession.removeAttribute( IAppConstants.CRM_UPFRONT_PAYMENT_LIST );
                }
                financeDto = getFinanceManagerBm().searchUpfrontPaymentDetails( financeForm );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), financeDto.getStatusCode() ) )
                {
                    crmUpfrontPaymentPojoList = financeDto.getCrmUpfrontPaymentPojos();
                    if ( crmUpfrontPaymentPojoList.size() < 1 )
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    else
                    {
                        financeForm.setCrmUpfrontPaymentPojoList( crmUpfrontPaymentPojoList );
                        httpSession.setAttribute( IAppConstants.CRM_UPFRONT_PAYMENT_LIST, crmUpfrontPaymentPojoList );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
                    LOGGER.info( "status code of finance dto: " + financeDto.getStatusCode() );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while searching payment details", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    private Set<CrmUpCrfMappingPojo> crfIdValidation( ActionMessages errors,
                                                      FinanceForm financeForm,
                                                      HttpServletRequest inRequest,
                                                      String[] crfIdsArray )
    {
        Map<String, Object[]> resultMap = null;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        CrmUpCrfMappingPojo crmUpCrfMappingPojo = null;
        Set<CrmUpCrfMappingPojo> crfMappingPojos = new HashSet<CrmUpCrfMappingPojo>();
        for ( String crfId : crfIdsArray )
        {
            financeForm.setCrfId( crfId );
            resultMap = ValidationPojoUtil
                    .validateSingleFormProperty( financeForm, ICRMValidationCriteriaUtil.FORM_CUSTOMER_SEARCH_CRITERIA,
                                                 false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                crmUpCrfMappingPojo = new CrmUpCrfMappingPojo();
                crmUpCrfMappingPojo.setCrfId( crfId );
                crmUpCrfMappingPojo.setCreatedBy( user );
                crmUpCrfMappingPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmUpCrfMappingPojo.setCreatedTime( DateUtils.toXMLGregorianCalendar( Calendar.getInstance() ) );
                crfMappingPojos.add( crmUpCrfMappingPojo );
                LOGGER.info( "The crf ids is :" + crfId );
            }
            else
            {
                break;
            }
        }
        return crfMappingPojos;
    }

    public static boolean checkDuplicateCrfId( String[] crfIdArray )
    {
        boolean flag = false;
        for ( int i = 0; i < crfIdArray.length; i++ )
        {
            for ( int j = 0; j < i; j++ )
            {
                if ( crfIdArray[i].equals( crfIdArray[j] ) )
                {
                    LOGGER.info( "The duplicate value is : " + crfIdArray[j] );
                    flag = true;
                }
            }
        }
        return flag;
    }

    public ActionForward subUpfrontPopupSearch( ActionMapping inMapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages errors = getErrors( inRequest );
        try
        {
            FinanceForm financeForm = (FinanceForm) inForm;
            CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = new CrmUpfrontPaymentPojo();
            LOGGER.info( "Requested Id ::" + financeForm.getUpFrontId() );
            crmUpfrontPaymentPojo.setUpfrontId( financeForm.getUpFrontId() );
            List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojos = (List<CrmUpfrontPaymentPojo>) inRequest
                    .getSession( false ).getAttribute( IAppConstants.CRM_UPFRONT_PAYMENT_LIST );
            int index = crmUpfrontPaymentPojos.indexOf( crmUpfrontPaymentPojo );
            if ( index >= 0 )
            {
                LOGGER.info( "ID " + financeForm.getUpFrontId() + " found in sesssion on index ::" + index );
                List<String> crfIds = new ArrayList<String>();
                for ( CrmUpCrfMappingPojo crmUpCrfMappingPojo : crmUpfrontPaymentPojos.get( index )
                        .getCrmUpCrfMappings() )
                {
                    crfIds.add( crmUpCrfMappingPojo.getCrfId() );
                }
                LOGGER.info( "Size Of CRF Ids :: " + crfIds.size() );
                financeForm.setCrfIds( crfIds );
                CrmFinanceDto crmFinanceDto = getFinanceManagerBm().getUpfrontPopUpDetails( financeForm );
                if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isValidObj( crmFinanceDto.getCrmCustomerDetailsPojos() )
                            && !crmFinanceDto.getCrmCustomerDetailsPojos().isEmpty() )
                    {
                        financeForm.setCrmCustomerDetailsPojos( crmFinanceDto.getCrmCustomerDetailsPojos() );
                    }
                    else
                    {
                        LOGGER.info( "NO Record Found IN database " );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmFinanceDto.getStatusCode() ) );
                    LOGGER.info( "status code of finance dto: " + crmFinanceDto.getStatusCode() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In subUpfrontPopupSearch Method ", ex );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.UPFRONT_POPUP );
    }
}
