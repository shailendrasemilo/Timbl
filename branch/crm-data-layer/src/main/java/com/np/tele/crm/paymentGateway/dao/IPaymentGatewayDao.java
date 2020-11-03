package com.np.tele.crm.paymentGateway.dao;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmFinanceDto;

public interface IPaymentGatewayDao
{
    CRMServiceCode generateTrackId( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto updatePgwAuth( CrmFinanceDto inCrmFinanceDto );

    CRMServiceCode saveTransactionDetails( CrmFinanceDto inCrmFinanceDto );
}
