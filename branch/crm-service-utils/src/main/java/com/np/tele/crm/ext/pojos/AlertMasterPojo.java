package com.np.tele.crm.ext.pojos;

public class AlertMasterPojo
{
    private String category;
    private String subCategory;
    private String alias;
    private int    max_retry           = 5;
    private int    retry_time_interval = 300;
    private int    trai_start          = 8;
    private int    trai_end            = 20;
    private String inventory_email   ;
    private String cms_status_email    = "";

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String inCategory )
    {
        category = inCategory;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory( String inSubCategory )
    {
        subCategory = inSubCategory;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias( String inAlias )
    {
        alias = inAlias;
    }

    public int getMax_retry()
    {
        return max_retry;
    }

    public void setMax_retry( int inMax_retry )
    {
        max_retry = inMax_retry;
    }

    public int getRetry_time_interval()
    {
        return retry_time_interval;
    }

    public void setRetry_time_interval( int inRetry_time_interval )
    {
        retry_time_interval = inRetry_time_interval;
    }

    public int getTrai_start()
    {
        return trai_start;
    }

    public void setTrai_start( int inTrai_start )
    {
        trai_start = inTrai_start;
    }

    public int getTrai_end()
    {
        return trai_end;
    }

    public void setTrai_end( int inTrai_end )
    {
        trai_end = inTrai_end;
    }

    public String getInventory_email()
    {
        return inventory_email;
    }

    public void setInventory_email( String inInventory_email )
    {
        inventory_email = inInventory_email;
    }

    public String getCms_status_email()
    {
        return cms_status_email;
    }

    public void setCms_status_email( String inCms_status_email )
    {
        cms_status_email = inCms_status_email;
    }
    
}
