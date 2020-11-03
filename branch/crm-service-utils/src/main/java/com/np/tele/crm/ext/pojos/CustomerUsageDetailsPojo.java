package com.np.tele.crm.ext.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerUsageDetailsPojo
    implements Serializable
{
    private BigDecimal usageID;     //ID
    private Long       customerNo;  //CUSTOMER_NO
    private String     framedIP;    //FRAMED_IP_ADDRESS
    private Date       startTime;   //CALL_START
    private Date       endTime;     //CALL_END
    private long       uploadInKB;  //ACCT_INPUT_OCTETS
    private long       downloadInKB; //ACCT_OUTPUT_OCTETS
    private Date       createdate;
    private String     callEndDate;
    private BigDecimal uploadKB;
    private BigDecimal downloadKB;

    public BigDecimal getUsageID()
    {
        return usageID;
    }

    public void setUsageID( BigDecimal inUsageID )
    {
        usageID = inUsageID;
    }

    public Long getCustomerNo()
    {
        return customerNo;
    }

    public void setCustomerNo( Long inCustomerNo )
    {
        customerNo = inCustomerNo;
    }

    public String getFramedIP()
    {
        return framedIP;
    }

    public void setFramedIP( String inFramedIP )
    {
        framedIP = inFramedIP;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime( Date inStartTime )
    {
        startTime = inStartTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime( Date inEndTime )
    {
        endTime = inEndTime;
    }

    public long getUploadInKB()
    {
        return uploadInKB;
    }

    public void setUploadInKB( long inUploadInKB )
    {
        uploadInKB = inUploadInKB;
    }

    public long getDownloadInKB()
    {
        return downloadInKB;
    }

    public void setDownloadInKB( long inDownloadInKB )
    {
        downloadInKB = inDownloadInKB;
    }

    public Date getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate( Date inCreatedate )
    {
        createdate = inCreatedate;
    }

    public String getCallEndDate()
    {
        return callEndDate;
    }

    public void setCallEndDate( String inCallEndDate )
    {
        callEndDate = inCallEndDate;
    }

    public BigDecimal getUploadKB()
    {
        return uploadKB;
    }

    public void setUploadKB( BigDecimal inUploadKB )
    {
        uploadKB = inUploadKB;
    }

    public BigDecimal getDownloadKB()
    {
        return downloadKB;
    }

    public void setDownloadKB( BigDecimal inDownloadKB )
    {
        downloadKB = inDownloadKB;
    }
}
