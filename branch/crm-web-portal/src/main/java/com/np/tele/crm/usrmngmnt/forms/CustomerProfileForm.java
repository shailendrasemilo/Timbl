package com.np.tele.crm.usrmngmnt.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CustomerProfileForm
    extends ActionForm
{
    private static final Logger          LOGGER = Logger.getLogger( CustomerProfileForm.class );
    private String                       profileSearchName;
    private String                       searchValue;
    private List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos;
    private List<LmsPojo>                lmsPojoList;
    private List<CrmSrTicketsPojo>       srTicketsPojos;

    public String getProfileSearchName()
    {
        return profileSearchName;
    }

    public void setProfileSearchName( String inProfileSearchName )
    {
        profileSearchName = inProfileSearchName;
    }

    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue( String inSearchValue )
    {
        searchValue = inSearchValue;
    }

    public List<CrmCustomerDetailsPojo> getCrmCustomerDetailsPojos()
    {
        return crmCustomerDetailsPojos;
    }

    public void setCrmCustomerDetailsPojos( List<CrmCustomerDetailsPojo> inCrmCustomerDetailsPojos )
    {
        crmCustomerDetailsPojos = inCrmCustomerDetailsPojos;
    }

    public List<LmsPojo> getLmsPojoList()
    {
        return lmsPojoList;
    }

    public void setLmsPojoList( List<LmsPojo> inLmsPojoList )
    {
        lmsPojoList = inLmsPojoList;
    }

    public List<CrmSrTicketsPojo> getSrTicketsPojos()
    {
        return srTicketsPojos;
    }

    public void setSrTicketsPojos( List<CrmSrTicketsPojo> inSrTicketsPojos )
    {
        srTicketsPojos = inSrTicketsPojos;
    }

    public ActionErrors validate( ActionMapping mapping, HttpServletRequest request )
    {
        String methodName = request.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "Inside Customer Profile Form: Validating " + methodName );
        ActionErrors actionError = new ActionErrors();
        if ( StringUtils.equals( methodName, IAppConstants.CUSTOMER_PROFILE_SEARCH ) )
        {
            this.setSearchValue( StringUtils.trim( this.getSearchValue() ) );
            if ( StringUtils.isEmpty( this.getProfileSearchName() ) || StringUtils.isEmpty( this.getSearchValue() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.EMPTY_CUSTOMER_SEARCH_CRITERIA ) );
            }
            else if ( !StringUtils.isNumeric( this.getSearchValue() )
                    && ( StringUtils.equals( this.getProfileSearchName(), IAppConstants.REGISTERED_MOBILE_NUMBER )
                             || StringUtils.equals( this.getProfileSearchName(), IAppConstants.CONTACT_NUMBER ) ) )
            {
                actionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.NUMERIC_DATA_REQUIRED ) );
            }
            else if ( StringUtils.equals( this.getProfileSearchName(), CustomerProfile.TICKET_ID.getCode() )
                    && this.getSearchValue().length() < 7 )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.TICKET_ID_MINIMUM_LENGTH ) );
            }
        }
        return actionError;
    }

    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Customer Profile Search Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( methodName, IAppConstants.CUSTOMER_PROFILE_SEARCH ) )
        {
            this.setSearchValue( "" );
            this.setProfileSearchName( "" );
            if ( CommonValidator.isValidCollection( crmCustomerDetailsPojos ) )
            {
                this.crmCustomerDetailsPojos.clear();
            }
            else if ( CommonValidator.isValidCollection( lmsPojoList ) )
            {
                this.lmsPojoList.clear();
            }
            else if ( CommonValidator.isValidCollection( srTicketsPojos ) )
            {
                this.srTicketsPojos.clear();
            }
        }
    }
}
