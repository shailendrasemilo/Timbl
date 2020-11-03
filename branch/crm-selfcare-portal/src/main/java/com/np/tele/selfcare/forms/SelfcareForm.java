package com.np.tele.selfcare.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCustAdditionalDetails;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.StatePojo;

public class SelfcareForm
    extends ActionForm
{
    private static final long               serialVersionUID = -6033253943946519472L;
    private final Logger                    LOGGER           = Logger.getLogger( SelfcareForm.class );
    private CrmCustMyAccountPojo            custMyAccountPojo;
    private CrmCustomerDetailsPojo          customerDetailsPojo;
    private CrmPlanDetailsPojo              custPlanDetailsPojo;
    private CrmPlanMasterPojo               basePlanMasterPojo;
    private CrmPlanMasterPojo               addonPlanMasterPojo;
    private CrmCustAdditionalDetails        custAdditionalDetails;
    private CrmInvoiceDetailsPojo           invoiceDetailsPojo;
    private List<CrmInvoiceDetailsPojo>     invoiceDetailsPojos;
    private List<CrmPaymentDetailsPojo>     paymentDetailsPojos;
    private Long                            currentUsage     = 0l;
    private Long                            totalQuota       = 0l;
    private double                          percentage       = 1;
    private double                          usedPercentage   = 0;
    private String                          retypePassword;
    private CrmAddressDetailsPojo           installationAddressPojo;
    private CrmAddressDetailsPojo           billingAddressPojo;
    private List<StatePojo>                 stateList;
    private List<CityPojo>                  cityList;
    private CrmSrTicketsPojo                openTicketsPojo;
    private List<CrmSrTicketsPojo>          openTicketsPojos;
    private CrmSrTicketsPojo                nonOpenTicketsPojo;
    private List<CrmSrTicketsPojo>          nonOpenTicketsPojos;
    private List<String>                    selfcareSubjects;
    private CrmSelfcareCategoriesPojo       crmSelfcareCategoriesPojo;
    private List<CrmSelfcareCategoriesPojo> crmSelfcareCategoriesPojos;
    private CrmTicketHistoryPojo            ticketHistoryPojo;

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

    public CrmPlanDetailsPojo getCustPlanDetailsPojo()
    {
        return custPlanDetailsPojo;
    }

    public void setCustPlanDetailsPojo( CrmPlanDetailsPojo inCustPlanDetailsPojo )
    {
        custPlanDetailsPojo = inCustPlanDetailsPojo;
    }

    public CrmPlanMasterPojo getBasePlanMasterPojo()
    {
        return basePlanMasterPojo;
    }

    public void setBasePlanMasterPojo( CrmPlanMasterPojo inBasePlanMasterPojo )
    {
        basePlanMasterPojo = inBasePlanMasterPojo;
    }

    public CrmPlanMasterPojo getAddonPlanMasterPojo()
    {
        return addonPlanMasterPojo;
    }

    public void setAddonPlanMasterPojo( CrmPlanMasterPojo inAddonPlanMasterPojo )
    {
        addonPlanMasterPojo = inAddonPlanMasterPojo;
    }

    public CrmCustAdditionalDetails getCustAdditionalDetails()
    {
        return custAdditionalDetails;
    }

    public void setCustAdditionalDetails( CrmCustAdditionalDetails inCustAdditionalDetail )
    {
        custAdditionalDetails = inCustAdditionalDetail;
    }

    public CrmInvoiceDetailsPojo getInvoiceDetailsPojo()
    {
        return invoiceDetailsPojo;
    }

    public void setInvoiceDetailsPojo( CrmInvoiceDetailsPojo inInvoiceDetailsPojo )
    {
        invoiceDetailsPojo = inInvoiceDetailsPojo;
    }

    public List<CrmInvoiceDetailsPojo> getInvoiceDetailsPojos()
    {
        return invoiceDetailsPojos;
    }

    public void setInvoiceDetailsPojos( List<CrmInvoiceDetailsPojo> inInvoiceDetailsPojos )
    {
        invoiceDetailsPojos = inInvoiceDetailsPojos;
    }

    public List<CrmPaymentDetailsPojo> getPaymentDetailsPojos()
    {
        return paymentDetailsPojos;
    }

    public void setPaymentDetailsPojos( List<CrmPaymentDetailsPojo> inPaymentDetailsPojos )
    {
        paymentDetailsPojos = inPaymentDetailsPojos;
    }

    public Long getCurrentUsage()
    {
        return currentUsage;
    }

    public void setCurrentUsage( Long inCurrentUsage )
    {
        currentUsage = inCurrentUsage;
    }

    public Long getTotalQuota()
    {
        return totalQuota;
    }

    public void setTotalQuota( Long inTotalQuota )
    {
        totalQuota = inTotalQuota;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public void setPercentage( double inPercentage )
    {
        percentage = inPercentage;
    }

    public double getUsedPercentage()
    {
        return usedPercentage;
    }

    public void setUsedPercentage( double inUsedPercentage )
    {
        usedPercentage = inUsedPercentage;
    }

    public String getRetypePassword()
    {
        return retypePassword;
    }

    public void setRetypePassword( String inRetypePassword )
    {
        retypePassword = inRetypePassword;
    }

    public CrmAddressDetailsPojo getInstallationAddressPojo()
    {
        return installationAddressPojo;
    }

    public void setInstallationAddressPojo( CrmAddressDetailsPojo inInstallationAddressPojo )
    {
        installationAddressPojo = inInstallationAddressPojo;
    }

    public CrmAddressDetailsPojo getBillingAddressPojo()
    {
        return billingAddressPojo;
    }

    public void setBillingAddressPojo( CrmAddressDetailsPojo inBillingAddressPojo )
    {
        billingAddressPojo = inBillingAddressPojo;
    }

    public List<StatePojo> getStateList()
    {
        return stateList;
    }

    public void setStateList( List<StatePojo> inStateList )
    {
        stateList = inStateList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> inCityList )
    {
        cityList = inCityList;
    }

    public CrmSrTicketsPojo getOpenTicketsPojo()
    {
        return openTicketsPojo;
    }

    public void setOpenTicketsPojo( CrmSrTicketsPojo inOpenTicketsPojo )
    {
        openTicketsPojo = inOpenTicketsPojo;
    }

    public List<CrmSrTicketsPojo> getOpenTicketsPojos()
    {
        return openTicketsPojos;
    }

    public void setOpenTicketsPojos( List<CrmSrTicketsPojo> inOpenTicketsPojos )
    {
        openTicketsPojos = inOpenTicketsPojos;
    }

    public CrmSrTicketsPojo getNonOpenTicketsPojo()
    {
        return nonOpenTicketsPojo;
    }

    public void setNonOpenTicketsPojo( CrmSrTicketsPojo inNonOpenTicketsPojo )
    {
        nonOpenTicketsPojo = inNonOpenTicketsPojo;
    }

    public List<CrmSrTicketsPojo> getNonOpenTicketsPojos()
    {
        return nonOpenTicketsPojos;
    }

    public void setNonOpenTicketsPojos( List<CrmSrTicketsPojo> inNonOpenTicketsPojos )
    {
        nonOpenTicketsPojos = inNonOpenTicketsPojos;
    }

    public List<String> getSelfcareSubjects()
    {
        return selfcareSubjects;
    }

    public void setSelfcareSubjects( List<String> inSelfcareSubjects )
    {
        selfcareSubjects = inSelfcareSubjects;
    }

    public CrmSelfcareCategoriesPojo getCrmSelfcareCategoriesPojo()
    {
        return crmSelfcareCategoriesPojo;
    }

    public void setCrmSelfcareCategoriesPojo( CrmSelfcareCategoriesPojo inCrmSelfcareCategoriesPojo )
    {
        crmSelfcareCategoriesPojo = inCrmSelfcareCategoriesPojo;
    }

    public List<CrmSelfcareCategoriesPojo> getCrmSelfcareCategoriesPojos()
    {
        return crmSelfcareCategoriesPojos;
    }

    public void setCrmSelfcareCategoriesPojos( List<CrmSelfcareCategoriesPojo> inCrmSelfcareCategoriesPojos )
    {
        crmSelfcareCategoriesPojos = inCrmSelfcareCategoriesPojos;
    }

    public CrmTicketHistoryPojo getTicketHistoryPojo()
    {
        return ticketHistoryPojo;
    }

    public void setTicketHistoryPojo( CrmTicketHistoryPojo inTicketHistoryPojo )
    {
        ticketHistoryPojo = inTicketHistoryPojo;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Inside reset of selfcare form" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "Method is :" + methodName );
        SelfcareFormHelper.resetSelfcareForm( this, methodName );
    }
}
