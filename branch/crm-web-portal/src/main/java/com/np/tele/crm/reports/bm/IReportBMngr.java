package com.np.tele.crm.reports.bm;

import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.reports.form.CRMReportForm;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.ReportDto;

public interface IReportBMngr
{
    ReportDto getINAReports( CRMReportForm inReportForm, ReportEnum inReport );

    ReportDto crfDetailsHistory( CRMReportForm inReportForm );

    ReportDto validityExtensionRecords( CRMReportForm reportForm );

    CrmQrcDto searchCustomerInteraction( CRMReportForm reportForm );

    ReportDto listAdjustmentReport( CRMReportForm inReportForm );

    ReportDto listMigrationReport( CRMReportForm inReportForm );

    ReportDto ticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum );

    ReportDto getLMSReport( CRMReportForm inReportForm, ReportEnum inReportEnum );
    
    ReportDto getRFSReport( CRMReportForm inReportForm, ReportEnum inReportEnum );

    ReportDto getPaymentReport( CRMReportForm inReportForm, ReportEnum inReportEnum );

    ReportDto getMassOutageReport( CRMReportForm inReportForm, ReportEnum inReportEnum );

    public ReportDto catWiseticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum );
    
    ReportDto userWiseOpenticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum );

}
