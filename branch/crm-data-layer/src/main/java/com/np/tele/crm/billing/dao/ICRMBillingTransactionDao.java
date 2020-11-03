package com.np.tele.crm.billing.dao;

import com.np.tele.crm.dto.ConfigDto;

public interface ICRMBillingTransactionDao
{
    //boolean saveBillingEventData( CrmBillingEventsCalling inBillingEventsCalling );

    ConfigDto saveTransactionPOJO( ConfigDto inConfigDto );
}
