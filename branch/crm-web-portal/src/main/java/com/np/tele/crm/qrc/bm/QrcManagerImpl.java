package com.np.tele.crm.qrc.bm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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

import com.np.tele.crm.client.CRMAlertsClient;
import com.np.tele.crm.client.CrmConfigClient;
import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.client.QRCFaultRepairClient;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.ExcelReadUtils;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.AlertStatusPojo;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.AlertsDto.ParamValues;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmMassOutageMastersPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmMassOutageSocietyPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcCommonPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QrcManagerImpl
    implements IQrcManager
{
    private static final Logger  LOGGER            = Logger.getLogger( QrcManagerImpl.class );
    private CrmQrcService        qrcServiceClient  = null;
    private CrmCapService        capServiceClient  = null;
    private CrmConfigClient      crmConfigClient;
    private CrmFinanceService    financeService    = null;
    private MasterDataClient     masterDataClient;
    private CRMAlertsClient      crmAlertsClient   = null;
    private QRCFaultRepairClient faultRepairClient = null;

    public QRCFaultRepairClient getFaultRepairClient()
    {
        return faultRepairClient;
    }

    public void setFaultRepairClient( QRCFaultRepairClient inFaultRepairClient )
    {
        faultRepairClient = inFaultRepairClient;
    }

    public CRMAlertsClient getCrmAlertsClient()
    {
        return crmAlertsClient;
    }

    public void setCrmAlertsClient( CRMAlertsClient inCrmAlertsClient )
    {
        crmAlertsClient = inCrmAlertsClient;
    }

    /**
     * @return the qrcServiceClient
     */
    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    /**
     * @param inQrcServiceClient the qrcServiceClient to set
     */
    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    public CrmCapService getCapServiceClient()
    {
        return capServiceClient;
    }

    public void setCapServiceClient( CrmCapService capServiceClient )
    {
        this.capServiceClient = capServiceClient;
    }

    public CrmConfigClient getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CrmConfigClient inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    public CrmFinanceService getFinanceService()
    {
        return financeService;
    }

    public void setFinanceService( CrmFinanceService financeService )
    {
        this.financeService = financeService;
    }

    public MasterDataClient getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterDataClient masterDataClient )
    {
        this.masterDataClient = masterDataClient;
    }

    @Override
    public List<CrmQrcCategoriesPojo> getQrcCategories( String inStatus )
    {
        List<CrmQrcCategoriesPojo> qrcCategoriesPojos = null;
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
            qrcCategoriesPojo.setStatus( inStatus );
            qrcDto.setCrmQrcCategoriesPojo( qrcCategoriesPojo );
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.TRACK.getParameter(),
                                                          CRMParameter.QRCCATEGORY.getParameter(), qrcDto );
            qrcCategoriesPojos = qrcDto.getCrmQrcCategoriesPojos();
            LOGGER.info( "QRC Categories Pojos List Size: " + qrcCategoriesPojos.size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in retriving QRC Categories Pojos: ", ex );
        }
        return qrcCategoriesPojos;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategories( String inStatus )
    {
        List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos = null;
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
            qrcCategoriesPojo.setStatus( inStatus );
            qrcDto.setCrmQrcCategoriesPojo( qrcCategoriesPojo );
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.TRACK.getParameter(),
                                                          CRMParameter.QRCSUBSUBCATEGORY.getParameter(), qrcDto );
            qrcSubSubCategoriesPojos = qrcDto.getCrmQrcSubSubCategoriesPojos();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in retriving QRC Sub Sub Categories Pojos: ", ex );
        }
        return qrcSubSubCategoriesPojos;
    }

    @Override
    public CrmQrcCategoriesPojo getCrmQrcCategoriesPojo( long inQrcCategoryId )
    {
        CrmQrcCategoriesPojo qrcCategoriesPojo = null;
        List<CrmQrcCategoriesPojo> categoriesPojos = QRCCacheManager.getActiveQrcCategories();
        for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
        {
            if ( crmQrcCategoriesPojo.getQrcCategoryId() == inQrcCategoryId )
            {
                qrcCategoriesPojo = crmQrcCategoriesPojo;
                break;
            }
        }
        return qrcCategoriesPojo;
    }

    @Override
    public CrmQrcDto getCustomerTickets( String inCustomerId )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmSrTicketsPojo crmSrTicketsPojo = new CrmSrTicketsPojo();
        crmSrTicketsPojo.setMappingId( inCustomerId );
        crmQrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                             CRMParameter.SERVICE_REQUEST.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getTicketView( CrmSrTicketsPojo crmSrTicketsPojo )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
        try
        {
            crmQrcDto = getQrcServiceClient().ticketOperations( ServiceParameter.VIEW.getParameter(),
                                                                CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto logQrcTicket( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, logQrcTicket" );
        try
        {
            inCrmQrcDto = getQrcServiceClient().ticketOperations( CrmActionEnum.OPENED.getActionCode(),
                                                                  CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while logging SR: ", ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmCapDto saveCustomerProfileDetails( CrmCapDto crmCapDto )
    {
        try
        {
            crmCapDto = getCapServiceClient()
                    .crmCapOperation( ServiceParameter.SAVE_CUSTOMER_PROFILE_DETAILS.getParameter(),
                                      CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while calling crmCapOperation in saveCustomerProfileDetails ", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto sendVerificationLink( CrmCapDto inCrmCapDto )
    {
        if ( StringUtils.isValidObj( inCrmCapDto ) && StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() )
                && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustomerId() ) )
        {
            try
            {
                inCrmCapDto.setUserId( inCrmCapDto.getCustomerDetailsPojo().getCustomerId() );
                inCrmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.PROCESS.getParameter(),
                                                                     CRMParameter.CUSTOMER_EMAIL.getParameter(),
                                                                     inCrmCapDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "unable to send email verification link", ex );
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            }
        }
        else
        {
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        return inCrmCapDto;
    }

    @Override
    public CrmCapDto getCustomerProfileDetails( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        CrmCapDto capDto = new CrmCapDto();
        try
        {
            capDto.setCustomerDetailsPojo( inCustomerDetailsPojo );
            capDto = getCapServiceClient().crmCapOperation( ServiceParameter.VIEW.getParameter(),
                                                            CrmCustomerDetailsPojo.class.getSimpleName(), capDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search customer profile", ex );
        }
        return capDto;
    }

    @Override
    public ConfigDto customerActivityOps( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        ConfigDto configDto = new ConfigDto();
        CrmCustomerActivityPojo customerActivityPojo = new CrmCustomerActivityPojo();
        try
        {
            customerActivityPojo.setCustomerId( inCrmCustomerDetailsPojo.getCustomerId() );
            configDto.setCustomerActivityPojo( customerActivityPojo );
            configDto = getCrmConfigClient().customerActivityOps( ServiceParameter.VIEW.getParameter(), configDto );
            if ( CommonValidator.isValidCollection( configDto.getCustomerActivityPojos() ) )
            {
                SortingComparator<CrmCustomerActivityPojo> sorter = new SortingComparator<CrmCustomerActivityPojo>( "createdTime" );
                Collections.sort( configDto.getCustomerActivityPojos(), Collections.reverseOrder( sorter ) );
                sorter = null;
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error fetching customer activities. " + ex );
        }
        return configDto;
    }

    @Override
    public void setCategoriesNameById( List<CrmSrTicketsPojo> crmSrTicketsPojos )
    {
        List<CrmQrcCategoriesPojo> categoriesPojos = QRCCacheManager.getAllQrcCategories();
        for ( CrmSrTicketsPojo crmSrTicketsPojo : crmSrTicketsPojos )
        {
            CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
            qrcCategoriesPojo.setQrcCategoryId( crmSrTicketsPojo.getQrcCategoryId() );
            int pojoIndex = categoriesPojos.indexOf( qrcCategoriesPojo );
            if ( pojoIndex >= 0 )
            {
                qrcCategoriesPojo = categoriesPojos.get( pojoIndex );
                crmSrTicketsPojo.setQrcCategory( qrcCategoriesPojo.getQrcCategory() );
                List<CrmQrcSubCategoriesPojo> subCategoriesPojos = qrcCategoriesPojo.getCrmQrcSubCategorieses();
                CrmQrcSubCategoriesPojo qrcSubCategoriesPojo = new CrmQrcSubCategoriesPojo();
                qrcSubCategoriesPojo.setQrcSubCategoryId( crmSrTicketsPojo.getQrcSubCategoryId() );
                pojoIndex = subCategoriesPojos.indexOf( qrcSubCategoriesPojo );
                if ( pojoIndex >= 0 )
                {
                    qrcSubCategoriesPojo = subCategoriesPojos.get( pojoIndex );
                    crmSrTicketsPojo.setQrcSubCategory( qrcSubCategoriesPojo.getQrcSubCategory() );
                    List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos = qrcSubCategoriesPojo
                            .getCrmQrcSubSubCategorieses();
                    CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
                    subSubCategoriesPojo.setQrcSubSubCategoryId( crmSrTicketsPojo.getQrcSubSubCategoryId() );
                    pojoIndex = qrcSubSubCategoriesPojos.indexOf( subSubCategoriesPojo );
                    if ( pojoIndex >= 0 )
                    {
                        subSubCategoriesPojo = qrcSubSubCategoriesPojos.get( pojoIndex );
                        crmSrTicketsPojo.setQrcSubSubCategory( subSubCategoriesPojo.getQrcSubSubCategory() );
                    }
                }
            }
        }
    }

    @Override
    public List<CrmQrcWhitelistPojo> checkWhiteListCustomer( CrmQrcWhitelistPojo crmQrcWhitelistPojo )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCrmQrcWhitelistPojo( crmQrcWhitelistPojo );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                             CRMParameter.WHITELIST.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error fetching whiteList customer. " + ex );
        }
        return crmQrcDto.getCrmQrcWhitelistPojoList();
    }

    @Override
    public CrmQrcDto addUpdateWhiteList( QrcForm qrcForm )
    {
        qrcForm.getCrmQrcWhitelistPojo().setEndDate( DateUtils.changeDateFormat( qrcForm.getEndDate() ) );
        CrmQrcDto crmQrcDto = callingWhiteListService( qrcForm );
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto removeFromWhiteList( QrcForm qrcForm )
    {
        qrcForm.getCrmQrcWhitelistPojo().setEndDate( DateUtils.changeDateFormat( DateUtils.getYesterdayDateString() ) );
        qrcForm.getCrmQrcWhitelistPojo().setReason( qrcForm.getExceptionReason() );
        CrmQrcDto crmQrcDto = callingWhiteListService( qrcForm );
        return crmQrcDto;
    }

    private CrmQrcDto callingWhiteListService( QrcForm qrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setUserId( qrcForm.getCrmUserId() );
        crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
        qrcForm.getCrmQrcWhitelistPojo().setCustomerId( qrcForm.getCustomerId() );
        crmQrcDto.setCrmQrcWhitelistPojo( qrcForm.getCrmQrcWhitelistPojo() );
        crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.SAVE.getParameter(),
                                                             CRMParameter.WHITELIST.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error fetching whiteList customer. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public List<String> processBulkWhitelistExcel( QrcForm inQrcForm, String inUser, String inFilePath )
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
                                                                  CRMParameter.WHITELIST.getParameter() );
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
                excelWriteUtils = new ExcelWriteUtils( CRMParameter.WHITELIST.getParameter() );
                List<CrmQrcWhitelistPojo> dataToProcess = excelReadUtils.readFileData( inUser, workbook, inFilePath,
                                                                                       excelWriteUtils, null,
                                                                                       CRMParameter.WHITELIST
                                                                                               .getParameter() );
                if ( !StringUtils.isValidObj( dataToProcess ) )
                {
                    statusList.add( "Invalid File Header." );
                }
                else
                {
                    if ( !dataToProcess.isEmpty() )
                    {
                        String status = storeValidData( dataToProcess, inQrcForm, inUser, excelWriteUtils ) + "_"
                                + excelReadUtils.getnRows();
                        String outPath = excelWriteUtils.createExcel( inUser, inFilePath,
                                                                      CRMParameter.WHITELIST.getParameter() );
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

    @Override
    public CrmQrcDto bulkWhiteList( List<CrmQrcWhitelistPojo> crmQrcWhitelistPojos, RemarksPojo remarksPojo )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.getCrmQrcWhitelistPojoList().addAll( crmQrcWhitelistPojos );
        crmQrcDto.setRemarksPojo( remarksPojo );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.SAVE.getParameter(),
                                                             CRMParameter.WHITELIST.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error saving bulk whiteList customer. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    private String storeValidData( List<CrmQrcWhitelistPojo> inDataToProcess,
                                   QrcForm inQrcForm,
                                   String inUser,
                                   ExcelWriteUtils inExcelWriteUtils )
    {
        String status = "failed";
        CrmQrcDto qrcDto = new CrmQrcDto();
        for ( CrmQrcWhitelistPojo crmQrcWhitelistPojo : inDataToProcess )
        {
            crmQrcWhitelistPojo.setEndDate( DateUtils.changeDateFormat( inQrcForm.getEndDate() ) );
            crmQrcWhitelistPojo.setWhitelistType( inQrcForm.getCrmQrcWhitelistPojo().getWhitelistType() );
            crmQrcWhitelistPojo.setReason( inQrcForm.getCrmQrcWhitelistPojo().getReason() );
        }
        qrcDto.getCrmQrcWhitelistPojoList().addAll( inDataToProcess );
        qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        try
        {
            if ( !inDataToProcess.isEmpty() )
            {
                qrcDto.setUserId( inUser );
                qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.SAVE.getParameter(),
                                                              CRMParameter.BULK_WHITELIST.getParameter(), qrcDto );
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
            LOGGER.error( "Exception while storing valid leads : ", exe );
        }
        return status + IAppConstants.UNDERSCORE
                + ( inDataToProcess.size() - qrcDto.getFailedCustomerMapList().getEntry().size() );
    }

    @Override
    public CrmQrcDto barringUnbarringService( QrcForm qrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmQrcCommonPojo crmQrcCommonPojo = new CrmQrcCommonPojo();
        List<CrmRcaReasonPojo> whitlistReasonList = null;
        //crmQrcDto.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
        crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
        crmQrcDto.setUserId( qrcForm.getCrmUserId() );
        crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
        crmQrcCommonPojo.setCustomerId( qrcForm.getCustomerId() );
        crmQrcCommonPojo.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
        if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
        {
            crmQrcCommonPojo.setAction( CRMCustomerActivityActions.BARRING.getActionDesc() );
            whitlistReasonList = QRCCacheManager.getCustomerBarringReasons();
            for ( CrmRcaReasonPojo crmRcaReasonPojo2 : whitlistReasonList )
            {
                if ( crmRcaReasonPojo2.getCategoryId() == qrcForm.getExceptionReason() )
                {
                    crmQrcCommonPojo.setRcaReasonPojo( crmRcaReasonPojo2 );
                    break;
                }
            }
        }
        else if ( ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(), CRMStatusCode.BARRED.getStatusCode() ) ) )
        {
            crmQrcCommonPojo.setAction( CRMCustomerActivityActions.UNBARRING.getActionDesc() );
            whitlistReasonList = QRCCacheManager.getCustomerUnBarringReasons();
            for ( CrmRcaReasonPojo crmRcaReasonPojo2 : whitlistReasonList )
            {
                if ( crmRcaReasonPojo2.getCategoryId() == qrcForm.getExceptionReason() )
                {
                    crmQrcCommonPojo.setRcaReasonPojo( crmRcaReasonPojo2 );
                    break;
                }
            }
        }
        crmQrcCommonPojo.setRemarksPojo( qrcForm.getRemarksPojo() );
        crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
        crmQrcDto.setCrmQrcCommonPojo( crmQrcCommonPojo );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.PROCESS.getParameter(),
                                                             CRMParameter.BARRING_UNBARRING.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error calling customer barring/Unbarring service. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto resetPassword( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
        crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.RESET.getParameter(),
                                                             CRMParameter.RESET_PASSWORD.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error call service resetPassword. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto changeInstallationAddress( QrcForm inQrcForm )
    {
        getGisDetailsByMaster( inQrcForm );
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCrmAddressDetailsPojo( inQrcForm.getChangeInstallationAddressPojo() );
        crmQrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
        crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
        crmQrcDto.setSrTicketNo( inQrcForm.getSrTicketNo() );
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        LOGGER.info( "CRM QRCDTO" + crmQrcDto );
        try
        {
            crmQrcDto = getQrcServiceClient()
                    .workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                         CRMParameter.IFR_EOC_LEVEL2.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error call service change installation address. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    private void getGisDetailsByMaster( QrcForm qrcForm )
    {
        GISMasterPojo gisMaster = new GISMasterPojo();
        gisMaster.setCountry( IAppConstants.COUNTRY_INDIA );
        gisMaster.setState( qrcForm.getInstallationAddressPojo().getStateName() );
        gisMaster.setCity( qrcForm.getInstallationAddressPojo().getCityName() );
        gisMaster.setArea( qrcForm.getInstAddrArea() );
        List<GISMasterPojo> gisMasterPojos = GISUtils.getActiveGisMasterPojos();
        int index = gisMasterPojos.indexOf( gisMaster );
        if ( index >= 0 )
        {
            gisMaster = gisMasterPojos.get( index );
            qrcForm.getInstallationAddressPojo().setStateId( gisMaster.getStateId() );
            qrcForm.getInstallationAddressPojo().setCityId( gisMaster.getCityId() );
            qrcForm.getInstallationAddressPojo().setInstAreaId( gisMaster.getAreaId() );
            List<SocietyPojo> societyPojos = GISUtils.getActiveSocieties( gisMaster.getCountryId(),
                                                                          gisMaster.getStateId(),
                                                                          gisMaster.getCityId(), gisMaster.getAreaId() );
            SocietyPojo societyPojo = GISUtils.getSociety( qrcForm.getInstAddrSociety(), societyPojos );
            if ( StringUtils.isValidObj( societyPojo ) )
            {
                qrcForm.getInstallationAddressPojo().setInstSocietyId( societyPojo.getSocietyId() );
            }
        }
    }

    //    private void prepareChangeInstAddrPojo( QrcForm qrcForm )
    //    {
    //        String stateName = qrcForm.getInstallationAddressPojo().getStateName();
    //        String cityName = qrcForm.getInstallationAddressPojo().getCityName();
    //        String area = qrcForm.getInstAddrArea();
    //        String locality = qrcForm.getInstAddrLocality();
    //        String society = qrcForm.getInstAddrSociety();
    //        if ( StringUtils.isNotBlank( stateName ) && StringUtils.isNotBlank( cityName ) )
    //        {
    //            StatePojo statePojo = GISUtils.getState( stateName );
    //            if ( StringUtils.isValidObj( statePojo ) )
    //            {
    //                qrcForm.getInstallationAddressPojo().setStateId( statePojo.getStateId() );
    //                CityPojo cityPojo = GISUtils.getCity( cityName, statePojo.getCities() );
    //                if ( StringUtils.isValidObj( cityPojo ) )
    //                {
    //                    qrcForm.getInstallationAddressPojo().setCityId( cityPojo.getCityId() );
    //                }
    //            }
    //        }
    //        if ( StringUtils.isNotBlank( area ) )
    //        {
    //            AreaPojo areaPojo = GISUtils.getArea( area, GISUtils.getActiveAreas( IAppConstants.COUNTRY_INDIA,
    //                                                                                 stateName, cityName ) );
    //            if ( StringUtils.isValidObj( areaPojo ) )
    //            {
    //                qrcForm.getInstallationAddressPojo().setInstAreaId( areaPojo.getAreaId() );
    //            }
    //        }
    //        if ( StringUtils.isNotBlank( locality ) )
    //        {
    //            LocalityPojo localityPojo = GISUtils.getLocality( locality, GISUtils
    //                    .getActiveLocalities( IAppConstants.COUNTRY_INDIA, stateName, cityName, area ) );
    //            if ( StringUtils.isValidObj( localityPojo ) )
    //            {
    //                qrcForm.getInstallationAddressPojo().setInstLocalityId( localityPojo.getLocalityId() );
    //            }
    //        }
    //        if ( StringUtils.isNotBlank( society ) )
    //        {
    //            SocietyPojo societyPojo = GISUtils.getSociety( society, GISUtils
    //                    .getActiveSocieties( IAppConstants.COUNTRY_INDIA, stateName, cityName, area, locality ) );
    //            if ( StringUtils.isValidObj( societyPojo ) )
    //            {
    //                qrcForm.getInstallationAddressPojo().setInstSocietyId( societyPojo.getSocietyId() );
    //            }
    //        }
    //    }
    @Override
    public CrmQrcDto disconnectionCall( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        if ( StringUtils.isValidObj( inQrcForm.getCustDetailsPojo() ) )
            crmQrcDto.setCustomerRecordId( inQrcForm.getCustDetailsPojo().getRecordId() );
        crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
        crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
        crmQrcDto.setSrTicketNo( inQrcForm.getSrTicketNo() );
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        crmQrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
        crmQrcDto.setChurnType( inQrcForm.getChurnType() );
        try
        {
            if ( ( StringUtils
                    .equals( inQrcForm.getCustDetailsPojo().getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                    || ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getStatus(),
                                             CRMStatusCode.BARRED.getStatusCode() ) ) )
            {
                crmQrcDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.TDC.getStatusCode() );
            }
            else if ( ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getStatus(),
                                            CRMStatusCode.SC.getStatusCode() ) )
                    || ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getStatus(),
                                             CRMStatusCode.TDC.getStatusCode() ) ) )
            {
                crmQrcDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.PDC.getStatusCode() );
            }
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                                             CRMParameter.CHANGE_STATUS.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error call service disconnectionCall. " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto createCustomerInteraction( CrmQrcDto inQrcDto )
    {
        try
        {
            inQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.CREATE.getParameter(),
                                                            CRMParameter.CUSTOMER_INTERACTION.getParameter(), inQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Soap client Exception", ex );
        }
        catch ( Exception exe )
        {
            LOGGER.error( "Exception while creating Customer Interaction ", exe );
        }
        return inQrcDto;
    }

    @Override
    public CrmQrcDto searchCustomerInteraction( CrmQrcDto inQrcDto )
    {
        try
        {
            inQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                            CRMParameter.CUSTOMER_INTERACTION.getParameter(), inQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Soap client Exception", ex );
        }
        catch ( Exception exe )
        {
            LOGGER.error( "Exception while searching Customer Interaction ", exe );
        }
        return inQrcDto;
    }

    @Override
    public CrmMassOutagePojo getMassOutage( String inService,
                                            CrmAddressDetailsPojo inCrmAddressDetailsPojo,
                                            CrmNetworkConfigurationsPojo inCrmNetworkConfigurationsPojo )
    {
        try
        {
            CrmMassOutageDto outageDto = new CrmMassOutageDto();
            CrmMassOutagePojo outagePojo = new CrmMassOutagePojo();
            outagePojo.setServiceName( inService );
            outageDto.setCrmMassOutagePojo( outagePojo );
            if ( StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), inService ) )
            {
                CrmMassOutageSocietyPojo societyOutage = new CrmMassOutageSocietyPojo();
                societyOutage.setSocietyId( inCrmAddressDetailsPojo.getInstSocietyId() );
                outageDto.setCrmMassOutageSocietyPojo( societyOutage );
            }
            else
            {
                CrmMassOutageMastersPojo masterOutage = new CrmMassOutageMastersPojo();
                if ( StringUtils.isValidObj( inCrmNetworkConfigurationsPojo )
                        && StringUtils.isNotBlank( inCrmNetworkConfigurationsPojo.getOption82() ) )
                {
                    masterOutage.setMasterId( StringUtils.numericValue( inCrmNetworkConfigurationsPojo.getOption82() ) );
                }
                outageDto.setCrmMassOutageMastersPojo( masterOutage );
            }
            outageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.TRACK.getParameter(), outageDto );
            if ( StringUtils.equals( outageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                return outageDto.getCrmMassOutagePojo();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching Customer mass outage info ", ex );
        }
        return null;
    }

    @Override
    public CrmQrcDto getFunctionalBinbyId( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.DEPENDENT_FUNCTION_BINS.getParameter(),
                                                               inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while resloving functionalBin description: ", ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto qrcRcaList( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.QRC_RCA_LIST.getParameter(), inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching RCA List: ", ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto qrcRcaReasonList( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.LIST.getParameter(), CRMParameter.QRC_RCA_REASONS.getParameter(),
                                    inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching Resolution List: ", ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getCustomerTickets( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.VIEW.getParameter(), CRMParameter.SERVICE_REQUEST.getParameter(),
                                    inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto applyWaiver( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
        crmQrcDto.setSrTicketNo( inQrcForm.getSrTicketNo() );
        inQrcForm.getCustWaiverPojo().setQrcCategory( "Billing" );
        crmQrcDto.setCrmCustWaiverPojo( inQrcForm.getCustWaiverPojo() );
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.POST.getParameter(),
                                                             CRMParameter.WAIVER.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "applyWaiver: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getCustWaiverPojos( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
        crmQrcDto.setCrmCustWaiverPojo( inQrcForm.getCustWaiverPojo() );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                             CRMParameter.WAIVER.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception getCustWaiverPojos:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getBillList( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.LIST.getParameter(), CrmInvoiceDetailsPojo.class.getSimpleName(),
                                    inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception getCustWaiverPojos:: " + ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto editDevice( CrmQrcDto crmQrcDto )
    {
        try
        {
            LOGGER.info( "Client:::editDevice calling qrc Operations" );
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.MODIFY.getParameter(),
                                                             CRMParameter.DEVICE_AND_NETWORK_DETAILS.getParameter(),
                                                             crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "applyWaiver: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto saveAccessories( CrmQrcDto crmQrcDto, QrcForm qrcForm, String param )
    {
        try
        {
            LOGGER.info( "Client:::save Accessories calling qrc Operations" );
            crmQrcDto.setCrmCustAssetDetailsPojo( qrcForm.getCrmCustAssetDetailsPojo() );
            if ( StringUtils.isNotEmpty( qrcForm.getSrTicketNo() ) )
            {
                crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
            }
            crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.SAVE.getParameter(), param, crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "save Accesories exception client side: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto searchAccessories( CrmQrcDto crmQrcDto, String param )
    {
        try
        {
            LOGGER.info( "Client:::search Accessories calling qrc Operations" );
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(), param, crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "search Accessories exception client side: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto sendLegalMail( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.OUTSTANDING_NOTICE.getParameter(),
                                    CRMParameter.SEND_LEGAL_MAIL.getParameter(), inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception sendLegalMail:: " + ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    @Override
    public void getBasePlanInfo( QrcForm qrcForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
        //crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        LOGGER.info( "Plan Master Details" + crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getBasePlanInfo" );
            LOGGER.error( ex );
        }
        LOGGER.info( "Plan Master Size::" + masterDataDto.getCrmPlanMasterPojos().size() );
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
        {
            qrcForm.setCrmPlanMasterPojo( masterDataDto.getCrmPlanMasterPojos().get( 0 ) );
            LOGGER.info( "Plan Master Pojo" + masterDataDto.getCrmPlanMasterPojos().get( 0 ) );
        }
    }

    @Override
    public CrmQrcDto activateCustomerBasePlan( QrcForm qrcForm, String userId )
    {
        CrmQrcDto crmQrcDto = null;
        boolean process = false;
        try
        {
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
            process = balanceCheckPolicy( crmQrcDto, qrcForm );
            if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                     CRMDisplayListConstants.POST_PAID.getCode() ) )
            {
                process = true;
            }
            if ( process && !StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM997.getStatusCode() ) )
            {
                crmQrcDto.setPlanCategory( "Base Plan Change" );
                if ( CommonValidator.isValidCollection( qrcForm.getSrTicketsPojos() ) )
                {
                    crmQrcDto.getCrmSrTicketsPojos().addAll( qrcForm.getSrTicketsPojos() );
                }
                LOGGER.info( "Activation Type:: " + qrcForm.getActivationType() );
                crmQrcDto.setActivationTime( qrcForm.getActivationType() );
                crmQrcDto.setActivityAction( qrcForm.getPlanCategory() );
                crmQrcDto.setUserId( userId );
                crmQrcDto.setCrmPlanDetailsPojo( qrcForm.getPlanDetailsPojo() );
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                crmQrcDto.setExpiryDate( qrcForm.getCustAdditionalDetails().getExpiryDate() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                 CRMParameter.PLAN_MIGRATION.getParameter(), crmQrcDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception activateCustomerBasePlan:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    //    @Override
    //    public CrmQrcDto getTicketOtherDetails( CrmQrcDto inCrmQrcDto )
    //    {
    //        try
    //        {
    //            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
    //                                                               CRMParameter.TICKET_OTHER_DETAILS.getParameter(),
    //                                                               inCrmQrcDto );
    //        }
    //        catch ( SOAPException_Exception ex )
    //        {
    //            LOGGER.info( "Exception while get ticket other details: " + ex );
    //            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
    //            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
    //        }
    //        return inCrmQrcDto;
    //    }
    @Override
    public CrmQrcDto mountBoosterPlan( QrcForm inQrcForm, String userID )
    {
        LOGGER.info( "Inside QrcManagerImpl, mountBoosterPlan" );
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            qrcDto.setUserId( userID );
            qrcDto.setActivityAction( inQrcForm.getPlanCategory() );
            qrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
            qrcDto.getCrmCustAssetDetailsPojos().addAll( inQrcForm.getCrmCustAssetDetailsPojos() );
            if ( CommonValidator.isValidCollection( inQrcForm.getSrTicketsPojos() ) )
            {
                qrcDto.getCrmSrTicketsPojos().addAll( inQrcForm.getSrTicketsPojos() );
            }
            if ( StringUtils.isValidObj( inQrcForm.getRemarksPojo() ) )
            {
                qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
            }
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                          CRMParameter.PLAN_BOOSTER.getParameter(), qrcDto );
            LOGGER.info( "Service Status : " + qrcDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while Mounting Booster Plan ", ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public ConfigDto getRemarks( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, getRemarks" );
        try
        {
            inConfigDto = getCrmConfigClient().geMappingIdByRemarks( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Remarks SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public CrmQrcDto fetchActivatedVAS( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, fetchActivatedVAS" );
        try
        {
            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.VAS.getParameter(), inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Remarks SOAP Exception : ", ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto activateDeactivateVAS( QrcForm inQrcForm, String inUserId )
    {
        LOGGER.info( "Inside QrcManagerImpl, activateDeactivateVAS" );
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            qrcDto.setUserId( inUserId );
            qrcDto.setActivityAction( inQrcForm.getPlanCategory() );
            qrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
            qrcDto.getCrmCustAssetDetailsPojos().addAll( inQrcForm.getCrmCustAssetDetailsPojos() );
            if ( CommonValidator.isValidCollection( inQrcForm.getSrTicketsPojos() ) )
            {
                qrcDto.getCrmSrTicketsPojos().addAll( inQrcForm.getSrTicketsPojos() );
            }
            if ( StringUtils.isValidObj( inQrcForm.getRemarksPojo() ) )
            {
                qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
            }
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.PROCESS.getParameter(),
                                                          CRMParameter.VAS.getParameter(), qrcDto );
            LOGGER.info( "Service Status : " + qrcDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while Mounting Booster Plan ", ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto customerPlanReactivation( QrcForm qrcForm, String userId )
    {
        LOGGER.info( "Inside QrcManagerImpl, customerPlanReactivation" );
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
        boolean allowedFlag = balanceCheckCondition( crmQrcDto, qrcForm );
        if ( allowedFlag && !StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM997.getStatusCode() ) )
        {
            if ( CommonValidator.isValidCollection( qrcForm.getSrTicketsPojos() ) )
            {
                crmQrcDto.getCrmSrTicketsPojos().addAll( qrcForm.getSrTicketsPojos() );
            }
            LOGGER.info( "Activation Time:: " + qrcForm.getActivationType() );
            LOGGER.info( "Activation Action:: " + qrcForm.getPlanCategory() );
            crmQrcDto.setActivationTime( qrcForm.getActivationType() );
            crmQrcDto.setActivityAction( qrcForm.getPlanCategory() );
            crmQrcDto.setUserId( userId );
            crmQrcDto.setCrmPlanDetailsPojo( qrcForm.getPlanDetailsPojo() );
            crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
            crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
            crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
            if ( StringUtils.isValidObj( qrcForm.getRemarksPojo() ) )
            {
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
            }
            try
            {
                crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                 CRMParameter.PLAN_REACTIVATION.getParameter(),
                                                                 crmQrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "exception customerPlanReactivation:: " + ex );
                crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
                crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
            }
        }
        return crmQrcDto;
    }

    private boolean balanceCheckCondition( CrmQrcDto inCrmQrcDto, QrcForm inQrcForm )
    {
        LOGGER.info( "Inside QrcManagerImpl, -balanceCheckCondition" );
        CrmPlanMasterPojo crmPlanMasterPojo = null;
        CrmPlanMasterPojo crmPlanMasterPojoAddon = null;
        LOGGER.info( "inQrcForm.getAddonAction() " + inQrcForm.getAddonAction() );
        try
        {
            if ( CommonValidator.isValidCollection( inQrcForm.getPlanMasterList() )
                    && StringUtils.isNotBlank( inQrcForm.getSelectedPlanCode() ) )
            {
                LOGGER.info( "Selected Plan Code:: " + inQrcForm.getSelectedPlanCode() );
                crmPlanMasterPojo = new CrmPlanMasterPojo();
                crmPlanMasterPojo.setPlanCode( inQrcForm.getSelectedPlanCode() );
                int pojoIndex = inQrcForm.getPlanMasterList().indexOf( crmPlanMasterPojo );
                if ( pojoIndex >= 0 )
                {
                    crmPlanMasterPojo = inQrcForm.getPlanMasterList().get( pojoIndex );
                    inCrmQrcDto.setPlanMasterPojo( crmPlanMasterPojo );
                }
            }
            if ( StringUtils.isNotBlank( inQrcForm.getAddonAction() )
                    && StringUtils.equals( inQrcForm.getAddonAction(), "WA" ) )
            {
                if ( CommonValidator.isValidCollection( inQrcForm.getAddonPlanMasterList() )
                        && StringUtils.isNotBlank( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                {
                    LOGGER.info( "Selected Addon Plan Code:: " + inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                    crmPlanMasterPojoAddon = new CrmPlanMasterPojo();
                    crmPlanMasterPojoAddon.setPlanCode( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                    int pojoIndex = inQrcForm.getAddonPlanMasterList().indexOf( crmPlanMasterPojoAddon );
                    if ( pojoIndex >= 0 )
                    {
                        crmPlanMasterPojoAddon = inQrcForm.getAddonPlanMasterList().get( pojoIndex );
                        //inCrmQrcDto.setPlanMasterPojo( crmPlanMasterPojo );
                    }
                    LOGGER.info( "CrmPlanMasterAddon Pojo::" + crmPlanMasterPojoAddon.toString() );
                }
            }
            if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
            {
                if ( StringUtils.isValidObj( crmPlanMasterPojo )
                        && StringUtils.isValidObj( crmPlanMasterPojo.getRentInclTax() )
                        && StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                               CRMDisplayListConstants.PRE_PAID.getCode() )
                        || ( StringUtils.isValidObj( crmPlanMasterPojoAddon ) && StringUtils
                                .isValidObj( crmPlanMasterPojoAddon.getRentInclTax() ) ) )
                {
                    Double rentInclTax = 0D;
                    if ( StringUtils.isNotBlank( inQrcForm.getAddonAction() )
                            && StringUtils.equals( inQrcForm.getAddonAction(), "WA" ) )
                    {
                        rentInclTax = crmPlanMasterPojo.getRentInclTax().doubleValue()
                                + crmPlanMasterPojoAddon.getRentInclTax().doubleValue();
                    }
                    else
                    {
                        rentInclTax = crmPlanMasterPojo.getRentInclTax().doubleValue();
                    }
                    LOGGER.info( "Calculated Rent : " + rentInclTax );
                    //Pre-paid - Allowed only if Customer wallet Balance is equal or greater than selected plan rental charges
                    if ( inQrcForm.getCustAdditionalDetails().getBalance() < ( rentInclTax - 1 ) )
                    {
                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM427.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM427.getStatusDesc() );
                        return false;
                    }
                    inCrmQrcDto.setCustAdditionalDetails( inQrcForm.getCustAdditionalDetails() );
                }
                else
                {
                    //Post-paid - Allowed only if Customer has no outstanding
                    if ( inQrcForm.getCustAdditionalDetails().getBalance() > 0 )
                    {
                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM428.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM428.getStatusDesc() );
                        return false;
                    }
                }
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking balance condition for customer", ex );
            //return false;
        }
        return true;
    }

    private boolean balanceCheckPolicy( CrmQrcDto inCrmQrcDto, QrcForm inQrcForm )
    {
        LOGGER.info( "Inside QrcManagerImpl, -balanceCheckPolicy" );
        CrmPlanMasterPojo newCrmPlanMasterPojo = null;
        CrmPlanMasterPojo oldCrmPlanMasterPojo = null;
        try
        {
            if ( CommonValidator.isValidCollection( inQrcForm.getPlanMasterList() )
                    && StringUtils.isNotBlank( inQrcForm.getSelectedPlanCode() ) )
            {
                LOGGER.info( "Selected Plan Code:: " + inQrcForm.getSelectedPlanCode() );
                newCrmPlanMasterPojo = new CrmPlanMasterPojo();
                newCrmPlanMasterPojo.setPlanCode( inQrcForm.getSelectedPlanCode() );
                int pojoIndex = inQrcForm.getPlanMasterList().indexOf( newCrmPlanMasterPojo );
                if ( pojoIndex >= 0 )
                {
                    newCrmPlanMasterPojo = inQrcForm.getPlanMasterList().get( pojoIndex );
                    inCrmQrcDto.setPlanMasterPojo( newCrmPlanMasterPojo );
                }
            }
            if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
            {
                oldCrmPlanMasterPojo = inQrcForm.getCrmPlanMasterPojo();
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    if ( !StringUtils.equals( inQrcForm.getActivationType(), IAppConstants.NBC ) )
                    {
                        LOGGER.info( "PREPAID CUSTOMER" );
                        Double oldRentInclTax = oldCrmPlanMasterPojo.getRentInclTax().doubleValue();
                        LOGGER.info( "OLD Rental: " + oldRentInclTax );
                        Double newRentInclTax = 0D;
                        newRentInclTax = newCrmPlanMasterPojo.getRentInclTax().doubleValue();
                        LOGGER.info( "New Rental:: " + newRentInclTax );
                        if ( oldRentInclTax < newRentInclTax )
                        {
                            double differentialAmmount = newRentInclTax - oldRentInclTax;
                            LOGGER.info( "Diffrential Ammount:: " + differentialAmmount );
                            LOGGER.info( "Wallet Balance:: " + inQrcForm.getCustAdditionalDetails().getBalance() );
                            if ( inQrcForm.getCustAdditionalDetails().getBalance() < ( differentialAmmount - 1 ) )
                            {
                                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM427.getStatusCode() );
                                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM427.getStatusDesc() );
                                return false;
                            }
                        }
                    }
                }
                else
                {
                    LOGGER.info( "POSTPAID CUSTOMER" );
                    //Post-paid - Allowed only if Customer has no outstanding
                    if ( inQrcForm.getCustAdditionalDetails().getBalance() > 0 )
                    {
                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM428.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM428.getStatusDesc() );
                        return false;
                    }
                }
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking balance condition for customer", ex );
            //return false;
        }
        return true;
    }

    private boolean addOnbalanceCheckCondition( CrmQrcDto inCrmQrcDto, QrcForm inQrcForm )
    {
        LOGGER.info( "Inside QrcManagerImpl, -addOnbalanceCheckCondition" );
        CrmPlanMasterPojo newCrmAddonPlanMasterPojo = null;
        CrmPlanMasterPojo oldCrmPlanMasterPojo = null;
        try
        {
            LOGGER.info( "Selected Addon Plan Code:: " + inQrcForm.getAddonPlanCode() );
            newCrmAddonPlanMasterPojo = new CrmPlanMasterPojo();
            newCrmAddonPlanMasterPojo.setPlanCode( inQrcForm.getAddonPlanCode() );
            int pojoIndex = inQrcForm.getAddonPlanMasterList().indexOf( newCrmAddonPlanMasterPojo );
            if ( pojoIndex >= 0 )
            {
                newCrmAddonPlanMasterPojo = inQrcForm.getAddonPlanMasterList().get( pojoIndex );
                inCrmQrcDto.setAddOnPlanPojo( newCrmAddonPlanMasterPojo );
            }
            if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
            {
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    if ( !StringUtils.equals( inQrcForm.getActivationType(), IAppConstants.NBC ) )
                    {
                        if ( StringUtils.isNotEmpty( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                        {
                            MasterDataDto addonPlanMasterDataDto = getAddonPlanInfo( inQrcForm );
                            if ( CommonValidator.isValidCollection( addonPlanMasterDataDto.getCrmPlanMasterPojos() ) )
                            {
                                oldCrmPlanMasterPojo = addonPlanMasterDataDto.getCrmPlanMasterPojos().get( 0 );
                                Double oldRentInclTax = oldCrmPlanMasterPojo.getRentInclTax().doubleValue();
                                Double newRentInclTax = newCrmAddonPlanMasterPojo.getRentInclTax().doubleValue();
                                if ( oldRentInclTax < newRentInclTax )
                                {
                                    double differentialAmmount = newRentInclTax - oldRentInclTax;
                                    if ( inQrcForm.getCustAdditionalDetails().getBalance() <= ( differentialAmmount - 1 ) )
                                    {
                                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM427.getStatusCode() );
                                        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM427.getStatusDesc() );
                                        return false;
                                    }
                                }
                            }
                        }
                        else
                        {
                            if ( inQrcForm.getCustAdditionalDetails().getBalance() <= ( newCrmAddonPlanMasterPojo
                                    .getRentInclTax() - 1 ) )
                            {
                                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM427.getStatusCode() );
                                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM427.getStatusDesc() );
                                return false;
                            }
                        }
                    }
                }
                else
                {
                    //Post-paid - Allowed only if Customer has no outstanding
                    if ( inQrcForm.getCustAdditionalDetails().getBalance() > 0 )
                    {
                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM428.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM428.getStatusDesc() );
                        return false;
                    }
                }
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking balance condition for customer", ex );
            //return false;
        }
        return true;
    }

    @Override
    public CrmQrcDto cancelPlanMigration( QrcForm inQrcForm, String userId, String presentStage )
    {
        LOGGER.info( "in cancelPlanMigration :: userID " + userId );
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.getCrmSrTicketsPojos().addAll( inQrcForm.getSrTicketsPojos() );
        crmQrcDto.setCustomerId( inQrcForm.getCustDetailsPojo().getCustomerId() );
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        crmQrcDto.setUserId( userId );
        crmQrcDto.setPresentStage( presentStage );
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.CANCEL.getParameter(),
                                                             CRMParameter.PLAN_MIGRATION.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to cancel plan migration request", ex );
            if ( StringUtils.isBlank( crmQrcDto.getStatusCode() ) )
            {
                crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
                crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
            }
        }
        return crmQrcDto;
    }

    @Override
    public void trackPaymentHistory( QrcForm qrcForm, boolean inAll )
    {
        CrmFinanceDto crmFinanceDto = null;
        String fromDate = IDateConstants.SDF_DD_MMM_YYYY.format( DateUtils
                .getFutureEndDate( -181, Calendar.DAY_OF_YEAR ).getTime() );
        if ( inAll )
        {
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTimeInMillis( 0 );
            fromDate = IDateConstants.SDF_DD_MMM_YYYY.format( fromCal.getTime() );
        }
        LOGGER.info( "calling payment history" );
        if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) )
        {
            try
            {
                crmFinanceDto = new CrmFinanceDto();
                if ( !inAll )
                {
                    CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
                    if ( StringUtils.equals( qrcForm.getPlanType(), "APP" ) )
                    {
                        crmPaymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
                        crmPaymentDetailsPojo.setPaymentChannel( "APP" );
                    }
                    else
                    {
                        crmPaymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
                    }
                    crmFinanceDto.setPaymentDetailsPojo( crmPaymentDetailsPojo );
                }
                LOGGER.info( qrcForm.getCustDetailsPojo().getRecordId() + " "
                        + qrcForm.getCustDetailsPojo().getCustomerId() );
                crmFinanceDto.setFromDate( fromDate );
                crmFinanceDto.setToDate( DateUtils.getCurrentDateStr() );
                crmFinanceDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                crmFinanceDto = getFinanceService().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                                  CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
                qrcForm.setCustPaymentDetailsPojos( new ArrayList<CrmPaymentDetailsPojo>() );
                if ( CommonValidator.isValidCollection( crmFinanceDto.getPaymentDetailsPojos() ) )
                {
                    LOGGER.info( "payment history size:: " + crmFinanceDto.getPaymentDetailsPojos().size() );
                    if ( inAll )
                    {
                        SortingComparator<CrmPaymentDetailsPojo> sorter = new SortingComparator<CrmPaymentDetailsPojo>( "createdTime" );
                        Collections.sort( crmFinanceDto.getPaymentDetailsPojos(), sorter );
                        sorter = null;
                        qrcForm.setCustPaymentDetailsPojos( crmFinanceDto.getPaymentDetailsPojos() );
                    }
                    else
                    {
                        SortingComparator<CrmPaymentDetailsPojo> sorter = new SortingComparator<CrmPaymentDetailsPojo>( "createdTime" );
                        Collections.sort( crmFinanceDto.getPaymentDetailsPojos(), Collections.reverseOrder( sorter ) );
                        sorter = null;
                        int counter = 0;
                        List<CrmPaymentDetailsPojo> crmPaymentDetailsPojos = new ArrayList<CrmPaymentDetailsPojo>();
                        for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo2 : crmFinanceDto.getPaymentDetailsPojos() )
                        {
                            if ( counter < 6 )
                            {
                                crmPaymentDetailsPojos.add( crmPaymentDetailsPojo2 );
                                counter++;
                            }
                        }
                        qrcForm.setCustPaymentDetailsPojos( crmPaymentDetailsPojos );
                    }
                }
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "error trackPaymentHistory", ex );
            }
        }
    }

    @Override
    public List<CrmPlanMasterPojo> getMigratedActivationPlan( QrcForm qrcForm )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", qrcForm
                .getCustDetailsPojo().getProduct(), qrcForm.getCustDetailsPojo().getServiceType() );
        String prepaid = ( qrcForm.getCustDetailsPojo().getServiceType().equals( "PR" ) ? "Y" : "N" );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanType( qrcForm.getPlanType() );
        crmPlanMasterPojo.setPlanUsageType( qrcForm.getPlanUsageType() );
        crmPlanMasterPojo.setMigrAllowedYn( IAppConstants.Y );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setPrepaidYn( prepaid );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        LOGGER.info( "plan master pojo for activation plan :: " + crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getMigratedActivationPlan" );
            LOGGER.error( ex );
        }
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
            return masterDataDto.getCrmPlanMasterPojos();
        else
            return null;
    }

    @Override
    public List<CrmPlanMasterPojo> getAddonPlanDetails( QrcForm inQrcForm )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", inQrcForm
                .getCustDetailsPojo().getProduct(), inQrcForm.getCustDetailsPojo().getServiceType() );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.ADDON_PLAN.getCode() );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmPlanMasterPojo.setMigrAllowedYn( IAppConstants.Y );
        LOGGER.info( "Plan type for addon: " + inQrcForm.getPlanType() );
        if ( StringUtils.equals( inQrcForm.getPlanType(), "ADDON_FOC" ) )
        {
            crmPlanMasterPojo.setPlanType( "FOC" );
        }
        else
        {
            crmPlanMasterPojo.setPlanType( "PAID" );
        }
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getAddonPlanDetails" );
            LOGGER.error( ex );
        }
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
            return masterDataDto.getCrmPlanMasterPojos();
        else
            return null;
    }

    @Override
    public List<CrmPlanMasterPojo> getReActivationPlan( QrcForm qrcForm )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", qrcForm
                .getCustDetailsPojo().getProduct(), qrcForm.getCustDetailsPojo().getServiceType() );
        String prepaid = ( qrcForm.getCustDetailsPojo().getServiceType().equals( "PR" ) ? "Y" : "N" );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanType( qrcForm.getPlanType() );
        crmPlanMasterPojo.setPlanUsageType( qrcForm.getPlanUsageType() );
        crmPlanMasterPojo.setMigrAllowedYn( IAppConstants.Y );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setPrepaidYn( prepaid );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getReActivationPlan" );
            LOGGER.error( ex );
        }
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
            return masterDataDto.getCrmPlanMasterPojos();
        else
            return null;
    }

    @Override
    public CrmQrcDto getMigrationPolicy( CrmQrcDto crmQrcDto )
    {
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.PROCESS.getParameter(),
                                                             CRMParameter.PLAN_MIGRATION_POLICY.getParameter(),
                                                             crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception getMigrationPolicy:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getAddOnMigrationPolicy( CrmQrcDto crmQrcDto )
    {
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.PROCESS.getParameter(),
                                                             CRMParameter.ADDON_PLAN_MIGRATION_POLICY.getParameter(),
                                                             crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception getMigrationPolicy:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getBillDetails( String inCustomerId, String inServiceType )
    {
        LOGGER.info( "in QRCMangerImpl:getBillDetails:::" + inCustomerId );
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCustomerId( inCustomerId );
        qrcDto.setServiceType( inServiceType );
        try
        {
            LOGGER.info( "calling service for bill details" );
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                          CrmInvoiceDetailsPojo.class.getSimpleName(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error getBillDetails", ex );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getCustomerPriodicUsageDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, getCustomerPriodicUsageDetails" );
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            try
            {
                inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.APICALL.getParameter(),
                                                                   CRMParameter.USAGE_DETAILS.getParameter(),
                                                                   inCrmQrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "Exception while getting usage details ", ex );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public void prepareDownloadResponse( HttpServletResponse inResponse,
                                         com.np.tele.crm.utils.ExcelWriteUtils inExcelWriteUtils,
                                         ServletContext inServletContext,
                                         String directory )
    {
        LOGGER.info( "Inside QrcManagerImpl, prepareDownloadResponse" );
        ServletOutputStream servletOutputStream = null;
        byte[] outputByte = null;
        InputStream inputStream = null;
        URL url = null;
        try
        {
            inResponse.setHeader( "Content-Length", String.valueOf( inExcelWriteUtils.getCreatedExcel().length() ) );
            inResponse.setContentType( "application/vnd.ms-excel" );//vnd.openxmlformats-officedocument.spreadsheetml.sheet
            inResponse.setHeader( "Content-Disposition", "attachment;filename=" + inExcelWriteUtils.getFileName() );
            url = inServletContext.getResource( "/" + directory + "/" + inExcelWriteUtils.getFileName() );
            inputStream = url.openStream();
            servletOutputStream = inResponse.getOutputStream();
            outputByte = new byte[4096];
            while ( inputStream.read( outputByte, 0, 4096 ) != -1 )
            {
                servletOutputStream.write( outputByte, 0, 4096 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Prepare Download Response Exception ", ex );
        }
        finally
        {
            try
            {
                inputStream.close();
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Stream closing exception ", ex );
            }
        }
    }

    @Override
    public CrmQrcDto sendCustomerUsage( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, sendCustomerUsage" );
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            try
            {
                inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.APICALL.getParameter(),
                                                                   CRMParameter.SEND_USAGE_DETAILS.getParameter(),
                                                                   inCrmQrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "Exception while sending usage details ", ex );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto customerPlanRenewal( QrcForm qrcForm, String userId )
    {
        LOGGER.info( "Inside QrcManagerImpl, customerPlanRenewal" );
        boolean process;
        CrmQrcDto crmQrcDto = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
            process = balanceCheckCondition( crmQrcDto, qrcForm );
            if ( process )
            {
                /*if ( CommonValidator.isValidCollection( qrcForm.getAddonPlanMasterList() ) )
                {
                    LOGGER.info( "Selected Addon Plan Code:: " + qrcForm.getAddonPlanCode() );
                    CrmPlanMasterPojo crmAddonPlanMasterPojo = new CrmPlanMasterPojo();
                    crmAddonPlanMasterPojo.setPlanCode( qrcForm.getAddonPlanCode() );
                    int pojoIndex = qrcForm.getAddonPlanMasterList().indexOf( crmAddonPlanMasterPojo );
                    if ( pojoIndex >= 0 )
                    {
                        LOGGER.info( "Found Addon POjO:: " );
                        crmAddonPlanMasterPojo = qrcForm.getAddonPlanMasterList().get( pojoIndex );
                        crmQrcDto.setAddOnPlanPojo( crmAddonPlanMasterPojo );
                    }
                }*/
                if ( CommonValidator.isValidCollection( qrcForm.getSrTicketsPojos() ) )
                {
                    crmQrcDto.getCrmSrTicketsPojos().addAll( qrcForm.getSrTicketsPojos() );
                }
                LOGGER.info( "Activation Time:: " + qrcForm.getActivationType() );
                LOGGER.info( "Activation Action:: " + qrcForm.getPlanCategory() );
                crmQrcDto.setActivationTime( qrcForm.getActivationType() );
                crmQrcDto.setActivityAction( qrcForm.getPlanCategory() );
                crmQrcDto.setUserId( userId );
                crmQrcDto.setCrmPlanDetailsPojo( qrcForm.getPlanDetailsPojo() );
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                if ( StringUtils.isNotBlank( qrcForm.getAddonAction() ) )
                {
                    crmQrcDto.setAddonAction( qrcForm.getAddonAction() );
                }
                try
                {
                    crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.RENEW.getParameter(),
                                                                     CRMParameter.PLAN_RENEWAL.getParameter(),
                                                                     crmQrcDto );
                }
                catch ( SOAPException_Exception ex )
                {
                    LOGGER.info( "exception customerPlanRenewal:: " + ex );
                    crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
                    crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while plan renewal for customer", ex );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto sendEBill( CrmInvoiceDetailsPojo inInvoiceDetails, String inBrand )
    {
        LOGGER.info( "Inside QrcManagerImpl, sendEBill" );
        AlertsDto alertsDto = null;
        AlertStatusPojo alertStatusPojo = null;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        if ( StringUtils.isValidObj( inInvoiceDetails )
                && StringUtils.isNotBlank( inInvoiceDetails.getPasswordPdfFile() )
                && !StringUtils.equals( inBrand, IAppConstants.BRAND_INITIA ) )
        {
            try
            {
                alertsDto = new AlertsDto();
                EventsPojo eventsPojo = new EventsPojo();
                eventsPojo.setEventName( CRMEvents.SEND_EBILL.getEventName() );
                alertsDto.getAttachmentPaths().add( inInvoiceDetails.getPasswordPdfFile() );
                alertsDto.setCustomerId( inInvoiceDetails.getCustomerId() );
                alertsDto.setEventsPojo( eventsPojo );
                alertsDto.setParamName( CRMRequestType.CUSTOMER_ID.getRequestCode() );
                alertsDto.setParamValue( inInvoiceDetails.getCustomerId() );
                List<AlertsDto.ParamValues.Entry> list = new ArrayList<AlertsDto.ParamValues.Entry>();
                AlertsDto.ParamValues.Entry entry = new AlertsDto.ParamValues.Entry();
                entry.setKey( "BILL_NUMBER" );
                entry.setValue( inInvoiceDetails.getBillNumber() );
                list.add( entry );
                entry = new AlertsDto.ParamValues.Entry();
                entry.setKey( "BILL_DATE" );
                entry.setValue( DateUtils.convertXmlCalToString( inInvoiceDetails.getBillDate(),
                                                                 IDateConstants.SDF_DD_MMM_YYYY ) );
                list.add( entry );
                entry = new AlertsDto.ParamValues.Entry();
                entry.setKey( "BILL_PERIOD" );
                entry.setValue( inInvoiceDetails.getBillPeriod() );
                list.add( entry );
                if ( StringUtils.isValidObj( inInvoiceDetails.getDueDate() ) )
                {
                    entry = new AlertsDto.ParamValues.Entry();
                    entry.setKey( "BILL_DUE_DATE" );
                    entry.setValue( DateUtils.convertXmlCalToString( inInvoiceDetails.getDueDate(),
                                                                     IDateConstants.SDF_DD_MMM_YYYY ) );
                    list.add( entry );
                }
                ParamValues values = new ParamValues();
                values.getEntry().addAll( list );
                alertsDto.setParamValues( values );
                alertStatusPojo = getCrmAlertsClient().sendAlerts( alertsDto );
                if ( StringUtils.isValidObj( alertStatusPojo ) )
                {
                    if ( StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getEmailSent() )
                            || StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getSmsSent() ) )
                    {
                        crmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    }
                    else
                    {
                        crmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                    }
                }
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "Exception while sending E-Bill details ", ex );
            }
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getCustAdditionDetails( CrmCustomerDetailsPojo inCustDetailsPojo )
    {
        LOGGER.info( "in QrcManagerImpl:getCustAdditionDetails" );
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCustomerDetailsPojo( inCustDetailsPojo );
        try
        {
            qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                          CRMParameter.CUST_ADDITIONAL_DETAILS.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while invoking getCustAdditionDetails:", ex );
        }
        return qrcDto;
    }

    @Override
    public MasterDataDto getAddonPlanInfo( QrcForm qrcForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.ADDON_PLAN.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmPlanMasterPojo.setPlanCode( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
        //        crmPlanMasterPojo.setPlanCode( "PAD155" );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error getPlanInfo" );
            LOGGER.error( ex );
        }
        return masterDataDto;
    }

    @Override
    public CrmQrcDto removeCustomerAddonPlan( QrcForm inQrcForm, String inUserId )
    {
        LOGGER.info( "Add On Removal Request" );
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        try
        {
            if ( StringUtils.isNotBlank( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
            {
                if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    crmQrcDto.setExpiryDate( inQrcForm.getCustAdditionalDetails().getExpiryDate() );
                }
                CrmPlanMasterPojo crmAddonPlanMasterPojo = new CrmPlanMasterPojo();
                crmAddonPlanMasterPojo.setPlanCode( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                crmQrcDto.setAddOnPlanPojo( crmAddonPlanMasterPojo );
                if ( CommonValidator.isValidCollection( inQrcForm.getSrTicketsPojos() ) )
                {
                    crmQrcDto.getCrmSrTicketsPojos().addAll( inQrcForm.getSrTicketsPojos() );
                }
                crmQrcDto.setUserId( inUserId );
                crmQrcDto.setPlanCategory( inQrcForm.getPlanUsageType() );
                crmQrcDto.setActivationTime( inQrcForm.getActivationType() );
                crmQrcDto.setActivityAction( inQrcForm.getPlanUsageType() );
                crmQrcDto.setCrmPlanDetailsPojo( inQrcForm.getPlanDetailsPojo() );
                crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
                crmQrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
                crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
                crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                 CRMParameter.PLAN_MIGRATION.getParameter(), crmQrcDto );
            }
            else
            {
                crmQrcDto.setStatusCode( CRMServiceCode.CRM426.getStatusCode() );
                crmQrcDto.setStatusDesc( CRMServiceCode.CRM426.getStatusDesc() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "exception activateCustomerBasePlan:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto activateCustomerAddonPlan( QrcForm inQrcForm, String inUserId )
    {
        CrmQrcDto crmQrcDto = null;
        boolean process = false;
        try
        {
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
            process = addOnbalanceCheckCondition( crmQrcDto, inQrcForm );
            if ( process && !StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM997.getStatusCode() ) )
            {
                LOGGER.info( "Add On Added Request" );
                if ( StringUtils.equals( inQrcForm.getCustDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    crmQrcDto.setExpiryDate( inQrcForm.getCustAdditionalDetails().getExpiryDate() );
                }
                if ( CommonValidator.isValidCollection( inQrcForm.getSrTicketsPojos() ) )
                {
                    crmQrcDto.getCrmSrTicketsPojos().addAll( inQrcForm.getSrTicketsPojos() );
                }
                LOGGER.info( "Activation Type:: " + inQrcForm.getActivationType() );
                crmQrcDto.setActivationTime( inQrcForm.getActivationType() );
                crmQrcDto.setActivityAction( inQrcForm.getPlanUsageType() );
                crmQrcDto.setPlanCategory( inQrcForm.getPlanUsageType() );
                crmQrcDto.setUserId( inUserId );
                crmQrcDto.setCrmPlanDetailsPojo( inQrcForm.getPlanDetailsPojo() );
                crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
                crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
                crmQrcDto.setExpiryDate( inQrcForm.getCustAdditionalDetails().getExpiryDate() );
                crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                 CRMParameter.PLAN_MIGRATION.getParameter(), crmQrcDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "exception activateCustomerBasePlan:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto saveTicketHistory( CrmTicketHistoryPojo inPojo )
    {
        CrmQrcDto dataDto = new CrmQrcDto();
        dataDto.setTicketHistory( inPojo );
        try
        {
            dataDto = getQrcServiceClient().ticketOperations( CrmActionEnum.SAVED_REMARKS.getActionCode(),
                                                              CrmTicketHistoryPojo.class.getSimpleName(), dataDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCrmNetworkPartnerList method", ex );
            dataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return dataDto;
    }

    @Override
    public CrmQrcDto ticketOperations( String inQrcActions, CrmQrcDto inCrmQrcDto )
    {
        try
        {
            return getQrcServiceClient().ticketOperations( inQrcActions, CrmSrTicketsPojo.class.getSimpleName(),
                                                           inCrmQrcDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCrmNetworkPartnerList method", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto reopenTickets( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient().ticketOperations( CrmActionEnum.REOPENED.getActionCode(),
                                                                  CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in reopenTickets method", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return inCrmQrcDto;
    }

    @Override
    public void callFaultRepairAPI( String inTo, String inMessage, String inForm, String inOprator, String inDate )
    {
        try
        {
            getFaultRepairClient().qrcFaultRepair( inTo, inMessage, inOprator, inForm, inDate );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in callFaultRepairAPI method", ex );
        }
    }

    @Override
    public CrmQrcDto getCustomerFeedback( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, getCustomerFeedback" );
        try
        {
            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.TRACK.getParameter(),
                                                               CRMParameter.CUSTOMER_FEEDBACK.getParameter(),
                                                               inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Remarks SOAP Exception : ", ex );
        }
        return inCrmQrcDto;
    }
}
