package com.np.tele.crm.finance.dao;

import java.util.List;

import org.hibernate.Session;

import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.pojos.CrmCmsFilePojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPodDetailsPojo;

public interface IFinanceManagerDao
{
    CrmFinanceDto getPaymentHistory( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto manageCMSFile( CrmCmsFilePojo inCmsFile );

    //CrmFinanceDto manageCMSRecords( List<CrmCmsPaymentPojo> inCmsPayments );
    CrmFinanceDto manageCMSRecords( CrmFinanceDto inCrmFinanceDto, boolean inToUpdate );

    CrmFinanceDto getCMSFiles( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto getCMSRecords( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto postUpfrontPayments( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto getUpfrontPayments( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto getUpfrontPopUpDetails( CrmFinanceDto inCrmFinanceDto );

    //    boolean postMultiplePayments( CrmFinanceDto inCrmFinanceDto, List<CrmPaymentDetailsPojo> inCrmPaymentDetails );
    void postReversal( CrmFinanceDto destFinanceDto,
                       CrmCustomerDetailsPojo crmCustomerDetailsPojo,
                       CrmPaymentDetailsPojo crmPaymentDetailsPojo );

    boolean postSinglePayment( CrmFinanceDto inCrmFinanceDto,
                               CrmPaymentDetailsPojo inPaymentDetailsPojo,
                               Session inSession );

    public CrmFinanceDto getRefundDetails( CrmFinanceDto inCrmFinanceDto );

    public CrmFinanceDto changeRefundChequeStatus( CrmFinanceDto inCrmFinanceDto );

    public CrmFinanceDto postRefund( CrmFinanceDto inCrmFinanceDto );

    public CrmFinanceDto savePODData( List<CrmPodDetailsPojo> successPojos, List<CrmPodDetailsPojo> rejectedPojos );

    public CrmFinanceDto trackPODRecords( CrmFinanceDto inCrmFinanceDto );

    CrmFinanceDto paymentListByTransId( CrmFinanceDto inCrmFinanceDto );
}
