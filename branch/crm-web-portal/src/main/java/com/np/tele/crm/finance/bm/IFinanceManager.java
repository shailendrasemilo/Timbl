package com.np.tele.crm.finance.bm;

import java.util.List;
import java.util.Set;

import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;

public interface IFinanceManager
{
    public CrmFinanceDto paymentPosting( FinanceForm financeForm );

    public CrmFinanceDto searchPayment( FinanceForm financeForm );

    public CrmFinanceDto changePaymentStatus( FinanceForm financeForm );

    public String getFromDate( String inDate );

    public CrmFinanceDto searchPaymentDetails( FinanceForm inFinanceForm );

    public CrmFinanceDto reversePayment( FinanceForm inFinanceForm, String inUser );

    public CrmFinanceDto changeRealizationStatus( FinanceForm inFinanceForm );

    public CrmFinanceDto getFileList( FinanceForm inFinanceForm );

    public CrmFinanceDto realizeRecieveAll( FinanceForm inFinanceForm );

    public CrmFinanceDto changeCaseStatus( FinanceForm inFinanceForm );

    public CrmFinanceDto manualPayment( FinanceForm inForm, Set<CrmUpCrfMappingPojo> inCrfMappingPojos );

    public CrmFinanceDto searchUpfrontPaymentDetails( FinanceForm inForm );

    public CrmFinanceDto searchSuspenseRecord( FinanceForm inFinanceForm );

    public CrmFinanceDto rectifySuspenseRecord( FinanceForm inFinanceForm );

    CrmFinanceDto rectifyRejectedRecord( FinanceForm inFinanceForm );

    public CrmFinanceDto getUpfrontPopUpDetails( FinanceForm inForm );

    public List<String> processUpFrontExcel( FinanceForm inFinanceForm, String inUser, String inFilePath );

    public ConfigDto getActivityLogs( FinanceForm inFinanceForm );

    public CrmFinanceDto paymentGatewaysConfigure( FinanceForm inFinanceForm, String inUserId );

    public List<String> processPODExcel( FinanceForm inFinanceForm, String inUser, String inFilePath );

    public CrmFinanceDto searchRefundDetails( FinanceForm inFinanceForm );

    public CrmFinanceDto getchangeRefundStatus( FinanceForm inFinanceForm, String inUserId );

    public CrmFinanceDto processRefund( FinanceForm inFinanceForm, String inUserId );

    public CrmFinanceDto trackPODRecords( FinanceForm inFinanceForm );

    public CrmFinanceDto paymentListByTransId( CrmFinanceDto inFinanceDto );
}
