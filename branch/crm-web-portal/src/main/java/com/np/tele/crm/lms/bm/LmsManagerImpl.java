package com.np.tele.crm.lms.bm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.ExcelReadUtils;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.lms.form.LmsForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.CRMLMSService;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.LmsCrfMappingPojo;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class LmsManagerImpl
    implements ILmsManager
{
    private static final Logger LOGGER           = Logger.getLogger( LmsManagerImpl.class );
    private CRMLMSService       lmsServiceClient = null;
    private CRMConfigService    crmConfigClient  = null;
    private CRMUserManagement   crmUserClient    = null;
    private CrmCapService       capServiceClient = null;
    private CrmQrcService       qrcServiceClient = null;

    public CrmCapService getCapServiceClient()
    {
        return capServiceClient;
    }

    public void setCapServiceClient( CrmCapService inCapServiceClient )
    {
        capServiceClient = inCapServiceClient;
    }

    public CRMUserManagement getCrmUserClient()
    {
        return crmUserClient;
    }

    public void setCrmUserClient( CRMUserManagement inCrmUserClient )
    {
        crmUserClient = inCrmUserClient;
    }

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    public CRMLMSService getLmsServiceClient()
    {
        return lmsServiceClient;
    }

    public void setLmsServiceClient( CRMLMSService inLmsServiceClient )
    {
        lmsServiceClient = inLmsServiceClient;
    }

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    @Override
    public LmsDto leadOperations( LmsForm inLmsForm, String inParameter )
    {
        LmsDto lmsDto = new LmsDto();
        if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                 CRMActionConstants.MATURE_LEAD.getStoringValue() ) )
        {
            String[] crfIdsArray = StringUtils.split( inLmsForm.getCrfIds(), "," );
            LOGGER.info( "Retrieved Array List Size: " + crfIdsArray.length );
            LmsCrfMappingPojo lmsCRFMappingPojo = null;
            List<LmsCrfMappingPojo> lmsCRFMappingPojos = new ArrayList<LmsCrfMappingPojo>();
            if ( StringUtils.equals( inLmsForm.getInaModule(), IAppConstants.Y ) )
            {
                for ( String cfrId : crfIdsArray )
                {
                    lmsCRFMappingPojo = new LmsCrfMappingPojo();
                    lmsCRFMappingPojo.setLmsId( inLmsForm.getLmsPojo().getLmsId() );
                    lmsCRFMappingPojo.setCreatedBy( inLmsForm.getLmsPojo().getLastModifiedBy() );
                    lmsCRFMappingPojo.setCrfValue( cfrId );
                    lmsCRFMappingPojos.add( lmsCRFMappingPojo );
                }
            }
            lmsDto.getCrfMappingPojos().addAll( lmsCRFMappingPojos );
            LOGGER.info( "Form CAF Mapping list size: " + lmsDto.getCrfMappingPojos().size() );
        }
        if ( !StringUtils.equals( inParameter, ServiceParameter.SAVE.getParameter() ) )
        {
            lmsDto.setToStage( inLmsForm.getRemarksPojo().getActions() );
        }
        if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                 CRMActionConstants.FORWARD_AM.getStoringValue() ) )
        {
            lmsDto.setToUserId( inLmsForm.getToUser() );
            LOGGER.info( "BP user Id : " + lmsDto.getToUserId() );
        }
        if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                 CRMActionConstants.FORWARD_Sales.getStoringValue() ) )
        {
            lmsDto.setToUserId( "" );
            LOGGER.info( "Business PartnerId: " + inLmsForm.getLmsPojo().getPartnerId() );
        }
        if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                 CRMActionConstants.BACKWARD_SC.getStoringValue() ) )
        {
            lmsDto.setToUserId( "" );
            LOGGER.info( "Business PartnerId: " + inLmsForm.getLmsPojo().getPartnerId() );
        }
        List<LmsPojo> lmsPojos = new ArrayList<LmsPojo>();
        //inLmsForm.getRemarksPojo().setActions( CRMUtils.getValue( inLmsForm.getRemarksPojo().getActions() ) );
        lmsDto.setRemarksPojo( inLmsForm.getRemarksPojo() );
        lmsPojos.add( inLmsForm.getLmsPojo() );
        lmsDto.getLeadPojos().addAll( lmsPojos );
        lmsDto.setLeadPojo( inLmsForm.getLmsPojo() );
        if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() ) )
        {
            LOGGER.info( "Appointment Date : " + inLmsForm.getAppointmentPojo().getDisplayDate() );
            LOGGER.info( "Mode Of Appointment" + inLmsForm.getAppointmentPojo().getModeOfContact() );
            LOGGER.info( "Preferred Time of Appointment" + inLmsForm.getAppointmentPojo().getAppointmentTime() );
            lmsDto.setAppointmentPojo( inLmsForm.getAppointmentPojo() );
        }
        try
        {
            lmsDto = getLmsServiceClient().lmsOperation( inParameter, lmsDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for generating Lead", ex );
        }
        return lmsDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> processLMSExcel( LmsForm inLmsForm, String inUser, String inFilePath )
    {
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        ExcelWriteUtils excelWriteUtils = null;
        List<String> statusList = new ArrayList<String>();
        try
        {
            String inPath = ExcelWriteUtils.downloadUploadedFile( inFilePath, inUser, inLmsForm.getFormFile(),
                                                                  CRMParameter.LMS.getParameter() );
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
            excelWriteUtils = new ExcelWriteUtils( CRMParameter.LMS.getParameter() );
            List<LmsPojo> dataToProcess = excelReadUtils.readFileData( inUser, workbook, inFilePath, excelWriteUtils,
                                                                       null, CRMParameter.LMS.getParameter() );
            if ( !StringUtils.isValidObj( dataToProcess ) )
            {
                statusList.add( "Invalid File Header." );
            }
            else
            {
                String outPath = excelWriteUtils.createExcel( inUser, inFilePath, CRMParameter.LMS.getParameter() );
                String status = storeValidData( dataToProcess, inLmsForm );
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

    private String storeValidData( List<LmsPojo> inLmsPojos, LmsForm inLmsForm )
    {
        LOGGER.info( "Inside LmsManagerImpl:, -storeValidData" );
        String status = "failed";
        LmsDto lmsDto = new LmsDto();
        lmsDto.getLeadPojos().addAll( inLmsPojos );
        inLmsForm.getRemarksPojo().setActions( CRMActionConstants.CREATE_LEAD.getStoringValue() );
        lmsDto.setRemarksPojo( inLmsForm.getRemarksPojo() );
        try
        {
            if ( !inLmsPojos.isEmpty() )
            {
                lmsDto = getLmsServiceClient().lmsOperation( ServiceParameter.CREATE.getParameter(), lmsDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), lmsDto.getStatusCode() ) )
                    status = "success";
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
        return status;
    }

    @Override
    public ConfigDto getAppointments( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().getMappingIdtByAppointMents( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Appointments SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto getRemarks( ConfigDto inConfigDto )
    {
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
    public ConfigDto getBPs( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().getMappedUsersByUser( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get BPs against AM SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto getReferralSources()
    {
        ConfigDto configDto = new ConfigDto();
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.LMS_REFERRAL_SOURCE.getParameter(),
                                                               configDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Referral Sourcess SOAP Exception : ", ex );
        }
        return configDto;
    }

    /*@Override
    public int getAppointmentsCount( LmsForm inLmsForm )
    {
        int appointmentCount = 0;
        try
        {
            for ( AppointmentPojo appointmentPojo : inLmsForm.getAppointmentPojoList() )
            {
                if ( inLmsForm
                        .getLmsPojo()
                        .getLmsStage()
                        .matches( CRMOperationStages.AREA_MANAGER.getCode() + "|"
                                          + CRMOperationStages.BUSINESS_PARTNER.getCode() ) )
                {
                    if ( StringUtils.equals( appointmentPojo.getStage(), CRMOperationStages.AREA_MANAGER.getCode() )
                            || StringUtils.equals( appointmentPojo.getStage(),
                                                   CRMOperationStages.BUSINESS_PARTNER.getCode() ) )
                    {
                        appointmentCount++;
                    }
                }
                else if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                              CRMOperationStages.CLUSTER_HEAD.getCode() )
                        && StringUtils.equals( appointmentPojo.getStage(), CRMOperationStages.CLUSTER_HEAD.getCode() ) )
                {
                    appointmentCount++;
                }
            }
            LOGGER.info( "Total appointments of AM and BP : " + appointmentCount );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while calculating AM & BP appointments count : ", ex );
        }
        return appointmentCount;
    }*/
    @Override
    public List<ContentPojo> getActionsList( LmsForm inLmsForm )
    {
        List<ContentPojo> actionList = CRMUtils.getPerformingActions( inLmsForm.getStageIndex() );
        Iterator<ContentPojo> iterator = actionList.iterator();
        try
        {
            if ( inLmsForm.isShowAppointmentDiv()
                    && ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                             CRMOperationStages.AREA_MANAGER.getCode() ) || StringUtils
                            .equals( inLmsForm.getLmsPojo().getLmsStage(), CRMOperationStages.SALES.getCode() ) ) )
            {
                while ( iterator.hasNext() )
                {
                    ContentPojo contentPojo = iterator.next();
                    if ( StringUtils.equals( contentPojo.getContentValue(), CRMOperationStages.CLUSTER_HEAD.getCode() ) )
                    {
                        iterator.remove();
                    }
                }
            }
            /*else if ( !inLmsForm.isShowAppointmentDiv()
                    && StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                           CRMOperationStages.AREA_MANAGER.getCode() ) )
            {
                while ( iterator.hasNext() )
                {
                    ContentPojo contentPojo = iterator.next();
                    if ( StringUtils.equals( contentPojo.getContentValue(),
                                             CRMOperationStages.BUSINESS_PARTNER.getCode() ) )
                    {
                        iterator.remove();
                    }
                }
            }*/
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while perparing condition based action list : ", ex );
        }
        return actionList;
    }

    @Override
    public boolean getBackRouteSCValidation( List<RemarksPojo> inRemarksPojoList )
    {
        boolean isBackRouted = false;
        try
        {
            for ( RemarksPojo remarksPojo : inRemarksPojoList )
            {
                if ( StringUtils.equals( remarksPojo.getActions(), CRMActionConstants.BACKWARD_SC.getDisplayValue() ) )
                {
                    isBackRouted = true;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while checking Back Routed case for stage SC : ", ex );
        }
        return isBackRouted;
    }

    @Override
    public ConfigDto getLeadCloseReason()
    {
        ConfigDto configDto = new ConfigDto();
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.LMS_CLOSE_REASON.getParameter(), configDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Lead Close Reasons SOAP Exception : ", ex );
        }
        return configDto;
    }

    @Override
    public ConfigDto getActivityLogs( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Lead Close Reasons SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto getCustomerRefuselReason()
    {
        ConfigDto configDto = new ConfigDto();
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.INA_REFUSEL_REASON.getParameter(),
                                                               configDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get customer refusel Reasons SOAP Exception : ", ex );
        }
        return configDto;
    }

    public boolean checkCrfMapping( String inCrfId )
    {
        LOGGER.info( "inside lmsManagerImpl and method is checkDuplicateCrf" );
        LmsDto lmsDto = new LmsDto();
        boolean isDuplicate = false;
        LmsCrfMappingPojo lmsCrfMappingPojo = new LmsCrfMappingPojo();
        try
        {
            lmsCrfMappingPojo.setCrfValue( inCrfId );
            List<LmsCrfMappingPojo> lmsCrfMappingPojos = new ArrayList<LmsCrfMappingPojo>();
            lmsCrfMappingPojos.add( lmsCrfMappingPojo );
            lmsDto.getCrfMappingPojos().addAll( lmsCrfMappingPojos );
            lmsDto.setLmsCrfMappingPojo( lmsCrfMappingPojo );
            lmsDto = getLmsServiceClient().lmsOperation( ServiceParameter.CHECK_DUPLICATE.getParameter(), lmsDto );
            if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM307.getStatusCode() ) )
            {
                isDuplicate = true;
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "checking duplicate crf id SOAP Exception ", ex );
        }
        return isDuplicate;
    }

    @Override
    public List<String> getUsersByParameter( String inParam, String inValue, String inFunctionalBin )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        crmuserDetailsDto.setCrmUserDetailsPojo( new CrmUserPojo() );
        crmuserDetailsDto.setUserRolesPojo( new UserRolesPojo() );
        crmuserDetailsDto.getUserRolesPojo().setCrmParameter( new CrmParameterPojo() );
        Set<String> areaManagers = null;
        try
        {
            crmuserDetailsDto.getCrmUserDetailsPojo()
                    .setFunctionalBin( IAppConstants.COMMA
                                               + CRMCacheManager.getFunctionalBinsByAlias( inFunctionalBin )
                                                       .getCategoryId() + IAppConstants.COMMA );
            crmuserDetailsDto.getUserRolesPojo().setParameterValue( inValue );
            crmuserDetailsDto.getUserRolesPojo().getCrmParameter().setParameterName( inParam );
            crmuserDetailsDto = getCrmUserClient().getUsersByParameter( crmuserDetailsDto );
            if ( StringUtils.isValidObj( crmuserDetailsDto )
                    && CommonValidator.isValidCollection( crmuserDetailsDto.getUserRolesPojos() ) )
            {
                areaManagers = new HashSet<String>();
                for ( UserRolesPojo rolesPojo : crmuserDetailsDto.getUserRolesPojos() )
                {
                    areaManagers.add( rolesPojo.getUserId() );
                }
                LOGGER.info( "Area Managers List Size : " + areaManagers.size() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception while getting CRM User Client.", ex );
        }
        if ( CommonValidator.isValidCollection( areaManagers ) )
        {
            return new ArrayList<String>( areaManagers );
        }
        return null;
    }

    public boolean checkCrfMappingINA( String inCrfId )
    {
        LOGGER.info( "inside lmsManagerImpl and method is checkDuplicateCrf in INA" );
        CrmCapDto capDto = new CrmCapDto();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        boolean isDuplicate = false;
        try
        {
            crmCustomerDetailsPojo.setCrfId( inCrfId );
            capDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            capDto = getCapServiceClient().crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                            CRMRequestType.CAF.getRequestCode(), capDto );
            if ( StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( capDto.getCustomerDetailsPojosList().size() > 0 )
                {
                    isDuplicate = true;
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "checking duplicate CAF id SOAP Exception ", ex );
        }
        return isDuplicate;
    }

    @Override
    public LmsPojo getSocietyByPinCode( int inPinCode )
    {
        LmsPojo lmsPojo = new LmsPojo();
        LmsDto lmsDto = new LmsDto();
        try
        {
            lmsPojo.setPincode( inPinCode );
            lmsDto.setLeadPojo( lmsPojo );
            lmsDto = getLmsServiceClient().lmsOperation( ServiceParameter.SEARCH.getParameter(), lmsDto );
            if ( StringUtils.isValidObj( lmsDto ) )
            {
                return lmsDto.getLeadPojo();
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for searching society by pin code", ex );
        }
        return null;
    }

    @Override
    public CrmQrcDto logLMSTicket( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside LmsManagerImpl, logLMSTicket" );
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
    public CrmQrcDto getLMSTickets( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.VIEW.getParameter(), CRMParameter.SERVICE_REQUEST.getParameter(),
                                    inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        return inCrmQrcDto;
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
}
