package com.np.tele.crm.qrc.profile.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.MDC;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CrmCustAdditionalDetails;
import com.np.tele.crm.ext.pojos.ValidityExtensionPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmBillCyclePojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmNationalityDetailsPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.CrmWorkflowSequence;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CustomerProfileDaoImpl
    implements ICustomerProfileDao
{
    private static final Logger LOGGER            = Logger.getLogger( CustomerProfileDaoImpl.class );
    private ICrmBillingManager  crmBillingManager = null;
    private IQrcManagerDao      qrcManagerDao;

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager crmBillingManager )
    {
        this.crmBillingManager = crmBillingManager;
    }

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao qrcManagerDao )
    {
        this.qrcManagerDao = qrcManagerDao;
    }

    @Override
    public CrmQrcDto updateBillingCycle( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        long id = 0;
        try
        {
            LOGGER.info( "update Billing cycle method Called:: " + inCrmQrcDto.getSrTicketNo() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                {
                    boolean valid = getQrcManagerDao().isValidTicketNo( inCrmQrcDto.getSrTicketNo(),
                                                                        inCrmQrcDto.getCustomerDetailsPojo()
                                                                                .getCustomerId() );
                    if ( !valid )
                    {
                        crmServiceCode = CRMServiceCode.CRM402;
                    }
                }
                else
                {
                    LOGGER.info( "Not Valid Obj" );
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                boolean inProcess = isAllReadyInitiatedBillCycleRequest( inCrmQrcDto.getCustomerDetailsPojo()
                        .getCustomerId(), session );
                if ( !inProcess )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                            && StringUtils.isNotEmpty( inCrmQrcDto.getUsageToDate() ) )
                    {
                        //Billing Call start heres
                        boolean toCommit = false;
                        CrmBillingDto billingDto = new CrmBillingDto();
                        billingDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                        billingDto.setParamValue( inCrmQrcDto.getUsageToDate() );
                        billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                        getCrmBillingManager().changeBillCycle( billingDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            CrmWorkflowSequence workflowSequence = new CrmWorkflowSequence();
                            Long workflowId = (Long) session.save( workflowSequence );
                            CrmBillCyclePojo crmBillCyclePojo = new CrmBillCyclePojo();
                            crmBillCyclePojo.setWorkflowId( "W" + workflowId );
                            crmBillCyclePojo.setBillDate( inCrmQrcDto.getUsageToDate() );
                            crmBillCyclePojo.setApiTransactionId( billingDto.getParamValue() );
                            crmBillCyclePojo.setCreatedBy( inCrmQrcDto.getUserId() );
                            crmBillCyclePojo.setTicketId( inCrmQrcDto.getSrTicketNo() );
                            crmBillCyclePojo.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                            crmBillCyclePojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                            crmBillCyclePojo.setProcessingDate( DateUtils.getNextBillingDate( inCrmQrcDto
                                    .getCustomerDetailsPojo().getBillDate() ) );
                            id = (Long) session.save( crmBillCyclePojo );
                            evicts.put( CrmBillCyclePojo.class.getName(), crmBillCyclePojo.getBillCycleId() );
                            toCommit = true;
                            // follow up call with  DateUtils.getNextBillingDate( inCrmQrcDto.getCustomerDetailsPojo().getBillDate() )
                            CrmSrTicketsPojo ticketPojo = new CrmSrTicketsPojo();
                            ticketPojo.setSrId( inCrmQrcDto.getSrTicketNo() );
                            ticketPojo.setFollowupOn( DateUtils.getNextBillingDate( inCrmQrcDto
                                    .getCustomerDetailsPojo().getBillDate() ) );
                            ticketPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                            CrmTicketHistoryPojo history = new CrmTicketHistoryPojo();
                            history.setAction( CrmActionEnum.FOLLOW_UP.getActionCode() );
                            history.setCreatedBy( inCrmQrcDto.getUserId() );
                            history.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                            history.setTicketId( crmBillCyclePojo.getTicketId() );
                            inCrmQrcDto.setTicketHistory( history );
                            CRMServicesProxy
                                    .getInstance()
                                    .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                    .ticketOperations( CrmActionEnum.FOLLOW_UP.getActionCode(),
                                                       CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Status Code ::" + crmServiceCode );
                        }
                        //data Base call start here
                        if ( toCommit )
                        {
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                            {
                                RemarksPojo remarksPojo = new RemarksPojo();
                                remarksPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                                remarksPojo.setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                                remarksPojo.setMappingId( "W" + id );
                                remarksPojo.setActions( CRMCustomerActivityActions.BILL_CYCLE_MODIFY.getActionCode() );
                                remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                session.save( remarksPojo );
                                evicts.put( RemarksPojo.class.getName(), null );
                            }
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                    else
                        crmServiceCode = CRMServiceCode.CRM408;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM434;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Occured::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        return inCrmQrcDto;
    }

    private boolean isAllReadyInitiatedBillCycleRequest( String customerId, Session session )
    {
        Criteria criteria = session.createCriteria( CrmBillCyclePojo.class );
        criteria.add( Restrictions.eq( "customerId", customerId ) );
        criteria.add( Restrictions.eq( "status", CRMStatusCode.INPROCESS.getStatusCode() ) );
        criteria.setCacheable( true );
        criteria.setCacheRegion( CrmBillCyclePojo.class.getName() );
        List<CrmBillCyclePojo> crmBillCyclePojos = criteria.list();
        if ( CommonValidator.isValidCollection( crmBillCyclePojos ) )
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public CrmQrcDto updateCustomerProfile( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "DAO updateCustomerProfile called::" );
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                {
                    boolean valid = getQrcManagerDao().isValidTicketNo( inCrmQrcDto.getSrTicketNo(),
                                                                        inCrmQrcDto.getCustomerDetailsPojo()
                                                                                .getCustomerId() );
                    if ( !valid )
                    {
                        crmServiceCode = CRMServiceCode.CRM402;
                    }
                    else
                        activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                }
                else
                {
                    LOGGER.info( "Not Valid Obj" );
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                        && inCrmQrcDto.getCustomerDetailsPojo().getRecordId() > 0 )
                {
                    CRMCustomerActivityActions activityActions = CRMCustomerActivityActions
                            .getObjByActionDesc( inCrmQrcDto.getActivityAction() );
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                            .get( CrmCustomerDetailsPojo.class, inCrmQrcDto.getCustomerDetailsPojo().getRecordId() );
                    LOGGER.info( "Requested VAlue" + inCrmQrcDto.getCustomerDetailsPojo() );
                    LOGGER.info( "DB VAlue" + crmCustomerDetailsPojo );
                    if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                    {
                        CRMServiceUtils.fillActivityDetails( crmCustomerDetailsPojo,
                                                             inCrmQrcDto.getCustomerDetailsPojo(), activityLog,
                                                             activityActions );
                        BeanUtils.copyProperties( crmCustomerDetailsPojo, inCrmQrcDto.getCustomerDetailsPojo() );
                        //                        CRMUtils.copyPropertyValue( crmCustomerDetailsPojo, inCrmQrcDto.getCustomerDetailsPojo(), null );
                        CrmBillingDto billingDto = new CrmBillingDto();
                        billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                        billingDto = getCrmBillingManager().updateCustomer( inCrmQrcDto.getCustomerDetailsPojo(),
                                                                            Long.parseLong( inCrmQrcDto
                                                                                    .getCustomerDetailsPojo()
                                                                                    .getCustomerId() ) );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( crmCustomerDetailsPojo );
                            /**Nationality Updation **/
                            if ( !StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getNationality(),
                                                      IAppConstants.INDIAN )
                                    && StringUtils.isValidObj( inCrmQrcDto.getNationaltyDetailPojo() ) )
                            {
                                updateNationality( inCrmQrcDto, session, evicts );
                            }
                            /**Document Updation **/
                            if ( StringUtils.isValidObj( inCrmQrcDto.getDocumetDetailsPojo() ) )
                            {
                                updateDocumetDetail( inCrmQrcDto, session, evicts );
                            }
                            if ( StringUtils.isBlank( activityLog.getTicketId() ) )
                            {
                                CrmQrcDto tmpDto = CRMServiceUtils
                                        .generateAutoTicket( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId(),
                                                             QRCRolCategories.UPDATE_CONNECTION_TYPE, null, inCrmQrcDto
                                                                     .getUserId(), inCrmQrcDto.getRemarksPojo()
                                                                     .getRemarks() );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                                {
                                    activityLog.setTicketId( tmpDto.getCrmSrTicketsPojo().getSrId() );
                                    inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                                    inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                                }
                            }
                            /**generate Activity**/
                            activityLog.setReason( activityActions.getActionDesc() );
                            generateActivity( inCrmQrcDto, activityLog );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                            LOGGER.info( "Success Service Code::" + crmServiceCode );
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Status Code ::" + crmServiceCode );
                        }
                    }
                    else
                        crmServiceCode = CRMServiceCode.CRM408;
                }
                else
                    crmServiceCode = CRMServiceCode.CRM408;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Occured::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception Occured:::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            LOGGER.info( "finally Service Code::" + inCrmQrcDto.getStatusCode() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        return inCrmQrcDto;
    }

    private void updateNationality( CrmQrcDto inCrmQrcDto, Session session, Map<String, Long> inEvicts )
    {
        if ( inCrmQrcDto.getNationaltyDetailPojo().getCustomerRecordId() > 0 )
        {
            CrmNationalityDetailsPojo nationalityPojo = CRMServiceUtils.getDBValues( CrmNationalityDetailsPojo.class,
                                                                                     "customerRecordId", inCrmQrcDto
                                                                                             .getNationaltyDetailPojo()
                                                                                             .getCustomerRecordId(),
                                                                                     session );
            CRMUtils.copyPropertyValue( nationalityPojo, inCrmQrcDto.getNationaltyDetailPojo(), null );
            nationalityPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            nationalityPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            session.update( nationalityPojo );
            inEvicts.put( CrmNationalityDetailsPojo.class.getName(), nationalityPojo.getRecordId() );
        }
        else
        {
            inCrmQrcDto.getNationaltyDetailPojo().setCustomerRecordId( inCrmQrcDto.getCustomerDetailsPojo()
                                                                               .getRecordId() );
            inCrmQrcDto.getNationaltyDetailPojo().setCreatedBy( inCrmQrcDto.getUserId() );
            inCrmQrcDto.getNationaltyDetailPojo().setCreatedTime( Calendar.getInstance().getTime() );
            session.save( inCrmQrcDto.getNationaltyDetailPojo() );
            inEvicts.put( CrmNationalityDetailsPojo.class.getName(), null );
        }
    }

    private void updateDocumetDetail( CrmQrcDto inCrmQrcDto, Session session, Map<String, Long> inEvicts )
    {
        if ( inCrmQrcDto.getDocumetDetailsPojo().getRecordId() > 0 )
        {
            CrmDocumentDetailsPojo crmDocumentDetailsPojo = CRMServiceUtils
                    .getDBValues( CrmDocumentDetailsPojo.class, "customerRecordId", inCrmQrcDto.getDocumetDetailsPojo()
                            .getCustomerRecordId(), session );
            CRMUtils.copyPropertyValue( crmDocumentDetailsPojo, inCrmQrcDto.getDocumetDetailsPojo(), null );
            crmDocumentDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            crmDocumentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            session.update( crmDocumentDetailsPojo );
            inEvicts.put( CrmDocumentDetailsPojo.class.getName(), crmDocumentDetailsPojo.getRecordId() );
        }
        else
        {
            inCrmQrcDto.getDocumetDetailsPojo()
                    .setCustomerRecordId( inCrmQrcDto.getCustomerDetailsPojo().getRecordId() );
            inCrmQrcDto.getDocumetDetailsPojo().setCreatedBy( inCrmQrcDto.getUserId() );
            inCrmQrcDto.getDocumetDetailsPojo().setCreatedTime( Calendar.getInstance().getTime() );
            session.save( inCrmQrcDto.getDocumetDetailsPojo() );
            inEvicts.put( CrmDocumentDetailsPojo.class.getName(), null );
        }
    }

    private void generateActivity( CrmQrcDto inCrmQrcDto, CrmCustomerActivityPojo activityLog )
    {
        activityLog.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
        activityLog.setAction( inCrmQrcDto.getActivityAction() );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        CRMServiceUtils.createActivityLog( activityLog );
    }

    @Override
    public CrmQrcDto getBillCycleHistory( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CustomerProfileDaoImpl, getCustomerBillingCycle" );
        Session session = null;
        Criteria criteria = null;
        List<CrmBillCyclePojo> crmBillCyclePojos = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmBillCyclePojo.class );
            if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
            {
                LOGGER.info( "Customer id::" + inCrmQrcDto.getCustomerId() );
                criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustomerId() ) );
            }
            else if ( StringUtils.isValidObj( inCrmQrcDto.getBillCyclePojo() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getBillCyclePojo().getProcessingDate() ) )
                {
                    LOGGER.info( "Processing Date : " + inCrmQrcDto.getBillCyclePojo().getProcessingDate() );
                    criteria.add( Restrictions
                            .le( "processingDate", inCrmQrcDto.getBillCyclePojo().getProcessingDate() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getBillCyclePojo().getStatus() ) )
                {
                    LOGGER.info( "Status : " + inCrmQrcDto.getBillCyclePojo().getStatus() );
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getBillCyclePojo().getStatus() ) );
                }
            }
            if ( inCrmQrcDto.getMaxResultSize() > 0 )
            {
                criteria.setMaxResults( inCrmQrcDto.getMaxResultSize() );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmBillCyclePojo.class.getName() );
            crmBillCyclePojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmBillCyclePojos ) )
            {
                inCrmQrcDto.setCrmBillCyclePojosList( crmBillCyclePojos );
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmBillCyclePojos = new ArrayList<CrmBillCyclePojo>();
                inCrmQrcDto.setCrmBillCyclePojosList( crmBillCyclePojos );
                crmServiceCode = CRMServiceCode.CRM996;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting bill cycle history ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto updatePrepaidCustomerGracePeriod( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "DAO updatePrepaidCustomerGracePeriod called::" );
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                boolean valid = getQrcManagerDao().isValidTicket( inCrmQrcDto.getSrTicketNo(),
                                                                  inCrmQrcDto.getCustomerId() );
                if ( !valid )
                {
                    crmServiceCode = CRMServiceCode.CRM439;
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM439.getStatusCode() ) )
            {
                Date expiryDate = null;
                if ( Calendar.getInstance().getTime().compareTo( inCrmQrcDto.getExpiryDate() ) >= 0 )
                {
                    expiryDate = DateUtils.getNextExpiryDate( Calendar.getInstance().getTime(),
                                                              inCrmQrcDto.getNoOfdays() - 1, Calendar.DATE );
                }
                else
                {
                    expiryDate = DateUtils.getNextExpiryDate( inCrmQrcDto.getExpiryDate(), inCrmQrcDto.getNoOfdays(),
                                                              Calendar.DATE );
                }
                CrmBillingDto billingDto = new CrmBillingDto();
                billingDto.setCustomerId( inCrmQrcDto.getCustomerId() );
                billingDto.setExpiryDate( expiryDate );
                billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                getCrmBillingManager().allowGracePeriod( billingDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                {
                    CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                    activityLog.setOldValue( DateUtils.getFormattedDate( inCrmQrcDto.getExpiryDate(),
                                                                         IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                    activityLog.setNewValue( DateUtils.getFormattedDate( expiryDate,
                                                                         IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                    activityLog.setCustomerId( inCrmQrcDto.getCustomerId() );
                    activityLog.setAction( CRMCustomerActivityActions.VALIDITY_EXTENSION.getActionDesc() );
                    activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                    activityLog.setReason( inCrmQrcDto.getGracePeriodReason() );
                    activityLog.setAdditionalDetail( inCrmQrcDto.getNoOfdays() + "" );
                    activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                    activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
                    activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
                    long acticityId = CRMServiceUtils.createActivityLog( activityLog );
                    RemarksPojo remarksPojo = new RemarksPojo();
                    remarksPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                    remarksPojo.setReasonId( 0 );
                    remarksPojo.setMappingId( inCrmQrcDto.getCustomerId() );
                    remarksPojo.setActions( CRMCustomerActivityActions.VALIDITY_EXTENSION.getActionDesc() );
                    remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                    remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    remarksPojo.setActivityId( acticityId );
                    remarksPojo.setMappingType( CRMRCAReason.QRC.getStatusCode() );
                    session = HibernateUtil.getCurrentSession();
                    transaction = session.beginTransaction();
                    session.save( remarksPojo );
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                    LOGGER.info( "Status Code ::" + crmServiceCode );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Occured::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            LOGGER.info( "finally Service Code::" + inCrmQrcDto.getStatusCode() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto cancelBillCycleRequest( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "DAO cancelBillCycleRequest called::" );
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            CrmBillCyclePojo crmBillCyclePojo = (CrmBillCyclePojo) session.get( CrmBillCyclePojo.class,
                                                                                inCrmQrcDto.getCustomerRecordId() );
            CrmBillingDto billingDto = new CrmBillingDto();
            billingDto.setCustomerId( inCrmQrcDto.getCustomerId() );
            billingDto.setParamValue( crmBillCyclePojo.getBillDate() );
            billingDto.setStatus( ServiceParameter.CANCEL.getParameter() );
            getCrmBillingManager().changeBillCycle( billingDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
            {
                crmBillCyclePojo.setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                crmBillCyclePojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmBillCyclePojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                session.merge( crmBillCyclePojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                LOGGER.info( "Status Code ::" + crmServiceCode );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Occured::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto synchronizeBillCycleToBilling( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CustomerProfileDaoImpl, +synchronizeBillCycleToBilling" );
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CrmBillCyclePojo billCyclePojo = null;
        CrmBillingDto crmBillingDto = null;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo customerActivityPojo = null;
        CRMServiceCode crmServiceCode = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            if ( StringUtils.isValidObj( inCrmQrcDto.getBillCyclePojo() ) )
            {
                billCyclePojo = inCrmQrcDto.getBillCyclePojo();
                crmBillingDto = getCrmBillingManager().getCustomerUsageDetail( billCyclePojo.getCustomerId(), false ); //needs only bill cycle for customer.
                if ( StringUtils.isValidObj( crmBillingDto )
                        && StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                       billCyclePojo.getCustomerId() );
                    if ( StringUtils.isValidObj( customerDetailsPojo ) )
                    {
                        CrmCustAdditionalDetails additionalDetails = crmBillingDto.getCustAdditionalDetails();
                        if ( !StringUtils.equals( customerDetailsPojo.getBillDate(), billCyclePojo.getBillDate() )
                                && StringUtils.equals( additionalDetails.getBillCycle(), billCyclePojo.getBillDate() ) )
                        {
                            session = HibernateUtil.getCurrentSession();
                            transaction = session.beginTransaction();
                            customerActivityPojo = new CrmCustomerActivityPojo();
                            customerActivityPojo.setCustomerId( billCyclePojo.getCustomerId() );
                            customerActivityPojo.setAction( CRMCustomerActivityActions.BILL_CYCLE_MODIFY
                                    .getActionCode() );
                            customerActivityPojo.setTicketId( billCyclePojo.getTicketId() );
                            customerActivityPojo.setOldValue( customerDetailsPojo.getBillDate() );
                            customerActivityPojo.setNewValue( billCyclePojo.getBillDate() );
                            customerActivityPojo.setCreatedBy( billCyclePojo.getCreatedBy() );
                            //customerActivityPojo.setReason( CRMCustomerActivityActions.BILL_CYCLE_MODIFY
                            //                                    .getActionDesc() );
                            customerActivityPojo.setServiceIp( CRMUtils.getServerIp() );
                            customerActivityPojo.setClientIp( inCrmQrcDto.getClientIPAddress() );
                            customerActivityPojo.setServerIp( inCrmQrcDto.getServerIPAddress() );
                            billCyclePojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                            billCyclePojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            customerDetailsPojo.setBillDate( additionalDetails.getBillCycle() );
                            session.update( customerDetailsPojo );
                            session.update( billCyclePojo );
                            evicts.put( CrmCustomerDetailsPojo.class.getName(), customerDetailsPojo.getRecordId() );
                            evicts.put( CrmBillCyclePojo.class.getName(), billCyclePojo.getBillCycleId() );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                            long activityID = CRMServiceUtils.createActivityLog( customerActivityPojo );
                            if ( activityID > 0 )
                            {
                                LOGGER.info( "Bill cycle synchronized with billing for customer Id : "
                                        + billCyclePojo.getCustomerId() + " And activity ID : " + activityID );
                            }
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM999;
                            inCrmQrcDto.setBillingErrorCode( "Bill cycle not processed at billing : Expected"
                                    + billCyclePojo.getBillDate() + " Found : " + additionalDetails.getBillCycle() );
                        }
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.CRM309;
                        inCrmQrcDto.setBillingErrorCode( crmServiceCode.getStatusDesc() );
                    }
                }
                else
                {
                    inCrmQrcDto.setStatusCode( crmBillingDto.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( crmBillingDto.getStatusDesc() );
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while synchronizing bill cycle ::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while synchronizing bill cycle with billing for customer Id : "
                    + billCyclePojo.getCustomerId() );
            inCrmQrcDto.setBillingErrorCode( "Exception while synchronizing bill cycle with billing for customer Id : "
                    + billCyclePojo.getCustomerId() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isValidObj( crmServiceCode ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
            LOGGER.info( "finally Service Code::" + inCrmQrcDto.getStatusCode() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto updateMultipleGracePeriod( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmBillingDto crmBillingDto = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        Map<String, String> failedCustomerMapList = new HashMap<String, String>();
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            for ( ValidityExtensionPojo validityExtensionPojo : inCrmQrcDto.getValidityExtensionPojos() )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                      validityExtensionPojo.getCustomerId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo )
                        && ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                 CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        && ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                        || ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                 CRMStatusCode.BARRED.getStatusCode() ) ) )
                {
                    if ( StringUtils.isNotEmpty( validityExtensionPojo.getTicketId() ) )
                    {
                        boolean valid = getQrcManagerDao().isValidTicket( validityExtensionPojo.getTicketId(),
                                                                          validityExtensionPojo.getCustomerId() );
                        if ( !valid )
                        {
                            crmServiceCode = CRMServiceCode.CRM439;
                        }
                    }
                    if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM439.getStatusCode() ) )
                    {
                        crmBillingDto = getCrmBillingManager()
                                .getCustomerUsageDetail( validityExtensionPojo.getCustomerId(), false );
                        Date expiryDate = null;
                        if ( Calendar.getInstance().getTime()
                                .compareTo( crmBillingDto.getCustAdditionalDetails().getExpiryDate() ) >= 0 )
                        {
                            expiryDate = DateUtils.getNextExpiryDate( Calendar.getInstance().getTime(), Integer
                                    .parseInt( validityExtensionPojo.getExtensionDays() ) - 1, Calendar.DATE );
                        }
                        else
                        {
                            expiryDate = DateUtils.getNextExpiryDate( crmBillingDto.getCustAdditionalDetails()
                                                                              .getExpiryDate(), Integer
                                                                              .parseInt( validityExtensionPojo
                                                                                      .getExtensionDays() ),
                                                                      Calendar.DATE );
                        }
                        CrmBillingDto billingDto = new CrmBillingDto();
                        billingDto.setCustomerId( validityExtensionPojo.getCustomerId() );
                        billingDto.setExpiryDate( expiryDate );
                        billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                        getCrmBillingManager().allowGracePeriod( billingDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                            activityLog.setOldValue( DateUtils
                                    .getFormattedDate( crmBillingDto.getCustAdditionalDetails().getExpiryDate(),
                                                       IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                            activityLog.setNewValue( DateUtils
                                    .getFormattedDate( expiryDate, IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                            activityLog.setCustomerId( validityExtensionPojo.getCustomerId() );
                            activityLog.setAction( CRMCustomerActivityActions.VALIDITY_EXTENSION.getActionDesc() );
                            activityLog.setTicketId( validityExtensionPojo.getTicketId() );
                            activityLog.setReason( inCrmQrcDto.getGracePeriodReason() );
                            activityLog.setAdditionalDetail( validityExtensionPojo.getExtensionDays() );
                            activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
                            activityLog.setServiceIp( CRMUtils.getServerIp() );
                            activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
                            activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
                            long acticityId = CRMServiceUtils.createActivityLog( activityLog );
                            RemarksPojo remarksPojo = new RemarksPojo();
                            remarksPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                            remarksPojo.setReasonId( 0 );
                            remarksPojo.setMappingId( validityExtensionPojo.getCustomerId() );
                            remarksPojo.setActions( CRMCustomerActivityActions.VALIDITY_EXTENSION.getActionDesc() );
                            remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                            remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            remarksPojo.setActivityId( acticityId );
                            remarksPojo.setMappingType( CRMRCAReason.QRC.getStatusCode() );
                            session = HibernateUtil.getCurrentSession();
                            transaction = session.beginTransaction();
                            session.save( remarksPojo );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Status Code ::" + crmServiceCode );
                            String messsage = crmServiceCode.getStatusDesc();
                            messsage += billingDto.getBillingErrorCode();
                            failedCustomerMapList.put( validityExtensionPojo.getCustomerId(), messsage );
                            inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
                        }
                    }
                }
                else
                {
                    failedCustomerMapList.put( validityExtensionPojo.getCustomerId(),
                                               "Only applicable for prepaid Customer should be Active or Barred" );
                    inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Occured::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured:::", e );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            LOGGER.info( "finally Service Code::" + inCrmQrcDto.getStatusCode() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
        }
        return inCrmQrcDto;
    }
}
