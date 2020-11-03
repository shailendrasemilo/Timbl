package com.np.tele.crm.external.businessmgr;

import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;

public interface ICrmExternalTriggerMgr
{
    TriggerResponseDto postPrepaidPayment( PrepaidPaymentPojo inPrepaidPaymentPojo,
                                           TriggerRequestDto inTriggerRequestDto );

    TriggerResponseDto generateQrcTicket( QrcTicketPojo inQrcTicketPojo, TriggerRequestDto inTriggerRequestDto );

    TriggerResponseDto changeCustomerStatus( CustomerDetailsPojo inCustomerDetailsPojo,
                                             TriggerRequestDto inTriggerRequestDto );

    TriggerResponseDto changePlanStatus( CrmBillingPlanRequestPojo inBillingPlanRequestPojo,
                                         TriggerRequestDto inTriggerRequestDto );
}
