package com.np.tele.crm.qrc.config.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.qrc.config.bm.IQrcConfigManager;
import com.np.tele.crm.qrc.config.forms.QrcConfigForm;
import com.np.tele.crm.qrc.config.forms.QrcConfigFormHelper;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.QrcConfigDto;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QrcConfigAction
    extends DispatchAction
{
    private static final Logger LOGGER             = Logger.getLogger( QrcConfigAction.class );
    private IQrcConfigManager   qrcConfigManagerBm = null;

    public IQrcConfigManager getQrcConfigManagerBm()
    {
        return qrcConfigManagerBm;
    }

    public void setQrcConfigManagerBm( IQrcConfigManager inQrcConfigManagerBm )
    {
        qrcConfigManagerBm = inQrcConfigManagerBm;
    }

    public ActionForward configureSubSubCatPage( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_SUB_SUB_CAT_PAGE );
    }

    public ActionForward getSubSubCat( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        String forwardPage = IActionForwardConstant.QRC_CONFIG_SUB_SUB_CAT_PAGE;
        if ( StringUtils.isValidObj( qrcConfigForm.getQrcCategoryId() ) )
        {
            LOGGER.info( "QrcCategoryId::" + qrcConfigForm.getQrcCategoryId() );
            qrcConfigForm.setQrcSubCategoriesPojos( QRCCacheManager.getActiveSubCategories( qrcConfigForm
                    .getQrcCategoryId(), qrcConfigForm.getModuleType() ) );
            if ( StringUtils.isValidObj( qrcConfigForm.getQrcSubCategoryId() ) )
            {
                qrcConfigForm.setQrcSubSubCategoriesPojos( getQrcConfigManagerBm()
                        .getQrcSubSubCategories( qrcConfigForm.getQrcSubCategoryId(), qrcConfigForm.getModuleType() ) );
            }
        }
        else
        {
            qrcConfigForm.getQrcSubSubCategoriesPojos().clear();
            errors.add( IAppConstants.APP_MESSAGE,
                        new ActionMessage( IPropertiesConstant.ERROR_QRC_SUB_SUB_CAT_CATEGORY_REQUIRED ) );
        }
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategoriesByModuleType( qrcConfigForm
                .getModuleType() ) );
        qrcConfigForm.setResolutionTypeList( CRMUtils.getTicketActions() );
        qrcConfigForm.setQrcTypeList( CRMUtils.getQRCType() );
        qrcConfigForm.setFunctionalBinList( CRMCacheManager.getQRCFunctionalBins() );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward configureSubSubCat( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        Set<CrmQrcSubSubCategoriesPojo> toUpdateSubSubCategoriesPojos = new HashSet<CrmQrcSubSubCategoriesPojo>();
        PojoComparator<CrmQrcSubSubCategoriesPojo> pojoComparator = new PojoComparator<CrmQrcSubSubCategoriesPojo>();
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        String forwardPage = IActionForwardConstant.QRC_CONFIG_SUB_SUB_CAT_PAGE;
        QrcConfigFormHelper.validateSubSubCategory( errors, qrcConfigForm.getQrcSubSubCategoriesPojos() );
        if ( errors.isEmpty() )
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String user = userDto.getCrmUserDetailsPojo().getUserId();
            LOGGER.info( "SubSubCategories size:" + qrcConfigForm.getQrcSubSubCategoriesPojos().size() );
            List<CrmQrcSubSubCategoriesPojo> dbSubSubCategoriesPojos = getQrcConfigManagerBm()
                    .getQrcSubSubCategories( qrcConfigForm.getQrcSubCategoryId(), qrcConfigForm.getModuleType() );
            LOGGER.info( "DB SubSubCategories size:" + dbSubSubCategoriesPojos.size() );
            CrmQrcSubSubCategoriesPojo tmpSubSubCategories = null;
            int index = 0;
            for ( CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo : qrcConfigForm.getQrcSubSubCategoriesPojos() )
            {
                index = dbSubSubCategoriesPojos.indexOf( crmQrcSubSubCategoriesPojo );
                if ( StringUtils.equals( crmQrcSubSubCategoriesPojo.getModificationAllowed(), IAppConstants.YES_CHAR ) )
                {
                    if ( index > -1 )
                    {
                        tmpSubSubCategories = dbSubSubCategoriesPojos.get( index );
                        if ( ! ( pojoComparator.compare( crmQrcSubSubCategoriesPojo, tmpSubSubCategories ) == 0 ) )
                        {
                            LOGGER.info( "SubSubCategory to Update:"
                                    + crmQrcSubSubCategoriesPojo.getQrcSubSubCategory() );
                            // Checking Duplicate for the record 
                            long subSubCatId = crmQrcSubSubCategoriesPojo.getQrcSubSubCategoryId();
                            crmQrcSubSubCategoriesPojo.setQrcSubSubCategoryId( 0 );
                            index = dbSubSubCategoriesPojos.indexOf( crmQrcSubSubCategoriesPojo );
                            if ( index > -1 )
                            {
                                tmpSubSubCategories = dbSubSubCategoriesPojos.get( index );
                                if ( tmpSubSubCategories.getQrcSubSubCategoryId() == subSubCatId )
                                {
                                    crmQrcSubSubCategoriesPojo.setQrcSubSubCategoryId( subSubCatId );
                                    crmQrcSubSubCategoriesPojo.setModuleType( qrcConfigForm.getModuleType() );
                                    toUpdateSubSubCategoriesPojos.add( crmQrcSubSubCategoriesPojo );
                                }
                                else
                                {
                                    crmQrcSubSubCategoriesPojo.setQrcSubSubCategoryId( subSubCatId );
                                    errors.add( IAppConstants.APP_ERROR,
                                                new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                                   crmQrcSubSubCategoriesPojo.getQrcSubSubCategory(),
                                                                   "Sub Sub Category" ) );
                                    break;
                                }
                            }
                            else
                            {
                                crmQrcSubSubCategoriesPojo.setQrcSubSubCategoryId( subSubCatId );
                                crmQrcSubSubCategoriesPojo.setModuleType( qrcConfigForm.getModuleType() );
                                toUpdateSubSubCategoriesPojos.add( crmQrcSubSubCategoriesPojo );
                            }
                        }
                    }
                    else if ( crmQrcSubSubCategoriesPojo.getQrcSubSubCategoryId() == 0 && index == -1 )
                    {
                        LOGGER.info( "SubSubCategory to Create:" + crmQrcSubSubCategoriesPojo.getQrcSubSubCategory() );
                        crmQrcSubSubCategoriesPojo.setModuleType( qrcConfigForm.getModuleType() );
                        toUpdateSubSubCategoriesPojos.add( crmQrcSubSubCategoriesPojo );
                    }
                    LOGGER.info( crmQrcSubSubCategoriesPojo );
                }
            }
            LOGGER.info( "SubSubCategories to update:" + toUpdateSubSubCategoriesPojos.size() );
            if ( errors.isEmpty() )
            {
                if ( CommonValidator.isValidCollection( toUpdateSubSubCategoriesPojos ) )
                {
                    QrcConfigDto qrcConfigDto = new QrcConfigDto();
                    qrcConfigDto.getCrmQrcSubSubCategoriesPojos().addAll( toUpdateSubSubCategoriesPojos );
                    qrcConfigDto.setLoggedUser( user );
                    qrcConfigDto = getQrcConfigManagerBm().configureSubSubCat( qrcConfigDto );
                    LOGGER.info( "Response Code from Service:" + qrcConfigDto.getStatusCode() + "-"
                            + qrcConfigDto.getStatusDesc() );
                    if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        forwardPage = IActionForwardConstant.QRC_CONFIG_GET_SUB_SUB_CAT_REDIRECT;
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_CONFIG_SUB_SUB_CAT ) );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_QRC_SUB_SUB_CAT_EDIT_OR_ADD_SUBCATEGORY ) );
                }
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward configRcaPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcConfigAction, configRcaPage" );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        qrcConfigForm.setProductTypes( CRMUtils.getProducts() );
        qrcConfigForm.setQrcCategoryId( 0 );
        qrcConfigForm.setServiceName( null );
        qrcConfigForm.setQrcRootCausePojos( null );
        qrcConfigForm.setQrcActionTakenPojos( null );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RCA_PAGE );
    }

    public ActionForward getQrcRcaConfigurations( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        QrcConfigFormHelper.validateGetQrcRcaConfigurations( errors, qrcConfigForm );
        if ( errors.isEmpty() )
        {
            List<CrmQrcActionTakenPojo> qrcActionTakenPojos = getQrcConfigManagerBm()
                    .getQrcRcaPojos( qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getServiceName(), null );
            if ( CommonValidator.isValidCollection( qrcActionTakenPojos ) )
            {
                qrcConfigForm.setQrcActionTakenPojos( qrcActionTakenPojos );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RCA_CONFIGURATIONS ) );
                qrcConfigForm.setQrcActionTakenPojos( new ArrayList<CrmQrcActionTakenPojo>() );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RCA_PAGE );
    }

    public ActionForward configRCA( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcConfigAction, configRCA" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        String target = IActionForwardConstant.QRC_CONFIG_RCA_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigDto qrcConfigDto = null;
        QrcConfigForm qrcConfigForm = null;
        List<CrmQrcActionTakenPojo> qrcActionTakenPojos = null;
        boolean process = true;
        try
        {
            PojoComparator<CrmQrcActionTakenPojo> pojoComparator = new PojoComparator<CrmQrcActionTakenPojo>();
            qrcConfigForm = (QrcConfigForm) inForm;
            QrcConfigFormHelper.validateDuplicateRcaConfigurations( errors, qrcConfigForm );
            List<CrmQrcActionTakenPojo> dbActionTakenPojo = getQrcConfigManagerBm()
                    .getQrcRcaPojos( qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getServiceName(), null );
            int count = 0;
            if ( CommonValidator.isValidCollection( dbActionTakenPojo ) )
            {
                for ( CrmQrcActionTakenPojo actionTakenPojo : qrcConfigForm.getQrcActionTakenPojos() )
                {
                    count = validateModifiedRCA( pojoComparator, dbActionTakenPojo, count, actionTakenPojo );
                }
            }
            if ( count > 0 )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
                process = false;
            }
            if ( errors.isEmpty() && process )
            {
                qrcConfigDto = new QrcConfigDto();
                qrcConfigDto.setLoggedUser( user );
                qrcConfigDto.getQrcActionTakenPojos().addAll( qrcConfigForm.getQrcActionTakenPojos() );
                qrcConfigDto = getQrcConfigManagerBm().configurRCA( qrcConfigDto );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isValidObj( qrcConfigDto.getQrcActionTakenPojos() )
                            && !qrcConfigDto.getQrcActionTakenPojos().isEmpty() )
                    {
                        // qrcActionTakenPojos = qrcConfigDto.getQrcActionTakenPojos();
                        qrcActionTakenPojos = getQrcConfigManagerBm().getQrcRcaPojos( qrcConfigForm.getQrcCategoryId(),
                                                                                      qrcConfigForm.getServiceName(),
                                                                                      null );
                        qrcConfigForm.setQrcActionTakenPojos( qrcActionTakenPojos );
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_MSG_RCA_CONFIGURATION ) );
                    }
                    else
                    {
                        qrcActionTakenPojos = new ArrayList<CrmQrcActionTakenPojo>();
                        qrcConfigForm.setQrcActionTakenPojos( qrcActionTakenPojos );
                    }
                }
                else
                {
                    if ( StringUtils.isValidObj( qrcConfigDto.getQrcActionTakenPojo() ) )
                    {
                        {
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode(),
                                                                                    qrcConfigDto
                                                                                            .getQrcActionTakenPojo()
                                                                                            .getActionTaken() ) );
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode() ) );
                    }
                }
                LOGGER.info( "Service Status : " + qrcConfigDto.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while changing RCA configurations ", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    private int validateModifiedRCA( PojoComparator<CrmQrcActionTakenPojo> pojoComparator,
                                     List<CrmQrcActionTakenPojo> dbRCAsPojos,
                                     int count,
                                     CrmQrcActionTakenPojo actionTakenPojo )
    {
        CrmQrcActionTakenPojo tmpRCAPojo;
        int index;
        index = dbRCAsPojos.indexOf( actionTakenPojo );
        if ( actionTakenPojo.getActionId() > 0 && index > -1 && actionTakenPojo.isEditable() )
        {
            tmpRCAPojo = dbRCAsPojos.get( index );
            if ( ( pojoComparator.compare( actionTakenPojo, tmpRCAPojo ) == 0 ) )
            {
                count++;
            }
        }
        return count;
    }

    public ActionForward configResolutionCodePage( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        qrcConfigForm.setProductTypes( CRMUtils.getProducts() );
        qrcConfigForm.setQrcCategoryId( 0 );
        qrcConfigForm.setServiceName( null );
        qrcConfigForm.setQrcRootCausePojos( null );
        qrcConfigForm.setQrcActionTakenPojos( null );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RESOLUTION_CODE_PAGE );
    }

    public ActionForward getResolutionCodes( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        if ( qrcConfigForm.getQrcRcaId() > 0 )
        {
            List<CrmQrcActionTakenPojo> qrcActionTakenPojos = getQrcConfigManagerBm()
                    .getQrcRcaPojos( qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getServiceName(),
                                     CRMStatusCode.ACTIVE.getStatusCode() );
            if ( CommonValidator.isValidCollection( qrcActionTakenPojos ) )
            {
                qrcConfigForm.setQrcActionTakenPojos( qrcActionTakenPojos );
            }
            List<CrmQrcRootCausePojo> rootCausePojos = getQrcConfigManagerBm()
                    .getCrmQrcResolutionCodePojos( qrcConfigForm.getQrcRcaId() );
            if ( CommonValidator.isValidCollection( rootCausePojos ) )
            {
                qrcConfigForm.setQrcRootCausePojos( rootCausePojos );
                LOGGER.info( "in getResolutionCodes. list size: " + rootCausePojos.size() );
            }
            else
            {
                qrcConfigForm.setQrcRootCausePojos( null );
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RESOLUTION_CODES ) );
            }
        }
        else
        {
            qrcConfigForm.setQrcRootCausePojos( null );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RCA_ID ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RESOLUTION_CODE_PAGE );
    }

    public ActionForward addRowResolutionCode( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigFormHelper.validateConfigResolutionCode( errors, qrcConfigForm );
        if ( errors.isEmpty() )
        {
            if ( !CommonValidator.isValidCollection( qrcConfigForm.getQrcRootCausePojos() ) )
            {
                qrcConfigForm.setQrcRootCausePojos( new ArrayList<CrmQrcRootCausePojo>() );
            }
            CrmQrcRootCausePojo resolutionCodePojo = new CrmQrcRootCausePojo();
            resolutionCodePojo.setActionId( qrcConfigForm.getQrcRcaId() );
            resolutionCodePojo.setEditable( true );
            resolutionCodePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            qrcConfigForm.getQrcRootCausePojos().add( 0, resolutionCodePojo );
        }
        else
        {
            qrcConfigForm.setQrcRootCausePojos( null );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RESOLUTION_CODE_PAGE );
    }

    public ActionForward removeRowResolutionCode( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigFormHelper.validateConfigResolutionCode( errors, qrcConfigForm );
        if ( errors.isEmpty() )
        {
            try
            {
                int index = Integer.parseInt( inRequest.getParameter( IAppConstants.PARAM_INDEX ) );
                if ( index > -1 )
                {
                    qrcConfigForm.getQrcRootCausePojos().remove( index );
                }
            }
            catch ( NumberFormatException ex )
            {
                LOGGER.info( "invalid index: " + inRequest.getParameter( IAppConstants.PARAM_INDEX ) );
            }
        }
        else
        {
            qrcConfigForm.setQrcRootCausePojos( null );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RESOLUTION_CODE_PAGE )
                .getPath(), true );
    }

    public ActionForward configResolutionCode( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        LOGGER.info( "inside QrcConfigAction::configResolutionCode" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        QrcConfigFormHelper.validateConfigResolutionCode( errors, qrcConfigForm );
        int count = 0;
        boolean process = true;
        PojoComparator<CrmQrcRootCausePojo> pojoComparator = new PojoComparator<CrmQrcRootCausePojo>();
        List<CrmQrcRootCausePojo> dbRootCausePojo = getQrcConfigManagerBm()
                .getCrmQrcResolutionCodePojos( qrcConfigForm.getQrcRcaId() );
        LOGGER.info( "Form POJO:::" + qrcConfigForm.getQrcRootCausePojos().size() );
        for ( CrmQrcRootCausePojo crmRootCausePojo : qrcConfigForm.getQrcRootCausePojos() )
        {
            count = validateModifiedResolutionCode( count, pojoComparator, dbRootCausePojo, crmRootCausePojo );
        }
        if ( count > 0 )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
            process = false;
        }
        if ( errors.isEmpty() && checkDuplicateResolutionCode( errors, qrcConfigForm ) && process )
        {
            if ( CommonValidator.isValidCollection( qrcConfigForm.getQrcRootCausePojos() ) )
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                String user = userDto.getCrmUserDetailsPojo().getUserId();
                QrcConfigDto qrcConfigDto = null;
                try
                {
                    qrcConfigDto = getQrcConfigManagerBm().configurResolutionCode( qrcConfigForm, user );
                    if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        List<CrmQrcRootCausePojo> rootCausePojos = getQrcConfigManagerBm()
                                .getCrmQrcResolutionCodePojos( qrcConfigForm.getQrcRcaId() );
                        if ( CommonValidator.isValidCollection( rootCausePojos ) )
                        {
                            qrcConfigForm.setQrcRootCausePojos( rootCausePojos );
                        }
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_MSG_CONFIG_RESOLUTION_CODE ) );
                    }
                    else if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM308.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_DUBLICATE_VALUE_FOUND, qrcConfigDto
                                            .getQrcRootCausePojo().getRootCause() ) );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode() ) );
                    }
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception while save attributedToPage ", ex );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADD_RESOLUTION_CODES ) );
            }
        }
        //        else
        //        {
        //            qrcConfigForm.setQrcResolutionCodePojos( null );
        //        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RESOLUTION_CODE_PAGE )
                .getPath(), true );
    }

    private int validateModifiedResolutionCode( int count,
                                                PojoComparator<CrmQrcRootCausePojo> pojoComparator,
                                                List<CrmQrcRootCausePojo> dbResolutionCodePojo,
                                                CrmQrcRootCausePojo crmResolutionCodePojo )
    {
        CrmQrcRootCausePojo tmpResulotionCodePojo;
        int index;
        if ( StringUtils.isValidObj( dbResolutionCodePojo ) )
        {
            if ( StringUtils.isValidObj( dbResolutionCodePojo ) )
            {
                index = dbResolutionCodePojo.indexOf( crmResolutionCodePojo );
                LOGGER.info( "Index:::" + index );
                if ( crmResolutionCodePojo.getRootCauseId() > 0 && index > -1 && crmResolutionCodePojo.isEditable() )
                {
                    tmpResulotionCodePojo = dbResolutionCodePojo.get( index );
                    // tmpResulotionCodePojo.setEditable( true );
                    if ( ( pojoComparator.compare( crmResolutionCodePojo, tmpResulotionCodePojo ) == 0 ) )
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean checkDuplicateResolutionCode( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        if ( StringUtils.isValidObj( inQrcConfigForm.getQrcRootCausePojos() ) )
        {
            Set<String> resolutionCodePojoSet = new HashSet<String>();
            int editableCounter = 0;
            for ( CrmQrcRootCausePojo resolutionCodePojo : inQrcConfigForm.getQrcRootCausePojos() )
            {
                if ( resolutionCodePojo.isEditable() )
                {
                    editableCounter++;
                }
                if ( StringUtils.isBlank( resolutionCodePojo.getRootCause() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RESOLUTION_CODE_VALUE ) );
                    break;
                }
                else if ( !resolutionCodePojoSet.add( resolutionCodePojo.getRootCause() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                                              resolutionCodePojo.getRootCause(),
                                                                              "Root Cause" ) );
                    break;
                }
                if ( StringUtils.isBlank( resolutionCodePojo.getAttributedTo() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ATTRIBUTED_TO_VALUE ) );
                    break;
                }
            }
            if ( editableCounter <= 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM991.getStatusCode(),
                                                                          "Root Cause" ) );
            }
        }
        return inErrors.isEmpty();
    }

    //    public ActionForward configRcaPage( final ActionMapping inMapping,
    //                                        final ActionForm inForm,
    //                                        final HttpServletRequest inRequest,
    //                                        final HttpServletResponse inResponse )
    //    {
    //        LOGGER.info( "Inside QrcConfigAction, configRcaPage" );
    //        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
    //        List<CrmQrcRcaPojo> qrcRcaPojos = new ArrayList<CrmQrcRcaPojo>();
    //        qrcConfigForm.setQrcRcaPojos( qrcRcaPojos );
    //        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RCA_PAGE );
    //    }
    public ActionForward configAttributedToPage( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        qrcConfigForm.setProductTypes( CRMUtils.getProducts() );
        qrcConfigForm.setQrcCategoryId( 0 );
        qrcConfigForm.setServiceName( null );
        qrcConfigForm.setQrcRootCausePojos( null );
        qrcConfigForm.setQrcActionTakenPojos( null );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_ATTRIBUTED_TO_PAGE );
    }

    public ActionForward addRowAttributedTo( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        CrmQrcAttributedToPojo attributedToPojo = new CrmQrcAttributedToPojo();
        attributedToPojo.setResolutionCodeId( qrcConfigForm.getQrcResolutionCodeId() );
        attributedToPojo.setEditable( true );
        attributedToPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        if ( CommonValidator.isValidCollection( qrcConfigForm.getAttributedToPojos() ) )
        {
            qrcConfigForm.getAttributedToPojos().add( 0, attributedToPojo );
        }
        else
        {
            qrcConfigForm.setAttributedToPojos( new ArrayList<CrmQrcAttributedToPojo>() );
            qrcConfigForm.getAttributedToPojos().add( 0, attributedToPojo );
        }
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_ATTRIBUTED_TO_PAGE );
    }

    public ActionForward removeRowAttributedTo( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        int index = Integer.parseInt( inRequest.getParameter( "index" ) );
        if ( index > -1 )
        {
            qrcConfigForm.getAttributedToPojos().remove( index );
        }
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_ATTRIBUTED_TO_PAGE )
                .getPath(), true );
    }

    public ActionForward attributedToPage( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcConfigAction, attributedToPage" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        String target = IActionForwardConstant.QRC_CONFIG_ATTRIBUTED_TO_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigDto qrcConfigDto = null;
        QrcConfigForm qrcConfigForm = null;
        try
        {
            qrcConfigForm = (QrcConfigForm) inForm;
            if ( StringUtils.isValidObj( qrcConfigForm.getAttributedToPojos() )
                    && !qrcConfigForm.getAttributedToPojos().isEmpty() )
            {
                PojoComparator<CrmQrcAttributedToPojo> pojoComparator = new PojoComparator<CrmQrcAttributedToPojo>();
                CrmQrcAttributedToPojo tmpAttributedToList = null;
                List<CrmQrcAttributedToPojo> dbAttributedToListPojo = getQrcConfigManagerBm()
                        .getAttributedToList( qrcConfigForm.getQrcResolutionCodeId() );
                Set<String> crmAttributedToPojosSet = new HashSet<String>();
                int index = 0;
                int editableCounter = 0;
                for ( CrmQrcAttributedToPojo crmAttributedToPojo : qrcConfigForm.getAttributedToPojos() )
                {
                    if ( crmAttributedToPojo.isEditable() )
                    {
                        editableCounter++;
                        break;
                    }
                }
                if ( editableCounter > 0 )
                {
                    for ( CrmQrcAttributedToPojo crmAttributedToPojo : qrcConfigForm.getAttributedToPojos() )
                    {
                        if ( CommonValidator.isValidCollection( dbAttributedToListPojo ) )
                        {
                            index = dbAttributedToListPojo.indexOf( crmAttributedToPojo );
                            LOGGER.info( crmAttributedToPojo.isEditable() + ":is editable of:::::"
                                    + crmAttributedToPojo.getAttributedTo() );
                            if ( index > -1 && crmAttributedToPojo.isEditable() )
                            {
                                tmpAttributedToList = dbAttributedToListPojo.get( index );
                                if ( pojoComparator.compare( crmAttributedToPojo, tmpAttributedToList ) == 0
                                        && crmAttributedToPojo.getAttributedToId() > 0 )
                                {
                                    errors.add( IAppConstants.APP_ERROR,
                                                new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
                                    break;
                                }
                                // Checking Duplicate for the record 
                                long attributedId = crmAttributedToPojo.getAttributedToId();
                                crmAttributedToPojo.setAttributedToId( 0 );
                                index = dbAttributedToListPojo.indexOf( crmAttributedToPojo );
                                if ( index > -1 )
                                {
                                    tmpAttributedToList = dbAttributedToListPojo.get( index );
                                    if ( tmpAttributedToList.getAttributedToId() == attributedId )
                                    {
                                        crmAttributedToPojo.setAttributedToId( attributedId );
                                    }
                                    else
                                    {
                                        crmAttributedToPojo.setAttributedToId( attributedId );
                                        errors.add( IAppConstants.APP_ERROR,
                                                    new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                                       crmAttributedToPojo.getAttributedTo(),
                                                                       "Attributed To" ) );
                                        break;
                                    }
                                }
                            }
                        }
                        if ( StringUtils.isBlank( crmAttributedToPojo.getAttributedTo() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ATTRIBUTED_TO_VALUE ) );
                            break;
                        }
                        else if ( !crmAttributedToPojosSet.add( crmAttributedToPojo.getAttributedTo() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( CRMServiceCode.CRM990.getStatusCode(), crmAttributedToPojo
                                                .getAttributedTo(), "Attributed To" ) );
                            break;
                        }
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM991.getStatusCode(),
                                                                            "Attributed To" ) );
                }
            }
            if ( errors.isEmpty() )
            {
                qrcConfigDto = getQrcConfigManagerBm().configurAttributedTo( qrcConfigForm, user );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    List<CrmQrcAttributedToPojo> attributedToPojos = getQrcConfigManagerBm()
                            .getAttributedToList( qrcConfigForm.getQrcResolutionCodeId() );
                    if ( CommonValidator.isValidCollection( attributedToPojos ) )
                    {
                        qrcConfigForm.setAttributedToPojos( attributedToPojos );
                    }
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_ATTRIBUTED_TO ) );
                }
                else if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM308.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_DUBLICATE_VALUE_FOUND, qrcConfigDto
                                        .getCrmQrcAttributedToPojo().getAttributedTo() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while save attributedToPage ", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    public ActionForward functionalBinMappingPage( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
    {
        return null;
    }

    public ActionForward addRowSubSubCat( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigFormHelper.validateSubSubCategory( errors, qrcConfigForm );
        if ( errors.isEmpty() )
        {
            CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
            crmQrcSubSubCategoriesPojo.setModificationAllowed( IAppConstants.YES_CHAR );
            crmQrcSubSubCategoriesPojo.setQrcSubCategoryId( qrcConfigForm.getQrcSubCategoryId() );
            crmQrcSubSubCategoriesPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            if ( CommonValidator.isValidCollection( qrcConfigForm.getQrcSubSubCategoriesPojos() ) )
            {
                qrcConfigForm.getQrcSubSubCategoriesPojos().add( 0, crmQrcSubSubCategoriesPojo );
            }
            else
            {
                qrcConfigForm.setQrcSubSubCategoriesPojos( new ArrayList<CrmQrcSubSubCategoriesPojo>() );
                qrcConfigForm.getQrcSubSubCategoriesPojos().add( 0, crmQrcSubSubCategoriesPojo );
            }
        }
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_SUB_SUB_CAT_PAGE );
    }

    public ActionForward removeRowSubSubCat( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        int index = Integer.parseInt( inRequest.getParameter( "index" ) );
        if ( index > -1 )
        {
            qrcConfigForm.getQrcSubSubCategoriesPojos().remove( index );
        }
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_SUB_SUB_CAT_PAGE ).getPath(),
                                  true );
    }

    public ActionForward getAttributedTo( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        if ( qrcConfigForm.getQrcResolutionCodeId() > 0 )
        {
            List<CrmQrcAttributedToPojo> attributedToListPojo = getQrcConfigManagerBm()
                    .getAttributedToList( qrcConfigForm.getQrcResolutionCodeId() );
            if ( CommonValidator.isValidCollection( attributedToListPojo ) )
            {
                qrcConfigForm.setAttributedToPojos( attributedToListPojo );
            }
            else
            {
                qrcConfigForm.setAttributedToPojos( new ArrayList<CrmQrcAttributedToPojo>() );
            }
            List<CrmQrcActionTakenPojo> qrcRcaPojos = getQrcConfigManagerBm()
                    .getQrcRcaPojos( qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getServiceName(), null );
            if ( CommonValidator.isValidCollection( qrcRcaPojos ) )
            {
                qrcConfigForm.setQrcActionTakenPojos( qrcRcaPojos );
            }
            List<CrmQrcRootCausePojo> resolutionCodePojos = getQrcConfigManagerBm()
                    .getCrmQrcResolutionCodePojos( qrcConfigForm.getQrcRcaId() );
            if ( CommonValidator.isValidCollection( resolutionCodePojos ) )
            {
                qrcConfigForm.setQrcRootCausePojos( resolutionCodePojos );
                LOGGER.info( "in getResolutionCodes. list size: " + resolutionCodePojos.size() );
            }
        }
        else
        {
            qrcConfigForm.setQrcActionTakenPojos( null );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RESOLUTION_CODE ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_ATTRIBUTED_TO_PAGE );
    }

    public ActionForward addRCAConfiguration( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigFormHelper.validateGetQrcRcaConfigurations( errors, qrcConfigForm );
        if ( errors.isEmpty() )
        {
            CrmQrcActionTakenPojo qrcRcaPojo = new CrmQrcActionTakenPojo();
            qrcRcaPojo.setQrcCategoryId( qrcConfigForm.getQrcCategoryId() );
            qrcRcaPojo.setServiceName( qrcConfigForm.getServiceName() );
            qrcRcaPojo.setStatus( "A" );
            qrcRcaPojo.setEditable( true );
            if ( CommonValidator.isValidCollection( qrcConfigForm.getQrcActionTakenPojos() ) )
            {
                qrcConfigForm.getQrcActionTakenPojos().add( 0, qrcRcaPojo );
            }
            else
            {
                qrcConfigForm.setQrcActionTakenPojos( new ArrayList<CrmQrcActionTakenPojo>() );
                qrcConfigForm.getQrcActionTakenPojos().add( 0, qrcRcaPojo );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RCA_PAGE );
    }

    public ActionForward removeRCAConfiguration( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        int index = Integer.parseInt( inRequest.getParameter( "index" ) );
        if ( index > -1 )
        {
            qrcConfigForm.getQrcActionTakenPojos().remove( index );
        }
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_RCA_PAGE ).getPath(), true );
    }

    /*
     ***********************************************
     *  QRC RCA Configurations Ends Here
     ***********************************************
     *  QRC Bin Mapping Configuration Starts Here
     ***********************************************   
     */
    public ActionForward configFunctionalBinPage( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcConfigAction, configFunctionalBinPage" );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        qrcConfigForm.setFunctionBins( CRMCacheManager.getQRCFunctionalBins() );
        qrcConfigForm.setQrcType( null );
        qrcConfigForm.setQrcCategoryId( 0 );
        qrcConfigForm.setQrcSubCategoryId( 0 );
        qrcConfigForm.setQrcSubSubCategoryId( 0 );
        qrcConfigForm.setResolutionStr( null );
        qrcConfigForm.setQrcSubCategoriesPojos( null );
        qrcConfigForm.setQrcSubSubCategoriesPojos( null );
        qrcConfigForm.setCategoryBinMappingPojos( null );
        qrcConfigForm.setQrcType( "R" );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_FUNCTIONAL_BIN_PAGE );
    }

    public ActionForward getQrcBinConfigurations( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        boolean isValid = QrcConfigFormHelper.validateGetQrcBinConfigurations( errors, qrcConfigForm );
        if ( isValid )
        {
            qrcConfigForm.setQrcSubCategoriesPojos( QRCCacheManager.getActiveQrcSubCategories( qrcConfigForm
                    .getQrcCategoryId() ) );
            qrcConfigForm.setQrcSubSubCategoriesPojos( QRCCacheManager.getActiveQrcSubSubCategories( qrcConfigForm
                    .getQrcType(), qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getQrcSubCategoryId() ) );
            List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos = getQrcConfigManagerBm()
                    .getBinMappings( qrcConfigForm.getQrcSubSubCategoryId() );
            if ( CommonValidator.isValidCollection( categoryBinMappingPojos ) )
            {
                qrcConfigForm.setCategoryBinMappingPojos( categoryBinMappingPojos );
            }
            else
            {
                qrcConfigForm.setCategoryBinMappingPojos( new ArrayList<CrmQrcCategoryBinMappingPojo>() );
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BIN_CONFIGURATIONS ) );
            }
        }
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        LOGGER.info( "QRC Category Id::::" + qrcConfigForm.getQrcCategoryId() );
        qrcConfigForm
                .setQrcSubCategories( QRCCacheManager.getActiveQrcSubCategories( qrcConfigForm.getQrcCategoryId() ) );
        qrcConfigForm
                .setQrcSubSubCategories( QRCCacheManager.getActiveQrcSubSubCategories( qrcConfigForm.getQrcType(),
                                                                                       qrcConfigForm.getQrcCategoryId(),
                                                                                       qrcConfigForm
                                                                                               .getQrcSubCategoryId() ) );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_FUNCTIONAL_BIN_PAGE );
    }

    public ActionForward addBinConfiguration( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        boolean isValid = QrcConfigFormHelper.validateGetQrcBinConfigurations( errors, qrcConfigForm );
        LOGGER.info( "Inside addBinConfiguration is Valid" + isValid );
        if ( isValid )
        {
            CrmQrcCategoryBinMappingPojo binMappingPojo = new CrmQrcCategoryBinMappingPojo();
            binMappingPojo.setQrcSubSubCategoryId( qrcConfigForm.getQrcSubSubCategoryId() );
            binMappingPojo.setEditable( true );
            binMappingPojo.setStatus( "A" );
            if ( CommonValidator.isValidCollection( qrcConfigForm.getCategoryBinMappingPojos() ) )
            {
                qrcConfigForm.getCategoryBinMappingPojos().add( 0, binMappingPojo );
            }
            else
            {
                binMappingPojo.setFromBinId( getFunctionalBinID( qrcConfigForm.getQrcCategoryId(),
                                                                 qrcConfigForm.getQrcSubCategoryId(),
                                                                 qrcConfigForm.getQrcSubSubCategoryId() ) );
                qrcConfigForm.setCategoryBinMappingPojos( new ArrayList<CrmQrcCategoryBinMappingPojo>() );
                qrcConfigForm.getCategoryBinMappingPojos().add( 0, binMappingPojo );
            }
        }
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        LOGGER.info( "QRC Category Id::::" + qrcConfigForm.getQrcCategoryId() );
        qrcConfigForm
                .setQrcSubCategories( QRCCacheManager.getActiveQrcSubCategories( qrcConfigForm.getQrcCategoryId() ) );
        qrcConfigForm
                .setQrcSubSubCategories( QRCCacheManager.getActiveQrcSubSubCategories( qrcConfigForm.getQrcType(),
                                                                                       qrcConfigForm.getQrcCategoryId(),
                                                                                       qrcConfigForm
                                                                                               .getQrcSubCategoryId() ) );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_CONFIG_FUNCTIONAL_BIN_PAGE );
    }

    public ActionForward removeBinConfiguration( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        QrcConfigForm qrcConfigForm = (QrcConfigForm) inForm;
        int index = Integer.parseInt( inRequest.getParameter( "index" ) );
        if ( index > -1 )
        {
            qrcConfigForm.getCategoryBinMappingPojos().remove( index );
        }
        qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
        qrcConfigForm
                .setQrcSubCategories( QRCCacheManager.getActiveQrcSubCategories( qrcConfigForm.getQrcCategoryId() ) );
        qrcConfigForm
                .setQrcSubSubCategories( QRCCacheManager.getActiveQrcSubSubCategories( qrcConfigForm.getQrcType(),
                                                                                       qrcConfigForm.getQrcCategoryId(),
                                                                                       qrcConfigForm
                                                                                               .getQrcSubCategoryId() ) );
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_CONFIG_FUNCTIONAL_BIN_PAGE )
                .getPath(), true );
    }

    public ActionForward configureQrcFB( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcConfigAction, configureQrcFB" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        String target = IActionForwardConstant.QRC_CONFIG_FUNCTIONAL_BIN_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcConfigDto qrcConfigDto = null;
        QrcConfigForm qrcConfigForm = null;
        List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos = null;
        int count = 0;
        boolean process = true;
        try
        {
            PojoComparator<CrmQrcCategoryBinMappingPojo> pojoComparator = new PojoComparator<CrmQrcCategoryBinMappingPojo>();
            qrcConfigForm = (QrcConfigForm) inForm;
            QrcConfigFormHelper.validateDuplicateBinConfigurations( errors, qrcConfigForm );
            List<CrmQrcCategoryBinMappingPojo> dbBinMappingPojos = getQrcConfigManagerBm()
                    .getBinMappings( qrcConfigForm.getQrcSubSubCategoryId() );
            LOGGER.info( "Form POJO:::" + qrcConfigForm.getCategoryBinMappingPojos().size() );
            for ( CrmQrcCategoryBinMappingPojo crmBinPojo : qrcConfigForm.getCategoryBinMappingPojos() )
            {
                count = validateModifiedFBin( count, pojoComparator, dbBinMappingPojos, crmBinPojo );
            }
            if ( count > 0 )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
                process = false;
            }
            if ( errors.isEmpty() && process )
            {
                qrcConfigDto = new QrcConfigDto();
                qrcConfigDto.setLoggedUser( user );
                if ( ( StringUtils.isValidObj( qrcConfigForm.getCategoryBinMappingPojos() ) )
                        && ( !qrcConfigForm.getCategoryBinMappingPojos().isEmpty() ) )
                {
                    qrcConfigDto.getCategoryBinMappingPojos().addAll( qrcConfigForm.getCategoryBinMappingPojos() );
                    qrcConfigDto = getQrcConfigManagerBm().configurBinMapping( qrcConfigDto );
                    if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.isValidObj( qrcConfigDto.getCategoryBinMappingPojos() )
                                && !qrcConfigDto.getCategoryBinMappingPojos().isEmpty() )
                        {
                            categoryBinMappingPojos = qrcConfigDto.getCategoryBinMappingPojos();
                            qrcConfigForm.setCategoryBinMappingPojos( categoryBinMappingPojos );
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MSG_BIN_CONFIGURATION ) );
                        }
                        else
                        {
                            categoryBinMappingPojos = new ArrayList<CrmQrcCategoryBinMappingPojo>();
                            qrcConfigForm.setCategoryBinMappingPojos( categoryBinMappingPojos );
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcConfigDto.getStatusCode() ) );
                    }
                    LOGGER.info( "Service Status : " + qrcConfigDto.getStatusCode() );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
                }
            }
            //  qrcConfigForm.setQrcCategories( QRCCacheManager.getActiveQrcCategories() );
            qrcConfigForm.setQrcSubCategories( QRCCacheManager.getActiveQrcSubCategories( qrcConfigForm
                    .getQrcCategoryId() ) );
            qrcConfigForm.setQrcSubSubCategories( QRCCacheManager.getActiveQrcSubSubCategories( qrcConfigForm
                    .getQrcType(), qrcConfigForm.getQrcCategoryId(), qrcConfigForm.getQrcSubCategoryId() ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while changing Bin configurations ", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    private int validateModifiedFBin( int count,
                                      PojoComparator<CrmQrcCategoryBinMappingPojo> pojoComparator,
                                      List<CrmQrcCategoryBinMappingPojo> dbBinMappingPojos,
                                      CrmQrcCategoryBinMappingPojo crmBinPojo )
    {
        int index;
        CrmQrcCategoryBinMappingPojo tmpBinPojo;
        if ( StringUtils.isValidObj( dbBinMappingPojos ) )
        {
            index = dbBinMappingPojos.indexOf( crmBinPojo );
            LOGGER.info( "Index:::" + index );
            if ( crmBinPojo.getMappingId() > 0 && index > -1 && crmBinPojo.isEditable() )
            {
                tmpBinPojo = dbBinMappingPojos.get( index );
                tmpBinPojo.setEditable( true );
                if ( ( pojoComparator.compare( crmBinPojo, tmpBinPojo ) == 0 ) )
                {
                    count++;
                }
            }
        }
        return count;
    }

    private long getFunctionalBinID( long inCategoryId, long inSubCategoryId, long inSubSubCategoryId )
    {
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( null, inCategoryId, inSubCategoryId );
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
        subSubCategoriesPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
        int pojoIndex = subSubCategories.indexOf( subSubCategoriesPojo );
        if ( pojoIndex > -1 )
        {
            subSubCategoriesPojo = subSubCategories.get( pojoIndex );
            return subSubCategoriesPojo.getFunctionalBinId();
        }
        return 0L;
    }
}
