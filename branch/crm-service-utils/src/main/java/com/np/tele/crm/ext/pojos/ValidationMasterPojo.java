package com.np.tele.crm.ext.pojos;

public class ValidationMasterPojo
{
    private String category;
    private String subCategory;
    private String alias;
    private String mobile_no_starts_with = "9,8,7,6";

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

    public String getMobile_no_starts_with()
    {
        return mobile_no_starts_with;
    }

    public void setMobile_no_starts_with( String inMobile_no_starts_with )
    {
        mobile_no_starts_with = inMobile_no_starts_with;
    }
}
