package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.np.tele.crm.ext.pojos.FileUploader;
import com.np.tele.crm.pojos.CrmCustAadharNumberPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;

public class ECafDto
    implements Serializable
{
    private CrmCustomerDetailsPojo  customerDetailsPojo;
    private CrmPlanDetailsPojo      planDetailsPojo;
    private CrmDocumentDetailsPojo  documentDetailsPojo;
    private CrmPaymentDetailsPojo   paymentDetailsPojo;
    private CrmCustAadharNumberPojo aadharNumberPojo;
    private List<FileUploader>      fileUploaderList;
    private String                  clientIp;
    private String                  statusDesc;
    private String                  statusCode;
    private String                  userId;
    private String                  authUsername;
    private String                  authPassword;

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public CrmPlanDetailsPojo getPlanDetailsPojo()
    {
        return planDetailsPojo;
    }

    public void setPlanDetailsPojo( CrmPlanDetailsPojo inPlanDetailsPojo )
    {
        planDetailsPojo = inPlanDetailsPojo;
    }

    public CrmDocumentDetailsPojo getDocumentDetailsPojo()
    {
        return documentDetailsPojo;
    }

    public void setDocumentDetailsPojo( CrmDocumentDetailsPojo inDocumentDetailsPojo )
    {
        documentDetailsPojo = inDocumentDetailsPojo;
    }

    public CrmPaymentDetailsPojo getPaymentDetailsPojo()
    {
        return paymentDetailsPojo;
    }

    public void setPaymentDetailsPojo( CrmPaymentDetailsPojo inPaymentDetailsPojo )
    {
        paymentDetailsPojo = inPaymentDetailsPojo;
    }

    public CrmCustAadharNumberPojo getAadharNumberPojo()
    {
        return aadharNumberPojo;
    }

    public void setAadharNumberPojo( CrmCustAadharNumberPojo inAadharNumberPojo )
    {
        aadharNumberPojo = inAadharNumberPojo;
    }

    public List<FileUploader> getFileUploaderList()
    {
        return fileUploaderList;
    }

    public void setFileUploaderList( List<FileUploader> inFileUploaderList )
    {
        fileUploaderList = inFileUploaderList;
    }

    @XmlTransient
    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp( String inClientIp )
    {
        clientIp = inClientIp;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    @XmlTransient
    public String getAuthUsername()
    {
        return authUsername;
    }

    public void setAuthUsername( String inAuthUsername )
    {
        authUsername = inAuthUsername;
    }

    @XmlTransient
    public String getAuthPassword()
    {
        return authPassword;
    }

    public void setAuthPassword( String inAuthPassword )
    {
        authPassword = inAuthPassword;
    }
}
