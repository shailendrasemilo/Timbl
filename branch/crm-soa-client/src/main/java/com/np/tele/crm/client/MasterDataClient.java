package com.np.tele.crm.client;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.MasterDataServiceService;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class MasterDataClient
    implements MasterData
{
    private static final Logger LOGGER = Logger.getLogger( MasterDataClient.class );

    @Override
    public MasterDataDto getMasterData( MasterDataDto inMasterDto, String inPojoName )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.getMasterData( inMasterDto, inPojoName );
    }

    @Override
    public MasterDataDto parameterGroupOperations( MasterDataDto masterDto, String operation )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.parameterGroupOperations( masterDto, operation );
    }

    @Override
    public MasterDataDto rolesGroupOperations( MasterDataDto masterDto, String operation )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.rolesGroupOperations( masterDto, operation );
    }

    @Override
    public MasterDataDto externalApplication( String serviceParam, MasterDataDto masterDto )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.externalApplication( serviceParam, masterDto );
    }

    @Override
    public MasterDataDto partnerOperations( String inServiceParam, MasterDataDto inMasterDto )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.partnerOperations( inServiceParam, inMasterDto );
    }

    /*public static void main( String[] args ) 
    {
        MasterDataClient client = new MasterDataClient();
        try
        {
            System.out.println(client.getMasterData("crmroles" ).getCrmRolesPojoList());
        }
        catch ( SOAPException_Exception ex )
        {
            ex.printStackTrace();
        }
    }*/
    @Override
    public MasterDataDto masterOperations( String inServiceParam, String inMasterType, MasterDataDto inMasterDto )
        throws SOAPException_Exception
    {
        MasterDataServiceService service = new MasterDataServiceService();
        MasterData masterData = service.getMasterDataServicePort();
        return masterData.masterOperations( inServiceParam, inMasterType, inMasterDto );
    }
}
