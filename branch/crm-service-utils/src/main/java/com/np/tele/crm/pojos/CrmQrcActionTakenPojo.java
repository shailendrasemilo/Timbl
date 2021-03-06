package com.np.tele.crm.pojos;

// default package
// Generated Sep 9, 2014 6:05:03 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmQrcRcaPojo generated by hbm2java
 */
public class CrmQrcActionTakenPojo
    implements java.io.Serializable
{
    private long    actionId;
    //    private CrmQrcCategoriesPojo          crmQrcCategoriesPojo;
    private long    qrcCategoryId;
    private String  serviceName;
    private String  actionTaken;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  status;
    private boolean editable;

    //private Set<CrmQrcResolutionCodePojo> crmQrcResolutionCodes = new HashSet<CrmQrcResolutionCodePojo>( 0 );
    public CrmQrcActionTakenPojo()
    {
    }

    //    public CrmQrcRcaPojo( long actionId,
    //                          CrmQrcCategoriesPojo crmQrcCategoriesPojo,
    //                          String serviceName,
    //                          String rca,
    //                          String createdBy )
    //    {
    //        this.actionId = actionId;
    //        this.crmQrcCategoriesPojo = crmQrcCategoriesPojo;
    //        this.serviceName = serviceName;
    //        this.rca = rca;
    //        this.createdBy = createdBy;
    //    }
    public CrmQrcActionTakenPojo( Long actionId,
                                  long qrcCategoryId,
                                  String serviceName,
                                  String actionTaken,
                                  String createdBy,
                                  Date createdTime,
                                  String lastModifiedBy,
                                  Date lastModifiedTime,
                                  String status )
    {
        this.actionId = actionId;
        this.qrcCategoryId = qrcCategoryId;
        this.serviceName = serviceName;
        this.actionTaken = actionTaken;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    //    public CrmQrcRcaPojo( long actionId,
    //                          CrmQrcCategoriesPojo crmQrcCategoriesPojo,
    //                          String serviceName,
    //                          String actionTaken,
    //                          String createdBy,
    //                          Date createdTime,
    //                          String lastModifiedBy,
    //                          Date lastModifiedTime,
    //                          String status,
    //                          Set crmQrcResolutionCodes )
    //    {
    //        this.actionId = actionId;
    //        this.crmQrcCategoriesPojo = crmQrcCategoriesPojo;
    //        this.serviceName = serviceName;
    //        this.rca = rca;
    //        this.createdBy = createdBy;
    //        this.createdTime = createdTime;
    //        this.lastModifiedBy = lastModifiedBy;
    //        this.lastModifiedTime = lastModifiedTime;
    //        this.status = status;
    //        this.crmQrcResolutionCodes = crmQrcResolutionCodes;
    //    }
    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean inEditable )
    {
        editable = inEditable;
    }

    public long getActionId()
    {
        return actionId;
    }

    public void setActionId( long actionId )
    {
        this.actionId = actionId;
    }

    public long getQrcCategoryId()
    {
        return qrcCategoryId;
    }

    public void setQrcCategoryId( long qrcCategoryId )
    {
        this.qrcCategoryId = qrcCategoryId;
    }

    //    public CrmQrcCategoriesPojo getCrmQrcCategories()
    //    {
    //        return this.crmQrcCategoriesPojo;
    //    }
    //
    //    public void setCrmQrcCategories( CrmQrcCategoriesPojo crmQrcCategoriesPojo )
    //    {
    //        this.crmQrcCategoriesPojo = crmQrcCategoriesPojo;
    //    }
    public String getServiceName()
    {
        return this.serviceName;
    }

    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public String getActionTaken()
    {
        return actionTaken;
    }

    public void setActionTaken( String inActionTaken )
    {
        actionTaken = inActionTaken;
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

    public void setLastModifiedTime( Date lastModifiedTime )
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }
    //    public Set<CrmQrcResolutionCodePojo> getCrmQrcResolutionCodes()
    //    {
    //        return crmQrcResolutionCodes;
    //    }
    //
    //    public void setCrmQrcResolutionCodes( Set<CrmQrcResolutionCodePojo> inCrmQrcResolutionCodes )
    //    {
    //        crmQrcResolutionCodes = inCrmQrcResolutionCodes;
    //    }
}
