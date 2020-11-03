package com.np.tele.crm.gis.bm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.GREY_25_PERCENT;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.gis.pojo.GISUploadPojo;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto.FailedCustomerMapList;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.ValidityExtensionPojo;
import com.np.tele.crm.service.client.replicas.LmsExcelPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ExcelWriteUtils
{
    private static final Logger LOGGER      = Logger.getLogger( ExcelWriteUtils.class );
    FileOutputStream            fileOutputStream;
    XSSFWorkbook                xssfWorkbook;
    XSSFSheet                   sheet;
    private int                 rowIndex    = 1;
    private int                 rowIndexPod = 0;

    public int getRowIndexPod()
    {
        return rowIndexPod;
    }

    public void setRowIndexPod( int inRowIndexPod )
    {
        rowIndexPod = inRowIndexPod;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void setRowIndex( int inRowIndex )
    {
        rowIndex = inRowIndex;
    }

    public static String downloadUploadedFile( String inFilePath, String inUser, FormFile inFile, String inParameter )
    {
        FileOutputStream fileOutStream = null;
        File fileToCreate = null;
        String fileName = inFile.getFileName();
        if ( inFile.getFileName().endsWith( ".xls" ) )
        {
            fileName = saveAsName( inUser, inParameter ) + "_Uploaded.xls";
        }
        else if ( inFile.getFileName().endsWith( ".xlsx" ) )
        {
            fileName = saveAsName( inUser, inParameter ) + "_Uploaded.xlsx";
        }
        LOGGER.info( "path=" + inFilePath );
        try
        {
            File dir = new File( inFilePath );
            if ( !dir.exists() )
            {
                dir.mkdirs();
            }
            fileToCreate = new File( inFilePath, fileName );
            fileOutStream = new FileOutputStream( fileToCreate );
            fileOutStream.write( inFile.getFileData() );
            LOGGER.info( "File Uploaded : " + fileToCreate.getAbsolutePath() );
            fileOutStream.flush();
            fileOutStream.close();
        }
        catch ( FileNotFoundException ex )
        {
            LOGGER.error( "[Unable to create file : " + inFilePath + "/" + fileName + "] Reason : " + ex.getMessage() );
        }
        catch ( IOException ioex )
        {
            LOGGER.error( "[Unable to write to the file : " + inFilePath + "/" + fileName + "] Reason : "
                    + ioex.getMessage() );
        }
        finally
        {
            try
            {
                if ( fileOutStream != null )
                {
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
            catch ( IOException ex )
            {
                LOGGER.error( "[Unable to flush/close file : " + inFilePath + "/" + fileName + "] Reason : "
                        + ex.getMessage() );
            }
        }
        return fileToCreate.getAbsolutePath();
    }

    private static String saveAsName( final String inUserName, final String inParameter )
    {
        String saveAsName = "";
        GregorianCalendar curDate = new GregorianCalendar();
        curDate.setTime( new Date() );
        saveAsName = inParameter + "_" + inUserName + "_"
                + IDateConstants.SDF_YYYYMMDD_HHMMSS.format( curDate.getTime() );
        return saveAsName;
    }

    public ExcelWriteUtils( String inParameter )
    {
        xssfWorkbook = new XSSFWorkbook();
        if ( StringUtils.equals( inParameter, CRMParameter.MASS_OUTAGE.getParameter() ) )
        {
            sheet = xssfWorkbook.createSheet( "Input Data Status" );
        }
        else
        {
            sheet = xssfWorkbook.createSheet( "Invalid Data" );
        }
        XSSFFont font = xssfWorkbook.createFont();
        font.setBoldweight( (short) 700 );
        sheet.setDefaultColumnWidth( 25 );
        XSSFCellStyle headerStyle = xssfWorkbook.createCellStyle();
        headerStyle.setFillForegroundColor( GREY_25_PERCENT.index );
        headerStyle.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
        headerStyle.setFont( font );
        headerStyle.setAlignment( HorizontalAlignment.CENTER );
        headerStyle.setWrapText( true );
        headerStyle.setVerticalAlignment( VerticalAlignment.TOP );
        headerStyle.setLocked( true );
        int columnIndex = 0;
        XSSFRow headerRow = sheet.createRow( 0 );
        for ( FileHeaderConstants fileHeader : FileHeaderConstants.values() )
        {
            if ( StringUtils.equals( inParameter, fileHeader.getUpLoadType() ) )
            {
                XSSFCell headerCell1 = headerRow.createCell( columnIndex++ );
                headerCell1.setCellStyle( headerStyle );
                headerCell1.setCellValue( fileHeader.getValue() );
            }
        }
        if ( StringUtils.equals( inParameter, CRMParameter.MASS_OUTAGE.getParameter() ) )
        {
            XSSFCell headerCell2 = headerRow.createCell( columnIndex++ );
            headerCell2.setCellStyle( headerStyle );
            headerCell2.setCellValue( "Status" );
        }
        XSSFCell headerCell1 = headerRow.createCell( columnIndex );
        headerCell1.setCellStyle( headerStyle );
        headerCell1.setCellValue( CRMParameter.FILEERRORHEADER.getParameter() );
    }

    public String createExcel( final String inUser, final String inDownLoadPath, final String inParameter )
    {
        String outName = "";
        if ( StringUtils.equals( inParameter, CRMParameter.MASS_OUTAGE.getParameter() ) )
        {
            outName = saveAsName( inUser, inParameter ) + "_Status.xlsx";
        }
        else
        {
            outName = saveAsName( inUser, inParameter ) + "_Error.xlsx";
        }
        File file = new File( inDownLoadPath, outName );
        try
        {
            fileOutputStream = new FileOutputStream( file );
            xssfWorkbook.write( fileOutputStream );
            LOGGER.info( "File Created: " + file.getAbsolutePath() );
        }
        catch ( FileNotFoundException ex )
        {
            LOGGER.error( "ExcelWriteUtils:createExcel File not found.", ex );
        }
        catch ( IOException ioex )
        {
            LOGGER.error( "ExcelWriteUtils:createExcel I/O Exception.", ioex );
        }
        finally
        {
            try
            {
                if ( fileOutputStream != null )
                {
                    fileOutputStream.close();
                }
            }
            catch ( IOException ex )
            {
                LOGGER.error( "ExcelWriteUtils:createExcel I/O Exception.", ex );
            }
        }
        return outName;
    }

    /*Method to add rows in Error GIS File*/
    public void addRow( int inIndex,
                        final SocietyPojo inSocietyPojo,
                        final GISUploadPojo inGISMasterPojo,
                        final String inError )
    {
        XSSFRow dataRow = sheet.createRow( inIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i <= 22; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( inGISMasterPojo.getPincode() );
                        break;
                    case 1:
                        cell.setCellValue( inGISMasterPojo.getSecPincode() );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getState() ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getCity() ) );
                        break;
                    case 4:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getArea() ) );
                        break;
                    case 5:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getLocality() ) );
                        break;
                    case 6:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getSociety() ) );
                        break;
                    case 7:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getNetworkPartner() ) );
                        break;
                    case 8:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getNetworkType() ) );
                        break;
                    case 9:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getConnectableHomes() ) );
                        break;
                    case 10:
                        cell.setCellValue( inGISMasterPojo.getRfsDus() );
                        break;
                    case 11:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getLongitude() ) );
                        break;
                    case 12:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getLatitude() ) );
                        break;
                    case 13:
                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getCustomerCategory() ) );
                        break;
                    case 14:
                        if ( StringUtils.equals( inSocietyPojo.getNetworkAvailability(),
                                                 IAppConstants.GREEN_NETWORK_AVAILABILIY ) )
                            cell.setCellValue( new XSSFRichTextString( CRMParameter.GREEN.getParameter() ) );
                        else if ( StringUtils.equals( inSocietyPojo.getNetworkAvailability(),
                                                      IAppConstants.BROWN_NETWORK_AVAILABILIY ) )
                            cell.setCellValue( new XSSFRichTextString( CRMParameter.BROWN.getParameter() ) );
                        else
                            cell.setCellValue( "" );
                        break;
                    case 15:
                        cell.setCellValue( new XSSFRichTextString( inError ) );
                        break;
                //                    case 0:
                //                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inIndex ) ) );
                //                        break;
                //                    case 1:
                //                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getState() ) );
                //                        break;
                //                    case 2:
                //                        cell.setCellValue( inSocietyPojo.getSocietyId() );
                //                        break;
                //                    case 3:
                //                        cell.setCellValue( new XSSFRichTextString( inSocietyPojo.getSocietyName() ) );
                //                        break;
                //                    case 4:
                //                        if ( StringUtils.equals( inSocietyPojo.getNetworkAvailability(),
                //                                                 IAppConstants.GREEN_NETWORK_AVAILABILIY ) )
                //                            cell.setCellValue( new XSSFRichTextString( CRMParameter.GREEN.getParameter() ) );
                //                        else if ( StringUtils.equals( inSocietyPojo.getNetworkAvailability(),
                //                                                      IAppConstants.BROWN_NETWORK_AVAILABILIY ) )
                //                            cell.setCellValue( new XSSFRichTextString( CRMParameter.BROWN.getParameter() ) );
                //                        else
                //                            cell.setCellValue( "" );
                //                        break;
                //                    case 5:
                //                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getArea() ) );
                //                        break;
                //                    case 6:
                //                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getLocality() ) );
                //                        break;
                //                    case 7:
                //                        cell.setCellValue( new XSSFRichTextString( inGISMasterPojo.getCity() ) );
                //                        break;
                //                    case 8:
                //                        cell.setCellValue( inGISMasterPojo.getPincode() );
                //                        break;
                //                    case 9:
                //                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inSocietyPojo.getLatitude() ) ) );
                //                        break;
                //                    case 10:
                //                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inSocietyPojo.getLongitude() ) ) );
                //                        break;
                //                    case 11:
                //                        cell.setCellValue( new XSSFRichTextString( inSocietyPojo.getCustomerCategory() ) );
                //                        break;
                //                    case 12:
                //                        cell.setCellValue( new XSSFRichTextString( inError ) );
                //                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    /*Method to add rows in Error LMS File*/
    public void addRow( int inIndex, LmsExcelPojo inLmsExcelPojo, final String inError )
    {
        LOGGER.info( "Inside ExcelWriteUtils, addRow" );
        XSSFRow dataRow = sheet.createRow( inIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i <= 15; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inIndex ) ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getCustomerName() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inLmsExcelPojo.getContactNumber() ) ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getState() ) );
                        break;
                    case 4:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getCity() ) );
                        break;
                    case 5:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getArea() ) );
                        break;
                    case 6:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getLocality() ) );
                        break;
                    case 7:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getHouseNo() ) );
                        break;
                    case 8:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getLandMark() ) );
                        break;
                    case 9:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getService() ) );
                        break;
                    case 10:
                        cell.setCellValue( new XSSFRichTextString( inLmsExcelPojo.getRemarks() ) );
                        break;
                    case 11:
                        cell.setCellValue( new XSSFRichTextString( inError ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRow( final int inIndex, final CrmUpfrontPaymentPojo inUpfrontPaymentPojo, final String inError )
    {
        XSSFRow dataRow = sheet.createRow( inIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i < 8; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inIndex ) ) );
                        break;
                    case 1:
                        if ( CommonValidator.isValidCollection( inUpfrontPaymentPojo.getCrmUpCrfMappings() ) )
                        {
                            int size = inUpfrontPaymentPojo.getCrmUpCrfMappings().size();
                            CrmUpCrfMappingPojo crfMappingPojo = inUpfrontPaymentPojo.getCrmUpCrfMappings()
                                    .get( --size );
                            cell.setCellValue( new XSSFRichTextString( crfMappingPojo.getCrfId() ) );
                            inUpfrontPaymentPojo.getCrmUpCrfMappings().remove( crfMappingPojo );
                        }
                        else
                        {
                            cell.setCellValue( new XSSFRichTextString( "" ) );
                        }
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inUpfrontPaymentPojo.getChequeNo() ) ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inUpfrontPaymentPojo.getChequeDate() ) ) );
                        break;
                    case 4:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inUpfrontPaymentPojo.getBankId() ) ) );
                        break;
                    case 5:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inUpfrontPaymentPojo.getAmount() ) ) );
                        break;
                    case 6:
                        cell.setCellValue( new XSSFRichTextString( inUpfrontPaymentPojo.getEntityName() ) );
                        break;
                    case 7:
                        cell.setCellValue( new XSSFRichTextString( inError ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRow( int inCreateRowIndex, CrmQrcWhitelistPojo inQrcWhitelistPojo, String inString )
    {
        XSSFRow dataRow = sheet.createRow( inCreateRowIndex );
        setRowIndex( inCreateRowIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i <= 5; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inCreateRowIndex ) ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( inQrcWhitelistPojo.getCustomerId() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( inString ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRowServiceFail( FailedCustomerMapList inFailedCustomerMapList )
    {
        LOGGER.info( "Inside addRowServiceFail" );
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            for ( FailedCustomerMapList.Entry entry : inFailedCustomerMapList.getEntry() )
            {
                dataRow = sheet.createRow( rowIndex );
                for ( int i = 0; i <= 5; i++ )
                {
                    cell = dataRow.createCell( i );
                    switch ( i )
                    {
                        case 0:
                            cell.setCellValue( new XSSFRichTextString( String.valueOf( rowIndex++ ) ) );
                            break;
                        case 1:
                            cell.setCellValue( new XSSFRichTextString( entry.getKey() ) );
                            break;
                        case 2:
                            cell.setCellValue( new XSSFRichTextString( entry.getValue() ) );
                            break;
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRowServiceFailed( FailedCustomerMapList inFailedCustomerMapList )
    {
        LOGGER.info( "Inside addRowServiceFailed" );
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            for ( FailedCustomerMapList.Entry entry : inFailedCustomerMapList.getEntry() )
            {
                dataRow = sheet.createRow( rowIndex );
                for ( int i = 0; i < 4; i++ )
                {
                    cell = dataRow.createCell( i );
                    switch ( i )
                    {
                        case 0:
                            rowIndex++;
                            cell.setCellValue( new XSSFRichTextString( entry.getKey() ) );
                            break;
                        case 1:
                            cell.setCellValue( new XSSFRichTextString( IAppConstants.SPACE ) );
                            break;
                        case 2:
                            cell.setCellValue( new XSSFRichTextString( IAppConstants.SPACE ) );
                            break;
                        case 3:
                            cell.setCellValue( new XSSFRichTextString( entry.getValue() ) );
                            break;
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while creating row for Error File : ", ex );
        }
    }

    public void addRow( int inCreateRowIndex, CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, String inString )
    {
        XSSFRow dataRow = sheet.createRow( inCreateRowIndex );
        setRowIndex( inCreateRowIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i <= 5; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inCreateRowIndex ) ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( inCrmCustomerDetailsPojo.getCustomerId() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( inString ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    /*Method to add rows in Error validityExtension File*/
    public void addRow( int inCreateRowIndex,
                        ValidityExtensionPojo validityExtensionCustomerDetailPojo,
                        String inError )
    {
        XSSFRow dataRow = sheet.createRow( inCreateRowIndex );
        setRowIndex( inCreateRowIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i < 4; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( validityExtensionCustomerDetailPojo.getCustomerId() ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( validityExtensionCustomerDetailPojo
                                .getExtensionDays() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( validityExtensionCustomerDetailPojo.getTicketId() ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( inError ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    /*Method to add rows in Error POD File*/
    public void addRow( int inCreateRowIndex, CrmPodDetailsPojo inCrmPodDeatilsPojo, String inString )
    {
        XSSFRow dataRow = sheet.createRow( inCreateRowIndex );
        setRowIndexPod( inCreateRowIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i <= 9; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inCreateRowIndex ) ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getCustomerId() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getReceiverName() ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inCrmPodDeatilsPojo
                                .getDeliveredDate() ) ) );
                        break;
                    case 4:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getCustomerRelation() ) );
                        break;
                    case 5:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inCrmPodDeatilsPojo
                                .getContactNumber() ) ) );
                        break;
                    case 6:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getDeliveryStatus() ) );
                        break;
                    case 7:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getNonDeliveryReason() ) );
                        break;
                    case 8:
                        cell.setCellValue( new XSSFRichTextString( inCrmPodDeatilsPojo.getInstrumentNumber() ) );
                        break;
                    case 9:
                        cell.setCellValue( new XSSFRichTextString( inString ) );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRowPOD( List<CrmPodDetailsPojo> inCrmPodDetailsPojos )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            for ( CrmPodDetailsPojo crPodDetailsPojo : inCrmPodDetailsPojos )
            {
                dataRow = sheet.createRow( ++rowIndexPod );
                for ( int i = 0; i <= 9; i++ )
                {
                    cell = dataRow.createCell( i );
                    switch ( i )
                    {
                        case 0:
                            cell.setCellValue( new XSSFRichTextString( String.valueOf( rowIndexPod ) ) );
                            break;
                        case 1:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getCustomerId() ) );
                            LOGGER.info( "Add Row for:::" + crPodDetailsPojo.getCustomerId() );
                            break;
                        case 2:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getReceiverName() ) );
                            break;
                        case 3:
                            cell.setCellValue( new XSSFRichTextString( String.valueOf( crPodDetailsPojo
                                    .getDeliveredDate() ) ) );
                            break;
                        case 4:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getCustomerRelation() ) );
                            break;
                        case 5:
                            cell.setCellValue( new XSSFRichTextString( String.valueOf( crPodDetailsPojo
                                    .getContactNumber() ) ) );
                            break;
                        case 6:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getDeliveryStatus() ) );
                            break;
                        case 7:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getNonDeliveryReason() ) );
                            break;
                        case 8:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getInstrumentNumber() ) );
                            break;
                        case 9:
                            cell.setCellValue( new XSSFRichTextString( crPodDetailsPojo.getProcessFailureReason() ) );
                            break;
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void addRow( int inCreateRowIndex, CrmCustAssetDetailsPojo inAssetDetailsPojo, String inError )
    {
        XSSFRow dataRow = sheet.createRow( inCreateRowIndex );
        setRowIndex( inCreateRowIndex );
        XSSFCell cell = null;
        try
        {
            for ( int i = 0; i < 4; i++ )
            {
                cell = dataRow.createCell( i );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( new XSSFRichTextString( inAssetDetailsPojo.getCategoryName() ) );
                        break;
                    case 1:
                        cell.setCellValue( new XSSFRichTextString( inAssetDetailsPojo.getCategoryValue() ) );
                        break;
                    case 2:
                        cell.setCellValue( new XSSFRichTextString( String.valueOf( inAssetDetailsPojo
                                .getCategoryCount() ) ) );
                        break;
                    case 3:
                        cell.setCellValue( new XSSFRichTextString( inError ) );
                        break;
                }
            }
            LOGGER.info( "Mount Booster ...inError ::" + inError );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }
}
