package com.np.tele.crm.usrmngmnt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jboss.logging.MDC;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.usrmngmnt.bm.ICustomerProfileMgr;
import com.np.tele.crm.usrmngmnt.forms.CustomerProfileForm;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CustomerProfileSearchAction
    extends DispatchAction
{
    private static final Logger LOGGER                 = Logger.getLogger( CustomerProfileSearchAction.class );
    private ICustomerProfileMgr customerProfileMgrImpl = null;
    private IQrcManager         qrcManagerBm           = null;

    public ICustomerProfileMgr getCustomerProfileMgrImpl()
    {
        return customerProfileMgrImpl;
    }

    public void setCustomerProfileMgrImpl( ICustomerProfileMgr inCustomerProfileMgrImpl )
    {
        customerProfileMgrImpl = inCustomerProfileMgrImpl;
    }

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    /**
     * @param inMapping
     * @param inForm
     * @param inRequest
     * @param inResponse
     * @return
     */
    public ActionForward customerProfileSearch( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        ActionMessages errors = new ActionMessages();
        String target = IActionForwardConstant.CUSTOMER_PROFILE_SEARCH_PAGE;
        CustomerProfileForm customerProfileForm = (CustomerProfileForm) inForm;
        MDC.put( "KEY",
                 customerProfileForm.getProfileSearchName() + "-" + customerProfileForm.getSearchValue() );
        try
        {
            LOGGER.info( "searchKey: " + customerProfileForm.getProfileSearchName() );
            LOGGER.info( "Search Value:: " + customerProfileForm.getSearchValue() );
            String moduleName = CustomerProfile.getCodeByModule( customerProfileForm.getProfileSearchName() );
            LOGGER.info( "Profiler Module Name:: " + moduleName );
            customerProfileForm.setSearchValue( StringUtils.trim( customerProfileForm.getSearchValue() ) );
            if ( StringUtils.equals( moduleName, "INA" ) )
            {
                CrmCapDto crmCapDto = new CrmCapDto();
                crmCapDto = getCustomerProfileMgrImpl()
                        .CRFCustomerProfilesearch( customerProfileForm.getProfileSearchName(),
                                                   customerProfileForm.getSearchValue() );
                LOGGER.info( "Status Code::: " + crmCapDto.getStatusCode() );
                if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        || StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM042.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmCapDto.getCustomerDetailsPojosList() ) )
                    {
                        LOGGER.info( "Customer Profile Search List Size::: "
                                + crmCapDto.getCustomerDetailsPojosList().size() );
                        customerProfileForm.setCrmCustomerDetailsPojos( crmCapDto.getCustomerDetailsPojosList() );
                        inRequest.getSession( false ).setAttribute( IAppConstants.CUST_DETAILS_LIST,
                                                                    customerProfileForm.getCrmCustomerDetailsPojos() );
                        if ( crmCapDto.getCustomerDetailsPojosList().size() == 1 )
                        {
                            if ( StringUtils.isBlank( crmCapDto.getCustomerDetailsPojosList().get( 0 ).getCustomerId() ) )
                            {
                                inRequest.getSession( false ).setAttribute( "workingID",
                                                                            crmCapDto.getCustomerDetailsPojosList()
                                                                                    .get( 0 ).getCrfId() );
                            }
                            else
                            {
                                inRequest.getSession( false ).setAttribute( "workingID",
                                                                            crmCapDto.getCustomerDetailsPojosList()
                                                                                    .get( 0 ).getCustomerId() );
                            }
                            target = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
                        }
                        /*if ( crmCapDto.getCustomerDetailsPojosList().size() == 1 )
                         {
                             if ( !StringUtils.isEmpty( crmCapDto.getCustomerDetailsPojosList().get( 0 ).getCustomerId() ) )
                             {
                                 LOGGER.info( "Size of customer details pojo  with customer id is 1" );
                                 HttpSession session = inRequest.getSession( false );
                                 session.setAttribute( "workingId", crmCapDto.getCustomerDetailsPojosList().get( 0 )
                                         .getCustomerId() );
                                 target = IActionForwardConstant.QRC_VIEW_PAGE_FOR_SINGLE_CUSTOMER_ID;
                             }
                             else if ( !StringUtils.isEmpty( crmCapDto.getCustomerDetailsPojosList().get( 0 ).getCrfId() ) )
                             {
                                 LOGGER.info( "Size of customer details pojo  with customer id is 1 and crfId is ::" + crmCapDto.getCustomerDetailsPojosList().get( 0 ).getCrfId() + "and product is :::" + crmCapDto.getCustomerDetailsPojosList().get( 0 ).getProduct() );
                                 
                                 List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = new ArrayList<CrmCustomerDetailsPojo>();
                                 crmCustomerDetailsPojos.add( crmCapDto.getCustomerDetailsPojosList().get( 0 ) );
                                 customerProfileForm.setCrmCustomerDetailsPojos( crmCustomerDetailsPojos );
                                 target = IActionForwardConstant.QRC_VIEW_PAGE_FOR_SINGLE_CRF_ID;
                             }
                         }*/
                    }
                    else
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND_FOR_SEARCH,
                                                       CustomerProfile.getProfiler( customerProfileForm
                                                                                            .getProfileSearchName() )
                                                               .getValue(), customerProfileForm.getSearchValue() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                }
            }
            else if ( StringUtils.equals( moduleName, "LEAD" ) )
            {
                LmsDto lmsDto = new LmsDto();
                lmsDto = getCustomerProfileMgrImpl()
                        .leadCustomerProfileSearch( customerProfileForm.getProfileSearchName(),
                                                    customerProfileForm.getSearchValue() );
                LOGGER.info( "Status Code::: " + lmsDto.getStatusCode() );
                if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        || StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM042.getStatusCode() ) )
                {
                    if ( !lmsDto.getLeadPojos().isEmpty() && lmsDto.getLeadPojos().size() > 0 )
                    {
                        LOGGER.info( "Customer Profile Search List Size::: " + lmsDto.getLeadPojos().size() );
                        customerProfileForm.setLmsPojoList( lmsDto.getLeadPojos() );
                        /* if ( lmsDto.getLeadPojos().size() == 1 )
                         {
                             target = IActionForwardConstant.LMS_VIEW_PAGE_FOR_SINGLE;
                         }*/
                    }
                    else
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND_FOR_SEARCH,
                                                       CustomerProfile.getProfiler( customerProfileForm
                                                                                            .getProfileSearchName() )
                                                               .getValue(), customerProfileForm.getSearchValue() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( lmsDto.getStatusCode() ) );
                }
            }
            else if ( StringUtils.equals( moduleName, "QRC" ) )
            {
                CrmQrcDto crmQrcDto = new CrmQrcDto();
                crmQrcDto = getCustomerProfileMgrImpl()
                        .ticketIDprofileSearch( customerProfileForm.getProfileSearchName(),
                                                customerProfileForm.getSearchValue(), crmQrcDto );
                LOGGER.info( "Status Code::: " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM042.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                    {
                        LOGGER.info( "Customer Profile Search List Size::: " + crmQrcDto.getCrmSrTicketsPojos().size() );
                        getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                        customerProfileForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                    }
                    else
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND_FOR_SEARCH,
                                                       CustomerProfile.getProfiler( customerProfileForm
                                                                                            .getProfileSearchName() )
                                                               .getValue(), customerProfileForm.getSearchValue() ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }
}
