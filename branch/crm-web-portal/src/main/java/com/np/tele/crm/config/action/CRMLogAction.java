package com.np.tele.crm.config.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.config.forms.CRMLogForm;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.qrc.profile.bm.ICustomerProfileMgr;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmBillCyclePojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CRMLogAction
    extends DispatchAction
{
    private static final Logger LOGGER             = Logger.getLogger( CRMLogAction.class );
    private ICrmConfigManager   crmConfigManager   = null;
    private ICustomerProfileMgr customerProfileMgr = null;

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ICustomerProfileMgr getCustomerProfileMgr()
    {
        return customerProfileMgr;
    }

    public void setCustomerProfileMgr( ICustomerProfileMgr inCustomerProfileMgr )
    {
        customerProfileMgr = inCustomerProfileMgr;
    }

    public ActionForward getRemarks( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        LOGGER.info( "CRMLogAction::::getRemarks()" );
        ConfigDto configDto = null;
        String mappingId = inRequest.getParameter( "mappingId" );
        LOGGER.info( "mapping id is :: " + mappingId );
        CRMLogForm logForm = (CRMLogForm) inForm;
        logForm.setRemarksPojoList( null );
        if ( StringUtils.isValidObj( mappingId ) )
        {
            configDto = new ConfigDto();
            configDto.setMappingId( mappingId );
            configDto = getCrmConfigManager().getRemarks( configDto );
            if ( CommonValidator.isValidCollection( configDto.getRemarksPojos() ) )
            {
                logForm.setRemarksPojoList( configDto.getRemarksPojos() );
                SortingComparator<RemarksPojo> sorter = new SortingComparator<RemarksPojo>( "createdTime" );
                Collections.sort( logForm.getRemarksPojoList(), Collections.reverseOrder( sorter ) );
                sorter = null;
            }
        }
        else
        {
            List<RemarksPojo> pojos = new ArrayList<RemarksPojo>();
            logForm.setRemarksPojoList( pojos );
        }
        return inMapping.findForward( IActionForwardConstant.CUSTOMER_REMARKS );
    }

    public ActionForward getBillCycleHistory( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        String customerId = null;
        CRMLogForm logForm = (CRMLogForm) inForm;
        if ( StringUtils.isValidObj( inRequest.getParameter( "customerId" ) ) )
        {
            customerId = inRequest.getParameter( "customerId" );
        }
        else
        {
            customerId = logForm.getCustomerId();
            LOGGER.info( "Custoemr Id from form" + customerId );
        }
        if ( StringUtils.isValidObj( customerId ) )
        {
            CrmQrcDto qrcDto = getCustomerProfileMgr().getCustomerBillCycle( customerId );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && !qrcDto.getCrmBillCyclePojosList().isEmpty() )
            {
                if ( !qrcDto.getCrmBillCyclePojosList().isEmpty() )
                {
                    logForm.setCrmBillCyclePojosList( qrcDto.getCrmBillCyclePojosList() );
                    SortingComparator<CrmBillCyclePojo> sorter = new SortingComparator<CrmBillCyclePojo>( "createdTime" );
                    Collections.sort( logForm.getCrmBillCyclePojosList(), Collections.reverseOrder( sorter ) );
                    sorter = null;
                    LOGGER.info( "Bill Cycle lIst size::;" + qrcDto.getCrmBillCyclePojosList().size() );
                }
            }
            else
            {
                logForm.setCrmBillCyclePojosList( new ArrayList<CrmBillCyclePojo>() );
            }
        }
        return inMapping.findForward( IActionForwardConstant.CUSTOMER_BILL_CYCLE );
    }

    public ActionForward getTicketHistory( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "CRMLogAction::::getTicketHistory()" );
        ConfigDto configDto = null;
        String mappingId = inRequest.getParameter( "mappingId" );
        LOGGER.info( "mapping id is :: " + mappingId );
        CRMLogForm logForm = (CRMLogForm) inForm;
        logForm.setTicketHistoryList( new ArrayList<CrmTicketHistoryPojo>() );
        if ( StringUtils.isValidObj( mappingId ) )
        {
            configDto = getCrmConfigManager().listData( CrmTicketHistoryPojo.class.getSimpleName(), mappingId, null );
            if ( CommonValidator.isValidCollection( configDto.getTicketHistoryList() ) )
            {
                SortingComparator<CrmTicketHistoryPojo> sorter = new SortingComparator<CrmTicketHistoryPojo>( "createdTime" );
                Collections.sort( configDto.getTicketHistoryList(), Collections.reverseOrder( sorter ) );
                sorter = null;
            }
            logForm.setTicketHistoryList( configDto.getTicketHistoryList() );
        }
        return inMapping.findForward( "ticketHistory" );
    }
}
