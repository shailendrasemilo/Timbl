package com.np.tele.crm.finance.businessmgr;

import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.CrmFinanceDto;

public interface ICRMFinanceBusiness
{
    public CrmFinanceDto trackPayment( final String inServiceParam,
                                       final String inCrmParam,
                                       final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;

    public CrmFinanceDto postPayment( final String inServiceParam,
                                      final String inCrmParam,
                                      final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;

    public CrmFinanceDto pgwOperations( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;
}
