package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CRMReportServiceService;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class CRMReportsClient
    implements CRMReportService
{
    @Override
    public ReportDto inaReportsOperation( String inOperationParam, ReportDto inReportDto )
        throws SOAPException_Exception
    {
        CRMReportServiceService crmReportServiceService = new CRMReportServiceService();
        CRMReportService crmReportService = crmReportServiceService.getCRMReportServicePort();
        return crmReportService.inaReportsOperation( inOperationParam, inReportDto );
    }

    @Override
    public ReportDto qrcReportsOperation( String inOperationParam, ReportDto inReportDto )
        throws SOAPException_Exception
    {
        CRMReportServiceService crmReportServiceService = new CRMReportServiceService();
        CRMReportService crmReportService = crmReportServiceService.getCRMReportServicePort();
        return crmReportService.qrcReportsOperation( inOperationParam, inReportDto );
    }

    @Override
    public ReportDto slaOperations( String inServiceParam, String inCrmParam, ReportDto inReportDto )
        throws SOAPException_Exception
    {
        CRMReportServiceService crmReportServiceService = new CRMReportServiceService();
        CRMReportService crmReportService = crmReportServiceService.getCRMReportServicePort();
        return crmReportService.slaOperations( inServiceParam, inCrmParam, inReportDto );
    }
}
