package com.np.tele.crm.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.utils.StringUtils;

public class CrmDaoHelper
{
    public static void setPaymentDetails( CrmPaymentDetailsPojo inCrmPaymentDetailsPojo )
    {
        if ( !StringUtils.isValidObj( inCrmPaymentDetailsPojo.getPaymentDate() ) )
        {
            inCrmPaymentDetailsPojo.setPaymentDate( Calendar.getInstance().getTime() );
        }
        if ( StringUtils.isBlank( inCrmPaymentDetailsPojo.getPaymentStatus() ) )
        {
            if ( StringUtils.equals( inCrmPaymentDetailsPojo.getPaymentChannel(),
                                     CRMDisplayListConstants.SECURITY_DEPOSIT.getCode() ) )
            {
                inCrmPaymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
            }
            else
            {
                inCrmPaymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_NOTRECIEVED.getCode() );
            }
        }
        if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE.getCode(), inCrmPaymentDetailsPojo.getPaymentMode() ) )
        {
            inCrmPaymentDetailsPojo.setTransactionId( null );
            inCrmPaymentDetailsPojo.setReceiptNo( null );
            if ( StringUtils.equals( inCrmPaymentDetailsPojo.getPaymentChannel(),
                                     CRMDisplayListConstants.SECURITY_DEPOSIT.getCode() ) )
            {
                inCrmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
            }
            else
            {
                inCrmPaymentDetailsPojo
                        .setRealzationStatus( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode() );
            }
            if ( StringUtils.isBlank( inCrmPaymentDetailsPojo.getPaymentChannel() ) )
            {
                inCrmPaymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.DIRECT.getCode() );
            }
        }
        else if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(), inCrmPaymentDetailsPojo.getPaymentMode() ) )
        {
            inCrmPaymentDetailsPojo.setChequeDate( null );
            inCrmPaymentDetailsPojo.setRealzationStatus( null );
            inCrmPaymentDetailsPojo.setBankName( null );
            inCrmPaymentDetailsPojo.setBankCity( null );
            inCrmPaymentDetailsPojo.setBankBranch( null );
            inCrmPaymentDetailsPojo.setChequeDdNo( null );
            inCrmPaymentDetailsPojo.setTransactionId( null );
            if ( StringUtils.isBlank( inCrmPaymentDetailsPojo.getPaymentChannel() ) )
            {
                inCrmPaymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.DIRECT.getCode() );
            }
        }
        else if ( StringUtils.equals( CRMDisplayListConstants.ONLINE_PAYMENT.getCode(),
                                      inCrmPaymentDetailsPojo.getPaymentMode() ) )
        {
            inCrmPaymentDetailsPojo.setChequeDate( null );
            inCrmPaymentDetailsPojo.setRealzationStatus( null );
            inCrmPaymentDetailsPojo.setBankName( null );
            inCrmPaymentDetailsPojo.setBankCity( null );
            inCrmPaymentDetailsPojo.setBankBranch( null );
            inCrmPaymentDetailsPojo.setChequeDdNo( null );
            inCrmPaymentDetailsPojo.setReceiptNo( null );
            inCrmPaymentDetailsPojo.setBankTransId( inCrmPaymentDetailsPojo.getTransactionId() );
            inCrmPaymentDetailsPojo.setPgwTransId( inCrmPaymentDetailsPojo.getTransactionId() );
            if ( StringUtils.isBlank( inCrmPaymentDetailsPojo.getPaymentChannel() ) )
            {
                inCrmPaymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.NEFT.getCode() );
            }
        }
        else
        {
            inCrmPaymentDetailsPojo.setTransactionId( null );
            inCrmPaymentDetailsPojo.setBankTransId( null );
            inCrmPaymentDetailsPojo.setPgwTransId( null );
            inCrmPaymentDetailsPojo.setReceiptNo( null );
            inCrmPaymentDetailsPojo.setChequeDate( null );
            inCrmPaymentDetailsPojo.setRealzationStatus( null );
            inCrmPaymentDetailsPojo.setBankName( null );
            inCrmPaymentDetailsPojo.setBankCity( null );
            inCrmPaymentDetailsPojo.setBankBranch( null );
            inCrmPaymentDetailsPojo.setChequeDdNo( null );
            // Only for this case
            inCrmPaymentDetailsPojo.setPaymentStatus( null );
            inCrmPaymentDetailsPojo.setPaymentChannel( null );
            inCrmPaymentDetailsPojo.setPaymentDate( null );
        }
    }

    public static void setRecieptStatus( Session inSession, CrmPaymentDetailsPojo crmPaymentDetailsPojo, boolean used )
    {
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "category", CRMRCAReason.INA.getStatusCode() );
        criteriaMap.put( "subCategory", "CashReceipt" );
        criteriaMap.put( "categoryValue", crmPaymentDetailsPojo.getReceiptNo() );
        if ( used )
        {
            criteriaMap.put( "valueAlias", CRMStatusCode.UNUSED.getStatusCode() );
        }
        else
        {
            criteriaMap.put( "valueAlias", CRMStatusCode.USED.getStatusCode() );
        }
        CrmRcaReasonPojo masterCrf = CRMServiceUtils.getDBValue( CrmRcaReasonPojo.class, criteriaMap, null, false,
                                                                 inSession );
        if ( StringUtils.isValidObj( masterCrf ) )
        {
            if ( used )
            {
                masterCrf.setValueAlias( CRMStatusCode.USED.getStatusCode() );
            }
            else
            {
                masterCrf.setValueAlias( CRMStatusCode.UNUSED.getStatusCode() );
            }
            masterCrf.setLastModifiedBy( crmPaymentDetailsPojo.getLastModifiedBy() );
            masterCrf.setLastModifiedTime( Calendar.getInstance().getTime() );
            inSession.merge( masterCrf );
        }
    }

    public static int getPostpaidBillDate()
    {
        Calendar cal = Calendar.getInstance();
        int day = cal.get( Calendar.DAY_OF_MONTH );
        if ( day >= 5 && day <= 19 )
            day = 5;
        else
            day = 20;
        return day;
    }
}
