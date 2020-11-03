package com.np.tele.crm.ext.pojos;

public class CommonCustDetailsPojo
{
    private String crfId;
    private String customerId;
    private String status;
    private String custName;
    private String instAddress;
    private String firstCpeMacId;
    private String societyName;

    public String getCrfId()
    {
        return crfId;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public String getStatus()
    {
        return status;
    }

    public String getCustName()
    {
        return custName;
    }

    public String getInstAddress()
    {
        return instAddress;
    }

    public String getFirstCpeMacId()
    {
        return firstCpeMacId;
    }

    public void setCrfId( String inCrfId )
    {
        crfId = inCrfId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public void setCustName( String inCustName )
    {
        custName = inCustName;
    }

    public void setInstAddress( String inInstAddress )
    {
        instAddress = inInstAddress;
    }

    public void setFirstCpeMacId( String inFirstCpeMacId )
    {
        firstCpeMacId = inFirstCpeMacId;
    }

    public String getSocietyName()
    {
        return societyName;
    }

    public void setSocietyName( String inSocietyName )
    {
        societyName = inSocietyName;
    }
}
