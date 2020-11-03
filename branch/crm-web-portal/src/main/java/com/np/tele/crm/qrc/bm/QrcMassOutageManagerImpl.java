package com.np.tele.crm.qrc.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.qrc.forms.QrcMassOutageForm;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.SOAPException_Exception;

public class QrcMassOutageManagerImpl
    implements IQrcMassOutageManager
{
    private static final Logger LOGGER           = Logger.getLogger( QrcMassOutageManagerImpl.class );
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
    public CrmMassOutageDto searchMassOutage( QrcMassOutageForm inQrcMassOutageForm )
    {
        LOGGER.info( "searchMassOutage Method called" );
        CrmMassOutageDto massOutageDto = new CrmMassOutageDto();
        try
        {
            LOGGER.info( "Outage Type::" + inQrcMassOutageForm.getMassOutagePojo().getOutageType() );
            LOGGER.info( "Service Name::" + inQrcMassOutageForm.getMassOutagePojo().getServiceName() );
            massOutageDto.setCrmMassOutagePojo( inQrcMassOutageForm.getMassOutagePojo() );
            massOutageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.LIST.getParameter(),
                                                                        massOutageDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception Occured", ex );
            massOutageDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            massOutageDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return massOutageDto;
    }

    @Override
    public CrmMassOutageDto addMassOutage( QrcMassOutageForm massOutageForm )
    {
        CrmMassOutageDto massOutageDto = new CrmMassOutageDto();
        massOutageDto.setCrmMassOutagePojo( massOutageForm.getMassOutagePojo() );
        try
        {
            massOutageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.SAVE.getParameter(),
                                                                        massOutageDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to add mass outage", ex );
            massOutageDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            massOutageDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return massOutageDto;
    }

    @Override
    public CrmMassOutageDto viewMassOutage( CrmMassOutagePojo inCrmMassOutagePojo )
    {
        CrmMassOutageDto massOutageDto = new CrmMassOutageDto();
        massOutageDto.setCrmMassOutagePojo( inCrmMassOutagePojo );
        try
        {
            massOutageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.VIEW.getParameter(),
                                                                        massOutageDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to view mass outage", ex );
            massOutageDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            massOutageDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return massOutageDto;
    }

    @Override
    public CrmMassOutageDto updateOutage( CrmMassOutagePojo inMassOutagePojo, String inUserId )
    {
        CrmMassOutageDto massOutageDto = new CrmMassOutageDto();
        massOutageDto.setUserId( inUserId );
        massOutageDto.setCrmMassOutagePojo( inMassOutagePojo );
        try
        {
            massOutageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.UPDATE.getParameter(),
                                                                        massOutageDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to update mass outage", ex );
            massOutageDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            massOutageDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return massOutageDto;
    }

    @Override
    public CrmMassOutageDto resolveMassOutage( CrmMassOutagePojo inCrmMassOutagePojo, String inUserId )
    {
        CrmMassOutageDto massOutageDto = new CrmMassOutageDto();
        massOutageDto.setUserId( inUserId );
        massOutageDto.setCrmMassOutagePojo( inCrmMassOutagePojo );
        try
        {
            massOutageDto = getQrcServiceClient().massOutageOperations( ServiceParameter.CLOSE.getParameter(),
                                                                        massOutageDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "unable to resolve mass outage", ex );
            massOutageDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            massOutageDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return massOutageDto;
    }
}
