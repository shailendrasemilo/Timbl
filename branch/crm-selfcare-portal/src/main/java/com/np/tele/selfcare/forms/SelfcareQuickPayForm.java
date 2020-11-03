package com.np.tele.selfcare.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmPaymentCentresPojo;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.utils.StringUtils;

public class SelfcareQuickPayForm
    extends ActionForm
{
    private final Logger                 LOGGER = Logger.getLogger( SelfcareQuickPayForm.class );
    private CrmCustMyAccountPojo         custMyAccountPojo;
    private CrmCustomerDetailsPojo       customerDetailsPojo;
    private List<CrmCustomerDetailsPojo> customerDetailsPojos;
    private CrmPgwTransactionsPojo       paymentGatewayPojo;
    private List<CrmRcaReasonPojo>       paymentOptions;
    private String                       maskedRmn;
    private String                       maskedEmailId;
    private CrmPaymentCentresPojo        paymentCentresPojo;
    private List<CrmPaymentCentresPojo>  paymentCentresPojos;
    private List<String>                 listPincode;
    private CrmInvoiceDetailsPojo        invoicePojo;
    private List<CrmInvoiceDetailsPojo>  invoiceList;
    private double                       accountBalance;
    private double                       amountDue;
    private CrmPlanMasterPojo            crmPlanMasterPojo;
    private CrmPlanMasterPojo            crmAddonPlanMasterPojo;
    private double                       planBillAmount;
    private String                       appReturnUrl;

    public CrmCustMyAccountPojo getCustMyAccountPojo()
    {
        return custMyAccountPojo;
    }

    public void setCustMyAccountPojo( CrmCustMyAccountPojo inCustMyAccountPojo )
    {
        this.custMyAccountPojo = inCustMyAccountPojo;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        this.customerDetailsPojo = inCustomerDetailsPojo;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos()
    {
        return customerDetailsPojos;
    }

    public void setCustomerDetailsPojos( List<CrmCustomerDetailsPojo> inCustomerDetailsPojos )
    {
        this.customerDetailsPojos = inCustomerDetailsPojos;
    }

    public CrmPgwTransactionsPojo getPaymentGatewayPojo()
    {
        return paymentGatewayPojo;
    }

    public void setPaymentGatewayPojo( CrmPgwTransactionsPojo inPaymentGatewayPojo )
    {
        this.paymentGatewayPojo = inPaymentGatewayPojo;
    }

    public List<CrmRcaReasonPojo> getPaymentOptions()
    {
        return paymentOptions;
    }

    public void setPaymentOptions( List<CrmRcaReasonPojo> inPaymentOptions )
    {
        paymentOptions = inPaymentOptions;
    }

    public void setMaskedRmn( String inMaskedRmn )
    {
        this.maskedRmn = inMaskedRmn;
    }

    public String getMaskedRmn()
    {
        return maskedRmn;
    }

    public void setMaskedEmailId( String inMaskedEmailId )
    {
        this.maskedEmailId = inMaskedEmailId;
    }

    public String getMaskedEmailId()
    {
        return maskedEmailId;
    }

    public CrmPaymentCentresPojo getPaymentCentresPojo()
    {
        return paymentCentresPojo;
    }

    public void setPaymentCentresPojo( CrmPaymentCentresPojo inPaymentCentresPojo )
    {
        paymentCentresPojo = inPaymentCentresPojo;
    }

    public List<CrmPaymentCentresPojo> getPaymentCentresPojos()
    {
        return paymentCentresPojos;
    }

    public void setPaymentCentresPojos( List<CrmPaymentCentresPojo> inPaymentCentresPojos )
    {
        paymentCentresPojos = inPaymentCentresPojos;
    }

    public List<String> getListPincode()
    {
        return listPincode;
    }

    public CrmInvoiceDetailsPojo getInvoicePojo()
    {
        return invoicePojo;
    }

    public void setInvoicePojo( CrmInvoiceDetailsPojo inInvoicePojo )
    {
        invoicePojo = inInvoicePojo;
    }

    public List<CrmInvoiceDetailsPojo> getInvoiceList()
    {
        return invoiceList;
    }

    public void setInvoiceList( List<CrmInvoiceDetailsPojo> inInvoiceList )
    {
        invoiceList = inInvoiceList;
    }

    public void setListPincode( List<String> inListPincode )
    {
        listPincode = inListPincode;
    }

    public double getAccountBalance()
    {
        return accountBalance;
    }

    public void setAccountBalance( double inAccountBalance )
    {
        accountBalance = inAccountBalance;
    }

    public double getAmountDue()
    {
        return amountDue;
    }

    public void setAmountDue( double inAmountDue )
    {
        amountDue = inAmountDue;
    }

    public CrmPlanMasterPojo getCrmPlanMasterPojo()
    {
        return crmPlanMasterPojo;
    }

    public void setCrmPlanMasterPojo( CrmPlanMasterPojo inCrmPlanMasterPojo )
    {
        crmPlanMasterPojo = inCrmPlanMasterPojo;
    }

    public CrmPlanMasterPojo getCrmAddonPlanMasterPojo()
    {
        return crmAddonPlanMasterPojo;
    }

    public void setCrmAddonPlanMasterPojo( CrmPlanMasterPojo inCrmAddonPlanMasterPojo )
    {
        crmAddonPlanMasterPojo = inCrmAddonPlanMasterPojo;
    }

    public double getPlanBillAmount()
    {
        return planBillAmount;
    }

    public void setPlanBillAmount( double inPlanBillAmount )
    {
        planBillAmount = inPlanBillAmount;
    }

    public String getAppReturnUrl()
    {
        return appReturnUrl;
    }

    public void setAppReturnUrl( String inAppReturnUrl )
    {
        appReturnUrl = inAppReturnUrl;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( method, IAppConstants.METHOD_QUICKPAY_PAGE ) )
        {
            this.setPlanBillAmount( 0 );
            this.setAmountDue( 0 );
            this.setCrmAddonPlanMasterPojo( null );
            this.setCrmPlanMasterPojo( null );
        }
        else if ( StringUtils.equals( method, "quickPayAction" ) )
        {
            this.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        }
        else if ( StringUtils.equals( method, "quickPayTransaction" ) )
        {
            this.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setInvoicePojo( new CrmInvoiceDetailsPojo() );
            this.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
            this.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
        }
        else if ( StringUtils.equals( method, "quickPayRedirect" ) )
        {
            this.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
        }
        super.reset( inMapping, inRequest );
    }
}
