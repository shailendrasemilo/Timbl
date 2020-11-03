package com.np.tele.crm.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.faultRepair.IQRCFaultRepairService;
import com.np.tele.crm.locator.CRMServicesProxy;

@Controller
public class RIRestService
{
    private static final Logger LOGGER   = Logger.getLogger( RIRestService.class );
    private static final String URI_PATH = "/riRestService/{to}/{message}/{oprator}/{sender}/{date}";
    IQRCFaultRepairService      service  = null;

    public IQRCFaultRepairService getService()
    {
        if ( null == service )
        {
            synchronized ( this )
            {
                if ( null == service )
                {
                    service = CRMServicesProxy.getInstance().getRIRestService( IGlobalConstants.REMOTE,
                                                                               IGlobalConstants.APP );
                }
            }
        }
        return service;
    }

    @ResponseBody
    @RequestMapping(value = URI_PATH, method = RequestMethod.GET)
    public boolean riRestService( @PathVariable(value = "to") String inTo,
                                  @PathVariable(value = "message") String inMessage,
                                  @PathVariable(value = "oprator") String inOprator,
                                  @PathVariable(value = "sender") String inSender,
                                  @PathVariable(value = "date") String inDate )
    {
        LOGGER.info( "Rest Service Call...." );
        try
        {
            getService().qrcFaultRepair( inTo, inMessage, inOprator, inSender, inDate );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
        }
        return false;
    }
}
