package com.np.tele.crm.qrc.status.bm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.ExcelReadUtils;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CustomerStatusBusinessMgrImpl
    implements ICustomerStatusBusinessMgr
{
    private static final Logger LOGGER           = Logger.getLogger( CustomerStatusBusinessMgrImpl.class );
    private CrmQrcService       qrcServiceClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService qrcServiceClient )
    {
        this.qrcServiceClient = qrcServiceClient;
    }

    @Override
    public CrmQrcDto updateSafeCustody( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        try
        {
            if ( StringUtils.isValidObj( inQrcForm.getCustDetailsPojo() ) )
                crmQrcDto.setCustomerRecordId( inQrcForm.getCustDetailsPojo().getRecordId() );
            crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
            crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
            crmQrcDto.setSrTicketNo( inQrcForm.getSrTicketNo() );
            crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
            crmQrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
            crmQrcDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.SC.getStatusCode() );
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.CHANGE_STATUS.getParameter(), crmQrcDto );
        }
        catch ( Exception e )
        {
            LOGGER.info( "Exception", e );
        }
        return crmQrcDto;
    }

    @Override
    public List<String> updateCustomersStatus( QrcForm inQrcForm, String inUser, String inFilePath )
    {
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        ExcelWriteUtils excelWriteUtils = null;
        List<String> statusList = new ArrayList<String>();
        boolean processFlag = false;
        try
        {
            String inPath = ExcelWriteUtils.downloadUploadedFile( inFilePath, inUser,
                                                                  inQrcForm.getWhitelistExcelFile(),
                                                                  CRMParameter.CHANGE_STATUS.getParameter() );
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
                excelWriteUtils = new ExcelWriteUtils( CRMParameter.CHANGE_STATUS.getParameter() );
                @SuppressWarnings("unchecked")
                List<CrmCustomerDetailsPojo> dataToProcess = excelReadUtils.readFileData( inUser, workbook, inFilePath,
                                                                                          excelWriteUtils, null,
                                                                                          CRMParameter.CHANGE_STATUS
                                                                                                  .getParameter() );
                if ( !StringUtils.isValidObj( dataToProcess ) )
                {
                    statusList.add( "Invalid File Header." );
                }
                else
                {
                    if ( CommonValidator.isValidCollection( dataToProcess ) )
                    {
                        String status = custStatusValidateData( dataToProcess, inQrcForm, inUser, excelWriteUtils )
                                + "_" + excelReadUtils.getnRows();
                        String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                                      CRMParameter.CHANGE_STATUS.getParameter() );
                        statusList.add( status );
                        statusList.add( outPath );
                    }
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

    private String custStatusValidateData( List<CrmCustomerDetailsPojo> inCustDetailsPojo,
                                           QrcForm inQrcForm,
                                           String inUser,
                                           ExcelWriteUtils inExcelWriteUtils )
    {
        String status = "failed";
        CrmQrcDto qrcDto = new CrmQrcDto();
        try
        {
            if ( CommonValidator.isValidCollection( inCustDetailsPojo ) )
            {
                for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : inCustDetailsPojo )
                {
                    crmCustomerDetailsPojo.setStatus( inQrcForm.getCustDetailsPojo().getStatus() );
                }
                qrcDto.getCustomerDetailsPojos().addAll( inCustDetailsPojo );
                inQrcForm.getRemarksPojo().setReasonId( inQrcForm.getExceptionReason() );
                qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
                if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getStatus(),
                                         CRMStatusCode.BARRED.getStatusCode() ) )
                {
                    qrcDto.getCrmRcaReasonPojos().addAll( QRCCacheManager.getCustomerBarringReasons() );
                }
                else if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getStatus(),
                                              CRMStatusCode.UNBARRED.getStatusCode() ) )
                {
                    qrcDto.getCrmRcaReasonPojos().addAll( QRCCacheManager.getCustomerUnBarringReasons() );
                }
                qrcDto.setUserId( inUser );
                qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                                              CRMParameter.CHANGE_STATUS.getParameter(), qrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
                {
                    status = "success";
                    if ( StringUtils.isValidObj( qrcDto.getFailedCustomerMapList() ) )
                    {
                        inExcelWriteUtils.addRowServiceFail( qrcDto.getFailedCustomerMapList() );
                    }
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Soap client Exception", ex );
        }
        catch ( Exception exe )
        {
            LOGGER.error( "Exception while storing data : ", exe );
        }
        return status + IAppConstants.UNDERSCORE
                + ( inCustDetailsPojo.size() - qrcDto.getFailedCustomerMapList().getEntry().size() );
    }
}
