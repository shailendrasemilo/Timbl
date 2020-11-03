package com.np.tele.selfcare.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.utils.StringUtils;

public class SelfcareLoginForm
    extends ActionForm
{
    private final Logger                 LOGGER = Logger.getLogger( SelfcareLoginForm.class );
    private CrmCustMyAccountPojo         custMyAccountPojo;
    private CrmCustomerDetailsPojo       customerDetailsPojo;
    private List<CrmCustomerDetailsPojo> customerDetailsPojos;

    public CrmCustMyAccountPojo getCustMyAccountPojo()
    {
        return custMyAccountPojo;
    }

    public void setCustMyAccountPojo( CrmCustMyAccountPojo inCustMyAccountPojo )
    {
        custMyAccountPojo = inCustMyAccountPojo;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos()
    {
        return customerDetailsPojos;
    }

    public void setCustomerDetailsPojos( List<CrmCustomerDetailsPojo> inCustomerDetailsPojos )
    {
        customerDetailsPojos = inCustomerDetailsPojos;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( this.getClass().getSimpleName() + ":Reset Start" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( method, "loginPage" ) )
        {
            if ( StringUtils.isValidObj( getCustMyAccountPojo() ) )
            {
                getCustMyAccountPojo().setPassword( null );
                getCustMyAccountPojo().setCustomerId( "" );
                getCustMyAccountPojo().setCustEmailId( "" );
                getCustMyAccountPojo().setRmn( 0 );
            }
        }
        super.reset( inMapping, inRequest );
        LOGGER.info( this.getClass().getSimpleName() + ":Reset End" );
    }
}
