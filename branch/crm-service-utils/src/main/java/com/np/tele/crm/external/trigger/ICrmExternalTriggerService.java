package com.np.tele.crm.external.trigger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;

@WebService(name = "CrmExternalTriggerService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmExternalTriggerService
{
    @WebMethod
    public TriggerResponseDto postPrepaidPayment( @WebParam(header = true, name = "username") String inAuthUsername,
                                                  @WebParam(header = true, name = "password") String inAuthPassword,
                                                  @WebParam(name = "TriggerRequestDto") final TriggerRequestDto inTriggerRequestDto,
                                                  @WebParam(name = "PrepaidPaymentPojo") final PrepaidPaymentPojo inPrepaidPaymentPojo )
        throws SOAPException;

    @WebMethod
    public TriggerResponseDto generateQrcTicket( @WebParam(header = true, name = "username") String inAuthUsername,
                                                 @WebParam(header = true, name = "password") String inAuthPassword,
                                                 @WebParam(name = "TriggerRequestDto") final TriggerRequestDto inTriggerRequestDto,
                                                 @WebParam(name = "QrcTicketPojo") final QrcTicketPojo inQrcTicketPojo )
        throws SOAPException;

    @WebMethod
    public TriggerResponseDto changeCustomerStatus( @WebParam(header = true, name = "username") String inAuthUsername,
                                                    @WebParam(header = true, name = "password") String inAuthPassword,
                                                    @WebParam(name = "TriggerRequestDto") final TriggerRequestDto inTriggerRequestDto,
                                                    @WebParam(name = "CustomerDetailsPojo") final CustomerDetailsPojo inCustomerDetailsPojo )
        throws SOAPException;

    @WebMethod
    public TriggerResponseDto changePlanStatus( @WebParam(header = true, name = "username") String inAuthUsername,
                                                @WebParam(header = true, name = "password") String inAuthPassword,
                                                @WebParam(name = "CrmBillingPlanRequestPojo") final CrmBillingPlanRequestPojo inBillingPlanRequestPojo )
        throws SOAPException;
}
