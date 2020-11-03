package com.np.tele.crm.ext.pojos;

import java.math.BigInteger;
import java.util.Date;

public class SlaReportPojo
{
    private String     qrcType;
    private String     category;
    private String     subCategory;
    private String     subSubCategory;
    private String     userName;
    private BigInteger hr0_4Count;
    private BigInteger hr4_24Count;
    private BigInteger day1_2Count;
    private BigInteger day2_3Count;
    private BigInteger day3_4Count;
    private BigInteger day4_5Count;
    private BigInteger day5_6Count;
    private BigInteger day6_7Count;
    private BigInteger day7_10Count;
    private BigInteger day10_15Count;
    private BigInteger dayAbove15Count;
    private BigInteger totalCount;
    private String     slaTime;
    private Date       expectedSlaTime;

    public String getQrcType()
    {
        return qrcType;
    }

    public void setQrcType( String inQrcType )
    {
        qrcType = inQrcType;
    }

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

    public String getSubSubCategory()
    {
        return subSubCategory;
    }

    public void setSubSubCategory( String inSubSubCategory )
    {
        subSubCategory = inSubSubCategory;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String inUserName )
    {
        userName = inUserName;
    }

    public Date getExpectedSlaTime()
    {
        return expectedSlaTime;
    }

    public void setExpectedSlaTime( Date inExpectedSlaTime )
    {
        expectedSlaTime = inExpectedSlaTime;
    }

    public String getSlaTime()
    {
        return slaTime;
    }

    public void setSlaTime( String inSlaTime )
    {
        slaTime = inSlaTime;
    }

    public BigInteger getHr0_4Count()
    {
        return hr0_4Count;
    }

    public void setHr0_4Count( BigInteger inHr0_4Count )
    {
        hr0_4Count = inHr0_4Count;
    }

    public BigInteger getHr4_24Count()
    {
        return hr4_24Count;
    }

    public void setHr4_24Count( BigInteger inHr4_24Count )
    {
        hr4_24Count = inHr4_24Count;
    }

    public BigInteger getDay1_2Count()
    {
        return day1_2Count;
    }

    public void setDay1_2Count( BigInteger inDay1_2Count )
    {
        day1_2Count = inDay1_2Count;
    }

    public BigInteger getDay2_3Count()
    {
        return day2_3Count;
    }

    public void setDay2_3Count( BigInteger inDay2_3Count )
    {
        day2_3Count = inDay2_3Count;
    }

    public BigInteger getDay3_4Count()
    {
        return day3_4Count;
    }

    public void setDay3_4Count( BigInteger inDay3_4Count )
    {
        day3_4Count = inDay3_4Count;
    }

    public BigInteger getDay4_5Count()
    {
        return day4_5Count;
    }

    public void setDay4_5Count( BigInteger inDay4_5Count )
    {
        day4_5Count = inDay4_5Count;
    }

    public BigInteger getDay5_6Count()
    {
        return day5_6Count;
    }

    public void setDay5_6Count( BigInteger inDay5_6Count )
    {
        day5_6Count = inDay5_6Count;
    }

    public BigInteger getDay6_7Count()
    {
        return day6_7Count;
    }

    public void setDay6_7Count( BigInteger inDay6_7Count )
    {
        day6_7Count = inDay6_7Count;
    }

    public BigInteger getDay7_10Count()
    {
        return day7_10Count;
    }

    public void setDay7_10Count( BigInteger inDay7_10Count )
    {
        day7_10Count = inDay7_10Count;
    }

    public BigInteger getDay10_15Count()
    {
        return day10_15Count;
    }

    public void setDay10_15Count( BigInteger inDay10_15Count )
    {
        day10_15Count = inDay10_15Count;
    }

    public BigInteger getDayAbove15Count()
    {
        return dayAbove15Count;
    }

    public void setDayAbove15Count( BigInteger inDayAbove15Count )
    {
        dayAbove15Count = inDayAbove15Count;
    }

    public BigInteger getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount( BigInteger inTotalCount )
    {
        totalCount = inTotalCount;
    }
}
