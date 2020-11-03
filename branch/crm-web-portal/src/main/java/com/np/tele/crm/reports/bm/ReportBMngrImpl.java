package com.np.tele.crm.reports.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.reports.form.CRMReportForm;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.PaymentReportPojo;
import com.np.tele.crm.service.client.QrcTicketReportPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SlaReportPojo;
import com.np.tele.crm.utils.StringUtils;

public class ReportBMngrImpl
    implements IReportBMngr
{
    private static final Logger LOGGER           = Logger.getLogger( ReportBMngrImpl.class );
    private CrmQrcService       qrcServiceClient = null;
    private CRMReportService    reportsClient    = null;

    public CRMReportService getReportsClient()
    {
        return reportsClient;
    }

    public void setReportsClient( CRMReportService inReportsClient )
    {
        reportsClient = inReportsClient;
    }

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    @Override
    public CrmQrcDto searchCustomerInteraction( CRMReportForm reportForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        try
        {
            crmQrcDto.setToDate( reportForm.getToDate() );
            crmQrcDto.setFromDate( reportForm.getFromDate() );
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                             CRMParameter.CUSTOMER_INTERACTION.getParameter(),
                                                             crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Exception", ex );
        }
        return crmQrcDto;
    }

    @Override
    public ReportDto validityExtensionRecords( CRMReportForm reportForm )
    {
        ReportDto reportDto = new ReportDto();
        reportDto.setFromDate( reportForm.getFromDate() );
        reportDto.setToDate( reportForm.getToDate() );
        try
        {
            reportDto = getReportsClient().qrcReportsOperation( ReportEnum.VALIDITY_EXTENSION_REPORT.getReportName(),
                                                                reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto getINAReports( CRMReportForm inReportForm, ReportEnum report )
    {
        ReportDto reportDto = null;
        try
        {
            reportDto = new ReportDto();
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            if ( StringUtils.isNotBlank( inReportForm.getProductType() ) )
            {
                reportDto.setProductType( inReportForm.getProductType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getServiceType() ) )
            {
                reportDto.setServiceType( inReportForm.getServiceType() );
            }
            reportDto = getReportsClient().inaReportsOperation( report.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search reports of migration::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto listAdjustmentReport( CRMReportForm inReportForm )
    {
        ReportDto reportDto = null;
        try
        {
            reportDto = new ReportDto();
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            reportDto = getReportsClient()
                    .qrcReportsOperation( ReportEnum.ADJUSTMENT_REPORT.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search reports of adjustment::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto listMigrationReport( CRMReportForm inReportForm )
    {
        ReportDto reportDto = null;
        try
        {
            reportDto = new ReportDto();
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            if ( StringUtils.isNotBlank( inReportForm.getProductType() ) )
            {
                reportDto.setProductType( inReportForm.getProductType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getServiceType() ) )
            {
                reportDto.setServiceType( inReportForm.getServiceType() );
            }
            reportDto = getReportsClient()
                    .qrcReportsOperation( ReportEnum.MIGRATION_REPORTS.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search reports of migration::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto crfDetailsHistory( CRMReportForm inReportForm )
    {
        ReportDto reportDto = new ReportDto();
        try
        {
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            if ( StringUtils.isNotBlank( inReportForm.getProductType() ) )
            {
                reportDto.setProductType( inReportForm.getProductType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getServiceType() ) )
            {
                reportDto.setServiceType( inReportForm.getServiceType() );
            }
            reportDto = getReportsClient()
                    .inaReportsOperation( ReportEnum.CRF_DETAIL_REPORT.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search reports of migration::", ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto ticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = null;
        try
        {
            reportDto = new ReportDto();
            reportDto.setQrcTicketReportPojo( new QrcTicketReportPojo() );
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            if ( StringUtils.isNotBlank( inReportForm.getQrcType() )
                    && !StringUtils.equals( inReportForm.getQrcType(), "0" ) )
            {
                reportDto.getQrcTicketReportPojo().setQrcType( inReportForm.getQrcType() );
            }
            if ( StringUtils.isValidObj( inReportForm.getQrcCategoryId() ) && inReportForm.getQrcCategoryId() != 0 )
            {
                reportDto.getQrcTicketReportPojo().setCategory( inReportForm.getQrcCategoryId() + "" );
                if ( StringUtils.isValidObj( inReportForm.getQrcSubCategoryId() )
                        && inReportForm.getQrcSubCategoryId() != 0 )
                {
                    reportDto.getQrcTicketReportPojo().setSubCategory( inReportForm.getQrcSubCategoryId() + "" );
                    if ( StringUtils.isValidObj( inReportForm.getQrcSubSubCategoryId() )
                            && inReportForm.getQrcSubSubCategoryId() != 0 )
                    {
                        reportDto.getQrcTicketReportPojo().setSubSubCategory( inReportForm.getQrcSubSubCategoryId()
                                                                                      + "" );
                    }
                }
            }
            if ( StringUtils.isValidObj( inReportForm.getFunctionalBinId() ) && inReportForm.getFunctionalBinId() != 0 )
            {
                reportDto.getQrcTicketReportPojo().setBinName( ( inReportForm.getFunctionalBinId() + "" ) );
            }
            if ( StringUtils.isNotBlank( inReportForm.getStatusOfTicket()))
            {
                reportDto.getQrcTicketReportPojo().setStatusOfTicket(inReportForm.getStatusOfTicket());
            }
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search ticketReports::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto getLMSReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = new ReportDto();
        try
        {
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search lms Reports::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto getRFSReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = new ReportDto();
        try
        {
            if ( StringUtils.isNotBlank( inReportForm.getState() )
                    && !StringUtils.equals( inReportForm.getState(), "0" ) )
            {
                reportDto.setStateName( inReportForm.getState() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getCity() ) && !StringUtils.equals( inReportForm.getCity(), "0" ) )
            {
                reportDto.setCityName( inReportForm.getCity() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getArea() ) && !StringUtils.equals( inReportForm.getArea(), "0" ) )
            {
                reportDto.setAreaName( inReportForm.getArea() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getSociety() )
                    && !StringUtils.equals( inReportForm.getSociety(), "0" ) )
            {
                reportDto.setSocietyName( inReportForm.getSociety() );
            }
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto getPaymentReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = new ReportDto();
        try
        {
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            reportDto.setPaymentReportPojo( new PaymentReportPojo() );
            if ( StringUtils.isNotBlank( inReportForm.getCustomerId() ) )
            {
                reportDto.getPaymentReportPojo().setCustomerId( inReportForm.getCustomerId() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getCrfId() ) )
            {
                reportDto.getPaymentReportPojo().setCrfId( inReportForm.getCrfId() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getCustomerServiceType() )
                    && !StringUtils.equals( inReportForm.getCustomerServiceType(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setServiceType( inReportForm.getCustomerServiceType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getInstallationStatus() )
                    && !StringUtils.equals( inReportForm.getInstallationStatus(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setInstallationStatus( inReportForm.getInstallationStatus() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getPaymentStatus() )
                    && !StringUtils.equals( inReportForm.getPaymentStatus(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setPaymentStatus( inReportForm.getPaymentStatus() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getCaseStatus() )
                    && !StringUtils.equals( inReportForm.getCaseStatus(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setCaseStatus( inReportForm.getCaseStatus() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getChequeStatus() )
                    && !StringUtils.equals( inReportForm.getChequeStatus(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setChequeStatus( inReportForm.getChequeStatus() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getEntityType() )
                    && !StringUtils.equals( inReportForm.getEntityType(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setEntityType( inReportForm.getEntityType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getPaymentMode() )
                    && !StringUtils.equals( inReportForm.getPaymentMode(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setPaymentMode( inReportForm.getPaymentMode() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getChannelType() )
                    && !StringUtils.equals( inReportForm.getChannelType(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setPaymentChannel( inReportForm.getChannelType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getPostingStatus() )
                    && !StringUtils.equals( inReportForm.getPostingStatus(), "0" ) )
            {
                reportDto.getPaymentReportPojo().setPostingStatus( inReportForm.getPostingStatus() );
            }
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search payment Reports::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto getMassOutageReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = null;
        CrmMassOutagePojo crmMassOutagePojo = null;
        try
        {
            reportDto = new ReportDto();
            crmMassOutagePojo = new CrmMassOutagePojo();
            reportDto.setFromDate( inReportForm.getFromDate() );
            reportDto.setToDate( inReportForm.getToDate() );
            if ( StringUtils.isNotBlank( inReportForm.getProductType() ) )
            {
                crmMassOutagePojo.setServiceName( inReportForm.getProductType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getOutageType() ) )
            {
                crmMassOutagePojo.setOutageType( inReportForm.getOutageType() );
            }
            if ( StringUtils.isNotBlank( inReportForm.getOutageStatus() ) )
            {
                crmMassOutagePojo.setStatus( inReportForm.getOutageStatus() );
            }
            reportDto.setCrmMassOutagePojo( crmMassOutagePojo );
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "getting error while getMassOutageReport::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto catWiseticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = null;
        try
        {
            LOGGER.info( "catWiseticketReport called" );
            reportDto = new ReportDto();
            reportDto.setSlaReportPojo( new SlaReportPojo() );
            if ( StringUtils.isNotBlank( inReportForm.getQrcType() )
                    && !StringUtils.equals( inReportForm.getQrcType(), "0" ) )
            {
                reportDto.getSlaReportPojo().setQrcType( inReportForm.getQrcType() );
            }
            if ( StringUtils.isValidObj( inReportForm.getQrcCategoryId() ) && inReportForm.getQrcCategoryId() != 0 )
            {
                reportDto.getSlaReportPojo().setCategory( inReportForm.getQrcCategoryId() + "" );
                if ( StringUtils.isValidObj( inReportForm.getQrcSubCategoryId() )
                        && inReportForm.getQrcSubCategoryId() != 0 )
                {
                    reportDto.getSlaReportPojo().setSubCategory( inReportForm.getQrcSubCategoryId() + "" );
                    if ( StringUtils.isValidObj( inReportForm.getQrcSubSubCategoryId() )
                            && inReportForm.getQrcSubSubCategoryId() != 0 )
                    {
                        reportDto.getSlaReportPojo().setSubSubCategory( inReportForm.getQrcSubSubCategoryId() + "" );
                    }
                }
            }
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while search ticketReports::" + ex );
        }
        return reportDto;
    }

    @Override
    public ReportDto userWiseOpenticketReport( CRMReportForm inReportForm, ReportEnum inReportEnum )
    {
        ReportDto reportDto = null;
        try
        {
            LOGGER.info( "userWiseOpenticketReport called" );
            reportDto = new ReportDto();
            reportDto.setSlaReportPojo( new SlaReportPojo() );
            if ( StringUtils.isNotBlank( inReportForm.getUserName() )
                    && StringUtils.isValidObj( inReportForm.getUserName() ) )
            {
                reportDto.getSlaReportPojo().setUserName( inReportForm.getUserName() );
            }
            reportDto = getReportsClient().qrcReportsOperation( inReportEnum.getReportName(), reportDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while fetching userWiseOpenticketReport::" + ex );
        }
        return reportDto;
    }
}
