package com.np.tele.crm.report.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.report.dao.IINAReportDao;
import com.np.tele.crm.report.dao.IQRCReportDao;
import com.np.tele.crm.utils.StringUtils;

public class ReportOperationMgrImpl
    implements IReportOperationMgr
{
    private static final Logger LOGGER            = Logger.getLogger( ReportOperationMgrImpl.class );
    private IINAReportDao       inaReportDao      = null;
    private IQRCReportDao       qrcReportDao      = null;
    private ICrmBillingManager  crmBillingManager = null;

    public IINAReportDao getInaReportDao()
    {
        return inaReportDao;
    }

    public void setInaReportDao( IINAReportDao inaReportDao )
    {
        this.inaReportDao = inaReportDao;
    }

    public IQRCReportDao getQrcReportDao()
    {
        return qrcReportDao;
    }

    public void setQrcReportDao( IQRCReportDao qrcReportDao )
    {
        this.qrcReportDao = qrcReportDao;
    }

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager inCrmBillingManager )
    {
        crmBillingManager = inCrmBillingManager;
    }

    @Override
    public ReportDto INAReportsOperation( String inOperationParam, ReportDto inReportDto )
    {
        LOGGER.info( "RreportOperationMgrImpl ::: InAReportsOperation::inOperationParam::: " + inOperationParam );
        if ( StringUtils.isNotBlank( inOperationParam ) )
        {
            inReportDto = getInaReportDao().getINAReport( inReportDto, inOperationParam );
            /*  ReportEnum report = ReportEnum.getReportType( inOperationParam );
            switch ( report )
            {
                case CHURN_REPORT:
                case BARRING_REPORT:
                    List<InaReportPojo> inaReports = inReportDto.getInaReportPojos();
                    if ( CommonValidator.isValidCollection( inaReports ) )
                    {
                        for ( InaReportPojo inaReportPojo : inaReports )
                        {
                            CrmCustAdditionalDetails additionalDetail = getCrmBillingManager()
                                    .getCustomerUsageDetail( inaReportPojo.getCustomerId(), true )
                                    .getCustAdditionalDetails();
                            if ( StringUtils.isValidObj( additionalDetail ) )
                            {
                                inaReportPojo.setOutStandingBalance( additionalDetail.getBalance() );
                            }
                        }
                    }
                    break;
                default:
                    break;
            }*/
        }
        return inReportDto;
    }

    @Override
    public ReportDto QRCReportsOperation( String inOperationParam, ReportDto inReportDto )
    {
        LOGGER.info( "ReportOperationMgrImpl:::::QRCReportsOperation:::inOperationParam" + inOperationParam );
        LOGGER.info( inReportDto );
        if ( StringUtils.equals( inOperationParam, ReportEnum.ADJUSTMENT_REPORT.getReportName() ) )
        {
            inReportDto = getQrcReportDao().listAdjustmentReports( inReportDto );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.MIGRATION_REPORTS.getReportName() ) )
        {
            inReportDto = getQrcReportDao().listMigrationReports( inReportDto );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.VALIDITY_EXTENSION_REPORT.getReportName() ) )
        {
            inReportDto = getQrcReportDao().ValidityExtensionHistory( inReportDto,
                                                                      ReportEnum.VALIDITY_EXTENSION_REPORT
                                                                              .getReportName() );
        }
        else if ( ( StringUtils.equals( inOperationParam, ReportEnum.TICKET_REPORTS.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.OPEN_TICKET_REPORT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.TAGGING_REPORT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.RESOLVE_TICKET_REPORT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.FIRST_ASSIGNED_BIN_TICKET_REPORT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.REOPEN_TICKET_REPOERT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.REPEAT_TICKET_REPOERT.getReportName() ) ) )
        {
            inReportDto = getQrcReportDao().getTicketReport( inReportDto, inOperationParam );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.MASS_OUTAGE_REPORTS.getReportName() ) )
        {
            inReportDto = getQrcReportDao().getMassOutageReport( inReportDto );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.LMS_REPORT.getReportName() ) )
        {
            inReportDto = getQrcReportDao().getLMSReport( inReportDto, inOperationParam );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.RFS_REPORT.getReportName() ) )
        {
            inReportDto = getQrcReportDao().getRFSReport( inReportDto, inOperationParam );
        }
        else if ( StringUtils.equals( inOperationParam, ReportEnum.PAYMENT_REPORT.getReportName() ) )
        {
            inReportDto = getQrcReportDao().getPaymentReport( inReportDto, inOperationParam );
        }
        else if ( ( StringUtils.equals( inOperationParam, ReportEnum.CATEGORY_WISE_OPEN_TICKET_REPORT.getReportName() ) ) )
        {
            LOGGER.info( "CATEGORY_WISE_OPEN_TICKET_REPORT" );
            inReportDto = getQrcReportDao().catWiseTicketReport( inReportDto, inOperationParam );
        }
        else if ( ( StringUtils.equals( inOperationParam, ReportEnum.USER_WISE_OPEN_TICKET_REPORT.getReportName() ) )
                || ( StringUtils.equals( inOperationParam, ReportEnum.OUT_OF_SLA_TICKET_REPORT.getReportName() ) ) )
        {
            LOGGER.info( "USER_WISE_TICKET_REPORT" );
            inReportDto = getQrcReportDao().userWiseTicketReport( inReportDto, inOperationParam );
        }
        return inReportDto;
    }
}
