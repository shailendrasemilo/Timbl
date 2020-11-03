package com.np.tele.crm.report.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.ReportDto;

@WebService(name = "CRMReportService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICRMReportService
{
    @WebMethod
    public ReportDto INAReportsOperation( @WebParam(name = "operationParam") final String inOperationParam,
                                          @WebParam(name = "ReportDto") final ReportDto inReportDto )
        throws SOAPException;

    @WebMethod
    public ReportDto QRCReportsOperation( @WebParam(name = "operationParam") final String inOperationParam,
                                          @WebParam(name = "ReportDto") final ReportDto inReportDto )
        throws SOAPException;

    @WebMethod
    public ReportDto SLAOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                    @WebParam(name = "CrmParam") final String inCrmParam,
                                    @WebParam(name = "ReportDto") final ReportDto inReportDto )
        throws SOAPException;
}
