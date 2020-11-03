package com.np.tele.crm.qrc.config.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.QrcConfigDto;
import com.np.tele.crm.qrc.config.dao.IQrcConfigManagerDao;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcConfigBusinessImpl
    implements IQrcConfigBusiness
{
    private static final Logger  LOGGER              = Logger.getLogger( QrcConfigBusinessImpl.class );
    private IQrcConfigManagerDao qrcConfigManagerDao = null;

    public IQrcConfigManagerDao getQrcConfigManagerDao()
    {
        return qrcConfigManagerDao;
    }

    public void setQrcConfigManagerDao( IQrcConfigManagerDao inQrcConfigManagerDao )
    {
        qrcConfigManagerDao = inQrcConfigManagerDao;
    }

    private void invalidRequest( QrcConfigDto inQrcConfigDto )
    {
        inQrcConfigDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        inQrcConfigDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
    }

    @Override
    public QrcConfigDto qrcConfigOperations( String inServiceParam, String inCrmParam, QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, qrcConfigOperations" );
        invalidRequest( inQrcConfigDto );
        if ( StringUtils.equals( inServiceParam, ServiceParameter.LIST.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_RCA_CONFIGURATIONS.getParameter() ) )
            {
                inQrcConfigDto = getQrcRcaPojos( inQrcConfigDto );
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_RESOLUTION_CODE.getParameter() ) )
            {
                inQrcConfigDto = getQrcResolutionCodePojos( inQrcConfigDto );
            }
            //            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_ATTRIBUTED_TO.getParameter() ) )
            //            {
            //                if ( validateResolutionData( inQrcConfigDto ) )
            //                {
            //                    inQrcConfigDto = getQrcConfigManagerDao().getQrcAttributedToPojos( inQrcConfigDto );
            //                }
            //            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRCSUBCATEGORY.getParameter() ) )
            {
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_FB_MAPPINGS.getParameter() ) )
            {
                inQrcConfigDto = getQrcBinMappingPojos( inQrcConfigDto );
            }
        }
        if ( StringUtils.equals( inServiceParam, ServiceParameter.CREATE.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_RCA_CONFIGURATIONS.getParameter() ) )
            {
                inQrcConfigDto = createQrcRcaPojos( inQrcConfigDto );
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_RESOLUTION_CODE.getParameter() ) )
            {
                inQrcConfigDto = configureQrcResolutionCodes( inQrcConfigDto );
            }
            //            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_ATTRIBUTED_TO.getParameter() ) )
            //            {
            //                inQrcConfigDto = createQrcAttributedToPojos( inQrcConfigDto );
            //            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_FB_MAPPINGS.getParameter() ) )
            {
                inQrcConfigDto = createQrcBinMappingPojos( inQrcConfigDto );
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.SUB_SUB_CATEGORY.getParameter() ) )
            {
                inQrcConfigDto = createSubSubCat( inQrcConfigDto );
            }
        }
        return inQrcConfigDto;
    }

    private QrcConfigDto configureQrcResolutionCodes( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl:configureQrcResolutionCodes" );
        if ( CommonValidator.isValidCollection( inQrcConfigDto.getQrcRootCausePojos() ) )
        {
            inQrcConfigDto = getQrcConfigManagerDao().configureResolutionCodePojo( inQrcConfigDto );
        }
        return inQrcConfigDto;
    }

    //    private QrcConfigDto createQrcAttributedToPojos( QrcConfigDto inQrcConfigDto )
    //    {
    //        LOGGER.info( "Inside QrcConfigBusinessImpl, createQrcAttributedToPojos" );
    //        if ( StringUtils.isValidObj( inQrcConfigDto )
    //                && StringUtils.isValidObj( inQrcConfigDto.getQrcAttributedToPojos() )
    //                && !inQrcConfigDto.getQrcAttributedToPojos().isEmpty() )
    //        {
    //            inQrcConfigDto = getQrcConfigManagerDao().createAttributedToConfiguration( inQrcConfigDto );
    //        }
    //        return inQrcConfigDto;
    //    }
    private QrcConfigDto createSubSubCat( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, createQrcAttributedToPojos" );
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && ( CommonValidator.isValidCollection( inQrcConfigDto.getCrmQrcSubSubCategoriesPojos() ) ) )
        {
            inQrcConfigDto = getQrcConfigManagerDao().createSubSubCat( inQrcConfigDto );
        }
        return inQrcConfigDto;
    }

    private boolean validateResolutionData( QrcConfigDto inQrcConfigDto )
    {
        boolean isValid = false;
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getCrmQrcAttributedToPojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inQrcConfigDto.getCrmQrcAttributedToPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_GET_RESOLUTION_ID_CRITERIA ) == null;
            if ( isValid )
            {
                isValid = true;
            }
            else
                invalidRequest( inQrcConfigDto );
        }
        return isValid;
    }

    private QrcConfigDto createQrcRcaPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, createQrcRcaPojos" );
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getQrcActionTakenPojos() )
                && !inQrcConfigDto.getQrcActionTakenPojos().isEmpty() )
        {
            inQrcConfigDto = getQrcConfigManagerDao().createQrcRcaPojos( inQrcConfigDto );
            if ( StringUtils.equals( inQrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                inQrcConfigDto = getQrcRcaPojos( inQrcConfigDto );
            }
        }
        return inQrcConfigDto;
    }

    private QrcConfigDto getQrcRcaPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, getQrcRcaPojos" );
        boolean isValid = false;
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getQrcActionTakenPojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inQrcConfigDto.getQrcActionTakenPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_GET_RCA_CONFIGURATIONS_CRITERIA ) == null;
            if ( isValid )
            {
                inQrcConfigDto = getQrcConfigManagerDao().getRcaConfigurations( inQrcConfigDto );
            }
            else
                invalidRequest( inQrcConfigDto );
        }
        return inQrcConfigDto;
    }

    private QrcConfigDto getQrcResolutionCodePojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, getQrcResolutionCodePojos" );
        boolean isValid = false;
        if ( StringUtils.isValidObj( inQrcConfigDto ) && StringUtils.isValidObj( inQrcConfigDto.getQrcRootCausePojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inQrcConfigDto.getQrcRootCausePojo(),
                                                       ICRMValidationCriteriaUtil.QRC_GET_RESOLUTION_CODE_CRITERIA ) == null;
            if ( isValid )
            {
                inQrcConfigDto = getQrcConfigManagerDao().getQrcResolutionCodePojos( inQrcConfigDto );
            }
            else
                invalidRequest( inQrcConfigDto );
        }
        return inQrcConfigDto;
    }

    private QrcConfigDto getQrcBinMappingPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, getQrcBinMappingPojos" );
        boolean isValid = false;
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getCategoryBinMappingPojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inQrcConfigDto.getCategoryBinMappingPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_GET_BIN_MAPPING_CRITERIA ) == null;
            if ( isValid )
            {
                inQrcConfigDto = getQrcConfigManagerDao().getQrcBinMappingPojos( inQrcConfigDto );
            }
            else
                invalidRequest( inQrcConfigDto );
        }
        return inQrcConfigDto;
    }

    private QrcConfigDto createQrcBinMappingPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigBusinessImpl, createQrcBinMappingPojos" );
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getCategoryBinMappingPojos() )
                && !inQrcConfigDto.getCategoryBinMappingPojos().isEmpty() )
        {
            inQrcConfigDto = getQrcConfigManagerDao().createQrcBinMappingPojos( inQrcConfigDto );
            if ( StringUtils.equals( inQrcConfigDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                inQrcConfigDto = getQrcBinMappingPojos( inQrcConfigDto );
            }
        }
        return inQrcConfigDto;
    }
}
