package com.np.tele.crm.pojos;

// default package
// Generated 5 Jul, 2013 11:15:37 AM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Projects generated by hbm2java
 */
public class ProjectsPojo
    implements java.io.Serializable
{
    private long                  projectId;
    private String                projectName;
    private String                projectType;
    private String                projectDescription;
    private String                createdBy;
    private Date                  createdTime;
    private String                lastModifiedBy;
    private Date                  lastModifiedTime;
    private String                status;
    private Set<CrmParameterPojo> crmParameterPojosSet = new HashSet<CrmParameterPojo>();
    private String                displayCreatedTime;
    private String                displayLastModifiedTime;

    //private Set<EventsPojo>        eventses       = new HashSet<EventsPojo>( 0 );
    //private Set<EmailTemplatePojo> emailTemplates = new HashSet<EmailTemplatePojo>( 0 );
    //private Set<SmsTemplatePojo>   smsTemplates   = new HashSet<SmsTemplatePojo>( 0 );
    public ProjectsPojo()
    {
    }

    public ProjectsPojo( Long projectId,
                         String projectName,
                         String projectType,
                         String createdBy,
                         String lastModifiedBy,
                         Date lastModifiedTime )
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectType = projectType;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public ProjectsPojo( Long projectId,
                         String projectName,
                         String projectType,
                         String projectDescription,
                         String createdBy,
                         Date createdTime,
                         String lastModifiedBy,
                         Date lastModifiedTime,
                         String status,
                         Set eventses,
                         Set emailTemplates,
                         Set smsTemplates )
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectDescription = projectDescription;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        /*this.eventses = eventses;
        this.emailTemplates = emailTemplates;
        this.smsTemplates = smsTemplates;*/
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId( long projectId )
    {
        this.projectId = projectId;
    }

    public String getProjectName()
    {
        return this.projectName;
    }

    public void setProjectName( String projectName )
    {
        this.projectName = projectName;
    }

    public String getProjectType()
    {
        return this.projectType;
    }

    public void setProjectType( String projectType )
    {
        this.projectType = projectType;
    }

    public String getProjectDescription()
    {
        return this.projectDescription;
    }

    public void setProjectDescription( String projectDescription )
    {
        this.projectDescription = projectDescription;
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

    public Set<CrmParameterPojo> getCrmParameterPojosSet()
    {
        return crmParameterPojosSet;
    }

    public void setCrmParameterPojosSet( Set<CrmParameterPojo> inCrmParameterPojosSet )
    {
        crmParameterPojosSet = inCrmParameterPojosSet;
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
        builder.append( "ProjectsPojo [projectId=" ).append( projectId ).append( ", projectName=" )
                .append( projectName ).append( ", projectType=" ).append( projectType )
                .append( ", projectDescription=" ).append( projectDescription ).append( ", createdBy=" )
                .append( createdBy ).append( ", createdTime=" ).append( createdTime ).append( ", lastModifiedBy=" )
                .append( lastModifiedBy ).append( ", lastModifiedTime=" ).append( lastModifiedTime )
                .append( ", status=" ).append( status ).append( "]" );
        return builder.toString();
    }
    /*  public Set<EventsPojo> getEventses()
      {
          return eventses;
      }

      public void setEventses( Set<EventsPojo> eventses )
      {
          this.eventses = eventses;
      }

      public Set<EmailTemplatePojo> getEmailTemplates()
      {
          return emailTemplates;
      }

      public void setEmailTemplates( Set<EmailTemplatePojo> emailTemplates )
      {
          this.emailTemplates = emailTemplates;
      }

      public Set<SmsTemplatePojo> getSmsTemplates()
      {
          return smsTemplates;
      }

      public void setSmsTemplates( Set<SmsTemplatePojo> smsTemplates )
      {
          this.smsTemplates = smsTemplates;
      }*/
}
