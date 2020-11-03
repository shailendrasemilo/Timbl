package com.np.tele.crm.finance.bm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.gis.bm.ExcelReadUtils;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerRefundsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class FinanceManagerImpl
    implements IFinanceManager
{
    private static final Logger LOGGER               = Logger.getLogger( FinanceManagerImpl.class );
    private CrmFinanceService   financeService       = null;
    private IFinanceManager     financeManagerClient = null;
    private MasterDataClient    masterDataClient     = null;
    private CRMConfigService    crmConfigClient      = null;

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    public CrmFinanceService getFinanceService()
    {
        return financeService;
    }

    public IFinanceManager getFinanceManagerClient()
    {
        return financeManagerClient;
    }

    public void setFinanceManagerClient( IFinanceManager inFinanceManagerClient )
    {
        financeManagerClient = inFinanceManagerClient;
    }

    public MasterDataClient getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterDataClient inMasterDataClient )
    {
        masterDataClient = inMasterDataClient;
    }

    public void setFinanceService( CrmFinanceService inFinanceService )
    {
        financeService = inFinanceService;
    }

    @Override
    public CrmFinanceDto paymentPosting( FinanceForm financeForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        if ( StringUtils.isValidObj( financeForm.getCrmPaymentDetailsPojo() ) )
        {
            financeForm.getCrmPaymentDetailsPojo().setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            financeForm.getCrmPaymentDetailsPojo()
                    .setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
            if ( StringUtils.equals( CRMDisplayListConstants.CHEQUE.getCode(), financeForm.getCrmPaymentDetailsPojo()
                    .getPaymentMode() ) )
            {
                financeForm.getCrmPaymentDetailsPojo().setTransactionId( null );
                financeForm.getCrmPaymentDetailsPojo().setReceiptNo( null );
            }
            else if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(), financeForm
                    .getCrmPaymentDetailsPojo().getPaymentMode() ) )
            {
                financeForm.getCrmPaymentDetailsPojo().setChequeDate( null );
                financeForm.getCrmPaymentDetailsPojo().setRealzationStatus( null );
                financeForm.getCrmPaymentDetailsPojo().setBankName( null );
                financeForm.getCrmPaymentDetailsPojo().setChequeDdNo( null );
                financeForm.getCrmPaymentDetailsPojo().setTransactionId( null );
            }
            else if ( StringUtils.equals( CRMDisplayListConstants.ONLINE_PAYMENT.getCode(), financeForm
                    .getCrmPaymentDetailsPojo().getPaymentMode() ) )
            {
                financeForm.getCrmPaymentDetailsPojo().setChequeDate( null );
                financeForm.getCrmPaymentDetailsPojo().setRealzationStatus( null );
                financeForm.getCrmPaymentDetailsPojo().setBankName( null );
                financeForm.getCrmPaymentDetailsPojo().setChequeDdNo( null );
                financeForm.getCrmPaymentDetailsPojo().setReceiptNo( null );
                financeForm.getCrmPaymentDetailsPojo().setBankTransId( financeForm.getCrmPaymentDetailsPojo()
                                                                               .getTransactionId() );
                financeForm.getCrmPaymentDetailsPojo().setPgwTransId( financeForm.getCrmPaymentDetailsPojo()
                                                                              .getTransactionId() );
            }
            crmFinanceDto.setPaymentDetailsPojo( financeForm.getCrmPaymentDetailsPojo() );
            crmFinanceDto.setUserId( financeForm.getCrmPaymentDetailsPojo().getCreatedBy() );
            try
            {
                crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                                 CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
            }
            catch ( Exception ex )
            {
                LOGGER.info( "Getting Error While, Fetching Category Value: ", ex );
            }
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto searchPayment( FinanceForm financeForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        if ( StringUtils.isNotEmpty( financeForm.getCrfId() ) || StringUtils.isNotEmpty( financeForm.getCustomerId() ) )
        {
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
            crmCustomerDetailsPojo.setCustomerId( financeForm.getCustomerId() );
            crmCustomerDetailsPojo.setCrfId( financeForm.getCrfId() );
            crmFinanceDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
        }
        crmFinanceDto.setFromDate( financeForm.getFromDate() );
        crmFinanceDto.setToDate( financeForm.getToDate() );
        crmPaymentDetailsPojo.setInstallationStatus( financeForm.getInstallationStatus() );
        crmPaymentDetailsPojo.setPaymentStatus( financeForm.getPaymentStatus() );
        crmPaymentDetailsPojo.setCaseStatus( financeForm.getCaseStatus() );
        crmPaymentDetailsPojo.setRealzationStatus( financeForm.getCheque_status() );
        crmPaymentDetailsPojo.setEntityType( ( financeForm.getEntity_type() ) );
        crmPaymentDetailsPojo.setPaymentMode( financeForm.getPayment_mode() );
        crmPaymentDetailsPojo.setPaymentChannel( financeForm.getChannel_type() );
        crmPaymentDetailsPojo.setServiceType( financeForm.getCustomerServiceType() );
        crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
        try
        {
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, search Payment", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto changePaymentStatus( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        crmPaymentDetailsPojo.setPaymentId( inFinanceForm.getPaymentId() );
        crmPaymentDetailsPojo.setPaymentStatus( inFinanceForm.getChangeDynamicVariable() );
        crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
        crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
            inFinanceForm.setChangeDynamicVariable( null );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, change payment status", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto changeRealizationStatus( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        crmPaymentDetailsPojo.setPaymentId( inFinanceForm.getPaymentId() );
        //        int pojoIndex = inFinanceForm.getSearchPaymentList().indexOf( crmPaymentDetailsPojo );
        //        if ( pojoIndex >= 0 )
        //        {
        //            crmPaymentDetailsPojo = inFinanceForm.getSearchPaymentList().get( pojoIndex );
        //            LOGGER.info( "Realization::: " + inFinanceForm.isRealizationVariable() );
        if ( inFinanceForm.isRealizationVariable() )
        {
            crmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
            crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
            crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
            crmFinanceDto.setUserId( inFinanceForm.getModifiedBy() );
        }
        else
        {
            LOGGER.info( "Calling Cheque Not Realize::" );
            crmPaymentDetailsPojo.setBouncingDate( DateUtils.changeDateFormat( inFinanceForm.getBouncingDate() ) );
            crmPaymentDetailsPojo.setBouncingReason( inFinanceForm.getBouncingReason() );
            crmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode() );
            crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
            crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
            crmFinanceDto.setUserId( inFinanceForm.getModifiedBy() );
        }
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, change Realization Status: ", ex );
        }
        //        }
        return crmFinanceDto;
    }

    public static void main( String args[] )
    {
        String DateStr = "12-Dec-2013" + IAppConstants.START_TIME;
        SimpleDateFormat df = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
        Date date;
        try
        {
            date = df.parse( DateStr );
            System.out.println( date );
            System.out.println( new SimpleDateFormat( "dd-MMM-yyyy" ).format( new Date() ).toString() );
            int a = 1 / 0;
        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }
        System.out.println( "deepak" );
    }

    public CrmFinanceDto searchPaymentDetails( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        CrmPaymentDetailsPojo paymentDetailsPojo = null;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            customerDetailsPojo = new CrmCustomerDetailsPojo();
            paymentDetailsPojo = new CrmPaymentDetailsPojo();
            // paymentDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            customerDetailsPojo.setCustomerId( inFinanceForm.getCustomerId() );
            customerDetailsPojo.setCrfId( inFinanceForm.getCrfId() );
            crmFinanceDto.setToDate( inFinanceForm.getToDate() );
            crmFinanceDto.setFromDate( inFinanceForm.getFromDate() );
            crmFinanceDto.setCustomerDetailsPojo( customerDetailsPojo );
            crmFinanceDto.setPaymentDetailsPojo( paymentDetailsPojo );
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.REVERSAL.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for search payment details ", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto reversePayment( FinanceForm inFinanceForm, String inUser )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojo = inFinanceForm.getCrmPaymentDetailsPojo();
            RemarksPojo remarksPojo = inFinanceForm.getRemarksPojo();
            crmPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
            crmPaymentDetailsPojo.setLastModifiedBy( inUser );
            CrmCustomerDetailsPojo customerDetailsPojo = inFinanceForm.getCrmCustomerDetailsPojo();
            customerDetailsPojo.setCrfId( StringUtils.upperCase( customerDetailsPojo.getCrfId() ) );
            crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
            crmFinanceDto.setRemarks( remarksPojo );
            crmFinanceDto.setReversalWOCrf( inFinanceForm.isReversalWOCrf() );
            crmFinanceDto.setCustomerDetailsPojo( customerDetailsPojo );
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                             CRMParameter.REVERSAL.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for reversing payment", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto manualPayment( FinanceForm inForm, Set<CrmUpCrfMappingPojo> inCrfMappingPojos )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = inForm.getCrmUpfrontPaymentPojo();
            crmUpfrontPaymentPojo.getCrmUpCrfMappings().addAll( ( inCrfMappingPojos ) );
            crmFinanceDto.setCrmUpfrontPaymentPojo( crmUpfrontPaymentPojo );
            List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojos = new ArrayList<CrmUpfrontPaymentPojo>();
            crmUpfrontPaymentPojos.add( crmUpfrontPaymentPojo );
            crmFinanceDto.getCrmUpfrontPaymentPojos().addAll( crmUpfrontPaymentPojos );
            crmFinanceDto = getFinanceService()
                    .postPayment( ServiceParameter.POST.getParameter(), CRMParameter.UPFRONT_PAYMENT.getParameter(),
                                  crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for reversing payment", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto searchUpfrontPaymentDetails( FinanceForm inForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        CrmUpfrontPaymentPojo crmUpfrontPaymentPojo = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            if ( StringUtils.isValidObj( inForm.getCrmUpfrontPaymentPojo() ) )
            {
                crmUpfrontPaymentPojo = inForm.getCrmUpfrontPaymentPojo();
                crmFinanceDto.setCrmUpfrontPaymentPojo( crmUpfrontPaymentPojo );
                LOGGER.info( "The partner id is : " + crmUpfrontPaymentPojo.getPartnerId() );
            }
            crmFinanceDto.setToDate( inForm.getToDate() );
            crmFinanceDto.setFromDate( inForm.getFromDate() );
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.UPFRONT_PAYMENT.getParameter(),
                                                              crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for search payment details ", ex );
        }
        return crmFinanceDto;
    }

    public String getFromDate( String inDate )
    {
        String oldDate = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
            Date date = dateFormat.parse( inDate );
            dateFormat.format( date );
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( date );
            calendar.add( Calendar.DAY_OF_YEAR, -180 );
            Date previousDate = calendar.getTime();
            oldDate = dateFormat.format( previousDate );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured in getFromDate", ex );
        }
        return oldDate;
    }

    @Override
    public CrmFinanceDto getFileList( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setCmsFile( inFinanceForm.getCrmCmsFilePojo() );
            crmFinanceDto.setToDate( inFinanceForm.getToDate() );
            crmFinanceDto.setFromDate( inFinanceForm.getFromDate() );
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.CMSFILE.getParameter(), crmFinanceDto );
            LOGGER.info( "Result size " + crmFinanceDto.getCmsFiles().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto getUpfrontPopUpDetails( FinanceForm inForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.getCrfIDs().addAll( inForm.getCrfIds() );
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.UPFRONT_POPUP_DETAIL.getParameter(),
                                                              crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto realizeRecieveAll( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        if ( StringUtils.isNotEmpty( inFinanceForm.getHiddenPaymentIdList() ) )
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojo = null;
            if ( StringUtils.contains( inFinanceForm.getHiddenPaymentIdList(), IAppConstants.COMMA ) )
            {
                String[] ids = inFinanceForm.getHiddenPaymentIdList().split( IAppConstants.COMMA );
                for ( String paymentId : ids )
                {
                    crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
                    LOGGER.info( "paymentid:" + paymentId );
                    crmPaymentDetailsPojo.setPaymentId( StringUtils.numericValue( paymentId ) );
                    if ( StringUtils.equals( inFinanceForm.getChangeDynamicVariable(),
                                             CRMDisplayListConstants.CHEQUE_REALIZED.getCode() ) )
                    {
                        crmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
                    }
                    else if ( StringUtils.equals( inFinanceForm.getChangeDynamicVariable(),
                                                  CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() ) )
                    {
                        crmPaymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
                    }
                    crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
                    crmFinanceDto.getPaymentDetailsPojos().add( crmPaymentDetailsPojo );
                }
            }
            else
            {
                crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
                crmPaymentDetailsPojo.setPaymentId( Long.parseLong( inFinanceForm.getHiddenPaymentIdList() ) );
                if ( StringUtils.equals( inFinanceForm.getChangeDynamicVariable(),
                                         CRMDisplayListConstants.CHEQUE_REALIZED.getCode() ) )
                {
                    crmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_REALIZED.getCode() );
                }
                else if ( StringUtils.equals( inFinanceForm.getChangeDynamicVariable(),
                                              CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() ) )
                {
                    crmPaymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
                }
                crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
                crmFinanceDto.getPaymentDetailsPojos().add( crmPaymentDetailsPojo );
            }
        }
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in realize all:", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto changeCaseStatus( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        crmPaymentDetailsPojo.setPaymentId( inFinanceForm.getPaymentId() );
        crmPaymentDetailsPojo.setCaseStatus( inFinanceForm.getChangeDynamicVariable() );
        crmPaymentDetailsPojo.setLastModifiedBy( inFinanceForm.getModifiedBy() );
        crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in changeCaseStatus :", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto searchSuspenseRecord( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setToDate( inFinanceForm.getToDate() );
            crmFinanceDto.setFromDate( inFinanceForm.getFromDate() );
            LOGGER.info( "Cms File Id......." + inFinanceForm.getCrmCmsPaymentPojo().getClearanceFileId() );
            if ( StringUtils.isValidObj( inFinanceForm.getCrmCmsPaymentPojo() ) )
            {
                CrmCmsPaymentPojo cmsPayment = new CrmCmsPaymentPojo();
                cmsPayment.setDepositFileId( inFinanceForm.getCrmCmsPaymentPojo().getDepositFileId() );
                cmsPayment.setClearanceFileId( inFinanceForm.getCrmCmsPaymentPojo().getClearanceFileId() );
                cmsPayment.setStatus( inFinanceForm.getCrmCmsPaymentPojo().getStatus() );
                crmFinanceDto.setCmsPayment( cmsPayment );
            }
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.CMSRECORDS.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto rectifyRejectedRecord( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            LOGGER.info( "Bank Name" + inFinanceForm.getCrmCmsPaymentPojo().getDraweeBank() );
            crmFinanceDto.getCmsPayments().add( inFinanceForm.getCrmCmsPaymentPojo() );
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.CMSRECORDS.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto rectifySuspenseRecord( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            CrmCmsPaymentPojo pojo = inFinanceForm.getCrmCmsPaymentPojo();
            LOGGER.info( "New CustomerId**********" + inFinanceForm.getCrmCmsPaymentPojo().getIe2() );
            crmFinanceDto.getCmsPayments().add( pojo );
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.CMSRECORDS.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public List<String> processUpFrontExcel( FinanceForm inFinanceForm, String inUser, String inFilePath )
    {
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        ExcelWriteUtils excelWriteUtils = null;
        List<String> statusList = new ArrayList<String>();
        try
        {
            LOGGER.info( "Start reading Upfront Payment File...." );
            String inPath = ExcelWriteUtils.downloadUploadedFile( inFilePath, inUser, inFinanceForm.getFormFile(),
                                                                  CRMParameter.UPFRONTFILE.getParameter() );
            fileInputStream = new FileInputStream( inPath );
            if ( inPath.endsWith( ".xls" ) )
            {
                excelReadUtils = new ExcelReadUtils<HSSFSheet, HSSFRow, HSSFCell>();
                workbook = new HSSFWorkbook( fileInputStream );
            }
            else if ( inPath.endsWith( ".xlsx" ) )
            {
                excelReadUtils = new ExcelReadUtils<XSSFSheet, XSSFRow, XSSFCell>();
                workbook = new XSSFWorkbook( fileInputStream );
            }
            excelWriteUtils = new ExcelWriteUtils( CRMParameter.UPFRONTFILE.getParameter() );
            @SuppressWarnings("unchecked")
            List<CrmUpfrontPaymentPojo> dataToProcess = excelReadUtils
                    .readFileData( inUser + "," + inFinanceForm.getCrmUpfrontPaymentPojo().getPartnerId(), workbook,
                                   inFilePath, excelWriteUtils, null, CRMParameter.UPFRONTFILE.getParameter() );
            if ( StringUtils.isValidObj( dataToProcess ) )
            {
                String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                              CRMParameter.UPFRONTFILE.getParameter() );
                String status = storeValidData( dataToProcess, inFinanceForm );
                statusList.add( status );
                statusList.add( excelReadUtils.getnRows() + "" );
                statusList.add( excelReadUtils.getProcessRows() + "" );
                statusList.add( outPath );
            }
        }
        catch ( FileNotFoundException ex )
        {
            LOGGER.error( "File not found Exception", ex );
        }
        catch ( IOException ioex )
        {
            LOGGER.error( "Input output Exception", ioex );
        }
        return statusList;
    }

    private String storeValidData( List<CrmUpfrontPaymentPojo> inDataToProcess, FinanceForm inFinanceForm )
    {
        String status = "failed";
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            LOGGER.info( "Going to insert Success Upfront Payments" );
            if ( StringUtils.isValidObj( inDataToProcess ) && !inDataToProcess.isEmpty() )
            {
                crmFinanceDto = new CrmFinanceDto();
                crmFinanceDto.getCrmUpfrontPaymentPojos().addAll( inDataToProcess );
                crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                                 CRMParameter.UPFRONT_PAYMENT.getParameter(),
                                                                 crmFinanceDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                    status = "success";
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while feeding CRM Upfront Payment : ", ex );
        }
        return status;
    }

    @Override
    public ConfigDto getActivityLogs( FinanceForm inFinanceForm )
    {
        ConfigDto configDto = new ConfigDto();
        CrmAuditLogPojo crmAuditLogPojo = new CrmAuditLogPojo();
        try
        {
            LOGGER.info( ":::VIEW PAYMENT coming for audit logs::" );
            crmAuditLogPojo.setMappingId( String.valueOf( inFinanceForm.getPaymentId() ) );
            LOGGER.info( ":::Mapping Id::" + crmAuditLogPojo.getMappingId() );
            crmAuditLogPojo.setModule( CRMRequestType.FINANCE.getRequestCode() );
            configDto.setAuditLogPojo( crmAuditLogPojo );
            configDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while getting activity log of user:Payment Details", ex );
        }
        return configDto;
    }

    @Override
    public CrmFinanceDto paymentGatewaysConfigure( FinanceForm inFinanceForm, String inUserId )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            masterDataDto.getCrmRcaReasonsList().addAll( inFinanceForm.getPaymentGateways() );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                    CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                    masterDataDto );
            if ( StringUtils.isValidObj( masterDataDto ) )
            {
                crmFinanceDto.setStatusCode( masterDataDto.getStatusCode() );
                crmFinanceDto.setStatusDesc( masterDataDto.getStatusDesc() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, creating Category Value for paymentGatewaysConfigure: " + ex );
        }
        return crmFinanceDto;
    }

    @Override
    public List<String> processPODExcel( FinanceForm inFinanceForm, String inUser, String inFilePath )
    {
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        ExcelWriteUtils excelWriteUtils = null;
        List<String> statusList = new ArrayList<String>();
        boolean processFlag = false;
        try
        {
            String inPath = ExcelWriteUtils.downloadUploadedFile( inFilePath, inUser, inFinanceForm.getFormFile(),
                                                                  CRMParameter.POD_FILE_UPLOAD.getParameter() );
            fileInputStream = new FileInputStream( inPath );
            if ( inPath.endsWith( ".xls" ) )
            {
                excelReadUtils = new ExcelReadUtils<HSSFSheet, HSSFRow, HSSFCell>();
                workbook = new HSSFWorkbook( fileInputStream );
                processFlag = true;
            }
            else if ( inPath.endsWith( ".xlsx" ) )
            {
                excelReadUtils = new ExcelReadUtils<XSSFSheet, XSSFRow, XSSFCell>();
                workbook = new XSSFWorkbook( fileInputStream );
                processFlag = true;
            }
            if ( processFlag )
            {
                excelWriteUtils = new ExcelWriteUtils( CRMParameter.POD_FILE_UPLOAD.getParameter() );
                @SuppressWarnings("unchecked")
                List<CrmPodDetailsPojo> dataToProcess = excelReadUtils.readFileData( inUser, workbook, inFilePath,
                                                                                     excelWriteUtils, null,
                                                                                     CRMParameter.POD_FILE_UPLOAD
                                                                                             .getParameter() );
                if ( !StringUtils.isValidObj( dataToProcess ) )
                {
                    statusList.add( "Invalid File Header." );
                }
                else
                {
                    LOGGER.info( "List Size::;" + dataToProcess.size() );
                    String status = storeValidPODData( dataToProcess, inFinanceForm, inUser, excelWriteUtils ) + "_"
                            + excelReadUtils.getnRows();
                    String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                                  CRMParameter.POD_FILE_UPLOAD.getParameter() );
                    statusList.add( status );
                    statusList.add( outPath );
                }
            }
            else
            {
                statusList.add( "File not found." );
            }
        }
        catch ( FileNotFoundException ex )
        {
            LOGGER.error( "File not found Exception", ex );
        }
        catch ( IOException ioex )
        {
            LOGGER.error( "Input output Exception", ioex );
        }
        return statusList;
    }

    private String storeValidPODData( List<CrmPodDetailsPojo> inDataToProcess,
                                      FinanceForm inFinanceForm,
                                      String inUser,
                                      ExcelWriteUtils inExcelWriteUtils )
    {
        String status = "failed";
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            LOGGER.info( "Going to insert data in POD" + inDataToProcess );
            if ( CommonValidator.isValidCollection( inDataToProcess ) )
            {
                crmFinanceDto = new CrmFinanceDto();
                crmFinanceDto.getCrmPodDetailsPojos().addAll( inDataToProcess );
                crmFinanceDto = getFinanceService().postPayment( ServiceParameter.PROCESS.getParameter(),
                                                                 CRMParameter.POD_FILE_UPLOAD.getParameter(),
                                                                 crmFinanceDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                {
                    inExcelWriteUtils.addRowPOD( crmFinanceDto.getCrmPodDetailsPojos() );
                    status = crmFinanceDto.getStatusCode() + "_" + crmFinanceDto.getDbSuccessCount();
                }
                else
                {
                    status = crmFinanceDto.getStatusCode() + "_";
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while feeding CRM POD Data : ", ex );
        }
        return status;
    }

    public CrmFinanceDto searchRefundDetails( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        CrmCustomerRefundsPojo refundsPojo = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        LOGGER.info( "searchRefundDetails" );
        if ( StringUtils.isNotBlank( inFinanceForm.getCrfId() ) )
        {
            crmCustomerDetailsPojo.setCrfId( inFinanceForm.getCrfId() );
        }
        else
        {
            crmCustomerDetailsPojo.setCustomerId( inFinanceForm.getCustomerId() );
        }
        if ( !StringUtils.isValidObj( inFinanceForm.getCrmCustomerRefundsPojo() ) )
        {
            refundsPojo = new CrmCustomerRefundsPojo();
        }
        else
        {
            refundsPojo = inFinanceForm.getCrmCustomerRefundsPojo();
        }
        if ( StringUtils.isNotBlank( inFinanceForm.getPayment_mode() ) )
        {
            refundsPojo.setRefundMode( inFinanceForm.getPayment_mode() );
        }
        if ( StringUtils.isNotBlank( inFinanceForm.getPaymentStatus() ) )
        {
            refundsPojo.setStatus( inFinanceForm.getPaymentStatus() );
        }
        if ( StringUtils.isNotBlank( inFinanceForm.getEntity_type() ) )
        {
            refundsPojo.setEntityType( inFinanceForm.getEntity_type() );
        }
        crmFinanceDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
        crmFinanceDto.setCrmCustomerRefundsPojo( refundsPojo );
        try
        {
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.REFUND.getParameter(), crmFinanceDto );
            LOGGER.info( "Status Code:::" + crmFinanceDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto getchangeRefundStatus( FinanceForm inFinanceForm, String inUserId )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            LOGGER.info( inFinanceForm.getCrmCustomerRefundsPojo() );
            crmFinanceDto.setCrmCustomerRefundsPojo( inFinanceForm.getCrmCustomerRefundsPojo() );
            crmFinanceDto.setUserId( inUserId );
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.REFUND.getParameter(), crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto processRefund( FinanceForm inFinanceForm, String inUserId )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            crmFinanceDto.setCrmCustomerRefundsPojo( inFinanceForm.getCrmCustomerRefundsPojo() );
            crmFinanceDto.setCustomerDetailsPojo( inFinanceForm.getCrmCustomerDetailsPojo() );
            crmFinanceDto.setUserId( inUserId );
            crmFinanceDto.setRemarks( inFinanceForm.getRemarksPojo() );
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                             CRMParameter.REFUND.getParameter(), crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto trackPODRecords( FinanceForm inFinanceForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        try
        {
            crmFinanceDto.setToDate( inFinanceForm.getToDate() );
            crmFinanceDto.setFromDate( inFinanceForm.getFromDate() );
            CrmPodDetailsPojo pojo = new CrmPodDetailsPojo();
            pojo.setStatus( inFinanceForm.getPodStatus() );
            crmFinanceDto.setCrmPodDetailsPojo( pojo );
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CRMParameter.POD_FILE_UPLOAD.getParameter(),
                                                              crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
        }
        return crmFinanceDto;
    }

    public CrmFinanceDto paymentListByTransId( CrmFinanceDto crmFinanceDto )
    {
        try
        {
            crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                              CrmPaymentDetailsPojo.class.getSimpleName(),
                                                              crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception occured PaymentListByTransId :", ex );
        }
        return crmFinanceDto;
    }
}
