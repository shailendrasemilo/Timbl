package com.np.tele.selfcare.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;

public class SelfcareMigrationForm
    extends ActionForm
{
    private CrmPlanDetailsPojo      crmPlanDetailsPojo;
    private List<CrmPlanMasterPojo> unlimitedBasePlanMasterPojos;
    private List<CrmPlanMasterPojo> limitedBasePlanMasterPojos;
    private List<CrmPlanMasterPojo> crmAddonPLMasterPojos;
    private List<CrmPlanMasterPojo> boosterUsageTopUps;
    private CrmPlanMasterPojo       crmPlanMasterPojo;
    private String                  selectedPlanCode;
    private String                  addonPlanCode;
    private String                  activationTime;
    private boolean                 tariffNoAddOn;
    private String                  topUpUsage;
    private String                  oldBasePlanCode;
    private String                  oldAddonPlanCode;
    private String                  activationType;
    private String                  serviceType;
    private List<CrmSrTicketsPojo>  srTicketsPojos;
    private String                  customerId;

    public String getTopUpUsage()
    {
        return topUpUsage;
    }

    public void setTopUpUsage( String inTopUpUsage )
    {
        topUpUsage = inTopUpUsage;
    }

    public List<CrmPlanMasterPojo> getBoosterUsageTopUps()
    {
        return boosterUsageTopUps;
    }

    public void setBoosterUsageTopUps( List<CrmPlanMasterPojo> inBoosterUsageTopUps )
    {
        boosterUsageTopUps = inBoosterUsageTopUps;
    }

    public CrmPlanDetailsPojo getCrmPlanDetailsPojo()
    {
        return crmPlanDetailsPojo;
    }

    public void setCrmPlanDetailsPojo( CrmPlanDetailsPojo crmPlanDetailsPojo )
    {
        this.crmPlanDetailsPojo = crmPlanDetailsPojo;
    }

    public List<CrmPlanMasterPojo> getUnlimitedBasePlanMasterPojos()
    {
        return unlimitedBasePlanMasterPojos;
    }

    public void setUnlimitedBasePlanMasterPojos( List<CrmPlanMasterPojo> unlimitedBasePlanMasterPojos )
    {
        this.unlimitedBasePlanMasterPojos = unlimitedBasePlanMasterPojos;
    }

    public List<CrmPlanMasterPojo> getLimitedBasePlanMasterPojos()
    {
        return limitedBasePlanMasterPojos;
    }

    public void setLimitedBasePlanMasterPojos( List<CrmPlanMasterPojo> limitedBasePlanMasterPojos )
    {
        this.limitedBasePlanMasterPojos = limitedBasePlanMasterPojos;
    }

    public List<CrmPlanMasterPojo> getCrmAddonPLMasterPojos()
    {
        return crmAddonPLMasterPojos;
    }

    public void setCrmAddonPLMasterPojos( List<CrmPlanMasterPojo> crmAddonPLMasterPojos )
    {
        this.crmAddonPLMasterPojos = crmAddonPLMasterPojos;
    }

    public CrmPlanMasterPojo getCrmPlanMasterPojo()
    {
        return crmPlanMasterPojo;
    }

    public void setCrmPlanMasterPojo( CrmPlanMasterPojo crmPlanMasterPojo )
    {
        this.crmPlanMasterPojo = crmPlanMasterPojo;
    }

    public String getSelectedPlanCode()
    {
        return selectedPlanCode;
    }

    public void setSelectedPlanCode( String selectedPlanCode )
    {
        this.selectedPlanCode = selectedPlanCode;
    }

    public String getAddonPlanCode()
    {
        return addonPlanCode;
    }

    public void setAddonPlanCode( String addonPlanCode )
    {
        this.addonPlanCode = addonPlanCode;
    }

    public String getActivationTime()
    {
        return activationTime;
    }

    public void setActivationTime( String activationTime )
    {
        this.activationTime = activationTime;
    }

    public boolean isTariffNoAddOn()
    {
        return tariffNoAddOn;
    }

    public void setTariffNoAddOn( boolean tariffNoAddOn )
    {
        this.tariffNoAddOn = tariffNoAddOn;
    }

    public String getOldBasePlanCode()
    {
        return oldBasePlanCode;
    }

    public void setOldBasePlanCode( String oldBasePlanCode )
    {
        this.oldBasePlanCode = oldBasePlanCode;
    }

    public String getOldAddonPlanCode()
    {
        return oldAddonPlanCode;
    }

    public void setOldAddonPlanCode( String oldAddonPlanCode )
    {
        this.oldAddonPlanCode = oldAddonPlanCode;
    }

    public String getActivationType()
    {
        return activationType;
    }

    public void setActivationType( String activationType )
    {
        this.activationType = activationType;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String serviceType )
    {
        this.serviceType = serviceType;
    }

    public List<CrmSrTicketsPojo> getSrTicketsPojos()
    {
        return srTicketsPojos;
    }

    public void setSrTicketsPojos( List<CrmSrTicketsPojo> srTicketsPojos )
    {
        this.srTicketsPojos = srTicketsPojos;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }
}
