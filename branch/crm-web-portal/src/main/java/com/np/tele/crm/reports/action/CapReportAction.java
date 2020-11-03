package com.np.tele.crm.reports.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.reports.bm.IReportBMngr;
import com.np.tele.crm.reports.form.CRMReportForm;
import com.np.tele.crm.service.client.InaReportPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CapReportAction
    extends DispatchAction
{
    private static final Logger LOGGER        = Logger.getLogger( CapReportAction.class );
    private IReportBMngr        reportManager = null;

    public IReportBMngr getReportManager()
    {
        return reportManager;
    }

    public void setReportManager( IReportBMngr inReportManager )
    {
        reportManager = inReportManager;
    }

    public ActionForward crfScanningReportPage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        LOGGER.info( "CapReportAction :::crfScanningReportPage:::submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().getINAReports( reportForm, ReportEnum.SCANNING_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "Size of scanning report is :: " + repDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                    Collections.sort( repDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( repDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "No record found..." );
                    reportForm.setInaReportPojos( null );
                }
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.CRF_SCANNING_REPORT );
    }

    public ActionForward crfDetailReportPage( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        LOGGER.info( "CapReportAction :::crfDetailReportPage:::submit:-" + reportForm.isToSearch() );
        if ( reportForm.isToSearch() )
        {
            ReportDto repDto = getReportManager().crfDetailsHistory( reportForm );
            if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() )
                    && !repDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "Data Found" );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "crfDate" );
                Collections.sort( repDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( repDto.getInaReportPojos() );
            }
            else
            {
                errors.add( IAppConstants.NO_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                LOGGER.info( "No Record" );
                reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
            }
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.CRF_DETAILS_REPORT );
    }

    public ActionForward cancellationReportPage( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        LOGGER.info( "CapReportAction :::cancellationReportPage:::submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                ReportDto reportDto = getReportManager().getINAReports( reportForm, ReportEnum.CANCELLATION_REPORTS );
                if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() ))
                {
                    LOGGER.info( "reportDto Record:::::::" + reportDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "lastModifiedTime" );
                    Collections.sort( reportDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "reportDto:::::::No Record Found" );
                    reportForm.setInaReportPojos( null );
                }
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.CRF_CANCELLATION_REPORT );
    }

    public ActionForward workorderInstallationReportPage( final ActionMapping inMapping,
                                                          final ActionForm inForm,
                                                          final HttpServletRequest inRequest,
                                                          final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "WO-Installation submit-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                reportDto = getReportManager().getINAReports( reportForm, ReportEnum.WORK_ORDER_INSTALLATION_REPORT );
                if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                        && !reportDto.getInaReportPojos().isEmpty() )
                {
                    LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "activationDate" );
                    Collections.sort( reportDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
                }
            }
        }
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        return inMapping.findForward( IActionForwardConstant.WORK_ORDER_INSTALLATION_REPORT );
    }

    public ActionForward workorderPendancyReportPage( final ActionMapping inMapping,
                                                      final ActionForm inForm,
                                                      final HttpServletRequest inRequest,
                                                      final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "WO-PendancyReport submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            reportForm.setToDate( null );
            reportForm.setFromDate( null );
            reportDto = getReportManager().getINAReports( reportForm, ReportEnum.WORK_ORDER_PENDANCY_REPORT );
            if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                    && !reportDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "crfDate" );
                Collections.sort( reportDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
            }
            else
            {
                LOGGER.info( "No Record Found" );
                reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
            }
        }
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        return inMapping.findForward( IActionForwardConstant.WORK_ORDER_PENDANCY_REPORT );
    }

    public ActionForward ftRejectionReportPage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        LOGGER.info( "CapReportAction :::ftRejectionReportPage:::submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( null );
            reportForm.setFromDate( null );
        }
        else
        {
            ReportDto reportDto = getReportManager().getINAReports( reportForm, ReportEnum.FT_REJECTION_REPORTS );
            if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                    && !reportDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "reportDto Record:::::::" + reportDto.getInaReportPojos().size() );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "ftRejectionDate" );
                Collections.sort( reportDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
            }
            else
            {
                LOGGER.info( "reportDto:::::::No Record Found" );
                reportForm.setInaReportPojos( null );
            }
        }
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        return inMapping.findForward( IActionForwardConstant.FT_REJECTION_REPORT );
    }

    public ActionForward isrPendancyReportPage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        LOGGER.info( "CapReportAction :::isrPendancyReportPage:::submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( null );
            reportForm.setFromDate( null );
        }
        else
        {
            reportForm.setServiceType( CRMDisplayListConstants.POST_PAID.getCode() );
            ReportDto reportDto = getReportManager().getINAReports( reportForm, ReportEnum.ISR_PENDENCY_REPORTS );
            if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                    && !reportDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "reportDto Record:::::::" + reportDto.getInaReportPojos().size() );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                Collections.sort( reportDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
            }
            else
            {
                LOGGER.info( "reportDto:::::::No Record Found" );
                reportForm.setInaReportPojos( null );
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        return inMapping.findForward( IActionForwardConstant.ISR_PENDANCY_REPORT );
    }

    public ActionForward srPReportPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "CapReportAction :::srPReportPage:::submit:-" + reportForm.isToSearch() );
        if ( reportForm.isToSearch() )
        {
            reportForm.setToDate( null );
            reportForm.setFromDate( null );
            reportDto = getReportManager().getINAReports( reportForm, ReportEnum.SRP_REPORT );
            if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                    && !reportDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                Collections.sort( reportDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
            }
            else
            {
                LOGGER.info( "No Record Found" );
                reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.SRP_REPORT );
    }

    public ActionForward workorderGenerationReportPage( final ActionMapping inMapping,
                                                        final ActionForm inForm,
                                                        final HttpServletRequest inRequest,
                                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "WO-Generation submit-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                reportDto = getReportManager().getINAReports( reportForm, ReportEnum.WORKORDER_GENERATION_REPORT );
                if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                    Collections.sort( reportDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
                }
            }
        }
        return inMapping.findForward( IActionForwardConstant.WORK_ORDER_GENERATION_REPORT );
    }

    public ActionForward activationReportPage( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "ActivationReportPage::" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                reportDto = getReportManager().getINAReports( reportForm, ReportEnum.ACTIVATION_REPORT );
                if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                        && !reportDto.getInaReportPojos().isEmpty() )
                {
                    LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "activationDate" );
                    Collections.sort( reportDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
                }
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.ACTIVATION_REPORT );
    }

    public ActionForward kpiReportPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "CapReportAction :::kpiReportPage:::submit:-" + reportForm.isToSearch() );
        if ( !reportForm.isToSearch() )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                reportDto = getReportManager().getINAReports( reportForm, ReportEnum.KPI_REPORT );
                if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                        && !reportDto.getInaReportPojos().isEmpty() )
                {
                    LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                    Collections.sort( reportDto.getInaReportPojos(), sorter );
                    reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
                }
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.KPI_REPORT );
    }
   
    public ActionForward erpReportPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ReportDto reportDto = null;
        LOGGER.info( "CapReportAction :::erpReportPage:::submit:-" + reportForm.isToSearch() );
        if ( reportForm.isToSearch() )
        {
            reportForm.setToDate( null );
            reportForm.setFromDate( null );
            reportDto = getReportManager().getINAReports( reportForm, ReportEnum.ERP_REPORT );
            if ( CommonValidator.isValidCollection( reportDto.getInaReportPojos() )
                    && !reportDto.getInaReportPojos().isEmpty() )
            {
                LOGGER.info( "INA Report Pojos Size :" + reportDto.getInaReportPojos().size() );
                SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "createdTime" );
                Collections.sort( reportDto.getInaReportPojos(), sorter );
                reportForm.setInaReportPojos( reportDto.getInaReportPojos() );
            }
            else
            {
                LOGGER.info( "No Record Found" );
                reportForm.setInaReportPojos( new ArrayList<InaReportPojo>() );
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.ERP_REPORT );
    }
}
