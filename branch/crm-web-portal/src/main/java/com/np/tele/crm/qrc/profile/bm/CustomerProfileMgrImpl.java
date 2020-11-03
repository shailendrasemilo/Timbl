package com.np.tele.crm.qrc.profile.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;

public class CustomerProfileMgrImpl
    implements ICustomerProfileMgr
{
    private static final Logger LOGGER           = Logger.getLogger( CustomerProfileMgrImpl.class );
    private CrmQrcService       qrcServiceClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService qrcServiceClient )
    {
        this.qrcServiceClient = qrcServiceClient;
    }

    @Override
    public CrmQrcDto updateCustomerCategory( QrcForm inQrcForm, CrmQrcDto inCrmQrcDto )
    {
        boolean toProcess = false;
        try
        {
            LOGGER.info( "Bussiness Mgr called updateCustomerCategory" );
            if ( StringUtils.isValidObj( inQrcForm.getCustDetailsPojo() ) )
            {
                /* if (  !(StringUtils.equals( inQrcForm.getCustDetailsPojo().getConnectionType(),
                                             CRMDisplayListConstants.INDIVIDUAL.getCode() )||StringUtils.equals( inQrcForm.getCustDetailsPojo().getConnectionType(),
                                             CRMDisplayListConstants.PROPRIETORSHIP.getCode() )) ) 
                 {
                     inQrcForm.getCustDetailsPojo().setCustLname( null );
                 }*/
                CommonUtils.setConnectionTypeDetails( inQrcForm.getCustDetailsPojo() );
                inCrmQrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
                inCrmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
                inCrmQrcDto.setUserId( inQrcForm.getCrmUserId() );
                inCrmQrcDto.setSrTicketNo( inQrcForm.getSrTicketNo() );
                toProcess = true;
            }
            //Set Nationality Details
            if ( !StringUtils.equals( inQrcForm.getCustDetailsPojo().getNationality(), IAppConstants.INDIAN )
                    && StringUtils.isValidObj( inQrcForm.getCustDetailsPojo().getNationality() ) )
            {
                inCrmQrcDto.setNationaltyDetailPojo( inQrcForm.getNationalityDetailsPojo() );
                toProcess = true;
            }
            //Set Document  Details
            if ( StringUtils.isValidObj( inQrcForm.getDocumentDetailsPojo() ) )
            {
                inCrmQrcDto.setDocumetDetailsPojo( inQrcForm.getDocumentDetailsPojo() );
                toProcess = true;
            }
            if ( toProcess )
            {
                inCrmQrcDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.MODIFY.getParameter(),
                                                                               null, inCrmQrcDto );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( e );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto updateCustomerBillCycle( QrcForm qrcForm )
    {
        CrmQrcDto crmDto = new CrmQrcDto();
        crmDto.setUserId( qrcForm.getCrmUserId() );
        crmDto.setSrTicketNo( qrcForm.getSrTicketNo() );
        crmDto.setUsageToDate( qrcForm.getParamValue() );
        crmDto.setRemarksPojo( qrcForm.getRemarksPojo() );
        crmDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
        try
        {
            crmDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.MODIFY.getParameter(),
                                                                      IAppConstants.METHOD_UPDATE_CUSTOMER_BILLCYCLE,
                                                                      crmDto );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "Error occured while updating customer bill cycle :: " + e );
            crmDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmDto;
    }

    @Override
    public CrmQrcDto getCustomerBillCycle( String inCustomerId )
    {
        CrmQrcDto crmDto = new CrmQrcDto();
        crmDto.setCustomerId( inCustomerId );
        LOGGER.info( "[CustomerProfileMgrImpl]Customer Id ::" + crmDto.getCustomerId() );
        try
        {
            crmDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.LIST.getParameter(),
                                                                      IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE,
                                                                      crmDto );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "Error occured while updating customer bill cycle :: " + e );
            crmDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmDto;
    }

    public CrmQrcDto saveGracePeriod( QrcForm qrcForm )
    {
        CrmQrcDto crmDto = new CrmQrcDto();
        try
        {
            crmDto.setCustomerId( qrcForm.getCustDetailsPojo().getCustomerId() );
            crmDto.setNoOfdays( Integer.parseInt( qrcForm.getDays() ) );
            crmDto.setGracePeriodReason( qrcForm.getGracePeriodReason() );
            crmDto.setRemarksPojo( qrcForm.getRemarksPojo() );
            crmDto.setUserId( qrcForm.getCrmUserId() );
            crmDto.setSrTicketNo( qrcForm.getSrTicketNo() );
            crmDto.setExpiryDate( qrcForm.getCustAdditionalDetails().getExpiryDate() );
            crmDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.SAVE.getParameter(),
                                                                      IAppConstants.METHOD_GRACE_PERIOD, crmDto );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "Error occured while updating customer bill cycle :: " + e );
            crmDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmDto;
    }

    public CrmQrcDto cancelBillCycleRequest( String customerId, String recordID, String userId )
    {
        CrmQrcDto crmDto = new CrmQrcDto();
        crmDto.setCustomerId( customerId );
        crmDto.setCustomerRecordId( Long.parseLong( recordID ) );
        crmDto.setUserId( userId );
        try
        {
            crmDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.CANCEL.getParameter(),
                                                                      IAppConstants.METHOD_CANCEL_CUSTOMER_BILLCYCLE,
                                                                      crmDto );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "Error occured while updating customer bill cycle :: " + e );
            crmDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmDto;
    }
}
