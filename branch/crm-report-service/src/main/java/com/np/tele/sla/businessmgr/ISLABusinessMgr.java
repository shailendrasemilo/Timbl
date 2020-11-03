package com.np.tele.sla.businessmgr;

import com.np.tele.crm.dto.ReportDto;

public interface ISLABusinessMgr
{
    ReportDto SLAOperation( String inServiceParam, String inCrmParam, ReportDto inReportDto );
}
