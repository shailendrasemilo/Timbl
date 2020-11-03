package com.np.tele.selfcare.bm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.CRMGISService;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CountryPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmPaymentCentresPojo;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SelfcareDto;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.forms.SelfcareForm;
import com.np.tele.selfcare.forms.SelfcareLoginForm;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;
import com.np.validator.util.CommonValidator;

public class SelfcareManagerImpl
    implements ISelfcareManager
{
    private final Logger      LOGGER           = Logger.getLogger( SelfcareManagerImpl.class );
    private CrmCapService     capServiceClient = null;
    private CRMConfigService  crmConfigClient  = null;
    private CrmFinanceService crmFinanceClient = null;
    private MasterData        masterDataClient = null;
    private CrmQrcService     crmQrcClient     = null;
    private CRMGISService     gisServiceClient = null;

    public CrmCapService getCapServiceClient()
    {
        return capServiceClient;
    }

    public void setCapServiceClient( CrmCapService inCapServiceClient )
    {
        capServiceClient = inCapServiceClient;
    }

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    public CrmFinanceService getCrmFinanceClient()
    {
        return crmFinanceClient;
    }

    public void setCrmFinanceClient( CrmFinanceService inCrmFinanceClient )
    {
        crmFinanceClient = inCrmFinanceClient;
    }

    public MasterData getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterData inMasterDataClient )
    {
        masterDataClient = inMasterDataClient;
    }

    public CrmQrcService getCrmQrcClient()
    {
        return crmQrcClient;
    }

    public void setCrmQrcClient( CrmQrcService inCrmQrcClient )
    {
        crmQrcClient = inCrmQrcClient;
    }

    public CRMGISService getGisServiceClient()
    {
        return gisServiceClient;
    }

    public void setGisServiceClient( CRMGISService inGisServiceClient )
    {
        gisServiceClient = inGisServiceClient;
    }

    @Override
    public SelfcareDto authenticate( SelfcareLoginForm inLoginForm )
    {
        SelfcareDto selfcareDto = null;
        if ( StringUtils.isValidObj( inLoginForm.getCustMyAccountPojo() ) )
        {
            selfcareDto = new SelfcareDto();
            selfcareDto.setCustMyAccountPojo( inLoginForm.getCustMyAccountPojo() );
            try
            {
                selfcareDto = getCapServiceClient().selfcareOperation( ServiceParameter.VALIDATE.getParameter(),
                                                                       CrmCustMyAccountPojo.class.getSimpleName(),
                                                                       selfcareDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( ex );
            }
        }
        return selfcareDto;
    }

    @Override
    public SelfcareDto forgotPassword( SelfcareLoginForm inLoginForm )
    {
        LOGGER.info( "In selfcaremanagerImpl : forgotPassword " );
        SelfcareDto selfcareDto = null;
        if ( StringUtils.isValidObj( inLoginForm.getCustomerDetailsPojo() ) )
        {
            selfcareDto = new SelfcareDto();
            selfcareDto.setCustomerDetailsPojo( inLoginForm.getCustomerDetailsPojo() );
            try
            {
                selfcareDto = getCapServiceClient().selfcareOperation( ServiceParameter.RESET.getParameter(),
                                                                       CrmCustMyAccountPojo.class.getSimpleName(),
                                                                       selfcareDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "error forgotPassword:: " + ex );
            }
        }
        return selfcareDto;
    }

    @Override
    public SelfcareDto changePassword( SelfcareForm inSelfcareForm )
    {
        SelfcareDto selfcareDto = null;
        if ( StringUtils.isValidObj( inSelfcareForm.getCustMyAccountPojo() ) )
        {
            LOGGER.info( "In SelfcareManagerImpl : changePassword " );
            selfcareDto = new SelfcareDto();
            selfcareDto.setCustMyAccountPojo( inSelfcareForm.getCustMyAccountPojo() );
            try
            {
                selfcareDto = getCapServiceClient().selfcareOperation( ServiceParameter.UPDATE.getParameter(),
                                                                       CrmCustMyAccountPojo.class.getSimpleName(),
                                                                       selfcareDto );
            }
            catch ( Exception ex )
            {
                LOGGER.info( "error in change Password" );
            }
        }
        return selfcareDto;
    }

    @Override
    public CrmFinanceDto trackPaymentHistory( SelfcareForm inSelfcareForm )
    {
        CrmFinanceDto crmFinanceDto = null;
        if ( StringUtils.isValidObj( inSelfcareForm.getCustomerDetailsPojo() ) )
        {
            try
            {
                LOGGER.info( inSelfcareForm.getCustomerDetailsPojo().getRecordId() + " "
                        + inSelfcareForm.getCustomerDetailsPojo().getCustomerId() );
                crmFinanceDto = new CrmFinanceDto();
                crmFinanceDto.setFromDate( IDateConstants.SDF_DD_MMM_YYYY.format( DateUtils
                        .getFutureEndDate( -366, Calendar.DAY_OF_YEAR ).getTime() ) );
                crmFinanceDto.setToDate( DateUtils.getCurrentDateStr() );
                crmFinanceDto.setCustomerDetailsPojo( inSelfcareForm.getCustomerDetailsPojo() );
                crmFinanceDto = getCrmFinanceClient().trackPayment( ServiceParameter.TRACK.getParameter(),
                                                                    CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "error trackPaymentHistory" );
                LOGGER.error( ex );
            }
        }
        return crmFinanceDto;
    }

    @Override
    public MasterDataDto getPlanInfo( SelfcareForm inSelfcareForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanCode( inSelfcareForm.getCustPlanDetailsPojo().getBasePlanCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        //        crmPlanMasterPojo.setPlanCode( "DSTD2" );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getPlanInfo" );
            LOGGER.error( ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto getAddonPlanInfo( SelfcareForm inSelfcareForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.ADDON_PLAN.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmPlanMasterPojo.setPlanCode( inSelfcareForm.getCustPlanDetailsPojo().getAddOnPlanCode() );
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
            LOGGER.info( "error getPlanInfo" );
            LOGGER.error( ex );
        }
        return masterDataDto;
    }

    @Override
    public CrmQrcDto getBillDetails( SelfcareForm inSelfcareForm )
    {
        LOGGER.info( "in SelfcareManagerImpl:getBillDetails" );
        CrmQrcDto qrcDto = null;
        if ( StringUtils.isValidObj( inSelfcareForm.getCustomerDetailsPojo() ) )
        {
            qrcDto = new CrmQrcDto();
            qrcDto.setCustomerDetailsPojo( inSelfcareForm.getCustomerDetailsPojo() );
            qrcDto.setCustomerId( inSelfcareForm.getCustomerDetailsPojo().getCustomerId() );
            try
            {
                LOGGER.info( "calling service for bill details" );
                qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                          CrmInvoiceDetailsPojo.class.getSimpleName(), qrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "error getBillDetails" );
                LOGGER.error( ex );
            }
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getBillOutstanding( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "in SelfcareManagerImpl:getBillOutstanding" );
        CrmQrcDto qrcDto = null;
        if ( StringUtils.isValidObj( inCustomerDetailsPojo ) )
        {
            qrcDto = new CrmQrcDto();
            qrcDto.setCustomerDetailsPojo( inCustomerDetailsPojo );
            try
            {
                LOGGER.info( "calling service for bill details" );
                qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                          CRMParameter.CUST_ADDITIONAL_DETAILS.getParameter(), qrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "error getBillDetails", ex );
            }
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getCustomerPriodicUsageDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside getCustomerPriodicUsageDetails" );
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            try
            {
                inCrmQrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.APICALL.getParameter(),
                                                               CRMParameter.USAGE_DETAILS.getParameter(), inCrmQrcDto );
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
        LOGGER.info( "Inside prepareDownloadResponse" );
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
    public CrmCapDto customerDetails( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        CrmCapDto capDto = null;
        if ( StringUtils.isValidObj( inCustomerDetailsPojo ) )
        {
            LOGGER.info( "In SelfcaretManagerImpl : customerDetails " );
            capDto = new CrmCapDto();
            capDto.setCustomerDetailsPojo( inCustomerDetailsPojo );
            try
            {
                capDto = getCapServiceClient().crmCapOperation( ServiceParameter.VIEW.getParameter(),
                                                                CrmCustomerDetailsPojo.class.getSimpleName(), capDto );
            }
            catch ( Exception ex )
            {
                LOGGER.info( "error in extraction customer details" );
            }
        }
        return capDto;
    }

    @Override
    public CrmQrcDto getCustomerTickets( SelfcareForm inSelfcareForm )
    {
        LOGGER.info( "in SelfcareManagerImpl:getCustomerTickets" );
        CrmQrcDto qrcDto = new CrmQrcDto();
        if ( StringUtils.isValidObj( inSelfcareForm.getCustomerDetailsPojo() ) )
        {
            Calendar cal = Calendar.getInstance();
            cal.add( Calendar.DATE, -365 );
            Date today = cal.getTime();
            CrmSrTicketsPojo ticketsPojo = new CrmSrTicketsPojo();
            ticketsPojo.setMappingId( inSelfcareForm.getCustomerDetailsPojo().getCustomerId() );
            String currDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( today );
            ticketsPojo.setCreatedTime( DateUtils.changeDateFormatWithTime( currDate ) );
            qrcDto.setCrmSrTicketsPojo( ticketsPojo );
            try
            {
                qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                          CRMParameter.SERVICE_REQUEST.getParameter(), qrcDto );
            }
            catch ( Exception ex )
            {
                LOGGER.info( "Error while retreiving ticket details" );
            }
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getSelfcareSubjects( SelfcareForm inSelfcareForm )
    {
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                      CrmSelfcareCategoriesPojo.class.getSimpleName(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getting selfcare subjects" );
            LOGGER.error( ex );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getSelfcareCategories( SelfcareForm inSelfcareForm )
    {
        CrmQrcDto qrcDto = null;
        if ( StringUtils.isValidObj( inSelfcareForm.getCrmSelfcareCategoriesPojo() )
                && StringUtils.isNotBlank( inSelfcareForm.getCrmSelfcareCategoriesPojo().getSubject() ) )
        {
            try
            {
                qrcDto = new CrmQrcDto();
                qrcDto.setCrmSelfcareCategoriesPojo( inSelfcareForm.getCrmSelfcareCategoriesPojo() );
                qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                          CrmSelfcareCategoriesPojo.class.getSimpleName(), qrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "error getting selfcare subjects" );
                LOGGER.error( ex );
            }
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto logTicket( SelfcareForm inSelfcareForm )
    {
        CrmQrcDto qrcDto = null;
        if ( StringUtils.isValidObj( inSelfcareForm.getCrmSelfcareCategoriesPojo() )
                && StringUtils.isValidObj( inSelfcareForm.getTicketHistoryPojo() ) )
        {
            try
            {
                qrcDto = new CrmQrcDto();
                qrcDto.setCrmSelfcareCategoriesPojo( inSelfcareForm.getCrmSelfcareCategoriesPojo() );
                qrcDto.setTicketHistory( inSelfcareForm.getTicketHistoryPojo() );
                qrcDto.setUserId( inSelfcareForm.getCustomerDetailsPojo().getCustomerId() );
                qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.POST.getParameter(),
                                                          CRMParameter.SELFCARE_SERVICE_REQUEST.getParameter(), qrcDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "error logging ticket" );
                LOGGER.error( ex );
            }
        }
        return qrcDto;
    }

    @Override
    public SelfcareDto customerAccounts( SelfcareQuickPayForm inQuickPayForm )
    {
        LOGGER.info( "in SelfcareManagerImpl:getCustomerAccounts" );
        SelfcareDto selfcareDto = null;
        try
        {
            selfcareDto = new SelfcareDto();
            selfcareDto.setCustomerDetailsPojo( inQuickPayForm.getCustomerDetailsPojo() );
            selfcareDto = getCapServiceClient().selfcareOperation( ServiceParameter.VALIDATE.getParameter(),
                                                                   CrmCustomerDetailsPojo.class.getSimpleName(),
                                                                   selfcareDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting error while calling selfcareOperation in customerAccounts:: " + ex );
        }
        return selfcareDto;
    }

    @Override
    public CrmCapDto saveCustomerProfileDetails( CrmCapDto inCrmCapDto )
    {
        try
        {
            inCrmCapDto = getCapServiceClient()
                    .crmCapOperation( ServiceParameter.SAVE_CUSTOMER_PROFILE_DETAILS.getParameter(),
                                      CRMRequestType.CAF.getRequestCode(), inCrmCapDto );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "error saving customer details" );
            LOGGER.error( e );
        }
        return inCrmCapDto;
    }

    @Override
    public List<StatePojo> getStates( String inStatus )
    {
        GisDto gisDto = new GisDto();
        CountryPojo countryPojo = new CountryPojo();
        countryPojo.setCountryName( IAppConstants.COUNTRY_INDIA );
        countryPojo.setStatus( inStatus );
        gisDto.setCountryPojo( countryPojo );
        try
        {
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                         CRMParameter.COUNTRY.getParameter(), gisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving ActiveGisData", ex );
        }
        if ( CommonValidator.isValidCollection( gisDto.getCountryPojosList() ) )
        {
            return gisDto.getCountryPojosList().get( 0 ).getStates();
        }
        return null;
    }

    @Override
    public List<CityPojo> getCities( CityPojo inCityPojo )
    {
        GisDto gisDto = new GisDto();
        gisDto.setCityPojo( inCityPojo );
        try
        {
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                         CRMParameter.CITY.getParameter(), gisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving ActiveGisData", ex );
        }
        if ( CommonValidator.isValidCollection( gisDto.getCityPojosList() ) )
        {
            return gisDto.getCityPojosList();
        }
        return null;
    }

    @Override
    public List<CrmQrcCategoriesPojo> getQrcCategories()
    {
        List<CrmQrcCategoriesPojo> qrcCategoriesPojos = null;
        CrmQrcDto qrcDto = null;
        try
        {
            qrcDto = new CrmQrcDto();
            CrmQrcCategoriesPojo qrcCategoriesPojo = new CrmQrcCategoriesPojo();
            qrcDto.setCrmQrcCategoriesPojo( qrcCategoriesPojo );
            qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.TRACK.getParameter(),
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
    public CrmFinanceDto processPayment( SelfcareQuickPayForm inQuickPayForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setCrmPgwTransactionsPojo( inQuickPayForm.getPaymentGatewayPojo() );
        try
        {
            crmFinanceDto = getCrmFinanceClient().pgwOperations( ServiceParameter.PROCESS.getParameter(),
                                                                 CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to initiate process payment transaction.", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto postPayment( SelfcareQuickPayForm inQuickPayForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setCrmPgwTransactionsPojo( inQuickPayForm.getPaymentGatewayPojo() );
        try
        {
            crmFinanceDto = getCrmFinanceClient().pgwOperations( ServiceParameter.POST.getParameter(),
                                                                 CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to process payment transaction.", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto validateResponse( SelfcareQuickPayForm inResponseForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setCrmPgwTransactionsPojo( inResponseForm.getPaymentGatewayPojo() );
        try
        {
            crmFinanceDto = getCrmFinanceClient().pgwOperations( ServiceParameter.VALIDATE.getParameter(),
                                                                 CrmPgwTransactionsPojo.class.getSimpleName(),
                                                                 crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting error while calling pgwOperations in validateResponse:: " + ex );
        }
        return crmFinanceDto;
    }

    @Override
    public List<CrmRcaReasonPojo> getPaymentOptions()
    {
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        crmRcaReason.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
        crmRcaReason.setSubCategory( CRMParameter.PAYMENT_GATEWAY.getParameter() );
        List<CrmRcaReasonPojo> masterDataList = null;
        MasterDataDto masterDataDto = new MasterDataDto();
        crmRcaReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setCrmRcaReason( crmRcaReason );
        try
        {
            if ( StringUtils.isValidObj( masterDataDto ) )
            {
                try
                {
                    masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                            CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                            masterDataDto );
                    masterDataList = masterDataDto.getCrmRcaReasonsList();
                }
                catch ( SOAPException_Exception ex )
                {
                    LOGGER.error( "Error in get receipt no master operation", ex );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return masterDataList;
    }

    @Override
    public CrmFinanceDto savePayment( SelfcareQuickPayForm inQuickPayForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setCrmPgwTransactionsPojo( inQuickPayForm.getPaymentGatewayPojo() );
        try
        {
            crmFinanceDto = getCrmFinanceClient().pgwOperations( ServiceParameter.SAVE.getParameter(),
                                                                 CrmPgwTransactionsPojo.class.getSimpleName(),
                                                                 crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error save payment", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto getPaymentDetails( CrmPgwTransactionsPojo inPgwTransactionsPojo )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setCrmPgwTransactionsPojo( inPgwTransactionsPojo );
        try
        {
            crmFinanceDto = getCrmFinanceClient().pgwOperations( ServiceParameter.VIEW.getParameter(),
                                                                 CrmPgwTransactionsPojo.class.getSimpleName(),
                                                                 crmFinanceDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error getPaymentDetails::::", ex );
        }
        return crmFinanceDto;
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
    public CrmCapDto validateEmailToken( CrmCapDto inCrmCapDto )
    {
        if ( StringUtils.isValidObj( inCrmCapDto ) && StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() )
                && StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustToken() ) )
        {
            try
            {
                inCrmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.VALIDATE.getParameter(),
                                                                     CRMParameter.CUSTOMER_EMAIL.getParameter(),
                                                                     inCrmCapDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.error( "unable to validate email", ex );
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
    public List<String> getPincodeList( SelfcareQuickPayForm inQuickPayForm )
    {
        LOGGER.info( "In SAelfcareManagerImpl :: getPincodeList " );
        List<String> pincodeList = null;
        ConfigDto configDto = null;
        try
        {
            configDto = new ConfigDto();
            if ( StringUtils.isValidObj( inQuickPayForm.getPaymentCentresPojo() ) )
            {
                configDto.setPaymentCentre( inQuickPayForm.getPaymentCentresPojo() );
            }
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CrmPaymentCentresPojo.class.getSimpleName(), configDto );
            pincodeList = configDto.getStages();
            LOGGER.info( "Payment crnter's List is : " + pincodeList.size() );
            for ( String pincode : pincodeList )
            {
                LOGGER.info( "pincode in pojo is :: " + pincode );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in retriving payment center list : ", ex );
        }
        return pincodeList;
    }

    @Override
    public ConfigDto getPaymentCenterDetails( SelfcareQuickPayForm inQuickPayForm )
    {
        LOGGER.info( "In SAelfcareManagerImpl :: getPaymentCenterDetails " );
        List<CrmPaymentCentresPojo> paymentCentresPojos = null;
        ConfigDto configDto = null;
        try
        {
            configDto = new ConfigDto();
            if ( StringUtils.isValidObj( inQuickPayForm.getPaymentCentresPojo().getPincode() ) )
            {
                LOGGER.info( "Pincode :: " + inQuickPayForm.getPaymentCentresPojo().getPincode() );
                configDto.setPaymentCentre( inQuickPayForm.getPaymentCentresPojo() );
            }
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CrmPaymentCentresPojo.class.getSimpleName(), configDto );
            paymentCentresPojos = configDto.getPaymentCentres();
            LOGGER.info( "Payment crnter's List Size is : " + paymentCentresPojos.size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in retriving payment center list : ", ex );
        }
        return configDto;
    }

    @Override
    public CrmQrcDto getBillDetails( String inCustomerId )
    {
        LOGGER.info( "in SelfcareManagerImpl:getBillDetails:::" + inCustomerId );
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCustomerId( inCustomerId );
        try
        {
            LOGGER.info( "calling service for bill details" );
            qrcDto = getCrmQrcClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                      CrmInvoiceDetailsPojo.class.getSimpleName(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error getBillDetails", ex );
        }
        return qrcDto;
    }

    @Override
    public MasterDataDto getBasePlanInfo( CrmPlanDetailsPojo inCrmPlanDetailsPojo )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCode( inCrmPlanDetailsPojo.getBasePlanCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getBasePlanInfo", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto getAddonPlanInfo( CrmPlanDetailsPojo inCrmPlanDetailsPojo )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.ADDON_PLAN.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmPlanMasterPojo.setPlanCode( inCrmPlanDetailsPojo.getAddOnPlanCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getPlanInfo",ex );
        }
        return masterDataDto;
    }
}
