package com.np.tele.crm.finance.businessmgr;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.ext.pojos.AtomPgwPojo;
import com.np.tele.crm.ext.pojos.HDFCPgwPojo;
import com.np.tele.crm.ext.pojos.TechProcessPgwPojo;
import com.np.tele.crm.finance.dao.IFinanceManagerDao;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.masterdata.service.IMasterDataService;
import com.np.tele.crm.paymentGateway.dao.IPaymentGatewayDao;
import com.np.tele.crm.pojos.CrmCmsFilePojo;
import com.np.tele.crm.pojos.CrmCmsPaymentPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.pojos.CrmPodDetailsPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmUpfrontPaymentPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.PdfUtils;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.paymentGateway.ATOM;
import com.np.tele.paymentGateway.HDFC;
import com.np.tele.paymentGateway.TPSL;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class CRMFinanceBusinessImpl
    implements ICRMFinanceBusiness
{
    private static final Logger LOGGER            = Logger.getLogger( CRMFinanceBusinessImpl.class );
    private IFinanceManagerDao  financeManagerDao = null;
    private IPaymentGatewayDao  pgwDao            = null;
    private HDFC                hdfcPg            = null;
    private TPSL                tpslPg            = null;
    private ATOM                atomPg            = null;

    public IFinanceManagerDao getFinanceManagerDao()
    {
        return financeManagerDao;
    }

    public void setFinanceManagerDao( IFinanceManagerDao inFinanceManagerDao )
    {
        financeManagerDao = inFinanceManagerDao;
    }

    public IPaymentGatewayDao getPgwDao()
    {
        return pgwDao;
    }

    public void setPgwDao( IPaymentGatewayDao inPgwDao )
    {
        pgwDao = inPgwDao;
    }

    public HDFC getHdfcPg()
    {
        return hdfcPg;
    }

    public void setHdfcPg( HDFC inHdfcPg )
    {
        hdfcPg = inHdfcPg;
    }

    public TPSL getTpslPg()
    {
        return tpslPg;
    }

    public void setTpslPg( TPSL inTpslPg )
    {
        tpslPg = inTpslPg;
    }

    public ATOM getAtomPg()
    {
        return atomPg;
    }

    public void setAtomPg( ATOM inAtomPg )
    {
        atomPg = inAtomPg;
    }

    @Override
    public CrmFinanceDto trackPayment( final String inServiceParam,
                                       final String inCrmParam,
                                       final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        if ( StringUtils.isNotBlank( inServiceParam ) && StringUtils.isNotBlank( inCrmParam )
                && StringUtils.isValidObj( inCrmFinanceDto ) )
        {
            if ( StringUtils.equals( ServiceParameter.TRACK.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackPayments( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.REVERSAL.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackReversePayment( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.CMSFILE.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackCMSFiles( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.CMSRECORDS.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackCMSRecords( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.UPFRONT_PAYMENT.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackUPFrontPayments( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.UPFRONT_POPUP_DETAIL.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = getUpfrontPopUpDetails( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.REFUND.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackRefundPayments( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CRMParameter.POD_FILE_UPLOAD.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = trackPODRecords( inCrmFinanceDto );
                }
                else if ( StringUtils.equals( CrmPaymentDetailsPojo.class.getSimpleName(), inCrmParam ) )
                {
                    crmFinanceDto = paymentListByTransId( inCrmFinanceDto );
                }
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto paymentListByTransId( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = getFinanceManagerDao().paymentListByTransId( inCrmFinanceDto );
        LOGGER.info( "Status Code::" + inCrmFinanceDto.getStatusCode() );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto postPayment( final String inServiceParam,
                                      final String inCrmParam,
                                      final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        if ( StringUtils.isNotBlank( inServiceParam ) && StringUtils.isNotBlank( inCrmParam )
                && StringUtils.isValidObj( inCrmFinanceDto ) )
        {
            if ( StringUtils.equals( ServiceParameter.POST.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojo() ) )
                    {
                        crmFinanceDto = postSinglePayment( inCrmFinanceDto );
                    }
                    //                    else if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojos() ) )
                    //                    {
                    //                        crmFinanceDto = postMultiplePayments( inCrmFinanceDto );
                    //                    }
                }
                else if ( StringUtils.equals( CRMParameter.REVERSAL.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojo() )
                            && StringUtils.isValidObj( inCrmFinanceDto.getCustomerDetailsPojo() ) )
                    {
                        crmFinanceDto = reversePayment( inCrmFinanceDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.CMSFILE.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getCmsFile() ) )
                    {
                        crmFinanceDto = postCMSFile( inCrmFinanceDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.CMSRECORDS.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getCmsPayments() ) )
                    {
                        crmFinanceDto = postCMSRecords( inCrmFinanceDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.UPFRONT_PAYMENT.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmUpfrontPaymentPojos() ) )
                    {
                        crmFinanceDto = postUpfrontPayments( inCrmFinanceDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.REFUND.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto ) )
                    {
                        crmFinanceDto = postRefundPayment( inCrmFinanceDto );
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.UPDATE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
                {
                    LOGGER.info( "info::: in UPDATE PAYMENT OUT:::" );
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojos() ) )
                    {
                        crmFinanceDto = updatePaymentsStatus( inCrmFinanceDto );
                    }
                    else if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojo() ) )
                    {
                        crmFinanceDto = updatePayments( inCrmFinanceDto, false );
                    }
                }
                if ( StringUtils.equals( CRMParameter.CMSRECORDS.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = updateCMSRecords( inCrmFinanceDto );
                }
                if ( StringUtils.equals( CRMParameter.REFUND.getParameter(), inCrmParam ) )
                {
                    crmFinanceDto = updateRefundChequeStatus( inCrmFinanceDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.PROCESS.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getCmsFile() ) )
                    {
                        crmFinanceDto = processCMSPayments( inCrmFinanceDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.POD_FILE_UPLOAD.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmPodDetailsPojos() ) )
                    {
                        crmFinanceDto = processPODData( inCrmFinanceDto );
                    }
                }
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto processPODData( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        boolean isValid = false;
        List<CrmPodDetailsPojo> crmPodDetailsRejectedPojos = new ArrayList<CrmPodDetailsPojo>();
        List<CrmPodDetailsPojo> crmPodDetailsSuccessPojos = new ArrayList<CrmPodDetailsPojo>();
        for ( CrmPodDetailsPojo crmPodDetailsPojo : inCrmFinanceDto.getCrmPodDetailsPojos() )
        {
            isValid = ValidationPojoUtil.validatePojo( crmPodDetailsPojo,
                                                       ICRMValidationCriteriaUtil.POD_VALIATE_CRITERIA ) == null;
            if ( !isValid )
            {
                crmPodDetailsPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                LOGGER.info( "Mandatory Details Are Not Present in crmPodDetailsPojo"
                        + crmPodDetailsPojo.getCustomerId() );
                crmPodDetailsPojo.setProcessFailureReason( "Mandatory Details are not present in request" );
                crmPodDetailsRejectedPojos.add( crmPodDetailsPojo );
            }
            else
            {
                crmPodDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmPodDetailsSuccessPojos.add( crmPodDetailsPojo );
            }
        }
        inCrmFinanceDto = getFinanceManagerDao().savePODData( crmPodDetailsSuccessPojos, crmPodDetailsRejectedPojos );
        return inCrmFinanceDto;
    }

    @Override
    public CrmFinanceDto pgwOperations( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        LOGGER.info( "in pgwOperations" );
        CrmFinanceDto crmFinanceDto = invalidRequest();
        if ( StringUtils.isNotBlank( inServiceParam ) && StringUtils.isNotBlank( inCrmParam )
                && StringUtils.isValidObj( inCrmFinanceDto ) )
        {
            if ( StringUtils.equals( ServiceParameter.PROCESS.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
            {
                LOGGER.info( "PROCESS :::: PAYMENT" );
                if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmPgwTransactionsPojo() ) )
                {
                    CRMServiceCode serviceCode = CRMServiceCode.CRM997;
                    String errorCode = ValidationPojoUtil
                            .validatePojo( inCrmFinanceDto.getCrmPgwTransactionsPojo(),
                                           ICRMValidationCriteriaUtil.SELFCARE_QUICKPAY_INIT );
                    if ( StringUtils.isNotBlank( errorCode ) )
                    {
                        inCrmFinanceDto.setStatusCode( errorCode );
                        inCrmFinanceDto.setStatusDesc( CRMServiceCode.valueOf( errorCode ).getStatusDesc() );
                        return inCrmFinanceDto;
                    }
                    inCrmFinanceDto.getCrmPgwTransactionsPojo()
                            .setInitiationDatetime( Calendar.getInstance().getTime() );
                    inCrmFinanceDto.getCrmPgwTransactionsPojo().setStatus( CRMStatusCode.ATTEMPTED.getStatusCode() );
                    serviceCode = getPgwDao().generateTrackId( inCrmFinanceDto );
                    inCrmFinanceDto.setStatusCode( serviceCode.getStatusCode() );
                    inCrmFinanceDto.setStatusDesc( serviceCode.getStatusDesc() );
                    return inCrmFinanceDto;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.POST.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMParameter.PAYMENT.getParameter(), inCrmParam ) )
            {
                LOGGER.info( "POST :::: PAYMENT" );
                if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmPgwTransactionsPojo() )
                        && StringUtils.isNotBlank( inCrmFinanceDto.getCrmPgwTransactionsPojo().getPgwTrackId() ) )
                {
                    CRMServiceCode serviceCode = CRMServiceCode.CRM997;
                    //                    String errorCode = ValidationPojoUtil
                    //                            .validatePojo( inCrmFinanceDto.getCrmPgwTransactionsPojo(),
                    //                                           ICRMValidationCriteriaUtil.SELFCARE_QUICKPAY_POST );
                    CrmRcaReasonPojo crmRcaReason = getConfiguredGateway( inCrmFinanceDto, null );
                    if ( StringUtils.isValidObj( crmRcaReason )
                            && StringUtils.equals( crmRcaReason.getCategoryValue(), "HDFC" ) )
                    {
                        LOGGER.info( "selected gateway is:: " + crmRcaReason.getCategoryValue() );
                        serviceCode = postPaymentHDFC( inCrmFinanceDto );
                    }
                    else if ( StringUtils.isValidObj( crmRcaReason )
                            && StringUtils.equals( crmRcaReason.getCategoryValue(), "TP" ) )
                    {
                        LOGGER.info( "selected gateway is:: " + crmRcaReason.getCategoryValue() );
                        serviceCode = postPaymentTPSL( inCrmFinanceDto );
                    }
                    else if ( StringUtils.isValidObj( crmRcaReason )
                            && StringUtils.equals( crmRcaReason.getCategoryValue(), "ATOM" ) )
                    {
                        LOGGER.info( "selected gateway is:: " + crmRcaReason.getCategoryValue() );
                        serviceCode = postPaymentATOM( inCrmFinanceDto );
                    }
                    else
                    {
                        serviceCode = CRMServiceCode.CRM903;
                    }
                    inCrmFinanceDto.setStatusCode( serviceCode.getStatusCode() );
                    inCrmFinanceDto.setStatusDesc( serviceCode.getStatusDesc() );
                    return inCrmFinanceDto;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VALIDATE.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmPgwTransactionsPojo.class.getSimpleName(), inCrmParam ) )
            {
                LOGGER.info( "VALIDATE :::: " + inCrmParam );
                if ( StringUtils.isValidObj( inCrmFinanceDto.getCrmPgwTransactionsPojo() ) )
                {
                    CRMServiceCode serviceCode = CRMServiceCode.CRM997;
                    CrmRcaReasonPojo crmRcaReason = null;
                    if ( StringUtils.isNotBlank( inCrmFinanceDto.getCrmPgwTransactionsPojo().getPaymentOption() ) )
                    {
                        LOGGER.info( "found payment option:: "
                                + inCrmFinanceDto.getCrmPgwTransactionsPojo().getPaymentOption() );
                        crmRcaReason = getConfiguredGateway( inCrmFinanceDto, null );
                    }
                    else
                    {
                        LOGGER.info( "using udf4:: " + inCrmFinanceDto.getCrmPgwTransactionsPojo().getUdf4() );
                        crmRcaReason = getConfiguredGateway( inCrmFinanceDto, inCrmFinanceDto
                                .getCrmPgwTransactionsPojo().getUdf4() );
                    }
                    if ( StringUtils.isValidObj( crmRcaReason )
                            && StringUtils.equals( crmRcaReason.getCategoryValue(), "HDFC" ) )
                    {
                        LOGGER.info( "gateway is :::: " + crmRcaReason.getCategoryValue() );
                        serviceCode = validatePaymentHDFC( inCrmFinanceDto );
                    }
                    else if ( StringUtils.isValidObj( crmRcaReason )
                            && StringUtils.equals( crmRcaReason.getCategoryValue(), "TP" ) )
                    {
                        LOGGER.info( "gateway is :::: " + crmRcaReason.getCategoryValue() );
                        serviceCode = validatePaymentTPSL( inCrmFinanceDto );
                    }
                    inCrmFinanceDto.setStatusCode( serviceCode.getStatusCode() );
                    inCrmFinanceDto.setStatusDesc( serviceCode.getStatusDesc() );
                    return inCrmFinanceDto;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.SAVE.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmPgwTransactionsPojo.class.getSimpleName(), inCrmParam ) )
            {
                LOGGER.info( "SAVE :::: " + inCrmParam );
                CrmPgwTransactionsPojo crmPgwTransactionsPojo = inCrmFinanceDto.getCrmPgwTransactionsPojo();
                if ( StringUtils.isValidObj( crmPgwTransactionsPojo )
                        && StringUtils.isNotBlank( crmPgwTransactionsPojo.getPgwTrackId() ) )
                {
                    CRMServiceCode serviceCode = CRMServiceCode.CRM997;
                    CrmPgwTransactionsPojo pgwTransactionsPojoDb = CRMServiceUtils
                            .getDBValues( CrmPgwTransactionsPojo.class, "pgwTrackId",
                                          crmPgwTransactionsPojo.getPgwTrackId() );
                    if ( StringUtils.isValidObj( pgwTransactionsPojoDb ) && pgwTransactionsPojoDb.getRecordId() > 0 )
                    {
                        crmPgwTransactionsPojo.setRecordId( pgwTransactionsPojoDb.getRecordId() );
                        crmPgwTransactionsPojo.setCustomerId( pgwTransactionsPojoDb.getCustomerId() );
                        crmPgwTransactionsPojo.setPgwName( pgwTransactionsPojoDb.getPgwName() );
                        crmPgwTransactionsPojo.setPaymentOption( pgwTransactionsPojoDb.getPaymentOption() );
                        crmPgwTransactionsPojo.setPgwPaymentPage( pgwTransactionsPojoDb.getPgwPaymentPage() );
                        crmPgwTransactionsPojo.setPgwPaymentId( pgwTransactionsPojoDb.getPgwPaymentId() );
                        crmPgwTransactionsPojo.setAmount( pgwTransactionsPojoDb.getAmount() );
                        crmPgwTransactionsPojo.setPgwAuthDatetime( pgwTransactionsPojoDb.getPgwAuthDatetime() );
                        crmPgwTransactionsPojo.setPgwResponseDatetime( Calendar.getInstance().getTime() );
                        crmPgwTransactionsPojo.setBillingPaymentStatus( crmPgwTransactionsPojo
                                .getBillingPaymentStatus() );
                        inCrmFinanceDto.setCrmPgwTransactionsPojo( crmPgwTransactionsPojo );
                        serviceCode = getPgwDao().saveTransactionDetails( inCrmFinanceDto );
                        if ( StringUtils.equals( CRMStatusCode.SUCCESS.getStatusCode(),
                                                 crmPgwTransactionsPojo.getStatus() ) )
                        {
                            CrmCustomerDetailsPojo customerDetailsPojo = CRMServiceUtils
                                    .getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                  crmPgwTransactionsPojo.getCustomerId() );
                            long paymentId = processBillPayment( crmPgwTransactionsPojo, customerDetailsPojo );
                            LOGGER.info( "generated custPayment id:: " + paymentId );
                            if ( paymentId > 0 )
                            {
                                // update billing status flag to payment posted in pgwtransaction pojo
                                inCrmFinanceDto.getCrmPgwTransactionsPojo()
                                        .setBillingPaymentStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                                getPgwDao().saveTransactionDetails( inCrmFinanceDto );
                            }
                            // generate PDF and store it on temp location
                            String pdfPath = generateBillPaymentPDF( crmPgwTransactionsPojo, customerDetailsPojo );
                            List<String> attachments = new ArrayList<String>();
                            attachments.add( pdfPath );
                            LOGGER.info( "pdf generated. path:::: " + pdfPath );
                            CRMServiceUtils.sendAlerts( CRMEvents.BILLPAYMENT_SUCCESS, CRMRequestType.PGW_TRACK_ID,
                                                        crmPgwTransactionsPojo.getPgwTrackId(), attachments );
                        }
                        else if ( StringUtils.equals( CRMStatusCode.FAILURE.getStatusCode(),
                                                      crmPgwTransactionsPojo.getStatus() ) )
                        {
                            LOGGER.info( "sending failure mail" );
                            CRMServiceUtils.sendAlerts( CRMEvents.BILLPAYMENT_FAILURE, CRMRequestType.PGW_TRACK_ID,
                                                        crmPgwTransactionsPojo.getPgwTrackId(), null );
                        }
                    }
                    inCrmFinanceDto.setStatusCode( serviceCode.getStatusCode() );
                    inCrmFinanceDto.setStatusDesc( serviceCode.getStatusDesc() );
                    return inCrmFinanceDto;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam )
                    && StringUtils.equals( CrmPgwTransactionsPojo.class.getSimpleName(), inCrmParam ) )
            {
                LOGGER.info( "VIEW :::: " + inCrmParam );
                CrmPgwTransactionsPojo crmPgwTransactionsPojo = inCrmFinanceDto.getCrmPgwTransactionsPojo();
                if ( StringUtils.isValidObj( crmPgwTransactionsPojo )
                        && StringUtils.isNotBlank( crmPgwTransactionsPojo.getPgwTrackId() ) )
                {
                    crmPgwTransactionsPojo = CRMServiceUtils.getDBValues( CrmPgwTransactionsPojo.class, "pgwTrackId",
                                                                          crmPgwTransactionsPojo.getPgwTrackId() );
                    inCrmFinanceDto.setCrmPgwTransactionsPojo( crmPgwTransactionsPojo );
                    inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                    return inCrmFinanceDto;
                }
            }
        }
        return crmFinanceDto;
    }

    /**
     * Generate PDF of the Payment Gateway Transaction
     * @param inCrmPgwTransactionsPojo
     * @param inCustomerDetailsPojo
     * @return Path of the generated file.
     */
    private String generateBillPaymentPDF( CrmPgwTransactionsPojo inCrmPgwTransactionsPojo,
                                           CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "in generateBillPaymentPDF" );
        String[] lables = new String[]
        { "Receipt No.", "Customer Name", "Customer ID", "Transaction Refernce Number", "Payment Date and Time",
                "Amount (INR)" };
        String[] lableValue = new String[]
        {
                inCrmPgwTransactionsPojo.getPgwTrackId(),
                inCustomerDetailsPojo.getCustFname() + " "
                        + StringUtils.trimToEmpty( inCustomerDetailsPojo.getCustLname() ),
                inCustomerDetailsPojo.getCustomerId(), inCrmPgwTransactionsPojo.getPgwTransactionId(),
                IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( inCrmPgwTransactionsPojo.getPgwResponseDatetime() ),
                inCrmPgwTransactionsPojo.getAmount() + "" };
        String iconPath = "http://timbl.co.in/images/TIMBL_Logo_W-168px_X_H-67_px.png";//getServlet().getServletContext().getRealPath( IAppConstants.NEXTRA_ICON_PATH );
        // PDF header
        File pdf = new File( "PaymentReceipt_" + inCustomerDetailsPojo.getCustomerId() + "_"
                + inCrmPgwTransactionsPojo.getPgwTransactionId() + ".pdf" );
        try
        {
            FileOutputStream pdfFos = new FileOutputStream( pdf );
            PdfUtils.generatePaymentReciept( lables, lableValue, iconPath, pdfFos );
            pdfFos.flush();
            pdfFos.close();
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        inCrmPgwTransactionsPojo.setPaymentReciept( pdf.getAbsolutePath() );
        LOGGER.info( "pdf generated:: " + inCrmPgwTransactionsPojo.getPaymentReciept() );
        return pdf.getAbsolutePath();
    }

    /**
     * Insert Payment Gateway Transaction details in CRM_CUST_PAYMENT_DETAILS
     * @param inCrmPgwTransactionsPojo
     * @param inCustomerDetailsPojo
     * @return Generated <b>recordId</b> <i>CRM_CUST_PAYMENT_DETAILS</i>.
     */
    private long processBillPayment( CrmPgwTransactionsPojo inCrmPgwTransactionsPojo,
                                     CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "in processBillPayment" );
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmPaymentDetailsPojo crmPaymentDetail = new CrmPaymentDetailsPojo();
        crmPaymentDetail.setCustomerRecordId( inCustomerDetailsPojo.getRecordId() );
        crmPaymentDetail.setCustomerId( inCustomerDetailsPojo.getCustomerId() );
        crmPaymentDetail.setPaymentMode( CRMDisplayListConstants.ONLINE_PAYMENT.getCode() );
        crmPaymentDetail.setPaymentChannel( inCrmPgwTransactionsPojo.getPgwName() );
        crmPaymentDetail.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
        crmPaymentDetail.setPaymentDate( DateUtils.getCurrentDate() );
        crmPaymentDetail.setTransactionId( inCrmPgwTransactionsPojo.getPgwTrackId() );
        crmPaymentDetail
                .setBankTransId( StringUtils.isValidObj( inCrmPgwTransactionsPojo.getPgwPaymentId() )
                                                                                                     ? inCrmPgwTransactionsPojo
                                                                                                             .getPgwPaymentId()
                                                                                                     : inCrmPgwTransactionsPojo
                                                                                                             .getPgwTransactionId() );
        crmPaymentDetail.setPgwTransId( inCrmPgwTransactionsPojo.getPgwTransactionId() );
        crmPaymentDetail.setAmount( inCrmPgwTransactionsPojo.getAmount() );
        crmPaymentDetail.setCreatedBy( inCustomerDetailsPojo.getCustomerId() );
        crmFinanceDto.setUserId( inCustomerDetailsPojo.getCustomerId() );
        crmPaymentDetail.setInstallationStatus( CRMDisplayListConstants.POSTINSTALLATION.getCode() );
        crmPaymentDetail.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
        crmPaymentDetail.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetail );
        crmFinanceDto = postSinglePayment( crmFinanceDto );
        if ( !StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
        {
            // To handle failure case
            LOGGER.info( "unable to save custPayment :::: " + crmFinanceDto.getStatusCode() + " :: "
                    + crmFinanceDto.getStatusDesc() );
            return 0;
        }
        return crmFinanceDto.getPaymentDetailsPojo().getPaymentId();
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @param inPaymentOption CC (Credit Card) / DC (Debit Card) / IB (Internet Banking). 
     * <b>null</b> for using default value set in CrmPgwTransactionsPojo > paymentOption
     * @return 
     */
    private CrmRcaReasonPojo getConfiguredGateway( CrmFinanceDto inCrmFinanceDto, String inPaymentOption )
    {
        CrmRcaReasonPojo crmRcaReason = null;
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "category", CRMRCAReason.FINANCE.getStatusCode() );
        criteriaMap.put( "subCategory", CRMParameter.PAYMENT_GATEWAY.getParameter() );
        if ( StringUtils.isValidObj( inPaymentOption ) )
        {
            criteriaMap.put( "subSubCategory", inPaymentOption );
        }
        else
        {
            criteriaMap.put( "subSubCategory", inCrmFinanceDto.getCrmPgwTransactionsPojo().getPaymentOption() );
        }
        criteriaMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
        crmRcaReason = CRMServiceUtils.getDBValue( CrmRcaReasonPojo.class, criteriaMap, null, true );
        return crmRcaReason;
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @return Validation status of gateway hash.
     * @throws SOAPException
     */
    private CRMServiceCode validatePaymentHDFC( CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CRMServiceCode serviceCode;
        ConfigDto configDto = getPgwConfig( HDFCPgwPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( configDto ) && StringUtils.isValidObj( configDto.getHdfcPgwPojo() ) )
        {
            HDFCPgwPojo hdfcPgwPojo = configDto.getHdfcPgwPojo();
            serviceCode = getHdfcPg().validateHash( inCrmFinanceDto.getCrmPgwTransactionsPojo(), hdfcPgwPojo );
        }
        else
        {
            serviceCode = CRMServiceCode.CRM903;
        }
        return serviceCode;
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @return Validation status of gateway Encrypted Response
     * @throws SOAPException 
     */
    private CRMServiceCode validatePaymentTPSL( CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CRMServiceCode serviceCode;
        ConfigDto configDto = getPgwConfig( TechProcessPgwPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( configDto ) && StringUtils.isValidObj( configDto.getTechProcessPgwPojo() ) )
        {
            TechProcessPgwPojo tpPgwPojo = configDto.getTechProcessPgwPojo();
            serviceCode = getTpslPg()
                    .validateEncryptedResponse( inCrmFinanceDto.getCrmPgwTransactionsPojo(), tpPgwPojo );
        }
        else
        {
            serviceCode = CRMServiceCode.CRM903;
        }
        return serviceCode;
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @return Redirect URL for HDFC.
     * @throws SOAPException
     */
    private CRMServiceCode postPaymentHDFC( CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CRMServiceCode serviceCode;
        ConfigDto configDto = getPgwConfig( HDFCPgwPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( configDto ) && StringUtils.isValidObj( configDto.getHdfcPgwPojo() ) )
        {
            HDFCPgwPojo hdfcPgwPojo = configDto.getHdfcPgwPojo();
            inCrmFinanceDto.getCrmPgwTransactionsPojo().setPgwName( "HDFC" );
            inCrmFinanceDto.getCrmPgwTransactionsPojo()
                    .setAmount( CRMServiceUtils.getDBValues( CrmPgwTransactionsPojo.class,
                                                             "pgwTrackId",
                                                             inCrmFinanceDto.getCrmPgwTransactionsPojo()
                                                                     .getPgwTrackId() ).getAmount() );
            serviceCode = getHdfcPg().getRedirectURL( inCrmFinanceDto.getCrmPgwTransactionsPojo(), hdfcPgwPojo );
            if ( StringUtils.equals( serviceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                getPgwDao().updatePgwAuth( inCrmFinanceDto );
                // Send Alert for Payment Initiaition
                CRMServiceUtils.sendAlerts( CRMEvents.BILLPAYMENT_ATTEMPT, CRMRequestType.PGW_TRACK_ID, inCrmFinanceDto
                        .getCrmPgwTransactionsPojo().getPgwTrackId(), null );
            }
            else if ( StringUtils.equals( CRMServiceCode.CRM901.getStatusCode(), serviceCode.getStatusCode() ) )
            {
                getPgwDao().saveTransactionDetails( inCrmFinanceDto );
            }
        }
        else
        {
            serviceCode = CRMServiceCode.CRM903;
        }
        return serviceCode;
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @return Redirect URL for Tech Process Service Ltd.
     * @throws SOAPException
     */
    private CRMServiceCode postPaymentTPSL( CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CRMServiceCode serviceCode;
        CrmPgwTransactionsPojo pgwTransactionsPojoDB = null;
        ConfigDto configDto = getPgwConfig( TechProcessPgwPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( configDto ) && StringUtils.isValidObj( configDto.getTechProcessPgwPojo() ) )
        {
            TechProcessPgwPojo tpPgwPojo = configDto.getTechProcessPgwPojo();
            pgwTransactionsPojoDB = CRMServiceUtils.getDBValues( CrmPgwTransactionsPojo.class, "pgwTrackId",
                                                                 inCrmFinanceDto.getCrmPgwTransactionsPojo()
                                                                         .getPgwTrackId() );
            if ( StringUtils.isValidObj( pgwTransactionsPojoDB ) )
            {
                inCrmFinanceDto.getCrmPgwTransactionsPojo().setRecordId( pgwTransactionsPojoDB.getRecordId() );
                inCrmFinanceDto.getCrmPgwTransactionsPojo().setPgwPaymentPage( pgwTransactionsPojoDB
                                                                                       .getPgwPaymentPage() );
                inCrmFinanceDto.getCrmPgwTransactionsPojo().setAmount( pgwTransactionsPojoDB.getAmount() );
                inCrmFinanceDto.getCrmPgwTransactionsPojo().setInitiationDatetime( pgwTransactionsPojoDB
                                                                                           .getInitiationDatetime() );
                inCrmFinanceDto.getCrmPgwTransactionsPojo().setPgwName( "TP" );
                inCrmFinanceDto.getCrmPgwTransactionsPojo()
                        .setBillingPaymentStatus( pgwTransactionsPojoDB.getBillingPaymentStatus() );
            }
            serviceCode = getTpslPg().getRedirectURL( inCrmFinanceDto.getCrmPgwTransactionsPojo(), tpPgwPojo );
            if ( StringUtils.equals( serviceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                getPgwDao().updatePgwAuth( inCrmFinanceDto );
                // Send Alert for Payment Initiaition
                CRMServiceUtils.sendAlerts( CRMEvents.BILLPAYMENT_ATTEMPT, CRMRequestType.PGW_TRACK_ID, inCrmFinanceDto
                        .getCrmPgwTransactionsPojo().getPgwTrackId(), null );
            }
            else if ( StringUtils.equals( CRMServiceCode.CRM901.getStatusCode(), serviceCode.getStatusCode() ) )
            {
                getPgwDao().saveTransactionDetails( inCrmFinanceDto );
            }
        }
        else
        {
            serviceCode = CRMServiceCode.CRM903;
        }
        return serviceCode;
    }

    /**
     * 
     * @param inCrmFinanceDto
     * @return Redirect URL for ATOM.
     * @throws SOAPException
     */
    private CRMServiceCode postPaymentATOM( CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        CRMServiceCode serviceCode;
        ConfigDto configDto = getPgwConfig( AtomPgwPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( configDto ) && StringUtils.isValidObj( configDto.getAtomPgwPojo() ) )
        {
            AtomPgwPojo atomPgwPojo = configDto.getAtomPgwPojo();
            inCrmFinanceDto.getCrmPgwTransactionsPojo().setPgwName( "ATOM" );
            inCrmFinanceDto.getCrmPgwTransactionsPojo()
                    .setAmount( CRMServiceUtils.getDBValues( CrmPgwTransactionsPojo.class,
                                                             "pgwTrackId",
                                                             inCrmFinanceDto.getCrmPgwTransactionsPojo()
                                                                     .getPgwTrackId() ).getAmount() );
            serviceCode = getAtomPg().getRedirectURL( inCrmFinanceDto.getCrmPgwTransactionsPojo(), atomPgwPojo );
            if ( StringUtils.equals( serviceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                getPgwDao().updatePgwAuth( inCrmFinanceDto );
                // Send Alert for Payment Initiaition
                CRMServiceUtils.sendAlerts( CRMEvents.BILLPAYMENT_ATTEMPT, CRMRequestType.PGW_TRACK_ID, inCrmFinanceDto
                        .getCrmPgwTransactionsPojo().getPgwTrackId(), null );
            }
            else if ( StringUtils.equals( CRMServiceCode.CRM901.getStatusCode(), serviceCode.getStatusCode() ) )
            {
                getPgwDao().saveTransactionDetails( inCrmFinanceDto );
            }
        }
        else
        {
            serviceCode = CRMServiceCode.CRM903;
        }
        return serviceCode;
    }

    /**
     * Get Payment Gateway Configuration
     * @param inPgw Payment Gateway class simpleName
     * @return Payment Gateway class instance in ConfigDto
     * @throws SOAPException
     */
    private ConfigDto getPgwConfig( String inPgw )
        throws SOAPException
    {
        ConfigDto configDto = CRMServicesProxy.getInstance()
                .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                .configOperations( ServiceParameter.LIST.getParameter(), inPgw, null );
        return configDto;
    }

    private CrmFinanceDto updatePaymentsStatus( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        List<CrmPaymentDetailsPojo> payments = inCrmFinanceDto.getPaymentDetailsPojos();
        boolean isValid = false;
        for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
        {
            isValid = ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
                                                       ICRMValidationCriteriaUtil.PAYMENT_UPDATE_CRITERIA ) == null;
            if ( isValid )
            {
                isValid = ValidationPojoUtil
                        .validateSinglePojoProperty( crmPaymentDetailsPojo,
                                                     ICRMValidationCriteriaUtil.PAYMENT_UPDATE_STATUS_CRITERIA );
                if ( isValid
                        && StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                               crmPaymentDetailsPojo.getRealzationStatus() ) )
                {
                    if ( StringUtils.isBlank( crmPaymentDetailsPojo.getPaymentMode() ) )
                    {
                        crmPaymentDetailsPojo.setPaymentMode( CRMDisplayListConstants.CHEQUE.getCode() );
                    }
                    isValid = ValidationPojoUtil
                            .validatePojo( crmPaymentDetailsPojo,
                                           ICRMValidationCriteriaUtil.PAYMENT_CHEQUE_BOUNCE_CRITERIA ) == null;
                }
            }
            if ( !isValid )
            {
                break;
            }
        }
        if ( isValid )
        {
            boolean partialSuccess = false;
            boolean success = false;
            for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
            {
                inCrmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
                crmFinanceDto = updatePayments( inCrmFinanceDto, isValid );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                {
                    success = true;
                }
                else if ( success && !partialSuccess )
                {
                    partialSuccess = true;
                }
            }
            if ( partialSuccess )
            {
                LOGGER.info( "Partial success while updating multiple payments." );
                crmFinanceDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmFinanceDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto updatePayments( CrmFinanceDto inCrmFinanceDto, boolean isValid )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        CrmPaymentDetailsPojo inCrmPaymentDetailsPojo = inCrmFinanceDto.getPaymentDetailsPojo();
        if ( !isValid )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmPaymentDetailsPojo,
                                                       ICRMValidationCriteriaUtil.PAYMENT_UPDATE_CRITERIA ) == null;
            if ( isValid )
            {
                isValid = ValidationPojoUtil
                        .validateSinglePojoProperty( inCrmPaymentDetailsPojo,
                                                     ICRMValidationCriteriaUtil.PAYMENT_UPDATE_STATUS_CRITERIA );
                if ( isValid
                        && StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                               inCrmPaymentDetailsPojo.getRealzationStatus() ) )
                {
                    if ( StringUtils.isBlank( inCrmPaymentDetailsPojo.getPaymentMode() ) )
                    {
                        inCrmPaymentDetailsPojo.setPaymentMode( CRMDisplayListConstants.CHEQUE.getCode() );
                    }
                    isValid = ValidationPojoUtil
                            .validatePojo( inCrmPaymentDetailsPojo,
                                           ICRMValidationCriteriaUtil.PAYMENT_CHEQUE_BOUNCE_CRITERIA ) == null;
                }
            }
        }
        if ( isValid )
        {
            CrmPaymentDetailsPojo crmPaymentDetail = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class,
                                                                                  inCrmPaymentDetailsPojo
                                                                                          .getPaymentId() );
            if ( StringUtils.isValidObj( crmPaymentDetail ) )
            {
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                String remarks = "";
                long eventId = 0;
                if ( StringUtils.isNotBlank( inCrmPaymentDetailsPojo.getCaseStatus() )
                        || StringUtils.isNotBlank( inCrmPaymentDetailsPojo.getPaymentStatus() ) )
                {
                    eventId = CRMServiceUtils.getEventId( CRMEvents.UPDATE_PAYMENT_STATUS.getEventName() );
                    if ( StringUtils.isNotBlank( inCrmPaymentDetailsPojo.getCaseStatus() ) )
                    {
                        if ( StringUtils.isNotBlank( crmPaymentDetail.getCaseStatus() ) )
                        {
                            oldValues.append( "CaseStatus=" ).append( crmPaymentDetail.getCaseStatus() )
                                    .append( IAppConstants.COMMA );
                        }
                        newValues.append( "CaseStatus=" ).append( inCrmPaymentDetailsPojo.getCaseStatus() )
                                .append( IAppConstants.COMMA );
                        remarks = "Update Case Status";
                        crmPaymentDetail.setCaseStatus( inCrmPaymentDetailsPojo.getCaseStatus() );
                    }
                    else if ( StringUtils.isNotBlank( inCrmPaymentDetailsPojo.getPaymentStatus() ) )
                    {
                        oldValues.append( "PaymentStatus=" ).append( crmPaymentDetail.getPaymentStatus() )
                                .append( IAppConstants.COMMA );
                        newValues.append( "PaymentStatus=" ).append( inCrmPaymentDetailsPojo.getPaymentStatus() )
                                .append( IAppConstants.COMMA );
                        remarks = "Update Payment Status";
                        crmPaymentDetail.setPaymentStatus( inCrmPaymentDetailsPojo.getPaymentStatus() );
                    }
                    crmPaymentDetail.setLastModifiedBy( inCrmPaymentDetailsPojo.getLastModifiedBy() );
                    crmPaymentDetail.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmPaymentDetail = CRMServiceUtils.mergeDBValues( crmPaymentDetail );
                    if ( StringUtils.isValidObj( crmPaymentDetail ) )
                    {
                        inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                        inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                    }
                }
                else if ( StringUtils.isNotBlank( inCrmPaymentDetailsPojo.getRealzationStatus() ) )
                {
                    eventId = CRMServiceUtils.getEventId( CRMEvents.UPDATE_REALISATION_STATUS.getEventName() );
                    oldValues.append( "RLStatus=" ).append( crmPaymentDetail.getRealzationStatus() )
                            .append( IAppConstants.COMMA );
                    newValues.append( "RLStatus=" ).append( inCrmPaymentDetailsPojo.getRealzationStatus() )
                            .append( IAppConstants.COMMA );
                    remarks = "Update Realization Status ";
                    crmPaymentDetail.setRealzationStatus( inCrmPaymentDetailsPojo.getRealzationStatus() );
                    if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                             inCrmPaymentDetailsPojo.getRealzationStatus() ) )
                    {
                        crmPaymentDetail.setBouncingDate( inCrmPaymentDetailsPojo.getBouncingDate() );
                        crmPaymentDetail.setBouncingReason( inCrmPaymentDetailsPojo.getBouncingReason() );
                    }
                    crmPaymentDetail.setLastModifiedBy( inCrmPaymentDetailsPojo.getLastModifiedBy() );
                    inCrmFinanceDto.setUserId( inCrmPaymentDetailsPojo.getLastModifiedBy() );
                    crmPaymentDetail.setLastModifiedTime( Calendar.getInstance().getTime() );
                    inCrmFinanceDto.setPaymentDetailsPojo( crmPaymentDetail );
                    inCrmFinanceDto = postSinglePayment( inCrmFinanceDto );
                }
                if ( StringUtils.equals( inCrmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    String mappingId = String.valueOf( inCrmPaymentDetailsPojo.getPaymentId() );
                    String createdBy = inCrmPaymentDetailsPojo.getLastModifiedBy();
                    CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy, remarks, eventId,
                                                    CRMRequestType.FINANCE.getRequestCode(),
                                                    inCrmFinanceDto.getClientIPAddress(),
                                                    inCrmFinanceDto.getServerIPAddress() );
                }
            }
        }
        return inCrmFinanceDto;
    }

    private CrmFinanceDto processCMSPayments( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getCmsFile(),
                                                           ICRMValidationCriteriaUtil.CMSFILE_PROCESS_CRITERIA ) == null;
        Set<String> customers = new TreeSet<String>();
        try
        {
            if ( isValid )
            {
                CrmCmsFilePojo cmsFilePojo = inCrmFinanceDto.getCmsFile();
                CrmCmsPaymentPojo cmsPaymentPojo = new CrmCmsPaymentPojo();
                if ( StringUtils.equals( cmsFilePojo.getCmsFileType(), CRMStatusCode.DEPOSIT.getStatusCode() ) )
                {
                    LOGGER.info( "Processing Deposit File Records" );
                    cmsPaymentPojo.setDepositFileId( cmsFilePojo.getCmsFileId() );
                    cmsPaymentPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                }
                else if ( StringUtils.equals( cmsFilePojo.getCmsFileType(), CRMStatusCode.CLEARANCE.getStatusCode() ) )
                {
                    LOGGER.info( "Processing Clearance File Records" );
                    cmsPaymentPojo.setClearanceFileId( cmsFilePojo.getCmsFileId() );
                    cmsPaymentPojo.setStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                }
                inCrmFinanceDto.setCmsPayment( cmsPaymentPojo );
                inCrmFinanceDto = getFinanceManagerDao().getCMSRecords( inCrmFinanceDto );
                List<CrmCmsPaymentPojo> cmsPaymentPojos = inCrmFinanceDto.getCmsPayments();
                if ( CommonValidator.isValidCollection( cmsPaymentPojos ) )
                {
                    LOGGER.info( "Retrived all CMS Payments for input file : " + cmsFilePojo.getCmsFileName() );
                    Iterator<CrmCmsPaymentPojo> cmsPaymentItr = cmsPaymentPojos.iterator();
                    while ( cmsPaymentItr.hasNext() )
                    {
                        cmsPaymentPojo = (CrmCmsPaymentPojo) cmsPaymentItr.next();
                        LOGGER.info( "Processing CMS Record Id : " + cmsPaymentPojo.getCmsId() );
                        crmFinanceDto = processCmsSinglePayment( crmFinanceDto, customers, cmsPaymentPojo );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            crmFinanceDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmFinanceDto.setStatusDesc( CRMServiceCode.CRM999.getStatusCode() );
            LOGGER.error( "Exception ", ex );
        }
        finally
        {
            if ( CommonValidator.isValidCollection( customers ) )
            {
                LOGGER.info( "Payments Successfuly processed for " + customers );
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto processCmsSinglePayment( CrmFinanceDto crmFinanceDto,
                                                   Set<String> customers,
                                                   CrmCmsPaymentPojo cmsPaymentPojo )
    {
        CrmCustomerDetailsPojo customerDetailsPojo = CRMServiceUtils
                .getDBValues( CrmCustomerDetailsPojo.class, "customerId", cmsPaymentPojo.getIe2() );
        if ( StringUtils.isValidObj( customerDetailsPojo ) )
        {
            CrmPaymentDetailsPojo crmPaymentDetail = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class, "cmsId",
                                                                                  cmsPaymentPojo.getCmsId() );
            if ( StringUtils.isValidObj( crmPaymentDetail ) )
            {
                if ( StringUtils.equals( cmsPaymentPojo.getDebitCreditFlag(), "D" ) )
                {
                    crmPaymentDetail.setRealzationStatus( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode() );
                    crmPaymentDetail.setBouncingDate( cmsPaymentPojo.getValueDate() );
                    crmPaymentDetail.setBouncingReason( cmsPaymentPojo.getReturnReason() );
                }
                else if ( StringUtils.equals( cmsPaymentPojo.getDebitCreditFlag(), "C" ) )
                {
                    crmPaymentDetail.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
                }
                crmPaymentDetail.setCustomerId( customerDetailsPojo.getCustomerId() );
                crmPaymentDetail.setLastModifiedBy( cmsPaymentPojo.getLastModifiedBy() );
                crmFinanceDto.setUserId( cmsPaymentPojo.getLastModifiedBy() );
                crmPaymentDetail.setLastModifiedTime( Calendar.getInstance().getTime() );
            }
            else
            {
                crmPaymentDetail = new CrmPaymentDetailsPojo();
                crmPaymentDetail.setCustomerRecordId( customerDetailsPojo.getRecordId() );
                crmPaymentDetail.setCustomerId( customerDetailsPojo.getCustomerId() );
                crmPaymentDetail.setPaymentMode( CRMDisplayListConstants.CHEQUE.getCode() );
                crmPaymentDetail.setPaymentChannel( CRMDisplayListConstants.CMS.getCode() );
                crmPaymentDetail.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
                crmPaymentDetail.setRealzationStatus( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode() );
                crmPaymentDetail.setCmsId( cmsPaymentPojo.getCmsId() );
                if ( StringUtils.isValidObj( cmsPaymentPojo.getDepositDate() ) )
                {
                    crmPaymentDetail.setPaymentDate( cmsPaymentPojo.getDepositDate() );
                }
                else
                {
                    crmPaymentDetail.setPaymentDate( Calendar.getInstance().getTime() );
                }
                crmPaymentDetail.setChequeDdNo( cmsPaymentPojo.getInstrumentNo() );
                crmPaymentDetail.setChequeDate( cmsPaymentPojo.getInstrumentDate() );
                crmPaymentDetail.setAmount( cmsPaymentPojo.getInstrumentAmount() );
                crmPaymentDetail.setBankName( cmsPaymentPojo.getDraweeBank() );
                crmPaymentDetail.setBankBranch( cmsPaymentPojo.getDraweeBranch() );
                crmPaymentDetail.setCreatedBy( cmsPaymentPojo.getCreatedBy() );
                crmFinanceDto.setUserId( cmsPaymentPojo.getCreatedBy() );
                cmsPaymentPojo.setCreatedTime( Calendar.getInstance().getTime() );
                if ( StringUtils.equals( cmsPaymentPojo.getDebitCreditFlag(), "D" ) )
                {
                    crmPaymentDetail.setRealzationStatus( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode() );
                    crmPaymentDetail.setBouncingDate( cmsPaymentPojo.getValueDate() );
                    crmPaymentDetail.setBouncingReason( cmsPaymentPojo.getReturnReason() );
                }
                else if ( StringUtils.equals( cmsPaymentPojo.getDebitCreditFlag(), "C" ) )
                {
                    crmPaymentDetail.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
                }
                crmPaymentDetail.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION.getCode() );
                if ( StringUtils.isValidObj( customerDetailsPojo.getActivationDate() ) )
                {
                    crmPaymentDetail.setInstallationStatus( CRMDisplayListConstants.POSTINSTALLATION.getCode() );
                }
                crmPaymentDetail.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
                crmPaymentDetail.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            }
            if ( StringUtils.isValidObj( crmPaymentDetail ) )
            {
                long paymentId = crmPaymentDetail.getPaymentId();
                crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetail );
                crmFinanceDto = postSinglePayment( crmFinanceDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                {
                    if ( paymentId > 0 )
                    {
                        cmsPaymentPojo.setStatus( crmFinanceDto.getPaymentDetailsPojo().getStatus() );
                        CRMServiceUtils.mergeDBValues( cmsPaymentPojo );
                    }
                    if ( StringUtils.isValidObj( customers ) )
                    {
                        customers.add( customerDetailsPojo.getCustomerId() );
                    }
                }
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto updateCMSRecords( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        List<CrmCmsPaymentPojo> cmsPayments = inCrmFinanceDto.getCmsPayments();
        boolean isValid = true;
        List<CrmCmsPaymentPojo> nonEmptyIE2Pojos = new ArrayList<CrmCmsPaymentPojo>();
        Set<String> uniqueCustomerIds = new HashSet<String>();
        if ( CommonValidator.isValidCollection( cmsPayments ) )
        {
            for ( CrmCmsPaymentPojo crmCmsPaymentPojo : cmsPayments )
            {
                if ( StringUtils.equals( CRMStatusCode.SUSPENCE.getStatusCode(), crmCmsPaymentPojo.getStatus() )
                        && StringUtils.isNotBlank( ValidationPojoUtil
                                .validatePojo( crmCmsPaymentPojo,
                                               ICRMValidationCriteriaUtil.CMSRECORDS_RECTIFY_SUSPENSE_CRITERIA ) ) )
                {
                    isValid = false;
                    break;
                }
                else if ( StringUtils.equals( CRMStatusCode.REJECTED.getStatusCode(), crmCmsPaymentPojo.getStatus() )
                        && StringUtils.isNotBlank( ValidationPojoUtil
                                .validatePojo( crmCmsPaymentPojo,
                                               ICRMValidationCriteriaUtil.CMSRECORDS_RECTIFY_REJECTED_CRITERIA ) ) )
                {
                    isValid = false;
                    break;
                }
                if ( isValid )
                {
                    if ( StringUtils.equals( CRMStatusCode.SUSPENCE.getStatusCode(), crmCmsPaymentPojo.getStatus() )
                            && StringUtils.isNotBlank( ValidationPojoUtil
                                    .validatePojo( crmCmsPaymentPojo,
                                                   ICRMValidationCriteriaUtil.CMSRECORDS_RECTIFY_REJECTED_CRITERIA ) ) )
                    {
                        crmCmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                    }
                    else
                    {
                        crmCmsPaymentPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                    }
                    if ( StringUtils.isEmpty( crmCmsPaymentPojo.getIe2() ) )
                    {
                        crmCmsPaymentPojo.setStatus( CRMStatusCode.SUSPENCE.getStatusCode() );
                    }
                    else if ( StringUtils.equals( CRMStatusCode.PENDING.getStatusCode(), crmCmsPaymentPojo.getStatus() ) )
                    {
                        nonEmptyIE2Pojos.add( crmCmsPaymentPojo );
                        uniqueCustomerIds.add( crmCmsPaymentPojo.getIe2() );
                    }
                }
            }
        }
        if ( !nonEmptyIE2Pojos.isEmpty() )
        {
            inCrmFinanceDto.getCmsPayments().removeAll( nonEmptyIE2Pojos );
            setCustomerIdDBStatus( nonEmptyIE2Pojos, uniqueCustomerIds );
            inCrmFinanceDto.getCmsPayments().addAll( nonEmptyIE2Pojos );
        }
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().manageCMSRecords( inCrmFinanceDto, true );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
            {
                for ( CrmCmsPaymentPojo cmsPaymentPojo : inCrmFinanceDto.getCmsPayments() )
                {
                    if ( StringUtils.equals( CRMStatusCode.PENDING.getStatusCode(), cmsPaymentPojo.getStatus() ) )
                    {
                        if ( cmsPaymentPojo.getDepositFileId() > 0 )
                        {
                            crmFinanceDto = processCmsSinglePayment( crmFinanceDto, null, cmsPaymentPojo );
                        }
                        else if ( cmsPaymentPojo.getClearanceFileId() > 0 )
                        {
                            setClearanceDBStatus( cmsPaymentPojo );
                            // Update
                            cmsPaymentPojo = CRMServiceUtils.mergeDBValues( cmsPaymentPojo );
                            if ( StringUtils.isValidObj( cmsPaymentPojo )
                                    && StringUtils.equals( CRMStatusCode.SUCCESS.getStatusCode(),
                                                           cmsPaymentPojo.getStatus() ) )
                            {
                                // process
                                crmFinanceDto = processCmsSinglePayment( crmFinanceDto, null, cmsPaymentPojo );
                            }
                        }
                    }
                }
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto postCMSRecords( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "------------------ Inside postCMSRecords ------------------------" );
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getCmsPayments().get( 0 ),
                                                           ICRMValidationCriteriaUtil.CMSRECORDS_POST_CRITERIA ) == null
                && ValidationPojoUtil
                        .validateSinglePojoProperty( inCrmFinanceDto.getCmsPayments().get( 0 ),
                                                     ICRMValidationCriteriaUtil.CMSFILE_POST_CRITERIA_FILEID );
        if ( isValid )
        {
            Map<String, Object[]> resultMap = null;
            List<CrmCmsPaymentPojo> nonEmptyIE2Pojos = null;
            Set<String> uniqueCustomerIds = null;
            MasterDataDto masterDataDto = new MasterDataDto();
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.CRM_BANK.getStatusCode() );
            masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
            IMasterDataService masterDataService;
            try
            {
                masterDataService = CRMServicesProxy.getInstance().getCRMMasterDataService( IGlobalConstants.REMOTE,
                                                                                            IGlobalConstants.APP );
                LOGGER.info( "Master Data Service Obj : " + masterDataService );
                if ( StringUtils.isValidObj( masterDataService ) )
                {
                    masterDataDto = masterDataService.masterOperations( ServiceParameter.LIST.getParameter(),
                                                                        CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                        masterDataDto );
                }
                LOGGER.info( "Bank list size  " + masterDataDto.getCrmRcaReasonsList().size() );
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Exception while fetching banks details:", ex );
            }
            if ( StringUtils.equals( inCrmFinanceDto.getCmsFile().getCmsFileType(),
                                     CRMStatusCode.DEPOSIT.getStatusCode() ) )
            {
                nonEmptyIE2Pojos = new ArrayList<CrmCmsPaymentPojo>();
                uniqueCustomerIds = new HashSet<String>();
                for ( CrmCmsPaymentPojo cmsPaymentPojo : inCrmFinanceDto.getCmsPayments() )
                {
                    resultMap = ValidationPojoUtil
                            .validateForm( cmsPaymentPojo,
                                           ICRMValidationCriteriaUtil.FORM_CMSRECORDS_POST_CRITERIA_DEPOSIT, false );
                    if ( StringUtils.isValidObj( resultMap ) && !resultMap.isEmpty() )
                    {
                        Set<String> keyString = resultMap.keySet();
                        String errStr = "";
                        for ( String key : keyString )
                        {
                            errStr = PropertyUtils.getCmsKeyValue( key );
                            break;
                        }
                        cmsPaymentPojo.setFaliureReason( errStr );
                        cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                    }
                    else
                    {
                        if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
                        {
                            crmRcaReasonPojo.setCategoryValue( StringUtils.upperCase( cmsPaymentPojo.getDraweeBank() ) );
                            int pojoIndex = masterDataDto.getCrmRcaReasonsList().indexOf( crmRcaReasonPojo );
                            if ( pojoIndex < 0 )
                            {
                                cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank()
                                        + IAppConstants.WHITE_SPACE
                                        + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                                cmsPaymentPojo.setDraweeBank( "" );
                                cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                            }
                            else
                            {
                                CrmRcaReasonPojo rcaPojo = masterDataDto.getCrmRcaReasonsList().get( pojoIndex );
                                cmsPaymentPojo.setDraweeBank( String.valueOf( rcaPojo.getCategoryId() ) );
                                if ( !StringUtils.equals( rcaPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                                {
                                    cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank()
                                            + IAppConstants.WHITE_SPACE
                                            + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                                    cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                                }
                                else if ( StringUtils.isEmpty( cmsPaymentPojo.getIe2() ) )
                                {
                                    cmsPaymentPojo.setStatus( CRMStatusCode.SUSPENCE.getStatusCode() );
                                }
                                else
                                {
                                    nonEmptyIE2Pojos.add( cmsPaymentPojo );
                                    uniqueCustomerIds.add( cmsPaymentPojo.getIe2() );
                                }
                            }
                        }
                        else
                        {
                            cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank() + IAppConstants.WHITE_SPACE
                                    + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                            cmsPaymentPojo.setDraweeBank( "" );
                            cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                        }
                    }
                }
                if ( !nonEmptyIE2Pojos.isEmpty() )
                {
                    inCrmFinanceDto.getCmsPayments().removeAll( nonEmptyIE2Pojos );
                    setCustomerIdDBStatus( nonEmptyIE2Pojos, uniqueCustomerIds );
                    inCrmFinanceDto.getCmsPayments().addAll( nonEmptyIE2Pojos );
                }
            }
            else if ( StringUtils.equals( inCrmFinanceDto.getCmsFile().getCmsFileType(),
                                          CRMStatusCode.CLEARANCE.getStatusCode() ) )
            {
                for ( CrmCmsPaymentPojo cmsPaymentPojo : inCrmFinanceDto.getCmsPayments() )
                {
                    resultMap = ValidationPojoUtil
                            .validateForm( cmsPaymentPojo,
                                           ICRMValidationCriteriaUtil.FORM_CMSRECORDS_POST_CRITERIA_CLEARANCE, false );
                    if ( StringUtils.isValidObj( resultMap ) && !resultMap.isEmpty() )
                    {
                        Set<String> keyString = resultMap.keySet();
                        String errStr = "";
                        for ( String key : keyString )
                        {
                            errStr = PropertyUtils.getCmsKeyValue( key );
                            break;
                        }
                        cmsPaymentPojo.setFaliureReason( errStr );
                        cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                    }
                    else
                    {
                        if ( StringUtils.equals( cmsPaymentPojo.getDebitCreditFlag(), "D" )
                                && StringUtils.isEmpty( cmsPaymentPojo.getReturnReason() ) )
                        {
                            cmsPaymentPojo.setFaliureReason( PropertyUtils
                                    .getCmsKeyValue( "error.return.remarks.missing" ) );
                            cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                        }
                        else
                        {
                            if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
                            {
                                crmRcaReasonPojo.setCategoryValue( cmsPaymentPojo.getDraweeBank() );
                                int pojoIndex = masterDataDto.getCrmRcaReasonsList().indexOf( crmRcaReasonPojo );
                                if ( pojoIndex < 0 )
                                {
                                    cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank()
                                            + IAppConstants.WHITE_SPACE
                                            + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                                    cmsPaymentPojo.setDraweeBank( "" );
                                    cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                                }
                                else
                                {
                                    CrmRcaReasonPojo rcaPojo = masterDataDto.getCrmRcaReasonsList().get( pojoIndex );
                                    cmsPaymentPojo.setDraweeBank( String.valueOf( rcaPojo.getCategoryId() ) );
                                    if ( !StringUtils
                                            .equals( rcaPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                                    {
                                        cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank()
                                                + IAppConstants.WHITE_SPACE
                                                + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                                        cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                                    }
                                    else
                                    {
                                        setClearanceDBStatus( cmsPaymentPojo );
                                    }
                                }
                            }
                            else
                            {
                                cmsPaymentPojo.setFaliureReason( cmsPaymentPojo.getDraweeBank()
                                        + IAppConstants.WHITE_SPACE
                                        + PropertyUtils.getCmsKeyValue( "error.invalid.bank.name" ) );
                                cmsPaymentPojo.setDraweeBank( "" );
                                cmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                            }
                        }
                    }
                }
            }
            else
            {
                return crmFinanceDto;
            }
            crmFinanceDto = getFinanceManagerDao().manageCMSRecords( inCrmFinanceDto, false );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto postUpfrontPayments( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        boolean isValid = false;
        for ( CrmUpfrontPaymentPojo crmUpfrontPaymentPojo : inCrmFinanceDto.getCrmUpfrontPaymentPojos() )
        {
            isValid = ValidationPojoUtil.validatePojo( crmUpfrontPaymentPojo,
                                                       ICRMValidationCriteriaUtil.UPFRONT_POST_CRITERIA ) == null;
            if ( !isValid )
            {
                LOGGER.info( "Mandatory Details Are Not Present in Crm UPfront Payment Pojo" );
                break;
            }
        }
        if ( isValid )
        {
            inCrmFinanceDto = getFinanceManagerDao().postUpfrontPayments( inCrmFinanceDto );
        }
        return inCrmFinanceDto;
    }

    private CrmFinanceDto postCMSFile( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getCmsFile(),
                                                           ICRMValidationCriteriaUtil.CMSFILE_POST_CRITERIA ) == null;
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().manageCMSFile( inCrmFinanceDto.getCmsFile() );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto updateCMSFile( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getCmsFile(),
                                                           ICRMValidationCriteriaUtil.CMSFILE_UPDATE_CRITERIA ) == null;
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().manageCMSFile( inCrmFinanceDto.getCmsFile() );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto trackPayments( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = false;
        if ( StringUtils.isValidObj( inCrmFinanceDto.getFromDate() )
                && StringUtils.isValidObj( inCrmFinanceDto.getToDate() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto,
                                                       ICRMValidationCriteriaUtil.TRACK_PAYMENT_CRITERIA ) == null;
        }
        else if ( StringUtils.isValidObj( inCrmFinanceDto.getPaymentDetailsPojo() )
                && StringUtils.isNotBlank( inCrmFinanceDto.getPaymentDetailsPojo().getTransactionId() ) )
        {
            isValid = true;
        }
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().getPaymentHistory( inCrmFinanceDto );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto trackReversePayment( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto,
                                                           ICRMValidationCriteriaUtil.TRACK_PAYMENT_CRITERIA ) == null;
        if ( isValid )
        {
            isValid = ValidationPojoUtil
                    .validateSinglePojoProperty( inCrmFinanceDto.getCustomerDetailsPojo(),
                                                 ICRMValidationCriteriaUtil.CUSTOMER_SEARCH_CRITERIA );
            if ( isValid )
            {
                crmFinanceDto = getFinanceManagerDao().getPaymentHistory( inCrmFinanceDto );
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto trackCMSFiles( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto,
                                                           ICRMValidationCriteriaUtil.TRACK_PAYMENT_CRITERIA ) == null;
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().getCMSFiles( inCrmFinanceDto );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto trackCMSRecords( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto,
                                                           ICRMValidationCriteriaUtil.TRACK_PAYMENT_CRITERIA ) == null
                || ValidationPojoUtil.validateSinglePojoProperty( inCrmFinanceDto.getCmsPayment(),
                                                                  ICRMValidationCriteriaUtil.CMSRECORDS_TRACK_CRITERIA );
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().getCMSRecords( inCrmFinanceDto );
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto trackUPFrontPayments( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        return getFinanceManagerDao().getUpfrontPayments( inCrmFinanceDto );
    }

    private CrmFinanceDto reversePayment( final CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCrmFinanceDto.getCustomerDetailsPojo();
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getPaymentDetailsPojo(),
                                                           ICRMValidationCriteriaUtil.REVERSAL_PAYMENT_CRITERIA ) == null;
        if ( isValid )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getRemarks(),
                                                       ICRMValidationCriteriaUtil.REMARKS_POST_CRITERIA ) == null;
            if ( !isValid )
            {
                // Especially for Shifting Case Reversal
                if ( StringUtils.isValidObj( inCrmFinanceDto.getRemarks() )
                        && inCrmFinanceDto.getRemarks().getReasonId() == -1 )
                {
                    inCrmFinanceDto.getRemarks().setReasonId( 0L );
                    isValid = true;
                }
            }
        }
        if ( isValid & StringUtils.isBlank( crmCustomerDetailsPojo.getCrfId() )
                & StringUtils.isBlank( crmCustomerDetailsPojo.getCustomerId() ) & !inCrmFinanceDto.isReversalWOCrf() )
        {
            isValid = false;
        }
        if ( isValid )
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojo = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class,
                                                                                       inCrmFinanceDto
                                                                                               .getPaymentDetailsPojo()
                                                                                               .getPaymentId() );
            CrmCustomerDetailsPojo oldCustomerDetailsPojo = CRMServiceUtils
                    .getDBValues( CrmCustomerDetailsPojo.class, crmPaymentDetailsPojo.getCustomerRecordId() );
            if ( StringUtils.isValidObj( crmPaymentDetailsPojo ) && StringUtils.isValidObj( oldCustomerDetailsPojo ) )
            {
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                long eventId = CRMServiceUtils.getEventId( CRMEvents.POST_REVERSAL.getEventName() );
                String customerId = oldCustomerDetailsPojo.getCustomerId();
                oldValues.append( "CustomerId=" ).append( customerId ).append( IAppConstants.COMMA );
                if ( !inCrmFinanceDto.isReversalWOCrf() )
                {
                    newValues.append( "ReversalWithCrf=" ).append( inCrmFinanceDto.isReversalWOCrf() )
                            .append( IAppConstants.COMMA );
                    if ( ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCrfId() ) && StringUtils
                            .equals( crmCustomerDetailsPojo.getCrfId(), oldCustomerDetailsPojo.getCrfId() ) )
                            || ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCustomerId() ) && StringUtils
                                    .equals( crmCustomerDetailsPojo.getCustomerId(),
                                             oldCustomerDetailsPojo.getCustomerId() ) ) )
                    {
                        crmFinanceDto.setStatusCode( CRMServiceCode.CRM702.getStatusCode() );
                        crmFinanceDto.setStatusDesc( CRMServiceCode.CRM702.getStatusDesc() );
                        return crmFinanceDto;
                    }
                }
                else
                {
                    newValues.append( "ReversalWOCrf=" ).append( inCrmFinanceDto.isReversalWOCrf() )
                            .append( IAppConstants.COMMA );
                }
                crmPaymentDetailsPojo.setReversalReason( inCrmFinanceDto.getPaymentDetailsPojo().getReversalReason() );
                crmPaymentDetailsPojo.setSrId( inCrmFinanceDto.getPaymentDetailsPojo().getSrId() );
                crmPaymentDetailsPojo.setLastModifiedBy( inCrmFinanceDto.getUserId() );
                //SR Ticket Validation
                if ( StringUtils.isNotEmpty( crmPaymentDetailsPojo.getSrId() ) )
                {
                    Map<String, Object> criteriaMap = new HashMap<String, Object>();
                    criteriaMap.put( "srId", crmPaymentDetailsPojo.getSrId() );
                    criteriaMap.put( "status", CRMStatusCode.OPEN.getStatusCode() );
                    if ( StringUtils.isNotBlank( customerId ) )
                    {
                        criteriaMap.put( IAppConstants.PARAM_MAPPING_ID, customerId );
                    }
                    List<CrmSrTicketsPojo> crmSrTicketsPojos = CRMServiceUtils.getDBValueList( CrmSrTicketsPojo.class,
                                                                                               criteriaMap );
                    if ( !CommonValidator.isValidCollection( crmSrTicketsPojos ) )
                    {
                        if ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCustomerId() ) )
                        {
                            criteriaMap.put( IAppConstants.PARAM_MAPPING_ID, crmCustomerDetailsPojo.getCustomerId() );
                        }
                        crmSrTicketsPojos = CRMServiceUtils.getDBValueList( CrmSrTicketsPojo.class, criteriaMap );
                        if ( !CommonValidator.isValidCollection( crmSrTicketsPojos ) )
                        {
                            crmFinanceDto.setStatusCode( CRMServiceCode.CRM402.getStatusCode() );
                            crmFinanceDto.setStatusDesc( CRMServiceCode.CRM402.getStatusDesc() );
                            return crmFinanceDto;
                        }
                    }
                }
                crmFinanceDto.setUserId( inCrmFinanceDto.getUserId() );
                crmFinanceDto.setRemarks( inCrmFinanceDto.getRemarks() );
                crmFinanceDto.setReversalWOCrf( inCrmFinanceDto.isReversalWOCrf() );
                if ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCustomerId() ) )
                {
                    newValues.append( IAppConstants.SPACE ).append( "Customer ID" )
                            .append( crmCustomerDetailsPojo.getCustomerId() ).append( IAppConstants.COMMA );
                }
                else if ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCrfId() ) )
                {
                    newValues.append( IAppConstants.SPACE ).append( "CRF ID=" )
                            .append( crmCustomerDetailsPojo.getCrfId() ).append( IAppConstants.COMMA );
                }
                newValues.append( IAppConstants.SPACE ).append( "ReversalReason=" )
                        .append( inCrmFinanceDto.getPaymentDetailsPojo().getReversalReason() )
                        .append( IAppConstants.COMMA );
                getFinanceManagerDao().postReversal( crmFinanceDto, crmCustomerDetailsPojo, crmPaymentDetailsPojo );
                if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    String mappingId = String.valueOf( inCrmFinanceDto.getPaymentDetailsPojo().getPaymentId() );
                    String createdBy = inCrmFinanceDto.getPaymentDetailsPojo().getLastModifiedBy();
                    CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy, inCrmFinanceDto
                                                            .getRemarks().getRemarks(), eventId, CRMRequestType.FINANCE
                                                            .getRequestCode(),
                                                    inCrmFinanceDto.getClientIPAddress(), inCrmFinanceDto
                                                            .getServerIPAddress() );
                }
            }
        }
        return crmFinanceDto;
    }

    private CrmFinanceDto postSinglePayment( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = inCrmFinanceDto.getPaymentDetailsPojo();
        boolean isValid = ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
                                                           ICRMValidationCriteriaUtil.COMMON_PAYMENT_CRITERIA ) == null;
        isValid = isValid
                && ( ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
                                                      ICRMValidationCriteriaUtil.CASH_PAYMENT_CRITERIA ) == null
                        || ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
                                                            ICRMValidationCriteriaUtil.CHEQUE_PAYMENT_CRITERIA ) == null || ValidationPojoUtil
                        .validatePojo( crmPaymentDetailsPojo, ICRMValidationCriteriaUtil.ONLINE_PAYMENT_CRITERIA ) == null );
        if ( isValid
                && StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
                                       crmPaymentDetailsPojo.getRealzationStatus() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
                                                       ICRMValidationCriteriaUtil.PAYMENT_CHEQUE_BOUNCE_CRITERIA ) == null;
        }
        /*if ( isValid
                && StringUtils.equals( crmPaymentDetailsPojo.getPaymentMode(), CRMDisplayListConstants.CASH.getCode() )
                && !StringUtils
                        .startsWith( crmPaymentDetailsPojo.getReceiptNo(), crmPaymentDetailsPojo.getEntityType() ) )
        {
            isValid = false;
        }*/
        if ( isValid )
        {
            inCrmFinanceDto.setUserId( inCrmFinanceDto.getUserId() );
            isValid = getFinanceManagerDao().postSinglePayment( inCrmFinanceDto, crmPaymentDetailsPojo, null );
            if ( isValid )
            {
                inCrmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
            }
        }
        return inCrmFinanceDto;
    }

    //    private CrmFinanceDto postMultiplePayments( CrmFinanceDto inCrmFinanceDto )
    //    {
    //        CrmFinanceDto crmFinanceDto = invalidRequest();
    //        List<CrmPaymentDetailsPojo> payments = inCrmFinanceDto.getPaymentDetailsPojos();
    //        boolean isValid = false;
    //        for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : payments )
    //        {
    //            isValid = ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
    //                                                       ICRMValidationCriteriaUtil.COMMON_PAYMENT_CRITERIA ) == null;
    //            isValid = isValid
    //                    && ( ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
    //                                                          ICRMValidationCriteriaUtil.CASH_PAYMENT_CRITERIA ) == null
    //                            || ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
    //                                                                ICRMValidationCriteriaUtil.CHEQUE_PAYMENT_CRITERIA ) == null || ValidationPojoUtil
    //                            .validatePojo( crmPaymentDetailsPojo, ICRMValidationCriteriaUtil.ONLINE_PAYMENT_CRITERIA ) == null );
    //            if ( isValid
    //                    && StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(),
    //                                           crmPaymentDetailsPojo.getRealzationStatus() ) )
    //            {
    //                isValid = ValidationPojoUtil.validatePojo( crmPaymentDetailsPojo,
    //                                                           ICRMValidationCriteriaUtil.PAYMENT_CHEQUE_BOUNCE_CRITERIA ) == null;
    //            }
    //            if ( isValid
    //                    && StringUtils.equals( crmPaymentDetailsPojo.getPaymentMode(),
    //                                           CRMDisplayListConstants.CASH.getCode() )
    //                    && !StringUtils.startsWith( crmPaymentDetailsPojo.getReceiptNo(),
    //                                                crmPaymentDetailsPojo.getEntityType() ) )
    //            {
    //                isValid = false;
    //            }
    //            if ( !isValid )
    //            {
    //                break;
    //            }
    //        }
    //        if ( isValid )
    //        {
    //            getFinanceManagerDao().postMultiplePayments( crmFinanceDto, payments );
    //        }
    //        return crmFinanceDto;
    //    }
    private CrmFinanceDto getUpfrontPopUpDetails( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        if ( StringUtils.isValidObj( inCrmFinanceDto.getCrfIDs() ) && !inCrmFinanceDto.getCrfIDs().isEmpty() )
        {
            inCrmFinanceDto = getFinanceManagerDao().getUpfrontPopUpDetails( inCrmFinanceDto );
        }
        return inCrmFinanceDto;
    }

    private CrmFinanceDto invalidRequest()
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        crmFinanceDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        LOGGER.info( "in invalid request" );
        return crmFinanceDto;
    }

    private CrmFinanceDto invalidRequest( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        return inCrmFinanceDto;
    }

    private void setCustomerIdDBStatus( List<CrmCmsPaymentPojo> inCmsPaymentPojos, Set<String> inCustomerIds )
    {
        LOGGER.info( "Size of Payment Pojos to mark suspence : " + inCmsPaymentPojos.size() );
        try
        {
            ConfigDto configDto = null;
            if ( CommonValidator.isValidCollection( inCustomerIds ) )
            {
                configDto = new ConfigDto();
                List<String> idsList = new ArrayList<String>();
                idsList.addAll( inCustomerIds );
                configDto.setUserIds( idsList );
                configDto = CRMServicesProxy
                        .getInstance()
                        .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .configOperations( ServiceParameter.LIST.getParameter(),
                                           CrmCustomerDetailsPojo.class.getSimpleName(), configDto );
                if ( StringUtils.isValidObj( configDto )
                        && CommonValidator.isValidCollection( configDto.getCustomerDetailPojos() ) )
                {
                    for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : configDto.getCustomerDetailPojos() )
                    {
                        inCustomerIds.remove( crmCustomerDetailsPojo.getCustomerId() );
                    }
                }
                for ( CrmCmsPaymentPojo cmsPaymentPojo : inCmsPaymentPojos )
                {
                    if ( inCustomerIds.contains( cmsPaymentPojo.getIe2() ) )
                    {
                        cmsPaymentPojo.setStatus( CRMStatusCode.SUSPENCE.getStatusCode() );
                    }
                }
            }
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "SOAP Exception..", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting suspence status..", ex );
        }
    }

    private void setClearanceDBStatus( CrmCmsPaymentPojo inCmsPaymentPojo )
    {
        CrmFinanceDto financeDto = new CrmFinanceDto();
        inCmsPaymentPojo.setStatus( null );// CRMStatusCode.PENDING.getStatusCode()
        long clearenceFileId = inCmsPaymentPojo.getClearanceFileId();
        inCmsPaymentPojo.setClearanceFileId( 0L );
        financeDto.setCmsPayment( inCmsPaymentPojo );
        List<CrmCmsPaymentPojo> cmsPaymentDBPojos = getFinanceManagerDao().getCMSRecords( financeDto ).getCmsPayments();
        inCmsPaymentPojo.setClearanceFileId( clearenceFileId );
        try
        {
            if ( CommonValidator.isValidCollection( cmsPaymentDBPojos ) )
            {
                LOGGER.info( "Size of Pending Payment DB Pojos : " + cmsPaymentDBPojos.size() );
                int pojoIndex = cmsPaymentDBPojos.indexOf( inCmsPaymentPojo );
                if ( pojoIndex < 0 )
                {
                    inCmsPaymentPojo.setFaliureReason( PropertyUtils.getCmsKeyValue( "error.no.deposit.entry" ) );
                    inCmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
                }
                else
                {
                    CrmCmsPaymentPojo cmsDBPojo = cmsPaymentDBPojos.get( pojoIndex );
                    LOGGER.info( "Pre Payment Pojo :" + inCmsPaymentPojo );
                    inCmsPaymentPojo.setCmsId( cmsDBPojo.getCmsId() );
                    inCmsPaymentPojo.setDepositFileId( cmsDBPojo.getDepositFileId() );
                    inCmsPaymentPojo.setInternalDepNo( cmsDBPojo.getInternalDepNo() );
                    inCmsPaymentPojo.setDepositSlipNo( cmsDBPojo.getDepositSlipNo() );
                    inCmsPaymentPojo.setDepositRemarks( cmsDBPojo.getDepositRemarks() );
                    inCmsPaymentPojo.setDepositDate( cmsDBPojo.getDepositDate() );
                    inCmsPaymentPojo.setTotalAmount( cmsDBPojo.getTotalAmount() );
                    inCmsPaymentPojo.setTotalInstruments( cmsDBPojo.getTotalInstruments() );
                    inCmsPaymentPojo.setPickupDate( cmsDBPojo.getPickupDate() );
                    inCmsPaymentPojo.setInstrumentDate( cmsDBPojo.getInstrumentDate() );
                    inCmsPaymentPojo.setDraweeBranch( cmsDBPojo.getDraweeBranch() );
                    inCmsPaymentPojo.setDrawerName( cmsDBPojo.getDrawerName() );
                    inCmsPaymentPojo.setIe1( cmsDBPojo.getIe1() );
                    inCmsPaymentPojo.setCreatedTime( cmsDBPojo.getCreatedTime() );
                    inCmsPaymentPojo.setLastModifiedBy( PropertyUtils.getCmsKeyValue( "cms.file.owner" ) );
                    if ( StringUtils.equals( cmsDBPojo.getStatus(), CRMStatusCode.PENDING.getStatusCode() ) )
                    {
                        inCmsPaymentPojo.setStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                    }
                    else
                    {
                        inCmsPaymentPojo.setStatus( cmsDBPojo.getStatus() );
                    }
                    LOGGER.info( "Post Payment Pojo :" + inCmsPaymentPojo );
                }
            }
            else
            {
                inCmsPaymentPojo.setFaliureReason( PropertyUtils.getCmsKeyValue( "error.no.deposit.entry" ) );
                inCmsPaymentPojo.setStatus( CRMStatusCode.REJECTED.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting value to final Payment Pojo", ex );
        }
    }

    private CrmFinanceDto trackRefundPayments( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = getFinanceManagerDao().getRefundDetails( inCrmFinanceDto );
        LOGGER.info( "Status Code::" + inCrmFinanceDto.getStatusCode() );
        return inCrmFinanceDto;
    }

    private CrmFinanceDto updateRefundChequeStatus( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = getFinanceManagerDao().changeRefundChequeStatus( inCrmFinanceDto );
        LOGGER.info( "Status Code::" + inCrmFinanceDto.getStatusCode() );
        return inCrmFinanceDto;
    }

    private CrmFinanceDto postRefundPayment( CrmFinanceDto inCrmFinanceDto )
    {
        inCrmFinanceDto = invalidRequest( inCrmFinanceDto );
        boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto.getCrmCustomerRefundsPojo(),
                                                           ICRMValidationCriteriaUtil.REFUND_PAYMENT_CRITERIA ) == null;
        if ( isValid )
        {
            inCrmFinanceDto = getFinanceManagerDao().postRefund( inCrmFinanceDto );
            LOGGER.info( "Status Code::" + inCrmFinanceDto.getStatusCode() );
        }
        return inCrmFinanceDto;
    }

    private CrmFinanceDto trackPODRecords( CrmFinanceDto inCrmFinanceDto )
    {
        CrmFinanceDto crmFinanceDto = invalidRequest();
        boolean isValid = true;
        /* boolean isValid = ValidationPojoUtil.validatePojo( inCrmFinanceDto,
                                                            ICRMValidationCriteriaUtil.TRACK_PAYMENT_CRITERIA ) == null;*/
        if ( isValid )
        {
            crmFinanceDto = getFinanceManagerDao().trackPODRecords( inCrmFinanceDto );
            LOGGER.info( "Status Code::" + inCrmFinanceDto.getStatusCode() );
        }
        return crmFinanceDto;
    }
}
