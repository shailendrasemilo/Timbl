package com.np.tele.crm.faultRepair;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

@WebService(name = "QRCFaultRepairService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IQRCFaultRepairService
{
    @WebMethod
    public void qrcFaultRepair( @WebParam(name = "To") final String inTo,
                                @WebParam(name = "Message") final String inMessage,
                                @WebParam(name = "Oprator") final String inOprator,
                                @WebParam(name = "Sender") final String inSender,
                                @WebParam(name = "Date") final String inDate )
        throws SOAPException;
}
