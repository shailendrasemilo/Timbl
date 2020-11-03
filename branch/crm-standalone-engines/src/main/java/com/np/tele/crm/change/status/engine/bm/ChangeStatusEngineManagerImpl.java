package com.np.tele.crm.change.status.engine.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMCapClient;
import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ChangeStatusEngineManagerImpl
    implements IChangeStatusEngineManager
{
    private static final Logger LOGGER = Logger.getLogger( ChangeStatusEngineManagerImpl.class );

    @Override
    public boolean changeCustomersStatus( String inAuthority, byte inMonthDifference, int inProcessChunkSize )
    {
        LOGGER.info( "Inside ChangeStatusEngineManagerImpl, changeCustomersStatus" );
        boolean needProcessing = true;
        CrmCapDto crmCapDto = null;
        CrmQrcDto crmQrcDto = null;
        CrmCapService crmCapService = null;
        CrmQrcService qrcService = null;
        RemarksPojo remarksPojo = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        try
        {
            crmCapDto = new CrmCapDto();
            crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
            crmCustomerDetailsPojo.setSafeCustodyDate( DateUtils.getBackDateByMonth( inMonthDifference ) );
            crmCustomerDetailsPojo.setStatus( CRMStatusCode.SC.getStatusCode() );
            crmCapDto.setUserId( inAuthority );
            crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            //crmCapDto.setMaxResultSize( inProcessChunkSize );
            crmCapService = new CRMCapClient();
            crmCapDto = crmCapService.crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                       CRMRequestType.CAF.getRequestCode(), crmCapDto );
            if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( crmCapDto.getCustomerDetailsPojosList() ) )
            {
                crmQrcDto = new CrmQrcDto();
                LOGGER.info( " Working List Size : " + crmCapDto.getCustomerDetailsPojosList().size() );
                for ( CrmCustomerDetailsPojo customerDetailsPojo : crmCapDto.getCustomerDetailsPojosList() )
                {
                    customerDetailsPojo.setStatus( CRMStatusCode.TDC.getStatusCode() );
                    crmQrcDto.setCustomerDetailsPojo( customerDetailsPojo );
                    crmQrcDto.setCustomerId( customerDetailsPojo.getCustomerId() );
                    crmQrcDto.setCustomerRecordId( customerDetailsPojo.getRecordId() );
                    crmQrcDto.setUserId( inAuthority );
                    remarksPojo = new RemarksPojo();
                    remarksPojo.setRemarks( "Status changed to TDC by ChangeStatusEngine" );
                    crmQrcDto.setRemarksPojo( remarksPojo );
                    qrcService = new CrmQrcClient();
                    crmQrcDto = qrcService.qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                                          CRMParameter.CHANGE_STATUS.getParameter(), crmQrcDto );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Status successfully changed, Customer ID : "
                                + crmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                    }
                    LOGGER.info( crmQrcDto.getStatusCode() );
                }
            }
            else
            {
                needProcessing = false;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while closing resolved tickets ", ex );
        }
        return needProcessing;
    }
}
