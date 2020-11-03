package com.np.tele.crm.report.businessmgr;

import com.np.tele.crm.dto.ReportDto;

public interface IReportOperationMgr
{
    ReportDto INAReportsOperation( String inOperationParam, ReportDto inReportDto );

    ReportDto QRCReportsOperation( String inOperationParam, ReportDto inReportDto );
}
