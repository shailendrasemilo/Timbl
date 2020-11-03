package com.np.tele.crm.finance.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.xml.soap.SOAPException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmReasonsConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.exceptions.DuplicateRecieptException;
import com.np.tele.crm.exceptions.DuplicateTransactionException;
import com.np.tele.crm.pojos.CrmCmsFilePojo;
import com.np.tele.crm.pojos.CrmCmsPaymentPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmCustomerRefundsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPodDetailsPojo;
import com.np.tele.crm.pojos.CrmQrcWhitelistPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmUpfrontPaymentPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.CrmDaoHelper;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class FinanceManagerDaoImpl
    implements IFinanceManagerDao, Cloneable
{
    private static final Logger LOGGER            = Logger.getLogger( FinanceManagerDaoImpl.class );
    private IConfigManagerDao   configManagerDao  = null;
    private ICrmBillingManager  crmBillingManager = null;
    private IQrcManagerDao      qrcManagerDao;

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager inCrmBillingManager )
    {
        crmBillingManager = inCrmBillingManager;
    }

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao qrcManagerDao )
    {
        this.qrcManagerDao = qrcManagerDao;
    }

    /* public static void main( String[] args )
     {
         FinanceManagerDaoImpl daoImpl = new FinanceManagerDaoImpl();
         CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
         CrmPaymentDetailsPojo crmPaymentDetailsPojo=new CrmPaymentDetailsPojo();
         crmPaymentDetailsPojo.setPaymentId(646 );
         crmPaymentDetailsPojo.setPaymentStatus( "R" );
         System.out.println(daoImpl.postSinglePayment( crmFinanceDto, crmPaymentDetailsPojo ));
     }

     */
    @Override
    public boolean postSinglePayment( CrmFinanceDto inCrmFinanceDto,
                                      CrmPaymentDetailsPojo inPaymentDetailsPojo,
                                      Session inSession )
    {
        LOGGER.info( "Inside postSinglePayment" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        boolean updated = false;
        try
        {
            if ( !StringUtils.isValidObj( inSession ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
            }
            else
            {
                session = inSession;
            }
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
            if ( StringUtils.isValidObj( inPaymentDetailsPojo ) )
            {
                LOGGER.info( "Process: " + inPaymentDetailsPojo );
                if ( inPaymentDetailsPojo.getCustomerRecordId() > 0 )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                          inPaymentDetailsPojo.getCustomerRecordId() );
                }
                else if ( StringUtils.isNotBlank( inPaymentDetailsPojo.getCustomerId() ) )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                          inPaymentDetailsPojo.getCustomerId() );
                }
                else if ( StringUtils.isNotBlank( inPaymentDetailsPojo.getCrfId() ) )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                          inPaymentDetailsPojo.getCrfId() );
                }
                boolean postPayment = false;
                boolean postReversal = false;
                boolean toSave = true;
                boolean addCharge = false;
                CrmBillingDto addChargeBillingDto = null;
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo )
                        && StringUtils.numericValue( crmCustomerDetailsPojo.getCustomerId() ) > 0 )
                {
                    inPaymentDetailsPojo.setCustomerRecordId( crmCustomerDetailsPojo.getRecordId() );
                    inPaymentDetailsPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                    boolean toCommit = true;
                    boolean sendAlert = false;
                    QRCRolCategories autoTicket = null;
                    if ( StringUtils.equals( CRMDisplayListConstants.PREINSTALLATION.getCode(),
                                             inPaymentDetailsPojo.getInstallationStatus() ) )
                    {
                        boolean toUnFreeze = false;
                        boolean toFreeze = false;
                        boolean toUnBar = false;
                        boolean toBar = false;
                        if ( !StringUtils.isValidObj( inSession ) )
                        {
                            transaction = session.beginTransaction();
                        }
                        if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE.getCode(),
                                                 inPaymentDetailsPojo.getPaymentMode() ) )
                        {
                            if ( inPaymentDetailsPojo.getPaymentId() > 0 )
                            {
                                CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils
                                        .getDBValues( CrmPaymentDetailsPojo.class, inPaymentDetailsPojo.getPaymentId() );
                                if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
                                {
                                    inPaymentDetailsPojo.setCreatedTime( dbCrmPaymentDetailsPojo.getCreatedTime() );
                                    inPaymentDetailsPojo.setCreatedBy( dbCrmPaymentDetailsPojo.getCreatedBy() );
                                    inPaymentDetailsPojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                                    inPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    if ( StringUtils
                                            .equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode(),
                                                     dbCrmPaymentDetailsPojo.getRealzationStatus() )
                                            && StringUtils
                                                    .equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                                             inPaymentDetailsPojo.getRealzationStatus() ) )
                                    {
                                        addCharge = false;
                                        sendAlert = true;
                                        autoTicket = QRCRolCategories.CHEQUE_BOUNCE_ADVANCE_PAYMENT;
                                        if ( StringUtils.equals( CRMStatusCode.PAYMENT_POSTED.getStatusCode(),
                                                                 dbCrmPaymentDetailsPojo.getStatus() ) )
                                        {
                                            postReversal = true;
                                        }
                                        if ( !CrmReasonsConstants.getReasons( "NonFreeze" )
                                                .contains( inPaymentDetailsPojo.getBouncingReason() ) )
                                        {
                                            if ( !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                      CRMOperationStages.CLOSE.getCode() )
                                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                            CRMOperationStages.ON_BOARD.getCode() )
                                                    && !StringUtils.isValidObj( crmCustomerDetailsPojo
                                                            .getActivationDate() )
                                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                            CRMOperationStages.FREEZE.getCode() ) )
                                            {
                                                toFreeze = true;
                                            }
                                            else if ( StringUtils.isValidObj( crmCustomerDetailsPojo
                                                    .getActivationDate() )
                                                    && StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                           CRMStatusCode.ACTIVE.getStatusCode() ) )
                                            {
                                                toBar = true;
                                            }
                                        }
                                    }
                                    else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                            .getCode(), dbCrmPaymentDetailsPojo.getRealzationStatus() )
                                            && StringUtils.equals( CRMDisplayListConstants.CHEQUE_REALIZED.getCode(),
                                                                   inPaymentDetailsPojo.getRealzationStatus() ) )
                                    {
                                        if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                                 dbCrmPaymentDetailsPojo.getStatus() ) )
                                        {
                                            postPayment = true;
                                        }
                                        if ( StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                 CRMOperationStages.FREEZE.getCode() ) )
                                        {
                                            toUnFreeze = true;
                                        }
                                        else if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                      CRMStatusCode.BARRED.getStatusCode() ) )
                                        {
                                            toUnBar = true;
                                        }
                                    }
                                    toSave = false;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM999;
                                }
                            }
                            else
                            {
                                postPayment = true;
                                inPaymentDetailsPojo.setServiceType( crmCustomerDetailsPojo.getServiceType() );
                                inPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                                         inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    postPayment = false;
                                    addCharge = false;
                                    sendAlert = true;
                                    autoTicket = QRCRolCategories.CHEQUE_BOUNCE_ADVANCE_PAYMENT;
                                    if ( !CrmReasonsConstants.getReasons( "NonFreeze" )
                                            .contains( inPaymentDetailsPojo.getBouncingReason() ) )
                                    {
                                        if ( !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                  CRMOperationStages.CLOSE.getCode() )
                                                && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                        CRMOperationStages.ON_BOARD.getCode() )
                                                && !StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() )
                                                && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                        CRMOperationStages.FREEZE.getCode() ) )
                                        {
                                            toFreeze = true;
                                        }
                                        else if ( StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() )
                                                && StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                       CRMStatusCode.ACTIVE.getStatusCode() ) )
                                        {
                                            toBar = true;
                                        }
                                    }
                                }
                                else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                        .getCode(), inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    Map<String, Object> criteriaMap = new HashMap<String, Object>();
                                    criteriaMap
                                            .put( "installationStatus", inPaymentDetailsPojo.getInstallationStatus() );
                                    criteriaMap.put( "customerRecordId", crmCustomerDetailsPojo.getRecordId() );
                                    List<CrmPaymentDetailsPojo> payments = CRMServiceUtils
                                            .getDBValueList( CrmPaymentDetailsPojo.class, criteriaMap, "paymentId",
                                                             false );
                                    if ( CommonValidator.isValidCollection( payments ) )
                                    {
                                        for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
                                        {
                                            if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED
                                                    .getCode(), crmPaymentDetailsPojo.getRealzationStatus() ) )
                                            {
                                                postPayment = false;
                                            }
                                            break;
                                        }
                                    }
                                }
                                else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_REALIZED.getCode(),
                                                              inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    if ( StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                             CRMOperationStages.FREEZE.getCode() ) )
                                    {
                                        toUnFreeze = true;
                                    }
                                    else if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                  CRMStatusCode.BARRED.getStatusCode() ) )
                                    {
                                        toUnBar = true;
                                    }
                                }
                                toSave = true;
                            }
                        }
                        else
                        {
                            if ( inPaymentDetailsPojo.getPaymentId() > 0 )
                            {
                                CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils
                                        .getDBValues( CrmPaymentDetailsPojo.class, inPaymentDetailsPojo.getPaymentId() );
                                if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
                                {
                                    inPaymentDetailsPojo.setCreatedTime( dbCrmPaymentDetailsPojo.getCreatedTime() );
                                    inPaymentDetailsPojo.setCreatedBy( dbCrmPaymentDetailsPojo.getCreatedBy() );
                                    inPaymentDetailsPojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                                    inPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                             dbCrmPaymentDetailsPojo.getStatus() ) )
                                    {
                                        postPayment = true;
                                    }
                                    toSave = false;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM999;
                                }
                            }
                            else
                            {
                                if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                                         inPaymentDetailsPojo.getPaymentMode() ) )
                                {
                                    validateDuplicateReciept( inPaymentDetailsPojo, session );
                                }
                                if ( StringUtils.equals( CRMDisplayListConstants.ONLINE_PAYMENT.getCode(),
                                                         inPaymentDetailsPojo.getPaymentMode() ) )
                                {
                                    validateDuplicateTransactionID( inPaymentDetailsPojo, session );
                                }
                                postPayment = true;
                                if ( StringUtils.isBlank( inPaymentDetailsPojo.getServiceType() ) )
                                {
                                    inPaymentDetailsPojo.setServiceType( crmCustomerDetailsPojo.getServiceType() );
                                }
                                inPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                toSave = true;
                            }
                            if ( StringUtils.equals( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode(),
                                                     inPaymentDetailsPojo.getPaymentStatus() )
                                    && StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                           CRMOperationStages.FREEZE.getCode() ) )
                            {
                                toUnFreeze = true;
                            }
                        }
                        if ( toFreeze )
                        {
                            toCommit = false;
                            crmCustomerDetailsPojo.setCrfPreviousStage( crmCustomerDetailsPojo.getCrfStage() );
                            crmCustomerDetailsPojo.setCrfStage( CRMOperationStages.FREEZE.getCode() );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.update( crmCustomerDetailsPojo );
                            toCommit = changeInboxBin( inCrmFinanceDto.getUserId(), crmCustomerDetailsPojo, session );
                        }
                        else if ( toUnFreeze )
                        {
                            toCommit = false;
                            String prevoiusStage = crmCustomerDetailsPojo.getCrfPreviousStage();
                            crmCustomerDetailsPojo.setCrfPreviousStage( crmCustomerDetailsPojo.getCrfStage() );
                            crmCustomerDetailsPojo.setCrfStage( prevoiusStage );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.update( crmCustomerDetailsPojo );
                            toCommit = changeInboxBin( inCrmFinanceDto.getUserId(), crmCustomerDetailsPojo, session );
                        }
                        else if ( toBar )
                        {
                            // ToDo check for Barring Exception
                            CrmQrcWhitelistPojo barringWhitelist = null;
                            Map<String, Object> criteriaMap = new HashMap<String, Object>();
                            criteriaMap.put( "customerId", inPaymentDetailsPojo.getCustomerId() );
                            criteriaMap.put( "whitelistType", CRMStatusCode.BARRED.getStatusCode() );
                            List<CrmQrcWhitelistPojo> whiteList = CRMServiceUtils
                                    .getDBValueList( CrmQrcWhitelistPojo.class, criteriaMap, "endDate", false );
                            if ( CommonValidator.isValidCollection( whiteList ) )
                            {
                                barringWhitelist = whiteList.get( 0 );
                                if ( Calendar.getInstance().getTime().after( barringWhitelist.getEndDate() ) )
                                {
                                    barringWhitelist = null;
                                }
                            }
                            if ( !StringUtils.isValidObj( barringWhitelist ) )
                            {
                                toCommit = false;
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( inPaymentDetailsPojo.getCustomerId() );
                                billingDto.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                billingDto.setRemarks( inPaymentDetailsPojo.getBouncingReason() );
                                getCrmBillingManager().changeStatus( billingDto );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         billingDto.getStatusCode() ) )
                                {
                                    crmCustomerDetailsPojo.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( crmCustomerDetailsPojo );
                                    toCommit = true;
                                    CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                                    activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                                    activityLog.setAction( CRMCustomerActivityActions.BARRING.getActionCode() );
                                    activityLog.setReason( CRMCustomerActivityActions.BARRING.getActionDesc() );
                                    activityLog.setOldValue( CRMStatusCode.UNBARRED.getStatusDesc() );
                                    activityLog.setNewValue( CRMStatusCode.getStatus( crmCustomerDetailsPojo
                                            .getStatus() ) );
                                    activityLog.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    activityLog.setAdditionalDetail( CRMRCAReason.FINANCE.getStatusCode() );
                                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                                    activityLog.setClientIp( inCrmFinanceDto.getClientIPAddress() );
                                    activityLog.setServerIp( inCrmFinanceDto.getServerIPAddress() );
                                    CRMServiceUtils.createActivityLog( activityLog );
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else
                            {
                                if ( !CommonValidator.isValidCollection( inCrmFinanceDto.getStatusCodes() ) )
                                {
                                    inCrmFinanceDto.setStatusCodes( new TreeSet<String>() );
                                }
                                inCrmFinanceDto.getStatusCodes().add( CRMServiceCode.CRM403.getStatusCode() );
                                LOGGER.info( "Unable to bar Customer:" + barringWhitelist.getCustomerId()
                                        + " due to Barring Exception List: " + barringWhitelist );
                            }
                        }
                        else if ( toUnBar )
                        {
                            CrmQrcWhitelistPojo unbarringWhitelist = null;
                            Map<String, Object> criteriaMap = new HashMap<String, Object>();
                            criteriaMap.put( "customerId", inPaymentDetailsPojo.getCustomerId() );
                            criteriaMap.put( "whitelistType", CRMStatusCode.UNBARRED.getStatusCode() );
                            List<CrmQrcWhitelistPojo> whiteList = CRMServiceUtils
                                    .getDBValueList( CrmQrcWhitelistPojo.class, criteriaMap, "endDate", false );
                            if ( CommonValidator.isValidCollection( whiteList ) )
                            {
                                unbarringWhitelist = whiteList.get( 0 );
                                if ( Calendar.getInstance().getTime().after( unbarringWhitelist.getEndDate() ) )
                                {
                                    unbarringWhitelist = null;
                                }
                            }
                            if ( !StringUtils.isValidObj( unbarringWhitelist ) )
                            {
                                toCommit = false;
                                // ToDo check for Unbarring Exception
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( inPaymentDetailsPojo.getCustomerId() );
                                billingDto.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                billingDto.setRemarks( "Payment Reflecting" );
                                getCrmBillingManager().changeStatus( billingDto );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         billingDto.getStatusCode() ) )
                                {
                                    crmCustomerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( crmCustomerDetailsPojo );
                                    toCommit = true;
                                    CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                                    activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                                    activityLog.setAction( CRMCustomerActivityActions.BARRING.getActionCode() );
                                    activityLog.setReason( CRMCustomerActivityActions.BARRING.getActionDesc() );
                                    activityLog.setOldValue( CRMStatusCode.BARRED.getStatusDesc() );
                                    activityLog.setNewValue( CRMStatusCode.getStatus( crmCustomerDetailsPojo
                                            .getStatus() ) );
                                    activityLog.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    activityLog.setAdditionalDetail( CRMRCAReason.FINANCE.getStatusCode() );
                                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                                    activityLog.setClientIp( inCrmFinanceDto.getClientIPAddress() );
                                    activityLog.setServerIp( inCrmFinanceDto.getServerIPAddress() );
                                    CRMServiceUtils.createActivityLog( activityLog );
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else
                            {
                                if ( !CommonValidator.isValidCollection( inCrmFinanceDto.getStatusCodes() ) )
                                {
                                    inCrmFinanceDto.setStatusCodes( new TreeSet<String>() );
                                }
                                inCrmFinanceDto.getStatusCodes().add( CRMServiceCode.CRM403.getStatusCode() );
                                LOGGER.info( "Unable to unbar Customer:" + unbarringWhitelist.getCustomerId()
                                        + " due to Unbarring Exception List: " + unbarringWhitelist );
                            }
                        }
                        if ( toCommit && postPayment )
                        {
                            toCommit = false;
                            inPaymentDetailsPojo.setPostingDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
                            getCrmBillingManager().postPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                inPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                inPaymentDetailsPojo.setPostingDate( null );
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                        if ( toCommit && postReversal )
                        {
                            toCommit = false;
                            inPaymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
                            getCrmBillingManager().cancelPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                inPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                    }
                    else if ( StringUtils.equals( CRMDisplayListConstants.POSTINSTALLATION.getCode(),
                                                  inPaymentDetailsPojo.getInstallationStatus() ) )
                    {
                        boolean toUnBar = false;
                        boolean toBar = false;
                        if ( !StringUtils.isValidObj( inSession ) )
                        {
                            transaction = session.beginTransaction();
                        }
                        if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE.getCode(),
                                                 inPaymentDetailsPojo.getPaymentMode() ) )
                        {
                            if ( inPaymentDetailsPojo.getPaymentId() > 0 )
                            {
                                CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils
                                        .getDBValues( CrmPaymentDetailsPojo.class, inPaymentDetailsPojo.getPaymentId() );
                                if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
                                {
                                    inPaymentDetailsPojo.setCreatedTime( dbCrmPaymentDetailsPojo.getCreatedTime() );
                                    inPaymentDetailsPojo.setCreatedBy( dbCrmPaymentDetailsPojo.getCreatedBy() );
                                    inPaymentDetailsPojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                                    inPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    if ( StringUtils
                                            .equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode(),
                                                     dbCrmPaymentDetailsPojo.getRealzationStatus() )
                                            && StringUtils
                                                    .equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                                             inPaymentDetailsPojo.getRealzationStatus() ) )
                                    {
                                        addCharge = true;
                                        sendAlert = true;
                                        autoTicket = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
                                        if ( StringUtils.equals( CRMStatusCode.PAYMENT_POSTED.getStatusCode(),
                                                                 dbCrmPaymentDetailsPojo.getStatus() ) )
                                        {
                                            postReversal = true;
                                        }
                                        if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                                        {
                                            toBar = true;
                                        }
                                    }
                                    else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                            .getCode(), dbCrmPaymentDetailsPojo.getRealzationStatus() )
                                            && StringUtils.equals( CRMDisplayListConstants.CHEQUE_REALIZED.getCode(),
                                                                   inPaymentDetailsPojo.getRealzationStatus() ) )
                                    {
                                        if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                                 dbCrmPaymentDetailsPojo.getStatus() ) )
                                        {
                                            postPayment = true;
                                        }
                                        if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                 CRMStatusCode.BARRED.getStatusCode() ) )
                                        {
                                            toUnBar = true;
                                        }
                                    }
                                    toSave = false;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM999;
                                }
                            }
                            else
                            {
                                postPayment = true;
                                inPaymentDetailsPojo.setServiceType( crmCustomerDetailsPojo.getServiceType() );
                                inPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                                         inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    addCharge = true;
                                    postPayment = false;
                                    sendAlert = true;
                                    autoTicket = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
                                    if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                             CRMStatusCode.ACTIVE.getStatusCode() ) )
                                    {
                                        toBar = true;
                                    }
                                }
                                else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                        .getCode(), inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    Map<String, Object> criteriaMap = new HashMap<String, Object>();
                                    criteriaMap
                                            .put( "installationStatus", inPaymentDetailsPojo.getInstallationStatus() );
                                    criteriaMap.put( "customerRecordId", crmCustomerDetailsPojo.getRecordId() );
                                    List<CrmPaymentDetailsPojo> payments = CRMServiceUtils
                                            .getDBValueList( CrmPaymentDetailsPojo.class, criteriaMap, "paymentId",
                                                             false );
                                    if ( CommonValidator.isValidCollection( payments ) )
                                    {
                                        for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
                                        {
                                            if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED
                                                    .getCode(), crmPaymentDetailsPojo.getRealzationStatus() ) )
                                            {
                                                postPayment = false;
                                            }
                                            break;
                                        }
                                    }
                                }
                                else if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_REALIZED.getCode(),
                                                              inPaymentDetailsPojo.getRealzationStatus() ) )
                                {
                                    if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                             CRMStatusCode.BARRED.getStatusCode() ) )
                                    {
                                        toUnBar = true;
                                    }
                                }
                                toSave = true;
                            }
                        }
                        else
                        {
                            if ( inPaymentDetailsPojo.getPaymentId() > 0 )
                            {
                                CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils
                                        .getDBValues( CrmPaymentDetailsPojo.class, inPaymentDetailsPojo.getPaymentId() );
                                if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
                                {
                                    inPaymentDetailsPojo.setCreatedTime( dbCrmPaymentDetailsPojo.getCreatedTime() );
                                    inPaymentDetailsPojo.setCreatedBy( dbCrmPaymentDetailsPojo.getCreatedBy() );
                                    inPaymentDetailsPojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                                    inPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                             dbCrmPaymentDetailsPojo.getStatus() ) )
                                    {
                                        postPayment = true;
                                    }
                                    toSave = false;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM999;
                                }
                            }
                            else
                            {
                                if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                                         inPaymentDetailsPojo.getPaymentMode() ) )
                                {
                                    validateDuplicateReciept( inPaymentDetailsPojo, session );
                                }
                                if ( StringUtils.equals( CRMDisplayListConstants.ONLINE_PAYMENT.getCode(),
                                                         inPaymentDetailsPojo.getPaymentMode() ) )
                                {
                                    validateDuplicateTransactionID( inPaymentDetailsPojo, session );
                                }
                                postPayment = true;
                                if ( StringUtils.isBlank( inPaymentDetailsPojo.getServiceType() ) )
                                {
                                    inPaymentDetailsPojo.setServiceType( crmCustomerDetailsPojo.getServiceType() );
                                }
                                inPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                toSave = true;
                            }
                            if ( StringUtils.equals( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode(),
                                                     inPaymentDetailsPojo.getPaymentStatus() ) )
                            {
                                if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                         CRMStatusCode.BARRED.getStatusCode() ) )
                                {
                                    toUnBar = true;
                                }
                            }
                        }
                        if ( toCommit && postPayment )
                        {
                            toCommit = false;
                            inPaymentDetailsPojo.setPostingDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
                            getCrmBillingManager().postPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                inPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                inPaymentDetailsPojo.setPostingDate( null );
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                        if ( toCommit && postReversal )
                        {
                            toCommit = false;
                            inPaymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
                            getCrmBillingManager().cancelPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                inPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                        if ( toCommit && toBar )
                        {
                            // ToDo check for Barring Exception
                            CrmQrcWhitelistPojo barringWhitelist = null;
                            Map<String, Object> criteriaMap = new HashMap<String, Object>();
                            criteriaMap.put( "customerId", inPaymentDetailsPojo.getCustomerId() );
                            criteriaMap.put( "whitelistType", CRMStatusCode.BARRED.getStatusCode() );
                            List<CrmQrcWhitelistPojo> whiteList = CRMServiceUtils
                                    .getDBValueList( CrmQrcWhitelistPojo.class, criteriaMap, "endDate", false );
                            if ( CommonValidator.isValidCollection( whiteList ) )
                            {
                                barringWhitelist = whiteList.get( 0 );
                                if ( Calendar.getInstance().getTime().after( barringWhitelist.getEndDate() ) )
                                {
                                    barringWhitelist = null;
                                }
                            }
                            if ( !StringUtils.isValidObj( barringWhitelist ) )
                            {
                                // toCommit = false;
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( inPaymentDetailsPojo.getCustomerId() );
                                billingDto.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                billingDto.setRemarks( inPaymentDetailsPojo.getBouncingReason() );
                                getCrmBillingManager().changeStatus( billingDto );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         billingDto.getStatusCode() ) )
                                {
                                    crmCustomerDetailsPojo.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( crmCustomerDetailsPojo );
                                    toCommit = true;
                                    CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                                    activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                                    activityLog.setAction( CRMCustomerActivityActions.BARRING.getActionCode() );
                                    activityLog.setReason( CRMCustomerActivityActions.BARRING.getActionDesc() );
                                    activityLog.setOldValue( CRMStatusCode.UNBARRED.getStatusDesc() );
                                    activityLog.setNewValue( CRMStatusCode.getStatus( crmCustomerDetailsPojo
                                            .getStatus() ) );
                                    activityLog.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    activityLog.setAdditionalDetail( CRMRCAReason.FINANCE.getStatusCode() );
                                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                                    activityLog.setClientIp( inCrmFinanceDto.getClientIPAddress() );
                                    activityLog.setServerIp( inCrmFinanceDto.getServerIPAddress() );
                                    CRMServiceUtils.createActivityLog( activityLog );
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else
                            {
                                if ( !CommonValidator.isValidCollection( inCrmFinanceDto.getStatusCodes() ) )
                                {
                                    inCrmFinanceDto.setStatusCodes( new TreeSet<String>() );
                                }
                                inCrmFinanceDto.getStatusCodes().add( CRMServiceCode.CRM403.getStatusCode() );
                                LOGGER.info( "Unable to bar Customer:" + barringWhitelist.getCustomerId()
                                        + " due to Barring Exception List: " + barringWhitelist );
                            }
                        }
                        else if ( toCommit && toUnBar )
                        {
                            CrmQrcWhitelistPojo unbarringWhitelist = null;
                            Map<String, Object> criteriaMap = new HashMap<String, Object>();
                            criteriaMap.put( "customerId", inPaymentDetailsPojo.getCustomerId() );
                            criteriaMap.put( "whitelistType", CRMStatusCode.UNBARRED.getStatusCode() );
                            List<CrmQrcWhitelistPojo> whiteList = CRMServiceUtils
                                    .getDBValueList( CrmQrcWhitelistPojo.class, criteriaMap, "endDate", false );
                            if ( CommonValidator.isValidCollection( whiteList ) )
                            {
                                unbarringWhitelist = whiteList.get( 0 );
                                if ( Calendar.getInstance().getTime().after( unbarringWhitelist.getEndDate() ) )
                                {
                                    unbarringWhitelist = null;
                                }
                            }
                            if ( !StringUtils.isValidObj( unbarringWhitelist ) )
                            {
                                //toCommit = false;
                                // ToDo check for Unbarring Exception
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( inPaymentDetailsPojo.getCustomerId() );
                                billingDto.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                billingDto.setRemarks( "Payment Reflecting" );
                                getCrmBillingManager().changeStatus( billingDto );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         billingDto.getStatusCode() ) )
                                {
                                    crmCustomerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( crmCustomerDetailsPojo );
                                    toCommit = true;
                                    CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                                    activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                                    activityLog.setAction( CRMCustomerActivityActions.UNBARRING.getActionCode() );
                                    activityLog.setReason( CRMCustomerActivityActions.UNBARRING.getActionDesc() );
                                    activityLog.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    activityLog.setOldValue( CRMStatusCode.BARRED.getStatusDesc() );
                                    activityLog.setNewValue( CRMStatusCode.getStatus( crmCustomerDetailsPojo
                                            .getStatus() ) );
                                    activityLog.setAdditionalDetail( CRMRCAReason.FINANCE.getStatusCode() );
                                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                                    activityLog.setClientIp( inCrmFinanceDto.getClientIPAddress() );
                                    activityLog.setServerIp( inCrmFinanceDto.getServerIPAddress() );
                                    CRMServiceUtils.createActivityLog( activityLog );
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else
                            {
                                if ( !CommonValidator.isValidCollection( inCrmFinanceDto.getStatusCodes() ) )
                                {
                                    inCrmFinanceDto.setStatusCodes( new TreeSet<String>() );
                                }
                                inCrmFinanceDto.getStatusCodes().add( CRMServiceCode.CRM403.getStatusCode() );
                                LOGGER.info( "Unable to unbar Customer:" + unbarringWhitelist.getCustomerId()
                                        + " due to Unbarring Exception List: " + unbarringWhitelist );
                            }
                        }
                    }
                    if ( toCommit && addCharge )
                    {
                        toCommit = false;
                        addChargeBillingDto = new CrmBillingDto();
                        addChargeBillingDto.setCustomerId( inPaymentDetailsPojo.getCustomerId() );
                        if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                 CRMDisplayListConstants.POST_PAID.getCode() ) )
                        {
                            addChargeBillingDto.setChargeName( "Cheque Bounce Wavier" );
                        }
                        else if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                      CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        {
                            addChargeBillingDto.setChargeName( "Cheque Bounce Wavier_PRE" );
                        }
                        getCrmBillingManager().addCharge( addChargeBillingDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                 addChargeBillingDto.getStatusCode() ) )
                        {
                            toCommit = true;
                            LOGGER.info( "Amount Charged:" + addChargeBillingDto.getChargeAmount() );
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( addChargeBillingDto.getStatusCode() );
                            inCrmFinanceDto.setBillingErrorCode( addChargeBillingDto.getBillingErrorCode() );
                        }
                    }
                    if ( toCommit )
                    {
                        if ( toSave )
                        {
                            Long paymentId = (Long) session.save( inPaymentDetailsPojo );
                            inPaymentDetailsPojo.setPaymentId( paymentId );
                            inCrmFinanceDto.setPaymentDetailsPojo( inPaymentDetailsPojo );
                        }
                        else
                        {
                            inPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.update( inPaymentDetailsPojo );
                        }
                        if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                                 inPaymentDetailsPojo.getPaymentMode() ) )
                        {
                            CrmDaoHelper.setRecieptStatus( session, inPaymentDetailsPojo, true );
                        }
                        if ( !StringUtils.isValidObj( inSession ) )
                        {
                            transaction.commit();
                        }
                        // For Customer Activity
                        if ( addCharge )
                        {
                            CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                            CRMServiceUtils.fillActivityDetails( null, addChargeBillingDto, activityLog,
                                                                 CRMCustomerActivityActions.CHEQUE_BOUNCE_CHARGE );
                            activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            activityLog.setAction( CRMCustomerActivityActions.CHEQUE_BOUNCE_CHARGE.getActionCode() );
                            activityLog.setReason( CRMCustomerActivityActions.CHEQUE_BOUNCE_CHARGE.getActionDesc() );
                            activityLog.setCreatedBy( inCrmFinanceDto.getUserId() );
                            activityLog.setServiceIp( CRMUtils.getServerIp() );
                            activityLog.setClientIp( inCrmFinanceDto.getClientIPAddress() );
                            activityLog.setServerIp( inCrmFinanceDto.getServerIPAddress() );
                            CRMServiceUtils.createActivityLog( activityLog );
                        }
                        if ( crmServiceCode == CRMServiceCode.CRM508 )
                        {
                            crmServiceCode = CRMServiceCode.CRM706;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                        updated = true;
                        if ( sendAlert )
                        {
                            CRMServiceUtils.sendAlerts( CRMEvents.CHEQUE_BOUNCE, CRMRequestType.PAYMENT_ID,
                                                        inPaymentDetailsPojo.getPaymentId() + "", null );
                            CRMServiceUtils.generateAutoTicket( inPaymentDetailsPojo.getCustomerId(), autoTicket,
                                                                inPaymentDetailsPojo.getBouncingReason(),
                                                                inCrmFinanceDto.getUserId(),
                                                                inPaymentDetailsPojo.getBouncingReason() );
                        }
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM701;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while postSinglePayment", ex );
            CRMServiceUtils.rollback( transaction );
            if ( !StringUtils.isValidObj( inSession ) )
            {
                CRMServiceUtils.closeSessionOnException( session );
            }
        }
        catch ( Exception ex )
        {
            if ( ex instanceof DuplicateRecieptException || ex instanceof DuplicateTransactionException )
            {
                crmServiceCode = CRMServiceCode.valueOf( ex.getMessage() );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM999;
            }
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error while postSinglePayment: ", ex );
        }
        finally
        {
            if ( !StringUtils.isValidObj( inSession ) )
            {
                CRMServiceUtils.closeSession( session );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                             inPaymentDetailsPojo.getPaymentMode() ) )
                    {
                        HibernateUtil.evictAll( CrmRcaReasonPojo.class.getName(), null, null );
                    }
                    if ( updated )
                    {
                        HibernateUtil.evictAll( CrmPaymentDetailsPojo.class.getName(), null, null );
                    }
                }
            }
        }
        inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return updated;
    }

    private void validateDuplicateReciept( CrmPaymentDetailsPojo inPaymentDetailsPojo, Session session )
        throws DuplicateRecieptException
    {
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "receiptNo", inPaymentDetailsPojo.getReceiptNo() );
        List<String> criteriaList = new ArrayList<String>();
        criteriaList.add( CRMStatusCode.ACTIVE.getStatusCode() );
        criteriaList.add( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
        criteriaMap.put( "status", criteriaList );
        CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils.getDBValue( CrmPaymentDetailsPojo.class,
                                                                                    criteriaMap, null, false, session );
        if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
        {
            throw new DuplicateRecieptException( CRMServiceCode.CRM611.getStatusCode(),
                                                 inPaymentDetailsPojo.getReceiptNo() );
        }
    }

    private void validateDuplicateTransactionID( CrmPaymentDetailsPojo inPaymentDetailsPojo, Session session )
        throws DuplicateTransactionException
    {
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "transactionId", inPaymentDetailsPojo.getTransactionId() );
        criteriaMap.put( "customerRecordId", inPaymentDetailsPojo.getCustomerRecordId() );
        criteriaMap.put( "paymentMode", inPaymentDetailsPojo.getPaymentMode() );
        List<String> criteriaList = new ArrayList<String>();
        criteriaList.add( CRMStatusCode.ACTIVE.getStatusCode() );
        criteriaList.add( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
        criteriaMap.put( "status", criteriaList );
        CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils.getDBValue( CrmPaymentDetailsPojo.class,
                                                                                    criteriaMap, null, false, session );
        if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
        {
            throw new DuplicateTransactionException( CRMServiceCode.CRM618.getStatusCode(),
                                                     inPaymentDetailsPojo.getTransactionId() );
        }
    }

    private boolean changeInboxBin( String inUser, CrmCustomerDetailsPojo crmCustomerDetailsPojo, Session inSession )
        throws SOAPException
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setFromStage( crmCustomerDetailsPojo.getCrfPreviousStage() );
        configDto.setTostage( crmCustomerDetailsPojo.getCrfStage() );
        configDto.setFromUserId( inUser );
        configDto.setRequestType( CRMRequestType.CAF.getRequestCode() );
        configDto.setMappingId( crmCustomerDetailsPojo.getRecordId() + "" );
        configDto.setUserId( inUser );
        configDto.setUserIdChange( IAppConstants.NO );
        return getConfigManagerDao().changeInboxBin( configDto, inSession );
    }

    public static void main( String[] args )
    {
        FinanceManagerDaoImpl financeManagerDaoImpl = new FinanceManagerDaoImpl();
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setFromDate( "02-aug-2014 00:00:00" );
        crmFinanceDto.setToDate( "02-sep-2014 00:00:00" );
        crmFinanceDto = financeManagerDaoImpl.getPaymentHistory( crmFinanceDto );
        System.out.println( crmFinanceDto );
    }

    @Override
    public CrmFinanceDto getPaymentHistory( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        List<CrmPaymentDetailsPojo> payments = null;
        LOGGER.info( "Inside getPaymentHistory" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            String fromDateStr = inCrmFinanceDto.getFromDate();
            String toDateStr = inCrmFinanceDto.getToDate();
            criteria = session.createCriteria( CrmPaymentDetailsPojo.class );
            boolean toSearch = true;
            if ( StringUtils.isNotBlank( fromDateStr ) && StringUtils.isNotBlank( toDateStr ) )
            {
                fromDateStr += IAppConstants.START_TIME;
                toDateStr += IAppConstants.END_TIME;
                SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                Date fromDate = formatter.parse( fromDateStr );
                Date toDate = formatter.parse( toDateStr );
                LOGGER.info( "After Formatting: From Date: " + fromDate + " and To Date: " + toDate );
                criteria.add( Restrictions.between( "paymentDate", fromDate, toDate ) );
                criteria.addOrder( Order.desc( "paymentDate" ) );
            }
            CrmCustomerDetailsPojo customerDetailsPojo = inCrmFinanceDto.getCustomerDetailsPojo();
            if ( StringUtils.isValidObj( customerDetailsPojo ) )
            {
                if ( StringUtils.isNotBlank( customerDetailsPojo.getCrfId() ) )
                {
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                       customerDetailsPojo.getCrfId() );
                }
                else if ( StringUtils.isNotBlank( customerDetailsPojo.getCustomerId() ) )
                {
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                       customerDetailsPojo.getCustomerId() );
                }
                if ( StringUtils.isValidObj( customerDetailsPojo ) && customerDetailsPojo.getRecordId() > 0 )
                {
                    criteria.add( Restrictions.eq( "customerRecordId", customerDetailsPojo.getRecordId() ) );
                }
                else
                {
                    toSearch = false;
                }
            }
            if ( toSearch )
            {
                CrmPaymentDetailsPojo paymentDetailsPojo = inCrmFinanceDto.getPaymentDetailsPojo();
                if ( StringUtils.isValidObj( paymentDetailsPojo ) )
                {
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getInstallationStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "installationStatus", paymentDetailsPojo.getInstallationStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getEntityType() ) )
                    {
                        criteria.add( Restrictions.eq( "entityType", paymentDetailsPojo.getEntityType() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getPaymentMode() ) )
                    {
                        criteria.add( Restrictions.eq( "paymentMode", paymentDetailsPojo.getPaymentMode() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getPaymentChannel() ) )
                    {
                        if ( StringUtils.equals( paymentDetailsPojo.getPaymentChannel(), "APP" ) )
                        {
                            criteria.add( Restrictions.ne( "paymentChannel",
                                                           CRMDisplayListConstants.SECURITY_DEPOSIT.getCode() ) );
                        }
                        else
                            criteria.add( Restrictions.eq( "paymentChannel", paymentDetailsPojo.getPaymentChannel() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getTransactionId() ) )
                    {
                        criteria.add( Restrictions.eq( "transactionId", paymentDetailsPojo.getTransactionId() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getPaymentStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "paymentStatus", paymentDetailsPojo.getPaymentStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getRealzationStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "realzationStatus", paymentDetailsPojo.getRealzationStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getCaseStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "caseStatus", paymentDetailsPojo.getCaseStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getServiceType() ) )
                    {
                        criteria.add( Restrictions.eq( "serviceType", paymentDetailsPojo.getServiceType() ) );
                    }
                    if ( StringUtils.isNotBlank( paymentDetailsPojo.getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "status", paymentDetailsPojo.getStatus() ) );
                    }
                }
                criteria.add( Restrictions.gt( "amount", 0.0 ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPaymentDetailsPojo.class.getName() );
                payments = criteria.list();
                crmServiceCode = CRMServiceCode.CRM001;
                if ( !payments.isEmpty() )
                {
                    crmFinanceDto.setPaymentDetailsPojos( payments );
                }
                crmFinanceDto.setCustomerDetailsPojo( customerDetailsPojo );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM309;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while getPaymnentHistory: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto manageCMSFile( CrmCmsFilePojo inCmsFile )
    {
        LOGGER.info( "Inside manageCMSFile" );
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Map<String, Long> evictsMap = new HashMap<String, Long>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inCmsFile.getCmsFileId() > 0 )
            {
                CrmCmsFilePojo dbCrmCmsFilePojo = (CrmCmsFilePojo) session.get( CrmCmsFilePojo.class,
                                                                                inCmsFile.getCmsFileId() );
                dbCrmCmsFilePojo.setFailReason( inCmsFile.getFailReason() );
                dbCrmCmsFilePojo.setStatus( inCmsFile.getStatus() );
                session.update( dbCrmCmsFilePojo );
                evictsMap.put( CrmCmsFilePojo.class.getName(), dbCrmCmsFilePojo.getCmsFileId() );
                crmFinanceDto.setCmsFile( dbCrmCmsFilePojo );
            }
            else
            {
                inCmsFile.setCreatedTime( Calendar.getInstance().getTime() );
                Long cmsFileId = (Long) session.save( inCmsFile );
                if ( cmsFileId > 0 )
                {
                    inCmsFile.setCmsFileId( cmsFileId );
                    crmFinanceDto.setCmsFile( inCmsFile );
                    evictsMap.put( CrmCmsFilePojo.class.getName(), null );
                }
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while create Area: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( evictsMap );
            }
        }
        crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto manageCMSRecords( CrmFinanceDto inCrmFinanceDto, boolean inToUpdate )
    {
        LOGGER.info( "Inside manageCMSRecords" );
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmCmsPaymentPojo cmsPaymentPojoDB = null;
        StringBuilder oldValues = new StringBuilder();
        StringBuilder newValues = new StringBuilder();
        String remarks = "Manage CMS Records";
        long eventId = CRMServiceUtils.getEventId( CRMEvents.UPDATE_PAYMENT_STATUS.getEventName() );;
        int count = 0;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<CrmCmsPaymentPojo> inCmsPayments = inCrmFinanceDto.getCmsPayments();
            if ( CommonValidator.isValidCollection( inCmsPayments ) && inToUpdate )
            {
                List<CrmCmsPaymentPojo> updatedCmsPayments = new ArrayList<CrmCmsPaymentPojo>();
                Iterator<CrmCmsPaymentPojo> cmsIterator = inCmsPayments.iterator();
                while ( cmsIterator.hasNext() )
                {
                    CrmCmsPaymentPojo cmsPaymentPojo = cmsIterator.next();
                    if ( cmsPaymentPojo.getCmsId() > 0 )
                    {
                        cmsPaymentPojoDB = (CrmCmsPaymentPojo) session.get( CrmCmsPaymentPojo.class,
                                                                            cmsPaymentPojo.getCmsId() );
                        if ( StringUtils.isValidObj( cmsPaymentPojoDB ) )
                        {
                            if ( StringUtils.isNotBlank( cmsPaymentPojo.getIe2() )
                                    && !StringUtils.equals( cmsPaymentPojoDB.getIe2(), cmsPaymentPojo.getIe2() ) )
                            {
                                oldValues.append( "CustId-" ).append( cmsPaymentPojoDB.getIe2() )
                                        .append( IAppConstants.COMMA );
                                newValues.append( "CustId-" ).append( cmsPaymentPojo.getIe2() )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setIe2( cmsPaymentPojo.getIe2() );
                            }
                            if ( StringUtils.isNotBlank( cmsPaymentPojo.getInstrumentNo() )
                                    && !StringUtils.equals( cmsPaymentPojoDB.getInstrumentNo(),
                                                            cmsPaymentPojo.getInstrumentNo() ) )
                            {
                                oldValues.append( "ChequeNo-" ).append( cmsPaymentPojoDB.getInstrumentNo() )
                                        .append( IAppConstants.COMMA );
                                newValues.append( "ChequeNo-" ).append( cmsPaymentPojo.getInstrumentNo() )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setInstrumentNo( cmsPaymentPojo.getInstrumentNo() );
                            }
                            if ( StringUtils.isValidObj( cmsPaymentPojo.getInstrumentDate() )
                                    && cmsPaymentPojoDB.getInstrumentDate() != cmsPaymentPojo.getInstrumentDate() )
                            {
                                oldValues
                                        .append( "ChequeDate-" )
                                        .append( DateUtils.convertXmlCalToString( DateUtils
                                                         .toXMLGregorianCalendar( cmsPaymentPojoDB.getInstrumentDate() ) ) )
                                        .append( IAppConstants.COMMA );
                                newValues
                                        .append( "ChequeDate-" )
                                        .append( DateUtils.convertXmlCalToString( DateUtils
                                                         .toXMLGregorianCalendar( cmsPaymentPojo.getInstrumentDate() ) ) )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setInstrumentDate( cmsPaymentPojo.getInstrumentDate() );
                            }
                            if ( StringUtils.isNotBlank( cmsPaymentPojo.getDraweeBank() )
                                    && !StringUtils.equals( cmsPaymentPojoDB.getDraweeBank(),
                                                            cmsPaymentPojo.getDraweeBank() ) )
                            {
                                String oldBankName = "";
                                CrmRcaReasonPojo crmRcaReasonPojo = null;
                                if ( StringUtils.isNumeric( cmsPaymentPojoDB.getDraweeBank() ) )
                                {
                                    crmRcaReasonPojo = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class, Long
                                            .parseLong( cmsPaymentPojoDB.getDraweeBank() ) );
                                    oldBankName = crmRcaReasonPojo.getCategoryValue();
                                }
                                else
                                {
                                    oldBankName = cmsPaymentPojoDB.getDraweeBank();
                                }
                                if ( StringUtils.isNumeric( cmsPaymentPojo.getDraweeBank() ) )
                                {
                                    crmRcaReasonPojo = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class, Long
                                            .parseLong( cmsPaymentPojo.getDraweeBank() ) );
                                }
                                oldValues.append( "BankName-" ).append( oldBankName ).append( IAppConstants.COMMA );
                                newValues.append( "BankName-" ).append( crmRcaReasonPojo.getCategoryValue() )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setDraweeBank( cmsPaymentPojo.getDraweeBank() );
                            }
                            if ( StringUtils.isValidObj( cmsPaymentPojo.getInstrumentAmount() )
                                    && cmsPaymentPojoDB.getInstrumentAmount().doubleValue() != cmsPaymentPojo
                                            .getInstrumentAmount().doubleValue() )
                            {
                                oldValues.append( "PaidAmt-" ).append( cmsPaymentPojoDB.getInstrumentAmount() )
                                        .append( IAppConstants.COMMA );
                                newValues.append( "PaidAmt-" ).append( cmsPaymentPojo.getInstrumentAmount() )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setInstrumentAmount( cmsPaymentPojo.getInstrumentAmount() );
                            }
                            if ( StringUtils.isValidObj( cmsPaymentPojo.getStatus() )
                                    && !StringUtils.equals( cmsPaymentPojoDB.getStatus(), cmsPaymentPojo.getStatus() ) )
                            {
                                oldValues.append( "Status-" ).append( cmsPaymentPojoDB.getStatus() )
                                        .append( IAppConstants.COMMA );
                                newValues.append( "Status-" ).append( cmsPaymentPojo.getStatus() )
                                        .append( IAppConstants.COMMA );
                                cmsPaymentPojoDB.setStatus( cmsPaymentPojo.getStatus() );
                            }
                            cmsPaymentPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                            cmsPaymentPojoDB.setLastModifiedBy( cmsPaymentPojo.getLastModifiedBy() );
                            cmsPaymentPojoDB = (CrmCmsPaymentPojo) session.merge( cmsPaymentPojoDB );
                            if ( StringUtils.isValidObj( cmsPaymentPojoDB ) )
                            {
                                CRMServiceUtils.createAuditLog( oldValues, newValues, cmsPaymentPojo.getIe2(),
                                                                cmsPaymentPojo.getLastModifiedBy(), remarks, eventId,
                                                                CRMRequestType.FINANCE.getRequestCode(),
                                                                inCrmFinanceDto.getClientIPAddress(),
                                                                inCrmFinanceDto.getServerIPAddress() );
                            }
                            cmsIterator.remove();
                            updatedCmsPayments.add( cmsPaymentPojoDB );
                            count++;
                        }
                        else
                        {
                            LOGGER.info( "Unable to find record for CmsId:" + cmsPaymentPojo.getCmsId() );
                        }
                    }
                    else
                    {
                        LOGGER.info( "CmsId not exists for:" + cmsPaymentPojo );
                    }
                    if ( count % 20 == 0 )
                    {
                        session.flush();
                        session.clear();
                    }
                }
                if ( !updatedCmsPayments.isEmpty() )
                {
                    inCmsPayments.addAll( updatedCmsPayments );
                }
            }
            else if ( CommonValidator.isValidCollection( inCmsPayments ) )
            {
                for ( CrmCmsPaymentPojo cmsPaymentPojo : inCmsPayments )
                {
                    count++;
                    if ( cmsPaymentPojo.getCmsId() > 0 )
                    {
                        cmsPaymentPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        session.update( cmsPaymentPojo );
                    }
                    else
                    {
                        cmsPaymentPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        session.save( cmsPaymentPojo );
                    }
                    if ( count % 20 == 0 )
                    {
                        session.flush();
                        session.clear();
                    }
                }
            }
            else
            {
                LOGGER.info( "No CMS Payments to Process:" );
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while create Area: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCmsPaymentPojo.class.getName(), null, null );
            }
        }
        crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto getCMSFiles( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        List<CrmCmsFilePojo> payments = null;
        LOGGER.info( "Inside getCMSFiles" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            String fromDateStr = inCrmFinanceDto.getFromDate();
            String toDateStr = inCrmFinanceDto.getToDate();
            criteria = session.createCriteria( CrmCmsFilePojo.class );
            if ( StringUtils.isNotBlank( fromDateStr ) && StringUtils.isNotBlank( toDateStr ) )
            {
                fromDateStr += IAppConstants.START_TIME;
                toDateStr += IAppConstants.END_TIME;
                SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                Date fromDate = formatter.parse( fromDateStr );
                Date toDate = formatter.parse( toDateStr );
                LOGGER.info( "After Formatting: From Date: " + fromDate + " and To Date: " + toDate );
                criteria.add( Restrictions.between( "createdTime", fromDate, toDate ) );
                criteria.addOrder( Order.desc( "createdTime" ) );
                CrmCmsFilePojo cmsFilePojo = inCrmFinanceDto.getCmsFile();
                if ( StringUtils.isValidObj( cmsFilePojo ) )
                {
                    if ( StringUtils.isNotBlank( cmsFilePojo.getCmsFileType() ) )
                    {
                        criteria.add( Restrictions.eq( "cmsFileType", cmsFilePojo.getCmsFileType() ) );
                    }
                    if ( StringUtils.isNotBlank( cmsFilePojo.getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "status", cmsFilePojo.getStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( cmsFilePojo.getCmsFileName() ) )
                    {
                        criteria.add( Restrictions.eq( "cmsFileName", cmsFilePojo.getCmsFileName() ) );
                    }
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCmsFilePojo.class.getName() );
                payments = criteria.list();
                crmServiceCode = CRMServiceCode.CRM001;
                if ( !payments.isEmpty() )
                {
                    crmFinanceDto.setCmsFiles( payments );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while getCMSFiles: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto getCMSRecords( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        List<CrmCmsPaymentPojo> payments = null;
        LOGGER.info( "Inside getCMSRecords" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            String fromDateStr = inCrmFinanceDto.getFromDate();
            String toDateStr = inCrmFinanceDto.getToDate();
            criteria = session.createCriteria( CrmCmsPaymentPojo.class );
            if ( StringUtils.isNotBlank( fromDateStr ) && StringUtils.isNotBlank( toDateStr ) )
            {
                fromDateStr += IAppConstants.START_TIME;
                toDateStr += IAppConstants.END_TIME;
                SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                Date fromDate = formatter.parse( fromDateStr );
                Date toDate = formatter.parse( toDateStr );
                LOGGER.info( "After Formatting: From Date: " + fromDate + " and To Date: " + toDate );
                criteria.add( Restrictions.between( "createdTime", fromDate, toDate ) );
            }
            CrmCmsPaymentPojo cmsPaymentPojo = inCrmFinanceDto.getCmsPayment();
            if ( StringUtils.isValidObj( cmsPaymentPojo ) )
            {
                if ( cmsPaymentPojo.getDepositFileId() > 0 )
                {
                    criteria.add( Restrictions.eq( "depositFileId", cmsPaymentPojo.getDepositFileId() ) );
                }
                if ( cmsPaymentPojo.getClearanceFileId() > 0 )
                {
                    criteria.add( Restrictions.eq( "clearanceFileId", cmsPaymentPojo.getClearanceFileId() ) );
                }
                if ( StringUtils.isNotBlank( cmsPaymentPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", cmsPaymentPojo.getStatus() ) );
                }
                if ( StringUtils.isNotBlank( cmsPaymentPojo.getIe2() ) )
                {
                    criteria.add( Restrictions.eq( "ie2", cmsPaymentPojo.getIe2() ) );
                }
                if ( StringUtils.isNotBlank( cmsPaymentPojo.getDraweeBank() ) )
                {
                    criteria.add( Restrictions.eq( "draweeBank", cmsPaymentPojo.getDraweeBank() ) );
                }
                if ( StringUtils.isNotBlank( cmsPaymentPojo.getInstrumentNo() ) )
                {
                    criteria.add( Restrictions.eq( "instrumentNo", cmsPaymentPojo.getInstrumentNo() ) );
                }
                if ( StringUtils.isValidObj( cmsPaymentPojo.getInstrumentAmount() )
                        && cmsPaymentPojo.getInstrumentAmount().doubleValue() > 0.0 )
                {
                    criteria.add( Restrictions.eq( "instrumentAmount", cmsPaymentPojo.getInstrumentAmount() ) );
                }
            }
            criteria.addOrder( Order.desc( "createdTime" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCmsPaymentPojo.class.getName() );
            payments = criteria.list();
            crmServiceCode = CRMServiceCode.CRM001;
            if ( !payments.isEmpty() )
            {
                crmFinanceDto.setCmsPayments( payments );
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while getCMSRecords: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto postUpfrontPayments( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside postUpfrontPayments" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            int count = 0;
            //we will check object in business manager
            for ( CrmUpfrontPaymentPojo crmUpfrontPaymentPojo : inCrmFinanceDto.getCrmUpfrontPaymentPojos() )
            {
                count++;
                transaction = session.beginTransaction();
                crmUpfrontPaymentPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmUpfrontPaymentPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                session.save( crmUpfrontPaymentPojo );
                if ( count % 20 == 0 )
                {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
            inCrmFinanceDto.setCrmUpfrontPaymentPojos( null );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while createing upfront payment:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while createing upfront payment: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmUpfrontPaymentPojo.class.getName(), null, null );
            }
        }
        inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto getUpfrontPayments( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside trackUpfrontPayments" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUpfrontPaymentPojo.class );
            /* if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getChequeNo() ) )
                 criteria.add( Restrictions.ilike( "chequeNo", inCrmFinanceDto.getCrmUpfrontPaymentPojo().getChequeNo(),
                                                   MatchMode.ANYWHERE ) );
             if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getEntityName() ) )
                 criteria.add( Restrictions.ilike( "entityName", inCrmFinanceDto.getCrmUpfrontPaymentPojo()
                         .getEntityName(), MatchMode.ANYWHERE ) );
             if ( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getBankId() > 0 )
                 criteria.add( Restrictions.eq( "bankId", inCrmFinanceDto.getCrmUpfrontPaymentPojo().getBankId() ) );
             if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getCreatedTime() ) )
                 criteria.add( Restrictions.eq( "createdTime", inCrmFinanceDto.getCrmUpfrontPaymentPojo()
                         .getCreatedTime() ) );
                 */
            if ( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getPartnerId() > 0 )
            {
                criteria.add( Restrictions.eq( "partnerId", inCrmFinanceDto.getCrmUpfrontPaymentPojo().getPartnerId() ) );
            }
            if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmUpfrontPaymentPojo().getChequeDate() ) )
            {
                criteria.add( Restrictions
                        .eq( "chequeDate", inCrmFinanceDto.getCrmUpfrontPaymentPojo().getChequeDate() ) );
            }
            if ( StringUtils.isValidObj( inCrmFinanceDto.getFromDate() )
                    && StringUtils.isValidObj( inCrmFinanceDto.getToDate() ) )
            {
                criteria.add( Restrictions.between( "createdTime", new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" )
                                                            .parse( inCrmFinanceDto.getFromDate()
                                                                    + IAppConstants.START_TIME ),
                                                    new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" )
                                                            .parse( inCrmFinanceDto.getToDate()
                                                                    + IAppConstants.END_TIME ) ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUpfrontPaymentPojo.class.getName() );
            inCrmFinanceDto.setCrmUpfrontPaymentPojos( criteria.list() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while tracking upfront payment: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto getUpfrontPopUpDetails( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside getUpfront Popup Details Method" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmCustomerDetailsPojo> crmCustomerDetailsPojosForSend = null;
        try
        {
            LOGGER.info( "No Of CAF Ids :: " + inCrmFinanceDto.getCrfIDs().size() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            criteria.add( Restrictions.in( "crfId", inCrmFinanceDto.getCrfIDs() ) );
            List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = criteria.list();
            LOGGER.info( "Size Of Customer Details Pojo :: " + crmCustomerDetailsPojos.size() );
            crmCustomerDetailsPojosForSend = new ArrayList<CrmCustomerDetailsPojo>();
            for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : crmCustomerDetailsPojos )
            {
                criteria = session
                        .createCriteria( CrmPaymentDetailsPojo.class )
                        .add( Restrictions.eq( "installationStatus", CRMDisplayListConstants.PREINSTALLATION.getCode() ) )
                        .add( Restrictions.eq( "paymentMode", CRMDisplayListConstants.CASH.getCode() ) )
                        .add( Restrictions.eq( "customerRecordId", crmCustomerDetailsPojo.getRecordId() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPaymentDetailsPojo.class.getName() );
                List<CrmPaymentDetailsPojo> crmPaymentDetailsPojos = criteria.list();
                for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : crmPaymentDetailsPojos )
                {
                    //because of searilizable object we are developing clone
                    CrmCustomerDetailsPojo tempCrmCustomerDetailsPojo = (CrmCustomerDetailsPojo) crmCustomerDetailsPojo
                            .clone();
                    tempCrmCustomerDetailsPojo.setCrmPaymentDetailsPojo( crmPaymentDetailsPojo );
                    crmCustomerDetailsPojosForSend.add( tempCrmCustomerDetailsPojo );
                }
            }
            inCrmFinanceDto.setCrmCustomerDetailsPojos( crmCustomerDetailsPojosForSend );
            LOGGER.info( "Size Of List Customer Details Pojo :: " + crmCustomerDetailsPojosForSend.size() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while getting upfront popup details: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmFinanceDto;
    }

    private boolean postReversal( final CrmFinanceDto inCrmFinanceDto,
                                  final CrmPaymentDetailsPojo paymentDetailsPojo,
                                  Session session )
        throws Exception
    {
        LOGGER.info( "Inside postReversal" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        boolean updated = false;
        try
        {
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
            boolean postReversal = false;
            if ( StringUtils.isValidObj( paymentDetailsPojo ) )
            {
                boolean toCommit = true;
                if ( paymentDetailsPojo.getCustomerRecordId() > 0 )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                          paymentDetailsPojo.getCustomerRecordId() );
                }
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo )
                        && StringUtils.numericValue( crmCustomerDetailsPojo.getCustomerId() ) > 0 )
                {
                    if ( StringUtils.equals( CRMDisplayListConstants.PREINSTALLATION.getCode(),
                                             paymentDetailsPojo.getInstallationStatus() ) )
                    {
                        boolean toFreeze = false;
                        Map<String, Object> criteriaMap = new HashMap<String, Object>();
                        criteriaMap.put( "installationStatus", CRMDisplayListConstants.PREINSTALLATION.getCode() );
                        criteriaMap.put( "customerRecordId", crmCustomerDetailsPojo.getRecordId() );
                        List<CrmPaymentDetailsPojo> payments = CRMServiceUtils
                                .getDBValueList( CrmPaymentDetailsPojo.class, criteriaMap, "paymentId", false );
                        if ( CommonValidator.isValidCollection( payments ) )
                        {
                            if ( payments.size() > 1 )
                            {
                                for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
                                {
                                    if ( crmPaymentDetailsPojo.getPaymentId() != paymentDetailsPojo.getPaymentId() )
                                    {
                                        if ( StringUtils.equals( crmPaymentDetailsPojo.getStatus(),
                                                                 CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() ) )
                                        {
                                            if ( !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                      CRMOperationStages.CLOSE.getCode() )
                                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                            CRMOperationStages.ON_BOARD.getCode() )
                                                    && !StringUtils.isValidObj( crmCustomerDetailsPojo
                                                            .getActivationDate() )
                                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                                            CRMOperationStages.FREEZE.getCode() ) )
                                            {
                                                toFreeze = true;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            else if ( !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                           CRMOperationStages.CLOSE.getCode() )
                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                            CRMOperationStages.ON_BOARD.getCode() )
                                    && !StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() )
                                    && !StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                            CRMOperationStages.FREEZE.getCode() ) )
                            {
                                toFreeze = true;
                            }
                            if ( StringUtils.equals( paymentDetailsPojo.getStatus(),
                                                     CRMStatusCode.PAYMENT_POSTED.getStatusCode() ) )
                            {
                                postReversal = true;
                            }
                            else
                            {
                                paymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                paymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            }
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM999;
                            toCommit = false;
                            LOGGER.info( "No Payments Found for:" + criteriaMap );
                        }
                        if ( toCommit & toFreeze )
                        {
                            toCommit = false;
                            crmCustomerDetailsPojo.setCrfPreviousStage( crmCustomerDetailsPojo.getCrfStage() );
                            crmCustomerDetailsPojo.setCrfStage( CRMOperationStages.FREEZE.getCode() );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.update( crmCustomerDetailsPojo );
                            toCommit = changeInboxBin( paymentDetailsPojo.getLastModifiedBy(), crmCustomerDetailsPojo,
                                                       session );
                        }
                        if ( toCommit & postReversal )
                        {
                            toCommit = false;
                            paymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( paymentDetailsPojo );
                            getCrmBillingManager().cancelPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                paymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                    }
                    else if ( StringUtils.equals( CRMDisplayListConstants.POSTINSTALLATION.getCode(),
                                                  paymentDetailsPojo.getInstallationStatus() ) )
                    {
                        boolean toBar = false;
                        Map<String, Object> criteriaMap = new HashMap<String, Object>();
                        criteriaMap.put( "installationStatus", CRMDisplayListConstants.POSTINSTALLATION.getCode() );
                        criteriaMap.put( "customerRecordId", crmCustomerDetailsPojo.getRecordId() );
                        List<CrmPaymentDetailsPojo> payments = CRMServiceUtils
                                .getDBValueList( CrmPaymentDetailsPojo.class, criteriaMap, "paymentId", false );
                        if ( CommonValidator.isValidCollection( payments ) )
                        {
                            if ( payments.size() > 1 )
                            {
                                for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
                                {
                                    if ( crmPaymentDetailsPojo.getPaymentId() != paymentDetailsPojo.getPaymentId() )
                                    {
                                        if ( StringUtils.equals( crmPaymentDetailsPojo.getStatus(),
                                                                 CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() ) )
                                        {
                                            if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                     CRMStatusCode.ACTIVE.getStatusCode() ) )
                                            {
                                                toBar = true;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            else if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                          CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                toBar = true;
                            }
                            if ( StringUtils.equals( paymentDetailsPojo.getStatus(),
                                                     CRMStatusCode.PAYMENT_POSTED.getStatusCode() ) )
                            {
                                postReversal = true;
                            }
                            else
                            {
                                paymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                paymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            }
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM999;
                            toCommit = false;
                            LOGGER.info( "No Payments Found for:" + criteriaMap );
                        }
                        if ( toCommit && postReversal )
                        {
                            toCommit = false;
                            paymentDetailsPojo.setReversalDate( Calendar.getInstance().getTime() );
                            inCrmFinanceDto.setPaymentDetailsPojo( paymentDetailsPojo );
                            getCrmBillingManager().cancelPayment( inCrmFinanceDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inCrmFinanceDto.getStatusCode() ) )
                            {
                                paymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
                                toCommit = true;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.valueOf( inCrmFinanceDto.getStatusCode() );
                            }
                            inCrmFinanceDto.setPaymentDetailsPojo( null );
                        }
                        if ( toCommit && toBar )
                        {
                            CrmQrcWhitelistPojo barringWhitelist = null;
                            criteriaMap = new HashMap<String, Object>();
                            criteriaMap.put( "customerId", paymentDetailsPojo.getCustomerId() );
                            criteriaMap.put( "whitelistType", CRMStatusCode.BARRED.getStatusCode() );
                            List<CrmQrcWhitelistPojo> whiteList = CRMServiceUtils
                                    .getDBValueList( CrmQrcWhitelistPojo.class, criteriaMap, "endDate", false );
                            if ( CommonValidator.isValidCollection( whiteList ) )
                            {
                                barringWhitelist = whiteList.get( 0 );
                                if ( Calendar.getInstance().getTime().after( barringWhitelist.getEndDate() ) )
                                {
                                    barringWhitelist = null;
                                }
                            }
                            if ( !StringUtils.isValidObj( barringWhitelist ) )
                            {
                                toCommit = false;
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( paymentDetailsPojo.getCustomerId() );
                                billingDto.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                billingDto.setRemarks( paymentDetailsPojo.getBouncingReason() );
                                getCrmBillingManager().changeStatus( billingDto );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         billingDto.getStatusCode() ) )
                                {
                                    crmCustomerDetailsPojo.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( crmCustomerDetailsPojo );
                                    toCommit = true;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else
                            {
                                if ( !CommonValidator.isValidCollection( inCrmFinanceDto.getStatusCodes() ) )
                                {
                                    inCrmFinanceDto.setStatusCodes( new TreeSet<String>() );
                                }
                                inCrmFinanceDto.getStatusCodes().add( CRMServiceCode.CRM403.getStatusCode() );
                                LOGGER.info( "Unable to bar Customer:" + barringWhitelist.getCustomerId()
                                        + " due to Barring Exception List: " + barringWhitelist );
                            }
                        }
                    }
                    if ( toCommit )
                    {
                        paymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        session.update( paymentDetailsPojo );
                        crmServiceCode = CRMServiceCode.CRM001;
                        updated = true;
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM701;
                    LOGGER.info( "No Customer Found for:" + paymentDetailsPojo.getCustomerRecordId() );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            throw ex;
        }
        finally
        {
            inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return updated;
    }

    @Override
    public void postReversal( CrmFinanceDto inDestFinanceDto,
                              CrmCustomerDetailsPojo inCrmCustomerDetailsPojo,
                              CrmPaymentDetailsPojo inCrmPaymentDetailsPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        boolean reversed = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            reversed = postReversal( inDestFinanceDto, inCrmPaymentDetailsPojo, session );
            if ( reversed && !inDestFinanceDto.isReversalWOCrf() )
            {
                CrmPaymentDetailsPojo newPayment = new CrmPaymentDetailsPojo();
                CRMUtils.copyAllProperties( newPayment, inCrmPaymentDetailsPojo );
                newPayment.setCrfId( null );
                newPayment.setCustomerId( null );
                if ( StringUtils.isNotBlank( inCrmCustomerDetailsPojo.getCrfId() ) )
                {
                    newPayment.setCrfId( inCrmCustomerDetailsPojo.getCrfId() );
                }
                else if ( StringUtils.isNotBlank( inCrmCustomerDetailsPojo.getCustomerId() ) )
                {
                    newPayment.setCustomerId( inCrmCustomerDetailsPojo.getCustomerId() );
                }
                newPayment.setCustomerRecordId( 0 );
                newPayment.setPaymentId( 0 );
                newPayment.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                newPayment.setReversalReason( null );
                newPayment.setReversalDate( null );
                newPayment.setCustomerDetailsPojo( null );
                reversed = postSinglePayment( inDestFinanceDto, newPayment, session );
            }
            if ( reversed )
            {
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while posting reversal:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while posting reversal: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( reversed )
            {
                HibernateUtil.evictAll( CrmPaymentDetailsPojo.class.getName(), null, null );
                HibernateUtil.evictAll( CrmCustomerDetailsPojo.class.getName(), null, null );
            }
        }
    }

    @Override
    public CrmFinanceDto getRefundDetails( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside getRefundDetails" );
        SessionFactory sessionFactory = null;
        Session session = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inCrmFinanceDto.getCustomerDetailsPojo() ) )
            {
                CrmCustomerDetailsPojo customerDetailsPojo;
                Criteria criteria = session.createCriteria( CrmCustomerRefundsPojo.class );
                /* //  CrmCustomerDetailsPojo customerDetailsPojo = inCrmFinanceDto.getCustomerDetailsPojo();

                   else if ( StringUtils.isNotBlank( customerDetailsPojo.getCustomerId() ) )
                   {
                       customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                          customerDetailsPojo.getCustomerId() );
                   }
                   if ( StringUtils.isValidObj( customerDetailsPojo ) && customerDetailsPojo.getRecordId() > 0 )
                   {*/
                if ( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundId() > 0 )
                {
                    criteria.add( Restrictions.eq( "refundId", inCrmFinanceDto.getCrmCustomerRefundsPojo()
                            .getRefundId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCustomerDetailsPojo().getCustomerId() ) )
                {
                    criteria.add( Restrictions.eq( "customerId", inCrmFinanceDto.getCustomerDetailsPojo()
                            .getCustomerId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCustomerDetailsPojo().getCrfId() ) )
                {
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                       inCrmFinanceDto.getCustomerDetailsPojo()
                                                                               .getCrfId() );
                    if ( ( StringUtils.isValidObj( customerDetailsPojo ) ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", customerDetailsPojo.getCustomerId() ) );
                    }
                    else
                    {
                        inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM309.getStatusCode() );
                        inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM309.getStatusDesc() );
                        return inCrmFinanceDto;
                    }
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmCustomerRefundsPojo().getEntityType() ) )
                {
                    criteria.add( Restrictions.eq( "entityType", inCrmFinanceDto.getCrmCustomerRefundsPojo()
                            .getEntityType() ) );
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmCustomerRefundsPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmFinanceDto.getCrmCustomerRefundsPojo().getStatus() ) );
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundMode() ) )
                {
                    criteria.add( Restrictions.eq( "refundMode", inCrmFinanceDto.getCrmCustomerRefundsPojo()
                            .getRefundMode() ) );
                }
                if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmCustomerRefundsPojo().getChequeNumber() ) )
                {
                    criteria.add( Restrictions.eq( "chequeNumber", inCrmFinanceDto.getCrmCustomerRefundsPojo()
                            .getChequeNumber() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustomerRefundsPojo.class.getName() );
                List<CrmCustomerRefundsPojo> crmCustomerRefundsPojos = criteria.list();
                LOGGER.info( "SIZE::::" + crmCustomerRefundsPojos.size() );
                crmServiceCode = CRMServiceCode.CRM001;
                if ( !crmCustomerRefundsPojos.isEmpty() )
                {
                    inCrmFinanceDto.setRefundDetailsPojos( crmCustomerRefundsPojos );
                    inCrmFinanceDto.setCustomerDetailsPojo( inCrmFinanceDto.getCustomerDetailsPojo() );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM309;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception occured::", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto changeRefundChequeStatus( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "changeRefundChequeStatus " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerRefundsPojo crmCustomerRefundspojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmCustomerRefundsPojo() )
                    && inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundId() > 0 )
            {
                crmCustomerRefundspojo = (CrmCustomerRefundsPojo) session.get( CrmCustomerRefundsPojo.class,
                                                                               inCrmFinanceDto
                                                                                       .getCrmCustomerRefundsPojo()
                                                                                       .getRefundId() );
                if ( StringUtils.equals( inCrmFinanceDto.getCrmCustomerRefundsPojo().getChequeStatus(),
                                         CRMDisplayListConstants.CHEQUE_REALIZED.getCode() ) )
                {
                    CrmBillingDto billingDto = new CrmBillingDto();
                    billingDto.setCustomerId( crmCustomerRefundspojo.getCustomerId() );
                    billingDto.setChargeAmount( new BigDecimal( crmCustomerRefundspojo.getRefundAmount() ) );
                    getCrmBillingManager().terminationRefund( billingDto );
                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                    if ( StringUtils.isValidObj( crmCustomerRefundspojo )
                            && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                    {
                        crmCustomerRefundspojo.setChequeStatus( inCrmFinanceDto.getCrmCustomerRefundsPojo()
                                .getChequeStatus() );
                        if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRejectionReason() ) )
                        {
                            crmCustomerRefundspojo.setRejectionReason( inCrmFinanceDto.getCrmCustomerRefundsPojo()
                                    .getRejectionReason() );
                        }
                        crmCustomerRefundspojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                        crmCustomerRefundspojo.setRefundDate( Calendar.getInstance().getTime() );
                        crmCustomerRefundspojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                        crmCustomerRefundspojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        LOGGER.info( "After ::" + crmCustomerRefundspojo );
                        session.update( crmCustomerRefundspojo );
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                    }
                }
                else
                {
                    crmCustomerRefundspojo.setChequeStatus( inCrmFinanceDto.getCrmCustomerRefundsPojo()
                            .getChequeStatus() );
                    crmCustomerRefundspojo.setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                    crmCustomerRefundspojo.setRefundDate( Calendar.getInstance().getTime() );
                    crmCustomerRefundspojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                    crmCustomerRefundspojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    session.update( crmCustomerRefundspojo );
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
            else
            {
                LOGGER.info( "Details not present for the customer" );
                crmServiceCode = CRMServiceCode.CRM704;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.info( "Exception occured::", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmCustomerRefundsPojo.class.getName(), null,
                                        crmCustomerRefundspojo.getRefundId() );
            }
        }
        inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto postRefund( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "postRefund method called:: " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Map<String, Long> evictsMap = new HashMap<String, Long>();
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            boolean toCommit = false;
            if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmCustomerRefundsPojo() )
                    && StringUtils.isNotEmpty( inCrmFinanceDto.getCrmCustomerRefundsPojo().getTicketID() )
                    && ( StringUtils.isValidObj( inCrmFinanceDto.getCustomerDetailsPojo() ) ) )
            {
                boolean valid = getQrcManagerDao().isValidTicketNo( inCrmFinanceDto.getCrmCustomerRefundsPojo()
                                                                            .getTicketID(),
                                                                    inCrmFinanceDto.getCustomerDetailsPojo()
                                                                            .getCustomerId() );
                if ( !valid )
                {
                    crmServiceCode = CRMServiceCode.CRM402;
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                LOGGER.info( "Customer status" + inCrmFinanceDto.getCustomerDetailsPojo().getStatus() );
               /* if ( StringUtils.equals( inCrmFinanceDto.getCustomerDetailsPojo().getStatus(),
                                         CRMStatusCode.PDC.getStatusDesc() )
                        || StringUtils.equals( inCrmFinanceDto.getCustomerDetailsPojo().getStatus(),
                                               CRMStatusCode.CANCELED.getStatusDesc() ) )
                {*/
                    LOGGER.info( "postRefund method called with valid obj:: " );
                    if ( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundId() > 0 )
                    {
                        LOGGER.info( "update case:: " + inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundId() );
                        CrmCustomerRefundsPojo crmCustomerRefundspojo = (CrmCustomerRefundsPojo) session
                                .get( CrmCustomerRefundsPojo.class, inCrmFinanceDto.getCrmCustomerRefundsPojo()
                                        .getRefundId() );
                        CRMUtils.copyPropertyValue( crmCustomerRefundspojo,
                                                    inCrmFinanceDto.getCrmCustomerRefundsPojo(), null );
                        if ( StringUtils.isValidObj( crmCustomerRefundspojo ) )
                        {
                            if ( StringUtils.equals( inCrmFinanceDto.getCrmCustomerRefundsPojo().getChequeStatus(),
                                                     CRMDisplayListConstants.CHEQUE_REALIZED.getCode() ) )
                            {
                                LOGGER.info( "true" );
                                CrmBillingDto billingDto = new CrmBillingDto();
                                billingDto.setCustomerId( crmCustomerRefundspojo.getCustomerId() );
                                billingDto.setChargeAmount( new BigDecimal( crmCustomerRefundspojo.getRefundAmount() ) );
                                getCrmBillingManager().terminationRefund( billingDto );
                                billingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                                if ( StringUtils.equals( billingDto.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) )
                                {
                                    toCommit = true;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                }
                            }
                            else if ( StringUtils
                                    .equals( inCrmFinanceDto.getCrmCustomerRefundsPojo().getChequeStatus(),
                                             CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode() ) )
                            {
                                toCommit = true;
                            }
                            if ( toCommit )
                            {
                                crmCustomerRefundspojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                                crmCustomerRefundspojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                session.update( crmCustomerRefundspojo );
                                evictsMap.put( CrmCustomerRefundsPojo.class.getName(),
                                               crmCustomerRefundspojo.getRefundId() );
                                if ( StringUtils.isValidObj( inCrmFinanceDto.getRemarks() ) )
                                {
                                    RemarksPojo remarksPojo = new RemarksPojo();
                                    remarksPojo.setRemarks( inCrmFinanceDto.getRemarks().getRemarks() );
                                    remarksPojo.setReasonId( inCrmFinanceDto.getRemarks().getReasonId() );
                                    remarksPojo.setMappingId( inCrmFinanceDto.getCustomerDetailsPojo().getRecordId()
                                            + "" );
                                    remarksPojo.setActions( "Refund Requested" );
                                    remarksPojo.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                    remarksPojo.setMappingType( CRMRCAReason.FINANCE.getStatusCode() );
                                    session.save( remarksPojo );
                                    evictsMap.put( RemarksPojo.class.getName(), null );
                                }
                                transaction.commit();
                                crmServiceCode = CRMServiceCode.CRM001;
                            }
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM704;
                        }
                    }
                    else
                    {
                        if ( checkRefundDuplication( inCrmFinanceDto.getCrmCustomerRefundsPojo().getCustomerId() ) )
                        {
                            CrmCustomerRefundsPojo crmRefundsPojo = inCrmFinanceDto.getCrmCustomerRefundsPojo();
                            if ( StringUtils.equals( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundMode(),
                                                     CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
                            {
                                CrmBillingDto billingDto = new CrmBillingDto();
                                if ( StringUtils.equals( crmRefundsPojo.getStatus(),
                                                         CRMStatusCode.PROCESSED.getStatusCode() ) )
                                {
                                    billingDto.setCustomerId( crmRefundsPojo.getCustomerId() );
                                    billingDto.setChargeAmount( new BigDecimal( crmRefundsPojo.getRefundAmount() ) );
                                    getCrmBillingManager().terminationRefund( billingDto );
                                    inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                             billingDto.getStatusCode() ) )
                                    {
                                        toCommit = true;
                                    }
                                    else
                                    {
                                        crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                        inCrmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                    }
                                }
                                else
                                {
                                    toCommit = true;
                                }
                            }
                            else if ( StringUtils.equals( inCrmFinanceDto.getCrmCustomerRefundsPojo().getRefundMode(),
                                                          CRMDisplayListConstants.CHEQUE.getCode() ) )
                            {
                                toCommit = true;
                            }
                            if ( toCommit )
                            {
                                crmRefundsPojo.setCreatedBy( inCrmFinanceDto.getUserId() );
                                crmRefundsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                long id = (Long) session.save( crmRefundsPojo );
                                evictsMap.put( CrmCustomerRefundsPojo.class.getName(), null );
                                if ( StringUtils.isValidObj( inCrmFinanceDto.getRemarks() ) && id > 0 )
                                {
                                    RemarksPojo remarksPojo = new RemarksPojo();
                                    remarksPojo.setRemarks( inCrmFinanceDto.getRemarks().getRemarks() );
                                    remarksPojo.setReasonId( inCrmFinanceDto.getRemarks().getReasonId() );
                                    remarksPojo.setMappingId( id + "" );
                                    remarksPojo.setActions( "Refund Requested" );
                                    remarksPojo.setCreatedBy( inCrmFinanceDto.getUserId() );
                                    remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                    session.save( remarksPojo );
                                    evictsMap.put( RemarksPojo.class.getName(), null );
                                }
                                transaction.commit();
                                crmServiceCode = CRMServiceCode.CRM001;
                            }
                        }
                        else
                        {
                            LOGGER.info( "Duplicate entry not allowed" );
                            crmServiceCode = CRMServiceCode.CRM435;
                        }
                    }
                /*}
                else
                {
                    crmServiceCode = CRMServiceCode.CRM438;
                }*/
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception occured::", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evictsMap );
            }
        }
        inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto savePODData( List<CrmPodDetailsPojo> successRecordList,
                                      List<CrmPodDetailsPojo> rejectedRecordList )
    {
        LOGGER.info( "Inside savePODData" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerRefundsPojo crmRefundPojo = null;
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            int successCount = 0;
            for ( CrmPodDetailsPojo crmPodDetailsPojo : successRecordList )
            {
                transaction = session.beginTransaction();
                LOGGER.info( "working for customer Id:::" + crmPodDetailsPojo.getCustomerId() );
                if ( !validateCustomer( crmPodDetailsPojo ) )
                {
                    crmPodDetailsPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                    crmPodDetailsPojo.setProcessFailureReason( CRMServiceCode.CRM424.getStatusDesc() );
                    LOGGER.info( "POJO::::" + crmPodDetailsPojo );
                    rejectedRecordList.add( crmPodDetailsPojo );
                }
                else
                {
                    CrmPodDetailsPojo pojo = new CrmPodDetailsPojo();
                    BeanUtils.copyProperties( pojo, crmPodDetailsPojo );
                    pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    pojo.setCreatedTime( Calendar.getInstance().getTime() );
                    crmRefundPojo = getRefundCustomer( pojo );
                    if ( StringUtils.isValidObj( crmRefundPojo ) )
                    {
                        if ( StringUtils.equals( pojo.getDeliveryStatus(), CRMStatusCode.DELIVER.getStatusCode() ) )
                        {
                            CrmBillingDto billingDto = new CrmBillingDto();
                            billingDto.setCustomerId( pojo.getCustomerId() );
                            billingDto.setChargeAmount( new BigDecimal( crmRefundPojo.getRefundAmount() ) );
                            getCrmBillingManager().terminationRefund( billingDto );
                            crmFinanceDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Billing status Code::" + billingDto.getStatusCode() );
                            LOGGER.info( "Billing Error Code::" + billingDto.getBillingErrorCode() );
                            if ( StringUtils.equals( billingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                long podId = (Long) session.save( crmPodDetailsPojo );
                                crmRefundPojo.setChequeStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
                                crmRefundPojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                                crmRefundPojo.setPodId( podId );
                                crmRefundPojo.setLastModifiedBy( pojo.getCreatedBy() );
                                crmRefundPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                session.update( crmRefundPojo );
                                transaction.commit();
                                successCount = successCount + 1;
                            }
                            else
                            {
                                LOGGER.info( "transaction fail" );
                                crmPodDetailsPojo.setProcessFailureReason( billingDto.getStatusDesc()
                                        + billingDto.getBillingErrorCode() );
                                rejectedRecordList.add( crmPodDetailsPojo );
                            }
                        }
                        else
                        {
                            long podId = (Long) session.save( crmPodDetailsPojo );
                            crmRefundPojo.setChequeStatus( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode() );
                            crmRefundPojo.setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                            crmRefundPojo.setRejectionReason( pojo.getNonDeliveryReason() );
                            crmRefundPojo.setPodId( podId );
                            crmRefundPojo.setLastModifiedBy( pojo.getCreatedBy() );
                            crmRefundPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.update( crmRefundPojo );
                            transaction.commit();
                            successCount = successCount + 1;
                        }
                    }
                }
            }
            LOGGER.info( "final Rejected Record List size:::" + rejectedRecordList.size() + "Success Count "
                    + successCount );
            crmFinanceDto.setCrmPodDetailsPojos( rejectedRecordList );
            crmFinanceDto.setDbSuccessCount( successCount );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while save POD Data:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Exception while save POD Data: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            crmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            crmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmPodDetailsPojo.class.getName(), null, null );
                HibernateUtil.evictAll( CrmCustomerRefundsPojo.class.getName(), null, null );
            }
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto trackPODRecords( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside savePODData" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmPodDetailsPojo.class );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPodDetailsPojo.class.getName() );
            List<CrmPodDetailsPojo> CrmPodDetailsPojos = criteria.list();
            if ( !CrmPodDetailsPojos.isEmpty() )
            {
                inCrmFinanceDto.setCrmPodDetailsPojos( CrmPodDetailsPojos );
                LOGGER.info( "List Size :::" + inCrmFinanceDto.getCrmPodDetailsPojos().size() );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Exception while save POD Data: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmFinanceDto;
    }

    public boolean validateCustomer( CrmPodDetailsPojo crmPodDetailsPojo )
    {
        LOGGER.info( "customer Id" + crmPodDetailsPojo.getCustomerId() );
        LOGGER.info( crmPodDetailsPojo.getInstrumentNumber() );
        CrmFinanceDto dto = new CrmFinanceDto();
        CrmCustomerRefundsPojo crmCustomerRefundpojo = getRefundCustomer( crmPodDetailsPojo );
        if ( !StringUtils.isValidObj( crmCustomerRefundpojo ) )
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkRefundDuplication( String customerId )
    {
        CrmFinanceDto dto = new CrmFinanceDto();
        CrmCustomerDetailsPojo pojo = new CrmCustomerDetailsPojo();
        pojo.setCustomerId( customerId );
        CrmCustomerRefundsPojo refundPojo = new CrmCustomerRefundsPojo();
        refundPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
        dto.setCustomerDetailsPojo( pojo );
        dto.setCrmCustomerRefundsPojo( refundPojo );
        dto = getRefundDetails( dto );
        if ( !StringUtils.isValidObj( dto.getRefundDetailsPojos() ) || dto.getRefundDetailsPojos().isEmpty() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private CrmCustomerRefundsPojo getRefundCustomer( CrmPodDetailsPojo inPojo )
    {
        CrmFinanceDto dto = new CrmFinanceDto();
        CrmCustomerDetailsPojo pojo = new CrmCustomerDetailsPojo();
        pojo.setCustomerId( inPojo.getCustomerId() );
        CrmCustomerRefundsPojo refundPojo = new CrmCustomerRefundsPojo();
        refundPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
        if ( StringUtils.isNumeric( inPojo.getInstrumentNumber() ) )
        {
            refundPojo.setChequeNumber( inPojo.getInstrumentNumber() );
        }
        dto.setCustomerDetailsPojo( pojo );
        dto.setCrmCustomerRefundsPojo( refundPojo );
        dto = getRefundDetails( dto );
        if ( StringUtils.isValidObj( dto.getRefundDetailsPojos() ) && !dto.getRefundDetailsPojos().isEmpty() )
        {
            return dto.getRefundDetailsPojos().get( 0 );
        }
        return null;
    }

    @Override
    public CrmFinanceDto paymentListByTransId( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside paymentListByTransId" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmPaymentDetailsPojo> paymentList = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojo() ) )
            {
                CrmPaymentDetailsPojo paymentDetailsPojo = inCrmFinanceDto.getPaymentDetailsPojo();
                criteria = session.createCriteria( CrmPaymentDetailsPojo.class );
                if ( StringUtils.isNotBlank( paymentDetailsPojo.getPaymentMode() ) )
                {
                    criteria.add( Restrictions.eq( "paymentMode", paymentDetailsPojo.getPaymentMode() ) );
                }
                if ( StringUtils.isNotBlank( paymentDetailsPojo.getTransactionId() ) )
                {
                    criteria.add( Restrictions.eq( "transactionId", paymentDetailsPojo.getTransactionId() ) );
                }
                if ( paymentDetailsPojo.getCustomerRecordId() > 0 )
                {
                    criteria.add( Restrictions.eq( "customerRecordId", paymentDetailsPojo.getCustomerRecordId() ) );
                }
                criteria.add( Restrictions.in( "status", new String[]
                { CRMStatusCode.ACTIVE.getStatusCode(), CRMStatusCode.ACTIVE.getStatusCode(),
                        CRMStatusCode.PAYMENT_POSTED.getStatusCode() } ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPaymentDetailsPojo.class.getName() );
            paymentList = criteria.list();
            if ( CommonValidator.isValidCollection( paymentList ) )
            {
                LOGGER.info( "Size of Payment List:" + paymentList.size() );
                inCrmFinanceDto.setPaymentDetailsPojos( paymentList );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Exception while paymentListByTransId : ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmFinanceDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmFinanceDto;
    }
}
