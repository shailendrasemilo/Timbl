package com.np.tele.crm.sla.dao;

import com.np.tele.crm.dto.ReportDto;

public interface ISLAManagerDoa
{
    ReportDto getAuditLogHistory( ReportDto inReportDto );

    ReportDto getSlaLogHistory( ReportDto inReportDto );

    ReportDto getRemarksHistory( ReportDto inReportDto );

    ReportDto sendSLAAlertAndSaveStatus( ReportDto inReportDto );

    ReportDto forwardAndSaveStatus( ReportDto inReportDto );

    ReportDto getQrcSubSubCategoriesPojos( ReportDto inReportDto );

    ReportDto getQrcSubCategoriesPojos( ReportDto inReportDto );
}
