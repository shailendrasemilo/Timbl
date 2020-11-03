package com.np.tele.crm.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.UsageHeader.TopUsageHeader;
import com.np.tele.crm.utils.UsageHeader.UsageHeaderGB;
import com.np.tele.crm.utils.UsageHeader.UsageHeaderGB.TopUsageHeaderGB;

public class ExcelWriteUtils
{
    private static final Logger LOGGER       = Logger.getLogger( ExcelWriteUtils.class );
    private FileOutputStream    fileOutputStream;
    private XSSFWorkbook        xssfWorkbook;
    private XSSFSheet           sheet;
    private XSSFSheet           sheet2;
    private String              customerName;
    private String              customerID;
    private String              fileName;
    private File                createdExcel;
    private int                 rowIndex     = 0;
    private int                 rowIndexGB   = 0;
    private int                 dataStartRow = 10;
    private int                 dataEndRow   = 0;
    private int                 dataEndRowGB = 0;

    public File getCreatedExcel()
    {
        return createdExcel;
    }

    public void setCreatedExcel( File inCreatedExcel )
    {
        createdExcel = inCreatedExcel;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName( String inFileName )
    {
        fileName = inFileName;
    }

    private String saveAsName( final String inCustomerID )
    {
        String saveAsName = "";
        GregorianCalendar curDate = new GregorianCalendar();
        curDate.setTime( new Date() );
        saveAsName = "CustomerUsage_" + inCustomerID + "_"
                + IDateConstants.SDF_DD_MM_YYYY_HH_MM_SS.format( curDate.getTime() );
        return saveAsName;
    }

    public ExcelWriteUtils( String inCustomerName, String inCustomerID, String inFilePath )
    {
        customerName = inCustomerName;
        customerID = inCustomerID;
        fileName = inFilePath;
        xssfWorkbook = new XSSFWorkbook();
        sheet = xssfWorkbook.createSheet( "Session Wise Usage " + customerID );
        sheet2 = xssfWorkbook.createSheet( "Day Wise Usage " + customerID );
        sheet.setDefaultColumnWidth( 15 );
        sheet.createFreezePane( 0, 9 );
        sheet2.setDefaultColumnWidth( 16 );
        sheet2.createFreezePane( 0, 9 );
    }

    public void createExcel()
    {
        File dir = null;
        File file = null;
        String outName = "";
        outName = saveAsName( customerID ) + ".xlsx";
        try
        {
            dir = new File( fileName );
            if ( !dir.exists() )
            {
                dir.mkdirs();
            }
            file = new File( fileName, outName ); // change path to live
            fileOutputStream = new FileOutputStream( file );
            xssfWorkbook.write( fileOutputStream );
            createdExcel = file;
            fileName = outName;
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
    }

    public void prepareTopRows( String inUsageType, String inFormDate, String inToDate )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        XSSFCell date_cell = null;
        try
        {
            dataRow = sheet.createRow( rowIndex );
            cell = dataRow.createCell( 0 );
            cell.setCellStyle( defineCellStyle( true, false, false, false, false, true ) );
            cell.setCellValue( " Usage Details for " + customerName + ", Customer ID: " + customerID );
            mergeCellsWithBoader( sheet, dataRow, rowIndex, rowIndex, 0, 6 );
            rowIndex++;
            dataRow = sheet.createRow( rowIndex );
            if ( StringUtils.isNotBlank( inFormDate ) && StringUtils.isNotBlank( inToDate ) )
            {
                String formDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY.parse( inFormDate ),
                                                              IDateConstants.SDF_DD_MMM_YYYY );
                String toDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY.parse( inToDate ),
                                                            IDateConstants.SDF_DD_MMM_YYYY );
                dataRow = sheet.createRow( 1 );
                date_cell = dataRow.createCell( 0 );
                date_cell.setCellStyle( defineCellStyle( true, false, false, false, false, true ) );
                date_cell.setCellValue( "From " + formDate + " to " + toDate );
            }
            mergeCellsWithBoader( sheet, dataRow, rowIndex, rowIndex, 0, 6 );
            rowIndex++;
            dataRow = sheet.createRow( rowIndex );
            sheet.setDisplayGridlines( false );
            // mergeCellsWithBoader( dataRow, rowIndex, rowIndex, 1, 6 );
            /*  XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
              xssfCellStyle.setFillBackgroundColor(  new XSSFColor(Color.WHITE) );
              xssfCellStyle.setFillForegroundColor( new XSSFColor(Color.WHITE));*/
            rowIndex++;
            sheet.createRow( rowIndex++ );
            sheet.createRow( rowIndex++ );
            sheet.createRow( rowIndex++ );
            sheet.createRow( rowIndex++ );
            dataRow = sheet.createRow( rowIndex );
            sheet.setDisplayGridlines( false );
            // mergeCellsWithBoader( dataRow, rowIndex, rowIndex, 1, 6 );
            rowIndex++;
            dataRow = sheet.createRow( rowIndex++ );
            UsageHeader.prepareHeader( dataRow, defineCellStyle( true, false, false, false, false, false ) );
            sheet.addMergedRegion( new CellRangeAddress( 2, 7, 0, 1 ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while creating top rows ", ex );
        }
    }

    public void prepareTopRowsForGB( String inUsageType, String inFormDate, String inToDate )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        XSSFCell date_cell = null;
        try
        {
            dataRow = sheet2.createRow( rowIndexGB );
            cell = dataRow.createCell( 0 );
            cell.setCellStyle( defineCellStyleGB( true, false, false, false, false, true ) );
            cell.setCellValue( " Usage Details for " + customerName + ", Customer ID: " + customerID );
            mergeCellsWithBoader( sheet2, dataRow, rowIndexGB, rowIndexGB, 0, 4 );
            rowIndexGB++;
            dataRow = sheet2.createRow( rowIndexGB );
            if ( StringUtils.isNotBlank( inFormDate ) && StringUtils.isNotBlank( inToDate ) )
            {
                String formDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY.parse( inFormDate ),
                                                              IDateConstants.SDF_DD_MMM_YYYY );
                String toDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY.parse( inToDate ),
                                                            IDateConstants.SDF_DD_MMM_YYYY );
                dataRow = sheet2.createRow( 1 );
                date_cell = dataRow.createCell( 0 );
                date_cell.setCellStyle( defineCellStyleGB( true, false, false, false, false, true ) );
                date_cell.setCellValue( "From " + formDate + " to " + toDate );
            }
            mergeCellsWithBoader( sheet2, dataRow, rowIndexGB, rowIndexGB, 0, 4 );
            rowIndexGB++;
            dataRow = sheet2.createRow( rowIndexGB );
            sheet2.setDisplayGridlines( false );
            rowIndexGB++;
            sheet2.createRow( rowIndexGB++ );
            sheet2.createRow( rowIndexGB++ );
            sheet2.createRow( rowIndexGB++ );
            sheet2.createRow( rowIndexGB++ );
            dataRow = sheet2.createRow( rowIndexGB );
            sheet2.setDisplayGridlines( false );
            rowIndexGB++;
            dataRow = sheet2.createRow( rowIndexGB++ );
            UsageHeaderGB.prepareHeaderGB( dataRow, defineCellStyle( true, false, false, false, false, false ) );
            // sheet2.addMergedRegion( new CellRangeAddress( 2, 7, 0, 1 ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while creating top rows for GB", ex );
        }
    }

    /*    public void addUsageDetailsRow( final String inFramedIP,
                                        final Date inStartTime,
                                        final Date inEndTime,
                                        final long inUploadInKB,
                                        final long inDownloadInKB )
        {
            XSSFRow dataRow = null;
            XSSFCell cell = null;
            try
            {
                dataRow = sheet.createRow( dataStartRow++ );
                for ( int i = 0; i <= 6; i++ )
                {
                    cell = dataRow.createCell( i );
                    cell.setCellStyle( defineCellStyle( false, false, false, false, true ) );
                    switch ( i )
                    {
                        case 0:
                            cell.setCellValue( new XSSFRichTextString( inFramedIP ) );
                            break;
                        case 1:
                            cell.setCellValue( DateUtils.getFormattedDate( inStartTime,
                                                                           IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                            break;
                        case 2:
                            cell.setCellValue( DateUtils.getFormattedDate( inEndTime,
                                                                           IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                            break;
                        case 3:
                            cell.setCellValue( Double.valueOf( inUploadInKB ) );
                            break;
                        case 4:
                            cell.setCellValue( Double.valueOf( inDownloadInKB ) );
                            break;
                        case 5:
                            String totalInKB = "D" + dataStartRow + "+E" + dataStartRow;
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( totalInKB );
                            break;
                        case 6:
                            String upDownRatio = "IFERROR(D" + dataStartRow + "/E" + dataStartRow + ",\"0%\")";
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellStyle( defineCellStyle( false, true, false, false, true ) );
                            cell.setCellFormula( upDownRatio );
                            break;
                    }
                }
                dataEndRow = dataStartRow;
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Error while creating row for Error File : ", ex );
            }
        }*/
    public void prepareBottomRows()
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        int tempRowIndex = 3;
        try
        {
            dataRow = sheet.getRow( tempRowIndex );
            TopUsageHeader.prepareTopHeader( dataRow, defineCellStyle( true, false, false, false, false, false ) );
            //mergeCellsWithBoader( dataRow, tempRowIndex, tempRowIndex, 0, 1 );
            tempRowIndex++;
            for ( int z = 0; z <= 2; z++ )
            {
                dataRow = sheet.getRow( tempRowIndex );
                cell = dataRow.createCell( 2 );
                cell.setCellStyle( defineCellStyle( true, false, false, false, false, false ) );
                switch ( z )
                {
                    case 0:
                        cell.setCellValue( "KB" );
                        break;
                    case 1:
                        cell.setCellValue( "MB" );
                        break;
                    case 2:
                        cell.setCellValue( "GB" );
                        break;
                }
                // mergeCellsWithBoader( dataRow, tempRowIndex, tempRowIndex, 0, 1 );
                tempRowIndex++;
                for ( int j = 3; j <= 6; j++ )
                {
                    cell = dataRow.createCell( j );
                    cell.setCellStyle( defineCellStyle( true, false, false, false, true, false ) );
                    switch ( j )
                    {
                        case 3:
                            String totalUpload = null;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    totalUpload = "SUBTOTAL(" + 9 + ",D" + 10 + ":D" + dataEndRow + ")";
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    totalUpload = "D" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyle( true, false, false, true, true, false ) );
                                    totalUpload = "D" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                            }
                            LOGGER.info( "totalUpload:" + totalUpload );
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( totalUpload );
                            break;
                        case 4:
                            String totalDownload = null;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    totalDownload = "SUBTOTAL(" + 9 + ",E" + 10 + ":E" + dataEndRow + ")";
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    totalDownload = "E" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyle( true, false, false, true, true, false ) );
                                    totalDownload = "E" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                            }
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( totalDownload );
                            break;
                        case 5:
                            String total = "D" + tempRowIndex + "+E" + tempRowIndex;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                                    totalDownload = "E" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyle( true, false, false, true, true, false ) );
                                    totalDownload = "E" + ( tempRowIndex - 1 ) + "/1024";
                                    break;
                            }
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( total );
                            break;
                        case 6:
                            String upDownRatio = "IFERROR(D" + tempRowIndex + "/F" + tempRowIndex + ",\"0%\")";
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellStyle( defineCellStyle( true, true, false, false, true, false ) );
                            cell.setCellFormula( upDownRatio );
                            break;
                    }
                }
            }
            /*         for ( UsageHeader usageHeader : UsageHeader.values() )
                     {
            //                sheet.autoSizeColumn( usageHeader.getIndex() );
                         sheet.setColumnWidth( usageHeader.getIndex(), usageHeader.getSize() );
                     }*/
            sheet.addMergedRegion( new CellRangeAddress( 0, sheet.getPhysicalNumberOfRows() + 100, 7, 50 ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while creating bottom rows ", ex );
        }
    }

    public void prepareBottomRowsForGB()
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        int tempRowIndex = 3;
        try
        {
            dataRow = sheet2.getRow( tempRowIndex );
            TopUsageHeaderGB.prepareTopHeaderGB( dataRow, defineCellStyle( true, false, false, false, false, false ) );
            tempRowIndex++;
            for ( int z = 0; z <= 2; z++ )
            {
                dataRow = sheet2.getRow( tempRowIndex );
                cell = dataRow.createCell( 0 );
                cell.setCellStyle( defineCellStyle( true, false, false, false, false, false ) );
                switch ( z )
                {
                    case 0:
                        cell.setCellValue( "KB" );
                        break;
                    case 1:
                        cell.setCellValue( "MB" );
                        break;
                    case 2:
                        cell.setCellValue( "GB" );
                        break;
                }
                tempRowIndex++;
                for ( int j = 1; j <= 4; j++ )
                {
                    cell = dataRow.createCell( j );
                    cell.setCellStyle( defineCellStyle( true, false, false, false, true, false ) );
                    switch ( j )
                    {
                        case 1:
                            String totalUpload = null;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalUpload = "B" + ( tempRowIndex + 1 ) + "*1024";
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalUpload = "B" + ( tempRowIndex + 1 ) + "*1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalUpload = "SUBTOTAL(" + 9 + ",B" + 10 + ":B" + dataEndRowGB + ")";
                                    break;
                            }
                            LOGGER.info( "totalUpload:" + totalUpload );
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( totalUpload );
                            break;
                        case 2:
                            String totalDownload = null;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalDownload = "C" + ( tempRowIndex + 1 ) + "*1024";
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyleGB( true, false, false, true, true, false ) );
                                    totalDownload = "C" + ( tempRowIndex + 1 ) + "*1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalDownload = "SUBTOTAL(" + 9 + ",C" + 10 + ":C" + dataEndRowGB + ")";
                                    break;
                            }
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( totalDownload );
                            break;
                        case 3:
                            String total = "B" + tempRowIndex + "+C" + tempRowIndex;
                            switch ( z )
                            {
                                case 0:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    break;
                                case 1:
                                    cell.setCellStyle( defineCellStyleGB( true, false, true, true, true, false ) );
                                    totalDownload = "C" + ( tempRowIndex + 1 ) + "/1024";
                                    break;
                                case 2:
                                    cell.setCellStyle( defineCellStyleGB( true, false, false, true, true, false ) );
                                    totalDownload = "C" + ( tempRowIndex + 1 ) + "/1024";
                                    break;
                            }
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellFormula( total );
                            break;
                        case 4:
                            String upDownRatio = "IFERROR(B" + tempRowIndex + "/D" + tempRowIndex + ",\"0%\")";
                            cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                            cell.setCellStyle( defineCellStyle( true, true, false, false, true, false ) );
                            cell.setCellFormula( upDownRatio );
                            break;
                    }
                }
            }
            sheet2.addMergedRegion( new CellRangeAddress( 0, sheet2.getPhysicalNumberOfRows() + 100, 5, 50 ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while creating bottom rows ", ex );
        }
    }

    private void mergeCellsWithBoader( Sheet inSheet,
                                       XSSFRow inXSSFRow,
                                       int inFirstRow,
                                       int inLastRow,
                                       int inFirstColumn,
                                       int inLastColumn )
    {
        XSSFCell cell = null;
        for ( int i = 0; i <= inLastColumn; i++ )
        {
            if ( !StringUtils.isValidObj( inXSSFRow.getCell( i ) ) )
            {
                cell = inXSSFRow.createCell( i );
                cell.setCellStyle( defineCellStyle( false, false, false, false, false, false ) );
            }
        }
        inSheet.addMergedRegion( new CellRangeAddress( inFirstRow, inLastRow, inFirstColumn, inLastColumn ) );
    }

    private XSSFCellStyle defineCellStyle( boolean isHeader,
                                           boolean isPercentDisplay,
                                           boolean isMBPresentation,
                                           boolean isGBPresentation,
                                           boolean isRightAlign,
                                           boolean isTopHeaderAlign )
    {
        XSSFFont font = xssfWorkbook.createFont();
        XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
        if ( isHeader )
        {
            font.setBoldweight( (short) 700 );
            font.setColor( new XSSFColor( Color.BLACK ) );
            xssfCellStyle.setFillForegroundColor( new XSSFColor( Color.gray ) );
            xssfCellStyle.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
            xssfCellStyle.setWrapText( true );
            if ( isTopHeaderAlign )
            {
                xssfCellStyle.setAlignment( HorizontalAlignment.CENTER );
            }
            else
                xssfCellStyle.setAlignment( HorizontalAlignment.RIGHT );
        }
        if ( isRightAlign )
        {
            xssfCellStyle.setAlignment( HorizontalAlignment.RIGHT );
        }
        xssfCellStyle.setFont( font );
        xssfCellStyle.setWrapText( true );
        xssfCellStyle.setVerticalAlignment( VerticalAlignment.TOP );
        xssfCellStyle.setLocked( true );
        xssfCellStyle.setBorderLeft( (short) 1 );
        xssfCellStyle.setBorderBottom( (short) 1 );
        xssfCellStyle.setBorderRight( (short) 1 );
        xssfCellStyle.setBorderTop( (short) 1 );
        if ( isPercentDisplay )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "00%" ) );
        }
        if ( isMBPresentation )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "#,##0" ) );
        }
        if ( isGBPresentation )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "#,##0.00" ) );
        }
        return xssfCellStyle;
    }

    private XSSFCellStyle defineCellStyleGB( boolean isHeader,
                                             boolean isPercentDisplay,
                                             boolean isMBPresentation,
                                             boolean isGBPresentation,
                                             boolean isRightAlign,
                                             boolean isTopHeaderAlign )
    {
        XSSFFont font = xssfWorkbook.createFont();
        XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
        if ( isHeader )
        {
            font.setBoldweight( (short) 700 );
            font.setColor( new XSSFColor( Color.BLACK ) );
            xssfCellStyle.setFillForegroundColor( new XSSFColor( Color.gray ) );
            xssfCellStyle.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
            xssfCellStyle.setWrapText( true );
            if ( isTopHeaderAlign )
            {
                xssfCellStyle.setAlignment( HorizontalAlignment.CENTER );
            }
            else
                xssfCellStyle.setAlignment( HorizontalAlignment.RIGHT );
        }
        if ( isRightAlign )
        {
            xssfCellStyle.setAlignment( HorizontalAlignment.RIGHT );
        }
        xssfCellStyle.setFont( font );
        xssfCellStyle.setWrapText( true );
        xssfCellStyle.setVerticalAlignment( VerticalAlignment.TOP );
        xssfCellStyle.setLocked( true );
        xssfCellStyle.setBorderLeft( (short) 1 );
        xssfCellStyle.setBorderBottom( (short) 1 );
        xssfCellStyle.setBorderRight( (short) 1 );
        xssfCellStyle.setBorderTop( (short) 1 );
        if ( isPercentDisplay )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "00%" ) );
        }
        if ( isMBPresentation )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "#,##0.00" ) );
        }
        if ( isGBPresentation )
        {
            xssfCellStyle.setDataFormat( xssfWorkbook.createDataFormat().getFormat( "#,##0.00" ) );
        }
        return xssfCellStyle;
    }

    //Calling from QRC Action 
    public void addUsageDetailsRow( String inDate,
                                    XMLGregorianCalendar inStartTime,
                                    XMLGregorianCalendar inEndTime,
                                    BigDecimal inUploadInKB,
                                    BigDecimal inDownloadInKB )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet.createRow( rowIndex++ );
            for ( int i = 0; i <= 6; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyle( false, false, false, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( DateUtils.getFormattedDate( new SimpleDateFormat( "dd MMM yyyy" )
                                .parse( inDate ), new SimpleDateFormat( IDateConstants.FORMAT_DD_MMM_YYYY ) ) );
                        break;
                    case 1:
                        cell.setCellValue( DateUtils.convertXmlCalToString( inStartTime, IDateConstants.SDF_HH_MM_SS ) );
                        break;
                    case 2:
                        cell.setCellValue( DateUtils.convertXmlCalToString( inEndTime, IDateConstants.SDF_HH_MM_SS ) );
                        break;
                    case 3:
                        cell.setCellValue( Math.round( inUploadInKB.doubleValue() ) );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 4:
                        cell.setCellValue( Math.round( inDownloadInKB.doubleValue() ) );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 5:
                        String totalInKB = "D" + rowIndex + "+E" + rowIndex;
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 6:
                        String upDownRatio = "IFERROR(D" + rowIndex + "/F" + rowIndex + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyle( false, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
            dataEndRow = rowIndex;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    //Calling from QRC Action for GB
    public void addUsageDetailsRow( XMLGregorianCalendar inEndTime, BigDecimal inUploadInKB, BigDecimal inDownloadInKB )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet2.createRow( rowIndexGB++ );
            for ( int i = 0; i <= 4; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyle( false, false, false, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( DateUtils.convertXmlCalToString( inEndTime, IDateConstants.SDF_DD_MMM_YYYY ) );
                        break;
                    case 1:
                        cell.setCellValue( inUploadInKB.doubleValue() );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 2:
                        cell.setCellValue( inDownloadInKB.doubleValue() );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 3:
                        String totalInKB = "B" + rowIndexGB + "+C" + rowIndexGB;
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 4:
                        String upDownRatio = "IFERROR(B" + rowIndexGB + "/D" + rowIndexGB + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyleGB( false, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
            dataEndRowGB = rowIndexGB;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    //Calling from DAO layer for GB
    public void addUsageDetailsRow( Date inEndTime, BigDecimal inUploadInKB, BigDecimal inDownloadInKB )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet2.createRow( rowIndexGB++ );
            for ( int i = 0; i <= 4; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyle( false, false, false, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( DateUtils.getFormattedDate( inEndTime, IDateConstants.SDF_DD_MMM_YYYY ) );
                        break;
                    case 1:
                        cell.setCellValue( inUploadInKB.doubleValue() );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 2:
                        cell.setCellValue( inDownloadInKB.doubleValue() );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 3:
                        String totalInKB = "B" + rowIndexGB + "+C" + rowIndexGB;
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        cell.setCellStyle( defineCellStyleGB( false, false, true, false, false, false ) );
                        break;
                    case 4:
                        String upDownRatio = "IFERROR(B" + rowIndexGB + "/D" + rowIndexGB + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyleGB( false, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
            dataEndRowGB = rowIndexGB;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void lastRowCalculate()
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet.createRow( rowIndex++ );
            for ( int i = 0; i <= 6; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyle( true, false, true, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( "Total" );
                        break;
                    case 1:
                        cell.setCellValue( IAppConstants.SPACE );
                        break;
                    case 2:
                        cell.setCellValue( IAppConstants.SPACE );
                        break;
                    case 3:
                        String totalUpload = "SUM(D" + dataStartRow + ":D" + dataEndRow + ")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalUpload );
                        break;
                    case 4:
                        String totalDownload = "SUM(E" + dataStartRow + ":E" + dataEndRow + ")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalDownload );
                        break;
                    case 5:
                        String totalInKB = "D" + ( dataEndRow + 1 ) + "+E" + ( dataEndRow + 1 );
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        break;
                    case 6:
                        String upDownRatio = "IFERROR(D" + ( dataEndRow + 1 ) + "/F" + ( dataEndRow + 1 ) + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyle( true, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }

    public void lastRowCalculateForGB()
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet2.createRow( rowIndexGB++ );
            for ( int i = 0; i <= 4; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyleGB( true, false, true, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( "Total" );
                        break;
                    case 1:
                        String totalUpload = "SUM(B" + dataStartRow + ":B" + dataEndRowGB + ")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalUpload );
                        break;
                    case 2:
                        String totalDownload = "SUM(C" + dataStartRow + ":C" + dataEndRowGB + ")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalDownload );
                        break;
                    case 3:
                        String totalInKB = "B" + ( dataEndRowGB + 1 ) + "+C" + ( dataEndRowGB + 1 );
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        break;
                    case 4:
                        String upDownRatio = "IFERROR(B" + ( dataEndRowGB + 1 ) + "/D" + ( dataEndRowGB + 1 )
                                + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyle( true, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating last row in GB for Error File : ", ex );
        }
    }

    //Calling from DAO layer
    public void addUsageDetailsRow( String inCallEndDate,
                                    Date inStartTime,
                                    Date inEndTime,
                                    BigDecimal inUploadKB,
                                    BigDecimal inDownloadKB )
    {
        XSSFRow dataRow = null;
        XSSFCell cell = null;
        try
        {
            dataRow = sheet.createRow( rowIndex++ );
            for ( int i = 0; i <= 6; i++ )
            {
                cell = dataRow.createCell( i );
                cell.setCellStyle( defineCellStyle( false, false, false, false, true, false ) );
                switch ( i )
                {
                    case 0:
                        cell.setCellValue( DateUtils.getFormattedDate( new SimpleDateFormat( "dd MMM yyyy" )
                                .parse( inCallEndDate ), new SimpleDateFormat( "dd/MMM/yyyy" ) ) );
                        break;
                    case 1:
                        cell.setCellValue( DateUtils.getFormattedDate( inStartTime, IDateConstants.SDF_HH_MM_SS ) );
                        break;
                    case 2:
                        cell.setCellValue( DateUtils.getFormattedDate( inEndTime, IDateConstants.SDF_HH_MM_SS ) );
                        break;
                    case 3:
                        if ( StringUtils.isValidObj( inUploadKB ) && inUploadKB.doubleValue() > 0 )
                        {
                            cell.setCellValue( inUploadKB.doubleValue() );
                        }
                        else
                            cell.setCellValue( 0 );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 4:
                        if ( StringUtils.isValidObj( inDownloadKB ) && inDownloadKB.doubleValue() > 0 )
                        {
                            cell.setCellValue( inDownloadKB.doubleValue() );
                        }
                        else
                            cell.setCellValue( 0 );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 5:
                        String totalInKB = "D" + rowIndex + "+E" + rowIndex;
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellFormula( totalInKB );
                        cell.setCellStyle( defineCellStyle( false, false, true, false, false, false ) );
                        break;
                    case 6:
                        String upDownRatio = "IFERROR(D" + rowIndex + "/F" + rowIndex + ",\"0%\")";
                        cell.setCellType( XSSFCell.CELL_TYPE_FORMULA );
                        cell.setCellStyle( defineCellStyle( false, true, false, false, true, false ) );
                        cell.setCellFormula( upDownRatio );
                        break;
                }
            }
            dataEndRow = rowIndex;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while creating row for Error File : ", ex );
        }
    }
}
enum UsageHeader {
    DATE(0, "DATE", 13), START_TIME(1, "START TIME", 8), END_TIME(2, "END TIME", 8), UPLOAD_IN_KB(3, "UPLOAD (KB)", 11), DOWNLOAD_IN_KB(
            4, "DOWNLOAD (KB)", 12), TOTAL_KB(5, "   TOTAL (KB)", 13), UPLOAD_PERCENTAGE(6, "UPLOAD %", 12);
    private int    index;
    private String title;
    private int    size;

    private UsageHeader( int inIndex, String inTitle, int inSize )
    {
        index = inIndex;
        title = inTitle;
        size = inSize;
    }

    public int getIndex()
    {
        return index;
    }

    public String getTitle()
    {
        return title;
    }

    public int getSize()
    {
        return size;
    }

    public static void prepareHeader( XSSFRow dataRow, XSSFCellStyle xssfCellStyle )
    {
        XSSFCell cell = null;
        for ( UsageHeader usageHeader : UsageHeader.values() )
        {
            cell = dataRow.createCell( usageHeader.getIndex() );
            cell.setCellStyle( xssfCellStyle );
            cell.setCellValue( usageHeader.getTitle() );
        }
    }
    enum TopUsageHeader {
        BLANK_FIRST(2, ""), UPLOAD(3, "UPLOAD"), DOWNLOAD(4, "DOWNLOAD"), TOTAL(5, "TOTAL"), UPLOAD_PERCENTAGE(6,
                "UPLOAD %"), ;
        private int    index;
        private String title;

        private TopUsageHeader( int inIndex, String inTitle )
        {
            index = inIndex;
            title = inTitle;
        }

        public int getIndex()
        {
            return index;
        }

        public String getTitle()
        {
            return title;
        }

        public static void prepareTopHeader( XSSFRow dataRow, XSSFCellStyle xssfCellStyle )
        {
            XSSFCell cell = null;
            for ( TopUsageHeader usageTopHeader : TopUsageHeader.values() )
            {
                cell = dataRow.createCell( usageTopHeader.getIndex() );
                cell.setCellStyle( xssfCellStyle );
                cell.setCellValue( usageTopHeader.getTitle() );
            }
        }
    }
    enum UsageHeaderGB {
        DATE(0, "DATE", 13), UPLOAD_IN_GB(1, "UPLOAD (GB)", 11), DOWNLOAD_IN_GB(2, "DOWNLOAD (GB)", 12), TOTAL_GB(3,
                "   TOTAL (GB)", 13), UPLOAD_PERCENTAGE(4, "UPLOAD %", 12), ;
        private int    index;
        private String title;
        private int    size;

        private UsageHeaderGB( int inIndex, String inTitle, int inSize )
        {
            index = inIndex;
            title = inTitle;
            size = inSize;
        }

        public int getIndex()
        {
            return index;
        }

        public String getTitle()
        {
            return title;
        }

        public int getSize()
        {
            return size;
        }

        public static void prepareHeaderGB( XSSFRow dataRow, XSSFCellStyle xssfCellStyle )
        {
            XSSFCell cell = null;
            for ( UsageHeaderGB usageHeader : UsageHeaderGB.values() )
            {
                cell = dataRow.createCell( usageHeader.getIndex() );
                cell.setCellStyle( xssfCellStyle );
                cell.setCellValue( usageHeader.getTitle() );
            }
        }
        enum TopUsageHeaderGB {
            BLANK_FIRST(0, ""), UPLOAD(1, "UPLOAD"), DOWNLOAD(2, "DOWNLOAD"), TOTAL(3, "TOTAL"), UPLOAD_PERCENTAGE(4,
                    "UPLOAD %"), ;
            private int    index;
            private String title;

            private TopUsageHeaderGB( int inIndex, String inTitle )
            {
                index = inIndex;
                title = inTitle;
            }

            public int getIndex()
            {
                return index;
            }

            public String getTitle()
            {
                return title;
            }

            public static void prepareTopHeaderGB( XSSFRow dataRow, XSSFCellStyle xssfCellStyle )
            {
                XSSFCell cell = null;
                for ( TopUsageHeaderGB usageTopHeader : TopUsageHeaderGB.values() )
                {
                    cell = dataRow.createCell( usageTopHeader.getIndex() );
                    cell.setCellStyle( xssfCellStyle );
                    cell.setCellValue( usageTopHeader.getTitle() );
                }
            }
        }
    }
}
