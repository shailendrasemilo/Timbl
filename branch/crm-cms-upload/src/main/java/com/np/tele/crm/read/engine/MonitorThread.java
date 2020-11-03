package com.np.tele.crm.read.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

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

import com.np.tele.crm.cms.constants.IAppCons;
import com.np.tele.crm.cms.upload.bm.CrmUploadManagerImpl;
import com.np.tele.crm.cms.upload.bm.ICrmUploadManager;
import com.np.tele.crm.cms.utils.ExcelReadUtils;
import com.np.tele.crm.cms.utils.PropertyUtils;
import com.np.tele.crm.cms.utils.UploadUtils;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.comparators.CrmCmsFilePojoComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class MonitorThread
    implements Runnable
{
    private static final Logger LOGGER     = Logger.getLogger( MonitorThread.class );
    private static long         sleepTime  = 3600000;                                // 1 hour default
    private static String       monitorLoc = "";
    private static String       processLoc = "";
    private static String       fileOwner  = "";
    private static String       archiveLoc = "";

    public MonitorThread( final Properties inProperties )
    {
        sleepTime = PropertyUtils.getLong( inProperties, IAppCons.MONITOR_TIME_INTERVAL, sleepTime );
        monitorLoc = PropertyUtils.getString( inProperties, IAppCons.MONITOR_LOCATION, "." );
        processLoc = PropertyUtils.getString( inProperties, IAppCons.PROCESS_LOCATION, "." );
        fileOwner = PropertyUtils.getString( inProperties, IAppCons.CMS_FILE_OWNER, fileOwner );
        archiveLoc = PropertyUtils.getString( inProperties, IAppCons.ARCHIVE_LCATION, "." );
    }

    @Override
    public void run()
    {
        boolean firstRun = true;
        while ( true )
        {
            LOGGER.info( "Size of processed file list : " + IAppCons.cmsFilesToProcess.size() );
            LOGGER.info( "Starting run of Monitoring Thread..." );
            try
            {
                if ( firstRun )
                {
                    monitorOldFiles();
                    firstRun = false;
                }
                startMonitoring();
                LOGGER.info( "Monitor Thread going to sleep for : " + sleepTime );
                Thread.sleep( sleepTime );
                if ( !IAppCons.cmsFilesToProcess.isEmpty() )
                {
                    LOGGER.info( "Inside to process file." );
                    if ( IAppCons.cmsFilesToProcess.size() > 1 )
                    {
                        Collections.sort( IAppCons.cmsFilesToProcess, new CrmCmsFilePojoComparator() );
                    }
                    processFiles( IAppCons.cmsFilesToProcess );
                    processPayments( IAppCons.cmsFilesToProcess, IAppCons.cmsFilesToPayments );
                    sendFilesStatus( IAppCons.cmsFileIDsToSendStatus );
                }
            }
            catch ( InterruptedException ex )
            {
                LOGGER.warn( "Monitor Thread Interrupted. Doing nothing. Exception is : " + ex );
            }
        }
    }

    private void sendFilesStatus( List<Long> inCmsfileidstosendstatus )
    {
        LOGGER.info( "Inside MonitorThread, sendFilesStatus" );
        if ( CommonValidator.isValidCollection( inCmsfileidstosendstatus ) )
        {
            ICrmUploadManager crmUploadManagerImpl = new CrmUploadManagerImpl();
            ListIterator<Long> cmsFileIdIterator = inCmsfileidstosendstatus.listIterator();
            while ( cmsFileIdIterator.hasNext() )
            {
                crmUploadManagerImpl.sendFileStatus( cmsFileIdIterator.next() );
                cmsFileIdIterator.remove();
            }
        }
    }

    private void monitorOldFiles()
    {
        ICrmUploadManager crmUploadManagerImpl = new CrmUploadManagerImpl();
        List<CrmCmsFilePojo> filesToProcess = crmUploadManagerImpl.getFilesToProcess( CRMStatusCode.PENDING
                .getStatusCode() );
        if ( CommonValidator.isValidCollection( filesToProcess ) )
        {
            LOGGER.info( "Old Files to Process:" + filesToProcess );
            IAppCons.cmsFilesToProcess.addAll( filesToProcess );
        }
    }

    private void startMonitoring()
    {
        File sourceDir = new File( monitorLoc );
        File[] filesArray = null;
        CrmCmsFilePojo cmsFilePojo = null;
        if ( sourceDir.exists() && sourceDir.isDirectory() )
        {
            filesArray = sourceDir.listFiles();
            boolean toRefresh = false;
            if ( StringUtils.isValidObj( filesArray ) && filesArray.length > 0 )
            {
                for ( File file : filesArray )
                {
                    if ( StringUtils.endsWithIgnoreCase( file.getAbsolutePath(), ".csv" ) )
                    {
                        toRefresh = true;
                        if ( StringUtils.isValidObj( csvToXLSX( file.getAbsolutePath() ) ) )
                        {
                            UploadUtils.changeFileLocation( monitorLoc, archiveLoc, file.getName() );
                        }
                    }
                }
            }
            if ( toRefresh )
            {
                sourceDir = new File( monitorLoc );
                if ( sourceDir.exists() && sourceDir.isDirectory() )
                {
                    filesArray = sourceDir.listFiles();
                }
                else
                {
                    filesArray = null;
                }
            }
            if ( StringUtils.isValidObj( filesArray ) && filesArray.length > 0 )
            {
                for ( File file : filesArray )
                {
                    cmsFilePojo = new CrmCmsFilePojo();
                    cmsFilePojo.setCmsFileName( file.getName() );
                    cmsFilePojo.setCreatedBy( fileOwner );
                    if ( file.isFile() )
                    {
                        if ( UploadUtils.isValidFile( cmsFilePojo.getCmsFileName() ) )
                        {
                            //                            if ( cmsFilePojo.getCmsFileName().matches( CRMRegex.PATTERN_FILE_DEPOSIT.getPattern() ) )
                            if ( StringUtils.contains( cmsFilePojo.getCmsFileName(), "RITELERMCOLRECON" ) )
                            {
                                cmsFilePojo.setCmsFileType( CRMStatusCode.DEPOSIT.getStatusCode() );
                                cmsFilePojo.setFailReason( IAppConstants.NOTAPPLICABLE );
                                cmsFilePojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                            }
                            else
                            //if ( cmsFilePojo.getCmsFileName().matches( CRMRegex.PATTERN_FILE_CLEARANCE.getPattern() ) )
                            if ( StringUtils.contains( cmsFilePojo.getCmsFileName(), "RITELECLNTASCDNL" ) )
                            {
                                cmsFilePojo.setCmsFileType( CRMStatusCode.CLEARANCE.getStatusCode() );
                                cmsFilePojo.setFailReason( IAppConstants.NOTAPPLICABLE );
                                cmsFilePojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                            }
                            else
                            {
                                cmsFilePojo.setCmsFileType( CRMStatusCode.UNKNOWN.getStatusCode() );
                                cmsFilePojo.setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                                    IAppCons.ERROR_INVALID_FILE_NAME,
                                                                                    "." ) );
                                cmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                            }
                        }
                        else
                        {
                            cmsFilePojo.setCmsFileType( CRMStatusCode.INVALID.getStatusCode() );
                            cmsFilePojo
                                    .setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                             IAppCons.ERROR_INVALID_FILE_TYPE, "." ) );
                            cmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                        }
                    }
                    else
                    {
                        cmsFilePojo.setCmsFileType( CRMStatusCode.INVALID.getStatusCode() );
                        cmsFilePojo.setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                            IAppCons.ERROR_INPUT_NOT_FILE, "." ) );
                        cmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                    }
                    if ( !IAppCons.cmsFilesToProcess.contains( cmsFilePojo ) )
                    {
                        if ( isFileSaved( cmsFilePojo ) )
                        {
                            LOGGER.info( "File has saved to DB and move to process location." );
                        }
                        else
                        {
                            LOGGER.info( "File failed to save into DB and will process soon." );
                        }
                    }
                    else
                    {
                        cmsFilePojo = null;
                    }
                }
            }
            else
            {
                LOGGER.info( "Monitoring Thread started, but no file to process..." );
                LOGGER.info( "Source : " + monitorLoc + " is empty." );
            }
        }
        else
        {
            LOGGER.fatal( "Invalid monitoring location. Aborting program." );
            System.exit( 0 );
        }
    }

    private boolean isFileSaved( CrmCmsFilePojo inCrmCmsFilePojo )
    {
        boolean isSaved = false;
        int preSize = 0;
        int postSize = 0;
        try
        {
            ICrmUploadManager crmUploadManagerImpl = new CrmUploadManagerImpl();
            inCrmCmsFilePojo = crmUploadManagerImpl.cmsFileOperation( inCrmCmsFilePojo );
            if ( StringUtils.isValidObj( inCrmCmsFilePojo ) && inCrmCmsFilePojo.getCmsFileId() > 0 )
            {
                if ( StringUtils.equals( inCrmCmsFilePojo.getCmsFileType(), CRMStatusCode.DEPOSIT.getStatusCode() )
                        || StringUtils.equals( inCrmCmsFilePojo.getCmsFileType(),
                                               CRMStatusCode.CLEARANCE.getStatusCode() ) )
                {
                    preSize = IAppCons.cmsFilesToProcess.size();
                    IAppCons.cmsFilesToProcess.add( inCrmCmsFilePojo );
                    postSize = IAppCons.cmsFilesToProcess.size();
                    if ( postSize > preSize )
                    {
                        isSaved = true;
                        UploadUtils.changeFileLocation( monitorLoc, processLoc, inCrmCmsFilePojo );
                    }
                }
                else
                {
                    isSaved = true;
                    UploadUtils.changeFileLocation( monitorLoc, archiveLoc, inCrmCmsFilePojo );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while checking status of saved file detials.", ex );
        }
        return isSaved;
    }

    private void processFiles( List<CrmCmsFilePojo> cmsFilePojos )
    {
        LOGGER.info( "Going to process all tracked files..." );
        ICrmUploadManager crmUploadManagerImpl = null;
        Iterator<CrmCmsFilePojo> cmsFileIterator = cmsFilePojos.iterator();
        while ( cmsFileIterator.hasNext() )
        {
            CrmCmsFilePojo cmsFilePojo = cmsFileIterator.next();
            //LOGGER.info( "Current file : " + cmsFilePojo.getCmsFileName() );
            if ( !StringUtils.equals( CRMStatusCode.ARCHIVE.getStatusCode(), cmsFilePojo.getStatus() ) )
            {
                if ( !readFile( cmsFilePojo ) )
                {
                    cmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                    cmsFilePojo.setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                        IAppCons.ERROR_INVALID_RECORDS, "." ) );
                }
                crmUploadManagerImpl = new CrmUploadManagerImpl();
                cmsFilePojo = crmUploadManagerImpl.cmsFileOperation( cmsFilePojo );
                if ( StringUtils.isValidObj( cmsFilePojo ) )
                {
                    if ( cmsFilePojo.getCmsFileId() > 0 )
                    {
                        IAppCons.cmsFileIDsToSendStatus.add( cmsFilePojo.getCmsFileId() );
                    }
                    UploadUtils.changeFileLocation( processLoc, archiveLoc, cmsFilePojo );
                    if ( StringUtils.equals( CRMStatusCode.FAILURE.getStatusCode(), cmsFilePojo.getStatus() ) )
                    {
                        cmsFileIterator.remove();
                        LOGGER.info( "File got some problem while processing and removed form processing list." );
                    }
                    else
                    {
                        LOGGER.info( "File processed successfully." );
                        IAppCons.cmsFilesToPayments.add( cmsFilePojo );
                    }
                }
            }
        }
    }

    private boolean readFile( CrmCmsFilePojo inCrmCmsFilePojo )
    {
        LOGGER.info( "Processing file... " + inCrmCmsFilePojo.getCmsFileName() );
        boolean isRead = false;
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        List<CrmCmsPaymentPojo> cmsPaymentPojos = null;
        String inPath = UploadUtils.getFilePath( processLoc, inCrmCmsFilePojo.getCmsFileName() );
        ICrmUploadManager crmUploadManagerImpl = null;
        try
        {
            fileInputStream = new FileInputStream( inPath );
            if ( StringUtils.endsWithIgnoreCase( inPath, ".xls" ) )
            {
                excelReadUtils = new ExcelReadUtils<HSSFSheet, HSSFRow, HSSFCell>();
                workbook = new HSSFWorkbook( fileInputStream );
            }
            else if ( StringUtils.endsWithIgnoreCase( inPath, ".xlsx" ) )
            {
                excelReadUtils = new ExcelReadUtils<XSSFSheet, XSSFRow, XSSFCell>();
                workbook = new XSSFWorkbook( fileInputStream );
            }
            cmsPaymentPojos = excelReadUtils.readFileData( inCrmCmsFilePojo, workbook, inPath );
            crmUploadManagerImpl = new CrmUploadManagerImpl();
            if ( CommonValidator.isValidCollection( cmsPaymentPojos ) && StringUtils.isValidObj( inCrmCmsFilePojo ) )
            {
                isRead = crmUploadManagerImpl.cmsPaymentOperation( cmsPaymentPojos, inCrmCmsFilePojo );
            }
            else if ( !StringUtils.isValidObj( cmsPaymentPojos )
                    && StringUtils.equals( inCrmCmsFilePojo.getStatus(), CRMStatusCode.FAILURE.getStatusCode() ) )
            {
                isRead = true;
            }
            if ( isRead && StringUtils.equals( inCrmCmsFilePojo.getStatus(), CRMStatusCode.PENDING.getStatusCode() ) )
            {
                inCrmCmsFilePojo.setStatus( CRMStatusCode.ARCHIVE.getStatusCode() );
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
        return isRead;
    }

    private void processPayments( List<CrmCmsFilePojo> cmsFilePojos, List<CrmCmsFilePojo> inProcessPayments )
    {
        LOGGER.info( "Going to process payments for processed Files:" + cmsFilePojos );
        LOGGER.info( "Going to process payments for payments Fies..." + inProcessPayments );
        if ( cmsFilePojos.size() != inProcessPayments.size() )
        {
            return;
        }
        boolean paymentProcessed = false;
        ICrmUploadManager crmUploadManagerImpl = null;
        Iterator<CrmCmsFilePojo> cmsFileIterator = inProcessPayments.iterator();
        while ( cmsFileIterator.hasNext() )
        {
            CrmCmsFilePojo cmsFilePojo = cmsFileIterator.next();
            //LOGGER.info( "Current file : " + cmsFilePojo.getCmsFileName() );
            crmUploadManagerImpl = new CrmUploadManagerImpl();
            paymentProcessed = crmUploadManagerImpl.cmsProcessOperation( cmsFilePojo );
            if ( paymentProcessed )
            {
                IAppCons.cmsFilesToProcess.remove( cmsFilePojo );
                cmsFileIterator.remove();
                LOGGER.info( "Payments processed successfully and removed form processing list..." );
            }
            else
            {
                LOGGER.info( "File got some problem while processing Payments..." );
            }
        }
    }

    private static String csvToXLSX( String csvFileAddress )
    {
        String xlsxFileAddress = StringUtils.removeEndIgnoreCase( csvFileAddress, ".csv" );
        LOGGER.info( "XLSXFileAddress: " + xlsxFileAddress );
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            String sheetName = StringUtils.substringAfterLast( xlsxFileAddress, IAppConstants.FILE_SEPERATOR );
            LOGGER.info( "Sheet Name: " + sheetName );
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet( sheetName );
            String currentLine = null;
            int RowNum = 0;
            fr = new FileReader( csvFileAddress );
            br = new BufferedReader( fr );
            while ( ( currentLine = br.readLine() ) != null )
            {
                String str[] = currentLine.split( "," );
                XSSFRow currentRow = sheet.createRow( RowNum );
                for ( int i = 0; i < str.length; i++ )
                {
                    currentRow.createCell( i ).setCellValue( str[i] );
                }
                RowNum++;
            }
            xlsxFileAddress += ".xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream( xlsxFileAddress );
            workBook.write( fileOutputStream );
            fileOutputStream.close();
            LOGGER.info( xlsxFileAddress + " File created." );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to create excel file for " + csvFileAddress, ex );
            return null;
        }
        finally
        {
            if ( StringUtils.isValidObj( fr ) )
            {
                try
                {
                    fr.close();
                }
                catch ( IOException ex )
                {
                    LOGGER.error( "Unable to close FileReader:" + fr, ex );
                }
            }
            if ( StringUtils.isValidObj( br ) )
            {
                try
                {
                    br.close();
                }
                catch ( IOException ex )
                {
                    LOGGER.error( "Unable to close BufferedReader:" + br, ex );
                }
            }
        }
        return xlsxFileAddress;
    }
    //    public static void main( String[] args )
    //    {
    //        System.out.println(csvToXLSX( "E:/Official/Anurag/CodeBase/Nextra/Nextra-Trunk/CMSFiles/monitor/NEXTRATELECLNTASCDNL130306025011.CSV" ));
    //    }
}
