package com.np.tele.crm.pojos;

// default package
// Generated Jan 8, 2015 4:11:35 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmInvoiceDetailsPojo generated by hbm2java
 */
public class CrmInvoiceDetailsPojo
    implements java.io.Serializable
{
    private String billNumber;
    private Date   billDate;
    private double billAmount;
    private String paymentDueDate;
    private String billPeriod;
    private String customerId;
    private String billType;
    private Date   billGeneDate;
    private Date   dueDate;
    private String billGroupId;
    private String passwordPdfFile;
    private String nopasswordPdfFile;
    private String psFilePath;
    private String createdBy;
    private Date   createdTime;

    public CrmInvoiceDetailsPojo()
    {
    }

    public CrmInvoiceDetailsPojo( String billNumber, String customerId, String createdBy )
    {
        this.billNumber = billNumber;
        this.customerId = customerId;
        this.createdBy = createdBy;
    }

    public CrmInvoiceDetailsPojo( String billNumber,
                                  String customerId,
                                  String billType,
                                  Date billGeneDate,
                                  Date billDate,
                                  Date dueDate,
                                  String billPeriod,
                                  String billGroupId,
                                  String passwordPdfFile,
                                  String nopasswordPdfFile,
                                  String psFilePath,
                                  String createdBy,
                                  Date createdTime )
    {
        this.billNumber = billNumber;
        this.customerId = customerId;
        this.billType = billType;
        this.billGeneDate = billGeneDate;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.billPeriod = billPeriod;
        this.billGroupId = billGroupId;
        this.passwordPdfFile = passwordPdfFile;
        this.nopasswordPdfFile = nopasswordPdfFile;
        this.psFilePath = psFilePath;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public String getBillNumber()
    {
        return this.billNumber;
    }

    public void setBillNumber( String billNumber )
    {
        this.billNumber = billNumber;
    }

    public String getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getBillType()
    {
        return this.billType;
    }

    public void setBillType( String billType )
    {
        this.billType = billType;
    }

    public Date getBillGeneDate()
    {
        return this.billGeneDate;
    }

    public void setBillGeneDate( Date billGeneDate )
    {
        this.billGeneDate = billGeneDate;
    }

    public Date getBillDate()
    {
        return this.billDate;
    }

    public void setBillDate( Date billDate )
    {
        this.billDate = billDate;
    }

    public Date getDueDate()
    {
        return this.dueDate;
    }

    public void setDueDate( Date dueDate )
    {
        this.dueDate = dueDate;
    }

    public String getBillPeriod()
    {
        return this.billPeriod;
    }

    public void setBillPeriod( String billPeriod )
    {
        this.billPeriod = billPeriod;
    }

    public String getBillGroupId()
    {
        return this.billGroupId;
    }

    public void setBillGroupId( String billGroupId )
    {
        this.billGroupId = billGroupId;
    }

    public String getPasswordPdfFile()
    {
        return this.passwordPdfFile;
    }

    public void setPasswordPdfFile( String passwordPdfFile )
    {
        this.passwordPdfFile = passwordPdfFile;
    }

    public String getNopasswordPdfFile()
    {
        return this.nopasswordPdfFile;
    }

    public void setNopasswordPdfFile( String nopasswordPdfFile )
    {
        this.nopasswordPdfFile = nopasswordPdfFile;
    }

    public String getPsFilePath()
    {
        return this.psFilePath;
    }

    public void setPsFilePath( String psFilePath )
    {
        this.psFilePath = psFilePath;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public double getBillAmount()
    {
        return billAmount;
    }

    public void setBillAmount( double billAmount )
    {
        this.billAmount = billAmount;
    }

    public String getPaymentDueDate()
    {
        return paymentDueDate;
    }

    public void setPaymentDueDate( String inPaymentDueDate )
    {
        paymentDueDate = inPaymentDueDate;
    }
}