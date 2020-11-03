package com.np.tele.crm.pojos;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class SmsTemplatePojo
    implements java.io.Serializable
{
    private long         smsTemplateId;
    private ProjectsPojo projects;
    private String       smsTemplateName;
    private byte[]       smsTemplate;
    private String       smsGateway;
    private String       smsType;
    private String       createdBy;
    private Date         createdTime;
    private String       lastModifiedBy;
    private Date         lastModifiedTime;
    private String       status;
    private String       displayCreatedTime;
    private String       displayLastModifiedTime;

    // private Set      eventTemplateses = new HashSet( 0 );
    public SmsTemplatePojo()
    {
    }

    public SmsTemplatePojo( Long smsTemplateId,
                            ProjectsPojo projects,
                            String smsTemplateName,
                            byte[] smsTemplate,
                            String smsGateway,
                            String createdBy,
                            String lastModifiedBy,
                            Date lastModifiedTime )
    {
        this.smsTemplateId = smsTemplateId;
        this.projects = projects;
        this.smsTemplateName = smsTemplateName;
        this.smsTemplate = smsTemplate;
        this.smsGateway = smsGateway;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public SmsTemplatePojo( Long smsTemplateId,
                            ProjectsPojo projects,
                            String smsTemplateName,
                            byte[] smsTemplate,
                            String smsGateway,
                            String smsType,
                            String createdBy,
                            Date createdTime,
                            String lastModifiedBy,
                            Date lastModifiedTime,
                            String status,
                            Set eventTemplateses )
    {
        this.smsTemplateId = smsTemplateId;
        this.projects = projects;
        this.smsTemplateName = smsTemplateName;
        this.smsTemplate = smsTemplate;
        this.smsGateway = smsGateway;
        this.smsType = smsType;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        // this.eventTemplateses = eventTemplateses;
    }

    public long getSmsTemplateId()
    {
        return smsTemplateId;
    }

    public void setSmsTemplateId( long smsTemplateId )
    {
        this.smsTemplateId = smsTemplateId;
    }

    public ProjectsPojo getProjects()
    {
        return this.projects;
    }

    public void setProjects( ProjectsPojo projects )
    {
        this.projects = projects;
    }

    public String getSmsTemplateName()
    {
        return this.smsTemplateName;
    }

    public void setSmsTemplateName( String smsTemplateName )
    {
        this.smsTemplateName = smsTemplateName;
    }

    public byte[] getSmsTemplate()
    {
        return smsTemplate;
    }

    public void setSmsTemplate( byte[] smsTemplate )
    {
        this.smsTemplate = smsTemplate;
    }

    public String getSmsGateway()
    {
        return this.smsGateway;
    }

    public void setSmsGateway( String smsGateway )
    {
        this.smsGateway = smsGateway;
    }

    public String getSmsType()
    {
        return this.smsType;
    }

    public void setSmsType( String smsType )
    {
        this.smsType = smsType;
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

    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
        /* if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
         {
             displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
         }*/
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
        /*if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
        {
            displayLastModifiedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( lastModifiedTime );
        }*/
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public String getDisplayLastModifiedTime()
    {
        return displayLastModifiedTime;
    }

    public void setDisplayLastModifiedTime( String inDisplayLastModifiedTime )
    {
        displayLastModifiedTime = inDisplayLastModifiedTime;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "SmsTemplatePojo [smsTemplateId=" ).append( smsTemplateId ).append( ", projects=" )
                .append( projects ).append( ", smsTemplateName=" ).append( smsTemplateName ).append( ", smsTemplate=" )
                .append( Arrays.toString( smsTemplate ) ).append( ", smsGateway=" ).append( smsGateway )
                .append( ", smsType=" ).append( smsType ).append( ", createdBy=" ).append( createdBy )
                .append( ", createdTime=" ).append( createdTime ).append( ", lastModifiedBy=" ).append( lastModifiedBy )
                .append( ", lastModifiedTime=" ).append( lastModifiedTime ).append( ", status=" ).append( status )
                .append( "]" );
        return builder.toString();
    }
}
