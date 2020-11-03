package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class PlanMigrationPolicyPojo
    implements Serializable
{
    private String category;
    private String subCategory;
    private String decSpeedIMMAdvice;
    private String decSpeedNBCAdvice;
    private String decDULIMMAdvice;
    private String decDULNBCAdvice;
    private String incDULIMMAdvice;
    private String incDULNBCAdvice;
    private String incSpeedIMMAdvice;
    private String incSpeedNBCAdvice;

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory( String subCategory )
    {
        this.subCategory = subCategory;
    }

    public String getDecSpeedIMMAdvice()
    {
        return decSpeedIMMAdvice;
    }

    public void setDecSpeedIMMAdvice( String decSpeedIMMAdvice )
    {
        this.decSpeedIMMAdvice = decSpeedIMMAdvice;
    }

    public String getDecSpeedNBCAdvice()
    {
        return decSpeedNBCAdvice;
    }

    public void setDecSpeedNBCAdvice( String decSpeedNBCAdvice )
    {
        this.decSpeedNBCAdvice = decSpeedNBCAdvice;
    }

    public String getDecDULIMMAdvice()
    {
        return decDULIMMAdvice;
    }

    public void setDecDULIMMAdvice( String decDULIMMAdvice )
    {
        this.decDULIMMAdvice = decDULIMMAdvice;
    }

    public String getDecDULNBCAdvice()
    {
        return decDULNBCAdvice;
    }

    public void setDecDULNBCAdvice( String decDULNBCAdvice )
    {
        this.decDULNBCAdvice = decDULNBCAdvice;
    }

    public String getIncDULIMMAdvice()
    {
        return incDULIMMAdvice;
    }

    public void setIncDULIMMAdvice( String incDULIMMAdvice )
    {
        this.incDULIMMAdvice = incDULIMMAdvice;
    }

    public String getIncDULNBCAdvice()
    {
        return incDULNBCAdvice;
    }

    public void setIncDULNBCAdvice( String incDULNBCAdvice )
    {
        this.incDULNBCAdvice = incDULNBCAdvice;
    }

    public String getIncSpeedIMMAdvice()
    {
        return incSpeedIMMAdvice;
    }

    public void setIncSpeedIMMAdvice( String incSpeedIMMAdvice )
    {
        this.incSpeedIMMAdvice = incSpeedIMMAdvice;
    }

    public String getIncSpeedNBCAdvice()
    {
        return incSpeedNBCAdvice;
    }

    public void setIncSpeedNBCAdvice( String incSpeedNBCAdvice )
    {
        this.incSpeedNBCAdvice = incSpeedNBCAdvice;
    }
}
