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

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.reports.bm.IReportBMngr;
import com.np.tele.crm.reports.form.CRMReportForm;
import com.np.tele.crm.service.client.CrmCustInteractionsPojo;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.InaReportPojo;
import com.np.tele.crm.service.client.LmsReportPojo;
import com.np.tele.crm.service.client.PaymentReportPojo;
import com.np.tele.crm.service.client.QrcTicketReportPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QRCReportAction
    extends DispatchAction
{
    private static final Logger LOGGER        = Logger.getLogger( QRCReportAction.class );
    private IReportBMngr        reportManager = null;

    public IReportBMngr getReportManager()
    {
        return reportManager;
    }

    public void setReportManager( IReportBMngr inReportManager )
    {
        reportManager = inReportManager;
    }

    public ActionForward adjustmentReport( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() ) )
            {
                ReportDto reportDto = getReportManager().listAdjustmentReport( reportForm );
                if ( CommonValidator.isValidCollection( reportDto.getCrmCustWaiverPojos() )
                        && !reportDto.getCrmCustWaiverPojos().isEmpty() )
                {
                    SortingComparator<CrmCustWaiverPojo> sorter = new SortingComparator<CrmCustWaiverPojo>( "waiverPostedDate" );
                    Collections.sort( reportDto.getCrmCustWaiverPojos(), sorter );
                    reportForm.setCustWaiverPojos( reportDto.getCrmCustWaiverPojos() );
                }
                else
                {
                    reportForm.setCustWaiverPojos( null );
                }
            }
        }
        return inMapping.findForward( IActionForwardConstant.ADJUSTMENT_REPORT );
    }

    public ActionForward massOutageReport( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: massOutageReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
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
                ReportDto reportDto = getReportManager().getMassOutageReport( reportForm,
                                                                              ReportEnum.MASS_OUTAGE_REPORTS );
                if ( CommonValidator.isValidCollection( reportDto.getCrmMassOutagePojos() ) )
                {
                    LOGGER.info( "MassOutagePojos Size::" + reportDto.getCrmMassOutagePojos().size() );
                    SortingComparator<CrmMassOutagePojo> sorter = new SortingComparator<CrmMassOutagePojo>( "outageStartTime" );
                    Collections.sort( reportDto.getCrmMassOutagePojos(), Collections.reverseOrder( sorter ) );
                    reportForm.setCrmMassOutagePojos( reportDto.getCrmMassOutagePojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setCrmMassOutagePojos( null );
                }
            }
        }
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.MASSOUTAGE_REPORT );
    }

    public ActionForward churnReport( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: churnReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().getINAReports( reportForm, ReportEnum.CHURN_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "Size of churn report is :: " + repDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "tempDisconnectDate" );
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
        return inMapping.findForward( IActionForwardConstant.CHURN_REPORT );
    }

    public ActionForward interactionReport( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        if ( reportForm.isToSearch() )
        {
            CrmQrcDto qrcDto = getReportManager().searchCustomerInteraction( reportForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( qrcDto.getCustInteractionsPojos() )
                        && !qrcDto.getCustInteractionsPojos().isEmpty() )
                {
                    LOGGER.info( "Data Found" );
                    SortingComparator<CrmCustInteractionsPojo> sorter = new SortingComparator<CrmCustInteractionsPojo>( "createdTime" );
                    Collections.sort( qrcDto.getCustInteractionsPojos(), sorter );
                    reportForm.setInteractionPojos( qrcDto.getCustInteractionsPojos() );
                }
                else
                {
                    LOGGER.info( "No Data Found" );
                    /* errors.add( IAppConstants.NO_RECORD_FOUND,
                                 new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );*/
                    reportForm.setInteractionPojos( new ArrayList<CrmCustInteractionsPojo>() );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
            }
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.INTERACTION_REPORT );
    }

    public ActionForward migrationReport( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setProductTypeList( CRMUtils.getProducts() );
        reportForm.setServiceTypeList( CRMUtils.getServiceTypesList() );
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
                ReportDto reportDto = getReportManager().listMigrationReport( reportForm );
                if ( CommonValidator.isValidCollection( reportDto.getCrmCustomerActivityPojos() ) )
                {
                    LOGGER.info( "reportDto Record:getCrmCustomerActivityPojos()::::::"
                            + reportDto.getCrmCustomerActivityPojos().size() );
                    SortingComparator<CrmCustomerActivityPojo> sorter = new SortingComparator<CrmCustomerActivityPojo>( "createdTime" );
                    Collections.sort( reportDto.getCrmCustomerActivityPojos(), sorter );
                    reportForm.setCrmCustomerActivityPojos( reportDto.getCrmCustomerActivityPojos() );
                }
                else
                {
                    LOGGER.info( "reportDto:getCrmCustomerActivityPojos()::::::No Record Found" );
                    reportForm.setCrmCustomerActivityPojos( null );
                }
            }
        }
        return inMapping.findForward( IActionForwardConstant.MIGRATION_REPORT );
    }

    public ActionForward reactivationReport( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        LOGGER.info( "in QRCReportAction :: reactivationReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().getINAReports( reportForm, ReportEnum.REACTIVATION_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "Size of reactivation report is :: " + repDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "reacivationDate" );
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
        return inMapping.findForward( IActionForwardConstant.REACTIVATION_REPORT );
    }

    public ActionForward validityExtensionReport( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        if ( reportForm.isToSearch() )
        {
            ReportDto repDto = getReportManager().validityExtensionRecords( reportForm );
            if ( CommonValidator.isValidCollection( repDto.getQrcReportPojos() )
                    && !repDto.getQrcReportPojos().isEmpty() )
            {
                LOGGER.info( "Data Found" );
                SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "activationDate" );
                Collections.sort( repDto.getQrcReportPojos(), sorter );
                reportForm.setQrcReportPojos( repDto.getQrcReportPojos() );
            }
            else
            {
                LOGGER.info( "No Data Found" );
                /*errors.add( IAppConstants.NO_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );*/
                reportForm.setQrcReportPojos( new ArrayList<QrcTicketReportPojo>() );
            }
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.VALIDITY_EXTENSION_REPORT );
    }

    public ActionForward openTicketReport( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( reportForm.isToSearch() )
        {
            ReportDto repDto = new ReportDto();
            repDto = getReportManager().ticketReport( reportForm, ReportEnum.OPEN_TICKET_REPORT );
            if ( CommonValidator.isValidCollection( repDto.getQrcReportPojos() ) )
            {
                LOGGER.info( "Size of open report is :: " + repDto.getQrcReportPojos().size() );
                reportForm.setQrcReportPojos( repDto.getQrcReportPojos() );
                if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
                {
                    reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                            .getQrcCategoryId() ) );
                    if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                            && reportForm.getQrcSubCategoryId() != 0 )
                    {
                        reportForm.setQrcSubSubCategoryList( QRCCacheManager
                                .getActiveQrcSubSubCategories( null, reportForm.getQrcCategoryId(),
                                                               reportForm.getQrcSubCategoryId() ) );
                    }
                }
            }
            else
            {
                LOGGER.info( "No record found..." );
                reportForm.setQrcReportPojos( null );
            }
            reportForm.setQrcReportPojos( repDto.getQrcReportPojos() );
            if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
            {
                LOGGER.info( "Category ID::" + reportForm.getQrcCategoryId() );
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
        }
        /* reportForm.setQrcCategory( new CrmQrcCategoriesPojo() );
         reportForm.setQrcSubCategory( new CrmQrcSubCategoriesPojo() );
         reportForm.setQrcSubSubCategory( new CrmQrcSubSubCategoriesPojo() )*/;
        reportForm.setFunctionalBins( CRMCacheManager.getQRCFunctionalBins() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        return inMapping.findForward( IActionForwardConstant.OPEN_TICKET_REPORT );
    }

    public ActionForward resolvedTickets( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setFunctionalBins( CRMCacheManager.getQRCFunctionalBins() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        if ( reportForm.isToSearch() )
        {
            ReportDto reportDto = getReportManager().ticketReport( reportForm, ReportEnum.RESOLVE_TICKET_REPORT );
            if ( CommonValidator.isValidCollection( reportDto.getQrcReportPojos() ) )
            {
                SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "srResolvedOn" );
                Collections.sort( reportDto.getQrcReportPojos(), sorter );
                reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
            }
            else
            {
                reportForm.setQrcReportPojos( null );
            }
            if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
            {
                LOGGER.info( "Category ID::" + reportForm.getQrcCategoryId() );
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
        }
        else
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        return inMapping.findForward( IActionForwardConstant.RESOLVED_TICKET_REPORT );
    }

    public ActionForward taggingReport( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setFunctionalBins( CRMCacheManager.getQRCFunctionalBins() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        if ( reportForm.isToSearch() )
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().ticketReport( reportForm, ReportEnum.TAGGING_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getQrcReportPojos() ) )
                {
                    SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "createdTime" );
                    Collections.sort( repDto.getQrcReportPojos(), sorter );
                    reportForm.setQrcReportPojos( repDto.getQrcReportPojos() );
                    if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
                    {
                        reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                                .getQrcCategoryId() ) );
                        if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                                && reportForm.getQrcSubCategoryId() != 0 )
                        {
                            reportForm.setQrcSubSubCategoryList( QRCCacheManager
                                    .getActiveQrcSubSubCategories( null, reportForm.getQrcCategoryId(),
                                                                   reportForm.getQrcSubCategoryId() ) );
                        }
                    }
                }
                else
                {
                    LOGGER.info( "No record found..." );
                    reportForm.setQrcReportPojos( null );
                }
                
                if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
                {
                    reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                            .getQrcCategoryId() ) );
                    if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                            && reportForm.getQrcSubCategoryId() != 0 )
                    {
                        reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                                .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                    }
                }
                
            }
        }
        else
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        return inMapping.findForward( IActionForwardConstant.TAGGING_REPORT );
    }

    public ActionForward ticketReport( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        else
        {
            ReportDto reportDto = getReportManager().ticketReport( reportForm, ReportEnum.TICKET_REPORTS );
            if ( CommonValidator.isValidCollection( reportDto.getQrcReportPojos() ) )
            {
                SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "raisedDate" );
                Collections.sort( reportDto.getQrcReportPojos(), sorter );
                reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
            }
            else
            {
                reportForm.setQrcReportPojos( null );
            }
            if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
            {
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
        }
        return inMapping.findForward( IActionForwardConstant.TICKET_REPORT );
    }

    public ActionForward firstAssignedBinTicketReport( final ActionMapping inMapping,
                                                       final ActionForm inForm,
                                                       final HttpServletRequest inRequest,
                                                       final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setFunctionalBins( CRMCacheManager.getQRCFunctionalBins() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        ReportDto reportDto = null;
        if ( reportForm.isToSearch() )
        {
            if ( StringUtils.isNotBlank( reportForm.getFromDate() ) && StringUtils.isNotBlank( reportForm.getToDate() )
                    && reportForm.getFunctionalBinId() > 0 )
            {
                reportDto = getReportManager().ticketReport( reportForm, ReportEnum.FIRST_ASSIGNED_BIN_TICKET_REPORT );
                if ( CommonValidator.isValidCollection( reportDto.getQrcReportPojos() ) )
                {
                    LOGGER.info( "qrcReport Size::" + reportDto.getQrcReportPojos().size() );
                    SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "raisedDate" );
                    Collections.sort( reportDto.getQrcReportPojos(), sorter );
                    reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
                }
                else
                {
                    LOGGER.info( "No Record Found" );
                    reportForm.setQrcReportPojos( null );
                }
                
                if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
                {
                    reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                            .getQrcCategoryId() ) );
                    if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                            && reportForm.getQrcSubCategoryId() != 0 )
                    {
                        reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                                .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                    }
                }
            }
        }
        else
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        return inMapping.findForward( IActionForwardConstant.FIRST_ASSIGN_BIN_REPORT );
    }

    /*public ActionForward safeCustodyReport( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: safeCustodyReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                LOGGER.info( "To date is:  " + reportForm.getToDate() );
                LOGGER.info( "From date is:  " + reportForm.getFromDate() );
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().getINAReports( reportForm, ReportEnum.SAFE_CUSTODY_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "Size of safeCustodyReport report is :: " + repDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "activationDate" );
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
        return inMapping.findForward( IActionForwardConstant.SAFE_CUSTODY_REPORT );
    }*/
    public ActionForward barringReport( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: barringReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        if ( !reportForm.isToSearch() )
        {
            reportForm.setToDate( sdf.format( new Date() ) );
            reportForm.setFromDate( sdf.format( new Date() ) );
        }
        else
        {
            if ( StringUtils.isNotBlank( reportForm.getToDate() ) && StringUtils.isNotBlank( reportForm.getFromDate() ) )
            {
                LOGGER.info( "To date is:  " + reportForm.getToDate() );
                LOGGER.info( "From date is:  " + reportForm.getFromDate() );
                ReportDto repDto = new ReportDto();
                repDto = getReportManager().getINAReports( reportForm, ReportEnum.BARRING_REPORT );
                if ( CommonValidator.isValidCollection( repDto.getInaReportPojos() ) )
                {
                    LOGGER.info( "Size of barring report is :: " + repDto.getInaReportPojos().size() );
                    SortingComparator<InaReportPojo> sorter = new SortingComparator<InaReportPojo>( "ftSubmittionDate" );
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
        return inMapping.findForward( IActionForwardConstant.BARRING_REPORT );
    }

    public ActionForward reopenTicketReport( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( reportForm.isToSearch() )
        {
            ReportDto reportDto = getReportManager().ticketReport( reportForm, ReportEnum.REOPEN_TICKET_REPOERT );
            reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
        }
        else
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        return inMapping.findForward( IActionForwardConstant.REOPEN_TICKET_REPORT );
    }

    public ActionForward repeatTicketReport( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setFunctionalBins( CRMCacheManager.getQRCFunctionalBins() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        if ( reportForm.isToSearch() )
        {
            if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
            {
                LOGGER.info( "Category ID::" + reportForm.getQrcCategoryId() );
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
            
            ReportDto reportDto = getReportManager().ticketReport( reportForm, ReportEnum.REPEAT_TICKET_REPOERT );
            if ( CommonValidator.isValidCollection( reportDto.getQrcReportPojos() ) )
            {
                LOGGER.info( "qrcReport Size::" + reportDto.getQrcReportPojos().size() );
                SortingComparator<QrcTicketReportPojo> sorter = new SortingComparator<QrcTicketReportPojo>( "raisedDate" );
                Collections.sort( reportDto.getQrcReportPojos(), sorter );
                reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
            }
            else
            {
                reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
            }
            
        }
        else
        {
            reportForm.setToDate( DateUtils.getCurrentDateStr() );
            reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        }
        return inMapping.findForward( IActionForwardConstant.REPEAT_TICKET_REPORT );
    }

    /*public ActionForward categoryAndSlaWiseResolveTicketReport( final ActionMapping inMapping,
                                                                final ActionForm inForm,
                                                                final HttpServletRequest inRequest,
                                                                final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setToDate( DateUtils.getCurrentDateStr() );
        reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        return inMapping.findForward( IActionForwardConstant.CATEGORY_N_SLA_WISE_RESOLVE_TICKET_REPORT );
    }*/
    /* public ActionForward categoryWiseReopenTicketReport( final ActionMapping inMapping,
                                                          final ActionForm inForm,
                                                          final HttpServletRequest inRequest,
                                                          final HttpServletResponse inResponse )
     {
         CRMReportForm reportForm = (CRMReportForm) inForm;
         reportForm.setToDate( DateUtils.getCurrentDateStr() );
         reportForm.setFromDate( DateUtils.getCurrentDateStr() );
         reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
         return inMapping.findForward( IActionForwardConstant.CATEGORY_WISE_REOPEN_TICKET_REPORT );
     }*/
    public ActionForward catWiseOpenTicketReport( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: catWiseOpenTicketReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( reportForm.isToSearch() )
        {
            reportForm.setFunctionalBinId( 0L );
            ReportDto repDto = new ReportDto();
            repDto = getReportManager().catWiseticketReport( reportForm, ReportEnum.CATEGORY_WISE_OPEN_TICKET_REPORT );
            if ( StringUtils.isValidObj( repDto ) )
            {
                LOGGER.info( "Size of category wise open ticket report is :: " + repDto.getSlaReportPojos().size() );
                reportForm.setSlaReportPojos( repDto.getSlaReportPojos() );
            }
            else
            {
                LOGGER.info( "No record found..." );
                reportForm.setSlaReportPojos( null );
            }
            if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
            {
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
            {
                LOGGER.info( "Category ID::" + reportForm.getQrcCategoryId() );
                reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                        .getQrcCategoryId() ) );
                if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                        && reportForm.getQrcSubCategoryId() != 0 )
                {
                    reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                            .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                }
            }
            
        }
      //  reportForm.setQrcCategory( new CrmQrcCategoriesPojo() );
        //reportForm.setQrcSubCategory( new CrmQrcSubCategoriesPojo() );
        //reportForm.setQrcSubSubCategory( new CrmQrcSubSubCategoriesPojo() );
        reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
        return inMapping.findForward( IActionForwardConstant.CATEGORY_WISE_OPEN_TICKET_REPORT );
    }

    public ActionForward userWiseOpenTicketReport( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: userWiseOpenTicketReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( !reportForm.isToSearch() )
        {
            reportForm.setUserName( "" );
        }
        else
        {
            ReportDto reportDto = new ReportDto();
            reportDto = getReportManager().userWiseOpenticketReport( reportForm,
                                                                     ReportEnum.USER_WISE_OPEN_TICKET_REPORT );
            if ( StringUtils.isValidObj( reportDto ) )
            {
                LOGGER.info( "Size of user wise open ticket report is :: " + reportDto.getSlaReportPojos().size() );
                reportForm.setSlaReportPojos( reportDto.getSlaReportPojos() );
            }
            else
            {
                LOGGER.info( "No record found for user wise open ticket report." );
                reportForm.setSlaReportPojos( null );
            }
        }
        return inMapping.findForward( IActionForwardConstant.USER_WISE_OPEN_TICKET_REPORT );
    }

    /* public ActionForward userWiseReOpenResolvedTktReport( final ActionMapping inMapping,
                                                           final ActionForm inForm,
                                                           final HttpServletRequest inRequest,
                                                           final HttpServletResponse inResponse )
     {
         LOGGER.info( "In QRCReportAction :: userWiseReOpenResolvedTktReport" );
         CRMReportForm reportForm = (CRMReportForm) inForm;
         return inMapping.findForward( IActionForwardConstant.USER_WISE_REOPEN_RESOLVED_TICKET_REPORT );
     }*/
    public ActionForward lmsReportPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setFromDate( DateUtils.getCurrentDateStr() );
        reportForm.setToDate( DateUtils.getCurrentDateStr() );
        return inMapping.findForward( IActionForwardConstant.LMS_REPORT );
    }

    public ActionForward lmsReport( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        CRMReportForm inReportForm = (CRMReportForm) inForm;
        ReportDto reportDto = getReportManager().getLMSReport( inReportForm, ReportEnum.LMS_REPORT );
        if ( CommonValidator.isValidCollection( reportDto.getLmsReportPojos() ) )
        {
            LOGGER.info( "qrcReport Size::" + reportDto.getQrcReportPojos().size() );
            SortingComparator<LmsReportPojo> sorter = new SortingComparator<LmsReportPojo>( "createdTime" );
            Collections.sort( reportDto.getLmsReportPojos(), sorter );
            inReportForm.setLmsPojoList( reportDto.getLmsReportPojos() );
        }
        else
        {
            inReportForm.setLmsPojoList( null );
        }
        return inMapping.findForward( IActionForwardConstant.LMS_REPORT );
    }

    public ActionForward paymentReportPage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
    {
        CRMReportForm inReportForm = (CRMReportForm) inForm;
        inReportForm.setPaymentReportList( null );
        inReportForm.setToDate( DateUtils.getCurrentDateStr() );
        inReportForm.setFromDate( DateUtils.getCurrentDateStr() );
        inReportForm.setInstallationStatusList( CRMUtils.getInstallationStatusList() );
        inReportForm.setPaymentStatusList( CRMUtils.getPaymentRecievedStatus() );
        inReportForm.setCaseStatusList( CRMUtils.getPaymentCaseStatusList() );
        inReportForm.setCustomerServiceTypeList( CRMUtils.getCustomerServiceTypeList() );
        inReportForm.setChequeStatusList( CRMUtils.getChequeRealizationStatusList() );
        inReportForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        inReportForm.setPaymentModeList( CRMUtils.getPaymentMode() );
        inReportForm.setPaymentChannelList( CRMUtils.getPaymentChannelList() );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_REPORT );
    }

    public ActionForward paymentReport( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
    {
        CRMReportForm inReportForm = (CRMReportForm) inForm;
        ReportDto reportDto = getReportManager().getPaymentReport( inReportForm, ReportEnum.PAYMENT_REPORT );
        if ( CommonValidator.isValidCollection( reportDto.getPaymentReportPojos() ) )
        {
            LOGGER.info( "paymentReport Size::" + reportDto.getPaymentReportPojos().size() );
            SortingComparator<PaymentReportPojo> sorter = new SortingComparator<PaymentReportPojo>( "paymentDate" );
            Collections.sort( reportDto.getPaymentReportPojos(), sorter );
            inReportForm.setPaymentReportList( reportDto.getPaymentReportPojos() );
        }
        else
        {
            inReportForm.setPaymentReportList( null );
        }
        inReportForm.setInstallationStatusList( CRMUtils.getInstallationStatusList() );
        inReportForm.setPaymentStatusList( CRMUtils.getPaymentRecievedStatus() );
        inReportForm.setCaseStatusList( CRMUtils.getPaymentCaseStatusList() );
        inReportForm.setCustomerServiceTypeList( CRMUtils.getCustomerServiceTypeList() );
        inReportForm.setChequeStatusList( CRMUtils.getChequeRealizationStatusList() );
        inReportForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        inReportForm.setPaymentModeList( CRMUtils.getPaymentMode() );
        inReportForm.setPaymentChannelList( CRMUtils.getPaymentChannelList() );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_REPORT );
    }

    public ActionForward outOfSLATicketReport( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        LOGGER.info( "In QRCReportAction :: outOfSLATicketReport" );
        CRMReportForm reportForm = (CRMReportForm) inForm;
        if ( !reportForm.isToSearch() )
        {
            reportForm.setUserName( null );
        }
        else
        {
            ReportDto reportDto = new ReportDto();
            reportDto = getReportManager().userWiseOpenticketReport( reportForm, ReportEnum.OUT_OF_SLA_TICKET_REPORT );
            if ( CommonValidator.isValidCollection( reportDto.getSlaReportPojos() ) )
            {
                LOGGER.info( "Size of user wise out of sla open ticket report is :: "
                        + reportDto.getSlaReportPojos().size() );
                reportForm.setSlaReportPojos( reportDto.getSlaReportPojos() );
            }
            else
            {
                LOGGER.info( "No record found for user wise open ticket report." );
                reportForm.setSlaReportPojos( null );
            }
        }
        return inMapping.findForward( IActionForwardConstant.OUT_OF_SLA_TICKET_REPORT );
    }
    /* public ActionForward ctgryWiseResolveTckt( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
     {
         CRMReportForm reportForm = (CRMReportForm) inForm;
         reportForm.setQrcCategoryList( QRCCacheManager.getActiveQrcCategories() );
         if ( !reportForm.isToSearch() )
         {
             reportForm.setToDate( DateUtils.getCurrentDateStr() );
             reportForm.setFromDate( DateUtils.getCurrentDateStr() );
         }
         else
         {
             ReportDto reportDto = getReportManager().ticketReport( reportForm,
                                                                    ReportEnum.CATEGORY_WISE_RESOLVED_TICKET_REPORT );
             reportForm.setQrcReportPojos( reportDto.getQrcReportPojos() );
             if ( StringUtils.isValidObj( reportForm.getQrcCategoryId() ) && reportForm.getQrcCategoryId() != 0 )
             {
                 reportForm.setQrcSubCategoryList( QRCCacheManager.getActiveQrcSubCategories( reportForm
                         .getQrcCategoryId() ) );
                 if ( StringUtils.isValidObj( reportForm.getQrcSubCategoryId() )
                         && reportForm.getQrcSubCategoryId() != 0 )
                 {
                     reportForm.setQrcSubSubCategoryList( QRCCacheManager.getActiveQrcSubSubCategories( null, reportForm
                             .getQrcCategoryId(), reportForm.getQrcSubCategoryId() ) );
                 }
             }
         }
         return inMapping.findForward( IActionForwardConstant.CATEGORY_WISE_RESOLVE_TICKET_REPORT );
     }*/
}
