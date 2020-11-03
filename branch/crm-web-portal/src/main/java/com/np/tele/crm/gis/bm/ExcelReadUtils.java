package com.np.tele.crm.gis.bm;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ExcelFailCode;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.gis.pojo.GISUploadPojo;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.ValidityExtensionPojo;
import com.np.tele.crm.service.client.replicas.LmsExcelPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.PojoUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ExcelReadUtils<E1 extends Sheet, E2 extends Row, E3 extends Cell>
{
    private static final Logger                   LOGGER      = Logger.getLogger( ExcelReadUtils.class );
    private int                                   nRows       = 0;
    private int                                   processRows = 0;
    private E1                                    sheet;
    private E2                                    row;
    private E3                                    cell;
    private static final Map<String, Set<String>> HEADER_MAP  = new HashMap<String, Set<String>>();

    public ExcelReadUtils()
    {
        nRows = 0;
        processRows = 0;
    }

    public int getnRows()
    {
        return nRows;
    }

    /**
     * @return the processRows
     */
    public int getProcessRows()
    {
        return processRows;
    }

    public Map<Long, Map<String, List<SocietyPojo>>> readGisFileData( String inUser,
                                                                      Workbook inWorkbook,
                                                                      String inPath,
                                                                      ExcelWriteUtils inExcelWriteUtils,
                                                                      List<GISMasterPojo> inGisMasterPojos,
                                                                      List<PartnerPojo> networkPartners,
                                                                      GISForm gisForm )
    {
        Map<Long, Map<String, List<SocietyPojo>>> validRecords = new HashMap<Long, Map<String, List<SocietyPojo>>>();
        sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.GIS_SHEET.getValue() );
        try
        {
            LOGGER.info( "[Accessing File input...]" );
            GISUploadPojo gisUploadPojo = null;
            SocietyPojo societyPojo = null;
            boolean toProcess = false;
            FormulaEvaluator formulaEvaluator = null;
            if ( StringUtils.isValidObj( sheet ) )
            {
                toProcess = true;
                int createRowIndex = 1;
                StringBuilder error = null;
                LOGGER.info( "File accessed successfully. Start reading file..." );
                Iterator<Row> rowIterator = sheet.rowIterator();
                Set<String> expectedHeader = new HashSet<String>();
                List<String> recievedHeader = new ArrayList<String>();
                formulaEvaluator = inWorkbook.getCreationHelper().createFormulaEvaluator();
                while ( StringUtils.isValidObj( rowIterator ) && rowIterator.hasNext() && toProcess )
                {
                    row = ( (E2) rowIterator.next() );
                    //                    Iterator<Cell> cellIterator = row.cellIterator();
                    if ( row.getRowNum() == 0 )
                    {
                        expectedHeader = getHeaders( CRMParameter.GIS.getParameter(), HEADER_MAP );
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
                        //                        if ( StringUtils.isValidObj( cellIterator ) )
                        //                        {
                        //                        while ( cellIterator.hasNext() && toProcess )
                        for ( int c = 0; c < 15; c++ )
                        {
                            //                            cell = (E3) cellIterator.next();
                            cell = (E3) row.getCell( c, Row.CREATE_NULL_AS_BLANK );
                            recievedHeader.add( readingCellValue( cell, formulaEvaluator ) );
                            if ( !expectedHeader.containsAll( recievedHeader ) )
                            {
                                toProcess = false;
                                break;
                            }
                        }
                        LOGGER.info( "Received Headers:" + recievedHeader );
                        //                        }
                        //                        else
                        //                        {
                        //                            toProcess = false;
                        //                            break;
                        //                        }
                    }
                    else
                    //                    if ( StringUtils.isValidObj( cellIterator ) )
                    {
                        nRows++;
                        gisUploadPojo = new GISUploadPojo();
                        societyPojo = new SocietyPojo();
                        error = new StringBuilder();
                        //                        while ( cellIterator.hasNext() && toProcess )
                        for ( int c = 0; c < 15; c++ )
                        {
                            //                            cell = (E3) cellIterator.next();
                            cell = (E3) row.getCell( c, Row.CREATE_NULL_AS_BLANK );
                            String errStr = "";
                            errStr = checkAndSetValue( cell, societyPojo, gisUploadPojo, networkPartners,
                                                       formulaEvaluator );
                            if ( !StringUtils.isEmpty( errStr ) )
                            {
                                error.append( errStr );
                            }
                        }
                        //                        if ( !StringUtils.isEmpty( gisUploadPojo.getState() ) )
                        //                        {
                        if ( StringUtils.isEmpty( error ) )
                        {
                            if ( isMasterAvailable( gisUploadPojo, societyPojo, inGisMasterPojos ) )
                            {
                                prepareValidGisPojos( inUser, validRecords, gisUploadPojo, societyPojo );
                            }
                            else
                            {
                                // error.append( ExcelFailCode.MASTERVALIDATIONFAIL.getValue() );
                                if ( StringUtils.isValidObj( gisForm ) )
                                {
                                    if ( !CommonValidator.isValidCollection( gisForm.getConfigureGisUpload() ) )
                                    {
                                        gisForm.setConfigureGisUpload( new ArrayList<GISUploadPojo>() );
                                    }
                                    gisUploadPojo.setSocietyPojo( societyPojo );
                                    gisForm.getConfigureGisUpload().add( gisUploadPojo );
                                }
                            }
                        }
                        else
                        {
                            if ( StringUtils.endsWith( error, "?" ) )
                            {
                                error.deleteCharAt( error.length() - 1 );
                            }
                            inExcelWriteUtils.addRow( createRowIndex, societyPojo, gisUploadPojo, error.toString() );
                            createRowIndex++;
                        }
                        //                        }
                        //                        else
                        //                        {
                        //                            LOGGER.info( "state name is blank" );
                        //                        }
                    }
                }
                LOGGER.info( "File successfully Read. Rows : " + nRows + " processed rows : " + processRows
                        + " createRowIndex : " + createRowIndex );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "exception while reading", ex );
        }
        return validRecords;
    }

    public void prepareValidGisPojos( final String inUser,
                                      final Map<Long, Map<String, List<SocietyPojo>>> validRecords,
                                      final GISUploadPojo gisUploadPojo,
                                      final SocietyPojo societyPojo )
    {
        Map<String, List<SocietyPojo>> partnerSocieties = validRecords.get( gisUploadPojo.getPartnerId() );
        if ( !StringUtils.isValidObj( partnerSocieties ) )
        {
            partnerSocieties = new HashMap<String, List<SocietyPojo>>();
            validRecords.put( gisUploadPojo.getPartnerId(), partnerSocieties );
        }
        String[] networkTypes = StringUtils.split( gisUploadPojo.getNetworkType(), IAppConstants.COMMA );
        for ( String nt : networkTypes )
        {
            List<SocietyPojo> societies = partnerSocieties.get( StringUtils.trim( nt ) );
            if ( !CommonValidator.isValidCollection( societies ) )
            {
                societies = new ArrayList<SocietyPojo>();
            }
            societyPojo.setCreatedBy( inUser );
            societyPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            societies.add( societyPojo );
            partnerSocieties.put( StringUtils.trim( nt ), societies );
        }
        processRows++;
    }

    @SuppressWarnings("unchecked")
    public List readFileData( String inUser,
                              Workbook inWorkbook,
                              String inPath,
                              ExcelWriteUtils inExcelWriteUtils,
                              List<GISMasterPojo> inGisMasterPojos,
                              String inParameter )
    {
        boolean gisUpload = StringUtils.equals( inParameter, CRMParameter.GIS.getParameter() );
        boolean lmsUpload = StringUtils.equals( inParameter, CRMParameter.LMS.getParameter() );
        boolean upfrontUpload = StringUtils.equals( inParameter, CRMParameter.UPFRONTFILE.getParameter() );
        boolean bulkWhiteListUpload = StringUtils.equals( inParameter, CRMParameter.WHITELIST.getParameter() );
        boolean customerStatusUpload = StringUtils.equals( inParameter, CRMParameter.CHANGE_STATUS.getParameter() );
        boolean podUpload = StringUtils.equals( inParameter, CRMParameter.POD_FILE_UPLOAD.getParameter() );
        boolean mountBoosterUpload = StringUtils.equals( inParameter, CRMParameter.MOUNT_BOOSTER.getParameter() );
        boolean validityExtensionUpload = StringUtils.equals( inParameter,
                                                              CRMParameter.VALIDITY_EXTENSION.getParameter() );
        List dataToProcess = null;
        GISMasterPojo gisMasterPojo = null;
        SocietyPojo societyPojo = null;
        CrmUpfrontPaymentPojo upfrontPaymentPojo = null;
        CrmUpCrfMappingPojo upCrfMappingPojo = null;
        CrmQrcWhitelistPojo qrcWhitelistPojo = null;
        List<CrmRcaReasonPojo> crmBankList = null;
        CrmMassOutagePojo massOutagePojo = null;
        String[] userPartner = null;
        LmsExcelPojo lmsExcelPojo = null;
        LmsPojo lmsPojo = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CrmPodDetailsPojo crmPodDeatilsPojo = null;
        CrmCustAssetDetailsPojo assetDetailsPojo = null;
        ValidityExtensionPojo validityExtensionCustomerDetailPojo = null;
        if ( gisUpload )
        {
            dataToProcess = new ArrayList<SocietyPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.GIS_SHEET.getValue() );
        }
        else if ( lmsUpload )
        {
            dataToProcess = new ArrayList<LmsPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.LMS_SHEET.getValue() );
        }
        else if ( upfrontUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<CrmUpfrontPaymentPojo>();
            crmBankList = CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE );
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.UPFRONT_SHEET.getValue() );
        }
        else if ( bulkWhiteListUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<CrmQrcWhitelistPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.WHITELIST_SHEET.getValue() );
        }
        else if ( customerStatusUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<CrmCustomerDetailsPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.CUSTOMER_STATUS_SHEET.getValue() );
        }
        else if ( podUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<CrmPodDetailsPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.POD_SHEET.getValue() );
        }
        else if ( mountBoosterUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<CrmCustAssetDetailsPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.MOUNT_BOOSTER_SHEET.getValue() );
        }
        else if ( validityExtensionUpload )
        {
            userPartner = StringUtils.split( inUser, "," );
            dataToProcess = new ArrayList<ValidityExtensionPojo>();
            sheet = (E1) inWorkbook.getSheet( FileHeaderConstants.VALIDITY_EXTENSION_SHEET.getValue() );
        }
        boolean toProcess = false;
        FormulaEvaluator formulaEvaluator = null;
        try
        {
            LOGGER.info( "[Accessing File input...]" );
            if ( StringUtils.isValidObj( sheet ) )
            {
                toProcess = true;
                int createRowIndex = 1;
                StringBuilder error = null;
                LOGGER.info( "File accessed successfully. Start reading file..." );
                Iterator<Row> rowIterator = sheet.rowIterator();
                Set<String> expectedHeader = new HashSet<String>();
                List<String> recievedHeader = new ArrayList<String>();
                formulaEvaluator = inWorkbook.getCreationHelper().createFormulaEvaluator();
                while ( StringUtils.isValidObj( rowIterator ) && rowIterator.hasNext() && toProcess )
                {
                    row = ( (E2) rowIterator.next() );
                    Iterator<Cell> cellIterator = row.cellIterator();
                    if ( row.getRowNum() == 0 )
                    {
                        expectedHeader = getHeaders( inParameter, HEADER_MAP );
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
                    else if ( StringUtils.isValidObj( cellIterator ) )
                    {
                        nRows++;
                        if ( gisUpload )
                        {
                            gisMasterPojo = new GISMasterPojo();
                            societyPojo = new SocietyPojo();
                        }
                        else if ( lmsUpload )
                        {
                            lmsPojo = new LmsPojo();
                            lmsExcelPojo = new LmsExcelPojo();
                        }
                        else if ( upfrontUpload )
                        {
                            upfrontPaymentPojo = new CrmUpfrontPaymentPojo();
                            upCrfMappingPojo = new CrmUpCrfMappingPojo();
                        }
                        else if ( bulkWhiteListUpload )
                        {
                            qrcWhitelistPojo = new CrmQrcWhitelistPojo();
                        }
                        else if ( customerStatusUpload )
                        {
                            crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                        }
                        else if ( podUpload )
                        {
                            crmPodDeatilsPojo = new CrmPodDetailsPojo();
                        }
                        else if ( mountBoosterUpload )
                        {
                            assetDetailsPojo = new CrmCustAssetDetailsPojo();
                        }
                        else if ( validityExtensionUpload )
                        {
                            validityExtensionCustomerDetailPojo = new ValidityExtensionPojo();
                        }
                        error = new StringBuilder();
                        while ( cellIterator.hasNext() && toProcess )
                        {
                            cell = (E3) cellIterator.next();
                            String errStr = "";
                            if ( gisUpload )
                            {
                                // errStr = checkAndSetValue( cell, societyPojo, gisMasterPojo, formulaEvaluator );
                            }
                            else if ( lmsUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, lmsPojo, lmsExcelPojo );
                            }
                            else if ( upfrontUpload )
                            {
                                errStr = checkAndSetValue( userPartner[0], cell, formulaEvaluator, upfrontPaymentPojo,
                                                           upCrfMappingPojo, crmBankList );
                            }
                            else if ( bulkWhiteListUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, qrcWhitelistPojo );
                            }
                            else if ( customerStatusUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, crmCustomerDetailsPojo );
                            }
                            else if ( podUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, crmPodDeatilsPojo );
                                LOGGER.info( "Error String :::" + errStr );
                            }
                            else if ( mountBoosterUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, assetDetailsPojo );
                            }
                            else if ( validityExtensionUpload )
                            {
                                errStr = checkAndSetValue( cell, formulaEvaluator, validityExtensionCustomerDetailPojo );
                            }
                            if ( !StringUtils.isEmpty( errStr ) )
                            {
                                error.append( errStr );
                            }
                        }
                        //                        if ( gisUpload && !StringUtils.isEmpty( gisMasterPojo.getState() ) )
                        //                        {
                        //                            if ( !isMasterAvailable( gisUploadPojo, societyPojo, inGisMasterPojos ) )
                        //                            {
                        //                                // error.append( ExcelFailCode.MASTERVALIDATIONFAIL.getValue() );
                        //                                if ( StringUtils.isValidObj( gisForm ) )
                        //                                {
                        //                                    if ( !CommonValidator.isValidCollection( gisForm.getConfigureGisUpload() ) )
                        //                                    {
                        //                                        gisForm.setConfigureGisUpload( new ArrayList<GISUploadPojo>() );
                        //                                    }
                        //                                    gisForm.getConfigureGisUpload().add( gisUploadPojo );
                        //                                }
                        //                            }
                        //                            if ( StringUtils.isEmpty( error ) )
                        //                            {
                        //                                processRows++;
                        //                                societyPojo.setCreatedBy( inUser );
                        //                                societyPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        //                                dataToProcess.add( societyPojo );
                        //                            }
                        //                            else
                        //                            {
                        //                                if ( StringUtils.endsWith( error, "?" ) )
                        //                                {
                        //                                    error.deleteCharAt( error.length() - 1 );
                        //                                }
                        //                                inExcelWriteUtils.addRow( createRowIndex, societyPojo, gisUploadPojo, error.toString() );
                        //                                createRowIndex++;
                        //                            }
                        //                        }
                        //                        else 
                        if ( lmsUpload )
                        {
                            if ( StringUtils.isEmpty( error ) && lmsPojo.getContactNumber() > 0 )
                            {
                                processRows++;
                                if ( GISUtils.isSocietyFeasible( lmsPojo ) )
                                {
                                    lmsPojo.setFeasibility( CRMParameter.YES.getParameter() );
                                }
                                else if ( GISUtils.isAreaFeasible( lmsPojo ) )
                                {
                                    lmsPojo.setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                                }
                                else
                                {
                                    if ( StringUtils.isValidObj( lmsPojo ) && lmsPojo.getPincode() > 0 )
                                    {
                                        LmsPojo leadPojo = GISUtils.getSocietyByPinCode( lmsPojo.getPincode() );
                                        if ( StringUtils.isValidObj( leadPojo )
                                                && StringUtils.isNotEmpty( leadPojo.getState() ) )
                                        {
                                            LOGGER.info( leadPojo.toString() );
                                            lmsPojo.setFeasibility( IAppConstants.EMP_TYPE_PARTNER );
                                        }
                                        else
                                        {
                                            lmsPojo.setFeasibility( CRMParameter.NO.getParameter() );
                                        }
                                    }
                                    else
                                    {
                                        lmsPojo.setFeasibility( CRMParameter.NO.getParameter() );
                                    }
                                }
                                lmsPojo.setCreatedBy( inUser );
                                lmsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                lmsPojo.setLeadSource( CRMDisplayListConstants.WEBSITE.getCode() );
                                dataToProcess.add( lmsPojo );
                            }
                            else
                            {
                                if ( StringUtils.endsWith( error, "?" ) )
                                {
                                    error.deleteCharAt( error.length() - 1 );
                                }
                                inExcelWriteUtils.addRow( createRowIndex, lmsExcelPojo, error.toString() );
                                createRowIndex++;
                            }
                        }
                        else if ( upfrontUpload && StringUtils.isValidObj( upfrontPaymentPojo ) )
                        {
                            PojoUtils<CrmUpfrontPaymentPojo> pojoUtils = new PojoUtils<CrmUpfrontPaymentPojo>();
                            if ( !pojoUtils.checkPojoEmpty( upfrontPaymentPojo ) )
                            {
                                if ( StringUtils.isEmpty( error ) )
                                {
                                    processRows++;
                                    upfrontPaymentPojo.setCreatedBy( userPartner[0] );
                                    upfrontPaymentPojo.setPartnerId( StringUtils.numericValue( userPartner[1] ) );
                                    if ( CommonValidator.isValidCollection( upfrontPaymentPojo.getCrmUpCrfMappings() )
                                            && dataToProcess.contains( upfrontPaymentPojo ) )
                                    {
                                        int pojoIndex = dataToProcess.indexOf( upfrontPaymentPojo );
                                        CrmUpfrontPaymentPojo paymentPojo = (CrmUpfrontPaymentPojo) dataToProcess
                                                .get( pojoIndex );
                                        paymentPojo.getCrmUpCrfMappings().addAll( upfrontPaymentPojo
                                                                                          .getCrmUpCrfMappings() );
                                        upfrontPaymentPojo = paymentPojo;
                                    }
                                    else
                                    {
                                        dataToProcess.add( upfrontPaymentPojo );
                                    }
                                }
                                else
                                {
                                    error.deleteCharAt( error.length() - 1 );
                                    inExcelWriteUtils.addRow( createRowIndex, upfrontPaymentPojo, error.toString() );
                                    createRowIndex++;
                                }
                            }
                        }
                        else if ( bulkWhiteListUpload && !StringUtils.isEmpty( qrcWhitelistPojo.getCustomerId() ) )
                        {
                            if ( StringUtils.isEmpty( error ) )
                            {
                                dataToProcess.add( qrcWhitelistPojo );
                            }
                            else
                            {
                                error.deleteCharAt( error.length() - 1 );
                                inExcelWriteUtils.addRow( createRowIndex, qrcWhitelistPojo, error.toString() );
                                createRowIndex++;
                            }
                        }
                        else if ( customerStatusUpload && !StringUtils.isEmpty( crmCustomerDetailsPojo.getCustomerId() ) )
                        {
                            if ( StringUtils.isEmpty( error ) )
                            {
                                dataToProcess.add( crmCustomerDetailsPojo );
                            }
                            else
                            {
                                error.deleteCharAt( error.length() - 1 );
                                inExcelWriteUtils.addRow( createRowIndex, crmCustomerDetailsPojo, error.toString() );
                                createRowIndex++;
                            }
                        }
                        else if ( podUpload && StringUtils.isValidObj( crmPodDeatilsPojo ) )
                        {
                            PojoUtils<CrmPodDetailsPojo> pojoUtils = new PojoUtils<CrmPodDetailsPojo>();
                            if ( !pojoUtils.checkPojoEmpty( crmPodDeatilsPojo ) )
                            {
                                if ( StringUtils.isEmpty( error ) )
                                {
                                    processRows++;
                                    crmPodDeatilsPojo.setCreatedBy( inUser );
                                    dataToProcess.add( crmPodDeatilsPojo );
                                }
                                else
                                {
                                    if ( StringUtils.endsWith( error, "?" ) )
                                    {
                                        error.deleteCharAt( error.length() - 1 );
                                    }
                                    inExcelWriteUtils.addRow( createRowIndex, crmPodDeatilsPojo, error.toString() );
                                    createRowIndex++;
                                }
                            }
                        }
                        else if ( mountBoosterUpload && StringUtils.isValidObj( assetDetailsPojo ) )
                        {
                            LOGGER.info( "Mount Booster ..." );
                            if ( StringUtils.isEmpty( error ) )
                            {
                                processRows++;
                                dataToProcess.add( assetDetailsPojo );
                            }
                            else
                            {
                                if ( StringUtils.endsWith( error, "?" ) )
                                {
                                    error.deleteCharAt( error.length() - 1 );
                                }
                                inExcelWriteUtils.addRow( createRowIndex, assetDetailsPojo, error.toString() );
                                createRowIndex++;
                            }
                        }
                        else if ( validityExtensionUpload
                                && StringUtils.isValidObj( validityExtensionCustomerDetailPojo ) )
                        {
                            if ( StringUtils.isEmpty( error ) )
                            {
                                processRows++;
                                dataToProcess.add( validityExtensionCustomerDetailPojo );
                            }
                            else
                            {
                                error.deleteCharAt( error.length() - 1 );
                                inExcelWriteUtils.addRow( createRowIndex, validityExtensionCustomerDetailPojo,
                                                          error.toString() );
                                createRowIndex++;
                            }
                        }
                    }
                }
                LOGGER.info( "File successfully Read. Rows : " + nRows + " processed rows : " + processRows
                        + " createRowIndex : " + createRowIndex );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error while reading Input File", ex );
        }
        if ( !toProcess )
            return null;
        else
            return dataToProcess;
    }

    private String checkAndSetValue( E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     CrmCustAssetDetailsPojo inAssetDetailsPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.CUSTOMERIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCID.getValue();
                    }
                    else
                    {
                        inAssetDetailsPojo.setCategoryName( cellValue );
                    }
                    break;
                case 1:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.PLANCODEMISSING.getValue();
                    }
                    else
                    {
                        inAssetDetailsPojo.setCategoryValue( cellValue );
                    }
                    break;
                case 2:
                    if ( StringUtils.isEmpty( cellValue ) || StringUtils.equals( "0", cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.QUANTITYMISSING.getValue();
                    }
                    else if ( !StringUtils.isNumeric( cellValue ) )
                    {
                        error = ExcelFailCode.QUANTITYNUMERIC.getValue();
                    }
                    else
                    {
                        inAssetDetailsPojo.setCategoryCount( Byte.valueOf( cellValue ) );
                    }
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to AssetDetails Pojo : ", ex );
        }
        LOGGER.info( "Error :: " + error );
        return error;
    }

    private String checkAndSetValue( E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     ValidityExtensionPojo validityExtensionCustomerDetailPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.CUSTOMERIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCID.getValue();
                    }
                    else
                    {
                        validityExtensionCustomerDetailPojo.setCustomerId( cellValue );
                    }
                    break;
                case 1:
                    if ( StringUtils.isEmpty( cellValue ) || !StringUtils.isNumeric( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.EXTENSIONDAYSMISSING.getValue();
                    }
                    else if ( Integer.parseInt( cellValue ) < 1 || Integer.parseInt( cellValue ) > 31 )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.EXTENSIONDAYSMISSING.getValue();
                    }
                    else
                    {
                        validityExtensionCustomerDetailPojo.setExtensionDays( cellValue );
                    }
                    break;
                case 2:
                    if ( cellValue.length() < 11 )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.TICKETID_INVALID.getValue();
                    }
                    else
                    {
                        validityExtensionCustomerDetailPojo.setTicketId( cellValue );
                    }
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to ValidityExtensionCustomerDetail Pojo : ", ex );
        }
        LOGGER.info( "Error :: " + error );
        return error;
    }

    /*check and set value for POD Data */
    private String checkAndSetValue( E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     CrmPodDetailsPojo inCrmPodDeatilsPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    break;
                case 1:
                    //DataFormatter dataFormatter = new DataFormatter();
                    //cellValue = dataFormatter.formatCellValue( inCell );
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CUSTOMERIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCID.getValue();
                    }
                    else
                    {
                        inCrmPodDeatilsPojo.setCustomerId( cellValue );
                    }
                    break;
                case 2:
                    if ( StringUtils.isNotBlank( cellValue ) )
                    {
                        inCrmPodDeatilsPojo.setReceiverName( cellValue );
                    }
                    else
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.RECEIVERNAMEMISSING.getValue();
                    }
                    break;
                case 3:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.DELIVERDATEMISSING.getValue();
                    }
                    else
                    {
                        try
                        {
                            Calendar date = Calendar.getInstance();
                            date.setTime( IDateConstants.SDF_DD_MM_YYYY.parse( cellValue ) );
                            XMLGregorianCalendar xmlCustDobDate = DateUtils.toXMLGregorianCalendar( date );
                            inCrmPodDeatilsPojo.setDeliveredDate( xmlCustDobDate );
                        }
                        catch ( ParseException ex )
                        {
                            error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDDELIVERDATE.getValue();
                        }
                    }
                    break;
                case 4:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.CUSTOMERRELATION.getValue();
                    }
                    else
                    {
                        inCrmPodDeatilsPojo.setCustomerRelation( cellValue );
                    }
                    break;
                case 5:
                    String str = getValid10Digits( cellValue );
                    String startNos = ValidationUtil.validateInputMobile( str );
                    if ( StringUtils.isBlank( str ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.MOBILEINVALID.getValue();
                    }
                    else if ( !StringUtils.isNumeric( str ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.MOBILENONNUMERIC.getValue();
                    }
                    else if ( str.length() != FileHeaderConstants.POD5.getSize() )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.MOBILELENGTHPROB.getValue();
                        inCrmPodDeatilsPojo.setContactNumber( Long.parseLong( cellValue ) );
                    }
                    else if ( !StringUtils.isEmpty( startNos ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.MOBILEINVALIDSTART.getValue()
                                + startNos;
                        inCrmPodDeatilsPojo.setContactNumber( Long.parseLong( str ) );
                    }
                    else
                    {
                        inCrmPodDeatilsPojo.setContactNumber( Long.parseLong( str ) );
                    }
                    break;
                case 6:
                    if ( StringUtils.isBlank( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.STATUSMISSING.getValue();
                    }
                    else
                    {
                        inCrmPodDeatilsPojo.setDeliveryStatus( cellValue );
                    }
                    break;
                case 7:
                    if ( StringUtils.isNotBlank( cellValue ) )
                    {
                        inCrmPodDeatilsPojo.setNonDeliveryReason( cellValue );
                    }
                    break;
                case 8:
                    if ( StringUtils.isBlank( cellValue ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.CHEQUENUMBERMISSING.getValue();
                    }
                    else
                    {
                        inCrmPodDeatilsPojo.setInstrumentNumber( neutralizeValue( cellValue ) );
                    }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to CrmPodDetailsPojo : ", ex );
        }
        return error;
    }

    private String checkAndSetValue( E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    break;
                case 1:
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue( inCell );
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CUSTOMERIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCID.getValue();
                    }
                    inCrmCustomerDetailsPojo.setCustomerId( cellValue );
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to CrmCustomerDetailsPojo : ", ex );
        }
        return error;
    }

    private Set<String> getHeaders( String inParameter, Map<String, Set<String>> inHeaderMap )
    {
        if ( StringUtils.isNotBlank( inParameter ) )
        {
            if ( StringUtils.isValidObj( inHeaderMap ) )
            {
                if ( inHeaderMap.containsKey( inParameter ) )
                {
                    return inHeaderMap.get( inParameter );
                }
                else
                {
                    Set<String> expectedHeader = new HashSet<String>();
                    for ( FileHeaderConstants fileHeader : FileHeaderConstants.values() )
                    {
                        if ( StringUtils.equalsIgnoreCase( inParameter, fileHeader.getUpLoadType() ) )
                        {
                            expectedHeader.add( fileHeader.getValue() );
                        }
                    }
                    inHeaderMap.put( inParameter, expectedHeader );
                    return expectedHeader;
                }
            }
        }
        return null;
    }

    private String checkAndSetValue( E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     CrmQrcWhitelistPojo inQrcWhitelistPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    break;
                case 1:
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue( inCell );
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CUSTOMERIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCID.getValue();
                    }
                    inQrcWhitelistPojo.setCustomerId( cellValue );
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to White List Pojo : ", ex );
        }
        return error;
    }

    private String checkAndSetValue( String inUser,
                                     E3 inCell,
                                     FormulaEvaluator inFormulaEvaluator,
                                     CrmUpfrontPaymentPojo inUpfrontPaymentPojo,
                                     CrmUpCrfMappingPojo inUpCrfMappingPojo,
                                     List<CrmRcaReasonPojo> inCrmBankList )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            cellValue = neutralizeValue( cellValue );
            LOGGER.info( "Cell Index : " + cellIndex + " --> Cell Value : " + cellValue );
            switch ( cellIndex )
            {
                case 0:
                    break;
                case 1:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CRFIDMISSING.getValue();
                    }
                    else if ( !cellValue.matches( CRMRegex.PATTERN_CRFID.getPattern() ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCRFID.getValue();
                    }
                    else if ( !CommonUtils.isValidCRF( StringUtils.trimToEmpty( cellValue ) ) )
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.CRFIDNOTPUNCHED.getValue();
                    }
                    else
                    {
                        inUpCrfMappingPojo.setCrfId( cellValue );
                        inUpCrfMappingPojo.setCreatedBy( inUser );
                        inUpCrfMappingPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        inUpfrontPaymentPojo.getCrmUpCrfMappings().add( inUpCrfMappingPojo );
                    }
                    break;
                case 2:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CHQNOMISSING.getValue();
                    }
                    else if ( StringUtils.isNumeric( cellValue )
                            || cellValue.matches( CRMRegex.PATTERN_DOUBLE_VALUE.getPattern() ) )
                    {
                        cellValue = StringUtils.substringBefore( cellValue, IAppConstants.DOT );
                        if ( StringUtils.length( cellValue ) > FileHeaderConstants.UPFRONT2.getSize() )
                        {
                            error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCHQNO.getValue();
                        }
                        else
                        {
                            inUpfrontPaymentPojo.setChequeNo( preciseObj( cellValue ) );
                        }
                    }
                    else
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCHQNO.getValue();
                    }
                    break;
                case 3:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.CHQDATEMISSING.getValue();
                    }
                    else
                    {
                        try
                        {
                            Calendar date = Calendar.getInstance();
                            date.setTime( IDateConstants.SDF_DD_MM_YYYY.parse( cellValue ) );
                            XMLGregorianCalendar xmlCustDobDate = DateUtils.toXMLGregorianCalendar( date );
                            inUpfrontPaymentPojo.setChequeDate( xmlCustDobDate );
                        }
                        catch ( ParseException ex )
                        {
                            error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDCHQDATE.getValue();
                        }
                    }
                    break;
                case 4:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.BANKNAMEMISSING.getValue();
                    }
                    else
                    {
                        CrmRcaReasonPojo rcaReasonPojo = new CrmRcaReasonPojo();
                        rcaReasonPojo.setCategoryValue( cellValue );
                        rcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
                        rcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
                        rcaReasonPojo.setSubSubCategory( CRMRCAReason.CRM_BANK.getStatusCode() );
                        int pojoIndex = inCrmBankList.indexOf( rcaReasonPojo );
                        if ( pojoIndex < 0 )
                        {
                            error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.UNREGISTEREDBANK.getValue();
                        }
                        else
                        {
                            rcaReasonPojo = inCrmBankList.get( pojoIndex );
                            inUpfrontPaymentPojo.setBankId( rcaReasonPojo.getCategoryId() );
                        }
                    }
                    break;
                case 5:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.AMOUNTMISSING.getValue();
                    }
                    else if ( cellValue.matches( CRMRegex.PATTERN_DOUBLE_VALUE.getPattern() ) )
                    {
                        inUpfrontPaymentPojo.setAmount( Double.parseDouble( cellValue ) );
                    }
                    else
                    {
                        error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.INVALIDAMOUNT.getValue();
                    }
                    break;
                case 6:
                    if ( StringUtils.isEmpty( cellValue ) )
                    {
                        error = ExcelFailCode.ENTITYMISSING.getValue();
                    }
                    else
                    {
                        if ( StringUtils.contains( CRMDisplayListConstants.TELESERVICES.getValue(), cellValue )
                                || StringUtils.equalsIgnoreCase( CRMDisplayListConstants.TELESERVICES.getCode(),
                                                                 cellValue ) )
                        {
                            inUpfrontPaymentPojo.setEntityName( CRMDisplayListConstants.TELESERVICES.getCode() );
                        }
                        else if ( StringUtils.contains( CRMDisplayListConstants.TELESOLUTIONS.getValue(), cellValue )
                                || StringUtils.equalsIgnoreCase( CRMDisplayListConstants.TELESOLUTIONS.getCode(),
                                                                 cellValue ) )
                        {
                            inUpfrontPaymentPojo.setEntityName( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
                        }
                        else
                        {
                            error = cellValue + IAppConstants.WHITE_SPACE + ExcelFailCode.ENTITYINVALID.getValue();
                        }
                    }
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to CrmUpfrontPayment Pojo : ", ex );
        }
        return error;
    }

    private boolean isMasterAvailable( GISUploadPojo inGISUploadPojo,
                                       SocietyPojo inSocietyPojo,
                                       List<GISMasterPojo> inGisMasterPojos )
    {
        boolean isValid = false;
        GISMasterPojo gisMasterPojo = null;
        try
        {
            gisMasterPojo = new GISMasterPojo();
            gisMasterPojo.setCountry( inGISUploadPojo.getCountry() );
            gisMasterPojo.setState( inGISUploadPojo.getState() );
            gisMasterPojo.setCity( inGISUploadPojo.getCity() );
            gisMasterPojo.setArea( inGISUploadPojo.getArea() );
            int pojoIndex = inGisMasterPojos.indexOf( gisMasterPojo );
            if ( pojoIndex > -1 )
            {
                inSocietyPojo.setAreaId( inGisMasterPojos.get( pojoIndex ).getAreaId() );
                isValid = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occurred while validating Master Data : ", ex );
        }
        LOGGER.info( "isMasterAvailable:: " + isValid + "\n" + gisMasterPojo );
        return isValid;
    }

    private void isValidHeader( E3 inCell,
                                FormulaEvaluator inFormulaEvaluator,
                                String inParameter,
                                List<String> inExpectedHeader,
                                List<String> inRecievedHeader )
    {
        String value = "";
        try
        {
            if ( StringUtils.isValidObj( inCell ) )
            {
                value = readingCellValue( inCell, inFormulaEvaluator );
                inRecievedHeader.add( value );
            }
            if ( inExpectedHeader.isEmpty() )
            {
                for ( FileHeaderConstants fileHeader : FileHeaderConstants.values() )
                {
                    if ( StringUtils.equalsIgnoreCase( inParameter, fileHeader.getUpLoadType() ) )
                    {
                        inExpectedHeader.add( fileHeader.getValue() );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occurred while validating file header : ", ex );
        }
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
                        inCell.setCellType( Cell.CELL_TYPE_STRING );
                        value = String.valueOf( inCell.getStringCellValue() );
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
        //LOGGER.info( "Captured value : " + value );
        return StringUtils.trimToEmpty( value );
    }

    /*Method for GIS File*/
    private String checkAndSetValue( E3 inCell,
                                     SocietyPojo inSocietyPojo,
                                     GISUploadPojo inGISUploadPojo,
                                     List<PartnerPojo> networkPartners,
                                     FormulaEvaluator inFormulaEvaluator )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            switch ( cellIndex )
            {
                case 0:
                    inGISUploadPojo.setPincode( cellValue );
                    if ( StringUtils.isNumeric( cellValue ) && cellValue.length() == 6 )
                    {
                        inSocietyPojo.setPrimaryPincode( Integer.valueOf( getStr( cellValue ) ) );
                    }
                    else
                    {
                        error = ExcelFailCode.PINCOEDNULL.getValue();
                    }
                    break;
                case 1:
                    inGISUploadPojo.setSecPincode( cellValue );
                    LOGGER.info( "cell value:: " + cellValue + " isNumeric:: " + StringUtils.isNumeric( cellValue )
                            + " length:: " + cellValue.length() );
                    if ( StringUtils.isNumeric( cellValue ) && cellValue.length() == 6 )
                    {
                        inSocietyPojo.setSecPincode( Integer.valueOf( getStr( cellValue ) ) );
                    }
                    else if ( !StringUtils.isBlank( cellValue ) )
                    {
                        error = ExcelFailCode.PINCOEDNULL.getValue();
                    }
                    break;
                case 2:
                    inGISUploadPojo.setCountry( IAppConstants.COUNTRY_INDIA );
                    inGISUploadPojo.setState( StringUtils.upperCase( cellValue ) );
                    if ( StringUtils.isBlank( cellValue ) || StringUtils.isNumeric( cellValue )
                            || cellValue.length() > 30 )
                    {
                        error = ExcelFailCode.STATENULL.getValue();
                    }
                    break;
                case 3:
                    inGISUploadPojo.setCity( StringUtils.upperCase( cellValue ) );
                    if ( StringUtils.isBlank( cellValue ) || StringUtils.isNumeric( cellValue )
                            || cellValue.length() > 30 )
                    {
                        error = ExcelFailCode.CITYNULL.getValue();
                    }
                    break;
                case 4:
                    inGISUploadPojo.setArea( StringUtils.upperCase( cellValue ) );
                    if ( StringUtils.isBlank( cellValue ) || StringUtils.isNumeric( cellValue )
                            || cellValue.length() > 30 )
                    {
                        error = ExcelFailCode.AREANULL.getValue();
                    }
                    break;
                case 5:
                    inGISUploadPojo.setLocality( StringUtils.upperCase( cellValue ) );
                    if ( !StringUtils.isBlank( cellValue ) && !StringUtils.isNumeric( cellValue )
                            && cellValue.length() < 50 )
                    {
                        inSocietyPojo.setLocalityName( StringUtils.upperCase( cellValue ) );
                    }
                    else
                    {
                        error = ExcelFailCode.LOCALITYNULL.getValue();
                    }
                    break;
                case 6:
                    inGISUploadPojo.setSociety( StringUtils.upperCase( cellValue ) );
                    if ( StringUtils.isNumeric( cellValue ) || cellValue.length() > 50 )
                    {
                        error = ExcelFailCode.SOCIETYNULL.getValue();
                    }
                    else if ( !StringUtils.isBlank( cellValue ) )
                    {
                        inSocietyPojo.setSocietyName( StringUtils.upperCase( cellValue ) );
                    }
                    break;
                case 7:
                    inGISUploadPojo.setNetworkPartner( cellValue );
                    boolean validPartner = false;
                    for ( PartnerPojo partner : networkPartners )
                    {
                        if ( StringUtils.equals( partner.getPartnerName(), inGISUploadPojo.getNetworkPartner() ) )
                        {
                            inGISUploadPojo.setPartnerId( partner.getPartnerId() );
                            validPartner = true;
                            break;
                        }
                    }
                    if ( !validPartner )
                    {
                        error = ExcelFailCode.NETWORK_PARTNER_INVALID.getValue();
                    }
                    break;
                case 8:
                    inGISUploadPojo.setNetworkType( StringUtils.upperCase( cellValue ) );
                    String[] networkTypes = StringUtils.split( inGISUploadPojo.getNetworkType(), IAppConstants.COMMA );
                    if ( networkTypes.length > 0 )
                    {
                        for ( String nt : networkTypes )
                        {
                            if ( !StringUtils.isValidObj( CRMDisplayListConstants
                                    .getCodeByValue( "Product", StringUtils.trim( nt ) ) ) )
                            {
                                error = ExcelFailCode.FIBERCONNECTIVITYNULL.getValue();
                                break;
                            }
                        }
                    }
                    else
                    {
                        error = ExcelFailCode.FIBERCONNECTIVITYNULL.getValue();
                    }
                    break;
                case 9:
                    inGISUploadPojo.setConnectableHomes( cellValue );
                    if ( cellValue.length() > 128 )
                    {
                        error = ExcelFailCode.CONNECTABLE_HOMES_INVALID.getValue();
                    }
                    else if ( !StringUtils.isBlank( cellValue ) )
                    {
                        inSocietyPojo.setConnectableHomes( cellValue );
                    }
                    break;
                case 10:
                    inGISUploadPojo.setRfsDus( cellValue );
                    if ( StringUtils.isNumeric( cellValue ) && cellValue.length() < 21 )
                    {
                        inSocietyPojo.setRfsDus( Long.parseLong( getStr( cellValue ) ) );
                    }
                    else
                    {
                        error = ExcelFailCode.RFS_DUS_INVALID.getValue();
                    }
                    break;
                case 11:
                    if ( StringUtils.contains( inGISUploadPojo.getNetworkType().toUpperCase(), "FTTX" ) )
                    {
                        if ( cellValue.isEmpty() )
                        {
                            error = ExcelFailCode.LONGITUDE_REQUIRED.getValue();
                            break;
                        }
                    }
                    inGISUploadPojo.setLongitude( cellValue );
                    if ( cellValue.length() > 50 )
                    {
                        error = ExcelFailCode.LONGITUDE_INVALID.getValue();
                    }
                    else
                    {
                        inSocietyPojo.setLongitude( cellValue );
                    }
                    break;
                case 12:
                    if ( StringUtils.contains( inGISUploadPojo.getNetworkType().toUpperCase(), "FTTX" ) )
                    {
                        if ( cellValue.isEmpty() )
                        {
                            error = ExcelFailCode.LATITUDE_REQUIRED.getValue();
                            break;
                        }
                    }
                    inGISUploadPojo.setLatitude( cellValue );
                    if ( cellValue.length() > 50 )
                    {
                        error = ExcelFailCode.LATITUDE_INVALID.getValue();
                    }
                    else
                    {
                        inSocietyPojo.setLatitude( cellValue );
                    }
                    break;
                case 13:
                    inGISUploadPojo.setCustomerCategory( cellValue );
                    String custCat = CRMDisplayListConstants.getCodeByValue( "CustomerCategory", StringUtils
                            .capitalize( cellValue.toLowerCase() ) );
                    if ( StringUtils.isNotBlank( cellValue ) && cellValue.length() < 36
                            && StringUtils.isValidObj( custCat ) )
                    {
                        inSocietyPojo.setCustomerCategory( custCat );
                    }
                    else
                    {
                        error = ExcelFailCode.CUSTOMER_CATEGORY_INVALID.getValue();
                    }
                    break;
                case 14:
                    inGISUploadPojo.setTypeOfField( cellValue );
                    if ( cellValue.startsWith( CRMParameter.BROWN.getParameter() ) )
                    {
                        inSocietyPojo.setNetworkAvailability( "B" );
                    }
                    else if ( cellValue.startsWith( CRMParameter.GREEN.getParameter() ) )
                    {
                        inSocietyPojo.setNetworkAvailability( "G" );
                    }
                    else
                        error = ( ExcelFailCode.FIELDINVALID.getValue() );
                    break;
            //                case 0:
            //                    break;
            //                case 1:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                    {
            //                        inGISMasterPojo.setCountry( IAppConstants.COUNTRY_INDIA );
            //                        inGISMasterPojo.setState( StringUtils.upperCase( cellValue ) );
            //                    }
            //                    else
            //                        error = ( ExcelFailCode.STATENULL.getValue() );
            //                    break;
            //                case 2:
            //                    inSocietyPojo.setSocietyId( 0 );
            //                    break;
            //                case 3:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                    {
            //                        String societyName = "";
            //                        if ( cellValue.length() <= FileHeaderConstants.GIS3.getSize() )
            //                        {
            //                            societyName = cellValue;
            //                        }
            //                        else if ( cellValue.length() > FileHeaderConstants.GIS3.getSize() )
            //                        {
            //                            societyName = getAcceptableValue( cellValue, FileHeaderConstants.GIS3.getSize() );
            //                        }
            //                        if ( societyName.matches( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_3_28.getPattern() ) )
            //                        {
            //                            inSocietyPojo.setSocietyName( societyName );
            //                        }
            //                        else if ( StringUtils.removeET( societyName )
            //                                .matches( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_3_28.getPattern() ) )
            //                        {
            //                            inSocietyPojo.setSocietyName( societyName );
            //                        }
            //                        else
            //                        {
            //                            inSocietyPojo.setSocietyName( cellValue );
            //                            error = "Society Name must be alphanumeric.?";
            //                        }
            //                    }
            //                    else
            //                        error = ( ExcelFailCode.SOCIETYNULL.getValue() );
            //                    break;
            //                case 4:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                    {
            //                        if ( cellValue.startsWith( CRMParameter.BROWN.getParameter() ) )
            //                            inSocietyPojo.setNetworkAvailability( "B" );
            //                        else if ( cellValue.startsWith( CRMParameter.GREEN.getParameter() ) )
            //                            inSocietyPojo.setNetworkAvailability( "G" );
            //                        else
            //                            error = ( ExcelFailCode.FIELDINVALID.getValue() );
            //                    }
            //                    else
            //                        error = ( ExcelFailCode.FIELDNULL.getValue() );
            //                    break;
            //                case 5:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                        inGISMasterPojo.setArea( StringUtils.upperCase( cellValue ) );
            //                    else
            //                        error = ( ExcelFailCode.AREANULL.getValue() );
            //                    break;
            //                case 6:
            //                    /* Don't uncomment or delete the commented code */
            //                    /* StringUtils.upperCase( setLocality( getStr( cellValue ) ) ) */ 
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                        inGISMasterPojo.setLocality( StringUtils.upperCase( cellValue ) );
            //                    else
            //                        error = ( ExcelFailCode.LOCALITYNULL.getValue() );
            //                    break;
            //                case 7:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                        inGISMasterPojo.setCity( StringUtils.upperCase( cellValue ) );
            //                    else
            //                        error = ( ExcelFailCode.CITYNULL.getValue() );
            //                    break;
            //                case 8:
            //                    if ( !StringUtils.isEmpty( cellValue ) )
            //                        inGISMasterPojo.setPincode( Long.parseLong( getStr( cellValue ) ) );
            //                    else
            //                        error = ( ExcelFailCode.PINCOEDNULL.getValue() );
            //                    break;
            //                case 9:
            //                    inSocietyPojo.setLatitude( StringUtils.changeStringToDouble( cellValue ) );
            //                    break;
            //                case 10:
            //                    inSocietyPojo.setLongitude( StringUtils.changeStringToDouble( cellValue ) );
            //                    break;
            //                case 11:
            //                    inSocietyPojo.setCustomerCategory( cellValue );
            //                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to Society Pojo : ", ex );
        }
        return error;
    }

    /*Method for LMS File*/
    private String checkAndSetValue( final E3 inCell,
                                     final FormulaEvaluator inFormulaEvaluator,
                                     LmsPojo inLmsPojo,
                                     LmsExcelPojo inLmsExcelPojo )
    {
        String error = "";
        try
        {
            int cellIndex = inCell.getColumnIndex();
            String cellValue = readingCellValue( inCell, inFormulaEvaluator );
            switch ( cellIndex )
            {
                case 0:
                    inLmsExcelPojo.setSerialNumber( cellValue );
                    break;
                case 1:
                    inLmsExcelPojo.setCustomerName( cellValue );
                    inLmsPojo.setCustomerName( getAcceptableValue( cellValue, FileHeaderConstants.LMS1.getSize() ) );
                    break;
                case 2:
                    inLmsExcelPojo.setContactNumber( cellValue );
                    String str = getValid10Digits( cellValue );
                    String startNos = ValidationUtil.validateInputMobile( str );
                    if ( StringUtils.isBlank( str ) )
                    {
                        error = ExcelFailCode.MOBILEINVALID.getValue();
                    }
                    else if ( !StringUtils.isNumeric( str ) )
                    {
                        error = ExcelFailCode.MOBILENONNUMERIC.getValue();
                    }
                    else if ( str.length() != FileHeaderConstants.LMS2.getSize() )
                    {
                        error = ExcelFailCode.MOBILELENGTHPROB.getValue();
                        inLmsPojo.setContactNumber( Long.parseLong( cellValue ) );
                    }
                    else if ( !StringUtils.isEmpty( startNos ) )
                    {
                        error = ExcelFailCode.MOBILEINVALIDSTART.getValue() + startNos;
                        inLmsPojo.setContactNumber( Long.parseLong( str ) );
                    }
                    else
                        inLmsPojo.setContactNumber( Long.parseLong( str ) );
                    break;
                case 3:
                    inLmsExcelPojo.setState( cellValue );
                    inLmsPojo.setState( getAcceptableValue( cellValue, FileHeaderConstants.LMS3.getSize() ) );
                    break;
                case 4:
                    inLmsExcelPojo.setCity( cellValue );
                    inLmsPojo.setCity( getAcceptableValue( cellValue, FileHeaderConstants.LMS4.getSize() ) );
                    break;
                case 5:
                    inLmsExcelPojo.setArea( cellValue );
                    inLmsPojo.setArea( getAcceptableValue( cellValue, FileHeaderConstants.LMS5.getSize() ) );
                    break;
                case 6:
                    inLmsExcelPojo.setLocality( cellValue );
                    String value = "";
                    value = StringUtils.left( cellValue, FileHeaderConstants.LMS6.getSize() );
                    value = StringUtils.upperCase( value );
                    inLmsPojo.setLocality( value );
                    break;
                //                case 7:
                //                    inLmsExcelPojo.setSociety( cellValue );
                //                    inLmsPojo.setSociety( getAcceptableValue( cellValue, FileHeaderConstants.LMS7.getSize() ) );
                //                    break;
                case 7:
                    inLmsExcelPojo.setHouseNo( cellValue );
                    inLmsPojo.setHouseNumber( getAcceptableValue( cellValue, FileHeaderConstants.LMS8.getSize() ) );
                    break;
                case 8:
                    inLmsExcelPojo.setLandMark( cellValue );
                    inLmsPojo.setLandmark( getAcceptableValue( cellValue, FileHeaderConstants.LMS9.getSize() ) );
                    break;
                case 9:
                    inLmsExcelPojo.setService( cellValue );
                    if ( StringUtils.equalsIgnoreCase( cellValue, CRMDisplayListConstants.BROADBAND.getValue() ) )
                    {
                        inLmsPojo.setProduct( CRMDisplayListConstants.BROADBAND.getCode() );
                    }
                    else if ( StringUtils.equalsIgnoreCase( cellValue,
                                                            CRMDisplayListConstants.RADIO_FREQUENCY.getValue() ) )
                    {
                        inLmsPojo.setProduct( CRMDisplayListConstants.RADIO_FREQUENCY.getCode() );
                    }
                    else if ( StringUtils.equalsIgnoreCase( cellValue,
                                                            CRMDisplayListConstants.ETHERNET_ON_CABLE.getValue() ) )
                    {
                        inLmsPojo.setProduct( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() );
                    }
                    else
                    {
                        inLmsPojo.setProduct( "" );
                    }
                    break;
                case 10:
                    inLmsExcelPojo.setRemarks( cellValue );
                    inLmsPojo.setRemarks( StringUtils.left( cellValue, FileHeaderConstants.LMS11.getSize() ) );
                    break;
                case 11:
                    inLmsExcelPojo.setPincode( Integer.parseInt( cellValue ) );
                    inLmsPojo
                            .setPincode( Integer.parseInt( getAcceptableValue( cellValue,
                                                                               FileHeaderConstants.LMS13.getSize() ) ) );
                    break;
                case 12:
                    inLmsExcelPojo.setReferralSource( cellValue );
                    inLmsPojo.setReferralSource( getAcceptableValue( cellValue, FileHeaderConstants.LMS14.getSize() ) );
                    break;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while setting values to LMS Pojo : ", ex );
        }
        return error;
    }

    private String getStr( String inStr )
    {
        String str = "";
        try
        {
            if ( !StringUtils.isEmpty( inStr ) && StringUtils.contains( inStr, "." ) )
            {
                str = inStr.substring( 0, inStr.lastIndexOf( "." ) );
            }
            else if ( StringUtils.isEmpty( inStr ) )
            {
                str = "0";
            }
            else
            {
                str = inStr;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while coverting values to string : ", ex );
        }
        return str;
    }

    private String setLocality( String inStr )
    {
        //inStr = StringUtils.remove( StringUtils.trim( inStr ), "-" );
        try
        {
            if ( StringUtils.startsWithIgnoreCase( inStr, "Sector" ) )
            {
                //inStr = StringUtils.removeStartIgnoreCase( inStr, "Sector" );// do nothing and don't uncomment
            }
            else if ( StringUtils.startsWithIgnoreCase( inStr, "Sec" ) )
            {
                inStr = StringUtils.removeStartIgnoreCase( inStr, "Sec" );
            }
            inStr = StringUtils.trim( inStr );
            if ( StringUtils.isNumeric( getStr( inStr ) ) )
            {
                inStr = StringUtils.leftPad( inStr, 8, "Sector-" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while setting locality/sector : ", ex );
        }
        return inStr;
    }

    private String getAcceptableValue( String inStr, int inSize )
    {
        String value = "";
        try
        {
            LOGGER.info( "Input Value : " + inStr );
            if ( inStr.contains( IAppConstants.DOT ) )
            {
                int sep = inStr.lastIndexOf( IAppConstants.DOT );
                inStr = inStr.substring( 0, sep );
            }
            value = StringUtils.left( inStr, inSize );
            value = StringUtils.upperCase( value );
            LOGGER.info( "Accepted Value : " + value );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while getting Acceptable Value : ", ex );
        }
        return value;
    }

    private String getValid10Digits( String inStr )
    {
        try
        {
            inStr = StringUtils.remove( inStr, "." );
            inStr = StringUtils.remove( inStr, "E9" );
            inStr = StringUtils.removeStart( inStr, "0" );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while getting Valid Value : ", ex );
        }
        return inStr;
    }

    private String preciseObj( String inStr )
    {
        if ( StringUtils.length( String.valueOf( inStr ) ) < FileHeaderConstants.UPFRONT2.getSize() )
        {
            inStr = StringUtils.leftPad( String.valueOf( inStr ), FileHeaderConstants.UPFRONT2.getSize(), '0' );
        }
        return inStr;
    }

    public static String neutralizeValue( String inStr )
    {
        if ( !StringUtils.isEmpty( inStr ) && inStr.endsWith( ".0" ) )
        {
            inStr = StringUtils.removeEnd( inStr, ".0" );
        }
        else if ( !StringUtils.isEmpty( inStr ) && inStr.contains( "." ) && inStr.contains( "E" ) )
        {
            inStr = StringUtils.remove( inStr, "." );
            inStr = StringUtils.substringBefore( inStr, "E" );
        }
        return inStr;
    }
}
