package com.np.tele.crm.ecaf.businessmgr;

import javax.xml.soap.SOAPException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ECafDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.ecaf.dao.IECafDao;
import com.np.tele.crm.ext.pojos.FileUploader;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class ECafMgrImpl
    implements IECafMgr
{
    private static final Logger LOGGER  = Logger.getLogger( ECafMgrImpl.class );
    private IECafDao            eCafDao = null;

    public IECafDao geteCafDao()
    {
        return eCafDao;
    }

    public void seteCafDao( IECafDao inECafDao )
    {
        eCafDao = inECafDao;
    }

    @Override
    public ECafDto saveECAF( ECafDto inECafDto )
    {
        LOGGER.info( "ECafMgrImpl : saveECAF " );
        String statusCode = "";
        MasterDataDto masterDataDto = null;
        if ( StringUtils.isValidObj( inECafDto ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inECafDto,
                                                          ICRMValidationCriteriaUtil.COMMON_AUTH_ECAF_CRITERIA );
            LOGGER.info( "E-CAF Auth Code ::" + statusCode );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isValidObj( inECafDto.getCustomerDetailsPojo() ) )
                {
                    statusCode = ValidationPojoUtil
                            .validatePojo( inECafDto.getCustomerDetailsPojo(),
                                           ICRMValidationCriteriaUtil.ECAF_CRITERIA_COMMON_FOR_CUSTOMER );
                    if ( StringUtils.isEmpty( statusCode ) )
                    {
                        if ( StringUtils.isValidObj( CRMDisplayListConstants
                                .getValueByCode( CRMParameter.CONNECTION_TYPE.getParameter(), inECafDto
                                        .getCustomerDetailsPojo().getConnectionType() ) ) )
                        {
                            if ( StringUtils.equals( inECafDto.getCustomerDetailsPojo().getConnectionType(),
                                                     CRMDisplayListConstants.INDIVIDUAL.getCode() )
                                    || StringUtils.equals( inECafDto.getCustomerDetailsPojo().getConnectionType(),
                                                           CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) )
                            {
                                statusCode = ValidationPojoUtil
                                        .validatePojo( inECafDto.getCustomerDetailsPojo(),
                                                       ICRMValidationCriteriaUtil.ECAF_CUSTOMER_DETAILS_CRITERIA );
                                LOGGER.info( "statusCode of Customer ::" + statusCode );
                            }
                        }
                        else
                        {
                            statusCode = CRMServiceCode.CRM851.getStatusCode();
                        }
                        if ( !StringUtils.isValidObj( CRMDisplayListConstants.getValueByCode( CRMParameter.SERVICE_TYPE
                                .getParameter(), inECafDto.getCustomerDetailsPojo().getServiceType() ) ) )
                        {
                            statusCode = CRMServiceCode.CRM866.getStatusCode();
                        }
                        if ( !StringUtils.isValidObj( CRMDisplayListConstants.getValueByCode( CRMParameter.PRODUCT
                                .getParameter(), inECafDto.getCustomerDetailsPojo().getProduct() ) ) )
                        {
                            statusCode = CRMServiceCode.CRM862.getStatusCode();
                        }
                        if ( ! ( StringUtils.equals( "M", inECafDto.getCustomerDetailsPojo().getCustGender() ) || StringUtils
                                .equals( "F", inECafDto.getCustomerDetailsPojo().getCustGender() ) ) )
                        {
                            statusCode = CRMServiceCode.CRM858.getStatusCode();
                        }
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997.getStatusCode();
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isValidObj( inECafDto.getPlanDetailsPojo() ) )
                {
                    statusCode = ValidationPojoUtil
                            .validatePojo( inECafDto.getPlanDetailsPojo(),
                                           ICRMValidationCriteriaUtil.ECAF_PLAN_DETAILS_CRITERIA );
                    if ( StringUtils.isEmpty( statusCode ) )
                    {
                        masterDataDto = getPlanDetails( inECafDto.getPlanDetailsPojo().getBasePlanCode(),
                                                        CRMDisplayListConstants.BASE_PLAN.getCode() );
                        if ( !CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
                        {
                            statusCode = CRMServiceCode.CRM867.getStatusCode();
                        }
                        else
                        {
                            if ( !StringUtils.equals( IAppConstants.Y, masterDataDto.getCrmPlanMasterPojos().get( 0 )
                                    .getActvAllowedYn() ) )
                            {
                                statusCode = CRMServiceCode.CRM871.getStatusCode();
                            }
                        }
                        masterDataDto = getPlanDetails( inECafDto.getPlanDetailsPojo().getAddOnPlanCode(),
                                                        CRMDisplayListConstants.ADDON_PLAN.getCode() );
                        if ( !CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
                        {
                            statusCode = CRMServiceCode.CRM868.getStatusCode();
                        }
                        else
                        {
                            if ( !StringUtils.equals( IAppConstants.Y, masterDataDto.getCrmPlanMasterPojos().get( 0 )
                                    .getActvAllowedYn() ) )
                            {
                                statusCode = CRMServiceCode.CRM871.getStatusCode();
                            }
                        }
                    }
                    LOGGER.info( "statusCode of plan ::" + statusCode );
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997.getStatusCode();
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isValidObj( inECafDto.getPaymentDetailsPojo() ) )
                {
                    statusCode = ValidationPojoUtil
                            .validatePojo( inECafDto.getPaymentDetailsPojo(),
                                           ICRMValidationCriteriaUtil.ECAF_PAYMENT_DETAILS_CRITERIA );
                    LOGGER.info( "statusCode of payment ::" + statusCode );
                    if ( StringUtils.isEmpty( statusCode ) )
                    {
                        if ( StringUtils.equals( inECafDto.getPaymentDetailsPojo().getPaymentMode(),
                                                 CRMDisplayListConstants.CHEQUE.getCode() )
                                || StringUtils.equals( inECafDto.getPaymentDetailsPojo().getPaymentMode(),
                                                       CRMDisplayListConstants.CASH.getCode() ) )
                        {
                            if ( StringUtils.equals( inECafDto.getPaymentDetailsPojo().getPaymentMode(),
                                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
                            {
                                statusCode = ValidationPojoUtil
                                        .validatePojo( inECafDto.getPaymentDetailsPojo(),
                                                       ICRMValidationCriteriaUtil.ECAF_CHEQUE_PAYMENT_CRITERIA );
                            }
                            /* else if ( StringUtils.equals( inECafDto.getPaymentDetailsPojo().getPaymentMode(),
                                                           CRMDisplayListConstants.CASH.getCode() ) )
                             {
                                 statusCode = ValidationPojoUtil
                                         .validatePojo( inECafDto.getPaymentDetailsPojo(),
                                                        ICRMValidationCriteriaUtil.ECAF_CASH_PAYMENT_CRITERIA );
                                 if ( StringUtils.isEmpty( statusCode ) )
                                 {
                                     statusCode = isDuplicateReciept( inECafDto, statusCode, masterDataDto );
                                 }
                             }
                             else
                             {
                                 statusCode = CRMServiceCode.CRM997.getStatusCode();
                             }*/
                        }
                        else
                        {
                            statusCode = CRMServiceCode.CRM889.getStatusCode();
                        }
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997.getStatusCode();
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isValidObj( inECafDto.getAadharNumberPojo() ) )
                {
                    statusCode = ValidationPojoUtil
                            .validatePojo( inECafDto.getAadharNumberPojo(),
                                           ICRMValidationCriteriaUtil.ECAF_AADHAR_DETAILS_CRITERIA );
                    LOGGER.info( "statusCode of aadhar ::" + statusCode );
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997.getStatusCode();
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isValidObj( inECafDto.getDocumentDetailsPojo() )
                        && StringUtils.isNotEmpty( inECafDto.getDocumentDetailsPojo().getIdProof() )
                        && StringUtils.isNotEmpty( inECafDto.getDocumentDetailsPojo().getAddressProof() ) )
                {
                    LOGGER.info( "ID proof ::" + inECafDto.getDocumentDetailsPojo().getIdProof() );
                    LOGGER.info( "Address proof ::" + inECafDto.getDocumentDetailsPojo().getAddressProof() );
                    if ( !StringUtils.contains( inECafDto.getDocumentDetailsPojo().getAddressProof(),
                                                IAppConstants.COMMA ) )
                    {
                        inECafDto.getDocumentDetailsPojo().setAddressProof( inECafDto.getDocumentDetailsPojo()
                                                                                    .getAddressProof()
                                                                                    .concat( IAppConstants.COMMA ) );
                    }
                    if ( !StringUtils.contains( inECafDto.getDocumentDetailsPojo().getIdProof(), IAppConstants.COMMA ) )
                    {
                        inECafDto.getDocumentDetailsPojo().setIdProof( inECafDto.getDocumentDetailsPojo().getIdProof()
                                                                               .concat( IAppConstants.COMMA ) );
                    }
                    LOGGER.info( "Address -------- " + inECafDto.getDocumentDetailsPojo().getAddressProof() );
                    LOGGER.info( "ID Proof -------- " + inECafDto.getDocumentDetailsPojo().getIdProof() );
                    String addressProff[] = inECafDto.getDocumentDetailsPojo().getAddressProof()
                            .split( IAppConstants.COMMA );
                    String idProff[] = inECafDto.getDocumentDetailsPojo().getIdProof().split( IAppConstants.COMMA );
                    for ( int i = 0; i < idProff.length; i++ )
                    {
                        if ( ! ( StringUtils.isValidObj( CRMDisplayListConstants
                                .getValueByCode( CRMParameter.COMMON_PROOF.getParameter(), idProff[i] ) ) || StringUtils
                                .isValidObj( CRMDisplayListConstants.getValueByCode( CRMParameter.DOCUMENTS
                                        .getParameter(), idProff[i] ) ) ) )
                        {
                            statusCode = CRMServiceCode.CRM872.getStatusCode();
                            break;
                        }
                    }
                    for ( int i = 0; i < addressProff.length; i++ )
                    {
                        if ( ! ( StringUtils.isValidObj( CRMDisplayListConstants
                                .getValueByCode( CRMParameter.COMMON_PROOF.getParameter(), addressProff[i] ) ) )
                                || StringUtils.isValidObj( CRMDisplayListConstants
                                        .getValueByCode( CRMParameter.ADDRESS_PROOF.getParameter(), addressProff[i] ) ) )
                        {
                            statusCode = CRMServiceCode.CRM872.getStatusCode();
                            break;
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( inECafDto.getFileUploaderList() ) )
                {
                    for ( FileUploader fileUploader : inECafDto.getFileUploaderList() )
                    {
                        if ( StringUtils.isNotBlank( fileUploader.getDocType() )
                                && ( StringUtils.equalsIgnoreCase( "CAF", fileUploader.getDocType() )
                                        || StringUtils.equalsIgnoreCase( "POA", fileUploader.getDocType() ) || StringUtils
                                            .equalsIgnoreCase( "POI", fileUploader.getDocType() ) ) )
                        {
                            if ( !FilenameUtils.isExtension( fileUploader.getFileName(),
                                                             IAppConstants.DOCUMENT_EXTENSIONS ) )
                            {
                                statusCode = CRMServiceCode.CRM886.getStatusCode();
                            }
                        }
                        else
                        {
                            statusCode = CRMServiceCode.CRM885.getStatusCode();
                        }
                    }
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                CrmUserPojo userPojo = CRMServiceUtils.getDBValues( CrmUserPojo.class, "srCode", inECafDto
                        .getCustomerDetailsPojo().getSalesRepresentative() );
                if ( StringUtils.isValidObj( userPojo ) && StringUtils.isNotBlank( userPojo.getUserId() ) )
                {
                    inECafDto.getCustomerDetailsPojo().setSalesRepresentative( userPojo.getUserId() );
                }
                else
                {
                    statusCode = CRMServiceCode.CRM887.getStatusCode();
                }
                if ( StringUtils.isEmpty( statusCode ) )
                {
                    PartnerPojo partnerPojo = CRMServiceUtils.getDBValues( PartnerPojo.class, "crmPartnerCode",
                                                                           inECafDto.getCustomerDetailsPojo()
                                                                                   .getBussinessPartnerCode() );
                    if ( StringUtils.isValidObj( partnerPojo ) && StringUtils.isNotBlank( partnerPojo.getPartnerName() ) )
                    {
                        inECafDto.getCustomerDetailsPojo().setBusinessPartner( partnerPojo.getPartnerId() );
                    }
                    else
                    {
                        statusCode = CRMServiceCode.CRM888.getStatusCode();
                    }
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                LOGGER.info( "statusCode is empty" );
                inECafDto = geteCafDao().saveECAF( inECafDto );
            }
            else
            {
                inECafDto = new ECafDto();
                setResponseStatus( statusCode, inECafDto );
            }
        }
        return inECafDto;
    }

    private String isDuplicateReciept( ECafDto inECafDto, String statusCode, MasterDataDto masterDataDto )
    {
        LOGGER.info( "ReceiptNo ::" + inECafDto.getPaymentDetailsPojo().getReceiptNo() );
        CrmRcaReasonPojo master = new CrmRcaReasonPojo();
        master.setCategory( CRMRCAReason.INA.getStatusCode() );
        master.setSubCategory( "CashReceipt" );
        master.setSubSubCategory( CRMDisplayListConstants.TELESERVICES.getCode() );
        master.setCategoryValue( inECafDto.getPaymentDetailsPojo().getReceiptNo() );
        //master.setSubSubCategory( CRMDisplayListConstants.BROADBAND.getCode() );
        master.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setCrmRcaReason( master );
        try
        {
            masterDataDto = CRMServicesProxy
                    .getInstance()
                    .getCRMMasterDataService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .masterOperations( ServiceParameter.LIST.getParameter(),
                                       CRMParameter.CRM_RCA_REASON.getParameter(), masterDataDto );
            if ( !CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
            {
                statusCode = CRMServiceCode.CRM611.getStatusCode();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in E-CAF - isDuplicateReciept method", ex );
        }
        return statusCode;
    }

    public static MasterDataDto getPlanDetails( String inPlanCode, String planType )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        try
        {
            crmPlanMasterPojo.setPlanCode( inPlanCode );
            crmPlanMasterPojo.setPlanCategory( planType );
            crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setPlanMaster( crmPlanMasterPojo );
            masterDataDto = CRMServicesProxy
                    .getInstance()
                    .getCRMMasterDataService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .masterOperations( ServiceParameter.LIST.getParameter(),
                                       CRMParameter.PLAN_DETAILS_MASTER.getParameter(), masterDataDto );
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Exception in getting plan master details ::" + ex );
        }
        return masterDataDto;
    }

    private void setResponseStatus( String statusCode, ECafDto inECafDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.valueOf( statusCode );
        inECafDto.setStatusCode( serviceCode.getStatusCode() );
        inECafDto.setStatusDesc( serviceCode.getStatusDesc() );
    }
}
