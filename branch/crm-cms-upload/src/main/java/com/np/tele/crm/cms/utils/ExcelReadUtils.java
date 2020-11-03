package com.np.tele.crm.cms.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.np.tele.crm.cms.constants.CMSHeaderTitle;
import com.np.tele.crm.cms.constants.CMSPaymentDataTypes;
import com.np.tele.crm.cms.constants.IAppCons;
import com.np.tele.crm.cms.pojos.CrmCmsPaymentPojoCopy;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.read.engine.UploadEngine;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.PojoUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ExcelReadUtils<E1 extends Sheet, E2 extends Row, E3 extends Cell>
{
    private static final Logger                   LOGGER       = Logger.getLogger( ExcelReadUtils.class );
    private static final Map<String, Set<String>> HEADER_MAP   = new HashMap<String, Set<String>>();
    private static final Map<String, Set<String>> PROPERTY_MAP = new HashMap<String, Set<String>>();
    private E1                                    sheet;
    private E2                                    row;
    private E3                                    cell;

    @SuppressWarnings("unchecked")
    public List<CrmCmsPaymentPojo> readFileData( CrmCmsFilePojo inCrmCmsFilePojo, Workbook inWorkbook, String inPath )
    {
        boolean toProcess = false;
        FormulaEvaluator formulaEvaluator = null;
        CrmCmsPaymentPojo cmsPaymentPojo = null;
        CrmCmsPaymentPojoCopy cmsPaymentPojoCopy = null;
        Set<CrmCmsPaymentPojoCopy> cmsPaymentCopyPojos = null;
        List<CrmCmsPaymentPojo> cmsPaymentPojos = null;
        try
        {
            LOGGER.info( "[Accessing File input...]" );
            LOGGER.info( "Working for file Id " + inCrmCmsFilePojo.getCmsFileId() );
            String fileName = inCrmCmsFilePojo.getCmsFileName();
            String sheetName = StringUtils
                    .substring( StringUtils.substring( fileName, 0, fileName.lastIndexOf( "." ) ), 0, 31 );
            LOGGER.info( "Processing Sheet:" + sheetName );
            sheet = (E1) inWorkbook.getSheet( sheetName );
            if ( StringUtils.isValidObj( sheet ) )
            {
                toProcess = true;
                Set<String> expectedHeader = null;
                List<String> recievedHeader = null;
                cmsPaymentCopyPojos = new HashSet<CrmCmsPaymentPojoCopy>();
                cmsPaymentPojos = new ArrayList<CrmCmsPaymentPojo>();
                Iterator<Row> rowIterator = sheet.rowIterator();
                formulaEvaluator = inWorkbook.getCreationHelper().createFormulaEvaluator();
                while ( rowIterator.hasNext() && toProcess )
                {
                    row = ( (E2) rowIterator.next() );
                    if ( row.getRowNum() == 0 )
                    {
                        expectedHeader = UploadUtils.getHeaders( inCrmCmsFilePojo.getCmsFileType(), HEADER_MAP );
                        LOGGER.info( "Expected Headers:" + expectedHeader );
                        int noOfColumns = row.getPhysicalNumberOfCells();
                        if ( !CommonValidator.isValidCollection( expectedHeader ) )
                        {
                            toProcess = false;
                            break;
                        }
                        if ( toProcess && noOfColumns < expectedHeader.size() )
                        {
                            toProcess = false;
                            break;
                        }
                        recievedHeader = new ArrayList<String>();
                        Iterator<Cell> cellIterator = row.cellIterator();
                        if ( StringUtils.isValidObj( cellIterator ) )
                        {
                            while ( cellIterator.hasNext() && toProcess )
                            {
                                cell = (E3) cellIterator.next();
                                recievedHeader.add( readingCellValue( cell, formulaEvaluator ) );
                                if ( !expectedHeader.containsAll( recievedHeader ) )
                                {
                                    toProcess = false;
                                    break;
                                }
                            }
                            LOGGER.info( "Received Headers:" + recievedHeader );
                        }
                        else
                        {
                            toProcess = false;
                            break;
                        }
                    }
                    else
                    {
                        cmsPaymentPojo = new CrmCmsPaymentPojo();
                        Iterator<Cell> cellIterator = row.cellIterator();
                        if ( StringUtils.isValidObj( cellIterator ) )
                        {
                            inner: while ( cellIterator.hasNext() && toProcess )
                            {
                                cell = (E3) cellIterator.next();
                                boolean toContinue = feedPaymentPojo( inCrmCmsFilePojo, cmsPaymentPojo, cell,
                                                                      formulaEvaluator, recievedHeader );
                                if ( !toContinue )
                                {
                                    cmsPaymentPojo = null;
                                    break inner;
                                }
                            }
                            if ( StringUtils.isValidObj( cmsPaymentPojo ) )
                            {
                                if ( isNotBlank( inCrmCmsFilePojo.getCmsFileType(), cmsPaymentPojo ) )
                                {
                                    cmsPaymentPojo
                                            .setCreatedBy( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                                    IAppCons.CMS_FILE_OWNER, "." ) );
                                    cmsPaymentPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                                    if ( StringUtils.equals( inCrmCmsFilePojo.getCmsFileType(),
                                                             CRMStatusCode.DEPOSIT.getStatusCode() ) )
                                    {
                                        cmsPaymentPojo.setDepositFileId( inCrmCmsFilePojo.getCmsFileId() );
                                    }
                                    else if ( StringUtils.equals( inCrmCmsFilePojo.getCmsFileType(),
                                                                  CRMStatusCode.CLEARANCE.getStatusCode() ) )
                                    {
                                        cmsPaymentPojo.setClearanceFileId( inCrmCmsFilePojo.getCmsFileId() );
                                    }
                                    inCrmCmsFilePojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                                    cmsPaymentPojoCopy = new CrmCmsPaymentPojoCopy();
                                    CRMUtils.copyAllProperties( cmsPaymentPojoCopy, cmsPaymentPojo );
                                    if ( cmsPaymentCopyPojos.add( cmsPaymentPojoCopy ) )
                                    {
                                        cmsPaymentPojos.add( cmsPaymentPojo );
                                    }
                                }
                                else
                                {
                                    LOGGER.info( "Empty Row:" + row.getRowNum() );
                                }
                            }
                        }
                    }
                }
                if ( !toProcess )
                {
                    cmsPaymentPojos = null;
                    inCrmCmsFilePojo.setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                             IAppCons.ERROR_INVALID_FILE_HEADER, "." ) );
                    inCrmCmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                }
                else
                {
                    LOGGER.info( "Prepared Payment Pojo List Size : " + cmsPaymentPojos.size() );
                }
            }
            else
            {
                cmsPaymentPojos = null;
                inCrmCmsFilePojo.setFailReason( PropertyUtils.getString( UploadEngine.appConfigProps,
                                                                         IAppCons.ERROR_VALID_SHEET_MISSING, "." ) );
                inCrmCmsFilePojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error while reading Input File", ex );
        }
        return cmsPaymentPojos;
    }

    private boolean isNotBlank( String inFileType, CrmCmsPaymentPojo inCmsPaymentPojo )
    {
        PojoUtils<CrmCmsPaymentPojo> pojoUtils = new PojoUtils<CrmCmsPaymentPojo>();
        return !pojoUtils.isPojoEmpty( inCmsPaymentPojo, UploadUtils.getProperties( inFileType, PROPERTY_MAP ) );
    }

    private String readingCellValue( E3 inCell, FormulaEvaluator inFormulaEvaluator )
    {
        String value = "";
        try
        {
            switch ( inFormulaEvaluator.evaluateInCell( inCell ).getCellType() )
            {
                case E3.CELL_TYPE_BLANK:
                    break;
                case E3.CELL_TYPE_NUMERIC:
                    if ( DateUtil.isCellDateFormatted( inCell ) )
                    {
                        value = String.valueOf( inCell.getDateCellValue() );
                    }
                    else
                    {
                        value = String.valueOf( inCell.getNumericCellValue() );
                    }
                    break;
                case E3.CELL_TYPE_STRING:
                    value = StringUtils.trim( inCell.getStringCellValue() );
                    break;
                case E3.CELL_TYPE_BOOLEAN:
                    value = String.valueOf( inCell.getBooleanCellValue() );
                    break;
                case E3.CELL_TYPE_ERROR:
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occurred while reading cell value : ", ex );
        }
        return value.trim();
    }

    private boolean feedPaymentPojo( CrmCmsFilePojo inCrmCmsFilePojo,
                                     CrmCmsPaymentPojo inCrmCmsPaymentPojo,
                                     E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     List<String> inRecievedHeader )
    {
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String title = inRecievedHeader.get( cellIndex );
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            CMSHeaderTitle property = CMSHeaderTitle.getCMSHeaderByTypeValue( inCrmCmsFilePojo.getCmsFileType(), title );
            if ( property == CMSHeaderTitle.CMS_CLEARANCE_0 || property == CMSHeaderTitle.CMS_DEPOSIT_0 )
            {
                if ( StringUtils.equals( "H", cellValue ) )
                {
                    return false;
                }
            }
            if ( StringUtils.isValidObj( property ) && StringUtils.isNotEmpty( cellValue ) )
            {
                //LOGGER.info( "Going to set values" );
                if ( StringUtils.isNotEmpty( property.getPropertyType() ) )
                {
                    CMSPaymentDataTypes cmsDataType = UploadUtils.getPropertyType( property.getPropertyType() );
                    Object obj = UploadUtils.getValidObj( cmsDataType, cellValue );
                    if ( StringUtils.isValidObj( obj ) )
                    {
                        if ( StringUtils.equals( property.getProperty(), CMSHeaderTitle.CMS_DEPOSIT_11.getProperty() ) )
                        {
                            obj = preciseObj( property, obj );
                        }
                        org.apache.commons.beanutils.PropertyUtils.setProperty( inCrmCmsPaymentPojo,
                                                                                property.getProperty(), obj );
                    }
                }
            }
            // LOGGER.info( "Cell Value : " + cellValue + " Property : " + property );
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Illegal Access Exception", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "Invocation Target Exception", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "No Such Method Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while feeding Payment Pojo", ex );
        }
        return true;
    }

    private Object preciseObj( CMSHeaderTitle inTitle, Object inObj )
    {
        if ( inTitle.isPrefix() )
        {
            if ( StringUtils.length( String.valueOf( inObj ) ) < inTitle.getSize() )
            {
                inObj = StringUtils.leftPad( String.valueOf( inObj ), inTitle.getSize(), '0' );
            }
        }
        return inObj;
    }
}
