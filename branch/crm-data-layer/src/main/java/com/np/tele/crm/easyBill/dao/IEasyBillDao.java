package com.np.tele.crm.easyBill.dao;

import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillRequestDto;
import com.np.tele.crm.dto.EasyBillResponseDto;

public interface IEasyBillDao
{
    EasyBillResponseDto getCustomerDetails( EasyBillRequestDto inEasyBillRequestDto, EasyBillResponseDto inResponseDto );

    EasyBillResponseDto postPayment( EasyBillRequestDto inEasyBillRequestDto,
                                     EasyBillPaymentDto inEasyBillPaymentDto,
                                     EasyBillResponseDto inResponseDto );

    EasyBillResponseDto getPaymentStatus( EasyBillRequestDto inEasyBillRequestDto,
                                          EasyBillResponseDto inResponseDto );
}
