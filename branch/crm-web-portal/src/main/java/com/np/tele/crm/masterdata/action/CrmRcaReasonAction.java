package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.masterdata.bm.ICrmRcaReasonMgr;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.CrmRcaReasonForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmHolidayDetails;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmRcaReasonAction
    extends DispatchAction
{
    private static final Logger LOGGER      = Logger.getLogger( CrmRcaReasonAction.class );
    private ICrmRcaReasonMgr    crmRcaReasonMgrImpl;
    private IMasterBMngr        masterBMngr = null;

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public ICrmRcaReasonMgr getCrmRcaReasonMgrImpl()
    {
        return crmRcaReasonMgrImpl;
    }

    public void setCrmRcaReasonMgrImpl( ICrmRcaReasonMgr inCrmRcaReasonMgrImpl )
    {
        crmRcaReasonMgrImpl = inCrmRcaReasonMgrImpl;
    }

    public ActionForward createRcaReasonPage( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        crmRcaReasonForm.setCrmRcaReason( crmRcaReason );
        List<ContentPojo> pojos = CRMUtils.getCategoryList();
        List<ContentPojo> reasonList = CRMUtils.getAllowedList( pojos );
        crmRcaReasonForm.setCategoryList( reasonList );
        return inMapping.findForward( IActionForwardConstant.CREATE_RCA_REASON );
    }

    public ActionForward addRcaReasonRow( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        List<ContentPojo> pojos = CRMUtils.getCategoryList();
        List<ContentPojo> reasonList = CRMUtils.getAllowedList( pojos );
        crmRcaReasonForm.setCategoryList( reasonList );
        LOGGER.info( "Category Name: " + crmRcaReasonForm.getCrmRcaReason().getCategory() );
        LOGGER.info( "Sub Category Name: " + crmRcaReasonForm.getCrmRcaReason().getSubCategory() );
        LOGGER.info( "Sub Sub Category Name: " + crmRcaReasonForm.getCrmRcaReason().getSubSubCategory() );
        if ( !StringUtils.isValidObj( crmRcaReasonForm.getCrmRcaReasonsList() ) )
        {
            crmRcaReasonForm.setCrmRcaReasonsList( new ArrayList<CrmRcaReasonPojo>() );
        }
        getCrmRcaReasonMgrImpl().setCategoriesByForm( crmRcaReason, crmRcaReasonForm );
        crmRcaReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmRcaReason.setEditable( true );
        crmRcaReason.setModificationAllowed( IAppConstants.YES_CHAR );
        crmRcaReasonForm.getCrmRcaReasonsList().add( 0, crmRcaReason );
        return inMapping.findForward( IActionForwardConstant.CREATE_RCA_REASON );
    }

    public ActionForward createCategoryValues( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        masterDataDto = getCrmRcaReasonMgrImpl().createCategoryValues( crmRcaReasonForm,
                                                                       userDto.getCrmUserDetailsPojo().getUserId() );
        String statusCode = masterDataDto.getStatusCode();
        if ( StringUtils.isNotEmpty( statusCode ) )
        {
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                CRMCacheManager.refreshMasterData();
                LOGGER.info( "Successfully Insert/Update Category Value" );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                masterDataDto = getCrmRcaReasonMgrImpl().searchCategoryValue( crmRcaReasonForm );
                List<CrmRcaReasonPojo> crmRcaReasonPojosList = new ArrayList<CrmRcaReasonPojo>();
                crmRcaReasonPojosList.addAll( masterDataDto.getCrmRcaReasonsList() );
                crmRcaReasonForm.setCrmRcaReasonsList( crmRcaReasonPojosList );
            }
            else
            {
                LOGGER.info( "Somthing has wrong in Insert/Update Category Value" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        List<ContentPojo> pojos = CRMUtils.getCategoryList();
        List<ContentPojo> reasonList = CRMUtils.getAllowedList( pojos );
        crmRcaReasonForm.setCategoryList( reasonList );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CREATE_RCA_REASON );
    }

    public ActionForward searchCategoryValues( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        String forwadPage = IActionForwardConstant.CREATE_RCA_REASON;
        if ( StringUtils.isValidObj( inRequest.getParameter( "requestPage1" ) ) )
        {
            crmRcaReasonForm.setRequestPage( inRequest.getParameter( "requestPage1" ) );
        }
        LOGGER.info( crmRcaReasonForm );
        errors = validateSearchCategoryValues( errors, crmRcaReasonForm );
        if ( errors.isEmpty() )
        {
            MasterDataDto masterDataDto = new MasterDataDto();
            masterDataDto = getCrmRcaReasonMgrImpl().searchCategoryValue( crmRcaReasonForm );
            if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                crmRcaReasonForm.setCrmRcaReasonsList( masterDataDto.getCrmRcaReasonsList() );
            }
            else if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM996.getStatusCode() ) )
            {
                if ( StringUtils.isValidObj( crmRcaReasonForm.getCrmRcaReasonsList() ) )
                {
                    crmRcaReasonForm.getCrmRcaReasonsList().clear();
                }
                errors.add( IAppConstants.NO_RECORD_FOUND, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
            else
            {
                LOGGER.info( "Somthing has wrong in Search Category Value" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( masterDataDto.getStatusCode() ) );
            }
        }
        forwadPage = crfReciept( crmRcaReasonForm, forwadPage );
        List<ContentPojo> pojos = CRMUtils.getCategoryList();
        List<ContentPojo> reasonList = CRMUtils.getAllowedList( pojos );
        crmRcaReasonForm.setCategoryList( reasonList );
          
        crmRcaReasonForm.setSubCategoryList( CRMUtils.getCategories( crmRcaReasonForm.getCrmRcaReason().getCategory(),
                                                                     null ) );
        crmRcaReasonForm.setSubSubCategoryList( CRMUtils.getCategories( crmRcaReasonForm.getCrmRcaReason()
                .getCategory(), crmRcaReasonForm.getCrmRcaReason().getSubCategory() ) );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwadPage );
    }

    private String crfReciept( CrmRcaReasonForm crmRcaReasonForm, String forwadPage )
    {
        if ( StringUtils.equals( crmRcaReasonForm.getRequestPage(), "recipetPage" ) )
        {
            if ( StringUtils.equals( crmRcaReasonForm.getCrmRcaReason().getSubCategory(),
                                     CRMDisplayListConstants.CAF.getCode() ) )
            {
                crmRcaReasonForm.setRequestPage( IAppConstants.ADDCRF );
                crmRcaReasonForm.setReturnButton( "search" );
            }
            else if ( StringUtils.equals( crmRcaReasonForm.getCrmRcaReason().getSubCategory(), "CashReceipt" ) )
            {
                crmRcaReasonForm.setRequestPage( IAppConstants.ADDRECIEPT );
                crmRcaReasonForm.setReturnButton( "search" );
            }
            forwadPage = IActionForwardConstant.ADD_RECEIPT_PAGE;
        }
        return forwadPage;
    }

    public ActionForward deleteCategoryValueRow( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        LOGGER.info( "Index ::" + crmRcaReasonForm.getRowCounter() );
        crmRcaReasonForm.getCrmRcaReasonsList().remove( crmRcaReasonForm.getRowCounter() );
        LOGGER.info( "size after remove :: " + crmRcaReasonForm.getCrmRcaReasonsList().size() );
        return inMapping.findForward( IActionForwardConstant.CREATE_RCA_REASON );
    }

    /***************Add receipt action***************/
    public ActionForward addReceiptCRFPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        crmRcaReasonForm.setCrmRcaReason( crmRcaReason );
        crmRcaReasonForm.setCategoryList( CRMUtils.getCategoryList() );
        crmRcaReasonForm.setCrmRcaReasonsList( null );
        crmRcaReasonForm.setEntityTypes( CRMUtils.getEntityTypeList() );
        crmRcaReasonForm.setStartRangeString( IAppConstants.EMPTY_STRING );
        crmRcaReasonForm.setEndRangeString( IAppConstants.EMPTY_STRING );
        crmRcaReasonForm.setAciveInactiveStatusList( CRMUtils.getAciveInactiveStatusList() );
        crmRcaReasonForm.setRequestPage( IAppConstants.EMPTY_STRING );
        return inMapping.findForward( IActionForwardConstant.ADD_RECEIPT_PAGE );
    }

    public ActionForward viewAddReceiptPage( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        crmRcaReasonForm.setCrmRcaReason( crmRcaReason );
        if ( StringUtils.equals( crmRcaReasonForm.getRequestPage(), IAppConstants.ADDCRF ) )
        {
            crmRcaReasonForm.getCrmRcaReason().setSubCategory( "CAF" );
            crmRcaReasonForm.setPrefix( IAppConstants.EMPTY_STRING );
            crmRcaReasonForm.setStartRangeString( IAppConstants.EMPTY_STRING );
            crmRcaReasonForm.setEndRangeString( IAppConstants.EMPTY_STRING );
        }
        if ( ( StringUtils.equals( crmRcaReasonForm.getRequestPage(), IAppConstants.ADDRECIEPT ) ) )
        {
            crmRcaReasonForm.getCrmRcaReason().setSubCategory( "CashReceipt" );
            crmRcaReasonForm.setEntityTypes( CRMUtils.getEntityTypeList() );
            crmRcaReasonForm.setPrefix( IAppConstants.EMPTY_STRING );
            crmRcaReasonForm.setStartRangeString( IAppConstants.EMPTY_STRING );
            crmRcaReasonForm.setEndRangeString( IAppConstants.EMPTY_STRING );
        }
        crmRcaReasonForm.setCategoryList( CRMUtils.getCategoryList() );
        return inMapping.findForward( IActionForwardConstant.ADD_RECEIPT_PAGE );
    }

    public ActionForward changeStatus( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getMessages( inRequest );
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        LOGGER.info( crmRcaReasonForm.getCrmRcaReason().getStatus() );
        LOGGER.info( crmRcaReasonForm.getCrmRcaReason().getCategoryId() );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        MasterDataDto masterDataDto = getCrmRcaReasonMgrImpl().changeStatus( crmRcaReasonForm,
                                                                             userDto.getCrmUserDetailsPojo()
                                                                                     .getUserId() );
        String statusCode = masterDataDto.getStatusCode();
        if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
        {
            LOGGER.info( "Successfully Insert/Update Category Value" );
            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
            crmRcaReasonForm.getCrmRcaReason().setCategoryId( 0 );
            crmRcaReasonForm.getCrmRcaReason().setCategoryValue( null );
            masterDataDto = getCrmRcaReasonMgrImpl().searchCategoryValue( crmRcaReasonForm );
            crmRcaReasonForm.setCrmRcaReasonsList( masterDataDto.getCrmRcaReasonsList() );
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.ADD_RECEIPT_PAGE );
    }

    public ActionForward addReceipt( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.ADD_RECEIPT_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getMessages( inRequest );
        try
        {
            CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
            List<CrmRcaReasonPojo> crmRcaReasonsList = new ArrayList<CrmRcaReasonPojo>();
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            errors = validateCrmRCAMaster( errors, crmRcaReasonForm );
            if ( errors.size() > 0 )
            {
                LOGGER.info( "Action error exist>>>>" + errors.size() );
            }
            else
            {
                LOGGER.info( "Action error not  exist in else block." );
                crmRcaReasonForm.setStartRange( Long.parseLong( crmRcaReasonForm.getStartRangeString() ) );
                crmRcaReasonForm.setEndRange( Long.parseLong( crmRcaReasonForm.getEndRangeString() ) );
                for ( long i = crmRcaReasonForm.getStartRange(); i <= crmRcaReasonForm.getEndRange(); i++ )
                {
                    CrmRcaReasonPojo crmRCAReason = new CrmRcaReasonPojo();
                    crmRCAReason.setCategory( crmRcaReasonForm.getCrmRcaReason().getCategory() );
                    crmRCAReason.setCategoryValue( crmRcaReasonForm.getPrefix()
                            + crmRcaReasonForm.getStartRangeString().substring( 0,
                                                                                crmRcaReasonForm.getStartRangeString()
                                                                                        .length()
                                                                                        - getRemoveStringLength( i ) )
                            + i );
                    crmRCAReason.setSubCategory( crmRcaReasonForm.getCrmRcaReason().getSubCategory() );
                    crmRCAReason.setSubSubCategory( crmRcaReasonForm.getCrmRcaReason().getSubSubCategory() );
                    crmRCAReason.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                    crmRCAReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmRCAReason.setValueAlias( CRMStatusCode.UNUSED.getStatusCode() );
                    crmRcaReasonsList.add( crmRCAReason );
                    LOGGER.info( "Content Value" + crmRcaReasonForm.getCrmRcaReason().getCategoryValue() );
                }
                MasterDataDto masterDataDto = getCrmRcaReasonMgrImpl().addRecietValues( crmRcaReasonsList );
                String statusCode = masterDataDto.getStatusCode();
                if ( StringUtils.isNotEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Successfully Insert/Update Category Value" );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        masterDataDto = getCrmRcaReasonMgrImpl().searchCategoryValue( crmRcaReasonForm );
                        if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            List<CrmRcaReasonPojo> crmRcaReasonPojosList = new ArrayList<CrmRcaReasonPojo>();
                            crmRcaReasonPojosList.addAll( masterDataDto.getCrmRcaReasonsList() );
                            crmRcaReasonForm.setCrmRcaReasonsList( crmRcaReasonPojosList );
                            crmRcaReasonForm.setRequestPage( IAppConstants.EMPTY_STRING );
                            /* if ( StringUtils.equals( crmRcaReasonForm.getCrmRcaReason().getSubCategory(), "CRF" ) )
                             {
                                 crmRcaReasonForm.setProductList( CRMUtils.getProducts() );
                             }
                             else
                             {
                                 List<PartnerPojo> partnerPojoList = CRMCacheManager.getBussinessPartners();
                                 crmRcaReasonForm.setPartnerList( partnerPojoList );
                             }*/
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured", ex );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward showDetails( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        Map<String, List<Map<String, List<ContentPojo>>>> subSubCategoryDetails = new HashMap<String, List<Map<String, List<ContentPojo>>>>();
        List<Map<String, List<ContentPojo>>> subCats = null;
        List<ContentPojo> subSubCats = null;
        List<ContentPojo> categories = CRMUtils.getCategoryList();
        for ( ContentPojo category : categories )
        {
            List<ContentPojo> subCategories = CRMUtils.getCategories( category.getContentValue(), null );
            subCats = new ArrayList<Map<String, List<ContentPojo>>>();
            for ( ContentPojo subCategory : subCategories )
            {
                subSubCats = CRMUtils.getCategories( category.getContentValue(), subCategory.getContentName(), true );
                if ( CommonValidator.isValidCollection( subSubCats ) )
                {
                    Map<String, List<ContentPojo>> subSubCat = new HashMap<String, List<ContentPojo>>();
                    subSubCat.put( subCategory.getContentValue(), subSubCats );
                    subCats.add( subSubCat );
                }
            }
            if ( CommonValidator.isValidCollection( subCats ) )
            {
                subSubCategoryDetails.put( category.getContentName(), subCats );
            }
        }
        LOGGER.info( "all subsubcategories " + subSubCategoryDetails );
        CrmRcaReasonForm rcaReasonForm = (CrmRcaReasonForm) inForm;
        rcaReasonForm.setSubSubCategoryDetails( subSubCategoryDetails );
        return inMapping.findForward( IActionForwardConstant.SHOW_DETAILS );
    }

    private ActionMessages validateSearchCategoryValues( ActionMessages actionError, CrmRcaReasonForm crmRcaReasonForm )
    {
        if ( StringUtils.isNotBlank( crmRcaReasonForm.getStartRangeString() ) )
        {
            if ( !StringUtils.isNumeric( crmRcaReasonForm.getEndRangeString() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_NUMERIC_RANGE_REQUIRED ) );
            }
        }
        else if ( StringUtils.isNotBlank( crmRcaReasonForm.getEndRangeString() ) )
        {
            if ( !StringUtils.isNumeric( crmRcaReasonForm.getEndRangeString() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_NUMERIC_RANGE_REQUIRED ) );
            }
        }
        return actionError;
    }

    ActionMessages validateCrmRCAMaster( ActionMessages actionError, CrmRcaReasonForm inform )
    {
        /*if ( StringUtils.isBlank( inform.getPrefix() ) )
        {
            actionError.add( IAppConstants.APP_ERROR,
                             new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RECEIPT_PREFIX ) );
        }*/
        if ( StringUtils.isBlank( inform.getStartRangeString() ) )
        {
            actionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STARTRANGE_REQUIRED ) );
        }
        if ( ( StringUtils.isNotBlank( inform.getStartRangeString() ) && ( !StringUtils.isNumeric( inform
                .getStartRangeString() ) ) ) )
        {
            actionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STARTRANGE_REQUIRED ) );
        }
        if ( StringUtils.isBlank( inform.getEndRangeString() ) )
        {
            actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ENDRANGE_REQUIRED ) );
        }
        else if ( ( StringUtils.isNotBlank( inform.getEndRangeString() ) && !StringUtils.isNumeric( inform
                .getEndRangeString() ) ) )
        {
            actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ENDRANGE_REQUIRED ) );
        }
        else if ( Integer.parseInt( inform.getEndRangeString() ) < Integer.parseInt( inform.getStartRangeString() ) )
        {
            actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE ) );
        }
        else if ( StringUtils.isNumeric( inform.getStartRangeString() )
                && ( Integer.parseInt( inform.getStartRangeString() ) == 0 )
                || ( StringUtils.isNumeric( inform.getEndRangeString() ) && Integer.parseInt( inform
                        .getEndRangeString() ) == 0 ) )
        {
            actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_REQUIRED ) );
        }
        else if ( StringUtils.isNotBlank( inform.getStartRangeString() )
                && Integer.parseInt( inform.getStartRangeString() ) > 0 )
        /*&& ( inform.getPrefix().equals( IAppConstants.EA ) )*/
        {
            if ( inform.getStartRangeString().length() != IAppConstants.MAX_CRF_LENGTH )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                             inform.getStartRangeString(),
                                                                             IAppConstants.MAX_CRF_LENGTH ) );
            }
        }
        else if ( StringUtils.isNotBlank( inform.getEndRangeString() )
                && ( Integer.parseInt( inform.getEndRangeString() ) > 0 ) )
        /*&& ( inform.getPrefix().equals( IAppConstants.EA ) ) )*/
        {
            if ( inform.getEndRangeString().length() != IAppConstants.MAX_CRF_LENGTH )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                             inform.getEndRangeString(),
                                                                             IAppConstants.MAX_CRF_LENGTH ) );
            }
        }
        if ( ( Integer.parseInt( inform.getEndRangeString() ) - Integer.parseInt( inform.getStartRangeString() ) ) > 5000)
        {
            actionError.add( IAppConstants.APP_ERROR,
                             new ActionMessage( IPropertiesConstant.ERROR_CRF_RANGE_DIFFERENCE ) );
        }
        /*     else if ( ( StringUtils.isNotBlank( inform.getEndRangeString() )
                     && ( Integer.parseInt( inform.getEndRangeString() ) > 0 ) && ( inform.getPrefix()
                     .equals( IAppConstants.RA ) ) ) )
             {
                 if ( inform.getStartRangeString().length() != IAppConstants.MAX_RECIPT_LENGTH )
                 {
                     actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                                  inform.getStartRangeString(),
                                                                                  IAppConstants.MAX_RECIPT_LENGTH ) );
                 }
             }
             if ( ( StringUtils.isNotBlank( inform.getEndRangeString() )
                     && ( Integer.parseInt( inform.getEndRangeString() ) > 0 ) && ( inform.getPrefix()
                     .equals( IAppConstants.RA ) ) ) )
             {
                 if ( inform.getEndRangeString().length() != IAppConstants.MAX_RECIPT_LENGTH )
                 {
                     actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                                  inform.getEndRangeString(),
                                                                                  IAppConstants.MAX_RECIPT_LENGTH ) );
                 }
             }
        else if ( ( Integer.parseInt( inform.getStartRangeString() ) > 0 )
                && ( StringUtils.equals( inform.getPrefix(), CRMDisplayListConstants.TELESERVICES.getCode() ) || StringUtils
                        .equals( inform.getPrefix(), CRMDisplayListConstants.TELESOLUTIONS.getCode() ) ) )
        {
            if ( inform.getStartRangeString().length() != IAppConstants.MAX_CRF_LENGTH )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                             inform.getStartRangeString(),
                                                                             IAppConstants.MAX_CRF_LENGTH ) );
            }
        }
        if ( ( StringUtils.isNotBlank( inform.getEndRangeString() )
                && ( Integer.parseInt( inform.getEndRangeString() ) > 0 ) && ( StringUtils
                .equals( inform.getPrefix(), CRMDisplayListConstants.TELESERVICES.getCode() ) || StringUtils
                .equals( inform.getPrefix(), CRMDisplayListConstants.TELESOLUTIONS.getCode() ) ) ) )
        {
            if ( inform.getEndRangeString().length() != IAppConstants.MAX_CRF_LENGTH )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN,
                                                                             inform.getEndRangeString(),
                                                                             IAppConstants.MAX_CRF_LENGTH ) );
            }
        }*/
        return actionError;
    }

    private int getRemoveStringLength( long inputValue )
    {
        int i = String.valueOf( inputValue ).length();
        return i;
    }

    public ActionForward addHolidayPage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        crmRcaReasonForm.setCrmHolidayPojo( new CrmHolidayDetails() );
        crmRcaReasonForm.getCrmHolidayPojo().setStatus( "A" );
        List<CrmHolidayDetails> pastHolidayDetails = new ArrayList<CrmHolidayDetails>();
        List<CrmHolidayDetails> futureHolidayDetails = new ArrayList<CrmHolidayDetails>();
        MasterDataDto masterDataDto = getCrmRcaReasonMgrImpl().getHolidaysList();
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmHolidayPojos() ) )
        {
            for ( CrmHolidayDetails crmHolidays : masterDataDto.getCrmHolidayPojos() )
            {
                if ( DateUtils.daysDiff( DateUtils.getCurrentDate(),
                                         DateUtils.convertXmlCalToDate( crmHolidays.getHolidayDate() ) ) < 1 )
                {
                    pastHolidayDetails.add( crmHolidays );
                }
                else
                {
                    futureHolidayDetails.add( crmHolidays );
                }
            }
            SortingComparator<CrmHolidayDetails> sorter = null;
            sorter = new SortingComparator<CrmHolidayDetails>( "holidayDate" );
            Collections.sort( futureHolidayDetails, sorter );
            crmRcaReasonForm.setCrmFutureHolidayPojos( futureHolidayDetails );
            Collections.sort( pastHolidayDetails, sorter );
            crmRcaReasonForm.setCrmPastHolidayPojos( pastHolidayDetails );
        }
        return inMapping.findForward( IActionForwardConstant.ADD_HOLIDAYS );
    }

    public ActionForward addHoliday( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getMessages( inRequest );
        CrmRcaReasonForm crmRcaReasonForm = (CrmRcaReasonForm) inForm;
        MasterDataDto masterDataDto = null;
        if ( StringUtils.isValidObj( crmRcaReasonForm.getCrmHolidayPojo() ) )
        {
            Date currDate = DateUtils.getCurrentDate();
            Date date = DateUtils.convertXmlCalToDate( DateUtils.changeDateFormat( crmRcaReasonForm.getDate() ) );
            if ( crmRcaReasonForm.getCrmHolidayPojo().getRecordId() == 0
                    && CommonValidator.isValidCollection( crmRcaReasonForm.getCrmFutureHolidayPojos() ) )
            {
                loop: for ( CrmHolidayDetails crmHolidays : crmRcaReasonForm.getCrmFutureHolidayPojos() )
                {
                    if ( StringUtils.equals( DateUtils.convertXmlCalToString( crmHolidays.getHolidayDate() ),
                                             crmRcaReasonForm.getDate() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_DATE ) );
                        break loop;
                    }
                }
            }
            if ( errors.isEmpty() && DateUtils.daysDiff( currDate, date ) < 1 )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_PAST_DATE ) );
            }
            if ( errors.isEmpty() )
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                masterDataDto = getCrmRcaReasonMgrImpl().postHoliday( crmRcaReasonForm,
                                                                      userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    List<CrmHolidayDetails> pastHolidayDetails = new ArrayList<CrmHolidayDetails>();
                    List<CrmHolidayDetails> futureHolidayDetails = new ArrayList<CrmHolidayDetails>();
                    masterDataDto = getCrmRcaReasonMgrImpl().getHolidaysList();
                    if ( CommonValidator.isValidCollection( masterDataDto.getCrmHolidayPojos() ) )
                    {
                        for ( CrmHolidayDetails crmHolidays : masterDataDto.getCrmHolidayPojos() )
                        {
                            if ( DateUtils.daysDiff( DateUtils.getCurrentDate(),
                                                     DateUtils.convertXmlCalToDate( crmHolidays.getHolidayDate() ) ) < 1 )
                            {
                                pastHolidayDetails.add( crmHolidays );
                            }
                            else
                            {
                                futureHolidayDetails.add( crmHolidays );
                            }
                        }
                        SortingComparator<CrmHolidayDetails> sorter = null;
                        sorter = new SortingComparator<CrmHolidayDetails>( "holidayDate" );
                        Collections.sort( futureHolidayDetails, sorter );
                        crmRcaReasonForm.setCrmFutureHolidayPojos( futureHolidayDetails );
                        Collections.sort( pastHolidayDetails, sorter );
                        crmRcaReasonForm.setCrmPastHolidayPojos( pastHolidayDetails );
                    }
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( masterDataDto.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( masterDataDto.getStatusCode() ) );
                }
            }
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.ADD_HOLIDAYS );
    }
}
