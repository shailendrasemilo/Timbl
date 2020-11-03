package com.np.tele.crm.easyBill.businessmgr;

import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillRequestDto;
import com.np.tele.crm.dto.EasyBillResponseDto;

public interface ICrmEasyBillMgr
{
    EasyBillResponseDto getCustomerDetails( EasyBillRequestDto inEasyBillRequestDto );

    EasyBillResponseDto postPayment( EasyBillRequestDto inEasyBillRequestDto, EasyBillPaymentDto inEasyBillPaymentDto );

    EasyBillResponseDto getPaymentStatus( EasyBillRequestDto inEasyBillRequestDto );

    EasyBillResponseDto getTransactionStatus( EasyBillRequestDto inEasyBillRequestDto );
}
