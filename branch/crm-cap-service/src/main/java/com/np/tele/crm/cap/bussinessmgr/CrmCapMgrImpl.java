package com.np.tele.crm.cap.bussinessmgr;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.ina.dao.ICAPOperationDao;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class CrmCapMgrImpl
    implements ICrmCapMgr
{
    private static final Logger LOGGER          = Logger.getLogger( CrmCapMgrImpl.class );
    private ICAPOperationDao    capOperationDao = null;

    public ICAPOperationDao getCapOperationDao()
    {
        return capOperationDao;
    }

    public void setCapOperationDao( ICAPOperationDao inCapOperationDao )
    {
        capOperationDao = inCapOperationDao;
    }

    @Override
    public CrmCapDto crmCapOperation( String inServiceParam, String inOperationParam, CrmCapDto inCrmCapDto )
    {
        boolean setStatusCode = false;
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        try
        {
            LOGGER.info( "Service Name :: " + inServiceParam + " -- Operation Name :: " + inOperationParam );
            if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inOperationParam ) )
            {
                String serviceCode = ValidationPojoUtil.validatePojo( inCrmCapDto.getCustomerDetailsPojo(),
                                                                      ICRMValidationCriteriaUtil.CRF_SEARCH_CRITERIA );
                if ( StringUtils.isBlank( serviceCode ) )
                {
                    inCrmCapDto = getCapOperationDao().getCustomerDetails( inCrmCapDto );
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For View CAF. Error Code:" + serviceCode );
                    statusCode = CRMServiceCode.valueOf( serviceCode );
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inOperationParam ) )
            {
                String serviceCode = ValidationPojoUtil.validatePojo( inCrmCapDto.getCustomerDetailsPojo(),
                                                                      ICRMValidationCriteriaUtil.VIEW_CRF_CRITERIA );
                if ( StringUtils.isBlank( serviceCode ) )
                {
                    boolean toProcess = true;
                    if ( inCrmCapDto.getCustomerDetailsPojo().getInboxId() > 0 )
                    {
                        toProcess = false;
                        CrmInboxPojo inbox = CRMServiceUtils.getDBValues( CrmInboxPojo.class, inCrmCapDto
                                .getCustomerDetailsPojo().getInboxId() );
                        if ( StringUtils.isValidObj( inbox ) )
                        {
                            if ( inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0
                                    && StringUtils.equals( inCrmCapDto.getCustomerDetailsPojo().getRecordId() + "",
                                                           inbox.getMappingId() )
                                    && StringUtils.equals( inCrmCapDto.getUserId(), inbox.getUserId() ) )
                            {
                                inbox.setUnRead( false );
                                inbox = CRMServiceUtils.mergeDBValues( inbox );
                                toProcess = true;
                            }
                        }
                    }
                    if ( toProcess )
                    {
                        inCrmCapDto = getCapOperationDao().viewCRFDetails( inCrmCapDto.getCustomerDetailsPojo()
                                                                                   .getRecordId(),
                                                                           inCrmCapDto.getCustomerDetailsPojo()
                                                                                   .getCrfId(), null );
                    }
                    else
                    {
                        statusCode = CRMServiceCode.CRM312;
                        setStatusCode = true;
                    }
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For View CAF. Error Code:" + serviceCode );
                    statusCode = CRMServiceCode.valueOf( serviceCode );
                    setStatusCode = true;
                }
            }
            else if ( ( StringUtils.equals( ServiceParameter.SAVE.getParameter(), inServiceParam ) )
                    || StringUtils.equals( ServiceParameter.FORWARD.getParameter(), inServiceParam )
                    || StringUtils.equals( ServiceParameter.SAVE_CRF_REFERENCE.getParameter(), inServiceParam )
                    || StringUtils.equals( ServiceParameter.SAVE_ISR_REFERENCE.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() )
                        && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfId() )
                        //&& StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustLname() )
                        && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustFname() )
                        && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getProduct() )
                        && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getConnectionType() )
                        && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getDisplayCrfDate() )
                        || inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
                {
                    if ( StringUtils.equals( ServiceParameter.FORWARD.getParameter(), inServiceParam ) )
                    {
                        LOGGER.info( "Going For Submit[Forward] CAF" );
                        inCrmCapDto = getCapOperationDao().forwardCRF( inCrmCapDto );
                    }
                    else if ( StringUtils.equals( ServiceParameter.SAVE.getParameter(), inServiceParam ) )
                    {
                        LOGGER.info( "Going For Save CAF" );
                        inCrmCapDto = getCapOperationDao().saveCRF( inCrmCapDto, true );
                    }
                    else if ( ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfReferenceNo() ) && StringUtils
                            .equals( ServiceParameter.SAVE_CRF_REFERENCE.getParameter(), inServiceParam ) )
                            || ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getIsrReferenceNo() ) && StringUtils
                                    .equals( ServiceParameter.SAVE_ISR_REFERENCE.getParameter(), inServiceParam ) ) )
                    {
                        LOGGER.info( "Going to save CAF Reference No OR ISR Reference No For CAF::"
                                + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
                        inCrmCapDto = getCapOperationDao().saveCRForISRReferenceNo( inCrmCapDto );
                    }
                    else
                    {
                        LOGGER.info( "Required Details Are Not Present In DTO For Save or validate CAF" );
                        statusCode = CRMServiceCode.CRM997;
                        setStatusCode = true;
                    }
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For Save CAF" );
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmCustomerDetailsPojo.class.getSimpleName(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() )
                        && inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
                {
                    inCrmCapDto.setCustomerDetailsPojo( CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                                     inCrmCapDto
                                                                                             .getCustomerDetailsPojo()
                                                                                             .getRecordId() ) );
                    statusCode = CRMServiceCode.CRM001;
                    setStatusCode = true;
                }
                else
                {
                    inCrmCapDto = getCapOperationDao().getCustomerDetails( inCrmCapDto.getCustomerDetailsPojo()
                                                                                   .getRecordId(),
                                                                           inCrmCapDto.getCustomerDetailsPojo()
                                                                                   .getCrfId(),
                                                                           inCrmCapDto.getCustomerDetailsPojo()
                                                                                   .getCustomerId() );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.TRACK.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmCustomerDetailsPojo.class.getSimpleName(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
                {
                    boolean toProcess = true;
                    if ( inCrmCapDto.getCustomerDetailsPojo().getInboxId() > 0 )
                    {
                        toProcess = false;
                        CrmInboxPojo inbox = CRMServiceUtils.getDBValues( CrmInboxPojo.class, inCrmCapDto
                                .getCustomerDetailsPojo().getInboxId() );
                        if ( StringUtils.isValidObj( inbox ) )
                        {
                            if ( inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0
                                    && StringUtils.equals( inCrmCapDto.getCustomerDetailsPojo().getRecordId() + "",
                                                           inbox.getMappingId() )
                                    && StringUtils.equals( inCrmCapDto.getUserId(), inbox.getUserId() ) )
                            {
                                inbox.setUnRead( false );
                                inbox = CRMServiceUtils.mergeDBValues( inbox );
                                toProcess = true;
                            }
                        }
                    }
                    if ( toProcess )
                    {
                        inCrmCapDto = getCapOperationDao().getCustomerDetails( inCrmCapDto.getCustomerDetailsPojo()
                                                                                       .getRecordId(),
                                                                               inCrmCapDto.getCustomerDetailsPojo()
                                                                                       .getCrfId(),
                                                                               inCrmCapDto.getCustomerDetailsPojo()
                                                                                       .getCustomerId() );
                    }
                    else
                    {
                        statusCode = CRMServiceCode.CRM312;
                        setStatusCode = true;
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inOperationParam ) )
            {
                if ( checkValidDetail( inCrmCapDto ) )
                {
                    LOGGER.info( "Going To Serach Customer" );
                    inCrmCapDto = getCapOperationDao().searchCustomerProfile( inCrmCapDto );
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For Search Customer" );
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.MAC_ADDRESS.getRequestCode(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto ) )
                {
                    LOGGER.info( "Going To Serach Customer" );
                    if ( StringUtils.isNotBlank( inCrmCapDto.getNetworkConfigurationsPojo().getFirstCpeMacId() ) )
                    {
                        inCrmCapDto = getCapOperationDao().searchCustomerByMacAddress( inCrmCapDto );
                    }
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For Search Customer" );
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CUSTOMER_ID.getRequestCode(), inOperationParam ) )
            {
                LOGGER.info( "Going To Serach All Customer by Society" );
                inCrmCapDto = getCapOperationDao().searchCustomerBySociety( inCrmCapDto );
            }
            else if ( StringUtils
                    .equals( ServiceParameter.SAVE_CUSTOMER_PROFILE_DETAILS.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto ) )
                {
                    LOGGER.info( "Going To Save Customer Profile Details" );
                    inCrmCapDto = getCapOperationDao().saveCustomerProfileDetails( inCrmCapDto );
                }
                else
                {
                    LOGGER.info( "Required Details Are Not Present In DTO For Save CAF" );
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.PROCESS.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMParameter.CUSTOMER_EMAIL.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto )
                        && StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
                {
                    if ( getCapOperationDao().updateToken( inCrmCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                           inCrmCapDto.getUserId() ) )
                    {
                        CRMServiceUtils.sendAlerts( CRMEvents.REGISTERED_EMAIL_VERIFICATION,
                                                    CRMRequestType.CUSTOMER_ID, inCrmCapDto.getCustomerDetailsPojo()
                                                            .getCustomerId(), null );
                        statusCode = CRMServiceCode.CRM001;
                        setStatusCode = true;
                    }
                    else
                    {
                        setStatusCode = true;
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VALIDATE.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMParameter.CUSTOMER_EMAIL.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.isValidObj( inCrmCapDto )
                        && StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
                {
                    statusCode = getCapOperationDao().validateEmailToken( inCrmCapDto.getCustomerDetailsPojo()
                                                                                  .getCustToken() );
                    setStatusCode = true;
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMRequestType.LINK_TO_CRF.getRequestCode(), inOperationParam ) )
            {
                statusCode = CRMServiceCode.CRM996;
                setStatusCode = true;
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
                {
                    List<CrmCustomerDetailsPojo> records = CRMServiceUtils
                            .getDBValueList( CrmCustomerDetailsPojo.class, "linkedCRF", inCrmCapDto
                                    .getCustomerDetailsPojo().getCrfId() );
                    if ( CommonValidator.isValidCollection( records ) )
                    {
                        statusCode = CRMServiceCode.CRM001;
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmCustomerDetailsPojo.class.getSimpleName(), inOperationParam ) )
            {
                statusCode = CRMServiceCode.CRM996;
                setStatusCode = true;
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
                {
                    List<CrmCustomerDetailsPojo> records = CRMServiceUtils
                            .getDBValueList( CrmCustomerDetailsPojo.class, "crfReferenceNo", inCrmCapDto
                                    .getCustomerDetailsPojo().getCrfReferenceNo() );
                    if ( CommonValidator.isValidCollection( records ) )
                    {
                        inCrmCapDto.setCustomerDetailsPojosList( records );
                        statusCode = CRMServiceCode.CRM001;
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmNetworkConfigurationsPojo.class.getSimpleName(), inOperationParam ) )
            {
                statusCode = CRMServiceCode.CRM996;
                setStatusCode = true;
                if ( StringUtils.isValidObj( inCrmCapDto.getNetworkConfigurationsPojo() ) )
                {
                    List<CrmNetworkConfigurationsPojo> networkConfigPojos = CRMServiceUtils
                            .getDBValueList( CrmNetworkConfigurationsPojo.class, "option82", inCrmCapDto
                                    .getNetworkConfigurationsPojo().getOption82() );
                    if ( CommonValidator.isValidCollection( networkConfigPojos ) )
                    {
                        inCrmCapDto.setNetworkConfigurationsList( networkConfigPojos );
                        statusCode = CRMServiceCode.CRM001;
                    }
                }
                else
                {
                    statusCode = CRMServiceCode.CRM997;
                    setStatusCode = true;
                }
            }
            else
            {
                LOGGER.info( "Operation Name OR Service Parameter Is Not Correct. Operation Name ::" + inOperationParam );
                statusCode = CRMServiceCode.CRM007;
                setStatusCode = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception In crmCapOperation Method ", ex );
            statusCode = CRMServiceCode.CRM999;
            setStatusCode = true;
        }
        finally
        {
            if ( setStatusCode )
            {
                inCrmCapDto.setStatusCode( statusCode.getStatusCode() );
                inCrmCapDto.setStatusDesc( statusCode.getStatusDesc() );
            }
        }
        return inCrmCapDto;
    }

    private boolean checkValidDetail( CrmCapDto inCrmCapDto )
    {
        return StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustFname() )
                || StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustLname() )
                || StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfId() )
                || inCrmCapDto.getCustomerDetailsPojo().getRtn() > 0
                || inCrmCapDto.getCustomerDetailsPojo().getRmn() > 0
                || StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getStatus() )
                || StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustomerId() );
    }
}
