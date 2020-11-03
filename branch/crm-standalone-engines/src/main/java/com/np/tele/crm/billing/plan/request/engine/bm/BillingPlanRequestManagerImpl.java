package com.np.tele.crm.billing.plan.request.engine.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmBillingPlanRequestPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class BillingPlanRequestManagerImpl
    implements IBillingPlanRequestManager
{
    private static final Logger LOGGER = Logger.getLogger( BillingPlanRequestManagerImpl.class );

    @Override
    public boolean getBillingPlanRequest( String inAuthority )
    {
        LOGGER.info( "Inside BillingPlanRequestManagerImpl method" );
        CrmQrcDto crmQrcDto = null;
        CrmQrcService crmQrcService = null;
        boolean needProcessing = true;
        try
        {
            crmQrcDto = new CrmQrcDto();
            crmQrcService = new CrmQrcClient();
            crmQrcDto = crmQrcService.qrcOperations( ServiceParameter.LIST.getParameter(),
                                                     CrmBillingPlanRequestPojo.class.getSimpleName(), crmQrcDto );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( crmQrcDto.getBillingPlanRequestPojos() ) )
            {
                LOGGER.info( "Size of BillingPlanRequestPojos::" + crmQrcDto.getBillingPlanRequestPojos().size() );
                for ( CrmBillingPlanRequestPojo billingPlanRequestPojo : crmQrcDto.getBillingPlanRequestPojos() )
                {
                    LOGGER.info( "Customer ID:" + billingPlanRequestPojo.getCustomerId() );
                    crmQrcDto.setUserId( inAuthority );
                    crmQrcDto.setBillingPlanRequestPojo( billingPlanRequestPojo );
                    crmQrcDto = crmQrcService
                            .qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                            CrmBillingPlanRequestPojo.class.getSimpleName(), crmQrcDto );
                    LOGGER.info( crmQrcDto.getStatusCode() + " :: " + crmQrcDto.getStatusDesc() );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "successfully update billing plan request : " );
                    }
                }
            }
            else
            {
                needProcessing = false;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while billing plan request ", ex );
        }
        return needProcessing;
    }
}
