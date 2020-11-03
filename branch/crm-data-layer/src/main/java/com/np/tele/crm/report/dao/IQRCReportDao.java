package com.np.tele.crm.report.dao;

import com.np.tele.crm.dto.ReportDto;

public interface IQRCReportDao
{
    ReportDto listAdjustmentReports( ReportDto inReportDto );

    ReportDto listMigrationReports( ReportDto inReportDto );

    ReportDto ValidityExtensionHistory( ReportDto inReportDto, String inQuery );

    ReportDto getTicketReport( ReportDto inReportDto, String inNamedQuery );

    ReportDto getLMSReport( ReportDto inReportDto, String inNamedQuery );
    
    ReportDto getRFSReport( ReportDto inReportDto, String inNamedQuery );

    ReportDto getPaymentReport( ReportDto inReportDto, String inNamedQuery );

    ReportDto getMassOutageReport( ReportDto inReportDto );

    public ReportDto catWiseTicketReport( ReportDto inReportDto, String inNamedQuery );

    ReportDto userWiseTicketReport( ReportDto inReportDto, String inNamedQuery );
}
