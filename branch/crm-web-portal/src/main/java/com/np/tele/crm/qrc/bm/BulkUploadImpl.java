package com.np.tele.crm.qrc.bm;

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

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.ExcelReadUtils;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.qrc.forms.BulkUploadForm;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.ValidityExtensionPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class BulkUploadImpl
    implements IBulkUpload
{
    private static final Logger LOGGER           = Logger.getLogger( BulkUploadImpl.class );
    private CrmQrcService       qrcServiceClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    @Override
    public List<String> processMountBoosterExcel( BulkUploadForm inBulkUploadForm, String inUser, String inFilePath )
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
                                                                  inBulkUploadForm.getMountBoosterExcelFile(),
                                                                  CRMParameter.MOUNT_BOOSTER.getParameter() );
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
                LOGGER.info( "Process Flag::" + processFlag );
                excelWriteUtils = new ExcelWriteUtils( CRMParameter.MOUNT_BOOSTER.getParameter() );
                @SuppressWarnings("unchecked")
                List<CrmCustAssetDetailsPojo> dataToProcess = excelReadUtils.readFileData( inUser, workbook,
                                                                                           inFilePath, excelWriteUtils,
                                                                                           null,
                                                                                           CRMParameter.MOUNT_BOOSTER
                                                                                                   .getParameter() );
                if ( !StringUtils.isValidObj( dataToProcess ) )
                {
                    LOGGER.info( "Invalid File Header." );
                    statusList.add( "Invalid File Header." );
                }
                else
                {
                    String status = storeValidData( dataToProcess, inBulkUploadForm, inUser, excelWriteUtils );
                    String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                                  CRMParameter.MOUNT_BOOSTER.getParameter() );
                    statusList.add( status );
                    statusList.add( outPath );
                    statusList.add( excelReadUtils.getnRows() + "" );
                    statusList.add( excelReadUtils.getProcessRows() + "" );
                    LOGGER.info( "dataToProcess is not empty." );
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

    private String storeValidData( List<CrmCustAssetDetailsPojo> inDataToProcess,
                                   BulkUploadForm inBulkUploadForm,
                                   String inUser,
                                   ExcelWriteUtils inExcelWriteUtils )
    {
        String status = "failed";
        CrmQrcDto qrcDto = new CrmQrcDto();
        try
        {
            if ( CommonValidator.isValidCollection( inDataToProcess ) )
            {
                qrcDto.setUserId( inUser );
                qrcDto.getCrmCustAssetDetailsPojos().addAll( inDataToProcess );
                qrcDto.setRemarksPojo( inBulkUploadForm.getRemarksPojo() );
                qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                              CRMParameter.PLAN_BOOSTER.getParameter(), qrcDto );
                LOGGER.info( "Service Status : " + qrcDto.getStatusCode() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() )
                        && !StringUtils.isValidObj( qrcDto.getFailedCustomerMapList() ) )
                {
                    status = "success";
                }
                else
                {
                    LOGGER.info( "Billing Error ::" + qrcDto.getBillingErrorCode() );
                    if ( StringUtils.isValidObj( qrcDto.getFailedCustomerMapList() ) )
                    {
                        inExcelWriteUtils.addRowServiceFailed( qrcDto.getFailedCustomerMapList() );
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
            LOGGER.error( "Exception while storing valid mount booster : ", exe );
        }
        return status;
    }

    private String storeValidityExtension( List<ValidityExtensionPojo> inDataToProcess,
                                           BulkUploadForm inBulkUploadForm,
                                           String inUser,
                                           ExcelWriteUtils inExcelWriteUtils )
    {
        String status = "failed";
        CrmQrcDto qrcDto = new CrmQrcDto();
        try
        {
            if ( CommonValidator.isValidCollection( inDataToProcess ) )
            {
                qrcDto.setUserId( inUser );
                qrcDto.getValidityExtensionPojos().addAll( inDataToProcess );
                qrcDto.setRemarksPojo( inBulkUploadForm.getRemarksPojo() );
                qrcDto.setGracePeriodReason( inBulkUploadForm.getGracePeriodReason() );
                qrcDto = getQrcServiceClient().customerProfileOperations( ServiceParameter.SAVE.getParameter(),
                                                                          IAppConstants.MULTIPLE_GRACE_PERIOD, qrcDto );
                LOGGER.info( "Service Status : " + qrcDto.getStatusCode() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() )
                        && !StringUtils.isValidObj( qrcDto.getFailedCustomerMapList() ) )
                {
                    status = "success";
                }
                else
                {
                    LOGGER.info( "Customer should be Active and Barred" );
                    if ( StringUtils.isValidObj( qrcDto.getFailedCustomerMapList() ) )
                    {
                        inExcelWriteUtils.addRowServiceFailed( qrcDto.getFailedCustomerMapList() );
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
            LOGGER.error( "Exception while storing valid Validity Extension : ", exe );
        }
        return status;
    }

    @Override
    public List<String> processValidityExtensionExcel( BulkUploadForm inBulkUploadForm, String inUser, String inFilePath )
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
                                                                  inBulkUploadForm.getValidityExtensionExcelFile(),
                                                                  CRMParameter.VALIDITY_EXTENSION.getParameter() );
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
                excelWriteUtils = new ExcelWriteUtils( CRMParameter.VALIDITY_EXTENSION.getParameter() );
                @SuppressWarnings("unchecked")
                List<ValidityExtensionPojo> dataToProcess = excelReadUtils
                        .readFileData( inUser, workbook, inFilePath, excelWriteUtils, null,
                                       CRMParameter.VALIDITY_EXTENSION.getParameter() );
                if ( !StringUtils.isValidObj( dataToProcess ) )
                {
                    LOGGER.info( "Invalid File Header." );
                    statusList.add( "Invalid File Header." );
                }
                else
                {
                    String status = storeValidityExtension( dataToProcess, inBulkUploadForm, inUser, excelWriteUtils );
                    String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                                  CRMParameter.VALIDITY_EXTENSION.getParameter() );
                    statusList.add( status );
                    statusList.add( outPath );
                    statusList.add( excelReadUtils.getnRows() + "" );
                    statusList.add( excelReadUtils.getProcessRows() + "" );
                    LOGGER.info( "dataToProcess is not empty." );
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
}
