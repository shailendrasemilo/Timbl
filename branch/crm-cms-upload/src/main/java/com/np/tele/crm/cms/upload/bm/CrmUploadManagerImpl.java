package com.np.tele.crm.cms.upload.bm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMAlertsClient;
import com.np.tele.crm.client.CRMFinanceClient;
import com.np.tele.crm.cms.constants.IAppCons;
import com.np.tele.crm.cms.utils.PropertyUtils;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.read.engine.UploadEngine;
import com.np.tele.crm.service.client.AlertStatusPojo;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class CrmUploadManagerImpl
    implements ICrmUploadManager
{
    private static final Logger LOGGER = Logger.getLogger( CrmUploadManagerImpl.class );

    @Override
    public CrmCmsFilePojo cmsFileOperation( CrmCmsFilePojo inCrmCmsFilePojo )
    {
        LOGGER.info( "Inside CMS File Operation .... " );
        try
        {
            CrmFinanceService crmFinanceService = new CRMFinanceClient();
            CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setCmsFile( inCrmCmsFilePojo );
            crmFinanceDto = crmFinanceService.postPayment( ServiceParameter.POST.getParameter(),
                                                           CRMParameter.CMSFILE.getParameter(), crmFinanceDto );
            if ( StringUtils.isValidObj( crmFinanceDto ) )
            {
                inCrmCmsFilePojo = crmFinanceDto.getCmsFile();
                LOGGER.info( "File Pojo saved to DB successfully, Generated File Id : "
                        + inCrmCmsFilePojo.getCmsFileId() );
            }
            else
            {
                inCrmCmsFilePojo = null;
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "CRM Finance Service SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "CRM Finance Service Exception", ex );
        }
        return inCrmCmsFilePojo;
    }

    @Override
    public boolean cmsPaymentOperation( List<CrmCmsPaymentPojo> inCmsPaymentPojos, CrmCmsFilePojo inCrmCmsFilePojo )
    {
        LOGGER.info( "Inside CMS Payment Operation .... " );
        boolean isSuccess = true;
        try
        {
            CrmFinanceService crmFinanceService = new CRMFinanceClient();
            CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
            int chunkSize = PropertyUtils.getInt( UploadEngine.appConfigProps, IAppCons.CMS_RECORDS_CHUNKS_SIZE, 20 );
            List<CrmCmsPaymentPojo> subList = null;
            while ( !inCmsPaymentPojos.isEmpty() && isSuccess )
            {
                if ( inCmsPaymentPojos.size() > chunkSize )
                {
                    subList = inCmsPaymentPojos.subList( 0, chunkSize );
                }
                else
                {
                    subList = inCmsPaymentPojos;
                }
                crmFinanceDto.getCmsPayments().addAll( subList );
                crmFinanceDto.setCmsFile( inCrmCmsFilePojo );
                LOGGER.info( "CMS Payments Size : " + crmFinanceDto.getCmsPayments().size() );
                crmFinanceDto = crmFinanceService.postPayment( ServiceParameter.POST.getParameter(),
                                                               CRMParameter.CMSRECORDS.getParameter(), crmFinanceDto );
                if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "File data saved to database successfully" );
                    inCmsPaymentPojos.removeAll( subList );
                }
                else
                {
                    isSuccess = false;
                    LOGGER.info( "OOPs faced some problem...." + crmFinanceDto.getStatusCode() );
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "CRM Finance Service Exception", ex );
        }
        return isSuccess;
    }

    @Override
    public boolean cmsProcessOperation( CrmCmsFilePojo inCrmCmsFilePojo )
    {
        boolean processed = false;
        LOGGER.info( "Inside CMS Process Operation .... " );
        try
        {
            CrmFinanceService crmFinanceService = new CRMFinanceClient();
            CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setCmsFile( inCrmCmsFilePojo );
            crmFinanceDto = crmFinanceService.postPayment( ServiceParameter.PROCESS.getParameter(),
                                                           CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
            if ( StringUtils.isValidObj( crmFinanceDto )
                    && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
            {
                processed = true;
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "CRM Finance Service SOAP Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "CRM Finance Service Exception", ex );
        }
        return processed;
    }

    @Override
    public List<CrmCmsFilePojo> getFilesToProcess( String inStatus )
    {
        List<CrmCmsFilePojo> fileList = new ArrayList<CrmCmsFilePojo>();
        CrmFinanceDto crmFinanceDto = null;
        try
        {
            CrmCmsFilePojo cmsFilePojo = new CrmCmsFilePojo();
            cmsFilePojo.setStatus( inStatus );
            crmFinanceDto = new CrmFinanceDto();
            crmFinanceDto.setCmsFile( cmsFilePojo );
            crmFinanceDto.setToDate( DateUtils.getCurrentDateStr() );
            crmFinanceDto.setFromDate( IDateConstants.SDF_DD_MMM_YYYY.format( DateUtils
                    .getFutureEndDate( -7, Calendar.DAY_OF_YEAR ).getTime() ) );
            CrmFinanceService crmFinanceService = new CRMFinanceClient();
            crmFinanceDto = crmFinanceService.trackPayment( ServiceParameter.TRACK.getParameter(),
                                                            CRMParameter.CMSFILE.getParameter(), crmFinanceDto );
            if ( StringUtils.isValidObj( crmFinanceDto ) )
            {
                fileList = crmFinanceDto.getCmsFiles();
                LOGGER.info( "Result size " + crmFinanceDto.getCmsFiles().size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return fileList;
    }

    @Override
    public void sendFileStatus( Long inFileID )
    {
        LOGGER.info( "Inside CrmUploadManagerImpl, sendFileStatus" );
        AlertsDto alertsDto = new AlertsDto();
        EventsPojo eventsPojo = new EventsPojo();
        eventsPojo.setEventName( CRMEvents.CMS_UPLOAD_STATUS.getEventName() );
        alertsDto.setEventsPojo( eventsPojo );
        //alertsDto.setEmailParameter( "neeraj@netprophetsglobal.com" );
        alertsDto.setParamName( CRMRequestType.CMS_FILE.getRequestCode() );
        alertsDto.setParamValue( inFileID + "" );
        CRMAlertsService crmAlertsClient = new CRMAlertsClient();
        try
        {
            AlertStatusPojo alertStatusPojo = crmAlertsClient.sendAlerts( alertsDto );
            if ( StringUtils.isValidObj( alertStatusPojo ) && StringUtils.equals( alertStatusPojo.getEmailSent(), "Y" ) )
            {
                LOGGER.info( "Email sent for file ID : " + inFileID );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting Error While Calling sendAlerts Service:: ", ex );
        }
    }
}
