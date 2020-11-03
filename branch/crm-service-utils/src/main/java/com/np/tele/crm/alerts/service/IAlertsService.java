package com.np.tele.crm.alerts.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.dto.ExtAlertDto;
import com.np.tele.crm.pojos.AlertStatusPojo;

@WebService(name = "CRMAlertsService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IAlertsService
{
    @WebMethod
    public AlertStatusPojo sendAlerts( @WebParam(name = "AlertDto") final AlertsDto inAlertsDto )
        throws SOAPException;

    @WebMethod
    public AlertsDto alertTemplate( @WebParam(name = "ServiceParam") final String inServiceParam,
                                    @WebParam(name = "AlertType") final String inAlertType,
                                    @WebParam(name = "AlertDto") final AlertsDto inAlertsDto )
        throws SOAPException;

    @WebMethod
    public AlertsDto eventTemplate( @WebParam(name = "ServiceParam") final String inServiceParam,
                                    @WebParam(name = "AlertDto") final AlertsDto inAlertsDto )
        throws SOAPException;

    @WebMethod
    public AlertStatusPojo sendExtAlerts( @WebParam(name = "ExtAlertDto") final ExtAlertDto inExtAlertDto )
        throws SOAPException;

    @WebMethod
    AlertsDto alertOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                               @WebParam(name = "AlertType") final String inAlertType,
                               @WebParam(name = "AlertDto") AlertsDto inAlertsDto );
}
