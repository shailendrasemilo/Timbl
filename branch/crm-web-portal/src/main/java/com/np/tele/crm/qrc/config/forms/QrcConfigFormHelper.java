package com.np.tele.crm.qrc.config.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.utils.ICRMCacheManager;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcConfigFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( QrcConfigFormHelper.class );

    public static void reset( QrcConfigForm inQrcConfigForm, String inMethod )
    {
        if ( StringUtils.equals( inMethod, IAppConstants.METHOD_ADD_ROW_RESOLUTION_CODE ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getQrcRootCausePojos() ) )
            {
                for ( CrmQrcRootCausePojo resolutionCodePojo : inQrcConfigForm.getQrcRootCausePojos() )
                {
                    if ( resolutionCodePojo.getRootCauseId() > 0 )
                        resolutionCodePojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_REMOVE_ROW_RESOLUTION_CODE ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getQrcRootCausePojos() ) )
            {
                for ( CrmQrcRootCausePojo resolutionCodePojo : inQrcConfigForm.getQrcRootCausePojos() )
                {
                    if ( resolutionCodePojo.getRootCauseId() > 0 )
                        resolutionCodePojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_CONFIG_RESOLUTION_CODE ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getQrcRootCausePojos() ) )
            {
                for ( CrmQrcRootCausePojo resolutionCodePojo : inQrcConfigForm.getQrcRootCausePojos() )
                {
                    if ( resolutionCodePojo.getRootCauseId() > 0 )
                        resolutionCodePojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_ADD_ROW_ATTRIBUTED_TO ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getAttributedToPojos() ) )
            {
                for ( CrmQrcAttributedToPojo attributedToPojo : inQrcConfigForm.getAttributedToPojos() )
                {
                    if ( attributedToPojo.getAttributedToId() > 0 )
                        attributedToPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_REMOVE_ROW__ATTRIBUTED_TO ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getAttributedToPojos() ) )
            {
                for ( CrmQrcAttributedToPojo attributedToPojo : inQrcConfigForm.getAttributedToPojos() )
                {
                    if ( attributedToPojo.getAttributedToId() > 0 )
                        attributedToPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_CREATE_ATTRIBUTED_TO ) )
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getAttributedToPojos() ) )
            {
                for ( CrmQrcAttributedToPojo attributedToPojo : inQrcConfigForm.getAttributedToPojos() )
                {
                    if ( attributedToPojo.getAttributedToId() > 0 )
                        attributedToPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_CONFIGURE_SUB_SUB_CAT_PAGE ) )
        {
            inQrcConfigForm.setQrcSubSubCategoriesPojos( null );
            inQrcConfigForm.setQrcCategoryId( 0l );
            inQrcConfigForm.setQrcSubCategoryId( 0l );
            inQrcConfigForm.setModuleType( null );
        }
        else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_CREATE_ATTRIBUTED_TO_PAGE ) )
        {
            inQrcConfigForm.setAttributedToPojos( new ArrayList<CrmQrcAttributedToPojo>() );
        }
    }

    public static void validateGetQrcRcaConfigurations( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil
                .validateForm( inQrcConfigForm, ICRMValidationCriteriaUtil.FORM_QRC_CONFIG_GET_RCA_CONFIGURATION_CHECK,
                               false );
        if ( StringUtils.isValidObj( resultMap ) )
        {
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }

    public static boolean validateGetQrcBinConfigurations( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        boolean isValid = true;
        Map<String, Object[]> resultMap = null;
        if ( !inQrcConfigForm.getResolutionStr().contains( "Forward" ) )
        {
            inErrors.add( IAppConstants.APP_ERROR,
                          new ActionMessage( IPropertiesConstant.ROL_BIN_CONFIGURATIONS_CANNOT_CONFIGURE ) );
            isValid = false;
        }
        else
        {
            resultMap = ValidationPojoUtil
                    .validateForm( inQrcConfigForm,
                                   ICRMValidationCriteriaUtil.FORM_QRC_CONFIG_GET_BIN_CONFIGURATION_CHECK, false );
            if ( StringUtils.isValidObj( resultMap ) )
            {
                isValid = false;
                ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            }
        }
        return isValid;
    }

    public static void validateDuplicateRcaConfigurations( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        LOGGER.info( "Inside QrcConfigFormHelper, validateDuplicateRcaConfigurations" );
        List<CrmQrcActionTakenPojo> qrcRcaPojos = null;
        Set<String> uniqueRcaSet = null;
        try
        {
            qrcRcaPojos = inQrcConfigForm.getQrcActionTakenPojos();
            if ( !CommonValidator.isValidCollection( qrcRcaPojos ) )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RCA ) );
            }
            else
            {
                boolean flag = false;
                uniqueRcaSet = new HashSet<String>();
                for ( CrmQrcActionTakenPojo rcaPojo : qrcRcaPojos )
                {
                    if ( rcaPojo.isEditable() )
                    {
                        flag = true;
                        break;
                    }
                    LOGGER.info( "is editable :::" + flag );
                }
                if ( flag )
                {
                    for ( CrmQrcActionTakenPojo rcaPojo : qrcRcaPojos )
                    {
                        if ( StringUtils.isEmpty( rcaPojo.getActionTaken() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RCA_VALUE, rcaPojo
                                                  .getActionTaken() ) );
                            break;
                        }
                        else if ( StringUtils.isEmpty( rcaPojo.getStatus() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RCA_STATUS, rcaPojo
                                                  .getActionTaken() ) );
                            break;
                        }
                        else
                        {
                            boolean isUnique = uniqueRcaSet.add( rcaPojo.getActionTaken() );
                            if ( !isUnique )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( CRMServiceCode.CRM990.getStatusCode(), rcaPojo
                                                      .getActionTaken(), "Action Taken" ) );
                                break;
                            }
                        }
                    }
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM991.getStatusCode(),
                                                                              "Action Taken" ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while validating duplicate and empty RCA values", ex );
        }
    }

    public static void validateConfigResolutionCode( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inQrcConfigForm, ICRMValidationCriteriaUtil.FORM_CONFIG_RESOLUTION_CODE, false );
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }

    public static ActionErrors validate( QrcConfigForm qrcConfigForm, String method )
    {
        ActionErrors actionError = new ActionErrors();
        /* if ( StringUtils.equals( IAppConstants.METHOD_CREATE_ATTRIBUTED_TO, method ) )
         {
             if ( StringUtils.isValidObj( qrcConfigForm.getAttributedToPojos() )
                     && !qrcConfigForm.getAttributedToPojos().isEmpty() )
             {
                 Set<String> crmAttributedToPojosSet = new HashSet<String>();
                 int editableCounter = 0;
                 for ( CrmQrcAttributedToPojo crmAttributedToPojo : qrcConfigForm.getAttributedToPojos() )
                 {
                     if ( crmAttributedToPojo.isEditable() )
                     {
                         editableCounter++;
                     }
                     if ( StringUtils.isBlank( crmAttributedToPojo.getAttributedTo() ) )
                     {
                         actionError.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ATTRIBUTED_TO_VALUE ) );
                         break;
                     }
                     else if ( !crmAttributedToPojosSet.add( crmAttributedToPojo.getAttributedTo() ) )
                     {
                         actionError.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( CRMServiceCode.CRM990.getStatusCode(), crmAttributedToPojo
                                                  .getAttributedTo(), "Attributed To" ) );
                         break;
                     }
                 }
                 if ( editableCounter <= 0 )
                 {
                     actionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM991.getStatusCode(),
                                                                                  "Attributed To" ) );
                 }
             }
             else
             {
                 actionError.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ATTRIBUTED_TO_VALUE ) );
             }
         }*/
        return actionError;
    }

    public static void validateDuplicateBinConfigurations( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        LOGGER.info( "Inside QrcConfigFormHelper, validateDuplicateBinConfigurations" );
        List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos = null;
        Set<String> uniqueMappingSet = null;
        Set<Long> uniqueFromBinSet = null;
        ICRMCacheManager crmCacheManager = null;
        try
        {
            categoryBinMappingPojos = inQrcConfigForm.getCategoryBinMappingPojos();
            if ( !StringUtils.isValidObj( categoryBinMappingPojos ) || categoryBinMappingPojos.isEmpty() )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BIN_CONFIGURATIONS ) );
            }
            if ( StringUtils.isValidObj( categoryBinMappingPojos ) && !categoryBinMappingPojos.isEmpty() )
            {
                uniqueMappingSet = new HashSet<String>();
                uniqueFromBinSet = new HashSet<Long>();
                crmCacheManager = new CRMCacheManager();
                boolean flag = false;
                for ( CrmQrcCategoryBinMappingPojo binMappingPojo : categoryBinMappingPojos )
                {
                    if ( binMappingPojo.isEditable() )
                    {
                        flag = true;
                        break;
                    }
                }
                LOGGER.info( "is editable::::::::::::::::::::::" + flag );
                if ( flag )
                {
                    for ( CrmQrcCategoryBinMappingPojo binMappingPojo : categoryBinMappingPojos )
                    {
                        if ( binMappingPojo.isEditable() )
                        {
                            if ( StringUtils.isEmpty( binMappingPojo.getStatus() ) )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BIN_STATUS,
                                                                 crmCacheManager
                                                                         .getDisplayValue( "functionalBins", String
                                                                                 .valueOf( binMappingPojo
                                                                                         .getFromBinId() ) ),
                                                                 crmCacheManager.getDisplayValue( "functionalBins",
                                                                                                  String.valueOf( binMappingPojo
                                                                                                          .getToBinId() ) ) ) );
                                break;
                            }
                            else if ( binMappingPojo.getFromBinId() == binMappingPojo.getToBinId() )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_SAME_BIN ) );
                                break;
                            }
                        }
                        else
                        {
                            boolean isUniqueMapping = uniqueMappingSet.add( binMappingPojo.getFromBinId()
                                    + IAppConstants.UNDERSCORE + binMappingPojo.getToBinId() );
                            if ( !isUniqueMapping )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_BIN_VALUE,
                                                                 crmCacheManager
                                                                         .getDisplayValue( "functionalBins", String
                                                                                 .valueOf( binMappingPojo
                                                                                         .getFromBinId() ) ),
                                                                 crmCacheManager.getDisplayValue( "functionalBins",
                                                                                                  String.valueOf( binMappingPojo
                                                                                                          .getToBinId() ) ) ) );
                                break;
                            }
                            //                        else
                            //                        {
                            //                            boolean isUniqueFromBin = uniqueFromBinSet.add( binMappingPojo.getFromBinId() );
                            //                            if ( !isUniqueFromBin )
                            //                            {
                            //                                inErrors.add( IAppConstants.APP_ERROR,
                            //                                              new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_FROM_VALUE,
                            //                                                                 crmCacheManager
                            //                                                                         .getDisplayValue( "functionalBins", String
                            //                                                                                 .valueOf( binMappingPojo
                            //                                                                                         .getFromBinId() ) ) ) );
                            //                                isValid = false;
                            //                                break;
                            //                            }
                            //                        }
                        }
                    }
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM991.getStatusCode(),
                                                                              "Bin Mapping" ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while validating duplicate bin values", ex );
        }
    }

    public static void validateSubSubCategory( ActionMessages inErrors,
                                               List<CrmQrcSubSubCategoriesPojo> subsubCategoriesPojos )
    {
        if ( CommonValidator.isValidCollection( subsubCategoriesPojos ) )
        {
            for ( CrmQrcSubSubCategoriesPojo crmSubSubCatPojo : subsubCategoriesPojos )
            {
                Map<String, Object[]> resultMap = null;
                crmSubSubCatPojo.setQrcSubSubCategory( StringUtils.trim( crmSubSubCatPojo.getQrcSubSubCategory() ) );
                resultMap = ValidationPojoUtil.validateForm( crmSubSubCatPojo,
                                                             ICRMValidationCriteriaUtil.FORM_CONFIG_SUB_SUB_CAT_POJO,
                                                             false );
                if ( StringUtils.isValidObj( resultMap ) )
                {
                    LOGGER.info( crmSubSubCatPojo );
                    ValidationUtil.prepareErrorMessage( inErrors, resultMap );
                    break;
                }
            }
        }
        else
        {
            LOGGER.info( "no subsubcat pojos found" );
            inErrors.add( IAppConstants.APP_ERROR,
                          new ActionMessage( IPropertiesConstant.ERROR_PROVIDE_SUB_SUB_CAT_POJO ) );
        }
    }

    public static void validateSubSubCategory( ActionMessages inErrors, QrcConfigForm inQrcConfigForm )
    {
        if ( StringUtils.isValidObj( inQrcConfigForm ) )
        {
            Map<String, Object[]> resultMap = null;
            resultMap = ValidationPojoUtil.validateForm( inQrcConfigForm,
                                                         ICRMValidationCriteriaUtil.FORM_CONFIG_SUB_SUB_CAT, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }
}