package com.np.tele.crm.report.dao;

import com.np.tele.crm.dto.ReportDto;

public interface IINAReportDao
{
    ReportDto getINAReport( ReportDto inReportDto, String inNamedQuery );
}
