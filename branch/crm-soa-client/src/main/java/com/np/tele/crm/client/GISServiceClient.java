package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMGISService;
import com.np.tele.crm.service.client.GISServiceService;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;

@ClientOperations
public class GISServiceClient
    implements CRMGISService
{
    @Override
    public GisDto gisOperation( String inServiceParam, String inOperationParam, GisDto inGisDto )
        throws SOAPException_Exception
    {
        GISServiceService gisServiceService = new GISServiceService();
        CRMGISService gisService = gisServiceService.getGISServicePort();
        GisDto gisDto = gisService.gisOperation( inServiceParam, inOperationParam, inGisDto );
        if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                || StringUtils.equals( ServiceParameter.MODIFY_STATUS.getParameter(), inServiceParam ) )
        {
            synchronized ( IAppConstants.GIS_TIME_MAP )
            {
                IAppConstants.GIS_TIME_MAP.clear();
            }
        }
        return gisDto;
    }
}
