package com.np.tele.crm.qrc.config.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.qrc.config.forms.QrcConfigForm;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.QrcConfigDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QrcConfigManagerImpl
    implements IQrcConfigManager
{
    private static final Logger LOGGER           = Logger.getLogger( QrcConfigManagerImpl.class );
    private CrmQrcService       qrcServiceClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    @Override
    public List<CrmQrcActionTakenPojo> getQrcRcaPojos( long inCategoryId, String inService, String status )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl, getQrcRcaPojos" );
        List<CrmQrcActionTakenPojo> qrcRcaPojos = null;
        QrcConfigDto qrcConfigDto = null;
        CrmQrcActionTakenPojo crmQrcRcaPojo = null;
        try
        {
            if ( inCategoryId > 0 && !StringUtils.isEmpty( inService ) )
            {
                qrcConfigDto = new QrcConfigDto();
                crmQrcRcaPojo = new CrmQrcActionTakenPojo();
                crmQrcRcaPojo.setQrcCategoryId( inCategoryId );
                crmQrcRcaPojo.setServiceName( inService );
                if ( StringUtils.isValidObj( status ) )
                {
                    crmQrcRcaPojo.setStatus( status );
                }
                qrcConfigDto.setQrcActionTakenPojo( crmQrcRcaPojo );
                qrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.LIST.getParameter(),
                                                                          CRMParameter.QRC_RCA_CONFIGURATIONS
                                                                                  .getParameter(), qrcConfigDto );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcRcaPojos = qrcConfigDto.getQrcActionTakenPojos();
                    LOGGER.info( "QrcRcaPojos size : " + qrcRcaPojos.size() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting QRC RCA Pojos ", ex );
        }
        return qrcRcaPojos;
    }

    @Override
    public List<CrmQrcRootCausePojo> getCrmQrcResolutionCodePojos( long inRcaId )
    {
        LOGGER.info( "Inside getCrmQrcResolutionCodePojos( " + inRcaId + " )" );
        List<CrmQrcRootCausePojo> qrcResolutionCodePojos = null;
        QrcConfigDto qrcConfigDto = null;
        CrmQrcRootCausePojo qrcResolutionCodePojo = null;
        if ( inRcaId > 0 )
        {
            qrcConfigDto = new QrcConfigDto();
            qrcResolutionCodePojo = new CrmQrcRootCausePojo();
            qrcResolutionCodePojo.setActionId( inRcaId );
            qrcConfigDto.setQrcRootCausePojo( qrcResolutionCodePojo );
            try
            {
                qrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.LIST.getParameter(),
                                                                          CRMParameter.QRC_RESOLUTION_CODE
                                                                                  .getParameter(), qrcConfigDto );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcResolutionCodePojos = qrcConfigDto.getQrcRootCausePojos();
                    LOGGER.info( "qrcResolutionCodePojos size: " + qrcResolutionCodePojos.size() );
                }
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "exception while getting qrcResolutionCode", ex );
            }
        }
        return qrcResolutionCodePojos;
    }

    @Override
    public QrcConfigDto configurRCA( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl, configurRCA" );
        try
        {
            inQrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.CREATE.getParameter(),
                                                                        CRMParameter.QRC_RCA_CONFIGURATIONS
                                                                                .getParameter(), inQrcConfigDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring RCA ", ex );
        }
        return inQrcConfigDto;
    }

    @Override
    public List<CrmQrcAttributedToPojo> getAttributedToList( long qrcResolutionCodeId )
    {
        LOGGER.info( "Inside getAttributedToList( " + qrcResolutionCodeId + " )" );
        List<CrmQrcAttributedToPojo> qrcAttributedToPojos = null;
        QrcConfigDto qrcConfigDto = null;
        CrmQrcAttributedToPojo qrcAttributedToPojo = null;
        if ( qrcResolutionCodeId > 0 )
        {
            qrcConfigDto = new QrcConfigDto();
            qrcAttributedToPojo = new CrmQrcAttributedToPojo();
            qrcAttributedToPojo.setResolutionCodeId( qrcResolutionCodeId );
            qrcConfigDto.setCrmQrcAttributedToPojo( qrcAttributedToPojo );
            try
            {
                qrcConfigDto = getQrcServiceClient()
                        .qrcConfigOperations( ServiceParameter.LIST.getParameter(),
                                              CRMParameter.QRC_ATTRIBUTED_TO.getParameter(), qrcConfigDto );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcAttributedToPojos = qrcConfigDto.getQrcAttributedToPojos();
                    LOGGER.info( "qrcAttributedToPojos size: " + qrcAttributedToPojos.size() );
                }
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "exception while getting getAttributedToList", ex );
            }
        }
        return qrcAttributedToPojos;
    }

    @Override
    public QrcConfigDto configurResolutionCode( QrcConfigForm inQrcConfigForm, String inUser )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl:configurResolutionCode" );
        QrcConfigDto qrcConfigDto = null;
        try
        {
            if ( CommonValidator.isValidCollection( inQrcConfigForm.getQrcRootCausePojos() ) )
            {
                qrcConfigDto = new QrcConfigDto();
                qrcConfigDto.setLoggedUser( inUser );
                qrcConfigDto.getQrcRootCausePojos().addAll( inQrcConfigForm.getQrcRootCausePojos() );
                qrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.CREATE.getParameter(),
                                                                          CRMParameter.QRC_RESOLUTION_CODE
                                                                                  .getParameter(), qrcConfigDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring configurAttributedTo ", ex );
        }
        return qrcConfigDto;
    }

    @Override
    public QrcConfigDto configurAttributedTo( QrcConfigForm qrcConfigForm, String inUser )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl, configurAttributedTo" );
        QrcConfigDto qrcConfigDto = null;
        try
        {
            if ( CommonValidator.isValidCollection( qrcConfigForm.getAttributedToPojos() ) )
            {
                qrcConfigDto = new QrcConfigDto();
                qrcConfigDto.setLoggedUser( inUser );
                qrcConfigDto.getQrcAttributedToPojos().addAll( qrcConfigForm.getAttributedToPojos() );
                qrcConfigDto = getQrcServiceClient()
                        .qrcConfigOperations( ServiceParameter.CREATE.getParameter(),
                                              CRMParameter.QRC_ATTRIBUTED_TO.getParameter(), qrcConfigDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring configurAttributedTo ", ex );
        }
        return qrcConfigDto;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategories( long subCategoryId, String inModuleType )
    {
        CrmQrcDto qrcDto = null;
        List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
        List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos = null;
        List<CrmQrcSubSubCategoriesPojo> subSubCategoriesPojos = null;
        try
        {
            qrcDto = new CrmQrcDto();
            CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo = new CrmQrcSubCategoriesPojo();
            crmQrcSubCategoriesPojo.setQrcSubCategoryId( subCategoryId );
            qrcDto.setCrmQrcSubCategoriesPojo( crmQrcSubCategoriesPojo );
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.TRACK.getParameter(),
                                                          CRMParameter.QRCSUBCATEGORY.getParameter(), qrcDto );
            qrcSubCategoriesPojos = qrcDto.getCrmQrcSubCategoriesPojos();
            if ( CommonValidator.isValidCollection( qrcSubCategoriesPojos ) )
            {
                qrcSubSubCategoriesPojos = qrcSubCategoriesPojos.get( 0 ).getCrmQrcSubSubCategorieses();
                if ( StringUtils.isEmpty( inModuleType ) )
                {
                    return qrcSubSubCategoriesPojos;
                }
                else
                {
                    subSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
                    for ( CrmQrcSubSubCategoriesPojo subSubCatPojo : qrcSubSubCategoriesPojos )
                    {
                        if ( StringUtils.equals( subSubCatPojo.getModuleType(), inModuleType )
                                && StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                        {
                            subSubCategoriesPojos.add( subSubCatPojo );
                        }
                        else if ( !StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                        {
                            subSubCategoriesPojos.add( subSubCatPojo );
                        }
                    }
                }
                LOGGER.info( "qrcsubsubCat SIZE:" + subSubCategoriesPojos.size() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in retriving QRC Categories Pojos: ", ex );
        }
        return subSubCategoriesPojos;
    }

    @Override
    public List<CrmQrcCategoryBinMappingPojo> getBinMappings( long inQrcSubSubCategoryId )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl, getBinMappings" );
        List<CrmQrcCategoryBinMappingPojo> binMappingPojos = null;
        QrcConfigDto qrcConfigDto = null;
        CrmQrcCategoryBinMappingPojo binMappingPojo = null;
        try
        {
            if ( inQrcSubSubCategoryId > 0 )
            {
                qrcConfigDto = new QrcConfigDto();
                binMappingPojo = new CrmQrcCategoryBinMappingPojo();
                binMappingPojo.setQrcSubSubCategoryId( inQrcSubSubCategoryId );
                qrcConfigDto.setCategoryBinMappingPojo( binMappingPojo );
                qrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.LIST.getParameter(),
                                                                          CRMParameter.QRC_FB_MAPPINGS.getParameter(),
                                                                          qrcConfigDto );
                if ( StringUtils.equals( qrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( !qrcConfigDto.getCategoryBinMappingPojos().isEmpty() )
                    {
                        binMappingPojos = qrcConfigDto.getCategoryBinMappingPojos();
                    }
                    else
                    {
                        binMappingPojos = new ArrayList<CrmQrcCategoryBinMappingPojo>();
                        binMappingPojos.add( binMappingPojo );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting QRC RCA Pojos ", ex );
        }
        return binMappingPojos;
    }

    @Override
    public QrcConfigDto configurBinMapping( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerImpl, configurRCA" );
        try
        {
            inQrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.CREATE.getParameter(),
                                                                        CRMParameter.QRC_FB_MAPPINGS.getParameter(),
                                                                        inQrcConfigDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring RCA ", ex );
        }
        return inQrcConfigDto;
    }

    //code for qrcsubsubcat saveSubSubCat(){
    @Override
    public QrcConfigDto configureSubSubCat( QrcConfigDto qrcConfigDto )
    {
        LOGGER.info( "Inside configureSubSubCat, configureSubSubCat" );
        try
        {
            if ( CommonValidator.isValidCollection( qrcConfigDto.getCrmQrcSubSubCategoriesPojos() ) )
            {
                qrcConfigDto = getQrcServiceClient().qrcConfigOperations( ServiceParameter.CREATE.getParameter(),
                                                                          CRMParameter.SUB_SUB_CATEGORY.getParameter(),
                                                                          qrcConfigDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring configurAttributedTo ", ex );
        }
        return qrcConfigDto;
    }
}
