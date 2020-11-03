package com.np.tele.crm.easyBill;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillResponseDto;

@WebService(name = "CrmEasyBillService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmEasyBillService
{
    @WebMethod
    public EasyBillResponseDto getCustomerDetails( @WebParam(header = true, name = "username") String inAuthUsername,
                                                   @WebParam(header = true, name = "password") String inAuthPassword,
                                                   @WebParam(name = "rmn") long inRmn,
                                                   @WebParam(name = "customerId") String inCustomerID )
        throws SOAPException;

    @WebMethod
    public EasyBillResponseDto postPayment( @WebParam(header = true, name = "username") String inAuthUsername,
                                            @WebParam(header = true, name = "password") String inAuthPassword,
                                            @WebParam(name = "EasyBillPaymentDto") final EasyBillPaymentDto inEasyBillPaymentDto )
        throws SOAPException;

    @WebMethod
    public EasyBillResponseDto getPaymentStatus( @WebParam(header = true, name = "username") String inAuthUsername,
                                                 @WebParam(header = true, name = "password") String inAuthPassword,
                                                 @WebParam(name = "PaymentDate") Date inPaymentDate )
        throws SOAPException;

    @WebMethod
    EasyBillResponseDto getTransactionStatus( @WebParam(header = true, name = "username") String inAuthUsername,
                                              @WebParam(header = true, name = "password") String inAuthPassword,
                                              @WebParam(name = "transactionId") String inTransactionId )
        throws SOAPException;
}
