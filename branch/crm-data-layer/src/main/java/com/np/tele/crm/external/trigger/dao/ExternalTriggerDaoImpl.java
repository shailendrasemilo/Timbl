package com.np.tele.crm.external.trigger.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.exceptions.DuplicateRecieptException;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.CrmValueFirstRequestPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;

public class ExternalTriggerDaoImpl
    implements IExternalTriggerDao
{
    private static final Logger LOGGER        = Logger.getLogger( ExternalTriggerDaoImpl.class );
    private IQrcManagerDao      qrcManagerDao = null;
    private static long         EOC_RB_ID     = 0l;
    private static long         EOCS_ID       = 0l;

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao inQrcManagerDao )
    {
        qrcManagerDao = inQrcManagerDao;
    }
    static
    {
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "category", CRMRCAReason.CRM.getStatusCode() );
        criteriaMap.put( "subCategory", CRMRCAReason.CRM_MASTER.getStatusCode() );
        criteriaMap.put( "subSubCategory", CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        criteriaMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
        criteriaMap.put( "valueAlias", CRMOperationStages.EOC_RESOLVED_BIN.getCode() );
        CrmRcaReasonPojo crmRcaReason = CRMServiceUtils.getDBValue( CrmRcaReasonPojo.class, criteriaMap, null, true );
        if ( StringUtils.isValidObj( crmRcaReason ) )
        {
            EOC_RB_ID = crmRcaReason.getCategoryId();
        }
        Map<String, Object> eocsMap = new HashMap<String, Object>();
        eocsMap.put( "category", CRMRCAReason.CRM.getStatusCode() );
        eocsMap.put( "subCategory", CRMRCAReason.CRM_MASTER.getStatusCode() );
        eocsMap.put( "subSubCategory", CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        eocsMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
        eocsMap.put( "valueAlias", CRMOperationStages.EOC_SERVICE.getCode() );
        CrmRcaReasonPojo crmRcaReasonPojo = CRMServiceUtils.getDBValue( CrmRcaReasonPojo.class, eocsMap, null, true );
        if ( StringUtils.isValidObj( crmRcaReasonPojo ) )
        {
            EOCS_ID = crmRcaReasonPojo.getCategoryId();
        }
    }

    @Override
    public TriggerResponseDto changeCustomerStatus( CustomerDetailsPojo inCustomerDetailsPojo,
                                                    TriggerRequestDto inTriggerRequestDto,
                                                    TriggerResponseDto inResponseDto )
    {
        LOGGER.info( "inside :changeCustomerStatus " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMCustomerActivityActions activityAction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Calendar cal = Calendar.getInstance();
            activityLog = new CrmCustomerActivityPojo();
            if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                    || StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.BARRED.getStatusCode() )
                    || StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.TDC.getStatusCode() )
                    //                    || StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.SC.getStatusCode() )
                    || StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.PDC.getStatusCode() ) )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                      inTriggerRequestDto.getCustomerId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    LOGGER.info( "Customer ID Found:: " + crmCustomerDetailsPojo.getCustomerId() );
                    if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                            || StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                   CRMStatusCode.BARRED.getStatusCode() )
                            || StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                   CRMStatusCode.TDC.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                     CRMStatusCode.TDC.getStatusCode() ) )
                            {
                                activityAction = CRMCustomerActivityActions.REMOVE_TDC;
                            }
                            else
                            {
                                activityAction = CRMCustomerActivityActions.UNBARRING;
                                crmCustomerDetailsPojo.setUnBarringDate( cal.getTime() );
                            }
                        }
                        else if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(),
                                                      CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            activityAction = CRMCustomerActivityActions.BARRING;
                            crmCustomerDetailsPojo.setBarringDate( cal.getTime() );
                        }
                        else if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(),
                                                      CRMStatusCode.TDC.getStatusCode() ) )
                        {
                            activityAction = CRMCustomerActivityActions.TDC;
                            crmCustomerDetailsPojo.setTempDisconnectDate( cal.getTime() );
                        }
                        else if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(),
                                                      CRMStatusCode.PDC.getStatusCode() ) )
                        {
                            activityAction = CRMCustomerActivityActions.PDC;
                            crmCustomerDetailsPojo.setPermanentDisconnectDate( cal.getTime() );
                        }
                        if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() )
                                && StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                       CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        {
                            CrmBillingPlanRequestPojo billingPlanRequestPojo = new CrmBillingPlanRequestPojo();
                            billingPlanRequestPojo.setTransId( inTriggerRequestDto.getTransId() );
                            billingPlanRequestPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            billingPlanRequestPojo.setCreatedBy( "Billing" );
                            billingPlanRequestPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            billingPlanRequestPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                            Long generatedId = (Long) session.save( billingPlanRequestPojo );
                            LOGGER.info( "Successfully Save CrmBillingPlanRequestPojo. Generated Id ::::" + generatedId );
                        }
                        CRMServiceUtils.fillActivityDetails( crmCustomerDetailsPojo, inCustomerDetailsPojo,
                                                             activityLog, activityAction );
                        crmCustomerDetailsPojo.setStatus( inCustomerDetailsPojo.getStatus() );
                        crmCustomerDetailsPojo.setLastModifiedBy( "Billing" );
                        crmCustomerDetailsPojo.setLastModifiedTime( cal.getTime() );
                        session.merge( crmCustomerDetailsPojo );
                        long activityId = prepareAndSaveActivityLog( inTriggerRequestDto,
                                                                     crmCustomerDetailsPojo.getCustomerId(),
                                                                     activityLog, activityAction );
                        crmServiceCode = saveRemarks( session, inCustomerDetailsPojo, crmCustomerDetailsPojo,
                                                      inResponseDto, activityId, activityAction.getActionDesc() );
                        if ( StringUtils.equals( inCustomerDetailsPojo.getStatus(), CRMStatusCode.TDC.getStatusCode() ) )
                        {
                            try
                            {
                                CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( crmCustomerDetailsPojo
                                        .getCustomerId(), QRCRolCategories.TDC_SLAVE_RECOVERY, null, "Billing" );
                                if ( StringUtils.isValidObj( tmpDto.getCrmSrTicketsPojo() ) )
                                {
                                    LOGGER.info( "Slave Recovery Ticket Object:: " + tmpDto.getCrmSrTicketsPojo() );
                                }
                            }
                            catch ( SOAPException ex )
                            {
                                LOGGER.error( "Unable to generate ticket for " + QRCRolCategories.TDC_SLAVE_RECOVERY,
                                              ex );
                            }
                        }
                        if ( CRMServiceCode.CRM001 == crmServiceCode )
                        {
                            transaction.commit();
                            transaction = null;
                            crmServiceCode = CRMServiceCode.CRM001;
                            inResponseDto.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            inResponseDto.setCrmTransactionId( crmCustomerDetailsPojo.getRecordId() + "" );
                            LOGGER.info( "Successfully Update Customer Details and Remarks." );
                        }
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.CRM617;
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM603;
                }
            }
            else
            {
                LOGGER.info( "Status Unavailable/Invalid" );
                crmServiceCode = CRMServiceCode.CRM610;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In changeCustomerStatus Method :: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
        }
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }

    public long prepareAndSaveActivityLog( TriggerRequestDto inTriggerRequestDto,
                                           String inCustomerId,
                                           CrmCustomerActivityPojo activityLog,
                                           CRMCustomerActivityActions ativityAction )
    {
        activityLog.setCustomerId( inCustomerId );
        activityLog.setAction( ativityAction.getActionCode() );
        activityLog.setReason( ativityAction.getActionDesc() );
        activityLog.setCreatedBy( "Billing" );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inTriggerRequestDto.getClientIp() );
        activityLog.setServerIp( CRMUtils.getServerIp() );
        return CRMServiceUtils.createActivityLog( activityLog );
    }

    private static <E> CRMServiceCode saveRemarks( Session inSession,
                                                   E obj,
                                                   E objCrmCust,
                                                   TriggerResponseDto inResponseDto,
                                                   long inActivityId,
                                                   String inAtivityDesc )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        RemarksPojo remarksPojo = null;
        try
        {
            remarksPojo = new RemarksPojo();
            if ( obj instanceof CustomerDetailsPojo && objCrmCust instanceof CrmCustomerDetailsPojo )
            {
                CrmCustomerDetailsPojo customerDetailsPojo = (CrmCustomerDetailsPojo) objCrmCust;
                CustomerDetailsPojo customerPojo = (CustomerDetailsPojo) obj;
                remarksPojo.setCreatedBy( customerDetailsPojo.getLastModifiedBy() );
                remarksPojo.setMappingId( customerDetailsPojo.getCustomerId() );
                remarksPojo.setRemarks( customerPojo.getRemarks() );
                remarksPojo.setActions( inAtivityDesc );
            }
            remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
            remarksPojo.setActivityId( inActivityId );
            Long generatedId = (Long) inSession.save( remarksPojo );
            if ( generatedId > 0 )
            {
                statusCode = CRMServiceCode.CRM001;
                inResponseDto.setCrmTransactionId( String.valueOf( generatedId ) );
                LOGGER.info( "Successfully Save Remarks Pojo. Generated Id :: " + generatedId );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveRemarks Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        return statusCode;
    }

    @Override
    public TriggerResponseDto generateQRCTicket( QrcTicketPojo inQrcTicketPojo,
                                                 TriggerRequestDto inTriggerRequestDto,
                                                 TriggerResponseDto inResponseDto )
    {
        LOGGER.info( "inside :generateQRCTicket " );
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = null;
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo ticketsPojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            crmQrcDto = new CrmQrcDto();
            ticketsPojo = new CrmSrTicketsPojo();
            subSubCategoriesPojo = CRMServiceUtils.getDBValues( CrmQrcSubSubCategoriesPojo.class, "qrcSubSubCategory",
                                                                inQrcTicketPojo.getQrcEvent() );
            if ( StringUtils.isValidObj( subSubCategoriesPojo ) )
            {
                CrmQrcSubCategoriesPojo subCategoriesPojo = CRMServiceUtils
                        .getDBValues( CrmQrcSubCategoriesPojo.class, "qrcSubCategoryId",
                                      subSubCategoriesPojo.getQrcSubCategoryId() );
                ticketsPojo.setQrcSubSubCategoryId( subSubCategoriesPojo.getQrcSubSubCategoryId() );
                ticketsPojo.setQrcSubCategoryId( subSubCategoriesPojo.getQrcSubCategoryId() );
                ticketsPojo.setQrcCategoryId( subCategoriesPojo.getQrcCategoryId() );
                LOGGER.info( "ids category:: " + ticketsPojo.getQrcCategoryId() + " subcat:: "
                        + ticketsPojo.getQrcSubCategoryId() + " subsubcat:: " + ticketsPojo.getQrcSubSubCategoryId() );
                ticketsPojo.setResolutionType( CrmTicketActions.FORWARD.getCode() );
                ticketsPojo.setFunctionalbinId( subSubCategoriesPojo.getFunctionalBinId() );
                ticketsPojo.setMappingId( inTriggerRequestDto.getCustomerId() );
                ticketsPojo.setQrcType( subSubCategoriesPojo.getQrcType() );
                ticketsPojo.setModuleType( CRMRequestType.QRC.getRequestCode() );
                ticketsPojo.setCreatedBy( inTriggerRequestDto.getAuthUsername() );
                //set value in remarks 
                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                ticketHistory.setRemarks( inQrcTicketPojo.getRemarks() );
                ticketHistory.setCreatedBy( inTriggerRequestDto.getAuthUsername() );
                //set value in crmQrcDto 
                crmQrcDto.setTicketHistory( ticketHistory );
                crmQrcDto.setCrmSrTicketsPojo( ticketsPojo );
                crmQrcDto.setClientIPAddress( inTriggerRequestDto.getClientIp() );
                crmQrcDto.setServerIPAddress( CRMUtils.getServerIp() );
                //call Open Ticket method
                crmQrcDto = CRMServicesProxy
                        .getInstance()
                        .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .ticketOperations( CrmActionEnum.OPENED.getActionCode(),
                                           CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                if ( StringUtils.isValidObj( crmQrcDto )
                        && StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Status Code::" + crmQrcDto.getStatusCode() );
                    crmServiceCode = CRMServiceCode.CRM001;
                    inResponseDto.setCrmTransactionId( crmQrcDto.getCrmSrTicketsPojo().getSrId() );
                    inResponseDto.setCustomerId( crmQrcDto.getCrmSrTicketsPojo().getMappingId() );
                }
                else if ( StringUtils.isValidObj( crmQrcDto )
                        && StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM404.getStatusCode() ) )
                {
                    crmServiceCode = CRMServiceCode.CRM613;
                    inResponseDto.setCrmTransactionId( crmQrcDto.getCrmSrTicketsPojo().getSrId() );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM607;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In generateQRCTicket Method :: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        LOGGER.info( crmServiceCode.getStatusDesc() );
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }

    @Override
    public TriggerResponseDto postPrepaidPayment( PrepaidPaymentPojo inPrepaidPaymentPojo,
                                                  TriggerRequestDto inTriggerRequestDto,
                                                  TriggerResponseDto inResponseDto )
    {
        LOGGER.info( "inside :postPrepaidPayment " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = null;
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            LOGGER.info( "receiptNo::" + inPrepaidPaymentPojo.getReceiptNo() );
            dbCrmPaymentDetailsPojo = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class, "receiptNo",
                                                                   inPrepaidPaymentPojo.getReceiptNo() );
            if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
            {
                throw new DuplicateRecieptException( CRMServiceCode.CRM611.getStatusCode(),
                                                     inPrepaidPaymentPojo.getReceiptNo() );
            }
            else
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                      inTriggerRequestDto.getCustomerId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                             CRMDisplayListConstants.PRE_PAID.getCode() ) )
                    {
                        transaction = session.beginTransaction();
                        crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
                        crmPaymentDetailsPojo.setAmount( inPrepaidPaymentPojo.getAmount().doubleValue() );
                        crmPaymentDetailsPojo.setCreatedBy( "Billing" );
                        crmPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        crmPaymentDetailsPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                        crmPaymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.DIRECT.getCode() );
                        crmPaymentDetailsPojo.setPaymentMode( CRMDisplayListConstants.CASH.getCode() );
                        crmPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                        crmPaymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
                        crmPaymentDetailsPojo.setReceiptNo( inPrepaidPaymentPojo.getReceiptNo() );
                        crmPaymentDetailsPojo.setPaymentDate( inPrepaidPaymentPojo.getPaymentDate() );
                        crmPaymentDetailsPojo.setPostingDate( Calendar.getInstance().getTime() );
                        crmPaymentDetailsPojo.setCustomerRecordId( crmCustomerDetailsPojo.getRecordId() );
                        crmPaymentDetailsPojo.setServiceType( crmCustomerDetailsPojo.getServiceType() );
                        if ( StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() ) )
                        {
                            crmPaymentDetailsPojo.setInstallationStatus( CRMDisplayListConstants.POSTINSTALLATION
                                    .getCode() );
                        }
                        else
                        {
                            crmPaymentDetailsPojo.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION
                                    .getCode() );
                        }
                        Long generatedId = (Long) session.save( crmPaymentDetailsPojo );
                        transaction.commit();
                        if ( generatedId > 0 )
                        {
                            crmServiceCode = CRMServiceCode.CRM001;
                            Calendar cal = Calendar.getInstance();
                            cal.setTime( crmPaymentDetailsPojo.getCreatedTime() );
                            cal.set( Calendar.MILLISECOND, 0 );
                            StringBuilder sb = new StringBuilder();
                            sb.append( crmPaymentDetailsPojo.getCustomerRecordId() ).append( IAppConstants.UNDERSCORE )
                                    .append( crmPaymentDetailsPojo.getEntityType() ).append( IAppConstants.UNDERSCORE )
                                    .append( cal.getTimeInMillis() );
                            inResponseDto.setCrmTransactionId( sb.toString() );
                            inResponseDto.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            LOGGER.info( "Successfully save post Prepaid Payment.Generated Id :: " + generatedId );
                        }
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.CRM612;
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM603;
                }
            }
        }
        catch ( Exception ex )
        {
            if ( ex instanceof DuplicateRecieptException )
            {
                crmServiceCode = CRMServiceCode.valueOf( ex.getMessage() );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM999;
                LOGGER.error( "Getting Error In postPrepaidPayment Method :: ", ex );
            }
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmPaymentDetailsPojo.class.getName(), null, null );
            }
        }
        LOGGER.info( crmServiceCode.getStatusDesc() );
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }

    @Override
    public boolean updateSRTicket( String inSrId, String inMessage, String inSender )
    {
        LOGGER.info( "inside :updateSRTicket" );
        CrmQrcDto qrcDto = null;
        CrmTicketHistoryPojo ticketHistory = null;
        CrmSrTicketsPojo crmSrTicketsPojo = null;
        boolean success = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        boolean flag = false;
        try
        {
            qrcDto = new CrmQrcDto();
            ticketHistory = new CrmTicketHistoryPojo();
            if ( StringUtils.isNotBlank( inSrId ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmRcaReasonPojo.class );
                criteria.add( Restrictions.eq( "category", CRMRCAReason.CRM.getStatusCode() ) );
                criteria.add( Restrictions.eq( "subCategory", CRMRCAReason.CRM_MASTER.getStatusCode() ) );
                criteria.add( Restrictions.eq( "subSubCategory", CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.add( Restrictions.like( "categoryValue", "IFR", MatchMode.ANYWHERE ) );
                List<CrmRcaReasonPojo> rcaReasonPojos = criteria.list();
                crmSrTicketsPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inSrId );
                if ( StringUtils.isValidObj( crmSrTicketsPojo ) )
                {
                    for ( CrmRcaReasonPojo crmRcaReasonPojo : rcaReasonPojos )
                    {
                        if ( crmRcaReasonPojo.getCategoryId() == crmSrTicketsPojo.getFunctionalbinId() )
                        {
                            flag = true;
                            break;
                        }
                    }
                    if ( EOC_RB_ID > 0 && EOCS_ID == crmSrTicketsPojo.getFunctionalbinId() || flag )
                    {
                        ticketHistory.setAction( CrmActionEnum.FORWARDED.getActionCode() );
                        ticketHistory.setRemarks( inMessage );
                        ticketHistory.setCreatedBy( "SMS" + IAppConstants.SPACE + IAppConstants.DASH
                                + IAppConstants.SPACE + inSender );
                        crmSrTicketsPojo.setLastModifiedBy( "SMS" + IAppConstants.SPACE + IAppConstants.DASH
                                + IAppConstants.SPACE + inSender );
                        qrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
                        qrcDto.setTicketHistory( ticketHistory );
                        qrcDto.setFutureStage( EOC_RB_ID + "" );
                        qrcDto = CRMServicesProxy
                                .getInstance()
                                .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                .ticketOperations( CrmActionEnum.FORWARDED.getActionCode(),
                                                   CrmSrTicketsPojo.class.getSimpleName(), qrcDto );
                        if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            success = true;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Either EOC Resolved Bin not found in DB or ticket is not under in EOC Service Bin" );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error In updateSRTicket Method :: ", ex );
        }
        return success;
    }

    @Override
    public Long saveFirstRequestValue( String inTo, String inMessage, String inOprator, String inSender, String inDate )
    {
        LOGGER.info( "inside :saveFirstRequestValue " );
        CrmValueFirstRequestPojo firstRequestPojo = null;
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
            if ( StringUtils.isNotBlank( inMessage ) )
            {
                firstRequestPojo = new CrmValueFirstRequestPojo();
                firstRequestPojo.setReqTo( "" );
                firstRequestPojo.setReqFrom( inSender );
                firstRequestPojo.setReqMessage( inMessage );
                firstRequestPojo.setReqOprator( "" );
                firstRequestPojo.setReqDate( Calendar.getInstance().getTime() );
                firstRequestPojo.setStatus( CRMStatusCode.ATTEMPTED.getStatusCode() );
                firstRequestPojo.setCreatedBy( "SYSTEM" );
                firstRequestPojo.setCreatedTime( Calendar.getInstance().getTime() );
                return CRMServiceUtils.saveDBValues( firstRequestPojo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error In saveFirstRequestValue Method :: ", ex );
        }
        return 0L;
    }

    @Override
    public void updateFirstRequestValue( Long inRecordId )
    {
        LOGGER.info( "inside :updateFirstRequestValue " );
        CrmValueFirstRequestPojo firstRequestPojo = null;
        try
        {
            firstRequestPojo = CRMServiceUtils.getDBValues( CrmValueFirstRequestPojo.class, inRecordId );
            if ( StringUtils.isValidObj( firstRequestPojo ) )
            {
                firstRequestPojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                CRMServiceUtils.mergeDBValues( firstRequestPojo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error In updateFirstRequestValue Method :: ", ex );
        }
    }

    @Override
    public TriggerResponseDto changePlanStatus( CrmBillingPlanRequestPojo inBillingPlanRequestPojo,
                                                TriggerRequestDto inTriggerRequestDto,
                                                TriggerResponseDto inResponseDto )
    {
        LOGGER.info( "inside :changePlanStatus " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            inBillingPlanRequestPojo.setCreatedTime( Calendar.getInstance().getTime() );
            inBillingPlanRequestPojo.setCreatedBy( "Billing" );
            inBillingPlanRequestPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            Long generatedId = (Long) session.save( inBillingPlanRequestPojo );
            if ( generatedId > 0 )
            {
                transaction.commit();
                transaction = null;
                crmServiceCode = CRMServiceCode.CRM001;
                inResponseDto.setCustomerId( inBillingPlanRequestPojo.getCustomerId() );
                inResponseDto.setCrmTransactionId( generatedId + "" );
                inResponseDto.setExtTransactionId( inBillingPlanRequestPojo.getTransId() + "" );
                LOGGER.info( "Successfully Save CrmBillingPlanRequestPojo. Generated Id ::::" + generatedId );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Save changePlanStatus Method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In changePlanStatus Method :: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }
}
